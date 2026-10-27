package com.byd.vrsettings.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public class CustomLogToolDialog extends ConstraintLayout {
    private static long DIFF = 15000;
    private static long lastClickTime;
    private final String TAG;
    private ImageView iv_close;
    public Listener listener;
    private Button log_beginBtn;
    private Button log_endBtn;
    private Button log_packBtn;
    private TextView log_tip;
    private Context mContext;
    private String paths;

    public interface Listener {
        void clickCloseBtn();
    }

    public CustomLogToolDialog(Context context) {
        super(context);
        this.TAG = "NEU_CustomLogToolDialog";
        RLog.i("NEU_CustomLogToolDialog", "CustomLogToolDialog()");
        this.mContext = context;
        initView();
        initData();
        initEvent();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void initView() {
        LayoutInflater.from(this.mContext).inflate(R.layout.view_logtool, (ViewGroup) this, true);
        this.log_beginBtn = (Button) findViewById(R.id.log_beginBtn);
        this.log_packBtn = (Button) findViewById(R.id.log_packBtn);
        this.log_endBtn = (Button) findViewById(R.id.log_endBtn);
        this.log_tip = (TextView) findViewById(R.id.log_tip);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
    }

    private void initData() {
        this.paths = "/vr_log/log";
        this.log_tip.setText("Log ：OFF");
        this.log_endBtn.setEnabled(false);
        this.log_packBtn.setEnabled(false);
        this.log_beginBtn.setEnabled(true);
        this.log_endBtn.setSelected(true);
    }

    public void setPackOk(boolean z) {
        this.log_packBtn.setEnabled(true);
        this.log_packBtn.setSelected(false);
        if (z) {
            this.log_tip.setText("Packaging completed");
        } else {
            this.log_tip.setText("Packaging failed");
        }
    }

    private void initEvent() {
        this.log_beginBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.utils.CustomLogToolDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_CustomLogToolDialog", "onClick: log_beginBtn");
                long jElapsedRealtime = SystemClock.elapsedRealtime() - CustomLogToolDialog.lastClickTime;
                if (CustomLogToolDialog.lastClickTime <= 0 || jElapsedRealtime >= CustomLogToolDialog.DIFF || jElapsedRealtime <= 0) {
                    long unused = CustomLogToolDialog.lastClickTime = SystemClock.elapsedRealtime();
                    Intent intent = new Intent("com.byd.voice.action.LogOn");
                    intent.setComponent(new ComponentName("com.byd.vrassistant", "com.byd.vrassistant.receiver.AutoRunReceiver"));
                    intent.addFlags(32);
                    CustomLogToolDialog.this.getContext().sendBroadcast(intent);
                    CustomLogToolDialog.this.log_beginBtn.setEnabled(false);
                    CustomLogToolDialog.this.log_beginBtn.setSelected(true);
                    CustomLogToolDialog.this.log_tip.setText("Log : ON");
                    CustomLogToolDialog.this.log_endBtn.setEnabled(true);
                    CustomLogToolDialog.this.log_endBtn.setSelected(false);
                    CustomLogToolDialog.this.log_packBtn.setEnabled(true);
                    return;
                }
                RLog.i("NEU_CustomLogToolDialog", "短时间内按钮多次触发");
                ToastUtils.show(CustomLogToolDialog.this.mContext, "Only one switch is allowed within 5 seconds");
            }
        });
        this.log_packBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.utils.CustomLogToolDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_CustomLogToolDialog", "onClick: log_packBtn");
                Intent intent = new Intent("bydlogtool.action.savelog");
                intent.putExtra("paths", CustomLogToolDialog.this.paths);
                intent.putExtra("prefix", "CerenceLog");
                intent.putExtra("needAp", true);
                intent.putExtra("needResult", true);
                CustomLogToolDialog.this.getContext().sendBroadcast(intent);
                CustomLogToolDialog.this.log_packBtn.setEnabled(false);
                CustomLogToolDialog.this.log_packBtn.setSelected(true);
                CustomLogToolDialog.this.log_tip.setText("packing...");
            }
        });
        this.log_endBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.utils.CustomLogToolDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_CustomLogToolDialog", "onClick: log_endBtn");
                long jElapsedRealtime = SystemClock.elapsedRealtime() - CustomLogToolDialog.lastClickTime;
                if (CustomLogToolDialog.lastClickTime <= 0 || jElapsedRealtime >= CustomLogToolDialog.DIFF || jElapsedRealtime <= 0) {
                    long unused = CustomLogToolDialog.lastClickTime = SystemClock.elapsedRealtime();
                    Intent intent = new Intent("com.byd.voice.action.LogOff");
                    intent.setComponent(new ComponentName("com.byd.vrassistant", "com.byd.vrassistant.receiver.AutoRunReceiver"));
                    intent.addFlags(32);
                    CustomLogToolDialog.this.getContext().sendBroadcast(intent);
                    CustomLogToolDialog.this.log_tip.setText("Log : OFF");
                    CustomLogToolDialog.this.log_endBtn.setEnabled(false);
                    CustomLogToolDialog.this.log_endBtn.setSelected(true);
                    CustomLogToolDialog.this.log_packBtn.setEnabled(false);
                    CustomLogToolDialog.this.log_packBtn.setSelected(false);
                    CustomLogToolDialog.this.log_beginBtn.setEnabled(true);
                    CustomLogToolDialog.this.log_beginBtn.setSelected(false);
                    return;
                }
                RLog.i("NEU_CustomLogToolDialog", "短时间内按钮多次触发");
                ToastUtils.show(CustomLogToolDialog.this.mContext, "Only one switch is allowed within 5 seconds");
            }
        });
        this.iv_close.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.utils.CustomLogToolDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomLogToolDialog.this.listener.clickCloseBtn();
            }
        });
    }
}
