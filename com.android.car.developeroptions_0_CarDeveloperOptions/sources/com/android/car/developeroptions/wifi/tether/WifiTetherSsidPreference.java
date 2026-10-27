package com.android.car.developeroptions.wifi.tether;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.ValidatedEditTextPreference;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public class WifiTetherSsidPreference extends ValidatedEditTextPreference {
    private Drawable mButtonIcon;
    private View.OnClickListener mClickListener;
    private View mDivider;
    private ImageButton mImageButton;
    private boolean mVisible;

    public WifiTetherSsidPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize();
    }

    public WifiTetherSsidPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize();
    }

    public WifiTetherSsidPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public WifiTetherSsidPreference(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        setLayoutResource(R.layout.preference_two_target);
        setWidgetLayoutResource(R.layout.wifi_button_preference_widget);
    }

    @Override // com.android.car.developeroptions.widget.ValidatedEditTextPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mImageButton == null) {
            ImageButton imageButton = (ImageButton) preferenceViewHolder.findViewById(R.id.button_icon);
            this.mImageButton = imageButton;
            imageButton.setContentDescription(getContext().getString(R.string.wifi_dpp_share_hotspot));
            setButtonIcon(R.drawable.ic_qrcode_24dp);
            this.mImageButton.setImageDrawable(this.mButtonIcon);
            this.mDivider = preferenceViewHolder.findViewById(R.id.two_target_divider);
        }
        if (this.mVisible) {
            this.mImageButton.setOnClickListener(this.mClickListener);
            this.mImageButton.setVisibility(0);
            this.mDivider.setVisibility(0);
        } else {
            this.mImageButton.setVisibility(8);
            this.mDivider.setVisibility(8);
        }
    }

    public void setButtonOnClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public void setButtonVisible(boolean z) {
        this.mVisible = z;
    }

    private void setButtonIcon(int i) {
        try {
            this.mButtonIcon = getContext().getDrawable(i);
        } catch (Resources.NotFoundException unused) {
            Log.e("WifiTetherSsidPreference", "Resource does not exist: " + i);
        }
    }

    @VisibleForTesting
    boolean isQrCodeButtonAvailable() {
        return this.mVisible && this.mClickListener != null;
    }
}
