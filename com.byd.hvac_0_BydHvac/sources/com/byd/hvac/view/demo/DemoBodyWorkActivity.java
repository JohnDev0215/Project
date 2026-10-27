package com.byd.hvac.view.demo;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
public class DemoBodyWorkActivity extends BaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1025b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView f1026c;
    public List<ta> d;
    public List<ta> e;
    public CommonAdapter f;
    public CommonAdapter g;
    public EditText h;
    public EditText i;
    public BYDAutoBodyworkDevice j;
    public BYDAutoOtaDevice k;
    public String l = BYDAutoBodyworkDevice.class.getSimpleName();
    public TextView m;
    public TextView n;
    public RecyclerView o;
    public CopyOnWriteArrayList<ta> p;
    public CallbackAdapter q;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a extends AbsBYDAutoOtaListener {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i) {
            ta taVar = new ta("onLFDoorLockStateChanged ");
            taVar.c("state = " + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
        public void onLFDoorLockStateChanged(final int i) {
            super.onLFDoorLockStateChanged(i);
            BydLogHelper.logD("onLFDoorLockStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f336a.b(i);
                }
            });
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b extends AbsBYDAutoBodyworkListener {
        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B(int i) {
            ta taVar = new ta("onCarWindowAntiPinchConfigChanged ");
            taVar.c("config=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B0(int i) {
            ta taVar = new ta("onRightSlideDoorActionChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D(int i) {
            ta taVar = new ta("onDoorLockStatusChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D0(int i) {
            ta taVar = new ta("onRightSlideDoorPercentChanged ");
            taVar.c("value=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F(int i, int i2) {
            ta taVar = new ta("onDoorStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F0(int i) {
            ta taVar = new ta("onRightSlideDoorStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H(int i) {
            ta taVar = new ta("onElecSlideDoorStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H0(int i) {
            ta taVar = new ta("onRightStrutMorotRunningStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J(int i) {
            ta taVar = new ta("onEnergyTypeChanged ");
            taVar.c("type=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J0(int i) {
            ta taVar = new ta("onSeatModeWakeupFeedbackStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L(int i) {
            ta taVar = new ta("onFuelElecLowPowerChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L0(int i) {
            ta taVar = new ta("onShiftModeChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N(int i, int i2) {
            ta taVar = new ta("onInternalDoorHandleStateChanged ");
            taVar.c("area=" + i + " --- state=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N0(int i) {
            ta taVar = new ta("onSmartVoiceLimitChanged ");
            taVar.c("value=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P(int i) {
            ta taVar = new ta("onLeftSlideDoorActionChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P0(double d, int i, int i2) {
            ta taVar = new ta("onSteeringWheelAngleChanged ");
            taVar.c("value=" + d + " - - sensorState=" + i + " - - calibrationState=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R(int i) {
            ta taVar = new ta("onLeftSlideDoorPercentChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R0(double d, int i, int i2) {
            ta taVar = new ta("onSteeringWheelSpeedChanged ");
            taVar.c("value=" + d + " - - sensorState=" + i + " - - calibrationState=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T(int i) {
            ta taVar = new ta("onLeftSlideDoorStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T0(int i, double d) {
            ta taVar = new ta("onSteeringWheelValueChanged ");
            taVar.c("type=" + i + " - - value=" + d);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V(int i) {
            ta taVar = new ta("onLeftStrutMorotRunningStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V0(int i) {
            ta taVar = new ta("onSunroofCloseNoticeChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X(int i) {
            ta taVar = new ta("onLightOff ");
            taVar.c("type=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X0(int i) {
            ta taVar = new ta("onSunroofInitStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z(int i) {
            ta taVar = new ta("onLightOn ");
            taVar.c("type=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z0(int i) {
            ta taVar = new ta("onSunroofPositionChanged ");
            taVar.c("value=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i) {
            ta taVar = new ta("onAlarmStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b0(int i, int i2) {
            ta taVar = new ta("onMainSaveOrRecoverStateChanged ");
            taVar.c("canValue = " + i + " ---- value = " + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b1(int i) {
            ta taVar = new ta("onSunroofStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(int i) {
            ta taVar = new ta("onAutoExternalRearMirrorFoldActionStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d0(int i) {
            ta taVar = new ta("onMoonRoofConfigChanged ");
            taVar.c("config=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d1(int i) {
            ta taVar = new ta("onSunroofWindowblindPositionChanged ");
            taVar.c("value=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(int i) {
            ta taVar = new ta("onAutoExternalRearMirrorFoldedState ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f0(int i) {
            ta taVar = new ta("onPowerDayModeChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f1(int i, float f, float f2) {
            ta taVar = new ta("onTurnRearviewMirrorChanged ");
            taVar.c("action=" + i + "  - - - valueH=" + f + "  - - - valueV=" + f2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(int i) {
            ta taVar = new ta("onAutoSystemStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h0(int i) {
            ta taVar = new ta("onPowerLevelChanged ");
            taVar.c("level=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h1(int i) {
            ta taVar = new ta("onWindoblindInitStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(String str) {
            ta taVar = new ta("onAutoVINChanged ");
            taVar.c("vin=" + str);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j0(int i) {
            ta taVar = new ta("onRainCloseWindowChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j1(int i, int i2) {
            ta taVar = new ta("onWindowControlStateChanged ");
            taVar.c("area=" + i + " - - state = " + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l(int i) {
            ta taVar = new ta("onBCMVechileStatusChanged ");
            taVar.c("value=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l0(int i) {
            ta taVar = new ta("onReadyIndicatorLightStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l1(int i, int i2) {
            ta taVar = new ta("onWindowInitStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n(int i) {
            ta taVar = new ta("onBackDoorAntiPlayModeChanged ");
            taVar.c("mode=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n0(int i) {
            ta taVar = new ta("onRearLampLightStatusChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n1(int i, int i2) {
            ta taVar = new ta("onWindowLockStateChanged ");
            taVar.c("area=" + i + "  - - - state=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p(int i) {
            ta taVar = new ta("onBackDoorCurStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p0(int i) {
            ta taVar = new ta("onRearWindowblindInitStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p1(int i, int i2) {
            ta taVar = new ta("onWindowOpenPercentChanged ");
            taVar.c("area=" + i + " - - percent=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r(int i) {
            ta taVar = new ta("onBackDoorInitStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r0(int i) {
            ta taVar = new ta("onRearWindowblindPositionChanged ");
            taVar.c("position=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r1(int i) {
            ta taVar = new ta("onWindowPermitStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t(int i) {
            ta taVar = new ta("onBackDoorPositionChanged ");
            taVar.c("value=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t0(int i) {
            ta taVar = new ta("onRearWindowblindStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t1(int i, int i2) {
            ta taVar = new ta("onWindowStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v(double d) {
            ta taVar = new ta("onBatteryPowerHEVChanged ");
            taVar.c("value=" + d);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v0(int i, int i2, int i3, int i4) {
            ta taVar = new ta("onRecoverOrSaveParamsChanged ");
            taVar.c("position = " + i + " ---- location = " + i2 + " ---- state = " + i3 + " ---- code = " + i4);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v1(int i, int i2) {
            ta taVar = new ta("onWindowThermalProtectionStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x(int i) {
            ta taVar = new ta("onBatteryVoltageLevelChanged ");
            taVar.c("level=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x0(int i) {
            ta taVar = new ta("onRemoteWhistleChange ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x1(int i) {
            ta taVar = new ta("onWindowblindStateChanged ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z(int i) {
            ta taVar = new ta("onBatteryVoltageLevelLowTips ");
            taVar.c("value=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z0(int i) {
            ta taVar = new ta("onRemoteWhistleFail ");
            taVar.c("state=" + i);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z1(int i, float f, float f2) {
            ta taVar = new ta("turnSteeringWheelChanged ");
            taVar.c("action = " + i + " ---- valueH = " + f + " ---- valueV = " + f2);
            DemoBodyWorkActivity.this.p.add(taVar);
            DemoBodyWorkActivity.this.q.c(DemoBodyWorkActivity.this.p);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onAlarmStateChanged(final int i) {
            super.onAlarmStateChanged(i);
            BydLogHelper.logD("onAlarmStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f515a.b(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onAutoExternalRearMirrorFoldActionStateChanged(final int i) {
            super.onAutoExternalRearMirrorFoldActionStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m
                @Override // java.lang.Runnable
                public final void run() {
                    this.f593a.d(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onAutoExternalRearMirrorFoldedState(final int i) {
            super.onAutoExternalRearMirrorFoldedState(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f624a.f(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onAutoSystemStateChanged(final int i) {
            super.onAutoSystemStateChanged(i);
            BydLogHelper.logD("onAutoSystemStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t
                @Override // java.lang.Runnable
                public final void run() {
                    this.f760a.h(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onAutoVINChanged(final String str) {
            super.onAutoVINChanged(str);
            BydLogHelper.logD("onAutoVINChanged: vin = " + str);
            i.d(new Runnable() { // from class: b.a.a.i.h.y0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f868a.j(str);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBCMVechileStatusChanged(final int i) {
            super.onBCMVechileStatusChanged(i);
            BydLogHelper.logD("onBCMVechileStatusChanged: value = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.u0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f784a.l(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBackDoorAntiPlayModeChanged(final int i) {
            super.onBackDoorAntiPlayModeChanged(i);
            BydLogHelper.logD("onBackDoorAntiPlayModeChanged: mode = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h
                @Override // java.lang.Runnable
                public final void run() {
                    this.f465a.n(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBackDoorCurStateChanged(final int i) {
            super.onBackDoorCurStateChanged(i);
            BydLogHelper.logD("onBackDoorCurStateChanged state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f545a.p(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBackDoorInitStateChanged(final int i) {
            super.onBackDoorInitStateChanged(i);
            BydLogHelper.logD("onBackDoorInitStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f489a.r(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBackDoorPositionChanged(final int i) {
            super.onBackDoorPositionChanged(i);
            BydLogHelper.logD("onBackDoorPositionChanged: value = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f805a.t(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBatteryPowerHEVChanged(final double d) {
            super.onBatteryPowerHEVChanged(d);
            BydLogHelper.logD("onBatteryPowerHEVChanged: value = " + d);
            i.d(new Runnable() { // from class: b.a.a.i.h.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f782a.v(d);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBatteryVoltageLevelChanged(final int i) {
            super.onBatteryVoltageLevelChanged(i);
            BydLogHelper.logD("onBatteryVoltageLevelChanged: level = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f572a.x(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onBatteryVoltageLevelLowTips(final int i) {
            super.onBatteryVoltageLevelLowTips(i);
            BydLogHelper.logD("onBatteryVoltageLevelLowTips: value = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f494a.z(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onCarWindowAntiPinchConfigChanged(final int i) {
            super.onCarWindowAntiPinchConfigChanged(i);
            BydLogHelper.logD("onCarWindowAntiPinchConfigChanged: config = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f517a.B(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorLockStatusChanged(int i, final int i2) {
            super.onDoorLockStatusChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f388a.D(i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(final int i, final int i2) {
            super.onDoorStateChanged(i, i2);
            BydLogHelper.logD("onDoorStateChanged: area = " + i + " ---- state = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.h1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f469a.F(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onElecSlideDoorStateChanged(final int i) {
            super.onElecSlideDoorStateChanged(i);
            BydLogHelper.logD("onElecSlideDoorStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f823a.H(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onEnergyTypeChanged(final int i) {
            super.onEnergyTypeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f889a.J(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onFuelElecLowPowerChanged(final int i) {
            super.onFuelElecLowPowerChanged(i);
            BydLogHelper.logD("onFuelElecLowPowerChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f519a.L(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int i, int i2) {
            super.onHasMessageChanged(i, i2);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onInternalDoorHandleStateChanged(final int i, final int i2) {
            super.onInternalDoorHandleStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f540a.N(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onLeftSlideDoorActionChanged(final int i) {
            super.onLeftSlideDoorActionChanged(i);
            BydLogHelper.logD("onLeftSlideDoorActionChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f314a.P(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onLeftSlideDoorPercentChanged(final int i) {
            super.onLeftSlideDoorPercentChanged(i);
            BydLogHelper.logD("onLeftSlideDoorPercentChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f739a.R(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onLeftSlideDoorStateChanged(final int i) {
            super.onLeftSlideDoorStateChanged(i);
            BydLogHelper.logD("onLeftSlideDoorStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f392a.T(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onLeftStrutMorotRunningStateChanged(final int i) {
            super.onLeftStrutMorotRunningStateChanged(i);
            BydLogHelper.logD("onLeftStrutMorotRunningStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f467a.V(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onLightOff(final int i) {
            super.onLightOff(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f419a.X(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onLightOn(final int i) {
            super.onLightOn(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f440a.Z(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onMainSaveOrRecoverStateChanged(final int i, final int i2) {
            super.onMainSaveOrRecoverStateChanged(i, i2);
            BydLogHelper.logD("onMainSaveOrRecoverStateChanged: canValue = " + i + " ---- value = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.l0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f569a.b0(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onMessage5sOnlineStateChanged(int i, int i2) {
            super.onMessage5sOnlineStateChanged(i, i2);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onMoonRoofConfigChanged(final int i) {
            super.onMoonRoofConfigChanged(i);
            BydLogHelper.logD("onMoonRoofConfigChanged: config = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f417a.d0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerDayModeChanged(final int i) {
            super.onPowerDayModeChanged(i);
            BydLogHelper.logD("onPowerDayModeChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f825a.f0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(final int i) {
            super.onPowerLevelChanged(i);
            BydLogHelper.logD("onPowerLevelChanged: level = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f543a.h0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRainCloseWindowChanged(final int i) {
            super.onRainCloseWindowChanged(i);
            BydLogHelper.logD("onRainCloseWindowChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f762a.j0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onReadyIndicatorLightStateChanged(final int i) {
            super.onReadyIndicatorLightStateChanged(i);
            BydLogHelper.logD("onReadyIndicatorLightStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.q0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f695a.l0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRearLampLightStatusChanged(final int i) {
            super.onRearLampLightStatusChanged(i);
            BydLogHelper.logD("onRearLampLightStatusChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p
                @Override // java.lang.Runnable
                public final void run() {
                    this.f669a.n0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRearWindowblindInitStateChanged(final int i) {
            super.onRearWindowblindInitStateChanged(i);
            BydLogHelper.logD("onRearWindowblindInitStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f717a.p0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRearWindowblindPositionChanged(final int i) {
            super.onRearWindowblindPositionChanged(i);
            BydLogHelper.logD("onRearWindowblindPositionChanged: position = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f341a.r0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRearWindowblindStateChanged(final int i) {
            super.onRearWindowblindStateChanged(i);
            BydLogHelper.logD("onRearWindowblindStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f595a.t0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRecoverOrSaveParamsChanged(final int i, final int i2, final int i3, final int i4) {
            super.onRecoverOrSaveParamsChanged(i, i2, i3, i4);
            BydLogHelper.logD("onRecoverOrSaveParamsChanged: position = " + i + " ---- location = " + i2 + " ---- state = " + i3 + " ---- code = " + i4);
            i.d(new Runnable() { // from class: b.a.a.i.h.o0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f647a.v0(i, i2, i3, i4);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRemoteWhistleChange(final int i) {
            super.onRemoteWhistleChange(i);
            BydLogHelper.logD("onRemoteWhistleChange: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f619a.x0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRemoteWhistleFail(final int i) {
            super.onRemoteWhistleFail(i);
            BydLogHelper.logD("onRemoteWhistleFail: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f846a.z0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRightSlideDoorActionChanged(final int i) {
            super.onRightSlideDoorActionChanged(i);
            BydLogHelper.logD("onRightSlideDoorActionChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f597a.B0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRightSlideDoorPercentChanged(final int i) {
            super.onRightSlideDoorPercentChanged(i);
            BydLogHelper.logD("onRightSlideDoorPercentChanged:  value = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r
                @Override // java.lang.Runnable
                public final void run() {
                    this.f715a.D0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRightSlideDoorStateChanged(final int i) {
            super.onRightSlideDoorStateChanged(i);
            BydLogHelper.logD("onRightSlideDoorStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z
                @Override // java.lang.Runnable
                public final void run() {
                    this.f887a.F0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onRightStrutMorotRunningStateChanged(final int i) {
            super.onRightStrutMorotRunningStateChanged(i);
            BydLogHelper.logD("onRightStrutMorotRunningStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f866a.H0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSeatModeWakeupFeedbackStateChanged(final int i) {
            super.onSeatModeWakeupFeedbackStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v
                @Override // java.lang.Runnable
                public final void run() {
                    this.f803a.J0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onShiftModeChanged(final int i) {
            super.onShiftModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f671a.L0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSmartVoiceLimitChanged(final int i) {
            super.onSmartVoiceLimitChanged(i);
            BydLogHelper.logD("onSmartVoiceLimitChanged: value = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f650a.N0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSteeringWheelAngleChanged(final double d, final int i, final int i2) {
            super.onSteeringWheelAngleChanged(d, i, i2);
            BydLogHelper.logD("onSteeringWheelAngleChanged: value = " + d + " ---- sensorState = " + i + " ---- calibrationState = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.x
                @Override // java.lang.Runnable
                public final void run() {
                    this.f843a.P0(d, i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSteeringWheelSpeedChanged(final double d, final int i, final int i2) {
            super.onSteeringWheelSpeedChanged(d, i, i2);
            BydLogHelper.logD("onSteeringWheelSpeedChanged: value = " + d + " ---- sensorState = " + i + " ---- calibrationState = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f692a.R0(d, i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSteeringWheelValueChanged(final int i, final double d) {
            super.onSteeringWheelValueChanged(i, d);
            BydLogHelper.logD("onSteeringWheelValueChanged: type = " + i + " ---- value = " + d);
            i.d(new Runnable() { // from class: b.a.a.i.h.c0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f338a.T0(i, d);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSunroofCloseNoticeChanged(final int i) {
            super.onSunroofCloseNoticeChanged(i);
            BydLogHelper.logD("onSunroofCloseNoticeChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f442a.V0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSunroofInitStateChanged(final int i) {
            super.onSunroofInitStateChanged(i);
            BydLogHelper.logD("onSunroofInitStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f444a.X0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSunroofPositionChanged(final int i) {
            super.onSunroofPositionChanged(i);
            BydLogHelper.logD("onSunroofPositionChanged: value = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f673a.Z0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSunroofStateChanged(final int i) {
            super.onSunroofStateChanged(i);
            BydLogHelper.logD("onSunroofStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f366a.b1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onSunroofWindowblindPositionChanged(final int i) {
            super.onSunroofWindowblindPositionChanged(i);
            BydLogHelper.logD("onSunroofWindowblindPositionChanged: value = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o
                @Override // java.lang.Runnable
                public final void run() {
                    this.f645a.d1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onTurnRearviewMirrorChanged(final int i, final float f, final float f2) {
            super.onTurnRearviewMirrorChanged(i, f, f2);
            i.d(new Runnable() { // from class: b.a.a.i.h.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f566a.f1(i, f, f2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindoblindInitStateChanged(final int i) {
            super.onWindoblindInitStateChanged(i);
            BydLogHelper.logD("onWindoblindInitStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f284a.h1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowControlStateChanged(final int i, final int i2) {
            super.onWindowControlStateChanged(i, i2);
            BydLogHelper.logD("onWindowControlStateChanged: area = " + i + " -- state = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f414a.j1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowInitStateChanged(final int i, final int i2) {
            super.onWindowInitStateChanged(i, i2);
            BydLogHelper.logD("onWindowInitStateChanged: area = " + i + " ---- state = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.a1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f286a.l1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowLockStateChanged(final int i, final int i2) {
            super.onWindowLockStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.n0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f621a.n1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowOpenPercentChanged(final int i, final int i2) {
            super.onWindowOpenPercentChanged(i, i2);
            BydLogHelper.logD("onWindowOpenPercentChanged: area = " + i + " ---- percent = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.d1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f368a.p1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowPermitStateChanged(final int i) {
            super.onWindowPermitStateChanged(i);
            BydLogHelper.logD("onWindowPermitStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f390a.r1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowStateChanged(final int i, final int i2) {
            super.onWindowStateChanged(i, i2);
            BydLogHelper.logD("onWindowStateChanged: area = " + i + " ---- state = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.i0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f491a.t1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowThermalProtectionStateChanged(final int i, final int i2) {
            super.onWindowThermalProtectionStateChanged(i, i2);
            BydLogHelper.logD("onWindowThermalProtectionStateChanged: area = " + i + " ---- state = " + i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.b0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f311a.v1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onWindowblindStateChanged(final int i) {
            super.onWindowblindStateChanged(i);
            BydLogHelper.logD("onWindowblindStateChanged: state = " + i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f741a.x1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void turnSteeringWheelChanged(final int i, final float f, final float f2) {
            super.turnSteeringWheelChanged(i, f, f2);
            BydLogHelper.logD("turnSteeringWheelChanged: action = " + i + " ---- valueH = " + f + " ---- valueV = " + f2);
            i.d(new Runnable() { // from class: b.a.a.i.h.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f363a.z1(i, f, f2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: w0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void x0(ta taVar, int i) {
        int i2;
        String str;
        Editable text = this.h.getText();
        i2 = (text == null || TextUtils.isEmpty(text.toString())) ? 0 : Integer.parseInt(text.toString());
        String str2 = taVar.f780a;
        str2.hashCode();
        switch (str2) {
            case "获取主驾驶位置是否支持后视镜调节 getRearviewMirrorSupport":
                str = taVar.f780a + " : =  " + this.j.getRearviewMirrorSupport();
                break;
            case "获取油量电量低信息 getFuelElecLowPower":
                str = taVar.f780a + " : =  " + this.j.getFuelElecLowPower();
                break;
            case "获取左滑门动作状态getLeftSlideDoorAction":
                str = "获取左滑门动作状态getLeftSlideDoorAction =  " + this.j.getLeftSlideDoorAction();
                break;
            case "获取后背门初始化状态getBackDoorInitState":
                str = "获取后背门初始化状态getBackDoorInitState =  " + this.j.getBackDoorInitState();
                break;
            case "获取主驾座椅恢复，保存状态 getMain2SaveOrRecoverState":
                str = taVar.f780a + " : =  " + this.j.getMain2SaveOrRecoverState();
                break;
            case "获取车辆状态 getBCMVechileStatus":
                str = taVar.f780a + " : =  " + this.j.getBCMVechileStatus();
                break;
            case "获取外后视镜折叠执行状态 getAutoExternalRearMirrorFoldActionState":
                str = taVar.f780a + " : =  " + this.j.getAutoExternalRearMirrorFoldActionState();
                break;
            case "获取虚拟车架号getAutoVIN":
                str = "获取虚拟车架号 getAutoVIN =  " + this.j.getAutoVIN();
                break;
            case "获取右侧滑门状态getRightSlideDoorState":
                str = "获取右侧滑门状态getRightSlideDoorState =  " + this.j.getRightSlideDoorState();
                break;
            case "获取外后视镜折叠状态 getAutoExternalRearMirrorFoldedState":
                str = taVar.f780a + " : =  " + this.j.getAutoExternalRearMirrorFoldedState();
                break;
            case "获取是否支持电动前仓盖功能 getElectricFrontHatchConfiguration":
                str = taVar.f780a + " : =  " + this.j.getElectricFrontHatchConfiguration();
                break;
            case "获取动力模式getEnergyType":
                str = "获取动力模式getEnergyType =  " + this.j.getEnergyType();
                break;
            case "获取油量电量低信息getFuelElecLowPower":
                str = "获取油量电量低信息getFuelElecLowPower =  " + this.j.getFuelElecLowPower();
                break;
            case "获取后遮阳帘当前动作状态getRearWindowblindState":
                str = "获取后遮阳帘当前动作状态getRearWindowblindState前 =  " + this.j.getRearWindowblindState();
                break;
            case "获取座椅四人舒享配置 getFourPeopleEnjoyConfig":
                str = taVar.f780a + " : =  " + this.j.getFourPeopleEnjoyConfig();
                break;
            case "车窗热保护状态getWindowThermalProtectionState":
                str = "车窗热保护状态getWindowThermalProtectionState:左前 =  " + this.j.getWindowThermalProtectionState(1) + "  - -  右前 = " + this.j.getWindowThermalProtectionState(2) + "  - -  左后 =  " + this.j.getWindowThermalProtectionState(3) + "  - -  右后 =  " + this.j.getWindowThermalProtectionState(4) + "  - -  天窗 =  " + this.j.getWindowThermalProtectionState(5) + "  - -  前遮阳帘 =  " + this.j.getWindowThermalProtectionState(6) + "  - -  后遮阳帘 =  " + this.j.getWindowThermalProtectionState(7);
                break;
            case "获取天窗初始化状态getSunroofInitState":
                str = "获取天窗初始化状态getSunroofInitState =  " + this.j.getSunroofInitState();
                break;
            case "获取方向盘垂直方向的位置 getSteeringWheelY":
                str = taVar.f780a + " : =  " + this.j.getSteeringWheelY();
                break;
            case "获取车型getAutoType":
                str = "获取车型 getAutoType =  " + this.j.getAutoType();
                break;
            case "获取后背门当前位置getBackDoorCurState":
                str = "获取后背门当前位置getBackDoorCurState =  " + this.j.getBackDoorCurState();
                break;
            case "获取方向盘角度/速度信息getSteeringWheelValue":
                str = "获取方向盘角度/速度信息getSteeringWheelValue 角度 =  " + this.j.getSteeringWheelValue(1) + "  - -  速度 =  " + this.j.getSteeringWheelValue(2);
                break;
            case "获取蓄电池电压水平getBatteryVoltageLevel":
                str = "获取蓄电池电压水平getBatteryVoltageLevel =  " + this.j.getBatteryVoltageLevel();
                break;
            case "获取方向盘前后方向的位置 getSteeringWheelZ":
                str = taVar.f780a + " : =  " + this.j.getSteeringWheelZ();
                break;
            case "获取座椅腰撑配置 getSeatLumbarSupportConfig":
                str = taVar.f780a + " : 主驾座椅腰撑配置 =  " + this.j.getSeatLumbarSupportConfig(1) + "   ---  副驾座椅腰撑配置 = " + this.j.getSeatLumbarSupportConfig(2);
                break;
            case "获取左滑门打开位置百分比getLeftSlideDoorPercent":
                str = "获取左滑门打开位置百分比getLeftSlideDoorPercent =  " + this.j.getLeftSlideDoorPercent();
                break;
            case "车窗初始化状态getWindowInitState":
                str = "车窗初始化状态getWindowInitState:左前 =  " + this.j.getWindowInitState(1) + "  - -  右前 = " + this.j.getWindowInitState(2) + "  - -  左后 =  " + this.j.getWindowInitState(3) + "  - -  右后 =  " + this.j.getWindowInitState(4);
                break;
            case "获取车辆换挡模式 getShiftMode":
                str = taVar.f780a + " : =  " + this.j.getShiftMode();
                break;
            case "获取左/右后视镜上下翻转的位置 getRearviewMirrorY":
                str = taVar.f780a + " :左后视镜 =  " + this.j.getRearviewMirrorY(1) + "   ---  右后视镜 = " + this.j.getRearviewMirrorY(2);
                break;
            case "获取车身防盗状态getAntiTheftStatus":
                str = "获取车身防盗状态getAntiTheftStatus =  " + this.j.getAntiTheftStatus();
                break;
            case "获取READY指示灯状态getReadyIndicatorLightState":
                str = "获取READY指示灯状态getReadyIndicatorLightState: =  " + this.j.getReadyIndicatorLightState();
                break;
            case "获取左撑杆电机当前运行状态getLeftStrutMorotRunningState":
                str = "获取左撑杆电机当前运行状态getLeftStrutMorotRunningState =  " + this.j.getLeftStrutMorotRunningState();
                break;
            case "获取后背门实际位置信息getBackDoorPosition":
                BydLogHelper.logD("getBackDoorOpenedHeight() =  " + BYDAutoSettingDevice.getInstance(this).getBackDoorOpenedHeight());
                str = "获取后背门实际位置信息getBackDoorPosition =  " + this.j.getBackDoorPosition();
                break;
            case "获取左/右后视镜左右翻转的位置 getRearviewMirrorX":
                str = taVar.f780a + " :左后视镜 =  " + this.j.getRearviewMirrorX(1) + "   ---  右后视镜 = " + this.j.getRearviewMirrorX(2);
                break;
            case "获取右前，左后右后门锁状态 getDoorLockStatus":
                str = taVar.f780a + " : 左前门门锁状态 =  " + this.k.getLFDoorLockStatus() + "   ---  左后门门锁状态 = " + this.j.getDoorLockStatus(2) + "   ---  右前门门锁状态 = " + this.j.getDoorLockStatus(3) + "   ---  右后门门锁状态 = " + this.j.getDoorLockStatus(4);
                break;
            case "获取电源档位getPowerLevel":
                str = "获取电源档位getPowerLevel =  " + this.j.getPowerLevel();
                break;
            case "获取右滑门打开位置百分比getRightSlideDoorPercent":
                str = "获取右滑门打开位置百分比getRightSlideDoorPercent =  " + this.j.getRightSlideDoorPercent();
                break;
            case "获取离线状态hasMessage":
                str = "获取离线状态hasMessage: =  " + this.j.hasMessage(i2);
                break;
            case "获取天窗系统位置百分百控制标志位 getMoonRoofPercentControlFlag":
                str = taVar.f780a + " : =  " + this.j.getMoonRoofPercentControlFlag();
                break;
            case "获取天窗，遮阳窗帘配置getMoonRoofConfig":
                str = "获取天窗，遮阳窗帘配置getMoonRoofConfig =  " + this.j.getMoonRoofConfig();
                break;
            case "获取后遮阳帘位置信息反馈getRearWindowblindPosition":
                str = "获取后遮阳帘位置信息反馈getRearWindowblindPosition =  " + this.j.getRearWindowblindPosition();
                break;
            case "获取遮阳帘初始化状态getWindowblindInitState":
                str = "获取遮阳帘初始化状态getWindowblindInitState =  " + this.j.getWindowblindInitState();
                break;
            case "获取电动侧滑门功能状态getElecSlideDoorState":
                str = "获取电动侧滑门功能状态getElecSlideDoorState =  " + this.j.getElecSlideDoorState();
                break;
            case "获取车窗状态getWindowState":
                str = "获取车窗状态getWindowState:左前 =  " + this.j.getWindowState(1) + "  - -  右前 = " + this.j.getWindowState(2) + "  - -  左后 =  " + this.j.getWindowState(3) + "  - -  右后 =  " + this.j.getWindowState(4) + "  - -  天窗 =  " + this.j.getWindowState(5) + "  - -  遮阳帘 =  " + this.j.getWindowState(6);
                break;
            case "获取4门把手内位置开关信号状态 getInternalDoorHandleState ":
                str = taVar.f780a + " : 左前门把手内位置开关信号状态 =  " + this.j.getInternalDoorHandleState(1) + "   ---  右前门把手内位置开关信号状态 = " + this.j.getInternalDoorHandleState(2) + "   ---  左后门把手内位置开关信号状态 = " + this.j.getInternalDoorHandleState(3) + "   ---  右后门把手内位置开关信号状态 = " + this.j.getInternalDoorHandleState(4);
                break;
            case "获取背门防玩模式getBackDoorAntiPlayMode":
                str = "获取背门防玩模式getBackDoorAntiPlayMode =  " + this.j.getBackDoorAntiPlayMode();
                break;
            case "获取天窗当前动作状态getSunroofState":
                str = "获取天窗当前动作状态getSunroofState =  " + this.j.getSunroofState();
                break;
            case "获取整车状态getAutoSystemState":
                str = "获取整车状态 getAutoSystemState =  " + this.j.getAutoSystemState();
                break;
            case "获取左侧滑门状态getLeftSlideDoorState":
                str = "获取左侧滑门状态getLeftSlideDoorState =  " + this.j.getLeftSlideDoorState();
                break;
            case "获取车门、前舱盖、后舱盖状态getDoorState":
                str = "获取车门、前舱盖、后舱盖状态getDoorState:左前门 =  " + this.j.getDoorState(1) + "  - -  右前门 = " + this.j.getDoorState(2) + "  - -  左后门 =  " + this.j.getDoorState(3) + "  - -  右后门 =  " + this.j.getDoorState(4) + "  - -  发动机前舱盖 =  " + this.j.getDoorState(5) + "  - -  后舱盖 =  " + this.j.getDoorState(6);
                break;
            case "获取座椅模式唤醒反馈 getSeatModeWakeupFeedbackState":
                str = taVar.f780a + " : =  " + this.j.getSeatModeWakeupFeedbackState();
                break;
            case "获取天窗关闭提醒getSunroofCloseNotice":
                str = "获取天窗关闭提醒getSunroofCloseNotice =  " + this.j.getSunroofCloseNotice();
                break;
            case "获取主驾座椅恢复，保存状态 getMainSaveOrRecoverState":
                str = taVar.f780a + " : =  " + this.j.getMainSaveOrRecoverState();
                break;
            case "获取天窗，遮阳窗帘位置百分比getWindowOpenPercent":
                str = "获取天窗，遮阳窗帘位置百分比getWindowOpenPercent:左前 =  " + this.j.getWindowOpenPercent(1) + "  - -  右前 = " + this.j.getWindowOpenPercent(2) + "  - -  左后 =  " + this.j.getWindowOpenPercent(3) + "  - -  右后 =  " + this.j.getWindowOpenPercent(4) + "  - -  天窗 =  " + this.j.getWindowOpenPercent(5) + "  - -  遮阳帘 =  " + this.j.getWindowOpenPercent(6) + "  - -  后遮阳帘 =  " + this.j.getWindowOpenPercent(7);
                break;
            case "获取电动踏板配置 getElectricPedalConfig":
                str = taVar.f780a + " : =  " + this.j.getElectricPedalConfig();
                break;
            case "获取后遮阳帘初始化状态getRearWindowblindInitState":
                str = "获取后遮阳帘初始化状态getRearWindowblindInitState =  " + this.j.getRearWindowblindInitState();
                break;
            case "获取真实车架号getRealAutoVIN":
                str = "获取真实车架号 getRealAutoVIN =  " + this.j.getRealAutoVIN();
                break;
            case "获取车窗控制状态getWindowControlState":
                str = "获取车窗控制状态getWindowControlState:左前 =  " + this.j.getWindowControlState(1) + "  - -  右前 = " + this.j.getWindowControlState(2) + "  - -  左后 =  " + this.j.getWindowControlState(3) + "  - -  右后 =  " + this.j.getWindowControlState(4);
                break;
            case "获取遮阳帘当前动作状态getWindowblindState":
                str = "获取遮阳帘当前动作状态getWindowblindState =  " + this.j.getWindowblindState();
                break;
            case "获取后位灯开关状态 getRearLampLightState":
                str = taVar.f780a + " : =  " + this.j.getRearLampLightState();
                break;
            case "获取车窗防夹标识位 getWindowAntiPinchConfig":
                str = taVar.f780a + " : =  " + this.j.getWindowAntiPinchConfig();
                break;
            case "获取智能语音限制状态 getSmartVoiceLimit()":
                str = taVar.f780a + " : =  " + this.j.getSmartVoiceLimit();
                break;
            case "获取右滑门动作状态getRightSlideDoorAction":
                str = "获取右滑门动作状态getRightSlideDoorAction =  " + this.j.getRightSlideDoorAction();
                break;
            case "车窗百分比控制自学习 getWindowPercentageControlConfig":
                str = taVar.f780a + " : =  " + this.j.getWindowPercentageControlConfig();
                break;
            case "获取天窗位置信息反馈getSunroofPosition":
                str = "获取天窗位置信息反馈getSunroofPosition =  " + this.j.getSunroofPosition();
                break;
            case "获取尾门类型标志 getTailDoorTypeConfig":
                str = taVar.f780a + " : =  " + this.j.getTailDoorTypeConfig();
                break;
            case "获取右撑杆电机当前运行状态getRightStrutMorotRunningState":
                str = "获取右撑杆电机当前运行状态getRightStrutMorotRunningState =  " + this.j.getRightStrutMorotRunningState();
                break;
            case "获取遮阳帘位置信息反馈getSunroofWindowblindPosition":
                str = "获取遮阳帘位置信息反馈getSunroofWindowblindPosition =  " + this.j.getSunroofWindowblindPosition();
                break;
            case "获取车窗授权命令getWindowPermitState":
                str = "获取车窗授权命令getWindowPermitState =  " + this.j.getWindowPermitState();
                break;
            default:
                str = "";
                break;
        }
        this.m.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void z0(ta taVar, int i) {
        int i2;
        String str;
        Editable text = this.i.getText();
        i2 = (text == null || TextUtils.isEmpty(text.toString())) ? 0 : Integer.parseInt(text.toString());
        str = taVar.f780a;
        str.hashCode();
        switch (str) {
            case "四车门落锁（场景模式）setAllDoorsLockScene:":
                this.j.setAllDoorsLockScene(i2);
                BydLogHelper.logD("setAllDoorsLockScene() =  " + i2);
                str = str + i2;
                break;
            case "设置电动滑门功能开关状态 setElecSlideDoorState:":
                this.j.setElecSlideDoorState(i2);
                BydLogHelper.logD("setElecSlideDoorState() =  " + i2);
                str = str + i2;
                break;
            case "设置左前窗位置百分比setWindowOpenPercent(1):":
                this.j.setWindowOpenPercent(1, i2);
                str = str + "- -左前:" + i2;
                break;
            case "设置右前窗位置百分比setWindowOpenPercent(2):":
                BydLogHelper.logD("setWindowOpenPercent(2) =  " + i2);
                this.j.setWindowOpenPercent(2, i2);
                str = str + " - - 右前:" + i2;
                break;
            case "设置副驾座椅腰撑调节 setWaistSupportAdjustment:":
                this.j.setWaistSupportAdjustment(2, i2);
                BydLogHelper.logD("setWaistSupportAdjustment(2) =  " + i2);
                str = str + i2;
                break;
            case "天窗语音操控指令 voiceCtlMoonRoof:":
                this.j.voiceCtlMoonRoof(i2);
                BydLogHelper.logD("voiceCtlMoonRoof() =  " + i2);
                str = str + i2;
                break;
            case "设置四门门锁状态 setAllDoorsLock:":
                this.j.setAllDoorsLock(i2);
                BydLogHelper.logD("setAllDoorsLock() =  " + i2);
                str = str + i2;
                break;
            case "行李箱设置settingLuggage:":
                this.j.settingLuggage(i2);
                BydLogHelper.logD("settingLuggage() =  " + i2);
                str = str + i2;
                break;
            case "设置左后窗控制状态setWindowControlState(3):":
                BydLogHelper.logD("setWindowControlState(3) =  " + i2);
                this.j.setWindowControlState(3, i2);
                str = str + "  - -  左后:" + i2;
                break;
            case "设置右后窗控制状态setWindowControlState(4):":
                BydLogHelper.logD("setWindowControlState(4) =  " + i2);
                this.j.setWindowControlState(4, i2);
                str = str + "  - -  右后:" + i2;
                break;
            case "左前窗setWindowControlState(int[]):":
                BydLogHelper.logD("setWindowControlState(int[]) =  " + i2);
                this.j.setWindowControlState(new int[]{i2, 0, 0, 0});
                str = str + i2;
                break;
            case "设置车身防盗状态setAntiTheftStatus:":
                this.j.setAntiTheftStatus(i2);
                str = str + i2;
                BydLogHelper.logD("setAntiTheftStatus() =  " + i2);
                break;
            case "调整左右后视镜动作 turnRearviewMirror:":
                this.j.turnRearviewMirror(i2);
                BydLogHelper.logD("turnRearviewMirror() =  " + i2);
                str = str + i2;
                break;
            case "设置车窗状态setWindowsMode:":
                BydLogHelper.logD("setWindowsMode(int mode) =  " + i2);
                this.j.setWindowsMode(i2);
                str = str + i2;
                break;
            case "设置座椅模式控制命令 setSeatModeControl:":
                this.j.setSeatModeControl(i2);
                BydLogHelper.logD("setSeatModeControl =  " + i2);
                str = str + i2;
                break;
            case "左前窗+左后setWindowControlState(int[]):":
                BydLogHelper.logD("setWindowControlState(int[]) =  " + i2);
                this.j.setWindowControlState(new int[]{i2, 0, i2, 0});
                str = str + i2;
                break;
            case "设置左前窗控制状态setWindowControlState(1):":
                this.j.setWindowControlState(1, i2);
                str = str + " - - 左前:" + i2;
                break;
            case "遮阳帘语音操控指令 voiceCtlSunshadePanel:":
                this.j.voiceCtlSunshadePanel(i2);
                BydLogHelper.logD("voiceCtlSunshadePanel() =  " + i2);
                str = str + i2;
                break;
            case "设置遮阳窗帘位置百分比setWindowOpenPercent(6):":
                BydLogHelper.logD("setWindowOpenPercent(6) =  " + i2);
                this.j.setWindowOpenPercent(6, i2);
                str = str + " - - 遮阳帘:" + i2;
                break;
            case "设置右前窗控制状态setWindowControlState(2):":
                BydLogHelper.logD("setWindowControlState(2) =  " + i2);
                this.j.setWindowControlState(2, i2);
                str = str + "  - -  右前:" + i2;
                break;
            case "设置后遮阳窗帘位置百分比setWindowOpenPercent(7):":
                BydLogHelper.logD("setWindowOpenPercent(7) =  " + i2);
                this.j.setWindowOpenPercent(7, i2);
                str = str + " - - 后遮阳帘:" + i2;
                break;
            case "一键设置多个车窗setWindowControlState(int[]):":
                BydLogHelper.logD("setWindowControlState(int[]) =  " + i2);
                this.j.setWindowControlState(new int[]{i2, i2, i2, i2});
                str = str + i2;
                break;
            case "设置外后视镜折叠的转态 setAutoExternalRearMirrorFoldState:":
                this.j.setAutoExternalRearMirrorFoldState(i2);
                BydLogHelper.logD("setAutoExternalRearMirrorFoldState() =  " + i2);
                str = str + i2;
                break;
            case "设置后背门目标开启高度setBackDoorHeightPosition:":
                this.j.setBackDoorHeightPosition(i2);
                BydLogHelper.logD("setBackDoorHeightPosition() =  " + i2);
                str = str + i2;
                break;
            case "设置天窗，遮阳窗帘状态setSunroofAndSunshade:":
                BydLogHelper.logD("setSunroofAndSunshade() =  " + i2);
                this.j.setSunroofAndSunshade(i2);
                str = str + " - - 后遮阳帘:" + i2;
                break;
            case "方向盘位置调节 turnSteeringWheel:":
                this.j.turnSteeringWheel(i2);
                BydLogHelper.logD("turnSteeringWheel() =  " + i2);
                str = str + i2;
                break;
            case "设置左后窗位置百分比setWindowOpenPercent(3):":
                BydLogHelper.logD("setWindowOpenPercent(3) =  " + i2);
                this.j.setWindowOpenPercent(3, i2);
                str = str + " - - 左后:" + i2;
                break;
            case "设置右后窗位置百分比setWindowOpenPercent(4):":
                BydLogHelper.logD("setWindowOpenPercent(4) =  " + i2);
                this.j.setWindowOpenPercent(4, i2);
                str = str + " - - 右后:" + i2;
                break;
            case "设置主驾座椅腰撑调节 setWaistSupportAdjustment:":
                this.j.setWaistSupportAdjustment(1, i2);
                BydLogHelper.logD("setWaistSupportAdjustment(1) =  " + i2);
                str = str + i2;
                break;
            case "左侧电动滑门语音控制命令setLeftSlideDoorVoiceControlState:":
                this.j.setLeftSlideDoorVoiceControlState(i2);
                BydLogHelper.logD("setLeftSlideDoorVoiceControlState() =  " + i2);
                str = str + i2;
                break;
            case "右侧电动滑门语音控制命令setRightSlideDoorVoiceControlState:":
                this.j.setRightSlideDoorVoiceControlState(i2);
                BydLogHelper.logD("setRightSlideDoorVoiceControlState() =  " + i2);
                str = str + i2;
                break;
            case "设置电动后背门控制状态setElecBackDoorControlState:":
                this.j.setElecBackDoorControlState(i2);
                BydLogHelper.logD("setElecBackDoorControlState() =  " + i2);
                str = str + i2;
                break;
            case "设置天窗位置百分比setWindowOpenPercent(5):":
                BydLogHelper.logD("setWindowOpenPercent(5) =  " + i2);
                this.j.setWindowOpenPercent(5, i2);
                str = str + " - - 天窗:" + i2;
                break;
        }
        this.n.setText(str);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.demo_activity_bodywork;
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void V() {
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void f0() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.q1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f697a.B0(view);
            }
        });
        this.j = BYDAutoBodyworkDevice.getInstance(this);
        this.k = BYDAutoOtaDevice.getInstance(this);
        t0();
        v0();
        this.h = (EditText) findViewById(R.id.get_edittext1);
        this.i = (EditText) findViewById(R.id.set_edittext1);
        this.m = (TextView) findViewById(R.id.text);
        this.n = (TextView) findViewById(R.id.set_text);
        this.o = (RecyclerView) findViewById(R.id.callback_recyclerview);
        u0();
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void s0() {
        this.d.add(new ta("获取离线状态hasMessage"));
        this.d.add(new ta("获取真实车架号getRealAutoVIN"));
        this.d.add(new ta("获取虚拟车架号getAutoVIN"));
        this.d.add(new ta("获取车型getAutoType"));
        this.d.add(new ta("获取整车状态getAutoSystemState"));
        this.d.add(new ta("获取车门、前舱盖、后舱盖状态getDoorState"));
        this.d.add(new ta("获取车窗状态getWindowState"));
        this.d.add(new ta("获取车窗控制状态getWindowControlState"));
        this.d.add(new ta("获取天窗，遮阳窗帘位置百分比getWindowOpenPercent"));
        this.d.add(new ta("获取蓄电池电压水平getBatteryVoltageLevel"));
        this.d.add(new ta("获取电源档位getPowerLevel"));
        this.d.add(new ta("获取方向盘角度/速度信息getSteeringWheelValue"));
        this.d.add(new ta("获取油量电量低信息getFuelElecLowPower"));
        this.d.add(new ta("获取天窗，遮阳窗帘配置getMoonRoofConfig"));
        this.d.add(new ta("获取动力模式getEnergyType"));
        this.d.add(new ta("获取天窗当前动作状态getSunroofState"));
        this.d.add(new ta("获取车窗授权命令getWindowPermitState"));
        this.d.add(new ta("获取遮阳帘初始化状态getWindowblindInitState"));
        this.d.add(new ta("获取天窗初始化状态getSunroofInitState"));
        this.d.add(new ta("获取天窗关闭提醒getSunroofCloseNotice"));
        this.d.add(new ta("获取遮阳帘位置信息反馈getSunroofWindowblindPosition"));
        this.d.add(new ta("获取天窗位置信息反馈getSunroofPosition"));
        this.d.add(new ta("获取车身防盗状态getAntiTheftStatus"));
        this.d.add(new ta("获取遮阳帘当前动作状态getWindowblindState"));
        this.d.add(new ta("获取后遮阳帘当前动作状态getRearWindowblindState"));
        this.d.add(new ta("获取后遮阳帘初始化状态getRearWindowblindInitState"));
        this.d.add(new ta("获取后遮阳帘位置信息反馈getRearWindowblindPosition"));
        this.d.add(new ta("获取后背门初始化状态getBackDoorInitState"));
        this.d.add(new ta("获取后背门当前位置getBackDoorCurState"));
        this.d.add(new ta("获取右撑杆电机当前运行状态getRightStrutMorotRunningState"));
        this.d.add(new ta("获取后背门实际位置信息getBackDoorPosition"));
        this.d.add(new ta("获取背门防玩模式getBackDoorAntiPlayMode"));
        this.d.add(new ta("获取电动侧滑门功能状态getElecSlideDoorState"));
        this.d.add(new ta("获取左侧滑门状态getLeftSlideDoorState"));
        this.d.add(new ta("获取右侧滑门状态getRightSlideDoorState"));
        this.d.add(new ta("获取左滑门动作状态getLeftSlideDoorAction"));
        this.d.add(new ta("获取右滑门动作状态getRightSlideDoorAction"));
        this.d.add(new ta("获取左滑门打开位置百分比getLeftSlideDoorPercent"));
        this.d.add(new ta("获取右滑门打开位置百分比getRightSlideDoorPercent"));
        this.d.add(new ta("获取左撑杆电机当前运行状态getLeftStrutMorotRunningState"));
        this.d.add(new ta("车窗初始化状态getWindowInitState"));
        this.d.add(new ta("车窗热保护状态getWindowThermalProtectionState"));
        this.d.add(new ta("获取READY指示灯状态getReadyIndicatorLightState"));
        this.d.add(new ta("获取油量电量低信息 getFuelElecLowPower"));
        this.d.add(new ta("获取车窗防夹标识位 getWindowAntiPinchConfig"));
        this.d.add(new ta("获取主驾驶位置是否支持后视镜调节 getRearviewMirrorSupport"));
        this.d.add(new ta("获取右前，左后右后门锁状态 getDoorLockStatus"));
        this.d.add(new ta("获取后位灯开关状态 getRearLampLightState"));
        this.d.add(new ta("获取外后视镜折叠状态 getAutoExternalRearMirrorFoldedState"));
        this.d.add(new ta("获取外后视镜折叠执行状态 getAutoExternalRearMirrorFoldActionState"));
        this.d.add(new ta("获取方向盘垂直方向的位置 getSteeringWheelY"));
        this.d.add(new ta("获取方向盘前后方向的位置 getSteeringWheelZ"));
        this.d.add(new ta("获取左/右后视镜上下翻转的位置 getRearviewMirrorY"));
        this.d.add(new ta("获取左/右后视镜左右翻转的位置 getRearviewMirrorX"));
        this.d.add(new ta("获取智能语音限制状态 getSmartVoiceLimit()"));
        this.d.add(new ta("获取车辆状态 getBCMVechileStatus"));
        this.d.add(new ta("获取是否支持电动前仓盖功能 getElectricFrontHatchConfiguration"));
        this.d.add(new ta("获取主驾座椅恢复，保存状态 getMainSaveOrRecoverState"));
        this.d.add(new ta("获取主驾座椅恢复，保存状态 getMain2SaveOrRecoverState"));
        this.d.add(new ta("获取天窗系统位置百分百控制标志位 getMoonRoofPercentControlFlag"));
        this.d.add(new ta("获取座椅腰撑配置 getSeatLumbarSupportConfig"));
        this.d.add(new ta("获取座椅四人舒享配置 getFourPeopleEnjoyConfig"));
        this.d.add(new ta("获取座椅模式唤醒反馈 getSeatModeWakeupFeedbackState"));
        this.d.add(new ta("获取4门把手内位置开关信号状态 getInternalDoorHandleState "));
        this.d.add(new ta("获取电动踏板配置 getElectricPedalConfig"));
        this.d.add(new ta("获取尾门类型标志 getTailDoorTypeConfig"));
        this.d.add(new ta("车窗百分比控制自学习 getWindowPercentageControlConfig"));
        this.d.add(new ta("获取车辆换挡模式 getShiftMode"));
        this.e.add(new ta("设置左前窗控制状态setWindowControlState(1):"));
        this.e.add(new ta("设置右前窗控制状态setWindowControlState(2):"));
        this.e.add(new ta("设置左后窗控制状态setWindowControlState(3):"));
        this.e.add(new ta("设置右后窗控制状态setWindowControlState(4):"));
        this.e.add(new ta("一键设置多个车窗setWindowControlState(int[]):"));
        this.e.add(new ta("左前窗setWindowControlState(int[]):"));
        this.e.add(new ta("左前窗+左后setWindowControlState(int[]):"));
        this.e.add(new ta("设置车窗状态setWindowsMode:"));
        this.e.add(new ta("设置左前窗位置百分比setWindowOpenPercent(1):"));
        this.e.add(new ta("设置右前窗位置百分比setWindowOpenPercent(2):"));
        this.e.add(new ta("设置左后窗位置百分比setWindowOpenPercent(3):"));
        this.e.add(new ta("设置右后窗位置百分比setWindowOpenPercent(4):"));
        this.e.add(new ta("设置天窗位置百分比setWindowOpenPercent(5):"));
        this.e.add(new ta("设置遮阳窗帘位置百分比setWindowOpenPercent(6):"));
        this.e.add(new ta("设置后遮阳窗帘位置百分比setWindowOpenPercent(7):"));
        this.e.add(new ta("设置天窗，遮阳窗帘状态setSunroofAndSunshade:"));
        this.e.add(new ta("设置电动后背门控制状态setElecBackDoorControlState:"));
        this.e.add(new ta("设置后背门目标开启高度setBackDoorHeightPosition:"));
        this.e.add(new ta("左侧电动滑门语音控制命令setLeftSlideDoorVoiceControlState:"));
        this.e.add(new ta("右侧电动滑门语音控制命令setRightSlideDoorVoiceControlState:"));
        this.e.add(new ta("行李箱设置settingLuggage:"));
        this.e.add(new ta("设置四门门锁状态 setAllDoorsLock:"));
        this.e.add(new ta("四车门落锁（场景模式）setAllDoorsLockScene:"));
        this.e.add(new ta("设置外后视镜折叠的转态 setAutoExternalRearMirrorFoldState:"));
        this.e.add(new ta("调整左右后视镜动作 turnRearviewMirror:"));
        this.e.add(new ta("方向盘位置调节 turnSteeringWheel:"));
        this.e.add(new ta("天窗语音操控指令 voiceCtlMoonRoof:"));
        this.e.add(new ta("遮阳帘语音操控指令 voiceCtlSunshadePanel:"));
        this.e.add(new ta("设置电动滑门功能开关状态 setElecSlideDoorState:"));
        this.e.add(new ta("设置主驾座椅腰撑调节 setWaistSupportAdjustment:"));
        this.e.add(new ta("设置副驾座椅腰撑调节 setWaistSupportAdjustment:"));
        this.e.add(new ta("设置座椅模式控制命令 setSeatModeControl:"));
    }

    public final void t0() {
        this.k.registerListener((AbsBYDAutoOtaListener) new a());
        this.j.registerListener((AbsBYDAutoBodyworkListener) new b());
    }

    public final void u0() {
        this.o.setLayoutManager(new LinearLayoutManager(this, 1, true));
        CopyOnWriteArrayList<ta> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.p = copyOnWriteArrayList;
        CallbackAdapter callbackAdapter = new CallbackAdapter(this, copyOnWriteArrayList);
        this.q = callbackAdapter;
        this.o.setAdapter(callbackAdapter);
    }

    public final void v0() {
        this.f1025b = (RecyclerView) findViewById(R.id.get_recyclerview);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(this);
        flexboxLayoutManager.U(0);
        flexboxLayoutManager2.V(1);
        flexboxLayoutManager2.U(0);
        flexboxLayoutManager.V(1);
        this.f1025b.setLayoutManager(flexboxLayoutManager);
        this.d = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.set_recyclerview);
        this.f1026c = recyclerView;
        recyclerView.setLayoutManager(flexboxLayoutManager2);
        this.e = new ArrayList();
        s0();
        this.f = new CommonAdapter(this, this.d);
        this.g = new CommonAdapter(this, this.e);
        this.f1025b.setAdapter(this.f);
        this.f1026c.setAdapter(this.g);
        this.f.c(new CommonAdapter.b() { // from class: b.a.a.i.h.r1
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f719a.x0(taVar, i);
            }
        });
        this.g.c(new CommonAdapter.b() { // from class: b.a.a.i.h.s1
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f743a.z0(taVar, i);
            }
        });
    }
}
