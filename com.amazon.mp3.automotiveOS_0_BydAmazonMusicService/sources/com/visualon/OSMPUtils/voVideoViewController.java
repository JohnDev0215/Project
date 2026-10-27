package com.visualon.OSMPUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.visualon.OSMPRender.voGLRenderWrapper;
import com.visualon.OSMPSensor.voSphericalVideoView;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes7.dex */
public class voVideoViewController implements voGLRenderWrapper.onGLRenderListener {
    private static final int DEST_DIFF = 2;
    private static final int DEST_NEAR = 1;
    private static final int DEST_NO_CHANGE = 0;
    private static final String TAG = "@@@voVideoViewController";
    private static final int TIME_TRY_LOCK = 300;
    private static final int messageAddSecondView = -252706808;
    private static final int messageCleanUI = -252706804;
    private static final int messageNotifyUpdateVideoDestBoxFinish = -252706801;
    private static final int messageSetRenderType = -252706807;
    private static final int messageSetViewSize = -252706803;
    private static final int messageUpdateDisplaySize = -252706805;
    ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    private Context mContext;
    private voOSRect mDestRect;
    private EventHandler mEventHandler;
    private int mHeightVideo;
    private int mLastSetViewHeight;
    private int mLastSetViewWidth;
    private voLicenseDisplayRender mLicDisplayRender;
    private int mScreenHeight;
    private int mScreenWidth;
    private View mSecondView;
    private SurfaceHolder mSurfaceHolder;
    private View mView;
    private onViewChangeListener mViewChangeListener;
    private int mWidthVideo;
    private voSurfaceCallBack m_cbSurfaceHolder;
    private boolean mbEnforceSWVideoScaling;
    private boolean mbKeepAspectRatio;
    private final ReentrantLock mLock = new ReentrantLock();
    private int mZoomMode = 1;
    private int mRenderType = 1;
    private int mUpdateVideoDestIndex = 0;
    private boolean mWaitingSurfaceChange = false;
    private long lastResizeTime = 0;
    private boolean mbSphericalEnable = false;
    private boolean mbCubeEnable = false;
    private boolean mbCardBoardEnable = false;
    private boolean mbViewReposition = false;
    private voSphericalVideoView SphericalView_ = null;
    private float mTranslationX = 0.0f;
    private float mTranslationY = 0.0f;
    private boolean useMeasure = false;
    private boolean screenSizeChanged = false;
    private boolean isSecure = true;
    private WeakReference<Object> mAppSurfaceTextureListener = new WeakReference<>(null);
    voLayoutAdapter layoutAdapter_ = null;
    voGLRenderWrapper glWrapper = null;
    GLSurfaceView glSurfaceView = null;

    public interface onViewChangeListener {
        int onSetParam(long j, Object obj);
    }

    private static boolean isVOObject(Object obj) {
        return (obj instanceof voLayoutAdapter) || (obj instanceof voLicenseDisplayRender);
    }

    private void removeOldVOObject() {
        View view = this.mView;
        if (view == null || view.getParent() == null) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) this.mView.getParent();
        int i = 0;
        while (i < viewGroup.getChildCount()) {
            if (isVOObject(viewGroup.getChildAt(i))) {
                viewGroup.removeViewAt(i);
            } else {
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDetectedScreenSize(int i, int i2) {
        this.mScreenWidth = i;
        this.mScreenHeight = i2;
        setFixedSizeInternal();
        SetParam(98L, new int[]{0, 0, this.mScreenWidth, this.mScreenHeight});
    }

    public class voGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        public voGlobalLayoutListener() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (voVideoViewController.this.mView == null) {
                return;
            }
            if (((View) voVideoViewController.this.mView.getParent()) != null) {
                int measuredWidth = ((View) voVideoViewController.this.mView.getParent()).getMeasuredWidth();
                int measuredHeight = ((View) voVideoViewController.this.mView.getParent()).getMeasuredHeight();
                if (voVideoViewController.this.mScreenWidth != measuredWidth || voVideoViewController.this.mScreenHeight != measuredHeight) {
                    voVideoViewController.this.screenSizeChanged = true;
                    voVideoViewController.this.updateDetectedScreenSize(measuredWidth, measuredHeight);
                    if (voLog.enablePrintLog()) {
                        voLog.i(voVideoViewController.TAG, "#VideoDes === globalLayoutListener w:%d h:%d", Integer.valueOf(measuredWidth), Integer.valueOf(measuredHeight));
                    }
                } else {
                    voVideoViewController.this.screenSizeChanged = false;
                }
            }
            voVideoViewController.this.useMeasure = false;
        }
    }

    public class voLayoutAdapter extends FrameLayout {
        private ReentrantLock mChildrenLock;
        private voVideoViewController mController;

        public voLayoutAdapter(Context context) {
            super(context);
            this.mController = null;
            this.mChildrenLock = new ReentrantLock();
        }

        public void setController(voVideoViewController vovideoviewcontroller) {
            this.mChildrenLock.lock();
            this.mController = vovideoviewcontroller;
            this.mChildrenLock.unlock();
        }

        @Override // android.view.View
        protected void onConfigurationChanged(Configuration configuration) {
            this.mChildrenLock.lock();
            if (this.mController != null) {
                if (voLog.enablePrintLog()) {
                    voLog.i(voVideoViewController.TAG, "#VideoDes === onConfigurationChanged w:%d h:%d", Integer.valueOf(this.mController.mScreenWidth), Integer.valueOf(this.mController.mScreenHeight));
                }
                this.mController.useMeasure = true;
            }
            this.mChildrenLock.unlock();
            super.onConfigurationChanged(configuration);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            this.mChildrenLock.lock();
            if (getVisibility() != 8 && this.mController != null) {
                measureChildren(i, i2);
                int defaultSize = View.getDefaultSize(getSuggestedMinimumWidth(), i);
                int defaultSize2 = View.getDefaultSize(getSuggestedMinimumHeight(), i2);
                setMeasuredDimension(defaultSize, defaultSize2);
                if (((this.mController.mScreenWidth != defaultSize || this.mController.mScreenHeight != defaultSize2) && this.mController.useMeasure) || (this.mController.mScreenWidth != defaultSize && this.mController.mScreenHeight != defaultSize2)) {
                    this.mController.updateDetectedScreenSize(defaultSize, defaultSize2);
                    if (voLog.enablePrintLog()) {
                        voLog.i(voVideoViewController.TAG, "#VideoDes === onMeasure w:%d h:%d", Integer.valueOf(this.mController.mScreenWidth), Integer.valueOf(this.mController.mScreenHeight));
                    }
                }
            } else {
                if (voLog.enablePrintLog()) {
                    voLog.i(voVideoViewController.TAG, "#VideoDes onMeasure GONE", new Object[0]);
                }
                measureChildren(0, 0);
                setMeasuredDimension(0, 0);
            }
            this.mChildrenLock.unlock();
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "#VideoDes onSizeChanged == w:%d h:%d ow:%d oh:%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
            super.onSizeChanged(i, i2, i3, i4);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "#VideoDes onLayout == l:%d t:%d r:%d b:%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
            super.onLayout(z, i, i2, i3, i4);
        }
    }

    public void destroy() {
        this.mLock.lock();
        voGLRenderWrapper voglrenderwrapper = this.glWrapper;
        if (voglrenderwrapper != null) {
            voglrenderwrapper.destroy();
            this.glWrapper = null;
        }
        this.glSurfaceView = null;
        removeGlobalListener();
        if (this.m_cbSurfaceHolder != null) {
            View view = this.mView;
            if (view != null && (view instanceof SurfaceView)) {
                ((SurfaceView) view).getHolder().removeCallback(this.m_cbSurfaceHolder);
            }
            this.m_cbSurfaceHolder.setController(null);
            this.m_cbSurfaceHolder = null;
        }
        voLayoutAdapter volayoutadapter = this.layoutAdapter_;
        if (volayoutadapter != null) {
            volayoutadapter.setController(null);
            this.layoutAdapter_ = null;
        }
        this.mViewChangeListener = null;
        this.mEventHandler = null;
        this.mLicDisplayRender = null;
        this.mLock.unlock();
    }

    public voVideoViewController() {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mEventHandler = new EventHandler(looperMyLooper);
            if (voLog.enablePrintLog()) {
                voLog.v(TAG, "Looper.myLooper()", new Object[0]);
                return;
            }
            return;
        }
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            this.mEventHandler = new EventHandler(mainLooper);
            if (voLog.enablePrintLog()) {
                voLog.v(TAG, "getMainLooper()", new Object[0]);
                return;
            }
            return;
        }
        this.mEventHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanUI() {
        View view = this.mSecondView;
        if (view != null) {
            view.setVisibility(4);
            this.mSecondView = null;
        }
        voLicenseDisplayRender volicensedisplayrender = this.mLicDisplayRender;
        if (volicensedisplayrender != null) {
            volicensedisplayrender.clean();
        }
        enableGLView(false);
    }

    public void stop() {
        this.mUpdateVideoDestIndex = 0;
        this.mWaitingSurfaceChange = false;
        this.mbSphericalEnable = false;
        this.mbCubeEnable = false;
        this.mbCardBoardEnable = false;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            cleanUI();
        } else {
            EventHandler eventHandler = this.mEventHandler;
            eventHandler.sendMessage(eventHandler.obtainMessage(messageCleanUI));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSecondTextureViewInside() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "addSecondTextureView step 1 " + this.mSecondView, new Object[0]);
        }
        if (this.mSecondView == null) {
            try {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "addSecondTextureView step 2 " + this.mSecondView, new Object[0]);
                }
                ViewGroup viewGroup = (ViewGroup) this.mView.getParent();
                ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
                this.mSecondView = new voTextureView(this.mContext);
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "addSecondTextureView step 3 " + this.mSecondView, new Object[0]);
                }
                viewGroup.addView(this.mSecondView, layoutParams);
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "addSecondTextureView step 4 " + this.mSecondView, new Object[0]);
                }
                ((voTextureView) this.mSecondView).setSurfaceTextureListener(new SecondSurfaceTextureListener());
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "addSecondTextureView step 5 " + this.mSecondView, new Object[0]);
                }
                this.mSecondView.setAlpha(0.0f);
                View view = this.mView;
                if (view != null && (view instanceof voSurfaceView)) {
                    ((voSurfaceView) view).addRelevanceTextureView(this.mSecondView);
                }
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Add second textureview, SURFACE", new Object[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setViewChangeListener(onViewChangeListener onviewchangelistener) {
        this.mLock.lock();
        this.mViewChangeListener = onviewchangelistener;
        this.mLock.unlock();
    }

    public void setZoomMode(int i) {
        this.mZoomMode = i;
    }

    public void setViewSize(int i, int i2) {
        if (canDetectLayout(this.mView)) {
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "#VideoDes setViewSize igore w:%d h:%d", Integer.valueOf(i), Integer.valueOf(i2));
            }
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            setViewSizeInternal(i, i2);
        } else {
            EventHandler eventHandler = this.mEventHandler;
            eventHandler.sendMessage(eventHandler.obtainMessage(messageSetViewSize, i, i2, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewSizeInternal(int i, int i2) {
        if (canDetectLayout(this.mView)) {
            this.mScreenWidth = ((View) this.mView.getParent()).getWidth();
            this.mScreenHeight = ((View) this.mView.getParent()).getHeight();
        } else {
            this.mScreenWidth = i;
            this.mScreenHeight = i2;
        }
        setFixedSizeInternal();
        SetParam(98L, new int[]{0, 0, this.mScreenWidth, this.mScreenHeight});
    }

    private class voSurfaceCallBack implements SurfaceHolder.Callback {
        private ReentrantLock mChildrenLock;
        private voVideoViewController mController;

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }

        private voSurfaceCallBack() {
            this.mController = null;
            this.mChildrenLock = new ReentrantLock();
        }

        public void setController(voVideoViewController vovideoviewcontroller) {
            this.mChildrenLock.lock();
            this.mController = vovideoviewcontroller;
            this.mChildrenLock.unlock();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "#VideoDes surfaceChanged", new Object[0]);
            }
            this.mChildrenLock.lock();
            voVideoViewController vovideoviewcontroller = this.mController;
            if (vovideoviewcontroller != null) {
                vovideoviewcontroller.SetParam(4135L, 1);
            }
            this.mChildrenLock.unlock();
        }
    }

    private class FirstSurfaceTextureListener implements TextureView.SurfaceTextureListener {
        private FirstSurfaceTextureListener() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "onSurfaceTextureAvailable", new Object[0]);
            }
            if (voVideoViewController.this.mAppSurfaceTextureListener.get() != null) {
                ((TextureView.SurfaceTextureListener) voVideoViewController.this.mAppSurfaceTextureListener.get()).onSurfaceTextureAvailable(surfaceTexture, i, i2);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "#VideoDes SurfaceTextureSizeChanged", new Object[0]);
            }
            voVideoViewController.this.SetParam(4135L, 1);
            if (voVideoViewController.this.mAppSurfaceTextureListener.get() != null) {
                ((TextureView.SurfaceTextureListener) voVideoViewController.this.mAppSurfaceTextureListener.get()).onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "onSurfaceTextureDestroyed", new Object[0]);
            }
            if (voVideoViewController.this.mAppSurfaceTextureListener.get() == null) {
                return true;
            }
            ((TextureView.SurfaceTextureListener) voVideoViewController.this.mAppSurfaceTextureListener.get()).onSurfaceTextureDestroyed(surfaceTexture);
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            if (voVideoViewController.this.mAppSurfaceTextureListener.get() != null) {
                ((TextureView.SurfaceTextureListener) voVideoViewController.this.mAppSurfaceTextureListener.get()).onSurfaceTextureUpdated(surfaceTexture);
            }
        }
    }

    private class SecondSurfaceTextureListener implements TextureView.SurfaceTextureListener {
        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        private SecondSurfaceTextureListener() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "onSurfaceTextureAvailable", new Object[0]);
            }
            voVideoViewController.this.SetParam(102L, new Surface(surfaceTexture));
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            if (voLog.enablePrintLog()) {
                voLog.i(voVideoViewController.TAG, "onSurfaceTextureSizeChanged", new Object[0]);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (!voLog.enablePrintLog()) {
                return true;
            }
            voLog.i(voVideoViewController.TAG, "onSurfaceTextureDestroyed", new Object[0]);
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00d1 A[Catch: InterruptedException -> 0x00fa, TryCatch #2 {InterruptedException -> 0x00fa, blocks: (B:46:0x00c7, B:48:0x00d1, B:50:0x00d5, B:51:0x00d8, B:52:0x00df, B:54:0x00e5), top: B:80:0x00c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00df A[Catch: InterruptedException -> 0x00fa, TryCatch #2 {InterruptedException -> 0x00fa, blocks: (B:46:0x00c7, B:48:0x00d1, B:50:0x00d5, B:51:0x00d8, B:52:0x00df, B:54:0x00e5), top: B:80:0x00c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ff  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00c2 -> B:76:0x014f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int SetParam(long r9, java.lang.Object r11) {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPUtils.voVideoViewController.SetParam(long, java.lang.Object):int");
    }

    public class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (voVideoViewController.this.mEventHandler != null) {
                switch (message.what) {
                    case voVideoViewController.messageAddSecondView /* -252706808 */:
                        voVideoViewController.this.addSecondTextureViewInside();
                        return;
                    case voVideoViewController.messageSetRenderType /* -252706807 */:
                        voVideoViewController.this.setRenderTypePrivate(message.arg1);
                        return;
                    case -252706806:
                    case -252706802:
                    default:
                        return;
                    case voVideoViewController.messageUpdateDisplaySize /* -252706805 */:
                        if (voLog.enablePrintLog()) {
                            voLog.i(voVideoViewController.TAG, "#VideoDes  messageUpdateDisplaySize:%d", Integer.valueOf(message.arg1));
                        }
                        voVideoViewController.this.refreshDisplayOnMain(message.arg1);
                        return;
                    case voVideoViewController.messageCleanUI /* -252706804 */:
                        voVideoViewController.this.cleanUI();
                        break;
                    case voVideoViewController.messageSetViewSize /* -252706803 */:
                        break;
                    case voVideoViewController.messageNotifyUpdateVideoDestBoxFinish /* -252706801 */:
                        voVideoViewController.this.SetParam(65571L, Integer.valueOf(message.arg1));
                        return;
                }
                voVideoViewController.this.setViewSizeInternal(message.arg1, message.arg2);
                return;
            }
            if (voLog.enablePrintLog()) {
                voLog.e(voVideoViewController.TAG, "voID3ContainerImpl went away with unhandled events", new Object[0]);
            }
        }
    }

    public void handleExternalMessage(Message message) {
        View view;
        int i = message.what;
        if (i == 15) {
            setFixedSizeInternal();
            return;
        }
        if (i != 42) {
            if (i != 46) {
                return;
            }
            updateLicenseDisplay(message.arg1, (String) message.obj);
            return;
        }
        View view2 = this.mView;
        if (view2 == null || (view = this.mSecondView) == null) {
            return;
        }
        int i2 = message.arg1;
        if (i2 == 1) {
            if (view2 instanceof TextureView) {
                view2.setAlpha(1.0f);
            }
            this.mSecondView.setAlpha(0.0f);
            onViewChangeListener onviewchangelistener = this.mViewChangeListener;
            if (onviewchangelistener != null) {
                onviewchangelistener.onSetParam(69649L, this.mView);
            }
        } else if (i2 == 2) {
            view.setAlpha(1.0f);
            View view3 = this.mView;
            if (view3 instanceof TextureView) {
                view3.setAlpha(0.0f);
            }
            onViewChangeListener onviewchangelistener2 = this.mViewChangeListener;
            if (onviewchangelistener2 != null) {
                onviewchangelistener2.onSetParam(69649L, this.mSecondView);
            }
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "VOOSMP_CB_SURFACE_EXCHANGED, param1 = %d, First Surface alpha value is %f, Second Surface alpha value is %f.", Integer.valueOf(message.arg1), Float.valueOf(this.mView.getAlpha()), Float.valueOf(this.mSecondView.getAlpha()));
        }
    }

    public void updateLicenseDisplay(int i, String str) {
        if (this.mView == null) {
            return;
        }
        voLicenseDisplayRender volicensedisplayrender = this.mLicDisplayRender;
        if (volicensedisplayrender == null) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            voLicenseDisplayRender volicensedisplayrender2 = new voLicenseDisplayRender(this.mContext);
            this.mLicDisplayRender = volicensedisplayrender2;
            volicensedisplayrender2.setLayoutParams(layoutParams);
            ((ViewGroup) this.mView.getParent()).addView(this.mLicDisplayRender);
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "Create license display view", new Object[0]);
            }
        } else {
            ViewGroup viewGroup = (ViewGroup) volicensedisplayrender.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.mLicDisplayRender);
                ((ViewGroup) this.mView.getParent()).addView(this.mLicDisplayRender);
            } else {
                ((ViewGroup) this.mView.getParent()).addView(this.mLicDisplayRender);
            }
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "License display type %d value %s", Integer.valueOf(i), str);
        }
        ViewGroup.LayoutParams layoutParams2 = this.mView.getLayoutParams();
        int width = ((View) this.mView.getParent()).getWidth();
        int height = ((View) this.mView.getParent()).getHeight();
        int i2 = layoutParams2.width;
        if (i2 <= 0) {
            i2 = width;
        }
        int i3 = layoutParams2.height;
        if (i3 <= 0) {
            i3 = height;
        }
        if (i == 0) {
            this.mLicDisplayRender.drawString(str, i2, i3);
        } else if (i == 1) {
            this.mLicDisplayRender.drawIcon(width, height, i2, i3);
        } else if (voLog.enablePrintLog()) {
            voLog.w(TAG, "Unsupported license display option %d", Integer.valueOf(i));
        }
    }

    private void setFixedSizeInternal() {
        if (3 == this.mRenderType) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (Build.VERSION.RELEASE.startsWith("6.0")) {
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "#VideoDes setFixedSizeInternal version:%d", Integer.valueOf(i));
            }
            if (this.mSurfaceHolder != null) {
                if ((this.mWidthVideo <= 0 || this.mHeightVideo <= 0) && !(this.mView instanceof voSurfaceView)) {
                    return;
                }
                int i2 = this.mScreenWidth;
                int i3 = this.mScreenHeight;
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "#VideoDes setFixedSizeInternal w:%d h:%d", Integer.valueOf(i2), Integer.valueOf(i3));
                }
                this.mSurfaceHolder.setFixedSize(i2, i3);
            }
        }
    }

    public voVideoViewController withContext(Context context) {
        this.mContext = context;
        if (context != null && this.layoutAdapter_ == null) {
            voLayoutAdapter volayoutadapter = new voLayoutAdapter(this.mContext);
            this.layoutAdapter_ = volayoutadapter;
            volayoutadapter.setController(this);
        }
        return this;
    }

    public void reset() {
        this.mDestRect = null;
        this.mWidthVideo = 0;
        this.mHeightVideo = 0;
        this.mZoomMode = 1;
        this.mbKeepAspectRatio = true;
        stop();
    }

    private int initGLView() {
        Context context;
        if (this.glSurfaceView != null) {
            View view = this.mView;
            if (view != null && view.getParent() != null && this.mView.getParent().equals(this.glSurfaceView.getParent())) {
                return 0;
            }
            this.glWrapper.destroy();
            this.glWrapper = null;
            ViewGroup viewGroup = (ViewGroup) this.glSurfaceView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.glSurfaceView);
            }
            this.glSurfaceView = null;
            onViewChangeListener onviewchangelistener = this.mViewChangeListener;
            if (onviewchangelistener != null) {
                onviewchangelistener.onSetParam(69649L, null);
                this.mViewChangeListener.onSetParam(69640L, null);
            }
        }
        if (this.mView == null || (context = this.mContext) == null) {
            return voOSType.VOOSMP_ERR_Status;
        }
        if (Activity.class.isInstance(context)) {
            if (!(((ActivityManager) ((Activity) this.mContext).getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getDeviceConfigurationInfo().reqGlEsVersion >= 131072 || voGLRenderWrapper.isProbablyEmulator())) {
                return voOSType.VOOSMP_ERR_Unknown;
            }
        }
        this.glSurfaceView = new GLSurfaceView(this.mContext);
        if (voGLRenderWrapper.isProbablyEmulator()) {
            this.glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        }
        this.glSurfaceView.setEGLContextClientVersion(2);
        if (this.glWrapper == null) {
            voGLRenderWrapper voglrenderwrapper = new voGLRenderWrapper(this, this.mContext);
            this.glWrapper = voglrenderwrapper;
            voglrenderwrapper.setLibraryPath(null);
            this.glWrapper.setSpherical(this.mbSphericalEnable);
            this.glWrapper.setCube(this.mbCubeEnable);
            this.glWrapper.setSphericalVideoView(this.SphericalView_);
        }
        this.glSurfaceView.setRenderer(this.glWrapper);
        return 0;
    }

    private void showAvailableEglConfig() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        egl10.eglGetConfigs(eGLDisplayEglGetDisplay, null, 0, iArr);
        int i = iArr[0];
        EGLConfig[] eGLConfigArr = new EGLConfig[i];
        egl10.eglGetConfigs(eGLDisplayEglGetDisplay, eGLConfigArr, i, iArr);
        voLog.e(TAG, "Num of total configs: " + Integer.valueOf(iArr[0]), new Object[0]);
        for (int i2 = 0; i2 < iArr[0]; i2++) {
            int[] iArr2 = new int[1];
            egl10.eglGetConfigAttrib(eGLDisplayEglGetDisplay, eGLConfigArr[i2], 12324, iArr2);
            int i3 = iArr2[0];
            egl10.eglGetConfigAttrib(eGLDisplayEglGetDisplay, eGLConfigArr[i2], 12323, iArr2);
            int i4 = iArr2[0];
            egl10.eglGetConfigAttrib(eGLDisplayEglGetDisplay, eGLConfigArr[i2], 12322, iArr2);
            int i5 = iArr2[0];
            egl10.eglGetConfigAttrib(eGLDisplayEglGetDisplay, eGLConfigArr[i2], 12321, iArr2);
            voLog.e(TAG, "(" + i3 + "," + i4 + "," + i5 + "," + iArr2[0] + ")", new Object[0]);
        }
        egl10.eglTerminate(eGLDisplayEglGetDisplay);
    }

    @Override // com.visualon.OSMPRender.voGLRenderWrapper.onGLRenderListener
    public void onGLInitFinish() {
        this.mLock.lock();
        onViewChangeListener onviewchangelistener = this.mViewChangeListener;
        if (onviewchangelistener != null) {
            onviewchangelistener.onSetParam(69640L, Long.valueOf(this.glWrapper.getNativeWrapper()));
            this.mViewChangeListener.onSetParam(69649L, this.glSurfaceView);
        }
        this.mLock.unlock();
    }

    public boolean updateGLViewStatus(View view) {
        GLSurfaceView gLSurfaceView = this.glSurfaceView;
        if (gLSurfaceView == null || view == null) {
            return false;
        }
        gLSurfaceView.setTranslationX(view.getTranslationX());
        return true;
    }

    public int enableGLView(boolean z) {
        View view;
        GLSurfaceView gLSurfaceView;
        View view2;
        GLSurfaceView gLSurfaceView2 = this.glSurfaceView;
        if (gLSurfaceView2 == null) {
            return voOSType.VOOSMP_ERR_Pointer;
        }
        ViewGroup viewGroup = (ViewGroup) gLSurfaceView2.getParent();
        if (z) {
            this.glSurfaceView.setSecure(this.isSecure);
            if (this.glSurfaceView.getVisibility() != 0) {
                this.glSurfaceView.setVisibility(0);
            }
            if (viewGroup == null && (view2 = this.mView) != null && view2.getParent() != null) {
                ((ViewGroup) this.mView.getParent()).addView(this.glSurfaceView, 1, new ViewGroup.LayoutParams(-1, -1));
                updateGLViewStatus(this.mView);
                WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
                windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
                this.mTranslationX = this.mView.getTranslationX();
                this.mTranslationY = this.mView.getTranslationY();
                this.mView.setTranslationY(r5.getHeight() + r0.heightPixels);
                this.mbViewReposition = true;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("group is null: ");
                sb.append(viewGroup == null ? "true" : "false");
                sb.append("mView is null ");
                sb.append(this.mView != null ? "true" : "false");
                sb.append("mView parent is null");
                sb.append(this.mView.getParent() == null ? "false" : "true");
                voLog.w(TAG, sb.toString(), new Object[0]);
            }
        } else {
            if (this.mbCardBoardEnable && (gLSurfaceView = this.glSurfaceView) != null) {
                gLSurfaceView.setVisibility(4);
            }
            if (this.mbViewReposition && (view = this.mView) != null) {
                view.setTranslationX(this.mTranslationX);
                this.mView.setTranslationY(this.mTranslationY);
                this.mbViewReposition = false;
            }
        }
        return 0;
    }

    public void setRenderType(int i) {
        GLSurfaceView gLSurfaceView = this.glSurfaceView;
        if (gLSurfaceView != null && i != 3) {
            gLSurfaceView.setVisibility(4);
        }
        this.mRenderType = i;
        voLog.i(TAG, "setRenderType: %d \n", Integer.valueOf(i));
        if (Looper.myLooper() == Looper.getMainLooper()) {
            setRenderTypePrivate(i);
        } else {
            voLog.i(TAG, ": mEventHandler.obtainMessag %d \n", Integer.valueOf(i));
            this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(messageSetRenderType, i, -1, null));
        }
        this.mLock.lock();
        onViewChangeListener onviewchangelistener = this.mViewChangeListener;
        if (onviewchangelistener != null) {
            onviewchangelistener.onSetParam(22L, Integer.valueOf(i));
        }
        this.mLock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRenderTypePrivate(int i) {
        if (this.mEventHandler.hasMessages(messageCleanUI)) {
            cleanUI();
            this.mEventHandler.removeMessages(messageCleanUI);
        }
        if (3 == this.mRenderType) {
            initGLView();
            enableGLView(true);
        } else {
            enableGLView(false);
        }
    }

    private void removeOldDetect() {
        if (canDetectLayout(this.layoutAdapter_)) {
            if (this.layoutAdapter_.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.layoutAdapter_.getParent()).removeView(this.layoutAdapter_);
            }
            View view = this.mView;
            if (view instanceof SurfaceView) {
                ((SurfaceView) view).getHolder().removeCallback(this.m_cbSurfaceHolder);
                this.m_cbSurfaceHolder.setController(null);
                this.m_cbSurfaceHolder = null;
            } else if (view instanceof TextureView) {
                ((TextureView) view).setSurfaceTextureListener(null);
            }
        }
    }

    private void removeGlobalListener() {
        View view = this.mView;
        if (view != null) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        }
        this.globalLayoutListener = null;
    }

    private boolean canDetectLayout(View view) {
        return view != null && (view.getParent() instanceof ViewGroup);
    }

    public voVideoViewController withSurfaceView(View view, SurfaceHolder surfaceHolder) {
        if (this.mRenderType == 3 && this.glSurfaceView != null) {
            updateGLViewStatus(view);
        }
        if (this.mView == view) {
            setViewSizeInternal(0, 0);
            return this;
        }
        if (view != null) {
            removeOldDetect();
        }
        removeGlobalListener();
        this.mLock.lock();
        this.mView = view;
        removeOldVOObject();
        this.mLock.unlock();
        if (canDetectLayout(this.mView)) {
            if (this.mView.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.mView.getParent()).addView(this.layoutAdapter_, new FrameLayout.LayoutParams(-1, -1));
            }
            View view2 = this.mView;
            if (view2 instanceof SurfaceView) {
                if (this.m_cbSurfaceHolder == null) {
                    voSurfaceCallBack vosurfacecallback = new voSurfaceCallBack();
                    this.m_cbSurfaceHolder = vosurfacecallback;
                    vosurfacecallback.setController(this);
                }
                ((SurfaceView) this.mView).getHolder().addCallback(this.m_cbSurfaceHolder);
            } else if (view2 instanceof TextureView) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "setSurfaceTextureListener", new Object[0]);
                }
                TextureView textureView = (TextureView) this.mView;
                TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                if (surfaceTextureListener != null && !(surfaceTextureListener instanceof FirstSurfaceTextureListener)) {
                    this.mAppSurfaceTextureListener = new WeakReference<>(surfaceTextureListener);
                    if (voLog.enablePrintLog()) {
                        voLog.i(TAG, "setAppSurfaceTextureListener " + this.mAppSurfaceTextureListener.get(), new Object[0]);
                    }
                }
                textureView.setSurfaceTextureListener(new FirstSurfaceTextureListener());
            }
            ViewTreeObserver viewTreeObserver = this.mView.getViewTreeObserver();
            if (this.globalLayoutListener == null) {
                this.globalLayoutListener = new voGlobalLayoutListener();
            }
            viewTreeObserver.addOnGlobalLayoutListener(this.globalLayoutListener);
            this.useMeasure = true;
        }
        this.mSurfaceHolder = surfaceHolder;
        if (this.mView != null) {
            setRenderType(this.mRenderType);
        }
        onViewChangeListener onviewchangelistener = this.mViewChangeListener;
        if (onviewchangelistener != null && 3 != this.mRenderType) {
            onviewchangelistener.onSetParam(69649L, this.mView);
        }
        setViewSizeInternal(0, 0);
        return this;
    }

    public void addSecondTextureView() {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler != null) {
            eventHandler.sendEmptyMessage(messageAddSecondView);
        }
    }

    public void setVideoSize(int i, int i2) {
        this.mWidthVideo = i;
        this.mHeightVideo = i2;
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "#VideoDes setVideoSize w:%d h:%d", Integer.valueOf(this.mWidthVideo), Integer.valueOf(this.mHeightVideo));
        }
    }

    public voVideoViewController withEnforceSWVideoScaling(boolean z) {
        this.mbEnforceSWVideoScaling = z;
        return this;
    }

    public int setSphericalVideoView(float f, float f2, float f3) {
        voGLRenderWrapper voglrenderwrapper = this.glWrapper;
        if (voglrenderwrapper != null) {
            return voglrenderwrapper.setSphericalVideoView(f, f2, f3);
        }
        return 0;
    }

    public int setSphericalVideoView(voSphericalVideoView vosphericalvideoview) {
        this.SphericalView_ = vosphericalvideoview;
        voGLRenderWrapper voglrenderwrapper = this.glWrapper;
        if (voglrenderwrapper != null) {
            return voglrenderwrapper.setSphericalVideoView(vosphericalvideoview);
        }
        return 0;
    }

    private void checkOrientationAndScreenSize() {
        int i;
        int i2;
        if (this.mContext.getResources().getConfiguration().orientation != 2 || (i = this.mScreenWidth) >= (i2 = this.mScreenHeight)) {
            return;
        }
        this.mScreenWidth = i2;
        this.mScreenHeight = i;
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "Exchange screen width and height, new width is %d, new height is %d .", Integer.valueOf(this.mScreenWidth), Integer.valueOf(this.mScreenHeight));
        }
    }

    private void updateVideoViewSize(int i, int i2) {
        View view = this.mView;
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.mView.setLayoutParams(layoutParams);
        View view2 = this.mSecondView;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
            layoutParams2.width = i;
            layoutParams2.height = i2;
            this.mSecondView.setLayoutParams(layoutParams2);
        }
        if (this.mbEnforceSWVideoScaling) {
            int i3 = this.mZoomMode;
            if (i3 == 1 || (i3 == 0 && this.mbKeepAspectRatio)) {
                int[] iArr = {i, i2};
                if (voLog.enablePrintLog()) {
                    voLog.d(TAG, "VOOSMP_PID_VIDEO_OUTPUT_RESOLUTION intArray[0] = " + iArr[0] + " , intArray[1] = " + iArr[1], new Object[0]);
                }
                this.mLock.lock();
                onViewChangeListener onviewchangelistener = this.mViewChangeListener;
                if (onviewchangelistener != null) {
                    onviewchangelistener.onSetParam(65545L, iArr);
                }
                this.mLock.unlock();
            }
        }
    }

    public boolean setVideoDestBox(voOSRect voosrect, int i) {
        if (3 == this.mRenderType) {
            return true;
        }
        this.mLock.lock();
        try {
            int iRight = voosrect.Right() - voosrect.Left();
            int iBottom = voosrect.Bottom() - voosrect.Top();
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "#VideoDes setVideoDestBox :%d w:%d, h:%d", Integer.valueOf(i), Integer.valueOf(iRight), Integer.valueOf(iBottom));
            }
            if (iRight != 0 && iBottom != 0) {
                this.mDestRect = voosrect;
                if (isDestChanged(i) != 0) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        refreshDisplayOnMain(i);
                    } else {
                        this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(messageUpdateDisplaySize, i, -1, null));
                    }
                }
                return true;
            }
            return false;
        } finally {
            this.mLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDisplayOnMain(int i) {
        if (3 == this.mRenderType || this.mDestRect == null) {
            return;
        }
        if (this.mView == null) {
            notifyUpdateVideoDestBoxFinish(i);
            return;
        }
        this.mLock.lock();
        try {
            int iIsDestChanged = isDestChanged(i);
            if (iIsDestChanged == 0) {
                return;
            }
            int iRight = this.mDestRect.Right() - this.mDestRect.Left();
            int iBottom = this.mDestRect.Bottom() - this.mDestRect.Top();
            this.mLastSetViewWidth = iRight;
            this.mLastSetViewHeight = iBottom;
            ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
            updateVideoViewSize(iRight, iBottom);
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "#VideoDes updateViewSize w:%d h:%d index:%d lp.width:%d, lp.height:%d, zm:%d", Integer.valueOf(iRight), Integer.valueOf(iBottom), Integer.valueOf(i), Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height), Integer.valueOf(this.mZoomMode));
            }
            View view = this.mView;
            if ((view instanceof voSurfaceView) || (view instanceof SurfaceView) || (view instanceof TextureView)) {
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    if (5 == this.mZoomMode) {
                        ((RelativeLayout.LayoutParams) layoutParams).addRule(13, 0);
                        ((RelativeLayout.LayoutParams) layoutParams).setMargins(this.mDestRect.Left(), this.mDestRect.Top(), this.mScreenWidth - this.mDestRect.Right(), this.mScreenHeight - this.mDestRect.Bottom());
                    } else {
                        ((RelativeLayout.LayoutParams) layoutParams).addRule(13, -1);
                    }
                } else if ((layoutParams instanceof ViewGroup.MarginLayoutParams) && (view instanceof voSurfaceView)) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(this.mDestRect.Left(), this.mDestRect.Top(), this.mDestRect.Right(), this.mDestRect.Bottom());
                } else if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Skip setMargins. Didn't use voSurfaceView/RelativeLayout.", new Object[0]);
                }
                this.mView.setLayoutParams(layoutParams);
                View view2 = this.mSecondView;
                if (view2 != null) {
                    view2.setLayoutParams(layoutParams);
                }
            } else if (voLog.enablePrintLog()) {
                voLog.i(TAG, "Skip setLayoutParams. Didn't use voSurfaceView/SurfaceView at all.", new Object[0]);
            }
            licenseViewChange(iRight, iBottom);
            if (1 == iIsDestChanged) {
                notifyUpdateVideoDestBoxFinish(i);
            } else {
                this.lastResizeTime = System.currentTimeMillis();
                this.mWaitingSurfaceChange = true;
                if (i > 0) {
                    this.mUpdateVideoDestIndex = i;
                }
            }
        } finally {
            this.mLock.unlock();
        }
    }

    private int isDestChanged(int i) {
        voOSRect voosrect;
        this.mLock.lock();
        try {
            if (this.mView == null || (voosrect = this.mDestRect) == null) {
                notifyUpdateVideoDestBoxFinish(i);
            } else {
                int iRight = voosrect.Right() - this.mDestRect.Left();
                int iBottom = this.mDestRect.Bottom() - this.mDestRect.Top();
                if (this.screenSizeChanged) {
                    if (voLog.enablePrintLog()) {
                        voLog.i(TAG, "#VideoDes screenSizeChanged = true, skip comparison.", new Object[0]);
                    }
                } else if (this.mView.getWidth() == iRight && this.mView.getHeight() == iBottom && this.mLastSetViewWidth == iRight && this.mLastSetViewHeight == iBottom) {
                    if (this.mWaitingSurfaceChange && System.currentTimeMillis() - this.lastResizeTime < 200) {
                        if (i > this.mUpdateVideoDestIndex) {
                            this.mUpdateVideoDestIndex = i;
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "#VideoDes update index to :%d", Integer.valueOf(this.mUpdateVideoDestIndex));
                            }
                        }
                    } else {
                        notifyUpdateVideoDestBoxFinish(i);
                    }
                    if (voLog.enablePrintLog()) {
                        voLog.i(TAG, "#VideoDes no changed :%d, w = %d, h = %d", Integer.valueOf(i), Integer.valueOf(iRight), Integer.valueOf(iBottom));
                    }
                } else if (Math.abs(this.mView.getWidth() - iRight) <= 3 && Math.abs(this.mView.getHeight() - iBottom) <= 3) {
                    return 1;
                }
                return 2;
            }
            return 0;
        } finally {
            this.mLock.unlock();
        }
    }

    private void licenseViewChange(int i, int i2) {
        voLicenseDisplayRender volicensedisplayrender = this.mLicDisplayRender;
        if (volicensedisplayrender == null || this.mView == null) {
            return;
        }
        volicensedisplayrender.onScreenOrVideoSizeChanged(this.mScreenWidth, this.mScreenHeight, i, i2);
    }

    private void notifyUpdateVideoDestBoxFinish(int i) {
        SetParam(65571L, Integer.valueOf(i));
    }

    public void setSphericalEnable(boolean z) {
        this.mbSphericalEnable = z;
        voGLRenderWrapper voglrenderwrapper = this.glWrapper;
        if (voglrenderwrapper != null) {
            voglrenderwrapper.setSpherical(z);
        }
    }

    public void setCubemapEnable(boolean z) {
        this.mbCubeEnable = z;
        voGLRenderWrapper voglrenderwrapper = this.glWrapper;
        if (voglrenderwrapper != null) {
            voglrenderwrapper.setCube(z);
        }
    }

    public void setCardBoardEnable(boolean z) {
        this.mbCardBoardEnable = z;
    }

    public void setSecure(boolean z) {
        this.isSecure = z;
    }
}
