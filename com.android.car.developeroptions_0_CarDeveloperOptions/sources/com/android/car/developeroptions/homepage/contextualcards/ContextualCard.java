package com.android.car.developeroptions.homepage.contextualcards;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class ContextualCard {
    private final long mAppVersion;
    private final Builder mBuilder;
    private final int mCardAction;
    private final int mCardType;
    private final int mCategory;
    private final long mExpireTimeMS;
    private final Drawable mIconDrawable;
    private final int mIconResId;
    private final String mIconResName;
    private final boolean mIsLargeCard;
    private final boolean mIsPendingDismiss;
    private final String mLocalizedToLocale;
    private final String mName;
    private final String mPackageName;
    private final double mRankingScore;
    private final String mSliceUri;
    private final String mSummaryResName;
    private final String mSummaryText;
    private final String mTitleResName;
    private final String mTitleText;
    private final int mViewType;

    private int getViewTypeByCardType(int i) {
        if (i == 1) {
            return R.layout.contextual_slice_full_tile;
        }
        return 0;
    }

    public String getName() {
        return this.mName;
    }

    public int getCardType() {
        return this.mCardType;
    }

    public double getRankingScore() {
        return this.mRankingScore;
    }

    public String getTextSliceUri() {
        return this.mSliceUri;
    }

    public Uri getSliceUri() {
        return Uri.parse(this.mSliceUri);
    }

    public int getCategory() {
        return this.mCategory;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public String getSummaryText() {
        return this.mSummaryText;
    }

    public Drawable getIconDrawable() {
        return this.mIconDrawable;
    }

    public boolean isLargeCard() {
        return this.mIsLargeCard;
    }

    boolean isCustomCard() {
        return TextUtils.isEmpty(this.mSliceUri);
    }

    public int getViewType() {
        return this.mViewType;
    }

    public boolean isPendingDismiss() {
        return this.mIsPendingDismiss;
    }

    public Builder mutate() {
        return this.mBuilder;
    }

    public ContextualCard(Builder builder) {
        this.mBuilder = builder;
        this.mName = builder.mName;
        this.mCardType = builder.mCardType;
        this.mRankingScore = builder.mRankingScore;
        this.mSliceUri = builder.mSliceUri;
        this.mCategory = builder.mCategory;
        this.mLocalizedToLocale = builder.mLocalizedToLocale;
        this.mPackageName = builder.mPackageName;
        this.mAppVersion = builder.mAppVersion;
        this.mTitleResName = builder.mTitleResName;
        this.mTitleText = builder.mTitleText;
        this.mSummaryResName = builder.mSummaryResName;
        this.mSummaryText = builder.mSummaryText;
        this.mIconResName = builder.mIconResName;
        this.mIconResId = builder.mIconResId;
        this.mCardAction = builder.mCardAction;
        this.mExpireTimeMS = builder.mExpireTimeMS;
        this.mIconDrawable = builder.mIconDrawable;
        this.mIsLargeCard = builder.mIsLargeCard;
        this.mViewType = builder.mViewType;
        this.mIsPendingDismiss = builder.mIsPendingDismiss;
    }

    ContextualCard(Cursor cursor) {
        this.mBuilder = new Builder();
        String string = cursor.getString(cursor.getColumnIndex("name"));
        this.mName = string;
        this.mBuilder.setName(string);
        int i = cursor.getInt(cursor.getColumnIndex("type"));
        this.mCardType = i;
        this.mBuilder.setCardType(i);
        double d = cursor.getDouble(cursor.getColumnIndex("score"));
        this.mRankingScore = d;
        this.mBuilder.setRankingScore(d);
        String string2 = cursor.getString(cursor.getColumnIndex("slice_uri"));
        this.mSliceUri = string2;
        this.mBuilder.setSliceUri(Uri.parse(string2));
        int i2 = cursor.getInt(cursor.getColumnIndex("category"));
        this.mCategory = i2;
        this.mBuilder.setCategory(i2);
        String string3 = cursor.getString(cursor.getColumnIndex("localized_to_locale"));
        this.mLocalizedToLocale = string3;
        this.mBuilder.setLocalizedToLocale(string3);
        String string4 = cursor.getString(cursor.getColumnIndex("package_name"));
        this.mPackageName = string4;
        this.mBuilder.setPackageName(string4);
        long j = cursor.getLong(cursor.getColumnIndex("app_version"));
        this.mAppVersion = j;
        this.mBuilder.setAppVersion(j);
        String string5 = cursor.getString(cursor.getColumnIndex("title_res_name"));
        this.mTitleResName = string5;
        this.mBuilder.setTitleResName(string5);
        String string6 = cursor.getString(cursor.getColumnIndex("title_text"));
        this.mTitleText = string6;
        this.mBuilder.setTitleText(string6);
        String string7 = cursor.getString(cursor.getColumnIndex("summary_res_name"));
        this.mSummaryResName = string7;
        this.mBuilder.setSummaryResName(string7);
        String string8 = cursor.getString(cursor.getColumnIndex("summary_text"));
        this.mSummaryText = string8;
        this.mBuilder.setSummaryText(string8);
        String string9 = cursor.getString(cursor.getColumnIndex("icon_res_name"));
        this.mIconResName = string9;
        this.mBuilder.setIconResName(string9);
        int i3 = cursor.getInt(cursor.getColumnIndex("icon_res_id"));
        this.mIconResId = i3;
        this.mBuilder.setIconResId(i3);
        int i4 = cursor.getInt(cursor.getColumnIndex("card_action"));
        this.mCardAction = i4;
        this.mBuilder.setCardAction(i4);
        long j2 = cursor.getLong(cursor.getColumnIndex("expire_time_ms"));
        this.mExpireTimeMS = j2;
        this.mBuilder.setExpireTimeMS(j2);
        this.mIsLargeCard = false;
        this.mBuilder.setIsLargeCard(false);
        this.mIconDrawable = null;
        this.mBuilder.setIconDrawable(null);
        int viewTypeByCardType = getViewTypeByCardType(this.mCardType);
        this.mViewType = viewTypeByCardType;
        this.mBuilder.setViewType(viewTypeByCardType);
        this.mIsPendingDismiss = false;
        this.mBuilder.setIsPendingDismiss(false);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ContextualCard) {
            return TextUtils.equals(this.mName, ((ContextualCard) obj).mName);
        }
        return false;
    }

    public static class Builder {
        private long mAppVersion;
        private int mCardAction;
        private int mCardType;
        private int mCategory;
        private long mExpireTimeMS;
        private Drawable mIconDrawable;
        private int mIconResId;
        private String mIconResName;
        private boolean mIsLargeCard;
        private boolean mIsPendingDismiss;
        private String mLocalizedToLocale;
        private String mName;
        private String mPackageName;
        private double mRankingScore;
        private String mSliceUri;
        private String mSummaryResName;
        private String mSummaryText;
        private String mTitleResName;
        private String mTitleText;
        private int mViewType;

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setCardType(int i) {
            this.mCardType = i;
            return this;
        }

        public Builder setRankingScore(double d) {
            this.mRankingScore = d;
            return this;
        }

        public Builder setSliceUri(Uri uri) {
            this.mSliceUri = uri.toString();
            return this;
        }

        public Builder setCategory(int i) {
            this.mCategory = i;
            return this;
        }

        public Builder setLocalizedToLocale(String str) {
            this.mLocalizedToLocale = str;
            return this;
        }

        public Builder setPackageName(String str) {
            this.mPackageName = str;
            return this;
        }

        public Builder setAppVersion(long j) {
            this.mAppVersion = j;
            return this;
        }

        public Builder setTitleResName(String str) {
            this.mTitleResName = str;
            return this;
        }

        public Builder setTitleText(String str) {
            this.mTitleText = str;
            return this;
        }

        public Builder setSummaryResName(String str) {
            this.mSummaryResName = str;
            return this;
        }

        public Builder setSummaryText(String str) {
            this.mSummaryText = str;
            return this;
        }

        public Builder setIconResName(String str) {
            this.mIconResName = str;
            return this;
        }

        public Builder setIconResId(int i) {
            this.mIconResId = i;
            return this;
        }

        public Builder setCardAction(int i) {
            this.mCardAction = i;
            return this;
        }

        public Builder setExpireTimeMS(long j) {
            this.mExpireTimeMS = j;
            return this;
        }

        public Builder setIconDrawable(Drawable drawable) {
            this.mIconDrawable = drawable;
            return this;
        }

        public Builder setIsLargeCard(boolean z) {
            this.mIsLargeCard = z;
            return this;
        }

        public Builder setViewType(int i) {
            this.mViewType = i;
            return this;
        }

        public Builder setIsPendingDismiss(boolean z) {
            this.mIsPendingDismiss = z;
            return this;
        }

        public ContextualCard build() {
            return new ContextualCard(this);
        }
    }
}
