package com.android.car.ui.toolbar;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUxRestrictionsUtil;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class MenuItem {
    private final Context mContext;
    private CarUxRestrictions mCurrentRestrictions;
    private DisplayBehavior mDisplayBehavior;
    private Drawable mIcon;
    private int mId;
    private final boolean mIsActivatable;
    private boolean mIsActivated;
    private final boolean mIsCheckable;
    private boolean mIsChecked;
    private boolean mIsEnabled;
    private final boolean mIsPrimary;
    private final boolean mIsSearch;
    private final boolean mIsTinted;
    private boolean mIsVisible;
    private WeakReference<Listener> mListener;
    private OnClickListener mOnClickListener;
    private final boolean mShowIconAndTitle;
    private CharSequence mTitle;
    private int mUxRestrictions;
    private final CarUxRestrictionsUtil.OnUxRestrictionsChangedListener mUxRestrictionsListener;

    public enum DisplayBehavior {
        ALWAYS,
        NEVER
    }

    interface Listener {
        void onMenuItemChanged(MenuItem menuItem);
    }

    public interface OnClickListener {
        void onClick(MenuItem menuItem);
    }

    public /* synthetic */ void lambda$new$0$MenuItem(CarUxRestrictions uxRestrictions) {
        boolean wasRestricted = isRestricted();
        this.mCurrentRestrictions = uxRestrictions;
        if (isRestricted() != wasRestricted) {
            update();
        }
    }

    private MenuItem(Builder builder) {
        this.mListener = new WeakReference<>(null);
        this.mUxRestrictionsListener = new CarUxRestrictionsUtil.OnUxRestrictionsChangedListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$MenuItem$L8uyPLsON8uHfaoUZrFMW0-X32Y
            @Override // com.android.car.ui.utils.CarUxRestrictionsUtil.OnUxRestrictionsChangedListener
            public final void onRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.lambda$new$0$MenuItem(carUxRestrictions);
            }
        };
        this.mContext = builder.mContext;
        this.mId = builder.mId;
        this.mIsCheckable = builder.mIsCheckable;
        this.mIsActivatable = builder.mIsActivatable;
        this.mTitle = builder.mTitle;
        this.mIcon = builder.mIcon;
        this.mOnClickListener = builder.mOnClickListener;
        this.mDisplayBehavior = builder.mDisplayBehavior;
        this.mIsEnabled = builder.mIsEnabled;
        this.mIsChecked = builder.mIsChecked;
        this.mIsVisible = builder.mIsVisible;
        this.mIsActivated = builder.mIsActivated;
        this.mIsSearch = builder.mIsSearch;
        this.mShowIconAndTitle = builder.mShowIconAndTitle;
        this.mIsTinted = builder.mIsTinted;
        this.mIsPrimary = builder.mIsPrimary;
        this.mUxRestrictions = builder.mUxRestrictions;
        CarUxRestrictionsUtil.getInstance(this.mContext).register(this.mUxRestrictionsListener);
    }

    private void update() {
        Listener listener = this.mListener.get();
        if (listener != null) {
            listener.onMenuItemChanged(this);
        }
    }

    public void setId(int id) {
        this.mId = id;
        update();
    }

    public int getId() {
        return this.mId;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.mIsEnabled = enabled;
        update();
    }

    public boolean isCheckable() {
        return this.mIsCheckable;
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    public void setChecked(boolean checked) {
        if (!isCheckable()) {
            throw new IllegalStateException("Cannot call setChecked() on a non-checkable MenuItem");
        }
        this.mIsChecked = checked;
        update();
    }

    public boolean isTinted() {
        return this.mIsTinted;
    }

    public boolean isVisible() {
        return this.mIsVisible;
    }

    public void setVisible(boolean visible) {
        this.mIsVisible = visible;
        update();
    }

    public boolean isActivatable() {
        return this.mIsActivatable;
    }

    public boolean isActivated() {
        return this.mIsActivated;
    }

    public void setActivated(boolean activated) {
        if (!isActivatable()) {
            throw new IllegalStateException("Cannot call setActivated() on a non-activatable MenuItem");
        }
        this.mIsActivated = activated;
        update();
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        update();
    }

    public void setTitle(int resId) {
        setTitle(this.mContext.getString(resId));
    }

    public void setUxRestrictions(int uxRestrictions) {
        if (this.mUxRestrictions != uxRestrictions) {
            this.mUxRestrictions = uxRestrictions;
            update();
        }
    }

    public int getUxRestrictions() {
        return this.mUxRestrictions;
    }

    public OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }

    public boolean isShowingIconAndTitle() {
        return this.mShowIconAndTitle;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = listener;
        update();
    }

    boolean isRestricted() {
        return CarUxRestrictionsUtil.isRestricted(this.mUxRestrictions, this.mCurrentRestrictions);
    }

    public void performClick() {
        if (!isEnabled() || !isVisible()) {
            return;
        }
        if (isRestricted()) {
            Toast.makeText(this.mContext, R.string.car_ui_restricted_while_driving, 1).show();
            return;
        }
        if (isActivatable()) {
            setActivated(!isActivated());
        }
        if (isCheckable()) {
            setChecked(!isChecked());
        }
        OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public DisplayBehavior getDisplayBehavior() {
        return this.mDisplayBehavior;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
        update();
    }

    public void setIcon(int resId) {
        Drawable drawable;
        if (resId == 0) {
            drawable = null;
        } else {
            drawable = this.mContext.getDrawable(resId);
        }
        setIcon(drawable);
    }

    public boolean isPrimary() {
        return this.mIsPrimary;
    }

    boolean isSearch() {
        return this.mIsSearch;
    }

    public static final class Builder {
        private final Context mContext;
        private Drawable mIcon;
        private OnClickListener mOnClickListener;
        private Drawable mSearchIcon;
        private String mSearchTitle;
        private Drawable mSettingsIcon;
        private String mSettingsTitle;
        private CharSequence mTitle;
        private int mId = -1;
        private DisplayBehavior mDisplayBehavior = DisplayBehavior.ALWAYS;
        private boolean mIsTinted = true;
        private boolean mShowIconAndTitle = false;
        private boolean mIsEnabled = true;
        private boolean mIsCheckable = false;
        private boolean mIsChecked = false;
        private boolean mIsVisible = true;
        private boolean mIsActivatable = false;
        private boolean mIsActivated = false;
        private boolean mIsSearch = false;
        private boolean mIsSettings = false;
        private boolean mIsPrimary = false;
        private int mUxRestrictions = 0;

        public Builder(Context c) {
            this.mContext = c.getApplicationContext();
        }

        public MenuItem build() {
            if (this.mIsActivatable && (this.mShowIconAndTitle || this.mIcon == null)) {
                throw new IllegalStateException("Only simple icons can be activatable");
            }
            if (this.mIsCheckable && (this.mShowIconAndTitle || this.mIsActivatable)) {
                throw new IllegalStateException("Unsupported options for a checkable MenuItem");
            }
            if (this.mIsSearch && this.mIsSettings) {
                throw new IllegalStateException("Can't have both a search and settings MenuItem");
            }
            if (this.mIsActivatable && this.mDisplayBehavior == DisplayBehavior.NEVER) {
                throw new IllegalStateException("Activatable MenuItems not supported as Overflow");
            }
            if (this.mIsSearch && (!this.mSearchTitle.contentEquals(this.mTitle) || !this.mSearchIcon.equals(this.mIcon) || this.mIsCheckable || this.mIsActivatable || !this.mIsTinted || this.mShowIconAndTitle || this.mDisplayBehavior != DisplayBehavior.ALWAYS)) {
                throw new IllegalStateException("Invalid search MenuItem");
            }
            if (this.mIsSettings && (!this.mSettingsTitle.contentEquals(this.mTitle) || !this.mSettingsIcon.equals(this.mIcon) || this.mIsCheckable || this.mIsActivatable || !this.mIsTinted || this.mShowIconAndTitle || this.mDisplayBehavior != DisplayBehavior.ALWAYS)) {
                throw new IllegalStateException("Invalid settings MenuItem");
            }
            return new MenuItem(this);
        }

        public Builder setId(int id) {
            this.mId = id;
            return this;
        }

        public Builder setTitle(int resId) {
            setTitle(this.mContext.getString(resId));
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setIcon(int resId) {
            Drawable drawable;
            if (resId == 0) {
                drawable = null;
            } else {
                drawable = this.mContext.getDrawable(resId);
            }
            this.mIcon = drawable;
            return this;
        }

        public Builder setIcon(Drawable icon) {
            this.mIcon = icon;
            return this;
        }

        public Builder setTinted(boolean tinted) {
            this.mIsTinted = tinted;
            return this;
        }

        public Builder setVisible(boolean visible) {
            this.mIsVisible = visible;
            return this;
        }

        public Builder setActivatable() {
            this.mIsActivatable = true;
            return this;
        }

        public Builder setActivated(boolean activated) {
            setActivatable();
            this.mIsActivated = activated;
            return this;
        }

        public Builder setOnClickListener(OnClickListener listener) {
            this.mOnClickListener = listener;
            return this;
        }

        public Builder setShowIconAndTitle(boolean showIconAndTitle) {
            this.mShowIconAndTitle = showIconAndTitle;
            return this;
        }

        public Builder setDisplayBehavior(DisplayBehavior behavior) {
            this.mDisplayBehavior = behavior;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.mIsEnabled = enabled;
            return this;
        }

        public Builder setCheckable() {
            this.mIsCheckable = true;
            return this;
        }

        public Builder setChecked(boolean checked) {
            setCheckable();
            this.mIsChecked = checked;
            return this;
        }

        public Builder setPrimary(boolean primary) {
            this.mIsPrimary = primary;
            return this;
        }

        public Builder setUxRestrictions(int restrictions) {
            this.mUxRestrictions = restrictions;
            return this;
        }

        public Builder setToSearch() {
            this.mSearchTitle = this.mContext.getString(R.string.car_ui_toolbar_menu_item_search_title);
            this.mSearchIcon = this.mContext.getDrawable(R.drawable.car_ui_icon_search);
            this.mIsSearch = true;
            setTitle(this.mSearchTitle);
            setIcon(this.mSearchIcon);
            return this;
        }

        public Builder setToSettings() {
            this.mSettingsTitle = this.mContext.getString(R.string.car_ui_toolbar_menu_item_settings_title);
            this.mSettingsIcon = this.mContext.getDrawable(R.drawable.car_ui_icon_settings);
            this.mIsSettings = true;
            setTitle(this.mSettingsTitle);
            setIcon(this.mSettingsIcon);
            setUxRestrictions(64);
            return this;
        }

        @Deprecated
        public static MenuItem createSearch(Context c, OnClickListener listener) {
            return MenuItem.builder(c).setToSearch().setOnClickListener(listener).build();
        }

        @Deprecated
        public static MenuItem createSettings(Context c, OnClickListener listener) {
            return MenuItem.builder(c).setToSettings().setOnClickListener(listener).build();
        }
    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    void setListener(Listener listener) {
        this.mListener = new WeakReference<>(listener);
    }
}
