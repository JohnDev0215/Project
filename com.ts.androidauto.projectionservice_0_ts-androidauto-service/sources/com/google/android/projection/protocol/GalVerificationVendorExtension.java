package com.google.android.projection.protocol;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.google.android.projection.proto.Protos;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class GalVerificationVendorExtension implements CarServiceProvider, GalVerificationVendorExtensionCallbacks {
    private static final String TAG = "AHU.GVVE";
    private final AudioFocusManager mAudioFocusManager;
    private final InputSource mInputSource;
    private long mNativeGalVerificationVendorExtension;
    private final SensorSource mSensorSource;
    private final SurfaceHolder.Callback mSurfaceCallback = new SurfaceHolder.Callback() { // from class: com.google.android.projection.protocol.GalVerificationVendorExtension.1
        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder holder) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public synchronized void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            GalVerificationVendorExtension.this.mSurfaceWidth = width;
            GalVerificationVendorExtension.this.mSurfaceHeight = height;
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    };
    private int mSurfaceHeight;
    private final SurfaceView mSurfaceView;
    private int mSurfaceWidth;
    private final VideoSink mVideoSink;

    public interface AudioFocusManager {
        void disablePhoneFocus();

        void enablePhoneFocus();

        void setTransientLossState(int i);
    }

    private native boolean nativeCloseChannel();

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native void nativeSend(byte[] bArr);

    private native void nativeShutdown();

    public GalVerificationVendorExtension(SensorSource sensorSource, VideoSink videoSink, InputSource inputSource, SurfaceView surfaceView, AudioFocusManager audioFocusManager) {
        this.mSensorSource = sensorSource;
        this.mVideoSink = videoSink;
        this.mInputSource = inputSource;
        this.mSurfaceView = surfaceView;
        this.mAudioFocusManager = audioFocusManager;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        SurfaceView surfaceView;
        if (nativeInit(serviceId, nativeGalReceiver) == 0 && (surfaceView = this.mSurfaceView) != null) {
            surfaceView.getHolder().addCallback(this.mSurfaceCallback);
            this.mSurfaceWidth = this.mSurfaceView.getWidth();
            this.mSurfaceHeight = this.mSurfaceView.getHeight();
            return true;
        }
        return false;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null) {
            surfaceView.getHolder().removeCallback(this.mSurfaceCallback);
        }
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeGalVerificationVendorExtension;
    }

    public void closeChannel() {
        nativeCloseChannel();
    }

    @Override // com.google.android.projection.protocol.GalVerificationVendorExtensionCallbacks
    public int injectSensorDataCallback(byte[] sensorData) {
        try {
            Protos.GalVerificationSetSensor parsedSensorData = Protos.GalVerificationSetSensor.parseFrom(sensorData);
            this.mSensorSource.injectForTest(parsedSensorData.getSensors());
            return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
        } catch (InvalidProtocolBufferException e) {
            Log.w("AHU.GVVE", "Error parsing sensor data of length: " + sensorData.length);
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
    }

    public void overrideNightMode(Boolean isNight) {
        this.mSensorSource.overrideNightModeData(isNight);
    }

    @Override // com.google.android.projection.protocol.GalVerificationVendorExtensionCallbacks
    public int setVideoFocusCallback(byte[] videoFocus) {
        try {
            Protos.GalVerificationVideoFocus parsedVideoFocus = Protos.GalVerificationVideoFocus.parseFrom(videoFocus);
            this.mVideoSink.setVideoFocus(parsedVideoFocus.getVideoFocusMode().getNumber(), -1, true);
            return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
        } catch (InvalidProtocolBufferException e) {
            Log.w("AHU.GVVE", "Error parsing sensor data of length: " + videoFocus.length);
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
    }

    @Override // com.google.android.projection.protocol.GalVerificationVendorExtensionCallbacks
    public int injectInputCallback(byte[] inputData) {
        try {
            Protos.GalVerificationInjectInput parsedInputData = Protos.GalVerificationInjectInput.parseFrom(inputData);
            this.mInputSource.injectForTest(parsedInputData.getInput());
            return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
        } catch (InvalidProtocolBufferException e) {
            Log.w("AHU.GVVE", "Error parsing input data of length: " + inputData.length);
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
    }

    @Override // com.google.android.projection.protocol.GalVerificationVendorExtensionCallbacks
    public int requestScreenCaptureCallback() {
        requestScreenCapture();
        return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
    }

    public void requestScreenCapture() {
        View v = this.mSurfaceView;
        if (v == null) {
            Log.e("AHU.GVVE", "Error taking screen capture. The SurfaceView is null.");
            return;
        }
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        try {
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            Log.w("AHU.GVVE", "Error flushing or closing screen capture output stream.");
        }
        ByteString outArray = ByteString.copyFrom(outStream.toByteArray());
        Protos.GalVerificationScreenCaptureResponse screenCaptureResponse = (Protos.GalVerificationScreenCaptureResponse) Protos.GalVerificationScreenCaptureResponse.newBuilder().setScreenCapture(outArray).build();
        nativeSend(screenCaptureResponse.toByteArray());
    }

    @Override // com.google.android.projection.protocol.GalVerificationVendorExtensionCallbacks
    public int requestDisplayInformationCallback() {
        Protos.GalVerificationDisplayInformationResponse displayInfoResponse = (Protos.GalVerificationDisplayInformationResponse) Protos.GalVerificationDisplayInformationResponse.newBuilder().setNativeWidth(this.mSurfaceWidth).setNativeHeight(this.mSurfaceHeight).build();
        nativeSend(displayInfoResponse.toByteArray());
        return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
    }

    @Override // com.google.android.projection.protocol.GalVerificationVendorExtensionCallbacks
    public int requestAudioFocusChangeCallback(byte[] inputData) {
        try {
            Protos.GalVerificationAudioFocus audioFocus = Protos.GalVerificationAudioFocus.parseFrom(inputData);
            if (this.mAudioFocusManager == null) {
                Log.w("AHU.GVVE", "No AudioFocusManager, cannot force phone focus.");
            } else {
                int state = audioFocus.getAudioFocusState().getNumber();
                if (state == GalReceiver.AUDIO_FOCUS_STATE_LOSS_TRANSIENT || state == GalReceiver.AUDIO_FOCUS_STATE_LOSS_TRANSIENT_CAN_DUCK) {
                    this.mAudioFocusManager.setTransientLossState(state);
                } else if (state == GalReceiver.AUDIO_FOCUS_STATE_LOSS) {
                    this.mAudioFocusManager.disablePhoneFocus();
                } else {
                    if (state != GalReceiver.AUDIO_FOCUS_GAIN) {
                        Log.e("AHU.GVVE", String.format("Unknown Audio focus state requested through debug channel: %d", Integer.valueOf(state)));
                        return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
                    }
                    this.mAudioFocusManager.enablePhoneFocus();
                }
            }
            return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
        } catch (InvalidProtocolBufferException e) {
            Log.w("AHU.GVVE", "Error parsing input data of length: " + inputData.length);
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
    }
}
