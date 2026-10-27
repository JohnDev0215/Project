package org.chromium.chrome.browser.feed.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import defpackage.n32;
import defpackage.oj2;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* JADX INFO: loaded from: classes.dex */
public class RadioButtonGroupVideoPreviewsPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public int S;
    public final ArrayList<RadioButtonWithDescription> T;

    public RadioButtonGroupVideoPreviewsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.radio_button_group_video_previews_preference;
        this.T = new ArrayList<>(Collections.nCopies(3, null));
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2 = 0;
        while (true) {
            if (i2 >= 3) {
                break;
            }
            if (this.T.get(i2).b()) {
                this.S = i2;
                break;
            }
            i2++;
        }
        oj2.U(this.S, 3, "FeedVideoPreviewsPreferenceUserActions");
        h(Integer.valueOf(this.S));
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        ArrayList<RadioButtonWithDescription> arrayList = this.T;
        arrayList.set(0, (RadioButtonWithDescription) n32Var.u(R$id.video_previews_option_never_radio_button));
        arrayList.set(1, (RadioButtonWithDescription) n32Var.u(R$id.video_previews_option_wifi_radio_button));
        arrayList.set(2, (RadioButtonWithDescription) n32Var.u(R$id.video_previews_option_wifi_and_mobile_data_radio_button));
        RadioButtonWithDescription radioButtonWithDescription = arrayList.get(this.S);
        radioButtonWithDescription.setChecked(true);
        ((RadioButtonWithDescriptionLayout) radioButtonWithDescription.getParent()).setOnCheckedChangeListener(this);
    }
}
