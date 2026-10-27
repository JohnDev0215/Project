package com.blankj.utilcode.util;

import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class z {
    public static boolean a() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(i >= 24 ? w.a().getResources().getConfiguration().getLocales().get(0) : w.a().getResources().getConfiguration().locale) == 1;
        }
        return false;
    }

    public static View b(int i) {
        return ((LayoutInflater) w.a().getSystemService("layout_inflater")).inflate(i, (ViewGroup) null);
    }
}
