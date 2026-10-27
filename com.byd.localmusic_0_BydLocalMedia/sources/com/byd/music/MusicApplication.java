package com.byd.music;

import android.app.job.JobInfo;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAvrcpController;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Pair;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableLong;
import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.bluetoothaidl.listener.UIListener_Connect;
import com.byd.bluetoothaidl.listener.UIListener_Muisc;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.music.common.AppExecutors;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.byd.music.service.StorageService;
import com.byd.music.style.UiStyleResourceWrapper;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import com.bydux.api.common.views.application.BydApplication;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes5.dex */
public class MusicApplication extends BydApplication implements UIListener_Muisc, UIListener_Connect {
    static final String TAG = "MusicApplication";
    private long autoPlayEngageTiming;
    private BluetoothAdapter bluetoothAdapter;
    private boolean isBind = false;
    private boolean isRegisterAvrcp = false;
    private ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.byd.music.MusicApplication.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicApplication.this.isBind = true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MusicApplication.this.isBind = false;
        }
    };
    private final BroadcastReceiver avrcpReceiver = new BroadcastReceiver() { // from class: com.byd.music.MusicApplication.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.e(MusicApplication.TAG, "avrcpReceiver onReceive action:" + action);
            if (BluetoothAvrcpController.ACTION_CONNECTION_STATE_CHANGED.equals(action)) {
                int intExtra = intent.getIntExtra(BluetoothProfile.EXTRA_STATE, 0);
                if (intExtra == 0) {
                    LogUtil.e(MusicApplication.TAG, "avrcpReceiver STATE_DISCONNECTED:");
                } else {
                    if (intExtra != 2) {
                        return;
                    }
                    LogUtil.e(MusicApplication.TAG, "avrcpReceiver STATE_CONNECTED:");
                    MusicApplication.this.handleA2dpStateChange();
                }
            }
        }
    };

    @Override // com.bydux.api.common.views.application.BydApplication
    protected boolean onSkinEnable() {
        return true;
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        LogUtil.e(TAG, "MusicApplication-onCreate: " + Utils.getApp().getPackageName().equals(ProcessUtils.getCurrentProcessName()));
        if (Utils.getApp().getPackageName().equals(ProcessUtils.getCurrentProcessName())) {
            OnLineMusic.init(this);
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            HfpManage.getInstance().init(getApplicationContext(), "com.byd.bluetoothcall");
            HfpManage.getInstance().registerUIListener_Music(this);
            HfpManage.getInstance().registerUIListener_Connect(this);
            BluetoothViewModel.isHFPConnected.set(HfpManage.getInstance().isHFPConnect());
            LogUtil.e("isHFPConnected = " + HfpManage.getInstance().isHFPConnect());
            BluetoothViewModel.totalTime.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.MusicApplication.2
                @Override // androidx.databinding.Observable.OnPropertyChangedCallback
                public void onPropertyChanged(Observable observable, int i) {
                    BluetoothViewModel.totalTimeImgPair = new Pair<>(BluetoothViewModel.totalTime.get() + "", new ObservableField());
                }
            });
            BluetoothViewModel.isA2DPConnected.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.MusicApplication.3
                @Override // androidx.databinding.Observable.OnPropertyChangedCallback
                public void onPropertyChanged(Observable observable, int i) {
                    MusicApplication.this.handleA2dpStateChange();
                }
            });
            BluetoothViewModel.isA2DPConnected.set(HfpManage.getInstance().isA2DPConnect());
            LogUtil.e("isA2DPConnected = " + BluetoothViewModel.isA2DPConnected.get());
        }
        bindService(new Intent(this, (Class<?>) StorageService.class), this.serviceConnection, 1);
        RxJavaPlugins.setErrorHandler(new Consumer() { // from class: com.byd.music.-$$Lambda$MusicApplication$ECsfJ62Fec3uRJxBAmrj2v03lro
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LogUtil.i(" MusicApplication --> RxJavaError==" + ((Throwable) obj).getMessage());
            }
        });
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return new UiStyleResourceWrapper(this, super.getResources());
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Muisc
    public void updateA2dpConnectState(int i) {
        LogUtil.e(TAG, "updateA2dpConnectState = " + i);
        if (i == 1) {
            try {
                if (BluetoothViewModel.isA2DPConnected.get() && !BluetoothViewModel.connectedPhoneName.get().equals(HfpManage.getInstance().getA2dpConnectDeviceName())) {
                    LogUtil.e(TAG, "updateA2dpConnectState device= " + StringSubUtil.subString(HfpManage.getInstance().getA2dpConnectDeviceName()));
                    handleA2dpStateChange();
                }
            } catch (Throwable th) {
                LogUtil.e(TAG, "updateA2dpConnectState error = " + th.getMessage());
            }
        }
        BluetoothViewModel.isA2DPConnected.set(i == 1);
        if (i != 1) {
            BluetoothViewModel.TAG_BT_ONACTIVE = false;
        }
        if (OnLineMusic.musicEngine == 6) {
            CurrentMediaInfoUtils.getInstance().notifyWidgetUpdate();
        }
        LogUtil.e(TAG, "isRegisterAvrcp is " + this.isRegisterAvrcp);
        if (this.isRegisterAvrcp || i != 1) {
            return;
        }
        int avrcpState = getAvrcpState();
        LogUtil.e(TAG, "avrcpState is " + avrcpState);
        if (avrcpState != 2) {
            LogUtil.e(TAG, "avrcpState is not connected,goto register avrcp");
            registerAvrcpReceiver(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleA2dpStateChange() {
        LogUtil.d(TAG, "handleA2dpStateChange");
        if (BluetoothViewModel.isA2DPConnected.get()) {
            if (OnLineMusic.musicEngine == 6) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                LogUtil.d("bluetoothTime : bt connected time = ", jCurrentTimeMillis + "");
                long j = SPUtils.getInstance().getLong("killedByMultiTaskTime", 0L);
                SPUtils.getInstance().put("killedByMultiTaskTime", 0L);
                if (j > 0 && jCurrentTimeMillis - j > JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS) {
                    LogUtil.d("bluetoothTime : ", "autoplay killedByMultiTaskTime gap 30s");
                    autoPlayBluetooth();
                } else if (j == 0) {
                    LogUtil.d("bluetoothTime : ", "autoplay killedByMultiTaskTime time 0");
                    autoPlayBluetooth();
                }
            }
            BluetoothViewModel.connectedPhoneName.set(HfpManage.getInstance().getA2dpConnectDeviceName());
            int battery = HfpManage.getInstance().getBattery();
            LogUtil.e("getBattery = " + HfpManage.getInstance().getBattery());
            BluetoothViewModel.battery.set(battery);
            BluetoothViewModel.setBattery();
            BluetoothViewModel.title.set(StringUtils.isTrimEmpty(HfpManage.getInstance().getmMusicTile()) ? getResources().getString(R.string.unknown_music) : BluetoothViewModel.getFormatTitle(HfpManage.getInstance().getmMusicTile()));
            BluetoothViewModel.artist.set(StringUtils.isTrimEmpty(HfpManage.getInstance().getArtist()) ? getResources().getString(R.string.unknown_artist) : BluetoothViewModel.getFormatArtist(HfpManage.getInstance().getArtist()));
            BluetoothViewModel.currentTime.set(HfpManage.getInstance().getCurrentTime());
            BluetoothViewModel.totalTime.set(HfpManage.getInstance().getmTotal_time());
            if (BluetoothViewModel.currentTime.get() > BluetoothViewModel.totalTime.get()) {
                LogUtil.d(TAG, "updateBTConnectedInfo: Current Time is Larger Than Total Time, Reset Current Time To 0.");
                BluetoothViewModel.currentTime.set(0L);
            }
            BluetoothViewModel.imageUrl.set(HfpManage.getInstance().getIconUrl());
        } else {
            BluetoothViewModel.connectedPhoneName.set("");
            BluetoothViewModel.currentTime.set(0L);
            BluetoothViewModel.totalTime.set(0L);
            if (CurrentMediaInfoUtils.getInstance().getPlayingState() && OnLineMusic.musicEngine == 6) {
                CurrentMediaInfoUtils.getInstance().expectPlayingStatusShouldNotBeOverride = true;
            }
            BluetoothViewModel.playOrPause.set(false);
            if (OnLineMusic.musicEngine == 6) {
                CurrentMediaInfoUtils.getInstance().setPlayingState(false);
            }
            BluetoothViewModel.artist.set(getString(R.string.unknown_artist));
            BluetoothViewModel.title.set(getString(R.string.unknown_music));
            BluetoothViewModel.imageUrl.set("");
        }
        if (OnLineMusic.musicEngine == 6) {
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        }
    }

    private void autoPlayBluetooth() {
        LogUtil.d(TAG, "autoPlayBluetooth enter");
        if (OnLineMusic.musicEngine == 6 && CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState() && !DeviceInfoUtils.isInCall(false)) {
            LogUtil.e(TAG, "updateA2dpConnectState = 1, gotoPlay with savePlaying = " + CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState());
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
            if (OnLineMusic.bootComplete) {
                final long jCurrentTimeMillis = System.currentTimeMillis();
                this.autoPlayEngageTiming = jCurrentTimeMillis;
                AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.-$$Lambda$MusicApplication$BgA6iT-Su7pFxPG_DYrsaDYc4Fo
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$autoPlayBluetooth$1$MusicApplication(jCurrentTimeMillis);
                    }
                }, 1000L);
                return;
            }
            OnLineMusic.bootWaitForBT = true;
            return;
        }
        LogUtil.d(TAG, "autoPlayBluetooth failure OnLineMusic.musicEngine：6,savePlaying:" + CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState() + ",isInCall:" + DeviceInfoUtils.isInCall(false));
    }

    public /* synthetic */ void lambda$autoPlayBluetooth$1$MusicApplication(long j) {
        if (j != this.autoPlayEngageTiming) {
            return;
        }
        BluetoothViewModel.playMusic();
        LogUtil.e(TAG, "updateA2dpConnectState auto play bt music finish");
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Muisc
    public void updateBattery(int i) {
        LogUtil.e("battery = " + i);
        if (BluetoothViewModel.isA2DPConnected.get()) {
            BluetoothViewModel.battery.set(i);
            BluetoothViewModel.setBattery();
        }
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Muisc
    public void updateTitle(String str, String str2, String str3, String str4) {
        LogUtil.e(TAG, "updateTitle = " + StringSubUtil.subString(str) + ", artist = " + StringSubUtil.subString(str2));
        if (!BluetoothViewModel.isA2DPConnected.get()) {
            LogUtil.e(TAG, "updateTitle no a2dp just return");
            return;
        }
        BluetoothViewModel.isUnknownTitle.set(StringUtils.isTrimEmpty(str));
        BluetoothViewModel.isUnknownArtist.set(StringUtils.isTrimEmpty(str2));
        if (TextUtils.isEmpty(str) || StringUtils.isTrimEmpty(str)) {
            str = getResources().getString(R.string.unknown_song);
            LogUtil.e("musicAppliction" + StringSubUtil.subString(str));
        }
        if (TextUtils.isEmpty(str2) || StringUtils.isTrimEmpty(str2)) {
            str2 = getResources().getString(R.string.unknown_artist);
        }
        if (!TextUtils.equals(str2, BluetoothViewModel.artist.get())) {
            BluetoothViewModel.artist.set(BluetoothViewModel.getFormatArtist(str2));
        }
        BluetoothViewModel.title.set(BluetoothViewModel.getFormatTitle(str));
        LogUtil.d(TAG, "updateTitle: totalTimeImgPair.second is " + BluetoothViewModel.totalTimeImgPair.second.get());
        if (TextUtils.isEmpty(BluetoothViewModel.totalTimeImgPair.second.get())) {
            LogUtil.d(TAG, "updateTitle: imgUrl is " + BluetoothViewModel.imageUrl.get());
            if (!TextUtils.equals(str4, BluetoothViewModel.imageUrl.get())) {
                BluetoothViewModel.imageUrl.set(str4);
                BluetoothViewModel.totalTimeImgPair.second.set(str4);
                LogUtil.e(TAG, "Bluetooth imageUrl changed = " + str4);
            }
        } else if (!TextUtils.isEmpty(str4) && str4.startsWith("content://") && !TextUtils.equals(str4, BluetoothViewModel.imageUrl.get())) {
            BluetoothViewModel.imageUrl.set(str4);
            BluetoothViewModel.totalTimeImgPair.second.set(str4);
            LogUtil.e(TAG, "Bluetooth imageUrl changed = " + str4);
        }
        if (OnLineMusic.musicEngine == 6) {
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        }
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Muisc
    public void updateTotalTime(long j) {
        LogUtil.e(TAG, "updateTotalTime = " + j);
        if (!BluetoothViewModel.isA2DPConnected.get()) {
            LogUtil.e(TAG, "updateTotalTime no a2dp just return");
            return;
        }
        if (j < BluetoothViewModel.currentTime.get()) {
            BluetoothViewModel.currentTime.set(0L);
            LogUtil.d(TAG, "updateTotalTime = currentTime:" + BluetoothViewModel.currentTime.get() + " > totalTime" + j);
        }
        if (BluetoothViewModel.totalTime.get() != j) {
            LogUtil.e(TAG, "updateTotalTime = total time change to " + j + " from " + BluetoothViewModel.totalTime.get());
            BluetoothViewModel.currentTime.set(0L);
            ObservableLong observableLong = BluetoothViewModel.totalTime;
            if (j <= 0) {
                j = 0;
            }
            observableLong.set(j);
            if (OnLineMusic.musicEngine == 6) {
                CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(0L, 0L, BluetoothViewModel.totalTime.get());
            }
        }
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Muisc
    public void updateCurrentTime(long j) {
        long j2;
        LogUtil.e(TAG, "updateCurrentTime = " + j + " -> getMPlayState:" + HfpManage.getInstance().getmPlay_state());
        if (!BluetoothViewModel.isA2DPConnected.get()) {
            LogUtil.e(TAG, "updateCurrentTime no a2dp just return");
            return;
        }
        if (j > BluetoothViewModel.totalTime.get()) {
            LogUtil.d(TAG, "updateCurrentTime = currentTime:" + j + " > totalTime" + BluetoothViewModel.totalTime.get());
            j2 = 0L;
        } else {
            j2 = j;
        }
        BluetoothViewModel.playOrPause.set(HfpManage.getInstance().getmPlay_state() == 3);
        if (BluetoothViewModel.playOrPause.get()) {
            BluetoothViewModel.TAG_BT_ONACTIVE = true;
        }
        if (BluetoothViewModel.title.get().equals(getResources().getString(R.string.unknown_music)) || BluetoothViewModel.title.get().equals(getResources().getString(R.string.unknown_song))) {
            BluetoothViewModel.currentTime.set(0L);
        } else {
            BluetoothViewModel.currentTime.set(j2);
        }
        if (OnLineMusic.musicEngine != 6) {
            return;
        }
        CurrentMediaInfoUtils.getInstance().setPlayingState(HfpManage.getInstance().getmPlay_state() == 3);
        CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(j2, j2, BluetoothViewModel.totalTime.get());
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Muisc
    public void updatePlayState(final int i) {
        LogUtil.e(TAG, "updatePlayState = " + i);
        if (!BluetoothViewModel.isA2DPConnected.get()) {
            LogUtil.e(TAG, "updatePlayState no a2dp just return");
            return;
        }
        if (OnLineMusic.musicEngine != 6 && i == 3 && OnLineMusic.isBTMusicActive) {
            OnLineMusic.setMusicEngine(6);
        }
        new Timer().schedule(new TimerTask() { // from class: com.byd.music.MusicApplication.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                BluetoothViewModel.playOrPause.set(i == 3);
                if (BluetoothViewModel.playOrPause.get()) {
                    BluetoothViewModel.TAG_BT_ONACTIVE = true;
                }
                if (OnLineMusic.musicEngine == 6) {
                    CurrentMediaInfoUtils.getInstance().setPlayingState(i == 3);
                }
            }
        }, 50L);
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Connect
    public void updateHFPConnectState(int i) {
        LogUtil.e(TAG, "updateHFPConnectState = " + i);
        BluetoothViewModel.isHFPConnected.set(i == 2);
    }

    @Override // com.bydux.api.common.views.application.BydApplication, com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinSuccess() {
        CurrentMediaInfoUtils.getInstance().updateAlbumBitmap();
        CurrentMediaInfoUtils.getInstance().notifyWidgetUpdate(true);
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.e(TAG, "onConfigurationChanged: " + configuration);
        if (BluetoothViewModel.isUnknownTitle.get()) {
            BluetoothViewModel.title.set(getString(R.string.unknown_music));
        }
        if (BluetoothViewModel.isUnknownArtist.get()) {
            BluetoothViewModel.artist.set(getString(R.string.unknown_artist));
        }
        if (OnLineMusic.musicEngine == 6) {
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        }
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.-$$Lambda$MusicApplication$hSFI69TzCm9Rp1-h8aICOuLBuPc
            @Override // java.lang.Runnable
            public final void run() {
                MusicApplication.lambda$onConfigurationChanged$2();
            }
        });
        if (OnLineMusic.preLoadMap.isEmpty()) {
            return;
        }
        OnLineMusic.preLoadMap.clear();
        OnLineMusic.preloadFrontPageLayout(Utils.getApp());
    }

    static /* synthetic */ void lambda$onConfigurationChanged$2() {
        CurrentMediaInfoUtils.getInstance().lambda$new$2$CurrentMediaInfoUtils();
        CurrentMediaInfoUtils.getInstance().updateForceAndNotify();
    }

    private synchronized void registerAvrcpReceiver(Context context) {
        if (!this.isRegisterAvrcp) {
            this.isRegisterAvrcp = true;
            LogUtil.e(TAG, "register AvrcpReceiver");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BluetoothAvrcpController.ACTION_CONNECTION_STATE_CHANGED);
            context.registerReceiver(this.avrcpReceiver, intentFilter);
        } else {
            LogUtil.e(TAG, "avrcp has registered");
        }
    }

    private int getAvrcpState() {
        LogUtil.e(TAG, "getAvrcpState enter thread:");
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                int profileConnectionState = this.bluetoothAdapter.getProfileConnectionState(12);
                LogUtil.e(TAG, "getAvrcpState result " + profileConnectionState);
                return profileConnectionState;
            }
            LogUtil.e(TAG, "getAvrcpState bluetoothAdapter.isDisEnabled()");
        } else {
            LogUtil.e(TAG, "getAvrcpState bluetoothAdapter is null");
        }
        LogUtil.e(TAG, "getAvrcpState result -1");
        return -1;
    }
}
