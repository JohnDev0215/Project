package defpackage;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: ContactDetails.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xx implements Comparable<xx> {
    public final String e;
    public final String f;
    public final List<String> g;
    public final List<String> h;
    public final List<ky1> i;
    public final ArrayList j;
    public boolean k;
    public Drawable l;

    public xx(String str, String str2, List<String> list, List<String> list2, List<ky1> list3) {
        this.f = str2 == null ? "" : str2;
        this.g = list == null ? new ArrayList<>() : list;
        this.h = list2 == null ? new ArrayList<>() : list2;
        this.i = list3 == null ? new ArrayList<>() : list3;
        this.j = new ArrayList();
        this.e = str;
    }

    public static String a(String str) {
        String strReplaceAll = str.replaceAll("\n\n", "\n");
        while (true) {
            String str2 = strReplaceAll;
            String str3 = str;
            str = str2;
            if (str.length() >= str3.length()) {
                return str.replaceAll("\n", ", ");
            }
            strReplaceAll = str.replaceAll("\n\n", "\n");
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(xx xxVar) {
        return xxVar.f.compareTo(this.f);
    }

    public final String e(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (z) {
            int i2 = 0;
            for (ky1 ky1Var : this.i) {
                int i3 = i2 + 1;
                if (i2 > 0) {
                    sb.append("\n");
                }
                sb.append(a(ky1Var.c[0]));
                i2 = i3;
            }
            i = i2;
        }
        if (z2) {
            for (String str : this.g) {
                int i4 = i + 1;
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append(str);
                i = i4;
            }
        }
        if (z3) {
            for (String str2 : this.h) {
                int i5 = i + 1;
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append(str2);
                i = i5;
            }
        }
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof xx) {
            return this.e.equals(((xx) obj).e);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.e, this.f});
    }
}
