package com.byd.engineermode.ui.functiontest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CPUTestActivity extends BaseActivity {
    float[] A;
    ListView B;
    ListView C;
    ListView D;
    ListView E;
    private List<String> F;
    private List<String> G;
    private List<String> H;
    private List<String> I;
    private com.byd.engineermode.a.a.a J;
    private com.byd.engineermode.a.a.a K;
    private com.byd.engineermode.a.a.a L;
    private com.byd.engineermode.a.a.a M;
    a v;
    float[] w;
    float[] x;
    float[] y;
    float[] z;

    private class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<CPUTestActivity> f2388a;

        a(CPUTestActivity cPUTestActivity) {
            this.f2388a = new WeakReference<>(cPUTestActivity);
        }

        @Override // android.os.Handler
        @SuppressLint({"SetTextI18n"})
        public void handleMessage(Message message) {
            float[] fArr;
            float[] fArr2;
            float[] fArr3;
            float[] fArr4;
            if (this.f2388a.get() == null) {
                return;
            }
            CPUTestActivity cPUTestActivity = CPUTestActivity.this;
            cPUTestActivity.A = cPUTestActivity.z;
            cPUTestActivity.z = cPUTestActivity.y;
            cPUTestActivity.y = cPUTestActivity.x;
            cPUTestActivity.x = cPUTestActivity.w;
            cPUTestActivity.w = CPUTestActivity.V();
            CPUTestActivity cPUTestActivity2 = CPUTestActivity.this;
            float[] fArr5 = cPUTestActivity2.w;
            if (fArr5 != null && (fArr4 = cPUTestActivity2.x) != null) {
                cPUTestActivity2.W(fArr5, fArr4, cPUTestActivity2.J, "CPU last1", CPUTestActivity.this.F);
            }
            CPUTestActivity cPUTestActivity3 = CPUTestActivity.this;
            float[] fArr6 = cPUTestActivity3.x;
            if (fArr6 != null && (fArr3 = cPUTestActivity3.y) != null) {
                cPUTestActivity3.W(fArr6, fArr3, cPUTestActivity3.K, "CPU last2", CPUTestActivity.this.G);
            }
            CPUTestActivity cPUTestActivity4 = CPUTestActivity.this;
            float[] fArr7 = cPUTestActivity4.y;
            if (fArr7 != null && (fArr2 = cPUTestActivity4.z) != null) {
                cPUTestActivity4.W(fArr7, fArr2, cPUTestActivity4.L, "CPU last3", CPUTestActivity.this.H);
            }
            CPUTestActivity cPUTestActivity5 = CPUTestActivity.this;
            float[] fArr8 = cPUTestActivity5.z;
            if (fArr8 != null && (fArr = cPUTestActivity5.A) != null) {
                cPUTestActivity5.W(fArr8, fArr, cPUTestActivity5.M, "CPU last4", CPUTestActivity.this.I);
            }
            Log.d("TAG", "cpuTimeNew.length/2 = ");
            CPUTestActivity.this.v.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    public static float[] V() {
        float[] fArr = new float[18];
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            for (int i = 0; i < 9; i++) {
                String line = bufferedReader.readLine();
                Log.d("TAG", "load = " + line);
                String[] strArrSplit = line.split(" ");
                Log.d("TAG", "cpuInfos = " + strArrSplit.length + ",cpuInfos[0] = " + strArrSplit[0] + ",cpuInfos[1] = " + strArrSplit[1] + ",cpuInfos[2] = " + strArrSplit[2] + ",cpuInfos[3] = " + strArrSplit[3] + ",cpuInfos[4] = " + strArrSplit[4] + ",cpuInfos[5] = " + strArrSplit[5] + ",cpuInfos[6] = " + strArrSplit[6] + ",cpuInfos[7] = " + strArrSplit[7] + ",cpuInfos[8] = " + strArrSplit[8]);
                if (i == 0) {
                    fArr[0] = Long.parseLong(strArrSplit[8]) + Long.parseLong(strArrSplit[2]) + Long.parseLong(strArrSplit[3]) + Long.parseLong(strArrSplit[4]) + Long.parseLong(strArrSplit[5]) + Long.parseLong(strArrSplit[6]) + Long.parseLong(strArrSplit[7]);
                    fArr[1] = Long.parseLong(strArrSplit[5]);
                    Log.d("TAG", "cpuTimes[0]  = " + fArr[0] + ", cpuTimes[1] = " + fArr[1]);
                } else {
                    int i2 = i * 2;
                    fArr[i2] = Long.parseLong(strArrSplit[1]) + Long.parseLong(strArrSplit[2]) + Long.parseLong(strArrSplit[3]) + Long.parseLong(strArrSplit[4]) + Long.parseLong(strArrSplit[5]) + Long.parseLong(strArrSplit[6]) + Long.parseLong(strArrSplit[7]);
                    fArr[i2 + 1] = Long.parseLong(strArrSplit[4]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W(float[] fArr, float[] fArr2, com.byd.engineermode.a.a.a aVar, String str, List<String> list) {
        list.clear();
        list.add(0, str);
        for (int i = 0; i < fArr.length / 2; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("cpuTimeNew.length/2 = ");
            sb.append(fArr.length / 2);
            sb.append(", cpuTimeNew[2*i+1] = ");
            int i2 = i * 2;
            int i3 = i2 + 1;
            sb.append(fArr[i3]);
            sb.append(", cpuTimeOld[2*i] = ");
            sb.append(fArr2[i2]);
            Log.d("TAG", sb.toString());
            if (i == 0) {
                list.add(1, "CPU All:" + ((100.0f - (((fArr[i3] - fArr2[i3]) / (fArr[i2] - fArr2[i2])) * 100.0f)) * 8.0f) + "%");
            }
            if (i != 0) {
                list.add(i, "CPU  " + i + "  :" + (100.0f - (((fArr[i3] - fArr2[i3]) / (fArr[i2] - fArr2[i2])) * 100.0f)) + "%");
            }
        }
        aVar.a(list);
    }

    public void OnButtonClick(View view) {
        if (view.getId() == R.id.test_pass_btn) {
            J();
        } else if (view.getId() == R.id.test_retry_btn) {
            K();
        } else if (view.getId() == R.id.test_fail_btn) {
            I();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cpu_test);
        this.B = (ListView) findViewById(R.id.cpu_1);
        this.C = (ListView) findViewById(R.id.cpu_2);
        this.D = (ListView) findViewById(R.id.cpu_3);
        this.E = (ListView) findViewById(R.id.cpu_4);
        this.F = new ArrayList();
        this.G = new ArrayList();
        this.H = new ArrayList();
        this.I = new ArrayList();
        this.J = new com.byd.engineermode.a.a.a(this, this.F);
        this.K = new com.byd.engineermode.a.a.a(this, this.G);
        this.L = new com.byd.engineermode.a.a.a(this, this.H);
        this.M = new com.byd.engineermode.a.a.a(this, this.I);
        this.B.setAdapter((ListAdapter) this.J);
        this.C.setAdapter((ListAdapter) this.K);
        this.D.setAdapter((ListAdapter) this.L);
        this.E.setAdapter((ListAdapter) this.M);
        a aVar = new a(this);
        this.v = aVar;
        aVar.sendEmptyMessage(1);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        com.byd.engineermode.utils.a.b("TAG", "onRestart");
        this.v.sendEmptyMessage(1);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        com.byd.engineermode.utils.a.b("TAG", "onStop");
        this.v.removeMessages(1);
    }
}
