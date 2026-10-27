package f1;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.WindowConfiguration;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.key.AbsBYDAutoKeyListener;
import android.hardware.bydauto.key.BYDAutoKeyDevice;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.xcall.BYDAutoXCallDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.LauncherManager;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.byd.autoservice.R;
import com.byd.autoservice.application.AutoApplication;
import com.byd.autoservice.function.AutoReceiver;
import com.byd.info.BtCallInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.window.BnWindowHelper;
import com.bydux.api.common.views.window.ToastUtils;
import com.ts.avm.sdk.AvmManager;
import com.ts.car.core.ServiceProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import o1.b;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p1.a;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.INavigationDataListener;
import ts.car.service.carplay.ICallStateListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.CallStateInfo;

/* JADX INFO: compiled from: HardKeyEvent.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements AvmManager.c, AvmManager.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f2816a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f2817b = 500;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b f2818c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f2819d = 15;
    public final int A1;
    public boolean B1;
    public Boolean C0;
    public boolean C1;
    public Boolean D0;
    public ContentResolver D1;
    public String E0;
    public s E1;
    public String F0;
    public p1.a F1;
    public final int G0;
    public List<String> G1;
    public final int H0;
    public final List<String> H1;
    public BYDAutoInstrumentDevice I0;
    public int I1;
    public String J0;
    public LauncherManager J1;
    public BYDAutoGearboxDevice K0;
    public AbsBYDAutoKeyListener K1;
    public BYDAutoBodyworkDevice L0;

    @SuppressLint({"HandlerLeak"})
    public final Handler L1;
    public BYDAutoAudioDevice M0;
    public final IServiceConnectionListener M1;
    public BYDAutoDiPilotDevice N0;
    public ICarPlayStateListener N1;
    public Context O;
    public final int O0;
    public final ICallStateListener O1;
    public final int P0;
    public ICarPlayAppListener P1;
    public boolean Q0;
    public final IServiceConnectionListener Q1;
    public final int R0;
    public IAndroidAutoSessionStatusListener R1;
    public final int S0;
    public IAndroidAutoAppListener S1;
    public final int T0;
    public INavigationDataListener T1;
    public final int U0;
    public final u2.a U1;
    public final int V0;
    public final AbsBYDAutoGearboxListener V1;
    public final int W0;
    public final AbsBYDAutoBodyworkListener W1;
    public final int X0;
    public final AbsBYDAutoAudioListener X1;
    public final int Y0;
    public final k1.a Y1;
    public final int Z0;
    public int Z1;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public j1.a f2820a0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public i1.a f2821a1;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public j1.b f2822b0;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public BYDAutoXCallDevice f2823b1;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public n1.b f2825c1;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public e1.b f2826d0;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public BYDAutoTestDevice f2827d1;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public BYDAutoSettingDevice f2829e0;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public l1.a f2830e1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BYDAutoKeyDevice f2831f;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public final String f2833f1;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public final String f2836g1;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public final String f2839h1;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    public boolean f2842i1;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public AutoReceiver f2845j1;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public final String f2848k1;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public String f2851l1;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public final String f2854m1;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public AbsBYDAutoTestListener f2857n1;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    public ArrayList<Byte> f2860o1;

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    public int f2863p1;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    public List<HashMap> f2866q1;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    public WindowManager f2869r1;

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    public WindowManager.LayoutParams f2872s1;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    public boolean f2875t1;

    /* JADX INFO: renamed from: u1, reason: collision with root package name */
    public View f2878u1;

    /* JADX INFO: renamed from: v1, reason: collision with root package name */
    public int f2881v1;

    /* JADX INFO: renamed from: w1, reason: collision with root package name */
    public int f2884w1;

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    public int f2887x1;

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    public boolean f2890y1;

    /* JADX INFO: renamed from: z1, reason: collision with root package name */
    public final int f2893z1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2828e = "HardKeyEvent";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2834g = 1001;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2837h = 1002;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2840i = 1003;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f2843j = 1004;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f2846k = 1005;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2849l = 1006;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2852m = 1007;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f2855n = 1008;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2858o = 1009;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f2861p = 1010;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f2864q = 1011;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f2867r = 1012;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f2870s = 1013;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f2873t = 1014;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f2876u = 1015;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f2879v = 1016;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f2882w = 1;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f2885x = 2;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f2888y = 7;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f2891z = 8;
    public final int A = 10;
    public final int B = 11;
    public final int C = 12;
    public final int D = 13;
    public final int E = 17;
    public final int F = 18;
    public final int G = 20;
    public final int H = 501;
    public final int I = 502;
    public final int J = 503;
    public final int K = 504;
    public int L = 0;
    public final int M = 0;
    public final int N = 2;
    public AvmManager P = null;
    public final int Q = 2;
    public final int R = 3;
    public final int S = 3;
    public final int T = 4;
    public final int U = 1;
    public final int V = 2;
    public final int W = 5;
    public final int X = 6;
    public final int Y = 1001;
    public int Z = 3;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f2824c0 = 0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final int f2832f0 = 1;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final int f2835g0 = 2;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f2838h0 = 0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final String f2841i0 = "com.byd.avm";

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final String f2844j0 = "com.byd.avm.MainActivity";

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final String f2847k0 = "bydavmmode";

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final String f2850l0 = "com.byd.hvac";

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final String f2853m0 = "com.byd.hvac.view.MainActivity";

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final String f2856n0 = "byd.scene.mode.state";

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public ICarPlayAppManager f2859o0 = null;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public int f2862p0 = 4;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public boolean f2865q0 = false;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public boolean f2868r0 = false;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public IAndroidAutoAppManager f2871s0 = null;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f2874t0 = 1;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f2877u0 = 0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public final String f2880v0 = "com.byd.action.key.voice";

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public final String f2883w0 = "com.byd.vrassistant";

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public final String f2886x0 = "com.byd.vrassistant.receiver.HardKeyReceiver";

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int[] f2889y0 = null;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public boolean f2892z0 = true;
    public int A0 = 1;
    public int B0 = 0;

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class a implements IServiceConnectionListener {
        public a() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            if (b.this.f2871s0 != null) {
                try {
                    b.this.f2871s0.registerAndroidAutoSessionStatusListener(b.this.R1);
                    b.this.f2871s0.registerAndroidAutoAppListener(b.this.S1);
                } catch (RemoteException unused) {
                }
            }
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
        }
    }

    /* JADX INFO: renamed from: f1.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class C0011b implements IAndroidAutoSessionStatusListener {
        public C0011b() {
        }

        public void onProjectionAppStatesChanged(int i3, boolean z3) {
            BydLogHelper.logD("HardKeyEvent", "onProjectionAppStatesChanged: appType=" + i3 + " enabled=" + z3);
            if (i3 == 0) {
                b.this.C1 = z3;
            }
        }

        public void onSessionStatusChanged(int i3, int i4, String str) {
            BydLogHelper.logD("HardKeyEvent", "onSessionStatusChanged: status=" + i3 + " errorCode=" + i4 + " message=" + str);
            b.this.f2874t0 = i3;
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class c implements IAndroidAutoAppListener {
        public c() {
        }

        public void onVideoFocusStatusChanged(int i3) {
            BydLogHelper.logD("HardKeyEvent", "onVideoFocusStatusChanged: videoFocusStatus=" + i3);
            b.this.f2877u0 = i3;
            if (b.this.f2877u0 == 1) {
                b bVar = b.this;
                if (bVar.h1(bVar.O)) {
                    return;
                }
                b.this.f2829e0.setScreenRotation(1);
            }
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class d implements INavigationDataListener {
        public d() {
        }

        public void onNavigationStatusChanged(int i3) {
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class e implements u2.a {
        public e() {
        }

        @Override // u2.a
        public void a() {
            b.this.J0();
            b.this.L0();
        }

        @Override // u2.a
        public void b() {
            BydLogHelper.logD("HardKeyEvent", "avm onServiceDisConnected: ");
        }

        @Override // u2.a
        public void onError(int i3, String str) {
            BydLogHelper.logD("HardKeyEvent", "onError: ");
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class f extends AbsBYDAutoGearboxListener {
        public f() {
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i3) {
            super.onGearChanged(i3);
            if (i3 != 2 || b.this.T0() == 1) {
                return;
            }
            b bVar = b.this;
            bVar.q1(bVar.E0);
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class g extends AbsBYDAutoBodyworkListener {
        public g() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int i3, int i4) {
            super.onHasMessageChanged(i3, i4);
            if (i3 == 212) {
                BydLogHelper.logE("eCall onHasMessageChanged - state=" + i4);
                b.this.f2881v1 = i4;
            }
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i3) {
            if (i3 != 0) {
                if (i3 == 2) {
                    if (b.this.N0 == null) {
                        b bVar = b.this;
                        bVar.N0 = BYDAutoDiPilotDevice.getInstance(bVar.O);
                    }
                    Settings.System.putInt(b.this.O.getContentResolver(), "wade_protect_risk_dialog_show", 2);
                    BydLogHelper.logE(AbsBYDAutoBodyworkListener.TAG, "onPowerLevelChanged WADE_PROTECT_RISK_DIALOG_SHOW =" + Settings.System.getInt(b.this.O.getContentResolver(), "wade_protect_risk_dialog_show", 2));
                    Settings.System.putInt(b.this.O.getContentResolver(), "sand_mode_abs_dialog_show", 2);
                    BydLogHelper.logE(AbsBYDAutoBodyworkListener.TAG, "onPowerLevelChanged SAND_MODE_ABS_DIALOG_SHOW =" + Settings.System.getInt(b.this.O.getContentResolver(), "sand_mode_abs_dialog_show", 2));
                    return;
                }
                return;
            }
            if (b.this.f2821a1.b()) {
                e1.c.b().c();
                b.this.f2821a1.i(false);
            }
            if (b.this.f2822b0 != null && b.this.f2822b0.f()) {
                b.this.f2822b0.a();
            }
            if (b.this.T0() != 1) {
                b bVar2 = b.this;
                bVar2.q1(bVar2.E0);
            }
            if (AutoReceiver.f1833b) {
                b.this.O.sendBroadcast(new Intent(b.this.J0));
            }
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            b.this.O.startActivity(intent);
            b.this.f2842i1 = false;
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class h extends AbsBYDAutoAudioListener {
        public h() {
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onAllMuteState(int i3) {
            super.onAllMuteState(i3);
            BydLogHelper.logD(AbsBYDAutoAudioListener.TAG, "audioListener2222 state =" + i3 + ",mMutetoast =" + b.this.f2842i1);
            if (b.this.f2842i1) {
                if (b.this.f2884w1 != i3 && b.this.f2887x1 != i3) {
                    b.this.L1.removeMessages(1006);
                    Message message = new Message();
                    message.what = 1006;
                    message.arg1 = i3;
                    b.this.L1.sendMessageDelayed(message, 10L);
                }
                b.this.f2887x1 = i3;
                b.this.f2884w1 = i3;
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onPowerMuteStateChange(int i3) {
            super.onPowerMuteStateChange(i3);
            BydLogHelper.logD(AbsBYDAutoAudioListener.TAG, "onPowerMuteStateChange state =" + i3);
            if (i3 == 10) {
                b.this.L1.removeMessages(1010);
                b.this.L1.sendEmptyMessageDelayed(1010, 1000L);
                b.this.f2884w1 = 0;
            } else if (i3 == 4) {
                b.this.f2887x1 = -1;
            }
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class i implements k1.a {
        public i() {
        }

        @Override // k1.a
        public void b(List<BtCallInfo> list) {
            BydLogHelper.logD("HardKeyEvent", "callListener list =" + list.size());
            if (list.size() <= 0 || !b.this.f2821a1.b()) {
                return;
            }
            e1.c.b().c();
            b.this.f2821a1.h(false);
            b.this.f2821a1.i(false);
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class j extends AbsBYDAutoTestListener {
        public j() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onChangedConfigArea(int i3) {
            super.onChangedConfigArea(i3);
            BydLogHelper.logE(AbsBYDAutoTestListener.TAG, "onChangedConfigArea state = " + i3);
            if (i3 == 0) {
                b.this.L1.removeMessages(1012);
                b.this.L1.sendEmptyMessageDelayed(1012, 1000L);
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onReadConfigurationWordInfo(short s3, ArrayList<Byte> arrayList) {
            super.onReadConfigurationWordInfo(s3, arrayList);
            BydLogHelper.logE(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo did = " + ((int) s3) + ",data =" + arrayList.size());
            if (8448 == s3) {
                b.this.f2860o1.clear();
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    b.this.f2860o1.add(arrayList.get(i3));
                }
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onWriteConfigurationWordInfo(short s3, int i3) {
            super.onWriteConfigurationWordInfo(s3, i3);
            BydLogHelper.logE(AbsBYDAutoTestListener.TAG, "onWriteConfigurationWordInfo did = " + ((int) s3) + ",res =" + i3);
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class k implements a.c {
        public k() {
        }

        @Override // p1.a.c
        public void a(boolean z3) {
            Log.d("DANDAN", "----onServiceConnectChanged----connected: " + z3);
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class l extends AbsBYDAutoKeyListener {
        public l() {
        }

        @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener
        public void onIdlerWheel(int i3) {
            super.onIdlerWheel(i3);
        }

        @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener
        public void onKeyClick(int i3, int i4) {
            super.onKeyClick(i3, i4);
            BydLogHelper.logE(AbsBYDAutoKeyListener.TAG, "onKeyClick keyValue =" + i3 + ",click =" + i4);
            if (i3 == 1) {
                if (b.this.b1()) {
                    return;
                }
                if (!i1.d.a(b.this.O) || !i1.d.b(b.this.O)) {
                    BydLogHelper.logD("handlerAvm guide isn't complete");
                    return;
                }
                if (b.this.P == null) {
                    b.this.E0();
                }
                if (b.this.g1(b.f2817b)) {
                    return;
                }
                if (i4 != 1) {
                    if (i4 == 2) {
                        if (b.this.Z != 18) {
                            BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "mAvmManager.startPip()!");
                            b.this.P.i();
                            return;
                        } else {
                            if (b.this.Z == 18) {
                                BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "mAvmManager.stopPip()!");
                                b.this.P.j();
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                if (b.this.T0() != 1) {
                    b bVar = b.this;
                    bVar.q1(bVar.E0);
                }
                if (b.this.K0 != null && b.this.N0() == 2) {
                    BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "AutoGearboxDevice GEAR_R");
                    return;
                }
                b bVar2 = b.this;
                bVar2.f2838h0 = bVar2.P.c();
                BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "workState =" + b.this.f2838h0);
                if (b.this.f2838h0 == 3 || b.this.f2838h0 == 1) {
                    b.this.c();
                    b.this.t1(5);
                    return;
                } else if (b.this.f2838h0 == 2 && !b.this.f2890y1) {
                    b.this.t1(6);
                    return;
                } else {
                    if (b.this.f2838h0 == 2 && b.this.f2890y1) {
                        b.this.c();
                        b.this.t1(5);
                        return;
                    }
                    return;
                }
            }
            if (i3 == 2) {
                if (b.this.b1()) {
                    return;
                }
                if (!i1.d.a(b.this.O) || !i1.d.b(b.this.O)) {
                    BydLogHelper.logD("handlerAvm guide isn't complete");
                    b.this.L1.sendEmptyMessage(1004);
                    return;
                }
                if (b.this.T0() != 1) {
                    b bVar3 = b.this;
                    bVar3.q1(bVar3.E0);
                }
                Message message = new Message();
                message.what = 1002;
                message.arg1 = i4;
                b.this.L1.sendMessage(message);
                return;
            }
            if (i3 == 7) {
                if (b.this.b1()) {
                    return;
                }
                if (!i1.d.a(b.this.O) || !i1.d.b(b.this.O)) {
                    b.this.L1.sendEmptyMessage(1004);
                    return;
                }
                if (AutoReceiver.f1833b) {
                    b.this.O.sendBroadcast(new Intent(b.this.J0));
                }
                if (i4 != 1) {
                    if (i4 == 2) {
                        if (b.this.L1.hasMessages(1009)) {
                            b.this.L1.removeMessages(1009);
                            return;
                        }
                        Message message2 = new Message();
                        message2.what = 1009;
                        message2.arg1 = 7;
                        b.this.L1.sendMessageDelayed(message2, 200L);
                        return;
                    }
                    return;
                }
                if (b.this.f2859o0 == null) {
                    b.this.Z0();
                }
                if (b.this.f2871s0 == null) {
                    b.this.Y0();
                }
                if (b.this.f2821a1.b()) {
                    e1.c.b().c();
                    b.this.f2821a1.h(false);
                    b.this.f2821a1.i(false);
                }
                if (l1.a.o().n() == 1 || b.this.f2868r0) {
                    BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "KEY_MODE getCallState == 1 or mCarplayCalling state is " + b.this.f2868r0);
                    b.this.L1.removeMessages(1008);
                    b.this.L1.sendEmptyMessageDelayed(1008, 0L);
                    return;
                }
                if (b.this.T0() != 1) {
                    b bVar4 = b.this;
                    bVar4.q1(bVar4.E0);
                }
                b bVar5 = b.this;
                bVar5.f2838h0 = bVar5.P.c();
                if (b.this.f2838h0 == 2) {
                    b.this.t1(6);
                }
                b.this.X0();
                Message message3 = new Message();
                message3.what = 1001;
                message3.arg1 = i4;
                b.this.L1.sendMessageDelayed(message3, 100L);
                return;
            }
            if (i3 == 8) {
                if (b.this.b1()) {
                    return;
                }
                if (b.this.f2821a1.b()) {
                    BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "KEY_LEFT power true");
                    return;
                }
                BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "KEY_LEFT click =" + i4);
                if (i4 != 2) {
                    if (b.this.f2826d0 != null) {
                        b.this.f2826d0.e(i4);
                        return;
                    }
                    return;
                } else {
                    if (b.this.L1.hasMessages(1009)) {
                        b.this.L1.removeMessages(1009);
                        return;
                    }
                    Message message4 = new Message();
                    message4.what = 1009;
                    message4.arg1 = 8;
                    b.this.L1.sendMessageDelayed(message4, 300L);
                    return;
                }
            }
            if (i3 != 17 && i3 != 18) {
                if (i3 == 20) {
                    if (b.this.b1()) {
                        return;
                    }
                    if (!i1.d.a(b.this.O) || !i1.d.b(b.this.O)) {
                        b.this.L1.sendEmptyMessage(1004);
                        return;
                    }
                    if (b.this.O0() == 180) {
                        if (b.this.T0() != 1) {
                            b bVar6 = b.this;
                            bVar6.q1(bVar6.E0);
                        }
                        Message message5 = new Message();
                        message5.what = 1016;
                        message5.arg1 = i4;
                        b.this.L1.sendMessage(message5);
                        return;
                    }
                    return;
                }
                switch (i3) {
                    case 10:
                        if (b.this.b1()) {
                            return;
                        }
                        if (b.this.f2821a1.b()) {
                            BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "KEY_RIGHT power true");
                            return;
                        }
                        BydLogHelper.logD(AbsBYDAutoKeyListener.TAG, "KEY_RIGHT click =" + i4);
                        if (i4 != 2) {
                            if (b.this.f2826d0 != null) {
                                b.this.f2826d0.c(i4);
                                return;
                            }
                            return;
                        } else {
                            if (b.this.L1.hasMessages(1009)) {
                                b.this.L1.removeMessages(1009);
                                return;
                            }
                            Message message6 = new Message();
                            message6.what = 1009;
                            message6.arg1 = 10;
                            b.this.L1.sendMessageDelayed(message6, 300L);
                            return;
                        }
                    case 11:
                        if (b.this.c1(11) || b.this.T0() == 1) {
                            return;
                        }
                        b bVar7 = b.this;
                        bVar7.q1(bVar7.E0);
                        return;
                    case 12:
                        if (b.this.b1()) {
                            return;
                        }
                        if (b.this.T0() != 1) {
                            b bVar8 = b.this;
                            bVar8.q1(bVar8.E0);
                        }
                        if (AutoReceiver.f1833b) {
                            b.this.O.sendBroadcast(new Intent(b.this.J0));
                        }
                        if (l1.a.o().n() <= 0) {
                            b bVar9 = b.this;
                            bVar9.f2838h0 = bVar9.P.c();
                            if (b.this.f2838h0 == 2) {
                                b.this.t1(6);
                            }
                            if (b.this.f2821a1.b()) {
                                e1.c.b().c();
                                b.this.f2821a1.h(false);
                                b.this.f2821a1.i(false);
                                return;
                            }
                            return;
                        }
                        return;
                    case 13:
                        if (b.this.b1()) {
                            return;
                        } else {
                            return;
                        }
                    default:
                        switch (i3) {
                            case 501:
                                if (!b.this.b1()) {
                                    if (AutoReceiver.f1833b) {
                                        b.this.O.sendBroadcast(new Intent(b.this.J0));
                                    }
                                    b.this.n1();
                                    break;
                                }
                                break;
                            case 504:
                                b.this.L1.removeMessages(1007);
                                b.this.L1.sendEmptyMessageDelayed(1007, 500L);
                                break;
                        }
                        return;
                }
            }
            if (b.this.f2821a1.b()) {
                e1.c.b().c();
                b.this.f2821a1.h(false);
                b.this.f2821a1.i(false);
            }
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class m extends Handler {
        public m() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    b.this.c();
                    int i3 = message.arg1;
                    if (b.this.f2820a0 != null && i3 == 1) {
                        b.this.f2820a0.w(b.this.f2892z0, b.this.A0, b.this.f2889y0, b.this.C0, b.this.D0, b.this.B0);
                        break;
                    }
                    break;
                case 1002:
                    int i4 = message.arg1;
                    if (b.this.O0() == 123 || b.this.O0() == 180) {
                        if (i4 == 1) {
                            b bVar = b.this;
                            bVar.r1(bVar.O0(), 0);
                        } else if (i4 == 2) {
                            if (b.this.f2822b0 == null) {
                                b bVar2 = b.this;
                                bVar2.f2822b0 = new j1.b(bVar2.O);
                            }
                            if (b.this.f2822b0 != null) {
                                b.this.f2822b0.h(0);
                            }
                        }
                    } else if (i4 == 1) {
                        b bVar3 = b.this;
                        bVar3.r1(bVar3.O0(), 0);
                    } else if (i4 == 2) {
                        b bVar4 = b.this;
                        bVar4.r1(bVar4.O0(), 0);
                    }
                    break;
                case 1004:
                    ToastUtils.show(b.this.O, b.this.O.getString(R.string.warring_mode));
                    break;
                case 1005:
                    BydLogHelper.logD("HardKeyEvent", "MSG_WARRING_POWER =" + b.this.f2821a1.b());
                    if (b.this.f2821a1.b()) {
                        e1.c.b().c();
                        break;
                    } else {
                        int i5 = Settings.Global.getInt(b.this.O.getContentResolver(), "byd.scene.mode.state", 0);
                        BydLogHelper.logD("HardKeyEvent", "SCENE_MODE type =" + i5);
                        if (i5 != 5 && i5 != 2) {
                            if (b.this.f2823b1 == null) {
                                b bVar5 = b.this;
                                bVar5.f2823b1 = BYDAutoXCallDevice.getInstance(bVar5.O);
                            }
                            int callStatus = b.this.f2823b1.getCallStatus();
                            BydLogHelper.logD("HardKeyEvent", "XCALL state =" + callStatus);
                            if ((callStatus != 1 && callStatus != 2 && callStatus != 3 && callStatus != 4 && callStatus != 5 && callStatus != 6 && callStatus != 10) || b.this.f2881v1 != 1) {
                                int iN = l1.a.o().n();
                                BydLogHelper.logD("HardKeyEvent", "BTcall callState =" + iN);
                                if (iN > 0 || b.this.f2868r0) {
                                    b.this.L1.removeMessages(1008);
                                    b.this.L1.sendEmptyMessageDelayed(1008, 0L);
                                    break;
                                } else {
                                    Log.d("RTYRTYTRU", "bCPNaviState = " + b.this.B1 + " = bAANaviState = " + b.this.C1);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("AutoReceiver.mVoiceMicState =");
                                    sb.append(AutoReceiver.f1834c);
                                    BydLogHelper.logD("HardKeyEvent", sb.toString());
                                    if (!AutoReceiver.f1834c) {
                                        if (b.this.N0() == 2 || b.this.U0() || !b.this.C1 || b.this.B1) {
                                            ToastUtils.show(b.this.O, b.this.O.getString(R.string.warring_navi_mode));
                                        } else {
                                            b bVar6 = b.this;
                                            bVar6.f2838h0 = bVar6.P.c();
                                            BydLogHelper.logD("HardKeyEvent", "MSG_WARRING_POWER mAvmWorkStatus =" + b.this.f2838h0);
                                            if (b.this.T0() != 1) {
                                                b bVar7 = b.this;
                                                bVar7.q1(bVar7.E0);
                                            }
                                            e1.c.b().f(b.this.O);
                                            if (b.this.f2838h0 == 2) {
                                                b.this.t1(6);
                                            }
                                        }
                                        break;
                                    }
                                }
                            } else {
                                b.this.L1.removeMessages(1008);
                                b.this.L1.sendEmptyMessageDelayed(1008, 0L);
                                BydLogHelper.logD("HardKeyEvent", "XCALL mTbox id disconnect State = " + b.this.f2881v1);
                                break;
                            }
                        }
                    }
                    break;
                case 1006:
                    int i6 = message.arg1;
                    i1.c.b(b.this.O).c();
                    b.this.L1.removeMessages(1015);
                    if (i6 == 1) {
                        i1.c.b(b.this.O).d(b.this.O.getString(R.string.audio_muted));
                    } else if (i6 == 0) {
                        i1.c.b(b.this.O).d(b.this.O.getString(R.string.audio_removed));
                    }
                    b.this.L1.sendEmptyMessageDelayed(1015, 2000L);
                    break;
                case 1007:
                    if (b.this.N0() == 1) {
                        b.this.F0();
                    } else {
                        ToastUtils.show(b.this.O, b.this.O.getString(R.string.warring_power));
                    }
                    break;
                case 1008:
                    ToastUtils.show(b.this.O, b.this.O.getString(R.string.call_mode));
                    break;
                case 1009:
                    int i7 = message.arg1;
                    if (i7 == 7) {
                        b.this.n1();
                        break;
                    } else if (i7 == 10) {
                        if (b.this.f2826d0 != null) {
                            b.this.f2826d0.c(2);
                        }
                        break;
                    } else if (i7 == 8 && b.this.f2826d0 != null) {
                        b.this.f2826d0.e(2);
                        break;
                    }
                    break;
                case 1010:
                    b.this.f2842i1 = true;
                    break;
                case 1012:
                    b bVar8 = b.this;
                    bVar8.H0(bVar8.f2851l1);
                    BydLogHelper.logE("HardKeyEvent", "MESSAGE_MCU_RESET");
                    break;
                case 1014:
                    BydLogHelper.logE("HardKeyEvent", "MESSAGE_DELAY_TIME");
                    b.this.R0();
                    break;
                case 1015:
                    i1.c.b(b.this.O).c();
                    break;
                case 1016:
                    int i8 = message.arg1;
                    if (b.this.O0() == 123 || b.this.O0() == 180) {
                        if (i8 == 1) {
                            b bVar9 = b.this;
                            bVar9.r1(bVar9.O0(), 1);
                        } else if (i8 == 2) {
                            if (b.this.f2822b0 == null) {
                                b bVar10 = b.this;
                                bVar10.f2822b0 = new j1.b(bVar10.O);
                            }
                            if (b.this.f2822b0 != null) {
                                b.this.f2822b0.h(1);
                            }
                        }
                    }
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class n implements b.InterfaceC0045b {
        public n() {
        }

        @Override // o1.b.InterfaceC0045b
        public void a(int i3, int i4, b.e eVar) {
            String string = eVar.a().getString("success");
            BydLogHelper.logD("HardKeyEvent", "ACTION_GET_SUPPORT_MODE ret =" + string);
            if (!string.equals(o1.b.f4451b)) {
                BydLogHelper.logE("HardKeyEvent", "ACTION_GET_MEDIA_STATUS fail ret = " + eVar.a().getString("reason"));
                return;
            }
            int[] intArray = eVar.a().getIntArray("param_support_mode_list");
            boolean z3 = eVar.a().getBoolean("param_usb_plugin");
            boolean z4 = eVar.a().getBoolean("param_sd_plugin");
            if (!b.this.f1(intArray, z3, z4)) {
                b.this.f2892z0 = false;
                return;
            }
            b.this.f2892z0 = true;
            b.this.f2889y0 = intArray;
            b.this.C0 = Boolean.valueOf(z3);
            b.this.D0 = Boolean.valueOf(z4);
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class o implements b.InterfaceC0045b {
        public o() {
        }

        @Override // o1.b.InterfaceC0045b
        public void a(int i3, int i4, b.e eVar) {
            String string = eVar.a().getString("success");
            BydLogHelper.logD("HardKeyEvent", "ACTION_GET_MEDIA_STATUS ret =" + string);
            if (!string.equals(o1.b.f4451b)) {
                BydLogHelper.logE("HardKeyEvent", "ACTION_GET_MEDIA_STATUS fail ret = " + eVar.a().getString("reason"));
                return;
            }
            BydLogHelper.logD("HardKeyEvent", "ACTION GET MEDIA STATUS mode = " + eVar.a().getInt("param_media_mode"));
            BydLogHelper.logD("HardKeyEvent", "ACTION GET MEDIA STATUS type = " + eVar.a().getInt("param_media_type"));
            BydLogHelper.logD("HardKeyEvent", "ACTION GET MEDIA STATUS pattern = " + eVar.a().getInt(BYDAutoMultimediaDevice.PARAM_PATTERN));
            BydLogHelper.logD("HardKeyEvent", "ACTION GET MEDIA STATUS play state = " + eVar.a().getInt("param_media_play_state"));
            b.this.A0 = eVar.a().getInt("param_media_mode", 1);
            if (b.this.A0 == 1) {
                b.this.B0 = eVar.a().getInt("param_media_type", 0);
            }
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class p implements IServiceConnectionListener {
        public p() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            if (b.this.f2859o0 != null) {
                try {
                    b.this.f2859o0.registerCarPlayStateListener(b.this.N1);
                    b.this.f2859o0.registerCarPlayAppListener(b.this.P1);
                } catch (RemoteException unused) {
                }
            }
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class q implements ICarPlayStateListener {
        public q() {
        }

        public void onAppStateChanged(int i3, boolean z3, int i4) {
            if (i3 == 1) {
                Log.d("RTYRTYTRU", "onAppStateChanged = " + z3);
                b.this.B1 = z3;
            }
        }

        public void onSessionStateChanged(int i3) {
            BydLogHelper.logD("HardKeyEvent", "onSessionStateChanged status =" + i3);
            if (i3 == 2) {
                b.this.f2862p0 = 2;
            } else {
                b.this.f2862p0 = 4;
            }
            if (b.this.f2859o0 != null) {
                try {
                    if (i3 == 2) {
                        b.this.f2859o0.registerCallStateListener(b.this.O1);
                        BydLogHelper.logD("HardKeyEvent", "onSessionStateChanged registerCallState");
                    } else {
                        b.this.f2859o0.unregisterCallStateListener(b.this.O1);
                        b.this.f2868r0 = false;
                        BydLogHelper.logD("HardKeyEvent", "onSessionStateChanged unregisterCallState");
                    }
                } catch (RemoteException e3) {
                    BydLogHelper.logD("HardKeyEvent", "onSessionStateChanged registerCallException= " + e3.getMessage());
                }
            }
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class r implements ICarPlayAppListener {
        public r() {
        }

        public void onCarPlayDisplayStatusChanged(boolean z3) {
            BydLogHelper.logD("HardKeyEvent", "onCarPlayDisplayStatusChanged isShow =" + z3);
            b.this.f2865q0 = z3;
            if (z3) {
                b bVar = b.this;
                if (bVar.h1(bVar.O)) {
                    return;
                }
                b.this.f2829e0.setScreenRotation(1);
            }
        }
    }

    /* JADX INFO: compiled from: HardKeyEvent.java */
    public class s extends ContentObserver {
        public s(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z3, Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            if (!lastPathSegment.equals("navibar_mode_on_screensaver")) {
                if (lastPathSegment.equals("persist.sys.vehicle_privacy_status")) {
                    BydLogHelper.logE("HardKeyEvent", "隐私协议变更 SystemProperties.get(SYS_PRIVACY_STATUS) / " + SystemProperties.get("persist.sys.vehicle_privacy_status"));
                    b.this.o1();
                    return;
                }
                return;
            }
            int i3 = Settings.Global.getInt(b.this.O.getContentResolver(), "navibar_mode_on_screensaver", 0);
            BydLogHelper.logD("SettingsObserver", "screenOffType / " + i3);
            if ((i3 == 0 || i3 == 2) && b.this.f2820a0 != null) {
                b.this.f2820a0.A();
            }
        }
    }

    public b() {
        this.f2831f = null;
        this.O = null;
        this.f2820a0 = null;
        this.f2822b0 = null;
        this.f2826d0 = null;
        this.f2829e0 = null;
        Boolean bool = Boolean.FALSE;
        this.C0 = bool;
        this.D0 = bool;
        this.E0 = "byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver";
        this.F0 = "byd_pad://byd_car_settings:2933/launcher?action=screen_saver";
        this.G0 = 2;
        this.H0 = 1;
        this.I0 = null;
        this.J0 = "com.byd.action.qsPanel.auto";
        this.K0 = null;
        this.L0 = null;
        this.M0 = null;
        this.N0 = null;
        this.O0 = 5;
        this.P0 = 2;
        this.Q0 = false;
        this.R0 = 1;
        this.S0 = 2;
        this.T0 = 1;
        this.U0 = 2;
        this.V0 = 3;
        this.W0 = 4;
        this.X0 = 5;
        this.Y0 = 6;
        this.Z0 = 10;
        this.f2821a1 = null;
        this.f2823b1 = null;
        this.f2825c1 = null;
        this.f2827d1 = null;
        this.f2830e1 = null;
        this.f2833f1 = "com.neusoft.na.navigation";
        this.f2836g1 = "com.byd.automap";
        this.f2839h1 = "com.huawei.maps.auto.app";
        this.f2842i1 = true;
        this.f2845j1 = null;
        this.f2848k1 = "persist.vendor.byd.cfg.huc.config.area";
        this.f2851l1 = "byd_pad://byd_car_settings:2933/launcher?action=restore_factory_settings&pkg=com.byd.secretcode";
        this.f2854m1 = "lcountrycode";
        this.f2860o1 = new ArrayList<>();
        this.f2863p1 = 49;
        this.f2875t1 = false;
        this.f2881v1 = -1;
        this.f2884w1 = -1;
        this.f2887x1 = -1;
        this.f2890y1 = false;
        this.f2893z1 = 1;
        this.A1 = 0;
        this.B1 = false;
        this.C1 = true;
        this.G1 = Arrays.asList("47", "31", "44", "45", "351", "32", "46", "41", "43", "39", "34", "358", "353", "30", "49", "33", "359", "357", "385", "420", "36", "372", "371", "370", "352", "356", "48", "40", "421", "386", "354", "423", "377", "379", "382", "355", "55", "56", "598", "57", "506", "51", "593", "595", "591", "1242", "507", "52", "502", "1876", "65", "977", "61", "66", "92", "60", "62", "63", "64", "84", "997");
        this.H1 = Arrays.asList("51", "52", "55", "57", "60", "63", "66", "91", "92", "94", "95", "212", "216", "502", "505", "506", "507", "591", "593", "595", "598", "599", "673", "687", "689", "853", "855", "856", "961", "962", "965", "966", "968", "971", "973", "974", "975", "976", "977", "998", "1242", "1876", "1890", "27", "230", "263", "260", "267", "997", "355", "373", "379", "377", "503", "54", "1868", "1268", "297", "1246", "1345", "597", "1758", "592", "5997", "504", "254", "248", "256", "255", "258", "264");
        this.I1 = 0;
        this.J1 = null;
        this.K1 = new l();
        m mVar = new m();
        this.L1 = mVar;
        this.M1 = new p();
        this.N1 = new q();
        this.O1 = new ICallStateListener() { // from class: f1.a
            public final void onCallStateInfoChanged(CallStateInfo callStateInfo) {
                this.f2815a.j1(callStateInfo);
            }
        };
        this.P1 = new r();
        this.Q1 = new a();
        this.R1 = new C0011b();
        this.S1 = new c();
        this.T1 = new d();
        this.U1 = new e();
        f fVar = new f();
        this.V1 = fVar;
        g gVar = new g();
        this.W1 = gVar;
        h hVar = new h();
        this.X1 = hVar;
        i iVar = new i();
        this.Y1 = iVar;
        this.Z1 = 0;
        this.O = AutoApplication.a();
        this.f2866q1 = new ArrayList();
        this.f2831f = BYDAutoKeyDevice.getInstance(this.O);
        this.I0 = BYDAutoInstrumentDevice.getInstance(this.O);
        BydLogHelper.logI("HardKeyEvent", "mBYDAutoInstrumentDevice.registerListener(absBYDAutoInstrumentListener);");
        this.f2827d1 = BYDAutoTestDevice.getInstance(this.O);
        this.f2829e0 = BYDAutoSettingDevice.getInstance(this.O);
        BydLogHelper.logE("HardKeyEvent", "HardKeyEvent1111111111");
        E0();
        this.f2820a0 = new j1.a(this.O, this);
        this.f2822b0 = new j1.b(this.O);
        this.f2826d0 = new e1.b(this.O);
        this.f2830e1 = l1.a.o();
        Z0();
        Y0();
        this.f2845j1 = new AutoReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AutoReceiver.f1832a);
        intentFilter.addAction("com.byd.system.ecall.ACTION_SHOW");
        intentFilter.addAction("android.broadcast.voice.request.start");
        intentFilter.addAction("android.broadcast.voice.request.release");
        intentFilter.addAction("com.byd.action.quit.power");
        this.O.registerReceiver(this.f2845j1, intentFilter);
        this.K0 = BYDAutoGearboxDevice.getInstance(this.O);
        this.f2821a1 = new i1.a(this.O);
        this.K0.registerListener((AbsBYDAutoGearboxListener) fVar);
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.O);
        this.L0 = bYDAutoBodyworkDevice;
        bYDAutoBodyworkDevice.registerListener((AbsBYDAutoBodyworkListener) gVar);
        this.M0 = BYDAutoAudioDevice.getInstance(this.O);
        this.f2825c1 = n1.b.c(this.O);
        this.M0.registerListener((AbsBYDAutoAudioListener) hVar);
        this.f2884w1 = this.M0.getAllMute();
        this.f2887x1 = this.M0.getAllMute();
        this.f2830e1.q(iVar);
        l1();
        this.f2857n1 = new j();
        this.f2827d1.registerListener(this.f2857n1);
        this.f2831f.registerListener(this.K1);
        mVar.sendEmptyMessageDelayed(1014, 5000L);
        this.D1 = this.O.getContentResolver();
        this.E1 = new s(null);
        this.D1.registerContentObserver(Settings.Global.getUriFor("navibar_mode_on_screensaver"), false, this.E1);
        this.D1.registerContentObserver(Settings.Global.getUriFor("persist.sys.vehicle_privacy_status"), false, this.E1);
        this.F1 = p1.a.l(this.O, new k());
        BydLogHelper.logD("HardKeyEvent", "getCarId() == " + O0());
        if (O0() == 123 || O0() == 180) {
            int i3 = Settings.Global.getInt(this.D1, "byd_userdefind_value_7b", 0);
            BydLogHelper.logD("HardKeyEvent", "left_result == " + i3);
            if (i3 == 0) {
                i1.b.d(this.O, O0(), 4);
            }
        }
        if (O0() == 180) {
            int i4 = Settings.Global.getInt(this.D1, "byd_userdefind_value_mc_b6", 0);
            BydLogHelper.logD("HardKeyEvent", "right_result == " + i4);
            if (i4 == 0) {
                i1.b.e(this.O, O0(), 5);
            }
        }
    }

    public static b S0() {
        return f2818c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j1(CallStateInfo callStateInfo) {
        BydLogHelper.logD("HardKeyEvent", "onCallStateInfoChanged callState = " + callStateInfo.getStatus());
        if (callStateInfo.getStatus() == 0 || callStateInfo.getStatus() == -1) {
            this.f2868r0 = false;
            return;
        }
        this.f2868r0 = true;
        if (this.f2821a1.b()) {
            e1.c.b().c();
            this.f2821a1.h(false);
            this.f2821a1.i(false);
        }
    }

    public final void E0() {
        AvmManager avmManagerD = AvmManager.d();
        this.P = avmManagerD;
        avmManagerD.f(this);
        this.P.g(this);
        this.P.e(this.O, this.U1);
    }

    public final void F0() {
        if (this.f2825c1 == null) {
            this.f2825c1 = n1.b.c(this.O);
            BydLogHelper.logD("HardKeyEvent", "closeNaviMap mBydAutoNaviDevice reinitialize");
        }
        int focusMapType = this.f2825c1.getFocusMapType();
        int iB = this.f2825c1.b();
        StringBuilder sb = new StringBuilder();
        sb.append("closeNaviMap has local map focus ");
        sb.append(focusMapType == 0 || focusMapType == 1);
        BydLogHelper.logD("HardKeyEvent", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("closeNaviMap nav display mode not close");
        sb2.append(iB != 1);
        BydLogHelper.logD("HardKeyEvent", sb2.toString());
        if ((focusMapType == 0 || focusMapType == 1) && iB != 1) {
            this.f2825c1.sendNaviPresentationState(focusMapType, 1);
            BydLogHelper.logD("HardKeyEvent", "closeNaviMap local map close");
        }
    }

    public void G0() {
        j1.a aVar = this.f2820a0;
        if (aVar != null) {
            aVar.A();
        }
        j1.b bVar = this.f2822b0;
        if (bVar != null) {
            bVar.b();
        }
    }

    public final void H0(String str) {
        PackageManager packageManager = this.O.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        BydLogHelper.logE("HardKeyEvent", "----factory----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.O.startService(intent);
        BydLogHelper.logE("HardKeyEvent", "---- mContext.startService(in); = ");
    }

    public int I0() {
        BydLogHelper.logD("HardKeyEvent", "getAndroidAutoShow mVideoFocusStatus =" + this.f2877u0);
        return this.f2877u0;
    }

    public void J0() {
        int i3 = this.O.getResources().getConfiguration().uiMode & 48;
        BydLogHelper.logD("HardKeyEvent", "getAutoAvmTheme mode =" + i3);
        if (i3 == 32) {
            AvmManager avmManager = this.P;
            if (avmManager != null) {
                avmManager.l(AvmManager.Theme.DARK);
                return;
            }
            return;
        }
        AvmManager avmManager2 = this.P;
        if (avmManager2 != null) {
            avmManager2.l(AvmManager.Theme.LIGHT);
        }
    }

    public void K0() {
        int i3 = this.O.getResources().getConfiguration().uiMode & 48;
        BydLogHelper.logD("HardKeyEvent", "getAutoInstrumentTheme mode =" + i3);
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.I0;
        if (bYDAutoInstrumentDevice == null) {
            return;
        }
        if (i3 == 32) {
            bYDAutoInstrumentDevice.setDayNightMode(1);
        } else {
            bYDAutoInstrumentDevice.setDayNightMode(2);
        }
    }

    public void L0() {
        String string = this.O.getResources().getConfiguration().getLocales().get(0).toString();
        BydLogHelper.logI("HardKeyEvent", "langauge =" + string);
        if (this.P == null) {
            return;
        }
        if ("en_US".equals(string)) {
            this.P.k(AvmManager.Language.ENGLISH);
        } else if ("zh_CN".equals(string)) {
            this.P.k(AvmManager.Language.CHINESE);
        } else if ("de".equals(string)) {
            this.P.k(AvmManager.Language.GERMAN);
        } else if ("es".equals(string)) {
            this.P.k(AvmManager.Language.SPANISH);
        } else if ("es_LA".equals(string)) {
            this.P.k(AvmManager.Language.SPANISH_AMERICA);
        } else if ("pt_BR".equals(string)) {
            this.P.k(AvmManager.Language.PORTUGUESE_BRAZIL);
        } else if ("fr".equals(string)) {
            this.P.k(AvmManager.Language.FRANCH);
        } else if ("tr".equals(string)) {
            this.P.k(AvmManager.Language.TURKISH);
        } else if ("pt_PT".equals(string)) {
            this.P.k(AvmManager.Language.PORTUGUESE);
        } else if ("nb".equals(string)) {
            this.P.k(AvmManager.Language.NUOWAY);
        } else if ("iw".equals(string)) {
            this.P.k(AvmManager.Language.IVRIT);
        } else if ("sv".equals(string)) {
            this.P.k(AvmManager.Language.SWEDEN);
        } else if ("uz_UZ".equals(string)) {
            this.P.k(AvmManager.Language.UZBEKISTAN);
        } else if ("ru".equals(string)) {
            this.P.k(AvmManager.Language.RUSSIAN);
        } else if ("it".equals(string)) {
            this.P.k(AvmManager.Language.ITALIN);
        } else if ("zh_TW".equals(string) || "zh_HK".equals(string)) {
            BydLogHelper.logI("HardKeyEvent", "繁体 =" + string);
            this.P.k(AvmManager.Language.TRADITIONAL_CHINESE);
        } else if ("ja".equals(string)) {
            this.P.k(AvmManager.Language.JAPANESE);
        } else if ("ko".equals(string)) {
            this.P.k(AvmManager.Language.KOREAN);
        } else if ("hi".equals(string)) {
            this.P.k(AvmManager.Language.HINDI);
        } else if ("ar".equals(string)) {
            this.P.k(AvmManager.Language.ARABIC);
        } else if ("nl".equals(string)) {
            this.P.k(AvmManager.Language.DUTCH);
        } else if ("th".equals(string)) {
            this.P.k(AvmManager.Language.THAI);
        } else if ("hu".equals(string)) {
            this.P.k(AvmManager.Language.HUNGARIAN);
        } else if ("da".equals(string)) {
            this.P.k(AvmManager.Language.DANISH);
        } else if ("el".equals(string)) {
            this.P.k(AvmManager.Language.GREEK);
        } else if ("fi".equals(string)) {
            this.P.k(AvmManager.Language.FINNISH);
        } else if ("cs".equals(string)) {
            this.P.k(AvmManager.Language.CZECH);
        } else if ("pl".equals(string)) {
            this.P.k(AvmManager.Language.POLISH);
        } else if ("ms_MY".equals(string)) {
            this.P.k(AvmManager.Language.MALAY);
        } else if ("vi".equals(string)) {
            this.P.k(AvmManager.Language.VIETNAMESE);
        } else if ("in".equals(string)) {
            this.P.k(AvmManager.Language.BAHASA_INDONESIA);
        } else if ("ro".equals(string)) {
            this.P.k(AvmManager.Language.ROMANIAN);
        } else if ("sk".equals(string)) {
            this.P.k(AvmManager.Language.SLOVAK);
        } else if ("kk".equals(string)) {
            this.P.k(AvmManager.Language.KAZAKH);
        } else if ("bg".equals(string)) {
            this.P.k(AvmManager.Language.BULGARIAN);
        } else if ("uk".equals(string)) {
            this.P.k(AvmManager.Language.UKRAINIAN);
        } else if ("fr_CA".equals(string)) {
            this.P.k(AvmManager.Language.FRENCH_CANADA);
        } else if ("en_AU".equals(string)) {
            this.P.k(AvmManager.Language.ENGLISH_AUSTRALIA);
        } else if ("hr".equals(string)) {
            this.P.k(AvmManager.Language.CROATIAN);
        } else if ("ca".equals(string)) {
            this.P.k(AvmManager.Language.CATALAN);
        } else {
            this.P.k(AvmManager.Language.ENGLISH);
        }
        i1.e.a(this.O);
    }

    public void M0() {
        String string = this.O.getResources().getConfiguration().getLocales().get(0).toString();
        BydLogHelper.logI("HardKeyEvent", "from secretcode langauge =" + string);
        if (this.P == null) {
            return;
        }
        if ("en_US".equals(string)) {
            this.P.k(AvmManager.Language.ENGLISH);
            return;
        }
        if ("zh_CN".equals(string)) {
            this.P.k(AvmManager.Language.CHINESE);
            return;
        }
        if ("de".equals(string)) {
            this.P.k(AvmManager.Language.GERMAN);
            return;
        }
        if ("es".equals(string)) {
            this.P.k(AvmManager.Language.SPANISH);
            return;
        }
        if ("es_LA".equals(string)) {
            this.P.k(AvmManager.Language.SPANISH_AMERICA);
            return;
        }
        if ("pt_BR".equals(string)) {
            this.P.k(AvmManager.Language.PORTUGUESE_BRAZIL);
            return;
        }
        if ("fr".equals(string)) {
            this.P.k(AvmManager.Language.FRANCH);
            return;
        }
        if ("tr".equals(string)) {
            this.P.k(AvmManager.Language.TURKISH);
            return;
        }
        if ("pt_PT".equals(string)) {
            this.P.k(AvmManager.Language.PORTUGUESE);
            return;
        }
        if ("nb".equals(string)) {
            this.P.k(AvmManager.Language.NUOWAY);
            return;
        }
        if ("iw".equals(string)) {
            this.P.k(AvmManager.Language.IVRIT);
            return;
        }
        if ("sv".equals(string)) {
            this.P.k(AvmManager.Language.SWEDEN);
            return;
        }
        if ("uz_UZ".equals(string)) {
            this.P.k(AvmManager.Language.UZBEKISTAN);
            return;
        }
        if ("ru".equals(string)) {
            this.P.k(AvmManager.Language.RUSSIAN);
            return;
        }
        if ("it".equals(string)) {
            this.P.k(AvmManager.Language.ITALIN);
            return;
        }
        if ("zh_TW".equals(string) || "zh_HK".equals(string)) {
            this.P.k(AvmManager.Language.TRADITIONAL_CHINESE);
            return;
        }
        if ("ja".equals(string)) {
            this.P.k(AvmManager.Language.JAPANESE);
            return;
        }
        if ("ko".equals(string)) {
            this.P.k(AvmManager.Language.KOREAN);
            return;
        }
        if ("ar".equals(string)) {
            this.P.k(AvmManager.Language.ARABIC);
            return;
        }
        if ("nl".equals(string)) {
            this.P.k(AvmManager.Language.DUTCH);
            return;
        }
        if ("th".equals(string)) {
            this.P.k(AvmManager.Language.THAI);
            return;
        }
        if ("hu".equals(string)) {
            this.P.k(AvmManager.Language.HUNGARIAN);
            return;
        }
        if ("da".equals(string)) {
            this.P.k(AvmManager.Language.DANISH);
            return;
        }
        if ("el".equals(string)) {
            this.P.k(AvmManager.Language.GREEK);
            return;
        }
        if ("fi".equals(string)) {
            this.P.k(AvmManager.Language.FINNISH);
            return;
        }
        if ("cs".equals(string)) {
            this.P.k(AvmManager.Language.CZECH);
            return;
        }
        if ("pl".equals(string)) {
            this.P.k(AvmManager.Language.POLISH);
            return;
        }
        if ("ms_MY".equals(string)) {
            this.P.k(AvmManager.Language.MALAY);
            return;
        }
        if ("vi".equals(string)) {
            this.P.k(AvmManager.Language.VIETNAMESE);
            return;
        }
        if ("hr".equals(string)) {
            this.P.k(AvmManager.Language.CROATIAN);
            return;
        }
        if ("hi".equals(string)) {
            this.P.k(AvmManager.Language.HINDI);
            return;
        }
        if ("ca".equals(string)) {
            this.P.k(AvmManager.Language.CATALAN);
            return;
        }
        if ("in".equals(string)) {
            this.P.k(AvmManager.Language.BAHASA_INDONESIA);
            return;
        }
        if ("ro".equals(string)) {
            this.P.k(AvmManager.Language.ROMANIAN);
            return;
        }
        if ("sk".equals(string)) {
            this.P.k(AvmManager.Language.SLOVAK);
            return;
        }
        if ("kk".equals(string)) {
            this.P.k(AvmManager.Language.KAZAKH);
            return;
        }
        if ("bg".equals(string)) {
            this.P.k(AvmManager.Language.BULGARIAN);
            return;
        }
        if ("uk".equals(string)) {
            this.P.k(AvmManager.Language.UKRAINIAN);
            return;
        }
        if ("fr_CA".equals(string)) {
            this.P.k(AvmManager.Language.FRENCH_CANADA);
        } else if ("en_AU".equals(string)) {
            this.P.k(AvmManager.Language.ENGLISH_AUSTRALIA);
        } else {
            this.P.k(AvmManager.Language.ENGLISH);
        }
    }

    public int N0() {
        int autoType = this.L0.getAutoType();
        int gear = d1(autoType) ? this.K0.getGear() : e1(autoType) ? this.K0.getGearboxAutoModeLevel() : 0;
        BydLogHelper.logE("getCarGear carType = " + autoType + ", gear = " + gear);
        return gear;
    }

    public int O0() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.L0;
        int autoType = bYDAutoBodyworkDevice != null ? bYDAutoBodyworkDevice.getAutoType() : 0;
        BydLogHelper.logD("mCarID = " + autoType);
        return autoType;
    }

    public boolean P0() {
        BydLogHelper.logD("HardKeyEvent", "getCarplayShow mCarplayShow =" + this.f2865q0);
        return this.f2865q0;
    }

    public final int Q0(int i3) {
        for (int i4 = 0; i4 < this.f2866q1.size(); i4++) {
            if (this.f2866q1.get(i4).get("number").equals(String.valueOf(i3))) {
                return i4;
            }
        }
        return -1;
    }

    public final void R0() {
        if ("1".equals(SystemProperties.get("sys.boot_completed"))) {
            BydLogHelper.logE("HardKeyEvent", "getCurCountryCodeInfo system boot_completed");
        } else {
            int i3 = this.I1;
            if (i3 < f2819d) {
                this.I1 = i3 + 1;
                this.L1.sendEmptyMessageDelayed(1014, 2000L);
                return;
            }
        }
        int i4 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
        int i5 = CarSettings.UserTableData.getInt(this.O.getContentResolver(), "reboot_number");
        BydLogHelper.logE("HardKeyEvent", "getCurCountryCodeInfo6666666666666 reboot_num =" + i5 + ", mCountryNum = " + i4);
        if (i5 == -2147482648 || i5 == -1) {
            CarSettings.UserTableData.putInt(this.O.getContentResolver(), "LAST_COUNTRY", i4);
            CarSettings.UserTableData.putInt(this.O.getContentResolver(), "reboot_number", 0);
            BydLogHelper.logE("HardKeyEvent", "getCurCountryCodeInfo0000000000 mCountryCode =" + this.f2863p1 + ", mCountryNum = " + i4);
            return;
        }
        if (i5 < 1) {
            i5++;
            BydLogHelper.logE("HardKeyEvent", "getCurCountryCodeInfo22222222222222222 mCountryCode =" + this.f2863p1 + ", mCountryNum = " + i4);
        }
        CarSettings.UserTableData.putInt(this.O.getContentResolver(), "reboot_number", i5);
        BydLogHelper.logE("HardKeyEvent", "getCurCountryCodeInfo33333333333333 mCountryCode =" + this.f2863p1 + ", mCountryNum = " + i4);
        int i6 = CarSettings.UserTableData.getInt(this.O.getContentResolver(), "LAST_COUNTRY");
        this.f2863p1 = i6;
        if (i6 == i4) {
            BydLogHelper.logE("HardKeyEvent", "getCurCountryCodeInfo5555555555555 mCountryCode =" + this.f2863p1 + ", mCountryNum = " + i4);
            return;
        }
        BydLogHelper.logE("HardKeyEvent", "getCurCountryCodeInfo44444444444444444 mCountryCode =" + this.f2863p1 + ", mCountryNum = " + i4);
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f2829e0;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.notifyDilinkStarted();
        }
        BYDAutoTestDevice bYDAutoTestDevice = this.f2827d1;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.setConfigArea(String.valueOf(i4));
        }
        a1(this.O);
        boolean zContains = this.H1.contains(Integer.valueOf(this.f2863p1));
        BydLogHelper.logD("HardKeyEvent", "vspStateCode " + this.M0.setVSPState(!zContains ? 1 : 0));
        BydLogHelper.logD("HardKeyEvent", "insideAVASCode " + zContains);
        if (this.G1.indexOf(Integer.valueOf(Q0(this.f2863p1))) != -1) {
            SystemProperties.set("persist.sys.byd.navi_launcher", "true");
        } else {
            SystemProperties.set("persist.sys.byd.navi_launcher", "false");
            m1();
        }
    }

    public int T0() {
        return Settings.Global.getInt(this.O.getContentResolver(), "navibar_mode_on_screensaver", 0);
    }

    public final boolean U0() {
        ActivityManager.RunningTaskInfo runningTaskInfoV0 = V0();
        if (runningTaskInfoV0 == null) {
            return false;
        }
        String packageName = runningTaskInfoV0.baseActivity.getPackageName();
        BydLogHelper.logD("HardKeyEvent", "getRunningNavi packName =" + packageName);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return "com.neusoft.na.navigation".equals(packageName) || "com.byd.automap".equals(packageName) || "com.huawei.maps.auto.app".equals(packageName);
    }

    public ActivityManager.RunningTaskInfo V0() {
        return W0(3);
    }

    public ActivityManager.RunningTaskInfo W0(@WindowConfiguration.ActivityType int i3) {
        try {
            List filteredTasks = ActivityTaskManager.getService().getFilteredTasks(1, false);
            if (filteredTasks.isEmpty()) {
                return null;
            }
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) filteredTasks.get(0);
            if (runningTaskInfo == null || runningTaskInfo.displayId == 0) {
                return (ActivityManager.RunningTaskInfo) filteredTasks.get(0);
            }
            Log.w("HardKeyEvent", "getRunningTask tasks displayId not 0! info = " + runningTaskInfo);
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public void X0() {
        o1.b.c(this.O, -1, 72, null, new n());
        o1.b.c(this.O, -1, 70, null, new o());
    }

    public final void Y0() {
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.O);
        this.f2871s0 = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.Q1);
        }
    }

    public final void Z0() {
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this.O);
        this.f2859o0 = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            BydLogHelper.logD("HardKeyEvent", "initCarplay");
            this.f2859o0.registerConnectionListener(this.M1);
        }
    }

    @Override // com.ts.avm.sdk.AvmManager.d
    public void a(int i3) {
        BydLogHelper.logD("HardKeyEvent", "mode " + i3 + ", mAvmManager.getAvmViewMode() = " + this.P.b());
        this.Z = i3;
        if (i3 == 15) {
            this.f2890y1 = true;
            BydLogHelper.logD("HardKeyEvent", "onAvmViewStateChanged1111 ");
        } else if (i3 >= 1) {
            this.f2890y1 = false;
            BydLogHelper.logD("HardKeyEvent", "onAvmViewStateChanged2222 ");
        }
    }

    public final void a1(Context context) {
        this.f2869r1 = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2999);
        this.f2872s1 = layoutParams;
        layoutParams.gravity = 17;
        layoutParams.format = 1;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.flags = 1024;
        layoutParams.systemUiVisibility = 5894;
        this.f2878u1 = LayoutInflater.from(context).inflate(R.layout.show_reset_country, (ViewGroup) null);
        s1();
    }

    @Override // com.ts.avm.sdk.AvmManager.c
    public void b(int i3, String str) {
        BydLogHelper.logD("HardKeyEvent", "onStatusChanged: " + i3 + " " + str);
        this.f2838h0 = i3;
        if (i3 == 3) {
            if (this.f2865q0 && !h1(this.O)) {
                this.f2829e0.setScreenRotation(1);
            }
            if (I0() == 1 && !h1(this.O)) {
                this.f2829e0.setScreenRotation(1);
            }
            this.f2890y1 = false;
        }
        if (i3 == 2) {
            if (T0() != 1) {
                q1(this.E0);
            }
            if (this.f2821a1.b()) {
                e1.c.b().c();
                this.f2821a1.h(false);
                this.f2821a1.i(false);
            }
            t1(1001);
        }
    }

    public final boolean b1() {
        return c1(-1);
    }

    public final void c() {
        Log.d("DANDAN", "settingsApi == " + this.F1);
        p1.a aVar = this.F1;
        if (aVar != null) {
            aVar.j();
        }
    }

    public final boolean c1(int i3) {
        if (i1.b.c()) {
            return false;
        }
        BydLogHelper.logD("HardKeyEvent", "isBootState PRIVACY_POLICY = true");
        if (i3 == 11) {
            return true;
        }
        this.L1.sendEmptyMessageDelayed(1004, 100L);
        return true;
    }

    public void d() {
        if (this.f2822b0 != null) {
            this.f2822b0 = null;
        }
    }

    public final boolean d1(int i3) {
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
            case 196:
            case 208:
            case 224:
                return true;
            default:
                return false;
        }
    }

    public void e() {
        i1.a aVar = this.f2821a1;
        if (aVar != null && aVar.b()) {
            e1.c.b().c();
            this.f2821a1.h(false);
            this.f2821a1.i(false);
        }
        if (T0() != 1) {
            q1(this.E0);
        }
    }

    public final boolean e1(int i3) {
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

    public final void f() {
        Intent intent = new Intent();
        intent.setAction("CUSTOM_NAVI_STANDARD_BROADCAST_RECV");
        intent.putExtra("KEY_TYPE", 50001);
        intent.putExtra("DEST_TYPE", 0);
        intent.putExtra("IS_START_NAVI", 0);
        this.O.sendBroadcast(intent);
        Log.d("HSJFLDSF", "SendBroadCast2Navi===");
    }

    public final boolean f1(int[] iArr, boolean z3, boolean z4) {
        if (iArr == null) {
            return false;
        }
        int[] iArr2 = this.f2889y0;
        if (iArr2 == null || iArr2.length == 0 || iArr.length != iArr2.length) {
            return true;
        }
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] != this.f2889y0[i3]) {
                return true;
            }
        }
        return (z3 == this.C0.booleanValue() && z4 == this.D0.booleanValue()) ? false : true;
    }

    public boolean g1(long j3) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j4 = f2816a;
        long j5 = jCurrentTimeMillis - j4;
        if (j5 < 0) {
            return false;
        }
        if (j4 > 0 && j5 < j3) {
            return true;
        }
        f2816a = jCurrentTimeMillis;
        return false;
    }

    public boolean h1(Context context) {
        int i3 = context.getResources().getConfiguration().orientation;
        BydLogHelper.logD("HardKeyEvent", "orientation " + i3);
        return i3 != 1;
    }

    public void k1() {
        this.f2820a0.D();
        this.f2822b0.g();
        this.f2826d0.d();
        BydLogHelper.logD("HardKeyEvent", "onDestroy");
        if (this.f2821a1.b()) {
            e1.c.b().c();
            this.f2821a1.h(false);
            this.f2821a1.i(false);
        }
        ICarPlayAppManager iCarPlayAppManager = this.f2859o0;
        if (iCarPlayAppManager != null) {
            try {
                iCarPlayAppManager.unregisterCallStateListener(this.O1);
            } catch (RemoteException unused) {
            }
        }
        this.K0.unregisterListener(this.V1);
        this.L0.unregisterListener(this.W1);
        this.M0.unregisterListener(this.X1);
        this.O.unregisterReceiver(this.f2845j1);
        this.f2831f.unregisterListener(this.K1);
        AvmManager avmManager = this.P;
        if (avmManager != null) {
            avmManager.a(this.U1);
        }
        l1.a aVar = this.f2830e1;
        if (aVar != null) {
            aVar.q(this.Y1);
        }
        this.D1.unregisterContentObserver(this.E1);
    }

    public final void l1() {
        String str;
        InputStream inputStreamOpen;
        String language = Build.VERSION.SDK_INT >= 24 ? this.O.getResources().getConfiguration().getLocales().get(0).getLanguage() : this.O.getResources().getConfiguration().locale.getLanguage();
        try {
            inputStreamOpen = "zh".equals(language) ? this.O.getResources().getAssets().open("countrycode.json") : "en".equals(language) ? this.O.getResources().getAssets().open("countrycode_us.json") : this.O.getResources().getAssets().open("countrycode_us.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            str = new String(bArr);
        } catch (IOException unused) {
        }
        try {
            inputStreamOpen.close();
        } catch (IOException unused2) {
            language = str;
            str = language;
        }
        if (str == null) {
            Log.d("HardKeyEvent", "content is null ");
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i3);
                String string = jSONObject.getString("countryandregionname");
                String string2 = jSONObject.getString("internationaldomain");
                String string3 = jSONObject.getString("countrycode");
                String string4 = jSONObject.getString("number");
                HashMap map = new HashMap();
                map.put("countryandregion", string);
                map.put("internationaldomain", string2);
                map.put("countrycode", string3);
                map.put("number", string4);
                this.f2866q1.add(map);
            }
        } catch (JSONException unused3) {
        }
    }

    @SuppressLint({"WrongConstant"})
    public final void m1() {
        LauncherManager launcherManager = (LauncherManager) this.O.getSystemService("launcher");
        this.J1 = launcherManager;
        launcherManager.setDefaultLauncher("com.android.launcher3");
    }

    public final void n1() {
        this.L1.removeMessages(1005);
        this.L1.sendEmptyMessageDelayed(1005, 400L);
    }

    public void o1() {
        if (this.P != null) {
            BydLogHelper.logE("HardKeyEvent", "SystemProperties.get(SYS_PRIVACY_STATUS) / " + SystemProperties.get("persist.sys.vehicle_privacy_status"));
            try {
                if ("".equals(SystemProperties.get("persist.sys.vehicle_privacy_status"))) {
                    BydLogHelper.logE("HardKeyEvent", "SYS_PRIVACY_STATUS is null，set 3");
                    this.P.h(3);
                } else {
                    BydLogHelper.logE("HardKeyEvent", "SYS_PRIVACY_STATUS not null");
                    this.P.h(Integer.parseInt(SystemProperties.get("persist.sys.vehicle_privacy_status")));
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    public void p1() {
        if (this.f2865q0 || I0() == 1) {
            BydLogHelper.logD("mAvmWorkStatus = " + this.f2838h0);
            if (this.f2838h0 == 3) {
                this.f2829e0.setScreenRotation(1);
            }
        }
    }

    public final void q1(String str) {
        PackageManager packageManager = this.O.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        BydLogHelper.logD("HardKeyEvent", "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.O.startService(intent);
    }

    public final void r1(int i3, int i4) {
        int iA = i4 == 0 ? i1.b.a(this.O, i3) : i1.b.b(this.O, i3);
        BydLogHelper.logD("HardKeyEvent", "shortUserKey state == " + iA);
        switch (iA) {
            case 0:
                if (this.K0 != null && N0() == 2) {
                    BydLogHelper.logD("HardKeyEvent", "AutoGearboxDevice GEAR_R");
                } else {
                    if (this.f2829e0 == null) {
                        this.f2829e0 = BYDAutoSettingDevice.getInstance(this.O);
                    }
                    try {
                        if (!h1(this.O)) {
                            this.f2829e0.setScreenRotation(1);
                        } else if (P0() || I0() == 1) {
                            Context context = this.O;
                            ToastUtils.show(context, context.getString(R.string.carplay_androidauto_land));
                        } else {
                            this.f2829e0.setScreenRotation(2);
                        }
                    } catch (Exception unused) {
                        return;
                    }
                }
                break;
            case 1:
                o1.b.c(this.O, 11, 0, null, null);
                break;
            case 2:
                o1.b.c(this.O, 1, 0, null, null);
                break;
            case 3:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.byd.hvac", "com.byd.hvac.view.MainActivity"));
                intent.setFlags(337641472);
                BnWindowHelper.startActivity(this.O, intent);
                break;
            case 4:
                this.f2826d0.b();
                break;
            case 5:
                if (!b1()) {
                    if (i1.d.a(this.O) && i1.d.b(this.O)) {
                        if (AutoReceiver.f1833b) {
                            this.O.sendBroadcast(new Intent(this.J0));
                        }
                        if (this.f2859o0 == null) {
                            Z0();
                        }
                        if (this.f2871s0 == null) {
                            Y0();
                        }
                        if (this.f2821a1.b()) {
                            e1.c.b().c();
                            this.f2821a1.h(false);
                            this.f2821a1.i(false);
                        }
                        if (l1.a.o().n() == 1 || this.f2868r0) {
                            BydLogHelper.logD("HardKeyEvent", "KEY_MODE getCallState == 1 or mCarplayCalling state is " + this.f2868r0);
                            this.L1.removeMessages(1008);
                            this.L1.sendEmptyMessageDelayed(1008, 0L);
                        } else {
                            if (T0() != 1) {
                                q1(this.E0);
                            }
                            int iC = this.P.c();
                            this.f2838h0 = iC;
                            if (iC == 2) {
                                t1(6);
                            }
                            X0();
                            Message message = new Message();
                            message.what = 1001;
                            message.arg1 = 1;
                            this.L1.sendMessageDelayed(message, 100L);
                        }
                    } else {
                        this.L1.sendEmptyMessage(1004);
                    }
                    break;
                }
                break;
            case 6:
                f();
                break;
        }
    }

    public void s1() {
        WindowManager windowManager;
        if (this.f2875t1 || (windowManager = this.f2869r1) == null) {
            return;
        }
        windowManager.addView(this.f2878u1, this.f2872s1);
        this.f2875t1 = true;
    }

    public final void t1(int i3) {
        if (this.O == null || !i1.f.a().b(this.O, "com.byd.avm")) {
            StringBuilder sb = new StringBuilder();
            sb.append("start avm");
            sb.append(this.O == null ? "context is null" : "avm is not install");
            BydLogHelper.logD("HardKeyEvent", sb.toString());
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.avm", "com.byd.avm.MainActivity"));
        Bundle bundle = new Bundle();
        bundle.putInt("bydavmmode", i3);
        intent.putExtra(BnWindowHelper.WINDOW_KEY_PARAM, bundle);
        try {
            BnWindowHelper.startActivity(this.O, intent);
        } catch (ActivityNotFoundException unused) {
        }
    }
}
