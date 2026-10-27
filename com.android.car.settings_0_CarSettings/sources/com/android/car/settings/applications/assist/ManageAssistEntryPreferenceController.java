package com.android.car.settings.applications.assist;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.applications.defaultapps.DefaultAppEntryBasePreferenceController;
import com.android.car.settings.common.FragmentController;
import com.android.internal.app.AssistUtils;
import com.android.settingslib.applications.DefaultAppInfo;

/* JADX INFO: loaded from: classes5.dex */
public class ManageAssistEntryPreferenceController extends DefaultAppEntryBasePreferenceController<Preference> {
    private final AssistUtils mAssistUtils;

    public ManageAssistEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mAssistUtils = new AssistUtils(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppEntryBasePreferenceController
    protected DefaultAppInfo getCurrentDefaultAppInfo() {
        ComponentName cn = this.mAssistUtils.getAssistComponentForUser(getCurrentProcessUserId());
        if (cn == null) {
            return null;
        }
        return new DefaultAppInfo(getContext(), getContext().getPackageManager(), getCurrentProcessUserId(), cn);
    }
}
