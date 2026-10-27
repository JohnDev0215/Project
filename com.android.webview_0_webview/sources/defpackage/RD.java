package defpackage;

import java.util.Locale;
import java.util.Map;
import org.chromium.content_public.common.ResourceRequestBody;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RD {
    public String a;
    public int c;
    public C0984jE d;
    public Map e;
    public boolean k;
    public int b = 0;
    public int f = 0;
    public ResourceRequestBody g = null;
    public String h = null;
    public String i = null;
    public String j = null;

    public RD(String str, int i) {
        this.a = str;
        this.c = i;
    }

    public static String a(String str, String str2, boolean z, String str3) {
        StringBuilder sb = new StringBuilder("data:");
        sb.append(str2);
        if (str3 != null && !str3.isEmpty()) {
            sb.append(";charset=" + str3);
        }
        if (z) {
            sb.append(";base64");
        }
        sb.append(",");
        sb.append(str);
        return sb.toString();
    }

    public static RD b(String str, String str2, boolean z, String str3) {
        RD rd = new RD(a(str, str2, z, str3), 0);
        rd.b = 2;
        rd.c = 1;
        return rd;
    }

    public static RD c(String str, String str2, boolean z, String str3, String str4, String str5) {
        if (str3 != null && str3.toLowerCase(Locale.US).startsWith("data:")) {
            return b(str, str2, z, str5);
        }
        RD rdB = b("", str2, z, str5);
        if (str3 == null) {
            str3 = "about:blank";
        }
        rdB.h = str3;
        if (str4 == null) {
            str4 = "about:blank";
        }
        rdB.i = str4;
        rdB.j = a(str, str2, z, str5);
        return rdB;
    }

    public final String d(String str, boolean z) {
        if (this.e == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : this.e.entrySet()) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(((String) entry.getKey()).toLowerCase(Locale.US));
            sb.append(":");
            sb.append((String) entry.getValue());
        }
        if (z) {
            sb.append(str);
        }
        return sb.toString();
    }
}
