package com.android.car.developeroptions.notification;

import android.app.Dialog;
import android.os.Bundle;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.notification.ZenDurationDialog;

/* JADX INFO: loaded from: classes.dex */
public class SettingsZenDurationDialog extends InstrumentedDialogFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1341;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new ZenDurationDialog(getContext()).createDialog();
    }
}
