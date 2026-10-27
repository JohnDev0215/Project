package com.android.settingslib.widget;

import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.Comparator;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
public class BarViewInfo implements Comparable<BarViewInfo> {
    private View.OnClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mHeight;
    private final Drawable mIcon;
    private int mNormalizedHeight;
    private CharSequence mSummary;
    private CharSequence mTitle;

    public BarViewInfo(Drawable icon, int barHeight, CharSequence title, CharSequence summary, CharSequence contentDescription) {
        this.mIcon = icon;
        this.mHeight = barHeight;
        this.mTitle = title;
        this.mSummary = summary;
        this.mContentDescription = contentDescription;
    }

    public void setClickListener(View.OnClickListener listener) {
        this.mClickListener = listener;
    }

    @Override // java.lang.Comparable
    public int compareTo(BarViewInfo other) {
        return Comparator.comparingInt(new ToIntFunction() { // from class: com.android.settingslib.widget.-$$Lambda$BarViewInfo$0E64JyWB2WmVqNcEtw_jyuLCMME
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((BarViewInfo) obj).mHeight;
            }
        }).compare(other, this);
    }

    void setHeight(int height) {
        this.mHeight = height;
    }

    void setTitle(CharSequence title) {
        this.mTitle = title;
    }

    void setSummary(CharSequence summary) {
        this.mSummary = summary;
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

    void setNormalizedHeight(int barHeight) {
        this.mNormalizedHeight = barHeight;
    }

    int getNormalizedHeight() {
        return this.mNormalizedHeight;
    }
}
