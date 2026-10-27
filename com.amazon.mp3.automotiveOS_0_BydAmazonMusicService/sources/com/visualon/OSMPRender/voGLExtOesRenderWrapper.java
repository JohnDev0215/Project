package com.visualon.OSMPRender;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import androidx.work.Data;
import com.visualon.OSMPUtils.voGLSurfaceView;
import com.visualon.OSMPUtils.voLog;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes7.dex */
@SuppressLint({"NewApi"})
public class voGLExtOesRenderWrapper implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final int GL_TEXTURE_EXTERNAL_OES = 36197;
    private static final String TAG = "EXTERNAL_OESRenderer";
    private static final int VERTICES_DATA_POS_OFFSET = 0;
    private static final int VERTICES_DATA_STRIDE_BYTES = 20;
    private static final int VERTICES_DATA_UV_OFFSET = 3;
    private int mProgram;
    private SurfaceTexture mSurface;
    private int mTextureID;
    private FloatBuffer mVertices;
    private final float[] mVerticesData;
    private int maPositionHandle;
    private int maTextureHandle;
    private int muMVPMatrixHandle;
    private int muSTMatrixHandle;
    private voGLSurfaceView.SurfaceCallBack surfaceCallBack;
    private final String mVertexShader = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private final String mFragmentShader = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private float[] mMVPMatrix = new float[16];
    private float[] mProjMatrix = new float[16];
    private float[] mMMatrix = new float[16];
    private float[] mVMatrix = new float[16];
    private float[] mSTMatrix = new float[16];
    private boolean updateSurface = false;

    public void setSurfaceCallBack(voGLSurfaceView.SurfaceCallBack surfaceCallBack) {
        this.surfaceCallBack = surfaceCallBack;
    }

    public voGLExtOesRenderWrapper() {
        float[] fArr = {-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
        this.mVerticesData = fArr;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mVertices = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        Matrix.setIdentityM(this.mSTMatrix, 0);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        synchronized (this) {
            if (this.updateSurface) {
                this.mSurface.updateTexImage();
                this.mSurface.getTransformMatrix(this.mSTMatrix);
                this.updateSurface = false;
            }
        }
        GLES20.glClear(16640);
        GLES20.glUseProgram(this.mProgram);
        checkGlError("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, this.mTextureID);
        this.mVertices.position(0);
        GLES20.glVertexAttribPointer(this.maPositionHandle, 3, 5126, false, 20, (Buffer) this.mVertices);
        checkGlError("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.maPositionHandle);
        checkGlError("glEnableVertexAttribArray maPositionHandle");
        this.mVertices.position(3);
        GLES20.glVertexAttribPointer(this.maTextureHandle, 3, 5126, false, 20, (Buffer) this.mVertices);
        checkGlError("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.maTextureHandle);
        checkGlError("glEnableVertexAttribArray maTextureHandle");
        Matrix.setIdentityM(this.mMMatrix, 0);
        Matrix.multiplyMM(this.mMVPMatrix, 0, this.mVMatrix, 0, this.mMMatrix, 0);
        float[] fArr = this.mMVPMatrix;
        Matrix.multiplyMM(fArr, 0, this.mProjMatrix, 0, fArr, 0);
        GLES20.glUniformMatrix4fv(this.muMVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(this.muSTMatrixHandle, 1, false, this.mSTMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
        checkGlError("glDrawArrays");
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glViewport(0, 0, i, i2);
        Matrix.frustumM(this.mProjMatrix, 0, -1.0f, 1.0f, -1.0f, 1.0f, 3.0f, 7.0f);
        voGLSurfaceView.SurfaceCallBack surfaceCallBack = this.surfaceCallBack;
        if (surfaceCallBack != null) {
            surfaceCallBack.onSurfaceChanged(i, i2);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        int iCreateProgram = createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        this.mProgram = iCreateProgram;
        if (iCreateProgram == 0) {
            return;
        }
        this.maPositionHandle = GLES20.glGetAttribLocation(iCreateProgram, "aPosition");
        checkGlError("glGetAttribLocation aPosition");
        if (this.maPositionHandle == -1) {
            throw new RuntimeException("Could not get attrib location for aPosition");
        }
        this.maTextureHandle = GLES20.glGetAttribLocation(this.mProgram, "aTextureCoord");
        checkGlError("glGetAttribLocation aTextureCoord");
        if (this.maTextureHandle == -1) {
            throw new RuntimeException("Could not get attrib location for aTextureCoord");
        }
        this.muMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix");
        checkGlError("glGetUniformLocation uMVPMatrix");
        if (this.muMVPMatrixHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uMVPMatrix");
        }
        this.muSTMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uSTMatrix");
        checkGlError("glGetUniformLocation uSTMatrix");
        if (this.muSTMatrixHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uSTMatrix");
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        this.mTextureID = i;
        GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, i);
        checkGlError("glBindTexture mTextureID");
        GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, 10241, 9728.0f);
        GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, Data.MAX_DATA_BYTES, 9729.0f);
        GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10242, 33071);
        GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10243, 33071);
        checkGlError("glTexParameteri mTextureID");
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureID);
        this.mSurface = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        Matrix.setLookAtM(this.mVMatrix, 0, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        synchronized (this) {
            this.updateSurface = false;
        }
        voGLSurfaceView.SurfaceCallBack surfaceCallBack = this.surfaceCallBack;
        if (surfaceCallBack != null) {
            surfaceCallBack.onSurfaceCreated();
        }
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.updateSurface = true;
    }

    private int loadShader(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        if (iGlCreateShader == 0) {
            return iGlCreateShader;
        }
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        Log.e(TAG, "Could not compile shader " + i + ":");
        Log.e(TAG, GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        return 0;
    }

    private int createProgram(String str, String str2) {
        int iLoadShader;
        int iLoadShader2 = loadShader(35633, str);
        if (iLoadShader2 == 0 || (iLoadShader = loadShader(35632, str2)) == 0) {
            return 0;
        }
        int iGlCreateProgram = GLES20.glCreateProgram();
        if (iGlCreateProgram != 0) {
            GLES20.glAttachShader(iGlCreateProgram, iLoadShader2);
            checkGlError("glAttachShader");
            GLES20.glAttachShader(iGlCreateProgram, iLoadShader);
            checkGlError("glAttachShader");
            GLES20.glLinkProgram(iGlCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
            if (iArr[0] != 1) {
                Log.e(TAG, "Could not link program: ");
                Log.e(TAG, GLES20.glGetProgramInfoLog(iGlCreateProgram));
                GLES20.glDeleteProgram(iGlCreateProgram);
                return 0;
            }
        }
        return iGlCreateProgram;
    }

    private void checkGlError(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError == 0) {
            return;
        }
        voLog.e(TAG, str + ": glError " + iGlGetError, new Object[0]);
        throw new RuntimeException(str + ": glError " + iGlGetError);
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.mSurface;
    }
}
