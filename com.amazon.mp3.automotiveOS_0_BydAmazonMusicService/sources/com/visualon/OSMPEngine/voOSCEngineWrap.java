package com.visualon.OSMPEngine;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.visualon.OSMPEngine.voOnStreamSDK;
import com.visualon.OSMPOutputControl.voOSHDMIStateCheck;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSOption;
import com.visualon.OSMPUtils.voOSType;
import com.visualon.OSMPUtils.voOSVideoFormatImpl;
import com.visualon.OSMPUtils.voOSVideoPerformance;
import com.visualon.OSMPUtils.voOSVideoPerformanceImpl;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes7.dex */
public class voOSCEngineWrap implements voOnStreamSDK.onEventListener, voOSHDMIStateCheck.onHDMIStateChangeListener, SurfaceHolder.Callback {
    private static final int OSAET_BLUETOOTH_CONNECTIVITY_CHANGE = 1;
    private static final int OSAET_SYS_UI_VISIBILITY_CHANGED = 0;
    private static final int OSCECT_CODEC_NOT_SUPPORTED = 2;
    private static final int OSCECT_ERROR = 1;
    private static final int OSCECT_PLAY_COMPLETED = 0;
    private static final String TAG = "@@@voCEngineWrap";
    private static final boolean mEnableLog = true;
    private Context mContext;
    private int mDisplayHeight;
    private int mDisplayMetricsHeight;
    private int mDisplayMetricsWidth;
    private int mDisplayWidth;
    private int mInitialDisplayHeight;
    private int mInitialDisplayWidth;
    private long mNativeContext;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private voOSHDMIStateCheck mHDMIChecker = null;
    private int mAspectRatio = -1;
    private int mVideoWidth = -1;
    private int mVideoHeight = -1;
    private int mOldVideoWidth = -1;
    private int mOldVideoHeight = -1;
    private boolean mDoSetDisplaySizeARUpdate = false;
    private boolean mCheckSurfaceSize = false;
    private int mLastSystemUiVis = -1;
    private int mFixedSizeWidthThreshold = -1;
    private int mFixedSizeHeightThreshold = -1;
    private int mZoomMode = 1;
    private int mOldZoomMode = 1;
    private int mZmLeft = 0;
    private int mZmTop = 0;
    private int mZmWidth = 0;
    private int mZmHeight = 0;
    private int mLpWidth = 0;
    private int mLpHeight = 0;
    private boolean mIsBlack = false;
    private boolean mIsMc = false;
    private boolean mEnableHDMIDone = false;
    private boolean mHDMISupported = false;
    private boolean mHDMIConnected = false;
    private boolean mAllowVideoRedraw = false;
    private boolean mIsPaused = false;
    private boolean mUseCustomerLibPath = false;
    private String mCustomerLibPath = "";
    private int mVerboseLevel = 1;
    private voOnStreamSDK mSDK = new voOnStreamSDK();

    private int getRenderType() {
        return 3;
    }

    private native void nativeExecEventCallback(long j, int i, int i2, int i3);

    private native void nativeHDMIConnected(long j);

    private native void nativeHDMIDisconnected(long j);

    private native void nativeSetLicenseText(long j);

    public voOSCEngineWrap(Context context, long j) {
        this.mNativeContext = j;
        this.mContext = context;
        copyfile(context, "vompPlay.cfg", "vompPlay.cfg");
    }

    public voOSCEngineWrap(Context context, SurfaceView surfaceView, long j) {
        this.mNativeContext = j;
        this.mContext = context;
        this.mSurfaceView = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
        copyfile(context, "vompPlay.cfg", "vompPlay.cfg");
    }

    public void setCustomerLibPath(String str) {
        this.mCustomerLibPath = str;
        this.mUseCustomerLibPath = true;
    }

    public void setNullCustomerLibPath() {
        this.mUseCustomerLibPath = true;
    }

    public String getCustomerLibPath() {
        return this.mCustomerLibPath;
    }

    public void drawBlack() {
        voLog.i(TAG, "drawBlack", new Object[0]);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.visualon.OSMPEngine.voOSCEngineWrap.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (voOSCEngineWrap.this.mSurfaceView != null) {
                        ViewGroup.LayoutParams layoutParams = voOSCEngineWrap.this.mSurfaceView.getLayoutParams();
                        voOSCEngineWrap.this.mLpWidth = layoutParams.width;
                        voOSCEngineWrap.this.mLpHeight = layoutParams.height;
                        layoutParams.width = 0;
                        layoutParams.height = 0;
                        voOSCEngineWrap.this.mIsBlack = true;
                        voOSCEngineWrap.this.mSurfaceView.setLayoutParams(layoutParams);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void recoverNormal(int i) {
        voLog.i(TAG, "recoverNormal0", new Object[0]);
        if (this.mIsBlack) {
            this.mIsBlack = false;
            voLog.i(TAG, "recoverNormal1", new Object[0]);
            if (this.mOldZoomMode != i) {
                return;
            }
            voLog.i(TAG, "recoverNormal2", new Object[0]);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.visualon.OSMPEngine.voOSCEngineWrap.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (voOSCEngineWrap.this.mSurfaceView != null) {
                            ViewGroup.LayoutParams layoutParams = voOSCEngineWrap.this.mSurfaceView.getLayoutParams();
                            layoutParams.width = voOSCEngineWrap.this.mLpWidth;
                            layoutParams.height = voOSCEngineWrap.this.mLpHeight;
                            voOSCEngineWrap.this.mSurfaceView.setLayoutParams(layoutParams);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public int init(int i, int i2, int i3, int i4) {
        String userNativeLibPath;
        InputStream inputStreamOpen;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        if (this.mSDK == null) {
            return voOSType.VOOSMP_ERR_Unknown;
        }
        if (1 == i4) {
            this.mIsMc = true;
        }
        if (this.mUseCustomerLibPath) {
            userNativeLibPath = getCustomerLibPath();
        } else {
            userNativeLibPath = getUserNativeLibPath();
        }
        String str = userNativeLibPath;
        logMsg(3, "apkLibPath1 %s", str);
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(voOSOption.make(voOSOption.eVoOption.eoVideoRender, getRenderType()));
        } catch (Exception unused) {
        }
        int iInit = this.mSDK.Init(this.mContext, str, arrayList, i, 0L, 0L);
        if (iInit != 0) {
            logMsg(1, "Unable to initailize media player engine %d", Integer.valueOf(iInit));
            return iInit;
        }
        if (this.mSurfaceView != null && i2 != 0 && i3 != 0) {
            setInitDisplaySize(i2, i3);
            setView(this.mSurfaceView);
        } else {
            this.mSDK.SetSurface(new Surface(new SurfaceTexture(0)));
        }
        try {
            inputStreamOpen = this.mContext.getAssets().open("voVidDec.dat");
            byteArrayOutputStream = new ByteArrayOutputStream();
            bArr = new byte[4096];
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            int i5 = inputStreamOpen.read(bArr);
            if (i5 == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i5);
            this.mSDK.setEventListener(this);
            return iInit;
        }
        setParam(43L, byteArrayOutputStream.toByteArray());
        nativeSetLicenseText(this.mNativeContext);
        this.mSDK.setEventListener(this);
        return iInit;
    }

    public void setInitDisplaySize(int i, int i2) {
        voLog.i(TAG, "Init display size %dx%d", Integer.valueOf(i), Integer.valueOf(i2));
        this.mDisplayWidth = i;
        this.mInitialDisplayWidth = i;
        this.mDisplayHeight = i2;
        this.mInitialDisplayHeight = i2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i3 = displayMetrics.widthPixels;
        this.mDisplayMetricsWidth = i3;
        int i4 = displayMetrics.heightPixels;
        this.mDisplayMetricsHeight = i4;
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null && (i > i3 || i2 > i4)) {
            int systemUiVisibility = surfaceView.getSystemUiVisibility();
            this.mLastSystemUiVis = systemUiVisibility;
            if ((systemUiVisibility & 2) == 0) {
                this.mDisplayWidth = this.mDisplayMetricsWidth;
                this.mDisplayHeight = this.mDisplayMetricsHeight;
                logMsg(1, "Initial DisplaySize %dx%d -> %dx%d", Integer.valueOf(this.mInitialDisplayWidth), Integer.valueOf(this.mInitialDisplayHeight), Integer.valueOf(this.mDisplayWidth), Integer.valueOf(this.mDisplayHeight));
            }
            this.mCheckSurfaceSize = true;
            this.mDoSetDisplaySizeARUpdate = true;
        }
        logMsg(3, "Display %dx%d", Integer.valueOf(this.mDisplayWidth), Integer.valueOf(this.mDisplayHeight));
        this.mSDK.SetDisplaySize(this.mDisplayWidth, this.mDisplayHeight);
    }

    public void reconfigAudioTrack() {
        if (this.mSDK == null) {
            return;
        }
        logMsg(3, "Reconfig audio track1", new Object[0]);
        setParam(65571L, null);
        logMsg(3, "Reconfig audio track2", new Object[0]);
    }

    public int destroy() {
        if (this.mSDK == null) {
            return voOSType.VOOSMP_ERR_Unknown;
        }
        logMsg(1, "Set event listener to be null", new Object[0]);
        this.mSDK.setEventListener(null);
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this);
        }
        stop();
        if (this.mHDMIChecker != null) {
            enableHDMIDetection(false);
        }
        int iUninit = this.mSDK.Uninit();
        this.mSDK = null;
        return iUninit;
    }

    public int open(Object obj, long j) {
        if (this.mSDK == null) {
            return voOSType.VOOSMP_ERR_Unknown;
        }
        logMsg(1, "open", new Object[0]);
        this.mAllowVideoRedraw = false;
        return this.mSDK.Open(obj, j);
    }

    public int run() {
        if (this.mSDK == null) {
            return voOSType.VOOSMP_ERR_Unknown;
        }
        logMsg(1, "run", new Object[0]);
        int iRun = this.mSDK.Run();
        this.mIsPaused = false;
        return iRun;
    }

    public int pause() {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        if (voonstreamsdk == null) {
            return voOSType.VOOSMP_ERR_Unknown;
        }
        int iPause = voonstreamsdk.Pause();
        this.mIsPaused = true;
        return iPause;
    }

    public int stop() {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        if (voonstreamsdk == null) {
            return voOSType.VOOSMP_ERR_Unknown;
        }
        int iStop = voonstreamsdk.Stop();
        if (iStop == 0) {
            this.mAspectRatio = -1;
            this.mVideoWidth = -1;
            this.mVideoHeight = -1;
            this.mOldVideoWidth = -1;
            this.mOldVideoHeight = -1;
        }
        return iStop;
    }

    public int position() {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        return voonstreamsdk == null ? voOSType.VOOSMP_ERR_Unknown : voonstreamsdk.GetPos();
    }

    public int setPosition(long j) {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        return voonstreamsdk == null ? voOSType.VOOSMP_ERR_Unknown : voonstreamsdk.SetPos(j);
    }

    public int[] getAnalytics(int[] iArr) {
        if (this.mSDK == null) {
            return null;
        }
        setParam(53L, new voOSVideoPerformanceImpl(iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5], iArr[6], iArr[7], iArr[8], iArr[9], iArr[10], new int[1], iArr[12], iArr[13], iArr[14], iArr[15], iArr[16], iArr[17], iArr[18], iArr[19], iArr[20], iArr[21], iArr[22], iArr[23], iArr[24], iArr[25]));
        voOSVideoPerformance voosvideoperformance = (voOSVideoPerformance) this.mSDK.GetParam(53L);
        if (voosvideoperformance == null) {
            logMsg(5, "getAnalytics returns null", new Object[0]);
            return null;
        }
        int[] iArr2 = new int[voosvideoperformance.CodecErrorsNum() + 26];
        iArr2[0] = voosvideoperformance.LastTime();
        iArr2[1] = voosvideoperformance.SourceDropNum();
        iArr2[2] = voosvideoperformance.CodecDropNum();
        iArr2[3] = voosvideoperformance.RenderDropNum();
        iArr2[4] = voosvideoperformance.DecodedNum();
        iArr2[5] = voosvideoperformance.RenderNum();
        iArr2[6] = voosvideoperformance.SourceTimeNum();
        iArr2[7] = voosvideoperformance.CodecTimeNum();
        iArr2[8] = voosvideoperformance.RenderTimeNum();
        iArr2[9] = voosvideoperformance.JitterNum();
        iArr2[10] = voosvideoperformance.CodecErrorsNum();
        iArr2[11] = 0;
        iArr2[12] = voosvideoperformance.CPULoad();
        iArr2[13] = voosvideoperformance.Frequency();
        iArr2[14] = voosvideoperformance.MaxFrequency();
        iArr2[15] = voosvideoperformance.WorstDecodeTime();
        iArr2[16] = voosvideoperformance.WorstRenderTime();
        iArr2[17] = voosvideoperformance.AverageDecodeTime();
        iArr2[18] = voosvideoperformance.AverageRenderTime();
        iArr2[19] = voosvideoperformance.TotalCPULoad();
        iArr2[20] = voosvideoperformance.TotalPlaybackDuration();
        iArr2[21] = voosvideoperformance.TotalSourceDropNum();
        iArr2[22] = voosvideoperformance.TotalCodecDropNum();
        iArr2[23] = voosvideoperformance.TotalRenderDropNum();
        iArr2[24] = voosvideoperformance.TotalDecodedNum();
        iArr2[25] = voosvideoperformance.TotalRenderNum();
        for (int i = 0; i < voosvideoperformance.CodecErrorsNum(); i++) {
            iArr2[i + 26] = voosvideoperformance.CodecErrors()[i];
        }
        return iArr2;
    }

    public boolean enableHDMIDetection(final boolean z) {
        this.mEnableHDMIDone = false;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.visualon.OSMPEngine.voOSCEngineWrap.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (z) {
                        voOSCEngineWrap.this.initHDMIDetection();
                    } else {
                        voOSCEngineWrap.this.releaseHDMIDetection();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i = 200; !this.mEnableHDMIDone && i > 0; i--) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException unused) {
            }
        }
        return this.mHDMISupported;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeZoomAlone(int i) {
        int i2;
        int i3;
        if (5 != i) {
            logMsg(3, "executeZoomAlone mode %d", Integer.valueOf(i));
            recoverNormal(i);
            setParam(15L, Integer.valueOf(i));
            return;
        }
        logMsg(3, "executeZoomAlone (Before calulation) (%d, %d) mode %d", Integer.valueOf(this.mZmLeft), Integer.valueOf(this.mZmTop), Integer.valueOf(i));
        int iRound = Math.round((this.mDisplayWidth * this.mVideoWidth) / this.mZmWidth);
        int iRound2 = Math.round(((-this.mZmLeft) * this.mVideoWidth) / this.mZmWidth);
        int iRound3 = Math.round((this.mDisplayHeight * this.mVideoHeight) / this.mZmHeight);
        int iRound4 = Math.round(((-this.mZmTop) * this.mVideoHeight) / this.mZmHeight);
        if (iRound == this.mVideoWidth && iRound3 == this.mVideoHeight) {
            i = 1;
            i2 = 0;
            i3 = 0;
        } else {
            i2 = iRound + iRound2;
            i3 = iRound3 + iRound4;
            if (this.mIsMc) {
                logMsg(3, "TO PANSCAN", new Object[0]);
                i = 2;
            }
        }
        logMsg(3, "executeZoomAlone (After calculation) (%d, %d, %d, %d) mode %d", Integer.valueOf(iRound2), Integer.valueOf(iRound4), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i));
        recoverNormal(i);
        setParam(15L, Integer.valueOf(i));
        if (5 == i) {
            setParam(97L, new int[]{iRound2, iRound4, i2, i3});
        }
    }

    public synchronized void zoom(double d, double d2, double d3, double d4) {
        logMsg(3, "zoom (%g, %g, %g, %g)", Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4));
        if (d + d3 <= 0.0d && d2 + d4 <= 0.0d) {
            drawBlack();
            return;
        }
        this.mOldZoomMode = this.mZoomMode;
        this.mZmLeft = (int) Math.round(d);
        this.mZmTop = (int) Math.round(d2);
        this.mZmWidth = (int) Math.round(d3);
        this.mZmHeight = (int) Math.round(d4);
        if (-1 == this.mVideoWidth) {
            return;
        }
        boolean z = 0.0d == d && ((double) this.mDisplayWidth) == d3;
        boolean z2 = 0.0d == d2 && ((double) this.mDisplayHeight) == d4;
        if (z && z2) {
            this.mZoomMode = 1;
        } else if (z && 0.0d != d2) {
            this.mZoomMode = 1;
        } else if (z2 && 0.0d != d) {
            this.mZoomMode = 1;
        } else {
            voLog.i(TAG, "Change to zoomin", new Object[0]);
            this.mZoomMode = 5;
        }
        uiZoom(this.mZoomMode);
    }

    public void setFixedSizeThreshold(int i, int i2) {
        if (i == -1 || i2 == -1) {
            return;
        }
        this.mFixedSizeWidthThreshold = i;
        this.mFixedSizeHeightThreshold = i2;
        setParam(4138L, new voOSVideoFormatImpl(i, i2, 0));
    }

    public void setDisplaySize(int i, int i2) {
        if (this.mSDK == null) {
            return;
        }
        voLog.i(TAG, "old display size %dx%d", Integer.valueOf(this.mDisplayWidth), Integer.valueOf(this.mDisplayHeight));
        voLog.i(TAG, "new display size %dx%d", Integer.valueOf(i), Integer.valueOf(i2));
        this.mDisplayWidth = i;
        this.mDisplayHeight = i2;
        this.mSDK.SetDisplaySize(i, i2);
    }

    @Override // com.visualon.OSMPEngine.voOnStreamSDK.onEventListener
    public int onEvent(int i, int i2, int i3, Object obj) {
        if (i == 15) {
            synchronized (this) {
                int i4 = this.mVideoWidth;
                this.mOldVideoWidth = i4;
                this.mOldVideoHeight = this.mVideoHeight;
                this.mVideoWidth = i2;
                this.mVideoHeight = i3;
                logMsg(3, "VideoSizeChange %dx%d -> %dx%d", Integer.valueOf(i4), Integer.valueOf(this.mOldVideoHeight), Integer.valueOf(this.mVideoWidth), Integer.valueOf(this.mVideoHeight));
                if (this.mOldVideoWidth == -1) {
                    this.mOldVideoWidth = this.mVideoWidth;
                    this.mOldVideoHeight = this.mVideoHeight;
                }
                int i5 = this.mZoomMode;
                if (i5 == 5) {
                    logMsg(3, "ZoomIn original (%d, %d, %d, %d)", Integer.valueOf(this.mZmLeft), Integer.valueOf(this.mZmTop), Integer.valueOf(this.mZmWidth), Integer.valueOf(this.mZmHeight));
                    executeZoomAlone(5);
                } else if (i5 == 2) {
                    executeZoomAlone(2);
                } else if (i5 == 4) {
                    executeZoomAlone(4);
                } else if (i5 == 1) {
                    executeZoomAlone(1);
                }
                this.mAllowVideoRedraw = true;
            }
        } else if (i == 14) {
            this.mAspectRatio = i2;
        } else if (i == 1) {
            nativeExecEventCallback(this.mNativeContext, 0, i2, i3);
        } else if (i == -2147483636) {
            nativeExecEventCallback(this.mNativeContext, 1, i2, i3);
        } else if (i == -2147483632) {
            nativeExecEventCallback(this.mNativeContext, 2, i2, i3);
        }
        return 0;
    }

    @Override // com.visualon.OSMPOutputControl.voOSHDMIStateCheck.onHDMIStateChangeListener
    public void onHDMIStateChangeEvent(int i, Object obj) {
        while (!this.mEnableHDMIDone) {
        }
        voOSHDMIStateCheck vooshdmistatecheck = this.mHDMIChecker;
        if (vooshdmistatecheck != null && vooshdmistatecheck.isSupported()) {
            if (i == 0) {
                nativeHDMIDisconnected(this.mNativeContext);
                return;
            }
            if (i == 1) {
                nativeHDMIConnected(this.mNativeContext);
            } else {
                if (i != 2048) {
                    return;
                }
                if (((Integer) obj).intValue() == 1) {
                    nativeHDMIConnected(this.mNativeContext);
                } else {
                    nativeHDMIDisconnected(this.mNativeContext);
                }
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        logMsg(1, "surfaceCreated()", new Object[0]);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        int i4;
        logMsg(1, "surfaceChanged()", new Object[0]);
        setParam(4135L, 1);
        ViewGroup.LayoutParams layoutParams = this.mSurfaceView.getLayoutParams();
        logMsg(1, "surfaceView layout size %dx%d", Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height));
        if (this.mCheckSurfaceSize && (i4 = layoutParams.width) > 0 && this.mInitialDisplayWidth == this.mDisplayWidth && this.mInitialDisplayHeight == this.mDisplayHeight && this.mZoomMode == 1) {
            if (i4 < this.mDisplayMetricsWidth && layoutParams.height < this.mDisplayMetricsHeight) {
                logMsg(3, "Disable AR update", new Object[0]);
                this.mDoSetDisplaySizeARUpdate = false;
            }
            this.mCheckSurfaceSize = false;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        logMsg(1, "surfaceDestroyed()", new Object[0]);
        setView(null);
    }

    public void onApplicationEvent(int i, int i2, int i3) {
        int i4;
        if (i == 0) {
            logMsg(3, "SYS_UI_VISIBILITY_CHANGED", new Object[0]);
            SurfaceView surfaceView = this.mSurfaceView;
            if (surfaceView == null || (i4 = this.mLastSystemUiVis) == -1) {
                logMsg(5, "mLastSystemUiVis : %d", Integer.valueOf(this.mLastSystemUiVis));
                return;
            }
            int i5 = i4 ^ i2;
            logMsg(5, "mLastSystemUiVis: %d visibility: %d diff: %d getSystemUiVisibility: %d", Integer.valueOf(this.mLastSystemUiVis), Integer.valueOf(i2), Integer.valueOf(i5), Integer.valueOf(surfaceView.getSystemUiVisibility()));
            this.mLastSystemUiVis = i2;
            if ((i5 & 2) != 0) {
                int i6 = this.mInitialDisplayWidth;
                int i7 = this.mInitialDisplayHeight;
                if ((i2 & 2) == 0) {
                    logMsg(3, "UNHIDE NAVIGATION", new Object[0]);
                    i6 = this.mDisplayMetricsWidth;
                    i7 = this.mDisplayMetricsHeight;
                } else {
                    logMsg(3, "HIDE NAVIGATION", new Object[0]);
                }
                setDisplaySize(i6, i7);
            }
        }
    }

    public void setView(SurfaceView surfaceView) {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        if (voonstreamsdk == null) {
            return;
        }
        if (surfaceView == null) {
            voonstreamsdk.SetSurface(new Surface(new SurfaceTexture(0)));
            SurfaceHolder surfaceHolder = this.mSurfaceHolder;
            if (surfaceHolder != null) {
                surfaceHolder.removeCallback(this);
                this.mSurfaceHolder = null;
            }
            this.mSurfaceView = null;
            return;
        }
        this.mSurfaceView = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
        this.mSDK.SetView(this.mSurfaceView);
    }

    private int setParam(long j, Object obj) {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        return voonstreamsdk == null ? voOSType.VOOSMP_ERR_Unknown : voonstreamsdk.SetParam(j, obj);
    }

    private Object getParam(long j) {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        if (voonstreamsdk == null) {
            return null;
        }
        return voonstreamsdk.GetParam(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHDMIDetection() {
        voOSHDMIStateCheck vooshdmistatecheck = this.mHDMIChecker;
        if (vooshdmistatecheck == null) {
            this.mHDMIChecker = new voOSHDMIStateCheck(this.mContext);
        } else {
            vooshdmistatecheck.restart(this.mContext);
        }
        this.mHDMIChecker.setOnHDMIStateChangeListener(this);
        this.mHDMISupported = this.mHDMIChecker.isSupported();
        this.mEnableHDMIDone = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseHDMIDetection() {
        voOSHDMIStateCheck vooshdmistatecheck = this.mHDMIChecker;
        if (vooshdmistatecheck != null) {
            vooshdmistatecheck.Release();
            this.mHDMIChecker = null;
        }
        this.mHDMISupported = false;
        this.mEnableHDMIDone = true;
    }

    private void uiZoom(final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.visualon.OSMPEngine.voOSCEngineWrap.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    voOSCEngineWrap.this.executeZoomAlone(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void oneStepZoom(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mDisplayWidth, this.mDisplayHeight);
        layoutParams.addRule(13);
        setParam(15L, 5);
        setParam(2L, new int[]{i, i2, i3, i4});
        executeSetFixedSize(i3 - i, i4 - i2);
        if (this.mIsPaused && this.mAllowVideoRedraw) {
            logMsg(5, "oneStepZoom VOOSMP_PID_VIDEO_REDRAW", new Object[0]);
            setParam(7L, 1);
        }
        if (this.mLastSystemUiVis >= 0) {
            int i7 = this.mDisplayWidth;
            int i8 = this.mInitialDisplayWidth;
            if (i7 == i8 || (i5 = this.mDisplayHeight) == (i6 = this.mInitialDisplayHeight)) {
                layoutParams.width = i7;
                layoutParams.height = Math.round((this.mDisplayHeight * i7) / i8);
            } else if (i7 * i6 > i8 * i5) {
                layoutParams.height = i5;
                layoutParams.width = Math.round((i7 * i5) / i6);
            }
        }
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null) {
            surfaceView.setLayoutParams(layoutParams);
        }
    }

    private int roundRectangle(int i) {
        return i % 4 >= 2 ? (i & (-4)) + 4 : i & (-4);
    }

    private void executeSetFixedSize(int i, int i2) {
        SurfaceHolder surfaceHolder;
        int i3 = this.mFixedSizeWidthThreshold;
        if ((i3 == -1 || (i > i3 && i2 > this.mFixedSizeHeightThreshold)) && (surfaceHolder = this.mSurfaceHolder) != null) {
            surfaceHolder.setFixedSize(i, i2);
        }
    }

    private void updateAspectRatio() {
        if (this.mSDK == null) {
            return;
        }
        int i = this.mAspectRatio;
        if (i == -1) {
            logMsg(3, "updateAspectRatio: %dx%d", Integer.valueOf(this.mVideoWidth), Integer.valueOf(this.mVideoHeight));
            this.mSDK.updateVideoAspectRatio(this.mVideoWidth, this.mVideoHeight);
            return;
        }
        logMsg(3, "updateAspectRatio: %d", Integer.valueOf(i));
        int i2 = this.mAspectRatio;
        if (i2 == 1) {
            this.mSDK.updateVideoAspectRatio(1, 1);
            return;
        }
        if (i2 == 2) {
            this.mSDK.updateVideoAspectRatio(4, 3);
        } else if (i2 == 3) {
            this.mSDK.updateVideoAspectRatio(16, 9);
        } else {
            if (i2 != 4) {
                return;
            }
            this.mSDK.updateVideoAspectRatio(2, 1);
        }
    }

    private void setDisplaySizeUpdateZoom(int i, int i2) {
        int i3;
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
        int i4 = this.mInitialDisplayWidth;
        if (i == i4 && i2 == (i3 = this.mInitialDisplayHeight)) {
            layoutParams.width = i4;
            layoutParams.height = i3;
        } else {
            if (i * this.mInitialDisplayHeight > i2 * i4) {
                layoutParams.height = i2;
                layoutParams.width = Math.round((i * i2) / r2);
            } else {
                layoutParams.width = i;
                layoutParams.height = Math.round((i2 * i) / i4);
            }
        }
        this.mSurfaceView.setLayoutParams(layoutParams);
    }

    public String getUserNativeLibPath() {
        String packageName = this.mContext.getPackageName();
        String str = "/data/data/" + packageName + "/lib/";
        try {
            str = this.mContext.getPackageManager().getPackageInfo(packageName, 0).applicationInfo.dataDir + "/lib";
        } catch (PackageManager.NameNotFoundException unused) {
            voLog.e(TAG, "Cannot find the default directory assigned to the package.", new Object[0]);
        }
        File file = new File(str);
        if (!file.exists() || file.listFiles() == null) {
            try {
                str = this.mContext.getPackageManager().getPackageInfo(packageName, 0).applicationInfo.nativeLibraryDir;
            } catch (PackageManager.NameNotFoundException unused2) {
                voLog.e(TAG, "Cannot find the native JNI libraries.", new Object[0]);
            }
        }
        if (!isExtractNativeLibs(this.mContext)) {
            String splitUserPath = getSplitUserPath(this.mContext);
            if (splitUserPath != null) {
                str = splitUserPath;
            } else {
                ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
                if (applicationInfo != null) {
                    str = applicationInfo.sourceDir;
                }
            }
            str = str + "!/lib/" + getCPU_ABI();
        }
        String str2 = str + "/";
        voLog.i(TAG, "getUserNativeLibPath: " + str2, new Object[0]);
        return str2;
    }

    public static boolean isExtractNativeLibs(Context context) {
        if (context != null) {
            return (context.getApplicationInfo().flags & 268435456) != 0;
        }
        voLog.e(TAG, "context is null", new Object[0]);
        return true;
    }

    private static String getSplitUserPath(Context context) {
        if (context == null) {
            voLog.e(TAG, "context is null", new Object[0]);
            return null;
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo.splitSourceDirs == null) {
            voLog.i(TAG, "info.splitSourceDirs is null", new Object[0]);
            return null;
        }
        String strReplace = getCPU_ABI().replace("-", Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
        for (String str : applicationInfo.splitSourceDirs) {
            if (str.contains(strReplace)) {
                return str;
            }
        }
        return null;
    }

    private static String getCPU_ABI() {
        return Build.SUPPORTED_ABIS[0];
    }

    private String getApkPath(boolean z) {
        String str;
        PackageManager packageManager = this.mContext.getPackageManager();
        String packageName = this.mContext.getPackageName();
        try {
            str = packageManager.getPackageInfo(packageName, 0).applicationInfo.dataDir;
            logMsg(7, "ApkPath is %s", str);
        } catch (PackageManager.NameNotFoundException unused) {
            str = "/data/data/" + packageName;
            logMsg(7, "Use default ApkPath %s", str);
        }
        if (!z) {
            return str;
        }
        return str + "/";
    }

    private String getApkLibPath(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(getApkPath(false));
        sb.append("/lib");
        sb.append(z ? "/" : "");
        return sb.toString();
    }

    public static String GetApkLibPath(Context context) {
        String str;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            str = packageManager.getPackageInfo(packageName, 0).applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "/data/data/" + packageName;
        }
        return str + "/lib/";
    }

    private void setupIOMX() {
        logMsg(7, "setupIOMX", new Object[0]);
        String str = Build.VERSION.RELEASE;
        setParam(2359296L, voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_IOMX);
        logMsg(7, "Use hardware decoding/rendering", new Object[0]);
    }

    private boolean setRenderBufferSize(int i) {
        if (i <= 0) {
            return false;
        }
        try {
            String userPath = getUserPath(this.mContext);
            String str = userPath + "/OSMP.cfg";
            FileWriter fileWriter = new FileWriter(new File(str));
            String str2 = userPath + "/vompPlay.cfg";
            if (new File(str2).exists()) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(str2));
                    boolean z = false;
                    boolean z2 = false;
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (z) {
                            fileWriter.write(line + "\n");
                        } else if (z2) {
                            if (line.indexOf("VideoRenderBuffers") != -1) {
                                fileWriter.write("VideoRenderBuffers=" + i);
                                fileWriter.write("\n");
                                z = true;
                            } else {
                                if (line.indexOf(91) != -1) {
                                    fileWriter.write("VideoRenderBuffers=" + i);
                                    fileWriter.write("\n");
                                    z2 = false;
                                    z = true;
                                }
                                fileWriter.write(line + "\n");
                            }
                        } else if (line.indexOf("[Playback]") != -1) {
                            fileWriter.write(line + "\n");
                            z2 = true;
                        } else {
                            fileWriter.write(line + "\n");
                        }
                    }
                } catch (Exception unused) {
                    return false;
                }
            } else {
                fileWriter.write("[Playback]\n");
                fileWriter.write("VideoRenderBuffers=" + i + "\n");
            }
            fileWriter.write("\n");
            fileWriter.close();
            logMsg(7, "Writing cfg file " + str, new Object[0]);
            logMsg(1, "setRenderBufferSize: " + i, new Object[0]);
            setParam(34L, str);
            new File(str).delete();
            return true;
        } catch (Exception unused2) {
            logMsg(7, "Failed to write cfg file", new Object[0]);
            return false;
        }
    }

    private void setVerboseLevel(int i) {
        this.mVerboseLevel = i;
    }

    private boolean isRunning() {
        voOnStreamSDK voonstreamsdk = this.mSDK;
        return voonstreamsdk != null && ((Integer) voonstreamsdk.GetParam(14L)).intValue() == 2;
    }

    private void logMsg(int i, String str, Object... objArr) {
        int i2 = (int) ((((double) i) / 2.3d) + 1.0d);
        voLog.i(TAG, "logMsg_limit: %d", Integer.valueOf(i));
        voLog.i(TAG, "logMsg_level: %d", Integer.valueOf(i2));
        if (1 == i2) {
            voLog.d(TAG, String.format(str, objArr), new Object[0]);
        } else if (2 == i2) {
            voLog.i(TAG, String.format(str, objArr), new Object[0]);
        } else {
            voLog.v(TAG, String.format(str, objArr), new Object[0]);
        }
    }

    private boolean copyfile(Context context, String str, String str2) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            File file = new File(getUserPath(context) + "/" + str2);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (IOException unused) {
            logMsg(5, "Failed to copy %s", str);
            return false;
        }
    }

    private static String getUserPath(Context context) {
        String packageName = context.getPackageName();
        String str = "/data/data/" + packageName;
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException unused) {
            return str;
        }
    }
}
