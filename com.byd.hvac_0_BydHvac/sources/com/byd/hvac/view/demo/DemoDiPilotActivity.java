package com.byd.hvac.view.demo;

import a.b.c;
import android.hardware.bydauto.BYDAutoEventValue;
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
import b.a.a.h.i;
import b.a.a.i.h.ra;
import b.a.a.i.h.sa;
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
public class DemoDiPilotActivity extends BaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1029b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView f1030c;
    public List<ta> d;
    public List<ta> e;
    public CommonAdapter f;
    public CommonAdapter g;
    public EditText h;
    public EditText i;
    public ra j;
    public b k;
    public TextView l;
    public TextView m;
    public RecyclerView n;
    public CopyOnWriteArrayList<ta> o;
    public CallbackAdapter p;

    /* JADX INFO: compiled from: caradapter-bug */
    public class b extends sa {
        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B(int i) {
            ta taVar = new ta("onAiTurnLightStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B0(int i) {
            ta taVar = new ta("onEmergencyBrakeWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B1(int i) {
            ta taVar = new ta("onLKSFaultStateStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: A2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void B2(int i) {
            ta taVar = new ta("onTSRLitSpdChgSwitchStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D(int i) {
            ta taVar = new ta("onAlarmTypeChanged ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D0(int i) {
            ta taVar = new ta("onEmergencyLaneAssistance2F0StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D1(int i) {
            ta taVar = new ta("onLaneAssistTypeChanged ");
            taVar.c("type=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: C2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void D2(int i) {
            ta taVar = new ta("onTemperatureWhileFatigueChanged ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F(int i) {
            ta taVar = new ta("onAttentionDetectionStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F0(int i) {
            ta taVar = new ta("onEmergencyLaneAssistance32FStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F1(int i) {
            ta taVar = new ta("onLeftBehindReminderStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: E2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void F2(int i) {
            ta taVar = new ta("onTrafficIncidentWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H(int i) {
            ta taVar = new ta("onAutoParkingStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H0(int i) {
            ta taVar = new ta("onEmergencyLaneAssistanceStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H1(int i) {
            ta taVar = new ta("onLowSpeedEmergencyBrakingStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: G2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void H2(int i) {
            ta taVar = new ta("onTrafficJamWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J(int i) {
            ta taVar = new ta("onBlindMonitorStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J0(int i) {
            ta taVar = new ta("onEmergencyVehicleWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J1(int i) {
            ta taVar = new ta("onMechanismPositionChanged ");
            taVar.c("pos=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: I2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void J2(int i) {
            ta taVar = new ta("onTrafficLightInfoPushStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L(int i) {
            ta taVar = new ta("onBlindMonitorStateChanged1 ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L0(int i) {
            ta taVar = new ta("onFCWFaultStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L1(int i) {
            ta taVar = new ta("onMistakeGasStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: K2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void L2(int i) {
            ta taVar = new ta("onTrafficLightRecognize2B4StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N(int i) {
            ta taVar = new ta("onBlindMonitorStateChanged2 ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N0(int i) {
            ta taVar = new ta("onFatigueDetectionAidStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N1(int i) {
            ta taVar = new ta("onOpenDoorAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: M2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void N2(int i) {
            ta taVar = new ta("onTrafficLightRecognize2D5StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P(int i) {
            ta taVar = new ta("onChargerCoverStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P0(int i) {
            ta taVar = new ta("onFaultHandlerStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P1(int i) {
            ta taVar = new ta("onPredictCrashAlert138ModeChanged ");
            taVar.c("mode=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: O2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void P2(int i) {
            ta taVar = new ta("onTrafficLightRecognizeStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R(int i) {
            ta taVar = new ta("onChildCareStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R0(int i) {
            ta taVar = new ta("onFrontCarStartStateChange ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R1(int i) {
            ta taVar = new ta("onPredictCrashAlert32DModeChanged ");
            taVar.c("mode=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Q2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void R2(int i) {
            ta taVar = new ta("onTrafficSignRecognize2B4StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T(int i) {
            ta taVar = new ta("onCollisionWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T0(int i) {
            ta taVar = new ta("onFrontTrafficBrakeAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T1(int i) {
            ta taVar = new ta("onPredictCrashAlertModeChanged ");
            taVar.c("mode=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: S2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void T2(int i) {
            ta taVar = new ta("onTrafficSignRecognize2D5StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V(int i) {
            ta taVar = new ta("onControlLossWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V0(int i) {
            ta taVar = new ta("onFrontTrafficCrossAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V1(int i) {
            ta taVar = new ta("onPredictCrashAlertStateChange ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: U2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void V2(int i) {
            ta taVar = new ta("onTrafficSignRecognize316StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X(int i) {
            ta taVar = new ta("onCpdDelayStatusChanged ");
            taVar.c("status=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X0(int i) {
            ta taVar = new ta("onGazePerceptionStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X1(int i) {
            ta taVar = new ta("onRainOpenFogLightStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: W2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void X2(int i) {
            ta taVar = new ta("onTrafficSignRecognizeStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z(int i) {
            ta taVar = new ta("onCpdStatusChanged ");
            taVar.c("status=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z0(int i) {
            ta taVar = new ta("onGestureRecognitionStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z1(int i) {
            ta taVar = new ta("onRearCrashAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Y2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void Z2(int i) {
            ta taVar = new ta("onTrafficSignalWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i) {
            ta taVar = new ta("onADASOnlineStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b0(int i) {
            ta taVar = new ta("onDangerousActionDetectionStateChanged ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b1(int i) {
            ta taVar = new ta("onGreenLightOptimalSpeedAdvisoryStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b2(int i) {
            ta taVar = new ta("onRearTrafficBrakeAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a3, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b3(int i) {
            ta taVar = new ta("onUrbanAIAssistanceStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(int i) {
            ta taVar = new ta("onADASSoftStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d0(int i) {
            ta taVar = new ta("onDistractionDetectionAidStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d1(int i) {
            ta taVar = new ta("onHighSpeedAINavigationStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d2(int i) {
            ta taVar = new ta("onRearTrafficCrossAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c3, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d3(int i) {
            ta taVar = new ta("onVRUFaultStateStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(int i) {
            ta taVar = new ta("onAEBFaultStateStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f0(int i) {
            ta taVar = new ta("onDmsStatusChanged ");
            taVar.c("status=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f1(int i) {
            ta taVar = new ta("onHighSpeedOpenWindowStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f2(int i) {
            ta taVar = new ta("onRecommendRestSpotStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e3, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f3(int i) {
            ta taVar = new ta("onVerifyModeChanged ");
            taVar.c("mode=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(int i) {
            ta taVar = new ta("onAINavigationStateChanged ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h0(int i) {
            ta taVar = new ta("onDnpStatusChanged ");
            taVar.c("status=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h1(int i) {
            ta taVar = new ta("onISLIWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h2(int i) {
            ta taVar = new ta("onRedLightViolationWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(int i) {
            ta taVar = new ta("onAbnormalVehicleWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j0(int i) {
            ta taVar = new ta("onDoNotPassWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j1(int i) {
            ta taVar = new ta("onIntelligentSpeedLimitAlert2B4StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j2(int i) {
            ta taVar = new ta("onRelieveFatigueStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l(int i) {
            ta taVar = new ta("onAiCareStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l0(int i) {
            ta taVar = new ta("onDriveDepartureStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l1(int i) {
            ta taVar = new ta("onIntelligentSpeedLimitAlert2D5StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l2(int i) {
            ta taVar = new ta("onRemoteControlDriveCloseTops ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n(int i) {
            ta taVar = new ta("onAiDistanceLight2B4StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n0(int i) {
            ta taVar = new ta("onDriveLevelChanged ");
            taVar.c("level=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n1(int i) {
            ta taVar = new ta("onIntelligentSpeedLimitAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n2(int i) {
            ta taVar = new ta("onRequestHUNPClose ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p(int i) {
            ta taVar = new ta("onAiDistanceLight316StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p0(int i) {
            ta taVar = new ta("onDriveRemindStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p1(int i) {
            ta taVar = new ta("onIntelligentSpeedLimitControl2D5StateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p2(int i) {
            ta taVar = new ta("onSideDistanceWarningStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r(int i) {
            ta taVar = new ta("onAiDistanceLightStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r0(int i) {
            ta taVar = new ta("onDrivieDepartureStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r1(int i) {
            ta taVar = new ta("onIntelligentSpeedLimitControl32FStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r2(int i) {
            ta taVar = new ta("onSpeedLimitRange2B4Changed ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t(int i) {
            ta taVar = new ta("onAiEmergencyBrakeStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t0(int i) {
            ta taVar = new ta("onDrivieRemindStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t1(int i) {
            ta taVar = new ta("onIntelligentSpeedLimitControlStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t2(int i) {
            ta taVar = new ta("onSpeedLimitRange2D5Changed ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v(int i) {
            ta taVar = new ta("onAiMatrixLight3ACStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v0(int i) {
            ta taVar = new ta("onE2EAIAssistanceStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v1(int i) {
            ta taVar = new ta("onInteractiveLaneChangeAssistanceStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: u2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void v2(int i) {
            ta taVar = new ta("onSpeedLimitRangeChanged ");
            taVar.c("value=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x(int i) {
            ta taVar = new ta("onAiMatrixLight3ADStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x0(int i) {
            ta taVar = new ta("onELKTakeOverStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x1(int i) {
            ta taVar = new ta("onIntersectionCollisionAlertStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: w2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void x2(int i) {
            ta taVar = new ta("onSteepDescentSlowDownStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z(int i) {
            ta taVar = new ta("onAiMatrixLightStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z0(int i) {
            ta taVar = new ta("onElecCarBodyStablySysStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z1(int i) {
            ta taVar = new ta("onLDWFaultStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: y2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void z2(int i) {
            ta taVar = new ta("onTSRLitSpdChgInformStateChanged ");
            taVar.c("state=" + i);
            DemoDiPilotActivity.this.o.add(taVar);
            DemoDiPilotActivity.this.p.c(DemoDiPilotActivity.this.o);
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onADASOnlineStateChanged(final int i) {
            super.onADASOnlineStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f377a.b(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onADASSoftStateChanged(final int i) {
            super.onADASSoftStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f551a.d(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAEBFaultStateStateChanged(final int i) {
            super.onAEBFaultStateStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f601a.f(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAINavigationStateChanged(final int i) {
            super.onAINavigationStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f848a.h(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAbnormalVehicleWarningStateChanged(final int i) {
            super.onAbnormalVehicleWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.y3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f874a.j(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiCareStateChanged(final int i) {
            super.onAiCareStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.q4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f702a.l(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiDistanceLight2B4StateChanged(final int i) {
            super.onAiDistanceLight2B4StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f345a.n(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiDistanceLight316StateChanged(final int i) {
            super.onAiDistanceLight316StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f679a.p(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiDistanceLightStateChanged(final int i) {
            super.onAiDistanceLightStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f400a.r(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiEmergencyBrakeStateChanged(final int i) {
            super.onAiEmergencyBrakeStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f767a.t(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiMatrixLight3ACStateChanged(final int i) {
            super.onAiMatrixLight3ACStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f605a.v(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiMatrixLight3ADStateChanged(final int i) {
            super.onAiMatrixLight3ADStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f448a.x(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiMatrixLightStateChanged(final int i) {
            super.onAiMatrixLightStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f450a.z(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiTurnLightStateChanged(final int i) {
            super.onAiTurnLightStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f746a.B(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAlarmTypeChanged(final int i) {
            super.onAlarmTypeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f675a.D(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAttentionDetectionStateChanged(final int i) {
            super.onAttentionDetectionStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f423a.F(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAutoParkingStateChanged(final int i) {
            super.onAutoParkingStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f474a.H(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onBlindMonitorStateChanged(final int i) {
            super.onBlindMonitorStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f580a.J(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onBlindMonitorStateChanged1(final int i) {
            super.onBlindMonitorStateChanged1(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f553a.L(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onBlindMonitorStateChanged2(final int i) {
            super.onBlindMonitorStateChanged2(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f813a.N(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onChargerCoverStateChanged(final int i) {
            super.onChargerCoverStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f496a.P(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onChildCareStateChanged(final int i) {
            super.onChildCareStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f724a.R(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onCollisionWarningStateChanged(final int i) {
            super.onCollisionWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.u2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f788a.T(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onControlLossWarningStateChanged(final int i) {
            super.onControlLossWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f652a.V(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onCpdDelayStatusChanged(final int i) {
            super.onCpdDelayStatusChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f850a.X(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onCpdStatusChanged(final int i) {
            super.onCpdStatusChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.y1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f870a.Z(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDangerousActionDetectionStateChanged(final int i) {
            super.onDangerousActionDetectionStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f854a.b0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDataChanged(c cVar) {
            super.onDataChanged(cVar);
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
            super.onDataEventChanged(i, bYDAutoEventValue);
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDistractionDetectionAidStateChanged(final int i) {
            super.onDistractionDetectionAidStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f547a.d0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDmsStatusChanged(final int i) {
            super.onDmsStatusChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f295a.f0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDnpStatusChanged(final int i) {
            super.onDnpStatusChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f576a.h0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDoNotPassWarningStateChanged(final int i) {
            super.onDoNotPassWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f421a.j0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDriveDepartureStateChanged(final int i) {
            super.onDriveDepartureStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f291a.l0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDriveLevelChanged(final int i) {
            super.onDriveLevelChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f523a.n0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDriveRemindStateChanged(final int i) {
            super.onDriveRemindStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f815a.p0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDrivieDepartureStateChanged(final int i) {
            super.onDrivieDepartureStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f478a.r0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDrivieRemindStateChanged(final int i) {
            super.onDrivieRemindStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f293a.t0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onE2EAIAssistanceStateChanged(final int i) {
            super.onE2EAIAssistanceStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f677a.v0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onELKTakeOverStateChanged(final int i) {
            super.onELKTakeOverStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f897a.x0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onElecCarBodyStablySysStateChanged(final int i) {
            super.onElecCarBodyStablySysStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f525a.z0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onEmergencyBrakeWarningStateChanged(final int i) {
            super.onEmergencyBrakeWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f578a.B0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onEmergencyLaneAssistance2F0StateChanged(final int i) {
            super.onEmergencyLaneAssistance2F0StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f765a.D0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onEmergencyLaneAssistance32FStateChanged(final int i) {
            super.onEmergencyLaneAssistance32FStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f476a.F0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onEmergencyLaneAssistanceStateChanged(final int i) {
            super.onEmergencyLaneAssistanceStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f807a.H0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onEmergencyVehicleWarningStateChanged(final int i) {
            super.onEmergencyVehicleWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.h2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f472a.J0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onError(int i, String str) {
            super.onError(i, str);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onFCWFaultStateChanged(final int i) {
            super.onFCWFaultStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f831a.L0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onFatigueDetectionAidStateChanged(final int i) {
            super.onFatigueDetectionAidStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.y4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f876a.N0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onFaultHandlerStateChanged(final int i) {
            super.onFaultHandlerStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f398a.P0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onFrontCarStartStateChange(final int i) {
            super.onFrontCarStartStateChange(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.q3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f700a.R0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onFrontTrafficBrakeAlertStateChanged(final int i) {
            super.onFrontTrafficBrakeAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f347a.T0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onFrontTrafficCrossAlertStateChanged(final int i) {
            super.onFrontTrafficCrossAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f722a.V0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onGazePerceptionStateChanged(final int i) {
            super.onGazePerceptionStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f603a.X0(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onGestureRecognitionStateChanged(final int i) {
            super.onGestureRecognitionStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f771a.Z0(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onGreenLightOptimalSpeedAdvisoryStateChanged(final int i) {
            super.onGreenLightOptimalSpeedAdvisoryStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f720a.b1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onHighSpeedAINavigationStateChanged(final int i) {
            super.onHighSpeedAINavigationStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f630a.d1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onHighSpeedOpenWindowStateChanged(final int i) {
            super.onHighSpeedOpenWindowStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.y2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f872a.f1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onISLIWarningStateChanged(final int i) {
            super.onISLIWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f656a.h1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onIntelligentSpeedLimitAlert2B4StateChanged(final int i) {
            super.onIntelligentSpeedLimitAlert2B4StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f835a.j1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onIntelligentSpeedLimitAlert2D5StateChanged(final int i) {
            super.onIntelligentSpeedLimitAlert2D5StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f500a.l1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onIntelligentSpeedLimitAlertStateChanged(final int i) {
            super.onIntelligentSpeedLimitAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f658a.n1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onIntelligentSpeedLimitControl2D5StateChanged(final int i) {
            super.onIntelligentSpeedLimitControl2D5StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f498a.p1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onIntelligentSpeedLimitControl32FStateChanged(final int i) {
            super.onIntelligentSpeedLimitControl32FStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f343a.r1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onIntelligentSpeedLimitControlStateChanged(final int i) {
            super.onIntelligentSpeedLimitControlStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f322a.t1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onInteractiveLaneChangeAssistanceStateChanged(final int i) {
            super.onInteractiveLaneChangeAssistanceStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f628a.v1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onIntersectionCollisionAlertStateChanged(final int i) {
            super.onIntersectionCollisionAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f318a.x1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLDWFaultStateChanged(final int i) {
            super.onLDWFaultStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f811a.z1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLKSFaultStateStateChanged(final int i) {
            super.onLKSFaultStateStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f371a.B1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLaneAssistTypeChanged(final int i) {
            super.onLaneAssistTypeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f632a.D1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLeftBehindReminderStateChanged(final int i) {
            super.onLeftBehindReminderStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.a2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f289a.F1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLowSpeedEmergencyBrakingStateChanged(final int i) {
            super.onLowSpeedEmergencyBrakingStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f750a.H1(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onMechanismPositionChanged(final int i) {
            super.onMechanismPositionChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f452a.J1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onMistakeGasStateChanged(final int i) {
            super.onMistakeGasStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f748a.L1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onOpenDoorAlertStateChanged(final int i) {
            super.onOpenDoorAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f895a.N1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onPredictCrashAlert138ModeChanged(final int i) {
            super.onPredictCrashAlert138ModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f769a.P1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onPredictCrashAlert32DModeChanged(final int i) {
            super.onPredictCrashAlert32DModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.u3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f790a.R1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onPredictCrashAlertModeChanged(final int i) {
            super.onPredictCrashAlertModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f599a.T1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onPredictCrashAlertStateChange(final int i) {
            super.onPredictCrashAlertStateChange(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f574a.V1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRainOpenFogLightStateChanged(final int i) {
            super.onRainOpenFogLightStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f316a.X1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRearCrashAlertStateChanged(final int i) {
            super.onRearCrashAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f827a.Z1(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRearTrafficBrakeAlertStateChanged(final int i) {
            super.onRearTrafficBrakeAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f833a.b2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRearTrafficCrossAlertStateChanged(final int i) {
            super.onRearTrafficCrossAlertStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.v2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f809a.d2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRecommendRestSpotStateChanged(final int i) {
            super.onRecommendRestSpotStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.i5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f502a.f2(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRedLightViolationWarningStateChanged(final int i) {
            super.onRedLightViolationWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.q5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f704a.h2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRelieveFatigueStateChanged(final int i) {
            super.onRelieveFatigueStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.c5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f349a.j2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRemoteControlDriveCloseTops(final int i) {
            super.onRemoteControlDriveCloseTops(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f521a.l2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onRequestHUNPClose(final int i) {
            super.onRequestHUNPClose(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.p5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f681a.n2(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onSideDistanceWarningStateChanged(final int i) {
            super.onSideDistanceWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.s2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f744a.p2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onSpeedLimitRange2B4Changed(final int i) {
            super.onSpeedLimitRange2B4Changed(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f654a.r2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onSpeedLimitRange2D5Changed(final int i) {
            super.onSpeedLimitRange2D5Changed(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f626a.t2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onSpeedLimitRangeChanged(final int i) {
            super.onSpeedLimitRangeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.j5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f527a.v2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onSteepDescentSlowDownStateChanged(final int i) {
            super.onSteepDescentSlowDownStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.u1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f786a.x2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTSRLitSpdChgInformStateChanged(final int i) {
            super.onTSRLitSpdChgInformStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f427a.z2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTSRLitSpdChgSwitchStateChanged(final int i) {
            super.onTSRLitSpdChgSwitchStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.b4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f320a.B2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTemperatureWhileFatigueChanged(final int i) {
            super.onTemperatureWhileFatigueChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.u4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f792a.D2(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficIncidentWarningStateChanged(final int i) {
            super.onTrafficIncidentWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f425a.F2(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficJamWarningStateChanged(final int i) {
            super.onTrafficJamWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.w2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f829a.H2(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficLightInfoPushStateChanged(final int i) {
            super.onTrafficLightInfoPushStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f891a.J2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficLightRecognize2B4StateChanged(final int i) {
            super.onTrafficLightRecognize2B4StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f726a.L2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficLightRecognize2D5StateChanged(final int i) {
            super.onTrafficLightRecognize2D5StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.u5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f794a.N2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficLightRecognizeStateChanged(final int i) {
            super.onTrafficLightRecognizeStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.z2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f893a.P2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignRecognize2B4StateChanged(final int i) {
            super.onTrafficSignRecognize2B4StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f375a.R2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignRecognize2D5StateChanged(final int i) {
            super.onTrafficSignRecognize2D5StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f394a.T2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignRecognize316StateChanged(final int i) {
            super.onTrafficSignRecognize316StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.k3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f549a.V2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignRecognizeStateChanged(final int i) {
            super.onTrafficSignRecognizeStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.x3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f852a.X2(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignalWarningStateChanged(final int i) {
            super.onTrafficSignalWarningStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.q2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f698a.Z2(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onUrbanAIAssistanceStateChanged(final int i) {
            super.onUrbanAIAssistanceStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.e3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f396a.b3(i);
                }
            });
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onVRUFaultStateStateChanged(final int i) {
            super.onVRUFaultStateStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f373a.d3(i);
                }
            });
        }

        @Override // b.a.a.i.h.sa, android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onVerifyModeChanged(final int i) {
            super.onVerifyModeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f446a.f3(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: B0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void C0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: x0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void y0(ta taVar, int i) {
        String str;
        String str2 = taVar.f780a;
        str2.hashCode();
        switch (str2) {
            case "获取前方拥堵提醒 getTrafficJamWarningState":
                str = taVar.f780a + " = " + this.j.O0();
                break;
            case "获取注意力监测getAttentionDetectionState":
                str = "获取注意力监测 getAttentionDetectionState = " + this.j.p();
                break;
            case "获取VRU异常状态 getVRUFaultState":
                str = taVar.f780a + " = " + this.j.Z0();
                break;
            case "获取危险动作监测getDangerousActionDetectionState":
                str = "获取危险动作监测 getDangerousActionDetectionState = " + this.j.B();
                break;
            case "获取DNP状态 getDnpStatus":
                str = taVar.f780a + " = " + this.j.E();
                break;
            case "获取生命体检测系统/儿童遗留检测开关状态getCpdStatus":
                str = "获取生命体检测系统/儿童遗留检测开关状态getCpdStatus = " + this.j.z();
                break;
            case "获取前方交通穿行制动getFrontTrafficBrakeAlertState":
                str = "获取前方交通穿行制动 getFrontTrafficBrakeAlertState = " + this.j.V();
                break;
            case "获取智能矩阵光灯getAiMatrixLightState":
                str = "获取智能矩阵光灯 getAiMatrixLightState = " + this.j.m();
                break;
            case "获取休息点推荐getRecommendRestSpotState":
                str = "获取休息点推荐 getRecommendRestSpotState = " + this.j.C0();
                break;
            case "获取疲劳检测辅助getFatigueDetectionAidState":
                str = "获取疲劳检测辅助 getFatigueDetectionAidState = " + this.j.S();
                break;
            case "获取预测性碰撞报警（较晚/适中/较早/关闭）138getPredictCrashAlert138Mode":
                str = "预测性碰撞报警（较晚/适中/较早/关闭）138 getPredictCrashAlert138Mode = " + this.j.u0();
                break;
            case "获取手势控制getGestureRecognitionState":
                str = "获取手势控制 getGestureRecognitionState = " + this.j.Y();
                break;
            case "获取交通标志识别getTrafficSignRecognizeState":
                str = "获取交通标志识别 getTrafficSignRecognizeState = " + this.j.W0();
                break;
            case "获取车道辅助异常状态 getLKSFaultState":
                str = taVar.f780a + " = " + this.j.n0();
                break;
            case "获取自动驻车getAutoParkingState":
                str = "获取自动驻车 getAutoParkingState = " + this.j.q();
                break;
            case "获取防油门误踩getMistakeGasState":
                str = "获取防油门误踩 getMistakeGasState = " + this.j.s0();
                break;
            case "获取智能关怀getAiCareState":
                str = "获取智能关怀 getAiCareState = " + this.j.f();
                break;
            case "获取逆向超车预警 getDoNotPassWarningState":
                str = taVar.f780a + " = " + this.j.F();
                break;
            case "获取自动紧急制动异常状态 getAEBFaultState":
                str = taVar.f780a + " = " + this.j.c();
                break;
            case "获取盲区监测getBlindMonitorState":
                str = "获取盲区监测 getBlindMonitorState = " + this.j.r();
                break;
            case "获取异常车辆预警 getAbnormalVehicleWarningState":
                str = taVar.f780a + " = " + this.j.e();
                break;
            case "获取智能限速报警2D5getIntelligentSpeedLimitAlert2D5State":
                str = "获取智能限速报警2D5 getIntelligentSpeedLimitAlert2D5State = " + this.j.f0();
                break;
            case "获取智能矩阵光灯3ADgetAiMatrixLight3ADState":
                str = "获取智能矩阵光灯3AD getAiMatrixLight3ADState = " + this.j.l();
                break;
            case "获取生命体检测系统/儿童遗留检测开关状态getCpdDelayStatus":
                str = "获取生命体检测系统/儿童遗留检测开关状态getCpdDelayStatuse = " + this.j.y();
                break;
            case "获取盲区监测2getBlindMonitorState2":
                str = "盲区监测2 getBlindMonitorState2 = " + this.j.t();
                break;
            case "获取交通信号灯识别开关2D5getTrafficLightRecognize2D5State":
                str = "获取交通信号灯识别开关2D5 getTrafficLightRecognize2D5State = " + this.j.R0();
                break;
            case "获取疲劳时温度调节getTemperatureWhileFatigue":
                str = "获取疲劳时温度调节 getTemperatureWhileFatigue = " + this.j.M0();
                break;
            case "获取开门预警getOpenDoorAlertState":
                str = "获取开门预警 getOpenDoorAlertState = " + this.j.t0();
                break;
            case "获取智能远光灯getAiDistanceLightState":
                str = "获取智能远光灯 getAiDistanceLightState = " + this.j.i();
                break;
            case "获取陡坡缓降getSteepDescentSlowDownState":
                str = "获取陡坡缓降 getSteepDescentSlowDownState = " + this.j.J0();
                break;
            case "获取注视感知getGazePerceptionState":
                str = "获取注视感知 getGazePerceptionState = " + this.j.X();
                break;
            case "获取智能限速控制32FgetIntelligentSpeedLimitControl32FState":
                str = "获取智能限速控制32F getIntelligentSpeedLimitControl32FState = " + this.j.i0();
                break;
            case "获取紧急车道偏离辅助开关状态2F0getEmergencyLaneAssistance2F0State":
                str = "获取紧急车道偏离辅助开关状态2F0 getEmergencyLaneAssistance2F0State = " + this.j.N();
                break;
            case "获取智能限速报警getIntelligentSpeedLimitAlertState":
                str = "获取智能限速报警 getIntelligentSpeedLimitAlertState = " + this.j.g0();
                break;
            case "获取智能远光灯2B4getAiDistanceLight2B4State":
                str = "获取智能远光灯2B4 getAiDistanceLight2B4State = " + this.j.g();
                break;
            case "获取升降机构位置信号 getMechanismPosition":
                str = taVar.f780a + " = " + this.j.r0();
                break;
            case "获取交通标志识别316getTrafficSignRecognize316State":
                str = "获取交通标志识别316 getTrafficSignRecognize316State = " + this.j.V0();
                break;
            case "获取交叉路口碰撞预警 getIntersectionCollisionAlertState":
                str = taVar.f780a + " = " + this.j.l0();
                break;
            case "获取限速报警灵敏度getSpeedLimitRange":
                str = "获取限速报警灵敏度 getSpeedLimitRange = " + this.j.G0();
                break;
            case "获取紧急车道偏离辅助开关状态getEmergencyLaneAssistanceState":
                str = "获取紧急车道偏离辅助开关状态 getEmergencyLaneAssistanceState = " + this.j.P();
                break;
            case "获取故障处理提示功能getFaultHandlerState":
                str = "获取故障处理提示功能 getFaultHandlerState = " + this.j.T();
                break;
            case "获取交互式变道辅助（拨杆变道辅助）getInteractiveLaneChangeAssistanceState":
                str = "获取交互式变道辅助（拨杆变道辅助） getInteractiveLaneChangeAssistanceState = " + this.j.k0();
                break;
            case "获取雨天雾灯开启提醒功能getRainOpenFogLightState":
                str = "获取雨天雾灯开启提醒功能 getRainOpenFogLightState = " + this.j.y0();
                break;
            case "获取限速报警灵敏度2B4getSpeedLimitRange2B4":
                str = "获取限速报警灵敏度2B4 getSpeedLimitRange2B4 = " + this.j.H0();
                break;
            case "获取交通信号灯信息推送 getTrafficLightInfoPushState":
                str = taVar.f780a + " = " + this.j.P0();
                break;
            case "获取脱手检测状态getELKTakeOverState":
                str = "获取脱手检测状态getELKTakeOverState = " + this.j.K();
                break;
            case "获取交通标志识别2D5getTrafficSignRecognize2D5State":
                str = "获取交通标志识别2D5 getTrafficSignRecognize2D5State = " + this.j.U0();
                break;
            case "获取侧边距离报警 getSideDistanceWarningState":
                str = taVar.f780a + " = " + this.j.F0();
                break;
            case "获取ADAS系统在线状态getADASOnlineState":
                str = "获取ADAS系统在线状态getADASOnlineState = " + this.j.a();
                break;
            case "获取超速报警等级getISLIWarningState":
                str = "获取超速报警等级getISLIWarningState = " + this.j.c0();
                break;
            case "获取城市智能领航辅助（高级导航辅助驾驶）getUrbanAIAssistanceState":
                str = "获取城市智能领航辅助（高级导航辅助驾驶） getUrbanAIAssistanceState = " + this.j.Y0();
                break;
            case "获取交通标志识别2B4getTrafficSignRecognize2B4State":
                str = "获取交通标志识别2B4 getTrafficSignRecognize2B4State = " + this.j.T0();
                break;
            case "获取预测性碰撞报警模式getPredictCrashAlertMode":
                str = "预测性碰撞报警（较晚/适中/较早/关闭） getPredictCrashAlertMode = " + this.j.w0();
                break;
            case "获取充电盖提醒功能getChargerCoverState":
                str = "获取充电盖提醒功能 getChargerCoverState = " + this.j.u();
                break;
            case "获取报警方式getAlarmType":
                str = "获取报警方式（声音/振动/声音+振动） getAlarmType = " + this.j.o();
                break;
            case "获取限速值变化通知getTSRLitSpdChgInformState":
                str = "获取限速值变化通知getTSRLitSpdChgInformState = " + this.j.K0();
                break;
            case "获取限速报警灵敏度2D5getSpeedLimitRange2D5":
                str = "获取限速报警灵敏度2D5 getSpeedLimitRange2D5 = " + this.j.I0();
                break;
            case "获取交通信号灯识别开关getTrafficLightRecognizeState":
                str = "获取交通信号灯识别开关 getTrafficLightRecognizeState = " + this.j.S0();
                break;
            case "获取疲劳缓解getRelieveFatigueState":
                str = "获取疲劳缓解 getRelieveFatigueState = " + this.j.E0();
                break;
            case "获取电子车身稳定系统getElecCarBodyStablySysState":
                str = "获取电子车身稳定系统 getElecCarBodyStablySysState = " + this.j.L();
                break;
            case "获取手动确认/自动确认getVerifyMode":
                str = "手动确认/自动确认 getVerifyMode = " + this.j.a1();
                break;
            case "获取紧急车道偏离辅助开关状态32FgetEmergencyLaneAssistance32FState":
                str = "获取紧急车道偏离辅助开关状态32F getEmergencyLaneAssistance32FState = " + this.j.O();
                break;
            case "获取限速变化声音提示状态getTSRLitSpdChgSwitchState":
                str = "获取限速变化声音提示状态getTSRLitSpdChgSwitchState = " + this.j.L0();
                break;
            case "获取儿童关怀getChildCareState":
                str = "获取儿童关怀 getChildCareState = " + this.j.v();
                break;
            case "获取驾驶等级getDriveLevel":
                str = "获取驾驶等级 getDriveLevel = " + this.j.H();
                break;
            case "获取智能限速控制2D5getIntelligentSpeedLimitControl2D5State":
                str = "获取智能限速控制2D5 getIntelligentSpeedLimitControl2D5State = " + this.j.h0();
                break;
            case "获取紧急车辆提醒 getEmergencyVehicleWarningState":
                str = taVar.f780a + " = " + this.j.Q();
                break;
            case "获取紧急制动预警 getEmergencyBrakeWarningState":
                str = taVar.f780a + " = " + this.j.M();
                break;
            case "获取智能限速控制getIntelligentSpeedLimitControlState":
                str = "获取智能限速控制 getIntelligentSpeedLimitControlState = " + this.j.j0();
                break;
            case "获取高级导航辅助驾驶getHighSpeedAINavigationState":
                str = "获取高级导航辅助驾驶 getHighSpeedAINavigationState = " + this.j.a0();
                break;
            case "获取E2E智能领航辅助（高级导航辅助驾驶）getE2EAIAssistanceState":
                str = "获取E2E智能领航辅助（高级导航辅助驾驶） getE2EAIAssistanceState = " + this.j.J();
                break;
            case "获取预测性碰撞报警（较晚/适中/较早/关闭）32DgetPredictCrashAlert32DMode":
                str = "预测性碰撞报警（较晚/适中/较早/关闭）32D getPredictCrashAlert32DMode = " + this.j.v0();
                break;
            case "获取分心检测辅助getDistractionDetectionAidState":
                str = "获取分心检测辅助 getDistractionDetectionAidState = " + this.j.C();
                break;
            case "获取绿波车速引导 getGreenLightOptimalSpeedAdvisoryState":
                str = taVar.f780a + " = " + this.j.Z();
                break;
            case "获取高速车窗开启提醒功能getHighSpeedOpenWindowState":
                str = "获取高速车窗开启提醒功能 getHighSpeedOpenWindowState = " + this.j.b0();
                break;
            case "获取交通事件提醒 getTrafficIncidentWarningState":
                str = taVar.f780a + " = " + this.j.N0();
                break;
            case "获取后方碰撞预警getRearCrashAlertState":
                str = "获取后方碰撞预警 getRearCrashAlertState = " + this.j.z0();
                break;
            case "获取智能矩阵光灯3ACgetAiMatrixLight3ACState":
                str = "获取智能矩阵光灯3AC getAiMatrixLight3ACState = " + this.j.k();
                break;
            case "获取盲区监测1getBlindMonitorState1":
                str = "盲区监测1 getBlindMonitorState1 = " + this.j.s();
                break;
            case "获取闯红灯预警 getRedLightViolationWarningState":
                str = taVar.f780a + " = " + this.j.D0();
                break;
            case "获取FCW异常状态 getFCWFaultState":
                str = taVar.f780a + " = " + this.j.R();
                break;
            case "获取预测性碰撞报警开关getPredictCrashAlertState":
                str = "获取预测性碰撞报警（打开/关闭） getPredictCrashAlertState = " + this.j.x0();
                break;
            case "获取车道辅助类型getLaneAssistType":
                str = "获取车道辅助类型 getLaneAssistType = " + this.j.o0();
                break;
            case "获取智能领航getAINavigationState":
                str = "获取智能领航 getAINavigationState = " + this.j.d();
                break;
            case "获取驾驶状态提醒功能getDriveRemindState":
                str = "获取驾驶状态提醒功能 getDriveRemindState = " + this.j.I();
                break;
            case "获取智能远光灯316getAiDistanceLight316State":
                str = "获取智能远光灯316 getAiDistanceLight316State = " + this.j.h();
                break;
            case "获取智能限速报警2B4getIntelligentSpeedLimitAlert2B4State":
                str = "获取智能限速报警2B4 getIntelligentSpeedLimitAlert2B4State = " + this.j.e0();
                break;
            case "获取交通信号提醒 getTrafficSignalWarningState":
                str = taVar.f780a + " = " + this.j.X0();
                break;
            case "获取交通信号灯识别开关2B4getTrafficLightRecognize2B4State":
                str = "获取交通信号灯识别开关2B4 getTrafficLightRecognize2B4State = " + this.j.Q0();
                break;
            case "获取LDA功能软件版本getADASSoftVersion":
                str = "获取LDA功能软件版本getADASSoftVersion = " + this.j.b();
                break;
            case "获取车辆失控预警 getControlLossWarningState":
                str = taVar.f780a + " = " + this.j.x();
                break;
            case "获取智能转向灯功能getAiTurnLightState":
                str = "获取智能转向灯功能 getAiTurnLightState = " + this.j.n();
                break;
            case "获取获取DNP当前状态getDNPState":
                str = "获取获取DNP当前状态 getDNPState = " + this.j.A();
                break;
            case "获取车辆碰撞预警 getCollisionWarningState":
                str = taVar.f780a + " = " + this.j.w();
                break;
            case "获取后方交通穿行制动getRearTrafficBrakeAlertState":
                str = "获取后方交通穿行制动 getRearTrafficBrakeAlertState = " + this.j.A0();
                break;
            case "获取驾驶员监测辅助开关状态getDmsSwitchStatus":
                str = "获取驾驶员监测辅助开关状态getDmsSwitchStatus = " + this.j.D();
                break;
            case "获取低速紧急制动 getLowSpeedEmergencyBrakingState":
                str = taVar.f780a + " = " + this.j.q0();
                break;
            case "获取车道保持异常状态 getLDWFaultState":
                str = taVar.f780a + " = " + this.j.m0();
                break;
            case "获取安全驶离提醒功能getDriveDepartureState":
                str = "获取安全驶离提醒功能 getDriveDepartureState = " + this.j.G();
                break;
            case "获取自动紧急制动getAiEmergencyBrakeState":
                str = "获取自动紧急制动 getAiEmergencyBrakeState = " + this.j.j();
                break;
            case "获取后方交通穿行预警getRearTrafficCrossAlertState":
                str = "获取后方交通穿行预警 getRearTrafficCrossAlertState = " + this.j.B0();
                break;
            case "获取前车起步提醒功能getFrontCarStartState":
                str = "获取前车起步提醒功能 getFrontCarStartState = " + this.j.U();
                break;
            case "获取前方交通穿行预警getFrontTrafficCrossAlertState":
                str = "获取前方交通穿行预警 getFrontTrafficCrossAlertState = " + this.j.W();
                break;
            case "获取遗留物提醒getLeftBehindReminderState":
                str = "获取遗留物提醒 getLeftBehindReminderState = " + this.j.p0();
                break;
            default:
                str = "";
                break;
        }
        this.l.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: z0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void A0(ta taVar, int i) {
        int i2;
        Editable text = this.i.getText();
        i2 = (text == null || TextUtils.isEmpty(text.toString())) ? 0 : Integer.parseInt(text.toString());
        String str = taVar.f780a + i2;
        String str2 = taVar.f780a;
        str2.hashCode();
        switch (str2) {
            case "设置疲劳检测辅助setFatigueDetectionAidState":
                this.j.J1(i2);
                break;
            case "设置手动确认/自动确认setVerifyMode":
                this.j.z2(i2);
                break;
            case "设置紧急车道偏离辅助开关状态setEmergencyLaneAssistanceState":
                this.j.H1(i2);
                break;
            case "设置后方交通穿行制动setRearTrafficBrakeAlertState":
                this.j.i2(i2);
                break;
            case "设置交通标志识别setTrafficSignRecognizeState":
                this.j.w2(i2);
                break;
            case "设置休息点推荐setRecommendRestSpotState":
                this.j.k2(i2);
                break;
            case "设置充电盖提醒功能setChargerCoverState":
                this.j.p1(i2);
                break;
            case "设置后方交通穿行预警setRearTrafficCrossAlertState":
                this.j.j2(i2);
                break;
            case "设置智能远光灯setAiDistanceLightState":
                this.j.h1(i2);
                break;
            case "设置智能领航setAINavigationState":
                this.j.e1(i2);
                break;
            case "设置逆向超车预警 setDoNotPassWarningState":
                this.j.A1(i2);
                break;
            case "设置安全驶离提醒功能setDriveDeparturState":
                this.j.B1(i2);
                break;
            case "设置HNP/UNP功能请求关闭requestHUNPClose":
                this.j.d1();
                break;
            case "设置车辆失控预警 setControlLossWarningState":
                this.j.s1(i2);
                break;
            case "设置陡坡缓降setSteepDescentSlowDownState":
                this.j.p2(i2);
                break;
            case "设置驾驶状态提醒功能setDriveRemindState":
                this.j.D1(i2);
                break;
            case "设置生命体检测系统/儿童遗留检测开关状态setCpdStatus":
                this.j.t1(i2);
                break;
            case "设置遗留物提醒setLeftBehindReminderState":
                this.j.a2(i2);
                break;
            case "设置疲劳监测状态 setFatigueMonitoringStatus":
                this.j.K1(i2);
                break;
            case "设置儿童关怀setChildCareState":
                this.j.q1(i2);
                break;
            case "设置交互式变道辅助（拨杆变道辅助）setInteractiveLaneChangeAssistanceState":
                this.j.X1(i2);
                break;
            case "设置限速报警灵敏度setSpeedLimitRange":
                this.j.o2(i2);
                break;
            case "设置注意力监测setAttentionDetectionState":
                this.j.m1(i2);
                break;
            case "设置危险动作监测setDangerousActionDetectionState":
                this.j.u1(i2);
                break;
            case "设置交通信号灯识别开关setTrafficLightRecognizeState":
                this.j.v2(i2);
                break;
            case "设置智能转向灯功能setAiTurnLightState":
                this.j.k1(i2);
                break;
            case "设置注视感知setGazePerceptionState":
                this.j.Q1(i2);
                break;
            case "设置城市智能领航辅助（高级导航辅助驾驶）setUrbanAIAssistanceState":
                this.j.y2(i2);
                break;
            case "设置后方碰撞预警setRearCrashAlertState":
                this.j.h2(i2);
                break;
            case "设置防油门误踩setMistakeGasState":
                this.j.c2(i2);
                break;
            case "设置智能矩阵光灯setAiMatrixLightState":
                this.j.j1(i2);
                break;
            case "设置驾驶员监测辅助开关状态setDmsSwitchStatus":
                this.j.z1(i2);
                break;
            case "设置紧急车辆提醒 setEmergencyVehicleWarningState":
                this.j.I1(i2);
                break;
            case "设置异常车辆预警 setAbnormalVehicleWarningState":
                this.j.f1(i2);
                break;
            case "设置报警方式（声音/振动/声音+振动）setAlarmType":
                this.j.l1(i2);
                break;
            case "设置分心监测注意力监测 setDistractionMonitoringAttention":
                this.j.x1(i2);
                break;
            case "设置绿波车速引导 setGreenLightOptimalSpeedAdvisoryState":
                this.j.S1(i2);
                break;
            case "设置盲区监测setBlindMonitorState":
                this.j.o1(i2);
                break;
            case "设置DMS自检状态 setDmsSelfCheckStatus":
                this.j.y1(i2);
                break;
            case "设置交通信号灯信息推送 setTrafficLightInfoPushState":
                this.j.u2(i2);
                break;
            case "设置自动紧急制动setAiEmergencyBrakeState":
                this.j.i1(i2);
                break;
            case "设置开门预警setOpenDoorAlertState":
                this.j.d2(i2);
                break;
            case "设置疲劳时温度调节setTemperatureWhileFatigue":
                this.j.r2(i2);
                break;
            case "设置高速车窗开启提醒功能setHighSpeedOpenWindowState":
                this.j.U1(i2);
                break;
            case "设置交通信号提醒 setTrafficSignalWarningState":
                this.j.x2(i2);
                break;
            case "设置疲劳缓解开关（驾驶员疲劳提醒开关） setFatigueReliefSwitch":
                this.j.L1(i2);
                break;
            case "设置疲劳缓解setRelieveFatigueState":
                this.j.m2(i2);
                break;
            case "设置危险动作开关（驾驶员分心提醒开关） setDangerousActionfSwitch":
                this.j.v1(i2);
                break;
            case "设置车道辅助类型setLaneAssistType":
                this.j.Z1(i2);
                break;
            case "设置故障处理提示功能setFaultHandlerState":
                this.j.M1(i2);
                break;
            case "设置预测性碰撞报警（打开/关闭）setPredictCrashAlertState":
                this.j.f2(i2);
                break;
            case "设置预测性碰撞报警（较晚/适中/较早/关闭）setPredictCrashAlertMode":
                this.j.e2(i2);
                break;
            case "设置紧急制动预警 setEmergencyBrakeWarningState":
                this.j.G1(i2);
                break;
            case "设置限速变化声音提示状态setTSRLitSpdChgSwitchState":
                this.j.q2(i2);
                break;
            case "设置侧边距离报警 setSideDistanceWarningState":
                this.j.n2(i2);
                break;
            case "设置高级导航辅助驾驶setHighSpeedAINavigationState":
                this.j.T1(i2);
                break;
            case "设置自动驻车setAutoParkingState":
                this.j.n1(i2);
                break;
            case "设置前车起步提醒功能setFrontCarStartState":
                this.j.N1(i2);
                break;
            case "设置分心检测辅助setDistractionDetectionAidState":
                this.j.w1(i2);
                break;
            case "设置雨天雾灯开启提醒功能setRainOpenFogLightState":
                this.j.g2(i2);
                break;
            case "设置前方拥堵提醒 setTrafficJamWarningState":
                this.j.t2(i2);
                break;
            case "设置前方交通穿行预警setFrontTrafficCrossAlertState":
                this.j.P1(i2);
                break;
            case "设置车辆碰撞预警 setCollisionWarningState":
                this.j.r1(i2);
                break;
            case "设置智能关怀setAiCareState":
                this.j.g1(i2);
                break;
            case "设置驾驶等级setDriveLevel":
                this.j.C1(i2);
                break;
            case "设置交叉路口碰撞预警 setIntersectionCollisionAlertState":
                this.j.Y1(i2);
                break;
            case "设置电子车身稳定系统setElecCarBodyStablySysState":
                this.j.F1(i2);
                break;
            case "设置智能限速控制setIntelligentSpeedLimitControlState":
                this.j.W1(i2);
                break;
            case "设置智能限速报警setIntelligentSpeedLimitAlertState":
                this.j.V1(i2);
                break;
            case "设置交通事件提醒 setTrafficIncidentWarningState":
                this.j.s2(i2);
                break;
            case "设置前方交通穿行制动setFrontTrafficBrakeAlertState":
                this.j.O1(i2);
                break;
            case "设置低速紧急制动 setLowSpeedEmergencyBrakingState":
                this.j.b2(i2);
                break;
            case "设置闯红灯预警 setRedLightViolationWarningState":
                this.j.l2(i2);
                break;
            case "设置E2E智能领航辅助（高级导航辅助驾驶）setE2EAIAssistanceState":
                this.j.E1(i2);
                break;
            case "设置手势控制setGestureRecognitionState":
                this.j.R1(i2);
                break;
        }
        this.m.setText(str);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.demo_activity_fragrance;
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void V() {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (w0(currentFocus, motionEvent)) {
                s0(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void f0() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f764a.C0(view);
            }
        });
        v0();
        this.h = (EditText) findViewById(R.id.get_edittext1);
        this.i = (EditText) findViewById(R.id.set_edittext1);
        this.l = (TextView) findViewById(R.id.text);
        this.m = (TextView) findViewById(R.id.set_text);
        this.n = (RecyclerView) findViewById(R.id.callback_recyclerview);
        u0();
        ra raVarD0 = ra.d0();
        this.j = raVarD0;
        raVarD0.b1(this);
        b bVar = new b();
        this.k = bVar;
        this.j.c1(bVar);
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.j.A2(this.k);
        super.onDestroy();
    }

    public final void s0(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    public void t0() {
        this.d.add(new ta("获取高级导航辅助驾驶getHighSpeedAINavigationState"));
        this.d.add(new ta("获取城市智能领航辅助（高级导航辅助驾驶）getUrbanAIAssistanceState"));
        this.d.add(new ta("获取E2E智能领航辅助（高级导航辅助驾驶）getE2EAIAssistanceState"));
        this.d.add(new ta("获取交互式变道辅助（拨杆变道辅助）getInteractiveLaneChangeAssistanceState"));
        this.d.add(new ta("获取智能领航getAINavigationState"));
        this.d.add(new ta("获取车道辅助类型getLaneAssistType"));
        this.d.add(new ta("获取报警方式getAlarmType"));
        this.d.add(new ta("获取交通标志识别2D5getTrafficSignRecognize2D5State"));
        this.d.add(new ta("获取交通标志识别2B4getTrafficSignRecognize2B4State"));
        this.d.add(new ta("获取交通标志识别316getTrafficSignRecognize316State"));
        this.d.add(new ta("获取限速报警灵敏度2D5getSpeedLimitRange2D5"));
        this.d.add(new ta("获取限速报警灵敏度2B4getSpeedLimitRange2B4"));
        this.d.add(new ta("获取智能限速报警2D5getIntelligentSpeedLimitAlert2D5State"));
        this.d.add(new ta("获取智能限速报警2B4getIntelligentSpeedLimitAlert2B4State"));
        this.d.add(new ta("获取智能限速控制2D5getIntelligentSpeedLimitControl2D5State"));
        this.d.add(new ta("获取智能限速控制32FgetIntelligentSpeedLimitControl32FState"));
        this.d.add(new ta("获取手动确认/自动确认getVerifyMode"));
        this.d.add(new ta("获取防油门误踩getMistakeGasState"));
        this.d.add(new ta("获取智能矩阵光灯3ACgetAiMatrixLight3ACState"));
        this.d.add(new ta("获取智能矩阵光灯3ADgetAiMatrixLight3ADState"));
        this.d.add(new ta("获取智能远光灯2B4getAiDistanceLight2B4State"));
        this.d.add(new ta("获取智能远光灯316getAiDistanceLight316State"));
        this.d.add(new ta("获取交通信号灯识别开关2D5getTrafficLightRecognize2D5State"));
        this.d.add(new ta("获取交通信号灯识别开关2B4getTrafficLightRecognize2B4State"));
        this.d.add(new ta("获取紧急车道偏离辅助开关状态32FgetEmergencyLaneAssistance32FState"));
        this.d.add(new ta("获取紧急车道偏离辅助开关状态2F0getEmergencyLaneAssistance2F0State"));
        this.d.add(new ta("获取盲区监测1getBlindMonitorState1"));
        this.d.add(new ta("获取盲区监测2getBlindMonitorState2"));
        this.d.add(new ta("获取开门预警getOpenDoorAlertState"));
        this.d.add(new ta("获取后方碰撞预警getRearCrashAlertState"));
        this.d.add(new ta("获取后方交通穿行预警getRearTrafficCrossAlertState"));
        this.d.add(new ta("获取后方交通穿行制动getRearTrafficBrakeAlertState"));
        this.d.add(new ta("获取自动紧急制动getAiEmergencyBrakeState"));
        this.d.add(new ta("获取预测性碰撞报警开关getPredictCrashAlertState"));
        this.d.add(new ta("获取预测性碰撞报警（较晚/适中/较早/关闭）138getPredictCrashAlert138Mode"));
        this.d.add(new ta("获取预测性碰撞报警（较晚/适中/较早/关闭）32DgetPredictCrashAlert32DMode"));
        this.d.add(new ta("获取前方交通穿行预警getFrontTrafficCrossAlertState"));
        this.d.add(new ta("获取前方交通穿行制动getFrontTrafficBrakeAlertState"));
        this.d.add(new ta("获取电子车身稳定系统getElecCarBodyStablySysState"));
        this.d.add(new ta("获取自动驻车getAutoParkingState"));
        this.d.add(new ta("获取陡坡缓降getSteepDescentSlowDownState"));
        this.d.add(new ta("获取生命体检测系统/儿童遗留检测开关状态getCpdStatus"));
        this.d.add(new ta("获取生命体检测系统/儿童遗留检测开关状态getCpdDelayStatus"));
        this.d.add(new ta("获取驾驶员监测辅助开关状态getDmsSwitchStatus"));
        this.d.add(new ta("获取ADAS系统在线状态getADASOnlineState"));
        this.d.add(new ta("获取LDA功能软件版本getADASSoftVersion"));
        this.d.add(new ta("获取限速变化声音提示状态getTSRLitSpdChgSwitchState"));
        this.d.add(new ta("获取脱手检测状态getELKTakeOverState"));
        this.d.add(new ta("获取限速值变化通知getTSRLitSpdChgInformState"));
        this.d.add(new ta("获取超速报警等级getISLIWarningState"));
        this.d.add(new ta("获取车道辅助异常状态 getLKSFaultState"));
        this.d.add(new ta("获取车道保持异常状态 getLDWFaultState"));
        this.d.add(new ta("获取自动紧急制动异常状态 getAEBFaultState"));
        this.d.add(new ta("获取FCW异常状态 getFCWFaultState"));
        this.d.add(new ta("获取VRU异常状态 getVRUFaultState"));
        this.d.add(new ta("获取低速紧急制动 getLowSpeedEmergencyBrakingState"));
        this.d.add(new ta("获取侧边距离报警 getSideDistanceWarningState"));
        this.d.add(new ta("获取车辆碰撞预警 getCollisionWarningState"));
        this.d.add(new ta("获取交叉路口碰撞预警 getIntersectionCollisionAlertState"));
        this.d.add(new ta("获取逆向超车预警 getDoNotPassWarningState"));
        this.d.add(new ta("获取紧急制动预警 getEmergencyBrakeWarningState"));
        this.d.add(new ta("获取交通事件提醒 getTrafficIncidentWarningState"));
        this.d.add(new ta("获取异常车辆预警 getAbnormalVehicleWarningState"));
        this.d.add(new ta("获取车辆失控预警 getControlLossWarningState"));
        this.d.add(new ta("获取前方拥堵提醒 getTrafficJamWarningState"));
        this.d.add(new ta("获取紧急车辆提醒 getEmergencyVehicleWarningState"));
        this.d.add(new ta("获取交通信号提醒 getTrafficSignalWarningState"));
        this.d.add(new ta("获取交通信号灯信息推送 getTrafficLightInfoPushState"));
        this.d.add(new ta("获取闯红灯预警 getRedLightViolationWarningState"));
        this.d.add(new ta("获取绿波车速引导 getGreenLightOptimalSpeedAdvisoryState"));
        this.d.add(new ta("获取升降机构位置信号 getMechanismPosition"));
        this.d.add(new ta("获取DNP状态 getDnpStatus"));
        this.e.add(new ta("设置高级导航辅助驾驶setHighSpeedAINavigationState"));
        this.e.add(new ta("设置城市智能领航辅助（高级导航辅助驾驶）setUrbanAIAssistanceState"));
        this.e.add(new ta("设置E2E智能领航辅助（高级导航辅助驾驶）setE2EAIAssistanceState"));
        this.e.add(new ta("设置交互式变道辅助（拨杆变道辅助）setInteractiveLaneChangeAssistanceState"));
        this.e.add(new ta("设置智能领航setAINavigationState"));
        this.e.add(new ta("设置车道辅助类型setLaneAssistType"));
        this.e.add(new ta("设置报警方式（声音/振动/声音+振动）setAlarmType"));
        this.e.add(new ta("设置交通标志识别setTrafficSignRecognizeState"));
        this.e.add(new ta("设置智能关怀setAiCareState"));
        this.e.add(new ta("设置防油门误踩setMistakeGasState"));
        this.e.add(new ta("设置智能矩阵光灯setAiMatrixLightState"));
        this.e.add(new ta("设置智能远光灯setAiDistanceLightState"));
        this.e.add(new ta("设置交通信号灯识别开关setTrafficLightRecognizeState"));
        this.e.add(new ta("设置紧急车道偏离辅助开关状态setEmergencyLaneAssistanceState"));
        this.e.add(new ta("设置盲区监测setBlindMonitorState"));
        this.e.add(new ta("设置开门预警setOpenDoorAlertState"));
        this.e.add(new ta("设置后方碰撞预警setRearCrashAlertState"));
        this.e.add(new ta("设置后方交通穿行预警setRearTrafficCrossAlertState"));
        this.e.add(new ta("设置后方交通穿行制动setRearTrafficBrakeAlertState"));
        this.e.add(new ta("设置自动紧急制动setAiEmergencyBrakeState"));
        this.e.add(new ta("设置预测性碰撞报警（打开/关闭）setPredictCrashAlertState"));
        this.e.add(new ta("设置预测性碰撞报警（较晚/适中/较早/关闭）setPredictCrashAlertMode"));
        this.e.add(new ta("设置前方交通穿行预警setFrontTrafficCrossAlertState"));
        this.e.add(new ta("设置前方交通穿行制动setFrontTrafficBrakeAlertState"));
        this.e.add(new ta("设置电子车身稳定系统setElecCarBodyStablySysState"));
        this.e.add(new ta("设置自动驻车setAutoParkingState"));
        this.e.add(new ta("设置陡坡缓降setSteepDescentSlowDownState"));
        this.e.add(new ta("设置智能限速报警setIntelligentSpeedLimitAlertState"));
        this.e.add(new ta("设置智能限速控制setIntelligentSpeedLimitControlState"));
        this.e.add(new ta("设置手动确认/自动确认setVerifyMode"));
        this.e.add(new ta("设置限速报警灵敏度setSpeedLimitRange"));
        this.e.add(new ta("设置生命体检测系统/儿童遗留检测开关状态setCpdStatus"));
        this.e.add(new ta("设置驾驶员监测辅助开关状态setDmsSwitchStatus"));
        this.e.add(new ta("设置限速变化声音提示状态setTSRLitSpdChgSwitchState"));
        this.e.add(new ta("设置低速紧急制动 setLowSpeedEmergencyBrakingState"));
        this.e.add(new ta("设置侧边距离报警 setSideDistanceWarningState"));
        this.e.add(new ta("设置车辆碰撞预警 setCollisionWarningState"));
        this.e.add(new ta("设置交叉路口碰撞预警 setIntersectionCollisionAlertState"));
        this.e.add(new ta("设置逆向超车预警 setDoNotPassWarningState"));
        this.e.add(new ta("设置紧急制动预警 setEmergencyBrakeWarningState"));
        this.e.add(new ta("设置交通事件提醒 setTrafficIncidentWarningState"));
        this.e.add(new ta("设置异常车辆预警 setAbnormalVehicleWarningState"));
        this.e.add(new ta("设置车辆失控预警 setControlLossWarningState"));
        this.e.add(new ta("设置前方拥堵提醒 setTrafficJamWarningState"));
        this.e.add(new ta("设置紧急车辆提醒 setEmergencyVehicleWarningState"));
        this.e.add(new ta("设置交通信号提醒 setTrafficSignalWarningState"));
        this.e.add(new ta("设置交通信号灯信息推送 setTrafficLightInfoPushState"));
        this.e.add(new ta("设置闯红灯预警 setRedLightViolationWarningState"));
        this.e.add(new ta("设置绿波车速引导 setGreenLightOptimalSpeedAdvisoryState"));
        this.e.add(new ta("设置疲劳监测状态 setFatigueMonitoringStatus"));
        this.e.add(new ta("设置DMS自检状态 setDmsSelfCheckStatus"));
        this.e.add(new ta("设置分心监测注意力监测 setDistractionMonitoringAttention"));
        this.e.add(new ta("设置疲劳缓解开关（驾驶员疲劳提醒开关） setFatigueReliefSwitch"));
        this.e.add(new ta("设置危险动作开关（驾驶员分心提醒开关） setDangerousActionfSwitch"));
    }

    public final void u0() {
        this.n.setLayoutManager(new LinearLayoutManager(this, 1, true));
        CopyOnWriteArrayList<ta> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.o = copyOnWriteArrayList;
        CallbackAdapter callbackAdapter = new CallbackAdapter(this, copyOnWriteArrayList);
        this.p = callbackAdapter;
        this.n.setAdapter(callbackAdapter);
    }

    public final void v0() {
        this.f1029b = (RecyclerView) findViewById(R.id.get_recyclerview);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(this);
        flexboxLayoutManager.U(0);
        flexboxLayoutManager2.V(1);
        flexboxLayoutManager2.U(0);
        flexboxLayoutManager.V(1);
        this.f1029b.setLayoutManager(flexboxLayoutManager);
        this.d = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.set_recyclerview);
        this.f1030c = recyclerView;
        recyclerView.setLayoutManager(flexboxLayoutManager2);
        this.e = new ArrayList();
        t0();
        this.f = new CommonAdapter(this, this.d);
        this.g = new CommonAdapter(this, this.e);
        this.f1029b.setAdapter(this.f);
        this.f1030c.setAdapter(this.g);
        this.f.c(new CommonAdapter.b() { // from class: b.a.a.i.h.y5
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f878a.y0(taVar, i);
            }
        });
        this.g.c(new CommonAdapter.b() { // from class: b.a.a.i.h.x5
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f856a.A0(taVar, i);
            }
        });
    }

    public final boolean w0(View view, MotionEvent motionEvent) {
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
