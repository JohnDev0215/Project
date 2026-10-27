package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.datausage.CycleAdapter;
import com.android.settingslib.widget.settingsspinner.SettingsSpinner;

/* JADX INFO: loaded from: classes.dex */
public class SpinnerPreference extends Preference implements CycleAdapter.SpinnerInterface {
    private CycleAdapter mAdapter;
    private Object mCurrentObject;
    private AdapterView.OnItemSelectedListener mListener;
    private final AdapterView.OnItemSelectedListener mOnSelectedListener;
    private int mPosition;

    public SpinnerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.android.car.developeroptions.datausage.SpinnerPreference.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (SpinnerPreference.this.mPosition == i) {
                    return;
                }
                SpinnerPreference.this.mPosition = i;
                SpinnerPreference spinnerPreference = SpinnerPreference.this;
                spinnerPreference.mCurrentObject = spinnerPreference.mAdapter.getItem(i);
                SpinnerPreference.this.mListener.onItemSelected(adapterView, view, i, j);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                SpinnerPreference.this.mListener.onNothingSelected(adapterView);
            }
        };
        setLayoutResource(R.layout.data_usage_cycles);
    }

    @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
    public void setAdapter(CycleAdapter cycleAdapter) {
        this.mAdapter = cycleAdapter;
        notifyChanged();
    }

    @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mListener = onItemSelectedListener;
    }

    @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
    public Object getSelectedItem() {
        return this.mCurrentObject;
    }

    @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
    public void setSelection(int i) {
        this.mPosition = i;
        this.mCurrentObject = this.mAdapter.getItem(i);
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        SettingsSpinner settingsSpinner = (SettingsSpinner) preferenceViewHolder.findViewById(R.id.cycles_spinner);
        settingsSpinner.setAdapter((SpinnerAdapter) this.mAdapter);
        settingsSpinner.setSelection(this.mPosition);
        settingsSpinner.setOnItemSelectedListener(this.mOnSelectedListener);
    }

    @Override // androidx.preference.Preference
    protected void performClick(View view) {
        view.findViewById(R.id.cycles_spinner).performClick();
    }
}
