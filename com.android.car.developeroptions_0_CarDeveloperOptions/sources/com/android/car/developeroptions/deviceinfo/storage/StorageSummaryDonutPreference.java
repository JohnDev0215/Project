package com.android.car.developeroptions.deviceinfo.storage;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.widget.DonutView;

/* JADX INFO: loaded from: classes.dex */
public class StorageSummaryDonutPreference extends Preference implements View.OnClickListener {
    private double mPercent;

    public StorageSummaryDonutPreference(Context context) {
        this(context, null);
    }

    public StorageSummaryDonutPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPercent = -1.0d;
        setLayoutResource(R.layout.storage_summary_donut);
        setEnabled(false);
    }

    public void setPercent(long j, long j2) {
        if (j2 == 0) {
            return;
        }
        this.mPercent = j / j2;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setClickable(false);
        DonutView donutView = (DonutView) preferenceViewHolder.findViewById(R.id.donut);
        if (donutView != null) {
            donutView.setPercentage(this.mPercent);
        }
        Button button = (Button) preferenceViewHolder.findViewById(R.id.deletion_helper_button);
        if (button != null) {
            button.setOnClickListener(this);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || R.id.deletion_helper_button != view.getId()) {
            return;
        }
        Context context = getContext();
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 840, new Pair[0]);
        getContext().startActivity(new Intent("android.os.storage.action.MANAGE_STORAGE"));
    }

    private static class BoldLinkSpan extends StyleSpan {
        public BoldLinkSpan() {
            super(1);
        }

        @Override // android.text.style.StyleSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(textPaint.linkColor);
        }
    }
}
