package p;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class io2 implements vl0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;

    public /* synthetic */ io2(xy1 xy1Var, xy1 xy1Var2, vl0 vl0Var, xy1 xy1Var3, xy1 xy1Var4, int i) {
        this.a = i;
        this.b = xy1Var;
        this.c = xy1Var2;
        this.d = vl0Var;
        this.e = xy1Var3;
        this.f = xy1Var4;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.f;
        xy1 xy1Var2 = this.e;
        xy1 xy1Var3 = this.d;
        xy1 xy1Var4 = this.c;
        xy1 xy1Var5 = this.b;
        switch (i) {
            case 0:
                return new ho2((yt) xy1Var5.get(), (yt) xy1Var4.get(), (a72) xy1Var3.get(), (jr2) xy1Var2.get(), (sx2) xy1Var.get());
            case 1:
                return new t70((Executor) xy1Var5.get(), (bk1) xy1Var4.get(), (hy2) xy1Var3.get(), (jk0) xy1Var2.get(), (wh2) xy1Var.get());
            default:
                yt ytVar = (yt) xy1Var5.get();
                yt ytVar2 = (yt) xy1Var4.get();
                Object obj = xy1Var3.get();
                return new j62(ytVar, ytVar2, (bi) obj, (n72) xy1Var2.get(), this.f);
        }
    }
}
