package p;

import android.os.Bundle;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.ubi.ContentErrorType;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class zn implements sn {
    public final rd1 a;
    public final kk0 b;
    public final gh2 c;
    public final Scheduler d;
    public final up2 e;
    public final Disposable f;
    public final io.reactivex.rxjava3.disposables.a g;
    public LoginState h;

    public zn(rd1 rd1Var, rx rxVar, kk0 kk0Var, gh2 gh2Var, Scheduler scheduler, up2 up2Var) {
        v03.j(kk0Var, "parentIdsReloader");
        this.a = rd1Var;
        this.b = kk0Var;
        this.c = gh2Var;
        this.d = scheduler;
        this.e = up2Var;
        this.g = new io.reactivex.rxjava3.disposables.a();
        Disposable disposableSubscribe = Observable.combineLatest(rxVar.a, ((sd1) rd1Var).a.distinctUntilChanged(), qo.k).filter(w1.f203p).subscribe(new du(1, this));
        v03.i(disposableSubscribe, "combineLatest(\n         …StateChanged(it.second) }");
        this.f = disposableSubscribe;
    }

    public static final void a(zn znVar, String str, Referrer referrer) {
        znVar.getClass();
        np2 np2Var = new np2(str, referrer);
        up2 up2Var = znVar.e;
        up2Var.getClass();
        cp2 cp2VarA = up2.a(np2Var);
        if (cp2VarA == null) {
            return;
        }
        ip2 ip2Var = ip2.i;
        hp2 hp2VarM = qo.m();
        hp2VarM.h = "automotive";
        hp2VarM.a = "automotive-content-error";
        hp2VarM.f = "1.1.0";
        hp2VarM.g = "15.3.0";
        hp2VarM.d = str;
        ip2 ip2VarA = hp2VarM.a();
        lp2 lp2Var = new lp2();
        lp2Var.a = ip2VarA;
        lp2Var.b = cp2VarA;
        ((tp2) up2Var.a).a((mp2) lp2Var.a());
    }

    public static final void b(zn znVar, String str, Referrer referrer, Throwable th) {
        znVar.getClass();
        np2 np2Var = new np2(str, referrer);
        String message = th.getMessage();
        up2 up2Var = znVar.e;
        if (message == null || !kotlin.text.b.i0(message, "504")) {
            Logger.c(th, "Error when fetching Uri: " + str + " , Referrer: " + referrer, new Object[0]);
            up2Var.getClass();
            cp2 cp2VarA = up2.a(np2Var);
            if (cp2VarA == null) {
                return;
            }
            ((tp2) up2Var.a).a(new kk0(new zi(str, cp2VarA), ContentErrorType.UNKNOWN.a()).u());
            return;
        }
        Logger.b("User's system was offline when fetching Uri: " + str + " , Referrer: " + referrer, new Object[0]);
        up2Var.getClass();
        cp2 cp2VarA2 = up2.a(np2Var);
        if (cp2VarA2 == null) {
            return;
        }
        ((tp2) up2Var.a).a(new kk0(new zi(str, cp2VarA2), ContentErrorType.OFFLINE.a()).u());
    }

    public final Single c() {
        Single map = ((sd1) this.a).a.distinctUntilChanged().filter(w1.q).take(1L).singleOrError().map(new ux1(2, this));
        v03.i(map, "private fun getBrowseAct…ory.create(state) }\n    }");
        return map;
    }

    public final void d(ry0 ry0Var, io.reactivex.rxjava3.functions.o oVar) {
        c().flatMap(oVar).observeOn(this.d).subscribe(new wn(0, ry0Var), new wn(1, ry0Var), this.g);
    }

    public final void e(if2 if2Var, String str, Locale locale, Bundle bundle, ry0 ry0Var) {
        Referrer referrer;
        v03.j(bundle, "extras");
        String string = if2Var.toString();
        v03.i(string, "spotifyUri.toString()");
        Referrer[] referrerArrValues = Referrer.values();
        int length = referrerArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                referrer = null;
                break;
            }
            Referrer referrer2 = referrerArrValues[i];
            if (v03.b(referrer2.a(), str)) {
                referrer = referrer2;
                break;
            }
            i++;
        }
        d(ry0Var, new vn(if2Var, str, locale, bundle, this, string, referrer));
    }
}
