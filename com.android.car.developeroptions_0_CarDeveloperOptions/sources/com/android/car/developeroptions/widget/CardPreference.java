package com.android.car.developeroptions.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class CardPreference extends Preference {
    public CardPreference(Context context) {
        this(context, null);
    }

    public CardPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.cardPreferenceStyle);
    }
}
