package com.byd.music.fragment.radio;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseActivity;
import com.byd.music.databinding.ActivityEpgReplaceDialogBinding;

/* JADX INFO: loaded from: classes5.dex */
public class EpgReplaceDialogActivity extends BaseActivity<ActivityEpgReplaceDialogBinding, EmptyViewModel> {
    @Override // com.byd.music.base.BaseActivity
    protected void initData() {
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((ActivityEpgReplaceDialogBinding) this.dataBinding).tvDesc.setText(String.format(getResources().getString(R.string.hint_epg_replace), getIntent().getStringExtra("RadioTitle")));
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // com.byd.music.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_epg_replace_dialog;
    }

    @Override // com.byd.music.base.BaseActivity
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initView() {
        ((ActivityEpgReplaceDialogBinding) this.dataBinding).tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.EpgReplaceDialogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpgReplaceDialogActivity.this.setResult(-1);
                EpgReplaceDialogActivity.this.finish();
            }
        });
        ((ActivityEpgReplaceDialogBinding) this.dataBinding).tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.EpgReplaceDialogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpgReplaceDialogActivity.this.setResult(0);
                EpgReplaceDialogActivity.this.finish();
            }
        });
        ((ViewGroup) ((ActivityEpgReplaceDialogBinding) this.dataBinding).getRoot().getParent()).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$EpgReplaceDialogActivity$p4C9LixELO8AWXniQ2WMhjmPqdk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$EpgReplaceDialogActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$EpgReplaceDialogActivity(View view) {
        setResult(0);
        finish();
    }
}
