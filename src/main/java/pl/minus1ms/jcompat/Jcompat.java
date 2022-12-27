package pl.minus1ms.jcompat;

import java.nio.file.Path;

public final class Jcompat {
    private final Path file1;
    private final Path file2;
    private final Loader loader;
    private final Analyzer analyzer;

    Jcompat(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: diffcheck <file1> <file2>");
            System.exit(1);
        }

        file1 = Path.of(args[0]).toAbsolutePath();
        file2 = Path.of(args[1]).toAbsolutePath();

        loader = new Loader();
        analyzer = new Analyzer();
    }

    public static void main(String[] args) {
        new Jcompat(args).run();
    }

    private void run() {
        loader.load(file1, file2);
        analyzer.analyze(loader);
    }
}
