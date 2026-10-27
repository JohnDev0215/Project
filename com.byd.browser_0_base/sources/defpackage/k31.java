package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: JavaType.java */
/* JADX INFO: loaded from: classes.dex */
public enum k31 {
    VOID(Void.class, null),
    INT(Integer.class, 0),
    LONG(Long.class, 0L),
    FLOAT(Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.class, Boolean.FALSE),
    STRING(String.class, ""),
    BYTE_STRING(lk.class, lk.f),
    ENUM(Integer.class, null),
    MESSAGE(Object.class, null);

    public final Object e;

    k31(Class cls, Serializable serializable) {
        this.e = serializable;
    }
}
