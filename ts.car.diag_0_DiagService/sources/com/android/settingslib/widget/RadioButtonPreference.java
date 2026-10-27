package com.android.settingslib.widget;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.preference.CheckBoxPreference;
import androidx.preference.f;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class RadioButtonPreference extends CheckBoxPreference {
    private a V;
    private View W;
    private int X;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(RadioButtonPreference radioButtonPreference);
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.V = null;
        this.X = -1;
        N0();
    }

    private void N0() {
        y0(R$layout.preference_widget_radiobutton);
        m0(R$layout.preference_radio);
        j0(false);
    }

    @Override // androidx.preference.CheckBoxPreference, androidx.preference.Preference
    public void M(f fVar) {
        int i;
        super.M(fVar);
        View viewM = fVar.M(R$id.summary_container);
        if (viewM != null) {
            viewM.setVisibility(TextUtils.isEmpty(y()) ? 8 : 0);
            View viewM2 = fVar.M(R$id.appendix);
            this.W = viewM2;
            if (viewM2 != null && (i = this.X) != -1) {
                viewM2.setVisibility(i);
            }
        }
        TextView textView = (TextView) fVar.M(R.id.title);
        if (textView != null) {
            textView.setSingleLine(false);
            textView.setMaxLines(3);
        }
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public void N() {
        a aVar = this.V;
        if (aVar != null) {
            aVar.a(this);
        }
    }
}
