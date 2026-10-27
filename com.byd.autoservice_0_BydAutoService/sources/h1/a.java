package h1;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.safetybelt.BYDAutoSafetyBeltDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.tyre.BYDAutoTyreDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.byd.autoservice.R;
import com.byd.autoservice.application.AutoApplication;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Calendar;

/* JADX INFO: compiled from: AutoStudyManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f2955a;
    public int A;
    public int A0;
    public int A1;
    public int A2;
    public int A3;
    public int A4;
    public int A5;
    public int A6;
    public int A7;
    public int A8;
    public int A9;
    public AlertDialog Aa;
    public int B;
    public int B0;
    public int B1;
    public int B2;
    public int B3;
    public int B4;
    public int B5;
    public int B6;
    public int B7;
    public int B8;
    public int B9;
    public TextView Ba;
    public int C;
    public int C0;
    public int C1;
    public int C2;
    public int C3;
    public int C4;
    public int C5;
    public int C6;
    public int C7;
    public int C8;
    public int C9;
    public SharedPreferences Ca;
    public int D;
    public int D0;
    public int D1;
    public int D2;
    public int D3;
    public int D4;
    public int D5;
    public int D6;
    public int D7;
    public int D8;
    public int D9;
    public f Da;
    public int E;
    public int E0;
    public int E1;
    public int E2;
    public int E3;
    public int E4;
    public int E5;
    public int E6;
    public int E7;
    public int E8;
    public int E9;
    public int F;
    public int F0;
    public int F1;
    public int F2;
    public int F3;
    public int F4;
    public int F5;
    public int F6;
    public int F7;
    public int F8;
    public int F9;
    public int G;
    public int G0;
    public int G1;
    public int G2;
    public int G3;
    public int G4;
    public int G5;
    public int G6;
    public int G7;
    public int G8;
    public int G9;
    public int H;
    public int H0;
    public int H1;
    public int H2;
    public int H3;
    public int H4;
    public int H5;
    public int H6;
    public int H7;
    public int H8;
    public int H9;
    public int I;
    public int I0;
    public int I1;
    public int I2;
    public int I3;
    public int I4;
    public int I5;
    public int I6;
    public int I7;
    public int I8;
    public int I9;
    public int J;
    public int J0;
    public int J1;
    public int J2;
    public int J3;
    public int J4;
    public int J5;
    public int J6;
    public int J7;
    public int J8;
    public int J9;
    public int K;
    public int K0;
    public int K1;
    public int K2;
    public int K3;
    public int K4;
    public int K5;
    public int K6;
    public int K7;
    public int K8;
    public int K9;
    public int L;
    public int L0;
    public int L1;
    public int L2;
    public int L3;
    public int L4;
    public int L5;
    public int L6;
    public int L7;
    public int L8;
    public int L9;
    public WindowManager La;
    public int M;
    public int M0;
    public int M1;
    public int M2;
    public int M3;
    public int M4;
    public int M5;
    public int M6;
    public int M7;
    public int M8;
    public int M9;
    public WindowManager.LayoutParams Ma;
    public int N;
    public int N0;
    public int N1;
    public int N2;
    public int N3;
    public int N4;
    public int N5;
    public int N6;
    public int N7;
    public int N8;
    public int N9;
    public int O;
    public int O0;
    public int O1;
    public int O2;
    public int O3;
    public int O4;
    public int O5;
    public int O6;
    public int O7;
    public int O8;
    public int O9;
    public View Oa;
    public int P;
    public int P0;
    public int P1;
    public int P2;
    public int P3;
    public int P4;
    public int P5;
    public int P6;
    public int P7;
    public int P8;
    public int P9;
    public int Q;
    public int Q0;
    public int Q1;
    public int Q2;
    public int Q3;
    public int Q4;
    public int Q5;
    public int Q6;
    public int Q7;
    public int Q8;
    public int Q9;
    public final AbsBYDAutoBodyworkListener Qa;
    public int R;
    public int R0;
    public int R1;
    public int R2;
    public int R3;
    public int R4;
    public int R5;
    public int R6;
    public int R7;
    public int R8;
    public int R9;
    public final AbsBYDAutoAudioListener Ra;
    public int S;
    public int S0;
    public int S1;
    public int S2;
    public int S3;
    public int S4;
    public int S5;
    public int S6;
    public int S7;
    public int S8;
    public int S9;
    public AbsBYDAutoTestListener Sa;
    public int T;
    public int T0;
    public int T1;
    public int T2;
    public int T3;
    public int T4;
    public int T5;
    public int T6;
    public int T7;
    public int T8;
    public int T9;
    public Runnable Ta;
    public int U;
    public int U0;
    public int U1;
    public int U2;
    public int U3;
    public int U4;
    public int U5;
    public int U6;
    public int U7;
    public int U8;
    public int U9;
    public HandlerThread Ua;
    public int V;
    public int V0;
    public int V1;
    public int V2;
    public int V3;
    public int V4;
    public int V5;
    public int V6;
    public int V7;
    public int V8;
    public int V9;
    public Byte Va;
    public int W;
    public int W0;
    public int W1;
    public int W2;
    public int W3;
    public int W4;
    public int W5;
    public int W6;
    public int W7;
    public int W8;
    public int W9;
    public Byte Wa;
    public int X;
    public int X0;
    public int X1;
    public int X2;
    public int X3;
    public int X4;
    public int X5;
    public int X6;
    public int X7;
    public int X8;
    public int X9;
    public Byte Xa;
    public int Y;
    public int Y0;
    public int Y1;
    public int Y2;
    public int Y3;
    public int Y4;
    public int Y5;
    public int Y6;
    public int Y7;
    public int Y8;
    public int Y9;
    public Byte Ya;
    public int Z;
    public int Z0;
    public int Z1;
    public int Z2;
    public int Z3;
    public int Z4;
    public int Z5;
    public int Z6;
    public int Z7;
    public int Z8;
    public int Z9;
    public Byte Za;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f2956a0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public int f2957a1;

    /* JADX INFO: renamed from: a2, reason: collision with root package name */
    public int f2958a2;
    public int a3;
    public int a4;
    public int a5;
    public int a6;
    public int a7;
    public int a8;
    public int a9;
    public int aa;
    public byte[] ab;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2959b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f2960b0;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public int f2961b1;

    /* JADX INFO: renamed from: b2, reason: collision with root package name */
    public int f2962b2;
    public int b3;
    public int b4;
    public int b5;
    public int b6;
    public int b7;
    public int b8;
    public int b9;
    public int ba;
    public int bb;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2963c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f2964c0;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public int f2965c1;

    /* JADX INFO: renamed from: c2, reason: collision with root package name */
    public int f2966c2;
    public int c3;
    public int c4;
    public int c5;
    public int c6;
    public int c7;
    public int c8;
    public int c9;
    public BYDAutoSettingDevice ca;
    public int cb;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2967d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f2968d0;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public int f2969d1;

    /* JADX INFO: renamed from: d2, reason: collision with root package name */
    public int f2970d2;
    public int d3;
    public int d4;
    public int d5;
    public int d6;
    public int d7;
    public int d8;
    public int d9;
    public BYDAutoADASDevice da;
    public int db;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2971e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f2972e0;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public int f2973e1;

    /* JADX INFO: renamed from: e2, reason: collision with root package name */
    public int f2974e2;
    public int e3;
    public int e4;
    public int e5;
    public int e6;
    public int e7;
    public int e8;
    public int e9;
    public BYDAutoAcDevice ea;
    public int eb;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2975f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f2976f0;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public int f2977f1;

    /* JADX INFO: renamed from: f2, reason: collision with root package name */
    public int f2978f2;
    public int f3;
    public int f4;
    public int f5;
    public int f6;
    public int f7;
    public int f8;
    public int f9;
    public BYDAutoAudioDevice fa;
    public int fb;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2979g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f2980g0;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public int f2981g1;

    /* JADX INFO: renamed from: g2, reason: collision with root package name */
    public int f2982g2;
    public int g3;
    public int g4;
    public int g5;
    public int g6;
    public int g7;
    public int g8;
    public int g9;
    public BYDAutoBodyworkDevice ga;
    public String gb;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2983h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f2984h0;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public int f2985h1;

    /* JADX INFO: renamed from: h2, reason: collision with root package name */
    public int f2986h2;
    public int h3;
    public int h4;
    public int h5;
    public int h6;
    public int h7;
    public int h8;
    public int h9;
    public BYDAutoChargingDevice ha;
    public String hb;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2987i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public int f2988i0;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    public int f2989i1;

    /* JADX INFO: renamed from: i2, reason: collision with root package name */
    public int f2990i2;
    public int i3;
    public int i4;
    public int i5;
    public int i6;
    public int i7;
    public int i8;
    public int i9;
    public BYDAutoEnergyDevice ia;
    public int ib;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2991j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f2992j0;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public int f2993j1;

    /* JADX INFO: renamed from: j2, reason: collision with root package name */
    public int f2994j2;
    public int j3;
    public int j4;
    public int j5;
    public int j6;
    public int j7;
    public int j8;
    public int j9;
    public BYDAutoEngineDevice ja;
    public int jb;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2995k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public int f2996k0;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public int f2997k1;

    /* JADX INFO: renamed from: k2, reason: collision with root package name */
    public int f2998k2;
    public int k3;
    public int k4;
    public int k5;
    public int k6;
    public int k7;
    public int k8;
    public int k9;
    public BYDAutoGearboxDevice ka;
    public int kb;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2999l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public int f3000l0;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public int f3001l1;

    /* JADX INFO: renamed from: l2, reason: collision with root package name */
    public int f3002l2;
    public int l3;
    public int l4;
    public int l5;
    public int l6;
    public int l7;
    public int l8;
    public int l9;
    public BYDAutoInstrumentDevice la;
    public int lb;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3003m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public int f3004m0;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public int f3005m1;

    /* JADX INFO: renamed from: m2, reason: collision with root package name */
    public int f3006m2;
    public int m3;
    public int m4;
    public int m5;
    public int m6;
    public int m7;
    public int m8;
    public int m9;
    public BYDAutoLightDevice ma;
    public ArrayList<Byte> mb;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3007n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public int f3008n0;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public int f3009n1;

    /* JADX INFO: renamed from: n2, reason: collision with root package name */
    public int f3010n2;
    public int n3;
    public int n4;
    public int n5;
    public int n6;
    public int n7;
    public int n8;
    public int n9;
    public BYDAutoPM2p5Device na;
    public ArrayList<Byte> nb;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3011o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public int f3012o0;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    public int f3013o1;

    /* JADX INFO: renamed from: o2, reason: collision with root package name */
    public int f3014o2;
    public int o3;
    public int o4;
    public int o5;
    public int o6;
    public int o7;
    public int o8;
    public int o9;
    public BYDAutoSafetyBeltDevice oa;
    public ArrayList<Byte> ob;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3015p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public int f3016p0;

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    public int f3017p1;

    /* JADX INFO: renamed from: p2, reason: collision with root package name */
    public int f3018p2;
    public int p3;
    public int p4;
    public int p5;
    public int p6;
    public int p7;
    public int p8;
    public int p9;
    public BYDAutoStatisticDevice pa;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f3019q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f3020q0;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    public int f3021q1;

    /* JADX INFO: renamed from: q2, reason: collision with root package name */
    public int f3022q2;
    public int q3;
    public int q4;
    public int q5;
    public int q6;
    public int q7;
    public int q8;
    public int q9;
    public BYDAutoTyreDevice qa;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3023r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f3024r0;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    public int f3025r1;

    /* JADX INFO: renamed from: r2, reason: collision with root package name */
    public int f3026r2;
    public int r3;
    public int r4;
    public int r5;
    public int r6;
    public int r7;
    public int r8;
    public int r9;
    public BYDAutoDiPilotDevice ra;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3027s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f3028s0;

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    public int f3029s1;

    /* JADX INFO: renamed from: s2, reason: collision with root package name */
    public int f3030s2;
    public int s3;
    public int s4;
    public int s5;
    public int s6;
    public int s7;
    public int s8;
    public int s9;
    public BYDAutoStudy sa;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3031t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f3032t0;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    public int f3033t1;

    /* JADX INFO: renamed from: t2, reason: collision with root package name */
    public int f3034t2;
    public int t3;
    public int t4;
    public int t5;
    public int t6;
    public int t7;
    public int t8;
    public int t9;
    public BYDAutoVehicleHealthDevice ta;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3035u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f3036u0;

    /* JADX INFO: renamed from: u1, reason: collision with root package name */
    public int f3037u1;

    /* JADX INFO: renamed from: u2, reason: collision with root package name */
    public int f3038u2;
    public int u3;
    public int u4;
    public int u5;
    public int u6;
    public int u7;
    public int u8;
    public int u9;
    public BYDAutoPowerDevice ua;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3039v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f3040v0;

    /* JADX INFO: renamed from: v1, reason: collision with root package name */
    public int f3041v1;

    /* JADX INFO: renamed from: v2, reason: collision with root package name */
    public int f3042v2;
    public int v3;
    public int v4;
    public int v5;
    public int v6;
    public int v7;
    public int v8;
    public int v9;
    public BYDAutoTestDevice va;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f3043w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f3044w0;

    /* JADX INFO: renamed from: w1, reason: collision with root package name */
    public int f3045w1;

    /* JADX INFO: renamed from: w2, reason: collision with root package name */
    public int f3046w2;
    public int w3;
    public int w4;
    public int w5;
    public int w6;
    public int w7;
    public int w8;
    public int w9;
    public Configuration wa;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3047x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f3048x0;

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    public int f3049x1;

    /* JADX INFO: renamed from: x2, reason: collision with root package name */
    public int f3050x2;
    public int x3;
    public int x4;
    public int x5;
    public int x6;
    public int x7;
    public int x8;
    public int x9;
    public ContentResolver xa;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3051y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f3052y0;

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    public int f3053y1;

    /* JADX INFO: renamed from: y2, reason: collision with root package name */
    public int f3054y2;
    public int y3;
    public int y4;
    public int y5;
    public int y6;
    public int y7;
    public int y8;
    public int y9;
    public Context ya;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3055z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f3056z0;

    /* JADX INFO: renamed from: z1, reason: collision with root package name */
    public int f3057z1;

    /* JADX INFO: renamed from: z2, reason: collision with root package name */
    public int f3058z2;
    public int z3;
    public int z4;
    public int z5;
    public int z6;
    public int z7;
    public int z8;
    public int z9;
    public TextView za;
    public int[] Ea = {2002};
    public int Fa = 30;
    public boolean Ga = false;
    public boolean Ha = true;
    public boolean Ia = false;
    public StringBuffer Ja = new StringBuffer();
    public int Ka = -1;
    public boolean Na = false;
    public boolean Pa = false;

    /* JADX INFO: renamed from: h1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoStudyManager.java */
    public class C0015a extends AbsBYDAutoBodyworkListener {
        public C0015a() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i3) {
            super.onPowerLevelChanged(i3);
            BydLogHelper.logE(AbsBYDAutoBodyworkListener.TAG, "onPowerLevelChanged: level = " + i3 + ", mIsAutomaticLearningInProgress = " + a.this.Ga + ", mIsFirstLearning = " + a.this.Ha);
            if (i3 != 2) {
                BydLogHelper.logE(AbsBYDAutoBodyworkListener.TAG, "onPowerLevelChanged111 mIsFirstLearning= " + a.this.Ha);
                a.this.Da.removeMessages(0);
                a.this.Ja.setLength(0);
            } else if (!a.this.Ga) {
                BydLogHelper.logE("AbsBYDAutoBodyworkListener111", "onPowerLevelChanged222 mIsFirstLearning= " + a.this.Ha);
                if (a.this.Ka != 2) {
                    BydLogHelper.logE("AbsBYDAutoBodyworkListener222", "onPowerLevelChanged222 mIsFirstLearning= " + a.this.Ha);
                    a.this.Da.removeMessages(0);
                    a.this.Da.sendEmptyMessageDelayed(0, 10000L);
                }
            }
            a.this.Ka = i3;
        }
    }

    /* JADX INFO: compiled from: AutoStudyManager.java */
    public class b extends AbsBYDAutoAudioListener {
        public b() {
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onEepromAmpTypeChanged(int i3) {
            super.onEepromAmpTypeChanged(i3);
            a.this.U2 = i3;
            a aVar = a.this;
            aVar.t8 = CarSettings.UserTableData.getInt(aVar.xa, "Amplifier_Config_FromI2C");
            a aVar2 = a.this;
            if (aVar2.C(aVar2.t8, a.this.U2) && (a.this.t8 == 0 || a.this.Ha)) {
                CarSettings.UserTableData.putInt(a.this.xa, "Amplifier_Config_FromI2C", a.this.U2);
                a aVar3 = a.this;
                aVar3.z(R.string.auto_study_can_item_196, "AutoAudio.onEepromAmpTypeChanged(int type)", aVar3.U2, a.this.t8);
            }
            BydLogHelper.logD("getAutoFunctionFromMCU: mAmplifierConfigFromI2CByMCU = " + a.this.U2);
            BydLogHelper.logD("getAutoFunctionFromDB: mAmplifierConfigFromI2CByDB = " + a.this.t8);
        }
    }

    /* JADX INFO: compiled from: AutoStudyManager.java */
    public class c extends AbsBYDAutoTestListener {
        public c() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onReadConfigurationWordInfo(short s3, ArrayList<Byte> arrayList) {
            super.onReadConfigurationWordInfo(s3, arrayList);
            BydLogHelper.logE(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo did = " + ((int) s3) + ",data =" + arrayList.size());
            if (-763 != s3 || arrayList.size() <= 0) {
                return;
            }
            BydLogHelper.logE("0xFD05 StudyState = " + arrayList.get(0));
            AutoApplication.c(arrayList.get(0).byteValue());
            if (arrayList.get(0).byteValue() == 1) {
                a.this.N();
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onStatusChangeCarType(boolean z3) {
            super.onStatusChangeCarType(z3);
            BydLogHelper.logD("onStatusChangeCarType status / " + z3);
            a.this.Pa = z3;
        }
    }

    /* JADX INFO: compiled from: AutoStudyManager.java */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.s(a.this);
            if (a.this.Fa <= 0) {
                BydLogHelper.logV("AutoStudyManager", "runnable run: Restart countdown has ended, then reboot the device.");
                if (a.this.Aa != null) {
                    a.this.Aa.dismiss();
                }
                a.this.A();
                return;
            }
            a.this.za.setText(a.this.ya.getString(R.string.restart_time) + " : " + a.this.Fa);
            a.this.Da.postDelayed(this, 1000L);
        }
    }

    /* JADX INFO: compiled from: AutoStudyManager.java */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.N();
            a.this.Da.sendEmptyMessageDelayed(1, 2000L);
        }
    }

    /* JADX INFO: compiled from: AutoStudyManager.java */
    public class f extends Handler {
        public f(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i3 = message.what;
            if (i3 == 0) {
                BydLogHelper.logD("handleMessage: MSG_AUTOMATIC_LEARNING");
                a.this.Ga = true;
                a.this.E();
                return;
            }
            if (i3 == 1) {
                BydLogHelper.logD("handleMessage: MSG_REBOOT");
                a.this.Ga = false;
                if (a.this.ua != null) {
                    a.this.ua.mcuReset();
                    return;
                }
                return;
            }
            if (i3 == 2 && a.this.Aa != null && a.this.Aa.isShowing() && a.this.wa != null) {
                BydLogHelper.logD("handleMessage: MSG_CONFIGCHANGED");
                return;
            }
            if (i3 == 3) {
                if (a.this.ga == null) {
                    BydLogHelper.logD("mBYDAutoBodyworkDevice is null");
                    return;
                }
                int powerLevel = a.this.ga.getPowerLevel();
                BydLogHelper.logD("BydCanfdStudyAutonomouslyPolicy: power_level = " + powerLevel + ", 123 / " + a.this.Da.hasMessages(0));
                if (2 != powerLevel || a.this.Da.hasMessages(0)) {
                    return;
                }
                a.this.Da.sendEmptyMessageDelayed(0, 10000L);
                return;
            }
            if (i3 == 4) {
                a.s(a.this);
                if (a.this.Fa <= 0) {
                    BydLogHelper.logV("AutoStudyManager", "runnable run: Restart countdown has ended, then reboot the device.");
                    a.this.N();
                    a.this.A();
                    return;
                }
                a.this.za.setText(a.this.ya.getString(R.string.restart_time) + " : " + a.this.Fa);
                a.this.Da.sendEmptyMessageDelayed(4, 1000L);
                BydLogHelper.logD("AutoStudyManager", "mCountdownTimeNumber / " + a.this.Fa);
                return;
            }
            if (i3 == 5) {
                a aVar = a.this;
                aVar.O(aVar.ya);
                return;
            }
            if (i3 == 7) {
                a.this.va.writeConfigurationWordInfo((short) -7680, a.this.ob);
                return;
            }
            if (i3 == 8) {
                a.this.va.writeConfigurationWordInfo((short) -7679, a.this.mb);
                BydLogHelper.logE("MSG_DID_ANC mAncCodeByMCU = " + a.this.jb);
                return;
            }
            if (i3 == 9) {
                a.this.va.writeConfigurationWordInfo((short) -7678, a.this.nb);
                BydLogHelper.logE("MSG_DID_ANC_CONFIC mAncConfigByMCU = " + a.this.lb);
            }
        }
    }

    public a(Context context) {
        C0015a c0015a = new C0015a();
        this.Qa = c0015a;
        b bVar = new b();
        this.Ra = bVar;
        this.Sa = new c();
        this.Ta = new d();
        this.Ua = new HandlerThread("sub-thread1");
        this.Va = (byte) 0;
        this.Wa = (byte) 0;
        this.Xa = (byte) 0;
        this.Ya = (byte) 0;
        this.Za = (byte) 0;
        this.ab = new byte[3];
        this.bb = 0;
        this.cb = 1;
        this.db = 2;
        this.eb = 3;
        this.fb = 4;
        this.gb = "ANC_CODE";
        this.hb = "CONFIG_TYPE";
        this.ib = -1;
        this.jb = -2;
        this.kb = -1;
        this.lb = -2;
        this.mb = new ArrayList<>();
        this.nb = new ArrayList<>();
        this.ob = new ArrayList<>();
        this.ya = context;
        this.xa = context.getContentResolver();
        this.Ca = this.ya.getSharedPreferences("study_auto", 0);
        this.Ua.start();
        if (this.Ua.getLooper() != null) {
            this.Da = new f(this.Ua.getLooper());
        }
        this.ga = BYDAutoBodyworkDevice.getInstance(this.ya);
        this.ca = BYDAutoSettingDevice.getInstance(this.ya);
        this.ka = BYDAutoGearboxDevice.getInstance(this.ya);
        this.ja = BYDAutoEngineDevice.getInstance(this.ya);
        this.ga.registerListener((AbsBYDAutoBodyworkListener) c0015a);
        this.na = BYDAutoPM2p5Device.getInstance(this.ya);
        this.da = BYDAutoADASDevice.getInstance(this.ya);
        this.ia = BYDAutoEnergyDevice.getInstance(this.ya);
        this.fa = BYDAutoAudioDevice.getInstance(this.ya);
        this.ea = BYDAutoAcDevice.getInstance(this.ya);
        this.la = BYDAutoInstrumentDevice.getInstance(this.ya);
        this.ha = BYDAutoChargingDevice.getInstance(this.ya);
        this.qa = BYDAutoTyreDevice.getInstance(this.ya);
        this.ra = BYDAutoDiPilotDevice.getInstance(this.ya);
        this.pa = BYDAutoStatisticDevice.getInstance(this.ya);
        this.oa = BYDAutoSafetyBeltDevice.getInstance(this.ya);
        this.ma = BYDAutoLightDevice.getInstance(this.ya);
        this.sa = BYDAutoStudy.getInstance(this.ya);
        this.ua = BYDAutoPowerDevice.getInstance(this.ya);
        this.ta = BYDAutoVehicleHealthDevice.getInstance(this.ya);
        this.fa.registerListener((AbsBYDAutoAudioListener) bVar);
        BYDAutoTestDevice bYDAutoTestDevice = BYDAutoTestDevice.getInstance(this.ya);
        this.va = bYDAutoTestDevice;
        bYDAutoTestDevice.registerListener(this.Sa);
        this.Da.sendEmptyMessageDelayed(3, 3000L);
        String str = SystemProperties.get("persist.sys.AutoType");
        String str2 = SystemProperties.get("persist.sys.energytype");
        int i3 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.POWER_TYPE);
        BydLogHelper.logE("setPropertiesAsDB modelTypeDB = " + CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.MODEL_VALUE) + " modelTypeProp = " + str);
        BydLogHelper.logE("setPropertiesAsDB powerTypeDB = " + i3 + " powerTypeProp = " + str2);
    }

    public static synchronized a L() {
        if (f2955a == null) {
            f2955a = new a(AutoApplication.a());
        }
        return f2955a;
    }

    public static int s(a aVar) {
        int i3 = aVar.Fa;
        aVar.Fa = i3 - 1;
        return i3;
    }

    public void A() {
        if (AutoApplication.b() == 1) {
            BydLogHelper.logD("AutoApplication.getStudyState() = " + AutoApplication.b());
            return;
        }
        N();
        BYDAutoPowerDevice bYDAutoPowerDevice = this.ua;
        if (bYDAutoPowerDevice != null) {
            bYDAutoPowerDevice.mcuReset();
        }
        BydLogHelper.logD("automaticLearningReboot1111");
    }

    public final boolean B(int i3, int i4) {
        return (i4 == 0 || i3 == Integer.MIN_VALUE || i4 == 404 || i4 == i3 || i4 == 65535) ? false : true;
    }

    public final boolean C(int i3, int i4) {
        return (i3 == Integer.MIN_VALUE || i4 == 404 || i4 == i3 || i4 == 65535) ? false : true;
    }

    public final boolean D(int i3, int i4) {
        return (i3 == Integer.MIN_VALUE || i4 == 65535 || i4 == i3) ? false : true;
    }

    public void E() {
        int i3;
        H();
        G();
        T();
        I();
        W();
        boolean z3 = true;
        this.Ga = true;
        this.Ha = false;
        BydLogHelper.logD("mNeedAutomaticLearning / " + this.Ia);
        int i4 = CarSettings.UserTableData.getInt(this.xa, "reboot_num");
        if (i4 == -2147482648 || i4 == -1) {
            i4 = 0;
        } else if (i4 < 1) {
            i4++;
        }
        CarSettings.UserTableData.putInt(this.xa, "reboot_num", i4);
        BydLogHelper.logD("now, begin to automatic learning reboot_num / " + i4);
        if (!this.Ia) {
            BydLogHelper.logD("not learn self");
            BydLogHelper.logD("mIsFirstLearning= " + this.Ha);
            String str = SystemProperties.get("persist.sys.AutoType");
            if (String.valueOf(this.I4).equals(str) || (this.I4 == Integer.MIN_VALUE && this.f2983h == 404)) {
                z3 = false;
            }
            BydLogHelper.logD("nhl needResetProp=" + z3 + ", mAutoType=" + str + ", mAutoModelStatusByDB=" + this.I4);
            if (z3) {
                Intent intent = new Intent("com.byd.action.SET_AUTO_TYPE");
                int i5 = this.f2983h;
                if (i5 == 404) {
                    i5 = this.I4;
                }
                intent.putExtra("auto_type", i5);
                this.ya.sendBroadcast(intent);
            }
            this.Ga = false;
            return;
        }
        BydLogHelper.logD("now, begin to automatic learning mNeedAutomaticLearning / " + this.Ia);
        Z();
        if (i4 >= 1) {
            this.Ga = false;
            if (B(this.I4, this.f2983h) && (i3 = this.f2983h) > 0 && R(i3)) {
                BydLogHelper.logE("now, mAutoModelStatusByDB is " + this.I4);
            } else {
                if (this.jb == this.ib) {
                    BydLogHelper.logE("now, reboot_num is " + i4 + ", reboot is stop");
                    return;
                }
                BydLogHelper.logE("now, mAncCodeByMCU is " + this.jb + ", mAncCodeByDB = " + this.ib);
            }
        }
        this.Da.sendEmptyMessageDelayed(5, 5000L);
    }

    public final int F(int i3) {
        if (i3 == 1) {
            return 0;
        }
        return i3 == 2 ? 1 : 404;
    }

    public final void G() {
        this.I4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.MODEL_VALUE);
        this.W4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.PANORAMA_ONLINE);
        this.S4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.GO_HOME_TIME);
        this.V4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.LEAVE_HOME_TIME);
        this.e5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.SYSTEM_UNIT_VISIBLE);
        this.f5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.UNIT_POWER_VISIBLE);
        this.c5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.UNIT_TEMPERATURE_VISIBLE);
        this.d5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.UNIT_AIR_PRESSURE_VISIBLE);
        this.N4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.UNIT_DISTANCE_FUEL_CONSUMPTION_VISIBLE);
        this.m5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.REAR_AC_ONLINE);
        this.i5 = CarSettings.UserTableData.getInt(this.xa, "energy_state");
        this.j5 = CarSettings.UserTableData.getInt(this.xa, "drive_config");
        this.k5 = CarSettings.UserTableData.getInt(this.xa, "dm_type");
        this.o5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_switch_config");
        this.G4 = CarSettings.UserTableData.getInt(this.xa, "ac_wind_level");
        this.p5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_key_lock");
        this.q5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_wind_mode_control");
        this.r5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_auto_control");
        this.s5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_compressor");
        this.t5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_tempertrue_control");
        this.u5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_wind_level_control");
        this.v5 = CarSettings.UserTableData.getInt(this.xa, "ac_fault_num_shown_state");
        this.w5 = CarSettings.UserTableData.getInt(this.xa, "ac_temperature_control_mode");
        this.x5 = CarSettings.UserTableData.getInt(this.xa, "ac_wind_mode_num");
        this.y5 = CarSettings.UserTableData.getInt(this.xa, "ac_auto_key_can_close");
        this.H4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.AC_HEAT_MODE_VISIBLE);
        this.b5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.Global.STEERING_WHEEL_HEATING_VISIBLE);
        this.Q4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.Global.SEAT_MASSAGE_VISIBLE_LEFT);
        this.R4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.Global.SEAT_MASSAGE_VISIBLE_RIGHT);
        this.Y4 = CarSettings.UserTableData.getInt(this.xa, "seat_massage_visible_left_rear");
        this.Z4 = CarSettings.UserTableData.getInt(this.xa, "seat_massage_visible_right_rear");
        this.z5 = CarSettings.UserTableData.getInt(this.xa, "left_plat_form_configs");
        this.A5 = CarSettings.UserTableData.getInt(this.xa, "negative_ion");
        this.B5 = CarSettings.UserTableData.getInt(this.xa, "fragrance");
        this.C5 = CarSettings.UserTableData.getInt(this.xa, "car_type");
        this.D5 = CarSettings.UserTableData.getInt(this.xa, "lamp_singnal");
        this.E5 = CarSettings.UserTableData.getInt(this.xa, "afs_switch");
        this.T4 = CarSettings.UserTableData.getInt(this.xa, "head_lamp");
        this.F5 = CarSettings.UserTableData.getInt(this.xa, "head_lamp_level_config");
        this.a5 = CarSettings.UserTableData.getInt(this.xa, "smart_welcome_light");
        this.M4 = CarSettings.UserTableData.getInt(this.xa, "daytime_running_light");
        this.G5 = CarSettings.UserTableData.getInt(this.xa, "door_light_state");
        this.K4 = CarSettings.UserTableData.getInt(this.xa, "charge_temperature_control");
        this.H5 = CarSettings.UserTableData.getInt(this.xa, "charge_toaggle_guard");
        this.I5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.CHARGE_LIMIT);
        this.h5 = CarSettings.UserTableData.getInt(this.xa, "vtol");
        this.J5 = CarSettings.UserTableData.getInt(this.xa, "auto_power_on");
        this.K5 = CarSettings.UserTableData.getInt(this.xa, "auto_power_off");
        this.L5 = CarSettings.UserTableData.getInt(this.xa, "rear_mirror_follow_up");
        this.M5 = CarSettings.UserTableData.getInt(this.xa, "driver_seat_auto_return");
        this.N5 = CarSettings.UserTableData.getInt(this.xa, "steer_position_auto_return");
        this.O5 = CarSettings.UserTableData.getInt(this.xa, "brake_pedal_mode");
        this.L4 = CarSettings.UserTableData.getInt(this.xa, "comfortable_parking");
        this.P5 = CarSettings.UserTableData.getInt(this.xa, "motor_pretensioner_seatbelt");
        this.Q5 = CarSettings.UserTableData.getInt(this.xa, "steer_assis");
        this.R5 = CarSettings.UserTableData.getInt(this.xa, "auto_wiper");
        this.S5 = CarSettings.UserTableData.getInt(this.xa, "dynamic_suspension_mode");
        this.T5 = CarSettings.UserTableData.getInt(this.xa, "remote_key_start_ac_time");
        this.U5 = CarSettings.UserTableData.getInt(this.xa, "ac_auto_air");
        this.V5 = CarSettings.UserTableData.getInt(this.xa, "auto_purification");
        this.W5 = CarSettings.UserTableData.getInt(this.xa, "ac_tunnel_cycle");
        this.X5 = CarSettings.UserTableData.getInt(this.xa, "hud_show_params");
        this.Y5 = CarSettings.UserTableData.getInt(this.xa, "rear_ac_panel_auto_lock_time");
        this.Z5 = CarSettings.UserTableData.getInt(this.xa, "ac_warm_type_online_state");
        this.a6 = CarSettings.UserTableData.getInt(this.xa, "intelligent_ac_area");
        this.b6 = CarSettings.UserTableData.getInt(this.xa, "door_height");
        this.c6 = CarSettings.UserTableData.getInt(this.xa, "moon_roof_config");
        this.g5 = CarSettings.UserTableData.getInt(this.xa, "urban_intelligent_navigation");
        this.U4 = CarSettings.UserTableData.getInt(this.xa, "high_speed_intelligent_navigation");
        this.d6 = CarSettings.UserTableData.getInt(this.xa, "urban_navigation");
        this.e6 = CarSettings.UserTableData.getInt(this.xa, "e2e_navigation");
        this.f6 = CarSettings.UserTableData.getInt(this.xa, "lane_change_assist");
        this.g6 = CarSettings.UserTableData.getInt(this.xa, "lane_departure_warning");
        this.h6 = CarSettings.UserTableData.getInt(this.xa, "lane_keep");
        this.i6 = CarSettings.UserTableData.getInt(this.xa, "lane_departure_stop");
        this.j6 = CarSettings.UserTableData.getInt(this.xa, "emergency_lane_keep_assist");
        this.k6 = CarSettings.UserTableData.getInt(this.xa, "traffic_sign_recogniton");
        this.l6 = CarSettings.UserTableData.getInt(this.xa, "intellignet_speed_limit_alert");
        this.m6 = CarSettings.UserTableData.getInt(this.xa, "intellignet_speed_limit_control");
        this.n6 = CarSettings.UserTableData.getInt(this.xa, "traffic_light_attention");
        this.O4 = CarSettings.UserTableData.getInt(this.xa, "door_open_warning");
        this.X4 = CarSettings.UserTableData.getInt(this.xa, "rear_collision_warning");
        this.o6 = CarSettings.UserTableData.getInt(this.xa, "rear_traffic_crossing_alert");
        this.p6 = CarSettings.UserTableData.getInt(this.xa, "front_crossing_traffic_alert");
        this.q6 = CarSettings.UserTableData.getInt(this.xa, "front_crossing_traffic_brake");
        this.r6 = CarSettings.UserTableData.getInt(this.xa, "shake_alert");
        this.s6 = CarSettings.UserTableData.getInt(this.xa, "traffic_signs_recognition");
        this.t6 = CarSettings.UserTableData.getInt(this.xa, "predict_crash_alert_mode");
        this.u6 = CarSettings.UserTableData.getInt(this.xa, "ai_emergency_brake");
        this.v6 = CarSettings.UserTableData.getInt(this.xa, "mistake_gas");
        this.J4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.AUTO_PARKING);
        this.w6 = CarSettings.UserTableData.getInt(this.xa, "valet_parking");
        this.x6 = CarSettings.UserTableData.getInt(this.xa, "memory_parking");
        this.y6 = CarSettings.UserTableData.getInt(this.xa, "blind_spot_assist");
        this.z6 = CarSettings.UserTableData.getInt(this.xa, "rear_cross_brake");
        this.A6 = CarSettings.UserTableData.getInt(this.xa, "ai_distance_light");
        this.B6 = CarSettings.UserTableData.getInt(this.xa, "ai_matrix_light");
        this.P4 = CarSettings.UserTableData.getInt(this.xa, "esp_config");
        this.C6 = CarSettings.UserTableData.getInt(this.xa, "remote_control_lock_window_state");
        this.D6 = CarSettings.UserTableData.getInt(this.xa, "remote_control_unlock_window_state");
        this.E6 = CarSettings.UserTableData.getInt(this.xa, "micro_switch_lock_window_state");
        this.F6 = CarSettings.UserTableData.getInt(this.xa, "micro_switch_unlock_window_state");
        this.G6 = CarSettings.UserTableData.getInt(this.xa, "lock_car_rise_window");
        this.H6 = CarSettings.UserTableData.getInt(this.xa, "over_speed_lock");
        this.I6 = CarSettings.UserTableData.getInt(this.xa, "rain_auto_lock_window_state");
        this.J6 = CarSettings.UserTableData.getInt(this.xa, "doorknob_auto_shrink_state");
        this.K6 = CarSettings.UserTableData.getInt(this.xa, "lock_off");
        this.L6 = CarSettings.UserTableData.getInt(this.xa, "digtal_key");
        this.M6 = CarSettings.UserTableData.getInt(this.xa, "intelligent_unlock_state");
        this.N6 = CarSettings.UserTableData.getInt(this.xa, "intelligent_lock_state");
        this.O6 = CarSettings.UserTableData.getInt(this.xa, "remind_speed");
        this.P6 = CarSettings.UserTableData.getInt(this.xa, "engine_sound");
        this.Q6 = CarSettings.UserTableData.getInt(this.xa, "main_driver_seat_adjustment");
        this.R6 = CarSettings.UserTableData.getInt(this.xa, "main_drive_electric_leg_support_adjustment");
        this.S6 = CarSettings.UserTableData.getInt(this.xa, "Main_Drive_Electric_Headrest_Adjustment");
        this.T6 = CarSettings.UserTableData.getInt(this.xa, "Passenger_Seat_Memory_Function");
        this.U6 = CarSettings.UserTableData.getInt(this.xa, "Passenger_Seat_Electric_Adjustment");
        this.V6 = CarSettings.UserTableData.getInt(this.xa, "Passenger_Seat_Leg_Support_Adjustment");
        this.W6 = CarSettings.UserTableData.getInt(this.xa, "Passenger_Seat_Headrest");
        this.X6 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Level_Adjustment");
        this.Y6 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Backrest_Adjustment");
        this.Z6 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Leg_Adjustment");
        this.a7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Bidet_Adjustment");
        this.b7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Height_Adjustment");
        this.c7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Headrest_Level_Adjustment");
        this.d7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Headrest_Height_Adjustment");
        this.e7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Left_Memory_Switch");
        this.f7 = CarSettings.UserTableData.getInt(this.xa, "Left_Rear_Seat_Recline");
        this.g7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Seat_Recline");
        this.h7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Level_Adjustment");
        this.i7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Backrest_Adjustment");
        this.j7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Leg_Support_Adjustment");
        this.k7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Bidet_Adjustment");
        this.l7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Height_Adjustment");
        this.m7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Headrest_Level_Adjustment");
        this.n7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Headrest_Height_Adjustment");
        this.o7 = CarSettings.UserTableData.getInt(this.xa, "Rear_Right_Memory_Switch");
        this.p7 = CarSettings.UserTableData.getInt(this.xa, "Right_Rear_Seat_Recline");
        this.q7 = CarSettings.UserTableData.getInt(this.xa, "Steering_Wheel");
        this.r7 = CarSettings.UserTableData.getInt(this.xa, "Rearview_Mirror");
        this.s7 = CarSettings.UserTableData.getInt(this.xa, "Refrigerator_Mode");
        this.t7 = CarSettings.UserTableData.getInt(this.xa, "Remenber_Power_Off_State");
        this.u7 = CarSettings.UserTableData.getInt(this.xa, "Disinf_State");
        this.v7 = CarSettings.UserTableData.getInt(this.xa, "Tray_Init_State");
        this.w7 = CarSettings.UserTableData.getInt(this.xa, "Ambient_Light_Color");
        this.x7 = CarSettings.UserTableData.getInt(this.xa, "Ambient_Light");
        this.y7 = CarSettings.UserTableData.getInt(this.xa, "Ambient_Area");
        this.z7 = CarSettings.UserTableData.getInt(this.xa, "Ambient_Colors");
        this.A7 = CarSettings.UserTableData.getInt(this.xa, "Hud_Mode_Params");
        this.B7 = CarSettings.UserTableData.getInt(this.xa, "Instrument_Height");
        this.C7 = CarSettings.UserTableData.getInt(this.xa, "Discharge");
        this.D7 = CarSettings.UserTableData.getInt(this.xa, "Drive_Distance_Show_Standard");
        this.E7 = CarSettings.UserTableData.getInt(this.xa, "EPedal_State");
        this.F7 = CarSettings.UserTableData.getInt(this.xa, "Drive_Distance_Show_Mode");
        this.G7 = CarSettings.UserTableData.getInt(this.xa, "Electroni_Child_Lock_Config");
        this.H7 = CarSettings.UserTableData.getInt(this.xa, "Window_Anti_Pinch_Config");
        this.I7 = CarSettings.UserTableData.getInt(this.xa, "Comfortable_Speed_Up");
        this.J7 = CarSettings.UserTableData.getInt(this.xa, "HSpeed_Auto_Close_State");
        this.K7 = CarSettings.UserTableData.getInt(this.xa, "Runing_Close_Door");
        this.L7 = CarSettings.UserTableData.getInt(this.xa, "Tire_Pressure_Interface_Status");
        this.N7 = CarSettings.UserTableData.getInt(this.xa, "Cumulative_Average_Energy_Consumption_Interface_Status");
        this.O7 = CarSettings.UserTableData.getInt(this.xa, "Cumulative_Average_Fuel_Consumption_Interface_Status");
        this.P7 = CarSettings.UserTableData.getInt(this.xa, "Cumulative_Average_Power_Consumption_Interface_Status");
        this.Q7 = CarSettings.UserTableData.getInt(this.xa, "The_Last_50KM_Average_Energy_Consumption_Interface_State");
        this.R7 = CarSettings.UserTableData.getInt(this.xa, "The_Last_50KM_Average_Fuel_Consumption_Interface_State");
        this.S7 = CarSettings.UserTableData.getInt(this.xa, "The_Last_50KM_Average_Power_Consumption_Interface_State");
        this.T7 = CarSettings.UserTableData.getInt(this.xa, "Instantaneous_Fuel_Consumption_Interface_Status");
        this.U7 = CarSettings.UserTableData.getInt(this.xa, "External_Charging_Power_Interface_Status");
        this.V7 = CarSettings.UserTableData.getInt(this.xa, "Energy_Flow_Diagram_Interface_Status");
        this.W7 = CarSettings.UserTableData.getInt(this.xa, "Driving_Range_Interface_Status_For_Fuel");
        this.X7 = CarSettings.UserTableData.getInt(this.xa, "Average_Speed_Interface_Status");
        this.Y7 = CarSettings.UserTableData.getInt(this.xa, "Drive_Time_Interface_Status_For_Single_Trip");
        this.Z7 = CarSettings.UserTableData.getInt(this.xa, "ADAS_System_State");
        this.a8 = CarSettings.UserTableData.getInt(this.xa, "Smart_Charging_PHEV");
        this.b8 = CarSettings.UserTableData.getInt(this.xa, "Extreme");
        this.c8 = CarSettings.UserTableData.getInt(this.xa, "Car_Plan");
        this.d8 = CarSettings.UserTableData.getInt(this.xa, "No_Sense_Charge");
        this.e8 = CarSettings.UserTableData.getInt(this.xa, "Electric_Front_Hatch_Configuration");
        this.f8 = CarSettings.UserTableData.getInt(this.xa, "Charge_Preheat");
        this.g8 = CarSettings.UserTableData.getInt(this.xa, "DMS_Assemble_Status");
        this.h8 = CarSettings.UserTableData.getInt(this.xa, "Driving_Voice_Broadcast_SwitchConfig");
        this.i8 = CarSettings.UserTableData.getInt(this.xa, "Rest_Mode_Configuration");
        this.j8 = CarSettings.UserTableData.getInt(this.xa, "HUD_Mode_Configuration");
        this.k8 = CarSettings.UserTableData.getInt(this.xa, "Intelligent_Screen_Casting_Switch");
        this.l8 = CarSettings.UserTableData.getInt(this.xa, "FRElectric_Air_Outlet");
        this.m8 = CarSettings.UserTableData.getInt(this.xa, "Left_Rear_Massage_Chair_Configuration");
        this.n8 = CarSettings.UserTableData.getInt(this.xa, "Right_Rear_Massage_Chair_Configuration");
        this.o8 = CarSettings.UserTableData.getInt(this.xa, "Electronic_Parking_Brake");
        this.p8 = CarSettings.UserTableData.getInt(this.xa, "Trunk_Configutarion");
        this.q8 = CarSettings.UserTableData.getInt(this.xa, "Panoramic_Sunroof_Configutarion");
        this.r8 = CarSettings.UserTableData.getInt(this.xa, "PreSale_Mode_Config");
        this.F4 = CarSettings.UserTableData.getInt(this.xa, "elec_defrost");
        this.M7 = CarSettings.UserTableData.getInt(this.xa, "Fuel_Consumption_Interface_Status");
        this.s8 = CarSettings.UserTableData.getInt(this.xa, "Amplifier_Configuration");
        this.u8 = CarSettings.UserTableData.getInt(this.xa, "PAB_Switch_State");
        this.v8 = CarSettings.UserTableData.getInt(this.xa, "FrontLightDimmingCtrlConfigBy3AC");
        this.w8 = CarSettings.UserTableData.getInt(this.xa, "FrontLightDimmingCtrlConfigBy3AD");
        this.x8 = CarSettings.UserTableData.getInt(this.xa, "TSRLitSpdChgSwitchState");
        this.y8 = CarSettings.UserTableData.getInt(this.xa, "CVCConfig");
        this.z8 = CarSettings.UserTableData.getInt(this.xa, "DiSusShowStateByCan");
        this.A8 = CarSettings.UserTableData.getInt(this.xa, "DiSusShowStateByCanFD");
        this.B8 = CarSettings.UserTableData.getInt(this.xa, "DyHeightToggleState");
        this.C8 = CarSettings.UserTableData.getInt(this.xa, "WelcomeState");
        this.D8 = CarSettings.UserTableData.getInt(this.xa, "TailBoxTakeState");
        this.E8 = CarSettings.UserTableData.getInt(this.xa, "CampingBlanceState");
        this.F8 = CarSettings.UserTableData.getInt(this.xa, "DampState");
        this.G8 = CarSettings.UserTableData.getInt(this.xa, "LiftModeState");
        this.H8 = CarSettings.UserTableData.getInt(this.xa, "FdDyHeightToggleState");
        this.I8 = CarSettings.UserTableData.getInt(this.xa, "FdWelcomeState");
        this.J8 = CarSettings.UserTableData.getInt(this.xa, "FdTailBoxTakeState");
        this.K8 = CarSettings.UserTableData.getInt(this.xa, "FdCampingBlanceState");
        this.L8 = CarSettings.UserTableData.getInt(this.xa, "FdDampState");
        this.M8 = CarSettings.UserTableData.getInt(this.xa, "FdLiftModeState");
        this.N8 = CarSettings.UserTableData.getInt(this.xa, "WarningAuxiliarySwitchSetting");
        this.O8 = CarSettings.UserTableData.getInt(this.xa, "WelcomeCeremonySwitchSetting");
        this.P8 = CarSettings.UserTableData.getInt(this.xa, "AmbientModeSwitchSettings");
        this.Q8 = CarSettings.UserTableData.getInt(this.xa, "StarTopSwitchSetting");
        this.R8 = CarSettings.UserTableData.getInt(this.xa, "ComfortableParkingBy37F");
        this.S8 = CarSettings.UserTableData.getInt(this.xa, "LowSpeedEmergencyBrakingConfig");
        this.T8 = CarSettings.UserTableData.getInt(this.xa, "SideDistanceWarningConfig");
        this.U8 = CarSettings.UserTableData.getInt(this.xa, "V2XConfig");
        this.V8 = CarSettings.UserTableData.getInt(this.xa, "TrailerModeConfig");
        this.W8 = CarSettings.UserTableData.getInt(this.xa, "FoldDownTheSecondRowOfSeatsConfig");
        this.X8 = CarSettings.UserTableData.getInt(this.xa, "LevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow");
        this.Y8 = CarSettings.UserTableData.getInt(this.xa, "BackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow");
        this.Z8 = CarSettings.UserTableData.getInt(this.xa, "LegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow");
        this.a9 = CarSettings.UserTableData.getInt(this.xa, "PotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow");
        this.b9 = CarSettings.UserTableData.getInt(this.xa, "HeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow");
        this.c9 = CarSettings.UserTableData.getInt(this.xa, "HeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow");
        this.d9 = CarSettings.UserTableData.getInt(this.xa, "HeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow");
        this.e9 = CarSettings.UserTableData.getInt(this.xa, "OneButtonDownConfigurationOfTheThirdRowLeftRearSeat");
        this.f9 = CarSettings.UserTableData.getInt(this.xa, "OneButtonDownSwitchDisplayForTheThirdRowOfSeats");
        this.g9 = CarSettings.UserTableData.getInt(this.xa, "AllRearSeatOneButtonDownSwitchDisplayConfig");
        this.h9 = CarSettings.UserTableData.getInt(this.xa, "LevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow");
        this.i9 = CarSettings.UserTableData.getInt(this.xa, "BackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow");
        this.j9 = CarSettings.UserTableData.getInt(this.xa, "LegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow");
        this.k9 = CarSettings.UserTableData.getInt(this.xa, "PotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow");
        this.l9 = CarSettings.UserTableData.getInt(this.xa, "HeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow");
        this.m9 = CarSettings.UserTableData.getInt(this.xa, "HeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow");
        this.n9 = CarSettings.UserTableData.getInt(this.xa, "HeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow");
        this.o9 = CarSettings.UserTableData.getInt(this.xa, "OneButtonDownConfigurationOfTheThirdRowRightRearSeat");
        this.p9 = CarSettings.UserTableData.getInt(this.xa, "IntelligentSpeedLimitMapVersionDisplay");
        this.q9 = CarSettings.UserTableData.getInt(this.xa, "DriverSeatHotStoneMassageConfigurationByECU");
        this.r9 = CarSettings.UserTableData.getInt(this.xa, "PassengerSeatHotStoneMassageConfigurationByECU");
        this.s9 = CarSettings.UserTableData.getInt(this.xa, "LeftRearSeatHotStoneMassageConfigurationByECU");
        this.t9 = CarSettings.UserTableData.getInt(this.xa, "RightRearSeatHotStoneMassageConfigurationByECU");
        this.u9 = CarSettings.UserTableData.getInt(this.xa, "SupportAVH");
        this.v9 = CarSettings.UserTableData.getInt(this.xa, "SupportRearControlPanelSign");
        this.w9 = CarSettings.UserTableData.getInt(this.xa, "SupportDimmingCanopyConfiguration");
        this.x9 = CarSettings.UserTableData.getInt(this.xa, "SupportAlarmMode");
        this.y9 = CarSettings.UserTableData.getInt(this.xa, "BSISSupport");
        this.z9 = CarSettings.UserTableData.getInt(this.xa, "MOISSupport");
        this.A9 = CarSettings.UserTableData.getInt(this.xa, "DynamicSuspensionModeByCan");
        this.B9 = CarSettings.UserTableData.getInt(this.xa, "DynamicSuspensionModeByCanFD");
        this.C9 = CarSettings.UserTableData.getInt(this.xa, "SupportManualModel");
        this.D9 = CarSettings.UserTableData.getInt(this.xa, "TailgateTypeMarking");
        this.E9 = CarSettings.UserTableData.getInt(this.xa, "SupportTSRFlow");
        this.F9 = CarSettings.UserTableData.getInt(this.xa, "SupportDoubleConfirm");
        this.n5 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.AC_TYPE);
        this.G9 = CarSettings.UserTableData.getInt(this.xa, "5LevelDriverSeatHeating");
        this.H9 = CarSettings.UserTableData.getInt(this.xa, "5LevelPassengerSeatHeating");
        this.I9 = CarSettings.UserTableData.getInt(this.xa, "5LevelRearLeftSeatHeating");
        this.J9 = CarSettings.UserTableData.getInt(this.xa, "5LevelRearRightSeatHeating");
        this.K9 = CarSettings.UserTableData.getInt(this.xa, "5LevelDriveSeatVentilatingState");
        this.L9 = CarSettings.UserTableData.getInt(this.xa, "5LevelPassengerSeatVentilatingState");
        this.M9 = CarSettings.UserTableData.getInt(this.xa, "5LevelRearLeftSeatVentilatingState");
        this.N9 = CarSettings.UserTableData.getInt(this.xa, "5LevelRearRightSeatVentilatingState");
        this.O9 = CarSettings.UserTableData.getInt(this.xa, "DriverSeatVentilatingState");
        this.P9 = CarSettings.UserTableData.getInt(this.xa, "PassengerSeatVentilatingState");
        this.Q9 = CarSettings.UserTableData.getInt(this.xa, "RearLeftSeatVentilatingState");
        this.R9 = CarSettings.UserTableData.getInt(this.xa, "RearRightSeatVentilatingState");
        this.S9 = CarSettings.UserTableData.getInt(this.xa, "RearLeftSeatVentilatingState2");
        this.T9 = CarSettings.UserTableData.getInt(this.xa, "RearRightSeatVentilatingState2");
        this.U9 = CarSettings.UserTableData.getInt(this.xa, "SeatVentilatingNotL1PState");
        this.V9 = CarSettings.UserTableData.getInt(this.xa, "DriverSeatHeatingState");
        this.W9 = CarSettings.UserTableData.getInt(this.xa, "PassengerSeatHeatingState");
        this.X9 = CarSettings.UserTableData.getInt(this.xa, "RearLeftSeatHeatingState");
        this.Y9 = CarSettings.UserTableData.getInt(this.xa, "RearRightSeatHeatingState");
        this.Z9 = CarSettings.UserTableData.getInt(this.xa, "RearLeftSeatHeatingState2");
        this.aa = CarSettings.UserTableData.getInt(this.xa, "RearRightSeatHeatingState2");
        this.ba = CarSettings.UserTableData.getInt(this.xa, "SeatHeatingNotL1PState");
        this.ib = CarSettings.UserTableData.getInt(this.xa, this.gb);
        this.kb = CarSettings.UserTableData.getInt(this.xa, this.hb);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPreSaleModeConfigByMCU = " + this.f3051y);
        BydLogHelper.logD("getAutoFunctionFromDB: mPreSaleModeConfigByDB = " + this.r8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mELVINByMCU = " + this.f2963c);
        BydLogHelper.logD("getAutoFunctionFromDB: mELVINByDB = " + this.D4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFrontCrossBrakingByMCU = " + this.f2967d);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontCrossBrakingByDB = " + this.E4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mElecDefrostByMCU = " + this.f2971e);
        BydLogHelper.logD("getAutoFunctionFromDB: mElecDefrostByDB = " + this.F4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mACWindLevelByMCU = " + this.f2975f);
        BydLogHelper.logD("getAutoFunctionFromDB: mACWindLevelByDB = " + this.G4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAirConditionHeatModeByMCU = " + this.f2979g);
        BydLogHelper.logD("getAutoFunctionFromDB: mAirConditionHeatModeByDB = " + this.H4);
        BydLogHelper.logE("getAutoFunctionFromMCU: mAutoModelStatusByMCU = " + this.f2983h);
        BydLogHelper.logE("getAutoFunctionFromDB: mAutoModelStatusByDB = " + this.I4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAutoParkingByMCU = " + this.f2987i);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoParkingByDB = " + this.J4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mChargeTemperatureControlByMCU = " + this.f2995k);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargeTemperatureControlByDB = " + this.K4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mComfortableParkingByMCU = " + this.f2999l);
        BydLogHelper.logD("getAutoFunctionFromDB: mComfortableParkingByDB = " + this.L4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDaytimeRunningLightByMCU = " + this.f3003m);
        BydLogHelper.logD("getAutoFunctionFromDB: mDaytimeRunningLightByDB = " + this.M4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDistanceFuelConsumeByMCU = " + this.f3007n);
        BydLogHelper.logD("getAutoFunctionFromDB: mDistanceFuelConsumeByDB = " + this.N4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDoorOpenWarningByMCU = " + this.f3011o);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorOpenWarningByDB = " + this.O4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mESPByMCU = " + this.f3019q);
        BydLogHelper.logD("getAutoFunctionFromDB: mESPByDB = " + this.P4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFrontLeftSeatMassageByMCU = " + this.f3023r);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontLeftSeatMassageByDB = " + this.Q4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFrontRightSeatMassageByMCU = " + this.f3027s);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontRightSeatMassageByDB = " + this.R4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mGoHomeTimeStatusByMCU = " + this.f3031t);
        BydLogHelper.logD("getAutoFunctionFromDB: mGoHomeTimeStatusByDB = " + this.S4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mHeadlampByMCU = " + this.f3035u);
        BydLogHelper.logD("getAutoFunctionFromDB: mHeadlampByDB = " + this.T4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mHighSpeedIntelligentNavigationByMCU = " + this.f3039v);
        BydLogHelper.logD("getAutoFunctionFromDB: mHighSpeedIntelligentNavigationByDB = " + this.U4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLeaveHomeTimeStatusByMCU = " + this.f3043w);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeaveHomeTimeStatusByDB = " + this.V4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPanoramaOnlineStatusByMCU = " + this.f3047x);
        BydLogHelper.logD("getAutoFunctionFromDB: mPanoramaOnlineStatusByDB = " + this.W4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearCollisionWarningByMCU = " + this.f3055z);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearCollisionWarningByDB = " + this.X4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftSeatMassageByMCU = " + this.A);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftSeatMassageByDB = " + this.Y4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightSeatMassageByMCU = " + this.B);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightSeatMassageByDB = " + this.Z4);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSmartWelcomeLightByMCU = " + this.C);
        BydLogHelper.logD("getAutoFunctionFromDB: mSmartWelcomeLightByDB = " + this.a5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSteeringWheelHeatByMCU = " + this.D);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteeringWheelHeatByDB = " + this.b5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTemperatureUnitByMCU = " + this.F);
        BydLogHelper.logD("getAutoFunctionFromDB: mTemperatureUnitByDB = " + this.c5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTyrePressByMCU = " + this.G);
        BydLogHelper.logD("getAutoFunctionFromDB: mTyrePressByDB = " + this.d5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mUnitByMCU = " + this.H);
        BydLogHelper.logD("getAutoFunctionFromDB: mUnitByDB = " + this.e5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mUnitPowerByMCU = " + this.I);
        BydLogHelper.logD("getAutoFunctionFromDB: mUnitPowerByDB = " + this.f5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mUrbanIntelligentNavigationByMCU = " + this.J);
        BydLogHelper.logD("getAutoFunctionFromDB: mUrbanIntelligentNavigationByDB = " + this.g5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mVTOLByMCU = " + this.K);
        BydLogHelper.logD("getAutoFunctionFromDB: mVTOLByDB = " + this.h5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mEnergyStateByMCU = " + this.L);
        BydLogHelper.logD("getAutoFunctionFromDB: mEnergyStateByDB = " + this.i5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDriveConfigByMCU = " + this.M);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriveConfigByDB = " + this.j5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDMTypeByMCU = " + this.N);
        BydLogHelper.logD("getAutoFunctionFromDB: mDMTypeByDB = " + this.k5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mInstrumentConfigureByMCU = " + this.f2959b);
        BydLogHelper.logD("getAutoFunctionFromDB: mInstrumentConfigureByDB = " + this.l5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcConfigByMCU = " + this.O);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcConfigByDB = " + this.m5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcSwitchConfigByMCU = " + this.Q);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcSwitchConfigByDB = " + this.o5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcKeyLockByMCU = " + this.R);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcKeyLockByDB = " + this.p5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcWindModeControlByMCU = " + this.S);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcWindModeControlByDB = " + this.q5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcAutoControlByMCU = " + this.T);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcAutoControlByDB = " + this.r5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcCompressorByMCU = " + this.U);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcCompressorByDB = " + this.s5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcTempertureControlByMCU = " + this.V);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcTempertureControlByDB = " + this.t5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcWindLevelControlByMCU = " + this.W);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcWindLevelControlByDB = " + this.u5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAcFaultNumShownStateByMCU = " + this.X);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcFaultNumShownStateByDB = " + this.v5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAcTemperatureControlModeByMCU = " + this.Y);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcTemperatureControlModeByDB = " + this.w5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAcWindModeNumByMCU = " + this.Z);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcWindModeNumByDB = " + this.x5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAcAutoKeyCanCloseByMCU = " + this.f2956a0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcAutoKeyCanCloseByDB = " + this.y5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLeftPlatformConfigsByMCU = " + this.f2960b0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeftPlatformConfigsByDB = " + this.z5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mNegativeIonByMCU = " + this.f2964c0);
        BydLogHelper.logD("getAutoFunctionFromDB: mNegativeIonByDB = " + this.A5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFragranceByMCU = " + this.f2968d0);
        BydLogHelper.logD("getAutoFunctionFromDB: mFragranceByDB = " + this.B5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mCarTypeByMCU = " + this.f2972e0);
        BydLogHelper.logD("getAutoFunctionFromDB: mCarTypeByDB = " + this.C5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLampSingnalByMCU = " + this.f2976f0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLampSingnalByDB = " + this.D5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAFSSwitchByMCU = " + this.f2980g0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAFSSwitchByDB = " + this.E5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mHeadlampLevelConfigByMCU = " + this.f2984h0);
        BydLogHelper.logD("getAutoFunctionFromDB: mHeadlampLevelConfigByDB = " + this.F5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDoorLightStateByMCU = " + this.f2988i0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorLightStateByDB = " + this.G5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mChargeToaggleGuarMCUyMCU = " + this.f2992j0);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargeToaggleGuardByDB = " + this.H5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mChargeLimitByMCU = " + this.f2996k0);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargeLimitByDB = " + this.I5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAutoPowerOnByMCU = " + this.f3000l0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoPowerOnByDB = " + this.J5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAutoPowerOffByMCU = " + this.f3004m0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoPowerOffByDB = " + this.K5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearMirrorFollowUpByMCU = " + this.f3008n0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearMirrorFollowUpByDB = " + this.L5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDriverSeatAutoReturnByMCU = " + this.f3012o0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriverSeatAutoReturnByDB = " + this.M5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSteerPositionAutoReturnByMCU = " + this.f3016p0);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteerPositionAutoReturnByDB = " + this.N5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mBrakePedalModeByMCU = " + this.f3020q0);
        BydLogHelper.logD("getAutoFunctionFromDB: mBrakePedalModeByDB = " + this.O5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMotorPretensionerSeatbeltByMCU = " + this.f3024r0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMotorPretensionerSeatbeltByDB = " + this.P5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSteerAssisByMCU = " + this.f3028s0);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteerAssisByDB = " + this.Q5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAutoWiperByMCU = " + this.f3032t0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoWiperByDB = " + this.R5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDynamicSuspensionModeByMCU = " + this.f3036u0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDynamicSuspensionModeByDB = " + this.S5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRemoteKeyStartAcTimeByMCU = " + this.f3040v0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemoteKeyStartAcTimeByDB = " + this.T5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mACAutoAirByMCU = " + this.f3044w0);
        BydLogHelper.logD("getAutoFunctionFromDB: mACAutoAirByDB = " + this.U5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAutoPurificationByMCU = " + this.f3048x0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoPurificationByDB = " + this.V5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mACTunnelCycleByMCU = " + this.f3052y0);
        BydLogHelper.logD("getAutoFunctionFromDB: mACTunnelCycleByDB = " + this.W5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mHudShowParamsByMCU = " + this.f3056z0);
        BydLogHelper.logD("getAutoFunctionFromDB: mHudShowParamsByDB = " + this.X5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearAcPanelAutoLockTimeByMCU = " + this.A0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcPanelAutoLockTimeByDB = " + this.Y5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAcWarmTypeOnlineStateByMCU = " + this.B0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcWarmTypeOnlineStateByDB = " + this.Z5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mIntelligentAcAreaByMCU = " + this.C0);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentAcAreaByDB = " + this.a6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDoorHeightByMCU = " + this.D0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorHeightByDB = " + this.b6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMoonRoofConfigByMCU = " + this.E0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMoonRoofConfigByDB = " + this.c6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mUrbanNavigationByMCU = " + this.F0);
        BydLogHelper.logD("getAutoFunctionFromDB: mUrbanNavigationByDB = " + this.d6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mEndToEndNavigationByMCU = " + this.G0);
        BydLogHelper.logD("getAutoFunctionFromDB: mEndToEndNavigationByDB = " + this.e6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLaneChangeAssistByMCU = " + this.H0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneChangeAssistByDB = " + this.f6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLaneDepartureWarningByMCU = " + this.I0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneDepartureWarningByDB = " + this.g6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLaneKeepByMCU = " + this.J0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneKeepByDB = " + this.h6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLaneDepartureStopByMCU = " + this.K0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneDepartureStopByDB = " + this.i6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mEmergencyLaneKeepAssistByMCU = " + this.L0);
        BydLogHelper.logD("getAutoFunctionFromDB: mEmergencyLaneKeepAssistByDB = " + this.j6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTrafficSignRecognitonByMCU = " + this.M0);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrafficSignRecognitonByDB = " + this.k6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mIntellignetSpeedLimitAlertByMCU = " + this.N0);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntellignetSpeedLimitAlertByDB = " + this.l6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mIntellignetSpeedLimitControlByMCU = " + this.O0);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntellignetSpeedLimitControlByDB = " + this.m6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTrafficLightAttentionByMCU = " + this.P0);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrafficLightAttentionByDB = " + this.n6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearTrafficCrossingAlertByMCU = " + this.Q0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearTrafficCrossingAlertByDB = " + this.o6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFrontCrossingTrafficAlertByMCU = " + this.R0);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontCrossingTrafficAlertByDB = " + this.p6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFrontCrossingTrafficBrakeByMCU = " + this.S0);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontCrossingTrafficBrakeByDB = " + this.q6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mShakeAlertByMCU = " + this.T0);
        BydLogHelper.logD("getAutoFunctionFromDB: mShakeAlertByDB = " + this.r6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTrafficSignsRecognitionByMCU = " + this.U0);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrafficSignsRecognitionByDB = " + this.s6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPredictCrashAlertModeByMCU = " + this.V0);
        BydLogHelper.logD("getAutoFunctionFromDB: mPredictCrashAlertModeByDB = " + this.t6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAiEmergencyBrakeByMCU = " + this.W0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAiEmergencyBrakeByDB = " + this.u6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMistakeGasByMCU = " + this.X0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMistakeGasByDB = " + this.v6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mValetParkingByMCU = " + this.Y0);
        BydLogHelper.logD("getAutoFunctionFromDB: mValetParkingByDB = " + this.w6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMemoryParkingByMCU = " + this.Z0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMemoryParkingByDB = " + this.x6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mBlindSpotAssistByMCU = " + this.f2957a1);
        BydLogHelper.logD("getAutoFunctionFromDB: mBlindSpotAssistByDB = " + this.y6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearCrossBrakeByMCU = " + this.f2961b1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearCrossBrakeByDB = " + this.z6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAiDistanceLightByMCU = " + this.f2965c1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAiDistanceLightByDB = " + this.A6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAiMatrixLightByMCU = " + this.f2969d1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAiMatrixLightByDB = " + this.B6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRemoteControlLockWindowStateByMCU = " + this.f2973e1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemoteControlLockWindowStateByDB = " + this.C6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRemoteControlUnlockWindowStateByMCU = " + this.f2977f1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemoteControlUnlockWindowStateByDB = " + this.D6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMicroSwitchLockWindowStateByMCU = " + this.f2981g1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMicroSwitchLockWindowStateByDB = " + this.E6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMicroSwitchUnlockWindowStateByMCU = " + this.f2985h1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMicroSwitchUnlockWindowStateByDB = " + this.F6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLockCarRiseWindowByMCU = " + this.f2989i1);
        BydLogHelper.logD("getAutoFunctionFromDB: mLockCarRiseWindowByDB = " + this.G6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mOverspeedLockByMCU = " + this.f2993j1);
        BydLogHelper.logD("getAutoFunctionFromDB: mOverspeedLockByDB = " + this.H6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRainAutoLockWindowStateByMCU = " + this.f2997k1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRainAutoLockWindowStateByDB = " + this.I6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDoorknobAutoShrinkStateByMCU = " + this.f3001l1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorknobAutoShrinkStateByDB = " + this.J6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLockOffByMCU = " + this.f3005m1);
        BydLogHelper.logD("getAutoFunctionFromDB: mLockOffByDB = " + this.K6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDigtalKeySupportByMCU = " + this.f3009n1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDigtalKeySupportByDB = " + this.L6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mIntelligentUnlockStateByMCU = " + this.f3013o1);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentUnlockStateByDB = " + this.M6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mIntelligentLockStateByMCU = " + this.f3017p1);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentLockStateByDB = " + this.N6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRemindSpeedSupportByMCU = " + this.f3021q1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemindSpeedSupportByDB = " + this.O6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mEngineSoundSupportByMCU = " + this.f3025r1);
        BydLogHelper.logD("getAutoFunctionFromDB: mEngineSoundSupportByDB = " + this.P6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMainDriverSeatAdjustmentByMCU = " + this.f3029s1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMainDriverSeatAdjustmentByDB = " + this.Q6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMainDriveElectricLegSupportAdjustmentByMCU = " + this.f3033t1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMainDriveElectricLegSupportAdjustmentByDB = " + this.R6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMainDriveElectricHeadrestAdjustmentByMCU = " + this.f3037u1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMainDriveElectricHeadrestAdjustmentByDB = " + this.S6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPassengerSeatMemoryFunctionByMCU = " + this.f3041v1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatMemoryFunctionByDB = " + this.T6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPassengerSeatElectricAdjustmentByMCU = " + this.f3045w1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatElectricAdjustmentByDB = " + this.U6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPassengerSeatLegSupportAdjustmentByMCU = " + this.f3049x1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatLegSupportAdjustmentByDB = " + this.V6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPassengerSeatHeadrestByMCU = " + this.f3053y1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatHeadrestByDB = " + this.W6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftLevelAdjustmentByMCU = " + this.f3057z1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftLevelAdjustmentByDB = " + this.X6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftBackrestAdjustmentByMCU = " + this.A1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftBackrestAdjustmentByDB = " + this.Y6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftLegAdjustmentByMCU = " + this.B1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftLegAdjustmentByDB = " + this.Z6);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftBidetAdjustmentByMCU = " + this.C1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftBidetAdjustmentByDB = " + this.a7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftHeightAdjustmentByMCU = " + this.D1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftHeightAdjustmentByDB = " + this.b7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftHeadrestLevelAdjustmentByMCU = " + this.E1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftHeadrestLevelAdjustmentByDB = " + this.c7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftHeadrestHeightAdjustmentByMCU = " + this.F1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftHeadrestHeightAdjustmentByDB = " + this.d7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearLeftMemorySwitchByMCU = " + this.G1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftMemorySwitchByDB = " + this.e7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLeftRearSeatReclineByMCU = " + this.H1);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeftRearSeatReclineByDB = " + this.f7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearSeatReclineByMCU = " + this.I1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearSeatReclineByDB = " + this.g7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightLevelAdjustmentByMCU = " + this.J1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightLevelAdjustmentByDB = " + this.h7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightBackrestAdjustmentByMCU = " + this.K1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightBackrestAdjustmentByDB = " + this.i7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightLegSupportAdjustmentByMCU = " + this.L1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightLegSupportAdjustmentByDB = " + this.j7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightBidetAdjustmentByMCU = " + this.M1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightBidetAdjustmentByDB = " + this.k7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightHeightAdjustmentByMCU = " + this.N1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightHeightAdjustmentByDB = " + this.l7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightHeadrestLevelAdjustmentByMCU = " + this.O1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightHeadrestLevelAdjustmentByDB = " + this.m7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightHeadrestHeightAdjustmentByMCU = " + this.P1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightHeadrestHeightAdjustmentByDB = " + this.n7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearRightMemorySwitchByMCU = " + this.Q1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightMemorySwitchByDB = " + this.o7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRightRearSeatReclineByMCU = " + this.R1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRightRearSeatReclineByDB = " + this.p7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSteeringWheelByMCU = " + this.S1);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteeringWheelByDB = " + this.q7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRearviewMirrorByMCU = " + this.T1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearviewMirrorByDB = " + this.r7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRefrigeratorModeByMCU = " + this.U1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRefrigeratorModeByDB = " + this.s7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRemenberPowerOffStateByMCU = " + this.V1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemenberPowerOffStateByDB = " + this.t7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDisinfStateByMCU = " + this.W1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDisinfStateByDB = " + this.u7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mmTrayInitStateByMCU = " + this.X1);
        BydLogHelper.logD("getAutoFunctionFromDB: mmTrayInitStateByDB = " + this.v7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAmbientLightColorByMCU = " + this.Y1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientLightColorByDB = " + this.w7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAmbientLightByMCU = " + this.Z1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientLightByDB = " + this.x7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAmbientAreaByMCU = " + this.f2958a2);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientAreaByDB = " + this.y7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAmbientColorsByMCU = " + this.f2962b2);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientColorsByDB = " + this.z7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mHudModeParamsByMCU = " + this.f2966c2);
        BydLogHelper.logD("getAutoFunctionFromDB: mHudModeParamsByDB = " + this.A7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mInstrumentHeightByMCU = " + this.f2970d2);
        BydLogHelper.logD("getAutoFunctionFromDB: mInstrumentHeightByDB = " + this.B7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDischargeByMCU = " + this.f2974e2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDischargeByDB = " + this.C7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDriveDistanceShowStandarMCUyMCU = " + this.f2978f2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriveDistanceShowStandardByDB = " + this.D7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mEPedalStateByMCU = " + this.f2982g2);
        BydLogHelper.logD("getAutoFunctionFromDB: mEPedalStateByDB = " + this.E7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDriveDistanceShowModeByMCU = " + this.f2986h2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriveDistanceShowModeByDB = " + this.F7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mElectroniChildLockConfigByMCU = " + this.f2990i2);
        BydLogHelper.logD("getAutoFunctionFromDB: mElectroniChildLockConfigByDB = " + this.G7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mWindowAntiPinchConfigByMCU = " + this.f2994j2);
        BydLogHelper.logD("getAutoFunctionFromDB: mWindowAntiPinchConfigByDB = " + this.H7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mComfortableSpeedUpByMCU = " + this.f2998k2);
        BydLogHelper.logD("getAutoFunctionFromDB: mComfortableSpeedUpByDB = " + this.I7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mHSpeedAutoCloseStateByMCU = " + this.f3002l2);
        BydLogHelper.logD("getAutoFunctionFromDB: mHSpeedAutoCloseStateByDB = " + this.J7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRuningCloseDoorByMCU = " + this.f3006m2);
        BydLogHelper.logD("getAutoFunctionFromDB: mRuningCloseDoorByDB = " + this.K7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTirePressureInterfaceStatusByMCU = " + this.f3010n2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTirePressureInterfaceStatusByDB = " + this.L7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFuelConsumptionInterfaceStatusByMCU = " + this.f3014o2);
        BydLogHelper.logD("getAutoFunctionFromDB: mFuelConsumptionInterfaceStatusByDB = " + this.M7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mADASSystemStateByMCU = " + this.B2);
        BydLogHelper.logD("getAutoFunctionFromDB: mADASSystemStateByDB = " + this.Z7);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSmartChargingPHEVByMCU = " + this.C2);
        BydLogHelper.logD("getAutoFunctionFromDB: mSmartChargingPHEVByDB = " + this.a8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mExtremeByMCU = " + this.D2);
        BydLogHelper.logD("getAutoFunctionFromDB: mExtremeByDB = " + this.b8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mCarPlanByMCU = " + this.E2);
        BydLogHelper.logD("getAutoFunctionFromDB: mCarPlanByDB = " + this.c8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mNoSenseChargeByMCU = " + this.F2);
        BydLogHelper.logD("getAutoFunctionFromDB: mNoSenseChargeByDB = " + this.d8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mElectricFrontHatchConfigurationByMCU = " + this.G2);
        BydLogHelper.logD("getAutoFunctionFromDB: mElectricFrontHatchConfigurationByDB = " + this.e8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mChargePreheatByMCU = " + this.H2);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargePreheatByDB = " + this.f8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDMSAssembleStatusByMCU = " + this.I2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDMSAssembleStatusByDB = " + this.g8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDrivingVoiceBroadcastSwitchConfigByMCU = " + this.J2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDrivingVoiceBroadcastSwitchConfigByDB = " + this.h8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRestModeConfigurationByMCU = " + this.K2);
        BydLogHelper.logD("getAutoFunctionFromDB: mRestModeConfigurationByDB = " + this.i8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mHUDModeConfigurationByMCU = " + this.L2);
        BydLogHelper.logD("getAutoFunctionFromDB: mHUDModeConfigurationByDB = " + this.j8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mIntelligentScreenCastingSwitchByMCU = " + this.M2);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentScreenCastingSwitchByDB = " + this.k8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFRElectricAirOutletByMCU = " + this.N2);
        BydLogHelper.logD("getAutoFunctionFromDB: mFRElectricAirOutletByDB = " + this.l8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLeftRearMassageChairConfigurationByMCU = " + this.O2);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeftRearMassageChairConfigurationByDB = " + this.m8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mRightRearMassageChairConfigurationByMCU = " + this.P2);
        BydLogHelper.logD("getAutoFunctionFromDB: mRightRearMassageChairConfigurationByDB = " + this.n8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mElectronicParkingBrakeByMCU = " + this.Q2);
        BydLogHelper.logD("getAutoFunctionFromDB: mElectronicParkingBrakeByDB = " + this.o8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTrunkConfigutarionByMCU = " + this.R2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrunkConfigutarionByDB = " + this.p8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPanoramicSunroofConfigutarionByMCU = " + this.S2);
        BydLogHelper.logD("getAutoFunctionFromDB: mPanoramicSunroofConfigutarionByDB = " + this.q8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAmplifierConfigurationByMCU = " + this.T2);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmplifierConfigurationByDB = " + this.s8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mPABSwitchStateByMCU = " + this.V2);
        BydLogHelper.logD("getAutoFunctionFromDB: mPABSwitchStateByDB = " + this.u8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFrontLightDimmingCtrlConfigBy3ACByMCU = " + this.W2);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontLightDimmingCtrlConfigBy3ACByDB = " + this.v8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFrontLightDimmingCtrlConfigBy3ADByMCU = " + this.X2);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontLightDimmingCtrlConfigBy3ADByDB = " + this.w8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTSRLitSpdChgSwitchStateByMCU = " + this.Y2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTSRLitSpdChgSwitchStateByDB = " + this.x8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mCVCConfigByMCU = " + this.Z2);
        BydLogHelper.logD("getAutoFunctionFromDB: mCVCConfigByDB = " + this.y8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDiSusShowStateByCanByMCU = " + this.a3);
        BydLogHelper.logD("getAutoFunctionFromDB: mDiSusShowStateByCanByDB = " + this.z8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDiSusShowStateByCanFDByMCU = " + this.b3);
        BydLogHelper.logD("getAutoFunctionFromDB: mDiSusShowStateByCanFDByDB = " + this.A8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDyHeightToggleStateByMCU = " + this.c3);
        BydLogHelper.logD("getAutoFunctionFromDB: mDyHeightToggleStateByDB = " + this.B8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mWelcomeStateByMCU = " + this.d3);
        BydLogHelper.logD("getAutoFunctionFromDB: mWelcomeStateByDB = " + this.C8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTailBoxTakeStateByMCU = " + this.e3);
        BydLogHelper.logD("getAutoFunctionFromDB: mTailBoxTakeStateByDB = " + this.D8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mCampingBlanceStateByMCU = " + this.f3);
        BydLogHelper.logD("getAutoFunctionFromDB: mCampingBlanceStateByDB = " + this.E8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDampStateByMCU = " + this.g3);
        BydLogHelper.logD("getAutoFunctionFromDB: mDampStateByDB = " + this.F8);
        BydLogHelper.logD("getAutoFunctionFromDB: mLiftModeStateByMCU = " + this.h3);
        BydLogHelper.logD("getAutoFunctionFromDB: mLiftModeStateByDB = " + this.G8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFdDyHeightToggleStateByMCU = " + this.i3);
        BydLogHelper.logD("getAutoFunctionFromDB: mFdDyHeightToggleStateByDB = " + this.H8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFdWelcomeStateByMCU = " + this.j3);
        BydLogHelper.logD("getAutoFunctionFromDB: mFdWelcomeStateByDB = " + this.I8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFdTailBoxTakeStateByMCU = " + this.k3);
        BydLogHelper.logD("getAutoFunctionFromDB: mFdTailBoxTakeStateByDB = " + this.J8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFdCampingBlanceStateByMCU = " + this.l3);
        BydLogHelper.logD("getAutoFunctionFromDB: mFdCampingBlanceStateByDB = " + this.K8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFdDampStateByMCU = " + this.m3);
        BydLogHelper.logD("getAutoFunctionFromDB: mFdDampStateByDB = " + this.L8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFdLiftModeStateByMCU = " + this.n3);
        BydLogHelper.logD("getAutoFunctionFromDB: mFdLiftModeStateByDB = " + this.M8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mWarningAuxiliarySwitchSettingByMCU = " + this.o3);
        BydLogHelper.logD("getAutoFunctionFromDB: mWarningAuxiliarySwitchSettingByDB = " + this.N8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mWelcomeCeremonySwitchSettingByMCU = " + this.p3);
        BydLogHelper.logD("getAutoFunctionFromDB: mWelcomeCeremonySwitchSettingByDB = " + this.O8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAmbientModeSwitchSettingsByMCU = " + this.q3);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientModeSwitchSettingsByDB = " + this.P8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mStarTopSwitchSettingByMCU = " + this.r3);
        BydLogHelper.logD("getAutoFunctionFromDB: mStarTopSwitchSettingByDB = " + this.Q8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mComfortableParkingBy37FByMCU = " + this.s3);
        BydLogHelper.logD("getAutoFunctionFromDB: mComfortableParkingBy37FByDB = " + this.R8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mLowSpeedEmergencyBrakingConfigByMCU = " + this.t3);
        BydLogHelper.logD("getAutoFunctionFromDB: mLowSpeedEmergencyBrakingConfigByDB = " + this.S8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSideDistanceWarningConfigByMCU = " + this.u3);
        BydLogHelper.logD("getAutoFunctionFromDB: mSideDistanceWarningConfigByDB = " + this.T8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mV2XConfigByMCU = " + this.v3);
        BydLogHelper.logD("getAutoFunctionFromDB: mV2XConfigByDB = " + this.U8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTrailerModeConfigByMCU = " + this.w3);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrailerModeConfigByDB = " + this.V8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mFoldDownTheSecondRowOfSeatsConfigByMCU = " + this.x3);
        BydLogHelper.logD("getAutoFunctionFromDB: mFoldDownTheSecondRowOfSeatsConfigByDB = " + this.W8);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSupportAVHByMCU = " + this.V3);
        BydLogHelper.logD("getAutoFunctionFromDB: mSupportAVHByDB = " + this.u9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSupportRearControlPanelSignByMCU = " + this.W3);
        BydLogHelper.logD("getAutoFunctionFromDB: mSupportRearControlPanelSignByDB = " + this.v9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSupportDimmingCanopyConfigurationByMCU = " + this.X3);
        BydLogHelper.logD("getAutoFunctionFromDB: mSupportDimmingCanopyConfigurationByDB = " + this.w9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSupportAlarmModeByMCU = " + this.Y3);
        BydLogHelper.logD("getAutoFunctionFromDB: mSupportAlarmModeByDB = " + this.x9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mBSISSupportByMCU = " + this.Z3);
        BydLogHelper.logD("getAutoFunctionFromDB: mBSISSupportByDB = " + this.y9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mMOISSupportByMCU = " + this.a4);
        BydLogHelper.logD("getAutoFunctionFromDB: mMOISSupportByDB = " + this.z9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDynamicSuspensionModeByCanByMCU = " + this.b4);
        BydLogHelper.logD("getAutoFunctionFromDB: mDynamicSuspensionModeByCanByDB = " + this.A9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mDynamicSuspensionModeByCanFDByMCU = " + this.c4);
        BydLogHelper.logD("getAutoFunctionFromDB: mDynamicSuspensionModeByCanFDByDB = " + this.B9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSupportManualModelByMCU = " + this.d4);
        BydLogHelper.logD("getAutoFunctionFromDB: mSupportManualModelByDB = " + this.C9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mTailgateTypeMarkingByMCU = " + this.e4);
        BydLogHelper.logD("getAutoFunctionFromDB: mTailgateTypeMarkingByDB = " + this.D9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSupportTSRFlowByMCU = " + this.f4);
        BydLogHelper.logD("getAutoFunctionFromDB: mSupportTSRFlowByDB = " + this.E9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mSupportDoubleConfirmByMCU = " + this.g4);
        BydLogHelper.logD("getAutoFunctionFromDB: mSupportDoubleConfirmByDB = " + this.F9);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAcTypeByMCU = " + this.P);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcTypeByDB = " + this.n5);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAncCodeByMCU = " + this.jb);
        BydLogHelper.logD("getAutoFunctionFromDB: mAncCodeByDB = " + this.ib);
        BydLogHelper.logD("getAutoFunctionFromMCU: mAncConfigByMCU = " + this.lb);
        BydLogHelper.logD("getAutoFunctionFromDB: mAncConfigByDB = " + this.kb);
    }

    public final void H() {
        int autoType = this.ga.getAutoType();
        this.f2983h = autoType;
        if (autoType < 0) {
            this.f2983h = 404;
        }
        if (P(this.f2983h)) {
            int preSaleModelSupport = this.sa.getPreSaleModelSupport();
            if (preSaleModelSupport == 0) {
                this.f3051y = 1;
            } else if (preSaleModelSupport == 1) {
                this.f3051y = 0;
            } else {
                this.f3051y = 404;
            }
        } else {
            this.f3051y = 0;
        }
        this.f3047x = M();
        int backHomeLightDelayValue = this.ca.getBackHomeLightDelayValue();
        if (backHomeLightDelayValue == 0) {
            this.f3031t = 0;
        } else if (backHomeLightDelayValue < 1 || backHomeLightDelayValue > 7) {
            this.f3031t = 404;
        } else {
            this.f3031t = 1;
        }
        int leftHomeLightDelayValue = this.ca.getLeftHomeLightDelayValue();
        if (leftHomeLightDelayValue == 0) {
            this.f3043w = 0;
        } else if (leftHomeLightDelayValue < 1 || leftHomeLightDelayValue > 7) {
            this.f3043w = 404;
        } else {
            this.f3043w = 1;
        }
        int energyState = this.ia.getEnergyState();
        if (energyState == 0) {
            this.L = 0;
        } else if (energyState >= 1) {
            this.L = 1;
        } else {
            this.L = 404;
        }
        this.M = this.sa.getDriveConfig();
        this.N = this.sa.getDMType();
        int instrumentConfigure = this.la.getInstrumentConfigure();
        if (instrumentConfigure == 3 || instrumentConfigure == 5) {
            this.H = 1;
            int unit = this.la.getUnit(3);
            if (unit == 1 || unit == 2) {
                this.I = 1;
            } else if (unit == 0) {
                this.I = 0;
            } else {
                this.I = 404;
            }
            int unit2 = this.la.getUnit(1);
            if (unit2 >= 1 || unit2 <= 3) {
                this.G = 1;
            } else if (unit2 == 0) {
                this.G = 0;
            } else {
                this.G = 404;
            }
            int unit3 = this.la.getUnit(2);
            if (unit3 >= 1 && unit3 <= 5) {
                this.f3007n = 1;
            } else if (unit3 == 0) {
                this.f3007n = 0;
            } else {
                this.f3007n = 404;
            }
            int unit4 = this.la.getUnit(0);
            int acType = this.ea.getAcType();
            if (unit4 == 1 || acType == 1) {
                this.F = 1;
            } else if (unit4 == 0 && acType == 0) {
                this.F = 0;
            } else {
                this.F = 404;
            }
        } else if (instrumentConfigure == -999999999 || instrumentConfigure == 65535) {
            this.H = 404;
            this.I = 404;
            this.G = 404;
            this.f3007n = 404;
            this.F = 404;
        } else {
            this.H = 0;
            this.I = 0;
            this.G = 0;
            this.f3007n = 0;
            this.F = 0;
        }
        this.P = this.ea.getAcType();
        this.O = F(this.sa.getSupportRearAc());
        this.Q = F(this.sa.getSupportRearAcSwitch());
        this.f2975f = F(this.ea.getRearAcMaxWindLevel());
        this.R = F(this.sa.getSupportRearAcKeyLock());
        this.S = F(this.sa.getSupportRearAcWindModeControl());
        this.T = F(this.sa.getSupportRearAcAutoControl());
        this.U = F(this.sa.getSupportRearAcCompressor());
        this.V = F(this.sa.getSupportRearAcTempertureControl());
        this.W = F(this.sa.getSupportRearAcWindLevelControl());
        this.X = F(this.ea.getAcFaultNumShownState());
        this.Y = F(this.ea.getAcTemperatureControlMode());
        int acWindModeNum = this.ea.getAcWindModeNum();
        BYDAutoStudy bYDAutoStudy = this.sa;
        if (acWindModeNum == 1) {
            this.Z = 5;
        } else if (acWindModeNum == 2) {
            this.Z = 7;
        } else if (acWindModeNum == 0) {
            this.Z = 0;
        } else {
            this.Z = 404;
        }
        int supportAcAutoKeyCanClose = bYDAutoStudy.getSupportAcAutoKeyCanClose();
        if (supportAcAutoKeyCanClose == 1) {
            this.f2956a0 = 1;
        } else if (supportAcAutoKeyCanClose == 2) {
            this.f2956a0 = 0;
        } else {
            this.f2956a0 = 404;
        }
        this.f2971e = this.ea.getDefrostRearConfig();
        this.f2979g = this.ea.getAcWarmTypeOnlineState();
        this.D = this.sa.getSteeringWheelHeatingConfiguration();
        this.f3023r = this.ca.getSupportSeatMassage(0);
        this.f3027s = this.ca.getSupportSeatMassage(1);
        this.A = this.ca.getSupportSeatMassage(2);
        this.B = this.ca.getSupportSeatMassage(3);
        this.f2960b0 = this.sa.getLeftPlatformConfigs();
        this.f2964c0 = F(this.sa.getSupportNegativeIon());
        this.f2968d0 = this.sa.getSupportFragrance();
        this.f2972e0 = this.sa.getCarType();
        int supportLampSingnal = this.sa.getSupportLampSingnal();
        this.f2976f0 = supportLampSingnal;
        if (supportLampSingnal != 1) {
            this.f2976f0 = 0;
        }
        if (this.ma.getAFSSwitch() == 0) {
            this.f2980g0 = 1;
        } else {
            this.f2980g0 = 0;
        }
        this.f3035u = this.sa.getHeadlampSupport();
        this.f2984h0 = this.sa.getHeadlampLevelConfig();
        this.C = F(this.sa.getUsherLightSupport());
        int dayLightSupport = this.ma.getDayLightSupport();
        this.f3003m = dayLightSupport;
        if (dayLightSupport == 1 || dayLightSupport == 2) {
            this.f3003m = 1;
        }
        this.f2988i0 = this.sa.getIndoorLightSupport();
        this.f2995k = F(this.sa.getIntelligentKeepTemSupport());
        int chargeToaggleGuardSupport = this.sa.getChargeToaggleGuardSupport();
        if (chargeToaggleGuardSupport == 0) {
            this.f2992j0 = 1;
        } else if (chargeToaggleGuardSupport == 1) {
            this.f2992j0 = 0;
        }
        if (P(this.f2983h)) {
            this.f2996k0 = F(this.sa.getChargeLimitSupport());
        } else {
            this.f2996k0 = 404;
        }
        this.K = this.sa.getOBCType();
        int autoPowerOnState = this.ca.getAutoPowerOnState();
        if (autoPowerOnState == 1 || autoPowerOnState == 2) {
            this.f3000l0 = 1;
        } else if (autoPowerOnState == 0 || autoPowerOnState == 3) {
            this.f3000l0 = 0;
        } else {
            this.f3000l0 = 404;
        }
        int autoPowerOffState = this.ca.getAutoPowerOffState();
        if (autoPowerOffState == 1 || autoPowerOffState == 2) {
            this.f3004m0 = 1;
        } else if (autoPowerOffState == 0 || autoPowerOffState == 3) {
            this.f3004m0 = 0;
        } else {
            this.f3004m0 = 404;
        }
        this.f3008n0 = F(this.ca.getAutoExternalRearMirrorFollowUpSwitchSupport());
        int driverSeatAutoReturn = this.ca.getDriverSeatAutoReturn();
        if (driverSeatAutoReturn == 1 || driverSeatAutoReturn == 2) {
            this.f3012o0 = 1;
        } else if (driverSeatAutoReturn == 0 || driverSeatAutoReturn == 3) {
            this.f3012o0 = 0;
        } else {
            this.f3012o0 = 404;
        }
        int steerPositionAutoReturn = this.ca.getSteerPositionAutoReturn();
        if (steerPositionAutoReturn == 1 || steerPositionAutoReturn == 2) {
            this.f3016p0 = 1;
        } else if (steerPositionAutoReturn == 0 || steerPositionAutoReturn == 3) {
            this.f3016p0 = 0;
        } else {
            this.f3016p0 = 404;
        }
        if (this.sa.getBrakePedalModeSupport() == 1) {
            this.f3020q0 = 1;
        } else {
            this.f3020q0 = 0;
        }
        if (this.sa.getComfortableParkingSupport() == 1) {
            this.f2999l = 1;
        } else {
            this.f2999l = 0;
        }
        this.f3024r0 = this.sa.getMotorPretensionerSeatbeltSupport();
        if (this.sa.getSteerAssisSupport() == 1) {
            this.f3028s0 = 1;
        } else {
            this.f3028s0 = 0;
        }
        if (this.sa.getAutoWiperSupport() == 1) {
            this.f3032t0 = 1;
        } else {
            this.f3032t0 = 0;
        }
        int dynamicSuspensionMode = this.ca.getDynamicSuspensionMode();
        if (dynamicSuspensionMode == 1 || dynamicSuspensionMode == 2) {
            this.f3036u0 = 1;
        } else if (dynamicSuspensionMode == 0 || dynamicSuspensionMode == 3) {
            this.f3036u0 = 0;
        } else {
            this.f3036u0 = 404;
        }
        int remoteKeyStartAcTime = this.ca.getRemoteKeyStartAcTime();
        if (remoteKeyStartAcTime < 1 || remoteKeyStartAcTime > 5) {
            this.f3040v0 = 0;
        } else {
            this.f3040v0 = 1;
        }
        int aCAutoAir = this.ca.getACAutoAir();
        if (aCAutoAir == 1 || aCAutoAir == 2) {
            this.f3044w0 = 1;
        } else {
            this.f3044w0 = 0;
        }
        if (this.sa.getSupportPM25Mode() == 1) {
            this.f3048x0 = 1;
        } else {
            this.f3048x0 = 0;
        }
        int aCTunnelCycle = this.ca.getACTunnelCycle();
        if (aCTunnelCycle == 1 || aCTunnelCycle == 2) {
            this.f3052y0 = 1;
        } else if (aCTunnelCycle == 0 || aCTunnelCycle == 3) {
            this.f3052y0 = 0;
        } else {
            this.f3052y0 = 404;
        }
        this.f3056z0 = this.sa.getHudShowParamsSupport();
        int rearAcPanelAutoLockTime = this.ca.getRearAcPanelAutoLockTime();
        if (rearAcPanelAutoLockTime < 1 || rearAcPanelAutoLockTime > 4) {
            this.A0 = 0;
        } else {
            this.A0 = 1;
        }
        if (this.ea.getAcWarmTypeOnlineState() == 3) {
            this.B0 = 1;
        } else {
            this.B0 = 0;
        }
        if (Q(this.f2983h)) {
            if (this.sa.getIntelligentAcAreaSupport() == 2) {
                this.C0 = 1;
            } else {
                this.C0 = 0;
            }
        }
        if (this.ca.getDoorHeightSupport() == 1) {
            this.D0 = 1;
        } else {
            this.D0 = 0;
        }
        int moonRoofConfig = this.ga.getMoonRoofConfig();
        if (moonRoofConfig < 1 || moonRoofConfig > 3) {
            this.E0 = 0;
        } else {
            this.E0 = 1;
        }
        this.J = this.sa.getSupportIntelligentNavigation();
        this.f3039v = this.sa.getSupportHighwayNavigation();
        this.F0 = this.sa.getSupportUrbanNavigation();
        this.G0 = this.sa.getSupportEndToEndNavigation();
        this.H0 = this.sa.getSupportLaneChangeAssist();
        this.I0 = this.sa.getSupportLaneDepartureWarning();
        this.J0 = this.sa.getSupportLaneKeep();
        this.K0 = this.sa.getSupportLaneDepartureStop();
        this.L0 = this.sa.getSupportEmergencyLaneKeepAssist();
        this.M0 = this.sa.getSupportTrafficSignRecogniton();
        this.N0 = this.sa.getSupportIntellignetSpeedLimitAlert();
        this.O0 = this.sa.getSupportIntellignetSpeedLimitControl();
        this.P0 = this.sa.getSupportTrafficLightAttention();
        this.f3011o = this.sa.getSupportDoorOpenWarning();
        this.f3055z = this.sa.getSupportRearCollisionWarning();
        this.Q0 = this.sa.getSupportRearTrafficCrossingAlert();
        this.R0 = this.sa.getSupportFrontCrossingTrafficAlert();
        this.S0 = this.sa.getSupportFrontCrossingTrafficBrake();
        this.T0 = this.sa.getSupportShakeAlert();
        this.U0 = this.sa.getSupportTrafficSignsRecognition();
        this.V0 = this.sa.getSupportPredictCrashAlertMode();
        this.W0 = this.sa.getSupportAiEmergencyBrake();
        int supportMistakeGas = this.sa.getSupportMistakeGas();
        if (supportMistakeGas == 0) {
            this.X0 = 1;
        } else if (supportMistakeGas == 1) {
            this.X0 = 0;
        }
        this.f2987i = F(this.sa.getSupportAutoParking());
        this.Y0 = F(this.sa.getValetParking());
        this.Z0 = F(this.sa.getMemoryParking());
        this.f2957a1 = this.sa.getSupportBlindSpotAssist();
        this.f2961b1 = this.sa.getSupportRearCrossBrake();
        this.f2965c1 = this.sa.getSupportAiDistanceLight();
        this.f2969d1 = this.sa.getSupportAiMatrixLight();
        if (this.f2983h == 69) {
            this.f3019q = this.ra.getElecCarBodyStablySysState();
        } else if (this.sa.getSupportESP() == 2) {
            this.f3019q = 1;
        } else {
            this.f3019q = 0;
        }
        this.f2973e1 = J(this.ca.getRemoteControlLockWindowState());
        this.f2977f1 = J(this.ca.getRemoteControlUnlockWindowState());
        this.f2981g1 = J(this.ca.getMicroSwitchLockWindowState());
        this.f2985h1 = J(this.ca.getMicroSwitchUnlockWindowState());
        this.f2989i1 = J(this.ca.getLockCarRiseWindow());
        this.f2993j1 = J(this.ca.getOverspeedLock());
        this.f2997k1 = J(this.ca.getRainAutoLockWindowState());
        int doorknobAutoShrinkState = this.ca.getDoorknobAutoShrinkState();
        if (doorknobAutoShrinkState < 1 || doorknobAutoShrinkState > 4) {
            this.f3001l1 = 0;
        } else {
            this.f3001l1 = 1;
        }
        this.f3005m1 = J(this.ca.getLockOff());
        this.f3009n1 = this.sa.getDigtalKeySupport();
        this.f3013o1 = J(this.ca.getIntelligentUnlockState());
        this.f3017p1 = J(this.ca.getIntelligentLockState());
        int instrumentConfigure2 = this.la.getInstrumentConfigure();
        if (instrumentConfigure2 < 1 || instrumentConfigure2 > 3) {
            this.f3021q1 = 0;
        } else {
            this.f3021q1 = 1;
        }
        this.f3025r1 = this.sa.getEngineSoundSupport();
        int mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl = this.ca.getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
        int mainDriverSeatAdjustmentConfigurationByGCtrl = this.ca.getMainDriverSeatAdjustmentConfigurationByGCtrl();
        if (mainDriverSeatAdjustmentConfigurationByGCtrl == 0) {
            this.f3029s1 = mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl;
        } else {
            this.f3029s1 = mainDriverSeatAdjustmentConfigurationByGCtrl;
        }
        int mainDriveElectricLegSupportAdjustmentByLeftBodyCtrl = this.ca.getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
        int mainDriveElectricLegSupportAdjustmentByGCtrl = this.ca.getMainDriveElectricLegSupportAdjustmentByGCtrl();
        if (mainDriveElectricLegSupportAdjustmentByGCtrl == 0) {
            this.f3033t1 = mainDriveElectricLegSupportAdjustmentByLeftBodyCtrl;
        } else {
            this.f3033t1 = mainDriveElectricLegSupportAdjustmentByGCtrl;
        }
        int mainDriveElectricHeadrestAdjustmentByLeftBodyCtrl = this.ca.getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
        int mainDriveElectricHeadrestAdjustmentByGCtrl = this.ca.getMainDriveElectricHeadrestAdjustmentByGCtrl();
        if (mainDriveElectricHeadrestAdjustmentByGCtrl == 0) {
            this.f3037u1 = mainDriveElectricHeadrestAdjustmentByLeftBodyCtrl;
        } else {
            this.f3037u1 = mainDriveElectricHeadrestAdjustmentByGCtrl;
        }
        this.f3041v1 = this.ca.getPassengerSeatMemoryFunctionCanByECU();
        this.f3045w1 = this.ca.getPassengerSeatElectricAdjustmentConfigurationByECU();
        int passengerSeatLegSupportAdjustmentConfigurationByECU = this.ca.getPassengerSeatLegSupportAdjustmentConfigurationByECU();
        int passengerSeatLegSupportAdjustmentConfigurationByGCtrl = this.sa.getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
        if (passengerSeatLegSupportAdjustmentConfigurationByECU == 0) {
            this.f3049x1 = passengerSeatLegSupportAdjustmentConfigurationByGCtrl;
        } else {
            this.f3049x1 = passengerSeatLegSupportAdjustmentConfigurationByECU;
        }
        int passengerSeatHeadrestConfigurationByECU = this.ca.getPassengerSeatHeadrestConfigurationByECU();
        int passengerSeatHeadrestConfigurationByGCtrl = this.sa.getPassengerSeatHeadrestConfigurationByGCtrl();
        if (passengerSeatHeadrestConfigurationByECU == 0) {
            this.f3053y1 = passengerSeatHeadrestConfigurationByGCtrl;
        } else {
            this.f3053y1 = passengerSeatHeadrestConfigurationByECU;
        }
        int rearLeftLevelAdjustmentConfigurationByECU = this.ca.getRearLeftLevelAdjustmentConfigurationByECU();
        int leftRearSeatHorizontalAdjustmentConfiguration = this.ca.getLeftRearSeatHorizontalAdjustmentConfiguration();
        if (rearLeftLevelAdjustmentConfigurationByECU == 0) {
            this.f3057z1 = leftRearSeatHorizontalAdjustmentConfiguration;
        } else {
            this.f3057z1 = rearLeftLevelAdjustmentConfigurationByECU;
        }
        int rearLeftBackrestAdjustmentConfigurationByECU = this.ca.getRearLeftBackrestAdjustmentConfigurationByECU();
        int leftRearSeatBackAdjustmentConfiguration = this.ca.getLeftRearSeatBackAdjustmentConfiguration();
        if (rearLeftBackrestAdjustmentConfigurationByECU == 0) {
            this.A1 = leftRearSeatBackAdjustmentConfiguration;
        } else {
            this.A1 = rearLeftBackrestAdjustmentConfigurationByECU;
        }
        int rearLeftLegSupportAdjustmentConfigurationByECU = this.ca.getRearLeftLegSupportAdjustmentConfigurationByECU();
        int leftRearSeatLegBracket = this.ca.getLeftRearSeatLegBracket();
        if (rearLeftLegSupportAdjustmentConfigurationByECU == 0) {
            this.B1 = leftRearSeatLegBracket;
        } else {
            this.B1 = rearLeftLegSupportAdjustmentConfigurationByECU;
        }
        this.C1 = this.ca.getRearLeftBidetAdjustmentConfigurationByECU();
        this.D1 = this.ca.getRearLeftHeightAdjustmentConfigurationByECU();
        this.E1 = this.ca.getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
        this.F1 = this.ca.getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
        int rearLeftMemorySwitchConfigurationByECU = this.ca.getRearLeftMemorySwitchConfigurationByECU();
        int leftRearMemoryConfiguration = this.ca.getLeftRearMemoryConfiguration();
        if (rearLeftMemorySwitchConfigurationByECU == 0) {
            this.G1 = leftRearMemoryConfiguration;
        } else {
            this.G1 = rearLeftMemorySwitchConfigurationByECU;
        }
        this.H1 = this.sa.getLeftRearSeatReclineConfigurationByECU();
        this.I1 = this.ca.getRearSeatReclineConfiguration();
        int rearRightLevelAdjustmentConfigurationByECU = this.ca.getRearRightLevelAdjustmentConfigurationByECU();
        int rightRearSeatBackAdjustmentConfiguration = this.ca.getRightRearSeatBackAdjustmentConfiguration();
        if (rearRightLevelAdjustmentConfigurationByECU == 0) {
            this.J1 = rightRearSeatBackAdjustmentConfiguration;
        } else {
            this.J1 = rearRightLevelAdjustmentConfigurationByECU;
        }
        int rearRightBackrestAdjustmentConfigurationByECU = this.ca.getRearRightBackrestAdjustmentConfigurationByECU();
        int rightRearSeatBackAdjustmentConfiguration2 = this.ca.getRightRearSeatBackAdjustmentConfiguration();
        if (rearRightBackrestAdjustmentConfigurationByECU == 0) {
            this.K1 = rightRearSeatBackAdjustmentConfiguration2;
        } else {
            this.K1 = rearRightBackrestAdjustmentConfigurationByECU;
        }
        int rearRightLegSupportAdjustmentConfigurationByECU = this.ca.getRearRightLegSupportAdjustmentConfigurationByECU();
        int rightRearSeatLegBracket = this.ca.getRightRearSeatLegBracket();
        if (rearRightLegSupportAdjustmentConfigurationByECU == 0) {
            this.L1 = rightRearSeatLegBracket;
        } else {
            this.L1 = rearRightLegSupportAdjustmentConfigurationByECU;
        }
        this.M1 = this.ca.getRearRightBidetAdjustmentConfigurationByECU();
        this.N1 = this.ca.getRearRightHeightAdjustmentConfigurationByECU();
        this.O1 = this.ca.getRearRightHeadrestLevelAdjustmentConfigurationByECU();
        this.P1 = this.ca.getRearRightHeadrestHeightAdjustmentConfigurationByECU();
        int rearRightMemorySwitchConfigurationByECU = this.ca.getRearRightMemorySwitchConfigurationByECU();
        int rightRearMemoryConfiguration = this.ca.getRightRearMemoryConfiguration();
        if (rearRightMemorySwitchConfigurationByECU == 0) {
            this.Q1 = rightRearMemoryConfiguration;
        } else {
            this.Q1 = rearRightMemorySwitchConfigurationByECU;
        }
        this.R1 = this.ca.getRightRearSeatReclineConfigurationByEUC();
        int steeringWheelSupport = this.sa.getSteeringWheelSupport();
        if (steeringWheelSupport == 1 || steeringWheelSupport == 2) {
            this.S1 = 1;
        } else {
            this.S1 = 0;
        }
        int rearviewMirrorSupport = this.ga.getRearviewMirrorSupport();
        if (rearviewMirrorSupport == 2) {
            this.T1 = 1;
        } else if (rearviewMirrorSupport == 1) {
            this.T1 = 0;
        } else {
            this.T1 = 404;
        }
        this.U1 = this.sa.getRefrigeratorModeSupport();
        int remenberPowerOffState = this.ca.getRemenberPowerOffState();
        if (remenberPowerOffState == 1) {
            this.V1 = 1;
        } else if (remenberPowerOffState == 2) {
            this.V1 = 0;
        } else {
            this.V1 = 404;
        }
        int disinfState = this.ca.getDisinfState();
        if (disinfState == 1) {
            this.W1 = 1;
        } else if (disinfState == 2) {
            this.W1 = 0;
        } else {
            this.W1 = 404;
        }
        int trayInitState = this.ca.getTrayInitState();
        if (trayInitState == 1) {
            this.X1 = 1;
        } else if (trayInitState == 2) {
            this.X1 = 0;
        } else {
            this.X1 = 404;
        }
        this.Y1 = this.ca.getAmbientLightColorSupport();
        this.Z1 = this.ca.getAmbientLightSupport();
        this.f2958a2 = this.ca.getAmbientAreaSupport();
        this.f2962b2 = this.sa.getAmbientColorsSupport();
        this.f2966c2 = this.ca.getHudModeParams();
        this.f2970d2 = this.sa.getInstrumentHeightSupport();
        this.f2974e2 = this.sa.getDischargeSupport();
        int driveDistanceShowStandardSupport = this.sa.getDriveDistanceShowStandardSupport();
        if (driveDistanceShowStandardSupport == 0) {
            this.f2978f2 = 1;
        } else if (driveDistanceShowStandardSupport == 1) {
            this.f2978f2 = 0;
        } else {
            this.f2978f2 = 404;
        }
        int ePedalStateSupport = this.sa.getEPedalStateSupport();
        if (ePedalStateSupport == 0) {
            this.f2982g2 = 1;
        } else if (ePedalStateSupport == 1) {
            this.f2982g2 = 0;
        } else {
            this.f2982g2 = 404;
        }
        this.f2986h2 = F(this.sa.getDriveDistanceShowModeSupport());
        this.f2990i2 = this.ca.getElectroniChildLockConfig();
        this.f2994j2 = this.ga.getWindowAntiPinchConfig();
        this.f2998k2 = this.sa.getComfortableSpeedUp();
        int hSpeedAutoCloseState = this.ca.getHSpeedAutoCloseState();
        if (hSpeedAutoCloseState == 1 || hSpeedAutoCloseState == 2) {
            this.f3002l2 = 1;
        } else {
            this.f3002l2 = 0;
        }
        int runingCloseDoorSupport = this.sa.getRuningCloseDoorSupport();
        if (runingCloseDoorSupport == 2 || runingCloseDoorSupport == 3) {
            this.f3006m2 = 1;
        } else {
            this.f3006m2 = 0;
        }
        this.f3010n2 = F(this.sa.getTirePressureInterfaceStatus());
        this.f3014o2 = F(this.sa.getFuelConsumptionInterfaceStatus());
        this.f3018p2 = F(this.sa.getCumulativeAverageEnergyConsumptionInterfaceStatus());
        this.f3022q2 = F(this.sa.getCumulativeAverageFuelConsumptionInterfaceStatus());
        this.f3026r2 = F(this.sa.getCumulativeAveragePowerConsumptionInterfaceStatus());
        this.f3030s2 = F(this.sa.getTheLast50KMAverageEnergyConsumptionInterfaceState());
        this.f3034t2 = F(this.sa.getTheLast50KMAverageFuelConsumptionInterfaceState());
        this.f3038u2 = F(this.sa.getTheLast50KMAveragePowerConsumptionInterfaceState());
        this.f3042v2 = F(this.sa.getInstantaneousFuelConsumptionInterfaceStatus());
        this.f3046w2 = F(this.sa.getExternalChargingPowerInterfaceStatus());
        this.f3050x2 = F(this.sa.getEnergyFlowDiagramInterfaceStatus());
        this.f3054y2 = F(this.sa.getDrivingRangeInterfaceStatusForFuel());
        this.f3058z2 = F(this.sa.getAverageSpeedInterfaceStatus());
        this.A2 = F(this.sa.getDriveTimeInterfaceStatusForSingleTrip());
        this.B2 = this.sa.getADASSystemState();
        this.C2 = this.sa.getSupportSmartChargingPHEV();
        this.D2 = this.sa.getSupportExtreme();
        this.E2 = this.sa.getCarPlanSupport();
        this.F2 = this.sa.getNoSenseChargeSupport();
        this.G2 = this.ga.getElectricFrontHatchConfiguration();
        int chargePreheatSupport = this.sa.getChargePreheatSupport();
        if (chargePreheatSupport == 0) {
            this.H2 = 1;
        } else if (chargePreheatSupport == 1) {
            this.H2 = 0;
        } else {
            this.H2 = 404;
        }
        this.I2 = this.sa.getDMSAssembleStatus();
        this.J2 = this.sa.getDrivingVoiceBroadcastSwitchConfig();
        this.K2 = this.sa.getRestModeConfiguration();
        this.L2 = this.sa.getHUDModeConfiguration();
        this.M2 = this.sa.getIntelligentScreenCastingSwitchConfiguration();
        this.N2 = this.sa.getSupportFRElectricAirOutlet();
        this.O2 = this.ca.getLeftRearMassageChairConfigurationForECU();
        this.P2 = this.ca.getRightRearMassageChairConfigurationForECU();
        if (this.ta.getEPBConfig() == 1 && this.ta.getEPBType() == 1) {
            this.Q2 = 1;
        } else {
            this.Q2 = 0;
        }
        this.R2 = this.sa.getTrunkConfigutarion();
        this.S2 = this.sa.getPanoramicSunroofConfigutarion();
        int amplifierConfiguration = this.sa.getAmplifierConfiguration();
        this.T2 = amplifierConfiguration;
        if (amplifierConfiguration == 0) {
            this.fa.requestEepromAmpType();
        }
        this.V2 = this.sa.getPABSwitchState();
        this.W2 = this.sa.getFrontLightDimmingCtrlConfigBy3AC();
        this.X2 = this.sa.getFrontLightDimmingCtrlConfigBy3AD();
        this.Y2 = this.sa.getTSRLitSpdChgSwitchState();
        this.Z2 = this.sa.getCVCConfig();
        this.a3 = this.sa.getDiSusShowStateByCan();
        this.b3 = this.sa.getDiSusShowStateByCanFD();
        int dyHeightToggleState = this.ca.getDyHeightToggleState();
        if (dyHeightToggleState == 1 || dyHeightToggleState == 2) {
            this.c3 = 1;
        } else {
            this.c3 = 0;
        }
        int welcomeState = this.ca.getWelcomeState();
        if (welcomeState == 1 || welcomeState == 2) {
            this.d3 = 1;
        } else {
            this.d3 = 0;
        }
        int tailBoxTakeState = this.ca.getTailBoxTakeState();
        if (tailBoxTakeState == 1 || tailBoxTakeState == 2) {
            this.e3 = 1;
        } else {
            this.e3 = 0;
        }
        int campingBlanceState = this.ca.getCampingBlanceState();
        if (campingBlanceState == 1 || campingBlanceState == 2) {
            this.f3 = 1;
        } else {
            this.f3 = 0;
        }
        int dampState = this.ca.getDampState();
        if (dampState == 1 || dampState == 2) {
            this.g3 = 1;
        } else {
            this.g3 = 0;
        }
        int liftModeState = this.ca.getLiftModeState();
        if (liftModeState == 1 || liftModeState == 2) {
            this.h3 = 1;
        } else {
            this.h3 = 0;
        }
        int fdDyHeightToggleState = this.ca.getFdDyHeightToggleState();
        if (fdDyHeightToggleState == 1 || fdDyHeightToggleState == 2) {
            this.i3 = 1;
        } else {
            this.i3 = 0;
        }
        int fdWelcomeState = this.ca.getFdWelcomeState();
        if (fdWelcomeState == 1 || fdWelcomeState == 2) {
            this.j3 = 1;
        } else {
            this.j3 = 0;
        }
        int fdTailBoxTakeState = this.ca.getFdTailBoxTakeState();
        if (fdTailBoxTakeState == 1 || fdTailBoxTakeState == 2) {
            this.k3 = 1;
        } else {
            this.k3 = 0;
        }
        int fdCampingBlanceState = this.ca.getFdCampingBlanceState();
        if (fdCampingBlanceState == 1 || fdCampingBlanceState == 2) {
            this.l3 = 1;
        } else {
            this.l3 = 0;
        }
        int fdDampState = this.ca.getFdDampState();
        if (fdDampState == 1 || fdDampState == 2) {
            this.m3 = 1;
        } else {
            this.m3 = 0;
        }
        int fdLiftModeState = this.ca.getFdLiftModeState();
        if (fdLiftModeState == 1 || fdLiftModeState == 2) {
            this.n3 = 1;
        } else {
            this.n3 = 0;
        }
        this.o3 = this.sa.getWarningAuxiliarySwitchSetting();
        this.p3 = this.sa.getWelcomeCeremonySwitchSetting();
        this.q3 = this.sa.getAmbientModeSwitchSettings();
        this.r3 = this.sa.getStarTopSwitchSetting();
        this.s3 = this.sa.getComfortableParkingBy37F();
        this.t3 = this.sa.getLowSpeedEmergencyBrakingConfig();
        this.u3 = this.sa.getSideDistanceWarningConfig();
        this.v3 = this.sa.getV2XConfig();
        this.w3 = this.sa.getTrailerModeConfig();
        this.x3 = this.sa.getFoldDownTheSecondRowOfSeatsConfig();
        this.y3 = this.sa.getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        this.z3 = this.sa.getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        this.A3 = this.sa.getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        this.B3 = this.sa.getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        this.C3 = this.sa.getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        this.D3 = this.sa.getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        this.E3 = this.sa.getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        this.F3 = this.sa.getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat();
        this.G3 = this.sa.getOneButtonDownSwitchDisplayForTheThirdRowOfSeats();
        this.H3 = this.sa.getAllRearSeatOneButtonDownSwitchDisplayConfig();
        this.I3 = this.sa.getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        this.J3 = this.sa.getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        this.K3 = this.sa.getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        this.L3 = this.sa.getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        this.M3 = this.sa.getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        this.N3 = this.sa.getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        this.O3 = this.sa.getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        this.P3 = this.sa.getOneButtonDownConfigurationOfTheThirdRowRightRearSeat();
        this.Q3 = this.sa.getIntelligentSpeedLimitMapVersionDisplay();
        this.R3 = this.sa.getDriverSeatHotStoneMassageConfigurationByECU();
        this.S3 = this.sa.getPassengerSeatHotStoneMassageConfigurationByECU();
        this.T3 = this.sa.getLeftRearSeatHotStoneMassageConfigurationByECU();
        this.U3 = this.sa.getRightRearSeatHotStoneMassageConfigurationByECU();
        this.V3 = this.sa.getSupportAVH();
        this.W3 = this.sa.getSupportRearControlPanelSign();
        this.X3 = this.sa.getSupportDimmingCanopyConfiguration();
        this.Y3 = this.sa.getSupportAlarmMode();
        this.Z3 = this.sa.getBSISSupport();
        this.a4 = this.sa.getMOISSupport();
        this.b4 = this.sa.getDynamicSuspensionModeByCan();
        this.c4 = this.sa.getDynamicSuspensionModeByCanFD();
        this.d4 = this.sa.getSupportManualModel();
        this.h4 = this.ca.get5LevelSeatHeatingState(1);
        this.i4 = this.ca.get5LevelSeatHeatingState(2);
        this.j4 = this.ca.get5LevelSeatHeatingState(3);
        this.k4 = this.ca.get5LevelSeatHeatingState(4);
        this.l4 = this.ca.get5LevelSeatVentilatingState(1);
        this.m4 = this.ca.get5LevelSeatVentilatingState(2);
        this.n4 = this.ca.get5LevelSeatVentilatingState(3);
        this.o4 = this.ca.get5LevelSeatVentilatingState(4);
        this.p4 = this.ca.getSeatVentilatingState(1);
        this.q4 = this.ca.getSeatVentilatingState(2);
        this.r4 = this.ca.getSeatVentilatingState(3);
        this.s4 = this.ca.getSeatVentilatingState(4);
        this.t4 = this.ca.getSeatVentilatingState2(3);
        this.u4 = this.ca.getSeatVentilatingState2(4);
        this.v4 = this.ca.getSeatVentilatingNotL1PState();
        this.w4 = this.ca.getSeatHeatingState(1);
        this.x4 = this.ca.getSeatHeatingState(2);
        this.y4 = this.ca.getSeatHeatingState(3);
        this.z4 = this.ca.getSeatHeatingState(4);
        this.A4 = this.ca.getSeatHeatingState2(3);
        this.B4 = this.ca.getSeatHeatingState2(4);
        this.C4 = this.ca.getSeatHeatingNotL1PState();
    }

    public final void I() {
        int i3;
        BydLogHelper.logD("getAutomaticLearningFlagAndSetFunctionStatusToDB mIsFirstLearning= " + this.Ha);
        if (C(this.W4, this.f3047x) && this.Ha) {
            BydLogHelper.logD(" ban mPanoramaOnlineStatusByDB = " + this.W4 + ", mPanoramaOnlineStatusByMCU = " + this.f3047x);
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.PANORAMA_ONLINE, this.f3047x);
            z(R.string.auto_study_can_item_1, "Panorama.getPanoramaOnlineState", this.f3047x, this.W4);
        } else {
            z(R.string.auto_study_can_item_1, "Panorama.getPanoramaOnlineState", this.f3047x, this.W4);
        }
        if (C(this.S4, this.f3031t) && (this.S4 == 0 || this.Ha)) {
            BydLogHelper.logD("ban mGoHomeTimeStatusByDB = " + this.S4 + ", mGoHomeTimeStatusByMCU = " + this.f3031t);
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.GO_HOME_TIME, this.f3031t);
            z(R.string.auto_study_can_item_4, "Setting.getBackHomeLightDelayValue", this.f3031t, this.S4);
        }
        if (C(this.V4, this.f3043w) && (this.V4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.LEAVE_HOME_TIME, this.f3043w);
            z(R.string.auto_study_can_item_5, "Setting.getLeftHomeLightDelayValue", this.f3043w, this.V4);
        }
        if (B(this.I4, this.f2983h)) {
            BydLogHelper.logE("ban mAutoModelStatusByDB = " + this.I4 + ",mAutoModelStatusByMCU = " + this.f2983h);
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.MODEL_VALUE, this.f2983h);
            z(R.string.auto_study_can_item_2, "Bodywork.getAutoType", this.f2983h, this.I4);
            Intent intent = new Intent("com.byd.action.SET_AUTO_TYPE");
            intent.putExtra("auto_type", this.f2983h);
            this.ya.sendBroadcast(intent);
            BydLogHelper.logE("getAutomaticLearningFlagAndSetFunctionStatusToDB: mAutoModelStatusByMCU = " + this.f2983h);
            U();
            if (this.va != null) {
                BydLogHelper.logE("setCarType = " + this.f2983h);
                this.va.setCarType(this.f2983h);
            }
            String str = SystemProperties.get("apps.setting.product.outswver", "");
            if (!"".equals(str)) {
                SharedPreferences.Editor editorEdit = this.Ca.edit();
                editorEdit.putString("outswver_old", str);
                editorEdit.apply();
            }
        }
        int i4 = this.I4;
        if (i4 != Integer.MIN_VALUE && (i3 = this.f2983h) != 404 && i3 == i4) {
            String string = this.Ca.getString("outswver_old", "");
            String str2 = SystemProperties.get("apps.setting.product.outswver", "");
            BydLogHelper.logD("Reset the configuration for car studyAutoContent=" + string + ", outswver=" + str2);
            if (("".equals(string) || !string.equals(str2)) && !"".equals(str2)) {
                SharedPreferences.Editor editorEdit2 = this.Ca.edit();
                editorEdit2.putString("outswver_old", str2);
                editorEdit2.apply();
                U();
            }
        }
        if (C(this.i5, this.L) && (this.i5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "energy_state", this.L);
            z(R.string.auto_study_can_item_6, "BYDAutoEnergyDevice.getEnergyState", this.L, this.i5);
        }
        if (C(this.j5, this.M) && (this.j5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "drive_config", this.M);
            z(R.string.auto_study_can_item_7, "Setting.getDriveConfig", this.M, this.j5);
        }
        if (C(this.k5, this.N) && (this.k5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "dm_type", this.N);
            z(R.string.auto_study_can_item_8, "AutoStudy.getDMType", this.N, this.k5);
        }
        if (C(this.e5, this.H) && (this.e5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.SYSTEM_UNIT_VISIBLE, this.H);
            z(R.string.auto_study_can_item_9, "Instrument.getInstrumentConfigure", this.H, this.e5);
        }
        if (C(this.f5, this.I) && (this.f5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.UNIT_POWER_VISIBLE, this.I);
            z(R.string.auto_study_can_item_10, "Instrument.getUnit", this.I, this.f5);
        }
        if (C(this.d5, this.G) && (this.d5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.UNIT_AIR_PRESSURE_VISIBLE, this.G);
            z(R.string.auto_study_can_item_11, "Instrument.PRESSURE_UNIT", this.G, this.d5);
        }
        if (C(this.c5, this.F) && (this.c5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.UNIT_TEMPERATURE_VISIBLE, this.F);
            z(R.string.auto_study_can_item_13, "Instrument.TEMPERATURE_UNIT", this.F, this.c5);
        }
        if (C(this.N4, this.f3007n) && (this.N4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.UNIT_DISTANCE_FUEL_CONSUMPTION_VISIBLE, this.f3007n);
            z(R.string.auto_study_can_item_12, "Instrument.ScreenType", this.f3007n, this.N4);
        }
        if (C(this.m5, this.O) && (this.m5 == 0 || this.Ha)) {
            BydLogHelper.logD("mRearAcConfigByDB = " + this.m5 + ",mRearAcConfigByMCU = " + this.O);
            if (this.ga.hasMessage(3) == 1) {
                this.Ia = true;
                CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.REAR_AC_ONLINE, this.O);
                z(R.string.auto_study_can_item_14, "AutoStudy.getSupportRearAc", this.O, this.m5);
            }
        }
        if (C(this.n5, this.P) && (this.n5 == 0 || this.Ha)) {
            BydLogHelper.logD("mAcTypeByDB = " + this.n5 + ",mAcTypeByMCU = " + this.P);
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.AC_TYPE, this.P);
            z(R.string.auto_study_can_item_269, ".", this.P, this.n5);
        }
        if (C(this.o5, this.Q) && (this.o5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_switch_config", this.Q);
            z(R.string.auto_study_can_item_15, "AutoStudy.getSupportRearAcSwitch", this.Q, this.o5);
        }
        if (C(this.G4, this.f2975f) && (this.G4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_wind_level", this.f2975f);
            z(R.string.auto_study_can_item_16, "Setting.getRearAcMaxWindLevel", this.f2975f, this.G4);
        }
        if (C(this.p5, this.R) && (this.p5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_key_lock", this.R);
            z(R.string.auto_study_can_item_17, "Setting.getSupportRearAcKeyLock", this.R, this.p5);
        }
        if (C(this.q5, this.S) && (this.q5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_wind_mode_control", this.S);
            z(R.string.auto_study_can_item_18, "Setting.getSupportRearAcWindModeControl", this.S, this.q5);
        }
        if (C(this.r5, this.T) && (this.r5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_auto_control", this.T);
            z(R.string.auto_study_can_item_19, "Setting.getSupportRearAcAutoControl", this.T, this.r5);
        }
        if (C(this.s5, this.U) && (this.s5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_compressor", this.U);
            z(R.string.auto_study_can_item_20, "Setting.getSupportRearAcCompressor", this.U, this.s5);
        }
        if (C(this.t5, this.V) && (this.t5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_tempertrue_control", this.V);
            z(R.string.auto_study_can_item_21, "Setting.getSupportRearAcTempertureControl", this.V, this.t5);
        }
        if (C(this.u5, this.W) && (this.u5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_wind_level_control", this.W);
            z(R.string.auto_study_can_item_22, "Setting.getSupportRearAcWindLevelControl", this.W, this.u5);
        }
        if (C(this.v5, this.X) && this.v5 == 0) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_fault_num_shown_state", this.X);
            z(R.string.auto_study_can_item_23, "AutoAc.getAcFaultNumShownState", this.X, this.v5);
        }
        if (C(this.w5, this.Y) && (this.w5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_temperature_control_mode", this.Y);
            z(R.string.auto_study_can_item_24, "AutoAc.getAcTemperatureControlMode", this.Y, this.w5);
        }
        if (C(this.x5, this.Z) && (this.x5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_wind_mode_num", this.Z);
            z(R.string.auto_study_can_item_25, "AutoAc.getAcWindModeNum", this.Z, this.x5);
        }
        if (C(this.y5, this.f2956a0) && (this.y5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_auto_key_can_close", this.f2956a0);
            z(R.string.auto_study_can_item_26, "AutoStudy.getSupportAcAutoKeyCanClose", this.f2956a0, this.y5);
        }
        if (C(this.F4, this.f2971e) && (this.F4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "elec_defrost", this.f2971e);
            z(R.string.auto_study_can_item_27, "AutoStudy.getDefrostRearConfig", this.f2971e, this.F4);
        }
        if (C(this.H4, this.f2979g) && (this.H4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.AC_HEAT_MODE_VISIBLE, this.f2979g);
            z(R.string.auto_study_can_item_28, "AutoAcDevice.getAcWarmTypeOnlineState", this.f2979g, this.H4);
        }
        if (C(this.b5, this.D) && (this.b5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.Global.STEERING_WHEEL_HEATING_VISIBLE, this.D);
            z(R.string.auto_study_can_item_29, "AutoStudy.getSteeringWheelHeatingConfiguration", this.D, this.b5);
        }
        if (C(this.Q4, this.f3023r) && (this.Q4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.Global.SEAT_MASSAGE_VISIBLE_LEFT, this.f3023r);
            z(R.string.auto_study_can_item_30, "Setting.getSupportSeatMassage(0)", this.f3023r, this.Q4);
        }
        if (C(this.R4, this.f3027s) && (this.R4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.Global.SEAT_MASSAGE_VISIBLE_RIGHT, this.f3027s);
            z(R.string.auto_study_can_item_31, "Setting.getSupportSeatMassage(1)", this.f3027s, this.R4);
        }
        if (C(this.Y4, this.A) && (this.Y4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "seat_massage_visible_left_rear", this.A);
            z(R.string.auto_study_can_item_32, "Setting.getSupportSeatMassage(2)", this.A, this.Y4);
        }
        if (C(this.Z4, this.B) && (this.Z4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "seat_massage_visible_right_rear", this.B);
            z(R.string.auto_study_can_item_33, "Setting.getSupportSeatMassage(3)", this.B, this.Z4);
        }
        if (C(this.z5, this.f2960b0) && (this.z5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "left_plat_form_configs", this.f2960b0);
            z(R.string.auto_study_can_item_34, "AutoStudy.getLeftPlatformConfigs", this.f2960b0, this.z5);
        }
        if (C(this.A5, this.f2964c0) && (this.A5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "negative_ion", this.f2964c0);
            z(R.string.auto_study_can_item_35, "AutoStudy.getSupportNegativeIon", this.f2964c0, this.A5);
        }
        if (C(this.B5, this.f2968d0) && ((this.B5 == 0 || this.Ha) && this.ga.hasMessage(186) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "fragrance", this.f2968d0);
            z(R.string.auto_study_can_item_36, "AutoStudy.getSupportFragrance", this.f2968d0, this.B5);
        }
        if (C(this.C5, this.f2972e0) && (this.C5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "car_type", this.f2972e0);
            z(R.string.auto_study_can_item_37, "AutoStudy.getCarType", this.f2972e0, this.C5);
        }
        if (C(this.D5, this.f2976f0) && (this.D5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "lamp_singnal", this.f2976f0);
            z(R.string.auto_study_can_item_38, "AutoStudy.getSupportLampSingnal", this.f2976f0, this.D5);
        }
        if (C(this.E5, this.f2980g0) && (this.E5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "afs_switch", this.f2980g0);
            z(R.string.auto_study_can_item_39, "AutoLightDevice.getAFSSwitch", this.f2980g0, this.E5);
        }
        if (C(this.T4, this.f3035u) && (this.T4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "head_lamp", this.f3035u);
            z(R.string.auto_study_can_item_40, "AutoStudy.getHeadlampSuppor", this.f3035u, this.T4);
        }
        if (C(this.F5, this.f2984h0) && (this.F5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "head_lamp_level_config", this.f2984h0);
            z(R.string.auto_study_can_item_41, "AutoStudy.getHeadlampLevelConfig", this.f2984h0, this.F5);
        }
        if (C(this.a5, this.C) && (this.a5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "smart_welcome_light", this.C);
            z(R.string.auto_study_can_item_42, "AutoStudy.getUsherLightSupport", this.C, this.a5);
        }
        if (C(this.M4, this.f3003m) && (this.M4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "daytime_running_light", this.f3003m);
            z(R.string.auto_study_can_item_43, "AutoStudy.getDayLightSupport", this.f3003m, this.M4);
        }
        if (C(this.G5, this.f2988i0) && (this.G5 == 1 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "door_light_state", this.f2988i0);
            z(R.string.auto_study_can_item_44, "AutoStudy.getIndoorLightSupport", this.f2988i0, this.G5);
        }
        if (C(this.K4, this.f2995k) && (this.K4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "charge_temperature_control", this.f2995k);
            z(R.string.auto_study_can_item_45, "AutoStudy.getIntelligentKeepTemSupport", this.f2995k, this.K4);
        }
        if (C(this.H5, this.f2992j0) && (this.H5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "charge_toaggle_guard", this.f2992j0);
            z(R.string.auto_study_can_item_190, "AutoStudy.getChargeToaggleGuardSupport", this.f2992j0, this.H5);
        }
        if (C(this.I5, this.f2996k0) && (this.I5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.CHARGE_LIMIT, this.f2996k0);
            z(R.string.auto_study_can_item_46, "AutoStudy.getChargeLimitSupport", this.f2996k0, this.I5);
        }
        if (C(this.h5, this.K) && (this.h5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "vtol", this.K);
            z(R.string.auto_study_can_item_47, "AutoStudy.getOBCType", this.K, this.h5);
        }
        if (C(this.J5, this.f3000l0) && (this.J5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "auto_power_on", this.f3000l0);
            z(R.string.auto_study_can_item_48, "AutoSettingDevice.getAutoPowerOnState", this.f3000l0, this.J5);
        }
        if (C(this.K5, this.f3004m0) && (this.K5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "auto_power_off", this.f3004m0);
            z(R.string.auto_study_can_item_49, "AutoSettingDevice.getAutoPowerOffState", this.f3004m0, this.K5);
        }
        if (C(this.L5, this.f3008n0) && (this.L5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_mirror_follow_up", this.f3008n0);
            z(R.string.auto_study_can_item_50, "AutoStudy.getAutoExternalRearMirrorFollowUpSwitchSupport", this.f3008n0, this.L5);
        }
        if (C(this.M5, this.f3012o0) && (this.M5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "driver_seat_auto_return", this.f3012o0);
            z(R.string.auto_study_can_item_51, "AutoSettingDevice.getDriverSeatAutoReturn", this.f3012o0, this.M5);
        }
        if (C(this.N5, this.f3016p0) && (this.N5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "steer_position_auto_return", this.f3016p0);
            z(R.string.auto_study_can_item_52, "AutoSettingDevice.getSteerPositionAutoReturn", this.f3016p0, this.N5);
        }
        if (C(this.O5, this.f3020q0) && (this.O5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "brake_pedal_mode", this.f3020q0);
            z(R.string.auto_study_can_item_53, "AutoStudy.getBrakePedalModeSupport", this.f3020q0, this.O5);
        }
        if (C(this.L4, this.f2999l) && (this.L4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "comfortable_parking", this.f2999l);
            z(R.string.auto_study_can_item_54, "AutoStudy.getComfortableParkingSupport", this.f2999l, this.L4);
        }
        if (C(this.P5, this.f3024r0) && (this.P5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "motor_pretensioner_seatbelt", this.f3024r0);
            z(R.string.auto_study_can_item_55, "AutoStudy.getMotorPretensionerSeatbeltSupport", this.f3024r0, this.P5);
        }
        if (C(this.Q5, this.f3028s0) && (this.Q5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "steer_assis", this.f3028s0);
            z(R.string.auto_study_can_item_56, "AutoStudy.getSteerAssisSupport", this.f3028s0, this.Q5);
        }
        if (C(this.R5, this.f3032t0) && (this.R5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "auto_wiper", this.f3032t0);
            z(R.string.auto_study_can_item_57, "AutoStudy.getAutoWiperSupport", this.f3032t0, this.R5);
        }
        if (C(this.S5, this.f3036u0) && (this.S5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "dynamic_suspension_mode", this.f3036u0);
            z(R.string.auto_study_can_item_58, "AutoSettingDevice.getDynamicSuspensionMode", this.f3036u0, this.S5);
        }
        if (C(this.T5, this.f3040v0) && (this.T5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "remote_key_start_ac_time", this.f3040v0);
            z(R.string.auto_study_can_item_59, "AutoSettingDevice.getRemoteKeyStartAcTime", this.f3040v0, this.T5);
        }
        if (C(this.U5, this.f3044w0) && (this.U5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_auto_air", this.f3044w0);
            z(R.string.auto_study_can_item_60, "AutoSettingDevice.getACAutoAir", this.f3044w0, this.U5);
        }
        if (C(this.V5, this.f3048x0) && (this.V5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "auto_purification", this.f3048x0);
            z(R.string.auto_study_can_item_61, "AutoStudy.getSupportPM25Mode", this.f3048x0, this.V5);
        }
        if (C(this.W5, this.f3052y0) && (this.W5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_tunnel_cycle", this.f3052y0);
            z(R.string.auto_study_can_item_62, "AutoSettingDevice.getACTunnelCycle", this.f3052y0, this.W5);
        }
        if (C(this.X5, this.f3056z0) && this.Ha) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "hud_show_params", this.f3056z0);
            z(R.string.auto_study_can_item_63, "AutoStudy.getHudShowParamsSupport", this.f3056z0, this.X5);
        }
        if (C(this.Y5, this.A0) && (this.Y5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_ac_panel_auto_lock_time", this.A0);
        }
        if (C(this.Z5, this.B0) && (this.Z5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ac_warm_type_online_state", this.B0);
        }
        if (C(this.a6, this.C0) && (this.a6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "intelligent_ac_area", this.C0);
        }
        if (C(this.b6, this.D0) && (this.b6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "door_height", this.D0);
            z(R.string.auto_study_can_item_66, "AutoStudy.getDoorHeightSupport", this.D0, this.b6);
        }
        if (C(this.c6, this.E0) && (this.c6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "moon_roof_config", this.E0);
            z(R.string.auto_study_can_item_67, "AutoBodyworkDevice.getMoonRoofConfig", this.E0, this.c6);
        }
        if (C(this.g5, this.J) && (this.g5 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "urban_intelligent_navigation", this.J);
            z(R.string.auto_study_can_item_68, "AutoStudy.getSupportIntelligentNavigation", this.J, this.g5);
        }
        if (C(this.U4, this.f3039v) && (this.U4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "high_speed_intelligent_navigation", this.f3039v);
            z(R.string.auto_study_can_item_69, "AutoStudy.getSupportHighwayNavigation", this.f3039v, this.U4);
        }
        if (C(this.d6, this.F0) && (this.d6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "urban_navigation", this.F0);
            z(R.string.auto_study_can_item_70, "AutoStudy.getSupportUrbanNavigation", this.F0, this.d6);
        }
        if (C(this.e6, this.G0) && (this.e6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "e2e_navigation", this.G0);
            z(R.string.auto_study_can_item_71, "AutoStudy.getSupportEndToEndNavigation", this.G0, this.e6);
        }
        if (C(this.f6, this.H0) && (this.f6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "lane_change_assist", this.H0);
            z(R.string.auto_study_can_item_72, "AutoStudy.getSupportLaneChangeAssist", this.H0, this.f6);
        }
        if (C(this.g6, this.I0) && (this.g6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "lane_departure_warning", this.I0);
            z(R.string.auto_study_can_item_73, "AutoStudy.getSupportLaneDepartureWarning", this.I0, this.g6);
        }
        if (D(this.h6, this.J0) && (this.h6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "lane_keep", this.J0);
            z(R.string.auto_study_can_item_74, "AutoStudy.getSupportLaneKeep", this.J0, this.h6);
        }
        if (C(this.i6, this.K0) && (this.i6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "lane_departure_stop", this.K0);
            z(R.string.auto_study_can_item_75, "AutoStudy.getSupportLaneDepartureStop", this.K0, this.i6);
        }
        if (C(this.j6, this.L0) && (this.j6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "emergency_lane_keep_assist", this.L0);
            z(R.string.auto_study_can_item_76, "AutoStudy.getSupportEmergencyLaneKeepAssist", this.L0, this.j6);
        }
        if (C(this.k6, this.M0) && (this.k6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "traffic_sign_recogniton", this.M0);
            z(R.string.auto_study_can_item_77, "AutoStudy.getSupportTrafficSignRecogniton", this.M0, this.k6);
        }
        if (C(this.l6, this.N0) && (this.l6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "intellignet_speed_limit_alert", this.N0);
            z(R.string.auto_study_can_item_78, "AutoStudy.getSupportIntellignetSpeedLimitAlert", this.N0, this.l6);
        }
        if (C(this.m6, this.O0) && (this.m6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "intellignet_speed_limit_control", this.O0);
            z(R.string.auto_study_can_item_79, "AutoStudy.getSupportIntellignetSpeedLimitControl", this.O0, this.m6);
        }
        if (C(this.n6, this.P0) && (this.n6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "traffic_light_attention", this.P0);
            z(R.string.auto_study_can_item_80, "AutoStudy.getSupportTrafficLightAttention", this.P0, this.n6);
        }
        if (C(this.O4, this.f3011o) && (this.O4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "door_open_warning", this.f3011o);
            z(R.string.auto_study_can_item_81, "AutoStudy.getSupportDoorOpenWarning", this.f3011o, this.O4);
        }
        if (C(this.X4, this.f3055z) && (this.X4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_collision_warning", this.f3055z);
            z(R.string.auto_study_can_item_82, "AutoStudy.getSupportRearCollisionWarning", this.f3055z, this.X4);
        }
        if (C(this.o6, this.Q0) && (this.o6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_traffic_crossing_alert", this.Q0);
            z(R.string.auto_study_can_item_83, "AutoStudy.getSupportRearTrafficCrossingAlert", this.Q0, this.o6);
        }
        if (C(this.p6, this.R0) && (this.p6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "front_crossing_traffic_alert", this.R0);
            z(R.string.auto_study_can_item_84, "AutoStudy.getSupportFrontCrossingTrafficAlert", this.R0, this.p6);
        }
        if (C(this.q6, this.S0) && (this.q6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "front_crossing_traffic_brake", this.S0);
            z(R.string.auto_study_can_item_85, "AutoStudy.getSupportFrontCrossingTrafficBrake", this.S0, this.q6);
        }
        if (C(this.r6, this.T0) && (this.r6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "shake_alert", this.T0);
            z(R.string.auto_study_can_item_86, "AutoStudy.getSupportShakeAlert", this.T0, this.r6);
        }
        if (C(this.s6, this.U0) && (this.s6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "traffic_signs_recognition", this.U0);
            z(R.string.auto_study_can_item_87, "AutoStudy.getSupportTrafficSignsRecognition", this.U0, this.s6);
        }
        if (C(this.t6, this.V0) && (this.t6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "predict_crash_alert_mode", this.V0);
            z(R.string.auto_study_can_item_88, "AutoStudy.getSupportPredictCrashAlertMode", this.V0, this.t6);
        }
        if (C(this.u6, this.W0) && (this.u6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ai_emergency_brake", this.W0);
            z(R.string.auto_study_can_item_89, "AutoStudy.getSupportAiEmergencyBrake", this.W0, this.u6);
        }
        if (C(this.v6, this.X0) && (this.v6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "mistake_gas", this.X0);
            z(R.string.auto_study_can_item_90, "AutoStudy.getSupportMistakeGas", this.X0, this.v6);
        }
        if (C(this.J4, this.f2987i) && (this.J4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, CarSettings.UserTableData.AUTO_PARKING, this.f2987i);
            z(R.string.auto_study_can_item_91, "AutoStudy.getSupportAutoParking", this.f2987i, this.J4);
        }
        if (C(this.w6, this.Y0) && (this.w6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "valet_parking", this.Y0);
            z(R.string.auto_study_can_item_92, "AutoStudy.getValetParking", this.Y0, this.w6);
        }
        if (C(this.x6, this.Z0) && (this.x6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "memory_parking", this.Z0);
            z(R.string.auto_study_can_item_93, "AutoStudy.getMemoryParking", this.Z0, this.x6);
        }
        if (C(this.y6, this.f2957a1) && (this.y6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "blind_spot_assist", this.f2957a1);
            z(R.string.auto_study_can_item_94, "AutoStudy.getSupportBlindSpotAssist", this.f2957a1, this.y6);
        }
        if (C(this.z6, this.f2961b1) && (this.z6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rear_cross_brake", this.f2961b1);
            z(R.string.auto_study_can_item_95, "AutoStudy.getSupportRearCrossBrake", this.f2961b1, this.z6);
        }
        if (C(this.A6, this.f2965c1) && (this.A6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ai_distance_light", this.f2965c1);
            z(R.string.auto_study_can_item_96, "AutoStudy.getSupportAiDistanceLight", this.f2965c1, this.A6);
        }
        if (C(this.B6, this.f2969d1) && (this.B6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ai_matrix_light", this.f2969d1);
            z(R.string.auto_study_can_item_97, "AutoStudy.getSupportAiMatrixLight", this.f2969d1, this.B6);
        }
        if (C(this.P4, this.f3019q) && (this.P4 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "esp_config", this.f3019q);
            z(R.string.auto_study_can_item_98, "AutoDiPilotDevice.getElecCarBodyStablySysState", this.f3019q, this.P4);
        }
        if (C(this.C6, this.f2973e1) && (this.C6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "remote_control_lock_window_state", this.f2973e1);
        }
        if (C(this.D6, this.f2977f1) && (this.D6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "remote_control_unlock_window_state", this.f2977f1);
        }
        if (C(this.E6, this.f2981g1) && (this.E6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "micro_switch_lock_window_state", this.f2981g1);
        }
        if (C(this.F6, this.f2985h1) && (this.F6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "micro_switch_unlock_window_state", this.f2985h1);
        }
        if (C(this.G6, this.f2989i1) && (this.G6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "lock_car_rise_window", this.f2989i1);
            z(R.string.auto_study_can_item_103, "AutoSettingDevice.getLockCarRiseWindow", this.f2989i1, this.G6);
        }
        if (C(this.H6, this.f2993j1) && (this.H6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "over_speed_lock", this.f2993j1);
        }
        if (C(this.I6, this.f2997k1) && (this.I6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "rain_auto_lock_window_state", this.f2997k1);
        }
        if (C(this.J6, this.f3001l1) && (this.J6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "doorknob_auto_shrink_state", this.f3001l1);
        }
        if (C(this.K6, this.f3005m1) && (this.K6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "lock_off", this.f3005m1);
            z(R.string.auto_study_can_item_107, "AutoSettingDevice.getLockOff", this.f3005m1, this.K6);
        }
        if (C(this.L6, this.f3009n1) && (this.L6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "digtal_key", this.f3009n1);
            z(R.string.auto_study_can_item_108, "AutoStudy.getDigtalKeySupport", this.f3009n1, this.L6);
        }
        if (C(this.M6, this.f3013o1) && (this.M6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "intelligent_unlock_state", this.f3013o1);
            z(R.string.auto_study_can_item_109, "AutoSettingDevice.getIntelligentUnlockState", this.f3013o1, this.M6);
        }
        if (C(this.N6, this.f3017p1) && (this.N6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "intelligent_lock_state", this.f3017p1);
            z(R.string.auto_study_can_item_110, "AutoSettingDevice.getIntelligentLockState", this.f3017p1, this.N6);
        }
        if (C(this.O6, this.f3021q1) && (this.O6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "remind_speed", this.f3021q1);
            z(R.string.auto_study_can_item_111, "AutoInstrumentDevice.getInstrumentConfigure", this.f3021q1, this.O6);
        }
        if (C(this.P6, this.f3025r1) && (this.P6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "engine_sound", this.f3025r1);
        }
        if (C(this.Q6, this.f3029s1) && (this.Q6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "main_driver_seat_adjustment", this.f3029s1);
        }
        if (C(this.R6, this.f3033t1) && (this.R6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "main_drive_electric_leg_support_adjustment", this.f3033t1);
        }
        if (C(this.S6, this.f3037u1) && (this.S6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Main_Drive_Electric_Headrest_Adjustment", this.f3037u1);
        }
        if (C(this.T6, this.f3041v1) && (this.T6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Passenger_Seat_Memory_Function", this.f3041v1);
        }
        if (C(this.U6, this.f3045w1) && (this.U6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Passenger_Seat_Electric_Adjustment", this.f3045w1);
        }
        if (C(this.V6, this.f3049x1) && (this.V6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Passenger_Seat_Leg_Support_Adjustment", this.f3049x1);
        }
        if (C(this.W6, this.f3053y1) && (this.W6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Passenger_Seat_Headrest", this.f3053y1);
        }
        if (C(this.X6, this.f3057z1) && (this.X6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Level_Adjustment", this.f3057z1);
        }
        if (C(this.Y6, this.A1) && (this.Y6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Backrest_Adjustment", this.A1);
        }
        if (C(this.Z6, this.B1) && (this.Z6 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Leg_Adjustment", this.B1);
        }
        if (C(this.a7, this.C1) && (this.a7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Bidet_Adjustment", this.C1);
        }
        if (C(this.b7, this.D1) && (this.b7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Height_Adjustment", this.D1);
        }
        if (C(this.c7, this.E1) && (this.c7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Headrest_Level_Adjustment", this.E1);
        }
        if (C(this.d7, this.F1) && (this.d7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Headrest_Height_Adjustment", this.F1);
        }
        if (C(this.e7, this.G1) && (this.e7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Left_Memory_Switch", this.G1);
        }
        if (C(this.f7, this.H1) && (this.f7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Left_Rear_Seat_Recline", this.H1);
        }
        if (C(this.g7, this.I1) && (this.g7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Seat_Recline", this.I1);
        }
        if (C(this.h7, this.J1) && (this.h7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Level_Adjustment", this.J1);
        }
        if (C(this.i7, this.K1) && (this.i7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Backrest_Adjustment", this.K1);
        }
        if (C(this.j7, this.L1) && (this.j7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Leg_Support_Adjustment", this.L1);
        }
        if (C(this.k7, this.M1) && (this.k7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Bidet_Adjustment", this.M1);
        }
        if (C(this.l7, this.N1) && (this.l7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Height_Adjustment", this.N1);
        }
        if (C(this.m7, this.O1) && (this.m7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Headrest_Level_Adjustment", this.O1);
        }
        if (C(this.n7, this.P1) && (this.n7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Headrest_Height_Adjustment", this.P1);
        }
        if (C(this.o7, this.Q1) && (this.o7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rear_Right_Memory_Switch", this.Q1);
        }
        if (C(this.p7, this.R1) && (this.p7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Right_Rear_Seat_Recline", this.R1);
        }
        if (C(this.q7, this.S1) && (this.q7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Steering_Wheel", this.S1);
        }
        if (C(this.r7, this.T1) && (this.r7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rearview_Mirror", this.T1);
        }
        if (C(this.s7, this.U1) && (this.s7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Refrigerator_Mode", this.U1);
        }
        if (C(this.t7, this.V1) && (this.t7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Remenber_Power_Off_State", this.V1);
            z(R.string.auto_study_can_item_142, "mBydAutoSettingDevice.getRemenberPowerOffState", this.V1, this.t7);
        }
        if (C(this.u7, this.W1) && (this.u7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Disinf_State", this.W1);
            z(R.string.auto_study_can_item_143, "mBydAutoSettingDevice.getDisinfState", this.W1, this.u7);
        }
        if (C(this.v7, this.X1) && (this.v7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Tray_Init_State", this.X1);
        }
        if (C(this.w7, this.Y1) && (this.w7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Ambient_Light_Color", this.Y1);
        }
        if (C(this.x7, this.Z1) && (this.x7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Ambient_Light", this.Z1);
        }
        if (C(this.y7, this.f2958a2) && (this.y7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Ambient_Area", this.f2958a2);
        }
        if (C(this.z7, this.f2962b2) && (this.z7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Ambient_Colors", this.f2962b2);
            z(R.string.auto_study_can_item_149, "BYDAutoLightDevice.getAmbientColorsSupport", this.f2962b2, this.z7);
        }
        if (C(this.A7, this.f2966c2) && (this.A7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Hud_Mode_Params", this.f2966c2);
        }
        if (C(this.B7, this.f2970d2) && (this.B7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Instrument_Height", this.f2970d2);
        }
        if (C(this.C7, this.f2974e2) && (this.C7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Discharge", this.f2974e2);
            z(R.string.auto_study_can_item_152, "AutoStudy.getDischargeSupport", this.f2974e2, this.C7);
        }
        if (C(this.D7, this.f2978f2) && (this.D7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Drive_Distance_Show_Standard", this.f2978f2);
        }
        if (C(this.E7, this.f2982g2) && (this.E7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "EPedal_State", this.f2982g2);
            z(R.string.auto_study_can_item_154, "AutoStudy.getEPedalStateSupport", this.f2982g2, this.E7);
        }
        if (C(this.F7, this.f2986h2) && (this.F7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Drive_Distance_Show_Mode", this.f2986h2);
        }
        if (C(this.G7, this.f2990i2) && (this.G7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Electroni_Child_Lock_Config", this.f2990i2);
            z(R.string.auto_study_can_item_156, "BydAutoSettingDevice.getElectroniChildLockConfig", this.f2990i2, this.G7);
        }
        if (C(this.H7, this.f2994j2) && (this.H7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Window_Anti_Pinch_Config", this.f2994j2);
            z(R.string.auto_study_can_item_157, "BYDAutoBodyworkDevice.getWindowAntiPinchConfig", this.f2994j2, this.H7);
        }
        if (C(this.I7, this.f2998k2) && (this.I7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Comfortable_Speed_Up", this.f2998k2);
            z(R.string.auto_study_can_item_158, "AutoStudy.getComfortableSpeedUp", this.f2998k2, this.I7);
        }
        if (C(this.J7, this.f3002l2) && (this.J7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HSpeed_Auto_Close_State", this.f3002l2);
            z(R.string.auto_study_can_item_192, "BYDAutoSettingDevice.getHSpeedAutoCloseState", this.f3002l2, this.J7);
        }
        if (C(this.K7, this.f3006m2) && (this.K7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Runing_Close_Door", this.f3006m2);
            z(R.string.auto_study_can_item_193, "AutoStudy.getRuningCloseDoorSupport", this.f3006m2, this.K7);
        }
        if (C(this.L7, this.f3010n2) && (this.L7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Tire_Pressure_Interface_Status", this.f3010n2);
            z(R.string.auto_study_can_item_159, "AutoStudy.getTirePressureInterfaceStatus", this.f3010n2, this.L7);
        }
        if (C(this.M7, this.f3014o2) && (this.M7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Fuel_Consumption_Interface_Status", this.f3014o2);
        }
        if (C(this.N7, this.f3018p2) && (this.N7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Cumulative_Average_Energy_Consumption_Interface_Status", this.f3018p2);
        }
        if (C(this.O7, this.f3022q2) && (this.O7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Cumulative_Average_Fuel_Consumption_Interface_Status", this.f3022q2);
        }
        if (C(this.P7, this.f3026r2) && (this.P7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Cumulative_Average_Power_Consumption_Interface_Status", this.f3026r2);
        }
        if (C(this.Q7, this.f3030s2) && (this.Q7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "The_Last_50KM_Average_Energy_Consumption_Interface_State", this.f3030s2);
        }
        if (C(this.R7, this.f3034t2) && (this.R7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "The_Last_50KM_Average_Fuel_Consumption_Interface_State", this.f3034t2);
        }
        if (C(this.S7, this.f3038u2) && (this.S7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "The_Last_50KM_Average_Power_Consumption_Interface_State", this.f3038u2);
        }
        if (C(this.T7, this.f3042v2) && (this.T7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Instantaneous_Fuel_Consumption_Interface_Status", this.f3042v2);
        }
        if (C(this.U7, this.f3046w2) && (this.U7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "External_Charging_Power_Interface_Status", this.f3046w2);
        }
        if (C(this.V7, this.f3050x2) && (this.V7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Energy_Flow_Diagram_Interface_Status", this.f3050x2);
        }
        if (C(this.W7, this.f3054y2) && (this.W7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Driving_Range_Interface_Status_For_Fuel", this.f3054y2);
        }
        if (C(this.X7, this.f3058z2) && (this.X7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Average_Speed_Interface_Status", this.f3058z2);
        }
        if (C(this.Y7, this.A2) && (this.Y7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Drive_Time_Interface_Status_For_Single_Trip", this.A2);
        }
        if (C(this.Z7, this.B2) && (this.Z7 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ADAS_System_State", this.B2);
            z(R.string.auto_study_can_item_173, "AutoStudy.getADASSystemState", this.B2, this.Z7);
        }
        if (C(this.a8, this.C2) && (this.a8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Smart_Charging_PHEV", this.C2);
            z(R.string.auto_study_can_item_174, "AutoStudy.getSupportSmartChargingPHEV", this.C2, this.a8);
        }
        if (C(this.b8, this.D2) && (this.b8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Extreme", this.D2);
        }
        if (C(this.c8, this.E2) && (this.c8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Car_Plan", this.E2);
        }
        if (C(this.d8, this.F2) && (this.d8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "No_Sense_Charge", this.F2);
        }
        if (C(this.e8, this.G2) && (this.e8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Electric_Front_Hatch_Configuration", this.G2);
        }
        if (C(this.f8, this.H2) && (this.f8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Charge_Preheat", this.H2);
        }
        if (C(this.g8, this.I2) && (this.g8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DMS_Assemble_Status", this.I2);
        }
        if (C(this.h8, this.J2) && (this.h8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Driving_Voice_Broadcast_SwitchConfig", this.J2);
        }
        if (C(this.i8, this.K2) && (this.i8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Rest_Mode_Configuration", this.K2);
        }
        if (C(this.j8, this.L2) && (this.j8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HUD_Mode_Configuration", this.L2);
        }
        if (C(this.k8, this.M2) && (this.k8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Intelligent_Screen_Casting_Switch", this.M2);
        }
        if (C(this.l8, this.N2) && (this.l8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FRElectric_Air_Outlet", this.N2);
        }
        if (C(this.m8, this.O2) && (this.m8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Left_Rear_Massage_Chair_Configuration", this.O2);
        }
        if (C(this.n8, this.P2) && (this.n8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Right_Rear_Massage_Chair_Configuration", this.P2);
        }
        if (C(this.o8, this.Q2) && (this.o8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Electronic_Parking_Brake", this.Q2);
        }
        if (C(this.p8, this.R2) && (this.p8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Trunk_Configutarion", this.R2);
        }
        if (C(this.q8, this.S2) && (this.q8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Panoramic_Sunroof_Configutarion", this.S2);
        }
        if (C(this.r8, this.f3051y) && (this.r8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "PreSale_Mode_Config", this.f3051y);
        }
        if (C(this.s8, this.T2) && (this.s8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "Amplifier_Configuration", this.T2);
        }
        if (C(this.u8, this.V2) && (this.u8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "PAB_Switch_State", this.V2);
        }
        if (C(this.v8, this.W2) && (this.v8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FrontLightDimmingCtrlConfigBy3AC", this.W2);
        }
        if (C(this.w8, this.X2) && (this.w8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FrontLightDimmingCtrlConfigBy3AD", this.X2);
        }
        if (C(this.x8, this.Y2) && (this.x8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "TSRLitSpdChgSwitchState", this.Y2);
        }
        if (C(this.y8, this.Z2) && (this.y8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "CVCConfig", this.Z2);
        }
        if (C(this.z8, this.a3) && (this.z8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DiSusShowStateByCan", this.a3);
        }
        if (C(this.A8, this.b3) && (this.A8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DiSusShowStateByCanFD", this.b3);
        }
        if (C(this.B8, this.c3) && (this.B8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DyHeightToggleState", this.c3);
        }
        if (C(this.C8, this.d3) && (this.C8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "WelcomeState", this.d3);
        }
        if (C(this.D8, this.e3) && (this.D8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "TailBoxTakeState", this.e3);
        }
        if (C(this.E8, this.f3) && (this.E8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "CampingBlanceState", this.f3);
        }
        if (C(this.F8, this.g3) && (this.F8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DampState", this.g3);
        }
        if (C(this.G8, this.h3) && (this.G8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "LiftModeState", this.h3);
        }
        if (C(this.H8, this.i3) && (this.H8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FdDyHeightToggleState", this.i3);
        }
        if (C(this.I8, this.j3) && (this.I8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FdWelcomeState", this.j3);
        }
        if (C(this.J8, this.k3) && (this.J8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FdTailBoxTakeState", this.k3);
        }
        if (C(this.K8, this.l3) && (this.K8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FdCampingBlanceState", this.l3);
        }
        if (C(this.L8, this.m3) && (this.L8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FdDampState", this.m3);
        }
        if (C(this.M8, this.n3) && (this.M8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FdLiftModeState", this.n3);
        }
        if (C(this.N8, this.o3) && (this.N8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "WarningAuxiliarySwitchSetting", this.o3);
        }
        if (C(this.O8, this.p3) && (this.O8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "WelcomeCeremonySwitchSetting", this.p3);
        }
        if (C(this.P8, this.q3) && (this.P8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "AmbientModeSwitchSettings", this.q3);
        }
        if (C(this.Q8, this.r3) && (this.Q8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "StarTopSwitchSetting", this.r3);
        }
        if (C(this.R8, this.s3) && (this.R8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "ComfortableParkingBy37F", this.s3);
        }
        if (C(this.S8, this.t3) && (this.S8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "LowSpeedEmergencyBrakingConfig", this.t3);
        }
        if (C(this.T8, this.u3) && (this.T8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SideDistanceWarningConfig", this.u3);
        }
        if (C(this.U8, this.v3) && (this.U8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "V2XConfig", this.v3);
        }
        if (C(this.V8, this.w3) && (this.V8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "TrailerModeConfig", this.w3);
        }
        if (C(this.W8, this.x3) && (this.W8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "FoldDownTheSecondRowOfSeatsConfig", this.x3);
        }
        if (C(this.X8, this.y3) && (this.X8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "LevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow", this.y3);
        }
        if (C(this.Y8, this.z3) && (this.Y8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "BackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow", this.z3);
        }
        if (C(this.Z8, this.A3) && (this.Z8 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "LegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow", this.A3);
        }
        if (C(this.a9, this.B3) && (this.a9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "PotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow", this.B3);
        }
        if (C(this.b9, this.C3) && (this.b9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow", this.C3);
        }
        if (C(this.c9, this.D3) && (this.c9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow", this.D3);
        }
        if (C(this.d9, this.E3) && (this.d9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow", this.E3);
        }
        if (C(this.e9, this.F3) && (this.e9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "OneButtonDownConfigurationOfTheThirdRowLeftRearSeat", this.F3);
        }
        if (C(this.f9, this.G3) && (this.f9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "OneButtonDownSwitchDisplayForTheThirdRowOfSeats", this.G3);
        }
        if (C(this.g9, this.H3) && (this.g9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "AllRearSeatOneButtonDownSwitchDisplayConfig", this.H3);
        }
        if (C(this.h9, this.I3) && (this.h9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "LevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow", this.I3);
        }
        if (C(this.i9, this.J3) && (this.i9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "BackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow", this.J3);
        }
        if (C(this.j9, this.K3) && (this.j9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "LegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow", this.K3);
        }
        if (C(this.k9, this.L3) && (this.k9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "PotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow", this.L3);
        }
        if (C(this.l9, this.M3) && (this.l9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow", this.M3);
        }
        if (C(this.m9, this.N3) && (this.m9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow", this.N3);
        }
        if (C(this.n9, this.O3) && (this.n9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "HeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow", this.O3);
        }
        if (C(this.o9, this.P3) && (this.o9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "OneButtonDownConfigurationOfTheThirdRowRightRearSeat", this.P3);
        }
        if (C(this.p9, this.Q3) && (this.p9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "IntelligentSpeedLimitMapVersionDisplay", this.Q3);
        }
        if (C(this.q9, this.R3) && (this.q9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DriverSeatHotStoneMassageConfigurationByECU", this.R3);
        }
        if (C(this.r9, this.S3) && (this.r9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "PassengerSeatHotStoneMassageConfigurationByECU", this.S3);
        }
        if (C(this.s9, this.T3) && (this.s9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "LeftRearSeatHotStoneMassageConfigurationByECU", this.T3);
            z(R.string.auto_study_can_item_247, "Autostudy.getLeftRearSeatHotStoneMassageConfigurationByECU()", this.T3, this.s9);
        }
        if (C(this.t9, this.U3) && (this.t9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RightRearSeatHotStoneMassageConfigurationByECU", this.U3);
        }
        if (C(this.u9, this.V3) && (this.u9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SupportAVH", this.V3);
        }
        if (C(this.v9, this.W3) && (this.v9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SupportRearControlPanelSign", this.W3);
        }
        if (C(this.w9, this.X3) && (this.w9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SupportDimmingCanopyConfiguration", this.X3);
        }
        if (C(this.x9, this.Y3) && (this.x9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SupportAlarmMode", this.Y3);
        }
        if (C(this.y9, this.Z3) && (this.y9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "BSISSupport", this.Z3);
        }
        if (C(this.z9, this.a4) && (this.z9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "MOISSupport", this.a4);
        }
        if (C(this.A9, this.b4) && (this.A9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DynamicSuspensionModeByCan", this.b4);
        }
        if (C(this.B9, this.c4) && (this.B9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DynamicSuspensionModeByCanFD", this.c4);
        }
        if (C(this.C9, this.d4) && (this.C9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SupportManualModel", this.d4);
        }
        if (C(this.D9, this.e4) && (this.D9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "TailgateTypeMarking", this.e4);
        }
        if (C(this.E9, this.f4) && (this.E9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SupportTSRFlow", this.f4);
        }
        if (C(this.F9, this.g4) && (this.F9 == 0 || this.Ha)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SupportDoubleConfirm", this.g4);
        }
        if (C(this.G9, this.h4) && ((this.G9 == 0 || this.Ha) && this.ga.hasMessage(132) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelDriverSeatHeating", this.h4);
        }
        if (C(this.H9, this.i4) && ((this.H9 == 0 || this.Ha) && this.ga.hasMessage(135) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelPassengerSeatHeating", this.i4);
        }
        if (C(this.I9, this.j4) && ((this.I9 == 0 || this.Ha) && this.ga.hasMessage(123) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelRearLeftSeatHeating", this.j4);
        }
        if (C(this.J9, this.k4) && ((this.J9 == 0 || this.Ha) && this.ga.hasMessage(123) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelRearRightSeatHeating", this.k4);
        }
        if (C(this.K9, this.l4) && ((this.K9 == 0 || this.Ha) && this.ga.hasMessage(132) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelDriveSeatVentilatingState", this.l4);
        }
        if (C(this.L9, this.m4) && ((this.L9 == 0 || this.Ha) && this.ga.hasMessage(135) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelPassengerSeatVentilatingState", this.m4);
        }
        if (C(this.M9, this.n4) && ((this.M9 == 0 || this.Ha) && this.ga.hasMessage(123) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelRearLeftSeatVentilatingState", this.n4);
        }
        if (C(this.N9, this.o4) && ((this.N9 == 0 || this.Ha) && this.ga.hasMessage(123) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "5LevelRearRightSeatVentilatingState", this.o4);
        }
        if (C(this.O9, this.p4) && ((this.O9 == 0 || this.Ha) && this.ga.hasMessage(23) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DriverSeatVentilatingState", this.p4);
        }
        if (C(this.P9, this.q4) && ((this.P9 == 0 || this.Ha) && this.ga.hasMessage(23) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "PassengerSeatVentilatingState", this.q4);
        }
        if (C(this.Q9, this.r4) && ((this.Q9 == 0 || this.Ha) && this.ga.hasMessage(65) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearLeftSeatVentilatingState", this.r4);
        }
        if (C(this.R9, this.s4) && ((this.R9 == 0 || this.Ha) && this.ga.hasMessage(65) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearRightSeatVentilatingState", this.s4);
        }
        if (C(this.S9, this.t4) && ((this.S9 == 0 || this.Ha) && this.ga.hasMessage(170) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearLeftSeatVentilatingState2", this.t4);
        }
        if (C(this.T9, this.u4) && ((this.T9 == 0 || this.Ha) && this.ga.hasMessage(172) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearRightSeatVentilatingState2", this.u4);
        }
        if (C(this.U9, this.v4) && ((this.U9 == 0 || this.Ha) && this.ga.hasMessage(62) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SeatVentilatingNotL1PState", this.v4);
        }
        if (C(this.V9, this.w4) && ((this.V9 == 0 || this.Ha) && this.ga.hasMessage(23) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "DriverSeatHeatingState", this.w4);
        }
        if (C(this.W9, this.x4) && ((this.W9 == 0 || this.Ha) && this.ga.hasMessage(23) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "PassengerSeatHeatingState", this.x4);
            z(R.string.auto_study_can_item_286, "Setting.getSeatHeatingState(BYDAutoSettingDevice.PASSENGER_SEAT)", this.x4, this.W9);
        }
        if (C(this.X9, this.y4) && ((this.X9 == 0 || this.Ha) && this.ga.hasMessage(65) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearLeftSeatHeatingState", this.y4);
        }
        if (C(this.Y9, this.z4) && ((this.Y9 == 0 || this.Ha) && this.ga.hasMessage(65) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearRightSeatHeatingState", this.z4);
        }
        if (C(this.Z9, this.A4) && ((this.Z9 == 0 || this.Ha) && this.ga.hasMessage(170) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearLeftSeatHeatingState2", this.A4);
        }
        if (C(this.aa, this.B4) && ((this.aa == 0 || this.Ha) && this.ga.hasMessage(172) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "RearRightSeatHeatingState2", this.B4);
        }
        if (C(this.ba, this.C4) && ((this.ba == 0 || this.Ha) && this.ga.hasMessage(62) == 1)) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, "SeatHeatingNotL1PState", this.C4);
        }
        int i5 = this.jb;
        if (i5 != this.ib) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, this.gb, i5);
            BydLogHelper.logE("CarSettings put mAncCodeByMCU is " + this.jb);
        }
        z(R.string.auto_study_can_item_292, "Anc Parameter is ", this.jb, this.ib);
        int i6 = this.lb;
        if (i6 != this.kb) {
            this.Ia = true;
            CarSettings.UserTableData.putInt(this.xa, this.hb, i6);
            BydLogHelper.logE("CarSettings put mAncConfigByMCU is " + this.lb);
        }
        z(R.string.auto_study_can_item_293, "Anc Config is ", this.lb, this.kb);
    }

    public final int J(int i3) {
        if (i3 != 1) {
            return i3 == 0 ? 0 : 404;
        }
        return 1;
    }

    public final byte K(byte b3, int i3, int i4) {
        return (byte) (b3 | ((i3 | 0) << i4));
    }

    public final int M() {
        String str = SystemProperties.get("vehicle.config.cam_sort");
        if (str == null || str == "") {
            this.E = 0;
            this.f2991j = 0;
            this.f3015p = 0;
            return 255;
        }
        if (str.contains("rear")) {
            this.E = 1;
            this.f2991j = 1;
        } else {
            this.E = 0;
            if (str.contains("front")) {
                this.f2991j = 1;
            }
        }
        if (str.contains("dms")) {
            this.f3015p = 1;
        } else {
            this.f3015p = 0;
        }
        if (str.contains("pano_h")) {
            return 1;
        }
        if (str.contains("pano_l")) {
            return 4;
        }
        return str.contains("rvs") ? str.contains("rf") ? 2 : 3 : str.contains("apa") ? 5 : 255;
    }

    public void N() {
        WindowManager windowManager;
        if (!this.Na || (windowManager = this.La) == null) {
            return;
        }
        windowManager.removeView(this.Oa);
        this.Na = false;
    }

    public final void O(Context context) {
        Intent intent = new Intent("com.byd.card.close.FloatCard");
        intent.putExtra("package", "com.byd.autoservice");
        context.sendBroadcast(intent);
        this.La = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2003);
        this.Ma = layoutParams;
        layoutParams.gravity = 17;
        layoutParams.format = 1;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.flags = 1024;
        layoutParams.systemUiVisibility = 5894;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.show_reset_dialog, (ViewGroup) null);
        this.Oa = viewInflate;
        this.Ba = (TextView) viewInflate.findViewById(R.id.restart_alert_content);
        this.za = (TextView) this.Oa.findViewById(R.id.restart_time_key);
        Button button = (Button) this.Oa.findViewById(R.id.restart_Confirm);
        this.za.setText(this.ya.getString(R.string.restart_time) + " : " + this.Fa);
        this.Da.sendEmptyMessageDelayed(4, 1000L);
        BydLogHelper.logD("AutoStudyManager", "MSG_UPDATE_DIALOG11111 mCountdownTimeNumber / " + this.Fa);
        button.setOnClickListener(new e());
        Y();
    }

    public final boolean P(int i3) {
        switch (i3) {
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 187:
            case 196:
            case 208:
            case 224:
                return true;
            default:
                return false;
        }
    }

    public final boolean Q(int i3) {
        switch (i3) {
            case 15:
            case 31:
            case 35:
            case 43:
            case 46:
            case 47:
            case 48:
            case 50:
            case 52:
            case 56:
            case 57:
            case 61:
            case 63:
            case 65:
            case 67:
            case 71:
            case 74:
            case 83:
            case 85:
            case 88:
            case 89:
            case 90:
            case 102:
            case 111:
            case 113:
            case 115:
            case 121:
            case 129:
            case 136:
            case 140:
            case 141:
            case 143:
            case 180:
            case 223:
                return true;
            default:
                return false;
        }
    }

    public final boolean R(int i3) {
        BydLogHelper.logE("AutoStudyManager", "isValidCarType carID = " + i3);
        if (i3 == 121 || i3 == 123 || i3 == 143 || i3 == 147 || i3 == 180 || i3 == 182 || i3 == 187 || i3 == 196 || i3 == 208 || i3 == 128 || i3 == 129 || i3 == 140 || i3 == 141 || i3 == 223 || i3 == 224) {
            return true;
        }
        switch (i3) {
            case 135:
            case 136:
            case 137:
            case 138:
                return true;
            default:
                return false;
        }
    }

    public void S(Configuration configuration) {
        BydLogHelper.logD("Configuration newConfig / " + configuration);
        this.wa = configuration;
        f fVar = this.Da;
        if (fVar != null) {
            fVar.removeMessages(2);
            this.Da.sendEmptyMessage(2);
        }
    }

    public final void T() {
        int length;
        this.ib = CarSettings.UserTableData.getInt(this.xa, this.gb);
        this.kb = CarSettings.UserTableData.getInt(this.xa, this.hb);
        BydLogHelper.logE("getAncDate mAncCodeByDB = " + this.ib);
        BydLogHelper.logE("getAncDate mAncConfigByDB = " + this.kb);
        this.T2 = this.sa.getAmplifierConfiguration();
        this.f2983h = this.ga.getAutoType();
        byte[] vcuEncoded = this.sa.getVcuEncoded();
        this.ab = vcuEncoded;
        if (vcuEncoded == null) {
            BydLogHelper.logE("vcuCode vcuCodeLength is null");
            length = 0;
        } else {
            length = vcuEncoded.length;
            BydLogHelper.logE("vcuCode vcuCodeLength = " + length);
            if (length >= 3) {
                BydLogHelper.logE("vcuCode vcuCode[0] = " + ((int) this.ab[0]) + ", vcuCode[1] = " + ((int) this.ab[1]) + ", vcuCode[2] = " + ((int) this.ab[2]));
                BydLogHelper.logE("vcuCode BYTE_14 = 20, BYTE_61 = 97, BYTE_10 = 16");
            }
        }
        int i3 = this.kb;
        this.ob.clear();
        if (this.T2 == 0) {
            BydLogHelper.logE("writeConfigurationWordInfo mAmplifierConfigFromI2CByMCU = " + this.U2);
            if (this.U2 == 0) {
                Byte bValueOf = Byte.valueOf(K(this.Ya.byteValue(), 3, 0));
                this.Ya = bValueOf;
                this.ob.add(bValueOf);
                this.Da.sendEmptyMessageDelayed(7, 110L);
            } else {
                Byte bValueOf2 = Byte.valueOf(K(this.Ya.byteValue(), this.U2, 0));
                this.Ya = bValueOf2;
                this.ob.add(bValueOf2);
                this.Da.sendEmptyMessageDelayed(7, 110L);
            }
        } else {
            BydLogHelper.logE("writeConfigurationWordInfo mAmplifierConfigurationByMCU = " + this.T2);
            Byte bValueOf3 = Byte.valueOf(K(this.Ya.byteValue(), this.T2, 0));
            this.Ya = bValueOf3;
            this.ob.add(bValueOf3);
            this.Da.sendEmptyMessageDelayed(7, 110L);
        }
        int i4 = this.f2983h;
        if (i4 == 182 || i4 == 208) {
            this.jb = this.bb;
            this.mb.clear();
            this.Va = Byte.valueOf(K(this.Va.byteValue(), 255, 0));
            this.Wa = Byte.valueOf(K(this.Wa.byteValue(), 255, 0));
            this.mb.add(this.Va);
            this.mb.add(this.Wa);
            this.nb.clear();
            BydLogHelper.logE("setAncDate mAmplifierConfigurationByMCU = " + this.T2);
            BydLogHelper.logE("setAncDate mAmplifierConfigFromI2CByMCU = " + this.U2);
            int i5 = this.T2;
            if (i5 == 3) {
                this.lb = 1;
                Byte bValueOf4 = Byte.valueOf(K(this.Za.byteValue(), 1, 0));
                this.Za = bValueOf4;
                this.nb.add(bValueOf4);
                this.Da.sendEmptyMessageDelayed(9, 10L);
            } else if (i5 == 0 && this.U2 == 1) {
                this.lb = 2;
                Byte bValueOf5 = Byte.valueOf(K(this.Za.byteValue(), 2, 0));
                this.Za = bValueOf5;
                this.nb.add(bValueOf5);
                this.Da.sendEmptyMessageDelayed(9, 10L);
            } else {
                int i6 = this.U2;
                if (i6 == 0 && i6 == 0) {
                    this.lb = 1;
                    Byte bValueOf6 = Byte.valueOf(K(this.Za.byteValue(), 1, 0));
                    this.Za = bValueOf6;
                    this.nb.add(bValueOf6);
                    this.Da.sendEmptyMessageDelayed(9, 10L);
                }
            }
        } else if (i4 == 180) {
            this.jb = this.cb;
            this.mb.clear();
            this.Va = Byte.valueOf(K(this.Va.byteValue(), 12, 0));
            if (length >= 3) {
                byte[] bArr = this.ab;
                if (20 == bArr[0] && 97 == bArr[1] && 16 == bArr[2]) {
                    this.Wa = Byte.valueOf(K(this.Wa.byteValue(), 1, 0));
                    BydLogHelper.logE("vcuCode MCHBX");
                    this.jb = this.db;
                } else if (20 == bArr[0] && -87 == bArr[1] && 17 == bArr[2]) {
                    this.Wa = Byte.valueOf(K(this.Wa.byteValue(), 2, 0));
                    BydLogHelper.logE("vcuCode MCHCX");
                    this.jb = this.eb;
                } else if (21 == bArr[0] && 36 == bArr[1] && 16 == bArr[2]) {
                    this.Wa = Byte.valueOf(K(this.Wa.byteValue(), 1, 0));
                    BydLogHelper.logE("vcuCode MCHBG");
                    this.jb = this.db;
                } else {
                    this.Wa = Byte.valueOf(K(this.Wa.byteValue(), 255, 0));
                }
            } else {
                this.Wa = Byte.valueOf(K(this.Wa.byteValue(), 255, 0));
            }
            BydLogHelper.logE("vcuCode mByte = " + this.Va);
            BydLogHelper.logE("vcuCode mByte0 = " + this.Wa);
            this.mb.add(this.Va);
            this.mb.add(this.Wa);
            this.nb.clear();
            int i7 = this.T2;
            if (i7 == 12) {
                this.lb = 5;
                Byte bValueOf7 = Byte.valueOf(K(this.Za.byteValue(), 5, 0));
                this.Za = bValueOf7;
                this.nb.add(bValueOf7);
                this.Da.sendEmptyMessageDelayed(9, 10L);
            } else if (i7 == 0 && this.U2 == 1) {
                this.lb = 4;
                Byte bValueOf8 = Byte.valueOf(K(this.Za.byteValue(), 4, 0));
                this.Za = bValueOf8;
                this.nb.add(bValueOf8);
                this.Da.sendEmptyMessageDelayed(9, 10L);
            } else {
                int i8 = this.U2;
                if (i8 == 0 && i8 == 0) {
                    this.lb = 1;
                    Byte bValueOf9 = Byte.valueOf(K(this.Za.byteValue(), 1, 0));
                    this.Za = bValueOf9;
                    this.nb.add(bValueOf9);
                    this.Da.sendEmptyMessageDelayed(9, 10L);
                }
            }
        } else {
            this.jb = this.bb;
            this.mb.clear();
            this.Va = Byte.valueOf(K(this.Va.byteValue(), 255, 0));
            this.Wa = Byte.valueOf(K(this.Wa.byteValue(), 255, 0));
            this.mb.add(this.Va);
            this.mb.add(this.Wa);
            this.nb.clear();
            Byte bValueOf10 = Byte.valueOf(K(this.Za.byteValue(), 1, 0));
            this.Za = bValueOf10;
            this.nb.add(bValueOf10);
            this.lb = 1;
            this.Da.sendEmptyMessageDelayed(9, 10L);
        }
        BydLogHelper.logE("setAncDate mAncCodeByMCU = " + this.jb + ", mAncCodeByDB = " + this.ib);
        BydLogHelper.logE("setAncDate mAncConfigByMCU = " + this.lb + ", mAncConfigByDB = " + this.kb);
        Settings.System.putInt(this.xa, "bosch_audio_config", this.lb);
        this.Da.sendEmptyMessageDelayed(8, 60L);
    }

    public final void U() {
        BydLogHelper.logE("setCarModeConfiguration hasMessage(BYDAutoBodyworkDevice.HAS_0X040D00) =" + this.ga.hasMessage(1));
        if (this.ga.hasMessage(1) == 1) {
            BydLogHelper.logE("setCarModeConfiguration persist.sys.AutoType = " + this.f2983h);
            SystemProperties.set("persist.sys.AutoType", String.valueOf(this.f2983h));
        }
        X(this.f2983h);
        V(this.f2983h);
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public final void V(int r9) {
        /*
            Method dump skipped, instruction units count: 434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.a.V(int):void");
    }

    public final void W() {
        String str = SystemProperties.get("persist.sys.AutoType");
        String str2 = SystemProperties.get("persist.sys.energytype");
        int i3 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.POWER_TYPE);
        int i4 = CarSettings.UserTableData.getInt(this.xa, CarSettings.UserTableData.MODEL_VALUE);
        if (this.f2983h != 0 && i4 != Integer.MIN_VALUE && !String.valueOf(i4).equals(str)) {
            BydLogHelper.logE("executeAutomaticLearning modelTypeDB = " + i4 + " modelTypeProp = " + str);
            if (this.ga.hasMessage(1) == 1) {
                BydLogHelper.logE("set persist.sys.AutoType = " + i4);
                SystemProperties.set("persist.sys.AutoType", String.valueOf(i4));
            }
            BydLogHelper.logD("executeAutomaticLearning after setProp modelTypeProp = " + SystemProperties.get("persist.sys.AutoType"));
        }
        if (this.f2983h != 0 && i3 != Integer.MIN_VALUE && !String.valueOf(i3).equals(str2)) {
            BydLogHelper.logD("executeAutomaticLearning powerTypeDB = " + i3 + " powerTypeProp = " + str2);
            SystemProperties.set("persist.sys.energytype", String.valueOf(i3));
            StringBuilder sb = new StringBuilder();
            sb.append("executeAutomaticLearning after setProp powerTypeProp = ");
            sb.append(SystemProperties.get("persist.sys.energytype"));
            BydLogHelper.logD(sb.toString());
        }
        int i5 = this.f2983h;
        if (i5 == this.I4 && R(i5)) {
            U();
        }
    }

    public final void X(int i3) {
        if (i3 == 101 || i3 == 102) {
            this.ya.getString(R.string.wifi_tether_configure_ssid_qin_plus_default);
            return;
        }
        if (i3 == 110 || i3 == 111) {
            this.ya.getString(R.string.wifi_tether_configure_ssid_song_pro_default);
            return;
        }
        if (i3 == 11) {
            this.ya.getString(R.string.wifi_tether_configure_ssid_song_default);
            return;
        }
        if (i3 != 13) {
            if (i3 != 15) {
                if (i3 == 30) {
                    this.ya.getString(R.string.wifi_tether_configure_ssid_yuan_default);
                    return;
                }
                if (i3 != 63) {
                    if (i3 == 113) {
                        this.ya.getString(R.string.wifi_tether_configure_ssid_song_max_default);
                        return;
                    }
                    if (i3 != 115) {
                        if (i3 != 121) {
                            if (i3 == 147) {
                                this.ya.getString(R.string.wifi_tether_configure_ssid_song_plus_default);
                                return;
                            }
                            if (i3 == 140) {
                                this.ya.getString(R.string.wifi_tether_configure_ssid_f7_default);
                                return;
                            }
                            if (i3 == 141) {
                                this.ya.getString(R.string.wifi_tether_configure_ssid_destroyer_default);
                                return;
                            }
                            switch (i3) {
                                case 49:
                                    this.ya.getString(R.string.wifi_tether_configure_ssid_haea_default);
                                    break;
                                case 50:
                                case 51:
                                    break;
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 58:
                                case 59:
                                    break;
                                case 57:
                                case 60:
                                    this.ya.getString(R.string.wifi_tether_configure_ssid_cn_tang_max_default);
                                    break;
                                default:
                                    switch (i3) {
                                        case 42:
                                        case 43:
                                        case 44:
                                        case 45:
                                        case 46:
                                        case 47:
                                            break;
                                        default:
                                            switch (i3) {
                                                case 66:
                                                case 67:
                                                case 68:
                                                case 70:
                                                case 71:
                                                case 72:
                                                case 73:
                                                case 74:
                                                    break;
                                                case 69:
                                                    this.ya.getString(R.string.wifi_tether_configure_ssid_yuan_pro_default);
                                                    break;
                                                default:
                                                    switch (i3) {
                                                        case 81:
                                                        case 82:
                                                        case 83:
                                                        case 84:
                                                        case 85:
                                                            break;
                                                        default:
                                                            switch (i3) {
                                                                case 88:
                                                                case 89:
                                                                    break;
                                                                case 90:
                                                                    this.ya.getString(R.string.wifi_tether_configure_ssid_frigate03_default);
                                                                    break;
                                                                default:
                                                                    switch (i3) {
                                                                        case 92:
                                                                        case 93:
                                                                        case 94:
                                                                        case 97:
                                                                        case 98:
                                                                        case 99:
                                                                            break;
                                                                        case 95:
                                                                            this.ya.getString(R.string.wifi_tether_configure_ssid_e1_default);
                                                                            break;
                                                                        case 96:
                                                                            if (this.f2963c == 6) {
                                                                                this.ya.getString(R.string.wifi_tether_configure_ssid_el_elec_default);
                                                                            } else {
                                                                                this.ya.getString(R.string.wifi_tether_configure_ssid_el_ela_default);
                                                                            }
                                                                            break;
                                                                        default:
                                                                            switch (i3) {
                                                                                case 128:
                                                                                    this.ya.getString(R.string.wifi_tether_configure_ssid_yuan_plus_default);
                                                                                    break;
                                                                                case 129:
                                                                                    this.ya.getString(R.string.wifi_tether_configure_ssid_frigate07_default);
                                                                                    break;
                                                                                case 130:
                                                                                    this.ya.getString(R.string.wifi_tether_configure_ssid_song_l_default);
                                                                                    break;
                                                                                default:
                                                                                    switch (i3) {
                                                                                        case 135:
                                                                                        case 136:
                                                                                        case 137:
                                                                                            break;
                                                                                        case 138:
                                                                                            this.ya.getString(R.string.wifi_tether_configure_ssid_haibao_default);
                                                                                            break;
                                                                                        default:
                                                                                            this.ya.getString(R.string.wifi_tether_configure_ssid_byd_default);
                                                                                            break;
                                                                                    }
                                                                                    break;
                                                                            }
                                                                            break;
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            return;
                        }
                    }
                    this.ya.getString(R.string.wifi_tether_configure_ssid_cn_tang_default);
                    return;
                }
            }
            this.ya.getString(R.string.wifi_tether_configure_ssid_han_default);
            return;
        }
        this.ya.getString(R.string.wifi_tether_configure_ssid_qin_default);
    }

    public void Y() {
        WindowManager windowManager;
        if (this.Na || (windowManager = this.La) == null) {
            return;
        }
        windowManager.addView(this.Oa, this.Ma);
        this.Na = true;
    }

    public void Z() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = this.Ca.getString("study_auto_content", "");
        SharedPreferences.Editor editorEdit = this.Ca.edit();
        if (!string.equals("")) {
            String[] strArrSplit = string.split("#");
            BydLogHelper.logE("writeSharePreference: itemTimes.length = " + strArrSplit.length);
            if (strArrSplit.length >= 7) {
                editorEdit.clear();
                editorEdit.apply();
            } else {
                for (int i3 = strArrSplit.length == 5 ? 1 : 0; i3 < strArrSplit.length; i3++) {
                    stringBuffer.append(strArrSplit[i3]);
                    stringBuffer.append("#");
                }
            }
        }
        Calendar calendar = Calendar.getInstance();
        stringBuffer.append(((Object) DateFormat.format("yyyy-MM-dd", calendar.getTime())) + " " + DateFormat.getTimeFormat(this.ya).format(calendar.getTime()) + ";");
        this.Ja.insert(0, (CharSequence) stringBuffer);
        editorEdit.putString("study_auto_content", this.Ja.toString());
        editorEdit.apply();
        stringBuffer.setLength(0);
        BydLogHelper.logE("writeSharePreference: tempBuffer.length() = " + stringBuffer.length());
    }

    public final void z(int i3, String str, int i4, int i5) {
        this.Ja.append(this.ya.getString(i3) + "->" + str + ">" + i4 + " DB>" + i5 + ";");
    }
}
