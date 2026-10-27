package com.android.bluetooth.a2dpsink;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.R;
import com.android.bluetooth.avrcpcontroller.BluetoothMediaBrowserService;
import com.android.bluetooth.hfpclient.HeadsetClientService;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class A2dpSinkStreamHandler extends Handler {
    public static final int AUDIO_FOCUS_CHANGE = 7;
    private static final int DEFAULT_DUCK_PERCENT = 25;
    public static final int DELAYED_PAUSE = 9;
    public static final int DISCONNECT = 6;
    public static final int REQUEST_FOCUS = 8;
    private static final int SETTLE_TIMEOUT = 400;
    public static final int SNK_PAUSE = 3;
    public static final int SNK_PLAY = 2;
    public static final int SRC_PAUSE = 5;
    public static final int SRC_PLAY = 4;
    public static final int SRC_STR_START = 0;
    public static final int SRC_STR_STOP = 1;
    private static final int STATE_FOCUS_GRANTED = 1;
    private static final int STATE_FOCUS_LOST = 0;
    private A2dpSinkService mA2dpSinkService;
    private AudioManager mAudioManager;
    private Context mContext;
    private static final String TAG = "A2dpSinkStreamHandler";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    private boolean mStreamAvailable = false;
    private boolean mSentPause = false;
    private int mAudioFocus = 0;
    private MediaPlayer mMediaPlayer = null;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.android.bluetooth.a2dpsink.A2dpSinkStreamHandler.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int focusChange) {
            if (A2dpSinkStreamHandler.DBG) {
                Log.d(A2dpSinkStreamHandler.TAG, "onAudioFocusChangeListener focuschange " + focusChange);
            }
            A2dpSinkStreamHandler.this.obtainMessage(7, Integer.valueOf(focusChange)).sendToTarget();
        }
    };

    public A2dpSinkStreamHandler(A2dpSinkService a2dpSinkService, Context context) {
        this.mA2dpSinkService = a2dpSinkService;
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public void cleanup() {
        abandonAudioFocus();
        removeCallbacksAndMessages(null);
    }

    void requestAudioFocus(boolean request) {
        obtainMessage(8, Boolean.valueOf(request)).sendToTarget();
    }

    int getFocusState() {
        return this.mAudioFocus;
    }

    boolean isPlaying() {
        int i;
        return this.mStreamAvailable && ((i = this.mAudioFocus) == 1 || i == -3);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (DBG) {
            Log.w(TAG, " process message: " + message.what);
            Log.w(TAG, " audioFocus =  " + this.mAudioFocus);
        }
        switch (message.what) {
            case 0:
                this.mStreamAvailable = true;
                if (isTvDevice() || shouldRequestFocus()) {
                    requestAudioFocusIfNone();
                }
                Log.w(TAG, " MediaSessionService: setActive(true)");
                BluetoothMediaBrowserService.setActive(true);
                break;
            case 1:
                break;
            case 2:
                requestAudioFocusIfNone();
                break;
            case 3:
                this.mStreamAvailable = false;
                break;
            case 4:
                this.mStreamAvailable = true;
                if (isIotDevice() || isTvDevice() || shouldRequestFocus()) {
                    requestAudioFocusIfNone();
                }
                break;
            case 5:
                this.mStreamAvailable = false;
                break;
            case 6:
                this.mSentPause = false;
                break;
            case 7:
                this.mAudioFocus = ((Integer) message.obj).intValue();
                Log.w(TAG, "AUDIO_FOCUS_CHANGE  mAudioFocus==" + this.mAudioFocus);
                int i = this.mAudioFocus;
                if (i == -3) {
                    int duckPercent = this.mContext.getResources().getInteger(R.integer.a2dp_sink_duck_percent);
                    if (duckPercent < 0 || duckPercent > 100) {
                        Log.e(TAG, "Invalid duck percent using default.");
                        duckPercent = 25;
                    }
                    float duckRatio = duckPercent / 100.0f;
                    if (DBG) {
                        Log.d(TAG, "Setting reduce gain on transient loss gain=" + duckRatio);
                    }
                    setFluorideAudioTrackGain(duckRatio);
                    break;
                } else if (i == -2) {
                    sendMessageDelayed(obtainMessage(9), 400L);
                    setFluorideAudioTrackGain(0.0f);
                    break;
                } else if (i == -1) {
                    abandonAudioFocus();
                    sendAvrcpPause();
                    break;
                } else if (i == 1) {
                    removeMessages(9);
                    Log.w(TAG, "AUDIO_FOCUS_CHANGE start startFluorideStreaming\u3000mSentPause: " + this.mSentPause);
                    startFluorideStreaming();
                    if (this.mSentPause) {
                        sendAvrcpPlay();
                        this.mSentPause = false;
                    }
                    break;
                }
                break;
            case 8:
                requestAudioFocusIfNone();
                break;
            case 9:
                int playState = BluetoothMediaBrowserService.getPlaybackState();
                boolean inCallFromStreamingDevice = inCallFromStreamingDevice();
                StringBuilder sb = new StringBuilder();
                sb.append("DELAYED_PAUSE getPlaybackState(): ");
                sb.append(playState);
                sb.append("  inCallFromStreamingDevice():");
                sb.append(!inCallFromStreamingDevice);
                Log.w(TAG, sb.toString());
                if (playState == 3 && !inCallFromStreamingDevice) {
                    sendAvrcpPause();
                    this.mSentPause = true;
                    this.mStreamAvailable = false;
                    break;
                }
                break;
            default:
                Log.w(TAG, "Received unexpected event: " + message.what);
                break;
        }
    }

    void requestAudioFocusIfNone() {
        if (DBG) {
            Log.d(TAG, "requestAudioFocusIfNone()");
        }
        if (this.mAudioFocus != 1) {
            requestAudioFocus();
        }
    }

    private synchronized int requestAudioFocus() {
        int focusRequestStatus;
        if (DBG) {
            Log.d(TAG, "requestAudioFocus()");
        }
        AudioAttributes streamAttributes = new AudioAttributes.Builder().setUsage(1).setContentType(0).build();
        AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(streamAttributes).setOnAudioFocusChangeListener(this.mAudioFocusListener, this).build();
        focusRequestStatus = this.mAudioManager.requestAudioFocus(focusRequest);
        if (focusRequestStatus == 1) {
            startFluorideStreaming();
            this.mAudioFocus = 1;
        }
        return focusRequestStatus;
    }

    private synchronized void requestMediaKeyFocus() {
        if (DBG) {
            Log.w(TAG, "requestMediaKeyFocus()");
        }
        if (this.mMediaPlayer == null) {
            AudioAttributes attrs = new AudioAttributes.Builder().setUsage(1).build();
            MediaPlayer mediaPlayerCreate = MediaPlayer.create(this.mContext, R.raw.silent, attrs, this.mAudioManager.generateAudioSessionId());
            this.mMediaPlayer = mediaPlayerCreate;
            if (mediaPlayerCreate == null) {
                Log.e(TAG, "Failed to initialize media player. You may not get media key events");
                return;
            } else {
                mediaPlayerCreate.setLooping(false);
                this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.android.bluetooth.a2dpsink.-$$Lambda$A2dpSinkStreamHandler$PPcVodXV7oIPabVMroFxjVNhk2o
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        return this.f$0.lambda$requestMediaKeyFocus$0$A2dpSinkStreamHandler(mediaPlayer, i, i2);
                    }
                });
            }
        }
        Log.w(TAG, "mMediaPlayer start");
        this.mMediaPlayer.start();
        BluetoothMediaBrowserService.setActive(true);
    }

    public /* synthetic */ boolean lambda$requestMediaKeyFocus$0$A2dpSinkStreamHandler(MediaPlayer mp, int what, int extra) {
        Log.e(TAG, "Silent media player error: " + what + ", " + extra);
        releaseMediaKeyFocus();
        return false;
    }

    private synchronized void abandonAudioFocus() {
        if (DBG) {
            Log.d(TAG, "abandonAudioFocus()");
        }
        stopFluorideStreaming();
        this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
        this.mAudioFocus = 0;
    }

    private synchronized void releaseMediaKeyFocus() {
        if (DBG) {
            Log.d(TAG, "releaseMediaKeyFocus()");
        }
        if (this.mMediaPlayer == null) {
            return;
        }
        BluetoothMediaBrowserService.setActive(false);
        this.mMediaPlayer.stop();
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
    }

    private void startFluorideStreaming() {
        Log.w(TAG, "startFluorideStreaming");
        this.mA2dpSinkService.informAudioFocusStateNative(1);
        this.mA2dpSinkService.informAudioTrackGainNative(1.0f);
        Log.w(TAG, "requestMediaKeyFocus");
        requestMediaKeyFocus();
    }

    private void stopFluorideStreaming() {
        releaseMediaKeyFocus();
        this.mA2dpSinkService.informAudioFocusStateNative(0);
    }

    private void setFluorideAudioTrackGain(float gain) {
        this.mA2dpSinkService.informAudioTrackGainNative(gain);
    }

    private void sendAvrcpPause() {
        BluetoothMediaBrowserService.pause();
    }

    private void sendAvrcpPlay() {
        BluetoothMediaBrowserService.play();
    }

    private boolean inCallFromStreamingDevice() {
        List<BluetoothHeadsetClientCall> currentCalls;
        BluetoothDevice targetDevice = null;
        List<BluetoothDevice> connectedDevices = this.mA2dpSinkService.getConnectedDevices();
        if (!connectedDevices.isEmpty()) {
            BluetoothDevice targetDevice2 = connectedDevices.get(0);
            targetDevice = targetDevice2;
        }
        HeadsetClientService headsetClientService = HeadsetClientService.getHeadsetClientService();
        return (targetDevice == null || headsetClientService == null || (currentCalls = headsetClientService.getCurrentCalls(targetDevice)) == null || currentCalls.size() <= 0) ? false : true;
    }

    synchronized int getAudioFocus() {
        return this.mAudioFocus;
    }

    private boolean isIotDevice() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.type.embedded");
    }

    private boolean isTvDevice() {
        return this.mContext.getPackageManager().hasSystemFeature("android.software.leanback");
    }

    private boolean shouldRequestFocus() {
        return this.mContext.getResources().getBoolean(R.bool.a2dp_sink_automatically_request_audio_focus);
    }
}
