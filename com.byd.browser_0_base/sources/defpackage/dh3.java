package defpackage;

/* JADX INFO: compiled from: TypeIntrinsics.java */
/* JADX INFO: loaded from: classes.dex */
public final class dh3 {
    public static void a(nr0 nr0Var) {
        if (nr0Var != null) {
            if ((nr0Var instanceof vr0 ? ((vr0) nr0Var).b() : nr0Var instanceof yq0 ? 0 : nr0Var instanceof jr0 ? 1 : 2) == 2) {
                return;
            }
            ClassCastException classCastException = new ClassCastException(nr0Var.getClass().getName().concat(" cannot be cast to kotlin.jvm.functions.Function2"));
            i21.f(dh3.class.getName(), classCastException);
            throw classCastException;
        }
    }
}
