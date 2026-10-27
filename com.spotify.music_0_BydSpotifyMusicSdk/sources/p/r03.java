package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class r03 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;

    public /* synthetic */ r03(i13 i13Var, int i) {
        this.c = i;
        this.e = i13Var;
    }

    @Override // p.i13
    public final Object a() {
        int i = this.c;
        i13 i13Var = this.e;
        switch (i) {
            case 0:
                return new com.google.android.play.core.assetpacks.e(h13.b(i13Var));
            case 1:
                return new com.google.android.play.core.assetpacks.l((com.google.android.play.core.assetpacks.c) i13Var.a());
            default:
                Context context = (Context) i13Var.a();
                t23 t23Var = new t23();
                context.getPackageName();
                return t23Var;
        }
    }
}
