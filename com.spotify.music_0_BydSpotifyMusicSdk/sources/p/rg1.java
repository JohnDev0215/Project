package p;

import android.os.Bundle;
import android.service.media.MediaBrowserService;
import com.spotify.automotive.base.service.AAMMediaBrowserService;

/* JADX INFO: loaded from: classes.dex */
public final class rg1 extends og1 {
    public final /* synthetic */ sg1 k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public rg1(sg1 sg1Var, bh1 bh1Var) {
        super(sg1Var, bh1Var);
        this.k = sg1Var;
    }

    @Override // android.service.media.MediaBrowserService
    public final void onLoadChildren(String str, MediaBrowserService.Result result, Bundle bundle) {
        bi1.a(bundle);
        sg1 sg1Var = this.k;
        bh1 bh1Var = sg1Var.f;
        jg1 jg1Var = bh1Var.k;
        qg1 qg1Var = new qg1(sg1Var, str, new wg1(result), bundle);
        com.spotify.automotive.base.service.a aVar = ((AAMMediaBrowserService) bh1Var).q;
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        aVar.d(bundle, qg1Var, str);
        sg1Var.f.getClass();
    }
}
