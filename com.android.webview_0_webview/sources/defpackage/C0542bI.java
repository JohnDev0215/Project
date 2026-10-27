package defpackage;

import J.N;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.base.TraceEvent;
import org.chromium.media.VideoCapture;
import org.chromium.media.VideoCaptureFormat;

/* JADX INFO: renamed from: bI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0542bI extends VideoCapture {
    public static final String[] G = {"Pixel 3", "Pixel 3 XL"};
    public static final SparseIntArray H;
    public int A;
    public int B;
    public boolean C;
    public int D;
    public boolean E;
    public boolean F;
    public final Object f;
    public CameraDevice g;
    public CameraCaptureSession h;
    public CaptureRequest i;
    public CaptureRequest.Builder j;
    public ImageReader k;
    public Handler l;
    public ConditionVariable m;
    public Range n;
    public int o;
    public float p;
    public Rect q;
    public int r;
    public int s;
    public int t;
    public float u;
    public int v;
    public long w;
    public MeteringRectangle x;
    public int y;
    public int z;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        H = sparseIntArray;
        sparseIntArray.append(2850, 2);
        H.append(2950, 4);
        H.append(4250, 3);
        H.append(4600, 7);
        H.append(5000, 5);
        H.append(6000, 6);
        H.append(7000, 8);
    }

    public C0542bI(int i, long j) {
        super(i, j);
        this.f = new Object();
        this.m = new ConditionVariable();
        this.o = 3;
        this.p = 1.0f;
        this.q = new Rect();
        this.t = 4;
        this.u = 1.0f;
        this.v = 4;
        this.z = 4;
        this.A = -1;
        this.D = 1;
        N.Mqw5545M(this.e, this);
        HandlerThread handlerThread = new HandlerThread("VideoCaptureCamera2_CameraThread");
        handlerThread.start();
        this.l = new Handler(handlerThread.getLooper());
        CameraCharacteristics cameraCharacteristicsI = i(i);
        if (cameraCharacteristicsI != null) {
            this.p = ((Float) cameraCharacteristicsI.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void e(defpackage.C0542bI r10, int r11) {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0542bI.e(bI, int):void");
    }

    public static Size h(Size[] sizeArr, int i, int i2) {
        if (sizeArr == null) {
            return null;
        }
        Size size = null;
        int i3 = Integer.MAX_VALUE;
        for (Size size2 : sizeArr) {
            int iAbs = (i > 0 ? Math.abs(size2.getWidth() - i) : 0) + (i2 > 0 ? Math.abs(size2.getHeight() - i2) : 0);
            if (iAbs < i3) {
                size = size2;
                i3 = iAbs;
            }
        }
        if (i3 != Integer.MAX_VALUE) {
            return size;
        }
        Ts.a("VideoCapture", "Couldn't find resolution close to (%dx%d)", Integer.valueOf(i), Integer.valueOf(i2));
        return null;
    }

    public static CameraCharacteristics i(int i) {
        CameraManager cameraManager = (CameraManager) Is.a.getSystemService("camera");
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (i < cameraIdList.length) {
                return cameraManager.getCameraCharacteristics(cameraIdList[i]);
            }
            Ts.a("VideoCapture", "Invalid camera Id: ", Integer.valueOf(i));
            return null;
        } catch (CameraAccessException | AssertionError | IllegalArgumentException e) {
            Ts.a("VideoCapture", "getCameraCharacteristics: ", e);
            return null;
        }
    }

    public static int j() {
        try {
            CameraManager cameraManager = (CameraManager) Is.a.getSystemService("camera");
            if (cameraManager == null) {
                return 0;
            }
            try {
                return cameraManager.getCameraIdList().length;
            } catch (CameraAccessException | AssertionError | SecurityException e) {
                Ts.a("VideoCapture", "getNumberOfCameras: getCameraIdList(): ", e);
                return 0;
            }
        } catch (IllegalArgumentException e2) {
            Ts.a("VideoCapture", "getSystemService(Context.CAMERA_SERVICE): ", e2);
            return 0;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean allocate(int i, int i2, int i3, boolean z) {
        N.Mqw5545M(this.e, this);
        synchronized (this.f) {
            if (this.o != 0 && this.o != 1) {
                CameraCharacteristics cameraCharacteristicsI = i(this.d);
                StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristicsI.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                int iIntValue = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                this.a = iIntValue;
                if (iIntValue == 0 || iIntValue == 180) {
                    i2 = i;
                    i = i2;
                }
                Size sizeH = h(streamConfigurationMap.getOutputSizes(35), i, i2);
                if (sizeH == null) {
                    Ts.a("VideoCapture", "No supported resolutions.", new Object[0]);
                    return false;
                }
                List<Range> listAsList = Arrays.asList((Range[]) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
                if (listAsList.isEmpty()) {
                    Ts.a("VideoCapture", "No supported framerate ranges.", new Object[0]);
                    return false;
                }
                ArrayList arrayList = new ArrayList(listAsList.size());
                int i4 = ((Integer) ((Range) listAsList.get(0)).getUpper()).intValue() > 1000 ? 1 : 1000;
                for (Range range : listAsList) {
                    arrayList.add(new KH(((Integer) range.getLower()).intValue() * i4, ((Integer) range.getUpper()).intValue() * i4));
                }
                KH kh = (KH) Collections.min(arrayList, new JH(i3 * 1000));
                this.n = new Range(Integer.valueOf(kh.a / i4), Integer.valueOf(kh.b / i4));
                sizeH.getWidth();
                sizeH.getHeight();
                this.n.getLower();
                this.n.getUpper();
                this.c = new VideoCaptureFormat(sizeH.getWidth(), sizeH.getHeight(), i3, 35);
                this.b = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.LENS_FACING)).intValue() == 1;
                this.F = z;
                return true;
            }
            Ts.a("VideoCapture", "allocate() invoked while Camera is busy opening/configuring.", new Object[0]);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void deallocate() {
    }

    public final void f(int i) {
        synchronized (this.f) {
            this.o = i;
            this.f.notifyAll();
        }
    }

    public void finalize() {
        this.l.getLooper().quit();
    }

    public final void g(CaptureRequest.Builder builder) {
        int iAbs;
        boolean z;
        Boolean bool = Boolean.FALSE;
        TraceEvent traceEventM = TraceEvent.M("VideoCaptureCamera2.configureCommonCaptureSettings");
        try {
            CameraCharacteristics cameraCharacteristicsI = i(this.d);
            if (this.t == 4) {
                builder.set(CaptureRequest.CONTROL_AF_MODE, 4);
                builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            } else if (this.t == 2) {
                builder.set(CaptureRequest.CONTROL_AF_MODE, 0);
                builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                builder.set(CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(1.0f / this.u));
            }
            if (this.v == 1 || this.v == 2) {
                builder.set(CaptureRequest.CONTROL_AE_MODE, 0);
                if (this.w != 0) {
                    builder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, Long.valueOf(this.w / 100000));
                } else {
                    Range range = (Range) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
                    builder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, Long.valueOf((((Long) range.getLower()).longValue() + ((((Long) range.getUpper()).longValue() + ((Long) range.getLower()).longValue()) / 2)) / 100000));
                }
            } else {
                builder.set(CaptureRequest.CONTROL_MODE, 1);
                builder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                String[] strArr = G;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    } else {
                        if (strArr[i].contentEquals(Build.MODEL)) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                }
                if (!z) {
                    builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.n);
                }
            }
            if (this.E) {
                builder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(this.v == 4 ? 1 : 0));
                builder.set(CaptureRequest.FLASH_MODE, 2);
            } else {
                int i2 = this.D;
                if (i2 == 1) {
                    builder.set(CaptureRequest.FLASH_MODE, 0);
                } else if (i2 == 2) {
                    builder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(this.C ? 4 : 2));
                } else if (i2 == 3) {
                    builder.set(CaptureRequest.CONTROL_AE_MODE, 3);
                    builder.set(CaptureRequest.FLASH_MODE, 1);
                }
                builder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
            }
            builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(this.y));
            if (this.z == 4) {
                builder.set(CaptureRequest.CONTROL_AWB_LOCK, bool);
                builder.set(CaptureRequest.CONTROL_AWB_MODE, 1);
            } else if (this.z == 1) {
                builder.set(CaptureRequest.CONTROL_AWB_LOCK, bool);
                builder.set(CaptureRequest.CONTROL_AWB_MODE, 0);
            } else if (this.z == 2) {
                builder.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.TRUE);
            }
            if (this.A > 0) {
                int i3 = this.A;
                int[] iArr = (int[]) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
                int i4 = Integer.MAX_VALUE;
                int iValueAt = -1;
                for (int i5 = 0; i5 < H.size(); i5++) {
                    int iValueAt2 = H.valueAt(i5);
                    int i6 = 0;
                    while (true) {
                        if (i6 >= iArr.length) {
                            i6 = -1;
                            break;
                        } else if (iValueAt2 == iArr[i6]) {
                            break;
                        } else {
                            i6++;
                        }
                    }
                    if (i6 != -1 && (iAbs = Math.abs(i3 - H.keyAt(i5))) < i4) {
                        iValueAt = H.valueAt(i5);
                        i4 = iAbs;
                    }
                }
                if (iValueAt != -1) {
                    builder.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(iValueAt));
                }
            }
            if (this.x != null) {
                MeteringRectangle[] meteringRectangleArr = {this.x};
                this.x.toString();
                builder.set(CaptureRequest.CONTROL_AF_REGIONS, meteringRectangleArr);
                builder.set(CaptureRequest.CONTROL_AE_REGIONS, meteringRectangleArr);
                builder.set(CaptureRequest.CONTROL_AWB_REGIONS, meteringRectangleArr);
            }
            if (!this.q.isEmpty()) {
                builder.set(CaptureRequest.SCALER_CROP_REGION, this.q);
            }
            if (this.B > 0) {
                builder.set(CaptureRequest.SENSOR_SENSITIVITY, Integer.valueOf(this.B));
            }
            if (traceEventM != null) {
                traceEventM.close();
            }
        } catch (Throwable th) {
            if (traceEventM == null) {
                throw th;
            }
            try {
                traceEventM.close();
                throw th;
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
                throw th;
            }
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void getPhotoCapabilitiesAsync(long j) {
        N.Mqw5545M(this.e, this);
        this.l.post(new WH(this, j));
    }

    @Override // org.chromium.media.VideoCapture
    public void setPhotoOptions(double d, int i, double d2, int i2, double d3, double d4, double[] dArr, boolean z, double d5, double d6, int i3, double d7, boolean z2, boolean z3, int i4, boolean z4, boolean z5, double d8) {
        N.Mqw5545M(this.e, this);
        this.l.post(new YH(this, new XH(this, d, i, d2, i2, d3, d4, dArr, z, d5, d6, i3, d7, z2, z3, i4, z4, z5, d8)));
    }

    @Override // org.chromium.media.VideoCapture
    public boolean startCaptureMaybeAsync() {
        N.Mqw5545M(this.e, this);
        f(0);
        CameraManager cameraManager = (CameraManager) Is.a.getSystemService("camera");
        VH vh = new VH(this, null);
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (this.d >= cameraIdList.length) {
                Ts.a("VideoCapture", "Invalid camera Id: ", Integer.valueOf(this.d));
                return false;
            }
            TraceEvent.I("VideoCaptureCamera2.java", "VideoCaptureCamera2.startCaptureMaybeAsync calling manager.openCamera");
            cameraManager.openCamera(cameraIdList[this.d], vh, this.l);
            return true;
        } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
            Ts.a("VideoCapture", "allocate: manager.openCamera: ", e);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean stopCaptureAndBlockUntilStopped() {
        N.Mqw5545M(this.e, this);
        TraceEvent traceEventM = TraceEvent.M("VideoCaptureCamera2.stopCaptureAndBlockUntilStopped");
        try {
            synchronized (this.f) {
                while (this.o != 2 && this.o != 3) {
                    try {
                        this.f.wait();
                    } catch (InterruptedException e) {
                        Ts.a("VideoCapture", "CaptureStartedEvent: ", e);
                    }
                }
                if (this.o == 3) {
                    if (traceEventM != null) {
                        traceEventM.close();
                    }
                    return true;
                }
                this.l.post(new ZH(this, null));
                this.m.block();
                if (traceEventM != null) {
                    traceEventM.close();
                }
                return true;
            }
        } catch (Throwable th) {
            if (traceEventM != null) {
                try {
                    traceEventM.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
            }
            throw th;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void takePhotoAsync(long j) {
        N.Mqw5545M(this.e, this);
        TraceEvent.I("VideoCaptureCamera2.java", "takePhotoAsync");
        this.l.post(new RunnableC0486aI(this, j));
    }
}
