package com.ts.androidauto.aap.video;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.Surface;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.VideoFrame;
import com.google.android.projection.protocol.VideoSink;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.aap.common.UiConfig;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.aap.vehicledata.VehicleDataManager;
import com.ts.androidauto.aap.video.VideoPlayer;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.interfaces.video.IVideoControllerAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class AapClusterVideoManager {
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_LEFT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_LEFT_INIT = 0;
    private static final int AAP_CODEC_CONFIG = 4;
    private static final int AAP_CODEC_SETUP = 5;
    private static final int AAP_DEFAULT_DPI = 143;
    private static final int AAP_DEFAULT_DPI_UKE = 140;
    private static final int AAP_DEFAULT_HEIGHT = 480;
    private static final int AAP_DEFAULT_HEIGHT_UKE = 720;
    private static final float AAP_DEFAULT_PIXEL_ASPECT = 1.0f;
    private static final int AAP_DEFAULT_VIEWING_DISTANCE = 738;
    private static final int AAP_DEFAULT_VIEWING_DISTANCE_UKE = 604;
    private static final int AAP_DEFAULT_WIDTH = 800;
    private static final int AAP_DEFAULT_WIDTH_UKE = 1920;
    private static final int AAP_PROJECTION_UPDATE = 1;
    private static final int AAP_SOURCE_VIDEOCONFIG_CHANGED = 3;
    private static final int AAP_UICONFIG_REPLY = 6;
    private static final int AAP_VIDEO_DATA_TIMEOUT = 7;
    private static final int AAP_VIDEO_DATA_TIMEOUT_DELAY = 3000;
    private static final int AAP_VIDEO_FOCUS_REQUEST = 2;
    private static final int DECODER_ADDITIONAL_DEPTH = 1;
    public static final String DUMP_VIDEO_PATH = "/sdcard/dumpAapVideo/AapClusterVideo.h264";
    private static final boolean ENABLE_DEBUG_LOG = false;
    private static final String FINISH_SHOW_ACTIVITY_ACTION = "com.ts.androidauto.app.display.aapclusteractivity.RECEIVER_FINISH_ACTION";
    private static final int FPS_60 = 60;
    private static final int REQUEST_WAIT_CODEC_TIME_MS = 500;
    private static final String VEHICLE_MODEL_TANG = "BYD 5.0F_01";
    private static final String VEHICLE_MODEL_UKE = "BYD 5.0F_02";
    private static AapClusterVideoManager mAapClusterVideoManager;
    private int mAapResolution1080pHeight;
    private float mAapResolution1080pPixelAspect;
    private int mAapResolution1080pRealDpi;
    private int mAapResolution1080pReportDpi;
    private int mAapResolution1080pWidth;
    private int mAapResolution480pHeight;
    private float mAapResolution480pPixelAspect;
    private int mAapResolution480pRealDpi;
    private int mAapResolution480pReportDpi;
    private int mAapResolution480pWidth;
    private int mAapSurfaceView1080pHeight;
    private int mAapSurfaceView1080pScrollX;
    private int mAapSurfaceView1080pScrollY;
    private int mAapSurfaceView1080pWidth;
    private int mAapSurfaceView480pHeight;
    private int mAapSurfaceView480pScrollX;
    private int mAapSurfaceView480pScrollY;
    private int mAapSurfaceView480pWidth;
    private int mAapTouchPointOffsetX;
    private int mAapTouchPointOffsetY;
    private Context mContext;
    private int mCurrVideoFocus;
    private GalIntegration mGal;
    private int mHeight;
    private int mRealVideoHeight;
    private int mRealVideoWidth;
    private int mSurfaceViewScrollX;
    private int mSurfaceViewScrollY;
    private float mTouchXScale;
    private float mTouchYScale;
    private IVideoControllerAdapter mVideoControllerAdapter;
    private VideoDataListener mVideoDataListener;
    private VideoPlayer mVideoPlayer;
    private int mViewingDistance;
    private int mWaitShowHeight;
    private int mWaitShowWidth;
    private int mWidth;
    private static final String TAG = "AAP" + AapClusterVideoManager.class.getSimpleName();
    private static VideoPlayThread mVideoPlayThread = null;
    private RandomAccessFile mRandomFile = null;
    private final Object mLock = new Object();
    private Object syncObject = new Object();
    private Object releaseObject = new Object();
    private Surface mAlreadyShowSurface = null;
    private AapVideoCallback mAapVideoCallback = null;
    private int mRenderHeight = 0;
    private int mRenderWidth = 0;
    private Surface mWaitShowSurface = null;
    private boolean mIsVideoDataAvaiable = ENABLE_DEBUG_LOG;
    private boolean mIsVideoSetup = ENABLE_DEBUG_LOG;
    private boolean mIsVideoShowing = ENABLE_DEBUG_LOG;
    private boolean mIsMdRequest = ENABLE_DEBUG_LOG;
    private boolean mIsShowInShowing = ENABLE_DEBUG_LOG;
    private boolean mIsDumpVideo = ENABLE_DEBUG_LOG;
    private boolean mIsNewSemaphore = ENABLE_DEBUG_LOG;
    private boolean mIsVideoCreated = ENABLE_DEBUG_LOG;
    private Semaphore mCodecReleaseSemaphore = new Semaphore(0);
    private int mVideoHeight480P = 480;
    private int mVideoHeight720P = 720;
    private String mCurrentVehicleModel = VEHICLE_MODEL_TANG;
    private int mAapContentInsetsLeft = 0;
    private int mAapContentInsetsRight = 0;
    private int mAapContentInsetsTop = 0;
    private int mAapContentInsetsBottom = 0;
    private int mAapStableContentInsetsLeft = 0;
    private int mAapStableContentInsetsRight = 0;
    private int mAapStableContentInsetsTop = 0;
    private int mAapStableContentInsetsBottom = 0;
    private int mAapMarginLeft = 0;
    private int mAapMarginRight = 0;
    private int mAapMarginTop = 0;
    private int mAapMarginBottom = 0;
    private int mInitAapContentInsets1080pLeft = 0;
    private int mInitAapContentInsets1080pRight = 0;
    private int mInitAapContentInsets1080pTop = 0;
    private int mInitAapContentInsets1080pBottom = 0;
    private int mInitAapStableContentInsets1080pLeft = 0;
    private int mInitAapStableContentInsets1080pRight = 0;
    private int mInitAapStableContentInsets1080pTop = 0;
    private int mInitAapStableContentInsets1080pBottom = 0;
    private int mInitAapMargin1080pLeft = 0;
    private int mInitAapMargin1080pRight = 0;
    private int mInitAapMargin1080pTop = 0;
    private int mInitAapMargin1080pBottom = 0;
    private Handler mVideoProjectionHandler = new Handler(Looper.getMainLooper()) { // from class: com.ts.androidauto.aap.video.AapClusterVideoManager.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    AapClusterVideoManager.this.onProjectionUpdate((VideoFrame) msg.obj);
                    break;
                case 2:
                    AapClusterVideoManager.this.onVideoFocusRequest(msg.arg1, msg.arg2);
                    break;
                case 3:
                    AapClusterVideoManager.this.onSourceVideoConfigChanged(msg.arg1, msg.arg2);
                    break;
                case 4:
                    AapClusterVideoManager.this.onCodecConfig((byte[]) msg.obj);
                    break;
                case 5:
                    AapClusterVideoManager.this.onCodecSetup(msg.arg1);
                    break;
                case 6:
                    Protos.UiConfig reply = (Protos.UiConfig) msg.obj;
                    if (AapClusterVideoManager.this.mGal.displayCluster.getCurrentUiConfig() != null && AapClusterVideoManager.this.mGal.displayCluster.getCurrentUiConfig().equals(reply)) {
                        LogUtil.info(AapClusterVideoManager.TAG, "onClusterVideoUpdateUiConfigReply reply is the same UiConfig.");
                        AapClusterVideoManager.this.onClusterVideoUpdateUiConfigReply((Protos.UiConfig) msg.obj);
                        break;
                    }
                    break;
                case 7:
                    if (AapClusterVideoManager.this.mVideoControllerAdapter != null) {
                        AapClusterVideoManager.this.mVideoControllerAdapter.requestClusterVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_NATIVE);
                    }
                    break;
            }
        }
    };
    private final VideoPlayer.Listener mVideoPlayerListener = new VideoPlayer.Listener() { // from class: com.ts.androidauto.aap.video.AapClusterVideoManager.2
        @Override // com.ts.androidauto.aap.video.VideoPlayer.Listener
        public void onFrameRendered(int sessionId) {
            synchronized (AapClusterVideoManager.this.mLock) {
                if (AapClusterVideoManager.this.mGal != null) {
                    AapClusterVideoManager.this.mGal.displayCluster.ackFrames(sessionId, 1);
                }
            }
        }

        @Override // com.ts.androidauto.aap.video.VideoPlayer.Listener
        public void onMediaCodecReleased() {
            if (AapClusterVideoManager.this.mCodecReleaseSemaphore != null) {
                LogUtil.info(AapClusterVideoManager.TAG, "onMediaCodecReleased relese semaphore");
                AapClusterVideoManager.this.mCodecReleaseSemaphore.release();
            }
        }

        @Override // com.ts.androidauto.aap.video.VideoPlayer.Listener
        public void onMediaCodecError() {
            LogUtil.info(AapClusterVideoManager.TAG, "onMediaCodecError return to native");
            Intent finishShowActivityIntent = new Intent();
            finishShowActivityIntent.setAction(AapClusterVideoManager.FINISH_SHOW_ACTIVITY_ACTION);
            if (AapClusterVideoManager.this.mContext != null) {
                AapClusterVideoManager.this.mContext.sendBroadcast(finishShowActivityIntent);
            }
        }
    };
    private final VideoSink.ProjectionListener mProjectionListener = new VideoSink.ProjectionListener() { // from class: com.ts.androidauto.aap.video.AapClusterVideoManager.3
        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onProjectionUpdate(VideoFrame frame) {
            if (AapClusterVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapClusterVideoManager.this.mVideoProjectionHandler.obtainMessage(1, frame);
                AapClusterVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onSourceVideoConfigChanged(int width, int height, Protos.VideoConfiguration config) {
            String str = AapClusterVideoManager.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onSourceVideoConfigChanged start mVideoPlayer:");
            sb.append(AapClusterVideoManager.this.mVideoPlayer == null ? true : AapClusterVideoManager.ENABLE_DEBUG_LOG);
            sb.append(" height:");
            sb.append(height);
            sb.append(" mWidth:");
            sb.append(width);
            LogUtil.debug(str, sb.toString());
            if (AapClusterVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapClusterVideoManager.this.mVideoProjectionHandler.obtainMessage(3, width, height);
                AapClusterVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapClusterVideoManager.TAG, "onSourceVideoConfigChanged end");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onCodecConfig(byte[] data) {
            LogUtil.debug(AapClusterVideoManager.TAG, "onCodecConfig start data: " + data.length + " mCurrVideoFocus:" + AapClusterVideoManager.this.mCurrVideoFocus);
            if (AapClusterVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapClusterVideoManager.this.mVideoProjectionHandler.obtainMessage(4, data);
                AapClusterVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapClusterVideoManager.TAG, "onCodecConfig end");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onCodecSetup(int mediaCodecType) {
            LogUtil.debug(AapClusterVideoManager.TAG, "onCodecSetup start MD codec is OK.");
            if (AapClusterVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapClusterVideoManager.this.mVideoProjectionHandler.obtainMessage(5, Integer.valueOf(mediaCodecType));
                AapClusterVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapClusterVideoManager.TAG, "onCodecSetup end.");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onVideoFocusRequest(int focus, int reason) {
            LogUtil.debug(AapClusterVideoManager.TAG, "onVideoFocusRequest start focus is " + focus + " mCurrVideoFocus:" + AapClusterVideoManager.this.mCurrVideoFocus);
            if (AapClusterVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapClusterVideoManager.this.mVideoProjectionHandler.obtainMessage(2, focus, reason);
                AapClusterVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapClusterVideoManager.TAG, "onVideoFocusRequest end");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onVideoFocusModeChange(int focusMode, int reason) {
            LogUtil.info(AapClusterVideoManager.TAG, "MD focus  has changed to be  " + focusMode);
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onUpdateUiConfigReply(Protos.UiConfig currentUiConfig) {
            LogUtil.info(AapClusterVideoManager.TAG, "MD onUpdateUiConfigReply OK.");
            if (AapClusterVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapClusterVideoManager.this.mVideoProjectionHandler.obtainMessage(6, currentUiConfig);
                AapClusterVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapClusterVideoManager.TAG, "onUpdateUiConfigReply end.");
        }
    };

    public interface VideoDataListener {
        void receiveVideoData();

        void videoHasSetup();
    }

    public void onProjectionUpdate(VideoFrame frame) {
        setVideoDataAvaiable(true);
        if (this.mCurrVideoFocus == VideoSink.VIDEO_FOCUS_NATIVE) {
            LogUtil.info(TAG, "onProjectionUpdate video is hide, do not handle");
            return;
        }
        synchronized (this.releaseObject) {
            if (this.mVideoPlayer != null) {
                this.mVideoPlayer.decodeBuffer(frame);
            }
        }
        if (!this.mIsDumpVideo || frame == null) {
            return;
        }
        ByteBuffer buffer = frame.data;
        byte[] bufferArray = buffer.array();
        saveVideoData(bufferArray);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVideoFocusRequest(int focus, int reason) {
        if (this.mVideoControllerAdapter != null) {
            if (this.mCurrVideoFocus != focus) {
                this.mIsMdRequest = true;
                if (focus == VideoSink.VIDEO_FOCUS_PROJECTED) {
                    ResourceConst.VideoFocusReqResult result = this.mVideoControllerAdapter.requestClusterVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
                    LogUtil.info(TAG, "onVideoFocusRequest focus result is " + result);
                    if (result == ResourceConst.VideoFocusReqResult.VIDEOFOCUS_REQUEST_FAILED) {
                        setVidoeFocus(ENABLE_DEBUG_LOG);
                        return;
                    }
                    return;
                }
                this.mVideoControllerAdapter.requestClusterVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_NATIVE);
                return;
            }
            this.mGal.displayCluster.setVideoFocus(focus, reason, ENABLE_DEBUG_LOG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSourceVideoConfigChanged(int width, int height) {
        int surfaceWidth;
        int surfaceHeight;
        this.mHeight = height;
        this.mWidth = width;
        synchronized (this.syncObject) {
            prepareVideo(width, height);
        }
        if (VEHICLE_MODEL_UKE.equals(this.mCurrentVehicleModel)) {
            LogUtil.debug(TAG, "onSourceVideoConfigChanged, update cluster VideoConfig1080p");
            VideoPlayer videoPlayer = this.mVideoPlayer;
            if (videoPlayer != null) {
                videoPlayer.updateSurfaceSize(this.mAapSurfaceView1080pWidth, this.mAapSurfaceView1080pHeight);
            }
            this.mVideoControllerAdapter.requestClusterVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_AA_1080P);
            this.mRealVideoWidth = this.mAapResolution1080pWidth;
            this.mRealVideoHeight = this.mAapResolution1080pHeight;
            this.mSurfaceViewScrollX = this.mAapSurfaceView1080pScrollX;
            this.mSurfaceViewScrollY = this.mAapSurfaceView1080pScrollY;
            surfaceWidth = this.mAapSurfaceView1080pWidth;
            surfaceHeight = this.mAapSurfaceView1080pHeight;
        } else {
            LogUtil.debug(TAG, "onSourceVideoConfigChanged, update cluster VideoConfig480p");
            VideoPlayer videoPlayer2 = this.mVideoPlayer;
            if (videoPlayer2 != null) {
                videoPlayer2.updateSurfaceSize(this.mAapSurfaceView480pWidth, this.mAapSurfaceView480pHeight);
            }
            this.mVideoControllerAdapter.requestClusterVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_AA_480P);
            this.mRealVideoWidth = this.mAapResolution480pWidth;
            this.mRealVideoHeight = this.mAapResolution480pHeight;
            this.mSurfaceViewScrollX = this.mAapSurfaceView480pScrollX;
            this.mSurfaceViewScrollY = this.mAapSurfaceView480pScrollY;
            surfaceWidth = this.mAapSurfaceView480pWidth;
            surfaceHeight = this.mAapSurfaceView480pHeight;
        }
        if (surfaceWidth > 0 && surfaceHeight > 0) {
            this.mTouchXScale = width / surfaceWidth;
            this.mTouchYScale = height / surfaceHeight;
        } else {
            this.mTouchXScale = 0.0f;
            this.mTouchYScale = 0.0f;
        }
    }

    public void onCodecConfig(byte[] data) {
        LogUtil.debug(TAG, "onCodecConfig start data: " + data.length + " mCurrVideoFocus:" + this.mCurrVideoFocus);
        this.mVideoProjectionHandler.removeMessages(7);
        if (this.mIsDumpVideo) {
            saveVideoData(data);
        }
        synchronized (this.releaseObject) {
            if (this.mVideoPlayer != null) {
                this.mVideoPlayer.handleCodecConfig(data);
            }
        }
        LogUtil.debug(TAG, "onCodecConfig end");
        this.mIsVideoCreated = true;
        updateClusterUiConfig(true);
    }

    public void onCodecSetup(int mediaCodecType) {
        LogUtil.debug(TAG, "onCodecSetup start MD codec is OK.");
        VideoDataListener videoDataListener = this.mVideoDataListener;
        if (videoDataListener != null) {
            videoDataListener.videoHasSetup();
        }
        setVideoSetUp(true);
        LogUtil.debug(TAG, "onCodecSetup end.");
    }

    public static synchronized AapClusterVideoManager getInstance() {
        if (mAapClusterVideoManager == null) {
            mAapClusterVideoManager = new AapClusterVideoManager();
        }
        return mAapClusterVideoManager;
    }

    public void registerVideoDataListener(VideoDataListener listener) {
        this.mVideoDataListener = listener;
    }

    public void unregisterVideoDataListener() {
        this.mVideoDataListener = null;
    }

    public void registerVideoCallback(AapVideoCallback aapVideoCallback) {
        this.mAapVideoCallback = aapVideoCallback;
    }

    public void unRegisterVideoCallback() {
        this.mAapVideoCallback = null;
    }

    public void initVideoManager(Context context, GalIntegration gal, IVideoControllerAdapter videoControllerAdapter) {
        LogUtil.info(TAG, "Init video manager.");
        VideoPlayThread videoPlayThread = new VideoPlayThread();
        mVideoPlayThread = videoPlayThread;
        videoPlayThread.init();
        this.mContext = context;
        this.mGal = gal;
        this.mVideoControllerAdapter = videoControllerAdapter;
        this.mCurrVideoFocus = VideoSink.VIDEO_FOCUS_NATIVE;
        VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
        if (info != null) {
            this.mCurrentVehicleModel = info.mHeadUnitModel;
            LogUtil.debug(TAG, "init CurrentVehicleModel = " + this.mCurrentVehicleModel);
        }
        updateVideoConfig();
        addSupportConfiguration();
        this.mIsVideoShowing = ENABLE_DEBUG_LOG;
        this.mIsMdRequest = ENABLE_DEBUG_LOG;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mTouchXScale = 0.0f;
        this.mTouchYScale = 0.0f;
        this.mRealVideoWidth = 0;
        this.mRealVideoHeight = 0;
        this.mSurfaceViewScrollX = 0;
        this.mSurfaceViewScrollY = 0;
        initDumpFile();
    }

    public void setClusterUiConfig(UiConfig uiConfig, boolean isAapConnected) {
        LogUtil.info(TAG, "setClusterUiConfig");
        this.mAapContentInsetsLeft = uiConfig.mAapClusterUiConfig1080p.mAapContentInsetsLeft;
        this.mAapContentInsetsRight = uiConfig.mAapClusterUiConfig1080p.mAapContentInsetsRight;
        this.mAapContentInsetsTop = uiConfig.mAapClusterUiConfig1080p.mAapContentInsetsTop;
        this.mAapContentInsetsBottom = uiConfig.mAapClusterUiConfig1080p.mAapContentInsetsBottom;
        this.mAapStableContentInsetsLeft = uiConfig.mAapClusterUiConfig1080p.mAapStableContentInsetsLeft;
        this.mAapStableContentInsetsRight = uiConfig.mAapClusterUiConfig1080p.mAapStableContentInsetsRight;
        this.mAapStableContentInsetsTop = uiConfig.mAapClusterUiConfig1080p.mAapStableContentInsetsTop;
        this.mAapStableContentInsetsBottom = uiConfig.mAapClusterUiConfig1080p.mAapStableContentInsetsBottom;
        this.mAapMarginLeft = uiConfig.mAapClusterUiConfig1080p.mAapMarginLeft;
        this.mAapMarginRight = uiConfig.mAapClusterUiConfig1080p.mAapMarginRight;
        this.mAapMarginTop = uiConfig.mAapClusterUiConfig1080p.mAapMarginTop;
        int i = uiConfig.mAapClusterUiConfig1080p.mAapMarginBottom;
        this.mAapMarginBottom = i;
        this.mInitAapContentInsets1080pLeft = this.mAapContentInsetsLeft;
        this.mInitAapContentInsets1080pRight = this.mAapContentInsetsRight;
        this.mInitAapContentInsets1080pTop = this.mAapContentInsetsTop;
        this.mInitAapContentInsets1080pBottom = this.mAapContentInsetsBottom;
        this.mInitAapStableContentInsets1080pLeft = this.mAapStableContentInsetsLeft;
        this.mInitAapStableContentInsets1080pRight = this.mAapStableContentInsetsRight;
        this.mInitAapStableContentInsets1080pTop = this.mAapStableContentInsetsTop;
        this.mInitAapStableContentInsets1080pBottom = this.mAapStableContentInsetsBottom;
        this.mInitAapMargin1080pLeft = this.mAapMarginLeft;
        this.mInitAapMargin1080pRight = this.mAapMarginRight;
        this.mInitAapMargin1080pTop = this.mAapMarginTop;
        this.mInitAapMargin1080pBottom = i;
        LogUtil.info(TAG, "setClusterUiConfig mInitAapContentInsets1080pLeft=" + this.mInitAapContentInsets1080pLeft);
        updateClusterUiConfig(isAapConnected);
    }

    private void updateVideoConfig() {
        VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
        if (info != null) {
            LogUtil.info(TAG, "updateVideoConfig, info ok!");
            if (VEHICLE_MODEL_UKE.equals(this.mCurrentVehicleModel)) {
                LogUtil.debug(TAG, "updateVideoConfig, update cluster VideoConfig1080p");
                int i = this.mInitAapContentInsets1080pLeft;
                if (i == 0 || i == 110) {
                    updateVideoConfig1080pFull(info.mAapClusterVideoConfig1080pFull);
                } else {
                    updateVideoConfig1080pSmall(info.mAapClusterVideoConfig1080pSmall);
                }
            } else {
                LogUtil.debug(TAG, "updateVideoConfig, update cluster VideoConfig480p");
                updateVideoConfig480p(info.mAapClusterVideoConfig480p);
            }
            this.mAapTouchPointOffsetX = info.mAapTouchPointOffsetX;
            this.mAapTouchPointOffsetY = info.mAapTouchPointOffsetY;
            this.mViewingDistance = info.mClusterViewingDistance;
            return;
        }
        LogUtil.info(TAG, "updateVideoConfig, info is null!");
        if (VEHICLE_MODEL_UKE.equals(this.mCurrentVehicleModel)) {
            LogUtil.debug(TAG, "updateVideoConfig, update cluster default VideoConfig1080p");
            this.mAapResolution1080pWidth = 1920;
            this.mAapResolution1080pHeight = 720;
            this.mAapResolution1080pReportDpi = 140;
            this.mAapResolution1080pRealDpi = 140;
            this.mAapResolution1080pPixelAspect = AAP_DEFAULT_PIXEL_ASPECT;
            this.mAapSurfaceView1080pWidth = 1920;
            this.mAapSurfaceView1080pHeight = 720;
            this.mAapSurfaceView1080pScrollX = 0;
            this.mAapSurfaceView1080pScrollY = 0;
            this.mViewingDistance = AAP_DEFAULT_VIEWING_DISTANCE_UKE;
        } else {
            LogUtil.debug(TAG, "updateVideoConfig, update cluster default VideoConfig480p");
            this.mAapResolution480pWidth = 800;
            this.mAapResolution480pHeight = 480;
            this.mAapResolution480pReportDpi = 143;
            this.mAapResolution480pRealDpi = 143;
            this.mAapResolution480pPixelAspect = AAP_DEFAULT_PIXEL_ASPECT;
            this.mAapSurfaceView480pWidth = 800;
            this.mAapSurfaceView480pHeight = 480;
            this.mAapSurfaceView480pScrollX = 0;
            this.mAapSurfaceView480pScrollY = 0;
            this.mViewingDistance = AAP_DEFAULT_VIEWING_DISTANCE;
        }
        this.mAapTouchPointOffsetX = 0;
        this.mAapTouchPointOffsetY = 0;
    }

    private void updateVideoConfig480p(VehicleInfo.AapVideoConfig info) {
        this.mAapResolution480pWidth = info.mAapResolutionWidth;
        this.mAapResolution480pHeight = info.mAapResolutionHeight;
        this.mAapResolution480pReportDpi = info.mAapResolutionReportDpi;
        this.mAapResolution480pRealDpi = info.mAapResolutionRealDpi;
        this.mAapResolution480pPixelAspect = info.mAapResolutionPixelAspect;
        this.mAapSurfaceView480pWidth = info.mAapSurfaceViewWidth;
        this.mAapSurfaceView480pHeight = info.mAapSurfaceViewHeight;
        this.mAapSurfaceView480pScrollX = info.mAapSurfaceViewScrollX;
        this.mAapSurfaceView480pScrollY = info.mAapSurfaceViewScrollY;
    }

    private void updateVideoConfig1080pFull(VehicleInfo.AapVideoConfig info) {
        this.mAapResolution1080pWidth = info.mAapResolutionWidth;
        this.mAapResolution1080pHeight = info.mAapResolutionHeight;
        this.mAapResolution1080pReportDpi = info.mAapResolutionReportDpi;
        this.mAapResolution1080pRealDpi = info.mAapResolutionRealDpi;
        this.mAapResolution1080pPixelAspect = info.mAapResolutionPixelAspect;
        this.mAapSurfaceView1080pWidth = info.mAapSurfaceViewWidth;
        this.mAapSurfaceView1080pHeight = info.mAapSurfaceViewHeight;
        this.mAapSurfaceView1080pScrollX = info.mAapSurfaceViewScrollX;
        this.mAapSurfaceView1080pScrollY = info.mAapSurfaceViewScrollY;
        if (this.mInitAapContentInsets1080pLeft == 0) {
            this.mInitAapContentInsets1080pLeft = info.mAapContentInsetsLeft;
            this.mInitAapContentInsets1080pRight = info.mAapContentInsetsRight;
            this.mInitAapContentInsets1080pTop = info.mAapContentInsetsTop;
            this.mInitAapContentInsets1080pBottom = info.mAapContentInsetsBottom;
            this.mInitAapStableContentInsets1080pLeft = info.mAapStableContentInsetsLeft;
            this.mInitAapStableContentInsets1080pRight = info.mAapStableContentInsetsRight;
            this.mInitAapStableContentInsets1080pTop = info.mAapStableContentInsetsTop;
            this.mInitAapStableContentInsets1080pBottom = info.mAapStableContentInsetsBottom;
            this.mInitAapMargin1080pLeft = info.mAapMarginLeft;
            this.mInitAapMargin1080pRight = info.mAapMarginRight;
            this.mInitAapMargin1080pTop = info.mAapMarginTop;
            this.mInitAapMargin1080pBottom = info.mAapMarginBottom;
            LogUtil.info(TAG, "updateVideoConfig1080p no init value");
        }
    }

    private void updateVideoConfig1080pSmall(VehicleInfo.AapVideoConfig info) {
        this.mAapResolution1080pWidth = info.mAapResolutionWidth;
        this.mAapResolution1080pHeight = info.mAapResolutionHeight;
        this.mAapResolution1080pReportDpi = info.mAapResolutionReportDpi;
        this.mAapResolution1080pRealDpi = info.mAapResolutionRealDpi;
        this.mAapResolution1080pPixelAspect = info.mAapResolutionPixelAspect;
        this.mAapSurfaceView1080pWidth = info.mAapSurfaceViewWidth;
        this.mAapSurfaceView1080pHeight = info.mAapSurfaceViewHeight;
        this.mAapSurfaceView1080pScrollX = info.mAapSurfaceViewScrollX;
        this.mAapSurfaceView1080pScrollY = info.mAapSurfaceViewScrollY;
        if (this.mInitAapContentInsets1080pLeft == 0) {
            this.mInitAapContentInsets1080pLeft = info.mAapContentInsetsLeft;
            this.mInitAapContentInsets1080pRight = info.mAapContentInsetsRight;
            this.mInitAapContentInsets1080pTop = info.mAapContentInsetsTop;
            this.mInitAapContentInsets1080pBottom = info.mAapContentInsetsBottom;
            this.mInitAapStableContentInsets1080pLeft = info.mAapStableContentInsetsLeft;
            this.mInitAapStableContentInsets1080pRight = info.mAapStableContentInsetsRight;
            this.mInitAapStableContentInsets1080pTop = info.mAapStableContentInsetsTop;
            this.mInitAapStableContentInsets1080pBottom = info.mAapStableContentInsetsBottom;
            this.mInitAapMargin1080pLeft = info.mAapMarginLeft;
            this.mInitAapMargin1080pRight = info.mAapMarginRight;
            this.mInitAapMargin1080pTop = info.mAapMarginTop;
            this.mInitAapMargin1080pBottom = info.mAapMarginBottom;
            LogUtil.info(TAG, "updateVideoConfig1080p no init value");
        }
    }

    private void initDumpFile() {
        File audioFile = new File("/sdcard/dumpvideo");
        if (audioFile.exists()) {
            LogUtil.debug(TAG, "Enable dumping video sink data");
            this.mIsDumpVideo = true;
            File fileDir = new File("/sdcard/dumpAapVideo");
            if (!fileDir.exists() && !fileDir.mkdir()) {
                LogUtil.error(TAG, "initDumpFile mkdir fail.");
            }
        }
    }

    public void prepareVideo(int width, int height) {
        LogUtil.info(TAG, "prepare Video is invoke!");
        VideoPlayThread videoPlayThread = mVideoPlayThread;
        if (videoPlayThread != null) {
            videoPlayThread.startVideo(width, height);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVidoeFocus(boolean isShow) {
        LogUtil.info(TAG, "set Video focus isShow:" + isShow + " mCurrVideoFocus:" + this.mCurrVideoFocus);
        if (isShow) {
            if ((this.mVideoPlayer != null && this.mCurrVideoFocus == VideoSink.VIDEO_FOCUS_NATIVE) || this.mIsMdRequest) {
                LogUtil.info(TAG, "Show video PROJECTED");
                this.mCurrVideoFocus = VideoSink.VIDEO_FOCUS_PROJECTED;
                this.mGal.displayCluster.setVideoFocus(VideoSink.VIDEO_FOCUS_PROJECTED, -1, !this.mIsMdRequest);
            }
        } else if ((this.mVideoPlayer != null && this.mCurrVideoFocus == VideoSink.VIDEO_FOCUS_PROJECTED) || this.mIsMdRequest) {
            LogUtil.info(TAG, "Hide video NATIVE");
            this.mCurrVideoFocus = VideoSink.VIDEO_FOCUS_NATIVE;
            this.mGal.displayCluster.setVideoFocus(VideoSink.VIDEO_FOCUS_NATIVE, -1, !this.mIsMdRequest);
        }
        this.mIsMdRequest = ENABLE_DEBUG_LOG;
    }

    public void showVideo(Surface surface, int width, int height) {
        LogUtil.info(TAG, "show Video,do not release mCurrVideoFocus:" + this.mCurrVideoFocus + " mIsVideoShowing:" + this.mIsVideoShowing);
        synchronized (this.syncObject) {
            if (this.mIsVideoShowing) {
                LogUtil.info(TAG, "showVideo: the video is already showing, skip it");
            }
            if (!this.mIsVideoShowing) {
                this.mAlreadyShowSurface = surface;
                createVideo(surface, width, height);
                this.mIsVideoShowing = true;
                onClusterVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_PROJECTED.ordinal());
            }
        }
    }

    public void hideVideo() {
        LogUtil.info(TAG, "Hide video mCurrVideoFocus:" + this.mCurrVideoFocus + " mIsVideoShowing:" + this.mIsVideoShowing);
        synchronized (this.syncObject) {
            if (this.mIsVideoShowing) {
                this.mIsVideoShowing = ENABLE_DEBUG_LOG;
                setVidoeFocus(ENABLE_DEBUG_LOG);
                this.mVideoProjectionHandler.removeMessages(7);
                releaseVideo();
                onClusterVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal());
            }
        }
    }

    public void updateClusterScreenSize() {
        LogUtil.info(TAG, "updateClusterScreenSize video mCurrVideoFocus:" + this.mCurrVideoFocus + " mIsVideoShowing:" + this.mIsVideoShowing);
        synchronized (this.syncObject) {
            VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
            if (info != null) {
                LogUtil.info(TAG, "updateVideoConfig, info ok!");
            }
        }
    }

    private void updateClusterUiConfig(boolean isAapConnected) {
        synchronized (this.syncObject) {
            Protos.UiConfig.Builder uiConfigBuilder = makeUpdateUiConfigBuilder();
            if (uiConfigBuilder != null && this.mGal != null && isAapConnected && VEHICLE_MODEL_UKE.equals(this.mCurrentVehicleModel) && this.mIsVideoCreated) {
                LogUtil.info(TAG, "updateClusterUiConfig");
                this.mGal.displayCluster.updateUiConfig((Protos.UiConfig) uiConfigBuilder.build());
            }
        }
    }

    private Protos.UiConfig.Builder makeUpdateUiConfigBuilder() {
        Protos.Insets contentInsets = (Protos.Insets) Protos.Insets.newBuilder().setLeft(this.mAapContentInsetsLeft).setRight(this.mAapContentInsetsRight).setTop(this.mAapContentInsetsTop).setBottom(this.mAapContentInsetsBottom).build();
        Protos.Insets stableContentInsets = (Protos.Insets) Protos.Insets.newBuilder().setLeft(this.mAapStableContentInsetsLeft).setRight(this.mAapStableContentInsetsRight).setTop(this.mAapStableContentInsetsTop).setBottom(this.mAapStableContentInsetsBottom).build();
        Protos.Insets margins = (Protos.Insets) Protos.Insets.newBuilder().setLeft(this.mAapMarginLeft).setRight(this.mAapMarginRight).setTop(this.mAapMarginTop).setBottom(this.mAapMarginBottom).build();
        Protos.UiConfig.Builder uiConfigBuilder = Protos.UiConfig.newBuilder().mergeContentInsets(contentInsets).mergeStableContentInsets(stableContentInsets).mergeMargins(margins);
        return uiConfigBuilder;
    }

    private Protos.UiConfig.Builder getInitUiConfig() {
        Protos.Insets contentInsets = (Protos.Insets) Protos.Insets.newBuilder().setLeft(this.mInitAapContentInsets1080pLeft).setRight(this.mInitAapContentInsets1080pRight).setTop(this.mInitAapContentInsets1080pTop).setBottom(this.mInitAapContentInsets1080pBottom).build();
        Protos.Insets stableContentInsets = (Protos.Insets) Protos.Insets.newBuilder().setLeft(this.mInitAapStableContentInsets1080pLeft).setRight(this.mInitAapStableContentInsets1080pRight).setTop(this.mInitAapStableContentInsets1080pTop).setBottom(this.mInitAapStableContentInsets1080pBottom).build();
        Protos.Insets margins = (Protos.Insets) Protos.Insets.newBuilder().setLeft(this.mInitAapMargin1080pLeft).setRight(this.mInitAapMargin1080pRight).setTop(this.mInitAapMargin1080pTop).setBottom(this.mInitAapMargin1080pBottom).build();
        Protos.UiConfig.Builder uiConfigBuilder = Protos.UiConfig.newBuilder().mergeContentInsets(contentInsets).mergeStableContentInsets(stableContentInsets).mergeMargins(margins);
        return uiConfigBuilder;
    }

    public void onClusterVideoFocusStatusChanged(int videoFocusStatus) {
        LogUtil.info(TAG, "onClusterVideoFocusStatusChanged: " + videoFocusStatus);
        AapVideoCallback aapVideoCallback = this.mAapVideoCallback;
        if (aapVideoCallback != null) {
            aapVideoCallback.onClusterVideoFocusStatusChanged(videoFocusStatus);
        }
    }

    public void onClusterVideoUpdateUiConfigReply(Protos.UiConfig currentUiConfig) {
        AapVideoCallback aapVideoCallback = this.mAapVideoCallback;
        if (aapVideoCallback != null) {
            aapVideoCallback.onClusterVideoUpdateUiConfigReply(true);
        }
        LogUtil.info(TAG, "onClusterVideoUpdateUiConfigReply end.");
    }

    private void tryAcquireCodecRelease() {
        try {
            if (this.mCodecReleaseSemaphore.tryAcquire(500L, TimeUnit.MILLISECONDS)) {
                LogUtil.debug(TAG, "Codec released return");
            } else {
                LogUtil.debug(TAG, "Codec release tryAcquire timeout");
            }
            this.mIsNewSemaphore = ENABLE_DEBUG_LOG;
        } catch (InterruptedException exception) {
            LogUtil.error(TAG, "Codec release exception:" + exception.toString());
        }
        this.mHeight = 0;
        this.mWidth = 0;
    }

    public boolean isVideoShowing() {
        boolean z;
        LogUtil.info(TAG, "isVideoShowing: " + this.mIsVideoShowing);
        synchronized (this.syncObject) {
            z = this.mIsVideoShowing;
        }
        return z;
    }

    public void setHuVideoFocus(boolean isNative) {
        LogUtil.info(TAG, "setHuVideoFocus: " + isNative);
        boolean isConnected = AapController.getInstance().isAapConnected();
        LogUtil.info(TAG, "setHuVideoFocus: isConnected? " + isConnected + "isVideoShowing? " + this.mIsVideoShowing);
        if (isConnected && this.mIsVideoShowing) {
            setVidoeFocus(!isNative);
        }
    }

    private void addSupportConfiguration() {
        Protos.VideoConfiguration videoConfiguration;
        LogUtil.info(TAG, "Add supported configuration start.");
        if (VEHICLE_MODEL_UKE.equals(this.mCurrentVehicleModel)) {
            Protos.UiConfig.Builder uiConfigBuilder = getInitUiConfig();
            LogUtil.info(TAG, "addSupportConfiguration mInitAapContentInsets1080pLeft=" + this.mInitAapContentInsets1080pLeft);
            videoConfiguration = this.mGal.displayCluster.nearestVideoConfiguration(this.mAapResolution1080pWidth, this.mAapResolution1080pHeight, this.mAapResolution1080pReportDpi, this.mAapResolution1080pRealDpi, 60, 1, new Integer(this.mViewingDistance), Float.valueOf(this.mAapResolution1080pPixelAspect), uiConfigBuilder);
            LogUtil.info(TAG, "Add supported display 1080P.");
        } else {
            videoConfiguration = this.mGal.displayCluster.nearestVideoConfiguration(this.mAapResolution480pWidth, this.mAapResolution480pHeight, this.mAapResolution480pReportDpi, this.mAapResolution480pRealDpi, 60, 1, new Integer(this.mViewingDistance), Float.valueOf(this.mAapResolution480pPixelAspect), null);
            LogUtil.info(TAG, "Add supported display 480P.");
        }
        this.mGal.displayCluster.addSupportedConfiguration(videoConfiguration);
        this.mGal.displayCluster.setCodecType(Protos.MediaCodecType.MEDIA_CODEC_VIDEO_H264_BP);
        this.mGal.displayCluster.setDisplayIdAndType(1, Protos.DisplayType.DISPLAY_TYPE_CLUSTER);
    }

    private void createVideo(Surface surface, int width, int height) {
        LogUtil.info(TAG, "create new video player width:" + width + " height:" + height);
        VideoPlayThread videoPlayThread = mVideoPlayThread;
        if (videoPlayThread != null) {
            videoPlayThread.createVideo(surface, width, height);
        }
        LogUtil.info(TAG, "createVideo end");
    }

    public void releaseVideo() {
        LogUtil.info(TAG, "releaseVideo is invoke mIsNewSemaphore:" + this.mIsNewSemaphore);
        this.mCurrVideoFocus = VideoSink.VIDEO_FOCUS_NATIVE;
        if (mVideoPlayThread != null) {
            LogUtil.info(TAG, "release the video player");
            mVideoPlayThread.stopVideo();
        }
        this.mIsVideoSetup = ENABLE_DEBUG_LOG;
        if (!this.mIsNewSemaphore) {
            this.mIsNewSemaphore = true;
            this.mCodecReleaseSemaphore = new Semaphore(0);
            tryAcquireCodecRelease();
        }
        LogUtil.info(TAG, "releaseVideo end");
    }

    public void releaseVideoAndHandler() {
        LogUtil.info(TAG, "releaseVideoHandler is invoke mIsNewSemaphore:" + this.mIsNewSemaphore);
        this.mIsVideoCreated = ENABLE_DEBUG_LOG;
        this.mCurrVideoFocus = VideoSink.VIDEO_FOCUS_NATIVE;
        this.mAlreadyShowSurface = null;
        synchronized (this.releaseObject) {
            if (this.mVideoPlayer != null) {
                LogUtil.info(TAG, "release video player:" + this.mIsVideoShowing + "mIsNewSemaphore:" + this.mIsNewSemaphore);
                this.mVideoPlayer.release();
                this.mVideoPlayer = null;
                if (this.mIsVideoShowing && !this.mIsNewSemaphore) {
                    this.mIsNewSemaphore = true;
                    this.mCodecReleaseSemaphore = new Semaphore(0);
                    tryAcquireCodecRelease();
                }
            }
        }
        this.mIsVideoSetup = ENABLE_DEBUG_LOG;
        if (mVideoPlayThread != null) {
            LogUtil.info(TAG, "release video handler");
            mVideoPlayThread.release();
            mVideoPlayThread = null;
        }
        if (this.mIsDumpVideo) {
            closeVideoFile();
        }
    }

    public int getCurrVideoFocusStatus() {
        if (this.mCurrVideoFocus == VideoSink.VIDEO_FOCUS_PROJECTED) {
            return ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_PROJECTED.ordinal();
        }
        return ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal();
    }

    public VideoSink.ProjectionListener getProjectionListener() {
        return this.mProjectionListener;
    }

    public VideoPlayer.Listener getVideoPlayerListener() {
        return this.mVideoPlayerListener;
    }

    public VideoPlayer getVideoPlayer() {
        return this.mVideoPlayer;
    }

    public boolean isVideoDataAvaiable() {
        return this.mIsVideoDataAvaiable;
    }

    public void setVideoDataAvaiable(boolean avaiable) {
        this.mIsVideoDataAvaiable = avaiable;
    }

    public boolean isVideoSetUp() {
        return this.mIsVideoSetup;
    }

    public void setVideoSetUp(boolean setup) {
        this.mIsVideoSetup = setup;
    }

    private class VideoPlayThread {
        private static final long INIT_WAIT_TIME_MS = 5000;
        private static final long STOP_WAIT_TIME_MS = 2000;
        private final Semaphore mInitSemaphore;
        private Looper mLooper;
        private volatile boolean mReleased;
        private final Thread mThread;
        private VideoHandler mVideoHandler;
        private final Semaphore mWaitStopSemaphore;

        private VideoPlayThread() {
            this.mInitSemaphore = new Semaphore(0);
            this.mWaitStopSemaphore = new Semaphore(0);
            this.mReleased = AapClusterVideoManager.ENABLE_DEBUG_LOG;
            this.mThread = new Thread() { // from class: com.ts.androidauto.aap.video.AapClusterVideoManager.VideoPlayThread.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Looper.prepare();
                    VideoPlayThread.this.mLooper = Looper.myLooper();
                    VideoPlayThread.this.mVideoHandler = VideoPlayThread.this.new VideoHandler();
                    VideoPlayThread.this.mInitSemaphore.release();
                    VideoPlayThread.this.mWaitStopSemaphore.release();
                    Looper.loop();
                }
            };
        }

        public void init() {
            this.mThread.start();
            try {
                this.mInitSemaphore.tryAcquire(INIT_WAIT_TIME_MS, TimeUnit.MILLISECONDS);
            } catch (InterruptedException exception) {
                LogUtil.error(AapClusterVideoManager.TAG, "init exception:" + exception.toString());
            }
        }

        public void release() {
            this.mReleased = true;
            Looper looper = this.mLooper;
            if (looper != null) {
                looper.quit();
            }
        }

        public void startVideo(int width, int height) {
            VideoHandler videoHandler;
            if (!this.mReleased && (videoHandler = this.mVideoHandler) != null) {
                videoHandler.sendMessage(videoHandler.obtainMessage(1, width, height));
            }
        }

        public void stopVideo() {
            VideoHandler videoHandler;
            if (!this.mReleased && (videoHandler = this.mVideoHandler) != null) {
                videoHandler.sendMessage(videoHandler.obtainMessage(2));
            }
        }

        public void createVideo(Surface surface, int width, int height) {
            VideoHandler videoHandler;
            if (!this.mReleased && (videoHandler = this.mVideoHandler) != null) {
                videoHandler.sendMessage(videoHandler.obtainMessage(4, width, height, surface));
            }
        }

        public class VideoHandler extends Handler {
            private static final int DO_CREATE = 4;
            private static final int DO_START = 1;
            private static final int DO_STOP = 2;
            private static final int DO_UPDATE = 3;
            private boolean isStopEnd = AapClusterVideoManager.ENABLE_DEBUG_LOG;
            private boolean isWaitStopTimeout = AapClusterVideoManager.ENABLE_DEBUG_LOG;
            private boolean isStart = AapClusterVideoManager.ENABLE_DEBUG_LOG;

            public VideoHandler() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Surface surface = (Surface) msg.obj;
                int width = msg.arg1;
                int height = msg.arg2;
                int i = msg.what;
                if (i == 1) {
                    if (!this.isStart) {
                        try {
                            boolean zTryAcquire = VideoPlayThread.this.mWaitStopSemaphore.tryAcquire(VideoPlayThread.STOP_WAIT_TIME_MS, TimeUnit.MILLISECONDS);
                            this.isStopEnd = zTryAcquire;
                            if (zTryAcquire) {
                                if (AapClusterVideoManager.this.mVideoPlayer != null) {
                                    this.isStart = true;
                                    AapClusterVideoManager.this.mVideoPlayer.prepareStart(width, height);
                                    AapClusterVideoManager.this.mVideoPlayer.start();
                                }
                            } else {
                                LogUtil.info(AapClusterVideoManager.TAG, "DO_START WaitStopSemaphore timeout isWaitStopTimeout: " + this.isWaitStopTimeout);
                                this.isWaitStopTimeout = true;
                            }
                            return;
                        } catch (InterruptedException exception) {
                            LogUtil.error(AapClusterVideoManager.TAG, "DO_START exception" + exception.toString());
                            return;
                        }
                    }
                    return;
                }
                if (i != 2) {
                    if (i == 4) {
                        AapClusterVideoManager.this.mRenderHeight = height;
                        AapClusterVideoManager.this.mRenderWidth = width;
                        synchronized (AapClusterVideoManager.this.releaseObject) {
                            if (AapClusterVideoManager.this.mVideoPlayer == null) {
                                LogUtil.info(AapClusterVideoManager.TAG, "handler mVideoPlayer is null");
                                AapClusterVideoManager.this.mVideoPlayer = new VideoPlayer(surface, AapClusterVideoManager.this.mRenderWidth, AapClusterVideoManager.this.mRenderHeight, AapClusterVideoManager.this.mVideoPlayerListener);
                            }
                        }
                        if (AapClusterVideoManager.this.mWidth != 0 && AapClusterVideoManager.this.mHeight != 0) {
                            LogUtil.info(AapClusterVideoManager.TAG, "handler createVideo prepare video");
                            AapClusterVideoManager.this.prepareVideo(AapClusterVideoManager.this.mWidth, AapClusterVideoManager.this.mHeight);
                        }
                        AapClusterVideoManager.this.setVidoeFocus(true);
                        AapClusterVideoManager.this.mVideoProjectionHandler.removeMessages(7);
                        AapClusterVideoManager.this.mVideoProjectionHandler.sendEmptyMessageDelayed(7, 3000L);
                        LogUtil.info(AapClusterVideoManager.TAG, "handler create video end");
                        return;
                    }
                    return;
                }
                this.isStart = AapClusterVideoManager.ENABLE_DEBUG_LOG;
                synchronized (AapClusterVideoManager.this.releaseObject) {
                    if (AapClusterVideoManager.this.mVideoPlayer != null) {
                        AapClusterVideoManager.this.mVideoPlayer.release();
                        AapClusterVideoManager.this.mVideoPlayer = null;
                    }
                }
                VideoPlayThread.this.mWaitStopSemaphore.release();
                if (AapClusterVideoManager.this.mIsShowInShowing) {
                    LogUtil.info(AapClusterVideoManager.TAG, "DO_STOP setVidoeFocus project to MD and show");
                    AapClusterVideoManager.this.mIsShowInShowing = AapClusterVideoManager.ENABLE_DEBUG_LOG;
                    VideoPlayThread videoPlayThread = VideoPlayThread.this;
                    videoPlayThread.createVideo(AapClusterVideoManager.this.mWaitShowSurface, AapClusterVideoManager.this.mWaitShowWidth, AapClusterVideoManager.this.mWaitShowHeight);
                    AapClusterVideoManager.this.setVidoeFocus(true);
                    AapClusterVideoManager.this.mAlreadyShowSurface = AapClusterVideoManager.this.mWaitShowSurface;
                    AapClusterVideoManager.this.mIsVideoShowing = true;
                    AapClusterVideoManager.this.onClusterVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_PROJECTED.ordinal());
                    return;
                }
                if (this.isWaitStopTimeout) {
                    LogUtil.info(AapClusterVideoManager.TAG, "DO_STOP end need start");
                    this.isWaitStopTimeout = AapClusterVideoManager.ENABLE_DEBUG_LOG;
                    if (AapClusterVideoManager.this.mVideoPlayer != null) {
                        this.isStart = true;
                        AapClusterVideoManager.this.mVideoPlayer.prepareStart(width, height);
                        AapClusterVideoManager.this.mVideoPlayer.start();
                    }
                }
            }
        }
    }

    public void saveVideoData(byte[] data) {
        LogUtil.info(TAG, "Test: saveVideoData  start");
        try {
            if (this.mRandomFile == null) {
                this.mRandomFile = new RandomAccessFile(DUMP_VIDEO_PATH, "rw");
            }
            this.mRandomFile.write(data, 0, data.length);
        } catch (FileNotFoundException exception) {
            LogUtil.warning(TAG, "Test: Save video File FileNotFoundException:" + exception.getMessage());
        } catch (IOException exception2) {
            LogUtil.warning(TAG, "Test: Save video File IOException:" + exception2.getMessage());
        } catch (Exception exception3) {
            LogUtil.warning(TAG, "Test: Save video File Exception:" + exception3.getMessage());
        }
    }

    public void closeVideoFile() {
        try {
            if (this.mRandomFile != null) {
                this.mRandomFile.close();
                LogUtil.info(TAG, "closeVideoFile mRandomFile");
                this.mRandomFile = null;
            }
        } catch (IOException exception) {
            LogUtil.warning(TAG, "closeVideoFile IOException:" + exception.getMessage());
            exception.printStackTrace();
        } catch (Exception exception2) {
            LogUtil.warning(TAG, "closeVideoFile Exception:" + exception2.getMessage());
            exception2.printStackTrace();
        }
    }

    public MotionEvent transformMotion(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        MotionEvent.PointerProperties[] pp = new MotionEvent.PointerProperties[pointerCount];
        MotionEvent.PointerCoords[] pc = new MotionEvent.PointerCoords[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            pp[i] = new MotionEvent.PointerProperties();
            event.getPointerProperties(i, pp[i]);
            pc[i] = new MotionEvent.PointerCoords();
            event.getPointerCoords(i, pc[i]);
            pc[i].x = ((pc[i].x - this.mAapTouchPointOffsetX) - this.mSurfaceViewScrollX) * this.mTouchXScale;
            pc[i].x = Math.min(this.mRealVideoWidth, Math.max(pc[i].x, 0.0f));
            pc[i].y = ((pc[i].y - this.mAapTouchPointOffsetY) - this.mSurfaceViewScrollY) * this.mTouchYScale;
            pc[i].y = Math.min(this.mRealVideoHeight, Math.max(pc[i].y, 0.0f));
        }
        return MotionEvent.obtain(event.getDownTime(), event.getEventTime(), event.getAction(), pointerCount, pp, pc, event.getMetaState(), event.getButtonState(), event.getXPrecision(), event.getYPrecision(), event.getDeviceId(), event.getEdgeFlags(), event.getSource(), event.getFlags());
    }
}
