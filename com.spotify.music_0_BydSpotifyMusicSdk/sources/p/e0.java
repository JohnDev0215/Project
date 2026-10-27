package p;

import android.text.TextUtils;
import com.spotify.base.java.logging.Logger;
import com.spotify.common.uri.SpotifyUri$Kind;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends oj {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final st1 f118p;
    public final io.reactivex.rxjava3.disposables.a q;
    public final z91 r;
    public final u0 s;

    public e0(st1 st1Var, up2 up2Var, jf0 jf0Var, nk nkVar, z91 z91Var, rd1 rd1Var, ot1 ot1Var, hk hkVar, bi1 bi1Var, kr1 kr1Var, u0 u0Var) {
        super(st1Var, up2Var, jf0Var, nkVar, rd1Var, ot1Var, hkVar, bi1Var, kr1Var);
        this.q = new io.reactivex.rxjava3.disposables.a();
        this.f118p = st1Var;
        this.r = z91Var;
        this.s = u0Var;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // p.th1
    public final void d(String str) {
        Object next;
        str.getClass();
        final int i = 3;
        final int i2 = 2;
        final int i3 = 0;
        final int i4 = 1;
        byte b = -1;
        switch (str.hashCode()) {
            case -1788786792:
                if (str.equals("seek-backward")) {
                    b = 0;
                }
                break;
            case -1496036240:
                if (str.equals("seek-forward")) {
                    b = 1;
                }
                break;
            case -173836300:
                if (str.equals("likeTrack")) {
                    b = 2;
                }
                break;
            case -105396869:
                if (str.equals("unlikeTrack")) {
                    b = 3;
                }
                break;
            case -29999516:
                if (str.equals("likeEpisode")) {
                    b = 4;
                }
                break;
            case 410707220:
                if (str.equals("disable-shuffle")) {
                    b = 5;
                }
                break;
            case 1315784235:
                if (str.equals("unlikeEpisode")) {
                    b = 6;
                }
                break;
            case 2072332025:
                if (str.equals("shuffle")) {
                    b = 7;
                }
                break;
        }
        st1 st1Var = this.f118p;
        switch (b) {
            case 0:
                Objects.requireNonNull(st1Var);
                u(Completable.c(new c0(st1Var, 0)));
                break;
            case 1:
                Objects.requireNonNull(st1Var);
                u(Completable.c(new c0(st1Var, 1)));
                break;
            case 2:
            case 4:
                u(Completable.c(new Runnable(this) { // from class: p.b0
                    public final /* synthetic */ e0 e;

                    {
                        this.e = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i5 = i3;
                        e0 e0Var = this.e;
                        switch (i5) {
                            case 0:
                                e0Var.v(true);
                                break;
                            case 1:
                                e0Var.v(false);
                                break;
                            case 2:
                                e0Var.f118p.c(true);
                                break;
                            default:
                                e0Var.f118p.c(false);
                                break;
                        }
                    }
                }));
                break;
            case 3:
            case 6:
                u(Completable.c(new Runnable(this) { // from class: p.b0
                    public final /* synthetic */ e0 e;

                    {
                        this.e = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i5 = i4;
                        e0 e0Var = this.e;
                        switch (i5) {
                            case 0:
                                e0Var.v(true);
                                break;
                            case 1:
                                e0Var.v(false);
                                break;
                            case 2:
                                e0Var.f118p.c(true);
                                break;
                            default:
                                e0Var.f118p.c(false);
                                break;
                        }
                    }
                }));
                break;
            case 5:
                u(Completable.c(new Runnable(this) { // from class: p.b0
                    public final /* synthetic */ e0 e;

                    {
                        this.e = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i5 = i;
                        e0 e0Var = this.e;
                        switch (i5) {
                            case 0:
                                e0Var.v(true);
                                break;
                            case 1:
                                e0Var.v(false);
                                break;
                            case 2:
                                e0Var.f118p.c(true);
                                break;
                            default:
                                e0Var.f118p.c(false);
                                break;
                        }
                    }
                }));
                break;
            case 7:
                u(Completable.c(new Runnable(this) { // from class: p.b0
                    public final /* synthetic */ e0 e;

                    {
                        this.e = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i5 = i2;
                        e0 e0Var = this.e;
                        switch (i5) {
                            case 0:
                                e0Var.v(true);
                                break;
                            case 1:
                                e0Var.v(false);
                                break;
                            case 2:
                                e0Var.f118p.c(true);
                                break;
                            default:
                                e0Var.f118p.c(false);
                                break;
                        }
                    }
                }));
                break;
        }
        Iterator it = tt1.e.iterator();
        while (true) {
            if (it.hasNext()) {
                next = it.next();
                if (v03.b(((tt1) next).c, str)) {
                }
            } else {
                next = null;
            }
        }
        tt1 tt1Var = (tt1) next;
        if (tt1Var != null) {
            List list = tt1.e;
            int iIndexOf = list.indexOf(tt1Var) + 1;
            u(Completable.c(new p42(((tt1) (iIndexOf >= list.size() ? ou.c0(list) : list.get(iIndexOf))).b, 1, this)));
        }
    }

    @Override // p.th1
    public final void f() {
        st1 st1Var = this.f;
        Objects.requireNonNull(st1Var);
        u(Completable.c(new c0(st1Var, 5)));
        ((com.spotify.audiofocus.coreinternal.a) this.s).abandonAudioFocus();
    }

    @Override // p.th1
    public final void s() {
        st1 st1Var = this.f;
        Objects.requireNonNull(st1Var);
        u(Completable.c(new c0(st1Var, 2)));
        ((com.spotify.audiofocus.coreinternal.a) this.s).abandonAudioFocus();
    }

    public final void v(final boolean z) {
        this.f118p.e().take(1L).timeout(1000L, TimeUnit.MILLISECONDS, Observable.error(new TimeoutException())).switchMapCompletable(new io.reactivex.rxjava3.functions.o() { // from class: p.d0
            @Override // io.reactivex.rxjava3.functions.o
            public final Object apply(Object obj) {
                Completable completableK;
                ju1 ju1Var = (ju1) obj;
                e0 e0Var = this.c;
                e0Var.getClass();
                if (ju1Var.g().d()) {
                    if2 if2Var = (if2) ju1Var.g().c();
                    final z91 z91Var = e0Var.r;
                    z91Var.getClass();
                    SpotifyUri$Kind spotifyUri$Kind = if2Var.e;
                    SpotifyUri$Kind spotifyUri$Kind2 = SpotifyUri$Kind.TRACK;
                    if ((spotifyUri$Kind == spotifyUri$Kind2 || spotifyUri$Kind == SpotifyUri$Kind.EPISODE) && !TextUtils.isEmpty(if2Var.l)) {
                        final if2 if2Var2 = (if2) ju1Var.g().c();
                        z91Var.b = if2Var2;
                        final boolean z2 = z;
                        z91Var.d.onNext(Boolean.valueOf(z2));
                        SpotifyUri$Kind spotifyUri$Kind3 = if2Var2.e;
                        ku kuVar = z91Var.a;
                        if (spotifyUri$Kind2 == spotifyUri$Kind3) {
                            kuVar.getClass();
                            String strC = ku.c(spotifyUri$Kind2, if2Var2);
                            vv2 vv2Var = kuVar.a;
                            completableK = z2 ? vv2Var.b.p(strC) : vv2Var.b.u(strC);
                        } else {
                            SpotifyUri$Kind spotifyUri$Kind4 = SpotifyUri$Kind.EPISODE;
                            if (spotifyUri$Kind4 != spotifyUri$Kind3) {
                                throw new IllegalArgumentException("Only TRACK or EPISODE kinds supported.");
                            }
                            kuVar.getClass();
                            String strC2 = ku.c(spotifyUri$Kind4, if2Var2);
                            vv2 vv2Var2 = kuVar.a;
                            completableK = z2 ? vv2Var2.b.k(strC2) : vv2Var2.b.r(strC2);
                        }
                        io.reactivex.rxjava3.functions.a aVar = new io.reactivex.rxjava3.functions.a() { // from class: p.y91
                            @Override // io.reactivex.rxjava3.functions.a
                            public final void run() {
                                boolean z3;
                                z91 z91Var2 = z91Var;
                                io.reactivex.rxjava3.subjects.b bVar = z91Var2.d;
                                Object obj2 = bVar.c.get();
                                if (NotificationLite.h(obj2) || NotificationLite.i(obj2)) {
                                    obj2 = null;
                                }
                                boolean zBooleanValue = ((Boolean) obj2).booleanValue();
                                if (!if2Var2.equals(z91Var2.b) || zBooleanValue == (z3 = z2)) {
                                    return;
                                }
                                bVar.onNext(Boolean.valueOf(z3));
                            }
                        };
                        completableK.getClass();
                        return new io.reactivex.rxjava3.internal.operators.completable.l(completableK, io.reactivex.rxjava3.internal.functions.k.d, aVar);
                    }
                }
                Logger.b("Missing or unsupported URI in player state, can't like/unlike current track.", new Object[0]);
                return io.reactivex.rxjava3.internal.operators.completable.d.c;
            }
        }).subscribe(new ot(1), new ed1(7), this.q);
    }
}
