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
    public static final Priority HIGH;
    public static final Priority LOW;
    public static final Priority NORMAL;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ Priority[] f538b;
    private int priority;

    static {
        Priority priority = new Priority("HIGH", 0, 0);
        HIGH = priority;
        Priority priority2 = new Priority("NORMAL", 1, priority.value() + 1);
        NORMAL = priority2;
        Priority priority3 = new Priority("LOW", 2, priority2.value() + 1);
        LOW = priority3;
        f538b = new Priority[]{priority, priority2, priority3};
    }

    public Priority(String str, int i, int i2) {
        this.priority = i2;
    }

    public static Priority valueOf(String str) {
        return (Priority) Enum.valueOf(Priority.class, str);
    }

    public static Priority[] values() {
        return (Priority[]) f538b.clone();
    }

    public int value() {
        return this.priority;
    }
}
