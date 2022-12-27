package pl.minus1ms.jcompat.check.impl;

import pl.minus1ms.jcompat.check.CheckType;

public class Check {
    private final CheckType checkType;

    public Check(CheckType checkType) {
        this.checkType = checkType;
    }

    public CheckType getCheckType() {
        return checkType;
    }
}
