package com.android.car.developeroptions.widget;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class SummaryUpdater {
    protected final Context mContext;
    private final OnSummaryChangeListener mListener;
    private String mSummary;

    public interface OnSummaryChangeListener {
        void onSummaryChanged(String str);
    }

    protected abstract String getSummary();

    public SummaryUpdater(Context context, OnSummaryChangeListener onSummaryChangeListener) {
        this.mContext = context;
        this.mListener = onSummaryChangeListener;
    }

    protected void notifyChangeIfNeeded() {
        String summary = getSummary();
        if (TextUtils.equals(this.mSummary, summary)) {
            return;
        }
        this.mSummary = summary;
        OnSummaryChangeListener onSummaryChangeListener = this.mListener;
        if (onSummaryChangeListener != null) {
            onSummaryChangeListener.onSummaryChanged(summary);
        }
    }
}
