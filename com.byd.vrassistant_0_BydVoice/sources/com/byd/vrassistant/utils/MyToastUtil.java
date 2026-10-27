package com.byd.vrassistant.utils;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class MyToastUtil {
    public static void show(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }
}
