package com.byd.music.fragment.localMusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.api.ApiMethod;
import com.byd.music.base.BaseActivity;
import com.byd.music.beans.EventString;
import com.byd.music.databinding.ActivityLocalDialogBinding;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.utils.LocalMusicDeleteTask;
import com.byd.music.utils.LogUtil;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public class DialogActivity extends BaseActivity<ActivityLocalDialogBinding, DialogActivityViewModel> {
    private LocalMusicDeleteTask localMusicDeleteTask;
    int size;
    private int type;

    @Override // com.byd.music.base.BaseActivity
    protected void initData() {
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle bundleExtra = getIntent().getBundleExtra("localmusiclist_bundle");
        int size = MusicPlayHelper.getInstance().deleteMusicList.size();
        this.size = size;
        if (size == 1) {
            ((ActivityLocalDialogBinding) this.dataBinding).tvDesc.setText(String.format(getResources().getString(R.string.confirm_delete_desc), Integer.valueOf(this.size)));
        } else {
            ((ActivityLocalDialogBinding) this.dataBinding).tvDesc.setText(String.format(getResources().getString(R.string.confirm_delete_desc), Integer.valueOf(this.size)));
        }
        this.type = ((Integer) bundleExtra.getSerializable("musicTag")).intValue();
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
        ((ViewGroup) ((ActivityLocalDialogBinding) this.dataBinding).getRoot().getParent()).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.localMusic.activity.-$$Lambda$DialogActivity$d9ErHYDRk8hSdIilOSg6p3y0SYE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$DialogActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$DialogActivity(View view) {
        finish();
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        enableWidgetCard(true);
    }

    @Override // com.byd.music.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_local_dialog;
    }

    @Override // com.byd.music.base.BaseActivity
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initView() {
        ((ActivityLocalDialogBinding) this.dataBinding).tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.localMusic.activity.DialogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogActivity.this.startConfirmDeleteActivity();
                DialogActivity.this.finish();
            }
        });
        ((ActivityLocalDialogBinding) this.dataBinding).tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.localMusic.activity.DialogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogActivity.this.finish();
                EventBus.getDefault().post(new EventString("delete_item_music_cancel", String.valueOf(DialogActivity.this.type)));
            }
        });
    }

    public void startConfirmDeleteActivity() {
        Intent intent = new Intent(this, (Class<?>) DialogActivityConfirm.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("musicTag", Integer.valueOf(this.type));
        intent.putExtra("dialog_bundle", bundle);
        startActivity(intent);
        finish();
    }

    private void enableWidgetCard(boolean z) {
        LogUtil.d(this.TAG, "enableWidgetCard " + z);
        try {
            if (z) {
                ApiMethod.showWidgetCards(this);
            } else {
                ApiMethod.hideWidgetCards(this);
            }
        } catch (Exception unused) {
            LogUtil.e(getClass().getSimpleName(), "Failed to invoke widget card api.");
        }
    }
}
