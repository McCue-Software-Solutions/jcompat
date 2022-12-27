package pl.minus1ms.jcompat.check.impl;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import pl.minus1ms.jcompat.Util;
import pl.minus1ms.jcompat.check.CheckType;

public class FieldCheck extends ClassCheck {
    private final FieldNode f1;
    private final FieldNode f2;

    public FieldCheck(ClassNode c1, ClassNode c2, FieldNode f1, FieldNode f2, CheckType checkType) {
        super(c1, c2, checkType);
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        if (getCheckType() == CheckType.REMOVED)
            return "[FieldCheck] field \"" + f1.name + "\" in class \"" + getC1().name + "\" was removed";
        if (getCheckType() == CheckType.ACCESS_CHANGE) {
            return "[FieldCheck] access change of field \"" + f1.name + "\" in class \"" + getC1().name + "\" from " + Util.getAccessModifier(f1.access) + " to " + Util.getAccessModifier(f2.access);
        }

        return super.toString();
    }
}
