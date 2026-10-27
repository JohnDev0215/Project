package defpackage;

import android.text.SpannableString;
import java.util.Arrays;

/* JADX INFO: compiled from: SpanApplier.java */
/* JADX INFO: loaded from: classes3.dex */
public final class iq2 {
    public static SpannableString a(String str, a... aVarArr) {
        Object[] objArr;
        c(str, aVarArr);
        StringBuilder sb = new StringBuilder(str.length());
        int length = 0;
        for (a aVar : aVarArr) {
            d(aVar, str, length);
            sb.append((CharSequence) str, length, aVar.h);
            int length2 = aVar.e.length() + aVar.h;
            aVar.h = sb.length();
            sb.append((CharSequence) str, length2, aVar.i);
            length = aVar.i + aVar.f.length();
            aVar.i = sb.length();
        }
        sb.append((CharSequence) str, length, str.length());
        SpannableString spannableString = new SpannableString(sb);
        for (a aVar2 : aVarArr) {
            if (aVar2.h != -1 && (objArr = aVar2.g) != null && objArr.length != 0) {
                for (Object obj : objArr) {
                    if (obj != null) {
                        spannableString.setSpan(obj, aVar2.h, aVar2.i, 0);
                    }
                }
            }
        }
        return spannableString;
    }

    public static String b(String str, a... aVarArr) {
        c(str, aVarArr);
        StringBuilder sb = new StringBuilder(str.length());
        int length = 0;
        for (a aVar : aVarArr) {
            d(aVar, str, length);
            sb.append((CharSequence) str, length, aVar.h);
            length = aVar.i + aVar.f.length();
        }
        sb.append((CharSequence) str, length, str.length());
        return sb.toString();
    }

    public static void c(String str, a... aVarArr) {
        for (a aVar : aVarArr) {
            int iIndexOf = str.indexOf(aVar.e);
            aVar.h = iIndexOf;
            aVar.i = str.indexOf(aVar.f, aVar.e.length() + iIndexOf);
        }
        Arrays.sort(aVarArr);
    }

    public static void d(a aVar, String str, int i) {
        int i2 = aVar.h;
        if (i2 == -1 || aVar.i == -1 || i2 < i) {
            aVar.h = -1;
            throw new IllegalArgumentException(String.format("Input string is missing tags %s%s: %s", aVar.e, aVar.f, str));
        }
    }

    /* JADX INFO: compiled from: SpanApplier.java */
    public static final class a implements Comparable<a> {
        public final String e;
        public final String f;
        public final Object[] g;
        public int h;
        public int i;

        public a(Object obj, String str, String str2) {
            this.e = str;
            this.f = str2;
            this.g = obj == null ? null : new Object[]{obj};
        }

        @Override // java.lang.Comparable
        public final int compareTo(a aVar) {
            int i = this.h;
            int i2 = aVar.h;
            if (i < i2) {
                return -1;
            }
            return i == i2 ? 0 : 1;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            int i = this.h;
            int i2 = ((a) obj).h;
            return (i < i2 ? (byte) -1 : i == i2 ? (byte) 0 : (byte) 1) == 0;
        }

        public final int hashCode() {
            return 0;
        }

        public a(Object... objArr) {
            this.e = "<new>";
            this.f = "</new>";
            this.g = objArr;
        }
    }
}
