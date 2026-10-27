package com.android.car.ui.recyclerview;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiContentListItem extends CarUiListItem {
    private final Action mAction;
    private CharSequence mBody;
    private Drawable mIcon;
    private boolean mIsActionDividerVisible;
    private boolean mIsActivated;
    private boolean mIsChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private OnClickListener mOnClickListener;
    private Drawable mSupplementalIcon;
    private OnClickListener mSupplementalIconOnClickListener;
    private CharSequence mTitle;
    private boolean mIsEnabled = true;
    private IconType mPrimaryIconType = IconType.STANDARD;

    public enum Action {
        NONE,
        SWITCH,
        CHECK_BOX,
        RADIO_BUTTON,
        ICON,
        CHEVRON
    }

    public enum IconType {
        CONTENT,
        STANDARD,
        AVATAR
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CarUiContentListItem carUiContentListItem, boolean z);
    }

    public interface OnClickListener {
        void onClick(CarUiContentListItem carUiContentListItem);
    }

    public CarUiContentListItem(Action action) {
        this.mAction = action;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
    }

    public CharSequence getBody() {
        return this.mBody;
    }

    public void setBody(CharSequence body) {
        this.mBody = body;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
    }

    public IconType getPrimaryIconType() {
        return this.mPrimaryIconType;
    }

    public void setPrimaryIconType(IconType icon) {
        this.mPrimaryIconType = icon;
    }

    public boolean isActivated() {
        return this.mIsActivated;
    }

    public void setActivated(boolean activated) {
        this.mIsActivated = activated;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.mIsEnabled = enabled;
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    public void setChecked(boolean checked) {
        if (checked == this.mIsChecked) {
            return;
        }
        if (this.mAction == Action.CHECK_BOX || this.mAction == Action.SWITCH || this.mAction == Action.RADIO_BUTTON) {
            this.mIsChecked = checked;
            OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, checked);
            }
        }
    }

    public void setActionDividerVisible(boolean visible) {
        this.mIsActionDividerVisible = visible;
    }

    public boolean isActionDividerVisible() {
        return this.mIsActionDividerVisible;
    }

    public Action getAction() {
        return this.mAction;
    }

    public Drawable getSupplementalIcon() {
        if (this.mAction != Action.ICON) {
            return null;
        }
        return this.mSupplementalIcon;
    }

    public void setSupplementalIcon(Drawable icon) {
        setSupplementalIcon(icon, null);
    }

    public void setSupplementalIcon(Drawable icon, OnClickListener listener) {
        if (this.mAction != Action.ICON) {
            throw new IllegalStateException("Cannot set supplemental icon on list item that does not have an action of type ICON");
        }
        this.mSupplementalIcon = icon;
        this.mSupplementalIconOnClickListener = listener;
    }

    public OnClickListener getSupplementalIconOnClickListener() {
        return this.mSupplementalIconOnClickListener;
    }

    public void setOnItemClickedListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.mOnCheckedChangeListener = listener;
    }

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return this.mOnCheckedChangeListener;
    }
}
