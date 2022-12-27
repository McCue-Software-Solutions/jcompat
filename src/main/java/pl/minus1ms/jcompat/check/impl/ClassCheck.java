package pl.minus1ms.jcompat.check.impl;

import org.objectweb.asm.tree.ClassNode;
import pl.minus1ms.jcompat.Util;
import pl.minus1ms.jcompat.check.CheckType;

public class ClassCheck extends Check {
    private final ClassNode c1;
    private final ClassNode c2;

    public ClassCheck(ClassNode c1, ClassNode c2, CheckType checkType) {
        super(checkType);
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public String name() {
        return "[ClassCheck]";
    }

    @Override
    public String oName() {
        return "class \"" + c1.name + "\"";
    }

    @Override
    public String accOld() {
        return Util.getAccessModifier(c1.access);
    }

    @Override
    public String accNew() {
        return Util.getAccessModifier(c2.access);
    }

    public ClassNode getC1() {
        return c1;
    }
}
