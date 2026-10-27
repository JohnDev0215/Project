package com.android.phone.euicc;

import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class EuiccPublicActionUiDispatcherActivity extends EuiccUiDispatcherActivity {
    @Override // com.android.phone.euicc.EuiccUiDispatcherActivity
    protected Intent getEuiccUiIntent() {
        String action = getIntent().getAction();
        Intent intent = new Intent();
        intent.putExtras(getIntent());
        if (((action.hashCode() == 1009117959 && action.equals("android.telephony.euicc.action.START_EUICC_ACTIVATION")) ? (byte) 0 : (byte) -1) == 0) {
            intent.setAction("android.service.euicc.action.START_EUICC_ACTIVATION");
            return intent;
        }
        Log.w("EuiccPublicActionUiDispatcherActivity", "Unsupported action: " + action);
        return null;
    }
}
