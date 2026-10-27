package com.ts.appservice.power.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ts.appservice.power.R;
import com.ts.lib.power.data.CarPowerDarkNightDayState;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.data.CarPowerWorkModeStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity {
    private static final String TAG = "CarPowerAvtivity";
    private Button mBtnSetDisplay;
    private CarPowerManager mCarPowerManager;
    private CarPowerEventListener mModeChangeListener = new AnonymousClass3();
    private int mNum;
    private View.OnTouchListener mOnTouchListener;
    private TextView mTv;

    static /* synthetic */ int access$112(MainActivity x0, int x1) {
        int i = x0.mNum + x1;
        x0.mNum = i;
        return i;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mCarPowerManager = CarPowerManager.getInstance(this, this.mModeChangeListener);
        initView();
        initListener();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        this.mTv = (TextView) findViewById(R.id.tv);
        Button button = (Button) findViewById(R.id.btn_set_dispaly);
        this.mBtnSetDisplay = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ts.appservice.power.activity.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.d(MainActivity.TAG, "onClick");
                if (MainActivity.this.mCarPowerManager != null) {
                    Log.d(MainActivity.TAG, "onClick mCarPowerManager.setBrightnessState ");
                    MainActivity.this.mCarPowerManager.setBrightnessState(0, 2);
                }
            }
        });
    }

    private void initListener() {
        Log.d(TAG, "initListener");
        this.mOnTouchListener = new View.OnTouchListener() { // from class: com.ts.appservice.power.activity.MainActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i(MainActivity.TAG, "initListener v.getId() : " + view.getId() + " event.getAction() : " + motionEvent.getAction());
                if (view.getId() == R.id.btn_set_dispaly && motionEvent.getAction() == 1 && MainActivity.this.mCarPowerManager != null) {
                    Log.d(MainActivity.TAG, "touch");
                    return false;
                }
                return false;
            }
        };
    }

    /* JADX INFO: renamed from: com.ts.appservice.power.activity.MainActivity$3, reason: invalid class name */
    class AnonymousClass3 implements CarPowerEventListener {
        AnonymousClass3() {
        }

        public void onCarPowerWorkModeChangeEvent(CarPowerWorkModeStatus carPowerWorkModeStatus) {
            Log.d(MainActivity.TAG, "onCarPowerWorkModeChangeEvent carPowerWorkModeStatus value : " + carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal() + "");
            MainActivity.access$112(MainActivity.this, 1);
            Log.d(MainActivity.TAG, "onCarPowerWorkModeChangeEvent mNum : " + MainActivity.this.mNum);
            MainActivity.this.runOnUiThread(new Runnable() { // from class: com.ts.appservice.power.activity.-$$Lambda$MainActivity$3$vA-GndhSGUIAflT0TokZwBHR4DE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCarPowerWorkModeChangeEvent$0$MainActivity$3();
                }
            });
        }

        public /* synthetic */ void lambda$onCarPowerWorkModeChangeEvent$0$MainActivity$3() {
            MainActivity.this.mTv.setText(String.valueOf(MainActivity.this.mNum));
        }

        public void onCarPowerSignalChangeEvent(CarPowerSignalStatus carPowerSignalStatus) {
            Log.d(MainActivity.TAG, "onCarPowerSignalChangeEvent carPowerSignalStatus :" + carPowerSignalStatus.getCarPowerSignalStatusEnum().getVal() + " signal : " + carPowerSignalStatus.getCarPowerSignalStatusEnum());
        }

        public void onCarPowerEvent(int type, int state, CarPowerData data) {
            Log.d(MainActivity.TAG, "onCarPowerEvent type : " + type + " state : " + state);
            if (data != null) {
                Log.d(MainActivity.TAG, "onCarPowerEvent data : " + data + "");
                if (type == -2147483647) {
                    Bundle bundle = data.getBundle();
                    CarPowerDarkNightDayState darkNightDayState = new CarPowerDarkNightDayState();
                    darkNightDayState.createFromBundle(bundle);
                    int currentDarkNightDayState = darkNightDayState.getdarkNightDayState();
                    Log.d(MainActivity.TAG, "onCarPowerEvent currentDarkNightDayState : " + currentDarkNightDayState);
                }
            }
        }

        public void onCarPowerDisplayEvent(int type, int displayPanelId, int value) {
            Log.d(MainActivity.TAG, "onCarPowerDisplayEvent displayPanelId :" + displayPanelId + " value : " + value);
        }

        public void onCarPowerServiceConnected() {
            Log.d(MainActivity.TAG, "onCarPowerServiceConnected");
        }

        public void onCarPowerServiceDisconnected() {
            Log.d(MainActivity.TAG, "onCarPowerServiceDisconnected");
        }
    }
}
