package com.android.car.ui.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.DialogPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;
import com.android.car.ui.utils.ViewUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiSeekBarDialogPreference extends DialogPreference implements DialogFragmentCallbacks, UxRestrictablePreference {
    private int mMaxProgress;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private Consumer<Preference> mRestrictedClickListener;
    private SeekBar mSeekBar;
    private String mSeekBarLeftText;
    private TextView mSeekBarLeftTextView;
    private int mSeekBarLeftTextViewVisibility;
    private int mSeekBarProgress;
    private String mSeekBarRightText;
    private TextView mSeekBarRightTextView;
    private int mSeekBarRightTextViewVisibility;
    private String mSeekBarTopText;
    private TextView mSeekBarTopTextView;
    private int mSeekBarTopTextViewVisibility;
    private boolean mUxRestricted;

    public CarUiSeekBarDialogPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mMaxProgress = 100;
        this.mUxRestricted = false;
        init();
    }

    public CarUiSeekBarDialogPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMaxProgress = 100;
        this.mUxRestricted = false;
        init();
    }

    public CarUiSeekBarDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMaxProgress = 100;
        this.mUxRestricted = false;
        init();
    }

    public CarUiSeekBarDialogPreference(Context context) {
        super(context);
        this.mMaxProgress = 100;
        this.mUxRestricted = false;
        init();
    }

    private void init() {
        setDialogLayoutResource(R.layout.car_ui_seekbar_dialog);
        setPositiveButtonText(R.string.car_ui_dialog_preference_positive);
        setNegativeButtonText(R.string.car_ui_dialog_preference_negative);
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mSeekBarProgress = getPersistedInt(0);
    }

    @Override // com.android.car.ui.preference.DialogFragmentCallbacks
    public void onBindDialogView(View view) {
        this.mSeekBar = (SeekBar) CarUiUtils.findViewByRefId(view, R.id.seek_bar);
        this.mSeekBarTopTextView = (TextView) CarUiUtils.findViewByRefId(view, R.id.seek_bar_text_top);
        this.mSeekBarLeftTextView = (TextView) CarUiUtils.findViewByRefId(view, R.id.seek_bar_text_left);
        this.mSeekBarRightTextView = (TextView) CarUiUtils.findViewByRefId(view, R.id.seek_bar_text_right);
        setProgress(this.mSeekBarProgress);
        setSeekBarTopTextViewVisibility(this.mSeekBarTopTextViewVisibility);
        setSeekBarTopTextViewText(this.mSeekBarTopText);
        setSeekBarLeftTextViewVisibility(this.mSeekBarLeftTextViewVisibility);
        setSeekBarLeftTextViewText(this.mSeekBarLeftText);
        setSeekBarRightTextViewVisibility(this.mSeekBarRightTextViewVisibility);
        setSeekBarRightTextViewText(this.mSeekBarRightText);
        setMaxProgress(this.mMaxProgress);
        setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
    }

    public int getProgress() {
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            return seekBar.getProgress();
        }
        return this.mSeekBarProgress;
    }

    public void setProgress(int progress) {
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            seekBar.setProgress(progress);
        }
        this.mSeekBarProgress = progress;
    }

    @Override // com.android.car.ui.preference.DialogFragmentCallbacks
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            int progress = this.mSeekBar.getProgress();
            this.mSeekBarProgress = progress;
            persistInt(progress);
            notifyChanged();
        }
        this.mSeekBarTopTextView = null;
        this.mSeekBarRightTextView = null;
        this.mSeekBarLeftTextView = null;
        this.mSeekBar = null;
    }

    public void setSeekBarTopTextViewVisibility(int visibility) {
        TextView textView = this.mSeekBarTopTextView;
        if (textView != null) {
            textView.setVisibility(visibility);
        }
        this.mSeekBarTopTextViewVisibility = visibility;
    }

    public String getSeekBarTopTextViewText() {
        TextView textView = this.mSeekBarTopTextView;
        if (textView != null) {
            return textView.getText().toString();
        }
        return this.mSeekBarTopText;
    }

    public void setSeekBarTopTextViewText(String text) {
        TextView textView = this.mSeekBarTopTextView;
        if (textView != null) {
            textView.setText(text);
        }
        this.mSeekBarTopText = text;
    }

    public void setSeekBarLeftTextViewVisibility(int visibility) {
        TextView textView = this.mSeekBarLeftTextView;
        if (textView != null) {
            textView.setVisibility(visibility);
        }
        this.mSeekBarLeftTextViewVisibility = visibility;
    }

    public String getSeekBarLeftTextViewText() {
        TextView textView = this.mSeekBarLeftTextView;
        if (textView != null) {
            return textView.getText().toString();
        }
        return this.mSeekBarLeftText;
    }

    public void setSeekBarLeftTextViewText(String text) {
        TextView textView = this.mSeekBarLeftTextView;
        if (textView != null) {
            textView.setText(text);
        }
        this.mSeekBarLeftText = text;
    }

    public void setSeekBarRightTextViewVisibility(int visibility) {
        TextView textView = this.mSeekBarRightTextView;
        if (textView != null) {
            textView.setVisibility(visibility);
        }
        this.mSeekBarRightTextViewVisibility = visibility;
    }

    public String getSeekBarRightTextViewText() {
        TextView textView = this.mSeekBarRightTextView;
        if (textView != null) {
            return textView.getText().toString();
        }
        return this.mSeekBarRightText;
    }

    public void setSeekBarRightTextViewText(String text) {
        TextView textView = this.mSeekBarRightTextView;
        if (textView != null) {
            textView.setText(text);
        }
        this.mSeekBarRightText = text;
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(listener);
        }
        this.mOnSeekBarChangeListener = listener;
    }

    public int getMaxProgress() {
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            return seekBar.getMax();
        }
        return this.mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            seekBar.setMax(maxProgress);
        }
        this.mMaxProgress = maxProgress;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        ViewUtils.makeAllViewsUxRestricted(holder.itemView, isUxRestricted());
    }

    @Override // androidx.preference.Preference
    public void performClick() {
        if ((isEnabled() || isSelectable()) && isUxRestricted()) {
            Consumer<Preference> consumer = this.mRestrictedClickListener;
            if (consumer != null) {
                consumer.accept(this);
                return;
            }
            return;
        }
        super.performClick();
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public void setUxRestricted(boolean restricted) {
        if (restricted != this.mUxRestricted) {
            this.mUxRestricted = restricted;
            notifyChanged();
        }
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public boolean isUxRestricted() {
        return this.mUxRestricted;
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public void setOnClickWhileRestrictedListener(Consumer<Preference> listener) {
        this.mRestrictedClickListener = listener;
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public Consumer<Preference> getOnClickWhileRestrictedListener() {
        return this.mRestrictedClickListener;
    }
}
