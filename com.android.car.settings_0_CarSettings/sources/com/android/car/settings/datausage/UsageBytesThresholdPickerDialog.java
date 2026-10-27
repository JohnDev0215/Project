package com.android.car.settings.datausage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiDialogFragment;
import com.android.launcher3.icons.cache.BaseIconCache;

/* JADX INFO: loaded from: classes5.dex */
public class UsageBytesThresholdPickerDialog extends CarUiDialogFragment {
    private static final String ARG_CURRENT_THRESHOLD = "arg_current_threshold";
    private static final String ARG_DIALOG_TITLE_RES = "arg_dialog_title_res";
    static final long GIB_IN_BYTES = 1073741824;
    static final long MAX_DATA_LIMIT_BYTES = 53687091200000L;
    private static final float MB_GB_SUFFIX_THRESHOLD = 1.5f;
    static final long MIB_IN_BYTES = 1048576;
    public static final String TAG = "UsageBytesThresholdPickerDialog";
    private BytesThresholdPickedListener mBytesThresholdPickedListener;
    private NumberPicker mBytesUnits;
    private long mCurrentThreshold;
    private View mDownArrow;
    private EditText mThresholdEditor;
    private View mUpArrow;

    public interface BytesThresholdPickedListener {
        void onThresholdPicked(long j);
    }

    public static UsageBytesThresholdPickerDialog newInstance(int dialogTitle, long currentThreshold) {
        UsageBytesThresholdPickerDialog dialog = new UsageBytesThresholdPickerDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_DIALOG_TITLE_RES, dialogTitle);
        args.putLong(ARG_CURRENT_THRESHOLD, currentThreshold);
        dialog.setArguments(args);
        return dialog;
    }

    public void setBytesThresholdPickedListener(BytesThresholdPickedListener bytesThresholdPickedListener) {
        this.mBytesThresholdPickedListener = bytesThresholdPickedListener;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(builder.getContext());
        View view = inflater.inflate(R.layout.usage_bytes_threshold_picker, (ViewGroup) null, false);
        long j = getArguments().getLong(ARG_CURRENT_THRESHOLD);
        this.mCurrentThreshold = j;
        if (j < 0) {
            this.mCurrentThreshold = 0L;
        }
        String[] units = getContext().getResources().getStringArray(R.array.bytes_picker_sizes);
        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.bytes_units);
        this.mBytesUnits = numberPicker;
        numberPicker.setMinValue(0);
        this.mBytesUnits.setMaxValue(units.length - 1);
        this.mBytesUnits.setDisplayedValues(units);
        this.mThresholdEditor = (EditText) view.findViewById(R.id.bytes_threshold);
        View viewFindViewById = view.findViewById(R.id.up_arrow_container);
        this.mUpArrow = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.datausage.-$$Lambda$UsageBytesThresholdPickerDialog$19SiqQDMZmrH_ulUZ2JSYCN4SqM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateDialog$0$UsageBytesThresholdPickerDialog(view2);
            }
        });
        View viewFindViewById2 = view.findViewById(R.id.down_arrow_container);
        this.mDownArrow = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.datausage.-$$Lambda$UsageBytesThresholdPickerDialog$5IJrLlng340u7oENCEMsefWshNA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateDialog$1$UsageBytesThresholdPickerDialog(view2);
            }
        });
        updateCurrentView(this.mCurrentThreshold);
        return builder.setTitle(getArguments().getInt(ARG_DIALOG_TITLE_RES)).setView(view).setPositiveButton(R.string.usage_bytes_threshold_picker_positive_button, this).create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$UsageBytesThresholdPickerDialog(View v) {
        this.mBytesUnits.setValue(r0.getValue() - 1);
    }

    public /* synthetic */ void lambda$onCreateDialog$1$UsageBytesThresholdPickerDialog(View v) {
        NumberPicker numberPicker = this.mBytesUnits;
        numberPicker.setValue(numberPicker.getValue() + 1);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            long newThreshold = getCurrentThreshold();
            BytesThresholdPickedListener bytesThresholdPickedListener = this.mBytesThresholdPickedListener;
            if (bytesThresholdPickedListener != null && this.mCurrentThreshold != newThreshold) {
                bytesThresholdPickedListener.onThresholdPicked(newThreshold);
            }
        }
    }

    public long getCurrentThreshold() {
        String bytesString = this.mThresholdEditor.getText().toString();
        if (bytesString.isEmpty() || bytesString.equals(BaseIconCache.EMPTY_CLASS_NAME)) {
            bytesString = "0";
        }
        long bytes = (long) (Float.valueOf(bytesString).floatValue() * (this.mBytesUnits.getValue() == 0 ? 1048576L : GIB_IN_BYTES));
        long correctedBytes = Math.min(MAX_DATA_LIMIT_BYTES, bytes);
        return correctedBytes;
    }

    void setThresholdEditor(long threshold) {
        updateCurrentView(threshold);
    }

    private void updateCurrentView(long threshold) {
        String bytesText;
        if (threshold > 1.6106127E9f) {
            bytesText = formatText(threshold / 1.0737418E9f);
            this.mBytesUnits.setValue(1);
        } else {
            bytesText = formatText(threshold / 1048576.0f);
            this.mBytesUnits.setValue(0);
        }
        this.mThresholdEditor.setText(bytesText);
        this.mThresholdEditor.setSelection(0, bytesText.length());
    }

    private String formatText(float v) {
        return String.valueOf(Math.round(v * 100.0f) / 100.0f);
    }
}
