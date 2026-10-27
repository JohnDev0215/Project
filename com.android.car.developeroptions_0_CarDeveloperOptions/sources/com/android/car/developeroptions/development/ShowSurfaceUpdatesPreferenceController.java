package com.android.car.developeroptions.development;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class ShowSurfaceUpdatesPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    static final int SURFACE_FLINGER_READ_CODE = 1010;
    static final String SURFACE_FLINGER_SERVICE_KEY = "SurfaceFlinger";
    private final IBinder mSurfaceFlinger;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "show_screen_updates";
    }

    public ShowSurfaceUpdatesPreferenceController(Context context) {
        super(context);
        this.mSurfaceFlinger = ServiceManager.getService(SURFACE_FLINGER_SERVICE_KEY);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        writeShowUpdatesSetting(((Boolean) obj).booleanValue());
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updateShowUpdatesSetting();
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        SwitchPreference switchPreference = (SwitchPreference) this.mPreference;
        if (switchPreference.isChecked()) {
            writeShowUpdatesSetting(false);
            switchPreference.setChecked(false);
        }
    }

    void updateShowUpdatesSetting() {
        try {
            if (this.mSurfaceFlinger != null) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                parcelObtain.writeInterfaceToken("android.ui.ISurfaceComposer");
                this.mSurfaceFlinger.transact(SURFACE_FLINGER_READ_CODE, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readInt();
                parcelObtain2.readInt();
                ((SwitchPreference) this.mPreference).setChecked(parcelObtain2.readInt() != 0);
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        } catch (RemoteException unused) {
        }
    }

    void writeShowUpdatesSetting(boolean z) {
        try {
            if (this.mSurfaceFlinger != null) {
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.writeInterfaceToken("android.ui.ISurfaceComposer");
                parcelObtain.writeInt(z ? 1 : 0);
                this.mSurfaceFlinger.transact(1002, parcelObtain, null, 0);
                parcelObtain.recycle();
            }
        } catch (RemoteException unused) {
        }
        updateShowUpdatesSetting();
    }
}
