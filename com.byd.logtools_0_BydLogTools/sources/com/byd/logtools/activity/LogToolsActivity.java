package com.byd.logtools.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.byd.logtools.R;

/* JADX INFO: loaded from: classes.dex */
public class LogToolsActivity extends BydBaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3015b;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Dialog f3016b;

        a(Dialog dialog) {
            this.f3016b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogToolsActivity.this.startActivity(new Intent(LogToolsActivity.this.getApplicationContext(), (Class<?>) SaveCurrentLogActivity.class));
            this.f3016b.dismiss();
        }
    }

    class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Dialog f3018b;

        b(Dialog dialog) {
            this.f3018b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3018b.dismiss();
        }
    }

    class c implements DialogInterface.OnDismissListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
        }
    }

    private Dialog j() {
        Dialog dialog = new Dialog(this.f3015b, R.style.NormalDialogStyle);
        View viewInflate = View.inflate(this.f3015b, R.layout.save_current_log_dialog, null);
        Button button = (Button) viewInflate.findViewById(R.id.btn_confirm);
        Button button2 = (Button) viewInflate.findViewById(R.id.btn_cancel);
        dialog.setContentView(viewInflate);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        button.setOnClickListener(new a(dialog));
        button2.setOnClickListener(new b(dialog));
        dialog.setOnDismissListener(new c());
        dialog.show();
        return dialog;
    }

    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        if (view == null) {
        }
        switch (view.getId()) {
            case R.id.rl_clear_log /* 2131296585 */:
                startActivity(new Intent(getApplicationContext(), (Class<?>) ClearLogActivity.class));
                break;
            case R.id.rl_configuration_log /* 2131296586 */:
                startActivity(new Intent(getApplicationContext(), (Class<?>) LogLevelConfigActivity.class));
                break;
            case R.id.rl_fault_upload /* 2131296587 */:
                startActivity(new Intent(getApplicationContext(), (Class<?>) FaultUploadActivity.class));
                break;
            case R.id.rl_save_current_log /* 2131296588 */:
                j();
                break;
            case R.id.rl_save_log /* 2131296589 */:
                startActivity(new Intent(getApplicationContext(), (Class<?>) SaveLogActivity.class));
                break;
        }
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (i("LogToolsActivity")) {
            finish();
            return;
        }
        requestWindowFeature(1);
        getWindow().clearFlags(1024);
        setContentView(R.layout.activity_log_tools);
        this.f3015b = this;
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (i("LogToolsActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (i("LogToolsActivity")) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (i("LogToolsActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (i("LogToolsActivity")) {
            finish();
        }
    }
}
