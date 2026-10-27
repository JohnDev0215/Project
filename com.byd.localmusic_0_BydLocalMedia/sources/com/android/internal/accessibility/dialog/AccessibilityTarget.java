package com.android.internal.accessibility.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.accessibility.dialog.TargetAdapter;
import com.android.internal.accessibility.util.ShortcutUtils;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AccessibilityTarget implements TargetOperations, OnTargetSelectedListener, OnTargetCheckedChangeListener {
    private Context mContext;
    private int mFragmentType;
    private Drawable mIcon;
    private String mId;
    private String mKey;
    private CharSequence mLabel;
    private boolean mShortcutEnabled;
    private int mShortcutType;

    AccessibilityTarget(Context context, int i, int i2, boolean z, String str, CharSequence charSequence, Drawable drawable, String str2) {
        this.mContext = context;
        this.mShortcutType = i;
        this.mFragmentType = i2;
        this.mShortcutEnabled = z;
        this.mId = str;
        this.mLabel = charSequence;
        this.mIcon = drawable;
        this.mKey = str2;
    }

    @Override // com.android.internal.accessibility.dialog.TargetOperations
    public void updateActionItem(TargetAdapter.ViewHolder viewHolder, int i) {
        boolean z = i == 1;
        viewHolder.mCheckBoxView.setChecked(z && isShortcutEnabled());
        viewHolder.mCheckBoxView.setVisibility(z ? 0 : 8);
        viewHolder.mIconView.setImageDrawable(getIcon());
        viewHolder.mLabelView.setText(getLabel());
        viewHolder.mStatusView.setVisibility(8);
    }

    @Override // com.android.internal.accessibility.dialog.OnTargetSelectedListener
    public void onSelected() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService(AccessibilityManager.class);
        int shortcutType = getShortcutType();
        if (shortcutType == 0) {
            accessibilityManager.notifyAccessibilityButtonClicked(getContext().getDisplayId(), getId());
        } else {
            if (shortcutType == 1) {
                accessibilityManager.performAccessibilityShortcut(getId());
                return;
            }
            throw new IllegalStateException("Unexpected shortcut type");
        }
    }

    @Override // com.android.internal.accessibility.dialog.OnTargetCheckedChangeListener
    public void onCheckedChanged(boolean z) {
        setShortcutEnabled(z);
        if (z) {
            ShortcutUtils.optInValueToSettings(getContext(), ShortcutUtils.convertToUserType(getShortcutType()), getId());
        } else {
            ShortcutUtils.optOutValueFromSettings(getContext(), ShortcutUtils.convertToUserType(getShortcutType()), getId());
        }
    }

    public void setShortcutEnabled(boolean z) {
        this.mShortcutEnabled = z;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getShortcutType() {
        return this.mShortcutType;
    }

    public int getFragmentType() {
        return this.mFragmentType;
    }

    public boolean isShortcutEnabled() {
        return this.mShortcutEnabled;
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public String getKey() {
        return this.mKey;
    }
}
