package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: GeneratedMessageInfoFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class fs0 implements ah1 {
    public static final fs0 a = new fs0();

    @Override // defpackage.ah1
    public final zg1 a(Class<?> cls) {
        if (!z.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
        try {
            return (zg1) z.E(cls.asSubclass(z.class)).C(z.f.BUILD_MESSAGE_INFO);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
        }
    }

    @Override // defpackage.ah1
    public final boolean b(Class<?> cls) {
        return z.class.isAssignableFrom(cls);
    }
}
