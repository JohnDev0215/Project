package com.byd.systemsettings.hotspot.view.pager;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import com.byd.systemsettings.ui.widget.BydTextWatcher;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;
import k4.c;
import m4.d;
import m4.e;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class ApPwdSettingPager extends a<WifiInputConnectPresenter> implements c {
    private static final Class<?> CLASS = ApPwdSettingPager.class;
    private View bgPwd;
    private BydInvalidButton btnOk;
    private EditText etPwd;
    private ImageView ivPwdType;
    private ViewGroup llBottom;
    private String mPwd;
    private volatile HotspotParams params;
    private volatile boolean pwdType;
    private TextView tvPwdErr;

    public ApPwdSettingPager(Context context) {
        super(context);
        this.pwdType = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPwd() {
        this.mPwd = this.etPwd.getText().toString().trim();
        if (this.params != null && this.params.getPwd().equals(this.mPwd)) {
            x3.a.a(this.TAG, "热点密码没有变化");
            this.btnOk.setInvalid(true);
            return;
        }
        if (BydTextWatcher.checkPassword(this.mPwd)) {
            this.btnOk.setInvalid(false);
            this.tvPwdErr.setVisibility(8);
            return;
        }
        this.btnOk.setInvalid(true);
        this.tvPwdErr.setVisibility(0);
        if (this.mPwd.length() < 8) {
            this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_8_wireless));
        } else if (this.mPwd.length() > 63) {
            this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_63_wireless));
        } else {
            this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_multi_wireless));
        }
    }

    private void checkSimplePwdAndCallback() {
        if (this.mBtnCallback == null) {
            return;
        }
        final String strTrim = this.etPwd.getText().toString().trim();
        if (!d.o(strTrim) && !d.p(strTrim)) {
            this.mBtnCallback.onBtnClicked(CLASS, PagerBtnType.BOTTOM_LEFT, strTrim, Boolean.valueOf(this.pwdType));
            return;
        }
        String string = this.ref.get().getString(R.string.title_simple_ap_pwd_setting);
        String string2 = this.ref.get().getString(R.string.dialog_btn_ok);
        SystemDialog.showBtnLeftHighlight(this.ref.get(), string, this.ref.get().getString(R.string.hint_simple_ap_pwd_setting), string2, new e4.d() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.5
            @Override // e4.d
            public void onOKClicked() {
                ApPwdSettingPager.this.mBtnCallback.onBtnClicked(ApPwdSettingPager.CLASS, PagerBtnType.BOTTOM_LEFT, strTrim, Boolean.valueOf(ApPwdSettingPager.this.pwdType));
            }
        });
    }

    private void updatePwd() {
        if (this.mBtnCallback == null) {
            return;
        }
        this.mBtnCallback.onBtnClicked(CLASS, PagerBtnType.BOTTOM_LEFT, this.etPwd.getText().toString().trim(), Boolean.valueOf(this.pwdType));
    }

    private void updateUI(boolean z7) {
        this.pwdType = z7;
        String strTrim = this.etPwd.getText().toString().trim();
        w3.a aVar = this.mBtnCallback;
        if (aVar != null) {
            aVar.onBtnClicked(getClass(), PagerBtnType.IMAGE_BTN, Boolean.valueOf(z7), null);
        }
        if (z7) {
            this.ivPwdType.setImageResource(R.drawable.icon_public_closeeeye_48);
            this.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            this.ivPwdType.setImageResource(R.drawable.icon_public_eye_48);
            this.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        this.etPwd.setSelection(strTrim.length());
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_pwd_hotspot_right;
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
        this.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z7) {
                ApPwdSettingPager.this.bgPwd.setBackgroundResource(z7 ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_et_frame_gray_normal);
                if (z7) {
                    return;
                }
                ApPwdSettingPager apPwdSettingPager = ApPwdSettingPager.this;
                apPwdSettingPager.delayToCloseImeOnOtherPage(apPwdSettingPager.etPwd);
            }
        });
        this.etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
                ApPwdSettingPager.this.checkPwd();
                e.c(ApPwdSettingPager.this.etPwd, i8);
                return false;
            }
        });
        this.etPwd.addTextChangedListener(new TextWatcher() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (ApPwdSettingPager.this.mPwd == null || !ApPwdSettingPager.this.mPwd.equals(ApPwdSettingPager.this.etPwd.getText().toString().trim())) {
                    ApPwdSettingPager.this.checkPwd();
                } else {
                    x3.a.a(ApPwdSettingPager.this.TAG, "热点密码没有变化");
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            }
        });
        EditText editText = this.etPwd;
        BydTextWatcher bydTextWatcher = new BydTextWatcher(editText, BydTextWatcher.getNameRegex(editText.getContext())) { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.4
            @Override // com.byd.systemsettings.ui.widget.BydTextWatcher
            public void onInputFiltered(String str) {
                q2.d.b(ApPwdSettingPager.this.getContext(), R.string.hint_unsupport_input);
            }
        };
        bydTextWatcher.setCheckASCII(true);
        this.etPwd.addTextChangedListener(bydTextWatcher);
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.ivPwdType = (ImageView) this.mRootView.findViewById(R.id.iv_hotspot_pwd_hotspot_right);
        this.bgPwd = this.mRootView.findViewById(R.id.ll_bg_hotspot_pwd_hotspot_right);
        this.etPwd = (EditText) this.mRootView.findViewById(R.id.et_hotspot_pwd_hotspot_right);
        this.tvPwdErr = (TextView) this.mRootView.findViewById(R.id.tv_pwd_err_hotspot_right);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.bottom_view);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_ok_pwd_hotspot_right);
        this.btnOk = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_pwd_hotspot_right).setOnClickListener(this);
        this.ivPwdType.setOnClickListener(this);
    }

    @Override // z3.e
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok_pwd_hotspot_right) {
            updatePwd();
            return;
        }
        if (id == R.id.btn_cancel_pwd_hotspot_right) {
            w3.a aVar = this.mBtnCallback;
            if (aVar != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
                return;
            }
            return;
        }
        if (id == R.id.iv_hotspot_pwd_hotspot_right) {
            this.pwdType = !this.pwdType;
            updateUI(this.pwdType);
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
        if (z7 || (editText = this.etPwd) == null || !editText.hasFocus()) {
            return;
        }
        this.etPwd.clearFocus();
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
        x3.a.a(this.TAG, "data = " + bydData);
        if (bydData == null) {
            return;
        }
        this.params = (HotspotParams) bydData;
        if (this.params.getPwd() == null || this.params.getPwd().isEmpty()) {
            this.etPwd.setText("");
        } else {
            this.etPwd.setText(this.params.getPwd());
            updateUI(this.params.isPwdHide());
        }
        this.btnOk.setInvalid(true);
        this.tvPwdErr.setVisibility(8);
    }

    @Override // z3.g
    public WifiInputConnectPresenter newP() {
        return new WifiInputConnectPresenter();
    }
}
