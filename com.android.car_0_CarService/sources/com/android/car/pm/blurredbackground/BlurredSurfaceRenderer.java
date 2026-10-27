package com.android.car.pm.blurredbackground;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.os.IBinder;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceControl;
import com.android.car.R;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public class BlurredSurfaceRenderer implements GLSurfaceView.Renderer {
    private static final int NUM_INDICES_TO_RENDER = 4;
    private static final String TAG = "BlurredSurfaceRenderer";
    private final String mHorizontalBlurShader;
    private BlurTextureProgram mProgram;
    private int mScreenshotTextureId;
    private final boolean mShadersLoadedSuccessfully;
    private final boolean mShouldRenderBlurred;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private final String mVertexShader;
    private final String mVerticalBlurShader;
    private final Rect mWindowRect;
    private final IntBuffer mScreenshotTextureBuffer = IntBuffer.allocate(1);
    private final float[] mTexMatrix = new float[16];
    private boolean mIsScreenShotCaptured = false;

    public BlurredSurfaceRenderer(Context context, Rect windowRect, boolean shouldRenderBlurred) {
        this.mShouldRenderBlurred = shouldRenderBlurred;
        this.mVertexShader = GLHelper.getShaderFromRaw(context, R.raw.vertex_shader);
        this.mHorizontalBlurShader = GLHelper.getShaderFromRaw(context, R.raw.horizontal_blur_fragment_shader);
        String shaderFromRaw = GLHelper.getShaderFromRaw(context, R.raw.vertical_blur_fragment_shader);
        this.mVerticalBlurShader = shaderFromRaw;
        this.mShadersLoadedSuccessfully = (this.mVertexShader == null || this.mHorizontalBlurShader == null || shaderFromRaw == null) ? false : true;
        this.mWindowRect = windowRect;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        this.mScreenshotTextureId = GLHelper.createAndBindTextureObject(this.mScreenshotTextureBuffer, 36197);
        this.mSurfaceTexture = new SurfaceTexture(this.mScreenshotTextureId);
        this.mSurface = new Surface(this.mSurfaceTexture);
        if (this.mShouldRenderBlurred) {
            this.mIsScreenShotCaptured = captureScreenshot();
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl, int width, int height) {
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl) {
        if (shouldDrawFrame()) {
            BlurTextureProgram blurTextureProgram = new BlurTextureProgram(this.mScreenshotTextureBuffer, this.mTexMatrix, this.mVertexShader, this.mHorizontalBlurShader, this.mVerticalBlurShader, this.mWindowRect);
            this.mProgram = blurTextureProgram;
            blurTextureProgram.render();
        } else {
            logWillNotRenderBlurredMsg();
            renderTransparent();
        }
    }

    private void renderTransparent() {
        GLES30.glClear(16384);
        GLES30.glDrawArrays(5, 0, 4);
    }

    public void onPause() {
        BlurTextureProgram blurTextureProgram = this.mProgram;
        if (blurTextureProgram != null) {
            blurTextureProgram.cleanupResources();
        }
        deleteScreenshotTexture();
    }

    private boolean captureScreenshot() {
        boolean isScreenshotCaptured = false;
        try {
            IBinder token = SurfaceControl.getInternalDisplayToken();
            if (token == null) {
                Log.e(TAG, "Could not find display token for screenshot. Will not capture screenshot");
            } else {
                SurfaceControl.screenshot(token, this.mSurface, this.mWindowRect, this.mWindowRect.width(), this.mWindowRect.height(), false, 0);
                this.mSurfaceTexture.updateTexImage();
                this.mSurfaceTexture.getTransformMatrix(this.mTexMatrix);
                isScreenshotCaptured = true;
            }
            return isScreenshotCaptured;
        } finally {
            this.mSurface.release();
            this.mSurfaceTexture.release();
        }
    }

    private void deleteScreenshotTexture() {
        GLES30.glDeleteTextures(this.mScreenshotTextureBuffer.capacity(), this.mScreenshotTextureBuffer);
        GLHelper.checkGlErrors("glDeleteTextures");
        this.mIsScreenShotCaptured = false;
    }

    private void logWillNotRenderBlurredMsg() {
        if (!this.mIsScreenShotCaptured) {
            Log.e(TAG, "Screenshot was not captured. Will not render blurred surface");
        }
        if (!this.mShadersLoadedSuccessfully) {
            Log.e(TAG, "Shaders were not loaded successfully. Will not render blurred surface");
        }
    }

    private boolean shouldDrawFrame() {
        return this.mIsScreenShotCaptured && this.mShadersLoadedSuccessfully && this.mShouldRenderBlurred;
    }
}
