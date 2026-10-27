package com.android.inputmethod.latin.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public final class SeekBarDialogPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener {
    private final int mMaxValue;
    private final int mMinValue;
    private SeekBar mSeekBar;
    private final int mStepValue;
    private ValueProxy mValueProxy;
    private TextView mValueView;

    public interface ValueProxy {
        void feedbackValue(final int value);

        String getValueText(final int value);

        int readDefaultValue(final String key);

        int readValue(final String key);

        void writeDefaultValue(final String key);

        void writeValue(final int value, final String key);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(final SeekBar seekBar) {
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Preference another) {
        return super.compareTo(another);
    }

    public SeekBarDialogPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SeekBarDialogPreference, 0, 0);
        this.mMaxValue = typedArrayObtainStyledAttributes.getInt(0, 0);
        this.mMinValue = typedArrayObtainStyledAttributes.getInt(1, 0);
        this.mStepValue = typedArrayObtainStyledAttributes.getInt(2, 0);
        typedArrayObtainStyledAttributes.recycle();
        setDialogLayoutResource(R.layout.seek_bar_dialog);
    }

    public void setInterface(final ValueProxy proxy) {
        this.mValueProxy = proxy;
        setSummary(this.mValueProxy.getValueText(proxy.readValue(getKey())));
    }

    @Override // android.preference.DialogPreference
    protected View onCreateDialogView() {
        View viewOnCreateDialogView = super.onCreateDialogView();
        SeekBar seekBar = (SeekBar) viewOnCreateDialogView.findViewById(R.id.seek_bar_dialog_bar);
        this.mSeekBar = seekBar;
        seekBar.setMax(this.mMaxValue - this.mMinValue);
        this.mSeekBar.setOnSeekBarChangeListener(this);
        this.mValueView = (TextView) viewOnCreateDialogView.findViewById(R.id.seek_bar_dialog_value);
        return viewOnCreateDialogView;
    }

    private int getProgressFromValue(final int value) {
        return value - this.mMinValue;
    }

    private int getValueFromProgress(final int progress) {
        return progress + this.mMinValue;
    }

    private int clipValue(final int value) {
        int iMin = Math.min(this.mMaxValue, Math.max(this.mMinValue, value));
        int i = this.mStepValue;
        return i <= 1 ? iMin : iMin - (iMin % i);
    }

    private int getClippedValueFromProgress(final int progress) {
        return clipValue(getValueFromProgress(progress));
    }

    @Override // android.preference.DialogPreference
    protected void onBindDialogView(final View view) {
        int value = this.mValueProxy.readValue(getKey());
        this.mValueView.setText(this.mValueProxy.getValueText(value));
        this.mSeekBar.setProgress(getProgressFromValue(clipValue(value)));
    }

    @Override // android.preference.DialogPreference
    protected void onPrepareDialogBuilder(final AlertDialog.Builder builder) {
        builder.setPositiveButton(android.R.string.ok, this).setNegativeButton(android.R.string.cancel, this).setNeutralButton(R.string.button_default, this);
    }

    @Override // android.preference.DialogPreference, android.content.DialogInterface.OnClickListener
    public void onClick(final DialogInterface dialog, final int which) {
        super.onClick(dialog, which);
        String key = getKey();
        if (which == -3) {
            setSummary(this.mValueProxy.getValueText(this.mValueProxy.readDefaultValue(key)));
            this.mValueProxy.writeDefaultValue(key);
        } else if (which == -1) {
            int clippedValueFromProgress = getClippedValueFromProgress(this.mSeekBar.getProgress());
            setSummary(this.mValueProxy.getValueText(clippedValueFromProgress));
            this.mValueProxy.writeValue(clippedValueFromProgress, key);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
        int clippedValueFromProgress = getClippedValueFromProgress(progress);
        this.mValueView.setText(this.mValueProxy.getValueText(clippedValueFromProgress));
        if (fromUser) {
            return;
        }
        this.mSeekBar.setProgress(getProgressFromValue(clippedValueFromProgress));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(final SeekBar seekBar) {
        this.mValueProxy.feedbackValue(getClippedValueFromProgress(seekBar.getProgress()));
    }
}
