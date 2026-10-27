package androidx.preference;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class DropDownPreference extends ListPreference {
    private final Context a0;
    private final ArrayAdapter b0;
    private Spinner c0;
    private final AdapterView.OnItemSelectedListener d0;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements AdapterView.OnItemSelectedListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i >= 0) {
                String string = DropDownPreference.this.N0()[i].toString();
                if (string.equals(DropDownPreference.this.O0()) || !DropDownPreference.this.d(string)) {
                    return;
                }
                DropDownPreference.this.Q0(string);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.dropdownPreferenceStyle);
    }

    private int S0(String str) {
        CharSequence[] charSequenceArrN0 = N0();
        if (str == null || charSequenceArrN0 == null) {
            return -1;
        }
        for (int length = charSequenceArrN0.length - 1; length >= 0; length--) {
            if (TextUtils.equals(charSequenceArrN0[length].toString(), str)) {
                return length;
            }
        }
        return -1;
    }

    private void T0() {
        this.b0.clear();
        if (L0() != null) {
            for (CharSequence charSequence : L0()) {
                this.b0.add(charSequence.toString());
            }
        }
    }

    @Override // androidx.preference.Preference
    protected void I() {
        super.I();
        ArrayAdapter arrayAdapter = this.b0;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        Spinner spinner = (Spinner) fVar.f1092a.findViewById(R$id.spinner);
        this.c0 = spinner;
        spinner.setAdapter((SpinnerAdapter) this.b0);
        this.c0.setOnItemSelectedListener(this.d0);
        this.c0.setSelection(S0(O0()));
        super.M(fVar);
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected void N() {
        this.c0.performClick();
    }

    protected ArrayAdapter R0() {
        return new ArrayAdapter(this.a0, R.layout.simple_spinner_dropdown_item);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.d0 = new a();
        this.a0 = context;
        this.b0 = R0();
        T0();
    }
}
