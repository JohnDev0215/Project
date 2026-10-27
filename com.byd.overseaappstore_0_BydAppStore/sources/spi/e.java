package spi;

import android.hardware.bydauto_test.TestACManagerImplCallback;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class e {
    public static int a(IntRange intRange, int i) {
        return intRange.getEndInclusive().intValue() + i;
    }

    public static Integer b(int i, HashMap map, Integer num, int i2) {
        map.put(num, Integer.valueOf(i));
        return Integer.valueOf(i2);
    }

    public static String c(String str, int i) {
        return str + i;
    }

    public static String d(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String e(String str, a2 a2Var) {
        return str + a2Var;
    }

    public static String f(StringBuilder sb, int i, String str, int i2) {
        sb.append(i);
        sb.append(str);
        sb.append(i2);
        return sb.toString();
    }

    public static String g(StringBuilder sb, long j, String str, a2 a2Var) {
        sb.append(j);
        sb.append(str);
        sb.append(a2Var);
        return sb.toString();
    }

    public static String h(StringBuilder sb, String str, char c) {
        sb.append(str);
        sb.append(c);
        return sb.toString();
    }

    public static String i(StringBuilder sb, String str, a2 a2Var) {
        sb.append(str);
        sb.append(a2Var);
        return sb.toString();
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

    public static StringBuilder l(String str, int i, String str2, int i2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        sb.append(i2);
        sb.append(str3);
        return sb;
    }

    public static StringBuilder m(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder n(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static ArrayList o(Map map, Object obj) {
        ArrayList arrayList = new ArrayList();
        map.put(obj, arrayList);
        return arrayList;
    }

    public static KotlinNothingValueException p() {
        FlowKt.noImpl();
        return new KotlinNothingValueException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator] */
    public static IntIterator q(int i, int i2) {
        return new IntRange(i, i2).iterator();
    }

    public static void r(String str, int i, String str2) {
        Log.v(str2, str + i);
    }

    public static void s(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        android.hardware.bydauto.Log.v(str2, sb.toString());
    }

    public static void t(String str, int i, String str2) {
        android.hardware.bydauto.Log.i(str2, str + i);
    }

    public static void u(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        android.hardware.bydauto.Log.d(str2, sb.toString());
    }

    public static void v(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        Log.d(str2, sb.toString());
    }
}
