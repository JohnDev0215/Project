package com.android.car.settings.datetime;

import android.R;
import android.app.timedetector.ManualTimeSuggestion;
import android.app.timedetector.TimeDetector;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;
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
public class TimePickerFragment extends BaseFragment {
    private static final int MILLIS_IN_SECOND = 1000;
    private DirectManipulationState mDirectManipulationMode;
    private List<NumberPicker> mNumberPickers;
    private MenuItem mOkButton;
    private TimePicker mTimePicker;

    @Override // com.android.car.settings.common.BaseFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mOkButton);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOkButton = new MenuItem.Builder(getContext()).setTitle(R.string.ok).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.datetime.-$$Lambda$TimePickerFragment$zmBgpRkAYPSXPLGrmlPgDFHRZuI
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$TimePickerFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$TimePickerFragment(MenuItem i) {
        Calendar c = Calendar.getInstance();
        c.set(11, this.mTimePicker.getHour());
        c.set(12, this.mTimePicker.getMinute());
        c.set(13, 0);
        c.set(14, 0);
        long when = Math.max(c.getTimeInMillis(), DatetimeSettingsFragment.MIN_DATE);
        if (when / 1000 < 2147483647L) {
            TimeDetector timeDetector = (TimeDetector) getContext().getSystemService(TimeDetector.class);
            ManualTimeSuggestion manualTimeSuggestion = TimeDetector.createManualTimeSuggestion(when, "Settings: Set time");
            timeDetector.suggestManualTime(manualTimeSuggestion);
            getContext().sendBroadcast(new Intent("android.intent.action.TIME_SET"));
        }
        getFragmentHost().goBack();
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return com.android.car.settings.R.layout.time_picker;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return com.android.car.settings.R.string.time_picker_title;
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mDirectManipulationMode = new DirectManipulationState();
        TimePicker timePicker = (TimePicker) getView().findViewById(com.android.car.settings.R.id.time_picker);
        this.mTimePicker = timePicker;
        timePicker.setDescendantFocusability(393216);
        this.mTimePicker.setIs24HourView(Boolean.valueOf(is24Hour()));
        ArrayList arrayList = new ArrayList();
        this.mNumberPickers = arrayList;
        NumberPickerUtils.getNumberPickerDescendants(arrayList, this.mTimePicker);
        DirectManipulationHandler.setDirectManipulationHandler(this.mTimePicker, new DirectManipulationHandler.Builder(this.mDirectManipulationMode).setNudgeHandler(new View.OnKeyListener() { // from class: com.android.car.settings.datetime.-$$Lambda$TimePickerFragment$uo6YFVZLApS42WCaufrODIyQ8xE
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return TimePickerFragment.lambda$onActivityCreated$1(view, i, keyEvent);
            }
        }).setCenterButtonHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$TimePickerFragment$HHV2Da-vUg7f-6kSOtdI4bHk3oE
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$2$TimePickerFragment(z);
            }
        }).setBackHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$TimePickerFragment$TEealHss7NrV3rJHH4hSGxZEYI4
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$3$TimePickerFragment(z);
            }
        }).build());
        DirectManipulationHandler numberPickerListener = new DirectManipulationHandler.Builder(this.mDirectManipulationMode).setNudgeHandler(new NumberPickerNudgeHandler()).setCenterButtonHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$TimePickerFragment$eeK9CtVNjU7Q5rVIJFrrX_ssO3w
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$4$TimePickerFragment(z);
            }
        }).setBackHandler(new DirectManipulationHandler.EventListener() { // from class: com.android.car.settings.datetime.-$$Lambda$TimePickerFragment$Y2BmZ46Sg_OOZIUktBWoewrou6c
            @Override // com.android.car.settings.common.rotary.DirectManipulationHandler.EventListener
            public final boolean onEvent(boolean z) {
                return this.f$0.lambda$onActivityCreated$5$TimePickerFragment(z);
            }
        }).setRotationHandler(new NumberPickerRotationHandler()).build();
        for (int i = 0; i < this.mNumberPickers.size(); i++) {
            DirectManipulationHandler.setDirectManipulationHandler(this.mNumberPickers.get(i), numberPickerListener);
        }
    }

    static /* synthetic */ boolean lambda$onActivityCreated$1(View v, int keyCode, KeyEvent event) {
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$2$TimePickerFragment(boolean inDirectManipulationMode) {
        NumberPicker picker;
        if (!inDirectManipulationMode && (picker = this.mNumberPickers.get(0)) != null) {
            picker.requestFocus();
        }
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$3$TimePickerFragment(boolean inDirectManipulationMode) {
        if (!inDirectManipulationMode) {
            onBackPressed();
            return true;
        }
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$4$TimePickerFragment(boolean inDirectManipulationMode) {
        if (!inDirectManipulationMode) {
            return true;
        }
        this.mTimePicker.requestFocus();
        return true;
    }

    public /* synthetic */ boolean lambda$onActivityCreated$5$TimePickerFragment(boolean inDirectManipulationMode) {
        this.mTimePicker.requestFocus();
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
        DirectManipulationHandler.setDirectManipulationHandler(this.mTimePicker, null);
        for (int i = 0; i < this.mNumberPickers.size(); i++) {
            DirectManipulationHandler.setDirectManipulationHandler(this.mNumberPickers.get(i), null);
        }
        super.onDestroy();
    }

    private boolean is24Hour() {
        return DateFormat.is24HourFormat(getContext());
    }
}
