package com.android.car.pm.blurredbackground;

import android.graphics.Rect;
import android.opengl.GLES30;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class BlurTextureProgram {
    private static final float BLUR_RADIUS = 40.0f;
    private static final int NUM_COORDS_PER_VERTEX = 2;
    private static final int SIZEOF_FLOAT = 4;
    private final int mAHorizontalPositionLoc;
    private final int mAHorizontalTextureCoordLoc;
    private final int mAVerticalPositionLoc;
    private final int mAVerticalTextureCoordLoc;
    private int mFirstPassTextureId;
    private int mFrameBufferId;
    private final int mHeight;
    private final int mHorizontalBlurProgram;
    private final String mHorizontalBlurShader;
    private final FloatBuffer mResolutionBuffer;
    private final IntBuffer mScreenshotTextureBuffer;
    private final int mScreenshotTextureId;
    private final float[] mTexMatrix;
    private final int mUHorizontalMVPMatrixLoc;
    private final int mUHorizontalRadiusLoc;
    private final int mUHorizontalResolutionLoc;
    private final int mUHorizontalTexMatrixLoc;
    private final int mUVerticalMVPMatrixLoc;
    private final int mUVerticalRadiusLoc;
    private final int mUVerticalResolutionLoc;
    private final int mUVerticalTexMatrixLoc;
    private final String mVertexShader;
    private final int mVerticalBlurProgram;
    private final String mVerticalBlurShader;
    private final int mWidth;
    private static final float[] FRAME_COORDS = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] TEXTURE_COORDS = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] INVERTED_TEXTURE_COORDS = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private final FloatBuffer mVertexBuffer = GLHelper.createFloatBuffer(FRAME_COORDS);
    private final FloatBuffer mTexBuffer = GLHelper.createFloatBuffer(TEXTURE_COORDS);
    private final FloatBuffer mInvertedTexBuffer = GLHelper.createFloatBuffer(INVERTED_TEXTURE_COORDS);
    private final IntBuffer mFrameBuffer = IntBuffer.allocate(1);
    private final IntBuffer mFirstPassTextureBuffer = IntBuffer.allocate(1);

    BlurTextureProgram(IntBuffer screenshotTextureBuffer, float[] texMatrix, String vertexShader, String horizontalBlurShader, String verticalBlurShader, Rect windowRect) {
        this.mVertexShader = vertexShader;
        this.mHorizontalBlurShader = horizontalBlurShader;
        this.mVerticalBlurShader = verticalBlurShader;
        this.mScreenshotTextureBuffer = screenshotTextureBuffer;
        this.mScreenshotTextureId = screenshotTextureBuffer.get(0);
        this.mTexMatrix = texMatrix;
        this.mHorizontalBlurProgram = GLHelper.createProgram(this.mVertexShader, this.mHorizontalBlurShader);
        this.mVerticalBlurProgram = GLHelper.createProgram(this.mVertexShader, this.mVerticalBlurShader);
        this.mWidth = windowRect.width();
        int iHeight = windowRect.height();
        this.mHeight = iHeight;
        this.mResolutionBuffer = FloatBuffer.wrap(new float[]{this.mWidth, iHeight, 1.0f});
        this.mUHorizontalMVPMatrixLoc = GLES30.glGetUniformLocation(this.mHorizontalBlurProgram, "uMVPMatrix");
        this.mUHorizontalTexMatrixLoc = GLES30.glGetUniformLocation(this.mHorizontalBlurProgram, "uTexMatrix");
        this.mUHorizontalResolutionLoc = GLES30.glGetUniformLocation(this.mHorizontalBlurProgram, "uResolution");
        this.mUHorizontalRadiusLoc = GLES30.glGetUniformLocation(this.mHorizontalBlurProgram, "uRadius");
        this.mAHorizontalPositionLoc = GLES30.glGetAttribLocation(this.mHorizontalBlurProgram, "aPosition");
        this.mAHorizontalTextureCoordLoc = GLES30.glGetAttribLocation(this.mHorizontalBlurProgram, "aTextureCoord");
        this.mUVerticalMVPMatrixLoc = GLES30.glGetUniformLocation(this.mVerticalBlurProgram, "uMVPMatrix");
        this.mUVerticalTexMatrixLoc = GLES30.glGetUniformLocation(this.mVerticalBlurProgram, "uTexMatrix");
        this.mUVerticalResolutionLoc = GLES30.glGetUniformLocation(this.mVerticalBlurProgram, "uResolution");
        this.mUVerticalRadiusLoc = GLES30.glGetUniformLocation(this.mVerticalBlurProgram, "uRadius");
        this.mAVerticalPositionLoc = GLES30.glGetAttribLocation(this.mVerticalBlurProgram, "aPosition");
        this.mAVerticalTextureCoordLoc = GLES30.glGetAttribLocation(this.mVerticalBlurProgram, "aTextureCoord");
    }

    public void render() {
        setupProgram(this.mHorizontalBlurProgram, this.mScreenshotTextureId, 36197);
        setHorizontalUniformsAndAttributes();
        this.mFrameBufferId = GLHelper.createAndBindFramebuffer(this.mFrameBuffer);
        int iCreateAndBindTextureObject = GLHelper.createAndBindTextureObject(this.mFirstPassTextureBuffer, 3553);
        this.mFirstPassTextureId = iCreateAndBindTextureObject;
        setupTextureForFramebuffer(iCreateAndBindTextureObject);
        assertValidFramebufferStatus();
        renderToFramebuffer(this.mFrameBufferId);
        setupProgram(this.mVerticalBlurProgram, this.mFirstPassTextureId, 3553);
        setVerticalUniformsAndAttributes();
        renderToSurface();
        cleanupResources();
    }

    public void cleanupResources() {
        deleteFramebufferTexture();
        deleteFrameBuffer();
        deletePrograms();
        GLES30.glFlush();
    }

    private void setupTextureForFramebuffer(int textureId) {
        GLES30.glTexImage2D(3553, 0, 6407, this.mWidth, this.mHeight, 0, 6407, 5121, null);
        GLHelper.checkGlErrors("glTexImage2D");
        GLES30.glFramebufferTexture2D(36160, 36064, 3553, textureId, 0);
        GLHelper.checkGlErrors("glFramebufferTexture2D");
    }

    private void deleteFramebufferTexture() {
        GLES30.glDeleteTextures(this.mFirstPassTextureBuffer.capacity(), this.mFirstPassTextureBuffer);
        GLHelper.checkGlErrors("glDeleteTextures");
    }

    private void deleteFrameBuffer() {
        GLES30.glDeleteBuffers(1, this.mFrameBuffer);
        GLHelper.checkGlErrors("glDeleteBuffers");
    }

    private void deletePrograms() {
        GLES30.glDeleteProgram(this.mHorizontalBlurProgram);
        GLHelper.checkGlErrors("glDeleteProgram");
        GLES30.glDeleteProgram(this.mVerticalBlurProgram);
        GLHelper.checkGlErrors("glDeleteProgram");
    }

    private void setHorizontalUniformsAndAttributes() {
        GLES30.glUniformMatrix4fv(this.mUHorizontalMVPMatrixLoc, 1, false, GLHelper.getIdentityMatrix(), 0);
        GLES30.glUniformMatrix4fv(this.mUHorizontalTexMatrixLoc, 1, false, this.mTexMatrix, 0);
        GLES30.glUniform3fv(this.mUHorizontalResolutionLoc, 1, this.mResolutionBuffer);
        GLES30.glUniform1f(this.mUHorizontalRadiusLoc, BLUR_RADIUS);
        GLES30.glEnableVertexAttribArray(this.mAHorizontalPositionLoc);
        GLES30.glVertexAttribPointer(this.mAHorizontalPositionLoc, 2, 5126, false, 8, (Buffer) this.mVertexBuffer);
        GLES30.glEnableVertexAttribArray(this.mAHorizontalTextureCoordLoc);
        GLES30.glVertexAttribPointer(this.mAHorizontalTextureCoordLoc, 2, 5126, false, 8, (Buffer) this.mTexBuffer);
    }

    private void setVerticalUniformsAndAttributes() {
        GLES30.glUniformMatrix4fv(this.mUVerticalMVPMatrixLoc, 1, false, GLHelper.getIdentityMatrix(), 0);
        GLES30.glUniformMatrix4fv(this.mUVerticalTexMatrixLoc, 1, false, this.mTexMatrix, 0);
        GLES30.glUniform3fv(this.mUVerticalResolutionLoc, 1, this.mResolutionBuffer);
        GLES30.glUniform1f(this.mUVerticalRadiusLoc, BLUR_RADIUS);
        GLES30.glEnableVertexAttribArray(this.mAVerticalPositionLoc);
        GLES30.glVertexAttribPointer(this.mAVerticalPositionLoc, 2, 5126, false, 8, (Buffer) this.mVertexBuffer);
        GLES30.glEnableVertexAttribArray(this.mAVerticalTextureCoordLoc);
        GLES30.glVertexAttribPointer(this.mAVerticalTextureCoordLoc, 2, 5126, false, 8, (Buffer) this.mInvertedTexBuffer);
    }

    private void setupProgram(int programId, int textureId, int textureTarget) {
        GLES30.glUseProgram(programId);
        GLHelper.checkGlErrors("glUseProgram");
        GLES30.glActiveTexture(33984);
        GLHelper.checkGlErrors("glActiveTexture");
        GLES30.glBindTexture(textureTarget, textureId);
        GLHelper.checkGlErrors("glBindTexture");
    }

    private void renderToFramebuffer(int framebufferId) {
        GLES30.glClear(16384);
        GLHelper.checkGlErrors("glClear");
        GLES30.glBindFramebuffer(36160, framebufferId);
        GLHelper.checkGlErrors("glBindFramebuffer");
        GLES30.glViewport(0, 0, this.mWidth, this.mHeight);
        GLHelper.checkGlErrors("glViewport");
        GLES30.glDrawArrays(5, 0, FRAME_COORDS.length / 2);
        GLHelper.checkGlErrors("glDrawArrays");
        GLES30.glBindFramebuffer(36160, 0);
    }

    private void renderToSurface() {
        GLES30.glBindFramebuffer(36160, 0);
        GLHelper.checkGlErrors("glDrawArrays");
        GLES30.glClear(16384);
        GLHelper.checkGlErrors("glDrawArrays");
        GLES30.glDrawArrays(5, 0, FRAME_COORDS.length / 2);
        GLHelper.checkGlErrors("glDrawArrays");
    }

    private void assertValidFramebufferStatus() {
        if (GLES30.glCheckFramebufferStatus(36160) != 36053) {
            throw new RuntimeException("Failed to attach Framebuffer. Framebuffer status code is: " + GLES30.glCheckFramebufferStatus(36160));
        }
    }
}
