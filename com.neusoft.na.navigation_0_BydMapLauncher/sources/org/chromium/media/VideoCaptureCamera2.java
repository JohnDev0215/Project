package org.chromium.media;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
public class VideoCaptureCamera2 extends VideoCapture {
    private static final String TAG = "VideoCapture";
    private static final double kNanoSecondsToFps = 1.0E-9d;
    private CameraDevice mCameraDevice;
    private CameraState mCameraState;
    private final Object mCameraStateLock;
    private byte[] mCapturedData;
    private CaptureRequest mPreviewRequest;
    private CameraCaptureSession mPreviewSession;

    private enum CameraState {
        OPENING,
        CONFIGURING,
        STARTED,
        STOPPED
    }

    private class CrStateListener extends CameraDevice.StateCallback {
        private CrStateListener() {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            VideoCaptureCamera2.this.mCameraDevice = cameraDevice;
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.CONFIGURING);
            if (VideoCaptureCamera2.this.createPreviewObjects()) {
                return;
            }
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
            VideoCaptureCamera2 videoCaptureCamera2 = VideoCaptureCamera2.this;
            videoCaptureCamera2.nativeOnError(videoCaptureCamera2.mNativeVideoCaptureDeviceAndroid, "Error configuring camera");
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            cameraDevice.close();
            VideoCaptureCamera2.this.mCameraDevice = null;
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            VideoCaptureCamera2.this.mCameraDevice = null;
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
            VideoCaptureCamera2 videoCaptureCamera2 = VideoCaptureCamera2.this;
            videoCaptureCamera2.nativeOnError(videoCaptureCamera2.mNativeVideoCaptureDeviceAndroid, "Camera device error " + Integer.toString(i));
        }
    }

    private class CrPreviewSessionListener extends CameraCaptureSession.StateCallback {
        private final CaptureRequest mPreviewRequest;

        CrPreviewSessionListener(CaptureRequest captureRequest) {
            this.mPreviewRequest = captureRequest;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            Log.d(VideoCaptureCamera2.TAG, "onConfigured");
            VideoCaptureCamera2.this.mPreviewSession = cameraCaptureSession;
            try {
                VideoCaptureCamera2.this.mPreviewSession.setRepeatingRequest(this.mPreviewRequest, null, null);
                VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STARTED);
            } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
                Log.e(VideoCaptureCamera2.TAG, "setRepeatingRequest: ", e);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
            VideoCaptureCamera2 videoCaptureCamera2 = VideoCaptureCamera2.this;
            videoCaptureCamera2.nativeOnError(videoCaptureCamera2.mNativeVideoCaptureDeviceAndroid, "Camera session configuration error");
        }
    }

    private class CrImageReaderListener implements ImageReader.OnImageAvailableListener {
        private CrImageReaderListener() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            try {
                Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                if (imageAcquireLatestImage == null) {
                    if (imageAcquireLatestImage != null) {
                        imageAcquireLatestImage.close();
                        return;
                    }
                    return;
                }
                try {
                    if (imageAcquireLatestImage.getFormat() != 35 || imageAcquireLatestImage.getPlanes().length != 3) {
                        VideoCaptureCamera2 videoCaptureCamera2 = VideoCaptureCamera2.this;
                        videoCaptureCamera2.nativeOnError(videoCaptureCamera2.mNativeVideoCaptureDeviceAndroid, "Unexpected image format: " + imageAcquireLatestImage.getFormat() + " or #planes: " + imageAcquireLatestImage.getPlanes().length);
                        throw new IllegalStateException();
                    }
                    if (imageReader.getWidth() == imageAcquireLatestImage.getWidth() && imageReader.getHeight() == imageAcquireLatestImage.getHeight()) {
                        VideoCaptureCamera2.readImageIntoBuffer(imageAcquireLatestImage, VideoCaptureCamera2.this.mCapturedData);
                        VideoCaptureCamera2 videoCaptureCamera22 = VideoCaptureCamera2.this;
                        videoCaptureCamera22.nativeOnFrameAvailable(videoCaptureCamera22.mNativeVideoCaptureDeviceAndroid, VideoCaptureCamera2.this.mCapturedData, VideoCaptureCamera2.this.mCapturedData.length, VideoCaptureCamera2.this.getCameraRotation());
                        if (imageAcquireLatestImage != null) {
                            imageAcquireLatestImage.close();
                            return;
                        }
                        return;
                    }
                    VideoCaptureCamera2 videoCaptureCamera23 = VideoCaptureCamera2.this;
                    videoCaptureCamera23.nativeOnError(videoCaptureCamera23.mNativeVideoCaptureDeviceAndroid, "ImageReader size (" + imageReader.getWidth() + "x" + imageReader.getHeight() + ") did not match Image size (" + imageAcquireLatestImage.getWidth() + "x" + imageAcquireLatestImage.getHeight() + ")");
                    throw new IllegalStateException();
                } finally {
                }
            } catch (IllegalStateException e) {
                Log.e(VideoCaptureCamera2.TAG, "acquireLatestImage():", e);
            }
        }
    }

    private class CrPhotoSessionListener extends CameraCaptureSession.StateCallback {
        private final long mCallbackId;
        private final CaptureRequest mPhotoRequest;

        CrPhotoSessionListener(CaptureRequest captureRequest, long j) {
            this.mPhotoRequest = captureRequest;
            this.mCallbackId = j;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            Log.d(VideoCaptureCamera2.TAG, "onConfigured");
            try {
                cameraCaptureSession.capture(this.mPhotoRequest, null, null);
            } catch (CameraAccessException unused) {
                Log.e(VideoCaptureCamera2.TAG, "capture() error", new Object[0]);
                VideoCaptureCamera2.this.notifyTakePhotoError(this.mCallbackId);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Log.e(VideoCaptureCamera2.TAG, "failed configuring capture session", new Object[0]);
            VideoCaptureCamera2.this.notifyTakePhotoError(this.mCallbackId);
        }
    }

    private class CrPhotoReaderListener implements ImageReader.OnImageAvailableListener {
        private final long mCallbackId;

        CrPhotoReaderListener(long j) {
            this.mCallbackId = j;
        }

        private byte[] readCapturedData(Image image) {
            try {
                try {
                    return image.getPlanes()[0].getBuffer().array();
                } catch (UnsupportedOperationException unused) {
                    ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                    byte[] bArr = new byte[buffer.remaining()];
                    buffer.get(bArr);
                    return bArr;
                }
            } catch (Throwable unused2) {
                return null;
            }
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            Log.d(VideoCaptureCamera2.TAG, "CrPhotoReaderListener.mCallbackId " + this.mCallbackId);
            try {
                Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                try {
                    if (imageAcquireLatestImage == null) {
                        throw new IllegalStateException();
                    }
                    if (imageAcquireLatestImage.getFormat() != 256) {
                        Log.e(VideoCaptureCamera2.TAG, "Unexpected image format: %d", Integer.valueOf(imageAcquireLatestImage.getFormat()));
                        throw new IllegalStateException();
                    }
                    byte[] capturedData = readCapturedData(imageAcquireLatestImage);
                    VideoCaptureCamera2 videoCaptureCamera2 = VideoCaptureCamera2.this;
                    videoCaptureCamera2.nativeOnPhotoTaken(videoCaptureCamera2.mNativeVideoCaptureDeviceAndroid, this.mCallbackId, capturedData);
                    if (imageAcquireLatestImage != null) {
                        imageAcquireLatestImage.close();
                    }
                    if (VideoCaptureCamera2.this.createPreviewObjects()) {
                        return;
                    }
                    VideoCaptureCamera2 videoCaptureCamera22 = VideoCaptureCamera2.this;
                    videoCaptureCamera22.nativeOnError(videoCaptureCamera22.mNativeVideoCaptureDeviceAndroid, "Error restarting preview");
                } finally {
                }
            } catch (IllegalStateException unused) {
                VideoCaptureCamera2.this.notifyTakePhotoError(this.mCallbackId);
            }
        }
    }

    private static CameraCharacteristics getCameraCharacteristics(Context context, int i) {
        try {
            return ((CameraManager) context.getSystemService("camera")).getCameraCharacteristics(Integer.toString(i));
        } catch (CameraAccessException e) {
            Log.e(TAG, "getCameraCharacteristics: ", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTakePhotoError(long j) {
        nativeOnPhotoTaken(this.mNativeVideoCaptureDeviceAndroid, j, new byte[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean createPreviewObjects() {
        Log.d(TAG, "createPreviewObjects");
        if (this.mCameraDevice == null) {
            return false;
        }
        ImageReader imageReaderNewInstance = ImageReader.newInstance(this.mCaptureFormat.getWidth(), this.mCaptureFormat.getHeight(), this.mCaptureFormat.getPixelFormat(), 2);
        HandlerThread handlerThread = new HandlerThread("CameraPreview");
        handlerThread.start();
        imageReaderNewInstance.setOnImageAvailableListener(new CrImageReaderListener(), new Handler(handlerThread.getLooper()));
        try {
            CaptureRequest.Builder builderCreateCaptureRequest = this.mCameraDevice.createCaptureRequest(1);
            if (builderCreateCaptureRequest == null) {
                Log.e(TAG, "previewRequestBuilder error", new Object[0]);
                return false;
            }
            builderCreateCaptureRequest.addTarget(imageReaderNewInstance.getSurface());
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_MODE, 1);
            builderCreateCaptureRequest.set(CaptureRequest.NOISE_REDUCTION_MODE, 1);
            builderCreateCaptureRequest.set(CaptureRequest.EDGE_MODE, 1);
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(imageReaderNewInstance.getSurface());
            this.mPreviewRequest = builderCreateCaptureRequest.build();
            try {
                this.mCameraDevice.createCaptureSession(arrayList, new CrPreviewSessionListener(this.mPreviewRequest), null);
                return true;
            } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
                Log.e(TAG, "createCaptureSession: ", e);
                return false;
            }
        } catch (CameraAccessException | IllegalArgumentException | SecurityException e2) {
            Log.e(TAG, "createCaptureRequest: ", e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readImageIntoBuffer(Image image, byte[] bArr) {
        int width = image.getWidth();
        int height = image.getHeight();
        Image.Plane[] planes = image.getPlanes();
        int i = 0;
        int i2 = 0;
        while (i < planes.length) {
            ByteBuffer buffer = planes[i].getBuffer();
            int rowStride = planes[i].getRowStride();
            int pixelStride = planes[i].getPixelStride();
            int i3 = i == 0 ? width : width / 2;
            int i4 = i == 0 ? height : height / 2;
            if (pixelStride == 1 && rowStride == i3) {
                int i5 = i3 * i4;
                buffer.get(bArr, i2, i5);
                i2 += i5;
            } else {
                byte[] bArr2 = new byte[rowStride];
                for (int i6 = 0; i6 < i4 - 1; i6++) {
                    buffer.get(bArr2, 0, rowStride);
                    int i7 = 0;
                    while (i7 < i3) {
                        bArr[i2] = bArr2[i7 * pixelStride];
                        i7++;
                        i2++;
                    }
                }
                buffer.get(bArr2, 0, Math.min(rowStride, buffer.remaining()));
                int i8 = 0;
                while (i8 < i3) {
                    bArr[i2] = bArr2[i8 * pixelStride];
                    i8++;
                    i2++;
                }
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCameraStateAndNotify(CameraState cameraState) {
        synchronized (this.mCameraStateLock) {
            this.mCameraState = cameraState;
            this.mCameraStateLock.notifyAll();
        }
    }

    static boolean isLegacyDevice(Context context, int i) {
        CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(context, i);
        return cameraCharacteristics != null && ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2;
    }

    static int getNumberOfCameras(Context context) {
        try {
            return ((CameraManager) context.getSystemService("camera")).getCameraIdList().length;
        } catch (CameraAccessException | SecurityException e) {
            Log.e(TAG, "getNumberOfCameras: getCameraIdList(): ", e);
            return 0;
        }
    }

    static int getCaptureApiType(int i, Context context) {
        CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(context, i);
        if (cameraCharacteristics == null) {
            return 5;
        }
        int iIntValue = ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
        if (iIntValue != 0) {
            return iIntValue != 1 ? 1 : 2;
        }
        return 3;
    }

    static String getName(int i, Context context) {
        CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(context, i);
        if (cameraCharacteristics == null) {
            return null;
        }
        return "camera2 " + i + ", facing " + (((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0 ? "front" : "back");
    }

    static VideoCaptureFormat[] getDeviceSupportedFormats(Context context, int i) {
        boolean z;
        CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(context, i);
        if (cameraCharacteristics == null) {
            return null;
        }
        int[] iArr = (int[]) cameraCharacteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            }
            z = true;
            if (iArr[i2] == 1) {
                break;
            }
            i2++;
        }
        ArrayList arrayList = new ArrayList();
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        for (int i3 : streamConfigurationMap.getOutputFormats()) {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(i3);
            if (outputSizes != null) {
                for (Size size : outputSizes) {
                    double d = 0.0d;
                    if (z) {
                        long outputMinFrameDuration = streamConfigurationMap.getOutputMinFrameDuration(i3, size);
                        if (outputMinFrameDuration != 0) {
                            d = outputMinFrameDuration * 9.999999999999999E8d;
                        }
                    }
                    arrayList.add(new VideoCaptureFormat(size.getWidth(), size.getHeight(), (int) d, 0));
                }
            }
        }
        return (VideoCaptureFormat[]) arrayList.toArray(new VideoCaptureFormat[arrayList.size()]);
    }

    VideoCaptureCamera2(Context context, int i, long j) {
        super(context, i, j);
        this.mCameraStateLock = new Object();
        this.mCameraState = CameraState.STOPPED;
    }

    @Override // org.chromium.media.VideoCapture
    public boolean allocate(int i, int i2, int i3) {
        Log.d(TAG, "allocate: requested (%d x %d) @%dfps", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        synchronized (this.mCameraStateLock) {
            if (this.mCameraState != CameraState.OPENING && this.mCameraState != CameraState.CONFIGURING) {
                CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(this.mContext, this.mId);
                Size[] outputSizes = ((StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(35);
                if (outputSizes == null) {
                    return false;
                }
                Size size = null;
                int i4 = Integer.MAX_VALUE;
                for (Size size2 : outputSizes) {
                    int iAbs = Math.abs(size2.getWidth() - i) + Math.abs(size2.getHeight() - i2);
                    if (iAbs < i4) {
                        size = size2;
                        i4 = iAbs;
                    }
                }
                if (i4 == Integer.MAX_VALUE) {
                    Log.e(TAG, "No supported resolutions.", new Object[0]);
                    return false;
                }
                Log.d(TAG, "allocate: matched (%d x %d)", Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()));
                this.mCaptureFormat = new VideoCaptureFormat(size.getWidth(), size.getHeight(), i3, 35);
                this.mCapturedData = new byte[((this.mCaptureFormat.mWidth * this.mCaptureFormat.mHeight) * ImageFormat.getBitsPerPixel(this.mCaptureFormat.mPixelFormat)) / 8];
                this.mCameraNativeOrientation = ((Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                this.mInvertDeviceOrientationReadings = ((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 1;
                return true;
            }
            Log.e(TAG, "allocate() invoked while Camera is busy opening/configuring.", new Object[0]);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean startCapture() {
        Log.d(TAG, "startCapture");
        changeCameraStateAndNotify(CameraState.OPENING);
        try {
            ((CameraManager) this.mContext.getSystemService("camera")).openCamera(Integer.toString(this.mId), new CrStateListener(), new Handler(this.mContext.getMainLooper()));
            return true;
        } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
            Log.e(TAG, "allocate: manager.openCamera: ", e);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean stopCapture() {
        Log.d(TAG, "stopCapture");
        synchronized (this.mCameraStateLock) {
            while (this.mCameraState != CameraState.STARTED && this.mCameraState != CameraState.STOPPED) {
                try {
                    this.mCameraStateLock.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "CaptureStartedEvent: ", e);
                }
            }
            if (this.mCameraState == CameraState.STOPPED) {
                return true;
            }
            try {
                this.mPreviewSession.abortCaptures();
                CameraDevice cameraDevice = this.mCameraDevice;
                if (cameraDevice == null) {
                    return false;
                }
                cameraDevice.close();
                changeCameraStateAndNotify(CameraState.STOPPED);
                return true;
            } catch (CameraAccessException | IllegalStateException e2) {
                Log.e(TAG, "abortCaptures: ", e2);
                return false;
            }
        }
    }

    @Override // org.chromium.media.VideoCapture
    public PhotoCapabilities getPhotoCapabilities() {
        CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(this.mContext, this.mId);
        return new PhotoCapabilities(Math.round(((Float) cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue() * 100.0f), 100, (((Rect) this.mPreviewRequest.get(CaptureRequest.SCALER_CROP_REGION)).width() * 100) / ((Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE)).width());
    }

    @Override // org.chromium.media.VideoCapture
    public boolean takePhoto(long j) {
        Log.d(TAG, "takePhoto " + j);
        if (this.mCameraDevice != null && this.mCameraState == CameraState.STARTED) {
            ImageReader imageReaderNewInstance = ImageReader.newInstance(this.mCaptureFormat.getWidth(), this.mCaptureFormat.getHeight(), 256, 1);
            HandlerThread handlerThread = new HandlerThread("CameraPicture");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper());
            imageReaderNewInstance.setOnImageAvailableListener(new CrPhotoReaderListener(j), handler);
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(imageReaderNewInstance.getSurface());
            try {
                CaptureRequest.Builder builderCreateCaptureRequest = this.mCameraDevice.createCaptureRequest(2);
                if (builderCreateCaptureRequest == null) {
                    Log.e(TAG, "photoRequestBuilder error", new Object[0]);
                    return false;
                }
                builderCreateCaptureRequest.addTarget(imageReaderNewInstance.getSurface());
                builderCreateCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(getCameraRotation()));
                try {
                    this.mCameraDevice.createCaptureSession(arrayList, new CrPhotoSessionListener(builderCreateCaptureRequest.build(), j), handler);
                    return true;
                } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
                    Log.e(TAG, "createCaptureSession: " + e, new Object[0]);
                    return false;
                }
            } catch (CameraAccessException unused) {
                Log.e(TAG, "mCameraDevice.createCaptureRequest() error", new Object[0]);
            }
        }
        return false;
    }

    @Override // org.chromium.media.VideoCapture
    public void deallocate() {
        Log.d(TAG, "deallocate");
    }
}
