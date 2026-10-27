package com.android.systemui.statusbar.policy;

import android.car.Car;
import android.car.media.CarAudioManager;
import android.car.media.CarEnhancedAudioManager;
import android.content.Context;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.media.AudioAttributes;
import android.media.AudioFocusInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.VolumeController;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class VolumeControllerImpl extends BYDCarServiceBase implements VolumeController, Dumpable {
    public static final int BYD_MUTE = 1;
    public static final int BYD_MUTE_NAVI = 2;
    public static final int BYD_STREAM_BROADCAST = 25;
    public static final int BYD_STREAM_BROADCAST_MAX = 39;
    public static final int BYD_STREAM_BROADCAST_MIN = 5;
    public static final int BYD_STREAM_BROADCAST_USAGE = 18;
    public static final int BYD_STREAM_MEDIA_USAGE = 1;
    public static final int BYD_STREAM_MUSIC = 3;
    public static final int BYD_STREAM_MUSIC_MAX = 39;
    public static final int BYD_STREAM_MUSIC_MIN = 0;
    public static final int BYD_STREAM_NAVI = 14;
    public static final int BYD_STREAM_NAVI_MAX = 10;
    public static final int BYD_STREAM_NAVI_MIN = 0;
    public static final int BYD_STREAM_NAVI_USAGE = 12;
    public static final int BYD_STREAM_PHONE = 21;
    public static final int BYD_STREAM_PHONE_MAX = 39;
    public static final int BYD_STREAM_PHONE_MIN = 5;
    public static final int BYD_STREAM_PHONE_USAGE = 2;
    public static final int BYD_STREAM_RINGTONE = 6;
    public static final int BYD_STREAM_RINGTONE_MAX = 39;
    public static final int BYD_STREAM_RINGTONE_MIN = 0;
    public static final int BYD_STREAM_RINGTONE_USAGE = 6;
    public static final int BYD_STREAM_VOICE = 16;
    public static final int BYD_STREAM_VOICE_MAX = 24;
    public static final int BYD_STREAM_VOICE_MIN = 5;
    public static final int BYD_STREAM_VOICE_USAGE = 16;
    private static final String TAG = "VolumeControllerImpl";
    private int INVALID_GROUP_ID;
    private BYDAutoAudioDevice mBYDAutoAudioDevice;
    private BydAutoAudioListener mBydAudioistener;
    protected C mCallbacks;
    private Car mCar;
    private CarAudioManager mCarAudioManager;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private BydCarGroupMutingCallback mCarGroupMutingCallback;
    private BydCarVolumeCallback mCarVolumeCallback;
    private boolean mDestroyed;
    private boolean mIsConnected;
    private final VolumeController.State mState;
    protected StatusBar mStatusBar;
    private final W mWorker;
    private final HandlerThread mWorkerThread;

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public VolumeControllerImpl(Context context) {
        super(context);
        this.mCallbacks = new C();
        this.mState = new VolumeController.State();
        this.INVALID_GROUP_ID = -2;
        Log.i(TAG, "VolumeControllerImpl");
        HandlerThread handlerThread = new HandlerThread(VolumeControllerImpl.class.getSimpleName());
        this.mWorkerThread = handlerThread;
        handlerThread.start();
        this.mWorker = new W(handlerThread.getLooper());
        updateStatusBar();
        initBydAutoAudio();
    }

    private void initBydAutoAudio() {
        streamStateW(3).levelMin = 0;
        streamStateW(3).levelMax = 39;
        streamStateW(14).levelMin = 0;
        streamStateW(14).levelMax = 10;
        streamStateW(16).levelMin = 5;
        streamStateW(16).levelMax = 24;
        streamStateW(21).levelMin = 5;
        streamStateW(21).levelMax = 39;
        streamStateW(25).levelMin = 5;
        streamStateW(25).levelMax = 39;
        streamStateW(6).levelMin = 0;
        streamStateW(6).levelMax = 39;
    }

    @Override // com.android.systemui.statusbar.policy.BYDCarServiceBase
    protected void onServiceDied() {
        String str = TAG;
        BydLogHelper.logE(str, "ServiceDied");
        if (!this.mIsConnected) {
            BydLogHelper.logD(str, "service already disconnected");
            return;
        }
        this.mIsConnected = false;
        this.mCarAudioManager = null;
        this.mCarEnhancedAudioManager = null;
        this.mCarVolumeCallback = null;
        this.mCarGroupMutingCallback = null;
    }

    @Override // com.android.systemui.statusbar.policy.BYDCarServiceBase
    protected void onServiceConnected(Car car) {
        String str = TAG;
        Log.i(str, "onServiceConnected");
        if (this.mIsConnected) {
            BydLogHelper.logD(str, "Service already connected");
            return;
        }
        this.mIsConnected = true;
        this.mCar = car;
        this.mCarAudioManager = (CarAudioManager) car.getCarManager("audio");
        CarEnhancedAudioManager carEnhancedAudioManager = (CarEnhancedAudioManager) this.mCar.getCarManager("enhanced_audio");
        this.mCarEnhancedAudioManager = carEnhancedAudioManager;
        if (this.mCarAudioManager == null) {
            Log.i(str, "mCarAudioManager is null");
            return;
        }
        if (carEnhancedAudioManager == null) {
            Log.i(str, "mCarEnhancedAudioManager is null");
        }
        this.mCarVolumeCallback = new BydCarVolumeCallback();
        this.mCarGroupMutingCallback = new BydCarGroupMutingCallback();
        this.mCarAudioManager.registerCarVolumeCallback(this.mCarVolumeCallback);
        this.mCarEnhancedAudioManager.registerCarGroupMutingCallback(this.mCarGroupMutingCallback);
        this.mBYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(this.mContext);
        BydAutoAudioListener bydAutoAudioListener = new BydAutoAudioListener();
        this.mBydAudioistener = bydAutoAudioListener;
        this.mBYDAutoAudioDevice.registerListener((AbsBYDAutoAudioListener) bydAutoAudioListener);
        onGetStateWByd();
    }

    public void destroy() {
        BydLogHelper.logD("destroy");
        if (this.mDestroyed) {
            return;
        }
        this.mDestroyed = true;
        this.mWorkerThread.quitSafely();
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void addCallback(VolumeController.Callbacks callback, Handler handler) {
        this.mCallbacks.add(callback, handler);
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void removeCallback(VolumeController.Callbacks callback) {
        this.mCallbacks.remove(callback);
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void getState() {
        if (this.mDestroyed) {
            return;
        }
        this.mWorker.sendEmptyMessage(3);
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getNaviVolume() {
        return getGroupVolume(getVolumeGroupIdForUsage(12));
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getMediaVolume() {
        return getGroupVolume(getVolumeGroupIdForUsage(1));
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getVoiceVolume() {
        int groupVolume = getGroupVolume(getVolumeGroupIdForUsage(16));
        BydLogHelper.logD("getVoiceVolume  voiceVol:" + groupVolume);
        return groupVolume;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public VolumeController.State getVolumeState() {
        return this.mState;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void setStreamMute(int i, boolean z) {
        if (this.mDestroyed) {
            return;
        }
        this.mWorker.obtainMessage(7, i, z ? 1 : 0).sendToTarget();
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void setStreamVolume(int stream, int level) {
        BydLogHelper.logD("stream = " + stream + " ,level = " + level + " ,mDestroyed = " + this.mDestroyed);
        if (this.mDestroyed) {
            return;
        }
        this.mWorker.obtainMessage(10, stream, level).sendToTarget();
    }

    private void updateStatusBar() {
        if (this.mStatusBar == null) {
            this.mStatusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        }
    }

    private boolean shouldShowUIByd(int stream, int flags) {
        updateStatusBar();
        if ((stream == 3 || stream == 14 || stream == 16 || stream == 21 || stream == 25 || stream == 6) && (flags & 1) != 0) {
            return true;
        }
        BydLogHelper.logD("stream = " + stream + " ,flags = " + flags);
        return false;
    }

    private boolean updateActiveStreamW(int activeStream) {
        if (activeStream == this.mState.activeStream) {
            return false;
        }
        this.mState.activeStream = activeStream;
        return true;
    }

    private VolumeController.StreamState streamStateW(int stream) {
        VolumeController.StreamState streamState = this.mState.states.get(stream);
        if (streamState != null) {
            return streamState;
        }
        VolumeController.StreamState streamState2 = new VolumeController.StreamState();
        this.mState.states.put(stream, streamState2);
        return streamState2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGetStateWByd() {
        if (!this.mIsConnected) {
            BydLogHelper.logD("car audio not connect");
            return;
        }
        if (this.mCarAudioManager != null && this.mCarEnhancedAudioManager != null) {
            int groupVolume = getGroupVolume(getVolumeGroupIdForUsage(1));
            int groupVolume2 = getGroupVolume(getVolumeGroupIdForUsage(12));
            int groupVolume3 = getGroupVolume(getVolumeGroupIdForUsage(16));
            int groupVolume4 = getGroupVolume(getVolumeGroupIdForUsage(2));
            String str = TAG;
            BydLogHelper.logD(str, "mediaVol:" + groupVolume + " , naviVol:" + groupVolume2 + " , voiceAssistVol:" + groupVolume3 + " , phoneVol:" + groupVolume4);
            updateStreamLevelW(3, groupVolume);
            updateStreamLevelW(14, groupVolume2);
            updateStreamLevelW(16, groupVolume3);
            updateStreamLevelW(21, groupVolume4);
            boolean zIsVolumeGroupMuted = isVolumeGroupMuted(0, getVolumeGroupIdForUsage(1));
            boolean zIsVolumeGroupMuted2 = isVolumeGroupMuted(0, getVolumeGroupIdForUsage(12));
            BydLogHelper.logD(str, "mediaMute:" + zIsVolumeGroupMuted + " , naviMute:" + zIsVolumeGroupMuted2);
            updateStreamMuteW(3, zIsVolumeGroupMuted);
            updateStreamMuteW(14, zIsVolumeGroupMuted2);
            int groupVolume5 = getGroupVolume(getVolumeGroupIdForUsage(18));
            BydLogHelper.logD(str, "broadcastVol:" + groupVolume5);
            updateStreamLevelW(25, groupVolume5);
            BydLogHelper.logD(str, "ringtoneVol:" + getGroupVolume(getVolumeGroupIdForUsage(6)));
            updateStreamLevelW(6, groupVolume5);
        }
        this.mCallbacks.onStateChanged(false, this.mState, false, 0);
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public boolean getStreamMute(int stream) {
        if (stream == 3) {
            return isVolumeGroupMuted(0, getVolumeGroupIdForUsage(1));
        }
        if (stream == 14) {
            return isVolumeGroupMuted(0, getVolumeGroupIdForUsage(12));
        }
        return false;
    }

    private boolean updateStreamLevelW(int stream, int level) {
        VolumeController.StreamState streamStateStreamStateW = streamStateW(stream);
        if (streamStateStreamStateW.level == level) {
            return false;
        }
        streamStateStreamStateW.level = level;
        return true;
    }

    private boolean updateStreamMuteW(int stream, boolean muted) {
        VolumeController.StreamState streamStateStreamStateW = streamStateW(stream);
        if (streamStateStreamStateW.muted == muted) {
            return false;
        }
        streamStateStreamStateW.muted = muted;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSetStreamMuteWByd(int stream, boolean mute) {
        BydLogHelper.logD("onSetStreamMuteW--stream:" + stream + ",mute:" + mute);
        if (stream == 3) {
            setVolumeGroupMute(0, getVolumeGroupIdForUsage(1), mute, 0);
        } else if (stream == 14) {
            setVolumeGroupMute(0, getVolumeGroupIdForUsage(12), mute, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSetStreamVolumeWByd(int stream, int level) {
        BydLogHelper.logW(TAG, "onSetStreamVolume " + stream + " level=" + level);
        try {
            if (stream == 3) {
                setGroupVolume(getVolumeGroupIdForUsage(1), level, 0);
            } else if (stream != 14) {
            } else {
                setGroupVolume(getVolumeGroupIdForUsage(12), level, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSetActiveStreamW(int stream) {
        if (updateActiveStreamW(stream)) {
            this.mCallbacks.onStateChanged(false, this.mState, false, 0);
        }
    }

    private final class W extends Handler {
        private static final int BYD_VOLUME_CHANGE = 100;
        private static final int BYD_VOLUME_CHANGE_MUTE = 200;
        private static final int GET_STATE = 3;
        private static final int SET_ACTIVE_STREAM = 11;
        private static final int SET_STREAM_MUTE = 7;
        private static final int SET_STREAM_VOLUME = 10;

        W(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BydLogHelper.logD("handleMessage what = " + msg.what);
            int i = msg.what;
            if (i == 3) {
                VolumeControllerImpl.this.onGetStateWByd();
                return;
            }
            if (i == 7) {
                VolumeControllerImpl.this.onSetStreamMuteWByd(msg.arg1, msg.arg2 != 0);
                return;
            }
            if (i == 100) {
                VolumeControllerImpl.this.onVolumeChangedWByd(msg.arg1, msg.arg2, ((Integer) msg.obj).intValue());
                return;
            }
            if (i == 200) {
                VolumeControllerImpl.this.onBydMuteStateChanged(msg.arg1, msg.arg2, ((Integer) msg.obj).intValue());
            } else if (i == 10) {
                VolumeControllerImpl.this.onSetStreamVolumeWByd(msg.arg1, msg.arg2);
            } else {
                if (i != 11) {
                    return;
                }
                VolumeControllerImpl.this.onSetActiveStreamW(msg.arg1);
            }
        }
    }

    class C implements VolumeController.Callbacks {
        private final ConcurrentHashMap<VolumeController.Callbacks, Handler> mCallbackMap = new ConcurrentHashMap<>();

        C() {
        }

        public void add(VolumeController.Callbacks callback, Handler handler) {
            if (callback == null || handler == null) {
                throw new IllegalArgumentException();
            }
            this.mCallbackMap.put(callback, handler);
        }

        public void remove(VolumeController.Callbacks callback) {
            this.mCallbackMap.remove(callback);
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void onShowRequested(final VolumeController.State state, final boolean isFromMute, final int flags) {
            final VolumeController.State stateCopy = state.copy();
            for (final Map.Entry<VolumeController.Callbacks, Handler> entry : this.mCallbackMap.entrySet()) {
                entry.getValue().post(new Runnable() { // from class: com.android.systemui.statusbar.policy.VolumeControllerImpl.C.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((VolumeController.Callbacks) entry.getKey()).onShowRequested(stateCopy, isFromMute, flags);
                    }
                });
            }
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void onStateChanged(final boolean showUI, final VolumeController.State state, final boolean isFromMute, final int flags) {
            final VolumeController.State stateCopy = state.copy();
            for (final Map.Entry<VolumeController.Callbacks, Handler> entry : this.mCallbackMap.entrySet()) {
                entry.getValue().post(new Runnable() { // from class: com.android.systemui.statusbar.policy.VolumeControllerImpl.C.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ((VolumeController.Callbacks) entry.getKey()).onStateChanged(showUI, stateCopy, isFromMute, flags);
                    }
                });
            }
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void voiceBarVisibleChange(final boolean show) {
            for (final Map.Entry<VolumeController.Callbacks, Handler> entry : this.mCallbackMap.entrySet()) {
                entry.getValue().post(new Runnable() { // from class: com.android.systemui.statusbar.policy.VolumeControllerImpl.C.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ((VolumeController.Callbacks) entry.getKey()).voiceBarVisibleChange(show);
                    }
                });
            }
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void onAllMuteState(final boolean isAllMute) {
            for (final Map.Entry<VolumeController.Callbacks, Handler> entry : this.mCallbackMap.entrySet()) {
                entry.getValue().post(new Runnable() { // from class: com.android.systemui.statusbar.policy.VolumeControllerImpl.C.4
                    @Override // java.lang.Runnable
                    public void run() {
                        ((VolumeController.Callbacks) entry.getKey()).onAllMuteState(isAllMute);
                    }
                });
            }
        }
    }

    private class BydAutoAudioListener extends AbsBYDAutoAudioListener {
        private BydAutoAudioListener() {
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onAudioFocusChanged(int zoneId, List<AudioFocusInfo> focusInfoList) {
            AudioAttributes attributes;
            if (focusInfoList == null || focusInfoList.size() <= 0) {
                VolumeControllerImpl.this.updateVoiceBarVisible(false);
                return;
            }
            for (AudioFocusInfo audioFocusInfo : focusInfoList) {
                if (audioFocusInfo != null && (attributes = audioFocusInfo.getAttributes()) != null) {
                    int usage = attributes.getUsage();
                    BydLogHelper.logD(VolumeControllerImpl.TAG, "onAudioFocusChanged", "currUsage：" + usage);
                    if (usage == 16) {
                        VolumeControllerImpl.this.updateVoiceBarVisible(true);
                        return;
                    }
                }
            }
            VolumeControllerImpl.this.updateVoiceBarVisible(false);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onAllMuteState(int state) {
            BydLogHelper.logD("onAllMuteState state:" + state);
            VolumeControllerImpl.this.updateAllMuteState(state == 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVoiceBarVisible(boolean show) {
        this.mCallbacks.voiceBarVisibleChange(show);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllMuteState(boolean isAllMute) {
        this.mCallbacks.onAllMuteState(isAllMute);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onVolumeChangedWByd(int stream, int level, int flags) {
        boolean zShouldShowUIByd = shouldShowUIByd(stream, flags);
        boolean zUpdateStreamLevelW = updateStreamLevelW(stream, level) | updateActiveStreamW(stream) | false;
        if (zShouldShowUIByd) {
            this.mCallbacks.onShowRequested(this.mState, false, flags);
        } else {
            BydLogHelper.logD("showUI is false");
        }
        if (zUpdateStreamLevelW) {
            this.mCallbacks.onStateChanged(zShouldShowUIByd, this.mState, false, flags);
        } else {
            BydLogHelper.logD("volume not change");
        }
        return zUpdateStreamLevelW;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onBydMuteStateChanged(int stream, int state, int flags) {
        boolean zShouldShowUIByd = shouldShowUIByd(stream, flags);
        boolean zUpdateStreamMuteW = updateStreamMuteW(stream, state == 1) | updateActiveStreamW(stream) | false;
        if (zShouldShowUIByd) {
            this.mCallbacks.onShowRequested(this.mState, true, flags);
        } else {
            BydLogHelper.logD("showUI is false");
        }
        if (zUpdateStreamMuteW) {
            this.mCallbacks.onStateChanged(zShouldShowUIByd, this.mState, true, flags);
        } else {
            BydLogHelper.logD("mute state not change");
        }
        return zUpdateStreamMuteW;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void setGroupVolume(int zoneId, int groupId, int index, int flags) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            try {
                carAudioManager.setGroupVolume(zoneId, groupId, index, flags);
            } catch (Exception e) {
                BydLogHelper.logE(e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void setAllMute(boolean mute) {
        BydLogHelper.logD(TAG, "mute:" + mute);
        try {
            BYDAutoAudioDevice bYDAutoAudioDevice = this.mBYDAutoAudioDevice;
            if (bYDAutoAudioDevice == null) {
                BydLogHelper.logD("mBYDAutoAudioDevice is null");
            } else {
                bYDAutoAudioDevice.setAllMute(mute ? 1 : 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public boolean getAllMute() {
        BYDAutoAudioDevice bYDAutoAudioDevice = this.mBYDAutoAudioDevice;
        if (bYDAutoAudioDevice == null) {
            BydLogHelper.logD("mBYDAutoAudioDevice is null");
            return false;
        }
        int allMute = bYDAutoAudioDevice.getAllMute();
        BydLogHelper.logD(TAG, "getAllMute state:" + allMute);
        return allMute == 1;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void setGroupVolume(int groupId, int index, int flags) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            try {
                carAudioManager.setGroupVolume(groupId, index, flags);
            } catch (Exception e) {
                BydLogHelper.logE(e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getGroupVolume(int groupId) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            return carAudioManager.getGroupVolume(groupId);
        }
        return 7;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getGroupVolume(int zoneId, int groupId) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            return carAudioManager.getGroupVolume(zoneId, groupId);
        }
        return 7;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getVolumeGroupIdForUsage(int usage) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            return carAudioManager.getVolumeGroupIdForUsage(usage);
        }
        return this.INVALID_GROUP_ID;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getVolumeGroupIdForUsage(int zoneId, int usage) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            return carAudioManager.getVolumeGroupIdForUsage(zoneId, usage);
        }
        return this.INVALID_GROUP_ID;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public List<AudioFocusInfo> getCurAudioFocusInfos() {
        BYDAutoAudioDevice bYDAutoAudioDevice = this.mBYDAutoAudioDevice;
        if (bYDAutoAudioDevice != null) {
            return bYDAutoAudioDevice.getCurAudioFocusInfo(0);
        }
        return null;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public int getVolumeGroupCount() {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            return carAudioManager.getVolumeGroupCount();
        }
        return 0;
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public void setVolumeGroupMute(int zoneId, int groupId, boolean mute, int flags) {
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null) {
            try {
                carEnhancedAudioManager.setVolumeGroupMute(zoneId, groupId, mute, flags);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.VolumeController
    public boolean isVolumeGroupMuted(int zoneId, int groupId) {
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null) {
            return carEnhancedAudioManager.isVolumeGroupMuted(zoneId, groupId);
        }
        return false;
    }

    private class BydCarVolumeCallback extends CarAudioManager.CarVolumeCallback {
        private BydCarVolumeCallback() {
        }

        @Override // android.car.media.CarAudioManager.CarVolumeCallback
        public void onGroupVolumeChanged(int zoneId, int groupId, int flags) {
            super.onGroupVolumeChanged(zoneId, groupId, flags);
            BydLogHelper.logD("zoneId:" + zoneId + ",groupId:" + groupId + ",flags:" + flags);
            if (VolumeControllerImpl.this.mCarAudioManager != null) {
                for (int i : VolumeControllerImpl.this.mCarAudioManager.getUsagesForVolumeGroupId(groupId)) {
                    Integer numValueOf = Integer.valueOf(i);
                    BydLogHelper.logD("usage:" + numValueOf);
                    int i2 = 6;
                    if (numValueOf.intValue() == 16) {
                        i2 = 16;
                    } else if (numValueOf.intValue() == 1) {
                        i2 = 3;
                    } else if (numValueOf.intValue() == 12) {
                        i2 = 14;
                    } else if (numValueOf.intValue() == 2) {
                        i2 = 21;
                    } else if (numValueOf.intValue() == 18) {
                        i2 = 25;
                    } else if (numValueOf.intValue() != 6) {
                        i2 = -1;
                    }
                    if (i2 != -1) {
                        int groupVolume = VolumeControllerImpl.this.getGroupVolume(groupId);
                        BydLogHelper.logD("level:" + groupVolume);
                        VolumeControllerImpl.this.mWorker.obtainMessage(100, i2, groupVolume, Integer.valueOf(flags)).sendToTarget();
                    }
                }
                return;
            }
            BydLogHelper.logD("mCarAudioManager is null");
        }
    }

    private class BydCarGroupMutingCallback extends CarEnhancedAudioManager.CarGroupMutingCallback {
        private BydCarGroupMutingCallback() {
        }

        public void onGroupMuteChanged(int i, int i2, int i3) {
            super.onGroupMuteChanged(i, i2, i3);
            BydLogHelper.logD("zoneId:" + i + ",groupId:" + i2 + ",flags:" + i3);
            if (VolumeControllerImpl.this.mCarAudioManager != null) {
                for (int i4 : VolumeControllerImpl.this.mCarAudioManager.getUsagesForVolumeGroupId(i2)) {
                    Integer numValueOf = Integer.valueOf(i4);
                    BydLogHelper.logD("usage:" + numValueOf);
                    int i5 = 6;
                    if (numValueOf.intValue() == 16) {
                        i5 = 16;
                    } else if (numValueOf.intValue() == 1) {
                        i5 = 3;
                    } else if (numValueOf.intValue() == 12) {
                        i5 = 14;
                    } else if (numValueOf.intValue() == 2) {
                        i5 = 21;
                    } else if (numValueOf.intValue() == 18) {
                        i5 = 25;
                    } else if (numValueOf.intValue() != 6) {
                        i5 = -1;
                    }
                    if (i5 != -1) {
                        boolean zIsVolumeGroupMuted = VolumeControllerImpl.this.isVolumeGroupMuted(0, i2);
                        BydLogHelper.logD("muted:" + zIsVolumeGroupMuted);
                        VolumeControllerImpl.this.mWorker.obtainMessage(200, i5, zIsVolumeGroupMuted ? 1 : 0, Integer.valueOf(i3)).sendToTarget();
                    }
                }
                return;
            }
            BydLogHelper.logD("mCarAudioManager is null");
        }
    }
}
