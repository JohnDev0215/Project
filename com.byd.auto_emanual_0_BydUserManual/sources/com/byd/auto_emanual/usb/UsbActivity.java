package com.byd.auto_emanual.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.auto_emanual.ClassifyDbUtil;
import com.byd.auto_emanual.Config;
import com.byd.auto_emanual.JsonDataRunnable;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.fragment.ManualFragment;
import com.byd.auto_emanual.util.FileUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.SharedPrefUtil;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class UsbActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RESULT_UPDATE_OK = 1;
    private TextView buttonInterval;
    private Button cancelButton;
    private Button confirmButton;
    private EditText message;
    private String usbFileName;
    private BroadcastReceiver usbRemovedReceiver;
    private final String TAG = UsbActivity.class.getSimpleName();
    private volatile int currentStep = -1;
    private final int UPDATE_STEP_RESOURCE_FOUND = 0;
    private final int UPDATE_STEP_START = 1;
    private final int UPDATE_STEP_STARTED = 2;
    private final int UPDATE_STEP_OLD_DATA_DELETED = 3;
    private final int UPDATE_STEP_COMPLETED = 4;
    private final int UPDATE_STEP_USB_REMOVED = 5;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.acitviyt_usb);
        EditText editText = (EditText) findViewById(R.id.resource_update_message);
        this.message = editText;
        editText.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.buttonInterval = (TextView) findViewById(R.id.resource_update_button_interval);
        Button button = (Button) findViewById(R.id.resource_update_cancel);
        this.cancelButton = button;
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.resource_update_confirm);
        this.confirmButton = button2;
        button2.setOnClickListener(this);
        this.usbFileName = getIntent().getStringExtra(UsbReceiver.ZIP_NAME_IN_USB);
        this.usbRemovedReceiver = new UsbRemovedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("usb_removed_state");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.usbRemovedReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.usbRemovedReceiver, intentFilter);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.message.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$75dJAwqcOez9zGBit6KRe-jb0hY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onResume$0$UsbActivity();
            }
        }, 0);
        this.message.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$VI4aZ6SiXan4AqBesUfy-eUIcJ8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onResume$1$UsbActivity();
            }
        }, 1000);
        this.message.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$Z1fOolxZpYJnmgBQSBfuwJyj2pI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onResume$2$UsbActivity();
            }
        }, 2001);
    }

    public /* synthetic */ void lambda$onResume$0$UsbActivity() {
        this.message.getText().append((CharSequence) getString(R.string.resource_update_message_10));
    }

    public /* synthetic */ void lambda$onResume$1$UsbActivity() {
        this.message.getText().append((CharSequence) getString(R.string.resource_update_message_11));
    }

    public /* synthetic */ void lambda$onResume$2$UsbActivity() {
        this.message.getText().append((CharSequence) getString(R.string.resource_update_message_12));
        this.currentStep = 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resource_update_cancel /* 2131296604 */:
                doCancelClick();
                break;
            case R.id.resource_update_confirm /* 2131296605 */:
                doConfirmClick();
                break;
        }
    }

    private void doCancelClick() {
        MLog.i(this.TAG, "doCancelClick currentStep = " + this.currentStep);
        if (this.currentStep < 0 || this.currentStep >= 1) {
            return;
        }
        this.message.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$Lif6nmPBnxaaFym2eM5nwuOXbac
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$doCancelClick$3$UsbActivity();
            }
        }, 500);
        this.message.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$SkfY69tS62OjC03UQcc_Op5bSv8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$doCancelClick$4$UsbActivity();
            }
        }, 1500);
    }

    public /* synthetic */ void lambda$doCancelClick$3$UsbActivity() {
        this.message.getText().append((CharSequence) getString(R.string.resource_update_cancel_message));
    }

    public /* synthetic */ void lambda$doCancelClick$4$UsbActivity() {
        overridePendingTransition(0, 0);
        finish();
    }

    private void doConfirmClick() {
        MLog.i(this.TAG, "doConfirmClick currentStep = " + this.currentStep);
        if (this.currentStep < 0) {
            return;
        }
        if (this.currentStep >= 0 && this.currentStep < 2) {
            this.currentStep = 1;
            doUpdateResource();
        } else if (this.currentStep >= 4 && this.currentStep < 5) {
            this.message.post(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$_W6PDcOx3FUhMLwQUkZkJXt0TTw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$doConfirmClick$5$UsbActivity();
                }
            });
        } else if (this.currentStep >= 5) {
            this.message.post(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$2rwq5O0Trc41KWIZ5px6qhIjjvo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$doConfirmClick$6$UsbActivity();
                }
            });
            this.message.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.usb.-$$Lambda$UsbActivity$Y07thfQixmR8h3dZXp0gdftr-j0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$doConfirmClick$7$UsbActivity();
                }
            }, 1000L);
        }
    }

    public /* synthetic */ void lambda$doConfirmClick$5$UsbActivity() {
        this.message.getText().append((CharSequence) getString(R.string.resource_update_remove_usb_message));
    }

    public /* synthetic */ void lambda$doConfirmClick$6$UsbActivity() {
        this.message.getText().append((CharSequence) getString(R.string.resource_update_quiet));
    }

    public /* synthetic */ void lambda$doConfirmClick$7$UsbActivity() {
        setResult(1);
        overridePendingTransition(0, 0);
        finish();
    }

    private void doUpdateResource() {
        new UpdateTask().execute(this.usbFileName);
    }

    class UpdateTask extends AsyncTask<String, Integer, Integer> {
        UpdateTask() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            MLog.v(UsbActivity.this.TAG, "onPreExecute");
            UsbActivity.this.currentStep = 2;
            UsbActivity.this.buttonInterval.setVisibility(8);
            UsbActivity.this.cancelButton.setVisibility(8);
            UsbActivity.this.message.getText().append((CharSequence) UsbActivity.this.getString(R.string.resource_update_wait_message));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(String... strings) throws Throwable {
            MLog.v(UsbActivity.this.TAG, "doInBackground");
            FileUtil.copy(strings[0], FileUtil.getManualZipPathInDisk(), Config.ROOT_PATH_IN_DISK + File.separator + Config.HANDBOOK_ROOT_PATH);
            deleteOldData();
            return 4;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer integer) {
            MLog.v(UsbActivity.this.TAG, "onPostExecute");
            UsbActivity.this.currentStep = integer.intValue();
            UsbActivity.this.message.getText().append((CharSequence) UsbActivity.this.getString(R.string.resource_update_completed));
        }

        private void deleteOldData() {
            clearDb();
            clearPreference();
            clearCache();
        }

        private void clearDb() {
            ClassifyDbUtil.getInstance(MApp.context).clear();
        }

        private void clearPreference() {
            SharedPrefUtil.remove(MApp.context, ManualFragment.ZIP_IN_TYPE);
            SharedPrefUtil.remove(MApp.context, JsonDataRunnable.NET_ADDRESS);
            SharedPrefUtil.remove(MApp.context, JsonDataRunnable.MENU_TREE_LIST);
            SharedPrefUtil.remove(MApp.context, JsonDataRunnable.MULTI_MENU_TREE_LIST);
        }

        private void clearCache() {
            FileUtil.delete(FileUtil.getCacheDir(MApp.context));
        }
    }

    @Override // android.app.Activity
    public void finish() {
        this.currentStep = -1;
        unregisterReceiver(this.usbRemovedReceiver);
        super.finish();
    }

    class UsbRemovedReceiver extends BroadcastReceiver {
        UsbRemovedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("usb_state");
            MLog.v(UsbActivity.this.TAG, "usbRemovedReceiver onReceive " + action + " " + stringExtra);
            if ("usb_removed_state".equals(action) && "removed".equals(stringExtra)) {
                UsbActivity.this.currentStep = 5;
            }
        }
    }
}
