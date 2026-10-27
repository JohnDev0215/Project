package p;

import android.net.Uri;
import com.spotify.connectivity.httpwebgate.WebgateHelper;

/* JADX INFO: loaded from: classes.dex */
public final class p21 implements pt0 {
    public final v81 a;
    public final v81 b;
    public final boolean c;

    public p21(v81 v81Var, v81 v81Var2, boolean z) {
        this.a = v81Var;
        this.b = v81Var2;
        this.c = z;
    }

    @Override // p.pt0
    public final qt0 a(Object obj, br1 br1Var, l31 l31Var) {
        Uri uri = (Uri) obj;
        if (v03.b(uri.getScheme(), "http") || v03.b(uri.getScheme(), WebgateHelper.DEFAULT_WEBGATE_PROTOCOL)) {
            return new coil.fetch.a(uri.toString(), br1Var, this.a, this.b, this.c);
        }
        return null;
    }
}
