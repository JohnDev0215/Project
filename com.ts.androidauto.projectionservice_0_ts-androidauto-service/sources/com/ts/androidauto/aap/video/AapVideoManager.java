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
import com.ts.androidauto.aap.audiofocus.StateManager;
import com.ts.androidauto.aap.common.AapConfig;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.aap.vehicledata.VehicleDataManager;
import com.ts.androidauto.aap.video.VideoPlayer;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
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
public class AapVideoManager {
    private static final int AAP_CODEC_CONFIG = 4;
    private static final int AAP_CODEC_SETUP = 5;
    private static final int AAP_DEFAULT_DPI = 120;
    private static final int AAP_DEFAULT_HEIGHT = 480;
    private static final float AAP_DEFAULT_PIXEL_ASPECT = 1.0f;
    private static final int AAP_DEFAULT_VIEWING_DISTANCE = 950;
    private static final int AAP_DEFAULT_WIDTH = 800;
    private static final int AAP_PROJECTION_UPDATE = 1;
    private static final int AAP_RESOLUTION_1080P = 1;
    private static final int AAP_RESOLUTION_1080P_BOTTOM_MARGIN = 90;
    private static final int AAP_RESOLUTION_1080P_TOP_MARGIN = 84;
    private static final int AAP_RESOLUTION_480P = 3;
    private static final int AAP_RESOLUTION_480P_BOTTOM_MARGIN = 54;
    private static final int AAP_RESOLUTION_480P_TOP_MARGIN = 49;
    private static final int AAP_RESOLUTION_720P = 2;
    private static final int AAP_RESOLUTION_720P_BOTTOM_MARGIN = 60;
    private static final int AAP_RESOLUTION_720P_TOP_MARGIN = 56;
    private static final int AAP_SOURCE_VIDEOCONFIG_CHANGED = 3;
    private static final int AAP_UICONFIG_INIT_VALUE = 0;
    private static final int AAP_VIDEO_DATA_TIMEOUT = 6;
    private static final int AAP_VIDEO_DATA_TIMEOUT_DELAY = 3000;
    private static final int AAP_VIDEO_FOCUS_REQUEST = 2;
    private static final int DECODER_ADDITIONAL_DEPTH = 1;
    public static final String DUMP_VIDEO_PATH = "/sdcard/dumpAapVideo/AapVideo.h264";
    private static final boolean ENABLE_DEBUG_LOG = false;
    private static final String FINISH_SHOW_ACTIVITY_ACTION = "com.ts.androidauto.app.display.aapactivity.RECEIVER_FINISH_ACTION";
    private static final int FPS_60 = 60;
    private static final int REQUEST_WAIT_CODEC_TIME_MS = 500;
    private static final int SHOW_PROJECTION_DELAY = 100;
    private static AapVideoManager mAapVideoManager;
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
    private int mAapResolution720pHeight;
    private float mAapResolution720pPixelAspect;
    private int mAapResolution720pRealDpi;
    private int mAapResolution720pReportDpi;
    private int mAapResolution720pWidth;
    private int mAapSurfaceView1080pHeight;
    private int mAapSurfaceView1080pScrollX;
    private int mAapSurfaceView1080pScrollY;
    private int mAapSurfaceView1080pWidth;
    private int mAapSurfaceView480pHeight;
    private int mAapSurfaceView480pScrollX;
    private int mAapSurfaceView480pScrollY;
    private int mAapSurfaceView480pWidth;
    private int mAapSurfaceView720pHeight;
    private int mAapSurfaceView720pScrollX;
    private int mAapSurfaceView720pScrollY;
    private int mAapSurfaceView720pWidth;
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
    private static final String TAG = "AAP" + AapVideoManager.class.getSimpleName();
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
    private Semaphore mCodecReleaseSemaphore = new Semaphore(0);
    private int mVideoHeight480P = 480;
    private int mVideoHeight720P = AapConfig.VIDEO_SIZE_720P;
    private Handler mVideoProjectionHandler = new Handler(Looper.getMainLooper()) { // from class: com.ts.androidauto.aap.video.AapVideoManager.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    AapVideoManager.this.onProjectionUpdate((VideoFrame) msg.obj);
                    break;
                case 2:
                    AapVideoManager.this.onVideoFocusRequest(msg.arg1, msg.arg2);
                    break;
                case 3:
                    AapVideoManager.this.onSourceVideoConfigChanged(msg.arg1, msg.arg2);
                    break;
                case 4:
                    AapVideoManager.this.onCodecConfig((byte[]) msg.obj);
                    break;
                case 5:
                    AapVideoManager.this.onCodecSetup(msg.arg1);
                    break;
                case 6:
                    if (AapVideoManager.this.mVideoControllerAdapter != null) {
                        AapVideoManager.this.mVideoControllerAdapter.requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_NATIVE);
                    }
                    break;
            }
        }
    };
    private final VideoPlayer.Listener mVideoPlayerListener = new VideoPlayer.Listener() { // from class: com.ts.androidauto.aap.video.AapVideoManager.2
        @Override // com.ts.androidauto.aap.video.VideoPlayer.Listener
        public void onFrameRendered(int sessionId) {
            synchronized (AapVideoManager.this.mLock) {
                if (AapVideoManager.this.mGal != null) {
                    AapVideoManager.this.mGal.display.ackFrames(sessionId, 1);
                }
            }
        }

        @Override // com.ts.androidauto.aap.video.VideoPlayer.Listener
        public void onMediaCodecReleased() {
            if (AapVideoManager.this.mCodecReleaseSemaphore != null) {
                LogUtil.info(AapVideoManager.TAG, "onMediaCodecReleased relese semaphore");
                AapVideoManager.this.mCodecReleaseSemaphore.release();
            }
        }

        @Override // com.ts.androidauto.aap.video.VideoPlayer.Listener
        public void onMediaCodecError() {
            LogUtil.info(AapVideoManager.TAG, "onMediaCodecError return to native");
            Intent finishShowActivityIntent = new Intent();
            finishShowActivityIntent.setAction(AapVideoManager.FINISH_SHOW_ACTIVITY_ACTION);
            if (AapVideoManager.this.mContext != null) {
                AapVideoManager.this.mContext.sendBroadcast(finishShowActivityIntent);
            }
        }
    };
    private final VideoSink.ProjectionListener mProjectionListener = new VideoSink.ProjectionListener() { // from class: com.ts.androidauto.aap.video.AapVideoManager.3
        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onProjectionUpdate(VideoFrame frame) {
            if (AapVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapVideoManager.this.mVideoProjectionHandler.obtainMessage(1, frame);
                AapVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onSourceVideoConfigChanged(int width, int height, Protos.VideoConfiguration config) {
            String str = AapVideoManager.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onSourceVideoConfigChanged start mVideoPlayer:");
            sb.append(AapVideoManager.this.mVideoPlayer == null ? true : AapVideoManager.ENABLE_DEBUG_LOG);
            sb.append(" height:");
            sb.append(height);
            sb.append(" mWidth:");
            sb.append(width);
            LogUtil.debug(str, sb.toString());
            if (AapVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapVideoManager.this.mVideoProjectionHandler.obtainMessage(3, width, height);
                AapVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapVideoManager.TAG, "onSourceVideoConfigChanged end");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onCodecConfig(byte[] data) {
            LogUtil.debug(AapVideoManager.TAG, "onCodecConfig start data: " + data.length + " mCurrVideoFocus:" + AapVideoManager.this.mCurrVideoFocus);
            if (AapVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapVideoManager.this.mVideoProjectionHandler.obtainMessage(4, data);
                AapVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapVideoManager.TAG, "onCodecConfig end");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onCodecSetup(int mediaCodecType) {
            LogUtil.debug(AapVideoManager.TAG, "onCodecSetup start MD codec is OK.");
            if (AapVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapVideoManager.this.mVideoProjectionHandler.obtainMessage(5, Integer.valueOf(mediaCodecType));
                AapVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
            }
            LogUtil.debug(AapVideoManager.TAG, "onCodecSetup end.");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onVideoFocusRequest(int focus, int reason) {
            LogUtil.debug(AapVideoManager.TAG, "onVideoFocusRequest start focus is " + focus + " mCurrVideoFocus:" + AapVideoManager.this.mCurrVideoFocus);
            if (AapVideoManager.this.mVideoProjectionHandler != null) {
                Message msg = AapVideoManager.this.mVideoProjectionHandler.obtainMessage(2, focus, reason);
                if (AapController.getInstance().getLinkStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED || focus != VideoSink.VIDEO_FOCUS_PROJECTED) {
                    AapVideoManager.this.mVideoProjectionHandler.sendMessage(msg);
                } else {
                    LogUtil.debug(AapVideoManager.TAG, "onVideoFocusRequest getLinkStatus()" + AapController.getInstance().getLinkStatus() + " mCurrVideoFocus:" + AapVideoManager.this.mCurrVideoFocus);
                    AapVideoManager.this.mVideoProjectionHandler.sendMessageDelayed(msg, 100L);
                }
            }
            LogUtil.debug(AapVideoManager.TAG, "onVideoFocusRequest end");
        }

        @Override // com.google.android.projection.protocol.VideoSink.ProjectionListener
        public void onVideoFocusModeChange(int focusMode, int reason) {
            LogUtil.info(AapVideoManager.TAG, "MD focus  has changed to be  " + focusMode);
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
                    ResourceConst.VideoFocusReqResult result = this.mVideoControllerAdapter.requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
                    LogUtil.info(TAG, "onVideoFocusRequest focus result is " + result);
                    if (result == ResourceConst.VideoFocusReqResult.VIDEOFOCUS_REQUEST_FAILED) {
                        setVidoeFocus(ENABLE_DEBUG_LOG);
                        return;
                    }
                    return;
                }
                this.mVideoControllerAdapter.requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_NATIVE);
                return;
            }
            this.mGal.display.setVideoFocus(focus, reason, ENABLE_DEBUG_LOG);
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
        if (height <= this.mVideoHeight480P) {
            VideoPlayer videoPlayer = this.mVideoPlayer;
            if (videoPlayer != null) {
                videoPlayer.updateSurfaceSize(this.mAapSurfaceView480pWidth, this.mAapSurfaceView480pHeight);
            }
            this.mVideoControllerAdapter.requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_AA_480P);
            this.mRealVideoWidth = this.mAapResolution480pWidth;
            this.mRealVideoHeight = this.mAapResolution480pHeight;
            this.mSurfaceViewScrollX = this.mAapSurfaceView480pScrollX;
            this.mSurfaceViewScrollY = this.mAapSurfaceView480pScrollY;
            surfaceWidth = this.mAapSurfaceView480pWidth;
            surfaceHeight = this.mAapSurfaceView480pHeight;
        } else if (height <= this.mVideoHeight720P) {
            VideoPlayer videoPlayer2 = this.mVideoPlayer;
            if (videoPlayer2 != null) {
                videoPlayer2.updateSurfaceSize(this.mAapSurfaceView720pWidth, this.mAapSurfaceView720pHeight);
            }
            this.mVideoControllerAdapter.requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_AA_720P);
            this.mRealVideoWidth = this.mAapResolution720pWidth;
            this.mRealVideoHeight = this.mAapResolution720pHeight;
            this.mSurfaceViewScrollX = this.mAapSurfaceView720pScrollX;
            this.mSurfaceViewScrollY = this.mAapSurfaceView720pScrollY;
            surfaceWidth = this.mAapSurfaceView720pWidth;
            surfaceHeight = this.mAapSurfaceView720pHeight;
        } else {
            VideoPlayer videoPlayer3 = this.mVideoPlayer;
            if (videoPlayer3 != null) {
                videoPlayer3.updateSurfaceSize(this.mAapSurfaceView1080pWidth, this.mAapSurfaceView1080pHeight);
            }
            this.mVideoControllerAdapter.requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_AA_1080P);
            this.mRealVideoWidth = this.mAapResolution1080pWidth;
            this.mRealVideoHeight = this.mAapResolution1080pHeight;
            this.mSurfaceViewScrollX = this.mAapSurfaceView1080pScrollX;
            this.mSurfaceViewScrollY = this.mAapSurfaceView1080pScrollY;
            surfaceWidth = this.mAapSurfaceView1080pWidth;
            surfaceHeight = this.mAapSurfaceView1080pHeight;
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
        this.mVideoProjectionHandler.removeMessages(6);
        if (this.mIsDumpVideo) {
            saveVideoData(data);
        }
        synchronized (this.releaseObject) {
            if (this.mVideoPlayer != null) {
                this.mVideoPlayer.handleCodecConfig(data);
            }
        }
        LogUtil.debug(TAG, "onCodecConfig end");
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

    public static synchronized AapVideoManager getInstance() {
        if (mAapVideoManager == null) {
            mAapVideoManager = new AapVideoManager();
        }
        return mAapVideoManager;
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

    private void updateVideoConfig() {
        VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
        if (info != null) {
            LogUtil.info(TAG, "updateVideoConfig, info ok!");
            updateVideoConfig480p(info.mAapVideoConfig480p);
            updateVideoConfig720p(info.mAapVideoConfig720p);
            updateVideoConfig1080p(info.mAapVideoConfig1080p);
            this.mAapTouchPointOffsetX = info.mAapTouchPointOffsetX;
            this.mAapTouchPointOffsetY = info.mAapTouchPointOffsetY;
            this.mViewingDistance = info.mViewingDistance;
            return;
        }
        LogUtil.info(TAG, "updateVideoConfig, info is null!");
        this.mAapResolution480pWidth = 800;
        this.mAapResolution480pHeight = 480;
        this.mAapResolution480pReportDpi = 120;
        this.mAapResolution480pRealDpi = 120;
        this.mAapResolution480pPixelAspect = AAP_DEFAULT_PIXEL_ASPECT;
        this.mAapSurfaceView480pWidth = 800;
        this.mAapSurfaceView480pHeight = 480;
        this.mAapSurfaceView480pScrollX = 0;
        this.mAapSurfaceView480pScrollY = 0;
        this.mAapResolution720pWidth = 0;
        this.mAapResolution720pHeight = 0;
        this.mAapResolution720pReportDpi = 0;
        this.mAapResolution720pRealDpi = 0;
        this.mAapResolution720pPixelAspect = AAP_DEFAULT_PIXEL_ASPECT;
        this.mAapSurfaceView720pWidth = 800;
        this.mAapSurfaceView720pHeight = 480;
        this.mAapSurfaceView720pScrollX = 0;
        this.mAapSurfaceView720pScrollY = 0;
        this.mAapResolution1080pWidth = 0;
        this.mAapResolution1080pHeight = 0;
        this.mAapResolution1080pReportDpi = 0;
        this.mAapResolution1080pRealDpi = 0;
        this.mAapResolution1080pPixelAspect = AAP_DEFAULT_PIXEL_ASPECT;
        this.mAapSurfaceView1080pWidth = 800;
        this.mAapSurfaceView1080pHeight = 480;
        this.mAapSurfaceView1080pScrollX = 0;
        this.mAapSurfaceView1080pScrollY = 0;
        this.mAapTouchPointOffsetX = 0;
        this.mAapTouchPointOffsetY = 0;
        this.mViewingDistance = AAP_DEFAULT_VIEWING_DISTANCE;
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

    private void updateVideoConfig720p(VehicleInfo.AapVideoConfig info) {
        this.mAapResolution720pWidth = info.mAapResolutionWidth;
        this.mAapResolution720pHeight = info.mAapResolutionHeight;
        this.mAapResolution720pReportDpi = info.mAapResolutionReportDpi;
        this.mAapResolution720pRealDpi = info.mAapResolutionRealDpi;
        this.mAapResolution720pPixelAspect = info.mAapResolutionPixelAspect;
        this.mAapSurfaceView720pWidth = info.mAapSurfaceViewWidth;
        this.mAapSurfaceView720pHeight = info.mAapSurfaceViewHeight;
        this.mAapSurfaceView720pScrollX = info.mAapSurfaceViewScrollX;
        this.mAapSurfaceView720pScrollY = info.mAapSurfaceViewScrollY;
    }

    private void updateVideoConfig1080p(VehicleInfo.AapVideoConfig info) {
        this.mAapResolution1080pWidth = info.mAapResolutionWidth;
        this.mAapResolution1080pHeight = info.mAapResolutionHeight;
        this.mAapResolution1080pReportDpi = info.mAapResolutionReportDpi;
        this.mAapResolution1080pRealDpi = info.mAapResolutionRealDpi;
        this.mAapResolution1080pPixelAspect = info.mAapResolutionPixelAspect;
        this.mAapSurfaceView1080pWidth = info.mAapSurfaceViewWidth;
        this.mAapSurfaceView1080pHeight = info.mAapSurfaceViewHeight;
        this.mAapSurfaceView1080pScrollX = info.mAapSurfaceViewScrollX;
        this.mAapSurfaceView1080pScrollY = info.mAapSurfaceViewScrollY;
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
                LogUtil.info(TAG, "Show video PROJECTED mIsVideoShowing:" + this.mIsVideoShowing);
                this.mCurrVideoFocus = VideoSink.VIDEO_FOCUS_PROJECTED;
                this.mGal.display.setVideoFocus(VideoSink.VIDEO_FOCUS_PROJECTED, -1, this.mIsMdRequest ^ true);
            }
        } else if ((this.mVideoPlayer != null && this.mCurrVideoFocus == VideoSink.VIDEO_FOCUS_PROJECTED) || this.mIsMdRequest) {
            LogUtil.info(TAG, "Hide video NATIVE mIsVideoShowing: " + this.mIsVideoShowing);
            this.mCurrVideoFocus = VideoSink.VIDEO_FOCUS_NATIVE;
            this.mGal.display.setVideoFocus(VideoSink.VIDEO_FOCUS_NATIVE, -1, this.mIsMdRequest ^ true);
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
                StateManager.getInstance().setVideoShowing(true);
                onVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_PROJECTED.ordinal());
            }
        }
    }

    public void hideVideo() {
        LogUtil.info(TAG, "hideVideo() mCurrVideoFocus:" + this.mCurrVideoFocus + " mIsVideoShowing:" + this.mIsVideoShowing);
        synchronized (this.syncObject) {
            if (this.mIsVideoShowing) {
                this.mIsVideoShowing = ENABLE_DEBUG_LOG;
                setVidoeFocus(ENABLE_DEBUG_LOG);
                this.mVideoProjectionHandler.removeMessages(6);
                releaseVideo();
                StateManager.getInstance().setVideoShowing(ENABLE_DEBUG_LOG);
                onVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal());
            }
        }
    }

    public void onVideoFocusStatusChanged(int videoFocusStatus) {
        LogUtil.info(TAG, "onVideoFocusStatusChanged: " + videoFocusStatus);
        AapVideoCallback aapVideoCallback = this.mAapVideoCallback;
        if (aapVideoCallback != null) {
            aapVideoCallback.onVideoFocusStatusChanged(videoFocusStatus);
        }
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
        boolean isLastVideoMode = ENABLE_DEBUG_LOG;
        LogUtil.info(TAG, "setHuVideoFocus: isConnected? " + isConnected + "isVideoShowing? " + this.mIsVideoShowing + "isLastVideoMode? " + ENABLE_DEBUG_LOG);
        if (isConnected && this.mIsVideoShowing) {
            setVidoeFocus(!isNative);
        }
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            ResourceConst.LastVideoMode lastVideoMode = iVideoControllerAdapter.getLastVideoMode();
            LogUtil.info(TAG, "isLastVideoMode lastVideoMode:" + lastVideoMode);
            if (lastVideoMode == ResourceConst.LastVideoMode.PROJECTION_MODE && !isNative) {
                isLastVideoMode = true;
            }
        }
        LogUtil.info(TAG, "isLastVideoMode? " + isLastVideoMode);
        if (AapController.getInstance().getLinkStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED && !this.mIsVideoShowing && isLastVideoMode) {
            ResourceConst.VideoFocusReqResult result = this.mVideoControllerAdapter.requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
            LogUtil.info(TAG, "onVideoFocusRequest focus result is " + result);
        }
    }

    private void addSupportConfiguration() {
        LogUtil.info(TAG, "Add supported configuration start.");
        if (this.mAapResolution1080pWidth > 0 && this.mAapResolution1080pHeight > 0) {
            Protos.VideoConfiguration videoConfiguration = this.mGal.display.nearestVideoConfiguration(this.mAapResolution1080pWidth, this.mAapResolution1080pHeight, this.mAapResolution1080pReportDpi, this.mAapResolution1080pRealDpi, 60, 1, new Integer(this.mViewingDistance), Float.valueOf(this.mAapResolution1080pPixelAspect), getInitUiConfig(1));
            LogUtil.info(TAG, "Add supported display 1080P.");
            this.mGal.display.addSupportedConfiguration(videoConfiguration);
        }
        if (this.mAapResolution720pWidth > 0 && this.mAapResolution720pHeight > 0) {
            Protos.VideoConfiguration videoConfiguration2 = this.mGal.display.nearestVideoConfiguration(this.mAapResolution720pWidth, this.mAapResolution720pHeight, this.mAapResolution720pReportDpi, this.mAapResolution720pRealDpi, 60, 1, new Integer(this.mViewingDistance), Float.valueOf(this.mAapResolution720pPixelAspect), getInitUiConfig(2));
            LogUtil.info(TAG, "Add supported display 720P.");
            this.mGal.display.addSupportedConfiguration(videoConfiguration2);
        }
        Protos.VideoConfiguration videoConfiguration3 = this.mGal.display.nearestVideoConfiguration(this.mAapResolution480pWidth, this.mAapResolution480pHeight, this.mAapResolution480pReportDpi, this.mAapResolution480pRealDpi, 60, 1, new Integer(this.mViewingDistance), Float.valueOf(this.mAapResolution480pPixelAspect), getInitUiConfig(3));
        LogUtil.info(TAG, "Add supported display 480P.");
        this.mGal.display.addSupportedConfiguration(videoConfiguration3);
        this.mGal.display.setCodecType(Protos.MediaCodecType.MEDIA_CODEC_VIDEO_H264_BP);
        this.mGal.display.setDisplayIdAndType(0, Protos.DisplayType.DISPLAY_TYPE_MAIN);
    }

    private void createVideo(Surface surface, int width, int height) {
        LogUtil.info(TAG, "create new video player width:" + width + " height:" + height);
        VideoPlayThread videoPlayThread = mVideoPlayThread;
        if (videoPlayThread != null) {
            videoPlayThread.createVideo(surface, width, height);
        }
        LogUtil.info(TAG, "createVideo end");
    }

    private Protos.UiConfig.Builder getInitUiConfig(int type) {
        Protos.Insets margins;
        Protos.Insets contentInsets = (Protos.Insets) Protos.Insets.newBuilder().setLeft(0).setRight(0).setTop(0).setBottom(0).build();
        Protos.Insets stableContentInsets = (Protos.Insets) Protos.Insets.newBuilder().setLeft(0).setRight(0).setTop(0).setBottom(0).build();
        if (type == 1) {
            margins = (Protos.Insets) Protos.Insets.newBuilder().setLeft(0).setRight(0).setTop(84).setBottom(90).build();
        } else if (type == 2) {
            margins = (Protos.Insets) Protos.Insets.newBuilder().setLeft(0).setRight(0).setTop(56).setBottom(60).build();
        } else if (type == 3) {
            margins = (Protos.Insets) Protos.Insets.newBuilder().setLeft(0).setRight(0).setTop(49).setBottom(54).build();
        } else {
            margins = (Protos.Insets) Protos.Insets.newBuilder().setLeft(0).setRight(0).setTop(0).setBottom(0).build();
        }
        Protos.UiConfig.Builder uiConfigBuilder = Protos.UiConfig.newBuilder().mergeContentInsets(contentInsets).mergeStableContentInsets(stableContentInsets).mergeMargins(margins);
        return uiConfigBuilder;
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
        if (this.mIsVideoShowing) {
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
            this.mReleased = AapVideoManager.ENABLE_DEBUG_LOG;
            this.mThread = new Thread() { // from class: com.ts.androidauto.aap.video.AapVideoManager.VideoPlayThread.1
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
                LogUtil.error(AapVideoManager.TAG, "init exception:" + exception.toString());
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
            private boolean isStopEnd = AapVideoManager.ENABLE_DEBUG_LOG;
            private boolean isWaitStopTimeout = AapVideoManager.ENABLE_DEBUG_LOG;
            private boolean isStart = AapVideoManager.ENABLE_DEBUG_LOG;

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
                                if (AapVideoManager.this.mVideoPlayer != null) {
                                    this.isStart = true;
                                    AapVideoManager.this.mVideoPlayer.prepareStart(width, height);
                                    AapVideoManager.this.mVideoPlayer.start();
                                }
                            } else {
                                LogUtil.info(AapVideoManager.TAG, "DO_START WaitStopSemaphore timeout isWaitStopTimeout: " + this.isWaitStopTimeout);
                                this.isWaitStopTimeout = true;
                            }
                            return;
                        } catch (InterruptedException exception) {
                            LogUtil.error(AapVideoManager.TAG, "DO_START exception" + exception.toString());
                            return;
                        }
                    }
                    return;
                }
                if (i != 2) {
                    if (i == 4) {
                        AapVideoManager.this.mRenderHeight = height;
                        AapVideoManager.this.mRenderWidth = width;
                        synchronized (AapVideoManager.this.releaseObject) {
                            if (AapVideoManager.this.mVideoPlayer == null) {
                                LogUtil.info(AapVideoManager.TAG, "handler mVideoPlayer is null");
                                AapVideoManager.this.mVideoPlayer = new VideoPlayer(surface, AapVideoManager.this.mRenderWidth, AapVideoManager.this.mRenderHeight, AapVideoManager.this.mVideoPlayerListener);
                            }
                        }
                        if (AapVideoManager.this.mWidth != 0 && AapVideoManager.this.mHeight != 0) {
                            LogUtil.info(AapVideoManager.TAG, "handler createVideo prepare video");
                            AapVideoManager.this.prepareVideo(AapVideoManager.this.mWidth, AapVideoManager.this.mHeight);
                        }
                        AapVideoManager.this.setVidoeFocus(true);
                        AapVideoManager.this.mVideoProjectionHandler.removeMessages(6);
                        AapVideoManager.this.mVideoProjectionHandler.sendEmptyMessageDelayed(6, 3000L);
                        LogUtil.info(AapVideoManager.TAG, "handler create video end");
                        return;
                    }
                    return;
                }
                this.isStart = AapVideoManager.ENABLE_DEBUG_LOG;
                synchronized (AapVideoManager.this.releaseObject) {
                    if (AapVideoManager.this.mVideoPlayer != null) {
                        AapVideoManager.this.mVideoPlayer.release();
                        AapVideoManager.this.mVideoPlayer = null;
                    }
                }
                VideoPlayThread.this.mWaitStopSemaphore.release();
                if (AapVideoManager.this.mIsShowInShowing) {
                    LogUtil.info(AapVideoManager.TAG, "DO_STOP setVidoeFocus project to MD and show");
                    AapVideoManager.this.mIsShowInShowing = AapVideoManager.ENABLE_DEBUG_LOG;
                    VideoPlayThread videoPlayThread = VideoPlayThread.this;
                    videoPlayThread.createVideo(AapVideoManager.this.mWaitShowSurface, AapVideoManager.this.mWaitShowWidth, AapVideoManager.this.mWaitShowHeight);
                    AapVideoManager.this.setVidoeFocus(true);
                    AapVideoManager.this.mAlreadyShowSurface = AapVideoManager.this.mWaitShowSurface;
                    AapVideoManager.this.mIsVideoShowing = true;
                    StateManager.getInstance().setVideoShowing(true);
                    AapVideoManager.this.onVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_PROJECTED.ordinal());
                    return;
                }
                if (this.isWaitStopTimeout) {
                    LogUtil.info(AapVideoManager.TAG, "DO_STOP end need start");
                    this.isWaitStopTimeout = AapVideoManager.ENABLE_DEBUG_LOG;
                    if (AapVideoManager.this.mVideoPlayer != null) {
                        this.isStart = true;
                        AapVideoManager.this.mVideoPlayer.prepareStart(width, height);
                        AapVideoManager.this.mVideoPlayer.start();
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
