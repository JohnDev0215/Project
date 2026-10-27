package com.android.car.developeroptions.enterprise;

import android.content.Context;
import android.text.format.DateUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public abstract class AdminActionPreferenceControllerBase extends AbstractPreferenceController implements PreferenceControllerMixin {
    protected final EnterprisePrivacyFeatureProvider mFeatureProvider;

    protected abstract Date getAdminActionTimestamp();

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public AdminActionPreferenceControllerBase(Context context) {
        super(context);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getEnterprisePrivacyFeatureProvider(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        String dateTime;
        Date adminActionTimestamp = getAdminActionTimestamp();
        if (adminActionTimestamp == null) {
            dateTime = this.mContext.getString(R.string.enterprise_privacy_none);
        } else {
            dateTime = DateUtils.formatDateTime(this.mContext, adminActionTimestamp.getTime(), 17);
        }
        preference.setSummary(dateTime);
    }
}
