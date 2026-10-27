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
import b.b.g.r.z;
import b.b.i.d;
import b.b.m.e.a;
import b.b.m.e.g;
import b.b.m.f.d.d;
import b.b.m.g.f;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;

/* JADX INFO: loaded from: classes.dex */
public class ApPwdSettingPager extends a<WifiInputConnectPresenter> {
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
            b.b.m.c.a.a(this.TAG, "热点密码没有变化");
            this.btnOk.setInvalid(true);
            return;
        }
        if (b.b.g.q.h.a.checkPassword(this.mPwd)) {
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
        if (!f.n(strTrim) && !f.o(strTrim)) {
            this.mBtnCallback.onBtnClicked(CLASS, PagerBtnType.BOTTOM_LEFT, strTrim, Boolean.valueOf(this.pwdType));
            return;
        }
        String string = this.ref.get().getString(R.string.title_simple_ap_pwd_setting);
        String string2 = this.ref.get().getString(R.string.dialog_btn_ok);
        d.z(this.ref.get(), string, this.ref.get().getString(R.string.hint_simple_ap_pwd_setting), string2, new d.a() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.5
            @Override // b.b.m.f.d.d.a
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

    private void updateUI(boolean z) {
        this.pwdType = z;
        String strTrim = this.etPwd.getText().toString().trim();
        b.b.m.b.a aVar = this.mBtnCallback;
        if (aVar != null) {
            aVar.onBtnClicked(getClass(), PagerBtnType.IMAGE_BTN, Boolean.valueOf(z), null);
        }
        if (z) {
            this.ivPwdType.setImageResource(R.drawable.icon_public_closeeeye_48);
            this.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            this.ivPwdType.setImageResource(R.drawable.icon_public_eye_48);
            this.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        this.etPwd.setSelection(strTrim.length());
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_pwd_hotspot_right;
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
        this.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                ApPwdSettingPager.this.bgPwd.setBackgroundResource(z ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_et_frame_gray_normal);
            }
        });
        this.etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                ApPwdSettingPager.this.checkPwd();
                b.b.m.g.g.b(ApPwdSettingPager.this.etPwd, i);
                return false;
            }
        });
        this.etPwd.addTextChangedListener(new TextWatcher() { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (ApPwdSettingPager.this.mPwd == null || !ApPwdSettingPager.this.mPwd.equals(ApPwdSettingPager.this.etPwd.getText().toString().trim())) {
                    ApPwdSettingPager.this.checkPwd();
                } else {
                    b.b.m.c.a.a(ApPwdSettingPager.this.TAG, "热点密码没有变化");
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        EditText editText = this.etPwd;
        editText.addTextChangedListener(new b.b.g.q.h.a(editText, b.b.g.q.h.a.getNameRegex(editText.getContext())) { // from class: com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager.4
            @Override // b.b.g.q.h.a
            public void onInputFiltered(String str) {
                z.b(ApPwdSettingPager.this.getContext(), R.string.hint_unsupport_input);
            }
        });
    }

    @Override // b.b.m.e.f
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

    @Override // b.b.m.e.f
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel_pwd_hotspot_right) {
            b.b.m.b.a aVar = this.mBtnCallback;
            if (aVar != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
                return;
            }
            return;
        }
        if (id == R.id.btn_ok_pwd_hotspot_right) {
            updatePwd();
        } else {
            if (id != R.id.iv_hotspot_pwd_hotspot_right) {
                return;
            }
            this.pwdType = !this.pwdType;
            updateUI(this.pwdType);
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
        b.b.m.c.a.a(this.TAG, "data = " + bydData);
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
    }

    @Override // b.b.m.e.h
    public WifiInputConnectPresenter newP() {
        return new WifiInputConnectPresenter();
    }
}
