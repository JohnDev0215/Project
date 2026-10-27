package com.byd.systemsettings.wifi2.view.pager;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;

/* JADX INFO: loaded from: classes.dex */
public class WifiInputConnectPager extends a<WifiInputConnectPresenter> {
    private View bgPwd;
    private EditText etPwd;
    private ImageView ivPwdType;
    private ViewGroup llBottom;
    private volatile boolean pwdType;
    private volatile WifiScanResult result;
    private TextView tvName;
    private TextView tvPwdErr;

    public WifiInputConnectPager(Context context) {
        super(context);
        this.pwdType = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkPwd() {
        String strTrim = this.etPwd.getText().toString().trim();
        if (strTrim.length() < 8) {
            this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_8_wireless));
            this.tvPwdErr.setVisibility(0);
            return false;
        }
        if (strTrim.length() <= 64) {
            this.tvPwdErr.setVisibility(8);
            return true;
        }
        this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_64_wireless));
        this.tvPwdErr.setVisibility(0);
        return false;
    }

    private void updateUI(boolean z) {
        if (z) {
            this.ivPwdType.setImageResource(R.drawable.icon_public_closeeeye_48);
            this.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            this.ivPwdType.setImageResource(R.drawable.icon_public_eye_48);
            this.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        EditText editText = this.etPwd;
        if (editText == null || editText.getText() == null) {
            return;
        }
        EditText editText2 = this.etPwd;
        editText2.setSelection(editText2.getText().length());
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_wifi_input_connect;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ g mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ Object mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                WifiInputConnectPager.this.bgPwd.setBackgroundResource(z ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_et_frame_gray_normal);
            }
        });
        this.etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                b.b.m.c.a.a(WifiInputConnectPager.this.TAG, "initData", ", onEditorAction actionId: " + i);
                WifiInputConnectPager.this.checkPwd();
                b.b.m.g.g.b(WifiInputConnectPager.this.etPwd, i);
                return false;
            }
        });
        EditText editText = this.etPwd;
        editText.addTextChangedListener(new b.b.g.q.h.a(editText, "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$", true) { // from class: com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager.3
            @Override // b.b.g.q.h.a
            public void onTextChangedAfterFiltered(String str) {
                if (str.length() > 8) {
                    b.b.m.c.a.a(WifiInputConnectPager.this.TAG, "initData", ", onTextChangedAfterFiltered text: " + str);
                    WifiInputConnectPager.this.checkPwd();
                }
            }
        });
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.tvName = (TextView) this.mRootView.findViewById(R.id.tv_wifi_name_pager_wifi_input_connect);
        this.bgPwd = this.mRootView.findViewById(R.id.ll_bg_wifi_pwd_pager_wifi_input_connect);
        this.ivPwdType = (ImageView) this.mRootView.findViewById(R.id.iv_wifi_pwd_type_pager_wifi_input_connect);
        this.etPwd = (EditText) this.mRootView.findViewById(R.id.et_wifi_pwd_pager_wifi_input_connect);
        this.tvPwdErr = (TextView) this.mRootView.findViewById(R.id.tv_pwd_err_pager_wifi_input_connect);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_wifi_input_connect);
        this.mRootView.findViewById(R.id.btn_ok_pager_wifi_input_connect).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_pager_wifi_input_connect).setOnClickListener(this);
        this.ivPwdType.setOnClickListener(this);
    }

    @Override // b.b.m.e.f
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel_pager_wifi_input_connect) {
            b.b.m.b.a aVar = this.mBtnCallback;
            if (aVar != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
                return;
            }
            return;
        }
        if (id != R.id.btn_ok_pager_wifi_input_connect) {
            if (id != R.id.iv_wifi_pwd_type_pager_wifi_input_connect) {
                return;
            }
            this.pwdType = !this.pwdType;
            updateUI(this.pwdType);
            return;
        }
        b.b.m.c.a.a(this.TAG, "onClick", ", btn_ok_pager_wifi_input_connect");
        if (checkPwd()) {
            String strTrim = this.etPwd.getText().toString().trim();
            b.b.m.b.a aVar2 = this.mBtnCallback;
            if (aVar2 != null) {
                aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.result, strTrim);
            }
        }
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        b.b.m.g.g.a(this.etPwd);
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onHide() {
        super.onHide();
        b.b.m.g.g.a(this.etPwd);
    }

    @Override // b.b.m.e.f
    public void onLtrRudderL(boolean z) {
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        b.b.m.c.a.a(this.TAG, "onLtrRudderR land = " + z);
        setViewGroupRtl(this.llBottom, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.f
    public void updateData(BydData bydData) {
        b.b.m.c.a.a(this.TAG, "updateData", ", data: " + bydData);
        if (bydData == null || !(bydData instanceof WifiScanResult)) {
            return;
        }
        WifiScanResult wifiScanResult = (WifiScanResult) bydData;
        boolean z = (this.result == null || this.result.getName().equals(wifiScanResult.getName())) ? false : true;
        this.result = wifiScanResult;
        b.b.m.c.a.a(this.TAG, "updateData", ", hasChanged: " + z + ", result: " + this.result);
        if (this.result != null) {
            this.tvName.setText(this.result.getName());
        } else {
            this.tvName.setText("");
        }
        if (z || !this.result.isSaved()) {
            this.etPwd.setText("");
        }
        this.tvPwdErr.setVisibility(8);
        updateUI(this.pwdType);
    }

    @Override // b.b.m.e.h
    public WifiInputConnectPresenter newP() {
        return new WifiInputConnectPresenter();
    }
}
