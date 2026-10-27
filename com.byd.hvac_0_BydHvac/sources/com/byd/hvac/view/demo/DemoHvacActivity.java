package com.byd.hvac.view.demo;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
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
import b.a.a.h.i;
import b.a.a.i.h.ta;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseActivity;
import com.byd.hvac.view.demo.CommonAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DemoHvacActivity extends BaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1038b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView f1039c;
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
    public class a extends AbsBYDAutoAcListener {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B(int i) {
            ta taVar = new ta("onAcDefrostOnlineStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B0(int i, int i2) {
            ta taVar = new ta("onAirOutletStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B1(int i, int i2) {
            ta taVar = new ta("onSweepBlowStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D(int i, int i2) {
            ta taVar = new ta("onAcDefrostStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D0(int i, float f) {
            ta taVar = new ta("onAirOutletValueChanged ");
            taVar.c("area=" + i + " - - value=" + f);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D1(int i, int i2) {
            ta taVar = new ta("onTemperatureChanged ");
            taVar.c("area=" + i + " - - value=" + i2);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F(int i) {
            ta taVar = new ta("onAcFaultNumShownStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F0(int i) {
            ta taVar = new ta("onAutoCleanAirStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F1(int i) {
            ta taVar = new ta("onTemperatureUnitChanged ");
            taVar.c("unit=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H(int i) {
            ta taVar = new ta("onAcKeyActionStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H0(int i, int i2) {
            ta taVar = new ta("onAvoidBlowPeopleStateChanged ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H1(int i) {
            ta taVar = new ta("onVoiceCmdResultChanged ");
            taVar.c("result=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J(int i) {
            ta taVar = new ta("onAcMaxCoolingStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J0(int i, int i2) {
            ta taVar = new ta("onBlowPeopleState ");
            taVar.c("area=" + i + " - - state=" + i2);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L(int i) {
            ta taVar = new ta("onAcOnlineStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L0(int i, BYDAutoEventValue bYDAutoEventValue) {
            ta taVar = new ta("onDataEventChanged ");
            taVar.c("eventType=" + i + " - - eventValue=" + bYDAutoEventValue);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N(int i) {
            ta taVar = new ta("onAcPanelLockPromptStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N0(int i) {
            ta taVar = new ta("onElectricDefrostStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P(int i) {
            ta taVar = new ta("onAcPromptBoxShownStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P0(int i, String str) {
            ta taVar = new ta("onReadyIndicatorLightStateChanged ");
            taVar.c("errCode=" + i + " - - errMessage=" + str);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R(int i) {
            ta taVar = new ta("onAcPtcPreheatSignalChanged ");
            taVar.c("value=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R0(String str, int i) {
            ta taVar = new ta("onFeatureChanged ");
            taVar.c("feature=" + str + " - - ifHas=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T(int i) {
            ta taVar = new ta("onAcRearPaneNotAvailableMenuStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T0(int i) {
            ta taVar = new ta("onFreeWindBTNStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V(int i) {
            ta taVar = new ta("onAcRearPanelLockStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V0(int i) {
            ta taVar = new ta("onFrontRowAirOutletStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X() {
            DemoHvacActivity.this.m.add(new ta("onAcRearStarted "));
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X0(int i) {
            ta taVar = new ta("onHeatPumpCompressorWorkModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z() {
            DemoHvacActivity.this.m.add(new ta("onAcRearStoped "));
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z0(int i) {
            ta taVar = new ta("onHighTempAntivirusCountDownChanged ");
            taVar.c("time=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i) {
            ta taVar = new ta("on3daOnlineStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b0(int i) {
            ta taVar = new ta("onAcRemoteCtrlTimeChanged ");
            taVar.c("value=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b1(int i) {
            ta taVar = new ta("onHighTempAntivirusStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(int i) {
            ta taVar = new ta("onACAssistModeTips ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d0() {
            DemoHvacActivity.this.m.add(new ta("onAcStarted "));
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d1(int i) {
            ta taVar = new ta("onHighTempSterilizeTips ");
            taVar.c("value=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(int i) {
            ta taVar = new ta("onACBTWindTips ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f0() {
            DemoHvacActivity.this.m.add(new ta("onAcStoped "));
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f1(int i) {
            ta taVar = new ta("onIntelligentControlStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(int i) {
            ta taVar = new ta("onACDefrostRearPanelNotAvailableTips ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h0(int i) {
            ta taVar = new ta("onAcTemperatureControlModeChanaged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h1(int i) {
            ta taVar = new ta("onMaxAcStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(int i) {
            ta taVar = new ta("onACKeepEngineRunTips ");
            taVar.c("value=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j0(int i) {
            ta taVar = new ta("onAcTemperatureControlModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j1(int i) {
            ta taVar = new ta("onOtaSubBatteryTemperatureChanged ");
            taVar.c("value=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l(int i) {
            ta taVar = new ta("onACMaxEVModeTips ");
            taVar.c("value=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l0(int i) {
            ta taVar = new ta("onAcVentilationStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l1(int i) {
            ta taVar = new ta("onQuickCleanAirStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n(int i) {
            ta taVar = new ta("onACPreheatTips ");
            taVar.c("value=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n0(int i) {
            ta taVar = new ta("onAcWarmStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n1(int i) {
            ta taVar = new ta("onQuickCleanTipChanged ");
            taVar.c("tip=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p(int i) {
            ta taVar = new ta("onACSystemStateChange ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p0(int i) {
            ta taVar = new ta("onAcWarmTypeOnlineStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p1(int i) {
            ta taVar = new ta("onRearAcCtrlModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r(int i, int i2) {
            ta taVar = new ta("onACWorkModeChanged ");
            taVar.c("area=" + i + " - - mode=" + i2);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r0(int i) {
            ta taVar = new ta("onAcWindLevelChanged ");
            taVar.c("level=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r1(int i) {
            ta taVar = new ta("onRearAcKeyLockStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t(int i) {
            ta taVar = new ta("onAcCompressorManualSignChanged ");
            taVar.c("sign=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t0(int i) {
            ta taVar = new ta("onAcWindLevelChanged ");
            taVar.c("sign=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t1(int i) {
            ta taVar = new ta("onRearAcLockStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v(int i) {
            ta taVar = new ta("onAcCompressorModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v0(int i) {
            ta taVar = new ta("onAcWindModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v1(int i) {
            ta taVar = new ta("onRearAcMaxWindLevelChanged ");
            taVar.c("level=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x(int i) {
            ta taVar = new ta("onAcCtrlModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x0(int i) {
            ta taVar = new ta("onAcWindModeManualSignChanged ");
            taVar.c("sign=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x1(int i) {
            ta taVar = new ta("onRearAcWindLevelChanged ");
            taVar.c("level=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z(int i) {
            ta taVar = new ta("onAcCycleModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z0(int i) {
            ta taVar = new ta("onAirOutleOnlineStateChanged ");
            taVar.c("state=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z1(int i) {
            ta taVar = new ta("onRearAcWindModeChanged ");
            taVar.c("mode=" + i);
            DemoHvacActivity.this.m.add(taVar);
            DemoHvacActivity.this.n.c(DemoHvacActivity.this.m);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void on3daOnlineStateChanged(final int i) {
            super.on3daOnlineStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f458a.b(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACAssistModeTips(final int i) {
            super.onACAssistModeTips(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f505a.d(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACBTWindTips(final int i) {
            super.onACBTWindTips(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f902a.f(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACDefrostRearPanelNotAvailableTips(final int i) {
            super.onACDefrostRearPanelNotAvailableTips(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.q8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f711a.h(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACKeepEngineRunTips(final int i) {
            super.onACKeepEngineRunTips(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f777a.j(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACMaxEVModeTips(final int i) {
            super.onACMaxEVModeTips(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f484a.l(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACPreheatTips(final int i) {
            super.onACPreheatTips(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f584a.n(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACSystemStateChange(final int i) {
            super.onACSystemStateChange(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f482a.p(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACWorkModeChanged(final int i, final int i2) {
            super.onACWorkModeChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.o8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f664a.r(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcCompressorManualSignChanged(final int i) {
            super.onAcCompressorManualSignChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f407a.t(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcCompressorModeChanged(final int i) {
            super.onAcCompressorModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f820a.v(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcCtrlModeChanged(final int i) {
            super.onAcCtrlModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f460a.x(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcCycleModeChanged(final int i) {
            super.onAcCycleModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f456a.z(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcDefrostOnlineStateChanged(final int i) {
            super.onAcDefrostOnlineStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f636a.B(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcDefrostStateChanged(final int i, final int i2) {
            super.onAcDefrostStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.l9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f588a.D(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcFaultNumShownStateChanged(final int i) {
            super.onAcFaultNumShownStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f535a.F(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcKeyActionStateChanged(final int i) {
            super.onAcKeyActionStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f304a.H(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcMaxCoolingStateChanged(final int i) {
            super.onAcMaxCoolingStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f688a.J(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcOnlineStateChanged(final int i) {
            super.onAcOnlineStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f907a.L(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcPanelLockPromptStateChanged(final int i) {
            super.onAcPanelLockPromptStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f384a.N(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcPromptBoxShownStateChanged(final int i) {
            super.onAcPromptBoxShownStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f662a.P(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcPtcPreheatSignalChanged(final int i) {
            super.onAcPtcPreheatSignalChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f562a.R(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcRearPaneNotAvailableMenuStateChanged(final int i) {
            super.onAcRearPaneNotAvailableMenuStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f860a.T(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcRearPanelLockStateChanged(final int i) {
            super.onAcRearPanelLockStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f329a.V(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcRearStarted() {
            super.onAcRearStarted();
            i.d(new Runnable() { // from class: b.a.a.i.h.y6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f879a.X();
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcRearStoped() {
            super.onAcRearStoped();
            i.d(new Runnable() { // from class: b.a.a.i.h.m9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f615a.Z();
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcRemoteCtrlTimeChanged(final int i) {
            super.onAcRemoteCtrlTimeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f818a.b0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcStarted() {
            super.onAcStarted();
            i.d(new Runnable() { // from class: b.a.a.i.h.d8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f383a.d0();
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcStoped() {
            super.onAcStoped();
            i.d(new Runnable() { // from class: b.a.a.i.h.h7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f481a.f0();
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcTemperatureControlModeChanaged(final int i) {
            super.onAcTemperatureControlModeChanaged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f405a.h0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcTemperatureControlModeChanged(final int i) {
            super.onAcTemperatureControlModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f757a.j0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcVentilationStateChanged(final int i) {
            super.onAcVentilationStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f409a.l0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWarmStateChanged(final int i) {
            super.onAcWarmStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.y7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f880a.n0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWarmTypeOnlineStateChanged(final int i) {
            super.onAcWarmTypeOnlineStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f356a.p0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWindLevelChanged(final int i) {
            super.onAcWindLevelChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f300a.r0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWindLevelManualSignChanged(final int i) {
            super.onAcWindLevelManualSignChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f640a.t0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWindModeChanged(final int i) {
            super.onAcWindModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f435a.v0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWindModeManualSignChanged(final int i) {
            super.onAcWindModeManualSignChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f586a.x0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAirOutleOnlineStateChanged(final int i) {
            super.onAirOutleOnlineStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f381a.z0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAirOutletStateChanged(final int i, final int i2) {
            super.onAirOutletStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.i9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f509a.B0(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAirOutletValueChanged(final int i, final float f) {
            super.onAirOutletValueChanged(i, f);
            i.d(new Runnable() { // from class: b.a.a.i.h.x6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f857a.D0(i, f);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAutoCleanAirStateChanged(final int i) {
            super.onAutoCleanAirStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f327a.F0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAvoidBlowPeopleStateChanged(final int i, final int i2) {
            super.onAvoidBlowPeopleStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.z7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f904a.H0(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onBlowPeopleState(final int i, final int i2) {
            super.onBlowPeopleState(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.q7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f708a.J0(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onDataEventChanged(final int i, final BYDAutoEventValue bYDAutoEventValue) {
            super.onDataEventChanged(i, bYDAutoEventValue);
            i.d(new Runnable() { // from class: b.a.a.i.h.m7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f609a.L0(i, bYDAutoEventValue);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onElectricDefrostStateChanged(final int i) {
            super.onElectricDefrostStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f755a.N0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onError(final int i, final String str) {
            super.onError(i, str);
            i.d(new Runnable() { // from class: b.a.a.i.h.m8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f612a.P0(i, str);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onFeatureChanged(final String str, final int i) {
            super.onFeatureChanged(str, i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f532a.R0(str, i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onFreeWindBTNStateChanged(int i, final int i2) {
            super.onFreeWindBTNStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.k8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f560a.T0(i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onFrontRowAirOutletStateChanged(final int i) {
            super.onFrontRowAirOutletStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f331a.V0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onHeatPumpCompressorWorkModeChanged(final int i) {
            super.onHeatPumpCompressorWorkModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f638a.X0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onHighTempAntivirusCountDownChanged(final int i) {
            super.onHighTempAntivirusCountDownChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f840a.Z0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onHighTempAntivirusStateChanged(final int i) {
            super.onHighTempAntivirusStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f358a.b1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onHighTempSterilizeTips(final int i) {
            super.onHighTempSterilizeTips(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f433a.d1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onIntelligentControlStateChanged(final int i) {
            super.onIntelligentControlStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f686a.f1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onMaxAcStateChanged(final int i) {
            super.onMaxAcStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f862a.h1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onOtaSubBatteryTemperatureChanged(final int i) {
            super.onOtaSubBatteryTemperatureChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f431a.j1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onQuickCleanAirStateChanged(final int i) {
            super.onQuickCleanAirStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f354a.l1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onQuickCleanTipChanged(final int i) {
            super.onQuickCleanTipChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f775a.n1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onRearAcCtrlModeChanged(final int i) {
            super.onRearAcCtrlModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f730a.p1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onRearAcKeyLockStateChanged(final int i) {
            super.onRearAcKeyLockStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.u7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f797a.r1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onRearAcLockStateChanged(final int i) {
            super.onRearAcLockStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f302a.t1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onRearAcMaxWindLevelChanged(final int i) {
            super.onRearAcMaxWindLevelChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f838a.v1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onRearAcWindLevelChanged(final int i) {
            super.onRearAcWindLevelChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f530a.x1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onRearAcWindModeChanged(final int i) {
            super.onRearAcWindModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f558a.z1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onSweepBlowStateChanged(final int i, final int i2) {
            super.onSweepBlowStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.u8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f799a.B1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onTemperatureChanged(final int i, final int i2) {
            super.onTemperatureChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.r8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f732a.D1(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onTemperatureUnitChanged(final int i) {
            super.onTemperatureUnitChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.y8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f882a.F1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onVoiceCmdResultChanged(final int i) {
            super.onVoiceCmdResultChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f507a.H1(i);
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
            case "设置扫风状态setSweepBlowState":
                str = " 主驾侧= " + i2 + "； 副驾侧= " + i2;
                f1.l0().B4(0, i2);
                f1.l0().B4(1, i2);
                break;
            case "设置对人吹状态setBlowPeopleState":
                str = " 主驾侧= " + i2 + "； 副驾侧= " + i2;
                f1.l0().a4(0, i2);
                f1.l0().a4(1, i2);
                break;
            case "关闭空调stop":
                f1.l0().E4();
                str = str + "/stop/";
                break;
            case "设置空调循环方式setAcCycleMode":
                f1.l0().L3(i2);
                str = str + i2;
                break;
            case "设置最大制冷功能状态setAcMaxCoolingState":
                f1.l0().i4(i2);
                str = str + i2;
                break;
            case "设置空调控制方式setAcControlMode":
                f1.l0().K3(i2);
                str = str + i2;
                break;
            case "设置遥控钥匙开空调运行时间setAcRemoteCtrlTime":
                f1.l0().P3(i2);
                str = str + i2;
                break;
            case "开启后排空调startRearAc":
                f1.l0().D4();
                str = str + "/startRearAc/";
                break;
            case "设置后排按键锁状态setRearACkeyLockState":
                f1.l0().o4(i2);
                str = str + i2;
                break;
            case "设置后空调出风模式setRearAcWindMode":
                f1.l0().r4(i2);
                str = str + i2;
                break;
            case "设置后除霜状态setAcDefrostState(2)":
                f1.l0().M3(2, i2);
                str = str + i2;
                break;
            case "设置空调抑菌功能控制setACAntibacterialControlState":
                f1.l0().I3(i2);
                str = str + i2;
                break;
            case "设置 A/C（压缩机）状态setAcCompressorState":
                f1.l0().J3(i2);
                str = str + i2;
                break;
            case "设置空调出风模式setAcWindMode":
                f1.l0().V3(i2);
                str = str + i2;
                break;
            case "开启/关闭净化功能confirmCleanAirState":
                f1.l0().a(i2);
                str = str + i2;
                break;
            case "设置前除霜状态setAcDefrostState(1)":
                f1.l0().M3(1, i2);
                str = str + i2;
                break;
            case "设置快速净化状态setQuickCleanAirState":
                f1.l0().m4(i2);
                str = str + i2;
                break;
            case "关闭后排空调stopRearAc":
                f1.l0().F4();
                str = str + "/stopRearAc/";
                break;
            case "设置后空调风量档位setRearAcWindLevel":
                f1.l0().q4(i2);
                str = str + i2;
                break;
            case "设置空调风量档位setAcWindLevel":
                f1.l0().U3(i2);
                str = str + i2;
                break;
            case "设置风口位置setAirOutletValue":
                str = " 主驾左风口上下位置 = " + i2 + "； 主驾左风口左右位置= " + i2 + "； 主驾右风口上下位置= " + i2 + "； 主驾右风口左右位置= " + i2 + "； 副驾左风口上下位置= " + i2 + "； 副驾左风口左右位置= " + i2 + "； 副驾右风口上下位置= " + i2 + "； 副驾右风口左右位置= " + i2;
                f1.l0().X3(0, i2);
                f1.l0().X3(1, i2);
                f1.l0().X3(2, i2);
                f1.l0().X3(3, i2);
                f1.l0().X3(4, i2);
                f1.l0().X3(5, i2);
                f1.l0().X3(6, i2);
                f1.l0().X3(7, i2);
                break;
            case "设置避人吹状态setAvoidBlowPeopleState":
                str = " 主驾侧= " + i2 + "； 副驾侧= " + i2;
                f1.l0().Z3(0, i2);
                f1.l0().Z3(1, i2);
                break;
            case "设置自动净化状态setAutoCleanAirState":
                f1.l0().Y3(i2);
                str = str + i2;
                break;
            case "设置自由风状态setFreeWindBTNState":
                str = " 主驾侧= " + i2 + "； 副驾侧= " + i2;
                f1.l0().f4(0, i2);
                f1.l0().f4(1, i2);
                break;
            case "设置后排空调控制方式setRearAcControlMode":
                f1.l0().p4(i2);
                str = str + i2;
                break;
            case "设置温度分控方式setAcTemperatureControlMode":
                f1.l0().R3(i2);
                str = str + i2;
                break;
            case "设置副驾温度setAcTemperature(2)":
                f1.l0().Q3(2, i2);
                str = str + i2;
                break;
            case "设置后空调温度setAcTemperature(3)":
                f1.l0().Q3(3, i2);
                str = str + i2;
                break;
            case "开启空调start":
                f1.l0().C4();
                str = str + "/start/";
                break;
            case "设置风口关闭状态setAirOutletState":
                str = " 主驾侧= " + i2 + "； 副驾侧= " + i2;
                f1.l0().W3(0, i2);
                f1.l0().W3(1, i2);
                break;
            case "设置智能风状态setIntelligentControlState":
                str = " 主驾侧= " + i2 + "； 副驾侧= " + i2;
                f1.l0().h4(0, i2);
                f1.l0().h4(1, i2);
                break;
            case "设置主驾温度setAcTemperature(1)":
                f1.l0().Q3(1, i2);
                str = str + i2;
                break;
            case "设置电除霜功能状态setElectricDefrostState":
                f1.l0().c4(i2);
                str = str + i2;
                break;
            case "设置驻车通风功能setAcVentilationState":
                f1.l0().T3(i2);
                str = str + i2;
                break;
            case "设置前排常出风口开关状态setFrontRowAirOutletState":
                f1.l0().g4(i2);
                str = str + i2;
                break;
            case "设置后面板锁状态setAcRearPanelLockState":
                f1.l0().O3(i2);
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
        String str2 = taVar.f780a;
        str2.hashCode();
        switch (str2) {
            case "获取副驾温度getAcTemperature(2)":
                str = "副驾温度 getAcTemperature2 = " + f1.l0().B(2);
                break;
            case "获取风量手动标志getAcWindLevelManualSign":
                str = "风量手动标志 getAcWindLevelManualSign = " + f1.l0().I();
                break;
            case "获取后排温度getAcTemperature(3)":
                str = "后排温度 getAcTemperature3 = " + f1.l0().B(3);
                break;
            case "获取空调类型getAcType":
                str = "空调类型 getAcType = " + f1.l0().E();
                break;
            case "获取热泵压缩机工作模式getHeatPumpCompressorWorkMode":
                str = "热泵压缩机工作模式 getHeatPumpCompressorWorkMode = " + f1.l0().i0();
                break;
            case "获取温度单位getTemperatureUnit":
                str = "温度单位 getTemperatureUnit = " + f1.l0().p1();
                break;
            case "获取电动风口控制系统是否在线getAirOutleOnlineState":
                str = "获取电动风口控制系统是否在线getAirOutleOnlineState = " + f1.l0().M();
                break;
            case "获取主驾温度getAcTemperature(1)":
                str = "主驾温度 getAcTemperature1 = " + f1.l0().B(1);
                break;
            case "获取快速净化状态getQuickCleanAirState":
                str = "快速净化状态 getQuickCleanAirState = " + f1.l0().I0();
                break;
            case "获取空调加热模式标志位getAcWarmTypeOnlineState":
                str = "空调加热模式标志位 getAcWarmTypeOnlineState = " + f1.l0().G();
                break;
            case "获取高温消毒状态getHighTempAntivirusState":
                str = "高温消毒状态 getHighTempAntivirusState = " + f1.l0().k0();
                break;
            case "获取后面板锁状态getAcRearPanelLockState":
                str = "后面板锁状态 getAcRearPanelLockState = " + f1.l0().y();
                break;
            case "获取循环方式getAcCycleMode":
                str = "循环方式 getAcCycleMode = " + f1.l0().p();
                break;
            case "获取后排空调风量档位数getRearAcMaxWindLevel":
                str = "后排空调风量档位数 getRearAcMaxWindLevel = " + f1.l0().N0();
                break;
            case "获取后排按键锁状态getRearACkeyLockState":
                str = "后排按键锁状态 getRearACkeyLockState = " + f1.l0().L0();
                break;
            case "获取控制方式getAcControlMode":
                str = "控制方式 getAcControlMode = " + f1.l0().o();
                break;
            case "获取电除霜配置getDefrostRearConfig":
                str = "电除霜配置 getDefrostRearConfig = " + f1.l0().U();
                break;
            case "获取前排常出风口开关状态getFrontRowAirOutletState":
                str = "获取前排常出风口开关状态getFrontRowAirOutletState = " + f1.l0().h0();
                break;
            case "获取 A/C（压缩机）状态getAcCompressorState":
                str = "A/C（压缩机）状态getAcCompressorState = " + f1.l0().n();
                break;
            case "获取空调出风模式getAcWindMode":
                str = "空调出风模式 getAcWindMode = " + f1.l0().J();
                break;
            case "获取为保证车内温度舒适，将会启动发动机辅助制热提示框状态getACAssistModeTipsState":
                str = taVar.f780a + " = " + f1.l0().h();
                break;
            case "获取 On 档电空调提示标志位getAcPromptBoxShownState":
                str = taVar.f780a + " = " + f1.l0().w();
                break;
            case "获取净化弹框类型getQuickCleanTip":
                str = "净化弹框类型 getQuickCleanTip = " + f1.l0().J0();
                break;
            case "是否显示故障码模式getAcFaultNumShownState":
                str = "是否显示故障码模式 getAcFaultNumShownState = " + f1.l0().r();
                break;
            case "获取空调工作模式getACWorkMode":
                str = "获取空调工作模式getACWorkMode 主驾侧= " + f1.l0().l(0) + "； 副驾侧= " + f1.l0().l(1);
                break;
            case "获取高温消毒倒计时getHighTempAntivirusCountDown":
                str = "高温消毒倒计时 getHighTempAntivirusCountDown = " + f1.l0().j0();
                break;
            case "获取前除霜模式，后排空调按键操作无效提示语状态getACDefrostRearPanelNotAvailableTipsState":
                str = taVar.f780a + " = " + f1.l0().j();
                break;
            case "获取空调离线状态getAcOnlineState":
                str = "空调离线状态 getAcOnlineState = " + f1.l0().v();
                break;
            case "获取后空调出风模式getRearAcControlMode":
                str = "后空调出风模式 getRearAcWindMode = " + f1.l0().Q0();
                break;
            case "获取蓝牙通话自动降风速提示框状态getACBTWindTipsState":
                str = taVar.f780a + " = " + f1.l0().i();
                break;
            case "获取自由风状态getFreeWindBTNState":
                str = "获取自由风状态getFreeWindBTNState 主驾侧= " + f1.l0().g0(0) + "； 副驾侧= " + f1.l0().g0(1);
                break;
            case "获取后空调控制方式getRearAcControlMode":
                str = "后空调控制方式 getRearAcControlMode = " + f1.l0().M0();
                break;
            case "获取 A/C（压缩机）手动标志getAcCompressorManualSign":
                str = "A/C（压缩机）手动标志 getAcCompressorManualSign = " + f1.l0().m();
                break;
            case "获取智能风状态getIntelligentControlState":
                str = "获取智能风状态getIntelligentControlState = " + f1.l0().m0();
                break;
            case "获取空调开启状态getAcStartState":
                str = "空调开启状态 getAcStartState = " + f1.l0().A();
                break;
            case "获取通风功能设置getAcVentilationState":
                str = "通风功能 getAcVentilationState = " + f1.l0().F();
                break;
            case "获取车外温度getAcTemperature(4)":
                str = "车外温度 getAcTemperature4 = " + f1.l0().B(4);
                break;
            case "获取后空调开启状态getRearAcStartState":
                str = "后空调开启状态 getRearAcStartState = " + f1.l0().O0();
                break;
            case "获取电除霜功能状态getElectricDefrostState":
                str = "电除霜功能状态 getElectricDefrostState = " + f1.l0().Z();
                break;
            case "获取避人吹状态getAvoidBlowPeopleState":
                str = "获取避人吹状态getAvoidBlowPeopleState 主驾侧= " + f1.l0().R(0) + "； 副驾侧= " + f1.l0().R(1);
                break;
            case "获取后除霜状态getAcDefrostState(2)":
                str = "后除霜状态 getAcDefrostState2 = " + f1.l0().q(2);
                break;
            case "获取空调预热状态getAcPtcPreheatSignal":
                str = "空调预热状态 getAcPtcPreheatSignal = " + f1.l0().x();
                break;
            case "获取出风模式手动标志getAcWindModeManualSign":
                str = "出风模式手动标志 getAcWindModeManualSign = " + f1.l0().K();
                break;
            case "获取对人吹状态getBlowPeopleState":
                str = "获取对人吹状态getBlowPeopleState 主驾侧= " + f1.l0().S(0) + "； 副驾侧= " + f1.l0().S(1);
                break;
            case "获取遥控钥匙开空调运行时间getAcRemoteCtrlTime":
                str = "遥控钥匙开空调运行时间 getAcRemoteCtrlTime = " + f1.l0().z();
                break;
            case "获取为满足空调需求，需保持发动机启动提示框状态getACKeepEngineRunTipsState":
                str = taVar.f780a + " = " + f1.l0().k();
                break;
            case "获取风口位置getAirOutletValue":
                str = "获取风口位置getAirOutletValue 主驾左风口上下位置= " + f1.l0().O(0) + "； 主驾左风口左右位置= " + f1.l0().O(1) + "； 主驾右风口上下位置= " + f1.l0().O(2) + "； 主驾右风口左右位置= " + f1.l0().O(3) + "； 副驾左风口上下位置= " + f1.l0().O(4) + "； 副驾左风口左右位置= " + f1.l0().O(5) + "； 副驾右风口上下位置= " + f1.l0().O(6) + "； 副驾右风口左右位置= " + f1.l0().O(7);
                break;
            case "获取后排空调风量档位getRearAcWindLevel":
                str = "后排空调风量档位 getRearAcWindLevel = " + f1.l0().P0();
                break;
            case "前空调出风模式种数getAcWindModeNum":
                str = "前空调出风模式种数 getAcWindModeNum = " + f1.l0().L();
                break;
            case "获取分控方式getAcTemperatureControlMode":
                str = "分控方式 getAcTemperatureControlMode = " + f1.l0().C();
                break;
            case "获取前除霜状态getAcDefrostState(1)":
                str = "前除霜状态 getAcDefrostState1 = " + f1.l0().q(1);
                break;
            case "获取空调风量档位getAcWindLevel":
                str = "空调风量档位 getAcWindLevel = " + f1.l0().H();
                break;
            case "获取最大制冷功能状态getAcMaxCoolingState":
                str = "最大制冷功能状态 getAcMaxCoolingState = " + f1.l0().u();
                break;
            case "获取自动净化状态getAutoCleanAirState":
                str = "自动净化状态 getAutoCleanAirState = " + f1.l0().P();
                break;
            case "获取风口关闭状态getAirOutletState":
                str = "获取风口关闭状态getAirOutletState 主驾侧= " + f1.l0().N(0) + "； 副驾侧= " + f1.l0().N(1);
                break;
            case "获取侧扫风getSweepBlowState":
                str = "获取侧扫风getSweepBlowState 主驾侧= " + f1.l0().n1(0) + "； 副驾侧= " + f1.l0().n1(1);
                break;
            default:
                str = "";
                break;
        }
        this.j.setText(str);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.demo_activity_ac;
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
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.q9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f713a.D0(view);
            }
        });
        v0();
        w0();
        this.h = (EditText) findViewById(R.id.get_edittext1);
        this.i = (EditText) findViewById(R.id.set_edittext1);
        this.j = (TextView) findViewById(R.id.text);
        this.k = (TextView) findViewById(R.id.set_text);
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
        this.d.add(new ta("获取空调开启状态getAcStartState"));
        this.d.add(new ta("获取控制方式getAcControlMode"));
        this.d.add(new ta("获取循环方式getAcCycleMode"));
        this.d.add(new ta("获取空调出风模式getAcWindMode"));
        this.d.add(new ta("获取前除霜状态getAcDefrostState(1)"));
        this.d.add(new ta("获取后除霜状态getAcDefrostState(2)"));
        this.d.add(new ta("获取空调风量档位getAcWindLevel"));
        this.d.add(new ta("获取主驾温度getAcTemperature(1)"));
        this.d.add(new ta("获取副驾温度getAcTemperature(2)"));
        this.d.add(new ta("获取后排温度getAcTemperature(3)"));
        this.d.add(new ta("获取车外温度getAcTemperature(4)"));
        this.d.add(new ta("获取温度单位getTemperatureUnit"));
        this.d.add(new ta("获取分控方式getAcTemperatureControlMode"));
        this.d.add(new ta("获取通风功能设置getAcVentilationState"));
        this.d.add(new ta("获取后空调开启状态getRearAcStartState"));
        this.d.add(new ta("获取后空调控制方式getRearAcControlMode"));
        this.d.add(new ta("获取后空调出风模式getRearAcControlMode"));
        this.d.add(new ta("获取后排空调风量档位getRearAcWindLevel"));
        this.d.add(new ta("获取后排按键锁状态getRearACkeyLockState"));
        this.d.add(new ta("获取热泵压缩机工作模式getHeatPumpCompressorWorkMode"));
        this.d.add(new ta("获取 A/C（压缩机）状态getAcCompressorState"));
        this.d.add(new ta("获取 A/C（压缩机）手动标志getAcCompressorManualSign"));
        this.d.add(new ta("获取风量手动标志getAcWindLevelManualSign"));
        this.d.add(new ta("获取出风模式手动标志getAcWindModeManualSign"));
        this.d.add(new ta("获取最大制冷功能状态getAcMaxCoolingState"));
        this.d.add(new ta("获取电除霜功能状态getElectricDefrostState"));
        this.d.add(new ta("获取空调离线状态getAcOnlineState"));
        this.d.add(new ta("获取空调加热模式标志位getAcWarmTypeOnlineState"));
        this.d.add(new ta("是否显示故障码模式getAcFaultNumShownState"));
        this.d.add(new ta("获取后排空调风量档位数getRearAcMaxWindLevel"));
        this.d.add(new ta("获取遥控钥匙开空调运行时间getAcRemoteCtrlTime"));
        this.d.add(new ta("获取空调类型getAcType"));
        this.d.add(new ta("获取空调预热状态getAcPtcPreheatSignal"));
        this.d.add(new ta("前空调出风模式种数getAcWindModeNum"));
        this.d.add(new ta("获取后面板锁状态getAcRearPanelLockState"));
        this.d.add(new ta("获取快速净化状态getQuickCleanAirState"));
        this.d.add(new ta("获取净化弹框类型getQuickCleanTip"));
        this.d.add(new ta("获取自动净化状态getAutoCleanAirState"));
        this.d.add(new ta("获取高温消毒状态getHighTempAntivirusState"));
        this.d.add(new ta("获取高温消毒倒计时getHighTempAntivirusCountDown"));
        this.d.add(new ta("获取电除霜配置getDefrostRearConfig"));
        this.d.add(new ta("获取风口位置getAirOutletValue"));
        this.d.add(new ta("获取自由风状态getFreeWindBTNState"));
        this.d.add(new ta("获取对人吹状态getBlowPeopleState"));
        this.d.add(new ta("获取避人吹状态getAvoidBlowPeopleState"));
        this.d.add(new ta("获取侧扫风getSweepBlowState"));
        this.d.add(new ta("获取风口关闭状态getAirOutletState"));
        this.d.add(new ta("获取智能风状态getIntelligentControlState"));
        this.d.add(new ta("获取前排常出风口开关状态getFrontRowAirOutletState"));
        this.d.add(new ta("获取空调工作模式getACWorkMode"));
        this.d.add(new ta("获取电动风口控制系统是否在线getAirOutleOnlineState"));
        this.d.add(new ta("获取蓝牙通话自动降风速提示框状态getACBTWindTipsState"));
        this.d.add(new ta("获取前除霜模式，后排空调按键操作无效提示语状态getACDefrostRearPanelNotAvailableTipsState"));
        this.d.add(new ta("获取为保证车内温度舒适，将会启动发动机辅助制热提示框状态getACAssistModeTipsState"));
        this.d.add(new ta("获取为满足空调需求，需保持发动机启动提示框状态getACKeepEngineRunTipsState"));
        this.d.add(new ta("获取 On 档电空调提示标志位getAcPromptBoxShownState"));
        this.e.add(new ta("设置后面板锁状态setAcRearPanelLockState"));
        this.e.add(new ta("设置后排按键锁状态setRearACkeyLockState"));
        this.e.add(new ta("设置空调控制方式setAcControlMode"));
        this.e.add(new ta("设置空调循环方式setAcCycleMode"));
        this.e.add(new ta("设置后排空调控制方式setRearAcControlMode"));
        this.e.add(new ta("设置空调出风模式setAcWindMode"));
        this.e.add(new ta("设置后空调出风模式setRearAcWindMode"));
        this.e.add(new ta("设置前除霜状态setAcDefrostState(1)"));
        this.e.add(new ta("设置后除霜状态setAcDefrostState(2)"));
        this.e.add(new ta("设置空调风量档位setAcWindLevel"));
        this.e.add(new ta("设置后空调风量档位setRearAcWindLevel"));
        this.e.add(new ta("设置主驾温度setAcTemperature(1)"));
        this.e.add(new ta("设置副驾温度setAcTemperature(2)"));
        this.e.add(new ta("设置后空调温度setAcTemperature(3)"));
        this.e.add(new ta("设置温度分控方式setAcTemperatureControlMode"));
        this.e.add(new ta("设置驻车通风功能setAcVentilationState"));
        this.e.add(new ta("开启空调start"));
        this.e.add(new ta("开启后排空调startRearAc"));
        this.e.add(new ta("关闭空调stop"));
        this.e.add(new ta("关闭后排空调stopRearAc"));
        this.e.add(new ta("设置 A/C（压缩机）状态setAcCompressorState"));
        this.e.add(new ta("设置最大制冷功能状态setAcMaxCoolingState"));
        this.e.add(new ta("设置电除霜功能状态setElectricDefrostState"));
        this.e.add(new ta("设置遥控钥匙开空调运行时间setAcRemoteCtrlTime"));
        this.e.add(new ta("设置快速净化状态setQuickCleanAirState"));
        this.e.add(new ta("开启/关闭净化功能confirmCleanAirState"));
        this.e.add(new ta("设置自动净化状态setAutoCleanAirState"));
        this.e.add(new ta("设置空调抑菌功能控制setACAntibacterialControlState"));
        this.e.add(new ta("设置风口位置setAirOutletValue"));
        this.e.add(new ta("设置自由风状态setFreeWindBTNState"));
        this.e.add(new ta("设置对人吹状态setBlowPeopleState"));
        this.e.add(new ta("设置避人吹状态setAvoidBlowPeopleState"));
        this.e.add(new ta("设置扫风状态setSweepBlowState"));
        this.e.add(new ta("设置风口关闭状态setAirOutletState"));
        this.e.add(new ta("设置智能风状态setIntelligentControlState"));
        this.e.add(new ta("设置前排常出风口开关状态setFrontRowAirOutletState"));
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
        BYDAutoAcDevice.getInstance(this).registerListener((AbsBYDAutoAcListener) new a());
    }

    public final void w0() {
        this.f1038b = (RecyclerView) findViewById(R.id.get_recyclerview);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(this);
        flexboxLayoutManager.U(0);
        flexboxLayoutManager2.V(1);
        flexboxLayoutManager2.U(0);
        flexboxLayoutManager.V(1);
        this.f1038b.setLayoutManager(flexboxLayoutManager);
        this.d = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.set_recyclerview);
        this.f1039c = recyclerView;
        recyclerView.setLayoutManager(flexboxLayoutManager2);
        this.e = new ArrayList();
        t0();
        this.f = new CommonAdapter(this, this.d);
        this.g = new CommonAdapter(this, this.e);
        this.f1038b.setAdapter(this.f);
        this.f1039c.setAdapter(this.g);
        this.f.c(new CommonAdapter.b() { // from class: b.a.a.i.h.p9
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f690a.z0(taVar, i);
            }
        });
        this.g.c(new CommonAdapter.b() { // from class: b.a.a.i.h.o9
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f667a.B0(taVar, i);
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
