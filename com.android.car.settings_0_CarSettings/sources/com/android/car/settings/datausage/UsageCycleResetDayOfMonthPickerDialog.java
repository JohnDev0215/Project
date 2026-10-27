package com.android.car.settings.datausage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiDialogFragment;

/* JADX INFO: loaded from: classes5.dex */
public class UsageCycleResetDayOfMonthPickerDialog extends CarUiDialogFragment {
    private static final String ARG_SELECTED_DAY_OF_MONTH = "arg_selected_day_of_month";
    private static final int MAX_DAY = 31;
    private static final int MIN_DAY = 1;
    static final int TIME_INTERVAL_MILLIS = 250;
    private NumberPicker mCycleDayOfMonthPicker;
    private View mDownArrow;
    private ResetDayOfMonthPickedListener mResetDayOfMonthPickedListener;
    private View mUpArrow;

    public interface ResetDayOfMonthPickedListener {
        void onDayOfMonthPicked(int i);
    }

    public static UsageCycleResetDayOfMonthPickerDialog newInstance(int startDayOfMonth) {
        UsageCycleResetDayOfMonthPickerDialog dialog = new UsageCycleResetDayOfMonthPickerDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_SELECTED_DAY_OF_MONTH, startDayOfMonth);
        dialog.setArguments(args);
        return dialog;
    }

    public void setResetDayOfMonthPickedListener(ResetDayOfMonthPickedListener resetDayOfMonthPickedListener) {
        this.mResetDayOfMonthPickedListener = resetDayOfMonthPickedListener;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(builder.getContext());
        View view = inflater.inflate(R.layout.usage_cycle_reset_day_of_month_picker, (ViewGroup) null, false);
        int cycleDayOfMonth = getArguments().getInt(ARG_SELECTED_DAY_OF_MONTH);
        if (cycleDayOfMonth < 1) {
            cycleDayOfMonth = 1;
        }
        if (cycleDayOfMonth > 31) {
            cycleDayOfMonth = 31;
        }
        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.cycle_reset_day_of_month);
        this.mCycleDayOfMonthPicker = numberPicker;
        numberPicker.setMinValue(1);
        this.mCycleDayOfMonthPicker.setMaxValue(31);
        this.mCycleDayOfMonthPicker.setValue(cycleDayOfMonth);
        this.mCycleDayOfMonthPicker.setWrapSelectorWheel(true);
        View viewFindViewById = view.findViewById(R.id.up_arrow_container);
        this.mUpArrow = viewFindViewById;
        viewFindViewById.setOnTouchListener(new CycleArrowTouchListener(new CycleArrowTouchListener.IntervalActionListener() { // from class: com.android.car.settings.datausage.-$$Lambda$UsageCycleResetDayOfMonthPickerDialog$uX8IZHkVgcU87HhMXySXzHGge5I
            @Override // com.android.car.settings.datausage.UsageCycleResetDayOfMonthPickerDialog.CycleArrowTouchListener.IntervalActionListener
            public final void takeAction() {
                this.f$0.lambda$onCreateDialog$0$UsageCycleResetDayOfMonthPickerDialog();
            }
        }, 250L));
        View viewFindViewById2 = view.findViewById(R.id.down_arrow_container);
        this.mDownArrow = viewFindViewById2;
        viewFindViewById2.setOnTouchListener(new CycleArrowTouchListener(new CycleArrowTouchListener.IntervalActionListener() { // from class: com.android.car.settings.datausage.-$$Lambda$UsageCycleResetDayOfMonthPickerDialog$1Ab27PO3Lxz7nh35lf8nCYyM7Ck
            @Override // com.android.car.settings.datausage.UsageCycleResetDayOfMonthPickerDialog.CycleArrowTouchListener.IntervalActionListener
            public final void takeAction() {
                this.f$0.lambda$onCreateDialog$1$UsageCycleResetDayOfMonthPickerDialog();
            }
        }, 250L));
        return builder.setTitle(R.string.cycle_reset_day_of_month_picker_title).setView(view).setPositiveButton(R.string.cycle_reset_day_of_month_picker_positive_button, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.datausage.-$$Lambda$UsageCycleResetDayOfMonthPickerDialog$B7CEiAERdefepD5SnOBkJbCNKGw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$2$UsageCycleResetDayOfMonthPickerDialog(dialogInterface, i);
            }
        }).create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$UsageCycleResetDayOfMonthPickerDialog() {
        this.mCycleDayOfMonthPicker.setValue(r0.getValue() - 1);
    }

    public /* synthetic */ void lambda$onCreateDialog$1$UsageCycleResetDayOfMonthPickerDialog() {
        NumberPicker numberPicker = this.mCycleDayOfMonthPicker;
        numberPicker.setValue(numberPicker.getValue() + 1);
    }

    public /* synthetic */ void lambda$onCreateDialog$2$UsageCycleResetDayOfMonthPickerDialog(DialogInterface dialog, int which) {
        ResetDayOfMonthPickedListener resetDayOfMonthPickedListener;
        if (which == -1 && (resetDayOfMonthPickedListener = this.mResetDayOfMonthPickedListener) != null) {
            resetDayOfMonthPickedListener.onDayOfMonthPicked(this.mCycleDayOfMonthPicker.getValue());
        }
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
    }

    public int getSelectedDayOfMonth() {
        return this.mCycleDayOfMonthPicker.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CycleArrowTouchListener implements View.OnTouchListener {
        private final IntervalActionListener mIntervalActionListener;
        private final long mTimeIntervalMillis;
        private Handler mHandler = new Handler();
        private Runnable mAction = new Runnable() { // from class: com.android.car.settings.datausage.-$$Lambda$UsageCycleResetDayOfMonthPickerDialog$CycleArrowTouchListener$XJmvnrbq_eQ9kLc0DKAeaz8Y-tQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$UsageCycleResetDayOfMonthPickerDialog$CycleArrowTouchListener();
            }
        };

        interface IntervalActionListener {
            void takeAction();
        }

        CycleArrowTouchListener(IntervalActionListener listener, long timeIntervalMillis) {
            this.mIntervalActionListener = listener;
            this.mTimeIntervalMillis = timeIntervalMillis;
        }

        public /* synthetic */ void lambda$new$0$UsageCycleResetDayOfMonthPickerDialog$CycleArrowTouchListener() {
            this.mHandler.postDelayed(this.mAction, this.mTimeIntervalMillis);
            maybeTriggerAction();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (action == 0) {
                this.mHandler.removeCallbacks(this.mAction);
                this.mHandler.postDelayed(this.mAction, this.mTimeIntervalMillis);
                maybeTriggerAction();
                v.setPressed(true);
                return true;
            }
            if (action != 1 && action != 3) {
                return false;
            }
            this.mHandler.removeCallbacks(this.mAction);
            v.setPressed(false);
            return true;
        }

        private void maybeTriggerAction() {
            IntervalActionListener intervalActionListener = this.mIntervalActionListener;
            if (intervalActionListener != null) {
                intervalActionListener.takeAction();
            }
        }
    }
}
