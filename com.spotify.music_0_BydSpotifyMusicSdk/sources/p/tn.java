package p;

import com.spotify.automotive.ubi.Referrer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class tn implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ zn e;
    public final /* synthetic */ String k;
    public final /* synthetic */ Comparable l;

    public /* synthetic */ tn(zn znVar, String str, Referrer referrer, int i) {
        this.c = i;
        this.e = znVar;
        this.k = str;
        this.l = referrer;
    }

    public final void a(Throwable th) {
        Referrer referrer;
        int i = this.c;
        zn znVar = this.e;
        String str = this.k;
        Comparable comparable = this.l;
        switch (i) {
            case 1:
                v03.i(th, "error");
                zn.b(znVar, str, (Referrer) comparable, th);
                break;
            case 2:
            default:
                String string = ((if2) comparable).toString();
                v03.i(string, "spotifyUri.toString()");
                Referrer[] referrerArrValues = Referrer.values();
                int length = referrerArrValues.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        referrer = referrerArrValues[i2];
                        if (!v03.b(referrer.a(), str)) {
                            i2++;
                        }
                    } else {
                        referrer = null;
                    }
                }
                v03.i(th, "error");
                zn.b(znVar, string, referrer, th);
                break;
            case 3:
                v03.i(th, "error");
                zn.b(znVar, str, (Referrer) comparable, th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.c) {
            case 0:
                b((List) obj);
                break;
            case 1:
                a((Throwable) obj);
                break;
            case 2:
                b((List) obj);
                break;
            case 3:
                a((Throwable) obj);
                break;
            default:
                a((Throwable) obj);
                break;
        }
    }

    public final void b(List list) {
        int i = this.c;
        String str = this.k;
        Comparable comparable = this.l;
        zn znVar = this.e;
        switch (i) {
            case 0:
                znVar.b.l();
                if (list.isEmpty()) {
                    zn.a(znVar, str, (Referrer) comparable);
                }
                break;
            default:
                znVar.b.l();
                if (list.isEmpty()) {
                    zn.a(znVar, str, (Referrer) comparable);
                }
                break;
        }
    }

    public tn(zn znVar, if2 if2Var, String str) {
        this.c = 4;
        this.e = znVar;
        this.l = if2Var;
        this.k = str;
    }
}
