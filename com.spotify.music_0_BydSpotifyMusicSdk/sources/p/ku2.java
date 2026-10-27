package p;

/* JADX INFO: loaded from: classes.dex */
public interface ku2 {
    default iu2 a(Class cls) {
        throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
    }

    default iu2 b(Class cls, yl1 yl1Var) {
        return a(cls);
    }
}
