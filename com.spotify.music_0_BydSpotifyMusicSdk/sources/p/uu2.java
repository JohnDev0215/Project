package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class uu2 extends xu2 {
    public final /* synthetic */ int a;
    public boolean b;
    public int c;
    public final /* synthetic */ Object d;

    public uu2(vu2 vu2Var) {
        this.a = 0;
        this.d = vu2Var;
        this.b = false;
        this.c = 0;
    }

    @Override // p.wu2
    public final void a() {
        int i = this.a;
        Object obj = this.d;
        switch (i) {
            case 0:
                int i2 = this.c + 1;
                this.c = i2;
                vu2 vu2Var = (vu2) obj;
                if (i2 == vu2Var.a.size()) {
                    wu2 wu2Var = vu2Var.d;
                    if (wu2Var != null) {
                        wu2Var.a();
                    }
                    this.c = 0;
                    this.b = false;
                    vu2Var.e = false;
                }
                break;
            default:
                if (!this.b) {
                    ((hm2) obj).a.setVisibility(this.c);
                }
                break;
        }
    }

    @Override // p.xu2, p.wu2
    public final void b(View view) {
        switch (this.a) {
            case 1:
                this.b = true;
                break;
        }
    }

    @Override // p.xu2, p.wu2
    public final void c() {
        int i = this.a;
        Object obj = this.d;
        switch (i) {
            case 0:
                if (!this.b) {
                    this.b = true;
                    wu2 wu2Var = ((vu2) obj).d;
                    if (wu2Var != null) {
                        wu2Var.c();
                    }
                    break;
                }
                break;
            default:
                ((hm2) obj).a.setVisibility(0);
                break;
        }
    }

    public uu2(hm2 hm2Var, int i) {
        this.a = 1;
        this.d = hm2Var;
        this.c = i;
        this.b = false;
    }
}
