package com.byd.music.fragment.bluetooth;

import android.media.AudioSystem;
import android.text.TextUtils;
import android.util.Pair;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;
import com.blankj.utilcode.util.StringUtils;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.ThirdSDK.Carplay.CarplayManager;
import com.byd.music.base.BaseViewModel;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothViewModel extends BaseViewModel {
    private static final int UPDATE_TIME = 100;
    public SingleLiveEvent openBluetoothConnect;
    public SimpleDateFormat sdfHms;
    public SimpleDateFormat sdfMs;
    public BindingCommand suggestItemClick = new BindingCommand(new BindingConsumer() { // from class: com.byd.music.fragment.bluetooth.-$$Lambda$BluetoothViewModel$eDRD7PIq1cHK1EKCV2MRkIyB5As
        @Override // com.byd.music.utils.binding.BindingConsumer
        public final void call(Object obj) {
            this.f$0.lambda$new$0$BluetoothViewModel(obj);
        }
    });
    public static ObservableBoolean isA2DPConnected = new ObservableBoolean(false);
    public static ObservableBoolean isHFPConnected = new ObservableBoolean(false);
    public static ObservableBoolean playOrPause = new ObservableBoolean(false);
    public static ObservableField<String> title = new ObservableField<>(StringUtils.getString(R.string.unknown_music));
    public static ObservableField<String> artist = new ObservableField<>(StringUtils.getString(R.string.unknown_artist));
    public static ObservableBoolean isUnknownTitle = new ObservableBoolean(true);
    public static ObservableBoolean isUnknownArtist = new ObservableBoolean(true);
    public static ObservableField<String> imageUrl = new ObservableField<>();
    public static ObservableLong currentTime = new ObservableLong(0);
    public static ObservableLong totalTime = new ObservableLong(0);
    public static ObservableField<String> connectedPhoneName = new ObservableField<>();
    public static ObservableBoolean isShowConnectedPhone = new ObservableBoolean();
    public static Pair<String, ObservableField<String>> totalTimeImgPair = new Pair<>(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, new ObservableField());
    public static ObservableInt battery = new ObservableInt();
    public static ObservableInt iconBattery = new ObservableInt();
    public static volatile boolean TAG_BT_ONACTIVE = false;

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
    }

    public /* synthetic */ void lambda$new$0$BluetoothViewModel(Object obj) {
        this.openBluetoothConnect.call();
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        this.sdfMs = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        this.sdfHms = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        this.openBluetoothConnect = new SingleLiveEvent();
    }

    public String toHhMmSs(long j) {
        LogUtil.d(this.TAG, "toHhMmSs : " + j);
        if (j >= 3600000) {
            return this.sdfHms.format(Long.valueOf(j));
        }
        return this.sdfMs.format(Long.valueOf(j));
    }

    public static void playOrPause() {
        try {
            if (playOrPause.get()) {
                LogUtil.d("Bluetooth Music play pause in toggle");
                HfpManage.getInstance().Pause_Music();
            } else if (!DeviceInfoUtils.isInCall()) {
                OnLineMusic.setMusicEngine(6);
                LogUtil.d("Bluetooth Music play play in toggle");
                HfpManage.getInstance().Play_Music();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void nextMusic() {
        if (DeviceInfoUtils.isInCall()) {
            return;
        }
        OnLineMusic.setMusicEngine(6);
        LogUtil.d("Bluetooth Music play next");
        HfpManage.getInstance().Next_Music();
    }

    public static void preMusic() {
        if (DeviceInfoUtils.isInCall()) {
            return;
        }
        OnLineMusic.setMusicEngine(6);
        LogUtil.d("Bluetooth Music play pre");
        HfpManage.getInstance().Pre_Music();
    }

    public static void playMusic() {
        if (DeviceInfoUtils.isInCall()) {
            return;
        }
        LogUtil.d("Bluetooth Music play");
        OnLineMusic.setMusicEngine(6);
        HfpManage.getInstance().Play_Music();
    }

    public static void pauseMusic() {
        LogUtil.d("Bluetooth Music pause");
        String btMac = getBtMac();
        String carplayMac = CarplayManager.getCarplayMac();
        if (TextUtils.isEmpty(btMac)) {
            LogUtil.d("Bluetooth Music pause, bt mac is empty, just return.");
        } else if (!TextUtils.isEmpty(btMac) && !TextUtils.isEmpty(carplayMac) && TextUtils.equals(carplayMac, btMac)) {
            LogUtil.d("Bluetooth Music pause, both connect to the one device. just return.");
        } else {
            HfpManage.getInstance().Pause_Music();
        }
    }

    public static String getBtMac() {
        try {
            return HfpManage.getInstance().getA2dpConnectDevice().getAddress();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void setBattery() {
        battery.get();
        iconBattery.set(R.drawable.bt_no_battery);
    }

    public static String getFormatArtist(String str) {
        return (TextUtils.equals(str, "未知歌手") || TextUtils.equals(str, "Unknown artist")) ? StringUtils.getString(R.string.unknown_artist) : str;
    }

    public static String getFormatTitle(String str) {
        return (TextUtils.equals(str, "未知歌曲") || TextUtils.equals(str, "Unknown music") || TextUtils.equals(str, "Not Provided")) ? StringUtils.getString(R.string.unknown_music) : str;
    }
}
