package com.android.car.developeroptions.bluetooth;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.UserManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.widget.GearPreference;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothDevicePreference extends GearPreference implements CachedBluetoothDevice.Callback {
    private static int sDimAlpha = Integer.MIN_VALUE;
    private String contentDescription;
    private final CachedBluetoothDevice mCachedDevice;
    private AlertDialog mDisconnectDialog;
    private boolean mHideSecondTarget;
    boolean mNeedNotifyHierarchyChanged;
    private final boolean mShowDevicesWithoutNames;
    private final UserManager mUserManager;

    @Override // com.android.car.developeroptions.widget.GearPreference, com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_gear;
    }

    public BluetoothDevicePreference(Context context, CachedBluetoothDevice cachedBluetoothDevice, boolean z) {
        super(context, null);
        this.contentDescription = null;
        this.mHideSecondTarget = false;
        this.mNeedNotifyHierarchyChanged = false;
        getContext().getResources();
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mShowDevicesWithoutNames = z;
        if (sDimAlpha == Integer.MIN_VALUE) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.disabledAlpha, typedValue, true);
            sDimAlpha = (int) (typedValue.getFloat() * 255.0f);
        }
        this.mCachedDevice = cachedBluetoothDevice;
        cachedBluetoothDevice.registerCallback(this);
        onDeviceAttributesChanged();
    }

    public void setNeedNotifyHierarchyChanged(boolean z) {
        this.mNeedNotifyHierarchyChanged = z;
    }

    @Override // com.android.car.developeroptions.widget.GearPreference, com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference
    protected boolean shouldHideSecondTarget() {
        CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevice;
        return cachedBluetoothDevice == null || cachedBluetoothDevice.getBondState() != 12 || this.mUserManager.hasUserRestriction("no_config_bluetooth") || this.mHideSecondTarget;
    }

    CachedBluetoothDevice getCachedDevice() {
        return this.mCachedDevice;
    }

    @Override // androidx.preference.Preference
    protected void onPrepareForRemoval() {
        super.onPrepareForRemoval();
        this.mCachedDevice.unregisterCallback(this);
        AlertDialog alertDialog = this.mDisconnectDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDisconnectDialog = null;
        }
    }

    public CachedBluetoothDevice getBluetoothDevice() {
        return this.mCachedDevice;
    }

    public void hideSecondTarget(boolean z) {
        this.mHideSecondTarget = z;
    }

    @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
    public void onDeviceAttributesChanged() {
        setTitle(this.mCachedDevice.getName());
        setSummary(this.mCachedDevice.getConnectionSummary());
        Pair<Drawable, String> btRainbowDrawableWithDescription = BluetoothUtils.getBtRainbowDrawableWithDescription(getContext(), this.mCachedDevice);
        Object obj = btRainbowDrawableWithDescription.first;
        if (obj != null) {
            setIcon((Drawable) obj);
            this.contentDescription = (String) btRainbowDrawableWithDescription.second;
        }
        boolean z = true;
        setEnabled(!this.mCachedDevice.isBusy());
        if (!this.mShowDevicesWithoutNames && !this.mCachedDevice.hasHumanReadableName()) {
            z = false;
        }
        setVisible(z);
        if (this.mNeedNotifyHierarchyChanged) {
            notifyHierarchyChanged();
        }
    }

    @Override // com.android.car.developeroptions.widget.GearPreference, com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        ImageView imageView;
        if (findPreferenceInHierarchy("bt_checkbox") != null) {
            setDependency("bt_checkbox");
        }
        if (this.mCachedDevice.getBondState() == 12 && (imageView = (ImageView) preferenceViewHolder.findViewById(R.id.settings_button)) != null) {
            imageView.setOnClickListener(this);
        }
        ImageView imageView2 = (ImageView) preferenceViewHolder.findViewById(android.R.id.icon);
        if (imageView2 != null) {
            imageView2.setContentDescription(this.contentDescription);
            imageView2.setElevation(getContext().getResources().getDimension(R.dimen.bt_icon_elevation));
        }
        super.onBindViewHolder(preferenceViewHolder);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BluetoothDevicePreference)) {
            return false;
        }
        return this.mCachedDevice.equals(((BluetoothDevicePreference) obj).mCachedDevice);
    }

    public int hashCode() {
        return this.mCachedDevice.hashCode();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.preference.Preference, java.lang.Comparable
    public int compareTo(Preference preference) {
        if (!(preference instanceof BluetoothDevicePreference)) {
            return super.compareTo(preference);
        }
        return this.mCachedDevice.compareTo(((BluetoothDevicePreference) preference).mCachedDevice);
    }

    void onClicked() {
        Context context = getContext();
        int bondState = this.mCachedDevice.getBondState();
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        if (this.mCachedDevice.isConnected()) {
            metricsFeatureProvider.action(context, 868, new Pair[0]);
            askDisconnect();
        } else if (bondState == 12) {
            metricsFeatureProvider.action(context, 867, new Pair[0]);
            this.mCachedDevice.connect(true);
        } else if (bondState == 10) {
            metricsFeatureProvider.action(context, 866, new Pair[0]);
            if (!this.mCachedDevice.hasHumanReadableName()) {
                metricsFeatureProvider.action(context, 1096, new Pair[0]);
            }
            pair();
        }
    }

    private void askDisconnect() {
        Context context = getContext();
        String name = this.mCachedDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = context.getString(R.string.bluetooth_device);
        }
        String string = context.getString(R.string.bluetooth_disconnect_all_profiles, name);
        this.mDisconnectDialog = Utils.showDisconnectDialog(context, this.mDisconnectDialog, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.bluetooth.BluetoothDevicePreference.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                BluetoothDevicePreference.this.mCachedDevice.disconnect();
            }
        }, context.getString(R.string.bluetooth_disconnect_title), Html.fromHtml(string));
    }

    private void pair() {
        if (this.mCachedDevice.startPairing()) {
            return;
        }
        Utils.showError(getContext(), this.mCachedDevice.getName(), R.string.bluetooth_pairing_error_message);
    }
}
