package com.byd.systemsettings.wifi2.view.pager;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.widget.BydInvalidButton;
import com.byd.systemsettings.R;
import com.byd.systemsettings.ui.dialog.PopupWindowHelper;
import com.byd.systemsettings.ui.widget.BydTextWatcher;
import com.byd.systemsettings.wifi2.adapter.SecurityListAdapter;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.NetworkParams;
import com.byd.systemsettings.wifi2.model.WifiSecurity;
import com.byd.systemsettings.wifi2.present.WifiAddNetPresenter;
import d4.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import k4.c;
import m4.e;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class WifiAddNetworkPager extends a<WifiAddNetPresenter> implements c {
    private View bgName;
    private BydInvalidButton connectNetBtn;
    private NetworkParams curParams;
    private EditText etName;
    private boolean etNameFocus;
    private EditText etPwd;
    private boolean etPwdFocus;
    private ImageView ivPwdType;
    private ImageView ivSecurity;
    private LinearLayout ll;
    private ViewGroup llBottom;
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
        this.etNameFocus = false;
        this.etPwdFocus = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkNetName() {
        EditText editText;
        if (this.tvNameErr != null && (editText = this.etName) != null) {
            String string = editText.getText().toString();
            if (string.length() > 0 && string.length() <= 32) {
                NetworkParams networkParams = this.curParams;
                if (networkParams != null) {
                    networkParams.setNameErrShow(false);
                }
                this.tvNameErr.setVisibility(8);
                return true;
            }
            TextView textView = this.tvNameErr;
            textView.setText(textView.getContext().getString(R.string.net_name_err_32_wireless));
            this.tvNameErr.setVisibility(0);
            NetworkParams networkParams2 = this.curParams;
            if (networkParams2 != null) {
                networkParams2.setNameErrShow(true);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkPwd(boolean z7) {
        if (this.tvPwdErr == null) {
            return false;
        }
        if (!z7) {
            String string = this.etPwd.getText().toString();
            if (string.length() < 8 || string.length() > 63) {
                NetworkParams networkParams = this.curParams;
                if (networkParams != null) {
                    networkParams.setPwdErrShow(true);
                }
                this.tvPwdErr.setVisibility(0);
                TextView textView = this.tvPwdErr;
                textView.setTextColor(textView.getContext().getColor(R.color.text_color_red));
                this.tvPwdErr.setText(this.ref.get().getString(R.string.pwd_err_add_net_wireless));
                return false;
            }
        }
        NetworkParams networkParams2 = this.curParams;
        if (networkParams2 != null) {
            networkParams2.setPwdErrShow(false);
        }
        this.tvPwdErr.setVisibility(8);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSecurityListItem(int i8) {
        int i9 = 0;
        while (i9 < this.mSecurityList.size()) {
            this.mSecurityList.get(i9).setChecked(i9 == i8);
            i9++;
        }
        this.mAdapter.notifyDataSetChanged();
    }

    private void clearAllFocus() {
        x3.a.a(this.TAG, "---clearAllFocus---");
        EditText editText = this.etName;
        if (editText != null && editText.hasFocus()) {
            this.etName.clearFocus();
        }
        EditText editText2 = this.etPwd;
        if (editText2 == null || !editText2.hasFocus()) {
            return;
        }
        this.etPwd.clearFocus();
    }

    private void clearInput() {
        this.etName.setText("");
        this.etPwd.setText("");
        WifiSecurity wifiSecurity = this.mSecurityList.get(0);
        checkSecurityListItem(0);
        this.security = wifiSecurity.getType();
        this.tvSecurity.setText(wifiSecurity.getName());
        updatePwdImg(false);
        checkPwd(true);
        this.llPwd.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delayToCheckFocus(EditText editText, boolean z7) {
        x3.a.a(this.TAG, "---delayToCheckFocus---hasFocus: " + z7 + ", et: " + editText);
        if (z7) {
            return;
        }
        delayToCloseIme(editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePwdImg(boolean z7) {
        if (z7) {
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
        x3.a.a(this.TAG, "---updateSecurityImg---ref: " + this.ref);
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        Context context = this.ref.get();
        d dVar = new d() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.7
            @Override // d4.d
            public void onItemClicked(int i8) {
                WifiAddNetworkPager.this.checkSecurityListItem(i8);
                WifiAddNetworkPager wifiAddNetworkPager = WifiAddNetworkPager.this;
                wifiAddNetworkPager.security = ((WifiSecurity) wifiAddNetworkPager.mSecurityList.get(i8)).getType();
                if (WifiAddNetworkPager.this.curParams != null) {
                    WifiAddNetworkPager.this.curParams.setType(WifiAddNetworkPager.this.security);
                }
                WifiAddNetworkPager.this.tvSecurity.setText(((WifiSecurity) WifiAddNetworkPager.this.mSecurityList.get(i8)).getName());
                WifiAddNetworkPager.this.llPwd.setVisibility(i8 == 0 ? 8 : 0);
                WifiAddNetworkPager wifiAddNetworkPager2 = WifiAddNetworkPager.this;
                wifiAddNetworkPager2.updatePwdImg(wifiAddNetworkPager2.pwdType);
                PopupWindowHelper.dismiss();
                String string = WifiAddNetworkPager.this.etPwd.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(i8 != 0);
                }
                if (WifiAddNetworkPager.this.security == CipherType.OPEN) {
                    WifiAddNetworkPager.this.checkPwd(true);
                } else if (!TextUtils.isEmpty(string)) {
                    WifiAddNetworkPager.this.checkPwd(false);
                }
                if (TextUtils.isEmpty(WifiAddNetworkPager.this.etName.getText().toString())) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(true);
                } else if (i8 == 0) {
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
        x3.a.a(this.TAG, "---updateSecurityImg---ctx: " + context);
        PopupWindowHelper.showList(context, this.ivSecurity, this.mAdapter, dVar, onDismissListener);
        clearAllFocus();
    }

    private void updateUI() {
        x3.a.a(this.TAG, "updateUI_AddNetworkPage, curParams: " + this.curParams);
        this.etName.setText(this.curParams.getName());
        this.etPwd.setText(this.curParams.getPwd());
        CipherType type = this.curParams.getType();
        int i8 = 0;
        while (true) {
            if (i8 >= this.mSecurityList.size()) {
                i8 = 0;
                break;
            } else if (this.mSecurityList.get(i8).getType().equals(type)) {
                break;
            } else {
                i8++;
            }
        }
        x3.a.a(this.TAG, "updateUI_AddNetworkPage, index: " + i8);
        WifiSecurity wifiSecurity = this.mSecurityList.get(i8);
        if (wifiSecurity != null) {
            this.security = wifiSecurity.getType();
            String name = wifiSecurity.getName();
            TextView textView = this.tvSecurity;
            if (name == null) {
                name = "";
            }
            textView.setText(name);
        }
        updatePwdImg(this.curParams.isHidePwd());
        if (!TextUtils.isEmpty(this.curParams.getName()) || this.curParams.isNameErrShow()) {
            checkNetName();
        }
        if (!TextUtils.isEmpty(this.curParams.getPwd()) || this.curParams.isPwdErrShow()) {
            checkPwd(i8 == 0);
        }
        this.llPwd.setVisibility(i8 == 0 ? 8 : 0);
        checkSecurityListItem(i8);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.e
    public BydData getCurData() {
        return this.curParams;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_wifi_add_net;
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
    public void initData(final Context context) {
        this.curParams = new NetworkParams();
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z7) {
                WifiAddNetworkPager.this.bgName.setBackgroundResource(z7 ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_frame_gray_search_no_stroke);
                WifiAddNetworkPager.this.etNameFocus = z7;
                WifiAddNetworkPager wifiAddNetworkPager = WifiAddNetworkPager.this;
                wifiAddNetworkPager.delayToCheckFocus(wifiAddNetworkPager.etName, z7);
                if (z7) {
                    return;
                }
                WifiAddNetworkPager.this.checkNetName();
            }
        });
        this.etName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
                WifiAddNetworkPager.this.checkNetName();
                e.c(WifiAddNetworkPager.this.etName, i8);
                return false;
            }
        });
        this.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z7) {
                WifiAddNetworkPager.this.llPwd.setBackgroundResource(z7 ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_frame_gray_search_no_stroke);
                WifiAddNetworkPager.this.etPwdFocus = z7;
                WifiAddNetworkPager wifiAddNetworkPager = WifiAddNetworkPager.this;
                wifiAddNetworkPager.delayToCheckFocus(wifiAddNetworkPager.etPwd, z7);
                if (!z7) {
                    WifiAddNetworkPager wifiAddNetworkPager2 = WifiAddNetworkPager.this;
                    wifiAddNetworkPager2.checkPwd(wifiAddNetworkPager2.security == CipherType.OPEN);
                }
                int dimensionPixelOffset = WifiAddNetworkPager.this.tvNameErr.getVisibility() == 0 ? context.getResources().getDimensionPixelOffset(R.dimen.dp_106) : context.getResources().getDimensionPixelOffset(R.dimen.dp_80);
                if (context.getResources().getConfiguration().orientation == 2) {
                    if (z7) {
                        WifiAddNetworkPager.this.ll.scrollBy(0, dimensionPixelOffset);
                    } else {
                        WifiAddNetworkPager.this.ll.scrollBy(0, -dimensionPixelOffset);
                    }
                }
            }
        });
        this.etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.4
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
                WifiAddNetworkPager wifiAddNetworkPager = WifiAddNetworkPager.this;
                wifiAddNetworkPager.checkPwd(wifiAddNetworkPager.security == CipherType.OPEN);
                e.c(WifiAddNetworkPager.this.etPwd, i8);
                return false;
            }
        });
        EditText editText = this.etName;
        editText.addTextChangedListener(new BydTextWatcher(editText, BydTextWatcher.getNameRegex(this.etPwd.getContext())) { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.5
            @Override // com.byd.systemsettings.ui.widget.BydTextWatcher, android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String string = editable.toString();
                if (string.length() <= 0) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(true);
                } else if (WifiAddNetworkPager.this.llPwd.getVisibility() == 8) {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(false);
                } else {
                    WifiAddNetworkPager.this.connectNetBtn.setInvalid(WifiAddNetworkPager.this.etPwd.getText().toString().length() <= 7);
                }
                if (WifiAddNetworkPager.this.curParams != null) {
                    WifiAddNetworkPager.this.curParams.setName(string);
                }
            }
        });
        EditText editText2 = this.etPwd;
        editText2.addTextChangedListener(new BydTextWatcher(editText2, BydTextWatcher.getNameRegex(editText2.getContext()), true) { // from class: com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager.6
            @Override // com.byd.systemsettings.ui.widget.BydTextWatcher, android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String string = editable.toString();
                x3.a.a(WifiAddNetworkPager.this.TAG, "initData", "afterTextChanged security: " + WifiAddNetworkPager.this.security);
                if (WifiAddNetworkPager.this.security != CipherType.OPEN) {
                    String string2 = WifiAddNetworkPager.this.etName.getText().toString();
                    if (string.length() <= 7) {
                        WifiAddNetworkPager.this.connectNetBtn.setInvalid(true);
                    } else {
                        WifiAddNetworkPager.this.connectNetBtn.setInvalid(string2.length() <= 0);
                    }
                }
                if (WifiAddNetworkPager.this.curParams != null) {
                    WifiAddNetworkPager.this.curParams.setPwd(string);
                }
            }

            @Override // com.byd.systemsettings.ui.widget.BydTextWatcher
            public void onTextChangedAfterFiltered(String str) {
                if (str.length() >= 8) {
                    WifiAddNetworkPager wifiAddNetworkPager = WifiAddNetworkPager.this;
                    wifiAddNetworkPager.checkPwd(wifiAddNetworkPager.security == CipherType.OPEN);
                }
            }
        });
        Resources resources = this.ref.get().getResources();
        this.mSecurityList = new ArrayList();
        WifiSecurity wifiSecurity = new WifiSecurity(resources.getString(R.string.security_open_wireless), CipherType.OPEN);
        wifiSecurity.setChecked(true);
        this.mSecurityList.add(wifiSecurity);
        this.mSecurityList.add(new WifiSecurity(resources.getString(R.string.security_wep_wireless), CipherType.WEP));
        this.mSecurityList.add(new WifiSecurity(resources.getString(R.string.security_wap_psk_wireless), CipherType.WPA));
        this.mSecurityList.add(new WifiSecurity(resources.getString(R.string.wpa3_cipher_security_hotspot_right), CipherType.WPA3_PERSONAL));
        this.mAdapter = new SecurityListAdapter(this.mSecurityList);
        this.tvSecurity.setText(this.mSecurityList.get(0).getName());
        CipherType type = wifiSecurity.getType();
        this.security = type;
        NetworkParams networkParams = this.curParams;
        if (networkParams != null) {
            networkParams.setType(type);
            this.curParams.setHidePwd(true);
        }
        this.pwdType = true;
    }

    @Override // z3.e
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
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_wifi_add_net);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_connect_pager_add_net);
        this.connectNetBtn = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.connectNetBtn.setInvalid(true);
        this.mRootView.findViewById(R.id.btn_cancel_pager_add_net).setOnClickListener(this);
        this.ivPwdType.setOnClickListener(this);
    }

    @Override // z3.e
    public boolean lostAllFocus() {
        return (this.etNameFocus || this.etPwdFocus) ? false : true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_connect_pager_add_net) {
            if (this.security != null && checkNetName()) {
                if (checkPwd(this.security == CipherType.OPEN)) {
                    String string = this.etName.getText().toString();
                    String string2 = this.etPwd.getText().toString();
                    NetworkParams networkParams = this.curParams;
                    if (networkParams == null) {
                        this.curParams = new NetworkParams(string, this.security, string2);
                    } else {
                        networkParams.setName(string);
                        this.curParams.setType(this.security);
                        this.curParams.setPwd(string2);
                    }
                    w3.a aVar = this.mBtnCallback;
                    if (aVar != null) {
                        aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.curParams, null);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (id == R.id.btn_cancel_pager_add_net) {
            clearInput();
            w3.a aVar2 = this.mBtnCallback;
            if (aVar2 != null) {
                aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
            }
            if (getP() != null) {
                ((WifiAddNetPresenter) getP()).setAutoConnectEnable(true);
                return;
            }
            return;
        }
        if (id == R.id.ll_net_security_pwd_pager_add_net) {
            updateSecurityImg();
            e.c(this.etPwd, 6);
        } else if (id == R.id.iv_net_pwd_pager_add_net) {
            boolean z7 = !this.pwdType;
            this.pwdType = z7;
            NetworkParams networkParams2 = this.curParams;
            if (networkParams2 != null) {
                networkParams2.setHidePwd(z7);
            }
            updatePwdImg(this.pwdType);
        }
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        super.onDestroy();
        PopupWindowHelper.dismiss();
        e.b(this.etName);
        e.b(this.etPwd);
    }

    @Override // z3.a, z3.e
    public void onHide() {
        super.onHide();
        e.b(this.etName);
        e.b(this.etPwd);
    }

    @Override // k4.c
    public void onKeyboardStateChanged(boolean z7) {
        x3.a.a(this.TAG, "onKeyboardStateChanged showing: " + z7);
        if (!z7) {
            clearAllFocus();
            checkNetName();
            checkPwd(this.security == CipherType.OPEN);
        } else {
            if (getP() == null || lostAllFocus()) {
                return;
            }
            ((WifiAddNetPresenter) getP()).setAutoConnectEnable(false);
        }
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
        super.updateData(bydData);
        x3.a.a(this.TAG, "updateData_AddNetworkPage, data: " + bydData);
        if (bydData == null || !(bydData instanceof NetworkParams)) {
            this.curParams.clear();
            clearInput();
        } else {
            NetworkParams networkParams = (NetworkParams) bydData;
            this.curParams.copyFrom(networkParams);
            this.pwdType = networkParams.isHidePwd();
            updateUI();
        }
    }

    @Override // z3.g
    public WifiAddNetPresenter newP() {
        return new WifiAddNetPresenter();
    }
}
