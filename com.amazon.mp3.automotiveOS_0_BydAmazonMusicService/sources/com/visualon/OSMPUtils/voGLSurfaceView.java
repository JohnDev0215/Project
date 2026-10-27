package com.visualon.OSMPUtils;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.visualon.OSMPRender.voGLExtOesRenderWrapper;

/* JADX INFO: loaded from: classes7.dex */
public class voGLSurfaceView extends GLSurfaceView {
    voGLExtOesRenderWrapper mRenderer;

    public interface SurfaceCallBack {
        void onSurfaceChanged(int i, int i2);

        void onSurfaceCreated();
    }

    public voGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public voGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        voGLExtOesRenderWrapper voglextoesrenderwrapper = new voGLExtOesRenderWrapper();
        this.mRenderer = voglextoesrenderwrapper;
        setRenderer(voglextoesrenderwrapper);
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.mRenderer.getSurfaceTexture();
    }

    public void setSurfaceCallBack(SurfaceCallBack surfaceCallBack) {
        this.mRenderer.setSurfaceCallBack(surfaceCallBack);
    }
}
