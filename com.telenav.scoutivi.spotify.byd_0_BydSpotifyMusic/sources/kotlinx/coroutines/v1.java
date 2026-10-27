package kotlinx.coroutines;

/* JADX INFO: compiled from: MainCoroutineDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class v1 extends e0 {
    public abstract v1 d0();

    protected final String e0() {
        v1 v1VarD0;
        v1 v1VarB = u0.b();
        if (this == v1VarB) {
            return "Dispatchers.Main";
        }
        try {
            v1VarD0 = v1VarB.d0();
        } catch (UnsupportedOperationException unused) {
            v1VarD0 = null;
        }
        if (this == v1VarD0) {
            return "Dispatchers.Main.immediate";
        }
        return null;
    }

    @Override // kotlinx.coroutines.e0
    public String toString() {
        String strE0 = e0();
        if (strE0 != null) {
            return strE0;
        }
        return m0.a(this) + '@' + m0.b(this);
    }
}
