package com.airbnb.lottie;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import androidx.annotation.ColorInt;

/* JADX INFO: loaded from: classes.dex */
public class p extends PorterDuffColorFilter {
    public p(@ColorInt int i) {
        super(i, PorterDuff.Mode.SRC_ATOP);
    }
}
