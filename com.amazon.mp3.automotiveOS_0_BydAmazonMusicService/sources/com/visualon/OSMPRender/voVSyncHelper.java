package com.visualon.OSMPRender;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.WindowManager;
import com.visualon.OSMPUtils.voLog;

/* JADX INFO: loaded from: classes7.dex */
@TargetApi(16)
public class voVSyncHelper implements Choreographer.FrameCallback, Handler.Callback {
    private static final int ADD = 1;
    private static final long AFTM_VSYNC_OFFSET_PERCENT = 80;
    private static final int CREATE = 0;
    private static final int DEFAULT = 0;
    private static final int FORCE_ALAWAYS = 3;
    private static final int FORCE_NO_VSYNC = 4;
    private static final int FORCE_PARTIAL = 1;
    private static final int IS_OK = 0;
    private static final int IS_OVER_UNSYNC = 1;
    private static int MIN_FRAME_THRES = 0;
    private static final int PARTIAL_REVERSABLE = 2;
    private static final int REMOVE = 2;
    private static final long SAMPLE_DELAY_MS = 500;
    private static final int SCHEME_NOT_SET = -1;
    private static final String TAG = "@@@voVSyncHelper.java";
    private static long THRESHOLD_30FPS_NS = 0;
    private static long THRESHOLD_60FPS_NS = 0;
    private static long THRESHOLD_UNSYNC_NS = 0;
    private static final long VSYNC_OFFSET_PERCENT = 95;
    private static voVSyncHelper instance;
    private static boolean isAFTM;
    private static int ref_cnt;
    private static long vsyncDeltaNs;
    private static long vsyncOffsetNs;
    private long adjLastFrameTimeNs;
    private boolean bSynced;
    private Choreographer choreographer;
    private long frameCnt;
    private final Handler handler;
    private int initFrameCnt;
    private long lastFramePtsUs;
    private int observerCount;
    private long pendingAdjFrameTimeNs;
    private long previousCallSystemTimeUs;
    private long syncFramePtsNs;
    private long syncUnadjReleaseTimeNs;
    public volatile long vsyncDoFrameTimeNs;
    private final HandlerThread worker;
    private int overUnsyncCnt = 0;
    private int vsync_scheme = 0;
    private int set_scheme = -1;
    private long nativeContext = 0;

    private native void nativeEnable(long j);

    private native void nativeInit();

    private native void nativeUninit(long j);

    static {
        isAFTM = new String(Build.MANUFACTURER).equals("Amazon") && new String(Build.MODEL).equals("AFTM");
        vsyncDeltaNs = -1L;
        vsyncOffsetNs = -1L;
        MIN_FRAME_THRES = 6;
        THRESHOLD_UNSYNC_NS = 20000000L;
        THRESHOLD_60FPS_NS = 21000000L;
        THRESHOLD_30FPS_NS = 29500000L;
        ref_cnt = 0;
    }

    public static voVSyncHelper newVSyncHelper(Context context) {
        if (instance == null) {
            long displayRefreshRate = (long) (1.0E9d / ((double) getDisplayRefreshRate(context)));
            vsyncDeltaNs = displayRefreshRate;
            if (isAFTM) {
                vsyncOffsetNs = (displayRefreshRate * AFTM_VSYNC_OFFSET_PERCENT) / 100;
            } else {
                vsyncOffsetNs = (displayRefreshRate * VSYNC_OFFSET_PERCENT) / 100;
            }
            instance = new voVSyncHelper();
        }
        ref_cnt++;
        return instance;
    }

    private static float getDisplayRefreshRate(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }

    private voVSyncHelper() {
        HandlerThread handlerThread = new HandlerThread("worker:Handler");
        this.worker = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), this);
        this.handler = handler;
        handler.sendEmptyMessage(0);
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        this.vsyncDoFrameTimeNs = j;
        this.choreographer.postFrameCallbackDelayed(this, SAMPLE_DELAY_MS);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            createInstanceInternal();
            return true;
        }
        if (i == 1) {
            addInternal();
            return true;
        }
        if (i != 2) {
            return false;
        }
        removeInternal();
        return true;
    }

    public void enable() {
        this.bSynced = false;
        this.handler.sendEmptyMessage(1);
        nativeEnable(this.nativeContext);
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "enable", new Object[0]);
        }
        this.initFrameCnt = 0;
        this.overUnsyncCnt = 0;
        int i = this.set_scheme;
        if (i != -1) {
            this.vsync_scheme = i;
        }
    }

    public void disable() {
        this.handler.sendEmptyMessage(2);
        if (voLog.enablePrintLog()) {
            voLog.i("[VSYNC_JAVA]", "disable", new Object[0]);
        }
    }

    private void createInstanceInternal() {
        this.choreographer = Choreographer.getInstance();
    }

    private void addInternal() {
        int i = this.observerCount + 1;
        this.observerCount = i;
        if (i == 1) {
            this.choreographer.postFrameCallback(this);
        }
    }

    private void removeInternal() {
        int i = this.observerCount - 1;
        this.observerCount = i;
        if (i == 0) {
            this.choreographer.removeFrameCallback(this);
            this.vsyncDoFrameTimeNs = 0L;
        } else if (i < 0) {
            this.observerCount = 0;
        }
    }

    public long adjustReleaseTime(long j, long j2) {
        long j3;
        long j4;
        if (this.vsync_scheme == 0) {
            return j2;
        }
        long j5 = 1000 * j;
        this.initFrameCnt++;
        if (this.bSynced) {
            if (j != this.lastFramePtsUs) {
                this.frameCnt++;
                this.adjLastFrameTimeNs = this.pendingAdjFrameTimeNs;
            }
            long j6 = this.frameCnt;
            if (j6 >= MIN_FRAME_THRES) {
                long j7 = (j5 - this.syncFramePtsNs) / j6;
                long j8 = this.adjLastFrameTimeNs + j7;
                if (isOverThreshold(j8, j2) != 0) {
                    this.bSynced = false;
                    j3 = j2;
                    j4 = j5;
                } else {
                    j3 = (this.syncUnadjReleaseTimeNs + j8) - this.syncFramePtsNs;
                    j4 = j8;
                }
                if (j7 <= THRESHOLD_60FPS_NS) {
                    this.vsync_scheme = 3;
                } else if (this.vsync_scheme == 3 && j7 >= THRESHOLD_30FPS_NS) {
                    this.vsync_scheme = this.set_scheme;
                }
            } else {
                if (isOverThreshold(j5, j2) != 0) {
                    this.bSynced = false;
                }
                j3 = j2;
                j4 = j5;
            }
        } else {
            j3 = j2;
            j4 = j5;
        }
        if (!this.bSynced) {
            this.syncFramePtsNs = j5;
            this.syncUnadjReleaseTimeNs = j2;
            this.frameCnt = 0L;
            this.bSynced = true;
        }
        this.lastFramePtsUs = j;
        this.pendingAdjFrameTimeNs = j4;
        if (this.vsyncDoFrameTimeNs == 0) {
            return j3;
        }
        int i = this.vsync_scheme;
        boolean z = true;
        if (i == 1 ? this.initFrameCnt % 66 == 0 : i != 4) {
            z = false;
        }
        if (this.initFrameCnt % 300 == 0) {
            if (i == 3) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "always vsync.", new Object[0]);
                }
            } else if (i == 4) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "no vsync.", new Object[0]);
                }
            } else if (voLog.enablePrintLog()) {
                voLog.i(TAG, "partial vsync.", new Object[0]);
            }
        }
        if (z) {
            return -1L;
        }
        return findClosestVsyncPoint(j3, this.vsyncDoFrameTimeNs, vsyncDeltaNs) - vsyncOffsetNs;
    }

    private long findClosestVsyncPoint(long j, long j2, long j3) {
        long j4 = j2 + (((j - j2) / j3) * j3);
        return j <= j4 ? j4 : j4 + j3;
    }

    private int isOverThreshold(long j, long j2) {
        if (Math.abs((j - this.syncFramePtsNs) - (j2 - this.syncUnadjReleaseTimeNs)) <= THRESHOLD_UNSYNC_NS) {
            return 0;
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "IS_OVER_UNSYNC initFrameCnt:" + this.initFrameCnt, new Object[0]);
        }
        this.overUnsyncCnt++;
        return 1;
    }

    public long getContext() {
        return this.nativeContext;
    }

    public boolean init() {
        if (this.nativeContext != 0) {
            return true;
        }
        nativeInit();
        return true;
    }

    public void uninit() {
        int i = ref_cnt - 1;
        ref_cnt = i;
        if (i == 0) {
            long j = this.nativeContext;
            if (j != 0) {
                nativeUninit(j);
                this.nativeContext = 0L;
            }
        }
    }

    public void setVSyncScheme(int i) {
        if (i == 0) {
            this.set_scheme = 4;
        } else if (i == 2) {
            this.set_scheme = 1;
        } else {
            if (i != 3) {
                return;
            }
            this.set_scheme = 3;
        }
    }
}
