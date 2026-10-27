package com.android.car.pm.blurredbackground;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.Matrix;
import android.os.Build;
import android.util.Log;
import android.util.Slog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import libcore.io.Streams;

/* JADX INFO: loaded from: classes3.dex */
public class GLHelper {
    private static final int SIZEOF_FLOAT = 4;
    private static final String TAG = "GLHelper";

    public static int createProgram(String vertexShaderSource, String fragmentShaderSource) {
        int vertexShader = compileShader(35633, vertexShaderSource);
        int fragmentShader = compileShader(35632, fragmentShaderSource);
        int programId = GLES30.glCreateProgram();
        checkGlErrors("glCreateProgram");
        GLES30.glAttachShader(programId, vertexShader);
        GLES30.glAttachShader(programId, fragmentShader);
        GLES30.glDeleteShader(vertexShader);
        checkGlErrors("glDeleteShader");
        GLES30.glDeleteShader(fragmentShader);
        checkGlErrors("glDeleteShader");
        GLES30.glLinkProgram(programId);
        checkGlErrors("glLinkProgram");
        return programId;
    }

    public static int createAndBindTextureObject(IntBuffer textureIdBuffer, int textureTarget) {
        GLES30.glGenTextures(1, textureIdBuffer);
        int textureId = textureIdBuffer.get(0);
        GLES30.glBindTexture(textureTarget, textureId);
        checkGlErrors("glBindTexture");
        GLES30.glTexParameterf(textureTarget, 10241, 9729.0f);
        GLES30.glTexParameterf(textureTarget, 10240, 9729.0f);
        GLES30.glTexParameteri(textureTarget, 10242, 33071);
        GLES30.glTexParameteri(textureTarget, 10243, 33071);
        return textureId;
    }

    public static int createAndBindFramebuffer(IntBuffer frameBuffer) {
        GLES30.glGenFramebuffers(1, frameBuffer);
        checkGlErrors("glGenFramebuffers");
        int frameBufferId = frameBuffer.get(0);
        GLES30.glBindFramebuffer(36160, frameBufferId);
        checkGlErrors("glBindFramebuffer");
        return frameBufferId;
    }

    public static String getShaderFromRaw(Context context, int id) {
        try {
            InputStream stream = context.getResources().openRawResource(id);
            return new String(Streams.readFully(new InputStreamReader(stream)));
        } catch (IOException e) {
            Log.e(TAG, "Failed to load shader");
            return null;
        }
    }

    public static FloatBuffer createFloatBuffer(float[] coords) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(coords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(coords);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public static float[] getIdentityMatrix() {
        float[] identityMatrix = new float[16];
        Matrix.setIdentityM(identityMatrix, 0);
        return identityMatrix;
    }

    public static boolean checkGlErrors(String func) {
        boolean hadError = false;
        while (true) {
            int error = GLES30.glGetError();
            if (error != 0) {
                if (Build.IS_ENG || Build.IS_USERDEBUG) {
                    Slog.e(TAG, func + " failed: error " + error, new Throwable());
                }
                hadError = true;
            } else {
                return hadError;
            }
        }
    }

    private static int compileShader(int shaderType, String shaderSource) {
        int shader = GLES30.glCreateShader(shaderType);
        GLES30.glShaderSource(shader, shaderSource);
        GLES30.glCompileShader(shader);
        checkGlErrors("glCompileShader");
        return shader;
    }
}
