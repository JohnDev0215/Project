package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.EmptyList;
import kotlin.text.b;
import okhttp3.internal.Util;
import p.bk0;
import p.g81;
import p.ng2;
import p.rb;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Headers implements Iterable<Pair<? extends String, ? extends String>>, g81 {
    public static final Companion e = new Companion(0);
    public final String[] c;

    public static final class Builder {
        public final ArrayList a = new ArrayList(20);

        public final void a(String str, String str2) {
            v03.j(str, "name");
            v03.j(str2, "value");
            Headers.e.getClass();
            Companion.a(str);
            Companion.b(str2, str);
            c(str, str2);
        }

        public final void b(String str) {
            int iN0 = b.n0(str, ':', 1, false, 4);
            if (iN0 != -1) {
                String strSubstring = str.substring(0, iN0);
                v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                String strSubstring2 = str.substring(iN0 + 1);
                v03.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
                c(strSubstring, strSubstring2);
                return;
            }
            if (str.charAt(0) != ':') {
                c("", str);
                return;
            }
            String strSubstring3 = str.substring(1);
            v03.i(strSubstring3, "this as java.lang.String).substring(startIndex)");
            c("", strSubstring3);
        }

        public final void c(String str, String str2) {
            v03.j(str, "name");
            v03.j(str2, "value");
            ArrayList arrayList = this.a;
            arrayList.add(str);
            arrayList.add(b.G0(str2).toString());
        }

        public final void d(String str, String str2) {
            v03.j(str, "name");
            v03.j(str2, "value");
            Headers.e.getClass();
            Companion.a(str);
            c(str, str2);
        }

        public final Headers e() {
            return new Headers((String[]) this.a.toArray(new String[0]));
        }

        public final String f(String str) {
            v03.j(str, "name");
            ArrayList arrayList = this.a;
            int size = arrayList.size() - 2;
            int iT = v03.t(size, 0, -2);
            if (iT > size) {
                return null;
            }
            while (!ng2.b0(str, (String) arrayList.get(size), true)) {
                if (size == iT) {
                    return null;
                }
                size -= 2;
            }
            return (String) arrayList.get(size + 1);
        }

        public final void g(String str) {
            v03.j(str, "name");
            int i = 0;
            while (true) {
                ArrayList arrayList = this.a;
                if (i >= arrayList.size()) {
                    return;
                }
                if (ng2.b0(str, (String) arrayList.get(i), true)) {
                    arrayList.remove(i);
                    arrayList.remove(i);
                    i -= 2;
                }
                i += 2;
            }
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static void a(String str) {
            if (str.length() <= 0) {
                throw new IllegalArgumentException("name is empty".toString());
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if ('!' > cCharAt || cCharAt >= 127) {
                    throw new IllegalArgumentException(Util.i("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str).toString());
                }
            }
        }

        public static void b(String str, String str2) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt != '\t' && (' ' > cCharAt || cCharAt >= 127)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Util.i("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i), str2));
                    sb.append(Util.q(str2) ? "" : ": ".concat(str));
                    throw new IllegalArgumentException(sb.toString().toString());
                }
            }
        }

        public static Headers c(String... strArr) {
            if (strArr.length % 2 != 0) {
                throw new IllegalArgumentException("Expected alternating header names and values".toString());
            }
            String[] strArr2 = (String[]) strArr.clone();
            int length = strArr2.length;
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                String str = strArr2[i2];
                if (str == null) {
                    throw new IllegalArgumentException("Headers cannot be null".toString());
                }
                strArr2[i2] = b.G0(str).toString();
            }
            int iT = v03.t(0, strArr2.length - 1, 2);
            if (iT >= 0) {
                while (true) {
                    String str2 = strArr2[i];
                    String str3 = strArr2[i + 1];
                    a(str2);
                    b(str3, str2);
                    if (i == iT) {
                        break;
                    }
                    i += 2;
                }
            }
            return new Headers(strArr2);
        }
    }

    public Headers(String[] strArr) {
        this.c = strArr;
    }

    public final String a(String str) {
        v03.j(str, "name");
        e.getClass();
        String[] strArr = this.c;
        int length = strArr.length - 2;
        int iT = v03.t(length, 0, -2);
        if (iT <= length) {
            while (!ng2.b0(str, strArr[length], true)) {
                if (length != iT) {
                    length -= 2;
                }
            }
            return strArr[length + 1];
        }
        return null;
    }

    public final String b(int i) {
        return this.c[i * 2];
    }

    public final Builder c() {
        Builder builder = new Builder();
        ArrayList arrayList = builder.a;
        v03.j(arrayList, "<this>");
        String[] strArr = this.c;
        v03.j(strArr, "elements");
        arrayList.addAll(rb.h0(strArr));
        return builder;
    }

    public final String d(int i) {
        return this.c[(i * 2) + 1];
    }

    public final List e(String str) {
        v03.j(str, "name");
        int size = size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            if (ng2.b0(str, b(i), true)) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(d(i));
            }
        }
        if (arrayList == null) {
            return EmptyList.c;
        }
        List listUnmodifiableList = Collections.unmodifiableList(arrayList);
        v03.i(listUnmodifiableList, "{\n      Collections.unmodifiableList(result)\n    }");
        return listUnmodifiableList;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Headers) {
            if (Arrays.equals(this.c, ((Headers) obj).c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.c);
    }

    @Override // java.lang.Iterable
    public final Iterator<Pair<? extends String, ? extends String>> iterator() {
        int size = size();
        Pair[] pairArr = new Pair[size];
        for (int i = 0; i < size; i++) {
            pairArr[i] = new Pair(b(i), d(i));
        }
        return bk0.K(pairArr);
    }

    public final int size() {
        return this.c.length / 2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            String strB = b(i);
            String strD = d(i);
            sb.append(strB);
            sb.append(": ");
            if (Util.q(strB)) {
                strD = "██";
            }
            sb.append(strD);
            sb.append("\n");
        }
        String string = sb.toString();
        v03.i(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
