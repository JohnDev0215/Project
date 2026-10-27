package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.OfflineStateApi;
import io.reactivex.rxjava3.functions.g;
import p.af;
import p.b62;
import p.ff;
import p.sb2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineStateService implements sb2, OfflineStateApi {
    private final io.reactivex.rxjava3.disposables.a disposable;

    public OfflineStateService(af afVar, b62 b62Var) {
        v03.j(afVar, "authTriggerApi");
        v03.j(b62Var, "rxSettings");
        io.reactivex.rxjava3.disposables.a aVar = new io.reactivex.rxjava3.disposables.a();
        this.disposable = aVar;
        aVar.c(afVar.a().subscribe(new g(b62Var) { // from class: com.spotify.connectivity.platformconnectiontype.OfflineStateService.1
            final /* synthetic */ b62 $rxSettings;

            @Override // io.reactivex.rxjava3.functions.g
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                u4.z(obj);
                accept((ff) null);
            }

            public final void accept(ff ffVar) {
                throw null;
            }
        }));
    }

    /* JADX INFO: renamed from: getApi, reason: merged with bridge method [inline-methods] */
    public OfflineStateApi m163getApi() {
        return this;
    }

    @Override // p.sb2
    public void shutdown() {
        this.disposable.dispose();
    }
}
