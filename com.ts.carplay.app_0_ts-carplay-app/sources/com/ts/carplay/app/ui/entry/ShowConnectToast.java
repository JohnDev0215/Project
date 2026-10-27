package com.ts.carplay.app.ui.entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.ts.car.core.ServiceProvider;
import com.ts.carplay.app.R;
import ts.car.service.carplay.ICarPlayDeviceListManager;

/* JADX INFO: loaded from: classes.dex */
public class ShowConnectToast extends Activity implements View.OnClickListener {
    public static final int START_CONNECT_CARPLAY = 2;
    private static final String TAG = "ShowConnectToast";
    private Button btnCancel;
    private Button btnConnent;
    private ICarPlayDeviceListManager mCarPlayDeviceManager = null;
    private Handler mHandler;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_toast);
        this.mCarPlayDeviceManager = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, this);
        initView();
        this.btnConnent.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        this.btnConnent = (Button) findViewById(R.id.btn_connect);
        this.btnCancel = (Button) findViewById(R.id.btn_disconnect);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id != R.id.btn_connect) {
            if (id == R.id.btn_disconnect) {
                finish();
                return;
            }
            return;
        }
        Log.e(TAG, "start connect carplay");
        finish();
        Intent intent = getIntent();
        try {
            String serialNumber = intent.getStringExtra("serialNumber");
            this.mCarPlayDeviceManager.connectCarPlay(serialNumber, true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
