package com.android.settingslib.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
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

    void setBarViewInfos(BarViewInfo[] barViewInfos) {
        this.mBarViewInfos = barViewInfos;
    }

    private BarChartInfo(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mDetails = builder.mDetails;
        this.mEmptyText = builder.mEmptyText;
        this.mDetailsOnClickListener = builder.mDetailsOnClickListener;
        if (builder.mBarViewInfos == null) {
            return;
        }
        this.mBarViewInfos = (BarViewInfo[]) builder.mBarViewInfos.stream().toArray(new IntFunction() { // from class: com.android.settingslib.widget.-$$Lambda$BarChartInfo$2CrHVNAna8TvSeyBIL19oCkthVU
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return BarChartInfo.lambda$new$0(i);
            }
        });
    }

    static /* synthetic */ BarViewInfo[] lambda$new$0(int x$0) {
        return new BarViewInfo[x$0];
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

        public Builder setTitle(int title) {
            this.mTitle = title;
            return this;
        }

        public Builder setDetails(int details) {
            this.mDetails = details;
            return this;
        }

        public Builder setEmptyText(int emptyText) {
            this.mEmptyText = emptyText;
            return this;
        }

        public Builder setDetailsOnClickListener(View.OnClickListener clickListener) {
            this.mDetailsOnClickListener = clickListener;
            return this;
        }

        public Builder addBarViewInfo(BarViewInfo barViewInfo) {
            if (this.mBarViewInfos == null) {
                this.mBarViewInfos = new ArrayList();
            }
            if (this.mBarViewInfos.size() >= 4) {
                throw new IllegalStateException("We only support up to four bar views");
            }
            this.mBarViewInfos.add(barViewInfo);
            return this;
        }
    }
}
