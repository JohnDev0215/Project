package com.android.settingslib.wifi;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.R;
import com.android.settingslib.Utils;
import com.android.wifitrackerlib.WifiEntry;

/* JADX INFO: loaded from: classes6.dex */
public class WifiEntryPreference extends Preference implements WifiEntry.WifiEntryCallback, View.OnClickListener {
    private CharSequence mContentDescription;
    private final StateListDrawable mFrictionSld;
    private final IconInjector mIconInjector;
    private int mLevel;
    private OnButtonClickListener mOnButtonClickListener;
    private boolean mShowX;
    private WifiEntry mWifiEntry;
    private static final int[] STATE_SECURED = {R.attr.state_encrypted};
    private static final int[] FRICTION_ATTRS = {R.attr.wifi_friction};
    private static final int[] WIFI_CONNECTION_STRENGTH = {R.string.accessibility_no_wifi, R.string.accessibility_wifi_one_bar, R.string.accessibility_wifi_two_bars, R.string.accessibility_wifi_three_bars, R.string.accessibility_wifi_signal_full};

    public interface OnButtonClickListener {
        void onButtonClick(WifiEntryPreference wifiEntryPreference);
    }

    public WifiEntryPreference(Context context, WifiEntry wifiEntry) {
        this(context, wifiEntry, new IconInjector(context));
    }

    WifiEntryPreference(Context context, WifiEntry wifiEntry, IconInjector iconInjector) {
        super(context);
        this.mLevel = -1;
        setLayoutResource(R.layout.preference_access_point);
        setWidgetLayoutResource(R.layout.access_point_friction_widget);
        this.mFrictionSld = getFrictionStateListDrawable();
        this.mWifiEntry = wifiEntry;
        wifiEntry.setListener(this);
        this.mIconInjector = iconInjector;
        refresh();
    }

    public WifiEntry getWifiEntry() {
        return this.mWifiEntry;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        Drawable drawable = getIcon();
        if (drawable != null) {
            drawable.setLevel(this.mLevel);
        }
        view.itemView.setContentDescription(this.mContentDescription);
        view.findViewById(R.id.two_target_divider).setVisibility(4);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.icon_button);
        ImageView frictionImageView = (ImageView) view.findViewById(R.id.friction_icon);
        if (this.mWifiEntry.getHelpUriString() != null && this.mWifiEntry.getConnectedState() == 0) {
            Drawable drawablehelp = getDrawable(R.drawable.ic_help);
            drawablehelp.setTintList(Utils.getColorAttr(getContext(), android.R.attr.colorControlNormal));
            imageButton.setImageDrawable(drawablehelp);
            imageButton.setVisibility(0);
            imageButton.setOnClickListener(this);
            imageButton.setContentDescription(getContext().getText(R.string.help_label));
            if (frictionImageView != null) {
                frictionImageView.setVisibility(8);
                return;
            }
            return;
        }
        imageButton.setVisibility(8);
        if (frictionImageView != null) {
            frictionImageView.setVisibility(0);
            bindFrictionImage(frictionImageView);
        }
    }

    public void refresh() {
        setTitle(this.mWifiEntry.getTitle());
        int level = this.mWifiEntry.getLevel();
        boolean showX = this.mWifiEntry.shouldShowXLevelIcon();
        if (level != this.mLevel || showX != this.mShowX) {
            this.mLevel = level;
            this.mShowX = showX;
            updateIcon(showX, level);
            notifyChanged();
        }
        setSummary(this.mWifiEntry.getSummary(false));
        this.mContentDescription = buildContentDescription();
    }

    @Override // com.android.wifitrackerlib.WifiEntry.WifiEntryCallback
    public void onUpdated() {
        refresh();
    }

    public void onConnectResult(int status) {
    }

    public void onDisconnectResult(int status) {
    }

    public void onForgetResult(int status) {
    }

    public void onSignInResult(int status) {
    }

    private void updateIcon(boolean showX, int level) {
        if (level == -1) {
            setIcon((Drawable) null);
            return;
        }
        Drawable drawable = this.mIconInjector.getIcon(showX, level);
        if (drawable != null) {
            drawable.setTintList(Utils.getColorAttr(getContext(), android.R.attr.colorControlNormal));
            setIcon(drawable);
        } else {
            setIcon((Drawable) null);
        }
    }

    private StateListDrawable getFrictionStateListDrawable() {
        TypedArray frictionSld;
        try {
            frictionSld = getContext().getTheme().obtainStyledAttributes(FRICTION_ATTRS);
        } catch (Resources.NotFoundException e) {
            frictionSld = null;
        }
        if (frictionSld != null) {
            return (StateListDrawable) frictionSld.getDrawable(0);
        }
        return null;
    }

    private void bindFrictionImage(ImageView frictionImageView) {
        if (frictionImageView == null || this.mFrictionSld == null) {
            return;
        }
        if (this.mWifiEntry.getSecurity() != 0 && this.mWifiEntry.getSecurity() != 4) {
            this.mFrictionSld.setState(STATE_SECURED);
        }
        frictionImageView.setImageDrawable(this.mFrictionSld.getCurrent());
    }

    CharSequence buildContentDescription() {
        String string;
        Context context = getContext();
        CharSequence contentDescription = getTitle();
        CharSequence summary = getSummary();
        if (!TextUtils.isEmpty(summary)) {
            contentDescription = TextUtils.concat(contentDescription, ",", summary);
        }
        int level = this.mWifiEntry.getLevel();
        if (level >= 0) {
            int[] iArr = WIFI_CONNECTION_STRENGTH;
            if (level < iArr.length) {
                contentDescription = TextUtils.concat(contentDescription, ",", context.getString(iArr[level]));
            }
        }
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = contentDescription;
        charSequenceArr[1] = ",";
        if (this.mWifiEntry.getSecurity() == 0) {
            string = context.getString(R.string.accessibility_wifi_security_type_none);
        } else {
            string = context.getString(R.string.accessibility_wifi_security_type_secured);
        }
        charSequenceArr[2] = string;
        return TextUtils.concat(charSequenceArr);
    }

    static class IconInjector {
        private final Context mContext;

        IconInjector(Context context) {
            this.mContext = context;
        }

        public Drawable getIcon(boolean showX, int level) {
            return this.mContext.getDrawable(Utils.getWifiIconResource(showX, level));
        }
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.mOnButtonClickListener = listener;
        notifyChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnButtonClickListener onButtonClickListener;
        if (view.getId() == R.id.icon_button && (onButtonClickListener = this.mOnButtonClickListener) != null) {
            onButtonClickListener.onButtonClick(this);
        }
    }

    private Drawable getDrawable(int iconResId) {
        try {
            Drawable buttonIcon = getContext().getDrawable(iconResId);
            return buttonIcon;
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }
}
