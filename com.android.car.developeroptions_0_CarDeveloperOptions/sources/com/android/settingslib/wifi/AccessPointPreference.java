package com.android.settingslib.wifi;

import android.R;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.wifi.WifiConfiguration;
import android.os.Looper;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.R$attr;
import com.android.settingslib.R$dimen;
import com.android.settingslib.R$id;
import com.android.settingslib.R$layout;
import com.android.settingslib.R$string;
import com.android.settingslib.TronUtils;
import com.android.settingslib.Utils;

/* JADX INFO: loaded from: classes.dex */
public class AccessPointPreference extends Preference {
    private AccessPoint mAccessPoint;
    private Drawable mBadge;
    private final UserBadgeCache mBadgeCache;
    private final int mBadgePadding;
    private CharSequence mContentDescription;
    private int mDefaultIconResId;
    private boolean mForSavedNetworks;
    private final StateListDrawable mFrictionSld;
    private final IconInjector mIconInjector;
    private int mLevel;
    private final Runnable mNotifyChanged;
    private boolean mShowDivider;
    private TextView mTitleView;
    private int mWifiSpeed;
    private static final int[] STATE_SECURED = {R$attr.state_encrypted};
    private static final int[] STATE_METERED = {R$attr.state_metered};
    private static final int[] FRICTION_ATTRS = {R$attr.wifi_friction};
    private static final int[] WIFI_CONNECTION_STRENGTH = {R$string.accessibility_no_wifi, R$string.accessibility_wifi_one_bar, R$string.accessibility_wifi_two_bars, R$string.accessibility_wifi_three_bars, R$string.accessibility_wifi_signal_full};

    private static StateListDrawable getFrictionStateListDrawable(Context context) {
        TypedArray typedArrayObtainStyledAttributes;
        try {
            typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(FRICTION_ATTRS);
        } catch (Resources.NotFoundException unused) {
            typedArrayObtainStyledAttributes = null;
        }
        if (typedArrayObtainStyledAttributes != null) {
            return (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(0);
        }
        return null;
    }

    public AccessPointPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mForSavedNetworks = false;
        this.mWifiSpeed = 0;
        this.mNotifyChanged = new Runnable() { // from class: com.android.settingslib.wifi.AccessPointPreference.1
            @Override // java.lang.Runnable
            public void run() {
                AccessPointPreference.this.notifyChanged();
            }
        };
        this.mFrictionSld = null;
        this.mBadgePadding = 0;
        this.mBadgeCache = null;
        this.mIconInjector = new IconInjector(context);
    }

    public AccessPointPreference(AccessPoint accessPoint, Context context, UserBadgeCache userBadgeCache, boolean z) {
        this(accessPoint, context, userBadgeCache, 0, z);
        refresh();
    }

    public AccessPointPreference(AccessPoint accessPoint, Context context, UserBadgeCache userBadgeCache, int i, boolean z) {
        this(accessPoint, context, userBadgeCache, i, z, getFrictionStateListDrawable(context), -1, new IconInjector(context));
    }

    AccessPointPreference(AccessPoint accessPoint, Context context, UserBadgeCache userBadgeCache, int i, boolean z, StateListDrawable stateListDrawable, int i2, IconInjector iconInjector) {
        super(context);
        this.mForSavedNetworks = false;
        this.mWifiSpeed = 0;
        this.mNotifyChanged = new Runnable() { // from class: com.android.settingslib.wifi.AccessPointPreference.1
            @Override // java.lang.Runnable
            public void run() {
                AccessPointPreference.this.notifyChanged();
            }
        };
        setLayoutResource(R$layout.preference_access_point);
        setWidgetLayoutResource(getWidgetLayoutResourceId());
        this.mBadgeCache = userBadgeCache;
        this.mAccessPoint = accessPoint;
        this.mForSavedNetworks = z;
        accessPoint.setTag(this);
        this.mLevel = i2;
        this.mDefaultIconResId = i;
        this.mFrictionSld = stateListDrawable;
        this.mIconInjector = iconInjector;
        this.mBadgePadding = context.getResources().getDimensionPixelSize(R$dimen.wifi_preference_badge_padding);
    }

    protected int getWidgetLayoutResourceId() {
        return R$layout.access_point_friction_widget;
    }

    public AccessPoint getAccessPoint() {
        return this.mAccessPoint;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mAccessPoint == null) {
            return;
        }
        Drawable icon = getIcon();
        if (icon != null) {
            icon.setLevel(this.mLevel);
        }
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.title);
        this.mTitleView = textView;
        if (textView != null) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, this.mBadge, (Drawable) null);
            this.mTitleView.setCompoundDrawablePadding(this.mBadgePadding);
        }
        preferenceViewHolder.itemView.setContentDescription(this.mContentDescription);
        bindFrictionImage((ImageView) preferenceViewHolder.findViewById(R$id.friction_icon));
        preferenceViewHolder.findViewById(R$id.two_target_divider).setVisibility(shouldShowDivider() ? 0 : 4);
    }

    public boolean shouldShowDivider() {
        return this.mShowDivider;
    }

    public void setShowDivider(boolean z) {
        this.mShowDivider = z;
        notifyChanged();
    }

    protected void updateIcon(int i, Context context) {
        if (i == -1) {
            safeSetDefaultIcon();
            return;
        }
        TronUtils.logWifiSettingsSpeed(context, this.mWifiSpeed);
        Drawable icon = this.mIconInjector.getIcon(i);
        if (!this.mForSavedNetworks && icon != null) {
            icon.setTintList(Utils.getColorAttr(context, R.attr.colorControlNormal));
            setIcon(icon);
        } else {
            safeSetDefaultIcon();
        }
    }

    private void bindFrictionImage(ImageView imageView) {
        if (imageView == null || this.mFrictionSld == null) {
            return;
        }
        if (this.mAccessPoint.getSecurity() != 0 && this.mAccessPoint.getSecurity() != 4) {
            this.mFrictionSld.setState(STATE_SECURED);
        } else if (this.mAccessPoint.isMetered()) {
            this.mFrictionSld.setState(STATE_METERED);
        }
        imageView.setImageDrawable(this.mFrictionSld.getCurrent());
    }

    private void safeSetDefaultIcon() {
        int i = this.mDefaultIconResId;
        if (i != 0) {
            setIcon(i);
        } else {
            setIcon((Drawable) null);
        }
    }

    protected void updateBadge(Context context) {
        WifiConfiguration config = this.mAccessPoint.getConfig();
        if (config != null) {
            this.mBadge = this.mBadgeCache.getUserBadge(config.creatorUid);
        }
    }

    public void refresh() {
        setTitle(this, this.mAccessPoint);
        Context context = getContext();
        int level = this.mAccessPoint.getLevel();
        int speed = this.mAccessPoint.getSpeed();
        if (level != this.mLevel || speed != this.mWifiSpeed) {
            this.mLevel = level;
            this.mWifiSpeed = speed;
            updateIcon(level, context);
            notifyChanged();
        }
        updateBadge(context);
        setSummary(this.mForSavedNetworks ? this.mAccessPoint.getSavedNetworkSummary() : this.mAccessPoint.getSettingsSummary());
        this.mContentDescription = buildContentDescription(getContext(), this, this.mAccessPoint);
    }

    @Override // androidx.preference.Preference
    protected void notifyChanged() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            postNotifyChanged();
        } else {
            super.notifyChanged();
        }
    }

    static void setTitle(AccessPointPreference accessPointPreference, AccessPoint accessPoint) {
        accessPointPreference.setTitle(accessPoint.getTitle());
    }

    static CharSequence buildContentDescription(Context context, Preference preference, AccessPoint accessPoint) {
        String string;
        CharSequence title = preference.getTitle();
        CharSequence summary = preference.getSummary();
        if (!TextUtils.isEmpty(summary)) {
            title = TextUtils.concat(title, ",", summary);
        }
        int level = accessPoint.getLevel();
        if (level >= 0) {
            int[] iArr = WIFI_CONNECTION_STRENGTH;
            if (level < iArr.length) {
                title = TextUtils.concat(title, ",", context.getString(iArr[level]));
            }
        }
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = title;
        charSequenceArr[1] = ",";
        if (accessPoint.getSecurity() == 0) {
            string = context.getString(R$string.accessibility_wifi_security_type_none);
        } else {
            string = context.getString(R$string.accessibility_wifi_security_type_secured);
        }
        charSequenceArr[2] = string;
        return TextUtils.concat(charSequenceArr);
    }

    public void onLevelChanged() {
        postNotifyChanged();
    }

    private void postNotifyChanged() {
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.post(this.mNotifyChanged);
        }
    }

    public static class UserBadgeCache {
        private final SparseArray<Drawable> mBadges = new SparseArray<>();
        private final PackageManager mPm;

        public UserBadgeCache(PackageManager packageManager) {
            this.mPm = packageManager;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Drawable getUserBadge(int i) {
            int iIndexOfKey = this.mBadges.indexOfKey(i);
            if (iIndexOfKey < 0) {
                Drawable userBadgeForDensity = this.mPm.getUserBadgeForDensity(new UserHandle(i), 0);
                this.mBadges.put(i, userBadgeForDensity);
                return userBadgeForDensity;
            }
            return this.mBadges.valueAt(iIndexOfKey);
        }
    }

    static class IconInjector {
        private final Context mContext;

        public IconInjector(Context context) {
            this.mContext = context;
        }

        public Drawable getIcon(int i) {
            return this.mContext.getDrawable(Utils.getWifiIconResource(i));
        }
    }
}
