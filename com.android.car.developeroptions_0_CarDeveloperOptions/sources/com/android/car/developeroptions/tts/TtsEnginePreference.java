package com.android.car.developeroptions.tts;

import android.content.Context;
import android.content.DialogInterface;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class TtsEnginePreference extends Preference {
    private final TextToSpeech.EngineInfo mEngineInfo;
    private volatile boolean mPreventRadioButtonCallbacks;
    private final CompoundButton.OnCheckedChangeListener mRadioChangeListener;
    private final RadioButtonGroupState mSharedState;

    public interface RadioButtonGroupState {
        Checkable getCurrentChecked();

        String getCurrentKey();

        void setCurrentChecked(Checkable checkable);

        void setCurrentKey(String str);
    }

    public TtsEnginePreference(Context context, TextToSpeech.EngineInfo engineInfo, RadioButtonGroupState radioButtonGroupState) {
        super(context);
        this.mRadioChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.developeroptions.tts.TtsEnginePreference.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TtsEnginePreference.this.onRadioButtonClicked(compoundButton, z);
            }
        };
        setWidgetLayoutResource(R.layout.preference_widget_radiobutton);
        setLayoutResource(R.layout.preference_radio);
        setIconSpaceReserved(false);
        this.mSharedState = radioButtonGroupState;
        this.mEngineInfo = engineInfo;
        this.mPreventRadioButtonCallbacks = false;
        setKey(this.mEngineInfo.name);
        setTitle(this.mEngineInfo.label);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mSharedState == null) {
            throw new IllegalStateException("Call to getView() before a call tosetSharedState()");
        }
        RadioButton radioButton = (RadioButton) preferenceViewHolder.itemView.findViewById(android.R.id.checkbox);
        radioButton.setOnCheckedChangeListener(this.mRadioChangeListener);
        boolean zEquals = getKey().equals(this.mSharedState.getCurrentKey());
        if (zEquals) {
            this.mSharedState.setCurrentChecked(radioButton);
        }
        this.mPreventRadioButtonCallbacks = true;
        radioButton.setChecked(zEquals);
        this.mPreventRadioButtonCallbacks = false;
    }

    private boolean shouldDisplayDataAlert() {
        return !this.mEngineInfo.system;
    }

    private void displayDataAlert(DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        Log.i("TtsEnginePreference", "Displaying data alert for :" + this.mEngineInfo.name);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(android.R.string.dialog_alert_title);
        builder.setMessage(getContext().getString(R.string.tts_engine_security_warning, this.mEngineInfo.label));
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, onClickListener);
        builder.setNegativeButton(android.R.string.cancel, onClickListener2);
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRadioButtonClicked(final CompoundButton compoundButton, boolean z) {
        if (this.mPreventRadioButtonCallbacks || this.mSharedState.getCurrentChecked() == compoundButton || !z) {
            return;
        }
        if (shouldDisplayDataAlert()) {
            displayDataAlert(new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.tts.TtsEnginePreference.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    TtsEnginePreference.this.makeCurrentEngine(compoundButton);
                }
            }, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.tts.TtsEnginePreference.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    compoundButton.setChecked(false);
                }
            });
        } else {
            makeCurrentEngine(compoundButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeCurrentEngine(Checkable checkable) {
        if (this.mSharedState.getCurrentChecked() != null) {
            this.mSharedState.getCurrentChecked().setChecked(false);
        }
        this.mSharedState.setCurrentChecked(checkable);
        this.mSharedState.setCurrentKey(getKey());
        callChangeListener(this.mSharedState.getCurrentKey());
    }
}
