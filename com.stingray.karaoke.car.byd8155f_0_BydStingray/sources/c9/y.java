package c9;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class y {
    public static final Pattern d = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f12626e = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12627a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12628b;
    public final String[] c;

    public y(String str, String str2, String[] strArr) {
        this.f12627a = str;
        this.f12628b = str2;
        this.c = strArr;
    }

    public final Charset a(Charset charset) {
        String str;
        String[] strArr = this.c;
        int i7 = 0;
        int iN = n7.z.n(0, strArr.length - 1, 2);
        if (iN < 0) {
            str = null;
            break;
        }
        while (!Q8.o.t0(strArr[i7], "charset", true)) {
            if (i7 == iN) {
                str = null;
                break;
            }
            i7 += 2;
        }
        str = strArr[i7 + 1];
        if (str == null) {
            return charset;
        }
        try {
            return Charset.forName(str);
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof y) && A7.m.a(((y) obj).f12627a, this.f12627a);
    }

    public final int hashCode() {
        return this.f12627a.hashCode();
    }

    public final String toString() {
        return this.f12627a;
    }
}
