package com.android.settingslib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.f;
import java.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class BarChartPreference extends Preference {
    private static final int[] S = {R$id.bar_view1, R$id.bar_view2, R$id.bar_view3, R$id.bar_view4};
    private int P;
    private boolean Q;
    private c R;

    public BarChartPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        G0();
    }

    private void E0(f fVar) {
        Button button = (Button) fVar.M(R$id.bar_chart_details);
        int iB = this.R.b();
        if (iB == 0) {
            button.setVisibility(8);
            return;
        }
        button.setVisibility(0);
        button.setText(iB);
        button.setOnClickListener(this.R.c());
    }

    private void F0(f fVar) {
        ((TextView) fVar.M(R$id.bar_chart_title)).setText(this.R.e());
    }

    private void G0() {
        s0(false);
        m0(R$layout.settings_bar_chart);
        this.P = j().getResources().getDimensionPixelSize(R$dimen.settings_bar_view_max_height);
    }

    private void H0() {
        d[] dVarArrA = this.R.a();
        if (dVarArrA == null || dVarArrA.length == 0) {
            return;
        }
        Arrays.sort(dVarArrA);
        int iC = dVarArrA[0].c();
        int i = iC == 0 ? 0 : this.P / iC;
        for (d dVar : dVarArrA) {
            dVar.h(dVar.c() * i);
        }
    }

    private void I0(f fVar, boolean z) {
        View viewM = fVar.M(R$id.bar_views_container);
        TextView textView = (TextView) fVar.M(R$id.empty_view);
        int iD = this.R.d();
        if (iD != 0) {
            textView.setText(iD);
        }
        textView.setVisibility(z ? 0 : 8);
        viewM.setVisibility(z ? 8 : 0);
    }

    private void J0(f fVar) {
        H0();
        d[] dVarArrA = this.R.a();
        for (int i = 0; i < 4; i++) {
            BarView barView = (BarView) fVar.M(S[i]);
            if (dVarArrA == null || i >= dVarArrA.length) {
                barView.setVisibility(8);
            } else {
                barView.setVisibility(0);
                barView.b(dVarArrA[i]);
            }
        }
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        fVar.Q(true);
        fVar.R(true);
        F0(fVar);
        E0(fVar);
        if (this.Q) {
            fVar.f1092a.setVisibility(4);
            return;
        }
        fVar.f1092a.setVisibility(0);
        d[] dVarArrA = this.R.a();
        if (dVarArrA == null || dVarArrA.length == 0) {
            I0(fVar, true);
        } else {
            I0(fVar, false);
            J0(fVar);
        }
    }
}
