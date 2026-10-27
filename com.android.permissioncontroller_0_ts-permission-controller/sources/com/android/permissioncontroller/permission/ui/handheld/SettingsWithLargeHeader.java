package com.android.permissioncontroller.permission.ui.handheld;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SettingsWithLargeHeader extends PermissionsFrameFragment {
    static final String HEADER_KEY = " HEADER_PREFERENCE";
    private static final int HEADER_SORT_FIRST = -2;
    private View mHeader;
    private LargeHeaderPreference mHeaderPreference;
    protected Drawable mIcon;
    protected Intent mInfoIntent;
    protected CharSequence mLabel;
    private View.OnClickListener mListener;
    private boolean mShouldShowHeader = true;
    protected boolean mSmallIcon;
    private CharSequence mSummary;
    protected UserHandle mUserHandle;

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        ViewGroup root = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        if (!DeviceUtils.isTelevision(getContext()) && (view = this.mHeader) != null && view.getVisibility() == 0) {
            updateHeader(this.mHeader);
            this.mHeader.requireViewById(R.id.header_link).setVisibility(0);
        }
        return root;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void setPreferenceScreen(PreferenceScreen screen) {
        this.mHeaderPreference = new LargeHeaderPreference(getContext(), this);
        if (screen.findPreference(HEADER_KEY) == null) {
            screen.addPreference(this.mHeaderPreference);
        }
        super.setPreferenceScreen(screen);
    }

    public void setHeader(Drawable icon, CharSequence label, Intent infoIntent, UserHandle userHandle, boolean smallIcon) {
        this.mIcon = icon;
        this.mLabel = label;
        this.mInfoIntent = infoIntent;
        this.mUserHandle = userHandle;
        this.mSmallIcon = smallIcon;
        View view = this.mHeader;
        if (view != null) {
            updateHeader(view);
        }
    }

    protected void updateHeader(View header) {
        if (header != null) {
            header.setVisibility(0);
            ImageView appIcon = (ImageView) header.requireViewById(R.id.entity_header_icon);
            appIcon.setImageDrawable(this.mIcon);
            if (this.mSmallIcon) {
                int size = getContext().getResources().getDimensionPixelSize(R.dimen.permission_icon_header_size);
                appIcon.getLayoutParams().width = size;
                appIcon.getLayoutParams().height = size;
            }
            if (this.mInfoIntent != null) {
                appIcon.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$SettingsWithLargeHeader$YIl-_Nq3CN86mQs38-rc9dfWtnI
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$updateHeader$0$SettingsWithLargeHeader(view);
                    }
                });
                appIcon.setContentDescription(this.mLabel);
            }
            TextView appName = (TextView) header.requireViewById(R.id.entity_header_title);
            appName.setText(this.mLabel);
            header.requireViewById(R.id.entity_header_summary).setVisibility(8);
            header.requireViewById(R.id.entity_header_second_summary).setVisibility(8);
            header.requireViewById(R.id.header_link).setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$updateHeader$0$SettingsWithLargeHeader(View v) {
        getActivity().startActivityAsUser(this.mInfoIntent, this.mUserHandle);
    }

    public void hideHeader() {
        LargeHeaderPreference largeHeaderPreference = this.mHeaderPreference;
        if (largeHeaderPreference == null) {
            this.mShouldShowHeader = false;
        } else {
            largeHeaderPreference.setVisible(false);
            this.mHeader = null;
        }
    }

    public void setSummary(CharSequence summary, View.OnClickListener listener) {
        View view = this.mHeader;
        if (view == null) {
            this.mSummary = summary;
            this.mListener = listener;
            return;
        }
        TextView textView = (TextView) view.requireViewById(R.id.header_text);
        TextView linkView = (TextView) this.mHeader.requireViewById(R.id.header_link);
        if (listener != null) {
            linkView.setOnClickListener(listener);
            linkView.setVisibility(0);
            linkView.setText(summary);
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(summary);
        linkView.setVisibility(8);
    }

    public static class LargeHeaderPreference extends PreferenceCategory {
        private SettingsWithLargeHeader mFragment;

        private LargeHeaderPreference(Context context, SettingsWithLargeHeader fragment) {
            super(context);
            this.mFragment = fragment;
            setVisible(fragment.mShouldShowHeader);
            setSelectable(false);
            setLayoutResource(R.layout.header_large);
            setKey(SettingsWithLargeHeader.HEADER_KEY);
            setOrder(-2);
        }

        @Override // androidx.preference.PreferenceCategory, androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder holder) {
            if (!isVisible()) {
                return;
            }
            super.onBindViewHolder(holder);
            View view = holder.itemView;
            if (view != this.mFragment.mHeader) {
                this.mFragment.mHeader = view;
                if (this.mFragment.mIcon != null) {
                    this.mFragment.updateHeader(view);
                }
                if (this.mFragment.mSummary != null) {
                    SettingsWithLargeHeader settingsWithLargeHeader = this.mFragment;
                    settingsWithLargeHeader.setSummary(settingsWithLargeHeader.mSummary, this.mFragment.mListener);
                }
            }
        }
    }
}
