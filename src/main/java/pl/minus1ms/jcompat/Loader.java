package pl.minus1ms.jcompat;

import me.coley.cafedude.InvalidClassException;
import me.coley.cafedude.classfile.ClassFile;
import me.coley.cafedude.io.ClassFileReader;
import me.coley.cafedude.io.ClassFileWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.commons.JSRInlinerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Loader {
    private final Map<String, ClassNode> classes1 = new HashMap<>();
    private final Map<String, ClassNode> classes2 = new HashMap<>();

    public void load(Path file1, Path file2) {
        loadInput(file1, classes1);
        loadInput(file2, classes2);
    }

    private void loadInput(Path input, Map<String, ClassNode> loadTo) {
        System.out.println("Loading input file...");

        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(input))) {
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                if (ze.isDirectory() || ze.getName().endsWith(".class/")) {
                    continue;
                }

                loadInputFile(ze.getName(), zis.readAllBytes(), loadTo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loaded input file!");
    }

    private void loadInputFile(String name, byte[] data, Map<String, ClassNode> loadTo) {
        if (!isClass(name, data)) return;

        try {
            ClassReader reader;
            ClassNode c;
            try {
                reader = new ClassReader(data);
                c = new ClassNode();
                reader.accept(c, ClassReader.SKIP_FRAMES);
            } catch (Throwable t) {
                ClassFileReader cfr = new ClassFileReader();
                cfr.setDropForwardVersioned(true);
                ClassFile cf = cfr.read(data);
                ClassFileWriter cfw = new ClassFileWriter();
                byte[] fixedData = cfw.write(cf);
                reader = new ClassReader(fixedData);
                c = new ClassNode();
                reader.accept(c, ClassReader.SKIP_FRAMES);
            }

            for (int i = 0; i < c.methods.size(); i++) {
                MethodNode m = c.methods.get(i);
                JSRInlinerAdapter adapter = new JSRInlinerAdapter(m, m.access, m.name, m.desc, m.signature, m.exceptions.toArray(new String[0]));
                m.accept(adapter);
                c.methods.set(i, adapter);
            }

            loadTo.put(c.name, c);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | InvalidClassException x) {
            throw new IllegalStateException("Could not parse {} (is it a class file?)");
        }
    }

    private boolean isClass(String name, byte[] data) {
        return data.length >= 4 && String
                .format("%02X%02X%02X%02X", data[0], data[1], data[2], data[3]).equals("CAFEBABE") && (
                name.endsWith(".class") || name.endsWith(".class/"));
    }

    public Map<String, ClassNode> getClasses1() {
        return classes1;
    }

    public Map<String, ClassNode> getClasses2() {
        return classes2;
    }
}
