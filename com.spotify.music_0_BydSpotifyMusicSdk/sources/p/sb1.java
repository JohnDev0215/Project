package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class sb1 {
    public final p01 c;
    public boolean e;
    public int k = -1;
    public final /* synthetic */ androidx.lifecycle.b l;

    public sb1(androidx.lifecycle.b bVar, p01 p01Var) {
        this.l = bVar;
        this.c = p01Var;
    }

    public final void c(boolean z) {
        if (z == this.e) {
            return;
        }
        this.e = z;
        int i = z ? 1 : -1;
        androidx.lifecycle.b bVar = this.l;
        int i2 = bVar.c;
        bVar.c = i + i2;
        if (!bVar.d) {
            bVar.d = true;
            while (true) {
                try {
                    int i3 = bVar.c;
                    if (i2 == i3) {
                        break;
                    } else {
                        i2 = i3;
                    }
                } finally {
                    bVar.d = false;
                }
            }
        }
        if (this.e) {
            bVar.c(this);
        }
    }

    public void f() {
    }

    public abstract boolean g();
}
