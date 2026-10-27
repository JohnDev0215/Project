package com.ts.androidauto.aap.audio;

import android.content.Context;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.AudioSink;
import com.google.android.projection.protocol.GalReceiver;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.aap.audiofocus.AapAudioFocusManager;
import com.ts.androidauto.aap.audiofocus.StateManager;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes.dex */
public class AapAudioManager {
    private static final int DATA_TYPE_ASR = 1;
    private static final int DATA_TYPE_DEFAULT = -1;
    private static final int DATA_TYPE_GUIDANCE = 2;
    private static final int DATA_TYPE_MEDIA = 0;
    public static final String MEDIA_PATH = "/sdcard/media_stream.aac";
    public static final String RECORDER_PATH = "/sdcard/recorder_stream.pcm";
    private static final String TAG = "AAP" + AapAudioManager.class.getSimpleName();
    public static final String TTS_PATH = "/sdcard/tts_stream.pcm";
    private static AapAudioManager mAapAudioManager;
    private AudioSink.AudioListener[] audioListeners;
    private final AudioSink.AudioListener mAudioAacTtsStreamListener;
    private IAudioControllerAdapter mAudioControllerAdapter;
    private GalReceiver.AudioFocusListener mAudioFocusListener;
    private AapAudioFocusManager mAudioFocusManager;
    private AudioPlayer mAudioSinkAsrStreamPlayer;
    private AudioPlayer mAudioSinkGuidanceStreamPlayer;
    private AudioPlayer mAudioSinkMediaAsrPlayer;
    private AudioPlayer mAudioSinkMediaStreamPlayer;
    private AudioPlayer mAudioSinkTtsStreamPlayer;
    private Context mContext;
    private GalIntegration mGal;
    private AudioPlayer mGuidanceSinkAsrAacPlayer;
    private AudioPlayer mGuidanceSinkGuidanceAacPlayer;
    private RandomAccessFile mMediaRandomFile;
    private AudioPlayer mMediaSinkAsrAacPlayer;
    private AudioPlayer mMediaSinkMediaAacPlayer;
    private boolean mMediaStreamSwitcher;
    private AudioPlayer mMeidaSinkGuidanceAacPlayer;
    private MicrophoneRecorder mMicrophoneRecorder;
    private RandomAccessFile mRecorderRandomFile;
    private RandomAccessFile mTtsRandomFile;
    private boolean mTtsStreamSwitcher;
    private int mGuidanceStreamDataType = -1;
    private int mMediaStreamDataType = -1;
    private int mMediaStreamAacDataType = -1;
    private int mGuidanceStreamAacDataType = -1;
    private int mMediaStreamMediaCodecType = 0;
    private int mGuidanceStreamMediaCodecType = 0;
    private boolean mIsWireConnect = false;
    private boolean mIsMediaStreamSetup = false;
    private boolean mIsMediaStreamGuidanceSetup = false;
    private boolean mIsMediaStreamAsrSetup = false;
    private boolean mIsGuidanceStreamSetup = false;
    private boolean mIsGuidanceStreamAsrSetup = false;
    private final AudioSink.AudioListener mAudioMediaStreamListener = new AudioSink.AudioListener() { // from class: com.ts.androidauto.aap.audio.AapAudioManager.1
        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onSetUp(int mediaCodecType) {
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onSetUp start:" + mediaCodecType);
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onSetUp mIsWireConnect:" + AapAudioManager.this.mIsWireConnect);
            AapAudioManager.this.mMediaStreamDataType = -1;
            AapAudioManager.this.mMediaStreamMediaCodecType = mediaCodecType;
            if (AapAudioManager.this.mIsWireConnect) {
                if (AapAudioManager.this.mAudioControllerAdapter != null && AapAudioManager.this.mContext != null) {
                    if (AapAudioManager.this.mAudioSinkGuidanceStreamPlayer != null) {
                        AapAudioManager.this.mIsMediaStreamSetup = true;
                        AapAudioManager.this.mAudioSinkGuidanceStreamPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_NAVI), AapAudioManager.this.mContext);
                    }
                    if (AapAudioManager.this.mAudioSinkMediaStreamPlayer != null) {
                        AapAudioManager.this.mIsMediaStreamGuidanceSetup = true;
                        AapAudioManager.this.mAudioSinkMediaStreamPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_MEDIA), AapAudioManager.this.mContext);
                    }
                    if (AapAudioManager.this.mAudioSinkMediaAsrPlayer != null) {
                        AapAudioManager.this.mIsMediaStreamAsrSetup = true;
                        AapAudioManager.this.mAudioSinkMediaAsrPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_VR_48k), AapAudioManager.this.mContext);
                    }
                }
            } else {
                AapAudioManager.this.mIsMediaStreamSetup = false;
                AapAudioManager.this.mIsMediaStreamGuidanceSetup = false;
                AapAudioManager.this.mIsMediaStreamAsrSetup = false;
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onSetUp end:" + mediaCodecType);
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onStart(int sessionId) {
            int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
            AapAudioManager.this.getVoiceSessionState();
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onStart start curMdReq:" + curMdReq + " isSetup:" + AapAudioManager.this.mIsMediaStreamGuidanceSetup);
            if (AapAudioManager.this.mAudioControllerAdapter != null) {
                LogUtil.info(AapAudioManager.TAG, "mAudioMediaStreamListener audio Start!");
                AapAudioManager.this.mAudioControllerAdapter.requestChangeEntertainmentMute(0);
            }
            if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                AapAudioManager.this.mMediaStreamDataType = 2;
                AapAudioManager.this.setMdTtsState(true);
                if (AapAudioManager.this.mAudioSinkGuidanceStreamPlayer != null) {
                    if (!AapAudioManager.this.mIsWireConnect && !AapAudioManager.this.mIsMediaStreamGuidanceSetup) {
                        AapAudioManager.this.mIsMediaStreamGuidanceSetup = true;
                        AapAudioManager.this.mAudioSinkGuidanceStreamPlayer.setUp(AapAudioManager.this.mMediaStreamMediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_NAVI), AapAudioManager.this.mContext);
                    }
                    AapAudioManager.this.mAudioSinkGuidanceStreamPlayer.onStart(sessionId);
                }
            } else if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT) {
                LogUtil.info(AapAudioManager.TAG, "mAudioMediaStreamListener vr focus onstart isSetup:" + AapAudioManager.this.mIsMediaStreamAsrSetup);
                AapAudioManager.this.mMediaStreamDataType = 1;
                if (AapAudioManager.this.mAudioSinkMediaAsrPlayer != null) {
                    if (!AapAudioManager.this.mIsWireConnect && !AapAudioManager.this.mIsMediaStreamAsrSetup) {
                        AapAudioManager.this.mIsMediaStreamAsrSetup = true;
                        AapAudioManager.this.mAudioSinkMediaAsrPlayer.setUp(AapAudioManager.this.mMediaStreamMediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_VR_48k), AapAudioManager.this.mContext);
                    }
                    AapAudioManager.this.mAudioSinkMediaAsrPlayer.onStart(sessionId);
                }
            } else {
                LogUtil.info(AapAudioManager.TAG, "mAudioMediaStreamListener onStart: media focus isSetup:" + AapAudioManager.this.mIsMediaStreamSetup);
                AapAudioManager.this.mMediaStreamDataType = 0;
                StateManager.getInstance().setMediaStart(true);
                if (AapAudioManager.this.mAudioSinkMediaStreamPlayer != null) {
                    if (!AapAudioManager.this.mIsWireConnect && !AapAudioManager.this.mIsMediaStreamSetup) {
                        AapAudioManager.this.mIsMediaStreamSetup = true;
                        AapAudioManager.this.mAudioSinkMediaStreamPlayer.setUp(AapAudioManager.this.mMediaStreamMediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_MEDIA), AapAudioManager.this.mContext);
                    }
                    AapAudioManager.this.mAudioSinkMediaStreamPlayer.onStart(sessionId);
                }
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onStart end curMdReq:" + curMdReq);
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onStop(int sessionId) {
            int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onStop start mMediaStreamDataType:" + AapAudioManager.this.mMediaStreamDataType);
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onStop start curMdReq:" + curMdReq);
            if (AapAudioManager.this.mMediaStreamDataType == 2) {
                if (AapAudioManager.this.mAudioSinkGuidanceStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkGuidanceStreamPlayer.onStop(sessionId);
                }
                if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN) {
                    LogUtil.info(AapAudioManager.TAG, "Wire media channel navi onStop in GAIN focus!");
                    if (AapAudioManager.this.mAudioFocusManager != null) {
                        AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(8);
                    }
                }
            } else if (AapAudioManager.this.mMediaStreamDataType == 1) {
                if (AapAudioManager.this.mAudioSinkMediaAsrPlayer != null) {
                    AapAudioManager.this.mAudioSinkMediaAsrPlayer.onStop(sessionId);
                }
            } else {
                StateManager.getInstance().setMediaStart(false);
                if (AapAudioManager.this.mAudioSinkMediaStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkMediaStreamPlayer.onStop(sessionId);
                }
            }
            AapAudioManager.this.mMediaStreamDataType = -1;
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onStop end mMediaStreamDataType:" + AapAudioManager.this.mMediaStreamDataType);
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onShutdown() {
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onShutdown start");
            if (AapAudioManager.this.mAudioSinkGuidanceStreamPlayer != null) {
                AapAudioManager.this.mAudioSinkGuidanceStreamPlayer.onShutdown();
            }
            if (AapAudioManager.this.mAudioSinkMediaStreamPlayer != null) {
                AapAudioManager.this.mAudioSinkMediaStreamPlayer.onShutdown();
            }
            if (AapAudioManager.this.mAudioSinkMediaAsrPlayer != null) {
                AapAudioManager.this.mAudioSinkMediaAsrPlayer.onShutdown();
            }
            AapAudioManager.this.mMediaStreamDataType = -1;
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onShutdown end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onAudioData(int sessionId, long timestamp, byte[] data) {
            if (AapAudioManager.this.mMediaStreamSwitcher) {
                AapAudioManager.this.saveDataToFile(AapAudioManager.MEDIA_PATH, data);
            }
            if (AapAudioManager.this.mMediaStreamDataType == 2) {
                if (AapAudioManager.this.mAudioSinkGuidanceStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkGuidanceStreamPlayer.feedAudioData(sessionId, timestamp, data);
                }
            } else if (AapAudioManager.this.mMediaStreamDataType == 1) {
                if (AapAudioManager.this.mAudioSinkMediaAsrPlayer != null) {
                    AapAudioManager.this.mAudioSinkMediaAsrPlayer.feedAudioData(sessionId, timestamp, data);
                }
            } else if (AapAudioManager.this.mAudioSinkMediaStreamPlayer != null) {
                AapController.getInstance().removeWarnMessage();
                AapAudioManager.this.mAudioSinkMediaStreamPlayer.feedAudioData(sessionId, timestamp, data);
            }
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onCodecConfig(byte[] data) {
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onCodecConfig start mMediaStreamDataType:" + AapAudioManager.this.mMediaStreamDataType);
            if (AapAudioManager.this.mMediaStreamSwitcher) {
                AapAudioManager.this.saveDataToFile(AapAudioManager.MEDIA_PATH, data);
            }
            if (AapAudioManager.this.mMediaStreamDataType == 2) {
                if (AapAudioManager.this.mAudioSinkGuidanceStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkGuidanceStreamPlayer.onCodecConfig(data);
                }
            } else if (AapAudioManager.this.mMediaStreamDataType == 1) {
                if (AapAudioManager.this.mAudioSinkMediaAsrPlayer != null) {
                    AapAudioManager.this.mAudioSinkMediaAsrPlayer.onCodecConfig(data);
                }
            } else if (AapAudioManager.this.mAudioSinkMediaStreamPlayer != null) {
                AapAudioManager.this.mAudioSinkMediaStreamPlayer.onCodecConfig(data);
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioMediaStreamListener onCodecConfig end mMediaStreamDataType:" + AapAudioManager.this.mMediaStreamDataType);
        }
    };
    private final AudioSink.AudioListener mAudioTtsStreamListener = new AudioSink.AudioListener() { // from class: com.ts.androidauto.aap.audio.AapAudioManager.2
        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onSetUp(int mediaCodecType) {
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onSetUp start mIsWireConnect:" + AapAudioManager.this.mIsWireConnect);
            AapAudioManager.this.mGuidanceStreamDataType = -1;
            AapAudioManager.this.mGuidanceStreamMediaCodecType = mediaCodecType;
            if (AapAudioManager.this.mAudioControllerAdapter != null && AapAudioManager.this.mContext != null) {
                if (AapAudioManager.this.mAudioSinkTtsStreamPlayer != null) {
                    AapAudioManager.this.mIsGuidanceStreamSetup = true;
                    AapAudioManager.this.mAudioSinkTtsStreamPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_NAVI), AapAudioManager.this.mContext);
                }
                if (AapAudioManager.this.mAudioSinkAsrStreamPlayer != null) {
                    AapAudioManager.this.mIsGuidanceStreamAsrSetup = true;
                    AapAudioManager.this.mAudioSinkAsrStreamPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_VR), AapAudioManager.this.mContext);
                }
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onSetUp end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onStart(int sessionId) {
            int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
            int voiceState = AapAudioManager.this.getVoiceSessionState();
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onStart start curMdReq:" + curMdReq + " getVoiceSessionState:" + voiceState);
            LogUtil.info(AapAudioManager.TAG, "mAudioTtsStreamListener mGuidanceStreamDataType: " + AapAudioManager.this.mGuidanceStreamDataType + " isWireConnect:" + AapAudioManager.this.mIsWireConnect + " isNaviSetup:" + AapAudioManager.this.mIsGuidanceStreamSetup + " isAsrSetup:" + AapAudioManager.this.mIsGuidanceStreamAsrSetup);
            AapAudioManager.this.mGuidanceStreamDataType = -1;
            if (AapAudioManager.this.mAudioControllerAdapter != null) {
                LogUtil.info(AapAudioManager.TAG, "mAudioTtsStreamListener audio Start!");
                AapAudioManager.this.mAudioControllerAdapter.requestChangeEntertainmentMute(0);
            }
            if ((voiceState != GalReceiver.VOICE_RECOGNITION_STATE_STARTED || curMdReq == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK) && curMdReq != GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT) {
                AapAudioManager.this.mGuidanceStreamDataType = 2;
                AapAudioManager.this.setMdTtsState(true);
                if (AapAudioManager.this.mAudioFocusManager != null) {
                    AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(7);
                }
                if (AapAudioManager.this.mAudioSinkTtsStreamPlayer != null) {
                    if (!AapAudioManager.this.mIsWireConnect && AapAudioManager.this.mIsGuidanceStreamSetup) {
                        AapAudioManager.this.mIsGuidanceStreamSetup = true;
                        if (AapAudioManager.this.mAudioSinkTtsStreamPlayer != null) {
                            AapAudioManager.this.mAudioSinkTtsStreamPlayer.setUp(AapAudioManager.this.mGuidanceStreamMediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_NAVI), AapAudioManager.this.mContext);
                        }
                    }
                    AapAudioManager.this.mAudioSinkTtsStreamPlayer.onStart(sessionId);
                }
            } else {
                AapAudioManager.this.mGuidanceStreamDataType = 1;
                AapAudioManager.this.setVrPlayState(1);
                if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN && AapAudioManager.this.mAudioFocusManager != null) {
                    AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(5);
                }
                if (AapAudioManager.this.mAudioSinkAsrStreamPlayer != null) {
                    if (!AapAudioManager.this.mIsWireConnect && AapAudioManager.this.mIsGuidanceStreamAsrSetup) {
                        AapAudioManager.this.mIsGuidanceStreamAsrSetup = true;
                        AapAudioManager.this.mAudioSinkAsrStreamPlayer.setUp(AapAudioManager.this.mGuidanceStreamMediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_VR), AapAudioManager.this.mContext);
                    }
                    AapAudioManager.this.mAudioSinkAsrStreamPlayer.onStart(sessionId);
                }
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onStart end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onStop(int sessionId) {
            int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onStop start curMdReq:" + curMdReq + " mGuidanceStreamDataType:" + AapAudioManager.this.mGuidanceStreamDataType);
            if (AapAudioManager.this.mGuidanceStreamDataType == 1) {
                if (AapAudioManager.this.mAudioSinkAsrStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkAsrStreamPlayer.onStop(sessionId);
                }
                if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN && AapAudioManager.this.mAudioFocusManager != null) {
                    LogUtil.info(AapAudioManager.TAG, "mAudioTtsStreamListener onStop release VR focus");
                    AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(6);
                }
                AapAudioManager.this.setVrPlayState(2);
            } else if (AapAudioManager.this.mGuidanceStreamDataType == 2) {
                if (AapAudioManager.this.mAudioSinkTtsStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkTtsStreamPlayer.onStop(sessionId);
                }
                AapAudioManager.this.setMdTtsState(false);
                if (AapAudioManager.this.mAudioFocusManager != null) {
                    AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(8);
                }
            }
            AapAudioManager.this.mGuidanceStreamDataType = -1;
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onStop end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onShutdown() {
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onShutdown start");
            if (AapAudioManager.this.mAudioSinkAsrStreamPlayer != null) {
                AapAudioManager.this.mAudioSinkAsrStreamPlayer.onShutdown();
            }
            if (AapAudioManager.this.mAudioSinkTtsStreamPlayer != null) {
                AapAudioManager.this.mAudioSinkTtsStreamPlayer.onShutdown();
            }
            AapAudioManager.this.mGuidanceStreamDataType = -1;
            LogUtil.debug(AapAudioManager.TAG, "mAudioTtsStreamListener onShutdown end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onAudioData(int sessionId, long timestamp, byte[] data) {
            if (AapAudioManager.this.mTtsStreamSwitcher) {
                AapAudioManager.this.saveDataToFile(AapAudioManager.TTS_PATH, data);
            }
            if (AapAudioManager.this.mGuidanceStreamDataType == 1) {
                if (AapAudioManager.this.mAudioSinkAsrStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkAsrStreamPlayer.feedAudioData(sessionId, timestamp, data);
                }
            } else if (AapAudioManager.this.mGuidanceStreamDataType == 2 && AapAudioManager.this.mAudioSinkTtsStreamPlayer != null) {
                AapAudioManager.this.mAudioSinkTtsStreamPlayer.feedAudioData(sessionId, timestamp, data);
            }
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onCodecConfig(byte[] data) {
            LogUtil.debug(AapAudioManager.TAG, "mAudioSinkTtsStreamPlayer onCodecConfig start mGuidanceStreamDataType:" + AapAudioManager.this.mGuidanceStreamDataType);
            if (AapAudioManager.this.mGuidanceStreamDataType == 1) {
                if (AapAudioManager.this.mAudioSinkAsrStreamPlayer != null) {
                    AapAudioManager.this.mAudioSinkAsrStreamPlayer.onCodecConfig(data);
                }
            } else if (AapAudioManager.this.mGuidanceStreamDataType == 2 && AapAudioManager.this.mAudioSinkTtsStreamPlayer != null) {
                AapAudioManager.this.mAudioSinkTtsStreamPlayer.onCodecConfig(data);
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioSinkTtsStreamPlayer onCodecConfig end");
        }
    };
    private final AudioSink.AudioListener mAudioAacMediaStreamListener = new AudioSink.AudioListener() { // from class: com.ts.androidauto.aap.audio.AapAudioManager.3
        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onSetUp(int mediaCodecType) {
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onSetUp start:" + mediaCodecType);
            AapAudioManager.this.mMediaStreamAacDataType = -1;
            if (AapAudioManager.this.mAudioControllerAdapter != null && AapAudioManager.this.mContext != null) {
                if (AapAudioManager.this.mMeidaSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mMeidaSinkGuidanceAacPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_NAVI), AapAudioManager.this.mContext);
                }
                if (AapAudioManager.this.mMediaSinkMediaAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkMediaAacPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_MEDIA), AapAudioManager.this.mContext);
                }
                if (AapAudioManager.this.mMediaSinkAsrAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkAsrAacPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_VR_48k), AapAudioManager.this.mContext);
                }
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onSetUp end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onStart(int sessionId) {
            int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
            AapAudioManager.this.getVoiceSessionState();
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onStart start curMdReq:" + curMdReq);
            if (AapAudioManager.this.mAudioControllerAdapter != null) {
                LogUtil.info(AapAudioManager.TAG, "mAudioAacMediaStreamListener audio Start!");
                AapAudioManager.this.mAudioControllerAdapter.requestChangeEntertainmentMute(0);
            }
            if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                AapAudioManager.this.mMediaStreamAacDataType = 2;
                AapAudioManager.this.setMdTtsState(true);
                if (AapAudioManager.this.mMeidaSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mMeidaSinkGuidanceAacPlayer.onStart(sessionId);
                }
            } else if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT) {
                LogUtil.info(AapAudioManager.TAG, "mAudioAacMediaStreamListener vr focus onstart");
                AapAudioManager.this.mMediaStreamAacDataType = 1;
                if (AapAudioManager.this.mMediaSinkAsrAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkAsrAacPlayer.onStart(sessionId);
                }
            } else {
                LogUtil.info(AapAudioManager.TAG, "mAudioAacMediaStreamListener onStart: media focus ");
                IAudioControllerAdapter unused = AapAudioManager.this.mAudioControllerAdapter;
                AapAudioManager.this.mMediaStreamAacDataType = 0;
                StateManager.getInstance().setMediaStart(true);
                if (AapAudioManager.this.mMediaSinkMediaAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkMediaAacPlayer.onStart(sessionId);
                }
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onStart end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onStop(int sessionId) {
            int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onStop start mMediaStreamDataType:" + AapAudioManager.this.mMediaStreamAacDataType);
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onStop start curMdReq:" + curMdReq);
            if (AapAudioManager.this.mMediaStreamAacDataType == 2) {
                if (AapAudioManager.this.mMeidaSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mMeidaSinkGuidanceAacPlayer.onStop(sessionId);
                }
                if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN) {
                    LogUtil.info(AapAudioManager.TAG, "Wireless media channel navi onStop in GAIN focus!");
                    if (AapAudioManager.this.mAudioFocusManager != null) {
                        AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(8);
                    }
                }
            } else if (AapAudioManager.this.mMediaStreamAacDataType == 1) {
                if (AapAudioManager.this.mMediaSinkAsrAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkAsrAacPlayer.onStop(sessionId);
                }
            } else {
                StateManager.getInstance().setMediaStart(false);
                if (AapAudioManager.this.mMediaSinkMediaAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkMediaAacPlayer.onStop(sessionId);
                }
            }
            AapAudioManager.this.mMediaStreamAacDataType = -1;
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onStop end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onShutdown() {
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onShutdown start");
            if (AapAudioManager.this.mMeidaSinkGuidanceAacPlayer != null) {
                AapAudioManager.this.mMeidaSinkGuidanceAacPlayer.onShutdown();
            }
            if (AapAudioManager.this.mMediaSinkAsrAacPlayer != null) {
                AapAudioManager.this.mMediaSinkAsrAacPlayer.onShutdown();
            }
            if (AapAudioManager.this.mMediaSinkMediaAacPlayer != null) {
                AapAudioManager.this.mMediaSinkMediaAacPlayer.onShutdown();
            }
            AapAudioManager.this.mMediaStreamAacDataType = -1;
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onShutdown end");
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onAudioData(int sessionId, long timestamp, byte[] data) {
            if (AapAudioManager.this.mMediaStreamSwitcher) {
                AapAudioManager.this.saveDataToFile(AapAudioManager.MEDIA_PATH, data);
            }
            if (AapAudioManager.this.mMediaStreamAacDataType == 2) {
                if (AapAudioManager.this.mMeidaSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mMeidaSinkGuidanceAacPlayer.feedAudioData(sessionId, timestamp, data);
                }
            } else if (AapAudioManager.this.mMediaStreamAacDataType == 1) {
                if (AapAudioManager.this.mMediaSinkAsrAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkAsrAacPlayer.feedAudioData(sessionId, timestamp, data);
                }
            } else if (AapAudioManager.this.mMediaSinkMediaAacPlayer != null) {
                AapController.getInstance().removeWarnMessage();
                AapAudioManager.this.mMediaSinkMediaAacPlayer.feedAudioData(sessionId, timestamp, data);
            }
        }

        @Override // com.google.android.projection.protocol.AudioSink.AudioListener
        public void onCodecConfig(byte[] data) {
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onCodecConfig start mMediaStreamDataType:" + AapAudioManager.this.mMediaStreamAacDataType);
            if (AapAudioManager.this.mMediaStreamSwitcher) {
                AapAudioManager.this.saveDataToFile(AapAudioManager.MEDIA_PATH, data);
            }
            if (AapAudioManager.this.mMediaStreamAacDataType == 2) {
                if (AapAudioManager.this.mMeidaSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mMeidaSinkGuidanceAacPlayer.onCodecConfig(data);
                }
            } else if (AapAudioManager.this.mMediaStreamAacDataType == 1) {
                if (AapAudioManager.this.mMediaSinkAsrAacPlayer != null) {
                    AapAudioManager.this.mMediaSinkAsrAacPlayer.onCodecConfig(data);
                }
            } else if (AapAudioManager.this.mMediaSinkMediaAacPlayer != null) {
                AapAudioManager.this.mMediaSinkMediaAacPlayer.onCodecConfig(data);
            }
            LogUtil.debug(AapAudioManager.TAG, "mAudioAacMediaStreamListener onCodecConfig end");
        }
    };

    public static synchronized AapAudioManager getInstance() {
        if (mAapAudioManager == null) {
            mAapAudioManager = new AapAudioManager();
        }
        return mAapAudioManager;
    }

    private AapAudioManager() {
        this.mAudioSinkMediaStreamPlayer = null;
        this.mAudioSinkGuidanceStreamPlayer = null;
        this.mAudioSinkMediaAsrPlayer = null;
        this.mAudioSinkTtsStreamPlayer = null;
        this.mAudioSinkAsrStreamPlayer = null;
        this.mMediaSinkMediaAacPlayer = null;
        this.mMeidaSinkGuidanceAacPlayer = null;
        this.mMediaSinkAsrAacPlayer = null;
        this.mGuidanceSinkGuidanceAacPlayer = null;
        this.mGuidanceSinkAsrAacPlayer = null;
        AudioSink.AudioListener audioListener = new AudioSink.AudioListener() { // from class: com.ts.androidauto.aap.audio.AapAudioManager.4
            @Override // com.google.android.projection.protocol.AudioSink.AudioListener
            public void onSetUp(int mediaCodecType) {
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onSetUp start:" + mediaCodecType);
                AapAudioManager.this.mGuidanceStreamAacDataType = -1;
                if (AapAudioManager.this.mAudioControllerAdapter != null && AapAudioManager.this.mContext != null) {
                    if (AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_NAVI), AapAudioManager.this.mContext);
                    }
                    if (AapAudioManager.this.mGuidanceSinkAsrAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkAsrAacPlayer.setUp(mediaCodecType, AapAudioManager.this.mAudioControllerAdapter.getAudioAttributes(ResourceConst.AudioId.PROJECTION_AAP_VR), AapAudioManager.this.mContext);
                    }
                }
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onSetUp end");
            }

            @Override // com.google.android.projection.protocol.AudioSink.AudioListener
            public void onStart(int sessionId) {
                int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
                int voiceState = AapAudioManager.this.getVoiceSessionState();
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onStart start curMdReq:" + curMdReq + " getVoiceSessionState:" + voiceState);
                String str = AapAudioManager.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("mAudioAacTtsStreamListener mGuidanceStreamAacDataType: ");
                sb.append(AapAudioManager.this.mGuidanceStreamAacDataType);
                LogUtil.info(str, sb.toString());
                AapAudioManager.this.mGuidanceStreamAacDataType = -1;
                if (AapAudioManager.this.mAudioControllerAdapter != null) {
                    LogUtil.info(AapAudioManager.TAG, "mAudioAacTtsStreamListener  audio Start");
                    AapAudioManager.this.mAudioControllerAdapter.requestChangeEntertainmentMute(0);
                }
                if ((voiceState != GalReceiver.VOICE_RECOGNITION_STATE_STARTED || curMdReq == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK) && curMdReq != GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT) {
                    AapAudioManager.this.mGuidanceStreamAacDataType = 2;
                    AapAudioManager.this.setMdTtsState(true);
                    if (AapAudioManager.this.mAudioFocusManager != null) {
                        AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(7);
                    }
                    if (AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer.onStart(sessionId);
                    }
                } else {
                    AapAudioManager.this.mGuidanceStreamAacDataType = 1;
                    AapAudioManager.this.setVrPlayState(1);
                    if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN && AapAudioManager.this.mAudioFocusManager != null) {
                        AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(5);
                    }
                    if (AapAudioManager.this.mGuidanceSinkAsrAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkAsrAacPlayer.onStart(sessionId);
                    }
                }
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onStart end");
            }

            @Override // com.google.android.projection.protocol.AudioSink.AudioListener
            public void onStop(int sessionId) {
                int curMdReq = AapAudioManager.this.getCurrentMdFocusReq();
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onStop start curMdReq:" + curMdReq + " mGuidanceStreamAacDataType:" + AapAudioManager.this.mGuidanceStreamAacDataType);
                if (AapAudioManager.this.mGuidanceStreamAacDataType == 1) {
                    if (AapAudioManager.this.mGuidanceSinkAsrAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkAsrAacPlayer.onStop(sessionId);
                    }
                    if (curMdReq == GalReceiver.AUDIO_FOCUS_GAIN && AapAudioManager.this.mAudioFocusManager != null) {
                        LogUtil.info(AapAudioManager.TAG, "mAudioAacTtsStreamListener onStop release VR focus");
                        AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(6);
                    }
                    AapAudioManager.this.setVrPlayState(2);
                } else if (AapAudioManager.this.mGuidanceStreamAacDataType == 2) {
                    if (AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer.onStop(sessionId);
                    }
                    AapAudioManager.this.setMdTtsState(false);
                    if (AapAudioManager.this.mAudioFocusManager != null) {
                        AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(8);
                    }
                }
                AapAudioManager.this.mGuidanceStreamAacDataType = -1;
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onStop end");
            }

            @Override // com.google.android.projection.protocol.AudioSink.AudioListener
            public void onShutdown() {
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onShutdown start");
                if (AapAudioManager.this.mGuidanceSinkAsrAacPlayer != null) {
                    AapAudioManager.this.mGuidanceSinkAsrAacPlayer.onShutdown();
                }
                if (AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer.onShutdown();
                }
                AapAudioManager.this.mGuidanceStreamAacDataType = -1;
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onShutdown end");
            }

            @Override // com.google.android.projection.protocol.AudioSink.AudioListener
            public void onAudioData(int sessionId, long timestamp, byte[] data) {
                if (AapAudioManager.this.mTtsStreamSwitcher) {
                    AapAudioManager.this.saveDataToFile(AapAudioManager.TTS_PATH, data);
                }
                if (AapAudioManager.this.mGuidanceStreamAacDataType == 1) {
                    if (AapAudioManager.this.mGuidanceSinkAsrAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkAsrAacPlayer.feedAudioData(sessionId, timestamp, data);
                    }
                } else if (AapAudioManager.this.mGuidanceStreamAacDataType == 2 && AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer.feedAudioData(sessionId, timestamp, data);
                }
            }

            @Override // com.google.android.projection.protocol.AudioSink.AudioListener
            public void onCodecConfig(byte[] data) {
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onCodecConfig start mGuidanceStreamDataType:" + AapAudioManager.this.mGuidanceStreamAacDataType);
                if (AapAudioManager.this.mGuidanceStreamAacDataType == 1) {
                    if (AapAudioManager.this.mGuidanceSinkAsrAacPlayer != null) {
                        AapAudioManager.this.mGuidanceSinkAsrAacPlayer.onCodecConfig(data);
                    }
                } else if (AapAudioManager.this.mGuidanceStreamAacDataType == 2 && AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer != null) {
                    AapAudioManager.this.mGuidanceSinkGuidanceAacPlayer.onCodecConfig(data);
                }
                LogUtil.debug(AapAudioManager.TAG, "mAudioAacTtsStreamListener onCodecConfig end");
            }
        };
        this.mAudioAacTtsStreamListener = audioListener;
        this.audioListeners = new AudioSink.AudioListener[]{this.mAudioMediaStreamListener, this.mAudioTtsStreamListener, this.mAudioAacMediaStreamListener, audioListener};
        this.mAudioFocusListener = new GalReceiver.AudioFocusListener() { // from class: com.ts.androidauto.aap.audio.AapAudioManager.5
            @Override // com.google.android.projection.protocol.GalReceiver.AudioFocusListener
            public void onAudioFocusRequest(int request) {
                LogUtil.debug(AapAudioManager.TAG, "onAudioFocusRequest request start:" + request);
                LogUtil.debug(AapAudioManager.TAG, "onAudioFocusRequest mMediaStreamDataType:" + AapAudioManager.this.mMediaStreamDataType + " mGuidanceStreamDataType:" + AapAudioManager.this.mGuidanceStreamDataType);
                LogUtil.debug(AapAudioManager.TAG, "onAudioFocusRequest mMediaStreamAacDataType:" + AapAudioManager.this.mMediaStreamAacDataType + " mGuidanceStreamAacDataType:" + AapAudioManager.this.mGuidanceStreamAacDataType);
                LogUtil.debug(AapAudioManager.TAG, "onAudioFocusRequest mMediaStreamAacDataType:" + AapAudioManager.this.mMediaStreamAacDataType + " mGuidanceStreamAacDataType:" + AapAudioManager.this.mGuidanceStreamAacDataType);
                String str = AapAudioManager.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onAudioFocusRequest getCurrentMdFocusReq():");
                sb.append(AapAudioManager.this.getCurrentMdFocusReq());
                LogUtil.debug(str, sb.toString());
                if (AapAudioManager.this.mGuidanceStreamDataType != 2 && AapAudioManager.this.mMediaStreamDataType != 2 && AapAudioManager.this.mMediaStreamAacDataType != 2 && AapAudioManager.this.mGuidanceStreamAacDataType != 2 && AapAudioManager.this.getCurrentMdFocusReq() == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK && request == GalReceiver.AUDIO_FOCUS_GAIN) {
                    LogUtil.debug(AapAudioManager.TAG, "onAudioFocusRequest request media, release navi first");
                    if (AapAudioManager.this.mAudioFocusManager != null) {
                        AapAudioManager.this.mAudioFocusManager.mdAudioFocusReq(8);
                    }
                }
                if (AapAudioManager.this.mAudioFocusManager != null) {
                    AapAudioManager.this.mAudioFocusManager.audioFocusRequestCallback(request);
                }
                LogUtil.debug(AapAudioManager.TAG, "onAudioFocusRequest request end:" + request);
            }
        };
        this.mMediaStreamSwitcher = false;
        this.mTtsStreamSwitcher = false;
        this.mMediaRandomFile = null;
        this.mTtsRandomFile = null;
        this.mRecorderRandomFile = null;
        this.mAudioSinkMediaStreamPlayer = new AudioPlayer(100, 200);
        this.mAudioSinkGuidanceStreamPlayer = new AudioPlayer(100, 201);
        this.mAudioSinkMediaAsrPlayer = new AudioPlayer(100, 202);
        this.mAudioSinkTtsStreamPlayer = new AudioPlayer(101, 201);
        this.mAudioSinkAsrStreamPlayer = new AudioPlayer(101, 202);
        this.mMediaSinkMediaAacPlayer = new AudioPlayer(100, 200);
        this.mMeidaSinkGuidanceAacPlayer = new AudioPlayer(100, 201);
        this.mMediaSinkAsrAacPlayer = new AudioPlayer(100, 202);
        this.mGuidanceSinkGuidanceAacPlayer = new AudioPlayer(101, 201);
        this.mGuidanceSinkAsrAacPlayer = new AudioPlayer(101, 202);
        this.mMicrophoneRecorder = MicrophoneRecorder.getInstance();
        this.mAudioFocusManager = AapAudioFocusManager.getInstance();
    }

    public void initAudioManager(Context context, GalIntegration gal, IAudioControllerAdapter audioControllerAdapter, boolean isWireConnect) {
        this.mGal = gal;
        this.mAudioControllerAdapter = audioControllerAdapter;
        LogUtil.info(TAG, "Init audio manager isWireConnect:" + isWireConnect);
        this.mContext = context;
        this.mAudioFocusManager.init(this.mGal, audioControllerAdapter);
        this.mMicrophoneRecorder.init(this.mGal, audioControllerAdapter);
        this.mIsWireConnect = isWireConnect;
        this.mGal.audioSinks[0].addSupportedConfiguration(48000, 16, 2);
        this.mGal.audioSinks[0].setAudioType(Protos.AudioStreamType.AUDIO_STREAM_MEDIA);
        this.mGal.audioSinks[0].setCodecType(Protos.MediaCodecType.MEDIA_CODEC_AUDIO_PCM);
        this.mGal.audioSinks[2].addSupportedConfiguration(48000, 16, 2);
        this.mGal.audioSinks[2].setAudioType(Protos.AudioStreamType.AUDIO_STREAM_MEDIA);
        this.mGal.audioSinks[2].setCodecType(Protos.MediaCodecType.MEDIA_CODEC_AUDIO_AAC_LC);
        this.mGal.audioSinks[1].addSupportedConfiguration(48000, 16, 1);
        this.mGal.audioSinks[1].setAudioType(Protos.AudioStreamType.AUDIO_STREAM_GUIDANCE);
        this.mGal.audioSinks[1].setCodecType(Protos.MediaCodecType.MEDIA_CODEC_AUDIO_PCM);
        this.mGal.audioSinks[3].addSupportedConfiguration(48000, 16, 1);
        this.mGal.audioSinks[3].setAudioType(Protos.AudioStreamType.AUDIO_STREAM_GUIDANCE);
        this.mGal.audioSinks[3].setCodecType(Protos.MediaCodecType.MEDIA_CODEC_AUDIO_AAC_LC);
        this.mMicrophoneRecorder.setGalAudioSource(this.mGal.audioSource);
        this.mGal.audioSource.setConfig(this.mMicrophoneRecorder.getSamplingRate(), this.mMicrophoneRecorder.getNumberOfBits(), this.mMicrophoneRecorder.getNumberOfChannels());
        this.mAudioSinkMediaStreamPlayer.registerAudioSink(this.mGal.audioSinks[0]);
        this.mAudioSinkGuidanceStreamPlayer.registerAudioSink(this.mGal.audioSinks[0]);
        this.mAudioSinkMediaAsrPlayer.registerAudioSink(this.mGal.audioSinks[0]);
        this.mAudioSinkTtsStreamPlayer.registerAudioSink(this.mGal.audioSinks[1]);
        this.mAudioSinkAsrStreamPlayer.registerAudioSink(this.mGal.audioSinks[1]);
        this.mMediaSinkMediaAacPlayer.registerAudioSink(this.mGal.audioSinks[2]);
        this.mMeidaSinkGuidanceAacPlayer.registerAudioSink(this.mGal.audioSinks[2]);
        this.mMediaSinkAsrAacPlayer.registerAudioSink(this.mGal.audioSinks[2]);
        this.mGuidanceSinkGuidanceAacPlayer.registerAudioSink(this.mGal.audioSinks[3]);
        this.mGuidanceSinkAsrAacPlayer.registerAudioSink(this.mGal.audioSinks[3]);
    }

    public void releaseAudio() {
        this.mGuidanceStreamDataType = -1;
        this.mMediaStreamDataType = -1;
        boolean vrFocusState = StateManager.getInstance().getVrFocusState();
        boolean naviFocusState = StateManager.getInstance().getNaviFocusState();
        if (this.mAudioFocusManager != null) {
            if (vrFocusState) {
                LogUtil.info(TAG, "shutdown release vr focus");
                this.mAudioFocusManager.reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
            }
            if (naviFocusState) {
                LogUtil.info(TAG, "shutdown release navi focus");
                this.mAudioFocusManager.reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
            }
        }
        if (this.mMicrophoneRecorder != null) {
            LogUtil.info(TAG, "shutdown recorder audio");
            this.mMicrophoneRecorder.stop();
        }
        if (this.mAudioSinkMediaStreamPlayer != null) {
            LogUtil.info(TAG, "shutdown media audio");
            this.mAudioSinkMediaStreamPlayer.tearDown();
        }
        if (this.mAudioSinkMediaAsrPlayer != null) {
            LogUtil.info(TAG, "shutdown media with asr audio");
            this.mAudioSinkMediaAsrPlayer.tearDown();
        }
        if (this.mAudioSinkGuidanceStreamPlayer != null) {
            LogUtil.info(TAG, "shutdown media with guidance audio");
            this.mAudioSinkGuidanceStreamPlayer.tearDown();
        }
        if (this.mAudioSinkTtsStreamPlayer != null) {
            LogUtil.info(TAG, "shutdown tts audio");
            this.mAudioSinkTtsStreamPlayer.tearDown();
        }
        if (this.mAudioSinkAsrStreamPlayer != null) {
            LogUtil.info(TAG, "shutdown tts with ASR audio");
            this.mAudioSinkAsrStreamPlayer.tearDown();
        }
        if (this.mMediaSinkMediaAacPlayer != null) {
            LogUtil.info(TAG, "shutdown aac media audio");
            this.mMediaSinkMediaAacPlayer.tearDown();
        }
        if (this.mMeidaSinkGuidanceAacPlayer != null) {
            LogUtil.info(TAG, "shutdown aac media with guidance audio");
            this.mMeidaSinkGuidanceAacPlayer.tearDown();
        }
        if (this.mMediaSinkAsrAacPlayer != null) {
            LogUtil.info(TAG, "shutdown aac media with asr audio");
            this.mMediaSinkAsrAacPlayer.tearDown();
        }
        if (this.mGuidanceSinkGuidanceAacPlayer != null) {
            LogUtil.info(TAG, "shutdown aac tts audio");
            this.mGuidanceSinkGuidanceAacPlayer.tearDown();
        }
        if (this.mGuidanceSinkAsrAacPlayer != null) {
            LogUtil.info(TAG, "shutdown aac tts with ASR audio");
            this.mGuidanceSinkAsrAacPlayer.tearDown();
        }
        setMediaStreamSwitcher(false);
        setTtsStreamSwitcher(false);
        setRecorderStreamSwitcher(false);
        closeDataFile();
        closeAudioFile();
        AapAudioFocusManager aapAudioFocusManager = this.mAudioFocusManager;
        if (aapAudioFocusManager != null) {
            aapAudioFocusManager.releaseResource();
        }
    }

    public GalReceiver.AudioFocusListener getAudioFocusListener() {
        return this.mAudioFocusListener;
    }

    public MicrophoneRecorder getAudioSourceListener() {
        return this.mMicrophoneRecorder;
    }

    public AudioSink.AudioListener[] getAudioListener() {
        return this.audioListeners;
    }

    public AapAudioFocusManager getAapAudioFocusManager() {
        return this.mAudioFocusManager;
    }

    public void setCurrentMdFocusReq(int request) {
        StateManager.getInstance().setCurrentMdFocusReq(request);
    }

    public int getCurrentMdFocusReq() {
        return StateManager.getInstance().getCurrentMdFocusReq();
    }

    public void setVoiceSessionState(int state) {
        StateManager.getInstance().setVoiceSessionState(state);
    }

    public int getVoiceSessionState() {
        return StateManager.getInstance().getVoiceSessionState();
    }

    public void setMdTtsState(boolean state) {
        StateManager.getInstance().setMdTtsState(state);
    }

    public boolean getMdTtsState() {
        return StateManager.getInstance().getMdTtsState();
    }

    public void setVrPlayState(int state) {
        StateManager.getInstance().setVrPlayState(state);
    }

    public int getVrPlayState() {
        return StateManager.getInstance().getVrPlayState();
    }

    public void setMediaStreamSwitcher(boolean switcher) {
        this.mMediaStreamSwitcher = switcher;
    }

    public boolean getMediaStreamSwitcher() {
        return this.mMediaStreamSwitcher;
    }

    public void setTtsStreamSwitcher(boolean switcher) {
        this.mTtsStreamSwitcher = switcher;
    }

    public boolean getTtsStreamSwitcher() {
        return this.mTtsStreamSwitcher;
    }

    public void setRecorderStreamSwitcher(boolean switcher) {
        MicrophoneRecorder microphoneRecorder = this.mMicrophoneRecorder;
        if (microphoneRecorder != null) {
            microphoneRecorder.setRecorderStreamSwitcher(switcher);
        }
    }

    public void saveDataToFile(String path, byte[] dataToWrite) {
        if (path.equals(MEDIA_PATH)) {
            try {
                if (this.mMediaRandomFile == null) {
                    this.mMediaRandomFile = new RandomAccessFile(path, "rw");
                }
                this.mMediaRandomFile.write(dataToWrite);
            } catch (FileNotFoundException exception) {
                LogUtil.warning(TAG, "Test: Save meida File FileNotFoundException:" + exception.getMessage());
            } catch (IOException exception2) {
                LogUtil.warning(TAG, "Test: Save meida File IOException:" + exception2.getMessage());
            } catch (Exception exception3) {
                LogUtil.warning(TAG, "Test: Save meida File Exception:" + exception3.getMessage());
            }
        }
        if (path.equals(TTS_PATH)) {
            try {
                if (this.mTtsRandomFile == null) {
                    this.mTtsRandomFile = new RandomAccessFile(path, "rw");
                }
                this.mTtsRandomFile.write(dataToWrite);
            } catch (FileNotFoundException exception4) {
                LogUtil.warning(TAG, "Test: Save Tts File FileNotFoundException:" + exception4.getMessage());
            } catch (IOException exception5) {
                LogUtil.warning(TAG, "Test: Save Tts File IOException:" + exception5.getMessage());
            } catch (Exception exception6) {
                LogUtil.warning(TAG, "Test: Save Tts File Exception:" + exception6.getMessage());
            }
        }
        if (path.equals(RECORDER_PATH)) {
            try {
                if (this.mRecorderRandomFile == null) {
                    this.mRecorderRandomFile = new RandomAccessFile(path, "rw");
                }
                this.mRecorderRandomFile.write(dataToWrite);
            } catch (FileNotFoundException exception7) {
                LogUtil.warning(TAG, "Test: Save Recorder File FileNotFoundException:" + exception7.getMessage());
            } catch (IOException exception8) {
                LogUtil.warning(TAG, "Test: Save Recorder File IOException:" + exception8.getMessage());
            } catch (Exception exception9) {
                LogUtil.warning(TAG, "Test: Save Recorder File Exception:" + exception9.getMessage());
            }
        }
    }

    public void closeDataFile() {
        LogUtil.info(TAG, "Test:closeDataFile");
        try {
            if (this.mMediaRandomFile != null) {
                this.mMediaRandomFile.close();
                this.mMediaRandomFile = null;
            }
            if (this.mTtsRandomFile != null) {
                this.mTtsRandomFile.close();
                this.mTtsRandomFile = null;
            }
            if (this.mRecorderRandomFile != null) {
                this.mRecorderRandomFile.close();
                this.mRecorderRandomFile = null;
            }
        } catch (IOException exception) {
            LogUtil.warning(TAG, "Test: close File IOException:" + exception.getMessage());
            exception.printStackTrace();
        } catch (Exception exception2) {
            LogUtil.warning(TAG, "Test: close File Exception:" + exception2.getMessage());
            exception2.printStackTrace();
        }
    }

    public void closeAudioFile() {
        LogUtil.info(TAG, "closeAuidoFile is invoke.");
        AudioPlayer audioPlayer = this.mAudioSinkGuidanceStreamPlayer;
        if (audioPlayer != null) {
            audioPlayer.closeAudioFile();
        }
        AudioPlayer audioPlayer2 = this.mAudioSinkMediaAsrPlayer;
        if (audioPlayer2 != null) {
            audioPlayer2.closeAudioFile();
        }
        AudioPlayer audioPlayer3 = this.mAudioSinkMediaStreamPlayer;
        if (audioPlayer3 != null) {
            audioPlayer3.closeAudioFile();
        }
        AudioPlayer audioPlayer4 = this.mAudioSinkAsrStreamPlayer;
        if (audioPlayer4 != null) {
            audioPlayer4.closeAudioFile();
        }
        AudioPlayer audioPlayer5 = this.mAudioSinkTtsStreamPlayer;
        if (audioPlayer5 != null) {
            audioPlayer5.closeAudioFile();
        }
        AudioPlayer audioPlayer6 = this.mMeidaSinkGuidanceAacPlayer;
        if (audioPlayer6 != null) {
            audioPlayer6.closeAudioFile();
        }
        AudioPlayer audioPlayer7 = this.mMediaSinkAsrAacPlayer;
        if (audioPlayer7 != null) {
            audioPlayer7.closeAudioFile();
        }
        AudioPlayer audioPlayer8 = this.mMediaSinkMediaAacPlayer;
        if (audioPlayer8 != null) {
            audioPlayer8.closeAudioFile();
        }
        AudioPlayer audioPlayer9 = this.mGuidanceSinkAsrAacPlayer;
        if (audioPlayer9 != null) {
            audioPlayer9.closeAudioFile();
        }
        AudioPlayer audioPlayer10 = this.mGuidanceSinkGuidanceAacPlayer;
        if (audioPlayer10 != null) {
            audioPlayer10.closeAudioFile();
        }
    }

    public void stopAudioTrack() {
        LogUtil.info(TAG, "stopAudioTrack 1 is invoke.");
        AudioPlayer audioPlayer = this.mMediaSinkMediaAacPlayer;
        if (audioPlayer != null) {
            audioPlayer.stopAudioTrack();
        }
        LogUtil.info(TAG, "stopAudioTrack 2 is invoke.");
        AudioPlayer audioPlayer2 = this.mAudioSinkMediaStreamPlayer;
        if (audioPlayer2 != null) {
            audioPlayer2.stopAudioTrack();
        }
    }
}
