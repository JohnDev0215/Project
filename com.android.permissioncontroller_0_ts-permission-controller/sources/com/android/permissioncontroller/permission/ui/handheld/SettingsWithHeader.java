package com.android.permissioncontroller.permission.ui.handheld;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SettingsWithHeader extends PermissionsFrameFragment {
    private View mHeader;
    protected Drawable mIcon;
    protected Intent mInfoIntent;
    protected CharSequence mLabel;
    protected UserHandle mUserHandle;

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        if (!DeviceUtils.isTelevision(getContext())) {
            this.mHeader = inflater.inflate(R.layout.header, root, false);
            getPreferencesContainer().addView(this.mHeader, 0);
            updateHeader();
        }
        return root;
    }

    public void setHeader(Drawable icon, CharSequence label, Intent infoIntent, UserHandle userHandle) {
        this.mIcon = icon;
        this.mLabel = label;
        this.mInfoIntent = infoIntent;
        this.mUserHandle = userHandle;
        updateHeader();
    }

    private void updateHeader() {
        View view = this.mHeader;
        if (view != null) {
            ImageView appIcon = (ImageView) view.findViewById(R.id.icon);
            appIcon.setImageDrawable(this.mIcon);
            TextView appName = (TextView) this.mHeader.findViewById(R.id.name);
            appName.setText(this.mLabel);
            View info = this.mHeader.findViewById(R.id.info);
            if (this.mInfoIntent == null) {
                info.setVisibility(8);
                return;
            }
            info.setVisibility(0);
            info.setClickable(true);
            info.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$SettingsWithHeader$vkagaqslGF5Ft3oJtefSVmUMP6Q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$updateHeader$0$SettingsWithHeader(view2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateHeader$0$SettingsWithHeader(View v) {
        getActivity().startActivityAsUser(this.mInfoIntent, this.mUserHandle);
    }
}
