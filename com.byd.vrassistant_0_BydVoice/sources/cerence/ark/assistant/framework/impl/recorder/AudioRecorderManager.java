package cerence.ark.assistant.framework.impl.recorder;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.SampleRate;
import cerence.ark.assistant.framework.audio.recorder.IAudioRecorder;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes.dex */
public final class AudioRecorderManager implements Handler.Callback {
    private static final int FLAG_DESTROY = 16;
    private static final int FLAG_GET_AUDIO = 13;
    private static final int FLAG_INITIALIZE = 11;
    public static final int MIC_AUDIO_CHUNK_SIZE = 2560;
    public static final String TAG = "AudioRecorderManager";
    private static final int THREAD_PRIORITY = 8;
    private final IAudioListener mAudioListener;
    private IAudioRecorder mAudioRecorder;
    private Handler mHandler;
    private volatile boolean mInitialized = false;

    public AudioRecorderManager(IAudioListener iAudioListener) {
        this.mAudioListener = iAudioListener;
    }

    public void initialize(IAudioRecorder iAudioRecorder, IResultCallback<Void> iResultCallback) {
        if (isInitialized()) {
            Utils.notifyFailure(TAG, "initialize", "the audio recorder already initialized", iResultCallback);
            return;
        }
        if (iAudioRecorder == null) {
            throw new IllegalArgumentException("the audio recorder cannot be null");
        }
        Logger.debug(TAG, "initialize");
        this.mInitialized = true;
        HandlerThread handlerThread = new HandlerThread("audio_recorder");
        handlerThread.setPriority(8);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), this);
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setCallBack(iResultCallback);
        messageWrapper.setAudioRecorder(iAudioRecorder);
        Message messageObtain = Message.obtain();
        messageObtain.what = 11;
        messageObtain.obj = messageWrapper;
        this.mHandler.sendMessage(messageObtain);
    }

    public void startAudioRecording() {
        if (!isInitialized()) {
            Logger.error(TAG, "startAudioRecording: the audio recorder not initialized");
            return;
        }
        Logger.debug(TAG, "startAudioRecording");
        if (this.mHandler.hasMessages(13)) {
            return;
        }
        this.mHandler.sendEmptyMessage(13);
    }

    public boolean isInitialized() {
        return this.mInitialized;
    }

    public void destroy(IResultCallback<Void> iResultCallback) {
        if (!isInitialized()) {
            Logger.error(TAG, "destroy: the audio recorder not initialized");
            return;
        }
        Logger.debug(TAG, "destroy");
        this.mInitialized = false;
        this.mHandler.removeCallbacksAndMessages(null);
        Message messageObtain = Message.obtain();
        messageObtain.what = 16;
        messageObtain.obj = iResultCallback;
        this.mHandler.sendMessageAtFrontOfQueue(messageObtain);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 11) {
            MessageWrapper messageWrapper = (MessageWrapper) message.obj;
            IResultCallback<Void> callBack = messageWrapper.getCallBack();
            this.mAudioRecorder = messageWrapper.getAudioRecorder();
            String str = TAG;
            Logger.debug(str, "initialize audio recorder: " + this.mAudioRecorder.getClass().getSimpleName());
            if (this.mAudioRecorder.onInitialize(AudioChannel.CHANNEL_MONO, AudioFormat.PCM_16BIT, SampleRate.SAMPLE_RATE_HZ_16000)) {
                Utils.notifySuccess(callBack);
                return true;
            }
            this.mAudioRecorder = null;
            Utils.notifyFailure(str, "handleMessage", "Initialize audio recorder error.", callBack);
            return true;
        }
        if (i == 13) {
            getAudio();
            return true;
        }
        if (i != 16) {
            return true;
        }
        Logger.debug(TAG, "destroy manager");
        this.mHandler.removeCallbacksAndMessages(null);
        onReleaseAudioRecorder();
        Object obj = message.obj;
        if (obj instanceof IResultCallback) {
            Utils.notifySuccess((IResultCallback) obj);
        }
        this.mHandler.getLooper().quit();
        return true;
    }

    private void getAudio() {
        if (!isInitialized()) {
            Logger.error(TAG, "getAudio: the audio recorder not initialized");
            this.mHandler.removeMessages(13);
            return;
        }
        IAudioRecorder iAudioRecorder = this.mAudioRecorder;
        if (iAudioRecorder == null) {
            Logger.warn(TAG, "getAudio: audio recorder is null");
            this.mHandler.removeMessages(13);
            return;
        }
        byte[] bArrOnGetAudio = iAudioRecorder.onGetAudio(2560);
        if (bArrOnGetAudio == null) {
            Logger.error(TAG, "getAudio: get audio data is null");
            delayGetAudio();
            return;
        }
        if (bArrOnGetAudio.length != 2560) {
            Logger.warn(TAG, String.format("audio data size does not match requested size, length = %s, requested = %s", Integer.valueOf(bArrOnGetAudio.length), 2560));
        }
        IAudioListener iAudioListener = this.mAudioListener;
        if (iAudioListener != null) {
            iAudioListener.onAudioAvailable(bArrOnGetAudio);
        }
        if (this.mHandler.hasMessages(13)) {
            return;
        }
        this.mHandler.sendEmptyMessage(13);
    }

    private void delayGetAudio() {
        this.mHandler.removeMessages(13);
        this.mHandler.sendEmptyMessageDelayed(13, 100L);
    }

    private void onReleaseAudioRecorder() {
        IAudioRecorder iAudioRecorder = this.mAudioRecorder;
        if (iAudioRecorder != null) {
            iAudioRecorder.onRelease();
            this.mAudioRecorder = null;
        }
    }

    private static class MessageWrapper {
        private IAudioRecorder mAudioRecorder;
        private IResultCallback<Void> mCallBack;

        private MessageWrapper() {
        }

        public IResultCallback<Void> getCallBack() {
            return this.mCallBack;
        }

        public void setCallBack(IResultCallback<Void> iResultCallback) {
            this.mCallBack = iResultCallback;
        }

        public IAudioRecorder getAudioRecorder() {
            return this.mAudioRecorder;
        }

        public void setAudioRecorder(IAudioRecorder iAudioRecorder) {
            this.mAudioRecorder = iAudioRecorder;
        }
    }
}
