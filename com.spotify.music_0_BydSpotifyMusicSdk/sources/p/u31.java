package p;

import android.net.Uri;
import com.spotify.connectivity.httpwebgate.WebgateHelper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class u31 implements k51 {
    @Override // p.k51
    public final Object a(coil.intercept.b bVar, c00 c00Var) {
        Pattern pattern = v31.a;
        q31 q31VarA = bVar.d;
        v03.j(q31VarA, "originalRequest");
        Object obj = q31VarA.b;
        Uri uri = obj instanceof String ? Uri.parse((String) obj) : obj instanceof Uri ? (Uri) obj : null;
        if (uri != null) {
            Matcher matcher = v31.a.matcher(uri.toString());
            if (matcher.lookingAt()) {
                uri = Uri.parse(matcher.replaceFirst(Matcher.quoteReplacement("https://i.scdn.co/image/")));
                v03.i(uri, "parse(imageMatcher.repla…eReplacement(IMAGE_CDN)))");
            }
            if (v03.b(WebgateHelper.DEFAULT_WEBGATE_PROTOCOL, uri.getScheme()) || v03.b("http", uri.getScheme())) {
                String authority = uri.getAuthority();
                v03.g(authority, "null cannot be cast to non-null type kotlin.CharSequence");
                if (v31.b.matcher(authority).lookingAt()) {
                    Uri uriBuild = uri.buildUpon().appendQueryParameter("format", "webp").build();
                    o31 o31Var = new o31(q31VarA, q31VarA.a);
                    o31Var.c = uriBuild;
                    q31VarA = o31Var.a();
                }
            }
        }
        return bVar.b(q31VarA, c00Var);
    }
}
