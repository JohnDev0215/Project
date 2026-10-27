package org.chromium.content.browser;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.FrameLayout;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ContentViewRenderView extends FrameLayout {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CompositingSurfaceType mCompositingSurfaceType;
    protected ContentViewCore mContentViewCore;
    private boolean mFirstFrameReceived;
    private FirstRenderedFrameListener mFirstRenderedFrameListener;
    private long mNativeContentViewRenderView;
    private Surface mSurface;
    private SurfaceHolder.Callback mSurfaceCallback;
    private final SurfaceView mSurfaceView;
    private TextureView mTextureView;

    public enum CompositingSurfaceType {
        SURFACE_VIEW,
        TEXTURE_VIEW
    }

    public interface FirstRenderedFrameListener {
        void onFirstFrameReceived();
    }

    private static boolean isOpaque(int i) {
        return ((i >> 24) & 255) == 255;
    }

    private native void nativeDestroy(long j);

    private native long nativeInit(long j);

    private native void nativeSetCurrentContentViewCore(long j, long j2);

    private native void nativeSetOverlayVideoMode(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSurfaceChanged(long j, int i, int i2, int i3, Surface surface);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSurfaceCreated(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSurfaceDestroyed(long j);

    protected void onReadyToRender() {
    }

    private void initTextureView(Context context) {
        TextureView textureView = new TextureView(context);
        this.mTextureView = textureView;
        textureView.setBackgroundColor(-1);
        this.mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: org.chromium.content.browser.ContentViewRenderView.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                ContentViewRenderView.this.mSurface = new Surface(surfaceTexture);
                ContentViewRenderView contentViewRenderView = ContentViewRenderView.this;
                contentViewRenderView.nativeSurfaceCreated(contentViewRenderView.mNativeContentViewRenderView);
                onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
                ContentViewRenderView.this.onReadyToRender();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                ContentViewRenderView contentViewRenderView = ContentViewRenderView.this;
                contentViewRenderView.nativeSurfaceChanged(contentViewRenderView.mNativeContentViewRenderView, 1, i, i2, ContentViewRenderView.this.mSurface);
                if (ContentViewRenderView.this.mContentViewCore != null) {
                    ContentViewRenderView.this.mContentViewCore.onPhysicalBackingSizeChanged(i, i2);
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                ContentViewRenderView contentViewRenderView = ContentViewRenderView.this;
                contentViewRenderView.nativeSurfaceDestroyed(contentViewRenderView.mNativeContentViewRenderView);
                ContentViewRenderView.this.mSurface.release();
                ContentViewRenderView.this.mSurface = null;
                return true;
            }
        });
    }

    public ContentViewRenderView(Context context) {
        this(context, CompositingSurfaceType.SURFACE_VIEW);
    }

    public ContentViewRenderView(Context context, CompositingSurfaceType compositingSurfaceType) {
        super(context);
        this.mCompositingSurfaceType = compositingSurfaceType;
        if (compositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            initTextureView(context);
            addView(this.mTextureView, new FrameLayout.LayoutParams(-1, -1));
            this.mSurfaceView = null;
            this.mSurfaceCallback = null;
            return;
        }
        SurfaceView surfaceViewCreateSurfaceView = createSurfaceView(getContext());
        this.mSurfaceView = surfaceViewCreateSurfaceView;
        surfaceViewCreateSurfaceView.setZOrderMediaOverlay(true);
        setSurfaceViewBackgroundColor(-1);
        addView(surfaceViewCreateSurfaceView, new FrameLayout.LayoutParams(-1, -1));
        surfaceViewCreateSurfaceView.setVisibility(8);
    }

    public void onNativeLibraryLoaded(WindowAndroid windowAndroid) {
        this.mNativeContentViewRenderView = nativeInit(windowAndroid.getNativePointer());
        if (this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            return;
        }
        this.mSurfaceCallback = new SurfaceHolder.Callback() { // from class: org.chromium.content.browser.ContentViewRenderView.2
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                ContentViewRenderView contentViewRenderView = ContentViewRenderView.this;
                contentViewRenderView.nativeSurfaceChanged(contentViewRenderView.mNativeContentViewRenderView, i, i2, i3, surfaceHolder.getSurface());
                if (ContentViewRenderView.this.mContentViewCore != null) {
                    ContentViewRenderView.this.mContentViewCore.onPhysicalBackingSizeChanged(i2, i3);
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                ContentViewRenderView contentViewRenderView = ContentViewRenderView.this;
                contentViewRenderView.nativeSurfaceCreated(contentViewRenderView.mNativeContentViewRenderView);
                ContentViewRenderView.this.onReadyToRender();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                ContentViewRenderView contentViewRenderView = ContentViewRenderView.this;
                contentViewRenderView.nativeSurfaceDestroyed(contentViewRenderView.mNativeContentViewRenderView);
            }
        };
        this.mSurfaceView.getHolder().addCallback(this.mSurfaceCallback);
        this.mSurfaceView.setVisibility(0);
    }

    public void setSurfaceViewBackgroundColor(int i) {
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null) {
            surfaceView.setBackgroundColor(i);
            return;
        }
        TextureView textureView = this.mTextureView;
        if (textureView != null) {
            textureView.setOpaque(isOpaque(i));
            this.mTextureView.setBackgroundColor(i);
        }
    }

    public SurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    public void destroy() {
        if (this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            this.mTextureView.setSurfaceTextureListener(null);
            Surface surface = this.mSurface;
            if (surface != null) {
                surface.release();
                this.mSurface = null;
            }
        } else {
            this.mSurfaceView.getHolder().removeCallback(this.mSurfaceCallback);
        }
        nativeDestroy(this.mNativeContentViewRenderView);
        this.mNativeContentViewRenderView = 0L;
    }

    public void setCurrentContentViewCore(ContentViewCore contentViewCore) {
        this.mContentViewCore = contentViewCore;
        if (contentViewCore != null) {
            contentViewCore.onPhysicalBackingSizeChanged(getWidth(), getHeight());
            nativeSetCurrentContentViewCore(this.mNativeContentViewRenderView, this.mContentViewCore.getNativeContentViewCore());
        } else {
            nativeSetCurrentContentViewCore(this.mNativeContentViewRenderView, 0L);
        }
    }

    protected SurfaceView createSurfaceView(Context context) {
        return new SurfaceView(context);
    }

    public void registerFirstRenderedFrameListener(FirstRenderedFrameListener firstRenderedFrameListener) {
        this.mFirstRenderedFrameListener = firstRenderedFrameListener;
        if (!this.mFirstFrameReceived || firstRenderedFrameListener == null) {
            return;
        }
        firstRenderedFrameListener.onFirstFrameReceived();
    }

    public boolean isInitialized() {
        return (this.mSurfaceView.getHolder().getSurface() == null && this.mSurface == null) ? false : true;
    }

    public void setZOrderOnTop(boolean z) {
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView == null) {
            return;
        }
        surfaceView.setZOrderOnTop(z);
    }

    public void setOverlayVideoMode(boolean z) {
        if (this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            nativeSetOverlayVideoMode(this.mNativeContentViewRenderView, z);
        } else {
            this.mSurfaceView.getHolder().setFormat(z ? -3 : -1);
            nativeSetOverlayVideoMode(this.mNativeContentViewRenderView, z);
        }
    }

    private void onSwapBuffersCompleted() {
        ContentViewCore contentViewCore;
        if (!this.mFirstFrameReceived && (contentViewCore = this.mContentViewCore) != null && contentViewCore.getWebContents().isReady()) {
            this.mFirstFrameReceived = true;
            FirstRenderedFrameListener firstRenderedFrameListener = this.mFirstRenderedFrameListener;
            if (firstRenderedFrameListener != null) {
                firstRenderedFrameListener.onFirstFrameReceived();
            }
        }
        if (this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW || this.mSurfaceView.getBackground() == null) {
            return;
        }
        post(new Runnable() { // from class: org.chromium.content.browser.ContentViewRenderView.3
            @Override // java.lang.Runnable
            public void run() {
                ContentViewRenderView.this.mSurfaceView.setBackgroundResource(0);
            }
        });
    }
}
