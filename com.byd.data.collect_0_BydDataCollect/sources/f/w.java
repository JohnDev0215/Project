package f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String[] f5512a;

    /* JADX INFO: compiled from: Headers.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<String> f5513a = new ArrayList(20);

        public a a(String str, String str2) {
            w.a(str);
            w.b(str2, str);
            return d(str, str2);
        }

        public a b(w wVar) {
            int iH = wVar.h();
            for (int i2 = 0; i2 < iH; i2++) {
                d(wVar.e(i2), wVar.i(i2));
            }
            return this;
        }

        a c(String str) {
            int iIndexOf = str.indexOf(":", 1);
            return iIndexOf != -1 ? d(str.substring(0, iIndexOf), str.substring(iIndexOf + 1)) : str.startsWith(":") ? d("", str.substring(1)) : d("", str);
        }

        a d(String str, String str2) {
            this.f5513a.add(str);
            this.f5513a.add(str2.trim());
            return this;
        }

        public a e(String str, String str2) {
            w.a(str);
            return d(str, str2);
        }

        public w f() {
            return new w(this);
        }

        public a g(String str) {
            int i2 = 0;
            while (i2 < this.f5513a.size()) {
                if (str.equalsIgnoreCase(this.f5513a.get(i2))) {
                    this.f5513a.remove(i2);
                    this.f5513a.remove(i2);
                    i2 -= 2;
                }
                i2 += 2;
            }
            return this;
        }

        public a h(String str, String str2) {
            w.a(str);
            w.b(str2, str);
            g(str);
            d(str, str2);
            return this;
        }
    }

    w(a aVar) {
        List<String> list = aVar.f5513a;
        this.f5512a = (String[]) list.toArray(new String[list.size()]);
    }

    static void a(String str) {
        Objects.requireNonNull(str, "name == null");
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= ' ' || cCharAt >= 127) {
                throw new IllegalArgumentException(f.k0.e.p("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i2), str));
            }
        }
    }

    static void b(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("value for name " + str2 + " == null");
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt <= 31 && cCharAt != '\t') || cCharAt >= 127) {
                throw new IllegalArgumentException(f.k0.e.p("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt), Integer.valueOf(i2), str2, str));
            }
        }
    }

    @Nullable
    private static String d(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static w g(String... strArr) {
        Objects.requireNonNull(strArr, "namesAndValues == null");
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            if (strArr2[i2] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i2] = strArr2[i2].trim();
        }
        for (int i3 = 0; i3 < strArr2.length; i3 += 2) {
            String str = strArr2[i3];
            String str2 = strArr2[i3 + 1];
            a(str);
            b(str2, str);
        }
        return new w(strArr2);
    }

    @Nullable
    public String c(String str) {
        return d(this.f5512a, str);
    }

    public String e(int i2) {
        return this.f5512a[i2 * 2];
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof w) && Arrays.equals(((w) obj).f5512a, this.f5512a);
    }

    public a f() {
        a aVar = new a();
        Collections.addAll(aVar.f5513a, this.f5512a);
        return aVar;
    }

    public int h() {
        return this.f5512a.length / 2;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f5512a);
    }

    public String i(int i2) {
        return this.f5512a[(i2 * 2) + 1];
    }

    public List<String> j(String str) {
        int iH = h();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < iH; i2++) {
            if (str.equalsIgnoreCase(e(i2))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(i(i2));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iH = h();
        for (int i2 = 0; i2 < iH; i2++) {
            sb.append(e(i2));
            sb.append(": ");
            sb.append(i(i2));
            sb.append("\n");
        }
        return sb.toString();
    }

    private w(String[] strArr) {
        this.f5512a = strArr;
    }
}
