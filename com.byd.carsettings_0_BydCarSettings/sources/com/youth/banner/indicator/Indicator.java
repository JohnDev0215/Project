package com.youth.banner.indicator;

import android.view.View;
import androidx.annotation.NonNull;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

/* JADX INFO: loaded from: classes3.dex */
public interface Indicator extends OnPageChangeListener {
    IndicatorConfig getIndicatorConfig();

    @NonNull
    View getIndicatorView();

    void onPageChanged(int i, int i2);
}
