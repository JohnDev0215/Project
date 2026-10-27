package a.n;

/* JADX INFO: loaded from: classes.dex */
public class b0 implements z {
    @Override // a.n.z
    public <T extends x> T a(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot create an instance of " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Cannot create an instance of " + cls, e3);
        }
    }
}
