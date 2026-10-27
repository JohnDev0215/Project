package com.android.car.settings.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiPreference;
import com.android.car.ui.utils.DirectManipulationHelper;

/* JADX INFO: loaded from: classes5.dex */
public class SeekBarPreference extends CarUiPreference {
    private static final String TAG = "SeekBarPreference";
    private boolean mAdjustable;
    private boolean mContinuousUpdate;
    private boolean mInDirectManipulationMode;
    private int mMax;
    private int mMin;
    private SeekBar mSeekBar;
    private final SeekBar.OnSeekBarChangeListener mSeekBarChangeListener;
    private final View.OnFocusChangeListener mSeekBarFocusChangeListener;
    private int mSeekBarIncrement;
    private final View.OnKeyListener mSeekBarKeyListener;
    private final View.OnGenericMotionListener mSeekBarScrollListener;
    private int mSeekBarValue;
    private TextView mSeekBarValueTextView;
    private boolean mShowSeekBarValue;
    private boolean mTrackingTouch;

    public /* synthetic */ void lambda$new$0$SeekBarPreference(View v, boolean hasFocus) {
        if (!hasFocus && this.mInDirectManipulationMode && this.mSeekBar != null) {
            setInDirectManipulationMode(v, false);
        }
    }

    public /* synthetic */ boolean lambda$new$1$SeekBarPreference(View v, MotionEvent event) {
        int adjustment;
        if (!this.mInDirectManipulationMode || !this.mAdjustable || this.mSeekBar == null || (adjustment = Math.round(event.getAxisValue(26))) == 0) {
            return false;
        }
        int count = Math.abs(adjustment);
        int keyCode = adjustment < 0 ? 21 : 22;
        int i = keyCode;
        KeyEvent downEvent = new KeyEvent(event.getDownTime(), event.getEventTime(), 0, i, 0);
        KeyEvent upEvent = new KeyEvent(event.getDownTime(), event.getEventTime(), 1, i, 0);
        for (int i2 = 0; i2 < count; i2++) {
            this.mSeekBar.onKeyDown(keyCode, downEvent);
            this.mSeekBar.onKeyUp(keyCode, upEvent);
        }
        return true;
    }

    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.android.car.settings.common.SeekBarPreference.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (SeekBarPreference.this.mContinuousUpdate || !SeekBarPreference.this.mTrackingTouch) {
                        SeekBarPreference.this.syncValueInternal(seekBar);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                SeekBarPreference.this.mTrackingTouch = true;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                SeekBarPreference.this.mTrackingTouch = false;
                if (seekBar.getProgress() + SeekBarPreference.this.mMin != SeekBarPreference.this.mSeekBarValue) {
                    SeekBarPreference.this.syncValueInternal(seekBar);
                }
            }
        };
        this.mSeekBarKeyListener = new View.OnKeyListener() { // from class: com.android.car.settings.common.SeekBarPreference.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (SeekBarPreference.this.mSeekBar == null || !SeekBarPreference.this.mAdjustable) {
                    return false;
                }
                if (SeekBarPreference.this.mInDirectManipulationMode && (keyCode == 21 || keyCode == 22 || keyCode == 19 || keyCode == 20)) {
                    return true;
                }
                if (keyCode == 23) {
                    if (event.getAction() == 0) {
                        SeekBarPreference.this.setInDirectManipulationMode(v, !r0.mInDirectManipulationMode);
                    }
                    return true;
                }
                if (keyCode == 4 && SeekBarPreference.this.mInDirectManipulationMode) {
                    if (event.getAction() == 0) {
                        SeekBarPreference.this.setInDirectManipulationMode(v, false);
                    }
                    return true;
                }
                if (KeyEvent.isConfirmKey(keyCode)) {
                    return false;
                }
                return event.getAction() == 0 ? SeekBarPreference.this.mSeekBar.onKeyDown(keyCode, event) : SeekBarPreference.this.mSeekBar.onKeyUp(keyCode, event);
            }
        };
        this.mSeekBarFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.android.car.settings.common.-$$Lambda$SeekBarPreference$OAUpEJGcFZ7GKz-VDfALQAoIYhg
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                this.f$0.lambda$new$0$SeekBarPreference(view, z);
            }
        };
        this.mSeekBarScrollListener = new View.OnGenericMotionListener() { // from class: com.android.car.settings.common.-$$Lambda$SeekBarPreference$vBMqpdX6ExFOMnO8EARqGdCQET4
            @Override // android.view.View.OnGenericMotionListener
            public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$new$1$SeekBarPreference(view, motionEvent);
            }
        };
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SeekBarPreference, defStyleAttr, defStyleRes);
        this.mMin = a.getInt(R.styleable.SeekBarPreference_min, 0);
        setMax(a.getInt(R.styleable.SeekBarPreference_android_max, 100));
        setSeekBarIncrement(a.getInt(R.styleable.SeekBarPreference_seekBarIncrement, 0));
        this.mAdjustable = a.getBoolean(R.styleable.SeekBarPreference_adjustable, true);
        this.mShowSeekBarValue = a.getBoolean(R.styleable.SeekBarPreference_showSeekBarValue, true);
        a.recycle();
    }

    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.seekBarPreferenceStyle);
    }

    public SeekBarPreference(Context context) {
        this(context, null);
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        view.itemView.setOnKeyListener(this.mSeekBarKeyListener);
        view.itemView.setOnFocusChangeListener(this.mSeekBarFocusChangeListener);
        view.itemView.setOnGenericMotionListener(this.mSeekBarScrollListener);
        this.mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        TextView textView = (TextView) view.findViewById(R.id.seekbar_value);
        this.mSeekBarValueTextView = textView;
        if (this.mShowSeekBarValue) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
            this.mSeekBarValueTextView = null;
        }
        SeekBar seekBar = this.mSeekBar;
        if (seekBar == null) {
            Log.e(TAG, "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seekBar.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
        this.mSeekBar.setMax(this.mMax - this.mMin);
        int i = this.mSeekBarIncrement;
        if (i != 0) {
            this.mSeekBar.setKeyProgressIncrement(i);
        } else {
            this.mSeekBarIncrement = this.mSeekBar.getKeyProgressIncrement();
        }
        this.mSeekBar.setProgress(this.mSeekBarValue - this.mMin);
        TextView textView2 = this.mSeekBarValueTextView;
        if (textView2 != null) {
            textView2.setText(String.valueOf(this.mSeekBarValue));
        }
        this.mSeekBar.setEnabled(isEnabled());
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setValue(restoreValue ? getPersistedInt(this.mSeekBarValue) : ((Integer) defaultValue).intValue());
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Integer.valueOf(a.getInt(index, 0));
    }

    public void setMin(int min) {
        if (min > this.mMax) {
            min = this.mMax;
        }
        if (min != this.mMin) {
            this.mMin = min;
            notifyChanged();
        }
    }

    public int getMin() {
        return this.mMin;
    }

    public final void setMax(int max) {
        if (max < this.mMin) {
            max = this.mMin;
        }
        if (max != this.mMax) {
            this.mMax = max;
            notifyChanged();
        }
    }

    public final int getSeekBarIncrement() {
        return this.mSeekBarIncrement;
    }

    public final void setSeekBarIncrement(int seekBarIncrement) {
        if (seekBarIncrement != this.mSeekBarIncrement) {
            this.mSeekBarIncrement = Math.min(this.mMax - this.mMin, Math.abs(seekBarIncrement));
            notifyChanged();
        }
    }

    public int getMax() {
        return this.mMax;
    }

    public void setAdjustable(boolean adjustable) {
        this.mAdjustable = adjustable;
    }

    public boolean isAdjustable() {
        return this.mAdjustable;
    }

    public void setContinuousUpdate(boolean continuousUpdate) {
        this.mContinuousUpdate = continuousUpdate;
    }

    public void setShowSeekBarValue(boolean showSeekBarValue) {
        this.mShowSeekBarValue = showSeekBarValue;
    }

    public void setValue(int seekBarValue) {
        setValueInternal(seekBarValue, true);
    }

    private void setValueInternal(int seekBarValue, boolean notifyChanged) {
        if (seekBarValue < this.mMin) {
            seekBarValue = this.mMin;
        }
        if (seekBarValue > this.mMax) {
            seekBarValue = this.mMax;
        }
        if (seekBarValue != this.mSeekBarValue) {
            this.mSeekBarValue = seekBarValue;
            TextView textView = this.mSeekBarValueTextView;
            if (textView != null) {
                textView.setText(String.valueOf(seekBarValue));
            }
            persistInt(seekBarValue);
            if (notifyChanged) {
                notifyChanged();
            }
        }
    }

    public int getValue() {
        return this.mSeekBarValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncValueInternal(SeekBar seekBar) {
        int seekBarValue = this.mMin + seekBar.getProgress();
        if (seekBarValue != this.mSeekBarValue) {
            if (callChangeListener(Integer.valueOf(seekBarValue))) {
                setValueInternal(seekBarValue, false);
            } else {
                seekBar.setProgress(this.mSeekBarValue - this.mMin);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInDirectManipulationMode(View view, boolean enable) {
        this.mInDirectManipulationMode = enable;
        DirectManipulationHelper.enableDirectManipulationMode(this.mSeekBar, enable);
        view.setSelected(enable);
        this.mSeekBar.setSelected(enable);
    }

    @Override // androidx.preference.Preference
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            return superState;
        }
        SavedState myState = new SavedState(superState);
        myState.mSeekBarValue = this.mSeekBarValue;
        myState.mMin = this.mMin;
        myState.mMax = this.mMax;
        return myState;
    }

    @Override // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable state) {
        if (!state.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());
        this.mSeekBarValue = myState.mSeekBarValue;
        this.mMin = myState.mMin;
        this.mMax = myState.mMax;
        notifyChanged();
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.android.car.settings.common.SeekBarPreference.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int mMax;
        int mMin;
        int mSeekBarValue;

        SavedState(Parcel source) {
            super(source);
            this.mSeekBarValue = source.readInt();
            this.mMin = source.readInt();
            this.mMax = source.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.mSeekBarValue);
            dest.writeInt(this.mMin);
            dest.writeInt(this.mMax);
        }

        SavedState(Parcelable superState) {
            super(superState);
        }
    }
}
