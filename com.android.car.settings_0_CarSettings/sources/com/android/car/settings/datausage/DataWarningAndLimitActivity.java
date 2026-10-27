package com.android.car.settings.datausage;

import android.net.NetworkTemplate;
import androidx.fragment.app.Fragment;
import com.android.car.settings.common.BaseCarSettingsActivity;

/* JADX INFO: loaded from: classes5.dex */
public class DataWarningAndLimitActivity extends BaseCarSettingsActivity {
    @Override // com.android.car.settings.common.BaseCarSettingsActivity
    protected Fragment getInitialFragment() {
        NetworkTemplate template = getIntent().getParcelableExtra("android.net.NETWORK_TEMPLATE");
        return DataWarningAndLimitFragment.newInstance(template);
    }
}
