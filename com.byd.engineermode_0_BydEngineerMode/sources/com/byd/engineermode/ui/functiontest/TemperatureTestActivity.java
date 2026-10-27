package com.byd.engineermode.ui.functiontest;

import android.annotation.SuppressLint;
import android.hardware.bydauto.tools.AbsAutoDiagnoseListener;
import android.hardware.bydauto.tools.BYDAutoDiagnoseToolDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import butterknife.R;
import com.byd.engineermode.DiagnoseApplication;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TemperatureTestActivity extends BaseActivity {
    private List<String> A;
    private com.byd.engineermode.a.a.a B;
    private com.byd.engineermode.a.a.a C;
    private com.byd.engineermode.a.a.a D;
    float[] E;
    float[] F;
    private b G;
    private AbsAutoDiagnoseListener H;
    private BYDAutoDiagnoseToolDevice I;
    ListView v;
    ListView w;
    ListView x;
    private List<String> y;
    private List<String> z;

    class a extends AbsAutoDiagnoseListener {
        a() {
        }

        @Override // android.hardware.bydauto.tools.AbsAutoDiagnoseListener
        public void onHWTemperature(Bundle bundle) {
            super.onHWTemperature(bundle);
            com.byd.engineermode.utils.a.a("onHWTemperature bundle = " + bundle);
            if (bundle != null) {
                Message message = new Message();
                message.what = 1002;
                message.setData(bundle);
                TemperatureTestActivity.this.G.handleMessage(message);
            }
        }

        @Override // android.hardware.bydauto.tools.AbsAutoDiagnoseListener
        public void onSynchronousQNXData(int i) {
            super.onSynchronousQNXData(i);
            com.byd.engineermode.utils.a.a("onSynchronousQNXData state = " + i);
        }
    }

    private class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<TemperatureTestActivity> f2420a;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                TemperatureTestActivity.this.B.a(TemperatureTestActivity.this.y);
                TemperatureTestActivity.this.C.a(TemperatureTestActivity.this.z);
                TemperatureTestActivity.this.D.a(TemperatureTestActivity.this.A);
            }
        }

        b(TemperatureTestActivity temperatureTestActivity) {
            this.f2420a = new WeakReference<>(temperatureTestActivity);
        }

        @Override // android.os.Handler
        @SuppressLint({"SetTextI18n"})
        public void handleMessage(Message message) {
            int i;
            if (this.f2420a.get() == null || (i = message.what) == 1001 || i != 1002) {
                return;
            }
            TemperatureTestActivity temperatureTestActivity = TemperatureTestActivity.this;
            temperatureTestActivity.F = temperatureTestActivity.E;
            temperatureTestActivity.E = TemperatureTestActivity.V();
            String string = null;
            TemperatureTestActivity temperatureTestActivity2 = TemperatureTestActivity.this;
            if (temperatureTestActivity2.E != null && temperatureTestActivity2.F != null) {
                StringBuilder sb = new StringBuilder();
                TemperatureTestActivity temperatureTestActivity3 = TemperatureTestActivity.this;
                float[] fArr = temperatureTestActivity3.E;
                float f = fArr[1];
                float[] fArr2 = temperatureTestActivity3.F;
                sb.append((100.0f - (((f - fArr2[1]) / (fArr[0] - fArr2[0])) * 100.0f)) * 8.0f);
                sb.append("% ( 800%CPU )");
                string = sb.toString();
            }
            Bundle data = message.getData();
            String string2 = data.getString("PCB_T", "-1");
            String string3 = data.getString("SOC_T", "-1");
            String string4 = data.getString("LARGE_POWER_T", "-1");
            String string5 = data.getString("AMPLIFIER_T", "-1");
            String string6 = data.getString("TFT_T", "-1");
            com.byd.engineermode.utils.a.a("onHWTemperature PCB_T = " + string2 + ", SOC_T = " + string3 + ", LARGE_POWER_T = " + string4 + ", AMPLIFIER_T = " + string5 + ", TFT_T = " + string6);
            TemperatureTestActivity temperatureTestActivity4 = TemperatureTestActivity.this;
            temperatureTestActivity4.U(temperatureTestActivity4.A, TemperatureTestActivity.this.getString(R.string.latest_temperature_3), TemperatureTestActivity.this.z);
            TemperatureTestActivity temperatureTestActivity5 = TemperatureTestActivity.this;
            temperatureTestActivity5.U(temperatureTestActivity5.z, TemperatureTestActivity.this.getString(R.string.latest_temperature_2), TemperatureTestActivity.this.y);
            TemperatureTestActivity.this.y.clear();
            TemperatureTestActivity.this.y.add(TemperatureTestActivity.this.getString(R.string.latest_temperature_1));
            List list = TemperatureTestActivity.this.y;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(TemperatureTestActivity.this.getString(R.string.pcb_temperature));
            sb2.append(string2);
            list.add(sb2.toString());
            TemperatureTestActivity.this.y.add(TemperatureTestActivity.this.getString(R.string.soc_temperature) + string3);
            TemperatureTestActivity.this.y.add(TemperatureTestActivity.this.getString(R.string.cpu_usage_rate) + string);
            TemperatureTestActivity.this.y.add(TemperatureTestActivity.this.getString(R.string.large_power_supply_temperature) + string4);
            TemperatureTestActivity.this.y.add(TemperatureTestActivity.this.getString(R.string.amplifier_temperature) + string5);
            TemperatureTestActivity.this.y.add(TemperatureTestActivity.this.getString(R.string.tft_temperature) + string6);
            TemperatureTestActivity.this.runOnUiThread(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(List<String> list, String str, List<String> list2) {
        list.clear();
        list.add(str);
        for (int i = 1; i < list2.size(); i++) {
            list.add(list2.get(i));
        }
    }

    public static float[] V() {
        float[] fArr = new float[18];
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            String line = bufferedReader.readLine();
            Log.d("TAG", "load = " + line);
            String[] strArrSplit = line.split(" ");
            Log.d("TAG", "cpuInfos = " + strArrSplit.length + ",cpuInfos[0] = " + strArrSplit[0] + ",cpuInfos[1] = " + strArrSplit[1] + ",cpuInfos[2] = " + strArrSplit[2] + ",cpuInfos[3] = " + strArrSplit[3] + ",cpuInfos[4] = " + strArrSplit[4] + ",cpuInfos[5] = " + strArrSplit[5] + ",cpuInfos[6] = " + strArrSplit[6] + ",cpuInfos[7] = " + strArrSplit[7] + ",cpuInfos[8] = " + strArrSplit[8]);
            fArr[0] = (float) (Long.parseLong(strArrSplit[2]) + Long.parseLong(strArrSplit[3]) + Long.parseLong(strArrSplit[4]) + Long.parseLong(strArrSplit[5]) + Long.parseLong(strArrSplit[6]) + Long.parseLong(strArrSplit[7]) + Long.parseLong(strArrSplit[8]));
            fArr[1] = (float) Long.parseLong(strArrSplit[5]);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fArr;
    }

    public void OnButtonClick(View view) {
        Log.d("TemperatureTestActivity", "view.getId()  = " + view.getId());
        int id = view.getId();
        if (id == R.id.test_fail_btn) {
            I();
        } else {
            if (id != R.id.test_pass_btn) {
                return;
            }
            J();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_temperature_test);
        this.v = (ListView) findViewById(R.id.temperature_list1);
        this.w = (ListView) findViewById(R.id.temperature_list2);
        this.x = (ListView) findViewById(R.id.temperature_list3);
        this.y = new ArrayList();
        this.z = new ArrayList();
        this.A = new ArrayList();
        this.B = new com.byd.engineermode.a.a.a(this, this.y);
        this.C = new com.byd.engineermode.a.a.a(this, this.z);
        this.D = new com.byd.engineermode.a.a.a(this, this.A);
        this.v.setAdapter((ListAdapter) this.B);
        this.w.setAdapter((ListAdapter) this.C);
        this.x.setAdapter((ListAdapter) this.D);
        b bVar = new b(this);
        this.G = bVar;
        bVar.sendEmptyMessage(1001);
        this.H = new a();
        BYDAutoDiagnoseToolDevice bYDAutoDiagnoseToolDevice = BYDAutoDiagnoseToolDevice.getInstance(DiagnoseApplication.e());
        this.I = bYDAutoDiagnoseToolDevice;
        bYDAutoDiagnoseToolDevice.registerListener(this.H);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy");
        this.I.unregisterListener(this.H);
        this.H = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause");
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "onRestart");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop");
    }
}
