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
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.widget.BydInvalidButton;
import com.byd.systemsettings.R;
import com.byd.systemsettings.ui.widget.BydTextWatcher;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;
import k4.c;
import m4.e;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class WifiInputConnectPager extends a<WifiInputConnectPresenter> implements c {
    private View bgPwd;
    private BydInvalidButton btnOk;
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
        String string = this.etPwd.getText().toString();
        if (string.length() < 8) {
            this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_8_wireless));
            this.tvPwdErr.setVisibility(0);
            return false;
        }
        if (string.length() <= 64) {
            this.tvPwdErr.setVisibility(8);
            return true;
        }
        this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_64_wireless));
        this.tvPwdErr.setVisibility(0);
        return false;
    }

    private void updateUI(boolean z7) {
        if (z7) {
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

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.e
    public BydData getCurData() {
        return this.result;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_wifi_input_connect;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public void initData(Context context) {
        this.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z7) {
                WifiInputConnectPager.this.bgPwd.setBackgroundResource(z7 ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_et_frame_gray_normal);
                if (z7) {
                    return;
                }
                WifiInputConnectPager wifiInputConnectPager = WifiInputConnectPager.this;
                wifiInputConnectPager.delayToCloseIme(wifiInputConnectPager.etPwd);
            }
        });
        this.etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
                x3.a.a(WifiInputConnectPager.this.TAG, "initData", ", onEditorAction actionId: " + i8);
                WifiInputConnectPager.this.btnOk.setInvalid(WifiInputConnectPager.this.checkPwd() ^ true);
                e.c(WifiInputConnectPager.this.etPwd, i8);
                return false;
            }
        });
        EditText editText = this.etPwd;
        editText.addTextChangedListener(new BydTextWatcher(editText, BydTextWatcher.getNameRegex(editText.getContext()), true) { // from class: com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager.3
            @Override // com.byd.systemsettings.ui.widget.BydTextWatcher
            public void onTextChangedAfterFiltered(String str) {
                if (str.length() >= 8) {
                    WifiInputConnectPager.this.checkPwd();
                }
            }
        });
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.tvName = (TextView) this.mRootView.findViewById(R.id.tv_wifi_name_pager_wifi_input_connect);
        this.bgPwd = this.mRootView.findViewById(R.id.ll_bg_wifi_pwd_pager_wifi_input_connect);
        this.ivPwdType = (ImageView) this.mRootView.findViewById(R.id.iv_wifi_pwd_type_pager_wifi_input_connect);
        this.etPwd = (EditText) this.mRootView.findViewById(R.id.et_wifi_pwd_pager_wifi_input_connect);
        this.tvPwdErr = (TextView) this.mRootView.findViewById(R.id.tv_pwd_err_pager_wifi_input_connect);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_wifi_input_connect);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_ok_pager_wifi_input_connect);
        this.btnOk = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_pager_wifi_input_connect).setOnClickListener(this);
        this.ivPwdType.setOnClickListener(this);
    }

    @Override // z3.e
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok_pager_wifi_input_connect) {
            x3.a.a(this.TAG, "onClick", ", btn_ok_pager_wifi_input_connect");
            if (checkPwd()) {
                String string = this.etPwd.getText().toString();
                w3.a aVar = this.mBtnCallback;
                if (aVar != null) {
                    aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.result, string);
                    return;
                }
                return;
            }
            return;
        }
        if (id != R.id.btn_cancel_pager_wifi_input_connect) {
            if (id == R.id.iv_wifi_pwd_type_pager_wifi_input_connect) {
                this.pwdType = !this.pwdType;
                updateUI(this.pwdType);
                return;
            }
            return;
        }
        w3.a aVar2 = this.mBtnCallback;
        if (aVar2 != null) {
            aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
        }
        if (getP() != null) {
            ((WifiInputConnectPresenter) getP()).setAutoConnectEnable(true);
        }
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        super.onDestroy();
        e.b(this.etPwd);
    }

    @Override // z3.a, z3.e
    public void onHide() {
        super.onHide();
        e.b(this.etPwd);
    }

    @Override // k4.c
    public void onKeyboardStateChanged(boolean z7) {
        EditText editText;
        x3.a.a(this.TAG, "onKeyboardStateChanged showing: " + z7);
        if (z7) {
            if (getP() == null || (editText = this.etPwd) == null || !editText.hasFocus()) {
                return;
            }
            ((WifiInputConnectPresenter) getP()).setAutoConnectEnable(false);
            return;
        }
        EditText editText2 = this.etPwd;
        if (editText2 != null && editText2.hasFocus()) {
            this.etPwd.clearFocus();
        }
        if (this.btnOk == null || isPageInvalid()) {
            return;
        }
        this.btnOk.setInvalid(!checkPwd());
    }

    @Override // z3.e
    public void onLtrRudderL(boolean z7) {
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        x3.a.a(this.TAG, "onLtrRudderR land = " + z7);
        setViewGroupRtl(this.llBottom, true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.e
    public void updateData(BydData bydData) {
        x3.a.a(this.TAG, "updateData_Input", ", data: " + bydData);
        if (bydData == null || !(bydData instanceof WifiScanResult)) {
            return;
        }
        WifiScanResult wifiScanResult = (WifiScanResult) bydData;
        boolean z7 = (this.result == null || this.result.getName().equals(wifiScanResult.getName())) ? false : true;
        this.result = wifiScanResult;
        x3.a.a(this.TAG, "updateData_Input", ", hasChanged: " + z7 + ", result: " + this.result);
        if (this.result != null) {
            this.tvName.setText(this.result.getName());
        } else {
            this.tvName.setText("");
        }
        if (z7 || !this.result.isSaved()) {
            this.etPwd.setText("");
        }
        this.tvPwdErr.setVisibility(8);
        updateUI(this.pwdType);
    }

    @Override // z3.g
    public WifiInputConnectPresenter newP() {
        return new WifiInputConnectPresenter();
    }
}
