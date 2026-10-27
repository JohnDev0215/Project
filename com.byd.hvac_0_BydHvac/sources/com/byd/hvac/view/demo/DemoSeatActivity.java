package com.byd.hvac.view.demo;

import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.a.f.f1;
import b.a.a.f.g1;
import b.a.a.h.i;
import b.a.a.i.h.ta;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseActivity;
import com.byd.hvac.view.demo.CommonAdapter;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DemoSeatActivity extends BaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1043b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView f1044c;
    public List<ta> d;
    public List<ta> e;
    public CommonAdapter f;
    public CommonAdapter g;
    public EditText h;
    public EditText i;
    public TextView j;
    public TextView k;
    public RecyclerView l;
    public CopyOnWriteArrayList<ta> m;
    public CallbackAdapter n;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a extends AbsBYDAutoSettingListener {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B(int i, int i2) {
            ta taVar = new ta("onSeatVentilatingStateChanged ");
            taVar.c("seatID=" + i + " - - state=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D(int i) {
            ta taVar = new ta("onSteeringWheelHeatingAutoStateChanged ");
            taVar.c("value=" + i);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F(int i) {
            ta taVar = new ta("onSteeringWheelHeatingGearChanged ");
            taVar.c("value=" + i);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H(int i) {
            ta taVar = new ta("onSteeringWheelHeatingStateChanged ");
            taVar.c("state=" + i);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i, int i2) {
            ta taVar = new ta("on1LevelSeatHeatingStateChanged ");
            taVar.c("seatID=" + i + " - - state=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(int i, int i2) {
            ta taVar = new ta("on5LevelSeatHeatingLevelChanged ");
            taVar.c("seatID=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(int i, int i2) {
            ta taVar = new ta("on5LevelSeatHeatingModeChanged ");
            taVar.c("seatId=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(int i, int i2) {
            ta taVar = new ta("on5LevelSeatHeatingStateChanged ");
            taVar.c("seatId=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(int i, int i2) {
            ta taVar = new ta("on5LevelSeatVentilatingLevelChanged ");
            taVar.c("seatID=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l(int i, int i2) {
            ta taVar = new ta("on5LevelSeatVentilatingModeChanged ");
            taVar.c("seatId=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n(int i, int i2) {
            ta taVar = new ta("on5LevelSeatVentilatingStateChanged ");
            taVar.c("seatId=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p(int i) {
            ta taVar = new ta("onSeatHeatingNotL1PStateChanged ");
            taVar.c("value=" + i);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r(int i, int i2) {
            ta taVar = new ta("onSeatHeatingState2Changed ");
            taVar.c("seatId=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t(int i, int i2) {
            ta taVar = new ta("onSeatHeatingStateChanged ");
            taVar.c("seatID=" + i + " - - state=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v(int i, int i2) {
            ta taVar = new ta("onSeatHeatingStateChanged1 ");
            taVar.c("seatID=" + i + " - - state=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x(int i) {
            ta taVar = new ta("onSeatVentilatingNotL1PStateChanged ");
            taVar.c("value=" + i);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z(int i, int i2) {
            ta taVar = new ta("onSeatVentilatingState2Changed ");
            taVar.c("seatId=" + i + " - - value=" + i2);
            DemoSeatActivity.this.m.add(taVar);
            DemoSeatActivity.this.n.c(DemoSeatActivity.this.m);
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void on1LevelSeatHeatingStateChanged(final int i, final int i2) {
            super.on1LevelSeatHeatingStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ja
                @Override // java.lang.Runnable
                public final void run() {
                    this.f537a.b(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void on5LevelSeatHeatingLevelChanged(final int i, final int i2) {
            super.on5LevelSeatHeatingLevelChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ha
                @Override // java.lang.Runnable
                public final void run() {
                    this.f486a.d(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void on5LevelSeatHeatingModeChanged(final int i, final int i2) {
            super.on5LevelSeatHeatingModeChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ca
                @Override // java.lang.Runnable
                public final void run() {
                    this.f360a.f(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void on5LevelSeatHeatingStateChanged(final int i, final int i2) {
            super.on5LevelSeatHeatingStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ea
                @Override // java.lang.Runnable
                public final void run() {
                    this.f411a.h(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void on5LevelSeatVentilatingLevelChanged(final int i, final int i2) {
            super.on5LevelSeatVentilatingLevelChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ma
                @Override // java.lang.Runnable
                public final void run() {
                    this.f616a.j(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void on5LevelSeatVentilatingModeChanged(final int i, final int i2) {
            super.on5LevelSeatVentilatingModeChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.z9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f909a.l(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void on5LevelSeatVentilatingStateChanged(final int i, final int i2) {
            super.on5LevelSeatVentilatingStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.y9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f884a.n(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeatHeatingNotL1PStateChanged(final int i) {
            super.onSeatHeatingNotL1PStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f864a.p(i);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeatHeatingState2Changed(final int i, final int i2) {
            super.onSeatHeatingState2Changed(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.fa
                @Override // java.lang.Runnable
                public final void run() {
                    this.f437a.r(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeatHeatingStateChanged(final int i, final int i2) {
            super.onSeatHeatingStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ia
                @Override // java.lang.Runnable
                public final void run() {
                    this.f512a.t(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeatHeatingStateChanged1(final int i, final int i2) {
            super.onSeatHeatingStateChanged1(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ga
                @Override // java.lang.Runnable
                public final void run() {
                    this.f462a.v(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeatVentilatingNotL1PStateChanged(final int i) {
            super.onSeatVentilatingNotL1PStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.da
                @Override // java.lang.Runnable
                public final void run() {
                    this.f386a.x(i);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeatVentilatingState2Changed(final int i, final int i2) {
            super.onSeatVentilatingState2Changed(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.na
                @Override // java.lang.Runnable
                public final void run() {
                    this.f642a.z(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeatVentilatingStateChanged(final int i, final int i2) {
            super.onSeatVentilatingStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.ba
                @Override // java.lang.Runnable
                public final void run() {
                    this.f333a.B(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSteeringWheelHeatingAutoStateChanged(final int i) {
            super.onSteeringWheelHeatingAutoStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.ka
                @Override // java.lang.Runnable
                public final void run() {
                    this.f564a.D(i);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSteeringWheelHeatingGearChanged(final int i) {
            super.onSteeringWheelHeatingGearChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.aa
                @Override // java.lang.Runnable
                public final void run() {
                    this.f306a.F(i);
                }
            });
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSteeringWheelHeatingStateChanged(final int i) {
            super.onSteeringWheelHeatingStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.la
                @Override // java.lang.Runnable
                public final void run() {
                    this.f591a.H(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B0(ta taVar, int i) {
        int i2;
        String str;
        Editable text = this.i.getText();
        i2 = (text == null || TextUtils.isEmpty(text.toString())) ? 0 : Integer.parseInt(text.toString());
        str = taVar.f780a;
        str.hashCode();
        switch (str) {
            case "设置五档座椅通风状态 set5LevelSeatVentilatingState":
                f1.l0().H3(1, i2);
                f1.l0().H3(2, i2);
                f1.l0().H3(3, i2);
                f1.l0().H3(4, i2);
                str = str + " 主驾(0x4C1 子ID：0x20\t2.0-2.1) = " + i2 + "； 副驾(0x4C1 子ID：0x20\t3.0-3.1)= " + i2 + "； 左后(0x4C1 子ID：0x20\t4.0-4.1)= " + i2 + "； 右后(0x4C1 子ID：0x20\t5.0-5.1)= " + i2;
                break;
            case "设置五档座椅加热状态 set5LevelSeatHeatingState":
                f1.l0().E3(1, i2);
                f1.l0().E3(2, i2);
                f1.l0().E3(3, i2);
                f1.l0().E3(4, i2);
                str = str + " 主驾(0x4C1 子ID：0x20\t2.4-2.5) = " + i2 + "； 副驾(0x4C1 子ID：0x20\t3.4-3.5)= " + i2 + "； 左后(0x4C1 子ID：0x20\t4.4-4.5)= " + i2 + "； 右后(0x4C1 子ID：0x20\t5.4-5.5)= " + i2;
                break;
            case "设置二档座椅加热状态 setSeatHeatingState":
                f1.l0().w4(1, i2);
                f1.l0().w4(2, i2);
                f1.l0().w4(3, i2);
                f1.l0().w4(4, i2);
                str = str + " 主驾(0x431\t2.4~2.7) = " + i2 + "； 副驾(0x431\t3.4~3.7)= " + i2 + "； 左后(0x431\t5.3~5.5)= " + i2 + "； 右后(0x431\t6.3~6.5)= " + i2;
                break;
            case "设置五档方向盘加热自动模式状态(0x4C1子ID:0x22 2.0-2.1) setSteeringWheelHeatingAutoState":
                f1.l0().y4(i2);
                str = str + i2;
                break;
            case "设置二档座椅通风状态 setSeatVentilatingState":
                f1.l0().u4(1, i2);
                f1.l0().u4(2, i2);
                f1.l0().u4(3, i2);
                f1.l0().u4(4, i2);
                str = str + " 主驾(0x431 2.0~2.3) = " + i2 + "； 副驾(0x431 3.0~3.3)= " + i2 + "； 左后(0x431\t5.0~5.2)= " + i2 + "； 右后(0x431\t6.0~6.2)= " + i2;
                break;
            case "设置蓝牙通话状态(0x1DE 4.0-4.1) setCallState":
                f1.l0().b4(i2);
                str = str + i2;
                break;
            case "设置五档座椅加热控制模式 set5LevelSeatHeatingMode":
                f1.l0().D3(1, i2);
                f1.l0().D3(2, i2);
                f1.l0().D3(3, i2);
                f1.l0().D3(4, i2);
                str = str + " 主驾(0x4C1 子ID：0x20 2.6-2.7) = " + i2 + "； 副驾(0x4C1 子ID：0x20 3.6-3.7)= " + i2 + "； 左后(0x4C1 子ID：0x20 4.6-4.7)= " + i2 + "； 右后(0x4C1 子ID：0x20 5.6-5.7)= " + i2;
                break;
            case "设置五档座椅加热挡位 set5LevelSeatHeatingLevel":
                f1.l0().C3(1, i2);
                f1.l0().C3(2, i2);
                f1.l0().C3(3, i2);
                f1.l0().C3(4, i2);
                str = str + " 主驾(0x4C1 子ID：0x21 2.4-2.7) = " + i2 + "； 副驾(0x4C1 子ID：0x2 3.4-3.7)= " + i2 + "； 左后(0x4C1 子ID：0x21 4.4-4.7)= " + i2 + "； 右后(0x4C1 子ID：0x21 5.4-5.7)= " + i2;
                break;
            case "设置五档档设置方向盘加热档位(0x4C1子ID:0x22 2.2-2.5) setSteeringWheelHeatingGear":
                f1.l0().z4(i2);
                str = str + i2;
                break;
            case "设置五档座椅通风控制模式 set5LevelSeatVentilatingMode":
                f1.l0().G3(1, i2);
                f1.l0().G3(2, i2);
                f1.l0().G3(3, i2);
                f1.l0().G3(4, i2);
                str = str + " 主驾(0x4C1 子ID：0x20\t2.2-2.3) = " + i2 + "； 副驾(0x4C1 子ID：0x20\t3.2-3.3)= " + i2 + "； 左后(0x4C1 子ID：0x20\t4.2-4.3)= " + i2 + "； 右后(0x4C1 子ID：0x20\t5.2-5.3)= " + i2;
                break;
            case "设置五档座椅通风挡位 set5LevelSeatVentilatingLevel":
                f1.l0().F3(1, i2);
                f1.l0().F3(2, i2);
                f1.l0().F3(3, i2);
                f1.l0().F3(4, i2);
                str = str + " 主驾(0x4C1 子ID：0x21 2.0-2.3) = " + i2 + "； 副驾(0x4C1 子ID：0x21 3.0-3.3)= " + i2 + "； 左后(0x4C1 子ID：0x21 4.0-4.3)= " + i2 + "； 右后(0x4C1 子ID：0x21 5.0-5.3)= " + i2;
                break;
            case "设置二档方向盘加热功能状态(0x385 6.5-6.6) setSteeringWheelHeatingState":
                f1.l0().A4(i2);
                str = str + i2;
                break;
        }
        this.k.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: C0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void D0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void z0(ta taVar, int i) {
        String str;
        str = taVar.f780a;
        str.hashCode();
        switch (str) {
            case "获取五档方向盘加热档位(0x429 3.1-3.4) getSteeringWheelHeatingGear":
                str = str + f1.l0().k1();
                break;
            case "获取五档方向盘加热自动模式状态(0x429 2.6-2.7) getSteeringWheelHeatingAutoState":
                str = str + f1.l0().j1();
                break;
            case "获取五档座椅通风挡位 get5LevelSeatVentilatingLevel":
                str = str + " 主驾(0x29E\t3.0-3.3)：" + f1.l0().e(1) + "； 副驾(0x2A7\t3.0-3.3)：" + f1.l0().e(2) + "； 左后(0x271\t5.0-5.3)：" + f1.l0().e(3) + "； 右后(0x271\t6.0-6.3)：" + f1.l0().e(4);
                break;
            case "获取二档座椅加热设置状态 getSeatHeatingState":
                str = str + " 主驾(0x4FA 1.3-1.5)：" + f1.l0().g1(1) + "； 副驾(0x4FA 2.3-2.5)：" + f1.l0().g1(2) + "； 左后(0x486 1.3-1.5)：" + f1.l0().g1(3) + "； 右后(0x486 2.3-2.5)：" + f1.l0().g1(4);
                break;
            case "获取转向盘加热方案(0x429 7.0-7.2)getSteeringWheelHeatedPlan":
                BydLogHelper.logD("获取方向盘加热功能状态");
                str = str + f1.l0().i1();
                break;
            case "获取二档座椅通风设置状态 getSeatVentilatingState":
                str = str + " 主驾(0x4FA 1.0-1.2)：" + f1.l0().f1(1) + "； 副驾(L1P平台 0x4FA 2.0-2.2)：" + f1.l0().f1(2) + "； 左后(0x486 1.0-1.2)：" + f1.l0().f1(3) + "； 右后(0x486 2.0-2.2)：" + f1.l0().f1(4);
                break;
            case "获取五档座椅加热控制模式 get5LevelSeatHeatingMode":
                str = str + " 主驾(0x29E\t1.6-1.7)：" + f1.l0().c(1) + "； 副驾(0x2A7\t1.6-1.7)：" + f1.l0().c(2) + "； 左后(0x271\t1.6-1.7)：" + f1.l0().c(3) + "； 右后(0x271\t2.6-2.7)：" + f1.l0().c(4);
                break;
            case "获取二档座椅加热设置状态 getSeatHeatingState2":
                str = str + " 左后(0x402 5.3-5.5)：" + f1.l0().a1(3) + "； 右后(0x42F 5.3-5.5)：" + f1.l0().a1(4);
                break;
            case "获取左域平台配置(0x294 5.2-5.4) getLeftPlatformConfigs":
                str = str + g1.e().f();
                break;
            case "获取二档方向盘加热功能状态(0x429 1.6-1.7) getSteeringWheelHeatingState":
                BydLogHelper.logD("获取方向盘加热功能状态");
                str = str + f1.l0().l1();
                break;
            case "获取五档座椅通风状态 get5LevelSeatVentilatingState":
                str = str + " 主驾(0x29E\t1.0-1.1)：" + f1.l0().g(1) + "； 副驾(0x2A7\t1.0-1.1)：" + f1.l0().g(2) + "； 左后(0x271\t1.0-1.1)：" + f1.l0().g(3) + "； 右后(0x271\t2.0-2.1)：" + f1.l0().g(4);
                break;
            case "获取二档非L1P平台副驾座椅通风设置状态(0X41C 1.0-1.2) getSeatVentilatingNotL1PState":
                str = str + f1.l0().d1();
                break;
            case "获取二档座椅通风设置状态 getSeatVentilatingState2":
                str = str + " 左后(0x402 5.0-5.2)：" + f1.l0().e1(3) + "； 右后(0x42F 5.0-5.2)：" + f1.l0().e1(4);
                break;
            case "获取五档座椅加热状态 get5LevelSeatHeatingState":
                str = str + " 主驾(0x29E\t1.4-1.5)：" + f1.l0().d(1) + "； 副驾(0x2A7\t1.4-1.5)：" + f1.l0().d(2) + "； 左后(0x271\t1.4-1.5)：" + f1.l0().d(3) + "； 右后(0x271\t2.4-2.5)：" + f1.l0().d(4);
                break;
            case "获取五档座椅加热挡位 get5LevelSeatHeatingLevel":
                str = str + " 主驾(0x29E\t3.4-3.7)：" + f1.l0().b(1) + "； 副驾(0x2A7\t3.4-3.7)：" + f1.l0().b(2) + "； 左后(0x271\t5.4-5.7)：" + f1.l0().b(3) + "； 右后(0x271\t6.4-6.7)：" + f1.l0().b(4);
                break;
            case "获取二档非L1P平台副驾座椅加热设置状态(0X41C 1.3-1.5) getSeatHeatingNotL1PState":
                str = str + f1.l0().Z0();
                break;
            case "获取五档座椅通风控制模式 get5LevelSeatVentilatingMode":
                str = str + " 主驾(0x29E\t1.2-1.3)：" + f1.l0().f(1) + "； 副驾(0x2A7\t1.2-1.3)：" + f1.l0().f(2) + "； 左后(0x271\t1.2-1.3)：" + f1.l0().f(3) + "； 右后(0x271\t2.2-2.3)：" + f1.l0().f(4);
                break;
        }
        this.j.setText(str);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.demo_activity_seat;
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void V() {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (x0(currentFocus, motionEvent)) {
                s0(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void f0() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.oa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f668a.D0(view);
            }
        });
        v0();
        w0();
        this.h = (EditText) findViewById(R.id.get_edittext1);
        this.j = (TextView) findViewById(R.id.text);
        this.k = (TextView) findViewById(R.id.set_text);
        this.i = (EditText) findViewById(R.id.set_edittext1);
        this.l = (RecyclerView) findViewById(R.id.callback_recyclerview);
        u0();
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public final void s0(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    public void t0() {
        this.d.add(new ta("获取二档方向盘加热功能状态(0x429 1.6-1.7) getSteeringWheelHeatingState"));
        this.d.add(new ta("获取二档座椅通风设置状态 getSeatVentilatingState"));
        this.d.add(new ta("获取二档座椅通风设置状态 getSeatVentilatingState2"));
        this.d.add(new ta("获取左域平台配置(0x294 5.2-5.4) getLeftPlatformConfigs"));
        this.d.add(new ta("获取二档非L1P平台副驾座椅通风设置状态(0X41C 1.0-1.2) getSeatVentilatingNotL1PState"));
        this.d.add(new ta("获取二档座椅加热设置状态 getSeatHeatingState"));
        this.d.add(new ta("获取二档座椅加热设置状态 getSeatHeatingState2"));
        this.d.add(new ta("获取二档非L1P平台副驾座椅加热设置状态(0X41C 1.3-1.5) getSeatHeatingNotL1PState"));
        this.d.add(new ta("获取五档方向盘加热档位(0x429 3.1-3.4) getSteeringWheelHeatingGear"));
        this.d.add(new ta("获取五档座椅加热挡位 get5LevelSeatHeatingLevel"));
        this.d.add(new ta("获取五档座椅通风挡位 get5LevelSeatVentilatingLevel"));
        this.d.add(new ta("获取五档座椅加热控制模式 get5LevelSeatHeatingMode"));
        this.d.add(new ta("获取五档座椅通风控制模式 get5LevelSeatVentilatingMode"));
        this.d.add(new ta("获取五档座椅加热状态 get5LevelSeatHeatingState"));
        this.d.add(new ta("获取五档座椅通风状态 get5LevelSeatVentilatingState"));
        this.d.add(new ta("获取五档方向盘加热自动模式状态(0x429 2.6-2.7) getSteeringWheelHeatingAutoState"));
        this.d.add(new ta("获取转向盘加热方案(0x429 7.0-7.2)getSteeringWheelHeatedPlan"));
        this.e.add(new ta("设置二档方向盘加热功能状态(0x385 6.5-6.6) setSteeringWheelHeatingState"));
        this.e.add(new ta("设置二档座椅通风状态 setSeatVentilatingState"));
        this.e.add(new ta("设置二档座椅加热状态 setSeatHeatingState"));
        this.e.add(new ta("设置蓝牙通话状态(0x1DE 4.0-4.1) setCallState"));
        this.e.add(new ta("设置五档档设置方向盘加热档位(0x4C1子ID:0x22 2.2-2.5) setSteeringWheelHeatingGear"));
        this.e.add(new ta("设置五档座椅加热挡位 set5LevelSeatHeatingLevel"));
        this.e.add(new ta("设置五档座椅通风挡位 set5LevelSeatVentilatingLevel"));
        this.e.add(new ta("设置五档座椅加热控制模式 set5LevelSeatHeatingMode"));
        this.e.add(new ta("设置五档座椅通风控制模式 set5LevelSeatVentilatingMode"));
        this.e.add(new ta("设置五档座椅加热状态 set5LevelSeatHeatingState"));
        this.e.add(new ta("设置五档座椅通风状态 set5LevelSeatVentilatingState"));
        this.e.add(new ta("设置五档方向盘加热自动模式状态(0x4C1子ID:0x22 2.0-2.1) setSteeringWheelHeatingAutoState"));
    }

    public final void u0() {
        this.l.setLayoutManager(new LinearLayoutManager(this, 1, true));
        CopyOnWriteArrayList<ta> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.m = copyOnWriteArrayList;
        CallbackAdapter callbackAdapter = new CallbackAdapter(this, copyOnWriteArrayList);
        this.n = callbackAdapter;
        this.l.setAdapter(callbackAdapter);
    }

    public final void v0() {
        BYDAutoSettingDevice.getInstance(this).registerListener((AbsBYDAutoSettingListener) new a());
    }

    public final void w0() {
        this.f1043b = (RecyclerView) findViewById(R.id.get_recyclerview);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(this);
        flexboxLayoutManager.U(0);
        flexboxLayoutManager2.V(1);
        flexboxLayoutManager2.U(0);
        flexboxLayoutManager.V(1);
        this.f1043b.setLayoutManager(flexboxLayoutManager);
        this.d = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.set_recyclerview);
        this.f1044c = recyclerView;
        recyclerView.setLayoutManager(flexboxLayoutManager2);
        this.e = new ArrayList();
        t0();
        this.f = new CommonAdapter(this, this.d);
        this.g = new CommonAdapter(this, this.e);
        this.f1043b.setAdapter(this.f);
        this.f1044c.setAdapter(this.g);
        this.f.c(new CommonAdapter.b() { // from class: b.a.a.i.h.pa
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f691a.z0(taVar, i);
            }
        });
        this.g.c(new CommonAdapter.b() { // from class: b.a.a.i.h.qa
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f714a.B0(taVar, i);
            }
        });
    }

    public final boolean x0(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }
}
