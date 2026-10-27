package org.chromium.media;

import android.content.Context;
import android.hardware.Camera;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.Log;

/* JADX INFO: loaded from: classes3.dex */
public class VideoCaptureTango extends VideoCaptureCamera {
    private static final CamParams[] CAM_PARAMS = {new CamParams(0, "depth", 320, 240), new CamParams(1, "fisheye", 640, 480), new CamParams(2, "4MP", 1280, 720)};
    private static final byte CHROMA_ZERO_LEVEL = 127;
    private static final int DEPTH_CAMERA_ID = 0;
    private static final int FISHEYE_CAMERA_ID = 1;
    private static final int FOURMP_CAMERA_ID = 2;
    private static final int SF_FULL_HEIGHT = 1752;
    private static final int SF_HEIGHT = 1168;
    private static final int SF_LINES_BIGIMAGE = 720;
    private static final int SF_LINES_DEPTH = 60;
    private static final int SF_LINES_DEPTH_PADDED = 112;
    private static final int SF_LINES_FISHEYE = 240;
    private static final int SF_LINES_HEADER = 16;
    private static final int SF_LINES_RESERVED = 80;
    private static final int SF_OFFSET_4MP_CHROMA = 112;
    private static final int SF_WIDTH = 1280;
    private static final String TAG = "cr.media";
    private ByteBuffer mFrameBuffer;
    private final int mTangoCameraId;

    private static class CamParams {
        final int mHeight;
        final int mId;
        final String mName;
        final int mWidth;

        CamParams(int i, String str, int i2, int i3) {
            this.mId = i;
            this.mName = str;
            this.mWidth = i2;
            this.mHeight = i3;
        }
    }

    static int numberOfCameras() {
        return CAM_PARAMS.length;
    }

    static int getCaptureApiType(int i) {
        return i >= CAM_PARAMS.length ? 0 : 4;
    }

    static String getName(int i) {
        CamParams[] camParamsArr = CAM_PARAMS;
        return i >= camParamsArr.length ? "" : camParamsArr[i].mName;
    }

    static VideoCaptureFormat[] getDeviceSupportedFormats(int i) {
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            arrayList.add(new VideoCaptureFormat(320, 180, 5, AndroidImageFormat.YV12));
        } else if (i == 1) {
            arrayList.add(new VideoCaptureFormat(640, 480, 30, AndroidImageFormat.YV12));
        } else if (i == 2) {
            arrayList.add(new VideoCaptureFormat(1280, 720, 20, AndroidImageFormat.YV12));
        }
        return (VideoCaptureFormat[]) arrayList.toArray(new VideoCaptureFormat[arrayList.size()]);
    }

    VideoCaptureTango(Context context, int i, long j) {
        super(context, 0, j);
        this.mFrameBuffer = null;
        this.mTangoCameraId = i;
    }

    @Override // org.chromium.media.VideoCaptureCamera
    protected void setCaptureParameters(int i, int i2, int i3, Camera.Parameters parameters) {
        CamParams[] camParamsArr = CAM_PARAMS;
        this.mCaptureFormat = new VideoCaptureFormat(camParamsArr[this.mTangoCameraId].mWidth, camParamsArr[this.mTangoCameraId].mHeight, i3, AndroidImageFormat.YV12);
        parameters.set("sf-mode", "all");
    }

    @Override // org.chromium.media.VideoCaptureCamera
    protected void allocateBuffers() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(((this.mCaptureFormat.mWidth * this.mCaptureFormat.mHeight) * 3) / 2);
        this.mFrameBuffer = byteBufferAllocateDirect;
        Arrays.fill(byteBufferAllocateDirect.array(), (byte) 127);
    }

    @Override // org.chromium.media.VideoCaptureCamera
    protected void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.mCamera.setPreviewCallback(previewCallback);
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.mPreviewBufferLock.lock();
        try {
            if (this.mIsRunning) {
                if (bArr.length == 2242560) {
                    int i = this.mTangoCameraId;
                    if (i == 0) {
                        for (int i2 = 430080; i2 < 583680; i2 += 2) {
                            this.mFrameBuffer.put((byte) ((bArr[i2 + 1] << 4) | ((bArr[i2] & 240) >> 4)));
                        }
                        for (int i3 = 0; i3 < (this.mCaptureFormat.mWidth * this.mCaptureFormat.mHeight) - 76800; i3++) {
                            this.mFrameBuffer.put((byte) 0);
                        }
                    } else if (i == 1) {
                        ByteBuffer.wrap(bArr, 20480, 307200).get(this.mFrameBuffer.array(), 0, 307200);
                    } else if (i == 2) {
                        ByteBuffer.wrap(bArr, 573440, 921600).get(this.mFrameBuffer.array(), 0, 921600);
                        ByteBuffer.wrap(bArr, 1638400, 230400).get(this.mFrameBuffer.array(), 921600, 230400);
                        ByteBuffer.wrap(bArr, 2012160, 230400).get(this.mFrameBuffer.array(), 1152000, 230400);
                    } else {
                        Log.e(TAG, "Unknown camera, #id: %d", Integer.valueOf(i));
                    }
                    this.mFrameBuffer.rewind();
                    nativeOnFrameAvailable(this.mNativeVideoCaptureDeviceAndroid, this.mFrameBuffer.array(), this.mFrameBuffer.capacity(), getCameraRotation());
                }
            }
        } finally {
            this.mPreviewBufferLock.unlock();
        }
    }
}
