package com.android.car.developeroptions.applications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;

/* JADX INFO: loaded from: classes.dex */
public class ConvertToFbe extends InstrumentedFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 402;
    }

    private boolean runKeyguardConfirmation(int i) {
        return new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(i, getActivity().getResources().getText(R.string.convert_to_file_encryption));
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(R.string.convert_to_file_encryption);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.convert_fbe, (ViewGroup) null);
        ((Button) viewInflate.findViewById(R.id.button_convert_fbe)).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.-$$Lambda$ConvertToFbe$WT6tWUP9-vfL1jrR08k54LxM1Kw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0$ConvertToFbe(view);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateView$0$ConvertToFbe(View view) {
        if (runKeyguardConfirmation(55)) {
            return;
        }
        convert();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 55 && i2 == -1) {
            convert();
        }
    }

    private void convert() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(ConfirmConvertToFbe.class.getName());
        subSettingLauncher.setTitleRes(R.string.convert_to_file_encryption);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.launch();
    }
}
