package spi;

import android.hardware.bydauto.Log;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class hi {
    public static Object a(CharSequence charSequence, int i, Function1 function1) {
        return function1.invoke(Character.valueOf(charSequence.charAt(i)));
    }

    public static Object b(int[] iArr, int i, Function1 function1) {
        return function1.invoke(UInt.m151boximpl(UIntArray.m216getpVg5ArA(iArr, i)));
    }

    public static Object c(short[] sArr, int i, Function1 function1) {
        return function1.invoke(UShort.m335boximpl(UShortArray.m398getMh2AYeg(sArr, i)));
    }

    public static String d(RecyclerView recyclerView, StringBuilder sb) {
        sb.append(recyclerView.exceptionLabel());
        return sb.toString();
    }

    public static String e(String str, boolean z) {
        return str + z;
    }

    public static String f(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String g(StringBuilder sb, String str, String str2, int i) {
        sb.append(str);
        sb.append(str2);
        sb.append(i);
        return sb.toString();
    }

    public static void h(StringBuilder sb, int i, String str) {
        sb.append(i);
        Log.i(str, sb.toString());
    }

    public static void i(StringBuilder sb, int i, String str, int i2, String str2) {
        sb.append(i);
        sb.append(str);
        sb.append(i2);
        sb.append(str2);
    }

    public static void j(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        android.util.Log.e(str2, sb.toString());
    }

    public static boolean k(Flags.BooleanFlagField booleanFlagField, int i, String str) {
        Boolean bool = booleanFlagField.get(i);
        Intrinsics.checkNotNullExpressionValue(bool, str);
        return bool.booleanValue();
    }
}
