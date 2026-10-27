package p;

import android.net.Uri;
import com.spotify.automotive.ubi.Referrer;

/* JADX INFO: loaded from: classes.dex */
public final class kh1 {
    public final String a;
    public String b;
    public String c;
    public Integer d;

    public kh1(String str) {
        this.a = str;
    }

    public final String a() {
        Uri.Builder builderEncodedPath = new Uri.Builder().encodedPath(this.a);
        String str = this.b;
        if (str != null && str.length() != 0) {
            builderEncodedPath.appendQueryParameter("contextUri", this.b);
        }
        String str2 = this.c;
        if (str2 != null && str2.length() != 0) {
            builderEncodedPath.appendQueryParameter("ref", this.c);
        }
        Integer num = this.d;
        if (num != null) {
            builderEncodedPath.appendQueryParameter("pos", String.valueOf(num));
        }
        String string = builderEncodedPath.build().toString();
        v03.i(string, "builder.build().toString()");
        return string;
    }

    public final void b(Referrer referrer) {
        v03.j(referrer, "referrer");
        this.c = referrer.a();
    }
}
