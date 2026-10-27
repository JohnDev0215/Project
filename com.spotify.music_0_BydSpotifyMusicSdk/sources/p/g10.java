package p;

import com.spotify.prefs.esperanto.proto.EsPrefs$PrefValues;
import com.spotify.rcs.resolver.grpc.v0.Configuration;

/* JADX INFO: loaded from: classes.dex */
public final class g10 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ String e;

    public /* synthetic */ g10(String str, int i) {
        this.c = i;
        this.e = str;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        String str = this.e;
        switch (i) {
            case 0:
                return ((EsPrefs$PrefValues) obj).f(str);
            default:
                Configuration configuration = (Configuration) obj;
                v03.i(configuration, "it");
                w01 w01VarB = v01.b(configuration);
                m02 m02Var = m02.c;
                return zb0.u(w01.a(w01VarB, null, str, 15));
        }
    }
}
