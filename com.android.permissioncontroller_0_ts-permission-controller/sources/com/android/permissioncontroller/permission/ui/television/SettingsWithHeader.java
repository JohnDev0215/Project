package com.android.permissioncontroller.permission.ui.television;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SettingsWithHeader extends PermissionsFrameFragment implements View.OnClickListener {
    protected CharSequence mDecorTitle;
    private View mHeader;
    protected Drawable mIcon;
    protected Intent mInfoIntent;
    protected CharSequence mLabel;

    @Override // com.android.permissioncontroller.permission.ui.television.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        this.mHeader = inflater.inflate(R.layout.header, root, false);
        getPreferencesContainer().addView(this.mHeader, 0);
        updateHeader();
        return root;
    }

    public void setHeader(Drawable icon, CharSequence label, Intent infoIntent, CharSequence decorTitle) {
        this.mIcon = icon;
        this.mLabel = label;
        this.mInfoIntent = infoIntent;
        this.mDecorTitle = decorTitle;
        updateHeader();
    }

    public View getHeader() {
        return this.mHeader;
    }

    protected void updateHeader() {
        TextView decorTitle = (TextView) this.mHeader.findViewById(R.id.decor_title);
        decorTitle.setText(this.mDecorTitle);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        getActivity().startActivity(this.mInfoIntent);
    }
}
