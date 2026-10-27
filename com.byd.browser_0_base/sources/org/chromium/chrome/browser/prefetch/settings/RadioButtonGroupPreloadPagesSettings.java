package org.chromium.chrome.browser.prefetch.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import defpackage.mb1;
import defpackage.n32;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionAndAuxButton;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonGroupPreloadPagesSettings extends Preference implements RadioGroup.OnCheckedChangeListener, RadioButtonWithDescriptionAndAuxButton.a {
    public RadioButtonWithDescriptionAndAuxButton S;
    public RadioButtonWithDescriptionAndAuxButton T;
    public RadioButtonWithDescription U;
    public int V;
    public a W;
    public mb1 X;

    public interface a {
    }

    public RadioButtonGroupPreloadPagesSettings(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.radio_button_group_preload_pages_preference;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionAndAuxButton.a
    public final void k(int i) {
        if (i == this.S.getId()) {
            ((PreloadPagesSettingsFragment) this.W).O0(2);
        } else if (i == this.T.getId()) {
            ((PreloadPagesSettingsFragment) this.W).O0(1);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.S.getId()) {
            this.V = 2;
        } else if (i == this.T.getId()) {
            this.V = 1;
        } else if (i == this.U.getId()) {
            this.V = 0;
        }
        h(Integer.valueOf(this.V));
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton = (RadioButtonWithDescriptionAndAuxButton) n32Var.u(R$id.extended_preloading);
        this.S = radioButtonWithDescriptionAndAuxButton;
        radioButtonWithDescriptionAndAuxButton.setAuxButtonClickedListener(this);
        RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton2 = (RadioButtonWithDescriptionAndAuxButton) n32Var.u(R$id.standard_preloading);
        this.T = radioButtonWithDescriptionAndAuxButton2;
        radioButtonWithDescriptionAndAuxButton2.setAuxButtonClickedListener(this);
        RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) n32Var.u(R$id.no_preloading);
        this.U = radioButtonWithDescription;
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) radioButtonWithDescription.getRootView();
        radioButtonWithDescriptionLayout.setOnCheckedChangeListener(this);
        int i = this.V;
        this.V = i;
        this.S.setChecked(i == 2);
        this.T.setChecked(i == 1);
        this.U.setChecked(i == 0);
        if (this.X.g(this)) {
            radioButtonWithDescriptionLayout.setEnabled(false);
            this.S.setAuxButtonEnabled(true);
            this.T.setAuxButtonEnabled(true);
        }
    }
}
