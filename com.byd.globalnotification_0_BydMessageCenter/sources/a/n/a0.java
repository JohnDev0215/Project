package a.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 extends c0 implements z {
    @Override // a.n.z
    public <T extends x> T a(Class<T> cls) {
        throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
    }

    public abstract <T extends x> T c(String str, Class<T> cls);
}
