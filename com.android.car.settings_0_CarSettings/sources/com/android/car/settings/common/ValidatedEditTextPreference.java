package com.android.car.settings.common;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.preference.CarUiEditTextPreference;

/* JADX INFO: loaded from: classes5.dex */
public class ValidatedEditTextPreference extends CarUiEditTextPreference {
    private int mInputType;
    private Validator mValidator;

    public interface Validator {
        boolean isTextValid(String str);
    }

    public ValidatedEditTextPreference(Context context) {
        super(context);
    }

    public ValidatedEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ValidatedEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ValidatedEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSummaryInputType(int inputType) {
        this.mInputType = inputType;
        notifyChanged();
    }

    public int getSummaryInputType() {
        return this.mInputType;
    }

    public void setValidator(Validator validator) {
        this.mValidator = validator;
    }

    public Validator getValidator() {
        return this.mValidator;
    }

    @Override // com.android.car.ui.preference.CarUiEditTextPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        TextView summaryView = (TextView) holder.findViewById(R.id.summary);
        summaryView.setInputType(this.mInputType);
    }
}
