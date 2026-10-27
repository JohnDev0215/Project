package p;

/* JADX INFO: loaded from: classes.dex */
public final class g01 implements uj1 {
    public static final g01 a = new g01();

    @Override // p.uj1
    public final tj1 a(Class cls) {
        if (!com.google.protobuf.i.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
        try {
            return (tj1) com.google.protobuf.i.getDefaultInstance(cls.asSubclass(com.google.protobuf.i.class)).buildMessageInfo();
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
        }
    }

    @Override // p.uj1
    public final boolean b(Class cls) {
        return com.google.protobuf.i.class.isAssignableFrom(cls);
    }
}
