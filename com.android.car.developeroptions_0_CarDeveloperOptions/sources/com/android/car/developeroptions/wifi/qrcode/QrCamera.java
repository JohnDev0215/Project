package com.android.car.developeroptions.wifi.qrcode;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Size;
import android.view.WindowManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public class QrCamera extends Handler {
    private static long AUTOFOCUS_INTERVAL_MS = 1500;
    private static List<BarcodeFormat> FORMATS;
    private static Map<DecodeHintType, List<BarcodeFormat>> HINTS = new ArrayMap();
    private Camera mCamera;
    private int mCameraOrientation;
    private WeakReference<Context> mContext;
    private DecodingTask mDecodeTask;
    private Camera.Parameters mParameters;
    private Size mPreviewSize;
    private MultiFormatReader mReader;
    private ScannerCallback mScannerCallback;

    public interface ScannerCallback {
        Rect getFramePosition(Size size, int i);

        Size getViewSize();

        void handleCameraFailure();

        void handleSuccessfulResult(String str);

        boolean isValid(String str);

        void setTransform(Matrix matrix);
    }

    private double getRatio(double d, double d2) {
        return d < d2 ? d / d2 : d2 / d;
    }

    static {
        ArrayList arrayList = new ArrayList();
        FORMATS = arrayList;
        arrayList.add(BarcodeFormat.QR_CODE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, FORMATS);
    }

    public QrCamera(Context context, ScannerCallback scannerCallback) {
        this.mContext = new WeakReference<>(context);
        this.mScannerCallback = scannerCallback;
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        this.mReader = multiFormatReader;
        multiFormatReader.setHints(HINTS);
    }

    public void start(SurfaceTexture surfaceTexture) {
        if (this.mDecodeTask == null) {
            DecodingTask decodingTask = new DecodingTask(surfaceTexture);
            this.mDecodeTask = decodingTask;
            decodingTask.executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
        }
    }

    public void stop() {
        removeMessages(1);
        DecodingTask decodingTask = this.mDecodeTask;
        if (decodingTask != null) {
            decodingTask.cancel(true);
            this.mDecodeTask = null;
        }
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCameraParameter() {
        Camera.Parameters parameters = this.mCamera.getParameters();
        this.mParameters = parameters;
        Size bestPreviewSize = getBestPreviewSize(parameters);
        this.mPreviewSize = bestPreviewSize;
        this.mParameters.setPreviewSize(bestPreviewSize.getWidth(), this.mPreviewSize.getHeight());
        this.mParameters.setPictureSize(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight());
        if (this.mParameters.getSupportedFlashModes().contains("off")) {
            this.mParameters.setFlashMode("off");
        }
        List<String> supportedFocusModes = this.mParameters.getSupportedFocusModes();
        if (supportedFocusModes.contains("continuous-picture")) {
            this.mParameters.setFocusMode("continuous-picture");
        } else if (supportedFocusModes.contains("auto")) {
            this.mParameters.setFocusMode("auto");
        }
        this.mCamera.setParameters(this.mParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startPreview() {
        int i = 0;
        if (this.mContext.get() == null) {
            return false;
        }
        int rotation = ((WindowManager) this.mContext.get().getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation != 0) {
            if (rotation == 1) {
                i = 90;
            } else if (rotation == 2) {
                i = 180;
            } else if (rotation == 3) {
                i = 270;
            }
        }
        this.mCamera.setDisplayOrientation(((this.mCameraOrientation - i) + 360) % 360);
        this.mCamera.startPreview();
        if (this.mParameters.getFocusMode() == "auto") {
            this.mCamera.autoFocus(null);
            sendMessageDelayed(obtainMessage(1), AUTOFOCUS_INTERVAL_MS);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DecodingTask extends AsyncTask<Void, Void, String> {
        private QrYuvLuminanceSource mImage;
        private SurfaceTexture mSurface;

        private DecodingTask(SurfaceTexture surfaceTexture) {
            this.mSurface = surfaceTexture;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            Result resultDecodeWithState;
            if (!initCamera(this.mSurface)) {
                return null;
            }
            final Semaphore semaphore = new Semaphore(0);
            while (true) {
                QrCamera.this.mCamera.setOneShotPreviewCallback(new Camera.PreviewCallback() { // from class: com.android.car.developeroptions.wifi.qrcode.-$$Lambda$QrCamera$DecodingTask$CVPtxpIcbCrjYdW6N3uiUiLH984
                    @Override // android.hardware.Camera.PreviewCallback
                    public final void onPreviewFrame(byte[] bArr, Camera camera) {
                        this.f$0.lambda$doInBackground$0$QrCamera$DecodingTask(semaphore, bArr, camera);
                    }
                });
                try {
                    semaphore.acquire();
                    try {
                        resultDecodeWithState = QrCamera.this.mReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(this.mImage)));
                        QrCamera.this.mReader.reset();
                    } catch (ReaderException unused) {
                        QrCamera.this.mReader.reset();
                        resultDecodeWithState = null;
                    } catch (Throwable th) {
                        QrCamera.this.mReader.reset();
                        throw th;
                    }
                    if (resultDecodeWithState != null && QrCamera.this.mScannerCallback.isValid(resultDecodeWithState.getText())) {
                        return resultDecodeWithState.getText();
                    }
                } catch (InterruptedException unused2) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$doInBackground$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$doInBackground$0$QrCamera$DecodingTask(Semaphore semaphore, byte[] bArr, Camera camera) {
            this.mImage = QrCamera.this.getFrameImage(bArr);
            semaphore.release();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            if (str != null) {
                QrCamera.this.mScannerCallback.handleSuccessfulResult(str);
            }
        }

        private boolean initCamera(SurfaceTexture surfaceTexture) {
            int numberOfCameras = Camera.getNumberOfCameras();
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int i = 0;
            while (true) {
                if (i >= numberOfCameras) {
                    break;
                }
                try {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == 0) {
                        QrCamera.this.mCamera = Camera.open(i);
                        QrCamera.this.mCamera.setPreviewTexture(surfaceTexture);
                        QrCamera.this.mCameraOrientation = cameraInfo.orientation;
                        break;
                    }
                    i++;
                } catch (IOException unused) {
                    Log.e("QrCamera", "Error to init Camera");
                    QrCamera.this.mCamera = null;
                    QrCamera.this.mScannerCallback.handleCameraFailure();
                    return false;
                }
            }
            if (QrCamera.this.mCamera != null) {
                QrCamera.this.setCameraParameter();
                QrCamera.this.setTransformationMatrix(QrCamera.this.mScannerCallback.getViewSize());
                if (QrCamera.this.startPreview()) {
                    return true;
                }
                Log.e("QrCamera", "Error to init Camera");
                QrCamera.this.mCamera = null;
                QrCamera.this.mScannerCallback.handleCameraFailure();
                return false;
            }
            Log.e("QrCamera", "Cannot find available back camera.");
            QrCamera.this.mScannerCallback.handleCameraFailure();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransformationMatrix(Size size) {
        float f;
        if (((int) getRatio(size.getWidth(), size.getHeight())) != 1) {
            throw new IllegalArgumentException("Preview area should be square");
        }
        boolean z = this.mContext.get().getResources().getConfiguration().orientation == 1;
        Size size2 = this.mPreviewSize;
        int width = z ? size2.getWidth() : size2.getHeight();
        int height = z ? this.mPreviewSize.getHeight() : this.mPreviewSize.getWidth();
        float ratio = (float) getRatio(width, height);
        float f2 = 1.0f;
        if (width > height) {
            f = 1.0f / ratio;
        } else {
            f2 = 1.0f / ratio;
            f = 1.0f;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f2, f);
        this.mScannerCallback.setTransform(matrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QrYuvLuminanceSource getFrameImage(byte[] bArr) {
        Rect framePosition = this.mScannerCallback.getFramePosition(this.mPreviewSize, this.mCameraOrientation);
        Camera.Size pictureSize = this.mParameters.getPictureSize();
        return (QrYuvLuminanceSource) new QrYuvLuminanceSource(bArr, pictureSize.width, pictureSize.height).crop(framePosition.left, framePosition.top, framePosition.width(), framePosition.height());
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1) {
            this.mCamera.autoFocus(null);
            sendMessageDelayed(obtainMessage(1), AUTOFOCUS_INTERVAL_MS);
        } else {
            Log.d("QrCamera", "Unexpected Message: " + message.what);
        }
    }

    private Size getBestPreviewSize(Camera.Parameters parameters) {
        Size viewSize = this.mScannerCallback.getViewSize();
        double ratio = getRatio(viewSize.getWidth(), viewSize.getHeight());
        Size size = new Size(0, 0);
        double ratio2 = 0.0d;
        for (Camera.Size size2 : parameters.getSupportedPreviewSizes()) {
            double ratio3 = getRatio(size2.width, size2.height);
            if (size2.height * size2.width > size.getWidth() * size.getHeight() && (Math.abs(ratio2 - ratio) / ratio > 0.1d || Math.abs(ratio3 - ratio) / ratio <= 0.1d)) {
                size = new Size(size2.width, size2.height);
                ratio2 = getRatio(size2.width, size2.height);
            }
        }
        return size;
    }

    protected void decodeImage(BinaryBitmap binaryBitmap) {
        Result resultDecodeWithState;
        try {
            resultDecodeWithState = this.mReader.decodeWithState(binaryBitmap);
            this.mReader.reset();
        } catch (ReaderException unused) {
            this.mReader.reset();
            resultDecodeWithState = null;
        } catch (Throwable th) {
            this.mReader.reset();
            throw th;
        }
        if (resultDecodeWithState != null) {
            this.mScannerCallback.handleSuccessfulResult(resultDecodeWithState.getText());
        }
    }

    public boolean isDecodeTaskAlive() {
        return this.mDecodeTask != null;
    }
}
