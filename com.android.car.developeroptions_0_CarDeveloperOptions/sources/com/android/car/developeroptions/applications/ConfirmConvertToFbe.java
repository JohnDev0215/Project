package com.android.car.developeroptions.applications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;

/* JADX INFO: loaded from: classes.dex */
public class ConfirmConvertToFbe extends SettingsPreferenceFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 403;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.confirm_convert_fbe, (ViewGroup) null);
        ((Button) viewInflate.findViewById(R.id.button_confirm_convert_fbe)).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.ConfirmConvertToFbe.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.FACTORY_RESET");
                intent.addFlags(268435456);
                intent.setPackage("android");
                intent.putExtra("android.intent.extra.REASON", "convert_fbe");
                ConfirmConvertToFbe.this.getActivity().sendBroadcast(intent);
            }
        });
        return viewInflate;
    }
}
