package com.byd.music.service;

import android.car.Car;
import android.car.hardware.CarVendorExtensionManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.radio.BYDAutoRadioDevice;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioMetadata;
import android.media.AudioSystem;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.android.car.radio.SkipMode;
import com.android.car.radio.bands.ProgramType;
import com.android.car.radio.bands.RegionConfig;
import com.android.car.radio.platform.RadioTunerExt;
import com.android.car.radio.service.IRadioAppCallback;
import com.android.car.radio.service.IRadioAppService;
import com.android.car.radio.service.ITuneCallback;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.beans.ApiChangeBean;
import com.byd.music.beans.MultiMusicBean;
import com.byd.music.beans.RadioConfigChangeBean;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.AudioFocusManager;
import com.byd.music.db.RadioDB;
import com.byd.music.db.RadioEpgDB;
import com.byd.music.fragment.radio.IRadioProgramListener;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.RadioPTYUtil;
import com.byd.music.utils.ToastUtil;
import com.byd.music.view.AlarmView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class RadioServiceWrapper {
    public static final int RADIO_AM = 1;
    public static final int RADIO_DAB = 4;
    public static final int RADIO_DAB_PYT = 5;
    public static final int RADIO_FM = 2;
    public static final int RADIO_FM_RDS = 3;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_ERROR = 4;
    public static final int STATE_NOT_SUPPORTED = 3;
    private static final String TAG = "RadioServiceWrapper";
    public static final int TYPE_OF_LAST_AM = 102;
    public static final int TYPE_OF_LAST_DAB = 103;
    public static final int TYPE_OF_LAST_FM = 101;
    public static final int TYPE_OF_SCAN_MEMORY_AM = 991;
    public static final int TYPE_OF_SCAN_MEMORY_DAB = 994;
    public static final int TYPE_OF_SCAN_MEMORY_FM = 992;
    public static final int TYPE_OF_SCAN_MEMORY_OFFSET = 990;
    public static final int TYPE_OF_SCAN_MEMORY_RDS = 993;
    private static RadioServiceWrapper mInstance;
    private final int AM_SYNC_HIGHFREQ;
    private final int AM_SYNC_LOWFREQ;
    public volatile int DAB_BER;
    public volatile int ID_ONE_STRENGTH;
    public volatile int ID_TWO_STRENGTH;
    public ObservableArrayList<RadioDBBean> amCollectList;
    private ObservableBoolean amPlaying;
    public ObservableArrayList<RadioDBBean> amValidList;
    public RadioDBBean currentAmRadioDBBean;
    public RadioDBBean currentDABRadioDBBean;
    public RadioDBBean currentFMRadioDBBean;
    private Runnable currentRadioWriteToDBRunnable;
    public ObservableArrayList<RadioDBBean> dabCollectList;
    private Runnable dabDisableRunnable;
    private ObservableBoolean dabPlaying;
    public ObservableArrayList<RadioDBBean> dabValidList;
    private final Runnable epgRunnable;
    public volatile long finalSeekTiming;
    public ObservableArrayList<RadioDBBean> fmCollectList;
    private ObservableBoolean fmPlaying;
    public ObservableArrayList<RadioDBBean> fmValidList;
    int freqBeforeSeek;
    public final Handler handler;
    private boolean isAMEnable;
    private boolean isDABEnable;
    private boolean isEPGEnable;
    private boolean isFMEnable;
    public volatile long isGetDadBER;
    public volatile long isGetFiledStrength;
    public ObservableBoolean isPlaying;
    public volatile boolean isRadioHalReady;
    public volatile boolean isSeeking;
    public volatile boolean isSeekingAbort;
    public volatile boolean isTestMode;
    private int lastRadioType;
    private final IRadioAppCallback mCallback;
    private Context mClientContext;
    private final ObservableInt mConnectionState;
    private final AtomicReference<RadioService> mControlService;
    private final ObservableField<RadioManager.ProgramInfo> mCurrentProgram;
    public final MutableLiveData<RadioDBBean> mCurrentRadio;
    private final Object mLock;
    private final ObservableInt mPlaybackState;
    private final MutableLiveData<List<RadioManager.ProgramInfo>> mProgramList;
    private ProgramType mProgramType;
    public ObservableInt mRadioType;
    private final AtomicReference<IRadioAppService> mService;
    private final ServiceConnection mServiceConnection;
    int powerFreq;
    private Runnable programListUpdateRunnable;
    private final List<IRadioProgramListener> programListeners;
    Comparator<RadioDBBean> radioBeanSortComparator;
    public DiffObservableList<RadioEpgDBBean> radioCurrentEpgList;
    private Runnable radioMuteToastRunnable;
    public ObservableArrayList<RadioEpgDBBean> radioSubscribeEpgList;
    public AlarmView trafficAlarmView;
    public volatile long tuningDABSID;
    public volatile long tuningFreq;
    public TreeMap<Long, AbstractMap.SimpleEntry<RadioDBBean, Integer>> validCacheMap;
    public static int FM_FREQ_MAX = SPUtils.getInstance().getInt("radio_fm_max", BYDAutoRadioDevice.FM_UPPER_LIMIT);
    public static int FM_FREQ_MIN = SPUtils.getInstance().getInt("radio_fm_min", BYDAutoRadioDevice.FM_LOWER_LIMIT);
    public static int FM_FREQ_STEP = SPUtils.getInstance().getInt("radio_fm_step", 100);
    public static int AM_FREQ_MAX = SPUtils.getInstance().getInt("radio_am_max", 1620);
    public static int AM_FREQ_MIN = SPUtils.getInstance().getInt("radio_am_min", 522);
    public static int AM_FREQ_STEP = SPUtils.getInstance().getInt("radio_am_step", 9);

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RadioType {
    }

    private interface ServiceOperation<V> {
        V execute(IRadioAppService iRadioAppService) throws RemoteException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface ServiceVoidOperation {
        void execute(IRadioAppService iRadioAppService) throws RemoteException;
    }

    public boolean getBgScanEnable() {
        return true;
    }

    public RegionConfig getRegionConfig() {
        return null;
    }

    public boolean isProgramListSupported() {
        return false;
    }

    public void setMuted(boolean z) {
    }

    public void setSkipMode(SkipMode skipMode) {
    }

    public void skip(boolean z) {
    }

    public void step(boolean z, RadioTunerExt.TuneCallback tuneCallback) {
    }

    public void switchBand(ProgramSelector.ProgramType programType) {
    }

    public static void resetFMAMConfig(int i, int i2, int i3, int i4, int i5, int i6) {
        LogUtil.e(TAG, "resetFMAMConfig enter with fmMin = " + i + ", fmMax = " + i2 + ", fmStep = " + i3 + ", amMin = " + i4 + ", amMax = " + i5 + ", amStep = " + i6);
        FM_FREQ_MAX = i2;
        FM_FREQ_MIN = i;
        FM_FREQ_STEP = i3;
        AM_FREQ_MAX = i5;
        AM_FREQ_MIN = i4;
        AM_FREQ_STEP = i6;
        SPUtils.getInstance().put("radio_fm_max", i2);
        SPUtils.getInstance().put("radio_fm_min", i);
        SPUtils.getInstance().put("radio_fm_step", i3);
        SPUtils.getInstance().put("radio_am_max", i5);
        SPUtils.getInstance().put("radio_am_min", i4);
        SPUtils.getInstance().put("radio_am_step", i6);
        getInstance().initPogramListUpdateRunnable();
        if (getInstance().currentFMRadioDBBean != null) {
            getInstance().currentFMRadioDBBean.setRadioFrequency(FM_FREQ_MIN);
            RadioDB.insert(getInstance().currentFMRadioDBBean);
        }
        if (getInstance().currentAmRadioDBBean != null) {
            getInstance().currentAmRadioDBBean.setRadioFrequency(AM_FREQ_MIN);
            RadioDB.insert(getInstance().currentAmRadioDBBean);
            LogUtil.d(TAG, "reset am config finish");
        }
        EventBus.getDefault().post(new RadioConfigChangeBean());
    }

    public static RadioServiceWrapper getInstance() {
        if (mInstance == null) {
            synchronized (RadioServiceWrapper.class) {
                if (mInstance == null) {
                    mInstance = new RadioServiceWrapper();
                }
            }
        }
        return mInstance;
    }

    public ObservableBoolean getFMPlaying() {
        return this.fmPlaying;
    }

    public ObservableBoolean getAMPlaying() {
        return this.amPlaying;
    }

    public ObservableBoolean getDABPlaying() {
        return this.dabPlaying;
    }

    public boolean getEPGEnable() {
        return this.isEPGEnable;
    }

    public boolean getFMEnable() {
        return this.isFMEnable;
    }

    public boolean getAMEnable() {
        return this.isAMEnable;
    }

    public boolean getDABEnable() {
        return this.isDABEnable;
    }

    public void setFMEnable(boolean z) {
        this.isFMEnable = z;
        SPUtils.getInstance().put("fm_enable", z);
    }

    public void setAMEnable(boolean z) {
        this.isAMEnable = z;
        SPUtils.getInstance().put("am_enable", z);
    }

    public void setDABEnable(boolean z) {
        this.isDABEnable = z;
        SPUtils.getInstance().put("dab_enable", z);
    }

    public void initPogramListUpdateRunnable() {
        if (getBgScanEnable()) {
            if (this.programListUpdateRunnable != null) {
                LogUtil.e(TAG, "programListUpdateRunnable not null, do not need init again.");
                return;
            } else {
                this.programListUpdateRunnable = new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$-SKvtxwUaCDV2WWVf6vhw8Q3Yqw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$initPogramListUpdateRunnable$0$RadioServiceWrapper();
                    }
                };
                return;
            }
        }
        this.programListUpdateRunnable = null;
    }

    public /* synthetic */ void lambda$initPogramListUpdateRunnable$0$RadioServiceWrapper() {
        if (getBgScanEnable()) {
            updateAllSourceProgramList();
            this.handler.postDelayed(this.programListUpdateRunnable, 9000L);
        }
    }

    public String setRadioPlayState(boolean z) {
        if (this.isSeeking) {
            cancelSeek();
            if (z) {
                return "Seeking, canceled.";
            }
        }
        if (z) {
            if (OnLineMusic.musicEngine == 9 && this.mCurrentRadio.getValue().getDAB_SID() <= 0) {
                return MediaControl.REASON_UNSUPPORTED_OPERATION;
            }
            if (!AudioFocusManager.getInstance().isHoldAudioFocus() && 1 != AudioFocusManager.getInstance().requestAudioFocus()) {
                if (DeviceInfoUtils.isInCall()) {
                    return MediaControl.REASON_UNKNOWN;
                }
                ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
                return MediaControl.REASON_UNKNOWN;
            }
            if (this.mProgramType == ProgramType.FM && this.isFMEnable) {
                OnLineMusic.setMusicEngine(7);
                this.fmPlaying.set(true);
                this.amPlaying.set(false);
                this.dabPlaying.set(false);
            } else if (this.mProgramType == ProgramType.AM && this.isAMEnable) {
                OnLineMusic.setMusicEngine(8);
                this.amPlaying.set(true);
                this.fmPlaying.set(false);
                this.dabPlaying.set(false);
            } else if (this.mProgramType == ProgramType.DAB && this.isDABEnable) {
                OnLineMusic.setMusicEngine(9);
                this.dabPlaying.set(true);
                this.fmPlaying.set(false);
                this.amPlaying.set(false);
            }
            if (OnLineMusic.VENDOR_CODE == 2) {
                if (this.isRadioHalReady) {
                    AudioFocusManager.getInstance().audioManager.setParameters("handle_fm=1048578");
                }
            } else {
                Handler handler = this.handler;
                Runnable runnable = new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$F_IqxpZXpEZgPrh77UdIbFDYluM
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setRadioPlayState$1$RadioServiceWrapper();
                    }
                };
                long j = 250;
                if (OnLineMusic.getBuildType() == 1 && this.lastRadioType == 9) {
                    j = 450;
                }
                handler.postDelayed(runnable, j);
            }
            SystemProperties.set("sbr.audio.start", "1");
            LogUtil.e(TAG, "setRadioPlayState: after set sbr.audio.start 1 ");
        } else {
            this.fmPlaying.set(false);
            this.amPlaying.set(false);
            this.dabPlaying.set(false);
            if (OnLineMusic.VENDOR_CODE == 2) {
                AudioFocusManager.getInstance().audioManager.setParameters("handle_fm=1");
            } else {
                try {
                    BYDAutoAudioDevice.getInstance(Utils.getApp()).setLoudness(3);
                    LogUtil.d(TAG, "setRadioPlayState: setLoudness 3");
                } catch (Throwable th) {
                    LogUtil.e(TAG, "setRadioPlayState: setLoudness 3 error");
                    th.printStackTrace();
                }
            }
            SystemProperties.set("sbr.audio.start", AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
            LogUtil.e(TAG, "setRadioPlayState: after set sbr.audio.start 0 ");
        }
        this.isPlaying.set(z);
        LogUtil.e(TAG, "setRadioPlayState: isPlaying = " + z);
        if (OnLineMusic.musicEngine != 7 && OnLineMusic.musicEngine != 8 && OnLineMusic.musicEngine != 9) {
            return "";
        }
        CurrentMediaInfoUtils.getInstance().setPlayingState(z);
        return "";
    }

    public /* synthetic */ void lambda$setRadioPlayState$1$RadioServiceWrapper() {
        if (this.isPlaying.get()) {
            try {
                int i = OnLineMusic.musicEngine;
                if (i == 7 || i == 8) {
                    BYDAutoAudioDevice.getInstance(Utils.getApp()).setLoudness(1);
                    LogUtil.d(TAG, "setRadioPlayState: setLoudness 1");
                }
            } catch (Throwable th) {
                LogUtil.e(TAG, "setRadioPlayState: setLoudness 1 error");
                th.printStackTrace();
            }
        }
    }

    public void setProgramType(ProgramType programType) {
        this.mProgramType = programType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TuneCallbackAdapter extends ITuneCallback.Stub {
        private final RadioTunerExt.TuneCallback mCallback;

        private TuneCallbackAdapter(RadioTunerExt.TuneCallback tuneCallback) {
            this.mCallback = tuneCallback;
        }

        @Override // com.android.car.radio.service.ITuneCallback
        public void onFinished(boolean z) {
            LogUtil.d(RadioServiceWrapper.TAG, "onFinished: is succeeded = " + z);
        }
    }

    public String tuneToNext() {
        int radioFrequency;
        if (this.isSeeking) {
            cancelSeek();
            return "Seeking, canceled.";
        }
        if (!getBgScanEnable()) {
            return seek(true);
        }
        synchronized (RadioServiceWrapper.class) {
            if (this.mProgramType == ProgramType.DAB) {
                LogUtil.d(TAG, "tuneToNext dab enter");
                long dabSID = getDabSID(this.mCurrentProgram.get());
                if (!this.dabValidList.isEmpty()) {
                    int iIndexOf = -1;
                    Iterator<RadioDBBean> it = this.dabValidList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        RadioDBBean next = it.next();
                        if (next.getType() == 4 && next.getDAB_SID() == dabSID) {
                            iIndexOf = this.dabValidList.indexOf(next);
                            break;
                        }
                    }
                    LogUtil.d(TAG, "tuneToNext dab check index = " + iIndexOf);
                    if (iIndexOf < 0 || iIndexOf == this.dabValidList.size() - 1) {
                        for (RadioDBBean radioDBBean : this.dabValidList) {
                            if (radioDBBean.getType() == 4) {
                                String strTune = tune(generateDABSelector(radioDBBean));
                                setRadioPlayState(true);
                                return strTune;
                            }
                        }
                    } else {
                        do {
                            iIndexOf++;
                            if (iIndexOf < this.dabValidList.size()) {
                            }
                        } while (this.dabValidList.get(iIndexOf).getType() != 4);
                        String strTune2 = tune(generateDABSelector(this.dabValidList.get(iIndexOf)));
                        setRadioPlayState(true);
                        return strTune2;
                    }
                    return "tuneToNext dab failed with final index = " + iIndexOf;
                }
                LogUtil.d(TAG, "tuneToNext dab failed");
                return MediaControl.REASON_UNSUPPORTED_FREQUENCY;
            }
            if (this.mProgramType == ProgramType.FM) {
                if (this.fmValidList.size() == 0) {
                    ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_empty));
                    return "fm valid list is empty";
                }
                radioFrequency = this.currentFMRadioDBBean.getRadioFrequency();
                if (this.fmValidList.size() == 1) {
                    if (this.fmValidList.get(0).getRadioFrequency() == getFMAMFrequency(this.mCurrentProgram.get())) {
                        ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_only_one));
                        return "fm valid list size just 1";
                    }
                    radioFrequency = this.fmValidList.get(0).getRadioFrequency();
                } else {
                    int i = 0;
                    while (true) {
                        if (i >= this.fmValidList.size()) {
                            break;
                        }
                        if (this.fmValidList.get(i).getRadioFrequency() > radioFrequency) {
                            radioFrequency = this.fmValidList.get(i).getRadioFrequency();
                            break;
                        }
                        i++;
                    }
                    if (radioFrequency == this.currentFMRadioDBBean.getRadioFrequency()) {
                        radioFrequency = this.fmValidList.get(0).getRadioFrequency();
                    }
                }
            } else {
                if (this.amValidList.size() == 0) {
                    ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_empty));
                    return "am valid list is empty";
                }
                radioFrequency = this.currentAmRadioDBBean.getRadioFrequency();
                if (this.amValidList.size() == 1) {
                    if (this.amValidList.get(0).getRadioFrequency() == getFMAMFrequency(this.mCurrentProgram.get())) {
                        ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_only_one));
                        return "am valid list size just 1";
                    }
                    radioFrequency = this.amValidList.get(0).getRadioFrequency();
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= this.amValidList.size()) {
                            break;
                        }
                        if (this.amValidList.get(i2).getRadioFrequency() > radioFrequency) {
                            radioFrequency = this.amValidList.get(i2).getRadioFrequency();
                            break;
                        }
                        i2++;
                    }
                    if (radioFrequency == this.currentAmRadioDBBean.getRadioFrequency()) {
                        radioFrequency = this.amValidList.get(0).getRadioFrequency();
                    }
                }
            }
            LogUtil.d(TAG, "tuneToNext: target = " + radioFrequency);
            String strTune3 = tune(radioFrequency);
            setRadioPlayState(true);
            return strTune3;
        }
    }

    public String tuneToPre() {
        int radioFrequency;
        int size;
        if (this.isSeeking) {
            cancelSeek();
            return "Seeking, canceled.";
        }
        if (!getBgScanEnable()) {
            return seek(false);
        }
        synchronized (RadioServiceWrapper.class) {
            if (this.mProgramType == ProgramType.DAB) {
                LogUtil.d(TAG, "tuneToPre dab enter");
                long dabSID = getDabSID(this.mCurrentProgram.get());
                if (!this.dabValidList.isEmpty()) {
                    Iterator<RadioDBBean> it = this.dabValidList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            size = -1;
                            break;
                        }
                        RadioDBBean next = it.next();
                        if (next.getType() == 4 && next.getDAB_SID() == dabSID) {
                            size = this.dabValidList.indexOf(next);
                            break;
                        }
                    }
                    LogUtil.d(TAG, "tuneToPre dab check index = " + size);
                    if (size < 0) {
                        for (RadioDBBean radioDBBean : this.dabValidList) {
                            if (radioDBBean.getType() == 4) {
                                String strTune = tune(generateDABSelector(radioDBBean));
                                setRadioPlayState(true);
                                return strTune;
                            }
                        }
                    } else {
                        if (size != 0 && (size != 1 || this.dabValidList.get(0).getType() != 5)) {
                            do {
                                size--;
                                if (size >= 0) {
                                }
                            } while (this.dabValidList.get(size).getType() != 4);
                            String strTune2 = tune(generateDABSelector(this.dabValidList.get(size)));
                            setRadioPlayState(true);
                            return strTune2;
                        }
                        size = this.dabValidList.size() - 1;
                        while (size >= 0) {
                            if (this.dabValidList.get(size).getType() == 4) {
                                String strTune3 = tune(generateDABSelector(this.dabValidList.get(size)));
                                setRadioPlayState(true);
                                return strTune3;
                            }
                            size--;
                        }
                    }
                    return "tuneToPre dab failed with final index = " + size;
                }
                LogUtil.d(TAG, "tuneToPre dab failed");
                return MediaControl.REASON_UNSUPPORTED_FREQUENCY;
            }
            if (this.mProgramType == ProgramType.FM) {
                if (this.fmValidList.size() == 0) {
                    ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_empty));
                    return "fm valid list is empty";
                }
                int radioFrequency2 = this.currentFMRadioDBBean.getRadioFrequency();
                if (this.fmValidList.size() == 1) {
                    if (this.fmValidList.get(0).getRadioFrequency() == getFMAMFrequency(this.mCurrentProgram.get())) {
                        ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_only_one));
                        return "fm valid list size just 1";
                    }
                    radioFrequency = this.fmValidList.get(0).getRadioFrequency();
                } else {
                    int size2 = this.fmValidList.size() - 1;
                    while (true) {
                        if (size2 < 0) {
                            break;
                        }
                        if (this.fmValidList.get(size2).getRadioFrequency() < radioFrequency2) {
                            radioFrequency2 = this.fmValidList.get(size2).getRadioFrequency();
                            break;
                        }
                        size2--;
                    }
                    radioFrequency = radioFrequency2 == this.currentFMRadioDBBean.getRadioFrequency() ? this.fmValidList.get(this.fmValidList.size() - 1).getRadioFrequency() : radioFrequency2;
                }
            } else {
                if (this.amValidList.size() == 0) {
                    ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_empty));
                    return "am valid list is empty";
                }
                int radioFrequency3 = this.currentAmRadioDBBean.getRadioFrequency();
                if (this.amValidList.size() == 1) {
                    if (this.amValidList.get(0).getRadioFrequency() == getFMAMFrequency(this.mCurrentProgram.get())) {
                        ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_only_one));
                        return "am valid list size just 1";
                    }
                    radioFrequency = this.amValidList.get(0).getRadioFrequency();
                } else {
                    int size3 = this.amValidList.size() - 1;
                    while (true) {
                        if (size3 < 0) {
                            radioFrequency = radioFrequency3;
                            break;
                        }
                        if (this.amValidList.get(size3).getRadioFrequency() < radioFrequency3) {
                            radioFrequency = this.amValidList.get(size3).getRadioFrequency();
                            break;
                        }
                        size3--;
                    }
                    if (radioFrequency == this.currentAmRadioDBBean.getRadioFrequency()) {
                        radioFrequency = this.amValidList.get(this.amValidList.size() - 1).getRadioFrequency();
                    }
                }
            }
            LogUtil.d(TAG, "tuneToPre: target = " + radioFrequency);
            String strTune4 = tune(radioFrequency);
            setRadioPlayState(true);
            return strTune4;
        }
    }

    public String fineTune(boolean z) {
        int radioFrequency;
        if (this.isSeeking) {
            cancelSeek();
            return "Seeking, canceled.";
        }
        if (z) {
            if (this.mProgramType == ProgramType.FM) {
                radioFrequency = this.mCurrentRadio.getValue().getRadioFrequency() + FM_FREQ_STEP;
                if (radioFrequency > FM_FREQ_MAX) {
                    radioFrequency = FM_FREQ_MIN;
                }
            } else {
                radioFrequency = this.mCurrentRadio.getValue().getRadioFrequency() + AM_FREQ_STEP;
                if (radioFrequency > AM_FREQ_MAX) {
                    radioFrequency = AM_FREQ_MIN;
                }
            }
        } else if (this.mProgramType == ProgramType.FM) {
            radioFrequency = this.mCurrentRadio.getValue().getRadioFrequency() - FM_FREQ_STEP;
            if (radioFrequency < FM_FREQ_MIN) {
                radioFrequency = FM_FREQ_MAX;
            }
        } else {
            radioFrequency = this.mCurrentRadio.getValue().getRadioFrequency() - AM_FREQ_STEP;
            if (radioFrequency < AM_FREQ_MIN) {
                radioFrequency = AM_FREQ_MAX;
            }
        }
        LogUtil.d(TAG, "fineTune: target = " + radioFrequency);
        String strTune = tune(radioFrequency);
        setRadioPlayState(true);
        return strTune;
    }

    private RadioServiceWrapper() {
        this.handler = new Handler(Looper.getMainLooper());
        this.AM_SYNC_LOWFREQ = 1;
        this.AM_SYNC_HIGHFREQ = 2;
        this.isEPGEnable = false;
        this.isDABEnable = SPUtils.getInstance().getBoolean("dab_enable");
        this.isFMEnable = SPUtils.getInstance().getBoolean("fm_enable", true);
        this.isAMEnable = SPUtils.getInstance().getBoolean("am_enable", true);
        this.isGetFiledStrength = -1L;
        this.isGetDadBER = -1L;
        this.ID_ONE_STRENGTH = -1;
        this.ID_TWO_STRENGTH = -1;
        this.isTestMode = false;
        this.DAB_BER = -1;
        this.tuningDABSID = -1L;
        this.tuningFreq = -1L;
        this.isRadioHalReady = false;
        this.programListeners = new ArrayList();
        this.trafficAlarmView = null;
        this.isPlaying = new ObservableBoolean("1".equals(SystemProperties.get("sbr.audio.start")));
        this.fmPlaying = new ObservableBoolean(false);
        this.amPlaying = new ObservableBoolean(false);
        this.dabPlaying = new ObservableBoolean(false);
        this.mServiceConnection = new ServiceConnection() { // from class: com.byd.music.service.RadioServiceWrapper.3
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LogUtil.e(RadioServiceWrapper.TAG, "onServiceConnected: enter");
                RadioServiceWrapper.this.onServiceConnected(iBinder, (IRadioAppService) Objects.requireNonNull(IRadioAppService.Stub.asInterface(iBinder)));
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                LogUtil.e(RadioServiceWrapper.TAG, "onServiceDisconnected: enter");
                RadioServiceWrapper.this.onServiceFailure();
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName componentName) {
                LogUtil.e(RadioServiceWrapper.TAG, "onBindingDied: enter");
                RadioServiceWrapper.this.onServiceFailure();
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName componentName) {
                RadioServiceWrapper.this.onNullBinding();
            }
        };
        this.mCallback = new IRadioAppCallback.Stub() { // from class: com.byd.music.service.RadioServiceWrapper.4
            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onHardwareError() {
                LogUtil.d(RadioServiceWrapper.TAG, "onHardwareError: enter");
                RadioServiceWrapper.this.onServiceFailure();
            }

            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onCurrentProgramChanged(RadioManager.ProgramInfo programInfo) {
                LogUtil.d(RadioServiceWrapper.TAG, "onCurrentProgramChanged: enter with info = " + programInfo.toString());
                if (RadioServiceWrapper.this.mConnectionState.get() == 2 && RadioServiceWrapper.this.isRadioHalReady) {
                    if (RadioServiceWrapper.isDabProgram(programInfo) && RadioServiceWrapper.getDabSID(programInfo) == 0 && RadioServiceWrapper.this.tuningDABSID != 0) {
                        return;
                    }
                    if (programInfo.isMuted() || programInfo.isTuned() || RadioServiceWrapper.this.dabValidList.size() == 0) {
                        if (RadioServiceWrapper.this.trafficAlarmView == null || !RadioServiceWrapper.this.trafficAlarmView.getShowing()) {
                            RadioServiceWrapper.this.mCurrentProgram.set(programInfo);
                            if (RadioServiceWrapper.this.mProgramType != ProgramType.FM || (RadioServiceWrapper.isFmProgram(programInfo) && RadioServiceWrapper.this.isFMEnable)) {
                                if (RadioServiceWrapper.this.mProgramType != ProgramType.AM || (RadioServiceWrapper.isAmProgram(programInfo) && RadioServiceWrapper.this.isAMEnable)) {
                                    if (RadioServiceWrapper.this.mProgramType == ProgramType.DAB && (!RadioServiceWrapper.isDabProgram(programInfo) || !RadioServiceWrapper.this.isDABEnable)) {
                                        LogUtil.d(RadioServiceWrapper.TAG, "onCurrentProgramChanged: error return with DAB");
                                        return;
                                    } else {
                                        RadioServiceWrapper.this.updateCurrentRadio(programInfo);
                                        return;
                                    }
                                }
                                LogUtil.d(RadioServiceWrapper.TAG, "onCurrentProgramChanged: error return with AM");
                                return;
                            }
                            LogUtil.d(RadioServiceWrapper.TAG, "onCurrentProgramChanged: error return with FM");
                        }
                    }
                }
            }

            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onPlaybackStateChanged(int i) {
                LogUtil.d(RadioServiceWrapper.TAG, "onPlaybackStateChanged: enter");
                RadioServiceWrapper.this.mPlaybackState.set(i);
            }

            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onProgramListChanged(List<RadioManager.ProgramInfo> list) {
                LogUtil.d(RadioServiceWrapper.TAG, "onProgramListChanged: enter");
                RadioServiceWrapper.this.mProgramList.postValue(list);
            }
        };
        this.powerFreq = 0;
        LogUtil.e(TAG, "zzl FMRadioServiceWrapper: init enter");
        this.mService = new AtomicReference<>();
        this.mControlService = new AtomicReference<>();
        this.mLock = new Object();
        this.mConnectionState = new ObservableInt();
        this.mRadioType = new ObservableInt();
        this.mPlaybackState = new ObservableInt();
        this.mCurrentProgram = new ObservableField<>();
        this.mCurrentRadio = new MutableLiveData<>();
        ObservableArrayList<RadioDBBean> observableArrayList = new ObservableArrayList<>();
        this.fmValidList = observableArrayList;
        observableArrayList.addAll(RadioDB.queryScanMemoryFM());
        ObservableArrayList<RadioDBBean> observableArrayList2 = new ObservableArrayList<>();
        this.amValidList = observableArrayList2;
        observableArrayList2.addAll(RadioDB.queryScanMemoryAM());
        ObservableArrayList<RadioDBBean> observableArrayList3 = new ObservableArrayList<>();
        this.dabValidList = observableArrayList3;
        observableArrayList3.addAll(RadioDB.queryScanMemoryDAB());
        initCurrentRadio();
        if (this.isPlaying.get()) {
            int i = OnLineMusic.musicEngine;
            if (i == 7) {
                this.fmPlaying.set(true);
            } else if (i == 8) {
                this.amPlaying.set(true);
            } else if (i == 9) {
                this.dabPlaying.set(true);
            }
        }
        this.mProgramList = new MutableLiveData<>();
        this.mConnectionState.set(1);
        this.mPlaybackState.set(0);
        this.fmCollectList = new ObservableArrayList<>();
        this.amCollectList = new ObservableArrayList<>();
        this.dabCollectList = new ObservableArrayList<>();
        for (RadioDBBean radioDBBean : RadioDB.queryAll()) {
            int type = radioDBBean.getType();
            if (type == 1) {
                this.amCollectList.add(radioDBBean);
            } else if (type == 2 || type == 3) {
                this.fmCollectList.add(radioDBBean);
            } else if (type == 4) {
                this.dabCollectList.add(radioDBBean);
            }
        }
        ObservableArrayList<RadioEpgDBBean> observableArrayList4 = new ObservableArrayList<>();
        this.radioSubscribeEpgList = observableArrayList4;
        observableArrayList4.addAll(RadioEpgDB.queryAll());
        this.radioSubscribeEpgList.sort(new Comparator() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$R1XWQJH4SSTk2IToDYhuibBwePg
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return RadioServiceWrapper.lambda$new$2((RadioEpgDBBean) obj, (RadioEpgDBBean) obj2);
            }
        });
        initPogramListUpdateRunnable();
        this.epgRunnable = getEPGEnable() ? null : new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$gi2DBfSJeLXcP9p0JMESuRR1FmQ
            @Override // java.lang.Runnable
            public final void run() {
                RadioServiceWrapper.lambda$new$3();
            }
        };
        this.mProgramList.observeForever(new Observer() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$aG4qLJ8iOx547PrQsEC-QVRqSVw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LogUtil.d(RadioServiceWrapper.TAG, "mProgramList updated!");
            }
        });
        checkAndStartProgramListUpdateRunnable();
        int i2 = OnLineMusic.musicEngine;
        if (i2 == 8) {
            setProgramType(ProgramType.AM);
            if (!isAmBean(this.currentAmRadioDBBean)) {
                this.currentAmRadioDBBean.reset();
                this.currentAmRadioDBBean.setDAB_SID(-1L);
                this.currentAmRadioDBBean.setRadioFrequency(AM_FREQ_MIN);
            }
            this.mCurrentRadio.setValue(this.currentAmRadioDBBean);
            LogUtil.d(TAG, "init: set AM, freq = " + this.mCurrentRadio.getValue().getRadioFrequency());
        } else {
            if (i2 == 9) {
                setProgramType(ProgramType.DAB);
                if (!isDABBean(this.currentDABRadioDBBean)) {
                    this.currentDABRadioDBBean.reset();
                    this.currentDABRadioDBBean.setRadioFrequency(174928);
                    this.currentDABRadioDBBean.setDAB_SID(0L);
                }
                this.mCurrentRadio.setValue(this.currentDABRadioDBBean);
                setLastRadioType();
                mInstance = this;
                EventBus.getDefault().register(this);
                LogUtil.e(TAG, "init finish with fmMin = " + FM_FREQ_MIN + ", fmMax = " + FM_FREQ_MAX + ", fmStep = " + FM_FREQ_STEP + ", amMin = " + AM_FREQ_MIN + ", amMax = " + AM_FREQ_MAX + ", amStep = " + AM_FREQ_STEP);
                restartEpgCountDown();
            }
            setProgramType(ProgramType.FM);
            if (!isFmBean(this.currentFMRadioDBBean)) {
                this.currentFMRadioDBBean.reset();
                this.currentFMRadioDBBean.setDAB_SID(-1L);
                this.currentFMRadioDBBean.setRadioFrequency(FM_FREQ_MIN);
            }
            this.mCurrentRadio.setValue(this.currentFMRadioDBBean);
            LogUtil.d(TAG, "init: set FM, freq = " + this.mCurrentRadio.getValue().getRadioFrequency());
        }
        setLastRadioType();
        mInstance = this;
        EventBus.getDefault().register(this);
        LogUtil.e(TAG, "init finish with fmMin = " + FM_FREQ_MIN + ", fmMax = " + FM_FREQ_MAX + ", fmStep = " + FM_FREQ_STEP + ", amMin = " + AM_FREQ_MIN + ", amMax = " + AM_FREQ_MAX + ", amStep = " + AM_FREQ_STEP);
        restartEpgCountDown();
    }

    static /* synthetic */ int lambda$new$2(RadioEpgDBBean radioEpgDBBean, RadioEpgDBBean radioEpgDBBean2) {
        return (int) (radioEpgDBBean.getEpgStartTime() - radioEpgDBBean2.getEpgStartTime());
    }

    static /* synthetic */ void lambda$new$3() {
        AlarmView alarmView = new AlarmView(Utils.getApp());
        alarmView.initEPG();
        alarmView.showGlobalWindow();
    }

    private void initCurrentRadio() {
        LogUtil.d(TAG, "initCurrentRadio");
        if (this.currentFMRadioDBBean == null) {
            this.currentFMRadioDBBean = RadioDB.queryUniqueRadioByType(101);
        }
        if (this.currentAmRadioDBBean == null) {
            this.currentAmRadioDBBean = RadioDB.queryUniqueRadioByType(102);
        }
        if (this.currentDABRadioDBBean == null) {
            this.currentDABRadioDBBean = RadioDB.queryUniqueRadioByType(103);
        }
        if (this.currentFMRadioDBBean == null) {
            RadioDBBean radioDBBean = new RadioDBBean();
            this.currentFMRadioDBBean = radioDBBean;
            radioDBBean.setType(101);
            this.currentFMRadioDBBean.setRadioFrequency(FM_FREQ_MIN);
            RadioDB.insert(this.currentFMRadioDBBean);
        }
        if (this.currentAmRadioDBBean == null) {
            RadioDBBean radioDBBean2 = new RadioDBBean();
            this.currentAmRadioDBBean = radioDBBean2;
            radioDBBean2.setType(102);
            this.currentAmRadioDBBean.setRadioFrequency(AM_FREQ_MIN);
            RadioDB.insert(this.currentAmRadioDBBean);
        }
        if (this.currentDABRadioDBBean == null) {
            RadioDBBean radioDBBean3 = new RadioDBBean();
            this.currentDABRadioDBBean = radioDBBean3;
            radioDBBean3.setType(103);
            this.currentDABRadioDBBean.setRadioFrequency(174928);
            this.currentDABRadioDBBean.setDAB_SID(0L);
            RadioDB.insert(this.currentDABRadioDBBean);
        }
    }

    public void updateProgramList(List<RadioManager.ProgramInfo> list) {
        resolveProgramList(list);
    }

    private void resolveProgramList(final List<RadioManager.ProgramInfo> list) {
        LogUtil.e(TAG, "resolveProgramList, size = " + list.size());
        AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$dcVrmSjMA52MpEupni7ji72CwKo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$resolveProgramList$6$RadioServiceWrapper(list);
            }
        });
    }

    public /* synthetic */ void lambda$resolveProgramList$6$RadioServiceWrapper(List list) {
        if (this.validCacheMap == null) {
            this.validCacheMap = new TreeMap<>();
        }
        if (CollectionUtils.isEmpty(list)) {
            this.validCacheMap.clear();
            this.fmValidList.clear();
            this.amValidList.clear();
            this.dabValidList.clear();
            return;
        }
        HashSet hashSet = new HashSet(this.validCacheMap.keySet());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RadioManager.ProgramInfo programInfo = (RadioManager.ProgramInfo) it.next();
            if (isDabProgram(programInfo)) {
                if (!TextUtils.isEmpty(getDabServiceName(programInfo, false))) {
                    long dabSID = getDabSID(programInfo);
                    if (this.validCacheMap.get(Long.valueOf(dabSID)) == null) {
                        this.validCacheMap.put(Long.valueOf(dabSID), new AbstractMap.SimpleEntry<>(parseRadioProgram(programInfo), 1));
                    } else {
                        AbstractMap.SimpleEntry<RadioDBBean, Integer> simpleEntry = this.validCacheMap.get(Long.valueOf(dabSID));
                        simpleEntry.getKey();
                        shouldUpdateDes(programInfo, simpleEntry, 1, true);
                        hashSet.remove(Long.valueOf(dabSID));
                    }
                }
            } else {
                int fMAMFrequency = getFMAMFrequency(programInfo);
                if (fMAMFrequency == 0) {
                    fMAMFrequency = getRDSFreq(programInfo);
                }
                long j = fMAMFrequency;
                if (this.validCacheMap.get(Long.valueOf(j)) == null) {
                    this.validCacheMap.put(Long.valueOf(j), new AbstractMap.SimpleEntry<>(parseRadioProgram(programInfo), 1));
                } else {
                    AbstractMap.SimpleEntry<RadioDBBean, Integer> simpleEntry2 = this.validCacheMap.get(Long.valueOf(j));
                    simpleEntry2.getKey();
                    shouldUpdateDes(programInfo, simpleEntry2, 1, true);
                    hashSet.remove(Long.valueOf(j));
                }
            }
        }
        hashSet.forEach(new Consumer() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$X_cRT8GR-kFeOGbX_myT2Zhjo9A
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$resolveProgramList$5$RadioServiceWrapper((Long) obj);
            }
        });
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<AbstractMap.SimpleEntry<RadioDBBean, Integer>> it2 = this.validCacheMap.values().iterator();
        while (it2.hasNext()) {
            RadioDBBean key = it2.next().getKey();
            int type = key.getType();
            if (type == 1) {
                arrayList2.add(key);
            } else if (type == 2 || type == 3) {
                arrayList.add(key);
            } else if (type == 4) {
                arrayList3.add(key);
            }
        }
        sortList(arrayList);
        sortList(arrayList2);
        sortList(arrayList3);
        LogUtil.d(TAG, "size of fmRadioDBBeans = " + arrayList.size());
        LogUtil.d(TAG, "size of amRadioDBBeans = " + arrayList2.size());
        LogUtil.d(TAG, "size of dabRadioDBBeans = " + arrayList3.size());
        mergeBeanList(arrayList, this.fmValidList, this.fmCollectList);
        mergeBeanList(arrayList2, this.amValidList, this.amCollectList);
        mergeBeanList(arrayList3, this.dabValidList, this.dabCollectList);
        LogUtil.d(TAG, "size of fmValidList = " + this.fmValidList.size());
        LogUtil.d(TAG, "size of amValidList = " + this.amValidList.size());
        LogUtil.d(TAG, "size of dabValidList = " + this.dabValidList.size());
        if (adjRDSProgramInfo()) {
            MutableLiveData<RadioDBBean> mutableLiveData = this.mCurrentRadio;
            mutableLiveData.setValue(mutableLiveData.getValue());
            if (OnLineMusic.musicEngine == 7) {
                CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(this.mCurrentRadio.getValue()));
            }
        }
    }

    public /* synthetic */ void lambda$resolveProgramList$5$RadioServiceWrapper(Long l) {
        this.validCacheMap.remove(l);
    }

    private void mergeBeanList(List<RadioDBBean> list, ObservableList<RadioDBBean> observableList, ObservableList<RadioDBBean> observableList2) {
        if (!CollectionUtils.isEmpty(observableList2) && observableList2.get(0).isFMRadio()) {
            for (int i = 0; i < observableList2.size(); i++) {
                RadioDBBean radioDBBean = observableList2.get(i);
                if (list.indexOf(radioDBBean) < 0) {
                    radioDBBean.reset();
                    observableList2.set(i, radioDBBean);
                    RadioDB.update(radioDBBean);
                }
            }
        }
        int i2 = 0;
        while (i2 < list.size()) {
            RadioDBBean radioDBBean2 = list.get(i2);
            int iIndexOf = observableList2.indexOf(radioDBBean2);
            if (iIndexOf >= 0) {
                RadioDBBean radioDBBean3 = observableList2.get(iIndexOf);
                if (shouldUpdateDes(radioDBBean2, radioDBBean3, true)) {
                    observableList2.set(iIndexOf, radioDBBean3);
                    RadioDB.update(radioDBBean3);
                }
            }
            RadioDBBean radioDBBean4 = observableList.size() > i2 ? observableList.get(i2) : null;
            LogUtil.d(TAG, "mergeBeanList, i = " + i2 + ", overlayBean = " + radioDBBean2 + ", baseBean = " + radioDBBean4);
            if (radioDBBean4 == null) {
                observableList.add(RadioDBBean.deepCopy(radioDBBean2));
            } else if (radioDBBean2.equals(radioDBBean4)) {
                if (shouldUpdateDes(radioDBBean2, radioDBBean4, false)) {
                    radioDBBean4.updateFrom(radioDBBean2);
                    observableList.set(i2, radioDBBean4);
                }
            } else {
                Comparator<RadioDBBean> comparator = this.radioBeanSortComparator;
                if (comparator != null) {
                    if (comparator.compare(radioDBBean2, radioDBBean4) > 0) {
                        observableList.remove(radioDBBean4);
                        i2--;
                    } else {
                        observableList.add(i2, RadioDBBean.deepCopy(radioDBBean2));
                    }
                }
            }
            i2++;
        }
        if (observableList.size() > list.size()) {
            for (int size = observableList.size() - 1; size >= list.size(); size--) {
                observableList.remove(size);
            }
        }
    }

    private boolean shouldUpdateDes(RadioDBBean radioDBBean, RadioDBBean radioDBBean2, boolean z) {
        boolean z2;
        int type = radioDBBean.getType();
        if (type != 3) {
            if (type == 4) {
                if (TextUtils.isEmpty(radioDBBean.getRadioName())) {
                    z2 = false;
                } else {
                    if (z) {
                        radioDBBean2.setRadioName(radioDBBean.getRadioName());
                    }
                    z2 = true;
                }
                if (radioDBBean.getRadioFrequency() != radioDBBean2.getRadioFrequency()) {
                    radioDBBean2.setRadioFrequency(radioDBBean.getRadioFrequency());
                    z2 = true;
                }
                if (radioDBBean.getIconId() > 0) {
                    if (z) {
                        radioDBBean2.setIconId(radioDBBean.getIconId());
                    }
                    z2 = true;
                }
                if (radioDBBean.getArtId() > 0) {
                    if (z) {
                        radioDBBean2.setArtId(radioDBBean.getArtId());
                    }
                    z2 = true;
                }
                if (z2) {
                    return true;
                }
            }
        } else if (!TextUtils.isEmpty(radioDBBean.getRDS_PS())) {
            if (z) {
                radioDBBean2.setRDS_PS(radioDBBean.getRDS_PS());
            }
            return true;
        }
        return false;
    }

    private boolean shouldUpdateDes(RadioManager.ProgramInfo programInfo, AbstractMap.SimpleEntry<RadioDBBean, Integer> simpleEntry, int i, boolean z) {
        boolean z2;
        if (i < simpleEntry.getValue().intValue()) {
            return false;
        }
        RadioDBBean key = simpleEntry.getKey();
        int type = key.getType();
        if (type == 3) {
            String rdsps = getRDSPS(programInfo);
            if (!TextUtils.isEmpty(rdsps)) {
                if (z) {
                    key.setRDS_PS(rdsps);
                    simpleEntry.setValue(Integer.valueOf(i));
                }
                return true;
            }
        } else if (type == 4) {
            String dabServiceName = getDabServiceName(programInfo, false);
            long dabIconID = getDabIconID(programInfo);
            key.setArtId(getDabArtNumber(programInfo));
            if (TextUtils.isEmpty(dabServiceName)) {
                z2 = false;
            } else {
                if (z) {
                    key.setRadioName(dabServiceName);
                }
                z2 = true;
            }
            if (dabIconID > 0) {
                if (z) {
                    key.setIconId((int) dabIconID);
                }
                z2 = true;
            }
            if (z2 && z) {
                simpleEntry.setValue(Integer.valueOf(i));
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    private void sortList(List<RadioDBBean> list) {
        if (this.radioBeanSortComparator == null) {
            this.radioBeanSortComparator = new Comparator() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$KLpNY12lSjRhY0Lg_l9OcLg9e4Q
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return this.f$0.lambda$sortList$7$RadioServiceWrapper((RadioDBBean) obj, (RadioDBBean) obj2);
                }
            };
        }
        list.sort(this.radioBeanSortComparator);
    }

    public /* synthetic */ int lambda$sortList$7$RadioServiceWrapper(RadioDBBean radioDBBean, RadioDBBean radioDBBean2) {
        boolean z = radioDBBean.getType() == 4;
        String radioName = z ? radioDBBean.getRadioName() : String.valueOf(radioDBBean.getRadioFrequency());
        String radioName2 = z ? radioDBBean2.getRadioName() : String.valueOf(radioDBBean2.getRadioFrequency());
        if (!z && isDouble(radioName) && isDouble(radioName2)) {
            return Double.compare(Double.parseDouble(radioName), Double.parseDouble(radioName2));
        }
        boolean zMatches = Pattern.matches("\\d+.*", radioName);
        if (zMatches == Pattern.matches("\\d+.*", radioName2)) {
            return radioName.compareTo(radioName2);
        }
        return zMatches ? 1 : -1;
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private void updateCollectRadio(List<RadioDBBean> list) {
        LogUtil.d(TAG, "updateCollectRadio! radio list size = " + list.size());
        if (list.size() > 0) {
            RadioDB.insertAll(list);
        }
    }

    public boolean queryRadioHalReady() {
        return this.mControlService.get().queryRadioHalReady();
    }

    public void registerRadioProgramListener(IRadioProgramListener iRadioProgramListener) {
        LogUtil.d(TAG, "registerRadioProgramListener!");
        if (iRadioProgramListener == null || this.programListeners.contains(iRadioProgramListener)) {
            return;
        }
        this.programListeners.add(iRadioProgramListener);
    }

    public void unregisterRadioProgramListener(IRadioProgramListener iRadioProgramListener) {
        LogUtil.d(TAG, "unregisterRadioProgramListener!");
        if (iRadioProgramListener != null) {
            this.programListeners.remove(iRadioProgramListener);
        }
    }

    private void insertSort(List list, List<RadioDBBean> list2) {
        if (list == null || list2 == null) {
            throw new RuntimeException("insert sort can not be null list!");
        }
        LogUtil.d("insertSort", "enter with target size = " + list.size() + ", temp size = " + list2.size());
        if (list2.size() == 0) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < list2.size(); i2++) {
            while (i < list.size()) {
                if (isDABBean(list2.get(i2))) {
                    if ((list.get(i) instanceof RadioDBBean) && list2.get(i2).getRDS_PTY() <= ((RadioDBBean) list.get(i)).getRDS_PTY()) {
                        list.add(i, list2.get(i2));
                        i++;
                        break;
                    }
                    i++;
                } else {
                    if ((list.get(i) instanceof RadioDBBean) && list2.get(i2).getRadioFrequency() <= ((RadioDBBean) list.get(i)).getRadioFrequency()) {
                        list.add(i, list2.get(i2));
                        i++;
                        break;
                    }
                    i++;
                }
            }
            if (i >= list.size()) {
                list.add(list2.get(i2));
                i++;
            }
        }
        LogUtil.d("insertSort", "finish with target list size = " + list.size());
    }

    public void setServiceFollow(boolean z) {
        if (this.mControlService.get() != null) {
            this.mControlService.get().setServiceFollow(z);
        }
    }

    public void setAlternativeFrequency(boolean z) {
        if (this.mControlService.get() != null) {
            this.mControlService.get().setAlternativeFrequency(z);
        }
    }

    public static void insertDABPTY(List<RadioDBBean> list) {
        int i = 0;
        int rds_pty = 0;
        while (i < list.size()) {
            if (list.get(i) != null && rds_pty != list.get(i).getRDS_PTY()) {
                rds_pty = list.get(i).getRDS_PTY();
                RadioDBBean radioDBBean = new RadioDBBean();
                radioDBBean.setType(5);
                radioDBBean.setRDS_PTY(rds_pty);
                radioDBBean.setRadioName(RadioPTYUtil.getPTYString(rds_pty));
                list.add(i, radioDBBean);
                i++;
            }
            i++;
        }
    }

    public void checkAndStartProgramListUpdateRunnable() {
        Runnable runnable = this.programListUpdateRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
            this.handler.postDelayed(this.programListUpdateRunnable, 9000L);
        }
    }

    private void setLastRadioType() {
        if (this.mProgramType == ProgramType.AM) {
            this.lastRadioType = 8;
        } else if (this.mProgramType == ProgramType.FM) {
            this.lastRadioType = 7;
        } else {
            this.lastRadioType = 9;
        }
        LogUtil.d(TAG, "setLastRadioType = " + this.lastRadioType);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveChangeApi(ApiChangeBean apiChangeBean) {
        checkAndStartProgramListUpdateRunnable();
        LogUtil.d(TAG, "receiveChangeApi! OnLineMusic.musicEngine = " + OnLineMusic.musicEngine);
        int i = OnLineMusic.musicEngine;
        if (i == 7) {
            if (!this.isFMEnable) {
                LogUtil.e(TAG, "receiveChangeApi !isFMAMEnable ");
                return;
            }
            LogUtil.d(TAG, "receiveChangeApi, currentFmRadioDBBean = " + this.currentFMRadioDBBean);
            setLastRadioType();
            setProgramType(ProgramType.FM);
            if (!isFmBean(this.currentFMRadioDBBean)) {
                LogUtil.e(TAG, "receiveChangeApi !not isFmBean");
                this.currentFMRadioDBBean.reset();
                this.currentFMRadioDBBean.setDAB_SID(-1L);
                this.currentFMRadioDBBean.setRadioFrequency(FM_FREQ_MIN);
            }
            this.mCurrentRadio.setValue(this.currentFMRadioDBBean);
            if (apiChangeBean.flagEnable(1)) {
                return;
            }
            tune(this.currentFMRadioDBBean.getRadioFrequency());
            return;
        }
        if (i == 8) {
            LogUtil.d(TAG, "receiveChangeApi, currentAmRadioDBBean = " + this.currentAmRadioDBBean);
            if (!this.isAMEnable) {
                LogUtil.e(TAG, "receiveChangeApi !isFMAMEnable ");
                return;
            }
            setLastRadioType();
            setProgramType(ProgramType.AM);
            if (!isAmBean(this.currentAmRadioDBBean)) {
                LogUtil.d(TAG, "receiveChangeApi, is not AmRadio");
                this.currentAmRadioDBBean.reset();
                this.currentAmRadioDBBean.setDAB_SID(-1L);
                this.currentAmRadioDBBean.setRadioFrequency(AM_FREQ_MIN);
            }
            this.mCurrentRadio.setValue(this.currentAmRadioDBBean);
            if (apiChangeBean.flagEnable(1)) {
                return;
            }
            tune(this.currentAmRadioDBBean.getRadioFrequency());
            return;
        }
        if (i != 9) {
            return;
        }
        if (!this.isDABEnable) {
            LogUtil.e(TAG, "receiveChangeApi !isDABEnable ");
            return;
        }
        LogUtil.d(TAG, "receiveChangeApi, currentDABRadioDBBean = " + this.currentDABRadioDBBean);
        setLastRadioType();
        setProgramType(ProgramType.DAB);
        if (!isDABBean(this.currentDABRadioDBBean)) {
            LogUtil.e(TAG, "receiveChangeApi !not isDABBean");
            this.currentDABRadioDBBean.reset();
            this.currentDABRadioDBBean.setRadioFrequency(174928);
            this.currentDABRadioDBBean.setDAB_SID(0L);
        }
        this.mCurrentRadio.setValue(this.currentDABRadioDBBean);
        if (this.currentDABRadioDBBean.getDAB_SID() <= 0) {
            if (CollectionUtils.isEmpty(this.dabValidList)) {
                CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(null));
                setRadioPlayState(false);
                if ((!isDabProgram(this.mCurrentProgram.get()) || getDabSID(this.mCurrentProgram.get()) != getDabSID(this.currentDABRadioDBBean)) && !apiChangeBean.flagEnable(1)) {
                    tune(generateDABSelector(this.currentDABRadioDBBean));
                }
            } else {
                for (RadioDBBean radioDBBean : this.dabValidList) {
                    if (radioDBBean instanceof RadioDBBean) {
                        if (!apiChangeBean.flagEnable(1)) {
                            tune(generateDABSelector(radioDBBean));
                        }
                        this.handler.postDelayed(new Runnable() { // from class: com.byd.music.service.RadioServiceWrapper.1
                            @Override // java.lang.Runnable
                            public void run() {
                                RadioServiceWrapper.this.setRadioPlayState(true);
                            }
                        }, 1000L);
                        return;
                    }
                }
            }
        } else if ((this.tuningDABSID != -1 || this.tuningFreq != -1 || !isDabProgram(this.mCurrentProgram.get()) || getDabSID(this.mCurrentProgram.get()) != getDabSID(this.currentDABRadioDBBean)) && !apiChangeBean.flagEnable(1)) {
            tune(generateDABSelector(this.currentDABRadioDBBean));
        }
        try {
            if (this.isPlaying.get()) {
                BYDAutoAudioDevice.getInstance(Utils.getApp()).setLoudness(3);
                LogUtil.d(TAG, "receiveChangeApi DAB SOP, setLoudness 3");
            }
        } catch (Throwable th) {
            LogUtil.d(TAG, "receiveChangeApi DAB SOP, setLoudness 3 error = " + th.getMessage());
        }
    }

    private RadioServiceWrapper(IRadioAppService iRadioAppService) {
        this();
        Objects.requireNonNull(iRadioAppService);
        this.mService.set(iRadioAppService);
        initialize(iRadioAppService);
    }

    public void setService(IRadioAppService iRadioAppService) {
        getInstance().mService.set(iRadioAppService);
        getInstance().initialize(iRadioAppService);
    }

    public void setControlSerivce(final RadioService radioService) {
        getInstance().mControlService.set(radioService);
        BYDAutoBodyworkDevice.getInstance(radioService).registerListener(new AbsBYDAutoBodyworkListener() { // from class: com.byd.music.service.RadioServiceWrapper.2
            @Override // android.hardware.IBYDAutoListener
            public void isConnected(boolean z) {
                super.isConnected(z);
                if (z) {
                    try {
                        int powerLevel = BYDAutoBodyworkDevice.getInstance(Utils.getApp()).getPowerLevel();
                        LogUtil.e("setControlSerivce isConnected BYDAutoBodyworkDevice powerLevel = " + powerLevel);
                        if (powerLevel == 1 || powerLevel == 2) {
                            if (OnLineMusic.VENDOR_CODE == 1) {
                                RadioServiceWrapper.this.setRadioSTR(false);
                            } else {
                                LogUtil.e("setControlSerivce setRadioSTR DESAY");
                            }
                        }
                    } catch (Throwable th) {
                        LogUtil.e("setControlSerivce setRadioSTR error = " + th.getMessage());
                        th.printStackTrace();
                    }
                    BYDAutoBodyworkDevice.getInstance(radioService).unregisterListener((AbsBYDAutoBodyworkListener) this);
                }
            }
        });
    }

    public void getFieldStrength() {
        if (this.mControlService.get() != null && (this.mControlService.get() instanceof RadioService)) {
            LogUtil.d(TAG, "getFieldStrength: enter.");
            this.mControlService.get().getFieldStrength();
        } else {
            LogUtil.d(TAG, "getFieldStrength: failed!");
        }
    }

    public void getDabBER() {
        if (this.mControlService.get() != null && (this.mControlService.get() instanceof RadioService)) {
            LogUtil.d(TAG, "getDabBER: enter.");
            this.mControlService.get().getDabBER();
        } else {
            LogUtil.d(TAG, "getDabBER: failed!");
        }
    }

    public void setRadioSTR(boolean z) {
        if (z && OnLineMusic.VENDOR_CODE == 1) {
            this.isRadioHalReady = false;
        }
        this.mControlService.get().setRadioSTR(z);
    }

    private void initialize(IRadioAppService iRadioAppService) {
        try {
            iRadioAppService.addCallback(this.mCallback);
        } catch (RemoteException e) {
            throw new RuntimeException("Wrapper initialization failed", e);
        }
    }

    public void updateCurrentRadio(RadioManager.ProgramInfo programInfo) {
        RadioDBBean value;
        AbstractMap.SimpleEntry<RadioDBBean, Integer> simpleEntry;
        AbstractMap.SimpleEntry<RadioDBBean, Integer> simpleEntry2;
        try {
            LogUtil.d(TAG, "updateCurrentRadio: enter with info = " + programInfo);
            if (this.mCurrentRadio.getValue() == null) {
                initCurrentRadio();
                if (this.mProgramType == ProgramType.FM) {
                    this.mCurrentRadio.postValue(this.currentFMRadioDBBean);
                    value = this.currentFMRadioDBBean;
                } else if (this.mProgramType == ProgramType.AM) {
                    this.mCurrentRadio.postValue(this.currentAmRadioDBBean);
                    value = this.currentAmRadioDBBean;
                } else if (this.mProgramType == ProgramType.DAB) {
                    this.mCurrentRadio.postValue(this.currentDABRadioDBBean);
                    value = this.currentDABRadioDBBean;
                } else {
                    throw new RuntimeException("currentValueBean can not be null!");
                }
            } else {
                value = this.mCurrentRadio.getValue();
            }
            boolean zIsFMRDSChanged = !isSameStation(value, programInfo);
            if (!zIsFMRDSChanged) {
                zIsFMRDSChanged = isFMRDSChanged(value, programInfo);
            }
            if (zIsFMRDSChanged) {
                LogUtil.d(TAG, "updateCurrentRadio: isRadioStationChanged ");
                value.reset();
                LogUtil.d(TAG, "updateCurrentRadio, Update mRadioType with 0!");
                this.mRadioType.set(0);
                this.tuningFreq = -1L;
                this.tuningDABSID = -1L;
            } else {
                if (isDabProgram(programInfo) && getDabSID(programInfo) == this.tuningDABSID) {
                    this.tuningDABSID = -1L;
                } else if (isFmProgram(programInfo) && getStationFreq(programInfo) == this.tuningFreq) {
                    this.tuningFreq = -1L;
                }
                if (isDabProgram(programInfo) && programInfo.isMuted() && this.isPlaying.get()) {
                    if (this.radioMuteToastRunnable == null) {
                        this.radioMuteToastRunnable = new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$o5l3fWQ5qA0fXHY0K1WWZA_f40c
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$updateCurrentRadio$8$RadioServiceWrapper();
                            }
                        };
                    }
                    this.handler.removeCallbacks(this.radioMuteToastRunnable);
                    this.handler.postDelayed(this.radioMuteToastRunnable, TelecomManager.VERY_SHORT_CALL_TIME_MS);
                }
            }
            value.setRadioFrequency(getStationFreq(programInfo));
            if (isDabProgram(programInfo)) {
                value.setType(103);
                value.setDAB_SID(getDabSID(programInfo));
                value.setIconId(getDabIconID(programInfo));
                value.setArtId(getDabArtNumber(programInfo));
                value.setRadioName(getDabServiceName(programInfo, false));
                value.setRDS_PI(0L);
            } else if (isFmProgram(programInfo)) {
                value.setType(101);
                value.setDAB_SID(-1L);
                if (!TextUtils.isEmpty(getRDSRT(programInfo))) {
                    value.setRDS_RT(getRDSRT(programInfo));
                }
                try {
                    if (this.validCacheMap != null) {
                        if (value.getType() == 4) {
                            simpleEntry2 = this.validCacheMap.get(Long.valueOf(value.getDAB_SID()));
                        } else {
                            simpleEntry2 = this.validCacheMap.get(Integer.valueOf(value.getRadioFrequency()));
                        }
                        try {
                            LogUtil.d(TAG, "updateCurrentRadio, radioDBBeanIntegerSimpleEntry get succeed.");
                        } catch (Throwable th) {
                            simpleEntry = simpleEntry2;
                            th = th;
                            LogUtil.e(TAG, "updateCurrentRadio, radioDBBeanIntegerSimpleEntry error. " + th.getMessage());
                            th.printStackTrace();
                            simpleEntry2 = simpleEntry;
                        }
                    } else {
                        simpleEntry2 = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    simpleEntry = null;
                }
                if (simpleEntry2 != null) {
                    shouldUpdateDes(programInfo, simpleEntry2, 2, true);
                }
                adjRDSProgramInfo();
            } else {
                value.setDAB_SID(-1L);
                value.setRDS_PI(0L);
                value.setRDS_PTY(0);
                value.setType(102);
            }
            this.mCurrentRadio.postValue(value);
            if (OnLineMusic.musicEngine == 7 || OnLineMusic.musicEngine == 8) {
                CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(value));
            } else if (OnLineMusic.musicEngine == 9) {
                if (value.getDAB_SID() > 0) {
                    CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(value));
                } else {
                    if (this.dabDisableRunnable == null) {
                        this.dabDisableRunnable = new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$aVxCqAGXJMROXgjTNfxv40nuXJM
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$updateCurrentRadio$9$RadioServiceWrapper();
                            }
                        };
                    }
                    CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(null));
                    this.handler.removeCallbacks(this.dabDisableRunnable);
                    this.handler.postDelayed(this.dabDisableRunnable, 1000L);
                }
            }
            this.handler.removeCallbacks(this.currentRadioWriteToDBRunnable);
            Runnable runnable = new Runnable() { // from class: com.byd.music.service.RadioServiceWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (RadioServiceWrapper.this.finalSeekTiming > 0 && System.currentTimeMillis() - RadioServiceWrapper.this.finalSeekTiming > 1000) {
                            LogUtil.d(RadioServiceWrapper.TAG, "updateCurrentRadio finalSeekTiming = " + RadioServiceWrapper.this.finalSeekTiming);
                            RadioServiceWrapper.this.isSeeking = false;
                            RadioServiceWrapper.this.finalSeekTiming = 0L;
                        }
                        RadioDB.insert(RadioServiceWrapper.this.mCurrentRadio.getValue());
                    } catch (Exception e) {
                        LogUtil.e(RadioServiceWrapper.TAG, "Error! updateCurrentRadio but update fmRadioDBBean failed!");
                        e.printStackTrace();
                    }
                }
            };
            this.currentRadioWriteToDBRunnable = runnable;
            this.handler.postDelayed(runnable, 1000L);
            LogUtil.d(TAG, "updateCurrentRadio: leave with value = " + this.mCurrentRadio.getValue());
        } catch (NullPointerException unused) {
            LogUtil.e(TAG, "updateCurrentRadio: NullPointerException ");
        }
    }

    public /* synthetic */ void lambda$updateCurrentRadio$8$RadioServiceWrapper() {
        if (OnLineMusic.musicEngine == 9 && this.mCurrentProgram.get() != null && this.mCurrentProgram.get().isMuted()) {
            ToastUtil.showCenter(Utils.getApp().getString(R.string.current_radio_weak_signal));
        }
    }

    public /* synthetic */ void lambda$updateCurrentRadio$9$RadioServiceWrapper() {
        if (OnLineMusic.musicEngine == 9 && this.mCurrentRadio.getValue().getDAB_SID() == 0) {
            setRadioPlayState(false);
        }
    }

    public long getRadioDBBeanUniqueId(RadioDBBean radioDBBean) {
        if (radioDBBean == null) {
            return -1L;
        }
        return radioDBBean.getDAB_SID() > 0 ? radioDBBean.getDAB_SID() : radioDBBean.getRadioFrequency();
    }

    public boolean adjRDSProgramInfo() {
        boolean z;
        LogUtil.d(TAG, "adjRDSProgramInfo enter");
        try {
            RadioDBBean value = this.mCurrentRadio.getValue();
            int iIndexOf = this.fmValidList.indexOf(value);
            boolean z2 = true;
            if (iIndexOf < 0) {
                if (value.getRDS_PTY() <= 0 && TextUtils.isEmpty(value.getRDS_PS())) {
                    z2 = false;
                }
                value.setRDS_PTY(0);
                value.setRDS_PI(0L);
                value.setRDS_PS("");
                value.setRDS_RT("");
                this.mRadioType.set(0);
                LogUtil.d(TAG, "adjRDSProgramInfo leave with index < 0");
                return z2;
            }
            RadioDBBean radioDBBean = this.fmValidList.get(iIndexOf);
            if (!isRDSBean(radioDBBean)) {
                boolean zIsRDSBean = isRDSBean(value);
                value.setRDS_PTY(0);
                value.setRDS_PI(0L);
                value.setRDS_PS("");
                value.setRDS_RT("");
                this.mRadioType.set(0);
                LogUtil.d(TAG, "adjRDSProgramInfo leave with list normal one");
                return zIsRDSBean;
            }
            if (TextUtils.isEmpty(radioDBBean.getRDS_PS())) {
                this.mRadioType.set(0);
            } else {
                this.mRadioType.set((int) radioDBBean.getRDS_PI());
            }
            if (value.getRDS_PI() != radioDBBean.getRDS_PI()) {
                value.setRDS_PI(radioDBBean.getRDS_PI());
                z = true;
            } else {
                z = false;
            }
            if (value.getRDS_PTY() != radioDBBean.getRDS_PTY()) {
                value.setRDS_PTY(radioDBBean.getRDS_PTY());
                z = true;
            }
            if (TextUtils.isEmpty(radioDBBean.getRDS_PS())) {
                value.setRDS_PS(parseFreqI18N(value.getRadioFrequency()) + " MHz");
            } else if (TextUtils.equals(value.getRDS_PS(), radioDBBean.getRDS_PS())) {
                LogUtil.d(TAG, "adjRDSProgramInfo leave with list rds same one");
            } else {
                value.setRDS_PS(radioDBBean.getRDS_PS());
                LogUtil.d(TAG, "adjRDSProgramInfo leave with list rds changed.");
                return z2;
            }
            z2 = z;
            LogUtil.d(TAG, "adjRDSProgramInfo leave with list rds changed.");
            return z2;
        } catch (Throwable th) {
            LogUtil.e(TAG, "adjRDSProgramInfo: error = " + th.getMessage());
            th.printStackTrace();
            return false;
        }
    }

    public void bind(Context context) {
        this.mClientContext = (Context) Objects.requireNonNull(context);
        if (!context.bindService(new Intent(RadioService.ACTION_APP_SERVICE, null, context, RadioService.class), this.mServiceConnection, 1)) {
            throw new RuntimeException("Failed to bind to RadioAppService");
        }
        LogUtil.e(TAG, "bindService succeed!");
    }

    public void unbind() {
        if (this.mClientContext == null) {
            throw new IllegalStateException("This is not a remote service wrapper, you can't unbind it");
        }
        try {
            callService(new ServiceVoidOperation() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$kOFANYN_NEqbAECbPkSPqfJMkok
                @Override // com.byd.music.service.RadioServiceWrapper.ServiceVoidOperation
                public final void execute(IRadioAppService iRadioAppService) throws RemoteException {
                    this.f$0.lambda$unbind$10$RadioServiceWrapper(iRadioAppService);
                }
            });
        } catch (IllegalStateException unused) {
        }
        this.mClientContext.unbindService(this.mServiceConnection);
    }

    public /* synthetic */ void lambda$unbind$10$RadioServiceWrapper(IRadioAppService iRadioAppService) throws RemoteException {
        iRadioAppService.removeCallback(this.mCallback);
    }

    private void callService(ServiceVoidOperation serviceVoidOperation) {
        IRadioAppService iRadioAppService = this.mService.get();
        if (iRadioAppService == null) {
            throw new IllegalStateException("Service is not connected");
        }
        try {
            serviceVoidOperation.execute(iRadioAppService);
        } catch (RemoteException unused) {
            LogUtil.e(TAG, "Remote call failed");
            onServiceFailure();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceConnected(IBinder iBinder, IRadioAppService iRadioAppService) {
        LogUtil.d(TAG, "RadioAppService connected");
        this.mService.set(iRadioAppService);
        initialize(iRadioAppService);
        this.mConnectionState.set(2);
        int i = OnLineMusic.musicEngine;
        if (i == 7 || i == 8 || i == 9) {
            LogUtil.e("zzl", "onServiceConnected: radio go to setCurrentMusicInfoAndPlay");
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        } else {
            setRadioPlayState(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceFailure() {
        if (this.mService.getAndSet(null) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("RadioAppService failed ");
        sb.append(this.mClientContext == null ? "(local)" : "(remote)");
        LogUtil.e(TAG, sb.toString());
        this.mConnectionState.set(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNullBinding() {
        LogUtil.i(TAG, "RadioAppService is not accepting connections. It means the radio hardware is not available");
        this.mClientContext.unbindService(this.mServiceConnection);
        this.mConnectionState.set(3);
    }

    public ObservableInt getConnectionState() {
        return this.mConnectionState;
    }

    public ObservableInt getPlaybackState() {
        return this.mPlaybackState;
    }

    public ObservableField<RadioManager.ProgramInfo> getCurrentProgram() {
        return this.mCurrentProgram;
    }

    public MutableLiveData<List<RadioManager.ProgramInfo>> getProgramList() {
        return this.mProgramList;
    }

    public String tune(ProgramSelector programSelector) {
        try {
            LogUtil.d(TAG, "tune: enter with sel = " + programSelector.toString());
            if (!this.isRadioHalReady) {
                LogUtil.e("tune failed, tuner hal is not ready.");
                return "tuner hal is not ready";
            }
            if (programSelector.getPrimaryId().getType() != 5) {
                this.tuningDABSID = -1L;
            } else {
                if (this.tuningDABSID == programSelector.getPrimaryId().getValue()) {
                    LogUtil.d(TAG, "tune: tuning just return, tuningDABSID = " + this.tuningDABSID);
                    return "";
                }
                this.tuningDABSID = programSelector.getPrimaryId().getValue();
            }
            tune(programSelector, null);
            return "";
        } catch (Throwable th) {
            LogUtil.d(TAG, "tune: enter but error = " + th.getMessage());
            if ((th instanceof IllegalStateException) && th.getMessage().contains("closed")) {
                this.mControlService.get().reopenSession(true);
            }
            th.printStackTrace();
            AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$WtfyA9afCjk2Gh02HF47itd6OLE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$tune$11$RadioServiceWrapper();
                }
            });
            this.tuningDABSID = -1L;
            return th.getMessage();
        }
    }

    public /* synthetic */ void lambda$tune$11$RadioServiceWrapper() {
        LogUtil.d(TAG, "tune: error so re-tune.");
        this.mCurrentRadio.setValue(this.mCurrentRadio.getValue());
    }

    public void reopenSession() {
        this.mControlService.get().reopenSession(true);
    }

    public String tune(int i) {
        if (this.mProgramType == ProgramType.FM) {
            LogUtil.d(TAG, "tune: enter with FM frequency = " + i);
            int i2 = FM_FREQ_MIN;
            if (i < i2 || i > FM_FREQ_MAX) {
                return MediaControl.REASON_OUT_OF_RANGE_FREQUENCY;
            }
            while (i2 <= FM_FREQ_MAX) {
                if (i <= i2) {
                    if (i < i2) {
                        return MediaControl.REASON_UNSUPPORTED_FREQUENCY;
                    }
                    if (i == i2) {
                        break;
                    }
                }
                i2 += FM_FREQ_STEP;
            }
            this.tuningFreq = i;
            return tune(this.mProgramType.parseDigits(i / 100.0f));
        }
        if (this.mProgramType != ProgramType.AM) {
            return MediaControl.REASON_UNSUPPORTED_OPERATION;
        }
        LogUtil.d(TAG, "tune: enter with AM frequency = " + i);
        int i3 = AM_FREQ_MIN;
        if (i < i3 || i > AM_FREQ_MAX) {
            return MediaControl.REASON_OUT_OF_RANGE_FREQUENCY;
        }
        while (i3 <= AM_FREQ_MAX) {
            if (i <= i3) {
                if (i < i3) {
                    return MediaControl.REASON_UNSUPPORTED_FREQUENCY;
                }
                if (i == i3) {
                    break;
                }
            }
            i3 += AM_FREQ_STEP;
        }
        if ((i >= 580 && i <= 625) || (i >= 875 && i <= 925)) {
            if (this.powerFreq != 2) {
                Car.createCar(Utils.getApp(), null, 0L, new Car.CarServiceLifecycleListener() { // from class: com.byd.music.service.RadioServiceWrapper.6
                    @Override // android.car.Car.CarServiceLifecycleListener
                    public void onLifecycleChanged(Car car, boolean z) {
                        RadioServiceWrapper.this.powerFreq = 2;
                        if (z && (car.getCarManager(Car.VENDOR_EXTENSION_SERVICE) instanceof CarVendorExtensionManager)) {
                            LogUtil.d(RadioServiceWrapper.TAG, "power freq, set power AM_SYNC_HIGHFREQ");
                            ((CarVendorExtensionManager) car.getCarManager(Car.VENDOR_EXTENSION_SERVICE)).setProperty(Integer.class, 557903873, 0, 2);
                        }
                    }
                });
            }
        } else if (this.powerFreq != 1) {
            this.powerFreq = 1;
            Car.createCar(Utils.getApp(), null, 0L, new Car.CarServiceLifecycleListener() { // from class: com.byd.music.service.RadioServiceWrapper.7
                @Override // android.car.Car.CarServiceLifecycleListener
                public void onLifecycleChanged(Car car, boolean z) {
                    if (z && (car.getCarManager(Car.VENDOR_EXTENSION_SERVICE) instanceof CarVendorExtensionManager)) {
                        LogUtil.d(RadioServiceWrapper.TAG, "power freq, set power AM_SYNC_LOWFREQ");
                        ((CarVendorExtensionManager) car.getCarManager(Car.VENDOR_EXTENSION_SERVICE)).setProperty(Integer.class, 557903873, 0, 1);
                    }
                }
            });
        }
        this.tuningFreq = i;
        return tune(this.mProgramType.parseDigits(i));
    }

    public void tune(final ProgramSelector programSelector, final RadioTunerExt.TuneCallback tuneCallback) {
        if (!this.isRadioHalReady) {
            LogUtil.e("tune failed, tuner hal is not ready.");
            return;
        }
        if (this.isSeeking) {
            this.mControlService.get().cancel();
            this.isSeeking = false;
        }
        callService(new ServiceVoidOperation() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$8Fc_oN1bjuBeEnxD_VVt8xu6-Ms
            @Override // com.byd.music.service.RadioServiceWrapper.ServiceVoidOperation
            public final void execute(IRadioAppService iRadioAppService) throws RemoteException {
                iRadioAppService.tune(programSelector, new RadioServiceWrapper.TuneCallbackAdapter(tuneCallback));
            }
        });
    }

    public String seek(boolean z) {
        seek(z, null);
        return "";
    }

    public void seek(final boolean z, final RadioTunerExt.TuneCallback tuneCallback) {
        setRadioPlayState(true);
        if (!getBgScanEnable()) {
            if (this.isSeeking) {
                this.mControlService.get().cancel();
                this.isSeeking = false;
                return;
            } else {
                this.isSeeking = true;
                this.finalSeekTiming = System.currentTimeMillis();
            }
        }
        callService(new ServiceVoidOperation() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$pCQODJuUHU24zcboS_eP8rI0l60
            @Override // com.byd.music.service.RadioServiceWrapper.ServiceVoidOperation
            public final void execute(IRadioAppService iRadioAppService) throws RemoteException {
                iRadioAppService.seek(z, new RadioServiceWrapper.TuneCallbackAdapter(tuneCallback));
            }
        });
    }

    public void step(boolean z) {
        step(z, null);
    }

    public String deleteTargetFreqFromCollect(final long j) {
        if (this.isSeeking) {
            cancelSeek();
            return "Seeking, canceled.";
        }
        List<RadioDBBean> listQueryAllRadioNoLive = RadioDB.queryAllRadioNoLive();
        if (listQueryAllRadioNoLive == null) {
            return MediaControl.REASON_NOT_COLLECTED;
        }
        Optional<RadioDBBean> optionalFindFirst = listQueryAllRadioNoLive.stream().filter(new Predicate() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$_u8WLUhd2Z1kmOgSYdTS9_VlzfM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RadioServiceWrapper.lambda$deleteTargetFreqFromCollect$14(j, (RadioDBBean) obj);
            }
        }).findFirst();
        if (!optionalFindFirst.isPresent()) {
            return MediaControl.REASON_NOT_COLLECTED;
        }
        LogUtil.d(TAG, "deleteTargetFreqFromCollect, radio = " + optionalFindFirst.get());
        boolean zDelete = RadioDB.delete(optionalFindFirst.get());
        if (zDelete) {
            this.fmCollectList.remove(optionalFindFirst.get());
            this.amCollectList.remove(optionalFindFirst.get());
        }
        return zDelete ? "" : MediaControl.REASON_UNKNOWN;
    }

    static /* synthetic */ boolean lambda$deleteTargetFreqFromCollect$14(long j, RadioDBBean radioDBBean) {
        return ((long) radioDBBean.getRadioFrequency()) == j;
    }

    public String deleteTargetDABSIDFromCollect(final long j) {
        LogUtil.d(TAG, "deleteTargetDABSIDFromCollect, sid = " + j);
        List<RadioDBBean> listQueryAllRadioNoLive = RadioDB.queryAllRadioNoLive();
        if (listQueryAllRadioNoLive == null) {
            return MediaControl.REASON_NOT_COLLECTED;
        }
        Optional<RadioDBBean> optionalFindFirst = listQueryAllRadioNoLive.stream().filter(new Predicate() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$c_ZfLJ0CL72yrYV1RKLjO2aPXN8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RadioServiceWrapper.lambda$deleteTargetDABSIDFromCollect$15(j, (RadioDBBean) obj);
            }
        }).findFirst();
        if (!optionalFindFirst.isPresent()) {
            return MediaControl.REASON_NOT_COLLECTED;
        }
        LogUtil.d(TAG, "deleteTargetDABSIDFromCollect, dabRadio = " + optionalFindFirst.get());
        RadioDB.delete(optionalFindFirst.get());
        ObservableArrayList<RadioDBBean> observableArrayList = this.dabCollectList;
        if (observableArrayList == null) {
            return "";
        }
        observableArrayList.remove(optionalFindFirst.get());
        return "";
    }

    static /* synthetic */ boolean lambda$deleteTargetDABSIDFromCollect$15(long j, RadioDBBean radioDBBean) {
        return isDABBean(radioDBBean) && radioDBBean.getDAB_SID() == j;
    }

    public String deleteTargetBeanFromCollect(RadioDBBean radioDBBean) {
        return radioDBBean != null ? deleteTargetFreqFromCollect(radioDBBean.getRadioFrequency()) : MediaControl.REASON_NOT_COLLECTED;
    }

    public String addTargetBeanToCollect(RadioDBBean radioDBBean) {
        LogUtil.d(TAG, "addTargetBeanToCollect enter with bean = " + radioDBBean);
        RadioDBBean radioDBBeanDeepCopy = RadioDBBean.deepCopy(radioDBBean);
        if (radioDBBeanDeepCopy == null) {
            return MediaControl.REASON_NOT_COLLECTED;
        }
        List<RadioDBBean> listQueryAllRadioNoLive = RadioDB.queryAllRadioNoLive();
        if (listQueryAllRadioNoLive != null && listQueryAllRadioNoLive.contains(radioDBBeanDeepCopy)) {
            LogUtil.d(TAG, "addTargetBeanToCollect find duplicate bean, just return.");
            return MediaControl.REASON_ALREADY_COLLECTED;
        }
        RadioDB.insert(radioDBBeanDeepCopy);
        int type = radioDBBeanDeepCopy.getType();
        if (type == 1) {
            this.amCollectList.add(0, radioDBBeanDeepCopy);
            return "";
        }
        if (type == 2 || type == 3) {
            this.fmCollectList.add(0, radioDBBeanDeepCopy);
            return "";
        }
        if (type == 4) {
            this.dabCollectList.add(0, radioDBBeanDeepCopy);
            return "";
        }
        LogUtil.e(TAG, "addTargetBeanToCollect error! target type = " + radioDBBeanDeepCopy.getType());
        return "";
    }

    public String addTargetFreqToCollect(final int i) {
        int i2;
        if (this.isSeeking) {
            cancelSeek();
            return "Seeking, canceled.";
        }
        LogUtil.d(TAG, "addTargetFreqToCollect, freq = " + i);
        if (i < FM_FREQ_MIN || i > FM_FREQ_MAX) {
            i2 = (i < AM_FREQ_MIN || i > AM_FREQ_MAX) ? 0 : 1;
        } else {
            i2 = 2;
        }
        if (i2 == 0) {
            return MediaControl.REASON_UNSUPPORTED_FREQUENCY;
        }
        List<RadioDBBean> listQueryAllRadioNoLive = RadioDB.queryAllRadioNoLive();
        if (listQueryAllRadioNoLive != null && listQueryAllRadioNoLive.stream().anyMatch(new Predicate() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$FfxEueiJMcgzcsG9t0WetRQDido
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RadioServiceWrapper.lambda$addTargetFreqToCollect$16(i, (RadioDBBean) obj);
            }
        })) {
            LogUtil.d(TAG, "addTargetFreqToCollect, already_collected!");
            return MediaControl.REASON_ALREADY_COLLECTED;
        }
        RadioDBBean radioDBBean = new RadioDBBean(i, i2);
        RadioDB.insert(radioDBBean);
        if (i2 == 1) {
            this.amCollectList.add(0, radioDBBean);
            return "";
        }
        if (i2 != 2) {
            return "";
        }
        this.fmCollectList.add(0, radioDBBean);
        return "";
    }

    static /* synthetic */ boolean lambda$addTargetFreqToCollect$16(int i, RadioDBBean radioDBBean) {
        return radioDBBean.getRadioFrequency() == i;
    }

    public String addTargetDabToCollect(final long j) {
        List<RadioDBBean> listQueryAllRadioNoLive = RadioDB.queryAllRadioNoLive();
        if (listQueryAllRadioNoLive != null && listQueryAllRadioNoLive.stream().anyMatch(new Predicate() { // from class: com.byd.music.service.-$$Lambda$RadioServiceWrapper$hqfRy7YtAaf2QZOWpb3mdpiMMSU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RadioServiceWrapper.lambda$addTargetDabToCollect$17(j, (RadioDBBean) obj);
            }
        })) {
            return MediaControl.REASON_ALREADY_COLLECTED;
        }
        for (RadioDBBean radioDBBean : this.dabValidList) {
            if (radioDBBean instanceof RadioDBBean) {
                RadioDBBean radioDBBean2 = radioDBBean;
                if (radioDBBean2.getDAB_SID() == j) {
                    RadioDBBean radioDBBeanDeepCopy = RadioDBBean.deepCopy(radioDBBean2);
                    RadioDB.insert(radioDBBeanDeepCopy);
                    this.dabCollectList.add(0, radioDBBeanDeepCopy);
                    return "";
                }
            }
        }
        return MediaControl.REASON_UNSUPPORTED_FREQUENCY;
    }

    static /* synthetic */ boolean lambda$addTargetDabToCollect$17(long j, RadioDBBean radioDBBean) {
        return radioDBBean.getDAB_SID() == j;
    }

    public boolean isFMRDSChanged(RadioDBBean radioDBBean, RadioManager.ProgramInfo programInfo) {
        if (programInfo.getSelector().getPrimaryId().getType() == 2 && radioDBBean.getRDS_PI() == 0 && getRDSFreq(programInfo) == radioDBBean.getRadioFrequency()) {
            return true;
        }
        return programInfo.getSelector().getPrimaryId().getType() == 1 && radioDBBean.getRDS_PI() > 0 && programInfo.getSelector().getPrimaryId().getValue() == ((long) radioDBBean.getRadioFrequency());
    }

    public boolean isSameStation(RadioDBBean radioDBBean, RadioManager.ProgramInfo programInfo) {
        try {
            if (programInfo.getSelector().getPrimaryId().getType() == 5) {
                if (getDabSID(programInfo) == getDabSID(radioDBBean)) {
                    return true;
                }
            } else if (programInfo.getSelector().getPrimaryId().getType() == 2) {
                if (getRDSFreq(programInfo) == radioDBBean.getRadioFrequency()) {
                    return true;
                }
            } else if (programInfo.getSelector().getPrimaryId().getType() == 1 && programInfo.getSelector().getPrimaryId().getValue() == radioDBBean.getRadioFrequency()) {
                return true;
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public void updateAllSourceProgramList() {
        this.mControlService.get().updateProgramList(null);
    }

    public void updateSingleSourceProgramList() {
        if (this.mProgramType == ProgramType.DAB) {
            this.mControlService.get().updateProgramListForDab();
            return;
        }
        if (this.mProgramType == ProgramType.FM) {
            if (!getBgScanEnable()) {
                if (this.isSeeking) {
                    this.mControlService.get().cancel();
                    this.isSeeking = false;
                    return;
                } else {
                    this.isSeeking = true;
                    this.finalSeekTiming = System.currentTimeMillis();
                }
            }
            this.mControlService.get().updateProgramListForFM();
            return;
        }
        if (!getBgScanEnable()) {
            if (this.isSeeking) {
                this.mControlService.get().cancel();
                this.isSeeking = false;
                return;
            } else {
                this.isSeeking = true;
                this.finalSeekTiming = System.currentTimeMillis();
            }
        }
        this.mControlService.get().updateProgramListForAM();
    }

    public boolean cancelSeek() {
        if (!this.isSeeking) {
            return false;
        }
        this.mControlService.get().cancel();
        this.isSeeking = false;
        return true;
    }

    public String tuneToFavoriteRadio() {
        List<RadioDBBean> listQueryAllRadioNoLive = RadioDB.queryAllRadioNoLive();
        if (listQueryAllRadioNoLive == null) {
            return MediaControl.REASON_NOT_FOUND;
        }
        for (RadioDBBean radioDBBean : listQueryAllRadioNoLive) {
            if (this.mProgramType == ProgramType.DAB && isDABBean(radioDBBean) && this.isDABEnable) {
                return tune(generateDABSelector(radioDBBean));
            }
            if (this.mProgramType == ProgramType.AM && isAmBean(radioDBBean) && this.isAMEnable) {
                return tune(radioDBBean.getRadioFrequency());
            }
            if (this.mProgramType == ProgramType.FM && isFmBean(radioDBBean) && this.isFMEnable) {
                return tune(radioDBBean.getRadioFrequency());
            }
        }
        return MediaControl.REASON_NOT_FOUND;
    }

    public int getStationType(RadioManager.ProgramInfo programInfo) {
        if (isDabProgram(programInfo)) {
            return 4;
        }
        if (isAmProgram(programInfo)) {
            return 1;
        }
        return isRDSProgram(programInfo) ? 3 : 2;
    }

    public int getStationFreq(RadioManager.ProgramInfo programInfo) {
        if (isDabProgram(programInfo)) {
            return getDabFreq(programInfo);
        }
        if (isRDSProgram(programInfo)) {
            return getRDSFreq(programInfo);
        }
        return getFMAMFrequency(programInfo);
    }

    public static int getFMAMFrequency(RadioManager.ProgramInfo programInfo) {
        if (programInfo.getSelector().getPrimaryId().getType() == 1) {
            return (int) programInfo.getSelector().getPrimaryId().getValue();
        }
        return 0;
    }

    public static boolean isFmBean(RadioDBBean radioDBBean) {
        return radioDBBean.getDAB_SID() < 0 && radioDBBean.getRadioFrequency() >= FM_FREQ_MIN && radioDBBean.getRadioFrequency() <= FM_FREQ_MAX;
    }

    public static boolean isAmBean(RadioDBBean radioDBBean) {
        return radioDBBean.getDAB_SID() < 0 && radioDBBean.getRadioFrequency() <= AM_FREQ_MAX && radioDBBean.getRadioFrequency() >= AM_FREQ_MIN;
    }

    public static boolean isDABBean(RadioDBBean radioDBBean) {
        return radioDBBean.getDAB_SID() >= 0;
    }

    public static boolean isRDSBean(RadioDBBean radioDBBean) {
        return radioDBBean.getRDS_PI() > 0;
    }

    public static boolean isDabProgram(RadioManager.ProgramInfo programInfo) {
        return programInfo.getSelector().getPrimaryId().getType() == 5;
    }

    public static boolean isFmProgram(RadioManager.ProgramInfo programInfo) {
        try {
            if (!isRDSProgram(programInfo)) {
                if (programInfo.getSelector().getPrimaryId().getType() == 1 && programInfo.getSelector().getPrimaryId().getValue() >= FM_FREQ_MIN) {
                    if (programInfo.getSelector().getPrimaryId().getValue() <= FM_FREQ_MAX) {
                    }
                }
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isAmProgram(RadioManager.ProgramInfo programInfo) {
        try {
            if (programInfo.getSelector().getPrimaryId().getType() == 1 && programInfo.getSelector().getPrimaryId().getValue() >= AM_FREQ_MIN) {
                if (programInfo.getSelector().getPrimaryId().getValue() <= AM_FREQ_MAX) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean isRDSProgram(RadioManager.ProgramInfo programInfo) {
        return programInfo.getSelector().getPrimaryId().getType() == 2;
    }

    public static RadioDBBean parseRadioProgram(RadioManager.ProgramInfo programInfo) {
        RadioDBBean radioDBBean = new RadioDBBean();
        int fMAMFrequency = getFMAMFrequency(programInfo);
        if (isFmProgram(programInfo)) {
            if (isRDSProgram(programInfo)) {
                int rDSFreq = getRDSFreq(programInfo);
                String rdsps = getRDSPS(programInfo);
                radioDBBean.setRadioFrequency(rDSFreq);
                radioDBBean.setRDS_PTY(getRDSPTY(programInfo));
                radioDBBean.setRDS_PI(getRDSPI(programInfo));
                radioDBBean.setRDS_PS(rdsps);
                radioDBBean.setType(3);
            } else if (isFmProgram(programInfo)) {
                radioDBBean.setRadioFrequency(fMAMFrequency);
                radioDBBean.setType(2);
            }
        } else if (isAmProgram(programInfo)) {
            radioDBBean.setRadioFrequency(fMAMFrequency);
            radioDBBean.setType(1);
        } else if (isDabProgram(programInfo)) {
            int dabFreq = getDabFreq(programInfo);
            radioDBBean.setRadioName(getDabServiceName(programInfo, false));
            radioDBBean.setDAB_SID(getDabSID(programInfo));
            radioDBBean.setRadioFrequency(dabFreq);
            radioDBBean.setRDS_PTY(getDabPTY(programInfo));
            radioDBBean.setIconId(getDabIconID(programInfo));
            radioDBBean.setArtId(getDabArtNumber(programInfo));
            radioDBBean.setType(4);
        }
        return radioDBBean;
    }

    public static long getDabSID(RadioDBBean radioDBBean) {
        return radioDBBean.getDAB_SID();
    }

    public static long getDabSID(RadioManager.ProgramInfo programInfo) {
        try {
            if (programInfo.getSelector().getPrimaryId().getType() == 5) {
                return programInfo.getSelector().getPrimaryId().getValue();
            }
            return -1L;
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public static int getRDSPTY(RadioManager.ProgramInfo programInfo) {
        try {
            if (programInfo.getSelector().getPrimaryId().getType() == 2 && programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_RDS_PTY) != 0) {
                return programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_RDS_PTY);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static String getRDSPS(RadioManager.ProgramInfo programInfo) {
        try {
            if (programInfo.getSelector().getPrimaryId().getType() == 2 && !TextUtils.isEmpty(programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_RDS_PS))) {
                return programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_RDS_PS);
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    public static String getRDSRT(RadioManager.ProgramInfo programInfo) {
        try {
            if (programInfo.getSelector().getPrimaryId().getType() == 2 && !TextUtils.isEmpty(programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_RDS_RT))) {
                return programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_RDS_RT);
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    public static int getRDSPI(RadioManager.ProgramInfo programInfo) {
        try {
            if (programInfo.getSelector().getPrimaryId().getType() == 2) {
                return (int) programInfo.getSelector().getPrimaryId().getValue();
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static int getRadioFreq(RadioManager.ProgramInfo programInfo) {
        try {
            return isDabProgram(programInfo) ? getDabFreq(programInfo) : isRDSProgram(programInfo) ? getRDSFreq(programInfo) : getFMAMFrequency(programInfo);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static int getRDSFreq(RadioManager.ProgramInfo programInfo) {
        int i;
        try {
        } catch (Throwable unused) {
        }
        for (ProgramSelector.Identifier identifier : programInfo.getSelector().getSecondaryIds()) {
            if (identifier.getType() == 1) {
                return (int) identifier.getValue();
            }
            return 0;
        }
        return 0;
    }

    public static int getDabFreq(RadioManager.ProgramInfo programInfo) {
        int i;
        try {
        } catch (Throwable unused) {
        }
        for (ProgramSelector.Identifier identifier : programInfo.getSelector().getSecondaryIds()) {
            if (identifier.getType() == 8) {
                return (int) identifier.getValue();
            }
            return -1;
        }
        return -1;
    }

    public static String getDabServiceName(RadioManager.ProgramInfo programInfo) {
        return getDabServiceName(programInfo, true);
    }

    public static String getDabServiceName(RadioManager.ProgramInfo programInfo, boolean z) {
        try {
            String string = programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_DAB_SERVICE_NAME);
            return TextUtils.isEmpty(string) ? z ? Utils.getApp().getString(R.string.unknown_radio_station) : "" : string;
        } catch (Throwable unused) {
            return z ? Utils.getApp().getString(R.string.unknown_radio_station) : "";
        }
    }

    public static int getDabIconID(RadioManager.ProgramInfo programInfo) {
        try {
            return programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ICON);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static Bitmap getDabAlbumPreferIcon(RadioManager.ProgramInfo programInfo) {
        return getDabAlbumPreferIcon(programInfo, false);
    }

    public static Bitmap getDabAlbumPreferIcon(RadioManager.ProgramInfo programInfo, boolean z) {
        try {
            if (programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ICON) != 0) {
                return getInstance().mControlService.get().getMetadataImage(programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ICON));
            }
            if (programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ART) == 0 || z) {
                return null;
            }
            return getInstance().mControlService.get().getMetadataImage(programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ART));
        } catch (Throwable th) {
            LogUtil.d(TAG, "getDabAlbumPreferIcon error with " + th.getMessage());
            return null;
        }
    }

    public static Bitmap getDabAlbumPreferIcon(RadioDBBean radioDBBean) {
        return getDabAlbumPreferIcon(radioDBBean, false);
    }

    public static Bitmap getDabAlbumPreferIcon(RadioDBBean radioDBBean, boolean z) {
        try {
            if (radioDBBean.getIconId() != 0) {
                return getInstance().mControlService.get().getMetadataImage(radioDBBean.getIconId());
            }
            if (radioDBBean.getArtId() == 0 || z) {
                return null;
            }
            return getInstance().mControlService.get().getMetadataImage(radioDBBean.getArtId());
        } catch (Throwable th) {
            LogUtil.d(TAG, "getDabAlbumPreferIcon error with " + th.getMessage());
            return null;
        }
    }

    public static Bitmap getDabAlbumPreferArt(RadioDBBean radioDBBean) {
        return getDabAlbumPreferArt(radioDBBean, false);
    }

    public static Bitmap getDabAlbumPreferArt(RadioDBBean radioDBBean, boolean z) {
        try {
            if (radioDBBean.getArtId() != 0) {
                return getInstance().mControlService.get().getMetadataImage(radioDBBean.getArtId());
            }
            if (radioDBBean.getIconId() == 0 || z) {
                return null;
            }
            return getInstance().mControlService.get().getMetadataImage(radioDBBean.getIconId());
        } catch (Throwable th) {
            LogUtil.d(TAG, "getDabAlbumPreferArt error with " + th.getMessage());
            return null;
        }
    }

    public static Bitmap getDabAlbumPreferArt(RadioManager.ProgramInfo programInfo) {
        return getDabAlbumPreferArt(programInfo, false);
    }

    public static Bitmap getDabAlbumPreferArt(RadioManager.ProgramInfo programInfo, boolean z) {
        try {
            if (programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ART) != 0) {
                return getInstance().mControlService.get().getMetadataImage(programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ART));
            }
            if (programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ICON) == 0 || z) {
                return null;
            }
            return getInstance().mControlService.get().getMetadataImage(programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ICON));
        } catch (Throwable th) {
            LogUtil.d(TAG, "getDabAlbumPreferArt error with " + th.getMessage());
            return null;
        }
    }

    public static Bitmap getDabAlbum(int i) {
        if (i != 0) {
            try {
                return getInstance().mControlService.get().getMetadataImage(i);
            } catch (Throwable th) {
                LogUtil.d(TAG, "getDabAlbum error with " + th.getMessage());
            }
        }
        return null;
    }

    public static int getDabArtNumber(RadioManager.ProgramInfo programInfo) {
        try {
            return programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_ART);
        } catch (Throwable th) {
            LogUtil.d(TAG, "getDabArtNumber error with " + th.getMessage());
            return 0;
        }
    }

    public static String getDabEnsembleName(RadioManager.ProgramInfo programInfo) {
        try {
            if (TextUtils.isEmpty(programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_DAB_ENSEMBLE_NAME))) {
                return Utils.getApp().getString(R.string.dab_no_radio_ensemble);
            }
            return programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_DAB_ENSEMBLE_NAME);
        } catch (Throwable unused) {
            return Utils.getApp().getString(R.string.dab_no_radio_ensemble);
        }
    }

    public static int getDabPTY(RadioManager.ProgramInfo programInfo) {
        try {
            return programInfo.getMetadata().getInt(RadioMetadata.METADATA_KEY_RDS_PTY);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String getDabDLS(RadioManager.ProgramInfo programInfo) {
        try {
            return TextUtils.isEmpty(programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_RDS_RT)) ? "" : programInfo.getMetadata().getString(RadioMetadata.METADATA_KEY_RDS_RT);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static ProgramSelector generateDABSelector(RadioDBBean radioDBBean) {
        LogUtil.d(TAG, "generateDABSelector enter with bean = " + radioDBBean);
        if (radioDBBean != null && (radioDBBean.getType() == 4 || radioDBBean.getType() == 103)) {
            return new ProgramSelector(5, new ProgramSelector.Identifier(5, radioDBBean.getDAB_SID()), new ProgramSelector.Identifier[]{new ProgramSelector.Identifier(8, radioDBBean.getRadioFrequency())}, null);
        }
        return new ProgramSelector(5, new ProgramSelector.Identifier(5, 0L), new ProgramSelector.Identifier[]{new ProgramSelector.Identifier(8, 174928L)}, null);
    }

    public static ProgramSelector generateDABSelector(long j, long j2) {
        return new ProgramSelector(5, new ProgramSelector.Identifier(5, j), new ProgramSelector.Identifier[]{new ProgramSelector.Identifier(8, j2)}, null);
    }

    public void restartEpgCountDown() {
        try {
            if (this.epgRunnable == null) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            LogUtil.d(TAG, "restartEpgCountDown enter with time = " + jCurrentTimeMillis);
            Iterator<RadioEpgDBBean> it = this.radioSubscribeEpgList.iterator();
            while (it.hasNext()) {
                RadioEpgDBBean next = it.next();
                if (next.getEpgStartTime() * 1000 <= jCurrentTimeMillis) {
                    it.remove();
                    LogUtil.d(TAG, "restartEpgCountDown remove bean = " + next);
                }
            }
            this.handler.removeCallbacks(this.epgRunnable);
            if (this.radioSubscribeEpgList.size() > 0) {
                this.handler.postAtTime(this.epgRunnable, (((this.radioSubscribeEpgList.get(0).getEpgStartTime() * 1000) - System.currentTimeMillis()) - 20000) + SystemClock.uptimeMillis());
            }
        } catch (Throwable th) {
            LogUtil.d(TAG, "restartEpgCountDown error with e = " + th.getMessage());
        }
    }

    public void subscribeEpgWriteToDB() {
        RadioEpgDB.deleteAll();
        RadioEpgDB.insertAll(this.radioSubscribeEpgList);
    }

    public RadioEpgDBBean getEpgSubscribeBean(RadioEpgDBBean radioEpgDBBean) {
        ObservableArrayList<RadioEpgDBBean> observableArrayList = this.radioSubscribeEpgList;
        if (observableArrayList == null || observableArrayList.size() == 0) {
            return null;
        }
        for (RadioEpgDBBean radioEpgDBBean2 : this.radioSubscribeEpgList) {
            if (radioEpgDBBean2.equals(radioEpgDBBean)) {
                return radioEpgDBBean2;
            }
        }
        return null;
    }

    public String addEpgToSubscribeListWithSort(RadioEpgDBBean radioEpgDBBean) {
        ObservableArrayList<RadioEpgDBBean> observableArrayList = this.radioSubscribeEpgList;
        if (observableArrayList != null && observableArrayList.size() != 0) {
            for (RadioEpgDBBean radioEpgDBBean2 : this.radioSubscribeEpgList) {
                if (radioEpgDBBean.getEpgStartTime() < radioEpgDBBean2.getEpgStartTime()) {
                    ObservableArrayList<RadioEpgDBBean> observableArrayList2 = this.radioSubscribeEpgList;
                    observableArrayList2.add(observableArrayList2.indexOf(radioEpgDBBean2), radioEpgDBBean);
                    restartEpgCountDown();
                    return "";
                }
                if (radioEpgDBBean2.equals(radioEpgDBBean)) {
                    return MediaControl.REASON_ALREADY_COLLECTED;
                }
            }
        }
        this.radioSubscribeEpgList.add(radioEpgDBBean);
        restartEpgCountDown();
        return "";
    }

    public String deleteEpgFromSubscribeList(RadioEpgDBBean radioEpgDBBean) {
        ObservableArrayList<RadioEpgDBBean> observableArrayList = this.radioSubscribeEpgList;
        if (observableArrayList == null || observableArrayList.size() == 0) {
            return MediaControl.REASON_NOT_COLLECTED;
        }
        for (RadioEpgDBBean radioEpgDBBean2 : this.radioSubscribeEpgList) {
            if (radioEpgDBBean2.equals(radioEpgDBBean)) {
                this.radioSubscribeEpgList.remove(radioEpgDBBean2);
                restartEpgCountDown();
                return "";
            }
        }
        return MediaControl.REASON_NOT_COLLECTED;
    }

    public static RadioManager.ProgramInfo generateAMProgram(int i) {
        LogUtil.d(TAG, "generateAMProgram!");
        return new RadioManager.ProgramInfo(new ProgramSelector(1, new ProgramSelector.Identifier(1, i), null, null), new ProgramSelector.Identifier(8, 194064L), new ProgramSelector.Identifier(8, 194064L), null, 0, 41, new RadioMetadata.Builder().build(), null);
    }

    public static RadioManager.ProgramInfo generateFMProgram(int i) {
        LogUtil.d(TAG, "generateFMProgram!");
        int i2 = (i / FM_FREQ_STEP) % 2 == 0 ? 1 : 2;
        long j = i;
        return new RadioManager.ProgramInfo(new ProgramSelector(i2, new ProgramSelector.Identifier(i2, j), new ProgramSelector.Identifier[]{new ProgramSelector.Identifier(1, j)}, null), new ProgramSelector.Identifier(8, 194064L), new ProgramSelector.Identifier(8, 194064L), null, 0, 41, new RadioMetadata.Builder().putString(RadioMetadata.METADATA_KEY_RDS_PS, "RDS_" + i).build(), null);
    }

    public static RadioManager.ProgramInfo generateDABProgram(int i, String str) {
        LogUtil.d(TAG, "generateDABProgram!");
        new Random();
        ProgramSelector programSelector = new ProgramSelector(5, new ProgramSelector.Identifier(5, i + 14705455), new ProgramSelector.Identifier[]{new ProgramSelector.Identifier(6, 197648L), new ProgramSelector.Identifier(8, 33153L)}, null);
        RadioMetadata.Builder builder = new RadioMetadata.Builder();
        if (str != null) {
            builder.putString(RadioMetadata.METADATA_KEY_DAB_SERVICE_NAME, str);
            builder.putInt(RadioMetadata.METADATA_KEY_ICON, str.length());
        }
        return new RadioManager.ProgramInfo(programSelector, new ProgramSelector.Identifier(8, 194064L), new ProgramSelector.Identifier(8, 194064L), null, 0, 41, builder.build(), null);
    }

    public static String parseFreqI18N(int i) {
        return new DecimalFormat("#.0#").format(i / 1000.0f);
    }
}
