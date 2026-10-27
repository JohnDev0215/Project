package org.chromium.chrome.browser.privacy_guide;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import defpackage.ji;
import defpackage.n3;
import defpackage.p42;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionAndAuxButton;

/* JADX INFO: loaded from: classes2.dex */
public class SafeBrowsingFragment extends Fragment implements RadioButtonWithDescriptionAndAuxButton.a, RadioGroup.OnCheckedChangeListener {
    public RadioButtonWithDescriptionAndAuxButton Z;
    public RadioButtonWithDescriptionAndAuxButton a0;
    public ji b0;

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.privacy_guide_sb_step, viewGroup, false);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionAndAuxButton.a
    public final void k(int i) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f12J.getContext());
        if (i == this.a0.getId()) {
            this.b0.d(new p42(layoutInflaterFrom.inflate(R$layout.privacy_guide_sb_enhanced_explanation, (ViewGroup) null)), true);
        } else if (i == this.Z.getId()) {
            this.b0.d(new p42(layoutInflaterFrom.inflate(R$layout.privacy_guide_sb_standard_explanation, (ViewGroup) null)), true);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R$id.enhanced_option) {
            N.MzV0f_Xz(2);
            n3.X0("Settings.PrivacyGuide.ChangeSafeBrowsingEnhanced");
        } else if (i == R$id.standard_option) {
            N.MzV0f_Xz(1);
            n3.X0("Settings.PrivacyGuide.ChangeSafeBrowsingStandard");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        ((RadioGroup) view.findViewById(R$id.sb_radio_button)).setOnCheckedChangeListener(this);
        this.a0 = (RadioButtonWithDescriptionAndAuxButton) view.findViewById(R$id.enhanced_option);
        this.Z = (RadioButtonWithDescriptionAndAuxButton) view.findViewById(R$id.standard_option);
        this.a0.setAuxButtonClickedListener(this);
        this.Z.setAuxButtonClickedListener(this);
        int iMdyQjr8h = N.MdyQjr8h();
        if (iMdyQjr8h == 1) {
            this.Z.setChecked(true);
        } else {
            if (iMdyQjr8h != 2) {
                return;
            }
            this.a0.setChecked(true);
        }
    }
}
