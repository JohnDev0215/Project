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
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
public class DropDownPreference extends ListPreference {
    public final ArrayAdapter d0;
    public Spinner e0;
    public final a f0;

    public DropDownPreference(Context context) {
        this(context, null);
    }

    @Override // androidx.preference.ListPreference
    public final void a0(CharSequence[] charSequenceArr) {
        this.Y = charSequenceArr;
        ArrayAdapter arrayAdapter = this.d0;
        arrayAdapter.clear();
        CharSequence[] charSequenceArr2 = this.Y;
        if (charSequenceArr2 != null) {
            for (CharSequence charSequence : charSequenceArr2) {
                arrayAdapter.add(charSequence.toString());
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void s() {
        super.s();
        ArrayAdapter arrayAdapter = this.d0;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        Spinner spinner = (Spinner) n32Var.e.findViewById(R$id.spinner);
        this.e0 = spinner;
        spinner.setAdapter((SpinnerAdapter) this.d0);
        this.e0.setOnItemSelectedListener(this.f0);
        Spinner spinner2 = this.e0;
        String str = this.a0;
        CharSequence[] charSequenceArr = this.Z;
        int i = -1;
        if (str != null && charSequenceArr != null) {
            int length = charSequenceArr.length - 1;
            while (true) {
                if (length < 0) {
                    break;
                }
                if (TextUtils.equals(charSequenceArr[length].toString(), str)) {
                    i = length;
                    break;
                }
                length--;
            }
        }
        spinner2.setSelection(i);
        super.y(n32Var);
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    public final void z() {
        this.e0.performClick();
    }

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.dropdownPreferenceStyle);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DropDownPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, 0);
        this.f0 = new a();
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner_dropdown_item);
        this.d0 = arrayAdapter;
        arrayAdapter.clear();
        CharSequence[] charSequenceArr = this.Y;
        if (charSequenceArr != null) {
            for (CharSequence charSequence : charSequenceArr) {
                arrayAdapter.add(charSequence.toString());
            }
        }
    }

    public class a implements AdapterView.OnItemSelectedListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i >= 0) {
                DropDownPreference dropDownPreference = DropDownPreference.this;
                String string = dropDownPreference.Z[i].toString();
                if (string.equals(dropDownPreference.a0) || !dropDownPreference.h(string)) {
                    return;
                }
                dropDownPreference.b0(string);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
