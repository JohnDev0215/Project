package com.android.systemui.glwallpaper;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
class ImageGLProgram {
    private static final String TAG = "ImageGLProgram";
    private Context mContext;
    private int mProgramHandle;

    ImageGLProgram(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private int loadShaderProgram(int vertexId, int fragmentId) {
        return getProgramHandle(getShaderHandle(35633, getShaderResource(vertexId)), getShaderHandle(35632, getShaderResource(fragmentId)));
    }

    private String getShaderResource(int shaderId) {
        Resources resources = this.mContext.getResources();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resources.openRawResource(shaderId)));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line).append(BydAdbHelper.COMMAND_LINE_END);
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            bufferedReader.close();
        } catch (Resources.NotFoundException | IOException e) {
            Log.d(TAG, "Can not read the shader source", e);
            sb = null;
        }
        return sb == null ? "" : sb.toString();
    }

    private int getShaderHandle(int type, String src) {
        int iGlCreateShader = GLES20.glCreateShader(type);
        if (iGlCreateShader == 0) {
            Log.d(TAG, "Create shader failed, type=" + type);
            return 0;
        }
        GLES20.glShaderSource(iGlCreateShader, src);
        GLES20.glCompileShader(iGlCreateShader);
        return iGlCreateShader;
    }

    private int getProgramHandle(int vertexHandle, int fragmentHandle) {
        int iGlCreateProgram = GLES20.glCreateProgram();
        if (iGlCreateProgram == 0) {
            Log.d(TAG, "Can not create OpenGL ES program");
            return 0;
        }
        GLES20.glAttachShader(iGlCreateProgram, vertexHandle);
        GLES20.glAttachShader(iGlCreateProgram, fragmentHandle);
        GLES20.glLinkProgram(iGlCreateProgram);
        return iGlCreateProgram;
    }

    boolean useGLProgram(int vertexResId, int fragmentResId) {
        int iLoadShaderProgram = loadShaderProgram(vertexResId, fragmentResId);
        this.mProgramHandle = iLoadShaderProgram;
        GLES20.glUseProgram(iLoadShaderProgram);
        return true;
    }

    int getAttributeHandle(String name) {
        return GLES20.glGetAttribLocation(this.mProgramHandle, name);
    }

    int getUniformHandle(String name) {
        return GLES20.glGetUniformLocation(this.mProgramHandle, name);
    }
}
