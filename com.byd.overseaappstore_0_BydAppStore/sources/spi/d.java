package spi;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.hardware.bydauto.Log;
import androidx.fragment.app.Fragment;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.UInt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import spi.g5;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d {
    public static float a(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static int b(UInt uInt, int i) {
        return UInt.m157constructorimpl(uInt.getData() + i);
    }

    public static String c(String str, int i, String str2, int i2) {
        return str + i + str2 + i2;
    }

    public static String d(String str, long j) {
        return str + j;
    }

    public static String e(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static String f(String str, String str2) {
        return str + str2;
    }

    public static String g(String str, String str2, String str3, String str4) {
        return str + str2 + str3 + str4;
    }

    public static String h(StringBuilder sb, int i, String str) {
        sb.append(i);
        sb.append(str);
        return sb.toString();
    }

    public static StringBuilder i(char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        return sb;
    }

    public static StringBuilder j(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder k(String str, String str2, String str3, int i, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(i);
        sb.append(str4);
        return sb;
    }

    public static StringBuilder l(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        sb.append(str5);
        return sb;
    }

    public static ArrayList m(LinkedHashMap linkedHashMap, Object obj) {
        ArrayList arrayList = new ArrayList();
        linkedHashMap.put(obj, arrayList);
        return arrayList;
    }

    public static Iterator n(Iterable iterable, String str, Function1 function1, String str2) {
        Intrinsics.checkNotNullParameter(iterable, str);
        Intrinsics.checkNotNullParameter(function1, str2);
        return iterable.iterator();
    }

    public static ll o(g5 g5Var, int i) {
        return u6.a(new g5.a(g5Var, i));
    }

    public static void p(int i, Canvas canvas, int i2, int i3) {
        InlineMarker.finallyStart(i);
        canvas.restoreToCount(i2);
        InlineMarker.finallyEnd(i3);
    }

    public static void q(int i, SkinView skinView) {
        skinView.setBackground(new ColorDrawable(i));
    }

    public static void r(String str, int i, String str2) {
        Log.v(str2, str + i);
    }

    public static void s(StringBuilder sb, int i, String str, long j) {
        sb.append(i);
        sb.append(str);
        sb.append(j);
    }

    public static void t(StringBuilder sb, int i, String str, String str2, String str3) {
        sb.append(i);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
    }

    public static void u(StringBuilder sb, String str, String str2, String str3) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
    }

    public static void v(int[] iArr, StringBuilder sb, String str) {
        sb.append(Arrays.toString(iArr));
        android.util.Log.i(str, sb.toString());
    }

    public static void w(String str, int i, String str2) {
        android.util.Log.d(str2, str + i);
    }

    public static void x(String str, int i, String str2) {
        android.util.Log.w(str2, str + i);
    }
}
