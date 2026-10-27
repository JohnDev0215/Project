package com.android.settingslib.widget;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public class FooterPreference extends Preference {
    public static final String KEY_FOOTER = "footer_preference";
    static final int ORDER_FOOTER = 2147483646;

    public FooterPreference(Context context, AttributeSet attrs) {
        super(context, attrs, TypedArrayUtils.getAttr(context, com.android.settingslib.R.attr.footerPreferenceStyle, android.R.attr.preferenceStyle));
        init();
    }

    public FooterPreference(Context context) {
        this(context, null);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        TextView title = (TextView) holder.itemView.findViewById(android.R.id.title);
        title.setMovementMethod(new LinkMovementMethod());
        title.setClickable(false);
        title.setLongClickable(false);
    }

    @Override // androidx.preference.Preference
    public void setSummary(CharSequence summary) {
        setTitle(summary);
    }

    @Override // androidx.preference.Preference
    public void setSummary(int summaryResId) {
        setTitle(summaryResId);
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        return getTitle();
    }

    private void init() {
        if (getIcon() == null) {
            setIcon(com.android.settingslib.R.drawable.ic_info_outline_24);
        }
        setOrder(ORDER_FOOTER);
        if (TextUtils.isEmpty(getKey())) {
            setKey(KEY_FOOTER);
        }
    }

    public static class Builder {
        private Context mContext;
        private String mKey;
        private CharSequence mTitle;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setKey(String key) {
            this.mKey = key;
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setTitle(int titleResId) {
            this.mTitle = this.mContext.getText(titleResId);
            return this;
        }

        public FooterPreference build() {
            FooterPreference footerPreference = new FooterPreference(this.mContext);
            footerPreference.setSelectable(false);
            if (TextUtils.isEmpty(this.mTitle)) {
                throw new IllegalArgumentException("Footer title cannot be empty!");
            }
            footerPreference.setTitle(this.mTitle);
            if (!TextUtils.isEmpty(this.mKey)) {
                footerPreference.setKey(this.mKey);
            }
            return footerPreference;
        }
    }
}
