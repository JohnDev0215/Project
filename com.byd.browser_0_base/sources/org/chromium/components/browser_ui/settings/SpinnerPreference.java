package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class SpinnerPreference extends Preference {
    public Spinner S;
    public ArrayAdapter<Object> T;
    public int U;
    public final boolean V;

    public SpinnerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SpinnerPreference);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SpinnerPreference_singleLine, false);
        this.V = z;
        typedArrayObtainStyledAttributes.recycle();
        if (z) {
            this.f17J = R$layout.preference_spinner_single_line;
        } else {
            this.f17J = R$layout.preference_spinner;
        }
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        ((TextView) n32Var.u(R$id.title)).setText(this.l);
        Spinner spinner = (Spinner) n32Var.u(R$id.spinner);
        this.S = spinner;
        spinner.setDropDownHorizontalOffset(-this.e.getResources().getDimensionPixelOffset(R$dimen.settings_spinner_padding_start2));
        this.S.setOnItemSelectedListener(new a());
        SpinnerAdapter adapter = this.S.getAdapter();
        ArrayAdapter<Object> arrayAdapter = this.T;
        if (adapter != arrayAdapter) {
            this.S.setAdapter((SpinnerAdapter) arrayAdapter);
            this.T.getItem(this.U);
        }
        this.S.setSelection(this.U);
    }

    public class a implements AdapterView.OnItemSelectedListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SpinnerPreference spinnerPreference = SpinnerPreference.this;
            spinnerPreference.U = i;
            Preference.c cVar = spinnerPreference.i;
            if (cVar != null) {
                Spinner spinner = spinnerPreference.S;
                cVar.n(spinnerPreference, spinner == null ? spinnerPreference.T.getItem(i) : spinner.getSelectedItem());
            }
            adapterView.getItemAtPosition(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
