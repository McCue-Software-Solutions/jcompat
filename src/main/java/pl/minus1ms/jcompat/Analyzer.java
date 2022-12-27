package pl.minus1ms.jcompat;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;
import pl.minus1ms.jcompat.check.CheckType;
import pl.minus1ms.jcompat.check.impl.Check;
import pl.minus1ms.jcompat.check.impl.ClassCheck;
import pl.minus1ms.jcompat.check.impl.FieldCheck;
import pl.minus1ms.jcompat.check.impl.MethodCheck;

import java.util.ArrayList;

public class Analyzer {
    public void analyze(Loader loader) {
        ArrayList<Check> checks = new ArrayList<>();

        loader.getClasses1().values().forEach(c1 -> {
            if (!loader.getClasses2().containsKey(c1.name)) {
                checks.add(new ClassCheck(c1, null, CheckType.REMOVED));
                return;
            }
            ClassNode c2 = loader.getClasses2().get(c1.name);
            if (!Util.isAccessEqual(c1.access, c2.access)) checks.add(new ClassCheck(c1, c2, CheckType.ACCESS_CHANGE));
            for (FieldNode f1 : c1.fields) {
                if (c2.fields.stream().noneMatch(f2 -> f2.name.equals(f1.name))) {
                    checks.add(new FieldCheck(c1, c2, f1, null, CheckType.REMOVED));
                    continue;
                }
                FieldNode f2 = c2.fields.stream().filter(f -> f.name.equals(f1.name)).findAny().get();
                if (!Util.isAccessEqual(f1.access, f2.access)) checks.add(new FieldCheck(c1, c2, f1, f2, CheckType.ACCESS_CHANGE));
            }
            for (MethodNode m1 : c1.methods) {
                if (c2.methods.stream().noneMatch(m2 -> m2.name.equals(m1.name))) {
                    checks.add(new MethodCheck(c1, c2, m1, null, CheckType.REMOVED));
                    continue;
                }
                MethodNode m2 = c2.methods.stream().filter(m -> m.name.equals(m1.name)).findAny().get();
                if (!Util.isAccessEqual(m1.access, m2.access)) checks.add(new MethodCheck(c1, c2, m1, m2, CheckType.ACCESS_CHANGE));
            }

            checks.forEach(System.out::println);
        });
    }
}
