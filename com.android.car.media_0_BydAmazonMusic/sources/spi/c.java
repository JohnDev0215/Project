package spi;

import android.hardware.bydauto_test.TestACManagerImplCallback;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.RomUtils;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c {
    public static void A(String str, int i, String str2) {
        Log.w(str2, str + i);
    }

    public static float a(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static Integer b(int i, HashMap map, Integer num, int i2) {
        map.put(num, Integer.valueOf(i));
        return Integer.valueOf(i2);
    }

    public static String c(RecyclerView recyclerView, StringBuilder sb) {
        sb.append(recyclerView.exceptionLabel());
        return sb.toString();
    }

    public static String d(String str, int i) {
        return str + i;
    }

    public static String e(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static String f(String str, String str2) {
        return str + str2;
    }

    public static String g(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String h(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder i(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(str);
        return sb;
    }

    public static StringBuilder j(TestACManagerImplCallback testACManagerImplCallback, int i, int i2, String str) {
        testACManagerImplCallback.enqueueMessage(i, i2);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder k(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder l(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder m(String str, int i, String str2, int i2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        sb.append(i2);
        sb.append(str3);
        return sb;
    }

    public static StringBuilder n(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder o(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static void p(String str, int i, String str2) {
        android.hardware.bydauto.Log.v(str2, str + i);
    }

    public static void q(StringBuilder sb, int i, String str) {
        sb.append(i);
        android.hardware.bydauto.Log.i(str, sb.toString());
    }

    public static void r(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        android.hardware.bydauto.Log.v(str2, sb.toString());
    }

    public static void s(int[] iArr, StringBuilder sb, String str) {
        sb.append(Arrays.toString(iArr));
        Log.i(str, sb.toString());
    }

    public static boolean t(String str) {
        return str.equals(RomUtils.getRomInfo().name);
    }

    public static void u(String str, int i, String str2) {
        Log.v(str2, str + i);
    }

    public static void v(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        android.hardware.bydauto.Log.d(str2, sb.toString());
    }

    public static void w(String str, int i, String str2) {
        Log.d(str2, str + i);
    }

    public static void x(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        Log.d(str2, sb.toString());
    }

    public static void y(String str, int i, String str2) {
        android.hardware.bydauto.Log.i(str2, str + i);
    }

    public static void z(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        Log.e(str2, sb.toString());
    }
}
