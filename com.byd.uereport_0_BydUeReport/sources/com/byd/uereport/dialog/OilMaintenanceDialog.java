package com.byd.uereport.dialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b5.g;
import com.byd.uereport.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import g5.k;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OilMaintenanceDialog extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RecyclerView f3231d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public z4.a f3232e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<a5.a> f3233f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3234g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ViewGroup f3235h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ViewGroup f3236i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3237j;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class b implements Comparator<a5.a> {
        public b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(a5.a aVar, a5.a aVar2) {
            return aVar.c() - aVar2.c();
        }
    }

    public class c implements Comparator<a5.a> {
        public c() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(a5.a aVar, a5.a aVar2) {
            return aVar.a() - aVar2.a();
        }
    }

    public OilMaintenanceDialog(Context context, int i8) {
        super(context);
        ArrayList arrayList = new ArrayList();
        this.f3233f = arrayList;
        this.f3237j = 200;
        this.f3234g = i8;
        arrayList.add(new a5.a(this.mContext.getResources().getString(R.string.carhealthy_oil_engine), -999, k.b(this.mContext, "oil_engine", false), 0));
        this.f3233f.add(new a5.a(this.mContext.getResources().getString(R.string.carhealthy_oil_transmission), -999, k.b(this.mContext, "oil_transmission", false), 1));
        this.f3233f.add(new a5.a(this.mContext.getResources().getString(R.string.carhealthy_oil_brake), -999, k.b(this.mContext, "oil_brake", false), 2));
        this.f3233f.add(new a5.a(this.mContext.getResources().getString(R.string.carhealthy_oil_blade_battery), -999, k.b(this.mContext, "blade_battery", false), 3));
        this.f3233f.add(new a5.a(this.mContext.getResources().getString(R.string.carhealthy_oil_motor_elec), -999, k.b(this.mContext, "motor_elec", false), 4));
    }

    public List<a5.a> c(int i8) {
        int oilLifetime;
        int i9 = 0;
        int i10 = 0;
        while (i10 < this.f3233f.size()) {
            if (BYDAutoVehicleHealthDevice.getInstance(getContext()).getOilMaintainanceHint(this.f3233f.get(i10).c()) != 1 || (oilLifetime = BYDAutoVehicleHealthDevice.getInstance(getContext()).getOilLifetime(this.f3233f.get(i10).c())) < -100 || oilLifetime > 100) {
                this.f3233f.get(i10).h(-999);
            } else {
                this.f3233f.get(i10).h(oilLifetime);
            }
            if (this.f3233f.get(i10).c() == 0) {
                this.f3233f.get(i10).f(k.b(this.mContext, "oil_engine", false));
            } else if (1 == this.f3233f.get(i10).c()) {
                this.f3233f.get(i10).f(k.b(this.mContext, "oil_transmission", false));
            } else if (2 == this.f3233f.get(i10).c()) {
                this.f3233f.get(i10).f(k.b(this.mContext, "oil_brake", false));
            } else if (3 == this.f3233f.get(i10).c()) {
                this.f3233f.get(i10).f(k.b(this.mContext, "blade_battery", false));
            } else if (4 == this.f3233f.get(i10).c()) {
                this.f3233f.get(i10).f(k.b(this.mContext, "motor_elec", false));
            }
            i10++;
        }
        for (int i11 = 0; i11 < this.f3233f.size(); i11++) {
            a5.a aVar = this.f3233f.get(i11);
            aVar.g(0);
            int iD = aVar.d();
            int iC = aVar.c();
            if (iD == -999) {
                aVar.g(0);
            } else if (iC == 4 && iD <= 3) {
                aVar.g(iD <= 1 ? 1 : 2);
            } else if (iC == 4 || iD > 10) {
                aVar.g(0);
            } else if (iC == 0) {
                aVar.g(iD <= 3 ? 1 : 2);
            } else {
                aVar.g(iD <= 1 ? 1 : 2);
            }
        }
        ArrayList arrayList = new ArrayList(this.f3233f);
        int i12 = 0;
        while (i12 < arrayList.size()) {
            if (((a5.a) arrayList.get(i12)).a() == 0) {
                arrayList.remove(i12);
                i12--;
            }
            i12++;
        }
        while (i9 < arrayList.size()) {
            if (((a5.a) arrayList.get(i9)).a() == 2 && ((a5.a) arrayList.get(i9)).e()) {
                arrayList.remove(i9);
                i9--;
            }
            i9++;
        }
        arrayList.sort(new b());
        arrayList.sort(new c());
        return arrayList;
    }

    public final void d() {
        PackageManager packageManager = this.mContext.getApplicationContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=maintenance_oil"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        Log.e("---", "----startServiceByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.mContext.startService(intent);
        dismiss();
    }

    public void e(int i8) {
        this.f3232e.b(c(i8));
    }

    @Override // b5.g
    public void initContent(View view) {
        this.f3231d = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.f3235h = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.f3236i = (ViewGroup) view.findViewById(R.id.ll_dialog_content);
        this.f3235h.setAlpha(0.0f);
        this.f3236i.setAlpha(0.0f);
        this.f3236i.setScaleX(0.9f);
        this.f3236i.setScaleY(0.9f);
        this.f3235h.setOnClickListener(new a());
        view.findViewById(R.id.tv_replace_reset).setOnClickListener(this);
        view.findViewById(R.id.btn_ok).setOnClickListener(this);
        z4.a aVar = new z4.a(getContext());
        this.f3232e = aVar;
        this.f3231d.setAdapter(aVar);
        this.f3231d.setLayoutManager(new LinearLayoutManager(getContext()));
        e(this.f3234g);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.layout_dialog_oil_maintenance;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok) {
            dismiss();
        } else {
            if (id != R.id.tv_replace_reset) {
                return;
            }
            d();
        }
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // b5.g, android.app.Dialog
    public void show() {
        super.show();
        this.f3236i.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.f3237j).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.f3236i.animate().alpha(1.0f).setDuration(this.f3237j).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.f3235h.animate().alpha(1.0f).setDuration(this.f3237j).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
