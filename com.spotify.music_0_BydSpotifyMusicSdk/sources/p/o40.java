package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class o40 implements vl0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;

    public /* synthetic */ o40(xy1 xy1Var, vk0 vk0Var, vk0 vk0Var2, int i) {
        this.a = i;
        this.b = xy1Var;
        this.c = vk0Var;
        this.d = vk0Var2;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.d;
        xy1 xy1Var2 = this.c;
        xy1 xy1Var3 = this.b;
        switch (i) {
            case 0:
                return new n40((Context) xy1Var3.get(), (yt) xy1Var2.get(), (yt) xy1Var.get());
            default:
                return new n72(((Integer) xy1Var.get()).intValue(), (Context) xy1Var3.get(), (String) xy1Var2.get());
        }
    }
}
