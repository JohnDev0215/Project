package kotlin.reflect.jvm.internal.impl.descriptors;

/* JADX INFO: compiled from: ClassKind.kt */
/* JADX INFO: loaded from: classes7.dex */
public enum ClassKind {
    CLASS,
    INTERFACE,
    ENUM_CLASS,
    ENUM_ENTRY,
    ANNOTATION_CLASS,
    OBJECT;

    public final boolean isSingleton() {
        return this == OBJECT || this == ENUM_ENTRY;
    }
}
