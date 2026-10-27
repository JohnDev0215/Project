package com.nuance.android.vocalizer;

import android.content.Context;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.nuance.android.vocalizer.internal.VocalizerAssetManager;
import com.nuance.android.vocalizer.internal.VocalizerFileInfo;
import com.nuance.android.vocalizer.internal.VocalizerInstallationListener;
import com.nuance.android.vocalizer.internal.VocalizerResourceInfo;
import com.nuance.android.vocalizer.internal.VocalizerStatusInfo;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public class VocalizerEngine implements AudioTrack.OnPlaybackPositionUpdateListener, VocalizerInstallationListener {
    public static final int DEFAULT_PITCH = 100;
    public static final int DEFAULT_RATE = 100;
    public static final int DEFAULT_READMODE = 1;
    public static final int DEFAULT_TEXTFORMAT = 0;
    public static final int DEFAULT_TEXTMODE = 1;
    public static final int DEFAULT_VOLUME = 80;
    public static final int DEFAULT_WAIT_FACTOR = 1;
    private static final int FP_PRECISION = 10;
    private static final int LISTENER_PRECISION_DEFAULT = 50;
    private static final int MESSAGE_CLOSE_AUDIODEV = 1;
    private static final int MESSAGE_CLOSE_AUDIODEV_DELAY = 500;
    private static final int MESSAGE_PROCESSNEXT_FROMSYNTH = 2;
    public static final int READMODE_CHAR = 2;
    public static final int READMODE_SENT = 1;
    public static final int READMODE_WORD = 3;
    public static final int STATE_INITIALIZED = 2;
    public static final int STATE_INIT_ERROR = 6;
    public static final int STATE_PAUSED = 5;
    public static final int STATE_READY = 4;
    public static final int STATE_SPEAKING = 3;
    public static final int STATE_UNINITIALIZED = 1;
    private static final String TAG = "NUANCE";
    public static final int TEXTFORMAT_NORM = 0;
    public static final int TEXTFORMAT_SSML = 1;
    public static final int TEXTMODE_SMS = 2;
    public static final int TEXTMODE_STANDARD = 1;
    private VocalizerAssetManager mAssetManager;
    private float mAudioBufferPayload;
    private VocalizerAudioSettings mAudioSettings;
    private Context mContext;
    private Handler mHandler;
    private float mInternalAudioBufferPayload;
    private Vector<VocalizerSpeechItem> mSpeechItems;
    private int mState;
    private VocalizerVersion mVersion;
    private long mainThreadId;
    private boolean mReloading = false;
    private boolean mAutoInit = true;
    private boolean mNotifyNewVoiceList = false;
    private EngineConfiguration mPrevConfiguration = null;
    private Thread mInitThread = null;
    private Thread mSynthThread = null;
    private int mSynthThreadPriority = 5;
    private boolean mInitialized = false;
    private AudioTrack mAudioStream = null;
    private int mAudioStreamChannel = 3;
    private VocalizerVoice[] mVoiceList = null;
    private VocalizerVoice mCurrentVoice = null;
    private int mMaxAudioBufferSize = 65536;
    private Hashtable<Long, VocalizerResourceInfo> mLoadedResources = null;
    private VocalizerAudioSettings mCustomAudioSettings = null;
    private float leftVolume = 1.0f;
    private float rightVolume = 1.0f;
    private VocalizerEngineListener mEngineListener = null;
    private boolean mStopRequested = false;
    private int mAudioBytesSent = 0;
    private int mAudioBytesSentCurrentSynthesis = 0;
    private int mVocalizerSamplesRead = 0;
    private VocalizerSpeechItem currentSpeechItem = null;
    private Vector<VocalizerSpeechMark> mScheduledSpeechMarks = null;
    private VocalizerSpeechMarkListener mSpeechMarkListener = null;
    private VocalizerAudioOutputListener mAudioOutputListener = null;
    private boolean mAudioListenerOnlySamples = false;
    private Semaphore pauseSemaphore = new Semaphore(1);
    private Semaphore streamSemaphore = new Semaphore(1);
    private long mNativeEnginePtr = 0;

    private native void createNativeEngine();

    private native void destroyNativeEngine();

    /* JADX INFO: Access modifiers changed from: private */
    public native VocalizerVoice[] getAvailableVoiceListNative();

    private native VocalizerVersion getEngineVersion();

    private native int getPitch();

    private native int getRate();

    private native int getReadMode();

    private native VocalizerStatusInfo getStatInfo();

    private native int getTextFormat();

    private native int getTextMode();

    private native int getVolume();

    private native int getWaitFactor();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean initializeNative(String str);

    private native boolean loadVoiceNative(VocalizerVoice vocalizerVoice);

    private native VocalizerResourceInfo nativeLoadResource(byte[] bArr, String str);

    private native void nativeUnloadResource(VocalizerResourceInfo vocalizerResourceInfo);

    private native void releaseNative();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean setAudioBufferSize(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setEnableMarks(boolean z);

    private native void setPitch(int i);

    private native void setRate(int i);

    private native void setReadMode(int i);

    private native void setTextFormat(int i);

    private native void setTextMode(int i);

    private native void setVolume(int i);

    private native void setWaitFactor(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void speakNative(String str);

    private native void stopSpeechNative();

    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
    public void onMarkerReached(AudioTrack audioTrack) {
    }

    static /* synthetic */ int access$2612(VocalizerEngine vocalizerEngine, int i) {
        int i2 = vocalizerEngine.mAudioBytesSent + i;
        vocalizerEngine.mAudioBytesSent = i2;
        return i2;
    }

    static /* synthetic */ int access$3212(VocalizerEngine vocalizerEngine, int i) {
        int i2 = vocalizerEngine.mAudioBytesSentCurrentSynthesis + i;
        vocalizerEngine.mAudioBytesSentCurrentSynthesis = i2;
        return i2;
    }

    final class EngineConfiguration {
        private int mPitch;
        private int mRate;
        private int mReadMode;
        private int mTextFormat;
        private int mTextMode;
        private int mVolume;
        private int mWaitFactor;
        private VocalizerVoice mVoice = null;
        private Hashtable<Long, VocalizerResourceInfo> mLoadedResources = null;

        EngineConfiguration() {
        }

        public boolean storeConfiguration() {
            if (!VocalizerEngine.this.isInitialized()) {
                return false;
            }
            this.mVoice = VocalizerEngine.this.getCurrentVoice();
            this.mVolume = VocalizerEngine.this.getSpeechVolume();
            this.mPitch = VocalizerEngine.this.getSpeechPitch();
            this.mRate = VocalizerEngine.this.getSpeechRate();
            this.mReadMode = VocalizerEngine.this.getSpeechReadMode();
            this.mTextMode = VocalizerEngine.this.getSpeechTextMode();
            this.mWaitFactor = VocalizerEngine.this.getSpeechWaitFactor();
            this.mTextFormat = VocalizerEngine.this.getSpeechTextFormat();
            if (VocalizerEngine.this.mLoadedResources == null) {
                return true;
            }
            this.mLoadedResources = (Hashtable) VocalizerEngine.this.mLoadedResources.clone();
            return true;
        }

        public boolean restoreConfiguration() {
            if (!VocalizerEngine.this.isInitialized()) {
                return false;
            }
            VocalizerEngine.this.setSpeechVolume(this.mVolume);
            VocalizerEngine.this.setSpeechPitch(this.mPitch);
            VocalizerEngine.this.setSpeechRate(this.mRate);
            VocalizerEngine.this.setSpeechReadMode(this.mReadMode);
            VocalizerEngine.this.setSpeechTextMode(this.mTextMode);
            VocalizerEngine.this.setSpeechWaitFactor(this.mWaitFactor);
            VocalizerEngine.this.setSpeechTextFormat(this.mTextFormat);
            VocalizerVoice vocalizerVoice = this.mVoice;
            if (vocalizerVoice != null) {
                VocalizerVoice[] sortedVoiceList = VocalizerEngine.this.getSortedVoiceList(vocalizerVoice.getVoiceName(), this.mVoice.getLanguage(), this.mVoice.getSampleRate());
                VocalizerVoice vocalizerVoice2 = (sortedVoiceList == null || sortedVoiceList.length <= 0) ? null : sortedVoiceList[0];
                if (vocalizerVoice2 == null && VocalizerEngine.this.mVoiceList != null && VocalizerEngine.this.mVoiceList.length > 0) {
                    vocalizerVoice2 = VocalizerEngine.this.mVoiceList[0];
                }
                try {
                    if (!VocalizerEngine.this.loadVoice(vocalizerVoice2)) {
                        Log.e(VocalizerEngine.TAG, "loadVoice failed while reloading.");
                    }
                } catch (Exception e) {
                    Log.e(VocalizerEngine.TAG, "Reloading voice EXCEPTION: " + e);
                }
            }
            Hashtable<Long, VocalizerResourceInfo> hashtable = this.mLoadedResources;
            if (hashtable == null) {
                return true;
            }
            for (VocalizerResourceInfo vocalizerResourceInfo : hashtable.values()) {
                if (VocalizerEngine.this.loadResource(vocalizerResourceInfo.mResourceData, vocalizerResourceInfo.mMimeType, vocalizerResourceInfo.mId) == -1) {
                    Log.w(VocalizerEngine.TAG, "Failed to reload resource: " + vocalizerResourceInfo.mId);
                }
            }
            return true;
        }
    }

    public VocalizerEngine(Context context) {
        this.mContext = null;
        this.mAssetManager = null;
        this.mVersion = null;
        this.mSpeechItems = null;
        this.mHandler = null;
        this.mAudioBufferPayload = 2.5f;
        this.mInternalAudioBufferPayload = 10.0f;
        this.mAudioSettings = null;
        this.mainThreadId = 0L;
        createNativeEngine();
        if (Build.PRODUCT.equals("sdk")) {
            this.mAudioBufferPayload = 5.0f;
            this.mInternalAudioBufferPayload = 5.0f;
        }
        this.mContext = context;
        setState(1);
        this.mSpeechItems = new Vector<>();
        this.mHandler = new Handler() { // from class: com.nuance.android.vocalizer.VocalizerEngine.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    VocalizerEngine.this.closeAudioStream();
                } else {
                    if (i != 2) {
                        return;
                    }
                    VocalizerEngine.this.mSynthThread = null;
                    VocalizerEngine.this.processNextQueueItem();
                }
            }
        };
        this.mainThreadId = Thread.currentThread().getId();
        this.mAudioSettings = new VocalizerAudioSettings();
        this.mAssetManager = new VocalizerAssetManager(this.mContext);
        this.mVersion = getEngineVersion();
    }

    private void scheduleCloseAudioDev() {
        cancelDeferredCloseAudioDev();
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = this;
        this.mHandler.sendMessageDelayed(messageObtain, 500L);
    }

    private void cancelDeferredCloseAudioDev() {
        this.mHandler.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAudioStream() {
        AudioTrack audioTrack = this.mAudioStream;
        if (audioTrack != null) {
            try {
                synchronized (audioTrack) {
                    this.mAudioStream.stop();
                    this.mAudioStream.setPositionNotificationPeriod(0);
                    this.mAudioStream.setPlaybackPositionUpdateListener(null);
                    this.mAudioStream.release();
                }
            } catch (Exception e) {
                Log.e(TAG, "closeAudioStream EXCEPTION: " + e);
            }
            this.mAudioStream = null;
        }
    }

    public void setListener(VocalizerEngineListener vocalizerEngineListener) {
        this.mEngineListener = vocalizerEngineListener;
        this.mAssetManager.setInstallationListener(vocalizerEngineListener == null ? null : this);
    }

    public void setSpeechMarkListener(VocalizerSpeechMarkListener vocalizerSpeechMarkListener) {
        this.mSpeechMarkListener = vocalizerSpeechMarkListener;
    }

    public void setAudioOutputListener(VocalizerAudioOutputListener vocalizerAudioOutputListener, boolean z) {
        this.mAudioOutputListener = vocalizerAudioOutputListener;
        this.mAudioListenerOnlySamples = z;
    }

    public void setCustomAudioSettings(VocalizerAudioSettings vocalizerAudioSettings) {
        this.mCustomAudioSettings = vocalizerAudioSettings;
    }

    public void setAudioOutputVolume(float f, float f2) {
        this.leftVolume = f;
        this.rightVolume = f2;
    }

    final class InitThread extends Thread implements Runnable {
        InitThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z;
            try {
                VocalizerEngine.this.mAssetManager.initialize();
                VocalizerEngine vocalizerEngine = VocalizerEngine.this;
                if (vocalizerEngine.initializeNative(vocalizerEngine.mAssetManager.getPipelineHeaders())) {
                    z = true;
                } else {
                    Log.e(VocalizerEngine.TAG, "ERROR: Could not Initialize native TTS engine");
                    z = false;
                }
                VocalizerEngine vocalizerEngine2 = VocalizerEngine.this;
                vocalizerEngine2.mVoiceList = vocalizerEngine2.getAvailableVoiceListNative();
            } catch (Exception e) {
                Log.e(VocalizerEngine.TAG, "Initialization exception: " + e);
                z = false;
            }
            if (!z) {
                VocalizerEngine.this.setInitialized(false);
                VocalizerEngine.this.setState(6);
                VocalizerEngine.this.notifyStateChanged();
            } else {
                VocalizerEngine.this.setInitialized(true);
                VocalizerEngine.this.setState(2);
                if (!VocalizerEngine.this.mReloading) {
                    VocalizerEngine.this.notifyStateChanged();
                } else {
                    VocalizerEngine.this.mHandler.post(new Runnable() { // from class: com.nuance.android.vocalizer.VocalizerEngine.InitThread.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (VocalizerEngine.this.mPrevConfiguration != null) {
                                VocalizerEngine.this.mPrevConfiguration.restoreConfiguration();
                            }
                            if (VocalizerEngine.this.mNotifyNewVoiceList && VocalizerEngine.this.mEngineListener != null) {
                                VocalizerEngine.this.mEngineListener.onVoiceListChanged();
                            }
                            VocalizerEngine.this.mReloading = false;
                            VocalizerEngine.this.mNotifyNewVoiceList = false;
                            VocalizerEngine.this.mPrevConfiguration = null;
                        }
                    });
                }
            }
        }
    }

    public synchronized void initialize() {
        if (isInitialized()) {
            return;
        }
        InitThread initThread = new InitThread();
        this.mInitThread = initThread;
        initThread.start();
    }

    public synchronized void release() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            Thread thread = this.mInitThread;
            if (thread != null) {
                thread.join();
            }
        } catch (Exception unused) {
        }
        this.mInitThread = null;
        if (isInitialized()) {
            try {
                stop();
                Hashtable<Long, VocalizerResourceInfo> hashtable = this.mLoadedResources;
                if (hashtable != null) {
                    Enumeration<Long> enumerationKeys = hashtable.keys();
                    while (enumerationKeys.hasMoreElements()) {
                        if (!unloadResource(enumerationKeys.nextElement().longValue())) {
                            Log.w(TAG, "unloadResource failed");
                        }
                    }
                }
                releaseNative();
                this.mAssetManager.release();
                VocalizerStatusInfo statInfo = getStatInfo();
                if (statInfo != null) {
                    statInfo.printLeaks(TAG);
                }
            } catch (Exception e) {
                Log.e(TAG, "release EXCEPTION: " + e);
            }
            cancelDeferredCloseAudioDev();
            closeAudioStream();
            this.mHandler.removeCallbacksAndMessages(null);
            this.mCurrentVoice = null;
            this.mVoiceList = null;
            this.mLoadedResources = null;
            setInitialized(false);
            setState(1);
            if (!this.mReloading) {
                notifyStateChanged();
            }
            Log.i(TAG, "release() took " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms.");
        } else {
            Log.i(TAG, "release() took " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms.");
        }
    }

    public synchronized boolean reload() {
        if (!isInitialized()) {
            return false;
        }
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        this.mPrevConfiguration = engineConfiguration;
        engineConfiguration.storeConfiguration();
        this.mReloading = true;
        release();
        initialize();
        return true;
    }

    public VocalizerSpeechItem speak(String str, boolean z, String str2) {
        if (!isInitialized() || this.mCurrentVoice == null || !checkMainThread()) {
            return null;
        }
        VocalizerSpeechItem vocalizerSpeechItem = new VocalizerSpeechItem();
        vocalizerSpeechItem.setText(str);
        vocalizerSpeechItem.setId(str2);
        if (z) {
            stop();
        }
        synchronized (this.mSpeechItems) {
            this.mSpeechItems.addElement(vocalizerSpeechItem);
        }
        if (processNextQueueItem()) {
            return vocalizerSpeechItem;
        }
        return null;
    }

    public boolean stop() {
        if (!isInitialized() || this.mCurrentVoice == null || !checkMainThread()) {
            return false;
        }
        clearPendingSpeechItems();
        if (this.mSynthThread != null) {
            this.mStopRequested = true;
            stopSpeechNative();
            try {
                this.pauseSemaphore.release();
                this.mSynthThread.join();
            } catch (Exception e) {
                Log.e(TAG, "EXCEPTION waiting for synth thread to die: " + e);
            }
            this.mStopRequested = false;
            this.mSynthThread = null;
        }
        cancelDeferredCloseAudioDev();
        closeAudioStream();
        if (getState() != 4) {
            setState(4);
            notifyStateChanged();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processNextQueueItem() {
        int i;
        if (isInitialized() && this.mCurrentVoice != null) {
            if (this.mSynthThread != null) {
                return true;
            }
            this.mHandler.removeMessages(2);
            cancelDeferredCloseAudioDev();
            if (this.mSpeechItems.size() == 0) {
                try {
                    if (this.mAudioStream != null && (i = this.mAudioSettings.mAudioBufferSize) > 0) {
                        this.mAudioStream.write(new short[i], 0, i);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "EXCEPTION adding empty buffer: " + e);
                }
                scheduleCloseAudioDev();
                if (getState() != 4) {
                    setState(4);
                    notifyStateChanged();
                }
                return true;
            }
            try {
                Thread thread = new Thread(new SynthThread());
                this.mSynthThread = thread;
                thread.setPriority(this.mSynthThreadPriority);
                Log.i(TAG, "Starting new synthesis thread with priority " + this.mSynthThreadPriority);
                this.mSynthThread.start();
                if (getState() != 3) {
                    setState(3);
                    notifyStateChanged();
                }
                return true;
            } catch (Exception e2) {
                Log.e(TAG, "processNextQueueItem. EXCEPTION: " + e2);
                if (getState() != 4) {
                    setState(4);
                    notifyStateChanged();
                }
            }
        }
        return false;
    }

    public boolean pause() {
        AudioTrack audioTrack = this.mAudioStream;
        if (audioTrack == null) {
            return false;
        }
        synchronized (audioTrack) {
            if (this.mAudioStream.getPlayState() != 3) {
                return false;
            }
            this.mAudioStream.pause();
            try {
                this.streamSemaphore.acquire();
                this.pauseSemaphore.acquire();
                this.streamSemaphore.release();
            } catch (Exception e) {
                Log.e(TAG, "EXCEPTION acquiring pause semaphore: " + e);
            }
            setState(5);
            notifyStateChanged();
            return true;
        }
    }

    public boolean resume() {
        AudioTrack audioTrack = this.mAudioStream;
        if (audioTrack == null) {
            return false;
        }
        synchronized (audioTrack) {
            if (this.mAudioStream.getPlayState() != 2) {
                return false;
            }
            this.mAudioStream.play();
            try {
                this.pauseSemaphore.release();
            } catch (Exception e) {
                Log.e(TAG, "EXCEPTION releasing pause semaphore: " + e);
            }
            setState(3);
            notifyStateChanged();
            return true;
        }
    }

    public boolean isPaused() {
        return getState() == 5;
    }

    public boolean isInitialized() {
        boolean z;
        synchronized (this) {
            z = this.mInitialized;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInitialized(boolean z) {
        synchronized (this) {
            this.mInitialized = z;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean loadVoice(com.nuance.android.vocalizer.VocalizerVoice r6) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L61
            boolean r1 = r5.isInitialized()
            if (r1 == 0) goto L61
            boolean r1 = r5.checkMainThread()
            if (r1 != 0) goto L10
            goto L61
        L10:
            com.nuance.android.vocalizer.VocalizerVoice[] r1 = r5.mVoiceList
            if (r1 == 0) goto L17
            int r1 = r1.length
            if (r1 != 0) goto L23
        L17:
            com.nuance.android.vocalizer.VocalizerVoice[] r1 = r5.getAvailableVoiceListNative()
            r5.mVoiceList = r1
            if (r1 == 0) goto L61
            int r1 = r1.length
            if (r1 != 0) goto L23
            goto L61
        L23:
            com.nuance.android.vocalizer.VocalizerVoice r1 = r5.mCurrentVoice
            r2 = 1
            if (r1 == 0) goto L2f
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L2f
            return r2
        L2f:
            r5.stop()
            r1 = r0
        L33:
            com.nuance.android.vocalizer.VocalizerVoice[] r3 = r5.mVoiceList
            int r4 = r3.length
            if (r1 >= r4) goto L61
            r3 = r3[r1]
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L5e
            com.nuance.android.vocalizer.VocalizerVoice[] r6 = r5.mVoiceList
            r6 = r6[r1]
            boolean r6 = r5.loadVoiceNative(r6)
            if (r6 == 0) goto L61
            com.nuance.android.vocalizer.VocalizerVoice[] r6 = r5.mVoiceList
            r6 = r6[r1]
            r5.mCurrentVoice = r6
            int r6 = r5.getState()
            r0 = 4
            if (r6 == r0) goto L5d
            r5.setState(r0)
            r5.notifyStateChanged()
        L5d:
            return r2
        L5e:
            int r1 = r1 + 1
            goto L33
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nuance.android.vocalizer.VocalizerEngine.loadVoice(com.nuance.android.vocalizer.VocalizerVoice):boolean");
    }

    public boolean isSpeaking() {
        return getState() == 3;
    }

    public int getState() {
        int i;
        synchronized (this) {
            i = this.mState;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(int i) {
        synchronized (this) {
            this.mState = i;
        }
    }

    public VocalizerVoice getCurrentVoice() {
        return this.mCurrentVoice;
    }

    public void setAudioStream(int i) {
        this.mAudioStreamChannel = i;
    }

    final class SynthThread implements Runnable {
        SynthThread() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            try {
                boolean z = true;
                if (VocalizerEngine.this.mAudioStream == null || VocalizerEngine.this.mAudioStream.getPlaybackRate() != VocalizerEngine.this.mAudioSettings.mRate) {
                    VocalizerEngine.this.closeAudioStream();
                    int sampleRate = VocalizerEngine.this.mCurrentVoice.getSampleRate() * 1000;
                    if (VocalizerEngine.this.mCustomAudioSettings == null) {
                        VocalizerEngine.this.mAudioSettings.mRate = sampleRate;
                        VocalizerEngine.this.mAudioSettings.mChannels = 1;
                    } else {
                        VocalizerEngine.this.mAudioSettings.mRate = VocalizerEngine.this.mCustomAudioSettings.mRate;
                        VocalizerEngine.this.mAudioSettings.mChannels = VocalizerEngine.this.mCustomAudioSettings.mChannels;
                    }
                    if (VocalizerEngine.this.mAudioSettings.mRate == -1) {
                        VocalizerEngine.this.mAudioSettings.mRate = sampleRate;
                    }
                    if (VocalizerEngine.this.mAudioSettings.mChannels == -1) {
                        VocalizerEngine.this.mAudioSettings.mChannels = 1;
                    }
                    int minBufferSize = AudioTrack.getMinBufferSize(VocalizerEngine.this.mAudioSettings.mRate, VocalizerEngine.this.mAudioSettings.convertToAudioStreamChannel(), 2);
                    float f = minBufferSize;
                    int iMin = Math.min(VocalizerEngine.this.mMaxAudioBufferSize, (int) (Math.max(VocalizerEngine.this.mAudioBufferPayload, 1.0f) * f));
                    Log.i(VocalizerEngine.TAG, "minAudioBufferSize: " + iMin + " minInternalBufferSize: " + Math.min(VocalizerEngine.this.mMaxAudioBufferSize, (int) (Math.max(VocalizerEngine.this.mInternalAudioBufferPayload, 1.0f) * f)) + " hardwareMinBufferSize: " + minBufferSize);
                    int i2 = (int) ((r10 / 2) * 2 * (sampleRate / VocalizerEngine.this.mAudioSettings.mRate));
                    Log.i(VocalizerEngine.TAG, "Internal buffer size: " + i2);
                    VocalizerEngine.this.mAudioSettings.mAudioBufferSize = (iMin / 2) * 2;
                    if (VocalizerEngine.this.mAudioSettings.mChannels == 2) {
                        VocalizerEngine.this.mAudioSettings.mAudioBufferSize *= 2;
                    }
                    if (VocalizerEngine.this.setAudioBufferSize(i2)) {
                        VocalizerEngine.this.mAudioStream = new AudioTrack(VocalizerEngine.this.mAudioStreamChannel, VocalizerEngine.this.mAudioSettings.mRate, VocalizerEngine.this.mAudioSettings.convertToAudioStreamChannel(), 2, VocalizerEngine.this.mAudioSettings.mAudioBufferSize, 1);
                        VocalizerEngine.this.mAudioBytesSent = 0;
                        VocalizerEngine.this.mAudioStream.setPlaybackPositionUpdateListener(VocalizerEngine.this);
                        VocalizerEngine.this.mAudioStream.setPositionNotificationPeriod(VocalizerAudioSettings.convertTimeToFrames(VocalizerEngine.this.mAudioSettings, 50));
                        VocalizerEngine.this.mAudioStream.setStereoVolume(VocalizerEngine.this.leftVolume, VocalizerEngine.this.rightVolume);
                        VocalizerEngine.this.mScheduledSpeechMarks = new Vector();
                    } else {
                        Log.e(VocalizerEngine.TAG, "Not enough memory to allocate buffer size: " + i2);
                        return;
                    }
                }
                if (VocalizerEngine.this.mAudioStream != null) {
                    VocalizerEngine.this.currentSpeechItem = null;
                    VocalizerEngine.this.mVocalizerSamplesRead = 0;
                    VocalizerEngine.this.mAudioBytesSentCurrentSynthesis = 0;
                    VocalizerEngine.this.streamSemaphore = new Semaphore(1);
                    VocalizerEngine.this.pauseSemaphore = new Semaphore(1);
                    synchronized (VocalizerEngine.this.mSpeechItems) {
                        if (VocalizerEngine.this.mSpeechItems.size() > 0) {
                            VocalizerEngine vocalizerEngine = VocalizerEngine.this;
                            vocalizerEngine.currentSpeechItem = (VocalizerSpeechItem) vocalizerEngine.mSpeechItems.get(0);
                        }
                    }
                    if (VocalizerEngine.this.currentSpeechItem != null) {
                        VocalizerEngine vocalizerEngine2 = VocalizerEngine.this;
                        vocalizerEngine2.notifySpeechElementEvent(vocalizerEngine2.currentSpeechItem, true);
                        VocalizerEngine vocalizerEngine3 = VocalizerEngine.this;
                        if (vocalizerEngine3.mSpeechMarkListener == null) {
                            z = false;
                        }
                        vocalizerEngine3.setEnableMarks(z);
                        VocalizerEngine.this.mAudioStream.play();
                        VocalizerEngine vocalizerEngine4 = VocalizerEngine.this;
                        vocalizerEngine4.speakNative(vocalizerEngine4.currentSpeechItem.getText());
                        if (!VocalizerEngine.this.mStopRequested && (VocalizerEngine.this.mAudioOutputListener == null || !VocalizerEngine.this.mAudioListenerOnlySamples)) {
                            int i3 = VocalizerEngine.this.mAudioBytesSentCurrentSynthesis / 2;
                            if (i3 < VocalizerEngine.this.mAudioSettings.mAudioBufferSize) {
                                Log.i(VocalizerEngine.TAG, "Minimum not reached. Adding silence. Sent: " + i3 + " Buffer size: " + VocalizerEngine.this.mAudioSettings.mAudioBufferSize);
                                i = VocalizerEngine.this.mAudioSettings.mAudioBufferSize - i3;
                            } else {
                                i = 0;
                            }
                            if (i > 0) {
                                int iWrite = VocalizerEngine.this.mAudioStream.write(new short[i], 0, i);
                                if (iWrite > 0) {
                                    int i4 = iWrite * 2;
                                    VocalizerEngine.access$2612(VocalizerEngine.this, i4);
                                    VocalizerEngine.access$3212(VocalizerEngine.this, i4);
                                }
                            }
                        }
                        VocalizerEngine.this.waitForAudioStreamToComplete();
                        synchronized (VocalizerEngine.this.mSpeechItems) {
                            if (VocalizerEngine.this.mSpeechItems.remove(VocalizerEngine.this.currentSpeechItem)) {
                                VocalizerEngine vocalizerEngine5 = VocalizerEngine.this;
                                vocalizerEngine5.notifySpeechElementEvent(vocalizerEngine5.currentSpeechItem, false);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(VocalizerEngine.TAG, "SynthThread EXCEPTION: " + e);
                VocalizerEngine.this.clearPendingSpeechItems();
            }
            if (VocalizerEngine.this.mStopRequested) {
                return;
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            messageObtain.obj = this;
            VocalizerEngine.this.mHandler.sendMessageDelayed(messageObtain, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPendingSpeechItems() {
        try {
            synchronized (this.mSpeechItems) {
                Iterator<VocalizerSpeechItem> it = this.mSpeechItems.iterator();
                while (it.hasNext()) {
                    it.next().mSemaphore.release();
                }
                this.mSpeechItems.clear();
            }
        } catch (Exception e) {
            Log.e(TAG, "clearPendingSpeechItems EXCEPTION: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForAudioStreamToComplete() throws Exception {
        Thread thread = this.mSynthThread;
        if (thread == null || thread.getId() != Thread.currentThread().getId()) {
            throw new Exception("waitForAudioStreamToComplete can only be called from SynthThread");
        }
        if (this.mAudioOutputListener == null || !this.mAudioListenerOnlySamples) {
            while (!this.mStopRequested && this.mAudioStream.getPlayState() == 3 && this.mAudioStream.getPlaybackHeadPosition() < VocalizerAudioSettings.convertBytesToFrames(this.mAudioSettings, this.mAudioBytesSent)) {
                try {
                    Thread.sleep(50L);
                } catch (Exception unused) {
                }
            }
        }
    }

    private void speechMarksReceived(VocalizerSpeechMark[] vocalizerSpeechMarkArr) {
        if (vocalizerSpeechMarkArr != null) {
            try {
                if (this.mScheduledSpeechMarks != null) {
                    int sampleRate = this.mCurrentVoice.getSampleRate() * 1000;
                    for (int i = 0; i < vocalizerSpeechMarkArr.length; i++) {
                        int destPos = ((vocalizerSpeechMarkArr[i].getDestPos() - this.mVocalizerSamplesRead) * this.mAudioSettings.mRate) / sampleRate;
                        if (this.mAudioSettings.isStereo()) {
                            destPos *= 2;
                        }
                        vocalizerSpeechMarkArr[i].scheduleAtFrame(VocalizerAudioSettings.convertBytesToFrames(this.mAudioSettings, this.mAudioBytesSent + (destPos * 2)));
                        if (this.mAudioOutputListener != null && this.mAudioListenerOnlySamples) {
                            processSpeechMark(vocalizerSpeechMarkArr[i]);
                        } else {
                            synchronized (this.mScheduledSpeechMarks) {
                                this.mScheduledSpeechMarks.add(vocalizerSpeechMarkArr[i]);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "speechMarksReceived EXCEPTION: " + e);
            }
        }
    }

    private boolean audioSamplesReceived(short[] sArr) {
        short[] sArr2;
        Exception e;
        VocalizerSpeechMark vocalizerSpeechMark;
        int iWrite;
        boolean z;
        try {
            this.pauseSemaphore.acquire();
            this.streamSemaphore.acquire();
        } catch (Exception e2) {
            Log.e(TAG, "audioSamplesReceived. Exception acquiring pause semaphore: " + e2);
        }
        boolean z2 = false;
        if (!this.mStopRequested) {
            try {
                if (this.mAudioStream != null && sArr.length > 0) {
                    this.mVocalizerSamplesRead += sArr.length;
                    int sampleRate = this.mCurrentVoice.getSampleRate() * 1000;
                    if (this.mAudioSettings.mRate != sampleRate || this.mAudioSettings.isStereo()) {
                        try {
                            boolean zIsStereo = this.mAudioSettings.isStereo();
                            int length = (sArr.length * this.mAudioSettings.mRate) / sampleRate;
                            int length2 = (sArr.length << 10) / length;
                            if (zIsStereo) {
                                length *= 2;
                            }
                            sArr2 = new short[length];
                            int i = 0;
                            int i2 = 0;
                            while (i < length) {
                                int i3 = i2 >> 10;
                                try {
                                    if (i3 >= sArr.length) {
                                        break;
                                    }
                                    short s = sArr[i3];
                                    sArr2[i] = s;
                                    if (zIsStereo) {
                                        i++;
                                        sArr2[i] = s;
                                    }
                                    i2 += length2;
                                    i++;
                                } catch (Exception e3) {
                                    e = e3;
                                    Log.e(TAG, "EXCEPTION RESAMPLING: " + e);
                                }
                            }
                        } catch (Exception e4) {
                            sArr2 = sArr;
                            e = e4;
                        }
                        sArr = sArr2;
                    }
                    if (this.mAudioOutputListener != null) {
                        vocalizerSpeechMark = new VocalizerSpeechMark();
                        vocalizerSpeechMark.setType(2048);
                        vocalizerSpeechMark.setAudioData(sArr);
                    } else {
                        vocalizerSpeechMark = null;
                    }
                    if (vocalizerSpeechMark != null && this.mAudioListenerOnlySamples) {
                        processSpeechMark(vocalizerSpeechMark);
                    } else {
                        if (this.mAudioStream.getPlayState() == 3) {
                            iWrite = this.mAudioStream.write(sArr, 0, sArr.length);
                            z = true;
                        } else {
                            iWrite = 0;
                            z = false;
                        }
                        if (z) {
                            if (iWrite <= 0) {
                                Log.e(TAG, "audioSamplesReceived Error: " + iWrite + " Rate: " + this.mAudioStream.getPlaybackRate() + " State: " + this.mAudioStream.getPlayState() + " Samples: " + sArr.length);
                            } else {
                                if (vocalizerSpeechMark != null) {
                                    vocalizerSpeechMark.scheduleAtFrame(VocalizerAudioSettings.convertBytesToFrames(this.mAudioSettings, this.mAudioBytesSent));
                                    synchronized (this.mScheduledSpeechMarks) {
                                        this.mScheduledSpeechMarks.add(vocalizerSpeechMark);
                                    }
                                }
                                int i4 = iWrite * 2;
                                this.mAudioBytesSent += i4;
                                this.mAudioBytesSentCurrentSynthesis += i4;
                            }
                        }
                    }
                    z2 = true;
                }
            } catch (Exception e5) {
                Log.e(TAG, "audioSamplesReceived EXCEPTION: " + e5);
            }
        }
        try {
            this.streamSemaphore.release();
            this.pauseSemaphore.release();
        } catch (Exception e6) {
            Log.e(TAG, "audioSamplesReceived. Exception releasing pause semaphore: " + e6);
        }
        return z2;
    }

    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
    public void onPeriodicNotification(AudioTrack audioTrack) {
        Vector<VocalizerSpeechMark> vector;
        try {
            AudioTrack audioTrack2 = this.mAudioStream;
            if (audioTrack2 != null) {
                synchronized (audioTrack2) {
                    playbackHeadPosition = this.mAudioStream.getPlayState() == 3 ? this.mAudioStream.getPlaybackHeadPosition() : -1;
                }
            }
            if (playbackHeadPosition <= 0 || (vector = this.mScheduledSpeechMarks) == null) {
                return;
            }
            synchronized (vector) {
                int i = 0;
                while (i < this.mScheduledSpeechMarks.size()) {
                    VocalizerSpeechMark vocalizerSpeechMark = this.mScheduledSpeechMarks.get(i);
                    if (vocalizerSpeechMark.getScheduleAtFrame() <= playbackHeadPosition) {
                        processSpeechMark(vocalizerSpeechMark);
                        if (this.mScheduledSpeechMarks.remove(vocalizerSpeechMark)) {
                            i--;
                        }
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "onPeriodicNotification EXCEPTION: " + e);
        }
    }

    private void processSpeechMark(VocalizerSpeechMark vocalizerSpeechMark) {
        this.mHandler.post(new Runnable(vocalizerSpeechMark) { // from class: com.nuance.android.vocalizer.VocalizerEngine.1SpeechMarkDispatcher
            private VocalizerSpeechMark mark;

            {
                this.mark = vocalizerSpeechMark;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (this.mark.getType() == 2048) {
                        if (VocalizerEngine.this.mAudioOutputListener != null) {
                            VocalizerEngine.this.mAudioOutputListener.onAudioData(VocalizerEngine.this.mAudioSettings, this.mark.getAudioData());
                        }
                    } else if (VocalizerEngine.this.mSpeechMarkListener != null) {
                        VocalizerEngine.this.mSpeechMarkListener.onSpeechMarkReceived(this.mark);
                    }
                } catch (Exception e) {
                    Log.e(VocalizerEngine.TAG, "Exception while dispatching the speech mark: " + e);
                }
            }
        });
    }

    public long loadUserDictionary(String str) {
        return loadResource(this.mAssetManager.readFileContents(str), "application/edct-bin-dictionary", 0L);
    }

    public long loadUserDictionary(byte[] bArr) {
        return loadResource(bArr, "application/edct-bin-dictionary", 0L);
    }

    public long loadUserDictionaryLangOverwriting(String str) {
        return loadResource(this.mAssetManager.readFileContents(str), "application/edct-bin-dictionary;mode=langoverwriting", 0L);
    }

    public long loadUserDictionaryLangOverwriting(byte[] bArr) {
        return loadResource(bArr, "application/edct-bin-dictionary;mode=langoverwriting", 0L);
    }

    public long loadRuleSet(String str) {
        return loadResource(this.mAssetManager.readFileContents(str), "application/x-vocalizer-rettt+text", 0L);
    }

    public long loadRuleSet(byte[] bArr) {
        return loadResource(bArr, "application/x-vocalizer-rettt+text", 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long loadResource(byte[] bArr, String str, long j) {
        if (isInitialized() && bArr != null && str != null && str.length() != 0) {
            if (this.mLoadedResources == null) {
                this.mLoadedResources = new Hashtable<>();
            }
            VocalizerResourceInfo vocalizerResourceInfoNativeLoadResource = nativeLoadResource(bArr, str);
            if (vocalizerResourceInfoNativeLoadResource != null) {
                if (j == 0) {
                    j = vocalizerResourceInfoNativeLoadResource.mHandle + SystemClock.uptimeMillis();
                }
                vocalizerResourceInfoNativeLoadResource.mId = j;
                vocalizerResourceInfoNativeLoadResource.mResourceData = bArr;
                vocalizerResourceInfoNativeLoadResource.mMimeType = str;
                this.mLoadedResources.put(Long.valueOf(vocalizerResourceInfoNativeLoadResource.mId), vocalizerResourceInfoNativeLoadResource);
                return vocalizerResourceInfoNativeLoadResource.mId;
            }
            Log.e(TAG, "loadResource FAILED. Data size: " + bArr.length + " mimeType: " + str);
        }
        return -1L;
    }

    public boolean unloadResource(long j) {
        if (!isInitialized()) {
            return false;
        }
        Hashtable<Long, VocalizerResourceInfo> hashtable = this.mLoadedResources;
        if (hashtable == null || !hashtable.containsKey(Long.valueOf(j))) {
            Log.e(TAG, "unloadResource: RESOURCE NOT FOUND: " + j);
            return false;
        }
        nativeUnloadResource(this.mLoadedResources.get(Long.valueOf(j)));
        this.mLoadedResources.remove(Long.valueOf(j));
        return true;
    }

    public int getNumLoadedResources() {
        Hashtable<Long, VocalizerResourceInfo> hashtable = this.mLoadedResources;
        if (hashtable != null) {
            return hashtable.size();
        }
        return 0;
    }

    public boolean isValidLoadedResourceId(long j) {
        Hashtable<Long, VocalizerResourceInfo> hashtable = this.mLoadedResources;
        if (hashtable != null) {
            return hashtable.containsKey(Long.valueOf(j));
        }
        return false;
    }

    private VocalizerFileInfo openAssetFile(String str) {
        return this.mAssetManager.openAssetFile(str);
    }

    private void closeAssetFile(int i) {
        this.mAssetManager.closeAssetFile(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStateChanged() {
        this.mHandler.post(new Runnable() { // from class: com.nuance.android.vocalizer.VocalizerEngine.2
            @Override // java.lang.Runnable
            public void run() {
                if (VocalizerEngine.this.mEngineListener != null) {
                    VocalizerEngine.this.mEngineListener.onStateChanged(VocalizerEngine.this.mState);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySpeechElementEvent(VocalizerSpeechItem vocalizerSpeechItem, boolean z) {
        if (vocalizerSpeechItem != null) {
            this.mHandler.post(new Runnable(vocalizerSpeechItem, z) { // from class: com.nuance.android.vocalizer.VocalizerEngine.1SpeechEventNotifier
                private VocalizerSpeechItem mItem;
                private boolean mStarted;

                {
                    this.mItem = vocalizerSpeechItem;
                    this.mStarted = z;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (VocalizerEngine.this.mEngineListener != null && this.mItem.getId() != null) {
                        if (this.mStarted) {
                            VocalizerEngine.this.mEngineListener.onSpeakElementStarted(this.mItem.getId());
                        } else {
                            VocalizerEngine.this.mEngineListener.onSpeakElementCompleted(this.mItem.getId());
                        }
                    }
                    if (this.mStarted) {
                        return;
                    }
                    try {
                        this.mItem.mSemaphore.release();
                    } catch (Exception e) {
                        Log.e(VocalizerEngine.TAG, "notifySpeechElementEvent EXCEPTION setting semaphore state: " + e);
                    }
                }
            });
        }
    }

    private void printVoiceList() {
        VocalizerVoice[] vocalizerVoiceArr = this.mVoiceList;
        if (vocalizerVoiceArr == null || vocalizerVoiceArr.length == 0) {
            Log.v(TAG, "printVoiceList: NO VOICES");
            return;
        }
        Log.v(TAG, "printVoiceList. Number of voices: " + this.mVoiceList.length);
        int i = 0;
        while (true) {
            VocalizerVoice[] vocalizerVoiceArr2 = this.mVoiceList;
            if (i >= vocalizerVoiceArr2.length) {
                return;
            }
            vocalizerVoiceArr2[i].print(TAG);
            i++;
        }
    }

    public VocalizerVoice[] getVoiceList() {
        if (!isInitialized()) {
            Log.w(TAG, "getVoiceList called with uninitialized engine.");
            return null;
        }
        return this.mVoiceList;
    }

    @Override // com.nuance.android.vocalizer.internal.VocalizerInstallationListener
    public void onInstallationEvent(int i, String str) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.nuance.android.vocalizer.VocalizerEngine.3
            @Override // java.lang.Runnable
            public void run() {
                if (VocalizerEngine.this.mAutoInit) {
                    VocalizerEngine.this.mNotifyNewVoiceList = true;
                    VocalizerEngine.this.reload();
                } else if (VocalizerEngine.this.mEngineListener != null) {
                    VocalizerEngine.this.mEngineListener.onVoiceListChanged();
                }
            }
        }, 100L);
    }

    /* JADX INFO: renamed from: com.nuance.android.vocalizer.VocalizerEngine$1VoiceScore, reason: invalid class name */
    class C1VoiceScore {
        VocalizerVoice mVoice = null;
        int mScore = 0;

        C1VoiceScore() {
        }
    }

    public VocalizerVoice[] getSortedVoiceList(String str, String str2, int i) {
        boolean z;
        VocalizerVoice[] voiceList = getVoiceList();
        if (voiceList == null || voiceList.length == 0) {
            return null;
        }
        Vector vector = new Vector();
        for (VocalizerVoice vocalizerVoice : voiceList) {
            C1VoiceScore c1VoiceScore = new C1VoiceScore();
            c1VoiceScore.mVoice = vocalizerVoice;
            if (str != null && c1VoiceScore.mVoice.getVoiceName().compareToIgnoreCase(str) == 0) {
                c1VoiceScore.mScore += 4;
            }
            if (str2 != null && c1VoiceScore.mVoice.getLanguage().compareToIgnoreCase(str2) == 0) {
                c1VoiceScore.mScore += 3;
            }
            if (i != 0 && c1VoiceScore.mVoice.getSampleRate() == i) {
                c1VoiceScore.mScore += 2;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= vector.size()) {
                    z = false;
                    break;
                }
                if (c1VoiceScore.mScore > ((C1VoiceScore) vector.get(i2)).mScore) {
                    vector.insertElementAt(c1VoiceScore, i2);
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                vector.add(c1VoiceScore);
            }
        }
        VocalizerVoice[] vocalizerVoiceArr = new VocalizerVoice[vector.size()];
        for (int i3 = 0; i3 < vector.size(); i3++) {
            vocalizerVoiceArr[i3] = ((C1VoiceScore) vector.get(i3)).mVoice;
        }
        return vocalizerVoiceArr;
    }

    public void setSpeechVolume(int i) {
        if (isInitialized()) {
            setVolume(i);
        }
    }

    public void setSpeechPitch(int i) {
        if (isInitialized()) {
            setPitch(i);
        }
    }

    public void setSpeechRate(int i) {
        if (isInitialized()) {
            setRate(i);
        }
    }

    public int getSpeechVolume() {
        if (isInitialized()) {
            return getVolume();
        }
        return 80;
    }

    public int getSpeechPitch() {
        if (isInitialized()) {
            return getPitch();
        }
        return 100;
    }

    public int getSpeechRate() {
        if (isInitialized()) {
            return getRate();
        }
        return 100;
    }

    public void setSpeechTextMode(int i) {
        if (isInitialized()) {
            setTextMode(i);
        }
    }

    public int getSpeechTextMode() {
        if (isInitialized()) {
            return getTextMode();
        }
        return 1;
    }

    public void setSpeechTextFormat(int i) {
        if (isInitialized()) {
            setTextFormat(i);
        }
    }

    public int getSpeechTextFormat() {
        if (isInitialized()) {
            return getTextFormat();
        }
        return 0;
    }

    public void setSpeechReadMode(int i) {
        if (isInitialized()) {
            setReadMode(i);
        }
    }

    public int getSpeechReadMode() {
        if (isInitialized()) {
            return getReadMode();
        }
        return 1;
    }

    public void setSpeechWaitFactor(int i) {
        if (isInitialized()) {
            setWaitFactor(i);
        }
    }

    public int getSpeechWaitFactor() {
        if (isInitialized()) {
            return getWaitFactor();
        }
        return 1;
    }

    public void setSpeechThreadPriority(int i) {
        if (i > 10) {
            i = 10;
        }
        if (i < 1) {
            i = 1;
        }
        this.mSynthThreadPriority = i;
    }

    public int getSpeechThreadPriority() {
        return this.mSynthThreadPriority;
    }

    public VocalizerVersion getVersion() {
        return this.mVersion;
    }

    public void setAutoReinitialize(boolean z) {
        this.mAutoInit = z;
    }

    private boolean checkMainThread() {
        if (Thread.currentThread().getId() == this.mainThreadId) {
            return true;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length < 3) {
            return false;
        }
        Log.w(TAG, "<" + stackTrace[3].getMethodName() + "> called from wrong thread.");
        return false;
    }

    protected void finalize() throws Throwable {
        try {
            destroyNativeEngine();
        } finally {
            super.finalize();
        }
    }

    private boolean hasLeaks() {
        VocalizerStatusInfo statInfo = getStatInfo();
        if (statInfo != null) {
            return statInfo.hasLeaks();
        }
        return false;
    }

    static {
        System.loadLibrary("NuanceVocalizer");
    }
}
