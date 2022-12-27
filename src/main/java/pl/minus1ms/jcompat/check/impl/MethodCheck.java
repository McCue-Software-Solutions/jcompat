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
    public String toString() {
        if (getCheckType() == CheckType.REMOVED)
            return "[MethodCheck] method \"" + m1.name + "\" in class \"" + getC1().name + "\" was removed";
        if (getCheckType() == CheckType.ACCESS_CHANGE) {
            return "[MethodCheck] access change of method \"" + m1.name + "\" in class \"" + getC1().name + "\" from " + Util.getAccessModifier(m1.access) + " to " + Util.getAccessModifier(m2.access);
        }

        return super.toString();
    }
}
