package com.android.car.settings.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiPreference;

/* JADX INFO: loaded from: classes5.dex */
public class ProgressBarPreference extends CarUiPreference {
    private int mMax;
    private CharSequence mMaxLabel;
    private int mMin;
    private CharSequence mMinLabel;
    private int mProgress;

    public ProgressBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public ProgressBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ProgressBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProgressBarPreference(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressBarPreference);
        this.mMin = a.getInteger(R.styleable.ProgressBarPreference_min, 0);
        this.mMax = a.getInteger(R.styleable.ProgressBarPreference_max, 100);
        this.mProgress = a.getInteger(R.styleable.ProgressBarPreference_progress, 0);
        this.mMinLabel = a.getString(R.styleable.ProgressBarPreference_minLabel);
        this.mMaxLabel = a.getString(R.styleable.ProgressBarPreference_maxLabel);
        a.recycle();
    }

    public void setMinLabel(CharSequence startLabel) {
        if (this.mMinLabel != startLabel) {
            this.mMinLabel = startLabel;
            notifyChanged();
        }
    }

    public void setMaxLabel(CharSequence endLabel) {
        if (this.mMaxLabel != endLabel) {
            this.mMaxLabel = endLabel;
            notifyChanged();
        }
    }

    public void setMin(int min) {
        if (this.mMin != min) {
            this.mMin = min;
            notifyChanged();
        }
    }

    public void setMax(int max) {
        if (this.mMax != max) {
            this.mMax = max;
            notifyChanged();
        }
    }

    public void setProgress(int progress) {
        if (this.mProgress != progress) {
            this.mProgress = progress;
            notifyChanged();
        }
    }

    public int getProgress() {
        return this.mProgress;
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        ProgressBar progressBar = (ProgressBar) view.findViewById(android.R.id.progress);
        progressBar.setMin(this.mMin);
        progressBar.setMax(this.mMax);
        progressBar.setProgress(this.mProgress);
        View progressBarLabels = view.findViewById(R.id.progress_bar_labels);
        if (TextUtils.isEmpty(this.mMinLabel) && TextUtils.isEmpty(this.mMaxLabel)) {
            progressBarLabels.setVisibility(8);
            return;
        }
        progressBarLabels.setVisibility(0);
        TextView minLabel = (TextView) view.findViewById(android.R.id.text1);
        if (minLabel != null) {
            minLabel.setText(this.mMinLabel);
        }
        TextView maxLabel = (TextView) view.findViewById(android.R.id.text2);
        if (maxLabel != null) {
            maxLabel.setText(this.mMaxLabel);
        }
    }
}
