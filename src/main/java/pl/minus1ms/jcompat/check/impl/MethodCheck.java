package pl.minus1ms.jcompat.check.impl;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import pl.minus1ms.jcompat.Util;
import pl.minus1ms.jcompat.check.CheckType;

public class MethodCheck extends ClassCheck {
    private final MethodNode m1;
    private final MethodNode m2;

    public MethodCheck(ClassNode c1, ClassNode c2, MethodNode m1, MethodNode m2, CheckType checkType) {
        super(c1, c2, checkType);
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    public String name() {
        return "[MethodCheck]";
    }

    @Override
    public String oName() {
        return "method \"" + getC1().name + " " + m1.name + "()\"";
    }

    @Override
    public String accOld() {
        return Util.getAccessModifier(m1.access);
    }

    @Override
    public String accNew() {
        return Util.getAccessModifier(m1.access);
    }
}
