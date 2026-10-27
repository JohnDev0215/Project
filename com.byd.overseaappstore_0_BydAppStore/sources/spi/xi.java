package spi;

import java.util.Iterator;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class xi {
    public static int a(CharSequence charSequence, String str, Function1 function1, String str2) {
        Intrinsics.checkNotNullParameter(charSequence, str);
        Intrinsics.checkNotNullParameter(function1, str2);
        return charSequence.length();
    }

    public static int b(String str, int i, int i2) {
        return (str.hashCode() + i) * i2;
    }

    public static Object c(byte[] bArr, int i, Function1 function1) {
        return function1.invoke(UByte.m75boximpl(UByteArray.m138getw2LRezQ(bArr, i)));
    }

    public static Object d(long[] jArr, int i, Function1 function1) {
        return function1.invoke(ULong.m229boximpl(ULongArray.m294getsVKNKU(jArr, i)));
    }

    public static String e(String str, int i, String str2) {
        return str + i + str2;
    }

    public static String f(String str, int i, String str2, String str3) {
        return str + i + str2 + str3;
    }

    public static StringBuilder g(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(str);
        return sb;
    }

    public static StringBuilder h(String str, int i, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb;
    }

    public static Iterator i(Sequence sequence, String str, Function1 function1, String str2) {
        Intrinsics.checkNotNullParameter(sequence, str);
        Intrinsics.checkNotNullParameter(function1, str2);
        return sequence.iterator();
    }
}
