package com.byd.uereport.dialog;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.VehicleService;
import com.byd.uereport.widget.WheelView;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import g5.k;
import java.util.ArrayList;
import r4.a;

/* JADX INFO: loaded from: classes2.dex */
public class TimeSetDialog extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3312d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3313e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public WheelView f3314f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WheelView f3315g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ViewGroup f3316h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ViewGroup f3317i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3318j;

    public TimeSetDialog(Context context) {
        super(context);
        this.f3318j = 200;
    }

    public final void c() {
        StringBuilder sb;
        ArrayList arrayList = new ArrayList();
        int size = 0;
        for (int i8 = 0; i8 < 24; i8++) {
            arrayList.add("" + i8);
            if (this.f3312d.equals(i8 > 9 ? i8 + "" : "0" + i8)) {
                size = arrayList.size() - 1;
            }
        }
        this.f3314f.setData(arrayList);
        this.f3314f.setDefault(size);
        ArrayList arrayList2 = new ArrayList();
        int size2 = 0;
        for (int i9 = 0; i9 < 60; i9++) {
            arrayList2.add("" + i9);
            String str = this.f3313e;
            if (i9 > 9) {
                sb = new StringBuilder();
                sb.append(i9);
                sb.append("");
            } else {
                sb = new StringBuilder();
                sb.append("0");
                sb.append(i9);
            }
            if (str.equals(sb.toString())) {
                size2 = arrayList2.size() - 1;
            }
        }
        this.f3315g.setData(arrayList2);
        this.f3315g.setDefault(size2);
    }

    public void d() {
        initData();
        c();
    }

    @Override // b5.g
    public void initContent(View view) {
        this.f3314f = (WheelView) view.findViewById(R.id.wheelView_h);
        this.f3315g = (WheelView) view.findViewById(R.id.wheelView_m);
        this.f3316h = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.f3317i = (ViewGroup) view.findViewById(R.id.ll_dialog_content);
        this.f3316h.setAlpha(0.0f);
        this.f3317i.setAlpha(0.0f);
        this.f3317i.setScaleX(0.9f);
        this.f3317i.setScaleY(0.9f);
        this.f3316h.setOnClickListener(this);
        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        view.findViewById(R.id.btn_ok).setOnClickListener(this);
        c();
    }

    @Override // b5.g
    public void initData() {
        this.f3312d = k.f(this.mContext, "set_hour", "00");
        this.f3313e = k.f(this.mContext, "set_min", "00");
    }

    @Override // b5.g
    public int initLayoutRes() {
        return a.a(R.layout.layout_dialog_time_set);
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        StringBuilder sb;
        StringBuilder sb2;
        if (view.getId() == R.id.btn_ok) {
            try {
                int i8 = Integer.parseInt(this.f3314f.getSelectedText());
                int i9 = Integer.parseInt(this.f3315g.getSelectedText());
                if ((i8 * 60) + i9 < 5) {
                    Context context = this.mContext;
                    Toast.makeText(context, context.getResources().getString(R.string.electricity_set_tiem_hint), 0).show();
                    return;
                }
                BYDAutoChargingDevice.getInstance(this.mContext).setDischargeTime(r2 * 60 * 1000);
                Context context2 = this.mContext;
                if (i8 > 9) {
                    sb = new StringBuilder();
                    sb.append("");
                    sb.append(i8);
                } else {
                    sb = new StringBuilder();
                    sb.append("0");
                    sb.append(i8);
                }
                k.k(context2, "set_hour", sb.toString());
                Context context3 = this.mContext;
                if (i9 > 9) {
                    sb2 = new StringBuilder();
                    sb2.append("");
                    sb2.append(i9);
                } else {
                    sb2 = new StringBuilder();
                    sb2.append("0");
                    sb2.append(i9);
                }
                k.k(context3, "set_min", sb2.toString());
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }
        Context context4 = this.mContext;
        context4.startService(new Intent(context4, (Class<?>) VehicleService.class).putExtra("uiShow", 9));
        dismiss();
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // b5.g, android.app.Dialog
    public void show() {
        super.show();
        this.f3317i.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.f3318j).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.f3317i.animate().alpha(1.0f).setDuration(this.f3318j).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.f3316h.animate().alpha(1.0f).setDuration(this.f3318j).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
