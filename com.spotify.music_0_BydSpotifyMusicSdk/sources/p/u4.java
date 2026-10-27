package p;

import android.os.Parcelable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceConfigurationError;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class u4 {
    public static int a(int i, int i2, int i3, int i4) {
        return com.google.protobuf.h.N(i) + i2 + i3 + i4;
    }

    public static String b(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        String string = sb.toString();
        v03.i(string, str2);
        return string;
    }

    public static String c(RecyclerView recyclerView, StringBuilder sb) {
        sb.append(recyclerView.y());
        return sb.toString();
    }

    public static String d(Class cls, StringBuilder sb, String str) {
        sb.append(cls.getName());
        sb.append(str);
        return sb.toString();
    }

    public static String e(String str, int i) {
        return str + i;
    }

    public static String f(String str, int i, String str2) {
        return str + i + str2;
    }

    public static String g(String str, long j) {
        return str + j;
    }

    public static String h(String str, String str2) {
        return str + str2;
    }

    public static String i(String str, String str2, char c, String str3, char c2) {
        return str + str2 + c + str3 + c2;
    }

    public static String j(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String k(StringBuilder sb, int i, char c) {
        sb.append(i);
        sb.append(c);
        return sb.toString();
    }

    public static String l(StringBuilder sb, int i, String str) {
        sb.append(i);
        sb.append(str);
        return sb.toString();
    }

    public static String m(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String n(StringBuilder sb, boolean z, char c) {
        sb.append(z);
        sb.append(c);
        return sb.toString();
    }

    public static StringBuilder o(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder p(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static /* synthetic */ Iterator q() {
        try {
            return Arrays.asList(new x6()).iterator();
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static /* synthetic */ void r() {
        throw new ClassCastException();
    }

    public static void s(int i, com.squareup.moshi.f fVar, com.squareup.moshi.m mVar, String str) {
        fVar.toJson(mVar, Integer.valueOf(i));
        mVar.J0(str);
    }

    public static /* synthetic */ void t(Parcelable parcelable) {
        if (parcelable != null) {
            throw new ClassCastException();
        }
    }

    public static /* synthetic */ void u(Object obj) {
        throw new ClassCastException();
    }

    public static /* synthetic */ void v(ty tyVar) {
        if (tyVar != null) {
            throw new ClassCastException();
        }
    }

    public static void w(boolean z, com.squareup.moshi.f fVar, com.squareup.moshi.m mVar, String str) {
        fVar.toJson(mVar, Boolean.valueOf(z));
        mVar.J0(str);
    }

    public static String x(String str, String str2) {
        return str + str2;
    }

    public static /* synthetic */ Iterator y() {
        try {
            return Arrays.asList(new w6()).iterator();
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static /* synthetic */ void z(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }
}
