package com.android.settingslib.widget;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes6.dex */
public class AppEntityInfo {
    private final View.OnClickListener mClickListener;
    private final Drawable mIcon;
    private final CharSequence mSummary;
    private final CharSequence mTitle;

    public Drawable getIcon() {
        return this.mIcon;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getSummary() {
        return this.mSummary;
    }

    public View.OnClickListener getClickListener() {
        return this.mClickListener;
    }

    private AppEntityInfo(Builder builder) {
        this.mIcon = builder.mIcon;
        this.mTitle = builder.mTitle;
        this.mSummary = builder.mSummary;
        this.mClickListener = builder.mClickListener;
    }

    public static class Builder {
        private View.OnClickListener mClickListener;
        private Drawable mIcon;
        private CharSequence mSummary;
        private CharSequence mTitle;

        public AppEntityInfo build() {
            return new AppEntityInfo(this);
        }

        public Builder setIcon(Drawable icon) {
            this.mIcon = icon;
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setSummary(CharSequence summary) {
            this.mSummary = summary;
            return this;
        }

        public Builder setOnClickListener(View.OnClickListener clickListener) {
            this.mClickListener = clickListener;
            return this;
        }
    }
}
