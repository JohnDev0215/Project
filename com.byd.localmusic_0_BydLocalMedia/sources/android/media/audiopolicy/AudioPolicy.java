package android.media.audiopolicy;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFocusInfo;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.IAudioService;
import android.media.audiopolicy.IAudioPolicyCallback;
import android.media.projection.MediaProjection;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Slog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class AudioPolicy {
    private static final boolean DEBUG = false;
    public static final int FOCUS_POLICY_DUCKING_DEFAULT = 0;
    public static final int FOCUS_POLICY_DUCKING_IN_APP = 0;
    public static final int FOCUS_POLICY_DUCKING_IN_POLICY = 1;
    private static final int MSG_FOCUS_ABANDON = 5;
    private static final int MSG_FOCUS_GRANT = 1;
    private static final int MSG_FOCUS_LOSS = 2;
    private static final int MSG_FOCUS_REQUEST = 4;
    private static final int MSG_MIX_STATE_UPDATE = 3;
    private static final int MSG_POLICY_STATUS_CHANGE = 0;
    private static final int MSG_VOL_ADJUST = 6;
    public static final int POLICY_STATUS_REGISTERED = 2;
    public static final int POLICY_STATUS_UNREGISTERED = 1;
    private static final String TAG = "AudioPolicy";
    private static IAudioService sService;
    private ArrayList<WeakReference<AudioRecord>> mCaptors;
    private AudioPolicyConfig mConfig;
    private Context mContext;
    private final EventHandler mEventHandler;
    private AudioPolicyFocusListener mFocusListener;
    private ArrayList<WeakReference<AudioTrack>> mInjectors;
    private boolean mIsFocusPolicy;
    private boolean mIsTestFocusPolicy;
    private final Object mLock;
    private final IAudioPolicyCallback mPolicyCb;
    private final MediaProjection mProjection;
    private String mRegistrationId;
    private int mStatus;
    private AudioPolicyStatusListener mStatusListener;
    private final AudioPolicyVolumeCallback mVolCb;

    public static abstract class AudioPolicyFocusListener {
        public void onAudioFocusAbandon(AudioFocusInfo audioFocusInfo) {
        }

        public void onAudioFocusGrant(AudioFocusInfo audioFocusInfo, int i) {
        }

        public void onAudioFocusLoss(AudioFocusInfo audioFocusInfo, boolean z) {
        }

        public void onAudioFocusRequest(AudioFocusInfo audioFocusInfo, int i) {
        }
    }

    public static abstract class AudioPolicyStatusListener {
        public void onMixStateUpdate(AudioMix audioMix) {
        }

        public void onStatusChange() {
        }
    }

    public static abstract class AudioPolicyVolumeCallback {
        public void onVolumeAdjustment(int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PolicyStatus {
    }

    public AudioPolicyConfig getConfig() {
        return this.mConfig;
    }

    public boolean hasFocusListener() {
        return this.mFocusListener != null;
    }

    public boolean isFocusPolicy() {
        return this.mIsFocusPolicy;
    }

    public boolean isTestFocusPolicy() {
        return this.mIsTestFocusPolicy;
    }

    public boolean isVolumeController() {
        return this.mVolCb != null;
    }

    public MediaProjection getMediaProjection() {
        return this.mProjection;
    }

    private AudioPolicy(AudioPolicyConfig audioPolicyConfig, Context context, Looper looper, AudioPolicyFocusListener audioPolicyFocusListener, AudioPolicyStatusListener audioPolicyStatusListener, boolean z, boolean z2, AudioPolicyVolumeCallback audioPolicyVolumeCallback, MediaProjection mediaProjection) {
        this.mLock = new Object();
        this.mPolicyCb = new IAudioPolicyCallback.Stub() { // from class: android.media.audiopolicy.AudioPolicy.1
            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusGrant(AudioFocusInfo audioFocusInfo, int i) {
                AudioPolicy.this.sendMsg(1, audioFocusInfo, i);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusLoss(AudioFocusInfo audioFocusInfo, boolean z3) {
                AudioPolicy.this.sendMsg(2, audioFocusInfo, z3 ? 1 : 0);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusRequest(AudioFocusInfo audioFocusInfo, int i) {
                AudioPolicy.this.sendMsg(4, audioFocusInfo, i);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusAbandon(AudioFocusInfo audioFocusInfo) {
                AudioPolicy.this.sendMsg(5, audioFocusInfo, 0);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyMixStateUpdate(String str, int i) {
                for (AudioMix audioMix : AudioPolicy.this.mConfig.getMixes()) {
                    if (audioMix.getRegistration().equals(str)) {
                        audioMix.mMixState = i;
                        AudioPolicy.this.sendMsg(3, audioMix, 0);
                    }
                }
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyVolumeAdjust(int i) {
                AudioPolicy.this.sendMsg(6, null, i);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyUnregistration() {
                AudioPolicy.this.setRegistration(null);
            }
        };
        this.mConfig = audioPolicyConfig;
        this.mStatus = 1;
        this.mContext = context;
        looper = looper == null ? Looper.getMainLooper() : looper;
        if (looper != null) {
            this.mEventHandler = new EventHandler(this, looper);
        } else {
            this.mEventHandler = null;
            Log.e(TAG, "No event handler due to looper without a thread");
        }
        this.mFocusListener = audioPolicyFocusListener;
        this.mStatusListener = audioPolicyStatusListener;
        this.mIsFocusPolicy = z;
        this.mIsTestFocusPolicy = z2;
        this.mVolCb = audioPolicyVolumeCallback;
        this.mProjection = mediaProjection;
    }

    public static class Builder {
        private Context mContext;
        private AudioPolicyFocusListener mFocusListener;
        private Looper mLooper;
        private MediaProjection mProjection;
        private AudioPolicyStatusListener mStatusListener;
        private AudioPolicyVolumeCallback mVolCb;
        private boolean mIsFocusPolicy = false;
        private boolean mIsTestFocusPolicy = false;
        private ArrayList<AudioMix> mMixes = new ArrayList<>();

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder addMix(AudioMix audioMix) throws IllegalArgumentException {
            if (audioMix == null) {
                throw new IllegalArgumentException("Illegal null AudioMix argument");
            }
            this.mMixes.add(audioMix);
            return this;
        }

        public Builder setLooper(Looper looper) throws IllegalArgumentException {
            if (looper == null) {
                throw new IllegalArgumentException("Illegal null Looper argument");
            }
            this.mLooper = looper;
            return this;
        }

        public void setAudioPolicyFocusListener(AudioPolicyFocusListener audioPolicyFocusListener) {
            this.mFocusListener = audioPolicyFocusListener;
        }

        public Builder setIsAudioFocusPolicy(boolean z) {
            this.mIsFocusPolicy = z;
            return this;
        }

        public Builder setIsTestFocusPolicy(boolean z) {
            this.mIsTestFocusPolicy = z;
            return this;
        }

        public void setAudioPolicyStatusListener(AudioPolicyStatusListener audioPolicyStatusListener) {
            this.mStatusListener = audioPolicyStatusListener;
        }

        public Builder setAudioPolicyVolumeCallback(AudioPolicyVolumeCallback audioPolicyVolumeCallback) {
            if (audioPolicyVolumeCallback == null) {
                throw new IllegalArgumentException("Invalid null volume callback");
            }
            this.mVolCb = audioPolicyVolumeCallback;
            return this;
        }

        public Builder setMediaProjection(MediaProjection mediaProjection) {
            if (mediaProjection == null) {
                throw new IllegalArgumentException("Invalid null volume callback");
            }
            this.mProjection = mediaProjection;
            return this;
        }

        public AudioPolicy build() {
            if (this.mStatusListener != null) {
                Iterator<AudioMix> it = this.mMixes.iterator();
                while (it.hasNext()) {
                    it.next().mCallbackFlags |= 1;
                }
            }
            if (this.mIsFocusPolicy && this.mFocusListener == null) {
                throw new IllegalStateException("Cannot be a focus policy without an AudioPolicyFocusListener");
            }
            return new AudioPolicy(new AudioPolicyConfig(this.mMixes), this.mContext, this.mLooper, this.mFocusListener, this.mStatusListener, this.mIsFocusPolicy, this.mIsTestFocusPolicy, this.mVolCb, this.mProjection);
        }
    }

    public int attachMixes(List<AudioMix> list) {
        int iAddMixForPolicy;
        if (list == null) {
            throw new IllegalArgumentException("Illegal null list of AudioMix");
        }
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                throw new IllegalStateException("Cannot alter unregistered AudioPolicy");
            }
            ArrayList<AudioMix> arrayList = new ArrayList<>(list.size());
            for (AudioMix audioMix : list) {
                if (audioMix == null) {
                    throw new IllegalArgumentException("Illegal null AudioMix in attachMixes");
                }
                arrayList.add(audioMix);
            }
            AudioPolicyConfig audioPolicyConfig = new AudioPolicyConfig(arrayList);
            try {
                iAddMixForPolicy = getService().addMixForPolicy(audioPolicyConfig, cb());
                if (iAddMixForPolicy == 0) {
                    this.mConfig.add(arrayList);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in attachMixes", e);
                return -1;
            }
        }
        return iAddMixForPolicy;
    }

    public int detachMixes(List<AudioMix> list) {
        int iRemoveMixForPolicy;
        if (list == null) {
            throw new IllegalArgumentException("Illegal null list of AudioMix");
        }
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                throw new IllegalStateException("Cannot alter unregistered AudioPolicy");
            }
            ArrayList<AudioMix> arrayList = new ArrayList<>(list.size());
            for (AudioMix audioMix : list) {
                if (audioMix == null) {
                    throw new IllegalArgumentException("Illegal null AudioMix in detachMixes");
                }
                arrayList.add(audioMix);
            }
            AudioPolicyConfig audioPolicyConfig = new AudioPolicyConfig(arrayList);
            try {
                iRemoveMixForPolicy = getService().removeMixForPolicy(audioPolicyConfig, cb());
                if (iRemoveMixForPolicy == 0) {
                    this.mConfig.remove(arrayList);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in detachMixes", e);
                return -1;
            }
        }
        return iRemoveMixForPolicy;
    }

    @SystemApi
    public boolean setUidDeviceAffinity(int i, List<AudioDeviceInfo> list) {
        boolean z;
        if (list == null) {
            throw new IllegalArgumentException("Illegal null list of audio devices");
        }
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                throw new IllegalStateException("Cannot use unregistered AudioPolicy");
            }
            int[] iArr = new int[list.size()];
            String[] strArr = new String[list.size()];
            int i2 = 0;
            for (AudioDeviceInfo audioDeviceInfo : list) {
                if (audioDeviceInfo == null) {
                    throw new IllegalArgumentException("Illegal null AudioDeviceInfo in setUidDeviceAffinity");
                }
                iArr[i2] = AudioDeviceInfo.convertDeviceTypeToInternalDevice(audioDeviceInfo.getType());
                strArr[i2] = audioDeviceInfo.getAddress();
                i2++;
            }
            try {
                z = getService().setUidDeviceAffinity(cb(), i, iArr, strArr) == 0;
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in setUidDeviceAffinity", e);
                return false;
            }
        }
        return z;
    }

    @SystemApi
    public boolean removeUidDeviceAffinity(int i) {
        boolean z;
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                throw new IllegalStateException("Cannot use unregistered AudioPolicy");
            }
            try {
                z = getService().removeUidDeviceAffinity(cb(), i) == 0;
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in removeUidDeviceAffinity", e);
                return false;
            }
        }
        return z;
    }

    @SystemApi
    public boolean removeUserIdDeviceAffinity(int i) {
        boolean z;
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                throw new IllegalStateException("Cannot use unregistered AudioPolicy");
            }
            try {
                z = getService().removeUserIdDeviceAffinity(cb(), i) == 0;
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in removeUserIdDeviceAffinity", e);
                return false;
            }
        }
        return z;
    }

    @SystemApi
    public boolean setUserIdDeviceAffinity(int i, List<AudioDeviceInfo> list) {
        boolean z;
        Objects.requireNonNull(list, "Illegal null list of audio devices");
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                throw new IllegalStateException("Cannot use unregistered AudioPolicy");
            }
            int[] iArr = new int[list.size()];
            String[] strArr = new String[list.size()];
            int i2 = 0;
            for (AudioDeviceInfo audioDeviceInfo : list) {
                if (audioDeviceInfo == null) {
                    throw new IllegalArgumentException("Illegal null AudioDeviceInfo in setUserIdDeviceAffinity");
                }
                iArr[i2] = AudioDeviceInfo.convertDeviceTypeToInternalDevice(audioDeviceInfo.getType());
                strArr[i2] = audioDeviceInfo.getAddress();
                i2++;
            }
            try {
                z = getService().setUserIdDeviceAffinity(cb(), i, iArr, strArr) == 0;
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in setUserIdDeviceAffinity", e);
                return false;
            }
        }
        return z;
    }

    public void setRegistration(String str) {
        synchronized (this.mLock) {
            this.mRegistrationId = str;
            this.mConfig.setRegistration(str);
            if (str != null) {
                this.mStatus = 2;
            } else {
                this.mStatus = 1;
            }
        }
        sendMsg(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean policyReadyToUse() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            int r1 = r5.mStatus     // Catch: java.lang.Throwable -> L83
            r2 = 2
            r3 = 0
            if (r1 == r2) goto L12
            java.lang.String r1 = "AudioPolicy"
            java.lang.String r2 = "Cannot use unregistered AudioPolicy"
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L83
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L83
            return r3
        L12:
            java.lang.String r1 = r5.mRegistrationId     // Catch: java.lang.Throwable -> L83
            if (r1 != 0) goto L1f
            java.lang.String r1 = "AudioPolicy"
            java.lang.String r2 = "Cannot use unregistered AudioPolicy"
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L83
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L83
            return r3
        L1f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L83
            java.lang.String r0 = "android.permission.MODIFY_AUDIO_ROUTING"
            int r0 = r5.checkCallingOrSelfPermission(r0)
            r1 = 1
            if (r0 != 0) goto L2b
            r0 = r1
            goto L2c
        L2b:
            r0 = r3
        L2c:
            android.media.projection.MediaProjection r2 = r5.mProjection     // Catch: android.os.RemoteException -> L76
            if (r2 == 0) goto L3e
            android.media.projection.MediaProjection r2 = r5.mProjection     // Catch: android.os.RemoteException -> L76
            android.media.projection.IMediaProjection r2 = r2.getProjection()     // Catch: android.os.RemoteException -> L76
            boolean r2 = r2.canProjectAudio()     // Catch: android.os.RemoteException -> L76
            if (r2 == 0) goto L3e
            r2 = r1
            goto L3f
        L3e:
            r2 = r3
        L3f:
            boolean r4 = r5.isLoopbackRenderPolicy()
            if (r4 == 0) goto L47
            if (r2 != 0) goto L75
        L47:
            if (r0 != 0) goto L75
            java.lang.String r0 = "AudioPolicy"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot use AudioPolicy for pid "
            r1.append(r2)
            int r2 = android.os.Binder.getCallingPid()
            r1.append(r2)
            java.lang.String r2 = " / uid "
            r1.append(r2)
            int r2 = android.os.Binder.getCallingUid()
            r1.append(r2)
            java.lang.String r2 = ", needs MODIFY_AUDIO_ROUTING or MediaProjection that can project audio."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Slog.w(r0, r1)
            return r3
        L75:
            return r1
        L76:
            r0 = move-exception
            java.lang.String r1 = "AudioPolicy"
            java.lang.String r2 = "Failed to check if MediaProjection#canProjectAudio"
            android.util.Log.e(r1, r2)
            java.lang.RuntimeException r0 = r0.rethrowFromSystemServer()
            throw r0
        L83:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L83
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.audiopolicy.AudioPolicy.policyReadyToUse():boolean");
    }

    private boolean isLoopbackRenderPolicy() {
        boolean zAllMatch;
        synchronized (this.mLock) {
            zAllMatch = this.mConfig.mMixes.stream().allMatch(new Predicate() { // from class: android.media.audiopolicy.-$$Lambda$AudioPolicy$-ztOT0FT3tzGMUr4lm1gv6dBE4c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return AudioPolicy.lambda$isLoopbackRenderPolicy$0((AudioMix) obj);
                }
            });
        }
        return zAllMatch;
    }

    static /* synthetic */ boolean lambda$isLoopbackRenderPolicy$0(AudioMix audioMix) {
        return audioMix.getRouteFlags() == 3;
    }

    private int checkCallingOrSelfPermission(String str) {
        Context context = this.mContext;
        if (context != null) {
            return context.checkCallingOrSelfPermission(str);
        }
        Slog.v(TAG, "Null context, checking permission via ActivityManager");
        try {
            return ActivityManager.getService().checkPermission(str, Binder.getCallingPid(), Binder.getCallingUid());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void checkMixReadyToUse(AudioMix audioMix, boolean z) throws IllegalArgumentException {
        if (audioMix == null) {
            throw new IllegalArgumentException(z ? "Invalid null AudioMix for AudioTrack creation" : "Invalid null AudioMix for AudioRecord creation");
        }
        if (!this.mConfig.mMixes.contains(audioMix)) {
            throw new IllegalArgumentException("Invalid mix: not part of this policy");
        }
        if ((audioMix.getRouteFlags() & 2) != 2) {
            throw new IllegalArgumentException("Invalid AudioMix: not defined for loop back");
        }
        if (z && audioMix.getMixType() != 1) {
            throw new IllegalArgumentException("Invalid AudioMix: not defined for being a recording source");
        }
        if (!z && audioMix.getMixType() != 0) {
            throw new IllegalArgumentException("Invalid AudioMix: not defined for capturing playback");
        }
    }

    public int getFocusDuckingBehavior() {
        return this.mConfig.mDuckingPolicy;
    }

    public int setFocusDuckingBehavior(int i) throws IllegalStateException, IllegalArgumentException {
        int focusPropertiesForPolicy;
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid ducking behavior " + i);
        }
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                throw new IllegalStateException("Cannot change ducking behavior for unregistered policy");
            }
            if (i == 1 && this.mFocusListener == null) {
                throw new IllegalStateException("Cannot handle ducking without an audio focus listener");
            }
            try {
                focusPropertiesForPolicy = getService().setFocusPropertiesForPolicy(i, cb());
                if (focusPropertiesForPolicy == 0) {
                    this.mConfig.mDuckingPolicy = i;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in setFocusPropertiesForPolicy for behavior", e);
                return -1;
            }
        }
        return focusPropertiesForPolicy;
    }

    public AudioRecord createAudioRecordSink(AudioMix audioMix) throws IllegalArgumentException {
        if (!policyReadyToUse()) {
            Log.e(TAG, "Cannot create AudioRecord sink for AudioMix");
            return null;
        }
        checkMixReadyToUse(audioMix, false);
        AudioRecord audioRecord = new AudioRecord(new AudioAttributes.Builder().setInternalCapturePreset(8).addTag(addressForTag(audioMix)).addTag(AudioRecord.SUBMIX_FIXED_VOLUME).build(), new AudioFormat.Builder(audioMix.getFormat()).setChannelMask(AudioFormat.inChannelMaskFromOutChannelMask(audioMix.getFormat().getChannelMask())).build(), AudioRecord.getMinBufferSize(audioMix.getFormat().getSampleRate(), 12, audioMix.getFormat().getEncoding()), 0);
        synchronized (this.mLock) {
            if (this.mCaptors == null) {
                this.mCaptors = new ArrayList<>(1);
            }
            this.mCaptors.add(new WeakReference<>(audioRecord));
        }
        return audioRecord;
    }

    public AudioTrack createAudioTrackSource(AudioMix audioMix) throws IllegalArgumentException {
        if (!policyReadyToUse()) {
            Log.e(TAG, "Cannot create AudioTrack source for AudioMix");
            return null;
        }
        checkMixReadyToUse(audioMix, true);
        AudioTrack audioTrack = new AudioTrack(new AudioAttributes.Builder().setUsage(15).addTag(addressForTag(audioMix)).build(), audioMix.getFormat(), AudioTrack.getMinBufferSize(audioMix.getFormat().getSampleRate(), audioMix.getFormat().getChannelMask(), audioMix.getFormat().getEncoding()), 1, 0);
        synchronized (this.mLock) {
            if (this.mInjectors == null) {
                this.mInjectors = new ArrayList<>(1);
            }
            this.mInjectors.add(new WeakReference<>(audioTrack));
        }
        return audioTrack;
    }

    public void invalidateCaptorsAndInjectors() {
        AudioRecord audioRecord;
        AudioTrack audioTrack;
        if (policyReadyToUse()) {
            synchronized (this.mLock) {
                if (this.mInjectors != null) {
                    Iterator<WeakReference<AudioTrack>> it = this.mInjectors.iterator();
                    while (it.hasNext() && (audioTrack = it.next().get()) != null) {
                        try {
                            audioTrack.stop();
                            audioTrack.flush();
                        } catch (IllegalStateException unused) {
                        }
                    }
                }
                if (this.mCaptors != null) {
                    Iterator<WeakReference<AudioRecord>> it2 = this.mCaptors.iterator();
                    while (it2.hasNext() && (audioRecord = it2.next().get()) != null) {
                        try {
                            audioRecord.stop();
                        } catch (IllegalStateException unused2) {
                        }
                    }
                }
            }
        }
    }

    public int getStatus() {
        return this.mStatus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPolicyStatusChange() {
        synchronized (this.mLock) {
            if (this.mStatusListener == null) {
                return;
            }
            this.mStatusListener.onStatusChange();
        }
    }

    public IAudioPolicyCallback cb() {
        return this.mPolicyCb;
    }

    private class EventHandler extends Handler {
        public EventHandler(AudioPolicy audioPolicy, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    AudioPolicy.this.onPolicyStatusChange();
                    break;
                case 1:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusGrant((AudioFocusInfo) message.obj, message.arg1);
                    }
                    break;
                case 2:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusLoss((AudioFocusInfo) message.obj, message.arg1 != 0);
                    }
                    break;
                case 3:
                    if (AudioPolicy.this.mStatusListener != null) {
                        AudioPolicy.this.mStatusListener.onMixStateUpdate((AudioMix) message.obj);
                    }
                    break;
                case 4:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusRequest((AudioFocusInfo) message.obj, message.arg1);
                    } else {
                        Log.e(AudioPolicy.TAG, "Invalid null focus listener for focus request event");
                    }
                    break;
                case 5:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusAbandon((AudioFocusInfo) message.obj);
                    } else {
                        Log.e(AudioPolicy.TAG, "Invalid null focus listener for focus abandon event");
                    }
                    break;
                case 6:
                    if (AudioPolicy.this.mVolCb != null) {
                        AudioPolicy.this.mVolCb.onVolumeAdjustment(message.arg1);
                    } else {
                        Log.e(AudioPolicy.TAG, "Invalid null volume event");
                    }
                    break;
                default:
                    Log.e(AudioPolicy.TAG, "Unknown event " + message.what);
                    break;
            }
        }
    }

    private static String addressForTag(AudioMix audioMix) {
        return "addr=" + audioMix.getRegistration();
    }

    private void sendMsg(int i) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler != null) {
            eventHandler.sendEmptyMessage(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(int i, Object obj, int i2) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler != null) {
            eventHandler.sendMessage(eventHandler.obtainMessage(i, i2, 0, obj));
        }
    }

    private static IAudioService getService() {
        IAudioService iAudioService = sService;
        if (iAudioService != null) {
            return iAudioService;
        }
        IAudioService iAudioServiceAsInterface = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
        sService = iAudioServiceAsInterface;
        return iAudioServiceAsInterface;
    }

    public String toLogFriendlyString() {
        return new String("android.media.audiopolicy.AudioPolicy:\n") + "config=" + this.mConfig.toLogFriendlyString();
    }
}
