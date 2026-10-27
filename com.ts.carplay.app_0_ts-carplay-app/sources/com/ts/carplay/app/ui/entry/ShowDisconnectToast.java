package com.ts.carplay.app.ui.entry;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import com.ts.carplay.app.R;
import ts.car.service.carplay.ICarPlayDeviceListManager;

/* JADX INFO: loaded from: classes.dex */
public class ShowDisconnectToast extends Activity implements View.OnClickListener {
    private static final String TAG = "ShowDisconnectToast";
    private static final int TIME = 1000;
    private Button btnCancel;
    private Button btnConnent;
    private ICarPlayDeviceListManager mCarPlayDeviceManager = null;
    private Handler mHandler;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_toast);
        initView();
        this.btnConnent.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);
        showToast();
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
        if (id == R.id.btn_connect) {
            finish();
        } else if (id == R.id.btn_disconnect) {
            finish();
        }
    }

    private void showToast() {
        new Handler().postDelayed(new Runnable() { // from class: com.ts.carplay.app.ui.entry.ShowDisconnectToast.1
            @Override // java.lang.Runnable
            public void run() {
                ShowDisconnectToast.this.finish();
            }
        }, 1000L);
    }
}
