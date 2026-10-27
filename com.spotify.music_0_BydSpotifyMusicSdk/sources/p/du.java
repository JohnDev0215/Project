package p;

import android.view.View;
import android.view.ViewGroup;
import com.spotify.automotive.auth.LoginState;
import com.spotify.offline.NativeForegroundObserver;
import com.spotify.player.model.PlayerError;
import com.spotify.voiceassistants.playermodels.VoiceAssistantsPerformance;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.Playlist;
import com.spotify.webapi.service.models.PlaylistTrack;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.List;
import java.util.Objects;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class du implements SingleOnSubscribe, io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.p, io.reactivex.rxjava3.functions.g, ct2 {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ du(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public void a(Pair pair) {
        String str;
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 1:
                zn znVar = (zn) obj;
                Object objD = pair.d();
                v03.i(objD, "it.second");
                LoginState loginState = (LoginState) objD;
                LoginState loginState2 = LoginState.e;
                kk0 kk0Var = znVar.b;
                if (loginState == loginState2) {
                    kk0Var.A();
                }
                LoginState loginState3 = znVar.h;
                if (loginState3 == loginState2 && loginState == LoginState.k) {
                    String str2 = (String) kk0Var.l;
                    if (str2 != null) {
                        ((p01) kk0Var.e).z(str2);
                    }
                } else if (loginState3 == loginState2 && loginState == LoginState.l && (str = (String) kk0Var.l) != null) {
                    ((p01) kk0Var.e).z(str);
                }
                znVar.h = loginState;
                break;
            default:
                w40 w40Var = (w40) obj;
                boolean zBooleanValue = ((Boolean) pair.c()).booleanValue();
                boolean zBooleanValue2 = ((Boolean) pair.d()).booleanValue();
                w40Var.d = zBooleanValue;
                w40Var.e = zBooleanValue2;
                String str3 = w40Var.f;
                if (str3 != null) {
                    w40Var.b(str3);
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                ((vx1) obj2).d.onNext((tx1) obj);
                break;
            case 1:
                a((Pair) obj);
                break;
            case 2:
                String str = (String) obj;
                z20 z20Var = (z20) obj2;
                v03.i(str, "partnerId");
                ge2 ge2Var = z20.g;
                z20Var.e = str;
                z20Var.b.setUserId(str);
                le2 le2VarA = ((com.spotify.appstorage.sharedpreferencesimpl.a) z20Var.d).a();
                ge2 ge2Var2 = z20.g;
                v03.j(ge2Var2, "key");
                le2VarA.a.putString(ge2Var2.a, str);
                le2VarA.a();
                break;
            case 3:
                a((Pair) obj);
                break;
            case 4:
                PlayerError playerError = (PlayerError) obj;
                v03.j(playerError, "pickAndShuffleCappedError");
                z1 z1Var = (z1) obj2;
                String strB = playerError.b();
                v03.i(strB, "pickAndShuffleCappedError.contextUri()");
                String strE = playerError.e();
                v03.i(strE, "pickAndShuffleCappedError.trackUri()");
                z1Var.getClass();
                if (kotlin.text.b.i0(strB, "spotify:search") && strE.length() > 0) {
                    strB = strE;
                }
                z1Var.s(null, strB, null, null);
                break;
            case 5:
                Throwable th = (Throwable) obj;
                v03.j(th, "p0");
                ((u11) ((com.spotify.eventsender.eventsender.observability.a) obj2).f).y("EventSender heartbeat event fatal error", th);
                break;
            case 6:
                ((NativeForegroundObserver) obj2).onForeground(((Boolean) obj).booleanValue());
                break;
            case 7:
                m02 m02Var = (m02) obj;
                cg2 cg2Var = ((ut0) obj2).a;
                v03.i(m02Var, "it");
                ((eg2) cg2Var).b(m02Var);
                break;
            case 8:
                wk2 wk2Var = (wk2) obj2;
                if (wk2Var != null) {
                    ((c7) wk2Var).g(VoiceAssistantsPerformance.MEASURE_NETWORK_SPEAKEASY);
                }
                break;
            default:
                ((io.reactivex.rxjava3.functions.a) obj2).run();
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                Pair pair = new Pair(((ts1) obj2).a, (Throwable) obj);
                int i2 = Flowable.c;
                return new io.reactivex.rxjava3.internal.operators.flowable.x(pair);
            default:
                ts1 ts1Var = (ts1) obj;
                a30 a30Var = new a30((jw0) obj2, ts1Var);
                int i3 = Flowable.c;
                int i4 = 0;
                return new io.reactivex.rxjava3.internal.operators.flowable.d0(new io.reactivex.rxjava3.internal.operators.flowable.t(a30Var), new du(i4, ts1Var), i4);
        }
    }

    public int b(View view) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                z12 z12Var = (z12) view.getLayoutParams();
                ((y12) obj).getClass();
                return view.getRight() + ((z12) view.getLayoutParams()).b.right + ((ViewGroup.MarginLayoutParams) z12Var).rightMargin;
            default:
                z12 z12Var2 = (z12) view.getLayoutParams();
                ((y12) obj).getClass();
                return view.getBottom() + ((z12) view.getLayoutParams()).b.bottom + ((ViewGroup.MarginLayoutParams) z12Var2).bottomMargin;
        }
    }

    public int c(View view) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                z12 z12Var = (z12) view.getLayoutParams();
                ((y12) obj).getClass();
                return (view.getLeft() - ((z12) view.getLayoutParams()).b.left) - ((ViewGroup.MarginLayoutParams) z12Var).leftMargin;
            default:
                z12 z12Var2 = (z12) view.getLayoutParams();
                ((y12) obj).getClass();
                return (view.getTop() - ((z12) view.getLayoutParams()).b.top) - ((ViewGroup.MarginLayoutParams) z12Var2).topMargin;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
    public void subscribe(SingleEmitter singleEmitter) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                fu fuVar = (fu) obj;
                o31 o31Var = fuVar.b;
                o31Var.r = Boolean.valueOf(fuVar.e);
                o31Var.d = new cu(singleEmitter);
                o31Var.M = null;
                o31Var.N = null;
                o31Var.O = null;
                ((coil.b) fuVar.a).b(o31Var.a());
                break;
            default:
                singleEmitter.onSuccess(((eg2) obj).c.b().a.e);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.p
    public boolean test(Object obj) {
        List<PlaylistTrack> list;
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                Playlist playlist = (Playlist) obj;
                ((ou1) obj2).getClass();
                if ((playlist != null ? playlist.tracks : null) != null) {
                    Pager<PlaylistTrack> pager = playlist.tracks;
                    if ((pager != null ? pager.items : null) != null && pager != null && (list = pager.items) != null && (!list.isEmpty())) {
                        return true;
                    }
                }
                return false;
            case 1:
                Throwable th = (Throwable) obj;
                com.spotify.eventsender.disk.a aVar = (com.spotify.eventsender.disk.a) obj2;
                for (cj0 cj0Var : aVar.a) {
                    v03.i(th, "t");
                    cj0Var.d(th);
                }
                ((u11) aVar.c).y("Error while writing to disk.", th);
                return true;
            case 2:
                u4.z(obj2);
                throw null;
            case 3:
                return ((Class) obj2).isInstance(obj);
            default:
                return Objects.equals(obj, obj2);
        }
    }
}
