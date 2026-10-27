package com.android.car.settings.network;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.telephony.euicc.EuiccManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AddMobileNetworkPreferenceController extends PreferenceController<Preference> {
    static final Intent ADD_NETWORK_INTENT = new Intent("android.telephony.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION").putExtra("android.telephony.euicc.extra.FORCE_PROVISION", true);

    public AddMobileNetworkPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        EuiccManager euiccManager = (EuiccManager) getContext().getSystemService(EuiccManager.class);
        if (!euiccManager.isEnabled()) {
            return 2;
        }
        List<ResolveInfo> resolveInfos = getContext().getPackageManager().queryIntentActivities(ADD_NETWORK_INTENT, 0);
        return resolveInfos.isEmpty() ? 2 : 0;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        getContext().startActivity(ADD_NETWORK_INTENT);
        return true;
    }
}
