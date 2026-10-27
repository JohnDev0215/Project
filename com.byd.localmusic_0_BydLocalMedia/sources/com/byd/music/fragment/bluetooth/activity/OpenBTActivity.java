package com.byd.music.fragment.bluetooth.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import androidx.lifecycle.Observer;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseActivity;
import com.byd.music.databinding.ActivityOpenBtBinding;
import com.byd.music.fragment.bluetooth.BlueToothFragment;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class OpenBTActivity extends BaseActivity<ActivityOpenBtBinding, OpenBTViewModel> {
    @Override // com.byd.music.base.BaseActivity
    protected void initData() {
    }

    @Override // com.byd.music.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_open_bt;
    }

    @Override // com.byd.music.base.BaseActivity
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initView() {
        ((OpenBTViewModel) this.viewModel).btnClickEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.bluetooth.activity.-$$Lambda$OpenBTActivity$9ADFcuOH5mmlvdVtdSxx2yVMeHU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initView$0$OpenBTActivity((Integer) obj);
            }
        });
        ((OpenBTViewModel) this.viewModel).outsizeClickEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.bluetooth.activity.-$$Lambda$OpenBTActivity$c6p0NePUYhS8peLQ5_V9NAw4CAM
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initView$1$OpenBTActivity(obj);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$OpenBTActivity(Integer num) {
        if (num.intValue() == 1) {
            finish();
            toConnectBlueTooth();
        } else {
            finish();
        }
    }

    public /* synthetic */ void lambda$initView$1$OpenBTActivity(Object obj) {
        finish();
    }

    private void toConnectBlueTooth() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth"));
        intent.setComponent(new ComponentName(BlueToothFragment.SERVICE_PKG, BlueToothFragment.SERVICE_CLASS));
        if (!getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
            startService(intent);
        }
        LogUtil.e("open BlueTooth by start service");
    }
}
