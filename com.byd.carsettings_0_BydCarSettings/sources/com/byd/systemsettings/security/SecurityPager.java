package com.byd.systemsettings.security;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import b.b.g.r.x;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.dialog.LeakDialogFragment;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.systemsettings.system.SystemSecurityDialog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class SecurityPager extends a<SecurityPresenter> implements CompoundButton.OnCheckedChangeListener {
    public Button btnClear;
    public BydSwitch bydSwitch;
    public Context context;
    private LeakDialogFragment fragment;
    public ImageView ivBack;
    public TextView tvTitle;
    public TextView tvTitleStorage;
    public View vInstall;
    public View vShow;

    public SecurityPager(Context context) {
        super(context);
        this.context = context;
    }

    private void highLevelDialogBgTransDeal(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        Window window = dialog.getWindow();
        window.setDimAmount(0.0f);
        ((RelativeLayout) window.getDecorView().findViewById(R.id.dialog_bg)).setBackgroundResource(R.color.transparent_00);
    }

    private void installCredential() {
        b.b.m.c.a.a(this.TAG, "----installCredential---");
        Intent intent = new Intent();
        intent.setClassName("com.byd.filemanager", "com.byd.filemanager.ui.MainActivity");
        this.context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCredentialList() {
        b.b.m.c.a.a(this.TAG, "----showCredentialList---");
        CustomViewDialog.y(7).show(((FragmentActivity) this.context).getSupportFragmentManager(), "CustomViewDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHintByClearCredential() {
        b.b.m.c.a.a(this.TAG, "----showHintByClearCredential---");
        SimpleDialog.z(this.context.getString(R.string.attention_credential_app_manage), this.context.getString(R.string.msg_credential_app_manage), this.context.getString(R.string.dialog_btn_ok), this.context.getString(R.string.dialog_btn_cancel), 104).show(((FragmentActivity) this.context).getSupportFragmentManager(), "SimpleDialog");
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        int iC = b.b.o.b.b.a.c();
        return iC == 2 ? R.layout.dialog_security_r : iC == 1 ? R.layout.dialog_security_rtl : R.layout.dialog_security;
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
        ((SecurityPresenter) mo7getP()).getKeyStoreSize();
        this.bydSwitch.setChecked(((SecurityPresenter) mo7getP()).getDisplaySecuritySwitch() == 1, true);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_security);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.iv_back_dialog_security);
        this.ivBack = imageView;
        imageView.setOnClickListener(this);
        BydSwitch bydSwitch = (BydSwitch) this.mRootView.findViewById(R.id.switch_show_credential_dialog_security);
        this.bydSwitch = bydSwitch;
        bydSwitch.setOnCheckedChangeListener(this);
        this.tvTitleStorage = (TextView) this.mRootView.findViewById(R.id.tv_title_credential_storage_dialog_security);
        this.btnClear = (Button) this.mRootView.findViewById(R.id.btn_clear_credential_dialog_security);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.dp_6);
        this.btnClear.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        this.btnClear.setOnClickListener(new b.b.a.j.d.a() { // from class: com.byd.systemsettings.security.SecurityPager.1
            @Override // b.b.a.j.d.a
            public void onNoDoubleClick(View view) {
                SecurityPager.this.showHintByClearCredential();
            }
        });
        View viewFindViewById = this.mRootView.findViewById(R.id.rl_show_credential_dialog_security);
        this.vShow = viewFindViewById;
        viewFindViewById.setOnClickListener(new b.b.a.j.d.a() { // from class: com.byd.systemsettings.security.SecurityPager.2
            @Override // b.b.a.j.d.a
            public void onNoDoubleClick(View view) {
                SecurityPager.this.showCredentialList();
            }
        });
        View viewFindViewById2 = this.mRootView.findViewById(R.id.rl_install_credential_dialog_security);
        this.vInstall = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this);
        this.tvTitle.setText(R.string.security_app_manage);
        final TextView textView = (TextView) this.mRootView.findViewById(R.id.tv_show_credential_dialog_security);
        textView.post(new Runnable() { // from class: com.byd.systemsettings.security.SecurityPager.3
            @Override // java.lang.Runnable
            public void run() {
                if (textView.getLineCount() > 1) {
                    textView.setPadding(0, 0, 0, 0);
                }
            }
        });
        x.b(this.tvTitleStorage);
        EventBus.getDefault().register(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ((SecurityPresenter) mo7getP()).setDisplaySecuritySwitch(z);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.iv_back_dialog_security) {
            if (id != R.id.rl_install_credential_dialog_security) {
                return;
            }
            installCredential();
        } else {
            LeakDialogFragment leakDialogFragment = this.fragment;
            if (leakDialogFragment != null) {
                leakDialogFragment.dismiss();
            }
        }
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.context = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 104) {
            b.b.m.c.a.a(getClass().getName(), "CLEAR_SECURITY_CREDENTIAL");
            ((SecurityPresenter) mo7getP()).clearCredential();
        }
    }

    public void setFragment(SystemSecurityDialog systemSecurityDialog) {
        this.fragment = systemSecurityDialog;
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.h
    public SecurityPresenter newP() {
        return new SecurityPresenter(getContext());
    }
}
