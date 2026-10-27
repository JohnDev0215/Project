package com.byd.secretcode.activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydActivity;
import defpackage.hg;
import defpackage.ig;
import defpackage.mg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OFFLineConfigActivity extends BydActivity implements View.OnClickListener {
    public ig A0;
    public ig B0;
    public ig C0;
    public ig D0;
    public ig E0;
    public ig F0;
    public ig G0;
    public ig H0;
    public ig I0;
    public ig J0;
    public ig K0;
    public ig L0;
    public ig M0;
    public ig N0;
    public ig O0;
    public ig P0;
    public ig Q0;
    public ig R0;
    public ig S0;
    public ig T0;
    public ig U0;
    public ig V0;
    public ig W0;
    public ig X0;
    public ig Y0;
    public ig Z0;
    public ig a1;
    public hg d0;
    public AbsBYDAutoTestListener d1;
    public Context e0;
    public boolean f0;
    public byte[] g0;
    public byte[] h0;
    public LinearLayout i0;
    public LinearLayout j0;
    public TextView k0;
    public TextView l0;
    public ListView m0;
    public BYDAutoPowerDevice n1;
    public EditText s0;
    public LinearLayout t0;
    public Button u0;
    public Button v0;
    public Button w0;
    public ig x0;
    public ig y0;
    public ig z0;
    public static final List<String> b = new ArrayList(Arrays.asList("纯电", "混动", "油车"));
    public static final List<String> c = new ArrayList(Arrays.asList("Di4.0", "Di3.0改Di4.0", "Di2.0改Di4.0", "预留"));
    public static final List<String> d = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> e = new ArrayList(Arrays.asList("POC供电", "硬线供电"));
    public static final List<String> f = new ArrayList(Arrays.asList("POC供电", "硬线供电"));
    public static final List<String> g = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> h = new ArrayList(Arrays.asList("POC供电", "独立硬线供电"));
    public static final List<String> i = new ArrayList(Arrays.asList("POC供电", "独立硬线供电"));
    public static final List<String> j = new ArrayList(Arrays.asList("POC供电", "独立硬线供电"));
    public static final List<String> k = new ArrayList(Arrays.asList("POC供电", "no poc供电"));
    public static final List<String> l = new ArrayList(Arrays.asList("POC供电", "no poc供电"));
    public static final List<String> m = new ArrayList(Arrays.asList("无", "有"));
    public static final List<String> n = new ArrayList(Arrays.asList("POC供电", "no poc供电"));
    public static final List<String> o = new ArrayList(Arrays.asList("4G", "5G"));
    public static final List<String> p = new ArrayList(Arrays.asList("无", "有"));
    public static final List<String> q = new ArrayList(Arrays.asList("美版", "非美版"));
    public static final List<String> r = new ArrayList(Arrays.asList("无", "有"));
    public static final List<String> s = new ArrayList(Arrays.asList("CAN总线", "模拟"));
    public static final List<String> t = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> u = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> v = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> w = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> x = new ArrayList(Arrays.asList("左舵", "右舵"));
    public static final List<String> y = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> z = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> A = new ArrayList(Arrays.asList("有", "无"));
    public static final List<String> B = new ArrayList(Arrays.asList("双音区、双MIC", "四音区、4个MIC", "六音区、6个MIC"));
    public final int C = 0;
    public final int D = 1;
    public final int E = 2;
    public final int F = 3;
    public final int G = 4;
    public final int H = 5;
    public final int I = 6;
    public final int J = 7;
    public final int K = 0;
    public final int L = 1;
    public final int M = 2;
    public final int N = 4;
    public final int O = 2;
    public final int P = 4;
    public final int Q = 8;
    public final int R = 8;
    public final int S = 1;
    public final int T = 0;
    public final int U = 1001;
    public final int V = 1002;
    public final int W = 1003;
    public final int X = 1004;
    public final int Y = 2;
    public final int Z = 4;
    public final int a0 = 3;
    public final int b0 = 1;
    public final String c0 = "OFFLineConfigActivity";
    public String n0 = BYDAutoSysPropertiesDevice.RIGHT_RUDDER_VALUE;
    public final String o0 = "persist.vendor.byd.cfg.huc.config.area";
    public int p0 = 0;
    public int q0 = 0;
    public int r0 = 49;
    public String b1 = "byd_pad://byd_car_settings:2933/launcher?action=restore_factory_settings&pkg=com.byd.secretcode";
    public BYDAutoTestDevice c1 = null;
    public Byte e1 = (byte) 0;
    public Byte f1 = (byte) 0;
    public Byte g1 = (byte) 0;
    public Byte h1 = (byte) 0;
    public Byte i1 = (byte) 0;
    public Byte j1 = (byte) 0;
    public Byte k1 = (byte) 0;
    public Byte l1 = (byte) 0;
    public Byte m1 = (byte) 0;
    public final int o1 = 0;
    public final int p1 = 1;
    public final List<String> q1 = new ArrayList();
    public final List<String> r1 = new ArrayList();
    public List<ig> s1 = new ArrayList();
    public d t1 = new a();
    public Handler u1 = new c();

    public class a implements d {
        public a() {
        }

        @Override // com.byd.secretcode.activity.OFFLineConfigActivity.d
        public void a(int i, boolean z) {
            BydLogHelper.logD("OFFLineConfigActivity", "onSwitchClick :" + i + ";;;;" + z);
            OFFLineConfigActivity.this.s1.get(i);
        }

        @Override // com.byd.secretcode.activity.OFFLineConfigActivity.d
        public void b(int i, int i2) {
            BydLogHelper.logD("OFFLineConfigActivity", "onItemClick :" + i + ";;;;" + i2);
            List<ig> list = OFFLineConfigActivity.this.s1;
            if (list == null || list.size() <= i) {
                return;
            }
            OFFLineConfigActivity.this.s1.get(i);
            OFFLineConfigActivity.this.s1.get(i).i(i2);
        }

        @Override // com.byd.secretcode.activity.OFFLineConfigActivity.d
        public void c(int i, int i2) {
            BydLogHelper.logD("OFFLineConfigActivity", "onSpinnerSelect :" + i + ";;;;" + i2);
            List<ig> list = OFFLineConfigActivity.this.s1;
            if (list == null || list.size() <= i) {
                return;
            }
            OFFLineConfigActivity.this.s1.get(i);
            OFFLineConfigActivity.this.s1.get(i).i(i2);
        }
    }

    public class b extends AbsBYDAutoTestListener {
        public b() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onChangedConfigArea(int i) {
            super.onChangedConfigArea(i);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onChangedConfigArea state = " + i);
            if (i == 0) {
                OFFLineConfigActivity.this.u1.removeMessages(1002);
                OFFLineConfigActivity.this.u1.sendEmptyMessageDelayed(1002, 1000L);
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onReadConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
            super.onReadConfigurationWordInfo(s, arrayList);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo did = " + ((int) s) + ",data =" + arrayList.size());
            if (8448 == s) {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList2.add(arrayList.get(i));
                    BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo mByte = " + OFFLineConfigActivity.this.e1);
                }
                byte bN1 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 128, 7);
                byte bN12 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 64, 6);
                byte bN13 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 32, 5);
                byte bN14 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 16, 4);
                byte bN15 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 8, 3);
                byte bN16 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 4, 2);
                byte bN17 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 2, 1);
                byte bN18 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(0)).byteValue(), 1, 0);
                OFFLineConfigActivity oFFLineConfigActivity = OFFLineConfigActivity.this;
                oFFLineConfigActivity.x0 = oFFLineConfigActivity.p1(2, "", OFFLineConfigActivity.d, bN1, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_aux_in), "");
                OFFLineConfigActivity oFFLineConfigActivity2 = OFFLineConfigActivity.this;
                oFFLineConfigActivity2.s1.add(oFFLineConfigActivity2.x0);
                OFFLineConfigActivity oFFLineConfigActivity3 = OFFLineConfigActivity.this;
                oFFLineConfigActivity3.y0 = oFFLineConfigActivity3.p1(2, "", OFFLineConfigActivity.e, bN12, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_dbc_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity4 = OFFLineConfigActivity.this;
                oFFLineConfigActivity4.s1.add(oFFLineConfigActivity4.y0);
                OFFLineConfigActivity oFFLineConfigActivity5 = OFFLineConfigActivity.this;
                oFFLineConfigActivity5.z0 = oFFLineConfigActivity5.p1(2, "", OFFLineConfigActivity.f, bN13, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_fm_am_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity6 = OFFLineConfigActivity.this;
                oFFLineConfigActivity6.s1.add(oFFLineConfigActivity6.z0);
                OFFLineConfigActivity oFFLineConfigActivity7 = OFFLineConfigActivity.this;
                oFFLineConfigActivity7.A0 = oFFLineConfigActivity7.p1(2, "", OFFLineConfigActivity.g, bN14, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_dms), "");
                OFFLineConfigActivity oFFLineConfigActivity8 = OFFLineConfigActivity.this;
                oFFLineConfigActivity8.s1.add(oFFLineConfigActivity8.A0);
                OFFLineConfigActivity oFFLineConfigActivity9 = OFFLineConfigActivity.this;
                oFFLineConfigActivity9.B0 = oFFLineConfigActivity9.p1(2, "", OFFLineConfigActivity.h, bN15, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_dms_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity10 = OFFLineConfigActivity.this;
                oFFLineConfigActivity10.s1.add(oFFLineConfigActivity10.B0);
                OFFLineConfigActivity oFFLineConfigActivity11 = OFFLineConfigActivity.this;
                oFFLineConfigActivity11.D0 = oFFLineConfigActivity11.p1(2, "", OFFLineConfigActivity.i, bN16, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_ims_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity12 = OFFLineConfigActivity.this;
                oFFLineConfigActivity12.s1.add(oFFLineConfigActivity12.D0);
                OFFLineConfigActivity oFFLineConfigActivity13 = OFFLineConfigActivity.this;
                oFFLineConfigActivity13.C0 = oFFLineConfigActivity13.p1(2, "", OFFLineConfigActivity.j, bN17, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_oms_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity14 = OFFLineConfigActivity.this;
                oFFLineConfigActivity14.s1.add(oFFLineConfigActivity14.C0);
                OFFLineConfigActivity oFFLineConfigActivity15 = OFFLineConfigActivity.this;
                oFFLineConfigActivity15.E0 = oFFLineConfigActivity15.p1(2, "", OFFLineConfigActivity.k, bN18, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_streaming_video_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity16 = OFFLineConfigActivity.this;
                oFFLineConfigActivity16.s1.add(oFFLineConfigActivity16.E0);
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo---1 aux = " + ((int) bN1) + ",dabPower =" + ((int) bN12) + ",fmPower =" + ((int) bN13) + ",avm =" + ((int) bN14) + ",dmsPower =" + ((int) bN15) + ",ims =" + ((int) bN16) + ",omsPower =" + ((int) bN17) + ",streaming =" + ((int) bN18));
                byte bN19 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 128, 7);
                byte bN110 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 64, 6);
                byte bN111 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 32, 5);
                byte bN112 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 16, 4);
                byte bN113 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 8, 3);
                byte bN114 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 4, 2);
                byte bN115 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 2, 1);
                byte bN116 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(1)).byteValue(), 1, 0);
                OFFLineConfigActivity oFFLineConfigActivity17 = OFFLineConfigActivity.this;
                oFFLineConfigActivity17.G0 = oFFLineConfigActivity17.p1(2, "", OFFLineConfigActivity.m, bN19, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_sxm), "");
                OFFLineConfigActivity oFFLineConfigActivity18 = OFFLineConfigActivity.this;
                oFFLineConfigActivity18.s1.add(oFFLineConfigActivity18.G0);
                OFFLineConfigActivity oFFLineConfigActivity19 = OFFLineConfigActivity.this;
                oFFLineConfigActivity19.F0 = oFFLineConfigActivity19.p1(2, "", OFFLineConfigActivity.l, bN110, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_dvr_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity20 = OFFLineConfigActivity.this;
                oFFLineConfigActivity20.s1.add(oFFLineConfigActivity20.F0);
                OFFLineConfigActivity oFFLineConfigActivity21 = OFFLineConfigActivity.this;
                oFFLineConfigActivity21.H0 = oFFLineConfigActivity21.p1(2, "", OFFLineConfigActivity.n, bN111, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_face_id_power_supply_mode), "");
                OFFLineConfigActivity oFFLineConfigActivity22 = OFFLineConfigActivity.this;
                oFFLineConfigActivity22.s1.add(oFFLineConfigActivity22.H0);
                OFFLineConfigActivity oFFLineConfigActivity23 = OFFLineConfigActivity.this;
                oFFLineConfigActivity23.I0 = oFFLineConfigActivity23.p1(2, "", OFFLineConfigActivity.o, bN112, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_tbox), "");
                OFFLineConfigActivity oFFLineConfigActivity24 = OFFLineConfigActivity.this;
                oFFLineConfigActivity24.s1.add(oFFLineConfigActivity24.I0);
                OFFLineConfigActivity oFFLineConfigActivity25 = OFFLineConfigActivity.this;
                oFFLineConfigActivity25.J0 = oFFLineConfigActivity25.p1(2, "", OFFLineConfigActivity.p, bN113, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_single_bt), "");
                OFFLineConfigActivity oFFLineConfigActivity26 = OFFLineConfigActivity.this;
                oFFLineConfigActivity26.s1.add(oFFLineConfigActivity26.J0);
                OFFLineConfigActivity oFFLineConfigActivity27 = OFFLineConfigActivity.this;
                oFFLineConfigActivity27.K0 = oFFLineConfigActivity27.p1(2, "", OFFLineConfigActivity.q, bN114, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_us_version), "");
                OFFLineConfigActivity oFFLineConfigActivity28 = OFFLineConfigActivity.this;
                oFFLineConfigActivity28.s1.add(oFFLineConfigActivity28.K0);
                OFFLineConfigActivity oFFLineConfigActivity29 = OFFLineConfigActivity.this;
                oFFLineConfigActivity29.L0 = oFFLineConfigActivity29.p1(2, "", OFFLineConfigActivity.r, bN115, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_carplay_wired), "");
                OFFLineConfigActivity oFFLineConfigActivity30 = OFFLineConfigActivity.this;
                oFFLineConfigActivity30.s1.add(oFFLineConfigActivity30.L0);
                OFFLineConfigActivity oFFLineConfigActivity31 = OFFLineConfigActivity.this;
                oFFLineConfigActivity31.M0 = oFFLineConfigActivity31.p1(2, "", OFFLineConfigActivity.s, bN116, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_swc_type), "");
                OFFLineConfigActivity oFFLineConfigActivity32 = OFFLineConfigActivity.this;
                oFFLineConfigActivity32.s1.add(oFFLineConfigActivity32.M0);
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo---2 sxm = " + ((int) bN19) + ",dvrPower =" + ((int) bN110) + ",faceId =" + ((int) bN111) + ",tbox =" + ((int) bN112) + ",singleBt =" + ((int) bN113) + ",usVersion =" + ((int) bN114) + ",carplay =" + ((int) bN115) + ",swc =" + ((int) bN116));
                byte bN117 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(2)).byteValue(), 255, 0);
                StringBuilder sb = new StringBuilder();
                sb.append("onReadConfigurationWordInfo---3 vehicleInfo = ");
                sb.append((int) bN117);
                sb.append(",offlineData.get(2) =");
                sb.append(arrayList2.get(2));
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, sb.toString());
                byte b = bN117 == -1 ? (byte) 3 : bN117;
                OFFLineConfigActivity oFFLineConfigActivity33 = OFFLineConfigActivity.this;
                oFFLineConfigActivity33.W0 = oFFLineConfigActivity33.p1(2, "", oFFLineConfigActivity33.l1(R.array.vehicle_info_array), b, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_vehicle_info), "");
                OFFLineConfigActivity oFFLineConfigActivity34 = OFFLineConfigActivity.this;
                oFFLineConfigActivity34.s1.add(oFFLineConfigActivity34.W0);
                int iN1 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(3)).byteValue(), 255, 0) & 255;
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo---4 configArea = " + iN1);
                OFFLineConfigActivity oFFLineConfigActivity35 = OFFLineConfigActivity.this;
                oFFLineConfigActivity35.Y0 = oFFLineConfigActivity35.p1(4, "", oFFLineConfigActivity35.l1(R.array.config_area), iN1, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_config_area), "");
                OFFLineConfigActivity oFFLineConfigActivity36 = OFFLineConfigActivity.this;
                oFFLineConfigActivity36.s1.add(oFFLineConfigActivity36.Y0);
                byte bN118 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 128, 7);
                byte bN119 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 64, 6);
                byte bN120 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 32, 5);
                byte bN121 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 16, 4);
                byte bN122 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 8, 3);
                byte bN123 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 4, 2);
                byte bN124 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 2, 1);
                byte bN125 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(4)).byteValue(), 1, 0);
                OFFLineConfigActivity oFFLineConfigActivity37 = OFFLineConfigActivity.this;
                oFFLineConfigActivity37.N0 = oFFLineConfigActivity37.p1(2, "", OFFLineConfigActivity.t, bN118, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_rds), "");
                OFFLineConfigActivity oFFLineConfigActivity38 = OFFLineConfigActivity.this;
                oFFLineConfigActivity38.s1.add(oFFLineConfigActivity38.N0);
                OFFLineConfigActivity oFFLineConfigActivity39 = OFFLineConfigActivity.this;
                oFFLineConfigActivity39.O0 = oFFLineConfigActivity39.p1(2, "", OFFLineConfigActivity.u, bN119, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_dab), "");
                OFFLineConfigActivity oFFLineConfigActivity40 = OFFLineConfigActivity.this;
                oFFLineConfigActivity40.s1.add(oFFLineConfigActivity40.O0);
                OFFLineConfigActivity oFFLineConfigActivity41 = OFFLineConfigActivity.this;
                oFFLineConfigActivity41.P0 = oFFLineConfigActivity41.p1(2, "", OFFLineConfigActivity.v, bN120, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_hd), "");
                OFFLineConfigActivity oFFLineConfigActivity42 = OFFLineConfigActivity.this;
                oFFLineConfigActivity42.s1.add(oFFLineConfigActivity42.P0);
                OFFLineConfigActivity oFFLineConfigActivity43 = OFFLineConfigActivity.this;
                oFFLineConfigActivity43.Q0 = oFFLineConfigActivity43.p1(2, "", OFFLineConfigActivity.w, bN121, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_drm), "");
                OFFLineConfigActivity oFFLineConfigActivity44 = OFFLineConfigActivity.this;
                oFFLineConfigActivity44.s1.add(oFFLineConfigActivity44.Q0);
                OFFLineConfigActivity oFFLineConfigActivity45 = OFFLineConfigActivity.this;
                oFFLineConfigActivity45.R0 = oFFLineConfigActivity45.p1(2, "", OFFLineConfigActivity.x, bN122, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_steering_wheel_position), "");
                OFFLineConfigActivity oFFLineConfigActivity46 = OFFLineConfigActivity.this;
                oFFLineConfigActivity46.s1.add(oFFLineConfigActivity46.R0);
                OFFLineConfigActivity oFFLineConfigActivity47 = OFFLineConfigActivity.this;
                oFFLineConfigActivity47.S0 = oFFLineConfigActivity47.p1(2, "", OFFLineConfigActivity.y, bN123, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_am), "");
                OFFLineConfigActivity oFFLineConfigActivity48 = OFFLineConfigActivity.this;
                oFFLineConfigActivity48.s1.add(oFFLineConfigActivity48.S0);
                OFFLineConfigActivity oFFLineConfigActivity49 = OFFLineConfigActivity.this;
                oFFLineConfigActivity49.T0 = oFFLineConfigActivity49.p1(2, "", OFFLineConfigActivity.z, bN124, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_single_am), "");
                OFFLineConfigActivity oFFLineConfigActivity50 = OFFLineConfigActivity.this;
                oFFLineConfigActivity50.s1.add(oFFLineConfigActivity50.T0);
                OFFLineConfigActivity oFFLineConfigActivity51 = OFFLineConfigActivity.this;
                oFFLineConfigActivity51.U0 = oFFLineConfigActivity51.p1(2, "", OFFLineConfigActivity.A, bN125, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_two_am), "");
                OFFLineConfigActivity oFFLineConfigActivity52 = OFFLineConfigActivity.this;
                oFFLineConfigActivity52.s1.add(oFFLineConfigActivity52.U0);
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo---5 rds = " + ((int) bN118) + ",dab =" + ((int) bN119) + ",hd =" + ((int) bN120) + ",drm =" + ((int) bN121) + ",steering =" + ((int) bN122) + ",am =" + ((int) bN123) + ",singleFm =" + ((int) bN124) + ",twoFm =" + ((int) bN125));
                byte bN126 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(5)).byteValue(), 192, 6);
                byte bN127 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(5)).byteValue(), 48, 4);
                byte bN128 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(5)).byteValue(), 15, 0);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("onReadConfigurationWordInfo---6 mic = ");
                sb2.append((int) bN126);
                sb2.append(",engineType =");
                sb2.append((int) bN127);
                sb2.append(",speakerNum =");
                sb2.append((int) bN128);
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, sb2.toString());
                byte bN129 = OFFLineConfigActivity.this.n1(((Byte) arrayList2.get(6)).byteValue(), 255, 0);
                OFFLineConfigActivity oFFLineConfigActivity53 = OFFLineConfigActivity.this;
                oFFLineConfigActivity53.V0 = oFFLineConfigActivity53.p1(2, "", OFFLineConfigActivity.B, bN126, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_mic_count), "");
                OFFLineConfigActivity oFFLineConfigActivity54 = OFFLineConfigActivity.this;
                oFFLineConfigActivity54.s1.add(oFFLineConfigActivity54.V0);
                OFFLineConfigActivity oFFLineConfigActivity55 = OFFLineConfigActivity.this;
                oFFLineConfigActivity55.X0 = oFFLineConfigActivity55.p1(2, "", OFFLineConfigActivity.b, bN127, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_engine_type), "");
                OFFLineConfigActivity oFFLineConfigActivity56 = OFFLineConfigActivity.this;
                oFFLineConfigActivity56.s1.add(oFFLineConfigActivity56.X0);
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo---7 sourceType = " + ((int) bN129));
                OFFLineConfigActivity oFFLineConfigActivity57 = OFFLineConfigActivity.this;
                oFFLineConfigActivity57.Z0 = oFFLineConfigActivity57.p1(4, "", oFFLineConfigActivity57.l1(R.array.speaker_num_array), bN128, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_loud_speaker_number), "");
                OFFLineConfigActivity oFFLineConfigActivity58 = OFFLineConfigActivity.this;
                oFFLineConfigActivity58.s1.add(oFFLineConfigActivity58.Z0);
                OFFLineConfigActivity oFFLineConfigActivity59 = OFFLineConfigActivity.this;
                oFFLineConfigActivity59.a1 = oFFLineConfigActivity59.p1(4, "", oFFLineConfigActivity59.l1(R.array.audiosource_type_array), bN129, OFFLineConfigActivity.this.e0.getString(R.string.offline_config_source_type), "");
                OFFLineConfigActivity oFFLineConfigActivity60 = OFFLineConfigActivity.this;
                oFFLineConfigActivity60.s1.add(oFFLineConfigActivity60.a1);
                Message message = new Message();
                message.what = 1001;
                OFFLineConfigActivity.this.u1.sendMessage(message);
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onWriteConfigurationWordInfo(short s, int i) {
            super.onWriteConfigurationWordInfo(s, i);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onWriteConfigurationWordInfo did = " + ((int) s) + ",res =" + i);
            if (s == -3679) {
                if (i == 0) {
                    OFFLineConfigActivity.this.u1.removeMessages(1003);
                    OFFLineConfigActivity.this.u1.sendEmptyMessageDelayed(1003, 1000L);
                    return;
                } else {
                    if (i == 1) {
                        OFFLineConfigActivity.this.u1.removeMessages(1004);
                        Message message = new Message();
                        message.what = 1004;
                        message.arg1 = 1;
                        OFFLineConfigActivity.this.u1.sendMessage(message);
                        return;
                    }
                    return;
                }
            }
            if (s == 8448) {
                if (i != 0) {
                    if (i == 1) {
                        Message message2 = new Message();
                        message2.what = 1004;
                        message2.arg1 = 2;
                        OFFLineConfigActivity.this.u1.sendMessage(message2);
                        return;
                    }
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("OFFLINE_DID NowCountryCode == ModifyCountryCode ");
                sb.append(OFFLineConfigActivity.this.r0 == OFFLineConfigActivity.this.q0);
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, sb.toString());
                if (OFFLineConfigActivity.this.r0 != OFFLineConfigActivity.this.q0) {
                    OFFLineConfigActivity.this.c1.setConfigArea(String.valueOf(OFFLineConfigActivity.this.q0));
                } else {
                    OFFLineConfigActivity.this.u1.removeMessages(1002);
                    OFFLineConfigActivity.this.u1.sendEmptyMessageDelayed(1002, 1000L);
                }
            }
        }
    }

    public class c extends Handler {
        public c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    OFFLineConfigActivity oFFLineConfigActivity = OFFLineConfigActivity.this;
                    List<ig> list = oFFLineConfigActivity.s1;
                    oFFLineConfigActivity.d0 = new hg(oFFLineConfigActivity.e0, OFFLineConfigActivity.this.t1);
                    OFFLineConfigActivity.this.m0.setAdapter((ListAdapter) OFFLineConfigActivity.this.d0);
                    OFFLineConfigActivity.this.d0.b(list);
                    break;
                case 1002:
                    if (OFFLineConfigActivity.this.p0 == 0) {
                        SystemProperties.set(OFFLineConfigActivity.this.n0, "false");
                    } else if (OFFLineConfigActivity.this.p0 == 1) {
                        SystemProperties.set(OFFLineConfigActivity.this.n0, "true");
                    }
                    OFFLineConfigActivity oFFLineConfigActivity2 = OFFLineConfigActivity.this;
                    oFFLineConfigActivity2.h1(oFFLineConfigActivity2.b1);
                    BydLogHelper.logD("OFFLineConfigActivity", "MESSAGE_MCU_RESET");
                    break;
                case 1003:
                    OFFLineConfigActivity.this.t1();
                    mg.b(OFFLineConfigActivity.this.e0, OFFLineConfigActivity.this.e0.getString(R.string.offline_write_success));
                    break;
                case 1004:
                    int i = message.arg1;
                    if (i == 1) {
                        mg.b(OFFLineConfigActivity.this.e0, OFFLineConfigActivity.this.e0.getString(R.string.offline_write_f1a1_fail));
                    } else if (i == 2) {
                        mg.b(OFFLineConfigActivity.this.e0, OFFLineConfigActivity.this.e0.getString(R.string.offline_write_2100_fail));
                    }
                    break;
            }
        }
    }

    public interface d {
        void a(int i, boolean z);

        void b(int i, int i2);

        void c(int i, int i2);
    }

    public final void f() {
        this.v0.setOnClickListener(this);
        this.w0.setOnClickListener(this);
        this.u0.setOnClickListener(this);
    }

    public String f1(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString + " ");
        }
        return sb.toString().toUpperCase();
    }

    public final void g1() {
        this.f1 = (byte) -1;
        this.g1 = (byte) -1;
        this.h1 = (byte) -1;
        this.i1 = (byte) -1;
        this.j1 = (byte) -1;
        this.k1 = (byte) -1;
        this.l1 = (byte) -1;
        this.m1 = (byte) -1;
        ArrayList<Byte> arrayList = new ArrayList<>();
        arrayList.add(this.f1);
        arrayList.add(this.g1);
        arrayList.add(this.h1);
        arrayList.add(this.i1);
        arrayList.add(this.j1);
        arrayList.add(this.k1);
        arrayList.add(this.l1);
        arrayList.add(this.m1);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            BydLogHelper.logD("OFFLineConfigActivity", " data =" + arrayList.get(i2) + ",i =" + i2);
        }
        this.c1.writeConfigurationWordInfo((short) -3679, arrayList);
    }

    public final void h1(String str) {
        PackageManager packageManager = this.e0.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        BydLogHelper.logD("OFFLineConfigActivity", "----factory----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.e0.startService(intent);
    }

    public final void i1() {
        this.r0 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
    }

    public final void initData() {
        this.s1.clear();
        this.d1 = new b();
        BydLogHelper.logD("OFFLineConfigActivity", "onReadConfigurationWordInfo OFFLINE_DID = 8448");
        this.c1.readConfigurationWordInfo((short) 8448);
    }

    public final void initView() {
        this.j0 = (LinearLayout) findViewById(R.id.offline_config_layout);
        this.l0 = (TextView) findViewById(R.id.offline_config_data);
        this.t0 = (LinearLayout) findViewById(R.id.offline_config_pwd_layout);
        this.u0 = (Button) findViewById(R.id.offline_config_enter);
        this.s0 = (EditText) findViewById(R.id.offline_config_password);
        this.i0 = (LinearLayout) findViewById(R.id.offline_config_data_change_layout);
        this.k0 = (TextView) findViewById(R.id.offline_config_data_change_txt);
        this.v0 = (Button) findViewById(R.id.offline_config_data_change_modify_btn);
        this.w0 = (Button) findViewById(R.id.offline_config_data_change_reset_btn);
        this.m0 = (ListView) findViewById(R.id.offline_config_list_view);
    }

    @SuppressLint({"SetTextI18n"})
    public final void j1() {
        BydLogHelper.logD("OFFLineConfigActivity", "getOffLineConfig: " + f1(null));
        this.l0.setText(this.e0.getString(R.string.offline_config_data) + this.e0.getString(R.string.offline_config_toast_getconfig_err));
    }

    public final byte k1(byte b2, int i2, int i3) {
        return (byte) (b2 | ((i2 | 0) << i3));
    }

    public final List<String> l1(int i2) {
        String[] stringArray = getResources().getStringArray(i2);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < stringArray.length; i3++) {
            if (stringArray[i3] != null) {
                arrayList.add(stringArray[i3]);
            }
        }
        return arrayList;
    }

    public final void m1(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public final byte n1(byte b2, int i2, int i3) {
        return (byte) ((b2 & i2) >> i3);
    }

    public void o1() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.offline_config_data_change_modify_btn) {
            LinearLayout linearLayout = this.i0;
            if (linearLayout == null) {
                BydLogHelper.logD("OFFLineConfigActivity", "offline_config_data_change_modify_btn");
                return;
            } else {
                linearLayout.setVisibility(4);
                q1();
                return;
            }
        }
        if (id != R.id.offline_config_enter) {
            return;
        }
        if (!"111".equals(this.s0.getText().toString())) {
            r1(this.e0.getString(R.string.offline_config_password_err));
            return;
        }
        this.f0 = true;
        initData();
        m1(this.s0);
        this.t0.setVisibility(8);
        if (this.i0.getVisibility() == 8) {
            this.i0.setVisibility(0);
        }
        if (this.j0.getVisibility() == 8) {
            this.j0.setVisibility(0);
        }
        o1();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f0 = false;
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.offline_config);
        this.e0 = this;
        initView();
        f();
        j1();
        o1();
        i1();
        this.c1 = BYDAutoTestDevice.getInstance(this);
        this.f0 = true;
        initData();
        m1(this.s0);
        this.t0.setVisibility(8);
        if (this.i0.getVisibility() == 8) {
            this.i0.setVisibility(0);
        }
        if (this.j0.getVisibility() == 8) {
            this.j0.setVisibility(0);
        }
        o1();
        this.n1 = BYDAutoPowerDevice.getInstance(this);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.g0 = null;
        this.h0 = null;
        this.c1.unregisterListener(this.d1);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BYDAutoTestDevice bYDAutoTestDevice = this.c1;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.registerListener(this.d1);
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.g0 = null;
        this.h0 = null;
        this.c1.unregisterListener(this.d1);
    }

    public final ig p1(int i2, String str, List<String> list, int i3, String str2, String str3) {
        ig igVar = new ig();
        igVar.m(i2);
        igVar.j(str);
        igVar.l(str3);
        if (i2 == 1) {
            igVar.n(str2);
        } else if (i2 == 2) {
            igVar.n(str2);
            igVar.k(list);
            igVar.i(i3);
        } else if (i2 == 3) {
            igVar.n(str2);
            igVar.i(i3);
        } else if (i2 == 4) {
            igVar.n(str2);
            igVar.k(list);
            igVar.i(i3);
        }
        igVar.h(this.f0);
        return igVar;
    }

    public final void q1() {
        g1();
        this.f1 = (byte) 0;
        this.g1 = (byte) 0;
        this.h1 = (byte) 0;
        this.i1 = (byte) 0;
        this.j1 = (byte) 0;
        this.k1 = (byte) 0;
        this.l1 = (byte) 0;
        this.m1 = (byte) 0;
        int iB = this.x0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB, 7));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig auxIn : " + iB);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_AUX_IN", iB);
        int iB2 = this.y0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB2, 6));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig dabPowerSupplyMode : " + iB2);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_DAB_POWER_SUPPLY_MODE", iB2);
        int iB3 = this.z0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB3, 5));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig fmAmPowerSupplyMode : " + iB3);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_FM_AM_POWER_SUPPLY_MODE", iB3);
        int iB4 = this.A0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB4, 4));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig avmApaMdcPowerSupplyModeGroupIndex : " + iB4);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_AVM_APA_MDC_POWER", iB4);
        int iB5 = this.B0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB5, 3));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig dmsPowerSupplyModeGroupIndex : " + iB5);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_DMS_POWER_SUPPLY_MODE", iB5);
        int iB6 = this.D0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB6, 2));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig imsPowerSupplyModeGroupIndex : " + iB6);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_IMS_POWER_SUPPLY_MODE", iB6);
        int iB7 = this.C0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB7, 1));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig omsPowerSupplyModeGroupIndex : " + iB7);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_OMS_POWER_SUPPLY_MODE", iB7);
        int iB8 = this.E0.b();
        this.f1 = Byte.valueOf(k1(this.f1.byteValue(), iB8, 0));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig streamingPowerSupplyModeGroupIndex : " + iB8);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_STREAMING_VIDEO_POWER_SUPPLY_MODE", iB8);
        int iB9 = this.G0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB9, 7));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig sxmGroupIndex : " + iB9);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_SXM", iB9);
        int iB10 = this.F0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB10, 6));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig dvrPowerSupplyModeGroupIndex : " + iB10);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_DVR_POWER_SUPPLY_MODE", iB10);
        int iB11 = this.H0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB11, 5));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig faceIdPowerSupplyModeGroupIndex : " + iB11);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_FACE_ID_POWER_SUPPLY_MODE", iB11);
        int iB12 = this.I0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB12, 4));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig tBoxGroupIndex : " + iB12);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_T_BOX", iB12);
        int iB13 = this.J0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB13, 3));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig singleBtGroupIndex : " + iB13);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_SINGLE_BT", iB13);
        int iB14 = this.K0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB14, 2));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig usVersionGroupIndex : " + iB14);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_US_VERSION", iB14);
        int iB15 = this.L0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB15, 1));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig carplayWiredGroupIndex : " + iB15);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_CARPLAY_WIRED", iB15);
        int iB16 = this.M0.b();
        this.g1 = Byte.valueOf(k1(this.g1.byteValue(), iB16, 0));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig swcTypeGroupIndex : " + iB16);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_SWC_TYPE", iB16);
        this.h1 = Byte.valueOf(k1(this.h1.byteValue(), this.W0.b(), 0));
        int iB17 = this.N0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB17, 7));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig rdsGroupIndex : " + iB17);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_RDS", iB17);
        int iB18 = this.O0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB18, 6));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig dabGroupIndex : " + iB18);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_DAB", iB18);
        int iB19 = this.P0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB19, 5));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig hdGroupIndex : " + iB19);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_HD", iB19);
        int iB20 = this.Q0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB20, 4));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig drmGroupIndex : " + iB20);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_DRM", iB20);
        int iB21 = this.R0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB21, 3));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig steeringWheelPositionGroupIndex : " + iB21);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_STEERING_WHEEL_POSITION", iB21);
        this.p0 = iB21;
        int iB22 = this.S0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB22, 2));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig amGroupIndex : " + iB22);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_AM", iB22);
        int iB23 = this.T0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB23, 1));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig singleFmGroupIndex : " + iB23);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_SINGLE_FM", iB23);
        int iB24 = this.U0.b();
        this.j1 = Byte.valueOf(k1(this.j1.byteValue(), iB24, 0));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig twoFmGroupIndex : " + iB24);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_TWO_FM", iB24);
        int iB25 = this.V0.b();
        this.k1 = Byte.valueOf(k1(this.k1.byteValue(), iB25, 6));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig micCountGroupIndex : " + iB25);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_MIC_COUNT", iB25);
        int iB26 = this.X0.b();
        this.k1 = Byte.valueOf(k1(this.k1.byteValue(), iB26, 4));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig vehicleTypeGroupIndex : " + iB26);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_VEHICLE_TYPE", iB26);
        int iB27 = this.Y0.b();
        this.q0 = iB27;
        this.i1 = Byte.valueOf(k1(this.i1.byteValue(), iB27, 0));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig areaGroupIndex : " + iB27);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_AREA", iB27);
        int iB28 = this.Z0.b();
        this.k1 = Byte.valueOf(k1(this.k1.byteValue(), iB28, 0));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig speakerGroupIndex : " + iB28);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_SPEAKER", iB28);
        int iB29 = this.a1.b();
        this.l1 = Byte.valueOf(k1(this.l1.byteValue(), iB29, 0));
        BydLogHelper.logD("OFFLineConfigActivity", "setOFFLineConfig audioSourceGroupIndex : " + iB29);
        Settings.Global.putInt(this.e0.getContentResolver(), "GLOBAL_AUDIO_SOURCE", iB29);
    }

    public final void r1(String str) {
        mg.b(this.e0, str);
    }

    public final void s1() {
        this.m1 = Byte.valueOf((byte) (((((((this.f1.byteValue() + this.g1.byteValue()) + this.h1.byteValue()) + this.i1.byteValue()) + this.j1.byteValue()) + this.k1.byteValue()) + this.l1.byteValue()) ^ 85));
    }

    public final void t1() {
        s1();
        ArrayList<Byte> arrayList = new ArrayList<>();
        arrayList.add(this.f1);
        arrayList.add(this.g1);
        arrayList.add(this.h1);
        arrayList.add(this.i1);
        arrayList.add(this.j1);
        arrayList.add(this.k1);
        arrayList.add(this.l1);
        arrayList.add(this.m1);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            BydLogHelper.logD("OFFLineConfigActivity", " data =" + arrayList.get(i2) + ",i =" + i2);
        }
        this.c1.writeConfigurationWordInfo((short) 8448, arrayList);
    }
}
