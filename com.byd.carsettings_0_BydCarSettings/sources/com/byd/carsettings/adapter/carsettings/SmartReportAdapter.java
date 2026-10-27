package com.byd.carsettings.adapter.carsettings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.r.u;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.viewholder.SmartViewHolder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SmartReportAdapter extends RecyclerView.Adapter<SmartViewHolder> implements b.b.o.b.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<Integer> f2661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2662b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f2663c = {R.string.vehiclesettings_fail_prompt_for_vehicle_start_or_withdrawal, R.string.vehiclesettings_parking_brake_warning, R.string.vehiclesettings_door_unclosed_warning, R.string.vehiclesettings_seat_belt_warning, R.string.vehiclesettings_left_with_p_warning, R.string.vehiclesettings_key_low_power_warning, R.string.vehiclesettings_night_light_warning, R.string.vehiclesettings_low_fuel_warning, R.string.vehiclesettings_auto_parking_warning, R.string.vehiclesettings_no_key_warning, R.string.vehiclesettings_dms_warings_tips, R.string.vehiclesettings_carseat_reminder_take_phone_tips};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String[] f2664d = {"byd_start_toggle", "byd_park_toggle", "byd_door_toggle", "byd_seatbelt_toggle", "byd_leave_toggle", "byd_key_power_toggle", "byd_night_run_toggle", "byd_oil_low_toggle", "byd_auto_park_toggle", "byd_uncheck_key_toggle", "dms_waring_tips", "forget_phone_tips"};

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2665a;

        public a(int i) {
            this.f2665a = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SmartReportAdapter smartReportAdapter = SmartReportAdapter.this;
            smartReportAdapter.j(((Integer) smartReportAdapter.f2661a.get(this.f2665a)).intValue(), z);
        }
    }

    public SmartReportAdapter(List<Integer> list, Context context) {
        this.f2662b = context;
        this.f2661a = list;
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.carsetting_smart_rtl_reminder_item;
    }

    public final boolean d(int i) {
        return i == 10 ? u.b(this.f2662b, this.f2664d[i], 2) == 1 : i == 7 ? u.b(this.f2662b, this.f2664d[i], 2) == 1 : e(this.f2662b, this.f2664d[i]);
    }

    public final boolean e(Context context, String str) {
        return u.a(context, str) == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull SmartViewHolder smartViewHolder, int i) {
        smartViewHolder.f2670a.setText(this.f2662b.getString(this.f2663c[this.f2661a.get(i).intValue()]));
        smartViewHolder.f2671b.setChecked(d(this.f2661a.get(i).intValue()), false);
        smartViewHolder.f2671b.setOnCheckedChangeListener(new a(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SmartViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(b.b.o.b.b.a.a(this), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2661a.size();
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.carsetting_smart_reminder_item;
    }

    public final void i(Context context, String str, boolean z) {
        u.c(context, str, z ? 1 : 2);
    }

    public final void j(int i, boolean z) {
        i(this.f2662b, this.f2664d[i], z);
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.carsetting_right_smart_reminder_item;
    }
}
