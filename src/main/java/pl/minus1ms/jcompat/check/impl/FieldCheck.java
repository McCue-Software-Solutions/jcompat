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
    public String name() {
        return "[FieldCheck]";
    }

    @Override
    public String oName() {
        return "field \"" + getC1().name + " " + f1.name + "\"";
    }

    @Override
    public String accOld() {
        return Util.getAccessModifier(f1.access);
    }

    @Override
    public String accNew() {
        return Util.getAccessModifier(f2.access);
    }
}
