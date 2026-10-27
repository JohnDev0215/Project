package c.a.b.c;

import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import c.a.b.d.k;

/* JADX INFO: compiled from: SpData.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f2329a;

    public static a k() {
        if (f2329a == null) {
            synchronized (a.class) {
                if (f2329a == null) {
                    f2329a = new a();
                }
            }
        }
        return f2329a;
    }

    public int A() {
        return k.b("update_mode", -1);
    }

    public String B() {
        return k.e("update_uuid", "");
    }

    public boolean C() {
        return k.a("user_click_check_state", false);
    }

    public String D() {
        return k.e("user_token", "");
    }

    public String E() {
        return k.e("vehicle_iccid", "");
    }

    public void F(String str) {
        k.j("acknowledgements", str);
    }

    public void G(long j) {
        k.i("appointment_time", j);
    }

    public void H(String str) {
        k.j("auto_vin", str);
    }

    public void I(String str) {
        k.j("batch_id", str);
    }

    public void J(String str) {
        k.j("cur_vehicle_version", str);
    }

    public void K(String str) {
        k.j("download_translate_time", str);
    }

    public void L(int i2) {
        k.h("ecu_estimate", i2);
    }

    public void M(int i2) {
        k.h("estimate", i2);
    }

    public void N(String str) {
        k.j("function_addition", str);
    }

    public void O(String str) {
        k.j("function_optimization", str);
    }

    public void P(String str) {
        k.j("latest_vehicle_version", str);
    }

    public void Q(String str) {
        k.j("msg_report_path", str);
    }

    public void R(int i2) {
        k.h("normal_code", i2);
    }

    public void S(int i2) {
        k.h("factory_update_over_time", i2);
    }

    public void T(String str) {
        k.j("time_for_power_on_first", str);
    }

    public void U(int i2) {
        k.h("priority_type", i2);
    }

    public void V(int i2) {
        k.h("qnx_normal_code", i2);
    }

    public void W(int i2) {
        k.h("show_count_when_car_off", i2);
    }

    public void X(int i2) {
        k.h("show_count_when_car_on", i2);
    }

    public void Y(String str) {
        k.j("strategy_type", str);
    }

    public void Z(String str) {
        k.j("strategy_ver", str);
    }

    public String a() {
        return k.e("acknowledgements", "");
    }

    public void a0(boolean z) {
        k.g("update_flag", Boolean.valueOf(z));
    }

    public long b() {
        return k.c("appointment_time", 0L);
    }

    public void b0(String str) {
        k.j("update_lowest_soc", str);
    }

    public String c() {
        return k.e("auto_vin", "");
    }

    public void c0(String str) {
        k.j("update_lowest_temperature", str);
    }

    public String d() {
        return k.e("batch_id", "batch_id");
    }

    public void d0(String str) {
        k.j("update_lowest_voltage", str);
    }

    public String e() {
        return k.e("cur_vehicle_version", "V1.0.0");
    }

    public void e0(int i2) {
        k.h("update_mode", i2);
    }

    public String f() {
        return k.e("download_translate_time", "");
    }

    public void f0(String str) {
        k.j("update_uuid", str);
    }

    public int g() {
        return k.b("ecu_estimate", 0);
    }

    public void g0(boolean z) {
        k.g("user_click_check_state", Boolean.valueOf(z));
    }

    public int h() {
        return k.b("estimate", 0);
    }

    public void h0(String str) {
        k.j("user_token", str);
    }

    public String i() {
        return k.e("function_addition", "");
    }

    public void i0(String str) {
        k.j("vehicle_iccid", str);
    }

    public String j() {
        return k.e("function_optimization", "");
    }

    public String l() {
        return k.e("latest_vehicle_version", "V1.0.0");
    }

    public String m() {
        return k.e("msg_report_path", "https://msg-firmware-cdn-eu.byd.auto:5113/api/bydota-msgreport/otaService/messageReport");
    }

    public int n() {
        return k.b("normal_code", -1);
    }

    public int o() {
        return k.b("factory_update_over_time", 0);
    }

    public String p() {
        return k.e("time_for_power_on_first", "");
    }

    public int q() {
        return k.b("priority_type", 0);
    }

    public int r() {
        return k.b("qnx_normal_code", -1);
    }

    public int s() {
        return k.b("show_count_when_car_off", 0);
    }

    public int t() {
        return k.b("show_count_when_car_on", 0);
    }

    public String u() {
        return k.e("strategy_type", "");
    }

    public String v() {
        return k.e("strategy_ver", "");
    }

    public boolean w() {
        return k.a("update_flag", false);
    }

    public String x() {
        return k.e("update_lowest_soc", "20");
    }

    public String y() {
        return k.e("update_lowest_temperature", "30");
    }

    public String z() {
        return k.e("update_lowest_voltage", TimeManagerImpl.TIME_FORMAT_TWELVE);
    }
}
