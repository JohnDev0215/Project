package defpackage;

import com.google.protobuf.c0;
import com.google.protobuf.l;
import java.nio.charset.Charset;
import java.util.HashSet;

/* JADX INFO: compiled from: ManifestSchemaFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class rb1 {
    public static final a b = new a();
    public final ah1 a;

    /* JADX INFO: compiled from: ManifestSchemaFactory.java */
    public static class a implements ah1 {
        @Override // defpackage.ah1
        public final zg1 a(Class<?> cls) {
            throw new IllegalStateException("This should never be called.");
        }

        @Override // defpackage.ah1
        public final boolean b(Class<?> cls) {
            return false;
        }
    }

    /* JADX INFO: compiled from: ManifestSchemaFactory.java */
    public static class b implements ah1 {
        public final ah1[] a;

        public b(ah1... ah1VarArr) {
            this.a = ah1VarArr;
        }

        @Override // defpackage.ah1
        public final zg1 a(Class<?> cls) {
            for (ah1 ah1Var : this.a) {
                if (ah1Var.b(cls)) {
                    return ah1Var.a(cls);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
        }

        @Override // defpackage.ah1
        public final boolean b(Class<?> cls) {
            for (ah1 ah1Var : this.a) {
                if (ah1Var.b(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    public rb1() {
        ah1 ah1Var;
        ah1[] ah1VarArr = new ah1[2];
        ah1VarArr[0] = fs0.a;
        try {
            HashSet hashSet = l.a;
            ah1Var = (ah1) l.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            ah1Var = b;
        }
        ah1VarArr[1] = ah1Var;
        b bVar = new b(ah1VarArr);
        Charset charset = c0.a;
        this.a = bVar;
    }
}
