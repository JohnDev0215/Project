package com.ts.appservice.atmospherelamp;

import android.app.Service;
import android.car.media.CarEnhancedAudioManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.media.AudioFocusInfo;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.media.MediaMetadata;
import android.media.audiofx.Visualizer;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.ambient.AmbientEventChangeValue;
import com.ts.lib.car.hardware.ambient.CarVendorAmbientManager;
import com.ts.lib.car.hardware.light.CarVendorLightManager;
import com.ts.lib.car.hardware.light.LightEventChangeValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AtmosphereLampService extends Service {
    private static final int AUTO_NUM = 15;
    private static final int DELAYED_NUM = 400;
    private static final int FF = 255;
    private static final int PAUSE_NUM = 3;
    private static final int PLAY_NUM = 2;
    private static final long REINIT_DELAY = 2000;
    private static final int STOP_NUM = 4;
    boolean btPlayStatus;
    private long curTime;
    private Handler handler;
    private HandlerThread handlerThread;
    String lastMediaPackage;
    private List<MediaController> mActiveSessions;
    private AudioManager mAudioManager;
    private BYDAutoInstrumentDevice mBydAutoInstrumentDevice;
    private Car mCarClient;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private CarVendorAmbientManager mCarVendorAmbientManager;
    private CarVendorLightManager mCarVendorLightManager;
    private Context mContext;
    private double mEnergyBase;
    private double mFreqBase;
    int mMusicModeStateValue;
    private double mPeekBase;
    private MediaController.Callback mSessionCallback;
    private Visualizer mVisualizer;
    private CarVendorAmbientManager mambienManager;
    boolean playerStatus;
    private static final String TAG = AtmosphereLampService.class.getSimpleName();
    private static final ArrayList<AbsBYDAutoAudioListener> BYD_AUTO_AUDIO_LISTENER = new ArrayList<>();
    private int times = 0;
    private int SEND_MESSAGE = 111;
    private boolean mCarServiceBindState = false;
    private ArrayList<Integer> mAtmosphereLampServiceArrayList = new ArrayList<>();
    private boolean mIsPlaying = false;
    private Object mObj = new Object();
    private final CarConnectionCallback mCarConnectionCallback = new CarConnectionCallback() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.2
        @Override // com.ts.lib.car.CarConnectionCallback
        public void onConnected(Car car) {
            Log.d(AtmosphereLampService.TAG, "onConnected");
            try {
                AtmosphereLampService.this.mCarVendorAmbientManager = (CarVendorAmbientManager) car.getCarManager(Car.VENDOR_AMBIENT_SERVICE);
                AtmosphereLampService.this.mCarVendorAmbientManager.addCarAmbientEventListener(AtmosphereLampService.this.mOnCarAmbientEventListenerr);
                AtmosphereLampService.this.mCarVendorLightManager = (CarVendorLightManager) car.getCarManager(Car.VENDOR_LIGHT_SERVICE);
                AtmosphereLampService.this.mCarVendorLightManager.addCarLightEventListener(AtmosphereLampService.this.mOnCarLightEventListenerr);
                synchronized (AtmosphereLampService.this.mObj) {
                    AtmosphereLampService.this.mMusicModeStateValue = AtmosphereLampService.this.mCarVendorLightManager.getAmbientMusicModeStateR();
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }

        @Override // com.ts.lib.car.CarConnectionCallback
        public void onDisconnected(Car car) {
            Log.w(AtmosphereLampService.TAG, "atmoservice is onDisconnected");
            AtmosphereLampService.this.mCarServiceBindState = false;
            AtmosphereLampService.this.mCarClient.disconnect();
            AtmosphereLampService.this.mCarClient = null;
            AtmosphereLampService.this.clearTsManager();
            AtmosphereLampService.this.releaseManager();
            AtmosphereLampService.this.reinitCar();
        }
    };
    private final AbsBYDAutoInstrumentListener mAbsBydAutoInstrumentListener = new AbsBYDAutoInstrumentListener() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.3
        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMusicInfoUrlChange(String musicName, String artistName, int playState, int musicSource, String imgUrl) {
            if (AtmosphereLampService.this.isMediaBelongTo(musicSource)) {
                boolean curStatus = playState == 1;
                if (curStatus == AtmosphereLampService.this.playerStatus) {
                    Log.v("AbsBYDAutoInstrumentListener", "play state is not change");
                    return;
                } else if (curStatus) {
                    AtmosphereLampService.this.playerStatus = true;
                    AtmosphereLampService.this.startTask();
                } else {
                    AtmosphereLampService.this.playerStatus = false;
                }
            } else {
                AtmosphereLampService.this.playerStatus = false;
            }
            Log.v("AbsBYDAutoInstrumentListener", " mAbsBydAutoInstrumentListener playState" + playState + " musicSource : " + musicSource);
        }
    };
    private final CarVendorLightManager.OnCarLightEventListener mOnCarLightEventListenerr = new CarVendorLightManager.OnCarLightEventListener() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.4
        @Override // com.ts.lib.car.hardware.light.CarVendorLightManager.OnCarLightEventListener
        public void onLightEventChange(LightEventChangeValue value) {
            if (value.getPropertyId() == 557860611) {
                synchronized (AtmosphereLampService.this.mObj) {
                    AtmosphereLampService.this.mMusicModeStateValue = value.getAmbientMusicModeStateR();
                    if (AtmosphereLampService.this.mMusicModeStateValue == 1) {
                        AtmosphereLampService.this.startTask();
                    } else {
                        if (AtmosphereLampService.this.mMusicModeStateValue == 2) {
                            try {
                                if (AtmosphereLampService.this.mCarVendorAmbientManager != null) {
                                    AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingStatusS(2);
                                    AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingModeS(0);
                                }
                            } catch (CarNotConnectedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        @Override // com.ts.lib.car.hardware.light.CarVendorLightManager.OnCarLightEventListener
        public void onLightErrorEvent(int propertyId, int zone) {
        }
    };
    private final CarVendorAmbientManager.OnCarAmbientEventListener mOnCarAmbientEventListenerr = new CarVendorAmbientManager.OnCarAmbientEventListener() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.5
        @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager.OnCarAmbientEventListener
        public void onAmbientEventChange(AmbientEventChangeValue ambientEventChangeValue) {
        }

        @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager.OnCarAmbientEventListener
        public void onAmbientErrorEvent(int i, int i1) {
        }
    };
    Visualizer.MeasurementPeakRms measurementPeakRms = new Visualizer.MeasurementPeakRms();
    private final AudioManager.AudioPlaybackCallback mAudioPlaybackCallback = new AudioManager.AudioPlaybackCallback() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.7
        @Override // android.media.AudioManager.AudioPlaybackCallback
        public void onPlaybackConfigChanged(List<AudioPlaybackConfiguration> configs) {
            super.onPlaybackConfigChanged(configs);
            Log.w(AtmosphereLampService.TAG, "configs.size():" + configs.size());
            synchronized (AtmosphereLampService.this.mObj) {
                if (configs.size() == 0) {
                    Log.v(AtmosphereLampService.TAG, "playerStatus configs.size is null:" + AtmosphereLampService.this.playerStatus);
                    AtmosphereLampService.this.playerStatus = false;
                } else {
                    for (int i = 0; i < configs.size(); i++) {
                        int usage = configs.get(i).getAudioAttributes().getUsage();
                        configs.get(i).getPlayerState();
                        if (AtmosphereLampService.this.isUsageBelongToMedia(usage)) {
                            AtmosphereLampService.this.mAtmosphereLampServiceArrayList.add(Integer.valueOf(configs.get(i).getPlayerState()));
                            Log.d(AtmosphereLampService.TAG, "get[" + i + "] playSta: " + configs.get(i).getPlayerState());
                        }
                    }
                    if (!AtmosphereLampService.this.mAtmosphereLampServiceArrayList.contains(2) || AtmosphereLampService.this.mAtmosphereLampServiceArrayList.contains(3) || AtmosphereLampService.this.mAtmosphereLampServiceArrayList.contains(4)) {
                        Log.v(AtmosphereLampService.TAG, "cgetPlayerState: is not 2" + AtmosphereLampService.this.mAtmosphereLampServiceArrayList.toString());
                        AtmosphereLampService.this.playerStatus = false;
                        try {
                            if (AtmosphereLampService.this.mCarVendorAmbientManager != null) {
                                AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingStatusS(2);
                                AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingModeS(0);
                            }
                        } catch (CarNotConnectedException e) {
                            e.printStackTrace();
                        }
                        AtmosphereLampService.this.mAtmosphereLampServiceArrayList.clear();
                    } else {
                        Log.v(AtmosphereLampService.TAG, "cgetPlayerState: 2" + AtmosphereLampService.this.mAtmosphereLampServiceArrayList.toString());
                        AtmosphereLampService.this.playerStatus = true;
                        AtmosphereLampService.this.startTask();
                        AtmosphereLampService.this.mAtmosphereLampServiceArrayList.clear();
                    }
                }
            }
        }
    };
    private CarEnhancedAudioManager.CarFocusCallback mCarFocusCallback = new CarEnhancedAudioManager.CarFocusCallback() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.10
        public void onFocusChanged(int audioZoneId, List<AudioFocusInfo> focusHolders) {
            for (AudioFocusInfo audioFocusInfo : focusHolders) {
                int usage = audioFocusInfo.getAttributes().getUsage();
                if (usage == 0) {
                    Log.e(AtmosphereLampService.TAG, "USAGE_UNKNOWN");
                } else if (usage != 1) {
                    if (usage == 24) {
                        Log.e(AtmosphereLampService.TAG, "USAGE_GAME");
                        Log.e(AtmosphereLampService.TAG, "USAGE_AAUTO_MEDIA");
                        Log.e(AtmosphereLampService.TAG, "USAGE_CP_TEMP_MEDIA");
                        AtmosphereLampService.this.lastMediaPackage = audioFocusInfo.getPackageName();
                    } else if (usage == 25) {
                        Log.e(AtmosphereLampService.TAG, "USAGE_CP_TEMP_MEDIA");
                        AtmosphereLampService.this.lastMediaPackage = audioFocusInfo.getPackageName();
                    } else if (usage == 33) {
                        Log.e(AtmosphereLampService.TAG, "USAGE_AAUTO_MEDIA");
                        Log.e(AtmosphereLampService.TAG, "USAGE_CP_TEMP_MEDIA");
                        AtmosphereLampService.this.lastMediaPackage = audioFocusInfo.getPackageName();
                    }
                }
                Log.e(AtmosphereLampService.TAG, "USAGE_MEDIA");
                Log.e(AtmosphereLampService.TAG, "USAGE_GAME");
                Log.e(AtmosphereLampService.TAG, "USAGE_AAUTO_MEDIA");
                Log.e(AtmosphereLampService.TAG, "USAGE_CP_TEMP_MEDIA");
                AtmosphereLampService.this.lastMediaPackage = audioFocusInfo.getPackageName();
            }
            for (AbsBYDAutoAudioListener listener : AtmosphereLampService.BYD_AUTO_AUDIO_LISTENER) {
                Log.d(AtmosphereLampService.TAG, "BYDAudio onFocusChanged");
                listener.onAudioFocusChanged(audioZoneId, focusHolders);
            }
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new AtmosphereLampBinder();
    }

    class AtmosphereLampBinder extends Binder {
        AtmosphereLampBinder() {
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        initCar();
        initVisualizer();
        Log.w(TAG, "oncreate");
        AtmosphereLampService();
        this.mAudioManager = (AudioManager) getSystemService("audio");
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(this);
        this.mBydAutoInstrumentDevice = bYDAutoInstrumentDevice;
        bYDAutoInstrumentDevice.registerListener(this.mAbsBydAutoInstrumentListener);
    }

    public void AtmosphereLampService() {
        this.curTime = System.currentTimeMillis();
        this.mFreqBase = 6.1d;
        this.mPeekBase = 52426.2d;
        this.mEnergyBase = 30000.0d;
        final int[] value = new int[16];
        final int interval = 8;
        final int[] frequency_seq = {40, 80, 120, 200, 250, 300, 400, 500, 600, 700, 800, 900, 1200, 1600, 1800, 3500};
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper()) { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Log.v(AtmosphereLampService.TAG, "handler playerStatus handler is :" + AtmosphereLampService.this.playerStatus + "   btPlayStatus  is : " + AtmosphereLampService.this.btPlayStatus + "mMusicModeStateValue is " + AtmosphereLampService.this.mMusicModeStateValue);
                if (msg.what == AtmosphereLampService.this.SEND_MESSAGE) {
                    synchronized (AtmosphereLampService.this.mObj) {
                        if (AtmosphereLampService.this.playerStatus && AtmosphereLampService.this.mMusicModeStateValue == 1) {
                            long lll = System.currentTimeMillis();
                            AtmosphereLampService.this.curTime = lll;
                            byte[] fftBytes = new byte[AtmosphereLampService.this.mVisualizer.getCaptureSize()];
                            AtmosphereLampService.this.mVisualizer.getFft(fftBytes);
                            float[] model = new float[(fftBytes.length / 2) + 1];
                            model[0] = Math.abs((int) fftBytes[0]);
                            int i = 2;
                            for (int j = 1; j < 400; j++) {
                                model[j] = (float) Math.hypot(fftBytes[i], fftBytes[i + 1]);
                                i += 2;
                            }
                            for (int i2 = 0; i2 < 16; i2++) {
                                int m = frequency_seq[i2] / interval;
                                value[i2] = (int) model[m];
                                if (value[i2] == 0) {
                                    value[i2] = 1;
                                }
                                if (value[i2] > 15) {
                                    value[i2] = 15;
                                }
                            }
                            try {
                                if (AtmosphereLampService.this.mCarVendorAmbientManager != null) {
                                    Log.v(AtmosphereLampService.TAG, "value = " + Arrays.toString(value));
                                    AtmosphereLampService.this.mCarVendorAmbientManager.setFrequencyPoint(value);
                                    AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingStatusS(1);
                                    AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingModeS(2);
                                }
                            } catch (CarNotConnectedException e) {
                                e.printStackTrace();
                            } catch (NullPointerException eX) {
                                eX.printStackTrace();
                            }
                            long delay = 400 - (System.currentTimeMillis() - lll);
                            AtmosphereLampService.this.handler.sendEmptyMessageDelayed(AtmosphereLampService.this.SEND_MESSAGE, delay);
                        } else {
                            try {
                            } catch (CarNotConnectedException e2) {
                                e2.printStackTrace();
                            }
                            if (AtmosphereLampService.this.mCarVendorAmbientManager != null) {
                                AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingStatusS(2);
                                AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingModeS(0);
                            }
                        }
                    }
                }
            }
        };
    }

    private void initCar() {
        Log.d(TAG, "initCar start");
        Car carCreateCar = Car.createCar(getApplicationContext(), this.mCarConnectionCallback);
        this.mCarClient = carCreateCar;
        carCreateCar.connect();
        Log.d(TAG, "initCar end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reinitCar() {
        new Handler().postDelayed(new Runnable() { // from class: com.ts.appservice.atmospherelamp.-$$Lambda$AtmosphereLampService$Zx2LrgnhqG_OGkeQ751Tq88H4NM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$reinitCar$0$AtmosphereLampService();
            }
        }, REINIT_DELAY);
    }

    public /* synthetic */ void lambda$reinitCar$0$AtmosphereLampService() {
        Log.w(TAG, "reinitCar start");
        Car carCreateCar = Car.createCar(getApplicationContext(), this.mCarConnectionCallback);
        this.mCarClient = carCreateCar;
        carCreateCar.connect();
        Log.w(TAG, "reinitCar end");
    }

    public void startTask() {
        synchronized (AtmosphereLampService.class) {
            this.handler.sendEmptyMessageDelayed(this.SEND_MESSAGE, 25L);
        }
    }

    public void release() {
        synchronized (AtmosphereLampService.class) {
            if (this.mVisualizer != null) {
                Log.d(TAG, "release");
                this.mVisualizer.setEnabled(false);
                this.mVisualizer.release();
                this.mVisualizer = null;
            }
        }
    }

    public void initVisualizer() {
        Visualizer visualizer = new Visualizer(0);
        this.mVisualizer = visualizer;
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        this.mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.6
            @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
            public void onWaveFormDataCapture(Visualizer visualizer2, byte[] bytes, int samplingRate) {
            }

            @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
            public void onFftDataCapture(Visualizer visualizer2, byte[] fftBytes, int samplingRate) {
            }
        }, Visualizer.getMaxCaptureRate(), false, false);
        this.mVisualizer.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUsageBelongToMedia(int usage) {
        if (usage == 0 || usage == 1 || usage == 24 || usage == 25 || usage == 33) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMediaBelongTo(int source) {
        if (source == 1 || source == 2 || source == 3 || source == 4 || source == 6 || source == 16 || source == 30 || source == 31) {
            return true;
        }
        return false;
    }

    private void initMediaSessionManager() {
        MediaSessionManager mediaSessionManager = (MediaSessionManager) getSystemService("media_session");
        ComponentName localComponentName = new ComponentName(this, (Class<?>) AtmosphereLampService.class);
        mediaSessionManager.addOnActiveSessionsChangedListener(new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.8
            @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
            public void onActiveSessionsChanged(List<MediaController> controllers) {
                for (MediaController mediaController : controllers) {
                    String packageName = mediaController.getPackageName();
                    Log.v(AtmosphereLampService.TAG, "MyApplication onActiveSessionsChanged mediaController.getPackageName: " + packageName);
                    synchronized (this) {
                        AtmosphereLampService.this.mActiveSessions = controllers;
                        AtmosphereLampService.this.registerSessionCallbacks();
                    }
                }
            }
        }, localComponentName);
        synchronized (this) {
            this.mActiveSessions = mediaSessionManager.getActiveSessions(localComponentName);
            registerSessionCallbacks();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerSessionCallbacks() {
        for (MediaController controller : this.mActiveSessions) {
            if (this.mSessionCallback == null) {
                this.mSessionCallback = new MediaController.Callback() { // from class: com.ts.appservice.atmospherelamp.AtmosphereLampService.9
                    @Override // android.media.session.MediaController.Callback
                    public void onMetadataChanged(MediaMetadata metadata) {
                        if (metadata != null) {
                            String trackName = metadata.getString("android.media.metadata.TITLE");
                            String artistName = metadata.getString("android.media.metadata.ARTIST");
                            String albumArtistName = metadata.getString("android.media.metadata.ALBUM_ARTIST");
                            String albumName = metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM);
                            Log.i(AtmosphereLampService.TAG, "---------------------------------");
                            Log.i(AtmosphereLampService.TAG, "| trackName: " + trackName);
                            Log.i(AtmosphereLampService.TAG, "| artistName: " + artistName);
                            Log.i(AtmosphereLampService.TAG, "| albumArtistName: " + albumArtistName);
                            Log.i(AtmosphereLampService.TAG, "| albumName: " + albumName);
                            Log.i(AtmosphereLampService.TAG, "---------------------------------");
                        }
                    }

                    @Override // android.media.session.MediaController.Callback
                    public void onPlaybackStateChanged(PlaybackState state) {
                        if (state != null) {
                            boolean curState = state.getState() == 3;
                            if (curState == AtmosphereLampService.this.btPlayStatus) {
                                Log.v(AtmosphereLampService.TAG, "play state is not change");
                                return;
                            }
                            if (curState) {
                                AtmosphereLampService.this.btPlayStatus = true;
                                AtmosphereLampService.this.startTask();
                            } else {
                                AtmosphereLampService.this.btPlayStatus = false;
                                try {
                                    if (AtmosphereLampService.this.mCarVendorAmbientManager != null) {
                                        AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingStatusS(2);
                                        AtmosphereLampService.this.mCarVendorAmbientManager.setMediaWorkingModeS(0);
                                    }
                                } catch (CarNotConnectedException e) {
                                    e.printStackTrace();
                                }
                            }
                            Log.v(AtmosphereLampService.TAG, "MediaController.Callback onPlaybackStateChanged isPlaying: " + AtmosphereLampService.this.btPlayStatus);
                        }
                    }
                };
            }
            controller.registerCallback(this.mSessionCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTsManager() {
        try {
            if (this.mCarVendorAmbientManager != null) {
                this.mCarVendorAmbientManager = null;
            }
        } catch (Exception except) {
            Log.e(TAG, "Car not connected in onServiceConnected");
            except.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseManager() {
    }
}
