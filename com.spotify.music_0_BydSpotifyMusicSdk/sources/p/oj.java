package p;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import com.google.common.base.Optional;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.base.java.logging.Logger;
import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.voiceassistants.playermodels.SearchEndpointRequest;
import com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse;
import com.spotify.webapi.service.models.Search;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class oj extends th1 {
    public final st1 f;
    public final up2 g;
    public final jf0 h;
    public final nk i;
    public final rd1 j;
    public final ot1 k;
    public final hk l;
    public final bi1 m;
    public final io.reactivex.rxjava3.disposables.a n = new io.reactivex.rxjava3.disposables.a();
    public final kr1 o;

    public oj(st1 st1Var, up2 up2Var, jf0 jf0Var, nk nkVar, rd1 rd1Var, ot1 ot1Var, hk hkVar, bi1 bi1Var, kr1 kr1Var) {
        this.f = st1Var;
        this.g = up2Var;
        this.h = jf0Var;
        this.i = nkVar;
        this.j = rd1Var;
        this.k = ot1Var;
        this.l = hkVar;
        this.m = bi1Var;
        this.o = kr1Var;
    }

    @Override // p.th1
    public final void b(MediaDescriptionCompat mediaDescriptionCompat) {
        Optional optionalB = Optional.b(vr2.g(mediaDescriptionCompat.c));
        if (!optionalB.d()) {
            Logger.b("onAddQueueItem called with invalid URI \"%s\".", mediaDescriptionCompat.c);
        } else {
            optionalB.c();
            u(Completable.c(new jj(this, optionalB, 0)));
        }
    }

    @Override // p.th1
    public final void c(MediaDescriptionCompat mediaDescriptionCompat) {
        String str = mediaDescriptionCompat.c;
        Logger.b("onAddQueueItem with index is not supported. Item will be added to end of queue.", new Object[0]);
        b(mediaDescriptionCompat);
    }

    @Override // p.th1
    public final void g() {
        u(this.f.l());
    }

    @Override // p.th1
    public final void h(String str) {
        String value;
        Optional optionalB = Optional.b(vr2.g(str));
        if (!optionalB.d()) {
            Logger.b("onPlayFromMediaId called with invalid URI \"%s\".", str);
            return;
        }
        final String string = ((if2) optionalB.c()).toString();
        if (str != null) {
            UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
            urlQuerySanitizer.registerParameter("contextUri", UrlQuerySanitizer.getAllIllegal());
            urlQuerySanitizer.registerParameter("ref", UrlQuerySanitizer.getAllIllegal());
            urlQuerySanitizer.registerParameter("pos", UrlQuerySanitizer.getAllIllegal());
            urlQuerySanitizer.setPreferFirstRepeatedParameter(true);
            urlQuerySanitizer.parseUrl(str);
            value = urlQuerySanitizer.getValue("contextUri");
        } else {
            value = null;
        }
        final String str2 = (String) Optional.b(vr2.g(value)).i(new lj(0)).h();
        final String strF = vr2.f(str);
        final Integer numE = vr2.e(str);
        if (str2 != null) {
            u(Completable.c(new Runnable() { // from class: p.mj
                @Override // java.lang.Runnable
                public final void run() {
                    String str3;
                    Referrer referrer;
                    oj ojVar = this.c;
                    ojVar.getClass();
                    String str4 = str2;
                    vp2 vp2Var = new vp2(str4);
                    Referrer[] referrerArrValues = Referrer.values();
                    int length = referrerArrValues.length;
                    int i = 0;
                    while (true) {
                        str3 = strF;
                        if (i >= length) {
                            referrer = null;
                            break;
                        }
                        referrer = referrerArrValues[i];
                        if (v03.b(referrer.a(), str3)) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    String str5 = string;
                    Integer num = numE;
                    ojVar.g.d(new rp2(str5, num, vp2Var, referrer));
                    ((z1) ojVar.f).s(num, str4, str5, str3);
                }
            }));
        } else {
            u(Completable.c(new g72(this, string, numE, strF, 2)));
        }
    }

    @Override // p.th1
    public final void i(String str, Bundle bundle) {
        CompletableSource completableSourceIgnoreElement;
        aq2 aq2Var = new aq2(str);
        if (bundle == null) {
            bundle = new Bundle();
        }
        final ot1 ot1Var = this.k;
        ot1Var.getClass();
        int i = 3;
        int i2 = 0;
        int i3 = 1;
        if (v03.y(str)) {
            String str2 = ot1Var.b.c;
            completableSourceIgnoreElement = v03.y(str2) ? new io.reactivex.rxjava3.internal.operators.completable.e(i2, new UnsupportedOperationException("Unable to retrieve username for playing collection. Not logged in?")) : new io.reactivex.rxjava3.internal.operators.completable.e(i3, new fd1(ot1Var, i, new if2(SpotifyUri$Kind.COLLECTION, null, str2).toString()));
        } else {
            pi piVar = new pi();
            String string = bundle.getString("android.intent.extra.artist");
            String string2 = bundle.getString("android.intent.extra.album");
            String string3 = bundle.getString("android.intent.extra.title");
            String string4 = bundle.getString("android.intent.extra.genre");
            String string5 = bundle.getString("android.intent.extra.playlist");
            String string6 = bundle.getString("android.intent.extra.focus");
            if (v03.b("vnd.android.cursor.item/artist", string6) && !v03.y(string)) {
                piVar.b = "track,artist";
                piVar.c = string;
                piVar.d = string4;
            } else if (v03.b("vnd.android.cursor.item/album", string6) && !v03.y(string2)) {
                piVar.b = Search.Type.ALBUM;
                piVar.d = string4;
                piVar.a = string2;
                piVar.c = string;
            } else if (v03.b("vnd.android.cursor.item/audio", string6) && !v03.y(string3)) {
                piVar.b = xv2.a;
                piVar.d = string4;
                piVar.c = string;
                piVar.e = string3;
            } else if (v03.b("vnd.android.cursor.item/genre", string6) && !v03.y(string4)) {
                piVar.b = Search.Type.TRACK;
                piVar.d = string4;
            } else if (!v03.b("vnd.android.cursor.item/playlist", string6) || v03.y(string5)) {
                piVar.b = xv2.a;
                piVar.e = str;
            } else {
                piVar.b = Search.Type.PLAYLIST;
                piVar.e = str;
            }
            piVar.a();
            final String str3 = (String) piVar.b;
            final String strA = piVar.a();
            completableSourceIgnoreElement = ot1Var.c.b.f(str3, strA, String.valueOf(5), false, "from_token").doOnSuccess(new io.reactivex.rxjava3.functions.g() { // from class: p.nt1
                @Override // io.reactivex.rxjava3.functions.g
                public final void accept(Object obj) {
                    Search search = (Search) obj;
                    ot1 ot1Var2 = ot1Var;
                    ot1Var2.getClass();
                    List listA = ot1.a(search.tracks);
                    List listA2 = ot1.a(search.artists);
                    List listA3 = ot1.a(search.albums);
                    List listA4 = ot1.a(search.episodes);
                    List listA5 = ot1.a(search.playlists);
                    List listA6 = ot1.a(search.shows);
                    if (!listA.isEmpty()) {
                        ot1Var2.b(listA);
                        return;
                    }
                    if (!listA2.isEmpty()) {
                        ot1Var2.b(listA2);
                        return;
                    }
                    if (!listA3.isEmpty()) {
                        ot1Var2.b(listA3);
                        return;
                    }
                    if (!listA4.isEmpty()) {
                        ot1Var2.b(listA4);
                        return;
                    }
                    if (!listA5.isEmpty()) {
                        ot1Var2.b(listA5);
                    } else if (!listA6.isEmpty()) {
                        ot1Var2.b(listA6);
                    } else {
                        Logger.g("Search result for type = \"%s\", query = \"%s\" returned nothing, can't play anything.", str3, strA);
                    }
                }
            }).ignoreElement();
        }
        io.reactivex.rxjava3.internal.operators.completable.e eVar = new io.reactivex.rxjava3.internal.operators.completable.e(i3, new fd1(this, i3, aq2Var));
        completableSourceIgnoreElement.getClass();
        u(new io.reactivex.rxjava3.internal.operators.completable.b(new io.reactivex.rxjava3.internal.operators.completable.c(new CompletableSource[]{eVar, completableSourceIgnoreElement}, i2), new lj(4), 3));
    }

    @Override // p.th1
    public final void j(Uri uri, Bundle bundle) {
        Completable eVar;
        int i = 0;
        if (uri == null) {
            Logger.g("onPlayFromUri called with null uri.", new Object[0]);
            return;
        }
        bq2 bq2Var = new bq2(uri.toString());
        if (bundle == null) {
            bundle = new Bundle();
        }
        nk nkVar = this.i;
        nkVar.getClass();
        Uri uriA = qc.a(uri);
        SearchEndpointRequest searchEndpointRequestA = nkVar.a(uriA, bundle);
        xe2 xe2Var = (xe2) nkVar.a;
        xe2Var.getClass();
        HashMap map = xe2Var.g;
        cx1 cx1Var = (cx1) map.get(uriA);
        int i2 = 1;
        if (cx1Var == null) {
            Logger.g("playPreparedUri called without preceding searchAndPrepare. Will search and play once search returns.", new Object[0]);
            eVar = xe2Var.a(uriA, searchEndpointRequestA);
            cx1 cx1Var2 = (cx1) map.get(uriA);
            if (cx1Var2 != null) {
                cx1Var2.b = true;
            }
        } else if (cx1Var.a) {
            SpeakeasyDualResponse speakeasyDualResponse = (SpeakeasyDualResponse) cx1Var.c;
            ov2 ov2Var = xe2Var.b;
            if (speakeasyDualResponse != null) {
                eVar = ((mk) ov2Var).a(searchEndpointRequestA, speakeasyDualResponse);
            } else {
                Logger.b("playPreparedUri got a null search response.", new Object[0]);
                mk mkVar = (mk) ov2Var;
                mkVar.getClass();
                eVar = new io.reactivex.rxjava3.internal.operators.completable.e(i2, new q(3, mkVar));
            }
            map.remove(uriA);
        } else {
            Logger.g("playPreparedUri called before searchAndPrepare returned result. Will play once search returns.", new Object[0]);
            cx1Var.b = true;
            eVar = io.reactivex.rxjava3.internal.operators.completable.d.c;
            v03.i(eVar, "{\n            Logger.w(\n…able.complete()\n        }");
        }
        io.reactivex.rxjava3.internal.operators.completable.e eVar2 = new io.reactivex.rxjava3.internal.operators.completable.e(i2, new fd1(this, 2, bq2Var));
        eVar.getClass();
        u(new io.reactivex.rxjava3.internal.operators.completable.c(new CompletableSource[]{eVar2, eVar}, i));
    }

    @Override // p.th1
    public final void k(Uri uri, Bundle bundle) {
        if (uri == null) {
            Logger.g("onPrepareFromUri called with null uri", new Object[0]);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        nk nkVar = this.i;
        nkVar.getClass();
        Uri uriA = qc.a(uri);
        SearchEndpointRequest searchEndpointRequestA = nkVar.a(uriA, bundle);
        xe2 xe2Var = (xe2) nkVar.a;
        xe2Var.getClass();
        u(xe2Var.a(uriA, searchEndpointRequestA));
    }

    @Override // p.th1
    public final void l(MediaDescriptionCompat mediaDescriptionCompat) {
        Optional optionalB = Optional.b(vr2.g(mediaDescriptionCompat.c));
        if (!optionalB.d()) {
            Logger.b("onRemoveQueueItem called with invalid URI \"%s\".", mediaDescriptionCompat.c);
        } else {
            optionalB.c();
            u(Completable.c(new jj(this, optionalB, 1)));
        }
    }

    @Override // p.th1
    public final void m(long j) {
        u(Completable.c(new kj(this, j, 0)));
    }

    @Override // p.th1
    public final void n(int i) {
        u(Completable.c(new nj(this, i, 1)));
    }

    @Override // p.th1
    public final void o(int i) {
        u(Completable.c(new nj(this, i, 0)));
    }

    @Override // p.th1
    public final void p() {
        st1 st1Var = this.f;
        Objects.requireNonNull(st1Var);
        u(Completable.c(new c0(st1Var, 4)));
    }

    @Override // p.th1
    public final void q() {
        st1 st1Var = this.f;
        Objects.requireNonNull(st1Var);
        u(Completable.c(new c0(st1Var, 3)));
    }

    @Override // p.th1
    public final void r(long j) {
        u(Completable.c(new kj(this, j, 1)));
    }

    public final void u(Completable completable) {
        gi1 gi1VarD = this.m.a.d();
        if (!this.o.b(gi1VarD.a.a)) {
            Logger.b("Failed to validate package \"%s\"", gi1VarD.a.a);
            return;
        }
        hk hkVar = this.l;
        hkVar.b();
        sd1 sd1Var = (sd1) this.j;
        int i = 1;
        Completable completableSwitchMapCompletable = (sd1Var.c == LoginState.e ? Observable.just(Boolean.TRUE) : sd1Var.a.distinctUntilChanged().filter(new lj(5)).map(new ed1(7)).take(1L).timeout(10L, TimeUnit.SECONDS, Observable.just(Boolean.FALSE))).switchMapCompletable(new rm(this, i, completable));
        Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.c;
        completableSwitchMapCompletable.getClass();
        Objects.requireNonNull(scheduler, "scheduler is null");
        this.n.c(new io.reactivex.rxjava3.internal.operators.completable.b(new io.reactivex.rxjava3.internal.operators.completable.b(new io.reactivex.rxjava3.internal.operators.completable.j(completableSwitchMapCompletable, scheduler, i), new q(2, hkVar), 1), io.reactivex.rxjava3.internal.functions.k.g, 3).subscribe());
    }
}
