package com.byd.music.activity.main;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;
import com.blankj.utilcode.util.SizeUtils;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.api.ApiMethod;
import com.byd.music.base.BaseActivity;
import com.byd.music.beans.ApiChangeBean;
import com.byd.music.beans.EventString;
import com.byd.music.beans.FragmentSwitchBean;
import com.byd.music.databinding.ActivityMediaMainBinding;
import com.byd.music.fragment.bluetooth.BlueToothFragment;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.PermissionUtil;
import com.byd.music.utils.mediahelper.MusicWidgetProvider;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class MediaMainActivity extends BaseActivity<ActivityMediaMainBinding, MediaMainViewModel> {
    private boolean isMemoryPlayReady;
    private boolean isNeedMemoryLocalTabReady;
    private Runnable runnable;
    private Handler scanHandler;
    SparseBooleanArray tabSelectedArray;
    private boolean isBind = false;
    private final int UPDATE_MESSAGE = 111;
    int tabSelectedCount = 1;
    private ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.byd.music.activity.main.MediaMainActivity.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaMainActivity.this.isBind = true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MediaMainActivity.this.isBind = false;
        }
    };
    boolean isNeedIntercept = false;

    private void refreshTabSelect(int i) {
    }

    @Override // com.byd.music.base.BaseActivity
    protected boolean isPreLoad() {
        return true;
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.drawable.app_bg);
        if (BYDAutoBodyworkDevice.getInstance(this).getPowerLevel() == 0) {
            LogUtil.e(this.TAG, " getPowerLevel BODYWORK_POWER_LEVEL_OFF, so just return");
        } else if (!CurrentMediaInfoUtils.getInstance().getPlayingState() && CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState()) {
            this.isMemoryPlayReady = true;
        }
        setNeedMemoryLocalTabReady(true);
    }

    @Override // com.byd.music.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_media_main;
    }

    @Override // com.byd.music.base.BaseActivity
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initData() {
        PermissionUtil.checkPermission(this);
        if (((MediaMainViewModel) this.viewModel).titleList != null) {
            LogUtil.d(this.TAG, "initData return viewModel.titleList size:" + ((MediaMainViewModel) this.viewModel).titleList.size());
            return;
        }
        ((MediaMainViewModel) this.viewModel).titleList = new ArrayList();
        ((MediaMainViewModel) this.viewModel).sourceImageList = new ArrayList();
        if (RadioServiceWrapper.getInstance().getFMEnable()) {
            ((MediaMainViewModel) this.viewModel).titleList.add(Integer.valueOf(R.string.module_name_fm));
            ((MediaMainViewModel) this.viewModel).sourceImageList.add(Integer.valueOf(R.drawable.icon_media_fm_48));
        }
        if (RadioServiceWrapper.getInstance().getAMEnable()) {
            ((MediaMainViewModel) this.viewModel).titleList.add(Integer.valueOf(R.string.module_name_am));
            ((MediaMainViewModel) this.viewModel).sourceImageList.add(Integer.valueOf(R.drawable.icon_media_am_48));
        }
        if (RadioServiceWrapper.getInstance().getDABEnable()) {
            ((MediaMainViewModel) this.viewModel).titleList.add(Integer.valueOf(R.string.module_name_dab));
            ((MediaMainViewModel) this.viewModel).sourceImageList.add(Integer.valueOf(R.drawable.icon_media_dab_48));
        }
        ((MediaMainViewModel) this.viewModel).titleList.add(Integer.valueOf(R.string.bluetooth_music));
        ((MediaMainViewModel) this.viewModel).sourceImageList.add(Integer.valueOf(R.drawable.icon_dark_media_btmusic_48));
        ((MediaMainViewModel) this.viewModel).titleList.add(Integer.valueOf(R.string.local_music));
        ((MediaMainViewModel) this.viewModel).sourceImageList.add(Integer.valueOf(R.drawable.icon_dark_media_folder_48));
        ((MediaMainViewModel) this.viewModel).titleList.add(Integer.valueOf(R.string.local_storage_sdcard));
        ((MediaMainViewModel) this.viewModel).sourceImageList.add(Integer.valueOf(R.drawable.icon_media_sd_48));
        ((MediaMainViewModel) this.viewModel).titleList.add(Integer.valueOf(R.string.local_storage_usb));
        ((MediaMainViewModel) this.viewModel).sourceImageList.add(Integer.valueOf(R.drawable.icon_media_usb_48));
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initView() {
        this.tabSelectedArray = new SparseBooleanArray();
        this.tabSelectedCount = 1;
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.setUserInputEnabled(false);
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.setAdapter(new MediaViewPager2Adapter(this, ((MediaMainViewModel) this.viewModel).titleList));
        int index = ((MediaMainViewModel) this.viewModel).checkedPosition.get();
        if (index == 0) {
            switch (OnLineMusic.musicEngine) {
                case 3:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_storage_sdcard));
                    break;
                case 4:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_storage_usb));
                    break;
                case 5:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_music));
                    break;
                case 6:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.bluetooth_music));
                    break;
                case 7:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_fm));
                    break;
                case 8:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_am));
                    break;
                case 9:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_dab));
                    break;
                default:
                    index = ((MediaMainViewModel) this.viewModel).getIndex(((MediaMainViewModel) this.viewModel).titleList.get(0));
                    break;
            }
        }
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.byd.music.activity.main.MediaMainActivity.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                LogUtil.e(MediaMainActivity.this.TAG, "onPageSelected: position = " + i + ", currentPageIndex = " + ((MediaMainViewModel) MediaMainActivity.this.viewModel).checkedPosition.get());
                MediaMainActivity.this.updateCheckedItem(i);
                if (i != ((MediaMainViewModel) MediaMainActivity.this.viewModel).getIndex(Integer.valueOf(R.string.bluetooth_music))) {
                    ((ActivityMediaMainBinding) MediaMainActivity.this.dataBinding).btMusicBattery.setVisibility(8);
                    OnLineMusic.isBTViewActive = false;
                    OnLineMusic.checkBluetoothMusicActive();
                    ((MediaMainViewModel) MediaMainActivity.this.viewModel).isBluetoothPage.set(false);
                    return;
                }
                ((MediaMainViewModel) MediaMainActivity.this.viewModel).isBluetoothPage.set(true);
            }
        });
        applyViewByOrientation(this.lastIsMultiMode, this.lastDeviceOrientation);
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.setCurrentItem(index, false);
        LogUtil.e(this.TAG, "activity initView, set position = " + index);
        ((MediaMainViewModel) this.viewModel).sourceClickEvent.observe(this, new Observer() { // from class: com.byd.music.activity.main.-$$Lambda$MediaMainActivity$SRkd8cd-vT2uUPymhTTlNH3tD_4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initView$0$MediaMainActivity((Integer) obj);
            }
        });
        ((ActivityMediaMainBinding) this.dataBinding).btMusicBattery.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.activity.main.MediaMainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BlueToothFragment.toConnectBlueTooth();
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$MediaMainActivity(Integer num) {
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.setCurrentItem(num.intValue(), false);
    }

    @Override // com.byd.music.base.BaseActivity
    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
        applyViewByOrientation(z, i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveChangeApi(ApiChangeBean apiChangeBean) {
        final int index;
        switch (OnLineMusic.musicEngine) {
            case 3:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_storage_sdcard));
                break;
            case 4:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_storage_usb));
                break;
            case 5:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_music));
                break;
            case 6:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.bluetooth_music));
                break;
            case 7:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_fm));
                break;
            case 8:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_am));
                break;
            case 9:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_dab));
                break;
            default:
                index = -1;
                break;
        }
        LogUtil.d(this.TAG, "receiveChangeApi get position = " + index);
        if (index >= 0) {
            updateCheckedItem(index);
            if (this.dataBinding == 0 || ((ActivityMediaMainBinding) this.dataBinding).viewPager == null) {
                return;
            }
            ((ActivityMediaMainBinding) this.dataBinding).viewPager.post(new Runnable() { // from class: com.byd.music.activity.main.-$$Lambda$MediaMainActivity$iuzUET8GGtsGZtyy1wUfqsUYskQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$receiveChangeApi$1$MediaMainActivity(index);
                }
            });
        }
    }

    public /* synthetic */ void lambda$receiveChangeApi$1$MediaMainActivity(int i) {
        if (this.dataBinding == 0 || ((ActivityMediaMainBinding) this.dataBinding).viewPager == null) {
            return;
        }
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.setCurrentItem(i, false);
        LogUtil.d(this.TAG, "receiveChangeApi post finish with finalPosition = " + i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveChangeApi(FragmentSwitchBean fragmentSwitchBean) {
        final int index;
        LogUtil.d(this.TAG, "receiveChangeApi FragmentSwitchBean enter.");
        switch (OnLineMusic.musicEngine) {
            case 3:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_storage_sdcard));
                break;
            case 4:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_storage_usb));
                break;
            case 5:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.local_music));
                break;
            case 6:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.bluetooth_music));
                break;
            case 7:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_fm));
                break;
            case 8:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_am));
                break;
            case 9:
                index = ((MediaMainViewModel) this.viewModel).getIndex(Integer.valueOf(R.string.module_name_dab));
                break;
            default:
                index = -1;
                break;
        }
        if (index < 0 || this.dataBinding == 0 || ((ActivityMediaMainBinding) this.dataBinding).viewPager == null) {
            return;
        }
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.post(new Runnable() { // from class: com.byd.music.activity.main.-$$Lambda$MediaMainActivity$512oISETSuWJfZHC5n-V4Qq5zM4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$receiveChangeApi$2$MediaMainActivity(index);
            }
        });
    }

    public /* synthetic */ void lambda$receiveChangeApi$2$MediaMainActivity(int i) {
        if (this.dataBinding == 0 || ((ActivityMediaMainBinding) this.dataBinding).viewPager == null) {
            return;
        }
        ((ActivityMediaMainBinding) this.dataBinding).viewPager.setCurrentItem(i, false);
        LogUtil.d(this.TAG, "receiveChangeApi FragmentSwitchBean leave with position = " + i);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void applyViewByOrientation(boolean z, int i) {
        if (!((MediaMainViewModel) this.viewModel).isPortraitMode.get() && !z) {
            ((ActivityMediaMainBinding) this.dataBinding).verticalSourceListContainer.setVisibility(0);
            ((ActivityMediaMainBinding) this.dataBinding).horizontalSourceListContainer.setVisibility(8);
        } else {
            ((ActivityMediaMainBinding) this.dataBinding).horizontalSourceListContainer.setVisibility(0);
            ((ActivityMediaMainBinding) this.dataBinding).verticalSourceListContainer.setVisibility(8);
        }
        if (((MediaMainViewModel) this.viewModel).isMultiMode.get()) {
            ((ActivityMediaMainBinding) this.dataBinding).viewPager.setPadding(((ActivityMediaMainBinding) this.dataBinding).viewPager.getPaddingLeft(), ((ActivityMediaMainBinding) this.dataBinding).viewPager.getPaddingTop(), ((ActivityMediaMainBinding) this.dataBinding).viewPager.getPaddingRight(), 0);
            ((ActivityMediaMainBinding) this.dataBinding).mainConstraint.setPadding(((ActivityMediaMainBinding) this.dataBinding).mainConstraint.getPaddingLeft(), ((ActivityMediaMainBinding) this.dataBinding).mainConstraint.getPaddingTop(), ((ActivityMediaMainBinding) this.dataBinding).mainConstraint.getPaddingRight(), 0);
        } else {
            ((ActivityMediaMainBinding) this.dataBinding).viewPager.setPadding(((ActivityMediaMainBinding) this.dataBinding).viewPager.getPaddingLeft(), ((ActivityMediaMainBinding) this.dataBinding).viewPager.getPaddingTop(), ((ActivityMediaMainBinding) this.dataBinding).viewPager.getPaddingRight(), SizeUtils.dp2px(20.0f));
            ((ActivityMediaMainBinding) this.dataBinding).mainConstraint.setPadding(((ActivityMediaMainBinding) this.dataBinding).mainConstraint.getPaddingLeft(), ((ActivityMediaMainBinding) this.dataBinding).mainConstraint.getPaddingTop(), ((ActivityMediaMainBinding) this.dataBinding).mainConstraint.getPaddingRight(), SizeUtils.dp2px(16.0f));
        }
        LogUtil.d(this.TAG, "applyViewByOrientation finish");
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (((ActivityMediaMainBinding) this.dataBinding).viewPager.getCurrentItem() != ((MediaMainViewModel) this.viewModel).checkedPosition.get()) {
                ((ActivityMediaMainBinding) this.dataBinding).viewPager.setCurrentItem(((MediaMainViewModel) this.viewModel).checkedPosition.get(), false);
            }
        } catch (Throwable th) {
            LogUtil.d(this.TAG, "onResume viewPager and viewModel no match but error. e = " + th.getMessage());
            th.printStackTrace();
        }
        ApiMethod.showWidgetCards(this);
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.byd.music.activity.main.-$$Lambda$MediaMainActivity$Efwxj8N5l7DZ5qXqa-o4xOw8ajA
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return this.f$0.lambda$onResume$3$MediaMainActivity();
            }
        });
    }

    public /* synthetic */ boolean lambda$onResume$3$MediaMainActivity() {
        int i;
        StringBuilder sb = new StringBuilder("isMemoryPlayReady=");
        sb.append(this.isMemoryPlayReady);
        if (this.isMemoryPlayReady) {
            int i2 = OnLineMusic.musicEngine;
            if (i2 == 3 || i2 == 4 || i2 == 5) {
                if (!MusicPlayHelper.getInstance().isPlaying.get()) {
                    boolean zMemoryPlayAccOn = MusicPlayHelper.getInstance().memoryPlayAccOn();
                    sb.append(" memoryPlayResult=");
                    sb.append(zMemoryPlayAccOn);
                }
            } else {
                ApiMethod.playOrStop(true);
            }
            this.isMemoryPlayReady = false;
        }
        sb.append(" isNeedMemoryLocalTabReady=");
        sb.append(this.isNeedMemoryLocalTabReady);
        if (this.isNeedMemoryLocalTabReady) {
            if (this.isMemoryPlayReady && ((i = OnLineMusic.musicEngine) == 3 || i == 4 || i == 5)) {
                sb.append(" and send event of memoryLocalTab!");
                EventBus.getDefault().post(new EventString("memoryLocalTab", "memoryLocalSourceType"));
            }
            setNeedMemoryLocalTabReady(false);
        }
        LogUtil.e(this.TAG, sb.toString());
        CurrentMediaInfoUtils.getInstance().notifyWidgetUpdate(true);
        return false;
    }

    private void setNeedMemoryLocalTabReady(boolean z) {
        LogUtil.e(this.TAG, " isNeedMemoryLocalTabReady= " + z);
        this.isNeedMemoryLocalTabReady = z;
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        LogUtil.d(this.TAG, "onStart: 111");
        MusicWidgetProvider.isSelected = true;
    }

    @Override // com.byd.music.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        LogUtil.d(this.TAG, "onStop: 111");
        MusicWidgetProvider.isSelected = false;
        CurrentMediaInfoUtils.getInstance().notifyWidgetUpdate(true);
    }

    @Override // com.byd.music.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        OnLineMusic.isBTViewActive = false;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void receiveEventString(EventString eventString) {
        if (eventString == null || !"SCAN_MESSAGE_COMPLETE".equals(eventString.getClazz())) {
            return;
        }
        LogUtil.e(this.TAG, "receiveEventString: SCAN_MESSAGE_COMPLETE");
        EventBus.getDefault().post(new EventString("update_data", "update_data"));
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 6) {
            this.isNeedIntercept = true;
        }
        if (motionEvent.getAction() == 1 && this.isNeedIntercept) {
            this.isNeedIntercept = false;
            motionEvent.setAction(3);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCheckedItem(int i) {
        if (((MediaMainViewModel) this.viewModel).checkedPosition == null || ((MediaMainViewModel) this.viewModel).checkedPosition.get() == i) {
            return;
        }
        ((MediaMainViewModel) this.viewModel).checkedPosition.set(i);
        if (this.dataBinding == 0) {
            return;
        }
        if (((ActivityMediaMainBinding) this.dataBinding).verticalSourceListContainer.getVisibility() == 0) {
            ((ActivityMediaMainBinding) this.dataBinding).verticalSourceList.scrollToPosition(i);
        } else {
            ((ActivityMediaMainBinding) this.dataBinding).horizontalSourceList.scrollToPosition(i);
        }
    }
}
