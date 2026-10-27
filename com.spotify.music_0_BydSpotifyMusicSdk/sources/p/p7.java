package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class p7 implements p3, mz1 {
    public boolean c = true;
    public final /* synthetic */ Object e;

    public p7(StringBuilder sb) {
        this.e = sb;
    }

    @Override // p.p3
    public final boolean a(View view) {
        u4.z(this.e);
        throw null;
    }

    @Override // p.mz1
    public final void b(int i, lz1 lz1Var) {
        boolean z = this.c;
        Object obj = this.e;
        if (z) {
            this.c = false;
        } else {
            ((StringBuilder) obj).append(", ");
        }
        ((StringBuilder) obj).append(i);
    }
}
