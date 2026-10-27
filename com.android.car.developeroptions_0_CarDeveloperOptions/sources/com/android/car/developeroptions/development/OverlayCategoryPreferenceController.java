package com.android.car.developeroptions.development;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class OverlayCategoryPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    private static final Comparator<OverlayInfo> OVERLAY_INFO_COMPARATOR = Comparator.comparingInt(new ToIntFunction() { // from class: com.android.car.developeroptions.development.-$$Lambda$OverlayCategoryPreferenceController$ZJID64fQDmXfn4fcaU1aEBn0l0Q
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            return ((OverlayInfo) obj).priority;
        }
    });
    static final String PACKAGE_DEVICE_DEFAULT = "package_device_default";
    private final boolean mAvailable;
    private final String mCategory;
    private final IOverlayManager mOverlayManager;
    private final PackageManager mPackageManager;
    private ListPreference mPreference;

    OverlayCategoryPreferenceController(Context context, PackageManager packageManager, IOverlayManager iOverlayManager, String str) {
        super(context);
        this.mOverlayManager = iOverlayManager;
        this.mPackageManager = packageManager;
        this.mCategory = str;
        this.mAvailable = (iOverlayManager == null || getOverlayInfos().isEmpty()) ? false : true;
    }

    public OverlayCategoryPreferenceController(Context context, String str) {
        this(context, context.getPackageManager(), IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay")), str);
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mAvailable;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mCategory;
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        setPreference((ListPreference) preferenceScreen.findPreference(getPreferenceKey()));
    }

    void setPreference(ListPreference listPreference) {
        this.mPreference = listPreference;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        return setOverlay((String) obj);
    }

    private boolean setOverlay(final String str) {
        final String str2 = (String) getOverlayInfos().stream().filter(new Predicate() { // from class: com.android.car.developeroptions.development.-$$Lambda$OverlayCategoryPreferenceController$_2B-vlFiuO6pZq_b63wPElsZXP8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((OverlayInfo) obj).isEnabled();
            }
        }).map(new Function() { // from class: com.android.car.developeroptions.development.-$$Lambda$OverlayCategoryPreferenceController$nH8zqhvwC6U4527eFMTemw3N7e0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((OverlayInfo) obj).packageName;
            }
        }).findFirst().orElse(null);
        if ((PACKAGE_DEVICE_DEFAULT.equals(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2)) {
            return true;
        }
        new AsyncTask<Void, Void, Boolean>() { // from class: com.android.car.developeroptions.development.OverlayCategoryPreferenceController.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                try {
                    return OverlayCategoryPreferenceController.PACKAGE_DEVICE_DEFAULT.equals(str) ? Boolean.valueOf(OverlayCategoryPreferenceController.this.mOverlayManager.setEnabled(str2, false, 0)) : Boolean.valueOf(OverlayCategoryPreferenceController.this.mOverlayManager.setEnabledExclusiveInCategory(str, 0));
                } catch (RemoteException e) {
                    Log.w("OverlayCategoryPC", "Error enabling overlay.", e);
                    return Boolean.FALSE;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                OverlayCategoryPreferenceController overlayCategoryPreferenceController = OverlayCategoryPreferenceController.this;
                overlayCategoryPreferenceController.updateState(overlayCategoryPreferenceController.mPreference);
                if (bool.booleanValue()) {
                    return;
                }
                Toast.makeText(((AbstractPreferenceController) OverlayCategoryPreferenceController.this).mContext, R.string.overlay_toast_failed_to_apply, 1).show();
            }
        }.execute(new Void[0]);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String string = this.mContext.getString(R.string.overlay_option_device_default);
        String str = PACKAGE_DEVICE_DEFAULT;
        arrayList.add(PACKAGE_DEVICE_DEFAULT);
        arrayList2.add(string);
        for (OverlayInfo overlayInfo : getOverlayInfos()) {
            arrayList.add(overlayInfo.packageName);
            try {
                arrayList2.add(this.mPackageManager.getApplicationInfo(overlayInfo.packageName, 0).loadLabel(this.mPackageManager).toString());
            } catch (PackageManager.NameNotFoundException unused) {
                arrayList2.add(overlayInfo.packageName);
            }
            if (overlayInfo.isEnabled()) {
                str = (String) arrayList.get(arrayList.size() - 1);
                string = (String) arrayList2.get(arrayList2.size() - 1);
            }
        }
        this.mPreference.setEntries((CharSequence[]) arrayList2.toArray(new String[arrayList2.size()]));
        this.mPreference.setEntryValues((CharSequence[]) arrayList.toArray(new String[arrayList.size()]));
        this.mPreference.setValue(str);
        this.mPreference.setSummary(string);
    }

    private List<OverlayInfo> getOverlayInfos() {
        ArrayList arrayList = new ArrayList();
        try {
            for (OverlayInfo overlayInfo : this.mOverlayManager.getOverlayInfosForTarget("android", 0)) {
                if (this.mCategory.equals(overlayInfo.category)) {
                    arrayList.add(overlayInfo);
                }
            }
            arrayList.sort(OVERLAY_INFO_COMPARATOR);
            return arrayList;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        setOverlay(PACKAGE_DEVICE_DEFAULT);
        updateState(this.mPreference);
    }
}
