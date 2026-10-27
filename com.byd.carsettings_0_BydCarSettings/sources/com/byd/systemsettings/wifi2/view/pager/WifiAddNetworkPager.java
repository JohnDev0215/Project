package com.byd.systemsettings.wifi2.view.pager;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import b.b.m.e.a;
import b.b.m.e.g;
import b.b.m.f.c.a.e;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.systemsettings.wifi2.adapter.SecurityListAdapter;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.NetworkParams;
import com.byd.systemsettings.wifi2.model.WifiSecurity;
import com.byd.systemsettings.wifi2.present.WifiAddNetPresenter;
import com.byd.systemsettings.wifi2.view.PopupWindowHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiAddNetworkPager extends a<WifiAddNetPresenter> {
    private View bgName;
    private BydInvalidButton connectNetBtn;
    private EditText etName;
    private EditText etPwd;
    private ImageView ivPwdType;
    private ImageView ivSecurity;
    private LinearLayout ll;
    private View llPwd;
    private SecurityListAdapter mAdapter;
    private List<WifiSecurity> mSecurityList;
    private boolean pwdType;
    private CipherType security;
    private TextView tvNameErr;
    private TextView tvPwdErr;
    private TextView tvSecurity;

    public WifiAddNetworkPager(Context context) {
        super(context);
        this.pwdType = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkNetName() {
        String strTrim = this.etName.getText().toString().trim();
        if (strTrim.length() > 0 && strTrim.length() <= 32) {
            this.tvNameErr.setVisibility(8);
            return true;
        }
        this.tvNameErr.setText(this.ref.get().getString(R.string.net_name_err_32_wireless));
        this.tvNameErr.setVisibility(0);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkPwd(boolean z) {
        if (!z) {
            this.tvPwdErr.setTextColor(getContext().getColor(R.color.text_color_red));
            this.tvPwdErr.setVisibility(0);
            String strTrim = this.etPwd.getText().toString().trim();
            if (strTrim.length() < 8 || strTrim.length() > 63) {
                this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_add_net_wireless));
                return false;
            }
        }
        this.tvPwdErr.setVisibility(8);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePwdImg(boolean z) {
        if (z) {
            this.ivPwdType.setImageResource(R.drawable.icon_public_closeeeye_48);
            this.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            this.ivPwdType.setImageResource(R.drawable.icon_public_eye_48);
            this.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        EditText editText = this.etPwd;
        editText.setSelection(editText.getText().length());
    }

    private void updateSecurityImg() {
        this.ivSecurity.setImageResource(R.drawable.icon_public_arrow_up_blue_48);
        b.b.m.c.a.a(this.TAG, "---updateSecurityImg---ref: " + this.ref);
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        Context context = this.ref.get();
        e eVar = new e() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.7
            @Override // b.b.m.f.c.a.e
            public void onItemClicked(int i) {
                int i2 = 0;
                while (true) {
                    boolean z = true;
                    if (i2 >= WifiAddNetworkPager.this.mSecurityList.size()) {
                        break;
                    }
                    WifiSecurity wifiSecurity = (WifiSecurity) WifiAddNetworkPager.this.mSecurityList.get(i2);
                    if (i2 != i) {
                        z = false;
                    }
                    wifiSecurity.setChecked(z);
                    i2++;
                }
                WifiAddNetworkPager.this.mAdapter.notifyDataSetChanged();
                WifiAddNetworkPager wifiAddNetworkPager = WifiAddNetworkPager.this;
                wifiAddNetworkPager.security = ((WifiSecurity) wifiAddNetworkPager.mSecurityList.get(i)).getType();
                WifiAddNetworkPager.this.tvSecurity.setText(((WifiSecurity) WifiAddNetworkPager.this.mSecurityList.get(i)).getName());
                WifiAddNetworkPager.this.llPwd.setVisibility(i == 0 ? 8 : 0);
                WifiAddNetworkPager.this.checkPwd(i == 0);
                WifiAddNetworkPager.this.tvPwdErr.setTextColor(WifiAddNetworkPager.this.getContext().getColor(R.color.text_color_gray_bt));
                WifiAddNetworkPager wifiAddNetworkPager2 = WifiAddNetworkPager.this;
                wifiAddNetworkPager2.updatePwdImg(wifiAddNetworkPager2.pwdType);
                PopupWindowHelper.dismiss();
                if (TextUtils.isEmpty(WifiAddNetworkPager.this.etPwd.getText().toString().trim())) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(i != 0);
                }
                if (TextUtils.isEmpty(WifiAddNetworkPager.this.etName.getText().toString().trim())) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(true);
                } else if (i == 0) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(false);
                }
            }
        };
        PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.8
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                WifiAddNetworkPager.this.ivSecurity.setImageResource(R.drawable.icon_public_arrow_down_gray_48);
            }
        };
        b.b.m.c.a.a(this.TAG, "---updateSecurityImg---ctx: " + context);
        PopupWindowHelper.showList(context, this.ivSecurity, this.mAdapter, eVar, onDismissListener);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_wifi_add_net;
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
    public void initData(final Context context) {
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                WifiAddNetworkPager.this.bgName.setBackgroundResource(z ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_frame_gray_search_no_stroke);
            }
        });
        this.etName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                WifiAddNetworkPager.this.checkNetName();
                b.b.m.g.g.b(WifiAddNetworkPager.this.etName, i);
                return false;
            }
        });
        this.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                WifiAddNetworkPager.this.llPwd.setBackgroundResource(z ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_frame_gray_search_no_stroke);
                int dimensionPixelOffset = WifiAddNetworkPager.this.tvNameErr.getVisibility() == 0 ? context.getResources().getDimensionPixelOffset(R.dimen.dp_106) : context.getResources().getDimensionPixelOffset(R.dimen.dp_80);
                if (context.getResources().getConfiguration().orientation == 2) {
                    if (z) {
                        WifiAddNetworkPager.this.ll.scrollBy(0, dimensionPixelOffset);
                    } else {
                        WifiAddNetworkPager.this.ll.scrollBy(0, -dimensionPixelOffset);
                    }
                }
            }
        });
        this.etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.4
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                WifiAddNetworkPager wifiAddNetworkPager = WifiAddNetworkPager.this;
                wifiAddNetworkPager.checkPwd(wifiAddNetworkPager.security == CipherType.OPEN);
                b.b.m.g.g.b(WifiAddNetworkPager.this.etPwd, i);
                return false;
            }
        });
        EditText editText = this.etName;
        editText.addTextChangedListener(new b.b.g.q.h.a(editText, b.b.g.q.h.a.getNameRegex(this.etPwd.getContext())) { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.5
            @Override // b.b.g.q.h.a, android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (editable.toString().length() <= 0) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(true);
                } else if (WifiAddNetworkPager.this.llPwd.getVisibility() == 8) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(false);
                } else {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(WifiAddNetworkPager.this.etPwd.getText().toString().trim().length() <= 7);
                }
            }
        });
        EditText editText2 = this.etPwd;
        editText2.addTextChangedListener(new b.b.g.q.h.a(editText2, "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$", true) { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.6
            @Override // b.b.g.q.h.a, android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String string = editable.toString();
                String strTrim = WifiAddNetworkPager.this.etName.getText().toString().trim();
                if (string.length() <= 7) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(true);
                } else {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(strTrim.length() <= 0);
                }
            }

            @Override // b.b.g.q.h.a
            public void onTextChangedAfterFiltered(String str) {
            }
        });
        Resources resources = this.ref.get().getResources();
        this.mSecurityList = new ArrayList();
        WifiSecurity wifiSecurity = new WifiSecurity(resources.getString(R.string.security_open_wireless), CipherType.OPEN);
        wifiSecurity.setChecked(true);
        this.mSecurityList.add(wifiSecurity);
        this.mSecurityList.add(new WifiSecurity(resources.getString(R.string.security_wep_wireless), CipherType.WEP));
        this.mSecurityList.add(new WifiSecurity(resources.getString(R.string.security_wap_psk_wireless), CipherType.WPA));
        this.mAdapter = new SecurityListAdapter(this.mSecurityList);
        this.mSecurityList.add(new WifiSecurity(resources.getString(R.string.wpa3_cipher_security_hotspot_right), CipherType.WPA3_PERSONAL));
        this.mAdapter = new SecurityListAdapter(this.mSecurityList);
        this.tvSecurity.setText(this.mSecurityList.get(0).getName());
        this.security = wifiSecurity.getType();
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.ll = (LinearLayout) this.mRootView.findViewById(R.id.ll_pager_add_net);
        this.etName = (EditText) this.mRootView.findViewById(R.id.et_net_name_pager_add_net);
        this.bgName = this.mRootView.findViewById(R.id.ll_bg_net_name_pager_add_net);
        this.tvNameErr = (TextView) this.mRootView.findViewById(R.id.tv_net_name_err_pager_add_net);
        this.tvSecurity = (TextView) this.mRootView.findViewById(R.id.tv_net_security_pwd_pager_add_net);
        this.ivSecurity = (ImageView) this.mRootView.findViewById(R.id.iv_show_security_list_pager_add_net);
        this.llPwd = this.mRootView.findViewById(R.id.ll_net_pwd_pager_add_net);
        this.ivPwdType = (ImageView) this.mRootView.findViewById(R.id.iv_net_pwd_pager_add_net);
        this.etPwd = (EditText) this.mRootView.findViewById(R.id.et_net_pwd_pager_add_net);
        this.tvPwdErr = (TextView) this.mRootView.findViewById(R.id.tv_pwd_err_pager_add_net);
        this.mRootView.findViewById(R.id.ll_net_security_pwd_pager_add_net).setOnClickListener(this);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_connect_pager_add_net);
        this.connectNetBtn = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.connectNetBtn.setInvalid(true);
        this.mRootView.findViewById(R.id.btn_cancel_pager_add_net).setOnClickListener(this);
        this.ivPwdType.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_pager_add_net /* 2131296467 */:
                b.b.m.b.a aVar = this.mBtnCallback;
                if (aVar != null) {
                    aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
                }
                break;
            case R.id.btn_connect_pager_add_net /* 2131296486 */:
                if (this.security != null && checkNetName()) {
                    if (checkPwd(this.security == CipherType.OPEN)) {
                        NetworkParams networkParams = new NetworkParams(this.etName.getText().toString().trim(), this.security, this.etPwd.getText().toString().trim());
                        b.b.m.b.a aVar2 = this.mBtnCallback;
                        if (aVar2 != null) {
                            aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, networkParams, null);
                        }
                        break;
                    }
                }
                break;
            case R.id.iv_net_pwd_pager_add_net /* 2131297308 */:
                boolean z = !this.pwdType;
                this.pwdType = z;
                updatePwdImg(z);
                break;
            case R.id.ll_net_security_pwd_pager_add_net /* 2131297519 */:
                updateSecurityImg();
                b.b.m.g.g.b(this.etPwd, 6);
                break;
        }
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        PopupWindowHelper.dismiss();
        b.b.m.g.g.a(this.etName);
        b.b.m.g.g.a(this.etPwd);
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onHide() {
        super.onHide();
        b.b.m.g.g.a(this.etName);
        b.b.m.g.g.a(this.etPwd);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.h
    public WifiAddNetPresenter newP() {
        return new WifiAddNetPresenter();
    }
}
