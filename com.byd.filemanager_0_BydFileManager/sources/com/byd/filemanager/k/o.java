package com.byd.filemanager.k;

import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: compiled from: TypedArrayUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class o {
    public static int[] a(Context context, int i) {
        TypedArray typedArrayObtainTypedArray = context.getResources().obtainTypedArray(i);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, 0);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }
}
