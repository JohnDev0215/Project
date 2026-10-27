package p;

/* JADX INFO: loaded from: classes.dex */
public final class b51 implements ul0, u81 {
    public final Object a;

    public b51(Object obj) {
        this.a = obj;
    }

    public static b51 a(Object obj) {
        if (obj != null) {
            return new b51(obj);
        }
        throw new NullPointerException("instance cannot be null");
    }

    @Override // p.xy1
    public final Object get() {
        return this.a;
    }
}
