package p;

import android.service.media.MediaBrowserService;

/* JADX INFO: loaded from: classes.dex */
public abstract class og1 extends mg1 {
    public final /* synthetic */ pg1 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public og1(pg1 pg1Var, bh1 bh1Var) {
        super(pg1Var, bh1Var);
        this.e = pg1Var;
    }

    @Override // android.service.media.MediaBrowserService
    public final void onLoadItem(String str, MediaBrowserService.Result result) {
        wg1 wg1Var = new wg1(result);
        pg1 pg1Var = this.e;
        pg1Var.getClass();
        lg1 lg1Var = new lg1(str, wg1Var, 1);
        bh1 bh1Var = pg1Var.e;
        jg1 jg1Var = bh1Var.k;
        bh1Var.b(str, lg1Var);
    }
}
