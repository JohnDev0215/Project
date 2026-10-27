package com.android.settingslib.widget;

import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.Comparator;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class BarViewInfo implements Comparable<BarViewInfo> {
    private View.OnClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mHeight;
    private final Drawable mIcon;
    private int mNormalizedHeight;
    private CharSequence mSummary;
    private CharSequence mTitle;

    public BarViewInfo(Drawable drawable, int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        this.mIcon = drawable;
        this.mHeight = i;
        this.mTitle = charSequence;
        this.mSummary = charSequence2;
        this.mContentDescription = charSequence3;
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    @Override // java.lang.Comparable
    public int compareTo(BarViewInfo barViewInfo) {
        return Comparator.comparingInt(new ToIntFunction() { // from class: com.android.settingslib.widget.-$$Lambda$BarViewInfo$0E64JyWB2WmVqNcEtw_jyuLCMME
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((BarViewInfo) obj).mHeight;
            }
        }).compare(barViewInfo, this);
    }

    Drawable getIcon() {
        return this.mIcon;
    }

    int getHeight() {
        return this.mHeight;
    }

    View.OnClickListener getClickListener() {
        return this.mClickListener;
    }

    CharSequence getTitle() {
        return this.mTitle;
    }

    CharSequence getSummary() {
        return this.mSummary;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    void setNormalizedHeight(int i) {
        this.mNormalizedHeight = i;
    }

    int getNormalizedHeight() {
        return this.mNormalizedHeight;
    }
}
