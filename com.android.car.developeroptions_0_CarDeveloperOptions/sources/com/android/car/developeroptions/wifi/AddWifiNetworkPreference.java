package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.wifi.dpp.WifiDppUtils;

/* JADX INFO: loaded from: classes.dex */
public class AddWifiNetworkPreference extends Preference {
    private boolean mInitialized;

    public AddWifiNetworkPreference(Context context) {
        super(context);
        setLayoutResource(R.layout.preference_access_point);
        setWidgetLayoutResource(R.layout.wifi_button_preference_widget);
        setIcon(R.drawable.ic_menu_add);
        setTitle(R.string.wifi_add_network);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mInitialized) {
            return;
        }
        this.mInitialized = true;
        ImageButton imageButton = (ImageButton) preferenceViewHolder.findViewById(R.id.button_icon);
        imageButton.setImageDrawable(getDrawable(R.drawable.ic_scan_24dp));
        imageButton.setContentDescription(getContext().getString(R.string.wifi_dpp_scan_qr_code));
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$AddWifiNetworkPreference$Teb5i8WpHde48VFmBQWyMDF90is
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$AddWifiNetworkPreference(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBindViewHolder$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onBindViewHolder$0$AddWifiNetworkPreference(View view) {
        getContext().startActivity(WifiDppUtils.getEnrolleeQrCodeScannerIntent(null));
    }

    private Drawable getDrawable(int i) {
        try {
            return getContext().getDrawable(i);
        } catch (Resources.NotFoundException unused) {
            Log.e("AddWifiNetworkPreference", "Resource does not exist: " + i);
            return null;
        }
    }
}
