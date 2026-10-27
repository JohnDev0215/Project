package com.byd.carsettings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.n.q.m;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.viewholder.CarStateViewHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WadeProtectAdapter extends RecyclerView.Adapter<CarStateViewHolder> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2613b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f2612a = {R.string.vehiclesettings_comfortable_wading_protection_power_little, R.string.vehiclesettings_comfortable_wading_protection_power_low, R.string.vehiclesettings_comfortable_wading_protection_speed_low, R.string.vehiclesettings_comfortable_wading_protection_door_mirror};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<Integer> f2614c = new ArrayList();

    public WadeProtectAdapter(Context context) {
        this.f2613b = context;
        b();
    }

    public void a() {
        List<Integer> list = this.f2614c;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.f2614c.clear();
    }

    public final void b() {
        a();
        if (m.p0().D1() == 1) {
            this.f2614c.add(0);
        }
        if (m.p0().D1() == 2) {
            this.f2614c.add(1);
        }
        if (m.p0().E1() == 1) {
            this.f2614c.add(2);
        }
        if (m.p0().G1() == 1) {
            this.f2614c.add(3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull CarStateViewHolder carStateViewHolder, int i) {
        String str;
        if (this.f2614c.size() > 1) {
            str = (i + 1) + ".";
            carStateViewHolder.f2667a.setTextAlignment(2);
        } else {
            carStateViewHolder.f2667a.setTextAlignment(4);
            str = "";
        }
        carStateViewHolder.f2667a.setText(str + this.f2613b.getString(this.f2612a[this.f2614c.get(i).intValue()]));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public CarStateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CarStateViewHolder(LayoutInflater.from(this.f2613b).inflate(R.layout.wade_protect_car_state_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2614c.size();
    }
}
