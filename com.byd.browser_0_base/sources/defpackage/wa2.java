package defpackage;

import android.content.Context;

/* JADX INFO: compiled from: RenameDialogManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class wa2 {
    public final ta2 a;
    public final xa2 b;
    public String c;
    public String d;
    public a f;
    public int e = 5;
    public int g = 0;

    /* JADX INFO: compiled from: RenameDialogManager.java */
    @FunctionalInterface
    public interface a {
        void r(String str, va2 va2Var);
    }

    public wa2(Context context, bi1 bi1Var) {
        this.a = new ta2(context, bi1Var, new va2(this, 0), new so(28, this));
        this.b = new xa2(context, bi1Var, new wb(22, this), new c3(18, this));
    }

    public final void a(int i, int i2) {
        bi1 bi1Var;
        xa2 xa2Var = this.b;
        ta2 ta2Var = this.a;
        switch (i) {
            case 0:
                int i3 = this.g;
                if (i3 == 4 || i3 == 6) {
                    bi1 bi1Var2 = xa2Var.a;
                    if (bi1Var2 != null) {
                        bi1Var2.b(i2, xa2Var.b);
                    }
                } else if ((i3 == 1 || i3 == 3) && (bi1Var = ta2Var.a) != null) {
                    bi1Var.b(i2, ta2Var.b);
                }
                break;
            case 1:
                String str = this.c;
                ta2Var.a.j(ta2Var.b, 1, true);
                ta2Var.c.a(str);
                break;
            case 2:
                bi1 bi1Var3 = ta2Var.a;
                if (bi1Var3 != null) {
                    bi1Var3.b(i2, ta2Var.b);
                }
                break;
            case 3:
                ta2Var.c.b(this.e, this.d);
                bi1 bi1Var4 = ta2Var.a;
                if (!bi1Var4.f()) {
                    bi1Var4.j(ta2Var.b, 1, true);
                }
                break;
            case 4:
                xa2Var.a.j(xa2Var.b, 1, true);
                bi1 bi1Var5 = ta2Var.a;
                if (bi1Var5 != null) {
                    bi1Var5.b(i2, ta2Var.b);
                }
                break;
            case 5:
                bi1 bi1Var6 = xa2Var.a;
                if (bi1Var6 != null) {
                    bi1Var6.b(i2, xa2Var.b);
                }
                String str2 = this.d;
                ta2Var.a.j(ta2Var.b, 1, true);
                ta2Var.c.a(str2);
                break;
            case 6:
                bi1 bi1Var7 = xa2Var.a;
                if (bi1Var7 != null) {
                    bi1Var7.b(i2, xa2Var.b);
                }
                ta2Var.c.b(this.e, this.d);
                bi1 bi1Var8 = ta2Var.a;
                if (!bi1Var8.f()) {
                    bi1Var8.j(ta2Var.b, 1, true);
                }
                break;
        }
        this.g = i;
    }
}
