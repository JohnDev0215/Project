package com.byd.engineermode.ui.activity;

import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class RadioTestActivity extends BaseActivity {
    private Handler A;
    private RadioTuner B;
    private RadioManager.ModuleProperties C;
    private TextView D;
    private TextView E;
    private int F = 0;
    private HashMap<String, String> v;
    private HashMap<String, String> w;
    private a x;
    private RadioManager y;
    private HandlerThread z;

    class a extends RadioTuner.Callback {

        /* JADX INFO: renamed from: com.byd.engineermode.ui.activity.RadioTestActivity$a$a, reason: collision with other inner class name */
        class RunnableC0068a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Map f2354b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f2355c;

            RunnableC0068a(Map map, String str) {
                this.f2354b = map;
                this.f2355c = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (RadioTestActivity.this.F % 2 == 0) {
                    RadioTestActivity.this.D.setText("数据一：\n" + ((String) this.f2354b.get(this.f2355c)));
                    return;
                }
                RadioTestActivity.this.E.setText("数据二：\n" + ((String) this.f2354b.get(this.f2355c)));
            }
        }

        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(Map map) {
            for (String str : map.keySet()) {
                if ("com.bosch.testmodedata".equals(str)) {
                    Log.d("RadioTestActivity", "onParametersUpdated: :key  " + str + ":value  " + ((String) map.get(str)));
                    RadioTestActivity.this.runOnUiThread(new RunnableC0068a(map, str));
                }
            }
        }

        public void onParametersUpdated(final Map<String, String> map) {
            RadioTestActivity.N(RadioTestActivity.this);
            Log.d("RadioTestActivity", "onParametersUpdated:1111  showFlag % 2 = " + (RadioTestActivity.this.F % 2) + ", showFlag = " + RadioTestActivity.this.F);
            RadioTestActivity.this.A.post(new Runnable() { // from class: com.byd.engineermode.ui.activity.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2362b.b(map);
                }
            });
        }
    }

    static /* synthetic */ int N(RadioTestActivity radioTestActivity) {
        int i = radioTestActivity.F;
        radioTestActivity.F = i + 1;
        return i;
    }

    private void R() {
        HandlerThread handlerThread = new HandlerThread("RadioTestActivity");
        this.z = handlerThread;
        handlerThread.start();
        this.y = (RadioManager) getSystemService("broadcastradio");
        ArrayList arrayList = new ArrayList();
        int iListModules = this.y.listModules(arrayList);
        if (iListModules != 0) {
            Log.w("RadioTestActivity", "Couldn't get radio module list: " + iListModules);
            return;
        }
        if (arrayList.size() == 0) {
            Log.i("RadioTestActivity", "No radio modules on this device");
            return;
        }
        this.C = (RadioManager.ModuleProperties) arrayList.get(0);
        this.x = new a();
        this.A = new Handler(this.z.getLooper());
    }

    private void S() {
        HashMap<String, String> map = new HashMap<>();
        this.v = map;
        map.put("com.bosch.diag.operations", "{\"selectedOperation\":1,\"testmode\":1}");
        HashMap<String, String> map2 = new HashMap<>();
        this.w = map2;
        map2.put("com.bosch.diag.operations", "{\"selectedOperation\":1,\"testmode\":0}");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U() {
        this.F = 0;
        this.B.setParameters(this.v);
        Log.d("RadioTestActivity", "mRadioTuner.setParameters");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W(View view) {
        try {
            this.B = this.y.openTuner(this.C.getId(), (RadioManager.BandConfig) null, true, this.x, this.A);
            this.A.postDelayed(new Runnable() { // from class: com.byd.engineermode.ui.activity.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2364b.U();
                }
            }, 1000L);
        } catch (Exception e) {
            Log.e("RadioTestActivity", "mRadioTuner.setParameters(mOpen): ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Y(View view) {
        try {
            RadioTuner radioTuner = this.B;
            if (radioTuner != null) {
                radioTuner.setParameters(this.w);
            } else {
                Log.e("RadioTestActivity", "mRadioTuner == null");
            }
        } catch (Exception e) {
            Log.e("RadioTestActivity", "mRadioTuner.setParameters(mClose): ", e);
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        S();
        R();
        setContentView(R.layout.video_activity_main);
        this.D = (TextView) findViewById(R.id.textview1);
        this.E = (TextView) findViewById(R.id.textview2);
        Button button = (Button) findViewById(R.id.button_01);
        Button button2 = (Button) findViewById(R.id.button_02);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.byd.engineermode.ui.activity.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2360b.W(view);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.engineermode.ui.activity.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2361b.Y(view);
            }
        });
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            RadioTuner radioTuner = this.B;
            if (radioTuner != null) {
                radioTuner.setParameters(this.w);
            } else {
                Log.e("RadioTestActivity", "mRadioTuner == null");
            }
        } catch (Exception e) {
            Log.e("RadioTestActivity", "mRadioTuner.setParameters(mClose): ", e);
        }
    }
}
