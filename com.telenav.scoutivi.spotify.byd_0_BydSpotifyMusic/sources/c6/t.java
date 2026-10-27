package c6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: Headers.kt */
/* JADX INFO: loaded from: classes.dex */
public final class t implements Iterable<g5.k<? extends String, ? extends String>>, s5.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f3843g = new b(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String[] f3844e;

    /* JADX INFO: compiled from: Headers.kt */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<String> f3845a = new ArrayList(20);

        public final a a(String name, String value) {
            kotlin.jvm.internal.l.e(name, "name");
            kotlin.jvm.internal.l.e(value, "value");
            b bVar = t.f3843g;
            bVar.d(name);
            bVar.e(value, name);
            c(name, value);
            return this;
        }

        public final a b(String line) {
            kotlin.jvm.internal.l.e(line, "line");
            int iP = y5.q.P(line, ':', 1, false, 4, null);
            if (iP != -1) {
                String strSubstring = line.substring(0, iP);
                kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                String strSubstring2 = line.substring(iP + 1);
                kotlin.jvm.internal.l.d(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                c(strSubstring, strSubstring2);
            } else if (line.charAt(0) == ':') {
                String strSubstring3 = line.substring(1);
                kotlin.jvm.internal.l.d(strSubstring3, "(this as java.lang.String).substring(startIndex)");
                c("", strSubstring3);
            } else {
                c("", line);
            }
            return this;
        }

        public final a c(String name, String value) {
            kotlin.jvm.internal.l.e(name, "name");
            kotlin.jvm.internal.l.e(value, "value");
            this.f3845a.add(name);
            this.f3845a.add(y5.q.z0(value).toString());
            return this;
        }

        public final t d() {
            Object[] array = this.f3845a.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            return new t((String[]) array, null);
        }

        public final List<String> e() {
            return this.f3845a;
        }

        public final a f(String name) {
            kotlin.jvm.internal.l.e(name, "name");
            int i7 = 0;
            while (i7 < this.f3845a.size()) {
                if (y5.p.o(name, this.f3845a.get(i7), true)) {
                    this.f3845a.remove(i7);
                    this.f3845a.remove(i7);
                    i7 -= 2;
                }
                i7 += 2;
            }
            return this;
        }

        public final a g(String name, String value) {
            kotlin.jvm.internal.l.e(name, "name");
            kotlin.jvm.internal.l.e(value, "value");
            b bVar = t.f3843g;
            bVar.d(name);
            bVar.e(value, name);
            f(name);
            c(name, value);
            return this;
        }
    }

    /* JADX INFO: compiled from: Headers.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void d(String str) {
            if (!(str.length() > 0)) {
                throw new IllegalArgumentException("name is empty".toString());
            }
            int length = str.length();
            for (int i7 = 0; i7 < length; i7++) {
                char cCharAt = str.charAt(i7);
                if (!('!' <= cCharAt && '~' >= cCharAt)) {
                    throw new IllegalArgumentException(d6.b.p("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i7), str).toString());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void e(String str, String str2) {
            int length = str.length();
            for (int i7 = 0; i7 < length; i7++) {
                char cCharAt = str.charAt(i7);
                if (!(cCharAt == '\t' || (' ' <= cCharAt && '~' >= cCharAt))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(d6.b.p("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i7), str2));
                    sb.append(d6.b.C(str2) ? "" : ": " + str);
                    throw new IllegalArgumentException(sb.toString().toString());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String f(String[] strArr, String str) {
            v5.a aVarH = v5.h.h(v5.h.g(strArr.length - 2, 0), 2);
            int iC = aVarH.c();
            int iD = aVarH.d();
            int iE = aVarH.e();
            if (iE >= 0) {
                if (iC > iD) {
                    return null;
                }
            } else if (iC < iD) {
                return null;
            }
            while (!y5.p.o(str, strArr[iC], true)) {
                if (iC == iD) {
                    return null;
                }
                iC += iE;
            }
            return strArr[iC + 1];
        }

        public final t g(String... namesAndValues) throws CloneNotSupportedException {
            kotlin.jvm.internal.l.e(namesAndValues, "namesAndValues");
            if (!(namesAndValues.length % 2 == 0)) {
                throw new IllegalArgumentException("Expected alternating header names and values".toString());
            }
            Object objClone = namesAndValues.clone();
            Objects.requireNonNull(objClone, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            String[] strArr = (String[]) objClone;
            int length = strArr.length;
            for (int i7 = 0; i7 < length; i7++) {
                if (!(strArr[i7] != null)) {
                    throw new IllegalArgumentException("Headers cannot be null".toString());
                }
                String str = strArr[i7];
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.CharSequence");
                strArr[i7] = y5.q.z0(str).toString();
            }
            v5.a aVarH = v5.h.h(h5.l.q(strArr), 2);
            int iC = aVarH.c();
            int iD = aVarH.d();
            int iE = aVarH.e();
            if (iE < 0 ? iC >= iD : iC <= iD) {
                while (true) {
                    String str2 = strArr[iC];
                    String str3 = strArr[iC + 1];
                    d(str2);
                    e(str3, str2);
                    if (iC == iD) {
                        break;
                    }
                    iC += iE;
                }
            }
            return new t(strArr, null);
        }
    }

    private t(String[] strArr) {
        this.f3844e = strArr;
    }

    public final String c(String name) {
        kotlin.jvm.internal.l.e(name, "name");
        return f3843g.f(this.f3844e, name);
    }

    public final String d(int i7) {
        return this.f3844e[i7 * 2];
    }

    public final a e() {
        a aVar = new a();
        h5.u.s(aVar.e(), this.f3844e);
        return aVar;
    }

    public boolean equals(Object obj) {
        return (obj instanceof t) && Arrays.equals(this.f3844e, ((t) obj).f3844e);
    }

    public final String f(int i7) {
        return this.f3844e[(i7 * 2) + 1];
    }

    public final List<String> g(String name) {
        kotlin.jvm.internal.l.e(name, "name");
        int size = size();
        ArrayList arrayList = null;
        for (int i7 = 0; i7 < size; i7++) {
            if (y5.p.o(name, d(i7), true)) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(f(i7));
            }
        }
        if (arrayList == null) {
            return h5.p.h();
        }
        List<String> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        kotlin.jvm.internal.l.d(listUnmodifiableList, "Collections.unmodifiableList(result)");
        return listUnmodifiableList;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3844e);
    }

    @Override // java.lang.Iterable
    public Iterator<g5.k<? extends String, ? extends String>> iterator() {
        int size = size();
        g5.k[] kVarArr = new g5.k[size];
        for (int i7 = 0; i7 < size; i7++) {
            kVarArr[i7] = g5.o.a(d(i7), f(i7));
        }
        return kotlin.jvm.internal.b.a(kVarArr);
    }

    public final int size() {
        return this.f3844e.length / 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            String strD = d(i7);
            String strF = f(i7);
            sb.append(strD);
            sb.append(": ");
            if (d6.b.C(strD)) {
                strF = "██";
            }
            sb.append(strF);
            sb.append("\n");
        }
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public /* synthetic */ t(String[] strArr, kotlin.jvm.internal.g gVar) {
        this(strArr);
    }
}
