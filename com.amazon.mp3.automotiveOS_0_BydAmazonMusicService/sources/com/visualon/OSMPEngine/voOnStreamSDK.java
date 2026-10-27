package com.visualon.OSMPEngine;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.visualon.OSMPOutputControl.voOSDisplayManager;
import com.visualon.OSMPOutputControl.voOSOutputControlServer;
import com.visualon.OSMPRender.voAudioRender;
import com.visualon.OSMPRender.voVSyncHelper;
import com.visualon.OSMPSubTitle.VOSubtitleRender;
import com.visualon.OSMPUtils.voBluetoothManager;
import com.visualon.OSMPUtils.voLicenseDisplayRender;
import com.visualon.OSMPUtils.voLoadJNI;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSAudioFormat;
import com.visualon.OSMPUtils.voOSAudioFormatImpl;
import com.visualon.OSMPUtils.voOSAudioRenderFormat;
import com.visualon.OSMPUtils.voOSCPUInfoImpl;
import com.visualon.OSMPUtils.voOSLoadAudioCodec;
import com.visualon.OSMPUtils.voOSModuleVersionImpl;
import com.visualon.OSMPUtils.voOSOption;
import com.visualon.OSMPUtils.voOSPCMBufferImpl;
import com.visualon.OSMPUtils.voOSRectImpl;
import com.visualon.OSMPUtils.voOSSEIPicTimingImpl;
import com.visualon.OSMPUtils.voOSSEIUserDataImpl;
import com.visualon.OSMPUtils.voOSSubtitleLanguage;
import com.visualon.OSMPUtils.voOSSubtitleLanguageImpl;
import com.visualon.OSMPUtils.voOSType;
import com.visualon.OSMPUtils.voOSVideoFormat;
import com.visualon.OSMPUtils.voOSVideoFormatImpl;
import com.visualon.OSMPUtils.voOSVideoPerformance;
import com.visualon.OSMPUtils.voOSVideoPerformanceImpl;
import com.visualon.OSMPUtils.voUnifyDecoderManager;
import com.visualon.OSMPUtils.voVideoViewController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes7.dex */
public final class voOnStreamSDK implements voAudioRender.onAudioRenderListener, voVideoViewController.onViewChangeListener, voOSOutputControlServer.onOutputControlListener, voUnifyDecoderManager.onUnifyDecoderListener, voBluetoothManager.onStatusChangeListener {
    private static final String LIB_NAME = "voOSEng";
    private static final String TAG = "@@@voOnStreamSDK.java";
    private static boolean mJNILoaded = false;
    private static final int messageBluetooth = -252706801;
    private static final int messageSetView = -252706807;
    private voAudioRender mAudioRender;
    private int mChannels;
    private Context mContext;
    private boolean mEventFinish;
    private EventHandler mEventHandler;
    private onEventListener mEventListener;
    private int mEventMsg;
    private int mEventParam1;
    private int mEventParam2;
    private int mHeightVideo;
    private voLicenseDisplayRender mLicDisplayRender;
    private long mNativeContext;
    private View mOverWritedView;
    private int mSampleBit;
    private int mSampleRate;
    private Surface mSurface;
    private SurfaceHolder mSurfaceHolder;
    private int mThirdLibOp;
    Thread mThreadEvent;
    private View mView;
    private int mWidthVideo;
    private voOSOutputControlServer mOutputControlServer = null;
    private voVideoViewController mVideoViewCtrl = new voVideoViewController();
    private voUnifyDecoderManager mUnifyDecoderManager = new voUnifyDecoderManager();
    private VOSubtitleRender mSubtitleRender = new VOSubtitleRender();
    private VOSubtitleRender mAnaRender = null;
    private final ReentrantLock mutex_ = new ReentrantLock();
    private voVSyncHelper mVSyncHelper = null;
    private voBluetoothManager mBluetoothManager = null;

    public interface onEventListener {
        int onEvent(int i, int i2, int i3, Object obj);
    }

    private native long nativeClose(long j);

    private native long nativeCreate(Object obj, String str, long j, long j2, long j3);

    private native long nativeDestroy(long j);

    private native long nativeGetAudioData(long j, byte[] bArr);

    private native Object nativeGetParam(long j, long j2);

    private native Object nativeGetParam(long j, long j2, Object obj);

    private native long nativeGetPos(long j);

    private native Object nativeGetSEITimingInfo(long j, int i, long j2);

    private native Object nativeGetSubTitleSample(long j, int i);

    private native int nativeGetSubtitleLanguageCount(long j);

    private native Object nativeGetSubtitleLanguageInfo(long j, int i);

    private native long nativeGetVideoData(long j, byte[] bArr);

    private native void nativeOnLog(int i, String str, String str2);

    private native long nativeOpen(long j, Object obj, long j2, Object obj2);

    private native long nativePause(long j);

    private native long nativeRun(long j);

    private native int nativeSelectSubtitleLanguage(long j, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeSetParam(long j, long j2, Object obj);

    private native long nativeSetPos(long j, long j2);

    private native long nativeSetPosTolerance(long j, long j2, int i, int i2);

    private native long nativeSetSurface(long j, Object obj);

    private native long nativeStop(long j);

    protected native int nativePreviewSubtitle(long j, String str, Object obj);

    protected native int nativeResetSubtitleParameter(long j);

    protected native int nativeSetSubtitleBoundingBox(long j, int i, int i2, int i3, int i4);

    protected native int nativeSetSubtitleFontBackgroundColor(long j, int i);

    protected native int nativeSetSubtitleFontBackgroundOpacity(long j, int i);

    protected native int nativeSetSubtitleFontColor(long j, int i);

    protected native int nativeSetSubtitleFontEdgeColor(long j, int i);

    protected native int nativeSetSubtitleFontEdgeOpacity(long j, int i);

    protected native int nativeSetSubtitleFontEdgeType(long j, int i);

    protected native int nativeSetSubtitleFontItalic(long j, boolean z);

    protected native int nativeSetSubtitleFontName(long j, String str);

    protected native int nativeSetSubtitleFontOpacity(long j, int i);

    protected native int nativeSetSubtitleFontSizeScale(long j, int i);

    protected native int nativeSetSubtitleFontUnderline(long j, boolean z);

    protected native int nativeSetSubtitleGravity(long j, int i, int i2);

    protected native int nativeSetSubtitleTrim(long j, String str);

    protected native int nativeSetSubtitleWindowBackgroundColor(long j, int i);

    protected native int nativeSetSubtitleWindowBackgroundOpacity(long j, int i);

    protected native int nativeStSubtitleFontBold(long j, boolean z);

    public void setEventListener(onEventListener oneventlistener) {
        this.mEventListener = oneventlistener;
    }

    public class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (voOnStreamSDK.this.mNativeContext != 0) {
                if (!voOnStreamSDK.mJNILoaded) {
                    voLog.e(voOnStreamSDK.TAG, "Fail to load JNI library", new Object[0]);
                    return;
                }
                int i = message.what;
                if (i == voOnStreamSDK.messageSetView) {
                    voOnStreamSDK.this.setViewInMainThread((View) message.obj);
                    return;
                }
                if (i == voOnStreamSDK.messageBluetooth) {
                    if (voOnStreamSDK.this.mAudioRender.DSPClockEnable()) {
                        return;
                    }
                    voOnStreamSDK.this.mAudioRender.setBluetoothStatus(((Boolean) message.obj).booleanValue());
                    voOnStreamSDK.this.mAudioRender.notifyReconfigAudioTrack();
                    return;
                }
                if (i == 15) {
                    voOnStreamSDK.this.mVideoViewCtrl.handleExternalMessage(message);
                    voOnStreamSDK.this.mEventFinish = true;
                } else if (i == 37) {
                    voLog.i(voOnStreamSDK.TAG, "Receive VOOSMP_CB_Block_Output, output control type is 0x%X, value is %d", Integer.valueOf(message.arg1), Integer.valueOf(message.arg2));
                    if ((message.arg1 & voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_ANTI_MIRRORING.getValue()) > 0 && (message.arg1 & voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_ALL_DISPLAY.getValue()) == 0) {
                        if (voOnStreamSDK.this.mOutputControlServer != null && message.arg2 == 0) {
                            voOnStreamSDK.this.mOutputControlServer.closeMiracast();
                        }
                    } else if ((message.arg1 & voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_ALL_DISPLAY.getValue()) > 0 || (message.arg1 & voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDCP.getValue()) > 0) {
                        if (voOnStreamSDK.this.mView == null || message.arg2 != 0) {
                            if (voOnStreamSDK.this.mView != null && message.arg2 == 1 && voOnStreamSDK.this.mOverWritedView != null) {
                                voOnStreamSDK.this.mOverWritedView.setVisibility(8);
                            }
                        } else if (voOnStreamSDK.this.mOverWritedView == null) {
                            voOnStreamSDK.this.mOverWritedView = new View(voOnStreamSDK.this.mContext);
                            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                            voOnStreamSDK.this.mOverWritedView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                            ViewParent parent = voOnStreamSDK.this.mView.getParent();
                            if (parent instanceof RelativeLayout) {
                                ((RelativeLayout) parent).addView(voOnStreamSDK.this.mOverWritedView, layoutParams);
                            } else if (parent instanceof FrameLayout) {
                                ((FrameLayout) parent).addView(voOnStreamSDK.this.mOverWritedView, layoutParams);
                            } else {
                                voLog.e(voOnStreamSDK.TAG, "Receive VOOSMP_CB_Block_Output, Current playback view's parent layout isn't RelativeLayout or FrameLayout, don't implement block all display function.", new Object[0]);
                            }
                        } else {
                            voOnStreamSDK.this.mOverWritedView.setVisibility(0);
                        }
                    }
                } else if (i == 42 || i == 46) {
                    voOnStreamSDK.this.mVideoViewCtrl.handleExternalMessage(message);
                } else if (i == 33) {
                    voOnStreamSDK voonstreamsdk = voOnStreamSDK.this;
                    voonstreamsdk.nativeSetParam(voonstreamsdk.mNativeContext, 114L, 1);
                }
                if (voOnStreamSDK.this.mEventListener != null) {
                    voOnStreamSDK.this.mEventListener.onEvent(message.what, message.arg1, message.arg2, message.obj);
                    return;
                }
                return;
            }
            voLog.w(voOnStreamSDK.TAG, "vomeplayer went away with unhandled events", new Object[0]);
        }
    }

    public voOnStreamSDK() {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mEventHandler = new EventHandler(looperMyLooper);
            voLog.v(TAG, "Looper.myLooper()", new Object[0]);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(mainLooper);
                voLog.v(TAG, "getMainLooper()", new Object[0]);
            } else {
                this.mEventHandler = null;
            }
        }
        this.mAudioRender = null;
        this.mSurface = null;
        this.mSurfaceHolder = null;
        this.mView = null;
        this.mOverWritedView = null;
        this.mNativeContext = 0L;
        this.mEventListener = null;
        this.mContext = null;
        this.mWidthVideo = 0;
        this.mHeightVideo = 0;
        this.mSampleRate = 0;
        this.mChannels = 0;
        this.mSampleBit = 16;
        this.mEventFinish = true;
        this.mThirdLibOp = 0;
        this.mVideoViewCtrl.setViewChangeListener(this);
        this.mVideoViewCtrl.withEnforceSWVideoScaling(false);
        this.mUnifyDecoderManager.setListener(this);
    }

    /* JADX WARN: Finally extract failed */
    public int Init(Context context, String str, List<voOSOption> list, long j, long j2, long j3) throws IllegalStateException {
        voLog.initLogLib(str);
        this.mVideoViewCtrl.reset();
        this.mVideoViewCtrl.withEnforceSWVideoScaling(false);
        this.mContext = context;
        this.mVideoViewCtrl.withContext(context);
        voOSOutputControlServer voosoutputcontrolserver = new voOSOutputControlServer(this.mContext, this.mEventHandler);
        this.mOutputControlServer = voosoutputcontrolserver;
        voosoutputcontrolserver.setOutputControlListener(this);
        if (!loadJNI(str)) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        Date date = new Date(System.currentTimeMillis());
        this.mutex_.lock();
        try {
            int iNativeCreate = (int) nativeCreate(this, "".equals(str) ? null : str, j, j2, j3);
            this.mutex_.unlock();
            this.mOutputControlServer.startDisplayListener();
            voAudioRender voaudiorender = new voAudioRender(this);
            this.mAudioRender = voaudiorender;
            voaudiorender.init();
            SetParam(69636L, this.mSubtitleRender);
            voLog.i(TAG, "init spend time is %d", Integer.valueOf(Long.valueOf(new Date(System.currentTimeMillis()).getTime() - date.getTime()).intValue()));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            SetParam(4138L, new voOSVideoFormatImpl(displayMetrics.widthPixels, displayMetrics.heightPixels, 0));
            this.mUnifyDecoderManager.setParam(voUnifyDecoderManager.VARSKEY.VARSKEY_CONTEXT, this.mContext);
            this.mUnifyDecoderManager.setParam(voUnifyDecoderManager.VARSKEY.VARSKEY_VIEW_CONTROL, this.mVideoViewCtrl);
            SetParam(22L, 1);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    voOSOption voosoption = list.get(i);
                    if (voosoption.getType() == voOSOption.eVoOption.eoVideoRender) {
                        int value = (int) voosoption.getValue();
                        SetParam(22L, Integer.valueOf(value));
                        voLog.v(TAG, "SetParam voOSType.VOOSMP_PID_VIDEO_RENDER_TYPE is %d .", Integer.valueOf(value));
                    }
                }
            }
            voVSyncHelper vovsynchelperNewVSyncHelper = voVSyncHelper.newVSyncHelper(context);
            this.mVSyncHelper = vovsynchelperNewVSyncHelper;
            if (vovsynchelperNewVSyncHelper != null) {
                vovsynchelperNewVSyncHelper.init();
            }
            if (this.mBluetoothManager == null) {
                voBluetoothManager vobluetoothmanager = new voBluetoothManager(this.mContext, this);
                this.mBluetoothManager = vobluetoothmanager;
                vobluetoothmanager.init();
            }
            return iNativeCreate;
        } catch (Throwable th) {
            this.mutex_.unlock();
            throw th;
        }
    }

    public int Open(Object obj, long j) throws IllegalStateException {
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        if (this.mSurface == null) {
            voLog.e(TAG, "SurfaceView/Surface should be set before open function", new Object[0]);
            return voOSType.VOOSMP_ERR_Uninitialize;
        }
        if (!this.mUnifyDecoderManager.isDeviceHardwareSupport()) {
            this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(-2147483632, -1, -1, null));
            return 0;
        }
        this.mWidthVideo = 0;
        this.mHeightVideo = 0;
        return (int) nativeOpen(this.mNativeContext, obj, j, this.mSurface);
    }

    public int Close() {
        voLog.v(TAG, "Close -", new Object[0]);
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        int iNativeClose = (int) nativeClose(this.mNativeContext);
        voLog.v(TAG, "Close +, return value is " + iNativeClose, new Object[0]);
        this.mWidthVideo = 0;
        this.mHeightVideo = 0;
        this.mVideoViewCtrl.reset();
        return iNativeClose;
    }

    public int Run() {
        if (this.mSurface == null) {
            voLog.e(TAG, "SurfaceView/Surface should be set before run function", new Object[0]);
            return voOSType.VOOSMP_ERR_Uninitialize;
        }
        voLog.v(TAG, "run", new Object[0]);
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        Surface surface = this.mSurface;
        if (surface != null && !surface.isValid()) {
            voLog.e(TAG, "@@@Surface voOnStreamSDK::Run surface is inValid ----------", new Object[0]);
        }
        int iNativeRun = (int) nativeRun(this.mNativeContext);
        if (iNativeRun != 0) {
            voLog.v(TAG, "run return error", new Object[0]);
            return iNativeRun;
        }
        voAudioRender voaudiorender = this.mAudioRender;
        if (voaudiorender != null) {
            voaudiorender.run();
        }
        Surface surface2 = this.mSurface;
        if (surface2 != null && !surface2.isValid()) {
            voLog.e(TAG, "@@@Surface voOnStreamSDK::Run surface is inValid +++++++++++++", new Object[0]);
        }
        voVSyncHelper vovsynchelper = this.mVSyncHelper;
        if (vovsynchelper != null) {
            vovsynchelper.enable();
            SetParam(69648L, Long.valueOf(this.mVSyncHelper.getContext()));
        }
        return iNativeRun;
    }

    public int Pause() {
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        voAudioRender voaudiorender = this.mAudioRender;
        if (voaudiorender != null) {
            voaudiorender.pause();
        }
        int iNativePause = (int) nativePause(this.mNativeContext);
        voLog.i(TAG, "Engine pause return is 0x%X, audio and video Render puase.", Integer.valueOf(iNativePause));
        return iNativePause;
    }

    public int Stop() {
        voLog.v(TAG, "stop-", new Object[0]);
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        voAudioRender voaudiorender = this.mAudioRender;
        if (voaudiorender != null) {
            voaudiorender.stop();
        }
        nativeSetParam(this.mNativeContext, 42L, new Integer(0));
        View view = this.mOverWritedView;
        if (view != null) {
            view.setVisibility(4);
        }
        voLog.v(TAG, "nativeStop-", new Object[0]);
        int iNativeStop = (int) nativeStop(this.mNativeContext);
        voLog.v(TAG, "stop+ nativeStop+, return value is " + iNativeStop, new Object[0]);
        this.mVideoViewCtrl.stop();
        if (this.mVSyncHelper != null) {
            SetParam(69648L, 0L);
            this.mVSyncHelper.disable();
        }
        return iNativeStop;
    }

    public int SetPos(long j) {
        return SetPosTolerance(j, 0, 0);
    }

    public int SetPosTolerance(long j, int i, int i2) {
        voLog.v(TAG, "setPospos is " + j, new Object[0]);
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        int iNativeSetPosTolerance = (int) nativeSetPosTolerance(this.mNativeContext, j, i, i2);
        if (iNativeSetPosTolerance != 0) {
            return iNativeSetPosTolerance;
        }
        voAudioRender voaudiorender = this.mAudioRender;
        if (voaudiorender != null) {
            voaudiorender.flush();
            this.mAudioRender.notifyReconfigAudioTrack(voAudioRender.UNSTABLE_REASON.SEEK);
        }
        return 0;
    }

    public int GetPos() {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : (int) nativeGetPos(this.mNativeContext);
    }

    public int SetVolume(float f, float f2) throws IllegalStateException {
        voAudioRender voaudiorender = this.mAudioRender;
        if (voaudiorender == null) {
            return 0;
        }
        voaudiorender.arsetVolume(f, f2);
        return 0;
    }

    public int Uninit() {
        int iIntValue;
        voLog.i(TAG, "Uninit -", new Object[0]);
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        Date date = new Date(System.currentTimeMillis());
        Object objGetParam = GetParam(14L);
        if (objGetParam != null && (objGetParam instanceof Integer) && (iIntValue = ((Integer) objGetParam).intValue()) != 0 && iIntValue != 4) {
            Stop();
        }
        voAudioRender voaudiorender = this.mAudioRender;
        if (voaudiorender != null) {
            voaudiorender.unInit();
            this.mAudioRender = null;
        }
        this.mutex_.lock();
        try {
            long jNativeDestroy = nativeDestroy(this.mNativeContext);
            if (0 == jNativeDestroy) {
                this.mNativeContext = 0L;
            }
            this.mutex_.unlock();
            this.mEventListener = null;
            this.mEventHandler.removeCallbacksAndMessages(null);
            this.mVideoViewCtrl.withEnforceSWVideoScaling(false);
            this.mUnifyDecoderManager.setListener(null);
            this.mOutputControlServer.stopDisplayListener();
            voLog.i(TAG, "Uninit + , init spend time is %d", Integer.valueOf(Long.valueOf(new Date(System.currentTimeMillis()).getTime() - date.getTime()).intValue()));
            voVSyncHelper vovsynchelper = this.mVSyncHelper;
            if (vovsynchelper != null) {
                vovsynchelper.uninit();
                this.mVSyncHelper = null;
            }
            voBluetoothManager vobluetoothmanager = this.mBluetoothManager;
            if (vobluetoothmanager != null) {
                vobluetoothmanager.uninit();
                this.mBluetoothManager = null;
            }
            return (int) jNativeDestroy;
        } catch (Throwable th) {
            this.mutex_.unlock();
            throw th;
        }
    }

    private void setViewInSubThread(View view) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return;
        }
        this.mSurface = null;
        this.mSurfaceHolder = null;
        if (view == null) {
            voLog.w(TAG, "SetView Surface Destroy", new Object[0]);
        } else if (view instanceof SurfaceView) {
            this.mSurfaceHolder = ((SurfaceView) view).getHolder();
            voLog.i(TAG, "setDisplay suface is " + this.mSurfaceHolder, new Object[0]);
            SurfaceHolder surfaceHolder = this.mSurfaceHolder;
            if (surfaceHolder != null) {
                this.mSurface = surfaceHolder.getSurface();
            }
        } else if (view instanceof TextureView) {
            this.mSurface = new Surface(((TextureView) view).getSurfaceTexture());
        }
        Surface surface = this.mSurface;
        if (surface != null && !surface.isValid()) {
            voLog.e(TAG, "@@@Surface surface is inValid", new Object[0]);
        }
        nativeSetSurface(this.mNativeContext, this.mSurface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewInMainThread(View view) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return;
        }
        this.mView = view;
        this.mVideoViewCtrl.withSurfaceView(view, this.mSurfaceHolder);
        voLog.i(TAG, "setViewInMainThread is " + this.mView, new Object[0]);
        VOSubtitleRender vOSubtitleRender = this.mSubtitleRender;
        if (vOSubtitleRender != null) {
            vOSubtitleRender.setSurfaceView(view);
        }
        VOSubtitleRender vOSubtitleRender2 = this.mAnaRender;
        if (vOSubtitleRender2 != null) {
            vOSubtitleRender2.setSurfaceView(view);
        }
    }

    public int SetView(View view) {
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        if (view != null) {
            boolean z = view instanceof SurfaceView;
            if (view instanceof TextureView) {
                z = true;
            }
            if (!z) {
                voLog.e(TAG, "SetView parameter should be SurfaceView or TextureView or voSurfaceView or voTextureView.", new Object[0]);
                return voOSType.VOOSMP_ERR_Args;
            }
        }
        setViewInSubThread(view);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            setViewInMainThread(view);
        } else {
            EventHandler eventHandler = this.mEventHandler;
            eventHandler.sendMessage(eventHandler.obtainMessage(messageSetView, view));
        }
        return 0;
    }

    public void SetSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return;
        }
        this.mSurface = null;
        this.mView = null;
        this.mSurfaceHolder = surfaceHolder;
        if (surfaceHolder != null) {
            this.mSurface = surfaceHolder.getSurface();
        }
        Surface surface = this.mSurface;
        if (surface != null && !surface.isValid()) {
            voLog.e(TAG, "@@@Surface voOnStreamSDK::SetSurfaceHolder surface is inValid", new Object[0]);
        }
        voLog.i(TAG, "@@@SetSurfaceHolder is " + this.mSurfaceHolder, new Object[0]);
        nativeSetSurface(this.mNativeContext, this.mSurface);
    }

    public void SetSurface(Surface surface) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return;
        }
        if (surface != null && !surface.isValid()) {
            voLog.e(TAG, "@@@Surface voOnStreamSDK::SetSurface surface is inValid", new Object[0]);
            surface = null;
        }
        this.mSurface = surface;
        this.mView = null;
        this.mSurfaceHolder = null;
        voLog.i(TAG, "@@@setDisplay surface is " + this.mSurface, new Object[0]);
        nativeSetSurface(this.mNativeContext, this.mSurface);
    }

    @Override // com.visualon.OSMPUtils.voBluetoothManager.onStatusChangeListener
    public int SetParam(long j, Object obj) {
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        if (65561 == j) {
            float[] fArr = (float[]) obj;
            return this.mVideoViewCtrl.setSphericalVideoView(fArr[0], fArr[1], fArr[2]);
        }
        if (65560 == j) {
            this.mVideoViewCtrl.setSphericalEnable(((Integer) obj).intValue() == 1);
        }
        if (j == 60) {
            voLog.i(TAG, " VOOSMP_PID_READ_SUBTITLE_CALLBACK", new Object[0]);
        }
        if (105 == j) {
            int iNativeSetParam = (int) nativeSetParam(this.mNativeContext, j, obj);
            if (this.mAnaRender == null) {
                VOSubtitleRender vOSubtitleRender = new VOSubtitleRender();
                this.mAnaRender = vOSubtitleRender;
                vOSubtitleRender.setSurfaceView(this.mView);
                SetParam(69637L, this.mAnaRender);
            }
            return iNativeSetParam;
        }
        if (j == 65560) {
            this.mUnifyDecoderManager.enableSphericalVideo(((Integer) obj).intValue() == 1);
        } else if (j == 65574) {
            this.mUnifyDecoderManager.enableCardBoardVideo(((Integer) obj).intValue() == 1);
        } else if (j == 15 || j == 10498 || j == 4135 || j == 58) {
            if (j == 58) {
                voLog.i(TAG, " VOOSMP_PID_APPLICATION_SUSPEND", new Object[0]);
                VOSubtitleRender vOSubtitleRender2 = this.mSubtitleRender;
                if (vOSubtitleRender2 != null) {
                    vOSubtitleRender2.setSurfaceView(null);
                }
            }
            this.mVideoViewCtrl.SetParam(j, obj);
        } else if (j == 51 || j == 59) {
            voLog.i(TAG, " VOOSMP_PID_APPLICATION_RESUME or VOOSMP_PID_VIEW_ACTIVE", new Object[0]);
            if (obj instanceof View) {
                SetView((View) obj);
            } else if (obj instanceof Surface) {
                SetSurface((Surface) obj);
            }
            this.mVideoViewCtrl.SetParam(j, obj);
        } else {
            if (j == 69) {
                if (!(obj instanceof SurfaceView)) {
                    return voOSType.VOOSMP_ERR_Args;
                }
                SurfaceView surfaceView = (SurfaceView) obj;
                this.mView = surfaceView;
                SurfaceHolder holder = surfaceView.getHolder();
                this.mSurfaceHolder = holder;
                if (holder != null) {
                    this.mSurface = holder.getSurface();
                } else {
                    this.mSurface = null;
                }
                voLog.v(TAG, "set VOOSMP_PID_IOMX_PROBE suface is " + this.mSurfaceHolder + " ID = " + j + " , mSurface is " + this.mSurface, new Object[0]);
                int iNativeSetParam2 = (int) nativeSetParam(this.mNativeContext, j, this.mSurface);
                voLog.v(TAG, "set VOOSMP_PID_IOMX_PROBE result is %d ", Integer.valueOf(iNativeSetParam2));
                return iNativeSetParam2;
            }
            if (j == 53) {
                if (obj == null) {
                    voLog.i(TAG, "Param data is NULL!", new Object[0]);
                    return voOSType.VOOSMP_ERR_Unknown;
                }
                int[] iArr = new int[30];
                voOSVideoPerformance voosvideoperformance = (voOSVideoPerformance) obj;
                iArr[0] = voosvideoperformance.LastTime();
                iArr[6] = voosvideoperformance.SourceTimeNum();
                iArr[7] = voosvideoperformance.CodecTimeNum();
                iArr[8] = voosvideoperformance.RenderTimeNum();
                iArr[9] = voosvideoperformance.JitterNum();
                iArr[12] = voosvideoperformance.CPULoad();
                return (int) nativeSetParam(this.mNativeContext, j, iArr);
            }
            if (j == 65545) {
                if (obj == null) {
                    voLog.i(TAG, "Param data is NULL!", new Object[0]);
                    return voOSType.VOOSMP_ERR_Unknown;
                }
                voOSVideoFormat voosvideoformat = (voOSVideoFormat) obj;
                int[] iArr2 = {voosvideoformat.Width(), voosvideoformat.Height()};
                voLog.d(TAG, "intArray[0] = " + iArr2[0] + " , intArray[1] = " + iArr2[1], new Object[0]);
                return (int) nativeSetParam(this.mNativeContext, j, iArr2);
            }
            if (j == 4141 || j == 4134 || j == 4142) {
                EventHandler eventHandler = this.mEventHandler;
                eventHandler.sendMessage(eventHandler.obtainMessage(messageBluetooth, Boolean.valueOf(obj.equals(1))));
            } else {
                if (j == 3) {
                    return voOSType.VOOSMP_ERR_Implement;
                }
                if (j == 4138) {
                    return 0;
                }
                if (j == 92) {
                    voOSAudioRenderFormat voosaudiorenderformat = (voOSAudioRenderFormat) obj;
                    return (int) nativeSetParam(this.mNativeContext, j, new int[]{voosaudiorenderformat.getSampleRate(), voosaudiorenderformat.getChannels(), voosaudiorenderformat.getSampleBits(), voosaudiorenderformat.getBufferSize()});
                }
                if (j == 102) {
                    voLog.i(TAG, "VOOSMP_PID_SECOND_SURFACE", new Object[0]);
                    return (int) nativeSetParam(this.mNativeContext, j, obj);
                }
                if (j == 103) {
                    voOSLoadAudioCodec voosloadaudiocodec = (voOSLoadAudioCodec) obj;
                    int[] iArr3 = {voosloadaudiocodec.getCodec().getValue(), voosloadaudiocodec.isEnable() ? 1 : 0};
                    int iNativeSetParam3 = (int) nativeSetParam(this.mNativeContext, j, iArr3);
                    if (voosloadaudiocodec.getCodec() == voOSType.VOOSMP_AUDIO_CODINGTYPE.VOOSMP_AUDIO_CodingDOLBY) {
                        nativeSetParam(this.mNativeContext, 39L, Integer.valueOf(iArr3[1]));
                    }
                    return iNativeSetParam3;
                }
                if (j == 96) {
                    int iNativeSetParam4 = (int) nativeSetParam(this.mNativeContext, j, obj);
                    this.mOutputControlServer.startDisplayListener();
                    return iNativeSetParam4;
                }
                if (j == 4140) {
                    return this.mUnifyDecoderManager.setParam(voUnifyDecoderManager.VARSKEY.VARSKEY_ADAPTIVE_PLAYBACK, obj);
                }
                if (j == (2097152 | voOSType.VOOSMP_AUDIO_CODINGTYPE.VOOSMP_AUDIO_CodingAC3.ordinal())) {
                    if (((String) obj).equals("NULL")) {
                        this.mAudioRender.notifyReconfigAudioTrack();
                        voLog.i(TAG, "[RECONFIG] notifyReconfigAudioTrack", new Object[0]);
                    }
                } else {
                    if (j == 65571) {
                        this.mAudioRender.notifyReconfigAudioTrack();
                        voLog.i(TAG, "[RECONFIG 1] notifyReconfigAudioTrack", new Object[0]);
                        return 0;
                    }
                    if (j == 128) {
                        this.mUnifyDecoderManager.setParam(voUnifyDecoderManager.VARSKEY.VARSKEY_ENABLE_SEI_POST_PROCESS_VIDEO, Boolean.valueOf(((Integer) obj).intValue() == 1));
                    } else {
                        if (j == 22) {
                            return this.mUnifyDecoderManager.setParam(voUnifyDecoderManager.VARSKEY.VARSKEY_RENDER_TYPE, obj);
                        }
                        if (j == 2293760 || j == 2359296) {
                            return this.mUnifyDecoderManager.setCodeType((int) j, ((Integer) obj).intValue());
                        }
                        if (j == 2 || j == 97 || j == 98) {
                            this.mutex_.lock();
                            try {
                                return (int) nativeSetParam(this.mNativeContext, j, obj);
                            } finally {
                                this.mutex_.unlock();
                            }
                        }
                        if (j == 292) {
                            if (obj == null) {
                                return 0;
                            }
                            voLog.i(TAG, "VOOSMP_PID_DISABLE_FORCE_OPENGL param: " + obj, new Object[0]);
                            return this.mUnifyDecoderManager.setParam(voUnifyDecoderManager.VARSKEY.VARSKEY_DISABLE_FORCE_OPENGL, obj);
                        }
                    }
                }
            }
        }
        return (int) nativeSetParam(this.mNativeContext, j, obj);
    }

    public Object GetParam(long j, Object obj) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return null;
        }
        return nativeGetParam(this.mNativeContext, j, obj);
    }

    public Object GetParam(long j) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return null;
        }
        if (j == 10497) {
            return null;
        }
        Object objNativeGetParam = nativeGetParam(this.mNativeContext, j);
        if (j == 1) {
            Integer num = (Integer) objNativeGetParam;
            voLog.i(TAG, "VOOSMP_PID_AUDIO_VOLUME: " + num, new Object[0]);
            return num;
        }
        if (j == 33) {
            if (objNativeGetParam == null) {
                return null;
            }
            return (int[]) objNativeGetParam;
        }
        if (j == 38) {
            if (objNativeGetParam == null) {
                return null;
            }
            int[] iArr = (int[]) objNativeGetParam;
            voLog.i(TAG, "CPU info %d %d %d", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]));
            return new voOSCPUInfoImpl(iArr[0], iArr[1], iArr[2], 0L);
        }
        if (j == 21) {
            if (objNativeGetParam == null) {
                return null;
            }
            int[] iArr2 = (int[]) objNativeGetParam;
            voLog.i(TAG, "VOOSMP_PID_VIDEO_FORMAT %d %d %d", Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1]), Integer.valueOf(iArr2[2]));
            return new voOSVideoFormatImpl(iArr2[0], iArr2[1], iArr2[2]);
        }
        if (j == 20) {
            if (objNativeGetParam == null) {
                return null;
            }
            int[] iArr3 = (int[]) objNativeGetParam;
            return new voOSAudioFormatImpl(iArr3[0], iArr3[1], iArr3[2]);
        }
        if (j != 53) {
            int i = (int) j;
            if ((65535 & i) == 73) {
                if (objNativeGetParam == null) {
                    return null;
                }
                voOSModuleVersionImpl voosmoduleversionimpl = new voOSModuleVersionImpl();
                if (voosmoduleversionimpl.parse((Parcel) objNativeGetParam)) {
                    voLog.i(TAG, " get module version %s, module type is %d, version is  %s", Integer.toHexString(i), Integer.valueOf(voosmoduleversionimpl.GetModuleType()), voosmoduleversionimpl.GetVersion());
                    return voosmoduleversionimpl;
                }
                voLog.i(TAG, " get module version error,id is VOOSMP_PID_MODULE_VERSION ", new Object[0]);
            }
            return objNativeGetParam;
        }
        if (objNativeGetParam == null) {
            voLog.i(TAG, "Get Performace Data Error!!", new Object[0]);
            return null;
        }
        Parcel parcel = (Parcel) objNativeGetParam;
        parcel.setDataPosition(0);
        if (parcel.dataAvail() == 0) {
            return null;
        }
        voOSVideoPerformanceImpl voosvideoperformanceimpl = new voOSVideoPerformanceImpl();
        voosvideoperformanceimpl.parser(parcel);
        voLog.i(TAG, "Get Performance Data, LastTime %d, SourceDropNum %d, CodecDropNum %d, RenderDropNum %d, DecodedNum %d, RenderNum %d, SourceTimeNum %d, CodecTimeNum %d, RenderTimeNum %d, JitterNum %d, CodecErrorsNum %d, CPULoad %d, Frequency %d, MaxFrequency %d, WorstDecodeTime %d, WorstRenderTime %d, AverageDecodeTime %d, AverageRenderTime %d, TotalCPULoad %d, TotalPlaybackDuration %d, TotalSourceDropNum %d,TotalCodecDropNum %d, TotalRenderDropNum %d, TotalDecodedNum %d, TotalRendernum %d", Integer.valueOf(voosvideoperformanceimpl.LastTime()), Integer.valueOf(voosvideoperformanceimpl.SourceDropNum()), Integer.valueOf(voosvideoperformanceimpl.CodecDropNum()), Integer.valueOf(voosvideoperformanceimpl.RenderDropNum()), Integer.valueOf(voosvideoperformanceimpl.DecodedNum()), Integer.valueOf(voosvideoperformanceimpl.RenderNum()), Integer.valueOf(voosvideoperformanceimpl.SourceTimeNum()), Integer.valueOf(voosvideoperformanceimpl.CodecTimeNum()), Integer.valueOf(voosvideoperformanceimpl.RenderTimeNum()), Integer.valueOf(voosvideoperformanceimpl.JitterNum()), Integer.valueOf(voosvideoperformanceimpl.CodecErrorsNum()), Integer.valueOf(voosvideoperformanceimpl.CPULoad()), Integer.valueOf(voosvideoperformanceimpl.Frequency()), Integer.valueOf(voosvideoperformanceimpl.MaxFrequency()), Integer.valueOf(voosvideoperformanceimpl.WorstDecodeTime()), Integer.valueOf(voosvideoperformanceimpl.WorstRenderTime()), Integer.valueOf(voosvideoperformanceimpl.AverageDecodeTime()), Integer.valueOf(voosvideoperformanceimpl.AverageRenderTime()), Integer.valueOf(voosvideoperformanceimpl.TotalCPULoad()), Integer.valueOf(voosvideoperformanceimpl.TotalPlaybackDuration()), Integer.valueOf(voosvideoperformanceimpl.TotalSourceDropNum()), Integer.valueOf(voosvideoperformanceimpl.TotalCodecDropNum()), Integer.valueOf(voosvideoperformanceimpl.TotalRenderDropNum()), Integer.valueOf(voosvideoperformanceimpl.TotalDecodedNum()), Integer.valueOf(voosvideoperformanceimpl.TotalRenderNum()));
        for (int i2 = 0; i2 < voosvideoperformanceimpl.CodecErrorsNum(); i2++) {
            voLog.i(TAG, "CodecErrors index = %d, errorcode is %d", Integer.valueOf(i2), Integer.valueOf(voosvideoperformanceimpl.CodecErrors()[i2]));
        }
        return voosvideoperformanceimpl;
    }

    @Override // com.visualon.OSMPRender.voAudioRender.onAudioRenderListener
    public long GetAudioData(byte[] bArr) {
        if (mJNILoaded) {
            return nativeGetAudioData(this.mNativeContext, bArr);
        }
        return -2147483633L;
    }

    public int GetVideoWidth() {
        return this.mWidthVideo;
    }

    public int GetVideoHeight() {
        return this.mHeightVideo;
    }

    public int GetAudioSampleRate() {
        return this.mSampleRate;
    }

    public int GetAudioChannels() {
        return this.mChannels;
    }

    public int GetAudioBitPerSample() {
        return this.mSampleBit;
    }

    @Override // com.visualon.OSMPRender.voAudioRender.onAudioRenderListener
    public int onGetFormatID() {
        return ((Integer) GetParam(88L)).intValue();
    }

    @Override // com.visualon.OSMPRender.voAudioRender.onAudioRenderListener
    public voOSAudioFormat onGetAudioFormat() {
        voOSAudioFormat voosaudioformat = (voOSAudioFormat) GetParam(20L);
        if (voosaudioformat == null) {
            return null;
        }
        this.mSampleRate = voosaudioformat.SampleRate();
        this.mChannels = voosaudioformat.Channels();
        this.mSampleBit = voosaudioformat.SampleBits();
        return voosaudioformat;
    }

    @Override // com.visualon.OSMPRender.voAudioRender.onAudioRenderListener
    public int onAudioRenderEvent(int i, int i2, int i3, Object obj) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler == null) {
            return 0;
        }
        eventHandler.sendEmptyMessage(i);
        return 0;
    }

    @Override // com.visualon.OSMPRender.voAudioRender.onAudioRenderListener
    public long onSetAudioParam(int i, Object obj) {
        return SetParam(i, obj);
    }

    @Override // com.visualon.OSMPRender.voAudioRender.onAudioRenderListener
    public Object onGetAudioParam(long j) {
        return GetParam(j);
    }

    public Parcel GetSubTitleSample(int i) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return null;
        }
        return (Parcel) nativeGetSubTitleSample(this.mNativeContext, i);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void osmpCallBack(int i, long j, long j2, Object obj) {
        if (i == 25) {
            if (obj != null) {
                try {
                    if (j == voOSType.VOOSMP_SEI_EVENT_FLAG.VOOSMP_FLAG_SEI_EVENT_PIC_TIMING.getValue()) {
                        obj = voOSSEIPicTimingImpl.parse((int[]) obj);
                        voLog.v(TAG, "osmpCallBack, VOOSMP_CB_SEI_INFO, VOOSMP_FLAG_SEI_EVENT_PIC_TIMING ok", new Object[0]);
                    }
                    if (j == voOSType.VOOSMP_SEI_EVENT_FLAG.VOOSMP_FLAG_SEI_USER_DATA_UNREGISTERED.getValue()) {
                        voOSSEIUserDataImpl voosseiuserdataimpl = new voOSSEIUserDataImpl();
                        voosseiuserdataimpl.parse((Parcel) obj);
                        try {
                            voLog.v(TAG, "osmpCallBack, VOOSMP_CB_SEI_INFO, VOOSMP_FLAG_SEI_USER_DATA_UNREGISTERED ok", new Object[0]);
                            obj = voosseiuserdataimpl;
                        } catch (Exception unused) {
                            obj = voosseiuserdataimpl;
                            voLog.v(TAG, "osmpCallBack, VOOSMP_CB_SEI_INFO arg0 is not Parcel object", new Object[0]);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
        } else if (i == 28) {
            voLog.v(TAG, "osmpCallBack, VOOSMP_CB_PCM_OUTPUT   " + obj, new Object[0]);
            if (obj != null) {
                try {
                    Parcel parcel = (Parcel) obj;
                    parcel.setDataPosition(0);
                    voOSPCMBufferImpl voospcmbufferimpl = new voOSPCMBufferImpl();
                    voospcmbufferimpl.parse(parcel);
                    j = (int) voospcmbufferimpl.getTimestamp();
                    j2 = voospcmbufferimpl.getBufferSize();
                    obj = voospcmbufferimpl.getBuffer();
                } catch (Exception e) {
                    e.printStackTrace();
                    voLog.v(TAG, "osmpCallBack, VOOSMP_CB_PCM_OUTPUT arg0 is not Parcel object", new Object[0]);
                }
                voLog.v(TAG, "osmpCallBack, VOOSMP_CB_PCM_OUTPUT ok", new Object[0]);
            }
        } else if (i == 15) {
            int i2 = (int) j;
            this.mWidthVideo = i2;
            int i3 = (int) j2;
            this.mHeightVideo = i3;
            this.mVideoViewCtrl.setVideoSize(i2, i3);
        } else if (i == 83886090) {
            if (obj != null) {
                try {
                    voOSRectImpl voosrectimpl = voOSRectImpl.parse((Parcel) obj);
                    voLog.i(TAG, "Rect is l:%d, r:%d, t:%d, b:%d", Integer.valueOf(voosrectimpl.Left()), Integer.valueOf(voosrectimpl.Right()), Integer.valueOf(voosrectimpl.Top()), Integer.valueOf(voosrectimpl.Bottom()));
                    this.mVideoViewCtrl.setVideoDestBox(voosrectimpl, (int) j2);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    voLog.v(TAG, "osmpCallBack, VOOSMP_CB_VIDEO_DEST_BOX arg0 is not Parcel object", new Object[0]);
                    return;
                }
            }
            return;
        }
        this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(i, (int) j, (int) j2, obj));
    }

    /* JADX INFO: renamed from: com.visualon.OSMPEngine.voOnStreamSDK$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$visualon$OSMPUtils$voOSType$VOOSMP_OUTPUT_CONTROL_TYPE;

        static {
            int[] iArr = new int[voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.values().length];
            $SwitchMap$com$visualon$OSMPUtils$voOSType$VOOSMP_OUTPUT_CONTROL_TYPE = iArr;
            try {
                iArr[voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDCP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$visualon$OSMPUtils$voOSType$VOOSMP_OUTPUT_CONTROL_TYPE[voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_ANTI_MIRRORING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private int RequestCallBack(int i, long j, long j2, Object obj) {
        if (i != 39) {
            return 0;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$visualon$OSMPUtils$voOSType$VOOSMP_OUTPUT_CONTROL_TYPE[voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.valueOf((int) j).ordinal()];
        int i3 = voOSType.VOOSMP_ERR_Unknown;
        if (i2 == 1) {
            if (new voOSDisplayManager(this.mContext).isHDCPForHDMI()) {
                i3 = 0;
            }
            voLog.i(TAG, "VOOSMP_CB_Query_Output_Control_Capacity, param1 is %d, param2 is %d. return is %d.", Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i3));
        } else if (i2 == 2) {
            if (new voOSDisplayManager(this.mContext).isHDCPForMiracast()) {
                i3 = 0;
            }
            voLog.i(TAG, "VOOSMP_CB_Query_Output_Control_Capacity, param1 is %d, param2 is %d. return is %d.", Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i3));
        }
        return i3;
    }

    public void setVideoAspectRatio(int i) {
        SetParam(48L, Integer.valueOf(i));
    }

    public void updateVideoAspectRatio(int i, int i2) {
        SetParam(48L, Integer.valueOf((i << 16) | i2));
    }

    public List<voOSSubtitleLanguage> GetSubtitleLanguageInfo() {
        long j = this.mNativeContext;
        if (j == 0) {
            voLog.e(TAG, "VOOSMP_ERR_Uninitialize", new Object[0]);
            return null;
        }
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return null;
        }
        int iNativeGetSubtitleLanguageCount = nativeGetSubtitleLanguageCount(j);
        voLog.v(TAG, "Language count is %d.", Integer.valueOf(iNativeGetSubtitleLanguageCount));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iNativeGetSubtitleLanguageCount; i++) {
            arrayList.add((voOSSubtitleLanguageImpl) nativeGetSubtitleLanguageInfo(this.mNativeContext, i));
        }
        return arrayList;
    }

    public int SelectSubtitleLanguage(int i) {
        long j = this.mNativeContext;
        if (j != 0) {
            return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSelectSubtitleLanguage(j, i);
        }
        voLog.e(TAG, "VOOSMP_ERR_Uninitialize", new Object[0]);
        return voOSType.VOOSMP_ERR_Uninitialize;
    }

    public void SetDisplaySize(int i, int i2) {
        this.mVideoViewCtrl.setViewSize(i, i2);
    }

    public EventHandler GetEventHandler() {
        return this.mEventHandler;
    }

    public boolean IsHardwareRender() {
        return voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_SW.getValue() != ((Integer) GetParam(65569L)).intValue();
    }

    public Object GetSEITimingInfo(voOSType.VOOSMP_SEI_EVENT_FLAG voosmp_sei_event_flag, long j) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return null;
        }
        if (voosmp_sei_event_flag == voOSType.VOOSMP_SEI_EVENT_FLAG.VOOSMP_FLAG_SEI_EVENT_PIC_TIMING) {
            return voOSSEIPicTimingImpl.parse((int[]) nativeGetSEITimingInfo(this.mNativeContext, voosmp_sei_event_flag.getValue(), j));
        }
        return null;
    }

    public int setSmoothBAWhiteListFile(String str) {
        return this.mUnifyDecoderManager.setSmoothBAWhiteListFile(str);
    }

    public int enforceSoftwareVideoScaling(boolean z) {
        this.mVideoViewCtrl.withEnforceSWVideoScaling(z);
        return 0;
    }

    private void sendSubtitleMessage(int i, int i2, int i3, Object obj) {
        EventHandler eventHandler = this.mEventHandler;
        eventHandler.sendMessage(eventHandler.obtainMessage(i, i2, i3, obj));
    }

    private static boolean loadJNI(String str) {
        boolean z = mJNILoaded;
        if (z) {
            return z;
        }
        boolean zLoadOneLibInPath = voLoadJNI.loadOneLibInPath(str);
        mJNILoaded = zLoadOneLibInPath;
        if (zLoadOneLibInPath) {
            return zLoadOneLibInPath;
        }
        boolean zLoadProductLib = voLoadJNI.loadProductLib(str, LIB_NAME);
        mJNILoaded = zLoadProductLib;
        if (!zLoadProductLib) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
        }
        return mJNILoaded;
    }

    @Override // com.visualon.OSMPUtils.voVideoViewController.onViewChangeListener, com.visualon.OSMPOutputControl.voOSOutputControlServer.onOutputControlListener
    public int onSetParam(long j, Object obj) {
        if (!mJNILoaded) {
            voLog.e(TAG, "Fail to load JNI library", new Object[0]);
            return voOSType.VOOSMP_ERR_JNI;
        }
        if (j == 69649) {
            return 0;
        }
        return (int) nativeSetParam(this.mNativeContext, j, obj);
    }

    @Override // com.visualon.OSMPUtils.voUnifyDecoderManager.onUnifyDecoderListener
    public int onUnifyDecoderSetParam(long j, Object obj) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : (int) nativeSetParam(this.mNativeContext, j, obj);
    }

    @Override // com.visualon.OSMPUtils.voUnifyDecoderManager.onUnifyDecoderListener
    public void onUnifyDecoderEvent(int i, int i2, int i3, Object obj) {
        this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(i, i2, i3, obj));
    }

    @Override // com.visualon.OSMPOutputControl.voOSOutputControlServer.onOutputControlListener
    public void onHdmiDisconnect() {
        voAudioRender voaudiorender = this.mAudioRender;
        if (voaudiorender != null) {
            voaudiorender.hdmiDisConnect();
        }
    }

    public int setSubtitleFontColor(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontColor(this.mNativeContext, i);
    }

    public int setSubtitleFontOpacity(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontOpacity(this.mNativeContext, i);
    }

    public int setSubtitleFontSizeScale(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontSizeScale(this.mNativeContext, i);
    }

    public int setSubtitleFontBackgroundColor(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontBackgroundColor(this.mNativeContext, i);
    }

    public int setSubtitleFontBackgroundOpacity(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontBackgroundOpacity(this.mNativeContext, i);
    }

    public int setSubtitleWindowBackgroundColor(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleWindowBackgroundColor(this.mNativeContext, i);
    }

    public int setSubtitleWindowBackgroundOpacity(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleWindowBackgroundOpacity(this.mNativeContext, i);
    }

    public int setSubtitleFontItalic(boolean z) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontItalic(this.mNativeContext, z);
    }

    public int setSubtitleFontBold(boolean z) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeStSubtitleFontBold(this.mNativeContext, z);
    }

    public int setSubtitleFontUnderline(boolean z) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontUnderline(this.mNativeContext, z);
    }

    public int setSubtitleFontName(String str) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontName(this.mNativeContext, str);
    }

    public int setSubtitleTypeface(Typeface typeface) {
        this.mSubtitleRender.setTypeface(typeface);
        return 0;
    }

    public int setSubtitleFontEdgeType(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontEdgeType(this.mNativeContext, i);
    }

    public int setSubtitleFontEdgeColor(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontEdgeColor(this.mNativeContext, i);
    }

    public int setSubtitleFontEdgeOpacity(int i) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleFontEdgeOpacity(this.mNativeContext, i);
    }

    public int setSubtitleBoundingBox(int i, int i2, int i3, int i4) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleBoundingBox(this.mNativeContext, i, i2, i3, i4);
    }

    public int setSubtitleTrim(String str) {
        this.mSubtitleRender.setSubtitleTrim(str);
        return 0;
    }

    public int resetSubtitleParameter() {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeResetSubtitleParameter(this.mNativeContext);
    }

    public int previewSubtitle(String str, Object obj) {
        Log.d(TAG, "previewSubtitle: " + str);
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativePreviewSubtitle(this.mNativeContext, str, obj);
    }

    public int setSubtitleGravity(int i, int i2) {
        return !mJNILoaded ? voOSType.VOOSMP_ERR_JNI : nativeSetSubtitleGravity(this.mNativeContext, i, i2);
    }
}
