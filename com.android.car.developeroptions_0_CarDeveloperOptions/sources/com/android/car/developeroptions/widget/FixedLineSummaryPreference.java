package com.android.car.developeroptions.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class FixedLineSummaryPreference extends Preference {
    private int mSummaryLineCount;

    public FixedLineSummaryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FixedLineSummaryPreference, 0, 0);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            this.mSummaryLineCount = typedArrayObtainStyledAttributes.getInteger(0, 1);
        } else {
            this.mSummaryLineCount = 1;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.findViewById(android.R.id.summary);
        if (textView != null) {
            textView.setMinLines(this.mSummaryLineCount);
            textView.setMaxLines(this.mSummaryLineCount);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }
}
