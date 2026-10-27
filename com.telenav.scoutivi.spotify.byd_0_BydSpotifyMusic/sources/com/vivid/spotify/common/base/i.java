package com.vivid.spotify.common.base;

import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: compiled from: Constants.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    public static boolean a() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
}
