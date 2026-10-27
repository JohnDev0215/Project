package p;

import android.text.TextUtils;
import com.google.common.base.Optional;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.playback.ErrorState;
import com.spotify.automotive.ubi.LoggerPlaybackError;
import com.spotify.base.java.logging.Logger;
import com.spotify.common.uri.SpotifyUri$Kind;
import io.reactivex.rxjava3.core.Observable;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pj implements io.reactivex.rxjava3.functions.g, io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ qj e;

    public /* synthetic */ pj(qj qjVar, int i) {
        this.c = i;
        this.e = qjVar;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        switch (this.c) {
            case 0:
                ((ph1) obj).a(this.e.b);
                return;
            default:
                qj qjVar = this.e;
                LoginState loginState = (LoginState) obj;
                if (loginState == LoginState.c) {
                    qjVar.getClass();
                    return;
                }
                yf2 yf2Var = qjVar.l;
                synchronized (yf2Var) {
                    v03.j(loginState, "loginState");
                    if (yf2Var.b) {
                        return;
                    }
                    yf2Var.c.c("login_state_decision");
                    yf2Var.c.a("login_state", loginState.toString());
                    yf2Var.c.c("startup_time");
                    ((b7) yf2Var.a).b(yf2Var.c.b());
                    yf2Var.b = true;
                    return;
                }
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        int i = this.c;
        qj qjVar = this.e;
        switch (i) {
            case 0:
                LoginState loginState = (LoginState) obj;
                final int i2 = 1;
                if (loginState != LoginState.e) {
                    return loginState == LoginState.c ? Observable.just(qjVar.j) : loginState == LoginState.l ? Observable.just(qjVar.i) : qjVar.k.a().map(new pj(qjVar, i2)).startWithItem(qjVar.h);
                }
                qjVar.getClass();
                ArrayList arrayList = new ArrayList(5);
                ei1 ei1Var = qjVar.g;
                Observable map = ei1Var.a.a().map(new ux1(i2, ei1Var));
                v03.i(map, "get() = mErrorController…          }\n            }");
                arrayList.add(map);
                p0 p0Var = qjVar.d;
                st1 st1Var = p0Var.a;
                int i3 = 7;
                Observable observableFilter = st1Var.e().filter(new lj(i3));
                int i4 = 9;
                Observable observableStartWithItem = st1Var.j().map(new ed1(i4)).startWithItem(Boolean.TRUE);
                int i5 = 8;
                int i6 = 10;
                Observable map2 = observableFilter.filter(new lj(i5)).map(new ed1(i6));
                final z91 z91Var = p0Var.b;
                Objects.requireNonNull(z91Var);
                arrayList.add(Observable.combineLatest(observableFilter, observableStartWithItem, map2.filter(new io.reactivex.rxjava3.functions.p() { // from class: p.o0
                    @Override // io.reactivex.rxjava3.functions.p
                    public final boolean test(Object obj2) {
                        if2 if2Var = (if2) obj2;
                        z91Var.getClass();
                        SpotifyUri$Kind spotifyUri$Kind = if2Var.e;
                        return (spotifyUri$Kind == SpotifyUri$Kind.TRACK || spotifyUri$Kind == SpotifyUri$Kind.EPISODE) && !TextUtils.isEmpty(if2Var.l);
                    }
                }).switchMap(new dd1(i5, z91Var)).startWithItem(Boolean.FALSE).distinctUntilChanged(), st1Var.f(), new af2(13, p0Var)).map(new ed1(11)));
                final j0 j0Var = qjVar.e;
                final int i7 = 0;
                arrayList.add(j0Var.a.e().filter(new lj(6)).doOnNext(new io.reactivex.rxjava3.functions.g() { // from class: p.h0
                    @Override // io.reactivex.rxjava3.functions.g
                    public final void accept(Object obj2) {
                        int i8 = i7;
                        j0 j0Var2 = j0Var;
                        switch (i8) {
                            case 0:
                                j0Var2.getClass();
                                if (((ju1) obj2).d()) {
                                    j0Var2.c.b.onNext(ErrorState.PLAYBACK_TEMPORARY_ERROR);
                                }
                                break;
                            default:
                                g0 g0Var = (g0) obj2;
                                j0Var2.getClass();
                                if (g0Var.a.isEmpty() && g0Var.c.isEmpty() && g0Var.b.isEmpty() && !g0Var.e.d() && !g0Var.f.d()) {
                                    j0Var2.d.e(LoggerPlaybackError.EMPTY_METADATA);
                                }
                                int i9 = kk.a;
                                Logger.b("Spotify AAM Version = 4.4.1.0", new Object[0]);
                                break;
                        }
                    }
                }).map(new dd1(i3, j0Var)).distinctUntilChanged().doOnNext(new io.reactivex.rxjava3.functions.g() { // from class: p.h0
                    @Override // io.reactivex.rxjava3.functions.g
                    public final void accept(Object obj2) {
                        int i8 = i2;
                        j0 j0Var2 = j0Var;
                        switch (i8) {
                            case 0:
                                j0Var2.getClass();
                                if (((ju1) obj2).d()) {
                                    j0Var2.c.b.onNext(ErrorState.PLAYBACK_TEMPORARY_ERROR);
                                }
                                break;
                            default:
                                g0 g0Var = (g0) obj2;
                                j0Var2.getClass();
                                if (g0Var.a.isEmpty() && g0Var.c.isEmpty() && g0Var.b.isEmpty() && !g0Var.e.d() && !g0Var.f.d()) {
                                    j0Var2.d.e(LoggerPlaybackError.EMPTY_METADATA);
                                }
                                int i9 = kk.a;
                                Logger.b("Spotify AAM Version = 4.4.1.0", new Object[0]);
                                break;
                        }
                    }
                }).map(new ed1(i5)));
                Optional optional = qjVar.f;
                if (optional.d()) {
                    s0 s0Var = (s0) optional.c();
                    st1 st1Var2 = s0Var.a;
                    arrayList.add(Observable.combineLatest(st1Var2.t(), st1Var2.e().filter(new lj(i4)), new lj(i2)).filter(new lj(i6)).map(new dd1(i4, s0Var)).distinctUntilChanged().map(new ed1(12)));
                }
                return Observable.merge(arrayList);
            default:
                return qjVar.h;
        }
    }
}
