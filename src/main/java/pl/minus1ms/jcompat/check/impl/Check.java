package pl.minus1ms.jcompat.check.impl;

import pl.minus1ms.jcompat.check.CheckType;

public abstract class Check {
    private final CheckType checkType;

    public Check(CheckType checkType) {
        this.checkType = checkType;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public abstract String name();

    public abstract String oName();

    public abstract String accOld();

    public abstract String accNew();

    public String generateMessage() {
        StringBuilder result = new StringBuilder(name() + " ");
        if (checkType == CheckType.REMOVED) {
            result.append(oName()).append(" was removed.");
        } else if (getCheckType() == CheckType.ACCESS_CHANGE) {
            result.append("access change of ").append(oName()).append(" from ").append(accOld()).append(" to ").append(accNew());
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return generateMessage();
    }
}
