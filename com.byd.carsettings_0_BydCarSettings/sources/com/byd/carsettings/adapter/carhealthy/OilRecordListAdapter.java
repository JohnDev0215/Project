package com.byd.carsettings.adapter.carhealthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.r.g;
import com.byd.carsettings.R;
import com.byd.carsettings.db.model.MaintainanceRecord;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OilRecordListAdapter extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2626a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<MaintainanceRecord> f2627b;

    public static class a extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2628a;

        public a(View view) {
            super(view);
            this.f2628a = (TextView) view.findViewById(R.id.tv_record_title);
        }
    }

    public static class b extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2629a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2630b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2631c;

        public b(View view) {
            super(view);
            this.f2629a = (TextView) view.findViewById(R.id.tv_record_date);
            this.f2630b = (TextView) view.findViewById(R.id.tv_record_mile);
            this.f2631c = (TextView) view.findViewById(R.id.tv_record_value);
        }
    }

    public OilRecordListAdapter(Context context) {
        this.f2626a = context;
    }

    public void a(List<MaintainanceRecord> list) {
        this.f2627b = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MaintainanceRecord> list = this.f2627b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.f2627b.get(i).getId() == -1 ? R.layout.item_oil_record : R.layout.item_oil_record_child;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        try {
            MaintainanceRecord maintainanceRecord = this.f2627b.get(i);
            if (viewHolder.getItemViewType() == R.layout.item_oil_record) {
                a aVar = (a) viewHolder;
                if (maintainanceRecord.getType() == 0) {
                    aVar.f2628a.setText(this.f2626a.getResources().getString(R.string.carhealthy_oil_engine));
                    return;
                }
                if (maintainanceRecord.getType() == 2) {
                    aVar.f2628a.setText(this.f2626a.getResources().getString(R.string.carhealthy_oil_brake));
                    return;
                }
                if (maintainanceRecord.getType() == 3) {
                    aVar.f2628a.setText(this.f2626a.getResources().getString(R.string.carhealthy_oil_blade_battery));
                    return;
                }
                if (maintainanceRecord.getType() == 4) {
                    aVar.f2628a.setText(this.f2626a.getResources().getString(R.string.carhealthy_oil_motor_elec));
                    return;
                } else if (maintainanceRecord.getType() == 1) {
                    aVar.f2628a.setText(this.f2626a.getResources().getString(R.string.carhealthy_oil_transmission));
                    return;
                } else {
                    aVar.f2628a.setText("");
                    return;
                }
            }
            b bVar = (b) viewHolder;
            bVar.f2629a.setText(g.d(maintainanceRecord.getDate()));
            int totalMileage = maintainanceRecord.getTotalMileage();
            int i2 = 0;
            if (totalMileage < 0 || totalMileage > 9999999.9d) {
                totalMileage = 0;
            }
            int hevMileage = maintainanceRecord.getHevMileage();
            if (hevMileage >= 0 && hevMileage <= 999999) {
                i2 = hevMileage;
            }
            bVar.f2630b.setText(totalMileage + "");
            bVar.f2631c.setText(i2 + "");
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(this.f2626a).inflate(i, viewGroup, false);
        return i == R.layout.item_oil_record ? new a(viewInflate) : new b(viewInflate);
    }
}
