package com.spotify.automotive.auth;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.net.Uri;
import com.google.common.base.Optional;
import com.spotify.automotive.accountmanager.model.AccountManagerModel;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.automotive.baseui.login.AAMFullscreenDialogActivity;
import com.spotify.base.java.logging.Logger;
import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.connectivity.connectiontypeflags.ConnectionTypePropertiesWriter;
import com.spotify.connectivity.platformconnectiontype.ConnectivityMonitorImpl;
import com.spotify.connectivity.platformconnectiontype.InternetConnectivityObservableImpl;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.f;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.operators.completable.j;
import io.reactivex.rxjava3.schedulers.e;
import java.util.Objects;
import kotlin.collections.c;
import p.aj;
import p.as2;
import p.aw;
import p.c7;
import p.cf;
import p.ck;
import p.dd1;
import p.du;
import p.ed1;
import p.ef2;
import p.fu;
import p.ge2;
import p.gq1;
import p.hk;
import p.i10;
import p.if2;
import p.ix;
import p.ju;
import p.ki1;
import p.ku;
import p.le2;
import p.lj;
import p.m60;
import p.mj2;
import p.mq2;
import p.ot;
import p.oz0;
import p.pr1;
import p.pt;
import p.qo;
import p.rm;
import p.sd1;
import p.td1;
import p.tx1;
import p.u11;
import p.u4;
import p.up1;
import p.ux1;
import p.v03;
import p.vp1;
import p.vs1;
import p.vx1;
import p.w1;
import p.wc1;
import p.wk2;
import p.x1;
import p.xi;
import p.xw1;
import p.y22;
import p.z1;
import p.z20;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements g, ObservableOnSubscribe, aw, oz0 {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:99:0x0419, code lost:
    
        if (r0.isConnectedOrConnecting() != false) goto L100;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02fc  */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v24, types: [p.b33] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v29, types: [java.lang.Boolean, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r3v24, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [boolean] */
    @Override // p.aw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(p.jm2 r42) {
        /*
            Method dump skipped, instruction units count: 1274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.auth.a.a(p.jm2):java.lang.Object");
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        LoginState loginState = LoginState.k;
        int i = 2;
        int i2 = this.c;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((b) obj2).d(loginState);
                return;
            case 1:
                LoginState loginState2 = (LoginState) obj;
                sd1 sd1Var = (sd1) ((td1) obj2);
                Logger.e("Login state updated: %s (was: %s)", loginState2, sd1Var.c);
                sd1Var.c = loginState2;
                sd1Var.a.onNext(loginState2);
                return;
            case 2:
                c7 c7Var = (c7) ((wk2) obj2);
                c7Var.a("result", "success");
                c7Var.c("cosmos-service-request");
                c7Var.g("bootstrapping");
                return;
            case 3:
                as2 as2Var = (as2) obj2;
                Optional optional = (Optional) obj;
                as2Var.getClass();
                as2Var.c = optional.d() ? ((LoginResponse) optional.c()).c : null;
                return;
            case 4:
                LoginState loginState3 = (LoginState) obj;
                LoginState loginState4 = LoginState.e;
                xi xiVar = ((cf) obj2).b;
                if (loginState3 != loginState4) {
                    if (loginState3 == loginState) {
                        y22 y22Var = xiVar.i;
                        y22Var.b.clear();
                        ConnectionTypePropertiesWriter connectionTypePropertiesWriter = y22Var.d.a;
                        connectionTypePropertiesWriter.clearNetCapabilitiesValidatedDisabled();
                        connectionTypePropertiesWriter.clearShouldUseSingleThread();
                        y22Var.a();
                        xiVar.b.a();
                        xiVar.d.a();
                        as2 as2Var2 = xiVar.c;
                        as2Var2.a();
                        as2Var2.c = null;
                        ((z1) xiVar.e).a();
                        xiVar.f.c.reset();
                        xiVar.g.getClass();
                        xiVar.h.a();
                        z20 z20Var = xiVar.k;
                        z20Var.a();
                        z20Var.b.setUserId("");
                        le2 le2VarA = ((com.spotify.appstorage.sharedpreferencesimpl.a) z20Var.d).a();
                        ge2 ge2Var = z20.g;
                        v03.j(ge2Var, "key");
                        le2VarA.a.remove(ge2Var.a);
                        le2VarA.a();
                        return;
                    }
                    return;
                }
                vx1 vx1Var = xiVar.b;
                int i3 = 0;
                vx1Var.c.c(((i10) vx1Var.b).i.a.values().onErrorReturnItem(c.h0()).scan(u11.r).map(new ux1(i3, tx1.g)).distinctUntilChanged().subscribe(new du(i3, vx1Var), qo.f173p));
                pt ptVar = xiVar.d;
                io.reactivex.rxjava3.subjects.b bVar = ptVar.d.d;
                v03.i(bVar, "productStateSubject");
                int i4 = 3;
                int i5 = 4;
                ptVar.f = bVar.filter(new lj(i4)).switchMapCompletable(new dd1(i5, ptVar)).subscribe(new ot(0), new ed1(i4));
                as2 as2Var3 = xiVar.c;
                sd1 sd1Var2 = (sd1) as2Var3.b;
                as2Var3.d = sd1Var2.a.distinctUntilChanged().switchMap(new dd1(i, sd1Var2)).subscribe(new a(i4, as2Var3), new ed1(i));
                y22 y22Var2 = xiVar.i;
                y22Var2.b.a();
                Logger.e("Activated remote configuration.", new Object[0]);
                aj ajVar = y22Var2.d;
                com.spotify.automotive.remoteconfig.a aVar = ajVar.b;
                aVar.b();
                boolean zB = aVar.b();
                ConnectionTypePropertiesWriter connectionTypePropertiesWriter2 = ajVar.a;
                connectionTypePropertiesWriter2.storeNetCapabilitiesValidatedDisabled(zB);
                aVar.a();
                connectionTypePropertiesWriter2.storeShouldUseSingleThread(aVar.a());
                Completable completableIgnoreElement = y22Var2.c.a().ignoreElement();
                io.reactivex.rxjava3.internal.functions.c cVar = k.g;
                completableIgnoreElement.getClass();
                io.reactivex.rxjava3.internal.operators.completable.b bVar2 = new io.reactivex.rxjava3.internal.operators.completable.b(completableIgnoreElement, cVar, i4);
                Scheduler scheduler = e.c;
                Objects.requireNonNull(scheduler, "scheduler is null");
                int i6 = 1;
                y22Var2.e = new j(bVar2, scheduler, i6).subscribe(new ot(12));
                z1 z1Var = (z1) xiVar.e;
                io.reactivex.rxjava3.subjects.b bVar3 = z1Var.d.d;
                v03.i(bVar3, "productStateSubject");
                z1Var.g.c(bVar3.onErrorReturn(w1.e).map(new x1(z1Var, i3)).distinctUntilChanged().switchMap(new x1(z1Var, i6)).subscribe(new du(i5, z1Var), w1.l));
                xiVar.f.getClass();
                xiVar.g.getClass();
                xw1 xw1Var = xiVar.h;
                xw1Var.c.c(xw1Var.b.t().map(new ed1(i5)).distinctUntilChanged().subscribe(new a(5, xw1Var), new ed1(i5)));
                z20 z20Var2 = xiVar.k;
                if (z20Var2.e != null) {
                    return;
                }
                z20Var2.f.c(z20Var2.c.a().subscribe(new du(i, z20Var2), u11.n));
                return;
            case 5:
                xw1 xw1Var2 = (xw1) obj2;
                pr1 pr1Var = (pr1) obj;
                xw1Var2.getClass();
                Object obj3 = pr1Var.a;
                m60 m60Var = xw1Var2.d;
                if (obj3 != null) {
                    fu fuVarA = m60Var.a((String) obj3);
                    ((coil.b) fuVarA.a).b(fuVarA.b.a());
                }
                Object obj4 = pr1Var.b;
                if (obj4 != null) {
                    fu fuVarA2 = m60Var.a((String) obj4);
                    ((coil.b) fuVarA2.a).b(fuVarA2.b.a());
                    return;
                }
                return;
            case 6:
                ((ef2) obj2).c((AccountManagerModel) obj);
                return;
            case 7:
                ck ckVar = (ck) obj2;
                ckVar.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                hk hkVar = ckVar.b;
                if (zBooleanValue) {
                    hkVar.b();
                    return;
                } else {
                    hkVar.c();
                    return;
                }
            case 8:
                u4.z(obj);
                int i7 = AAMFullscreenDialogActivity.H;
                ((AAMFullscreenDialogActivity) obj2).getClass();
                throw null;
            case 9:
                up1 up1Var = (up1) obj2;
                gq1 gq1Var = (gq1) obj;
                up1Var.getClass();
                gq1Var.toString();
                Long lValueOf = Long.valueOf(gq1Var.b);
                vp1 vp1Var = up1Var.d;
                vp1Var.getClass();
                vp1Var.b = com.google.common.collect.a.a(gq1Var.a).e(new lj(i)).d();
                vp1Var.c = lValueOf;
                return;
            default:
                vs1 vs1Var = (vs1) obj2;
                vs1Var.getClass();
                Object[] objArr = {((Throwable) obj).getMessage()};
                wc1 wc1Var = vs1Var.a;
                wc1Var.getClass();
                ((u11) wc1Var).v(String.format("Error running periodic scheduler: %s", objArr));
                return;
        }
    }

    @Override // p.oz0
    public Object apply(Object obj) {
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                return ((mj2) obj2).b((String) obj);
            case 1:
                ju juVar = (ju) obj2;
                if2 if2Var = (if2) obj;
                juVar.getClass();
                int iOrdinal = if2Var.e.ordinal();
                ku kuVar = juVar.a;
                if (iOrdinal == 1) {
                    kuVar.getClass();
                    return kuVar.a.b.b(ku.c(SpotifyUri$Kind.ARTIST, if2Var)).map(new ed1(16));
                }
                if (iOrdinal == 3) {
                    kuVar.getClass();
                    return kuVar.a.b.c(ku.c(SpotifyUri$Kind.ALBUM, if2Var)).map(new ed1(13));
                }
                if (iOrdinal == 5) {
                    kuVar.getClass();
                    return kuVar.a.b.j(ku.c(SpotifyUri$Kind.TRACK, if2Var)).map(new ed1(18));
                }
                if (iOrdinal == 9 || iOrdinal == 10) {
                    kuVar.getClass();
                    return kuVar.a.b.d().flatMap(new rm(kuVar, 2, ku.b(if2Var))).map(new ed1(17));
                }
                if (iOrdinal == 59) {
                    kuVar.getClass();
                    return kuVar.a.b.a(ku.c(SpotifyUri$Kind.SHOW, if2Var)).map(new ed1(15));
                }
                if (iOrdinal != 60) {
                    throw new IllegalArgumentException(String.format("Got unsupported URI of type %s.", if2Var.e));
                }
                kuVar.getClass();
                return kuVar.a.b.s(ku.c(SpotifyUri$Kind.EPISODE, if2Var)).map(new ed1(14));
            default:
                return ((ki1) obj2).b((Uri) obj, MediaUriUtil$Transformation.k);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.content.ComponentCallbacks, p.gx] */
    @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
    public void subscribe(final ObservableEmitter observableEmitter) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                final ix ixVar = (ix) obj;
                ixVar.getClass();
                final ?? r0 = new ComponentCallbacks() { // from class: p.gx
                    @Override // android.content.ComponentCallbacks
                    public final void onConfigurationChanged(Configuration configuration) {
                        ObservableEmitter observableEmitter2 = observableEmitter;
                        if (observableEmitter2.isDisposed()) {
                            return;
                        }
                        observableEmitter2.onNext(mq2.a);
                    }

                    @Override // android.content.ComponentCallbacks
                    public final /* bridge */ /* synthetic */ void onLowMemory() {
                    }
                };
                ixVar.a.registerComponentCallbacks(r0);
                observableEmitter.onNext(mq2.a);
                observableEmitter.setCancellable(new f() { // from class: p.hx
                    @Override // io.reactivex.rxjava3.functions.f
                    public final void cancel() {
                        ixVar.a.unregisterComponentCallbacks(r0);
                    }
                });
                break;
            case 1:
                ((ConnectivityMonitorImpl) obj).lambda$getConnectionTypes$2(observableEmitter);
                break;
            default:
                ((InternetConnectivityObservableImpl) obj).lambda$createConnectionType$3(observableEmitter);
                break;
        }
    }
}
