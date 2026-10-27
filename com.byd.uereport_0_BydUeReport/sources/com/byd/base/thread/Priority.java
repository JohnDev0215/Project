package com.byd.base.thread;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'NORMAL' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public final class Priority {
    private static final /* synthetic */ Priority[] $VALUES;
    public static final Priority HIGH;
    public static final Priority LOW;
    public static final Priority NORMAL;
    private int priority;

    static {
        Priority priority = new Priority("HIGH", 0, 0);
        HIGH = priority;
        Priority priority2 = new Priority("NORMAL", 1, priority.value() + 1);
        NORMAL = priority2;
        Priority priority3 = new Priority("LOW", 2, priority2.value() + 1);
        LOW = priority3;
        $VALUES = new Priority[]{priority, priority2, priority3};
    }

    private Priority(String str, int i8, int i9) {
        this.priority = i9;
    }

    public static Priority valueOf(String str) {
        return (Priority) Enum.valueOf(Priority.class, str);
    }

    public static Priority[] values() {
        return (Priority[]) $VALUES.clone();
    }

    public int value() {
        return this.priority;
    }
}
