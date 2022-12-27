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
    public String toString() {
        if (getCheckType() == CheckType.REMOVED)
            return "[ClassCheck] class \"" + c1.name + "\" was removed";
        if (getCheckType() == CheckType.ACCESS_CHANGE) {
            return "[ClassCheck] access change of class \"" + c1.name + "\" from " + Util.getAccessModifier(c1.access) + " to " + Util.getAccessModifier(c2.access);
        }

        return super.toString();
    }

    public ClassNode getC1() {
        return c1;
    }
}
