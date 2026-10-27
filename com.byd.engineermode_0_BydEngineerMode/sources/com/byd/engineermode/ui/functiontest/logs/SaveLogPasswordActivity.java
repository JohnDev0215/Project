package com.byd.engineermode.ui.functiontest.logs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class SaveLogPasswordActivity extends BaseActivity implements View.OnClickListener {
    private String A = "";
    private final int B = 6;
    EditText v;
    LinearLayout w;
    TextView x;
    TextView y;
    Button z;

    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    private void M(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    private void N() {
        try {
            d.a.a.a.a aVarH = d.a.a.a.a.h();
            if (aVarH != null) {
                this.A = aVarH.c();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (this.A != null) {
            this.y.setText(getString(R.string.imei) + this.A);
        }
    }

    private boolean P(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    public void O() {
        TextView textView = (TextView) findViewById(R.id.btn_save_dr_log);
        this.x = textView;
        textView.setOnClickListener(this);
        this.v = (EditText) findViewById(R.id.et_password);
        this.w = (LinearLayout) findViewById(R.id.ll_password);
        this.y = (TextView) findViewById(R.id.imei_tv);
        Button button = (Button) findViewById(R.id.ok_btn);
        this.z = button;
        button.setOnClickListener(this);
        this.v.addTextChangedListener(new a());
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (P(currentFocus, motionEvent)) {
                M(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        String strSubstring;
        int id = view.getId();
        if (id == R.id.btn_save_dr_log) {
            startActivity(new Intent(this, (Class<?>) SaveLogActivity.class));
            return;
        }
        if (id != R.id.ok_btn) {
            return;
        }
        String upperCase = this.v.getText().toString().trim().toUpperCase();
        if (upperCase != null && upperCase.isEmpty()) {
            com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
            return;
        }
        if (upperCase.length() != 6) {
            com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
            return;
        }
        if (TextUtils.isEmpty(this.A) || this.A.length() < 6) {
            strSubstring = "112233";
        } else {
            String str = this.A;
            strSubstring = str.substring(str.length() - 6, this.A.length());
        }
        boolean zJ = c.a.a.a.k().j(strSubstring, upperCase);
        com.byd.engineermode.utils.a.b("PasswordActivity", "onTextChanged check =" + zJ + ",imei =" + strSubstring);
        if (!zJ) {
            com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
            return;
        }
        this.v.setText("");
        this.w.setVisibility(8);
        this.x.setVisibility(0);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_save_log_password);
        O();
        N();
    }
}
