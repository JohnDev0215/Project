package com.android.car.settings.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.car.settings.R;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public class ActionButtonInfo {
    private static final Logger LOG = new Logger((Class<?>) ActionButtonInfo.class);
    private ImageView mButtonIconView;
    private WeakReference<ButtonInfoChangeListener> mButtonInfoChangeListener;
    private TextView mButtonTextView;
    private View mButtonView;
    private final Context mContext;
    private Drawable mIcon;
    private View.OnClickListener mListener;
    private String mMessageToShowWhenUxRestrictedPreferenceClicked;
    private CharSequence mText;
    private boolean mIsPreferenceRestricted = false;
    private boolean mIsEnabled = true;
    private boolean mIsVisible = true;

    interface ButtonInfoChangeListener {
        void onButtonInfoChange(ActionButtonInfo actionButtonInfo);
    }

    ActionButtonInfo(Context context, ButtonInfoChangeListener changeListener) {
        this.mContext = context;
        this.mButtonInfoChangeListener = new WeakReference<>(changeListener);
        this.mMessageToShowWhenUxRestrictedPreferenceClicked = context.getString(R.string.car_ui_restricted_while_driving);
    }

    public ActionButtonInfo setVisible(boolean isVisible) {
        if (isVisible != this.mIsVisible) {
            this.mIsVisible = isVisible;
            update();
        }
        return this;
    }

    public ActionButtonInfo setText(int textResId) {
        String newText = this.mContext.getString(textResId);
        if (!TextUtils.equals(newText, this.mText)) {
            this.mText = newText;
            update();
        }
        return this;
    }

    public ActionButtonInfo setIcon(int iconResId) {
        if (iconResId == 0) {
            return this;
        }
        try {
            Drawable icon = this.mContext.getDrawable(iconResId);
            this.mIcon = icon;
            update();
        } catch (Resources.NotFoundException e) {
            LOG.e("Resource does not exist: " + iconResId);
        }
        return this;
    }

    public ActionButtonInfo setEnabled(boolean isEnabled) {
        if (isEnabled != this.mIsEnabled) {
            this.mIsEnabled = isEnabled;
            update();
        }
        return this;
    }

    public ActionButtonInfo setOnClickListener(View.OnClickListener listener) {
        if (listener != this.mListener) {
            this.mListener = listener;
            update();
        }
        return this;
    }

    ActionButtonInfo setButtonView(View view) {
        this.mButtonView = view;
        return this;
    }

    ActionButtonInfo setButtonTextView(TextView textView) {
        this.mButtonTextView = textView;
        return this;
    }

    ActionButtonInfo setButtonIconView(ImageView iconView) {
        this.mButtonIconView = iconView;
        return this;
    }

    ActionButtonInfo setPreferenceRestricted(boolean isRestricted) {
        this.mIsPreferenceRestricted = isRestricted;
        return this;
    }

    public CharSequence getText() {
        return this.mText;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public View.OnClickListener getOnClickListener() {
        return this.mListener;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean isVisible() {
        return shouldBeVisible();
    }

    void setUpButton() {
        this.mButtonTextView.setText(this.mText);
        this.mButtonIconView.setImageDrawable(this.mIcon);
        this.mButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.common.-$$Lambda$sWect43tVaw4e7SlAC8JTcPFJQM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.performClick(view);
            }
        });
        boolean enabled = isEnabled() || this.mIsPreferenceRestricted;
        this.mButtonView.setEnabled(enabled);
        this.mButtonTextView.setEnabled(enabled);
        this.mButtonIconView.setEnabled(enabled);
        this.mButtonIconView.setVisibility(this.mIcon != null ? 0 : 8);
        if (shouldBeVisible()) {
            this.mButtonView.setVisibility(0);
        } else {
            this.mButtonView.setVisibility(8);
        }
    }

    void performClick(View v) {
        View.OnClickListener onClickListener;
        if (!isEnabled() || (onClickListener = this.mListener) == null) {
            return;
        }
        if (this.mIsPreferenceRestricted) {
            TextUtils.isEmpty(this.mMessageToShowWhenUxRestrictedPreferenceClicked);
        } else {
            onClickListener.onClick(v);
        }
    }

    private boolean shouldBeVisible() {
        return this.mIsVisible && !(TextUtils.isEmpty(this.mText) && this.mIcon == null);
    }

    private void update() {
        ButtonInfoChangeListener listener = this.mButtonInfoChangeListener.get();
        if (listener != null) {
            listener.onButtonInfoChange(this);
        }
    }
}
