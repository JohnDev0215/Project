package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.wifi.CarWifiManager;
import com.android.car.ui.preference.CarUiTwoActionSwitchPreference;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes5.dex */
public class WifiEntryPreferenceController extends PreferenceController<CarUiTwoActionSwitchPreference> implements CarWifiManager.Listener {
    private CarWifiManager mCarWifiManager;

    public WifiEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<CarUiTwoActionSwitchPreference> getPreferenceType() {
        return CarUiTwoActionSwitchPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mCarWifiManager = new CarWifiManager(getContext());
        getPreference().setOnSecondaryActionClickListener(new Consumer() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiEntryPreferenceController$okLhSuIgRfuCQkbZx8-X1Bc3Ps0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onCreateInternal$0$WifiEntryPreferenceController((Boolean) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreateInternal$0$WifiEntryPreferenceController(Boolean isChecked) {
        if (isChecked.booleanValue() != this.mCarWifiManager.isWifiEnabled()) {
            this.mCarWifiManager.setWifiEnabled(isChecked.booleanValue());
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mCarWifiManager.addListener(this);
        this.mCarWifiManager.start();
        getPreference().setSecondaryActionChecked(this.mCarWifiManager.isWifiEnabled());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mCarWifiManager.removeListener(this);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return WifiUtil.isWifiAvailable(getContext()) ? 0 : 2;
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onWifiStateChanged(int state) {
        getPreference().setSecondaryActionChecked(state == 3 || state == 2);
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onAccessPointsChanged() {
    }
}
