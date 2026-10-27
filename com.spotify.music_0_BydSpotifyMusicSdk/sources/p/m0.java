package p;

import android.content.Context;
import java.util.Set;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class m0 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;

    public /* synthetic */ m0(xy1 xy1Var, int i) {
        this.a = i;
        this.b = xy1Var;
    }

    public static te c(ut utVar) {
        v03.j(utVar, "clientInfo");
        return new te(1, utVar);
    }

    public static ue d(OkHttpClient okHttpClient) {
        v03.j(okHttpClient, "okHttp");
        return new ue(okHttpClient, 1);
    }

    public final te a() {
        int i = this.a;
        xy1 xy1Var = this.b;
        switch (i) {
            case 26:
                as2 as2Var = (as2) xy1Var.get();
                v03.j(as2Var, "usernameProvider");
                return new te(0, as2Var);
            default:
                return c((ut) xy1Var.get());
        }
    }

    public final ue b() {
        int i = this.a;
        xy1 xy1Var = this.b;
        switch (i) {
            case 27:
                OkHttpClient okHttpClient = (OkHttpClient) xy1Var.get();
                v03.j(okHttpClient, "okHttp");
                return new ue(okHttpClient, 0);
            default:
                return d((OkHttpClient) xy1Var.get());
        }
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.b;
        switch (i) {
            case 0:
                return new l0((Context) xy1Var.get());
            case 1:
                return new ju((ku) xy1Var.get());
            case 2:
                return new ku((vv2) xy1Var.get());
            case 3:
                return new z91((ku) xy1Var.get());
            case 4:
                return new j2((Context) xy1Var.get());
            case 5:
                return new ep1((rx) xy1Var.get());
            case 6:
                return new n52((xb1) xy1Var.get());
            case 7:
                return new v5(ua0.a(xy1Var), 0);
            case 8:
                return new v5(ua0.a(xy1Var), 1);
            case 9:
                return new v5(ua0.a(xy1Var), 2);
            case 10:
                return new v5(ua0.a(xy1Var), 3);
            case 11:
                return new v5(ua0.a(xy1Var), 4);
            case 12:
                return new v5(ua0.a(xy1Var), 5);
            case 13:
                return new v5(ua0.a(xy1Var), 6);
            case 14:
                return new v5(ua0.a(xy1Var), 7);
            case 15:
                return new v5(ua0.a(xy1Var), 8);
            case 16:
                return new v5(ua0.a(xy1Var), 9);
            case 17:
                return new v5(ua0.a(xy1Var), 10);
            case 18:
                return new v5(ua0.a(xy1Var), 11);
            case 19:
                return new v5(ua0.a(xy1Var), 12);
            case 20:
                return new v5(ua0.a(xy1Var), 13);
            case 21:
                return new v5(ua0.a(xy1Var), 14);
            case 22:
                return new v5(ua0.a(xy1Var), 15);
            case 23:
                return new v5(ua0.a(xy1Var), 16);
            case 24:
                return new v5(ua0.a(xy1Var), 17);
            case 25:
                return new xb1((Set) xy1Var.get());
            case 26:
                return a();
            case 27:
                return b();
            case 28:
                return a();
            default:
                return b();
        }
    }
}
