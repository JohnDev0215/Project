package com.byd.music.fragment.localMusic.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import com.blankj.utilcode.util.Utils;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseActivity;
import com.byd.music.beans.EventString;
import com.byd.music.databinding.ActivityLocalDialogConfirmBinding;
import com.byd.music.fragment.localInterface.DeleteInterface;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.utils.LocalMusicDeleteTask;
import com.byd.music.utils.MusicToastUtils1;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public class DialogActivityConfirm extends BaseActivity<ActivityLocalDialogConfirmBinding, DialogActivityConfirmViewModel> implements DeleteInterface.DeleteResultListener<Integer> {
    private LocalMusicDeleteTask localMusicDeleteTask;
    int size;
    private int type;

    @Override // com.byd.music.base.BaseActivity
    protected void initData() {
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle bundleExtra = getIntent().getBundleExtra("dialog_bundle");
        this.size = MusicPlayHelper.getInstance().deleteMusicList.size();
        ((DialogActivityConfirmViewModel) this.viewModel).setSbarMax(100);
        this.type = ((Integer) bundleExtra.getSerializable("musicTag")).intValue();
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // com.byd.music.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_local_dialog_confirm;
    }

    @Override // com.byd.music.base.BaseActivity
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initView() {
        ((DialogActivityConfirmViewModel) this.viewModel).setSbarMax(100);
        LocalMusicDeleteTask localMusicDeleteTask = new LocalMusicDeleteTask(MusicPlayHelper.getInstance().deleteMusicList);
        this.localMusicDeleteTask = localMusicDeleteTask;
        localMusicDeleteTask.setDeleteResultListener(this);
        this.localMusicDeleteTask.deleteBegin();
        ((ActivityLocalDialogConfirmBinding) this.dataBinding).seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.music.fragment.localMusic.activity.DialogActivityConfirm.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        ((ActivityLocalDialogConfirmBinding) this.dataBinding).tvCancelDelete.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.localMusic.activity.DialogActivityConfirm.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogActivityConfirm.this.localMusicDeleteTask != null) {
                    DialogActivityConfirm.this.localMusicDeleteTask.deleteCancel();
                }
            }
        });
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface.DeleteResultListener
    public void deleteFail(Integer[] numArr) {
        showToast(getString(R.string.delete_fail), 4);
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface.DeleteResultListener
    public void deleteSuccess(Integer[] numArr) {
        showToast(getString(R.string.delete_success), 3);
    }

    private void showToast(String str, int i) {
        MusicToastUtils1.showToast(Utils.getApp().getApplicationContext(), str, i);
        finish();
        EventBus.getDefault().post(new EventString("delete_item_music", String.valueOf(this.type)));
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface.DeleteResultListener
    public void updateDeleteMusicNum(int i) {
        ((ActivityLocalDialogConfirmBinding) this.dataBinding).seekBar.setProgress(i);
        ((ActivityLocalDialogConfirmBinding) this.dataBinding).tvProgress.setText(String.format(getResources().getString(R.string.delete_percent_is), Integer.valueOf(i)));
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface.DeleteResultListener
    public void cancelDelete(boolean z) {
        showToast(getString(z ? R.string.delete_is_cancelled_success : R.string.delete_is_cancelled_fail), 1);
    }
}
