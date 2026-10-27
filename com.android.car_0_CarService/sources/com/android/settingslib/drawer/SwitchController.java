package com.android.settingslib.drawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SwitchController {
    private String mAuthority;

    protected abstract String getErrorMessage(boolean z);

    protected abstract MetaData getMetaData();

    public abstract String getSwitchKey();

    protected abstract boolean isChecked();

    protected abstract boolean onCheckedChanged(boolean z);

    public void notifyTitleChanged(Context context) {
        if (this instanceof DynamicTitle) {
            notifyChanged(context, SwitchesProvider.METHOD_GET_DYNAMIC_TITLE);
        }
    }

    public void notifySummaryChanged(Context context) {
        if (this instanceof DynamicSummary) {
            notifyChanged(context, SwitchesProvider.METHOD_GET_DYNAMIC_SUMMARY);
        }
    }

    public void notifyCheckedChanged(Context context) {
        notifyChanged(context, SwitchesProvider.METHOD_IS_CHECKED);
    }

    void setAuthority(String authority) {
        this.mAuthority = authority;
    }

    Bundle getBundle() {
        MetaData metaData = getMetaData();
        if (metaData == null) {
            throw new NullPointerException("Should not return null in getMetaData()");
        }
        Bundle bundle = metaData.build();
        String uriString = new Uri.Builder().scheme("content").authority(this.mAuthority).build().toString();
        bundle.putString(TileUtils.META_DATA_PREFERENCE_KEYHINT, getSwitchKey());
        bundle.putString(TileUtils.META_DATA_PREFERENCE_SWITCH_URI, uriString);
        if (this instanceof ProviderIcon) {
            bundle.putString(TileUtils.META_DATA_PREFERENCE_ICON_URI, uriString);
        }
        if (this instanceof DynamicTitle) {
            bundle.putString(TileUtils.META_DATA_PREFERENCE_TITLE_URI, uriString);
        }
        if (this instanceof DynamicSummary) {
            bundle.putString(TileUtils.META_DATA_PREFERENCE_SUMMARY_URI, uriString);
        }
        return bundle;
    }

    private void notifyChanged(Context context, String method) {
        Uri uri = TileUtils.buildUri(this.mAuthority, method, getSwitchKey());
        context.getContentResolver().notifyChange(uri, null);
    }

    protected static class MetaData {
        private String mCategory;
        private int mIcon;
        private int mIconBackgroundArgb;
        private int mIconBackgroundHint;
        private Boolean mIconTintable;
        private int mOrder;
        private String mSummary;
        private int mSummaryId;
        private String mTitle;
        private int mTitleId;

        public MetaData(String category) {
            this.mCategory = category;
        }

        public MetaData setOrder(int order) {
            this.mOrder = order;
            return this;
        }

        public MetaData setIcon(int icon) {
            this.mIcon = icon;
            return this;
        }

        public MetaData setIconBackgoundHint(int hint) {
            this.mIconBackgroundHint = hint;
            return this;
        }

        public MetaData setIconBackgoundArgb(int argb) {
            this.mIconBackgroundArgb = argb;
            return this;
        }

        public MetaData setIconTintable(boolean tintable) {
            this.mIconTintable = Boolean.valueOf(tintable);
            return this;
        }

        public MetaData setTitle(int id) {
            this.mTitleId = id;
            return this;
        }

        public MetaData setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public MetaData setSummary(int id) {
            this.mSummaryId = id;
            return this;
        }

        public MetaData setSummary(String summary) {
            this.mSummary = summary;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putString("com.android.settings.category", this.mCategory);
            int i = this.mOrder;
            if (i != 0) {
                bundle.putInt(TileUtils.META_DATA_KEY_ORDER, i);
            }
            int i2 = this.mIcon;
            if (i2 != 0) {
                bundle.putInt(TileUtils.META_DATA_PREFERENCE_ICON, i2);
            }
            int i3 = this.mIconBackgroundHint;
            if (i3 != 0) {
                bundle.putInt(TileUtils.META_DATA_PREFERENCE_ICON_BACKGROUND_HINT, i3);
            }
            int i4 = this.mIconBackgroundArgb;
            if (i4 != 0) {
                bundle.putInt(TileUtils.META_DATA_PREFERENCE_ICON_BACKGROUND_ARGB, i4);
            }
            Boolean bool = this.mIconTintable;
            if (bool != null) {
                bundle.putBoolean(TileUtils.META_DATA_PREFERENCE_ICON_TINTABLE, bool.booleanValue());
            }
            int i5 = this.mTitleId;
            if (i5 != 0) {
                bundle.putInt(TileUtils.META_DATA_PREFERENCE_TITLE, i5);
            } else {
                String str = this.mTitle;
                if (str != null) {
                    bundle.putString(TileUtils.META_DATA_PREFERENCE_TITLE, str);
                }
            }
            int i6 = this.mSummaryId;
            if (i6 != 0) {
                bundle.putInt(TileUtils.META_DATA_PREFERENCE_SUMMARY, i6);
            } else {
                String str2 = this.mSummary;
                if (str2 != null) {
                    bundle.putString(TileUtils.META_DATA_PREFERENCE_SUMMARY, str2);
                }
            }
            return bundle;
        }
    }
}
