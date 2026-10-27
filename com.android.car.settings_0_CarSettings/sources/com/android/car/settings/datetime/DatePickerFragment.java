package com.android.car.settings.datetime;

import android.R;
import android.app.timedetector.ManualTimeSuggestion;
import android.app.timedetector.TimeDetector;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.common.rotary.DirectManipulationHandler;
import com.android.car.settings.common.rotary.DirectManipulationState;
import com.android.car.settings.common.rotary.NumberPickerNudgeHandler;
import com.android.car.settings.common.rotary.NumberPickerRotationHandler;
import com.android.car.settings.common.rotary.NumberPickerUtils;
import com.android.car.ui.toolbar.MenuItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DatePickerFragment extends BaseFragment {
    private static final int MILLIS_IN_SECOND = 1000;
    private DatePicker mDatePicker;
    private DirectManipulationState mDirectManipulationMode;
    private List<NumberPicker> mNumberPickers;
    private MenuItem mOkButton;

    @Override // com.android.car.settings.common.BaseFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mOkButton);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOkButton = new MenuItem.Builder(getContext()).setTitle(R.string.ok).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.datetime.-$$Lambda$DatePickerFragment$rxfVMM2LSPIZURnzLQo_oR5riWs
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$DatePickerFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$DatePickerFragment(MenuItem i) {
        Calendar c = Calendar.getInstance();
        c.set(1, this.mDatePicker.getYear());
        c.set(2, this.mDatePicker.getMonth());
        c.set(5, this.mDatePicker.getDayOfMonth());
        long when = Math.max(c.getTimeInMillis(), DatetimeSettingsFragment.MIN_DATE);
        if (when / 1000 < 2147483647L) {
            TimeDetector timeDetector = (TimeDetector) getContext().getSystemService(TimeDetector.class);
            ManualTimeSuggestion manualTimeSuggestion = TimeDetector.createManualTimeSuggestion(when, "Settings: Set date");
            timeDetector.suggestManualTime(manualTimeSuggestion);
            getContext().sendBroadcast(new Intent("android.intent.action.TIME_SET"));
        }
        getFragmentHost().goBack();
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return com.android.car.settings.R.layout.date_picker;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return com.android.car.settings.R.string.date_picker_title;
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mDirectManipulationMode = new DirectManipulationState();
        DatePicker datePicker = (DatePicker) getView().findViewById(com.android.car.settings.R.id.date_picker);
        this.mDatePicker = datePicker;
        datePicker.setDescendantFocusability(393216);
        ArrayList arrayList = new ArrayList();
        this.mNumberPickers = arrayList;
        NumberPickerUtils.getNumberPickerDescendants(arrayList, this.mDatePicker);
        DirectManipulationHandler.setDirectManipulationHandler(this.mDatePicker, new DirectManipulationHandler.Builder(this.mDirectManipulationMode).setNudgeHandler(new View.OnKeyListener() { // from class: com.android.car.settings.datetime.-$$Lambda$DatePickerFragment$5VayA2Dd4hCPyqsv2m_kbtOiAk4
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return DatePickerFragment.lambda$onActivityCreated$1(view, i, keyEvent);
            }
        }).setCenterButtonHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$DatePickerFragment$lccN1rYZPMHXY8VYHFxnFyg-Mvc
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$2$DatePickerFragment(z);
            }
        }).setBackHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$DatePickerFragment$1q8GPqSxrsZgrOsIvI-vXsr1Mq8
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$3$DatePickerFragment(z);
            }
        }).build());
        DirectManipulationHandler numberPickerListener = new DirectManipulationHandler.Builder(this.mDirectManipulationMode).setNudgeHandler(new NumberPickerNudgeHandler()).setCenterButtonHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$DatePickerFragment$HZDhd4GY0jhFpoNrU3PZaPT_z1g
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$4$DatePickerFragment(z);
            }
        }).setBackHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$DatePickerFragment$lKRsbZkoUkzJD4PTgknX1T08xGY
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$5$DatePickerFragment(z);
            }
        }).setRotationHandler(new NumberPickerRotationHandler()).build();
        for (int i = 0; i < this.mNumberPickers.size(); i++) {
            DirectManipulationHandler.setDirectManipulationHandler(this.mNumberPickers.get(i), numberPickerListener);
        }
    }

    static /* synthetic */ boolean lambda$onActivityCreated$1(View v, int keyCode, KeyEvent event) {
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$2$DatePickerFragment(boolean inDirectManipulationMode) {
        NumberPicker picker;
        if (!inDirectManipulationMode && (picker = this.mNumberPickers.get(0)) != null) {
            picker.requestFocus();
        }
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$3$DatePickerFragment(boolean inDirectManipulationMode) {
        if (!inDirectManipulationMode) {
            onBackPressed();
            return true;
        }
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$4$DatePickerFragment(boolean inDirectManipulationMode) {
        if (!inDirectManipulationMode) {
            return true;
        }
        this.mDatePicker.requestFocus();
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$5$DatePickerFragment(boolean inDirectManipulationMode) {
        this.mDatePicker.requestFocus();
        return true;
    }

    @Override // com.android.car.settings.common.BaseFragment, android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
    public void onUxRestrictionsChanged(CarUxRestrictions restrictionInfo) {
        DirectManipulationState directManipulationState;
        if (!canBeShown(restrictionInfo) && (directManipulationState = this.mDirectManipulationMode) != null && directManipulationState.isActive()) {
            this.mDirectManipulationMode.disable();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        DirectManipulationHandler.setDirectManipulationHandler(this.mDatePicker, null);
        for (int i = 0; i < this.mNumberPickers.size(); i++) {
            DirectManipulationHandler.setDirectManipulationHandler(this.mNumberPickers.get(i), null);
        }
        super.onDestroy();
    }
}
