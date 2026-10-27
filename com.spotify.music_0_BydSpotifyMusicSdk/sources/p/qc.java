package p;

import android.net.Uri;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class qc {
    public static final Pattern a = Pattern.compile("\\?.*");
    public static final Pattern b = Pattern.compile("^/+");
    public static final Uri c;

    static {
        Pattern.compile("\\?.*");
        c = Uri.parse("https://open.spotify.com/internal/pullcontext");
    }

    public static final Uri a(Uri uri) {
        String strReplaceFirst;
        v03.j(uri, "inputUri");
        boolean zB = v03.b("alexa", uri.getScheme());
        Uri uri2 = c;
        if (zB) {
            String path = uri.getPath();
            if (path == null) {
                strReplaceFirst = a.matcher(uri.getSchemeSpecificPart()).replaceFirst("");
                v03.i(strReplaceFirst, "{\n            ALEXA_FRAG…eplaceFirst(\"\")\n        }");
            } else {
                strReplaceFirst = b.matcher(path).replaceFirst("");
                v03.i(strReplaceFirst, "{\n            ALEXA_SLAS…eplaceFirst(\"\")\n        }");
            }
            uri = Uri.parse(strReplaceFirst);
            v03.i(uri, "parse(uri)");
            if (v03.b(uri, Uri.EMPTY)) {
                v03.i(uri2, "PULL_CONTEXT_URI");
                return uri2;
            }
        } else if (v03.b(uri, Uri.EMPTY)) {
            v03.i(uri2, "PULL_CONTEXT_URI");
            uri = uri2;
        }
        return uri;
    }
}
