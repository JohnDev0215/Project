package com.android.settingslib.widget;

import android.view.View;
import java.util.List;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes.dex */
public class BarChartInfo {
    private BarViewInfo[] mBarViewInfos;
    private final int mDetails;
    private final View.OnClickListener mDetailsOnClickListener;
    private final int mEmptyText;
    private final int mTitle;

    public int getTitle() {
        return this.mTitle;
    }

    public int getDetails() {
        return this.mDetails;
    }

    public int getEmptyText() {
        return this.mEmptyText;
    }

    public View.OnClickListener getDetailsOnClickListener() {
        return this.mDetailsOnClickListener;
    }

    public BarViewInfo[] getBarViewInfos() {
        return this.mBarViewInfos;
    }

    void setBarViewInfos(BarViewInfo[] barViewInfoArr) {
        this.mBarViewInfos = barViewInfoArr;
    }

    private BarChartInfo(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mDetails = builder.mDetails;
        this.mEmptyText = builder.mEmptyText;
        this.mDetailsOnClickListener = builder.mDetailsOnClickListener;
        if (builder.mBarViewInfos != null) {
            this.mBarViewInfos = (BarViewInfo[]) builder.mBarViewInfos.stream().toArray(new IntFunction() { // from class: com.android.settingslib.widget.-$$Lambda$BarChartInfo$2CrHVNAna8TvSeyBIL19oCkthVU
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return BarChartInfo.lambda$new$0(i);
                }
            });
        }
    }

    static /* synthetic */ BarViewInfo[] lambda$new$0(int i) {
        return new BarViewInfo[i];
    }

    public static class Builder {
        private List<BarViewInfo> mBarViewInfos;
        private int mDetails;
        private View.OnClickListener mDetailsOnClickListener;
        private int mEmptyText;
        private int mTitle;

        public BarChartInfo build() {
            if (this.mTitle == 0) {
                throw new IllegalStateException("You must call Builder#setTitle() once.");
            }
            return new BarChartInfo(this);
        }

        public Builder setTitle(int i) {
            this.mTitle = i;
            return this;
        }

        public Builder setDetails(int i) {
            this.mDetails = i;
            return this;
        }

        public Builder setEmptyText(int i) {
            this.mEmptyText = i;
            return this;
        }

        public Builder setDetailsOnClickListener(View.OnClickListener onClickListener) {
            this.mDetailsOnClickListener = onClickListener;
            return this;
        }
    }
}
