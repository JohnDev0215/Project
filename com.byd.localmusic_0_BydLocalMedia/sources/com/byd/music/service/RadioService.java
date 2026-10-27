package com.byd.music.service;

import android.app.Service;
import android.app.job.JobInfo;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.radio.ProgramList;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.media.AudioSystem;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import android.util.TimedRemoteCaller;
import com.android.car.radio.bands.ProgramType;
import com.android.car.radio.bands.RegionConfig;
import com.android.car.radio.platform.RadioManagerExt;
import com.android.car.radio.platform.RadioTunerExt;
import com.android.car.radio.service.IRadioAppCallback;
import com.android.car.radio.service.IRadioAppService;
import com.android.car.radio.service.ITuneCallback;
import com.blankj.utilcode.util.MapUtils;
import com.byd.music.OnLineMusic;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.AudioFocusManager;
import com.byd.music.fragment.radio.RadioEpgDiffCallback;
import com.byd.music.service.RadioService;
import com.byd.music.utils.LogUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class RadioService extends Service {
    public static String ACTION_APP_SERVICE = "com.android.car.radio.ACTION_APP_SERVICE";
    private static final long PROGRAM_LIST_RATE_LIMITING = 1000;
    private static final String TAG = "RadioService";
    private long latelyStartForBosch;
    ProgramList.ListCallback listCallback;
    public DiffObservableList<RadioEpgDBBean> mCurrentEpgList;
    private long mDabEpgSid;
    private ProgramList mProgramList;
    private RadioManagerExt mRadioManager;
    private RadioTunerExt mRadioTuner;
    private RadioServiceWrapper mRadioWrapper;
    ProgramList.OnCompleteListener onCompleteListener;
    private final Object mLock = new Object();
    private final List<IRadioAppCallback> mRadioAppCallbacks = new ArrayList();
    private RadioManager.ProgramInfo mCurrentProgram = null;
    private int mCurrentPlaybackState = 0;
    private boolean reopenChance = true;
    private boolean firstScanAlready = false;
    private long[] finalReadyTime = new long[1];
    private boolean isDoubleTunerSeeking = false;
    private long latelySeekingInterruptForNotReady = 0;
    private Runnable doubleTunerSeekingCancelRunnable = new Runnable() { // from class: com.byd.music.service.RadioService.1
        @Override // java.lang.Runnable
        public void run() {
            RadioService.this.isDoubleTunerSeeking = false;
        }
    };
    private final IRadioAppService.Stub mBinder = new IRadioAppService.Stub() { // from class: com.byd.music.service.RadioService.6
        @Override // com.android.car.radio.service.IRadioAppService
        public void addCallback(IRadioAppCallback iRadioAppCallback) throws RemoteException {
            synchronized (RadioService.this.mLock) {
                LogUtil.d(RadioService.TAG, "IRadioAppService addCallback: enter...");
                RadioService.this.mRadioAppCallbacks.add(iRadioAppCallback);
            }
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void removeCallback(IRadioAppCallback iRadioAppCallback) {
            synchronized (RadioService.this.mLock) {
                LogUtil.d(RadioService.TAG, "IRadioAppService removeCallback: enter...");
                RadioService.this.mRadioAppCallbacks.remove(iRadioAppCallback);
            }
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void tune(final ProgramSelector programSelector, ITuneCallback iTuneCallback) throws RemoteException {
            LogUtil.d(RadioService.TAG, "IRadioAppService tune: enter with sel = " + programSelector);
            synchronized (RadioService.this.mLock) {
                if (RadioService.this.mRadioWrapper.isRadioHalReady) {
                    if (RadioService.this.mRadioTuner != null) {
                        RadioService.this.mRadioTuner.tune(programSelector, new RadioTunerExt.TuneCallback() { // from class: com.byd.music.service.RadioService.6.1
                            @Override // com.android.car.radio.platform.RadioTunerExt.TuneCallback
                            public void onFinished(boolean z) {
                                LogUtil.d(RadioService.TAG, "IRadioAppService tune onFinished: succeeded = " + z + ", sel = " + programSelector);
                                if (z) {
                                    return;
                                }
                                RadioService.this.reopenSession();
                            }
                        });
                        return;
                    }
                    throw new IllegalStateException("Tuner session is closed");
                }
                LogUtil.e(RadioService.TAG, "IRadioAppService tune: isRadioHalReady false, just return");
                RadioService.this.mRadioWrapper.tuningDABSID = -1L;
                RadioService.this.mRadioWrapper.tuningFreq = -1L;
            }
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void seek(boolean z, ITuneCallback iTuneCallback) {
            LogUtil.d(RadioService.TAG, "IRadioAppService seek: enter with forward = " + z);
            synchronized (RadioService.this.mLock) {
                if (RadioService.this.mRadioTuner != null) {
                    RadioService.this.mRadioTuner.seek(z, new RadioTunerExt.TuneCallback() { // from class: com.byd.music.service.RadioService.6.2
                        @Override // com.android.car.radio.platform.RadioTunerExt.TuneCallback
                        public void onFinished(boolean z2) {
                            LogUtil.d(RadioService.TAG, "IRadioAppService seek onFinished: succeeded = " + z2);
                            if (z2) {
                                return;
                            }
                            RadioService.this.reopenSession();
                        }
                    });
                } else {
                    throw new IllegalStateException("Tuner session is closed");
                }
            }
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void skip(boolean z, ITuneCallback iTuneCallback) throws RemoteException {
            LogUtil.d(RadioService.TAG, "IRadioAppService skip: enter...");
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void setSkipMode(int i) {
            LogUtil.d(RadioService.TAG, "IRadioAppService setSkipMode: enter...");
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void step(boolean z, ITuneCallback iTuneCallback) {
            LogUtil.d(RadioService.TAG, "IRadioAppService step: enter...");
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void setMuted(boolean z) {
            LogUtil.d(RadioService.TAG, "IRadioAppService setMuted: enter...");
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void switchBand(ProgramType programType) throws RemoteException {
            LogUtil.d(RadioService.TAG, "IRadioAppService switchBand: enter...");
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public boolean isProgramListSupported() {
            LogUtil.d(RadioService.TAG, "IRadioAppService isProgramListSupported: enter...");
            return true;
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public RegionConfig getRegionConfig() {
            LogUtil.d(RadioService.TAG, "IRadioAppService getRegionConfig: enter...");
            return null;
        }
    };
    private RadioTuner.Callback mHardwareCallback = new AnonymousClass7();
    private volatile boolean alreadySetEpgEnable = false;

    /* JADX INFO: Access modifiers changed from: private */
    public void onHardwareError() {
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public void reopenSession() {
        reopenSession(false);
    }

    public void reopenSession(boolean z) {
        LogUtil.d(TAG, "reopenSession: enter with reopenChance = " + this.reopenChance + ", isForce = " + z);
        if (this.isDoubleTunerSeeking) {
            LogUtil.d(TAG, "reopenSession:isDoubleTunerSeeking working, just return.");
        }
        if (z || this.reopenChance) {
            if (OnLineMusic.VENDOR_CODE == 2) {
                AudioFocusManager.getInstance().audioManager.setParameters("handle_fm=1");
            }
            this.mRadioWrapper.tuningFreq = -1L;
            this.mRadioWrapper.tuningDABSID = -1L;
            this.mRadioWrapper.isRadioHalReady = false;
            this.reopenChance = false;
            this.mRadioTuner = this.mRadioManager.openSession(this.mHardwareCallback, null);
        }
    }

    public void cancel() {
        LogUtil.d(TAG, "cancel enter, go to mTuner.cancel, isSeeking = " + this.mRadioWrapper.isSeeking);
        if (this.mRadioWrapper.isSeeking) {
            this.mRadioWrapper.isSeekingAbort = true;
        }
        this.mRadioTuner.cancel();
    }

    public void setServiceFollow(boolean z) {
        if (OnLineMusic.VENDOR_CODE == 2) {
            setServiceFollowForDeasy(z);
        } else {
            this.mRadioTuner.setServiceFollow(z);
        }
    }

    public void setAlternativeFrequency(boolean z) {
        this.mRadioTuner.setAlternativeFrequency(z);
    }

    public Bitmap getMetadataImage(int i) {
        return this.mRadioTuner.getMetadataImage(i);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        RadioServiceWrapper radioServiceWrapper = RadioServiceWrapper.getInstance();
        this.mRadioWrapper = radioServiceWrapper;
        radioServiceWrapper.setService(this.mBinder);
        DiffObservableList<RadioEpgDBBean> diffObservableList = new DiffObservableList<>(new RadioEpgDiffCallback());
        this.mCurrentEpgList = diffObservableList;
        this.mRadioWrapper.radioCurrentEpgList = diffObservableList;
        this.listCallback = new ProgramList.ListCallback() { // from class: com.byd.music.service.RadioService.2
            @Override // android.hardware.radio.ProgramList.ListCallback
            public void onItemChanged(ProgramSelector.Identifier identifier) {
                LogUtil.v(RadioService.TAG, "zzl ListCallback onItemChanged = " + identifier);
            }

            @Override // android.hardware.radio.ProgramList.ListCallback
            public void onItemRemoved(ProgramSelector.Identifier identifier) {
                LogUtil.v(RadioService.TAG, "zzl ListCallback onItemRemoved = " + identifier.toString());
            }
        };
        this.onCompleteListener = new ProgramList.OnCompleteListener() { // from class: com.byd.music.service.RadioService.3
            @Override // android.hardware.radio.ProgramList.OnCompleteListener
            public void onComplete() {
                RadioService.this.pushProgramListUpdate();
            }
        };
        try {
            RadioManagerExt radioManagerExt = new RadioManagerExt(this);
            this.mRadioManager = radioManagerExt;
            this.mRadioTuner = radioManagerExt.openSession(this.mHardwareCallback, null);
        } catch (Exception e) {
            LogUtil.e(TAG, "onCreate: exception = " + e.getMessage());
            e.printStackTrace();
        }
        if (this.mRadioTuner == null) {
            LogUtil.e(TAG, "Couldn't open tuner session");
        } else {
            this.mRadioWrapper.setControlSerivce(this);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (ACTION_APP_SERVICE.equals(intent.getAction())) {
            return this.mBinder;
        }
        return null;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.i(TAG, "Shutting down RadioAppService...");
        super.onDestroy();
    }

    private void onPlaybackStateChanged(int i) {
        synchronized (this.mLock) {
            this.mCurrentPlaybackState = i;
        }
    }

    private void onProgramListChanged() {
        synchronized (this.mLock) {
            pushProgramListUpdate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushProgramListUpdate() {
        this.isDoubleTunerSeeking = false;
        ProgramList programList = this.mProgramList;
        if (programList == null) {
            LogUtil.e(TAG, "zzl updateProgramList pushProgramListUpdate: mProgramList is null.");
            return;
        }
        if (programList.toList().size() > 0) {
            LogUtil.d(TAG, "zzl updateProgramList pushProgramListUpdate: mProgramList size = " + this.mProgramList.toList().size());
        } else {
            LogUtil.d(TAG, "zzl updateProgramList pushProgramListUpdate: once info size = 0");
        }
        this.mRadioWrapper.updateProgramList(this.mProgramList.toList());
        new Handler().postDelayed(new Runnable() { // from class: com.byd.music.service.RadioService.4
            @Override // java.lang.Runnable
            public void run() {
                RadioService.this.mProgramList.close();
            }
        }, 200L);
    }

    public void updateProgramListForFM() {
        LogUtil.e(TAG, "zzl updateProgramList: enter for FM ");
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        updateProgramList(new ProgramList.Filter(hashSet, new HashSet(), true, false));
    }

    public void updateProgramListForAM() {
        LogUtil.e(TAG, "zzl updateProgramList: enter for AM ");
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        updateProgramList(new ProgramList.Filter(hashSet, new HashSet(), true, false));
    }

    public void updateProgramListForDab() {
        LogUtil.e(TAG, "zzl updateProgramList: enter for dab ");
        HashSet hashSet = new HashSet();
        hashSet.add(5);
        updateProgramList(new ProgramList.Filter(hashSet, new HashSet(), true, false));
    }

    public void updateProgramList(ProgramList.Filter filter) {
        LogUtil.e(TAG, "updateProgramList: enter with filter = " + filter);
        try {
            if (!this.mRadioWrapper.isRadioHalReady) {
                LogUtil.e(TAG, "updateProgramList: isRadioHalReady false");
                if (this.latelySeekingInterruptForNotReady == 0) {
                    this.latelySeekingInterruptForNotReady = System.currentTimeMillis();
                    return;
                } else {
                    if (System.currentTimeMillis() - this.latelySeekingInterruptForNotReady > JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS) {
                        this.latelySeekingInterruptForNotReady = 0L;
                        AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.service.RadioService.5
                            @Override // java.lang.Runnable
                            public void run() {
                                LogUtil.e(RadioService.TAG, "updateProgramList: go to reopenSession force after delay");
                                RadioService.this.reopenSession(true);
                            }
                        }, 1000L);
                        return;
                    }
                    return;
                }
            }
            if (this.mRadioWrapper.getBgScanEnable()) {
                this.isDoubleTunerSeeking = true;
                this.mRadioWrapper.handler.removeCallbacks(this.doubleTunerSeekingCancelRunnable);
                this.mRadioWrapper.handler.postDelayed(this.doubleTunerSeekingCancelRunnable, 2000L);
            } else {
                this.mRadioWrapper.isSeekingAbort = false;
            }
            if (this.mProgramList != null) {
                this.mProgramList.removeOnCompleteListener(this.onCompleteListener);
                this.mProgramList.unregisterListCallback(this.listCallback);
            }
            ProgramList dynamicProgramList = this.mRadioTuner.getDynamicProgramList(filter);
            this.mProgramList = dynamicProgramList;
            if (dynamicProgramList != null) {
                dynamicProgramList.addOnCompleteListener(this.onCompleteListener);
                this.mProgramList.registerListCallback(this.listCallback);
                LogUtil.d(TAG, "updateProgramList finish safety... ");
                return;
            }
            LogUtil.d(TAG, "updateProgramList finish with mProgramList == null. ");
        } catch (Throwable th) {
            LogUtil.e(TAG, "updateProgramList fatal boom. 1 ");
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.byd.music.service.RadioService$7, reason: invalid class name */
    class AnonymousClass7 extends RadioTuner.Callback {
        AnonymousClass7() {
        }

        @Override // android.hardware.radio.RadioTuner.Callback
        public void onTrafficAnnouncement(boolean z) {
            LogUtil.e(RadioService.TAG, "onTrafficAnnouncement: enter active = " + z);
        }

        @Override // android.hardware.radio.RadioTuner.Callback
        public void onEmergencyAnnouncement(boolean z) {
            LogUtil.e(RadioService.TAG, "onEmergencyAnnouncement: enter active = " + z);
        }

        @Override // android.hardware.radio.RadioTuner.Callback
        public void onProgramListChanged() {
            super.onProgramListChanged();
            LogUtil.d(RadioService.TAG, "onProgramListChanged: onProgramListUpdated onProgramListUpdated");
        }

        @Override // android.hardware.radio.RadioTuner.Callback
        public void onProgramInfoChanged(RadioManager.ProgramInfo programInfo) {
            LogUtil.d(RadioService.TAG, "RadioTuner.Callback onProgramInfoChanged: enter with info = " + programInfo.toString());
            synchronized (RadioService.this.mLock) {
                RadioService.this.mCurrentProgram = programInfo;
                try {
                    Iterator it = RadioService.this.mRadioAppCallbacks.iterator();
                    while (it.hasNext()) {
                        ((IRadioAppCallback) it.next()).onCurrentProgramChanged(programInfo);
                    }
                    if (RadioServiceWrapper.isDabProgram(programInfo) && RadioServiceWrapper.getDabSID(programInfo) != RadioService.this.mDabEpgSid) {
                        if (RadioServiceWrapper.getDabSID(programInfo) <= 0) {
                            RadioService.this.mCurrentEpgList.update(Collections.emptyList());
                            RadioService.this.mDabEpgSid = RadioServiceWrapper.getDabSID(programInfo);
                        } else if (RadioService.this.mDabEpgSid != RadioServiceWrapper.getDabSID(programInfo) && !programInfo.isMuted()) {
                            RadioService.this.mCurrentEpgList.update(Collections.emptyList());
                            RadioService.this.mDabEpgSid = RadioServiceWrapper.getDabSID(programInfo);
                            RadioService.this.getDabEpg();
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            if (RadioService.this.firstScanAlready || !RadioService.this.mRadioWrapper.getBgScanEnable()) {
                return;
            }
            RadioService.this.firstScanAlready = true;
            LogUtil.d(RadioService.TAG, "RadioTuner.Callback onProgramInfoChanged: updateProgramList with null");
            RadioService.this.mRadioWrapper.updateAllSourceProgramList();
        }

        @Override // android.hardware.radio.RadioTuner.Callback
        public void onError(int i) {
            LogUtil.d(RadioService.TAG, "RadioTuner.Callback onError: enter...");
            if (i == 0 || i == 1) {
                LogUtil.e(RadioService.TAG, "Fatal hardware error: " + i);
                RadioService.this.onHardwareError();
                return;
            }
            LogUtil.w(RadioService.TAG, "Hardware error: " + i);
        }

        @Override // android.hardware.radio.RadioTuner.Callback
        public void onControlChanged(boolean z) {
            LogUtil.d(RadioService.TAG, "RadioTuner.Callback onControlChanged: enter...");
            if (z) {
                return;
            }
            RadioService.this.onHardwareError();
        }

        @Override // android.hardware.radio.RadioTuner.Callback
        public void onParametersUpdated(Map<String, String> map) {
            LogUtil.d(RadioService.TAG, "onParametersUpdated = " + MapUtils.toString(map));
            if (RadioService.this.mRadioWrapper.getEPGEnable() && !TextUtils.isEmpty(map.get("com.bosch.dab.epg.schedule"))) {
                try {
                    long jLongValue = Long.valueOf(String.valueOf(((JSONObject) new JSONObject(map.get("com.bosch.dab.epg.schedule")).get(ZenModeConfig.SCHEDULE_PATH)).get("serviceId"))).longValue();
                    if (jLongValue != RadioService.this.mRadioWrapper.mCurrentRadio.getValue().getDAB_SID()) {
                        LogUtil.d(RadioService.TAG, "epg data too late, epgId = " + jLongValue + ", current service id = " + RadioService.this.mRadioWrapper.mCurrentRadio.getValue().getDAB_SID());
                        return;
                    }
                    JSONArray jSONArray = (JSONArray) ((JSONObject) new JSONObject(map.get("com.bosch.dab.epg.schedule")).get(ZenModeConfig.SCHEDULE_PATH)).get("program");
                    LogUtil.d(RadioService.TAG, "epg data ready, epg sid  = " + jLongValue + ", epg list size = " + jSONArray.length());
                    ArrayList arrayList = new ArrayList();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    int i = 0;
                    while (i < jSONArray.length()) {
                        int i2 = i;
                        long j = jCurrentTimeMillis;
                        long j2 = jLongValue;
                        RadioEpgDBBean radioEpgDBBean = new RadioEpgDBBean(jLongValue, Long.valueOf(String.valueOf(((JSONObject) jSONArray.get(i)).get("startTime"))).longValue(), Long.valueOf(String.valueOf(((JSONObject) jSONArray.get(i)).get("stopTime"))).longValue(), String.valueOf(TextUtils.isEmpty(String.valueOf(((JSONObject) jSONArray.get(i)).get("longDescription"))) ? ((JSONObject) jSONArray.get(i)).get("mediumDescription") : ((JSONObject) jSONArray.get(i)).get("longDescription")));
                        if (radioEpgDBBean.getEpgStartTime() * 1000 <= j) {
                            LogUtil.d(RadioService.TAG, "epg data ready, but invalid. getEpgEndTime = " + radioEpgDBBean.getEpgEndTime() + ", currentTimeMillis = " + j);
                        } else {
                            RadioEpgDBBean epgSubscribeBean = RadioServiceWrapper.getInstance().getEpgSubscribeBean(radioEpgDBBean);
                            if (epgSubscribeBean != null) {
                                arrayList.add(epgSubscribeBean);
                            } else {
                                arrayList.add(radioEpgDBBean);
                            }
                        }
                        jCurrentTimeMillis = j;
                        i = i2 + 1;
                        jLongValue = j2;
                    }
                    arrayList.sort(new Comparator() { // from class: com.byd.music.service.-$$Lambda$RadioService$7$Y2kngV9fY_Z7AO_s0gP-ZZeMTqM
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            return RadioService.AnonymousClass7.lambda$onParametersUpdated$0((RadioEpgDBBean) obj, (RadioEpgDBBean) obj2);
                        }
                    });
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        LogUtil.d(RadioService.TAG, "epg data looper = " + ((RadioEpgDBBean) it.next()));
                    }
                    RadioService.this.mCurrentEpgList.update(arrayList);
                    LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: epg schedule finish");
                } catch (Throwable th) {
                    LogUtil.e(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: epg schedule error with " + th.getMessage());
                    th.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(map.get("com.bosch.tuner_state"))) {
                String str = map.get("com.bosch.tuner_state");
                LogUtil.e(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: tuner_state value = " + str);
                if (str.equals("READY")) {
                    if (!RadioService.this.mRadioWrapper.isRadioHalReady) {
                        RadioService.this.mRadioWrapper.isRadioHalReady = true;
                        RadioService.this.latelySeekingInterruptForNotReady = 0L;
                        RadioService.this.mRadioManager.addAnnouncementListener();
                    }
                    final long jCurrentTimeMillis2 = System.currentTimeMillis();
                    RadioService.this.finalReadyTime[0] = jCurrentTimeMillis2;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.service.RadioService.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (RadioService.this.mRadioWrapper.isRadioHalReady) {
                                    LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: tuner_state go to tune with READY. currentMillis = " + jCurrentTimeMillis2 + ", finalReadyTime = " + RadioService.this.finalReadyTime[0]);
                                    if (jCurrentTimeMillis2 != RadioService.this.finalReadyTime[0]) {
                                        return;
                                    }
                                    if (RadioServiceWrapper.isDABBean(RadioService.this.mRadioWrapper.mCurrentRadio.getValue())) {
                                        RadioService.this.mRadioWrapper.tune(RadioServiceWrapper.generateDABSelector(RadioService.this.mRadioWrapper.mCurrentRadio.getValue()));
                                    } else {
                                        RadioService.this.mRadioWrapper.tune(RadioService.this.mRadioWrapper.mCurrentRadio.getValue().getRadioFrequency());
                                    }
                                    RadioService.this.mRadioTuner.setServiceFollow(true);
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    }, 500L);
                } else {
                    RadioService.this.latelyStartForBosch = 0L;
                    RadioService.this.mRadioWrapper.isRadioHalReady = false;
                }
            }
            if (!TextUtils.isEmpty(map.get("com.desay.tuner_init_state"))) {
                String str2 = map.get("com.desay.tuner_init_state");
                LogUtil.e(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: tuner_init_state value = " + str2);
                if (str2.equals("READY")) {
                    if (!RadioService.this.mRadioWrapper.isRadioHalReady) {
                        RadioService.this.mRadioWrapper.isRadioHalReady = true;
                        RadioService.this.latelySeekingInterruptForNotReady = 0L;
                        RadioService.this.mRadioManager.addAnnouncementListener();
                    }
                    final long jCurrentTimeMillis3 = System.currentTimeMillis();
                    RadioService.this.finalReadyTime[0] = jCurrentTimeMillis3;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.service.RadioService.7.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (RadioService.this.mRadioWrapper.isRadioHalReady) {
                                    LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: tuner_state go to tune with READY. currentMillis = " + jCurrentTimeMillis3 + ", finalReadyTime = " + RadioService.this.finalReadyTime[0]);
                                    if (jCurrentTimeMillis3 != RadioService.this.finalReadyTime[0]) {
                                        return;
                                    }
                                    if (RadioServiceWrapper.isDABBean(RadioService.this.mRadioWrapper.mCurrentRadio.getValue())) {
                                        RadioService.this.mRadioWrapper.tune(RadioServiceWrapper.generateDABSelector(RadioService.this.mRadioWrapper.mCurrentRadio.getValue()));
                                    } else {
                                        RadioService.this.mRadioWrapper.tune(RadioService.this.mRadioWrapper.mCurrentRadio.getValue().getRadioFrequency());
                                    }
                                    if (RadioServiceWrapper.getInstance().isPlaying.get()) {
                                        int i3 = OnLineMusic.musicEngine;
                                        if (i3 == 7 || i3 == 8 || i3 == 9) {
                                            AudioFocusManager.getInstance().audioManager.setParameters("handle_fm=1048578");
                                        }
                                    }
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    }, 500L);
                } else {
                    RadioService.this.mRadioWrapper.isRadioHalReady = false;
                }
            }
            if (RadioService.this.mRadioWrapper.isGetFiledStrength <= 0) {
                if (RadioService.this.mRadioWrapper.isGetDadBER > 0) {
                    if (!TextUtils.isEmpty(map.get("com.bosch.diag.operations"))) {
                        try {
                            boolean zBooleanValue = ((Boolean) new JSONObject(map.get("com.bosch.diag.operations")).get("testmode")).booleanValue();
                            RadioService.this.mRadioWrapper.isTestMode = zBooleanValue;
                            LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: testmode = " + zBooleanValue);
                            if (!zBooleanValue && RadioService.this.mRadioWrapper.DAB_BER >= 0) {
                                RadioService.this.mRadioWrapper.isGetDadBER = -1L;
                            }
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                    if (!TextUtils.isEmpty(map.get("com.bosch.diag.dab_netber"))) {
                        try {
                            JSONObject jSONObject = new JSONObject(map.get("com.bosch.diag.dab_netber"));
                            LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: dab_netber ber = " + jSONObject.get("ber"));
                            RadioService.this.mRadioWrapper.DAB_BER = ((Integer) jSONObject.get("ber")).intValue();
                            return;
                        } catch (JSONException e) {
                            LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: com.bosch.diag.dab_netber transform failed!!! ");
                            e.printStackTrace();
                            return;
                        }
                    }
                    if (TextUtils.isEmpty(map.get("com.desaysv.diag.operations"))) {
                        return;
                    }
                    try {
                        JSONObject jSONObject2 = new JSONObject(map.get("com.desaysv.diag.operations"));
                        LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: dab ber = " + jSONObject2.get("ber"));
                        RadioService.this.mRadioWrapper.DAB_BER = ((Integer) jSONObject2.get("ber")).intValue();
                        if (RadioService.this.mRadioWrapper.DAB_BER >= 0) {
                            RadioService.this.mRadioWrapper.isGetDadBER = -1L;
                            return;
                        }
                        return;
                    } catch (JSONException e2) {
                        LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: diag.operations ber transform failed!!! ");
                        e2.printStackTrace();
                        return;
                    }
                }
                return;
            }
            if (!TextUtils.isEmpty(map.get("com.bosch.diag.operations"))) {
                try {
                    JSONObject jSONObject3 = new JSONObject(map.get("com.bosch.diag.operations"));
                    LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: FieldStrength = " + jSONObject3.get("FieldStrength"));
                    LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: Frequency = " + jSONObject3.get("Frequency"));
                    LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: TunerID = " + jSONObject3.get("TunerID"));
                    if (((Integer) jSONObject3.get("TunerID")).intValue() == 1) {
                        RadioService.this.mRadioWrapper.ID_ONE_STRENGTH = ((Integer) jSONObject3.get("FieldStrength")).intValue();
                    } else if (((Integer) jSONObject3.get("TunerID")).intValue() == 2) {
                        RadioService.this.mRadioWrapper.ID_TWO_STRENGTH = ((Integer) jSONObject3.get("FieldStrength")).intValue();
                    }
                    if (RadioService.this.mRadioWrapper.ID_TWO_STRENGTH < 0 || RadioService.this.mRadioWrapper.ID_ONE_STRENGTH < 0) {
                        return;
                    }
                    RadioService.this.mRadioWrapper.isGetFiledStrength = -1L;
                    return;
                } catch (JSONException e3) {
                    LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: com.bosch.diag.operations transform failed!!! ");
                    e3.printStackTrace();
                    return;
                }
            }
            if (TextUtils.isEmpty(map.get("com.desaysv.diag.operations"))) {
                return;
            }
            try {
                JSONObject jSONObject4 = new JSONObject(map.get("com.desaysv.diag.operations"));
                LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: FieldStrength = " + jSONObject4.get("FieldStrength"));
                LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: TunerID = " + jSONObject4.get("TunerID"));
                if (((Integer) jSONObject4.get("TunerID")).intValue() == 1) {
                    RadioService.this.mRadioWrapper.ID_ONE_STRENGTH = ((Integer) jSONObject4.get("FieldStrength")).intValue();
                } else if (((Integer) jSONObject4.get("TunerID")).intValue() == 2) {
                    RadioService.this.mRadioWrapper.ID_TWO_STRENGTH = ((Integer) jSONObject4.get("FieldStrength")).intValue();
                }
                if (RadioService.this.mRadioWrapper.ID_TWO_STRENGTH < 0 || RadioService.this.mRadioWrapper.ID_ONE_STRENGTH < 0) {
                    return;
                }
                RadioService.this.mRadioWrapper.isGetFiledStrength = -1L;
            } catch (JSONException e4) {
                LogUtil.d(RadioService.TAG, "RadioTuner.Callback onParametersUpdated: diag.operations transform failed!!! ");
                e4.printStackTrace();
            }
        }

        static /* synthetic */ int lambda$onParametersUpdated$0(RadioEpgDBBean radioEpgDBBean, RadioEpgDBBean radioEpgDBBean2) {
            return (int) (radioEpgDBBean.getEpgStartTime() - radioEpgDBBean2.getEpgStartTime());
        }
    }

    public boolean queryRadioHalReady() {
        try {
            LogUtil.d(TAG, "queryRadioHalReady enter");
            ArrayList arrayList = new ArrayList();
            arrayList.add("com.desay.tuner_init_state");
            Map<String, String> parameters = this.mRadioTuner.mTuner.getParameters(arrayList);
            if (parameters.get("com.desay.tuner_init_state") != null) {
                return "READY".equals(parameters.get("com.desay.tuner_init_state"));
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void setRadioSTR(final boolean z) {
        this.latelySeekingInterruptForNotReady = 0L;
        if (OnLineMusic.VENDOR_CODE == 2) {
            if (z || !this.mRadioWrapper.isRadioHalReady) {
                return;
            }
            this.mRadioWrapper.isRadioHalReady = false;
            reopenSession(true);
            return;
        }
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioService$VGDo5zgXZ8mbeW6iMp2BCsDFsmw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setRadioSTR$0$RadioService(z);
            }
        });
    }

    public /* synthetic */ void lambda$setRadioSTR$0$RadioService(boolean z) {
        try {
            LogUtil.e(TAG, "setRadioSTR enter with isEnterSuspend = " + z + ", latelyStartForBosch = " + this.latelyStartForBosch);
            if (z) {
                this.latelyStartForBosch = 0L;
            } else if (System.currentTimeMillis() - this.latelyStartForBosch < TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS) {
                return;
            } else {
                this.latelyStartForBosch = System.currentTimeMillis();
            }
            HashMap map = new HashMap();
            map.put("com.bosch.sbr_state", z ? "STOP" : "START");
            this.mRadioTuner.mTuner.setParameters(map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void getDabEpg() {
        if (this.mRadioWrapper.getEPGEnable()) {
            AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioService$KUoo9wdjitbyU45u3Jn5ls843wc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$getDabEpg$1$RadioService();
                }
            });
        }
    }

    public /* synthetic */ void lambda$getDabEpg$1$RadioService() {
        try {
            LogUtil.d(TAG, "getDabEpg enter");
            HashMap map = new HashMap();
            if (!this.alreadySetEpgEnable) {
                map.put("com.bosch.dab.epg.enable", "1");
                this.mRadioTuner.mTuner.setParameters(map);
                LogUtil.d(TAG, "getDabEpg epg.enable finish");
                map.clear();
                this.alreadySetEpgEnable = true;
            }
            map.put("com.bosch.dab.getEPGSchedule", "10");
            this.mRadioTuner.mTuner.setParameters(map);
            LogUtil.d(TAG, "getDabEpg setEPGSchedule finish");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void getFieldStrength() {
        if (OnLineMusic.VENDOR_CODE == 2) {
            getFieldStrengthForDesay();
        } else {
            getFieldStrengthForBosch();
        }
    }

    private void getFieldStrengthForDesay() {
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioService$YoWqCNTWmIcI-5LqMIYQJaCNHJA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$getFieldStrengthForDesay$2$RadioService();
            }
        });
    }

    public /* synthetic */ void lambda$getFieldStrengthForDesay$2$RadioService() {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("selectedOperation", 3);
            jSONObject.put("TunerID", 2);
            jSONObject.put("Frequency", this.mCurrentProgram.getSelector().getPrimaryId().getValue());
            LogUtil.d(TAG, "getFieldStrength: value = " + jSONObject.toString());
            map.put("com.desaysv.diag.operations", jSONObject.toString());
            this.mRadioTuner.mTuner.setParameters(map);
            Thread.sleep(1000L);
            while (this.mRadioWrapper.ID_TWO_STRENGTH == -1) {
                Thread.sleep(200L);
            }
            jSONObject.put("selectedOperation", 3);
            jSONObject.put("TunerID", 1);
            jSONObject.put("Frequency", this.mCurrentProgram.getSelector().getPrimaryId().getValue());
            LogUtil.d(TAG, "getFieldStrength: value = " + jSONObject.toString());
            map.put("com.desaysv.diag.operations", jSONObject.toString());
            this.mRadioTuner.mTuner.setParameters(map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void getFieldStrengthForBosch() {
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioService$AjxDed1kJ8C7NGxnsXt-QC8hW9A
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$getFieldStrengthForBosch$3$RadioService();
            }
        });
    }

    public /* synthetic */ void lambda$getFieldStrengthForBosch$3$RadioService() {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("selectedOperation", 3);
            jSONObject.put("TunerID", 2);
            int radioFreq = RadioServiceWrapper.getRadioFreq(this.mCurrentProgram);
            jSONObject.put("Frequency", radioFreq);
            LogUtil.d(TAG, "getFieldStrength: value = " + jSONObject.toString());
            map.put("com.bosch.diag.operations", jSONObject.toString());
            this.mRadioTuner.mTuner.setParameters(map);
            Thread.sleep(1000L);
            while (this.mRadioWrapper.ID_TWO_STRENGTH == -1) {
                Thread.sleep(200L);
            }
            jSONObject.put("selectedOperation", 3);
            jSONObject.put("TunerID", 1);
            jSONObject.put("Frequency", radioFreq);
            LogUtil.d(TAG, "getFieldStrength: value = " + jSONObject.toString());
            map.put("com.bosch.diag.operations", jSONObject.toString());
            this.mRadioTuner.mTuner.setParameters(map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void getDabBER() {
        if (OnLineMusic.VENDOR_CODE == 2) {
            getDabBERForDesay();
        } else {
            getDabBERForBosch();
        }
    }

    private void getDabBERForDesay() {
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioService$O1ZoLenwLUmq-tPXi9cegsuuNv0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$getDabBERForDesay$4$RadioService();
            }
        });
    }

    public /* synthetic */ void lambda$getDabBERForDesay$4$RadioService() {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("selectedOperation", 1);
            map.put("com.desaysv.diag.operations", jSONObject.toString());
            this.mRadioTuner.mTuner.setParameters(map);
            LogUtil.d(TAG, "getDabBER: set dab_netber finish");
            while (this.mRadioWrapper.DAB_BER < 0) {
                Thread.sleep(500L);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void getDabBERForBosch() {
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioService$jZ29RehJR9DroUSiDMIc-Ipuhvk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$getDabBERForBosch$5$RadioService();
            }
        });
    }

    public /* synthetic */ void lambda$getDabBERForBosch$5$RadioService() {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("measuremode", false);
            jSONObject.put("selectedOperation", 1);
            jSONObject.put("selectedTunerFrontend", 0);
            jSONObject.put("testmode", true);
            map.put("com.bosch.diag.operations", jSONObject.toString());
            this.mRadioTuner.mTuner.setParameters(map);
            LogUtil.d(TAG, "Radioservice getDabBER set testmode true");
            Thread.sleep(1000L);
            while (!this.mRadioWrapper.isTestMode) {
                Thread.sleep(500L);
            }
            HashMap map2 = new HashMap();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ber", -1);
            jSONObject2.put("nob", 1000000);
            map2.put("com.bosch.diag.dab_netber", jSONObject2.toString());
            this.mRadioTuner.mTuner.setParameters(map2);
            LogUtil.d(TAG, "Radioservice getDabBER set dab_netber finish");
            while (this.mRadioWrapper.DAB_BER < 0) {
                Thread.sleep(500L);
            }
            HashMap map3 = new HashMap();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("measuremode", false);
            jSONObject3.put("selectedOperation", 1);
            jSONObject3.put("selectedTunerFrontend", 0);
            jSONObject3.put("testmode", false);
            map3.put("com.bosch.diag.operations", jSONObject3.toString());
            this.mRadioTuner.mTuner.setParameters(map3);
            LogUtil.d(TAG, "Radioservice getDabBER set testmode false");
        } catch (Throwable th) {
            HashMap map4 = new HashMap();
            JSONObject jSONObject4 = new JSONObject();
            try {
                jSONObject4.put("testmode", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            map4.put("com.bosch.diag.operations", jSONObject4.toString());
            this.mRadioTuner.mTuner.setParameters(map4);
            LogUtil.d(TAG, "Radioservice getDabBER set testmode false in throwable");
            th.printStackTrace();
        }
    }

    public void setServiceFollowForDeasy(final boolean z) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.service.-$$Lambda$RadioService$5acEhu3jTQJ6736ldl3VPZEbnTA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setServiceFollowForDeasy$6$RadioService(z);
            }
        });
    }

    public /* synthetic */ void lambda$setServiceFollowForDeasy$6$RadioService(boolean z) {
        try {
            LogUtil.d(TAG, "setServiceFollowForDeasy enter");
            HashMap map = new HashMap();
            map.put("com.desaysv.dab_service_following", z ? "1" : AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
            this.mRadioTuner.mTuner.setParameters(map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
