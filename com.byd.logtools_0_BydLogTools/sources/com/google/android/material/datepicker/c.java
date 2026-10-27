package com.google.android.material.datepicker;

import android.os.Build;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateStrings.java */
/* JADX INFO: loaded from: classes.dex */
class c {
    static String a(long j) {
        return b(j, Locale.getDefault());
    }

    static String b(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? l.b(locale).format(new Date(j)) : l.f(locale).format(new Date(j));
    }

    static String c(long j) {
        return d(j, Locale.getDefault());
    }

    static String d(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? l.n(locale).format(new Date(j)) : l.f(locale).format(new Date(j));
    }
}
