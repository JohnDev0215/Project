package androidx.emoji2.text;

import android.graphics.Typeface;
import android.util.SparseArray;
import defpackage.kh3;
import defpackage.mh1;
import defpackage.n3;
import defpackage.nh1;

/* JADX INFO: compiled from: MetadataRepo.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public final nh1 a;
    public final char[] b;
    public final a c = new a(1024);
    public final Typeface d;

    /* JADX INFO: compiled from: MetadataRepo.java */
    public static class a {
        public final SparseArray<a> a;
        public kh3 b;

        public a() {
            this(1);
        }

        public final void a(kh3 kh3Var, int i, int i2) {
            int iA = kh3Var.a(i);
            SparseArray<a> sparseArray = this.a;
            a aVar = sparseArray == null ? null : sparseArray.get(iA);
            if (aVar == null) {
                aVar = new a();
                sparseArray.put(kh3Var.a(i), aVar);
            }
            if (i2 > i) {
                aVar.a(kh3Var, i + 1, i2);
            } else {
                aVar.b = kh3Var;
            }
        }

        public a(int i) {
            this.a = new SparseArray<>(i);
        }
    }

    public h(Typeface typeface, nh1 nh1Var) {
        int i;
        int i2;
        this.d = typeface;
        this.a = nh1Var;
        int iA = nh1Var.a(6);
        if (iA != 0) {
            int i3 = iA + nh1Var.a;
            i = nh1Var.b.getInt(nh1Var.b.getInt(i3) + i3);
        } else {
            i = 0;
        }
        this.b = new char[i * 2];
        int iA2 = nh1Var.a(6);
        if (iA2 != 0) {
            int i4 = iA2 + nh1Var.a;
            i2 = nh1Var.b.getInt(nh1Var.b.getInt(i4) + i4);
        } else {
            i2 = 0;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            kh3 kh3Var = new kh3(this, i5);
            mh1 mh1VarC = kh3Var.c();
            int iA3 = mh1VarC.a(4);
            Character.toChars(iA3 != 0 ? mh1VarC.b.getInt(iA3 + mh1VarC.a) : 0, this.b, i5 * 2);
            n3.s("invalid metadata codepoint length", kh3Var.b() > 0);
            this.c.a(kh3Var, 0, kh3Var.b() - 1);
        }
    }
}
