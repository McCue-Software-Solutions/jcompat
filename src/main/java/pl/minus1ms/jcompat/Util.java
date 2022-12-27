package pl.minus1ms.jcompat;

import static org.objectweb.asm.Opcodes.*;

public class Util {
    public static boolean isAccessEqual(int acc1, int acc2) {
        return Util.getAccessModifier(acc1).equals(Util.getAccessModifier(acc2));
    }

    public static String getAccessModifier(int access) {
        StringBuilder stringBuilder = new StringBuilder("{");

        if (isPublic(access)) stringBuilder.append("public ");
        if (isPrivate(access)) stringBuilder.append("private ");
        if (isProtected(access)) stringBuilder.append("protected ");
        if (isFinal(access)) stringBuilder.append("final ");
        if (isInterface(access)) stringBuilder.append("interface ");
        if (isAbstract(access)) stringBuilder.append("abstract ");
        if (isAnnotation(access)) stringBuilder.append("annotation ");
        if (isEnum(access)) stringBuilder.append("enum ");
        if (isModule(access)) stringBuilder.append("module ");

        if (stringBuilder.toString().equals("{")) stringBuilder.append("no modifiers (package-private) ");

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder + "}";
    }

    public static boolean isPublic(int access) {
        return isAccess(access, ACC_PUBLIC);
    }

    public static boolean isPrivate(int access) {
        return isAccess(access, ACC_PRIVATE);
    }

    public static boolean isProtected(int access) {
        return isAccess(access, ACC_PROTECTED);
    }

    public static boolean isFinal(int access) {
        return isAccess(access, ACC_FINAL);
    }

    public static boolean isInterface(int access) {
        return isAccess(access, ACC_INTERFACE);
    }

    public static boolean isAbstract(int access) {
        return isAccess(access, ACC_ABSTRACT);
    }

    public static boolean isAnnotation(int access) {
        return isAccess(access, ACC_ANNOTATION);
    }

    public static boolean isEnum(int access) {
        return isAccess(access, ACC_ENUM);
    }

    public static boolean isModule(int access) {
        return isAccess(access, ACC_MODULE);
    }

    private static boolean isAccess(int access, int opcode) {
        return (access & opcode) != 0;
    }
}
