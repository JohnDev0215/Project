package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.loginflowrolloutapi.LoginFlowRollout;
import com.spotify.connectivity.productstate.ProductStateUpdateApi;
import io.reactivex.rxjava3.functions.g;
import p.cy1;
import p.dy1;
import p.r00;
import p.sb2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateUpdateService implements ProductStateUpdateApi, sb2 {
    private io.reactivex.rxjava3.disposables.a disposable;

    public ProductStateUpdateService(LoginFlowRollout loginFlowRollout, final r00 r00Var, cy1 cy1Var) {
        v03.j(loginFlowRollout, "loginFlowRollout");
        v03.j(r00Var, "coreFullSessionApi");
        v03.j(cy1Var, "productStateProvider");
        this.disposable = new io.reactivex.rxjava3.disposables.a();
        if (loginFlowRollout.getEnableNewLoginFlow()) {
            this.disposable.c(cy1Var.a().subscribe(new g() { // from class: com.spotify.connectivity.productstateesperanto.ProductStateUpdateService.1
                public final void accept(dy1 dy1Var) {
                }

                @Override // io.reactivex.rxjava3.functions.g
                public /* bridge */ /* synthetic */ void accept(Object obj) {
                    u4.z(obj);
                    accept((dy1) null);
                }
            }));
        }
    }

    /* JADX INFO: renamed from: getApi, reason: merged with bridge method [inline-methods] */
    public ProductStateUpdateApi m191getApi() {
        return this;
    }

    @Override // p.sb2
    public void shutdown() {
        this.disposable.dispose();
    }
}
