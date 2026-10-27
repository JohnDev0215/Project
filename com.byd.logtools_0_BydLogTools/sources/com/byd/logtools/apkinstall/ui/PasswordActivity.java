package com.byd.logtools.apkinstall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.logtools.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class PasswordActivity extends AppCompatActivity implements com.byd.logtools.apkinstall.a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private EditText f3085b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Button f3086c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Button f3087d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.byd.logtools.apkinstall.c.e f3088e;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(View view) {
        BydLogHelper.logW("PasswordActivity", "mPassword: " + this.f3088e.g() + " EditText:" + this.f3085b.getText().toString());
        if (!this.f3088e.g().equals(this.f3085b.getText().toString())) {
            Toast.makeText(this, "wrong password, please try again", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) ApkInstallActivity.class);
        intent.putExtra("verifyType", 1);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l(View view) {
        finish();
    }

    @Override // com.byd.logtools.apkinstall.a.b
    public void c() {
        finish();
    }

    @Override // com.byd.logtools.apkinstall.a.b
    public void g() {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_password);
        this.f3088e = com.byd.logtools.apkinstall.c.e.f(this);
        com.byd.logtools.apkinstall.c.f.e(this).d(this);
        this.f3085b = (EditText) findViewById(R.id.et_password);
        this.f3086c = (Button) findViewById(R.id.btn_confirm);
        this.f3087d = (Button) findViewById(R.id.btn_cancel);
        this.f3086c.setOnClickListener(new View.OnClickListener() { // from class: com.byd.logtools.apkinstall.ui.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3098b.j(view);
            }
        });
        this.f3087d.setOnClickListener(new View.OnClickListener() { // from class: com.byd.logtools.apkinstall.ui.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3099b.l(view);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        com.byd.logtools.apkinstall.c.f.e(this).i(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
