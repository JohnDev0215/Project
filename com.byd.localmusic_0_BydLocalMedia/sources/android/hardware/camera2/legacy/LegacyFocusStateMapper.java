package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.utils.ParamsUtils;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LegacyFocusStateMapper {
    private static final boolean DEBUG = false;
    private static String TAG = "LegacyFocusStateMapper";
    private final Camera mCamera;
    private int mAfStatePrevious = 0;
    private String mAfModePrevious = null;
    private final Object mLock = new Object();
    private int mAfRun = 0;
    private int mAfState = 0;

    public LegacyFocusStateMapper(Camera camera) {
        this.mCamera = (Camera) Preconditions.checkNotNull(camera, "camera must not be null");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void processRequestTriggers(CaptureRequest captureRequest, Camera.Parameters parameters) {
        final int i;
        byte b;
        final int i2;
        Preconditions.checkNotNull(captureRequest, "captureRequest must not be null");
        int i3 = 0;
        int iIntValue = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_AF_TRIGGER, 0)).intValue();
        final String focusMode = parameters.getFocusMode();
        if (!Objects.equals(this.mAfModePrevious, focusMode)) {
            synchronized (this.mLock) {
                this.mAfRun++;
                this.mAfState = 0;
            }
            this.mCamera.cancelAutoFocus();
        }
        this.mAfModePrevious = focusMode;
        synchronized (this.mLock) {
            i = this.mAfRun;
        }
        Camera.AutoFocusMoveCallback autoFocusMoveCallback = new Camera.AutoFocusMoveCallback() { // from class: android.hardware.camera2.legacy.LegacyFocusStateMapper.1
            @Override // android.hardware.Camera.AutoFocusMoveCallback
            public void onAutoFocusMoving(boolean z, Camera camera) {
                synchronized (LegacyFocusStateMapper.this.mLock) {
                    if (i != LegacyFocusStateMapper.this.mAfRun) {
                        Log.d(LegacyFocusStateMapper.TAG, "onAutoFocusMoving - ignoring move callbacks from old af run" + i);
                        return;
                    }
                    int i4 = z ? 1 : 2;
                    String str = focusMode;
                    byte b2 = -1;
                    int iHashCode = str.hashCode();
                    if (iHashCode != -194628547) {
                        if (iHashCode == 910005312 && str.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                            b2 = 0;
                        }
                    } else if (str.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                        b2 = 1;
                    }
                    if (b2 != 0 && b2 != 1) {
                        Log.w(LegacyFocusStateMapper.TAG, "onAutoFocus - got unexpected onAutoFocus in mode " + focusMode);
                    }
                    LegacyFocusStateMapper.this.mAfState = i4;
                }
            }
        };
        byte b2 = -1;
        switch (focusMode.hashCode()) {
            case -194628547:
                b = !focusMode.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO) ? (byte) -1 : (byte) 3;
                break;
            case 3005871:
                b = !focusMode.equals("auto") ? (byte) -1 : (byte) 0;
                break;
            case 103652300:
                b = !focusMode.equals(Camera.Parameters.FOCUS_MODE_MACRO) ? (byte) -1 : (byte) 1;
                break;
            case 910005312:
                b = !focusMode.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE) ? (byte) -1 : (byte) 2;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0 || b == 1 || b == 2 || b == 3) {
            this.mCamera.setAutoFocusMoveCallback(autoFocusMoveCallback);
        }
        if (iIntValue != 0) {
            if (iIntValue != 1) {
                if (iIntValue == 2) {
                    synchronized (this.mLock) {
                        synchronized (this.mLock) {
                            this.mAfRun++;
                            this.mAfState = 0;
                        }
                        this.mCamera.cancelAutoFocus();
                    }
                    return;
                }
                Log.w(TAG, "processRequestTriggers - ignoring unknown control.afTrigger = " + iIntValue);
                return;
            }
            switch (focusMode.hashCode()) {
                case -194628547:
                    if (focusMode.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                        b2 = 3;
                    }
                    break;
                case 3005871:
                    if (focusMode.equals("auto")) {
                        b2 = 0;
                    }
                    break;
                case 103652300:
                    if (focusMode.equals(Camera.Parameters.FOCUS_MODE_MACRO)) {
                        b2 = 1;
                    }
                    break;
                case 910005312:
                    if (focusMode.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                        b2 = 2;
                    }
                    break;
            }
            if (b2 == 0 || b2 == 1) {
                i3 = 3;
            } else if (b2 == 2 || b2 == 3) {
                i3 = 1;
            }
            synchronized (this.mLock) {
                i2 = this.mAfRun + 1;
                this.mAfRun = i2;
                this.mAfState = i3;
            }
            if (i3 == 0) {
                return;
            }
            this.mCamera.autoFocus(new Camera.AutoFocusCallback() { // from class: android.hardware.camera2.legacy.LegacyFocusStateMapper.2
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
                @Override // android.hardware.Camera.AutoFocusCallback
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onAutoFocus(boolean r8, android.hardware.Camera r9) {
                    /*
                        r7 = this;
                        android.hardware.camera2.legacy.LegacyFocusStateMapper r9 = android.hardware.camera2.legacy.LegacyFocusStateMapper.this
                        java.lang.Object r9 = android.hardware.camera2.legacy.LegacyFocusStateMapper.access$000(r9)
                        monitor-enter(r9)
                        android.hardware.camera2.legacy.LegacyFocusStateMapper r0 = android.hardware.camera2.legacy.LegacyFocusStateMapper.this     // Catch: java.lang.Throwable -> L95
                        int r0 = android.hardware.camera2.legacy.LegacyFocusStateMapper.access$100(r0)     // Catch: java.lang.Throwable -> L95
                        int r1 = r2     // Catch: java.lang.Throwable -> L95
                        r2 = 0
                        r3 = 1
                        r4 = 2
                        if (r0 == r1) goto L33
                        java.lang.String r8 = android.hardware.camera2.legacy.LegacyFocusStateMapper.access$200()     // Catch: java.lang.Throwable -> L95
                        java.lang.String r1 = "onAutoFocus - ignoring AF callback (old run %d, new run %d)"
                        java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L95
                        int r5 = r2     // Catch: java.lang.Throwable -> L95
                        java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L95
                        r4[r2] = r5     // Catch: java.lang.Throwable -> L95
                        java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L95
                        r4[r3] = r0     // Catch: java.lang.Throwable -> L95
                        java.lang.String r0 = java.lang.String.format(r1, r4)     // Catch: java.lang.Throwable -> L95
                        android.util.Log.d(r8, r0)     // Catch: java.lang.Throwable -> L95
                        monitor-exit(r9)     // Catch: java.lang.Throwable -> L95
                        return
                    L33:
                        if (r8 == 0) goto L37
                        r8 = 4
                        goto L38
                    L37:
                        r8 = 5
                    L38:
                        java.lang.String r0 = r3     // Catch: java.lang.Throwable -> L95
                        r1 = -1
                        int r5 = r0.hashCode()     // Catch: java.lang.Throwable -> L95
                        r6 = 3
                        switch(r5) {
                            case -194628547: goto L61;
                            case 3005871: goto L58;
                            case 103652300: goto L4e;
                            case 910005312: goto L44;
                            default: goto L43;
                        }     // Catch: java.lang.Throwable -> L95
                    L43:
                        goto L6b
                    L44:
                        java.lang.String r2 = "continuous-picture"
                        boolean r0 = r0.equals(r2)     // Catch: java.lang.Throwable -> L95
                        if (r0 == 0) goto L6b
                        r2 = r3
                        goto L6c
                    L4e:
                        java.lang.String r2 = "macro"
                        boolean r0 = r0.equals(r2)     // Catch: java.lang.Throwable -> L95
                        if (r0 == 0) goto L6b
                        r2 = r6
                        goto L6c
                    L58:
                        java.lang.String r5 = "auto"
                        boolean r0 = r0.equals(r5)     // Catch: java.lang.Throwable -> L95
                        if (r0 == 0) goto L6b
                        goto L6c
                    L61:
                        java.lang.String r2 = "continuous-video"
                        boolean r0 = r0.equals(r2)     // Catch: java.lang.Throwable -> L95
                        if (r0 == 0) goto L6b
                        r2 = r4
                        goto L6c
                    L6b:
                        r2 = r1
                    L6c:
                        if (r2 == 0) goto L8e
                        if (r2 == r3) goto L8e
                        if (r2 == r4) goto L8e
                        if (r2 == r6) goto L8e
                        java.lang.String r0 = android.hardware.camera2.legacy.LegacyFocusStateMapper.access$200()     // Catch: java.lang.Throwable -> L95
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L95
                        r1.<init>()     // Catch: java.lang.Throwable -> L95
                        java.lang.String r2 = "onAutoFocus - got unexpected onAutoFocus in mode "
                        r1.append(r2)     // Catch: java.lang.Throwable -> L95
                        java.lang.String r2 = r3     // Catch: java.lang.Throwable -> L95
                        r1.append(r2)     // Catch: java.lang.Throwable -> L95
                        java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L95
                        android.util.Log.w(r0, r1)     // Catch: java.lang.Throwable -> L95
                    L8e:
                        android.hardware.camera2.legacy.LegacyFocusStateMapper r0 = android.hardware.camera2.legacy.LegacyFocusStateMapper.this     // Catch: java.lang.Throwable -> L95
                        android.hardware.camera2.legacy.LegacyFocusStateMapper.access$302(r0, r8)     // Catch: java.lang.Throwable -> L95
                        monitor-exit(r9)     // Catch: java.lang.Throwable -> L95
                        return
                    L95:
                        r8 = move-exception
                        monitor-exit(r9)     // Catch: java.lang.Throwable -> L95
                        throw r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.legacy.LegacyFocusStateMapper.AnonymousClass2.onAutoFocus(boolean, android.hardware.Camera):void");
                }
            });
        }
    }

    public void mapResultTriggers(CameraMetadataNative cameraMetadataNative) {
        int i;
        Preconditions.checkNotNull(cameraMetadataNative, "result must not be null");
        synchronized (this.mLock) {
            i = this.mAfState;
        }
        cameraMetadataNative.set(CaptureResult.CONTROL_AF_STATE, Integer.valueOf(i));
        this.mAfStatePrevious = i;
    }

    private static String afStateToString(int i) {
        switch (i) {
            case 0:
                return "INACTIVE";
            case 1:
                return "PASSIVE_SCAN";
            case 2:
                return "PASSIVE_FOCUSED";
            case 3:
                return "ACTIVE_SCAN";
            case 4:
                return "FOCUSED_LOCKED";
            case 5:
                return "NOT_FOCUSED_LOCKED";
            case 6:
                return "PASSIVE_UNFOCUSED";
            default:
                return "UNKNOWN(" + i + ")";
        }
    }
}
