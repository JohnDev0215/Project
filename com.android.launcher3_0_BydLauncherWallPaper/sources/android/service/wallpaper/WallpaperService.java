package android.service.wallpaper;

import android.annotation.SystemApi;
import android.app.Service;
import android.app.WallpaperColors;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.SettingsStringUtil;
import android.service.wallpaper.IWallpaperEngine;
import android.service.wallpaper.IWallpaperService;
import android.util.Log;
import android.util.MergedConfiguration;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.IWindowSession;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.R;
import com.android.internal.os.HandlerCaller;
import com.android.internal.view.BaseIWindow;
import com.android.internal.view.BaseSurfaceHolder;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WallpaperService extends Service {
    static final boolean DEBUG = false;
    private static final int DO_ATTACH = 10;
    private static final int DO_DETACH = 20;
    private static final int DO_IN_AMBIENT_MODE = 50;
    private static final int DO_SET_DESIRED_SIZE = 30;
    private static final int DO_SET_DISPLAY_PADDING = 40;
    private static final int MSG_REQUEST_WALLPAPER_COLORS = 10050;
    private static final int MSG_SCALE_PREVIEW = 10110;
    private static final int MSG_TOUCH_EVENT = 10040;
    private static final int MSG_UPDATE_SURFACE = 10000;
    private static final int MSG_VISIBILITY_CHANGED = 10010;
    private static final int MSG_WALLPAPER_COMMAND = 10025;
    private static final int MSG_WALLPAPER_OFFSETS = 10020;
    private static final int MSG_WINDOW_MOVED = 10035;
    private static final int MSG_WINDOW_RESIZED = 10030;
    private static final int MSG_ZOOM = 10100;
    private static final int NOTIFY_COLORS_RATE_LIMIT_MS = 1000;
    public static final String SERVICE_INTERFACE = "android.service.wallpaper.WallpaperService";
    public static final String SERVICE_META_DATA = "android.service.wallpaper";
    static final String TAG = "WallpaperService";
    private final ArrayList<Engine> mActiveEngines = new ArrayList<>();

    public abstract Engine onCreateEngine();

    static final class WallpaperCommand {
        String action;
        Bundle extras;
        boolean sync;
        int x;
        int y;
        int z;

        WallpaperCommand() {
        }
    }

    public class Engine {
        final Rect mBackdropFrame;
        HandlerCaller mCaller;
        private final Supplier<Long> mClockFunction;
        IWallpaperConnection mConnection;
        final Rect mContentInsets;
        boolean mCreated;
        int mCurHeight;
        int mCurWidth;
        int mCurWindowFlags;
        int mCurWindowPrivateFlags;
        boolean mDestroyed;
        final Rect mDispatchedContentInsets;
        DisplayCutout mDispatchedDisplayCutout;
        final Rect mDispatchedStableInsets;
        private Display mDisplay;
        private Context mDisplayContext;
        final DisplayCutout.ParcelableWrapper mDisplayCutout;
        private final DisplayManager.DisplayListener mDisplayListener;
        private int mDisplayState;
        boolean mDrawingAllowed;
        final Rect mFinalStableInsets;
        final Rect mFinalSystemInsets;
        boolean mFixedSizeAllowed;
        int mFormat;
        private final Handler mHandler;
        int mHeight;
        IWallpaperEngineWrapper mIWallpaperEngine;
        boolean mInitializing;
        WallpaperInputEventReceiver mInputEventReceiver;
        final InsetsState mInsetsState;
        boolean mIsCreating;
        boolean mIsInAmbientMode;
        private long mLastColorInvalidation;
        final WindowManager.LayoutParams mLayout;
        final Object mLock;
        final MergedConfiguration mMergedConfiguration;
        private final Runnable mNotifyColorsChanged;
        boolean mOffsetMessageEnqueued;
        boolean mOffsetsChanged;
        MotionEvent mPendingMove;
        boolean mPendingSync;
        float mPendingXOffset;
        float mPendingXOffsetStep;
        float mPendingYOffset;
        float mPendingYOffsetStep;
        Rect mPreviewSurfacePosition;
        boolean mReportedVisible;
        IWindowSession mSession;
        final Rect mStableInsets;
        SurfaceControl mSurfaceControl;
        boolean mSurfaceCreated;
        final BaseSurfaceHolder mSurfaceHolder;
        private final Point mSurfaceSize;
        final InsetsSourceControl[] mTempControls;
        private final Matrix mTmpMatrix;
        SurfaceControl mTmpSurfaceControl;
        private final float[] mTmpValues;
        int mType;
        boolean mVisible;
        final Rect mVisibleInsets;
        int mWidth;
        final Rect mWinFrame;
        final BaseIWindow mWindow;
        int mWindowFlags;
        int mWindowPrivateFlags;
        IBinder mWindowToken;
        float mZoom;

        @SystemApi
        public void onAmbientModeChanged(boolean z, long j) {
        }

        public void onApplyWindowInsets(WindowInsets windowInsets) {
        }

        public Bundle onCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) {
            return null;
        }

        public WallpaperColors onComputeColors() {
            return null;
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
        }

        public void onDesiredSizeChanged(int i, int i2) {
        }

        public void onDestroy() {
        }

        public void onOffsetsChanged(float f, float f2, float f3, float f4, int i, int i2) {
        }

        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
        }

        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
        }

        public void onSurfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
        }

        public void onTouchEvent(MotionEvent motionEvent) {
        }

        public void onVisibilityChanged(boolean z) {
        }

        public void onZoomChanged(float f) {
        }

        public boolean shouldZoomOutWallpaper() {
            return false;
        }

        final class WallpaperInputEventReceiver extends InputEventReceiver {
            public WallpaperInputEventReceiver(InputChannel inputChannel, Looper looper) {
                super(inputChannel, looper);
            }

            @Override // android.view.InputEventReceiver
            public void onInputEvent(InputEvent inputEvent) {
                boolean z = false;
                try {
                    if ((inputEvent instanceof MotionEvent) && (inputEvent.getSource() & 2) != 0) {
                        Engine.this.dispatchPointer(MotionEvent.obtainNoHistory((MotionEvent) inputEvent));
                        z = true;
                    }
                } finally {
                    finishInputEvent(inputEvent, false);
                }
            }
        }

        public Engine(WallpaperService wallpaperService) {
            this(new Supplier() { // from class: android.service.wallpaper.-$$Lambda$OJ01g4C3-dIy7DJeZXaYLthdjxE
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Long.valueOf(SystemClock.elapsedRealtime());
                }
            }, Handler.getMain());
        }

        public Engine(Supplier<Long> supplier, Handler handler) {
            this.mInitializing = true;
            this.mZoom = 0.0f;
            this.mWindowFlags = 16;
            this.mWindowPrivateFlags = 4;
            this.mCurWindowFlags = 16;
            this.mCurWindowPrivateFlags = 4;
            this.mVisibleInsets = new Rect();
            this.mWinFrame = new Rect();
            this.mContentInsets = new Rect();
            this.mStableInsets = new Rect();
            this.mDispatchedContentInsets = new Rect();
            this.mDispatchedStableInsets = new Rect();
            this.mFinalSystemInsets = new Rect();
            this.mFinalStableInsets = new Rect();
            this.mBackdropFrame = new Rect();
            this.mDisplayCutout = new DisplayCutout.ParcelableWrapper();
            this.mDispatchedDisplayCutout = DisplayCutout.NO_CUTOUT;
            this.mInsetsState = new InsetsState();
            this.mTempControls = new InsetsSourceControl[0];
            this.mMergedConfiguration = new MergedConfiguration();
            this.mSurfaceSize = new Point();
            this.mTmpMatrix = new Matrix();
            this.mTmpValues = new float[9];
            this.mLayout = new WindowManager.LayoutParams();
            this.mLock = new Object();
            this.mNotifyColorsChanged = new Runnable() { // from class: android.service.wallpaper.-$$Lambda$cf_AJBRaYSf0frWV5Rga1GwSyFk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.notifyColorsChanged();
                }
            };
            this.mSurfaceControl = new SurfaceControl();
            this.mTmpSurfaceControl = new SurfaceControl();
            this.mSurfaceHolder = new BaseSurfaceHolder() { // from class: android.service.wallpaper.WallpaperService.Engine.1
                {
                    this.mRequestedFormat = 2;
                }

                @Override // com.android.internal.view.BaseSurfaceHolder
                public boolean onAllowLockCanvas() {
                    return Engine.this.mDrawingAllowed;
                }

                @Override // com.android.internal.view.BaseSurfaceHolder
                public void onRelayoutContainer() {
                    Engine.this.mCaller.sendMessage(Engine.this.mCaller.obtainMessage(10000));
                }

                @Override // com.android.internal.view.BaseSurfaceHolder
                public void onUpdateSurface() {
                    Engine.this.mCaller.sendMessage(Engine.this.mCaller.obtainMessage(10000));
                }

                @Override // android.view.SurfaceHolder
                public boolean isCreating() {
                    return Engine.this.mIsCreating;
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public void setFixedSize(int i, int i2) {
                    if (!Engine.this.mFixedSizeAllowed) {
                        throw new UnsupportedOperationException("Wallpapers currently only support sizing from layout");
                    }
                    super.setFixedSize(i, i2);
                }

                @Override // android.view.SurfaceHolder
                public void setKeepScreenOn(boolean z) {
                    throw new UnsupportedOperationException("Wallpapers do not support keep screen on");
                }

                private void prepareToDraw() {
                    if (Engine.this.mDisplayState == 3 || Engine.this.mDisplayState == 4) {
                        try {
                            Engine.this.mSession.pokeDrawLock(Engine.this.mWindow);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public Canvas lockCanvas() {
                    prepareToDraw();
                    return super.lockCanvas();
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public Canvas lockCanvas(Rect rect) {
                    prepareToDraw();
                    return super.lockCanvas(rect);
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public Canvas lockHardwareCanvas() {
                    prepareToDraw();
                    return super.lockHardwareCanvas();
                }
            };
            this.mWindow = new BaseIWindow() { // from class: android.service.wallpaper.WallpaperService.Engine.2
                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void resized(Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z, MergedConfiguration mergedConfiguration, Rect rect5, boolean z2, boolean z3, int i, DisplayCutout.ParcelableWrapper parcelableWrapper) {
                    Engine.this.mCaller.sendMessage(Engine.this.mCaller.obtainMessageI(10030, z ? 1 : 0));
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void moved(int i, int i2) {
                    Engine.this.mCaller.sendMessage(Engine.this.mCaller.obtainMessageII(10035, i, i2));
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void dispatchAppVisibility(boolean z) {
                    if (Engine.this.mIWallpaperEngine.mIsPreview) {
                        return;
                    }
                    Engine.this.mCaller.sendMessage(Engine.this.mCaller.obtainMessageI(10010, z ? 1 : 0));
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void dispatchWallpaperOffsets(float f, float f2, float f3, float f4, float f5, boolean z) {
                    synchronized (Engine.this.mLock) {
                        Engine.this.mPendingXOffset = f;
                        Engine.this.mPendingYOffset = f2;
                        Engine.this.mPendingXOffsetStep = f3;
                        Engine.this.mPendingYOffsetStep = f4;
                        if (z) {
                            Engine.this.mPendingSync = true;
                        }
                        if (!Engine.this.mOffsetMessageEnqueued) {
                            Engine.this.mOffsetMessageEnqueued = true;
                            Engine.this.mCaller.sendMessage(Engine.this.mCaller.obtainMessage(10020));
                        }
                        Engine.this.mCaller.sendMessage(Engine.this.mCaller.obtainMessageI(WallpaperService.MSG_ZOOM, Float.floatToIntBits(f5)));
                    }
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) {
                    synchronized (Engine.this.mLock) {
                        WallpaperCommand wallpaperCommand = new WallpaperCommand();
                        wallpaperCommand.action = str;
                        wallpaperCommand.x = i;
                        wallpaperCommand.y = i2;
                        wallpaperCommand.z = i3;
                        wallpaperCommand.extras = bundle;
                        wallpaperCommand.sync = z;
                        Message messageObtainMessage = Engine.this.mCaller.obtainMessage(10025);
                        messageObtainMessage.obj = wallpaperCommand;
                        Engine.this.mCaller.sendMessage(messageObtainMessage);
                    }
                }
            };
            this.mDisplayListener = new DisplayManager.DisplayListener() { // from class: android.service.wallpaper.WallpaperService.Engine.3
                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayAdded(int i) {
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayRemoved(int i) {
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayChanged(int i) throws Throwable {
                    if (Engine.this.mDisplay.getDisplayId() == i) {
                        Engine.this.reportVisibility();
                    }
                }
            };
            this.mClockFunction = supplier;
            this.mHandler = handler;
        }

        public SurfaceHolder getSurfaceHolder() {
            return this.mSurfaceHolder;
        }

        public int getDesiredMinimumWidth() {
            return this.mIWallpaperEngine.mReqWidth;
        }

        public int getDesiredMinimumHeight() {
            return this.mIWallpaperEngine.mReqHeight;
        }

        public boolean isVisible() {
            return this.mReportedVisible;
        }

        public boolean isPreview() {
            return this.mIWallpaperEngine.mIsPreview;
        }

        @SystemApi
        public boolean isInAmbientMode() {
            return this.mIsInAmbientMode;
        }

        public void setTouchEventsEnabled(boolean z) throws Throwable {
            int i;
            if (z) {
                i = this.mWindowFlags & (-17);
            } else {
                i = this.mWindowFlags | 16;
            }
            this.mWindowFlags = i;
            if (this.mCreated) {
                updateSurface(false, false, false);
            }
        }

        public void setOffsetNotificationsEnabled(boolean z) throws Throwable {
            int i;
            if (z) {
                i = this.mWindowPrivateFlags | 4;
            } else {
                i = this.mWindowPrivateFlags & (-5);
            }
            this.mWindowPrivateFlags = i;
            if (this.mCreated) {
                updateSurface(false, false, false);
            }
        }

        public void setFixedSizeAllowed(boolean z) {
            this.mFixedSizeAllowed = z;
        }

        public float getZoom() {
            return this.mZoom;
        }

        public void notifyColorsChanged() {
            long jLongValue = this.mClockFunction.get().longValue();
            if (jLongValue - this.mLastColorInvalidation < 1000) {
                Log.w(WallpaperService.TAG, "This call has been deferred. You should only call notifyColorsChanged() once every 1.0 seconds.");
                if (this.mHandler.hasCallbacks(this.mNotifyColorsChanged)) {
                    return;
                }
                this.mHandler.postDelayed(this.mNotifyColorsChanged, 1000L);
                return;
            }
            this.mLastColorInvalidation = jLongValue;
            this.mHandler.removeCallbacks(this.mNotifyColorsChanged);
            try {
                WallpaperColors wallpaperColorsOnComputeColors = onComputeColors();
                IWallpaperConnection iWallpaperConnection = this.mConnection;
                if (iWallpaperConnection != null) {
                    iWallpaperConnection.onWallpaperColorsChanged(wallpaperColorsOnComputeColors, this.mDisplay.getDisplayId());
                } else {
                    Log.w(WallpaperService.TAG, "Can't notify system because wallpaper connection was not established.");
                }
            } catch (RemoteException e) {
                Log.w(WallpaperService.TAG, "Can't notify system because wallpaper connection was lost.", e);
            }
        }

        public void setCreated(boolean z) {
            this.mCreated = z;
        }

        protected void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mInitializing=");
            printWriter.print(this.mInitializing);
            printWriter.print(" mDestroyed=");
            printWriter.println(this.mDestroyed);
            printWriter.print(str);
            printWriter.print("mVisible=");
            printWriter.print(this.mVisible);
            printWriter.print(" mReportedVisible=");
            printWriter.println(this.mReportedVisible);
            printWriter.print(str);
            printWriter.print("mDisplay=");
            printWriter.println(this.mDisplay);
            printWriter.print(str);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print(" mSurfaceCreated=");
            printWriter.print(this.mSurfaceCreated);
            printWriter.print(" mIsCreating=");
            printWriter.print(this.mIsCreating);
            printWriter.print(" mDrawingAllowed=");
            printWriter.println(this.mDrawingAllowed);
            printWriter.print(str);
            printWriter.print("mWidth=");
            printWriter.print(this.mWidth);
            printWriter.print(" mCurWidth=");
            printWriter.print(this.mCurWidth);
            printWriter.print(" mHeight=");
            printWriter.print(this.mHeight);
            printWriter.print(" mCurHeight=");
            printWriter.println(this.mCurHeight);
            printWriter.print(str);
            printWriter.print("mType=");
            printWriter.print(this.mType);
            printWriter.print(" mWindowFlags=");
            printWriter.print(this.mWindowFlags);
            printWriter.print(" mCurWindowFlags=");
            printWriter.println(this.mCurWindowFlags);
            printWriter.print(str);
            printWriter.print("mWindowPrivateFlags=");
            printWriter.print(this.mWindowPrivateFlags);
            printWriter.print(" mCurWindowPrivateFlags=");
            printWriter.println(this.mCurWindowPrivateFlags);
            printWriter.print(str);
            printWriter.print("mVisibleInsets=");
            printWriter.print(this.mVisibleInsets.toShortString());
            printWriter.print(" mWinFrame=");
            printWriter.print(this.mWinFrame.toShortString());
            printWriter.print(" mContentInsets=");
            printWriter.println(this.mContentInsets.toShortString());
            printWriter.print(str);
            printWriter.print("mConfiguration=");
            printWriter.println(this.mMergedConfiguration.getMergedConfiguration());
            printWriter.print(str);
            printWriter.print("mLayout=");
            printWriter.println(this.mLayout);
            printWriter.print(str);
            printWriter.print("mZoom=");
            printWriter.println(this.mZoom);
            printWriter.print(str);
            printWriter.print("mPreviewSurfacePosition=");
            printWriter.println(this.mPreviewSurfacePosition);
            synchronized (this.mLock) {
                printWriter.print(str);
                printWriter.print("mPendingXOffset=");
                printWriter.print(this.mPendingXOffset);
                printWriter.print(" mPendingXOffset=");
                printWriter.println(this.mPendingXOffset);
                printWriter.print(str);
                printWriter.print("mPendingXOffsetStep=");
                printWriter.print(this.mPendingXOffsetStep);
                printWriter.print(" mPendingXOffsetStep=");
                printWriter.println(this.mPendingXOffsetStep);
                printWriter.print(str);
                printWriter.print("mOffsetMessageEnqueued=");
                printWriter.print(this.mOffsetMessageEnqueued);
                printWriter.print(" mPendingSync=");
                printWriter.println(this.mPendingSync);
                if (this.mPendingMove != null) {
                    printWriter.print(str);
                    printWriter.print("mPendingMove=");
                    printWriter.println(this.mPendingMove);
                }
            }
        }

        public void setZoom(float f) {
            boolean z;
            synchronized (this.mLock) {
                if (this.mIsInAmbientMode) {
                    this.mZoom = 0.0f;
                }
                if (Float.compare(f, this.mZoom) != 0) {
                    this.mZoom = f;
                    z = true;
                } else {
                    z = false;
                }
            }
            if (!z || this.mDestroyed) {
                return;
            }
            onZoomChanged(this.mZoom);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dispatchPointer(MotionEvent motionEvent) {
            if (motionEvent.isTouchEvent()) {
                synchronized (this.mLock) {
                    if (motionEvent.getAction() == 2) {
                        this.mPendingMove = motionEvent;
                    } else {
                        this.mPendingMove = null;
                    }
                }
                this.mCaller.sendMessage(this.mCaller.obtainMessageO(10040, motionEvent));
                return;
            }
            motionEvent.recycle();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        void updateSurface(boolean z, boolean z2, boolean z3) throws Throwable {
            boolean z4;
            int i;
            int i2;
            boolean z5;
            boolean z6;
            boolean z7;
            int i3;
            int i4;
            boolean z8;
            boolean z9;
            boolean z10;
            boolean z11;
            if (this.mDestroyed) {
                Log.w(WallpaperService.TAG, "Ignoring updateSurface: destroyed");
            }
            int requestedWidth = this.mSurfaceHolder.getRequestedWidth();
            int i5 = -1;
            if (requestedWidth <= 0) {
                requestedWidth = -1;
                z4 = false;
            } else {
                z4 = true;
            }
            int requestedHeight = this.mSurfaceHolder.getRequestedHeight();
            if (requestedHeight > 0) {
                z4 = true;
                i5 = requestedHeight;
            }
            boolean z12 = !this.mCreated;
            boolean z13 = !this.mSurfaceCreated;
            boolean z14 = this.mFormat != this.mSurfaceHolder.getRequestedFormat();
            boolean z15 = (this.mWidth == requestedWidth && this.mHeight == i5) ? false : true;
            boolean z16 = !this.mCreated;
            boolean z17 = this.mType != this.mSurfaceHolder.getRequestedType();
            boolean z18 = (this.mCurWindowFlags == this.mWindowFlags && this.mCurWindowPrivateFlags == this.mWindowPrivateFlags) ? false : true;
            if (!z && !z12 && !z13 && !z14 && !z15 && !z17 && !z18 && !z3 && this.mIWallpaperEngine.mShownReported) {
                return;
            }
            try {
                this.mWidth = requestedWidth;
                this.mHeight = i5;
                this.mFormat = this.mSurfaceHolder.getRequestedFormat();
                this.mType = this.mSurfaceHolder.getRequestedType();
                this.mLayout.x = 0;
                this.mLayout.y = 0;
                this.mLayout.width = requestedWidth;
                this.mLayout.height = i5;
                this.mLayout.format = this.mFormat;
                int i6 = this.mWindowFlags;
                this.mCurWindowFlags = i6;
                this.mLayout.flags = i6 | 512 | 65536 | 256 | 8;
                int i7 = this.mWindowPrivateFlags;
                this.mCurWindowPrivateFlags = i7;
                this.mLayout.privateFlags = i7;
                this.mLayout.memoryType = this.mType;
                this.mLayout.token = this.mWindowToken;
                if (this.mCreated) {
                    i = requestedWidth;
                    i2 = i5;
                    z5 = z12;
                    z6 = z14;
                    z7 = z15;
                } else {
                    WallpaperService.this.obtainStyledAttributes(R.styleable.Window).recycle();
                    this.mLayout.type = this.mIWallpaperEngine.mWindowType;
                    this.mLayout.gravity = 8388659;
                    this.mLayout.setFitInsetsTypes(0);
                    this.mLayout.setTitle(WallpaperService.this.getClass().getName());
                    this.mLayout.windowAnimations = R.style.Animation_Wallpaper;
                    InputChannel inputChannel = new InputChannel();
                    IWindowSession iWindowSession = this.mSession;
                    BaseIWindow baseIWindow = this.mWindow;
                    i = requestedWidth;
                    i2 = i5;
                    z7 = z15;
                    z6 = z14;
                    z5 = z12;
                    if (iWindowSession.addToDisplay(baseIWindow, baseIWindow.mSeq, this.mLayout, 0, this.mDisplay.getDisplayId(), this.mWinFrame, this.mContentInsets, this.mStableInsets, this.mDisplayCutout, inputChannel, this.mInsetsState, this.mTempControls) < 0) {
                        Log.w(WallpaperService.TAG, "Failed to add window while updating wallpaper surface.");
                        return;
                    } else {
                        this.mSession.setShouldZoomOutWallpaper(this.mWindow, shouldZoomOutWallpaper());
                        this.mCreated = true;
                        this.mInputEventReceiver = new WallpaperInputEventReceiver(inputChannel, Looper.myLooper());
                    }
                }
                this.mSurfaceHolder.mSurfaceLock.lock();
                this.mDrawingAllowed = true;
                if (!z4) {
                    this.mLayout.surfaceInsets.set(this.mIWallpaperEngine.mDisplayPadding);
                } else {
                    this.mLayout.surfaceInsets.set(0, 0, 0, 0);
                }
                IWindowSession iWindowSession2 = this.mSession;
                BaseIWindow baseIWindow2 = this.mWindow;
                boolean z19 = z4;
                int iRelayout = iWindowSession2.relayout(baseIWindow2, baseIWindow2.mSeq, this.mLayout, this.mWidth, this.mHeight, 0, 0, -1L, this.mWinFrame, this.mContentInsets, this.mVisibleInsets, this.mStableInsets, this.mBackdropFrame, this.mDisplayCutout, this.mMergedConfiguration, this.mSurfaceControl, this.mInsetsState, this.mTempControls, this.mSurfaceSize, this.mTmpSurfaceControl);
                if (this.mSurfaceControl.isValid()) {
                    this.mSurfaceHolder.mSurface.copyFrom(this.mSurfaceControl);
                }
                int iWidth = this.mWinFrame.width();
                int iHeight = this.mWinFrame.height();
                if (z19) {
                    i3 = i;
                    i4 = i2;
                } else {
                    Rect rect = this.mIWallpaperEngine.mDisplayPadding;
                    i3 = iWidth + rect.left + rect.right;
                    i4 = iHeight + rect.top + rect.bottom;
                    this.mContentInsets.left += rect.left;
                    this.mContentInsets.top += rect.top;
                    this.mContentInsets.right += rect.right;
                    this.mContentInsets.bottom += rect.bottom;
                    this.mStableInsets.left += rect.left;
                    this.mStableInsets.top += rect.top;
                    this.mStableInsets.right += rect.right;
                    this.mStableInsets.bottom += rect.bottom;
                    DisplayCutout.ParcelableWrapper parcelableWrapper = this.mDisplayCutout;
                    parcelableWrapper.set(parcelableWrapper.get().inset(-rect.left, -rect.top, -rect.right, -rect.bottom));
                }
                if (this.mCurWidth != i3) {
                    this.mCurWidth = i3;
                    z8 = true;
                } else {
                    z8 = z7;
                }
                if (this.mCurHeight != i4) {
                    this.mCurHeight = i4;
                    z8 = true;
                }
                boolean z20 = z16 | (!this.mDispatchedContentInsets.equals(this.mContentInsets)) | (!this.mDispatchedStableInsets.equals(this.mStableInsets)) | (!this.mDispatchedDisplayCutout.equals(this.mDisplayCutout.get()));
                this.mSurfaceHolder.setSurfaceFrameSize(i3, i4);
                this.mSurfaceHolder.mSurfaceLock.unlock();
                if (!this.mSurfaceHolder.mSurface.isValid()) {
                    reportSurfaceDestroyed();
                    return;
                }
                try {
                    this.mSurfaceHolder.ungetCallbacks();
                    if (z13) {
                        this.mIsCreating = true;
                        onSurfaceCreated(this.mSurfaceHolder);
                        SurfaceHolder.Callback[] callbacks = this.mSurfaceHolder.getCallbacks();
                        if (callbacks != null) {
                            for (SurfaceHolder.Callback callback : callbacks) {
                                callback.surfaceCreated(this.mSurfaceHolder);
                            }
                        }
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    z9 = z3 | (z5 || (iRelayout & 2) != 0);
                    if (z2 || z5 || z13 || z6 || z8) {
                        try {
                            onSurfaceChanged(this.mSurfaceHolder, this.mFormat, this.mCurWidth, this.mCurHeight);
                            SurfaceHolder.Callback[] callbacks2 = this.mSurfaceHolder.getCallbacks();
                            if (callbacks2 != null) {
                                for (SurfaceHolder.Callback callback2 : callbacks2) {
                                    callback2.surfaceChanged(this.mSurfaceHolder, this.mFormat, this.mCurWidth, this.mCurHeight);
                                }
                            }
                            z10 = true;
                        } catch (Throwable th) {
                            th = th;
                            this.mIsCreating = false;
                            this.mSurfaceCreated = true;
                            if (z9) {
                                this.mSession.finishDrawing(this.mWindow, null);
                            }
                            reposition();
                            this.mIWallpaperEngine.reportShown();
                            throw th;
                        }
                    }
                    if (z20) {
                        this.mDispatchedContentInsets.set(this.mContentInsets);
                        this.mDispatchedStableInsets.set(this.mStableInsets);
                        this.mDispatchedDisplayCutout = this.mDisplayCutout.get();
                        this.mFinalStableInsets.set(this.mDispatchedStableInsets);
                        onApplyWindowInsets(new WindowInsets(this.mFinalSystemInsets, this.mFinalStableInsets, WallpaperService.this.getResources().getConfiguration().isScreenRound(), false, this.mDispatchedDisplayCutout));
                    }
                    if (z9) {
                        onSurfaceRedrawNeeded(this.mSurfaceHolder);
                        SurfaceHolder.Callback[] callbacks3 = this.mSurfaceHolder.getCallbacks();
                        if (callbacks3 != null) {
                            for (SurfaceHolder.Callback callback3 : callbacks3) {
                                if (callback3 instanceof SurfaceHolder.Callback2) {
                                    ((SurfaceHolder.Callback2) callback3).surfaceRedrawNeeded(this.mSurfaceHolder);
                                }
                            }
                        }
                    }
                    if (!z10 || this.mReportedVisible) {
                        z11 = false;
                    } else {
                        if (this.mIsCreating) {
                            onVisibilityChanged(true);
                        }
                        z11 = false;
                        onVisibilityChanged(false);
                    }
                    this.mIsCreating = z11;
                    this.mSurfaceCreated = true;
                    if (z9) {
                        this.mSession.finishDrawing(this.mWindow, null);
                    }
                    reposition();
                    this.mIWallpaperEngine.reportShown();
                } catch (Throwable th2) {
                    th = th2;
                    z9 = z3;
                }
            } catch (RemoteException unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void scalePreview(Rect rect) throws Throwable {
            Rect rect2;
            if (!(isPreview() && this.mPreviewSurfacePosition == null && rect != null) && ((rect2 = this.mPreviewSurfacePosition) == null || rect2.equals(rect))) {
                return;
            }
            this.mPreviewSurfacePosition = rect;
            if (this.mSurfaceControl.isValid()) {
                reposition();
            } else {
                updateSurface(false, false, false);
            }
        }

        private void reposition() {
            if (this.mPreviewSurfacePosition == null) {
                return;
            }
            this.mTmpMatrix.setTranslate(r0.left, this.mPreviewSurfacePosition.top);
            this.mTmpMatrix.postScale(this.mPreviewSurfacePosition.width() / this.mCurWidth, this.mPreviewSurfacePosition.height() / this.mCurHeight);
            this.mTmpMatrix.getValues(this.mTmpValues);
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            transaction.setPosition(this.mSurfaceControl, this.mPreviewSurfacePosition.left, this.mPreviewSurfacePosition.top);
            SurfaceControl surfaceControl = this.mSurfaceControl;
            float[] fArr = this.mTmpValues;
            transaction.setMatrix(surfaceControl, fArr[0], fArr[3], fArr[1], fArr[4]);
            transaction.apply();
        }

        void attach(IWallpaperEngineWrapper iWallpaperEngineWrapper) throws Throwable {
            if (this.mDestroyed) {
                return;
            }
            this.mIWallpaperEngine = iWallpaperEngineWrapper;
            this.mCaller = iWallpaperEngineWrapper.mCaller;
            this.mConnection = iWallpaperEngineWrapper.mConnection;
            this.mWindowToken = iWallpaperEngineWrapper.mWindowToken;
            this.mSurfaceHolder.setSizeFromLayout();
            this.mInitializing = true;
            IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
            this.mSession = windowSession;
            this.mWindow.setSession(windowSession);
            this.mLayout.packageName = WallpaperService.this.getPackageName();
            this.mIWallpaperEngine.mDisplayManager.registerDisplayListener(this.mDisplayListener, this.mCaller.getHandler());
            Display display = this.mIWallpaperEngine.mDisplay;
            this.mDisplay = display;
            this.mDisplayContext = WallpaperService.this.createDisplayContext(display);
            this.mDisplayState = this.mDisplay.getState();
            onCreate(this.mSurfaceHolder);
            this.mInitializing = false;
            this.mReportedVisible = false;
            updateSurface(false, false, false);
        }

        public Context getDisplayContext() {
            return this.mDisplayContext;
        }

        public void doAmbientModeChanged(boolean z, long j) {
            if (this.mDestroyed) {
                return;
            }
            this.mIsInAmbientMode = z;
            if (this.mCreated) {
                onAmbientModeChanged(z, j);
            }
        }

        void doDesiredSizeChanged(int i, int i2) {
            if (this.mDestroyed) {
                return;
            }
            this.mIWallpaperEngine.mReqWidth = i;
            this.mIWallpaperEngine.mReqHeight = i2;
            onDesiredSizeChanged(i, i2);
            doOffsetsChanged(true);
        }

        void doDisplayPaddingChanged(Rect rect) throws Throwable {
            if (this.mDestroyed || this.mIWallpaperEngine.mDisplayPadding.equals(rect)) {
                return;
            }
            this.mIWallpaperEngine.mDisplayPadding.set(rect);
            updateSurface(true, false, false);
        }

        void doVisibilityChanged(boolean z) throws Throwable {
            if (this.mDestroyed) {
                return;
            }
            this.mVisible = z;
            reportVisibility();
        }

        void reportVisibility() throws Throwable {
            if (this.mDestroyed) {
                return;
            }
            Display display = this.mDisplay;
            int state = display == null ? 0 : display.getState();
            this.mDisplayState = state;
            boolean z = this.mVisible && state != 1;
            if (this.mReportedVisible != z) {
                this.mReportedVisible = z;
                if (z) {
                    doOffsetsChanged(false);
                    updateSurface(true, false, false);
                }
                onVisibilityChanged(z);
            }
        }

        void doOffsetsChanged(boolean z) {
            float f;
            float f2;
            float f3;
            float f4;
            boolean z2;
            if (this.mDestroyed) {
                return;
            }
            if (z || this.mOffsetsChanged) {
                synchronized (this.mLock) {
                    f = this.mPendingXOffset;
                    f2 = this.mPendingYOffset;
                    f3 = this.mPendingXOffsetStep;
                    f4 = this.mPendingYOffsetStep;
                    z2 = this.mPendingSync;
                    this.mPendingSync = false;
                    this.mOffsetMessageEnqueued = false;
                }
                if (this.mSurfaceCreated) {
                    if (this.mReportedVisible) {
                        int i = this.mIWallpaperEngine.mReqWidth - this.mCurWidth;
                        int i2 = i > 0 ? -((int) ((i * f) + 0.5f)) : 0;
                        int i3 = this.mIWallpaperEngine.mReqHeight - this.mCurHeight;
                        onOffsetsChanged(f, f2, f3, f4, i2, i3 > 0 ? -((int) ((i3 * f2) + 0.5f)) : 0);
                    } else {
                        this.mOffsetsChanged = true;
                    }
                }
                if (z2) {
                    try {
                        this.mSession.wallpaperOffsetsComplete(this.mWindow.asBinder());
                    } catch (RemoteException unused) {
                    }
                }
            }
        }

        void doCommand(WallpaperCommand wallpaperCommand) {
            Bundle bundleOnCommand = !this.mDestroyed ? onCommand(wallpaperCommand.action, wallpaperCommand.x, wallpaperCommand.y, wallpaperCommand.z, wallpaperCommand.extras, wallpaperCommand.sync) : null;
            if (wallpaperCommand.sync) {
                try {
                    this.mSession.wallpaperCommandComplete(this.mWindow.asBinder(), bundleOnCommand);
                } catch (RemoteException unused) {
                }
            }
        }

        void reportSurfaceDestroyed() {
            if (this.mSurfaceCreated) {
                this.mSurfaceCreated = false;
                this.mSurfaceHolder.ungetCallbacks();
                SurfaceHolder.Callback[] callbacks = this.mSurfaceHolder.getCallbacks();
                if (callbacks != null) {
                    for (SurfaceHolder.Callback callback : callbacks) {
                        callback.surfaceDestroyed(this.mSurfaceHolder);
                    }
                }
                onSurfaceDestroyed(this.mSurfaceHolder);
            }
        }

        void detach() {
            if (this.mDestroyed) {
                return;
            }
            this.mDestroyed = true;
            if (this.mIWallpaperEngine.mDisplayManager != null) {
                this.mIWallpaperEngine.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
            }
            if (this.mVisible) {
                this.mVisible = false;
                onVisibilityChanged(false);
            }
            reportSurfaceDestroyed();
            onDestroy();
            if (this.mCreated) {
                try {
                    WallpaperInputEventReceiver wallpaperInputEventReceiver = this.mInputEventReceiver;
                    if (wallpaperInputEventReceiver != null) {
                        wallpaperInputEventReceiver.dispose();
                        this.mInputEventReceiver = null;
                    }
                    this.mSession.remove(this.mWindow);
                } catch (RemoteException unused) {
                }
                this.mSurfaceHolder.mSurface.release();
                this.mCreated = false;
            }
        }
    }

    class IWallpaperEngineWrapper extends IWallpaperEngine.Stub implements HandlerCaller.Callback {
        private final HandlerCaller mCaller;
        final IWallpaperConnection mConnection;
        private final AtomicBoolean mDetached;
        final Display mDisplay;
        final int mDisplayId;
        final DisplayManager mDisplayManager;
        final Rect mDisplayPadding;
        Engine mEngine;
        final boolean mIsPreview;
        int mReqHeight;
        int mReqWidth;
        boolean mShownReported;
        final IBinder mWindowToken;
        final int mWindowType;

        IWallpaperEngineWrapper(WallpaperService wallpaperService, IWallpaperConnection iWallpaperConnection, IBinder iBinder, int i, boolean z, int i2, int i3, Rect rect, int i4) {
            Rect rect2 = new Rect();
            this.mDisplayPadding = rect2;
            this.mDetached = new AtomicBoolean();
            HandlerCaller handlerCaller = new HandlerCaller(wallpaperService, wallpaperService.getMainLooper(), this, true);
            this.mCaller = handlerCaller;
            this.mConnection = iWallpaperConnection;
            this.mWindowToken = iBinder;
            this.mWindowType = i;
            this.mIsPreview = z;
            this.mReqWidth = i2;
            this.mReqHeight = i3;
            rect2.set(rect);
            this.mDisplayId = i4;
            DisplayManager displayManager = (DisplayManager) WallpaperService.this.getSystemService(DisplayManager.class);
            this.mDisplayManager = displayManager;
            Display display = displayManager.getDisplay(i4);
            this.mDisplay = display;
            if (display == null) {
                throw new IllegalArgumentException("Cannot find display with id" + i4);
            }
            handlerCaller.sendMessage(handlerCaller.obtainMessage(10));
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setDesiredSize(int i, int i2) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageII(30, i, i2));
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setDisplayPadding(Rect rect) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageO(40, rect));
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setVisibility(boolean z) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageI(10010, z ? 1 : 0));
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setInAmbientMode(boolean z, long j) throws RemoteException {
            this.mCaller.sendMessage(this.mCaller.obtainMessageIO(50, z ? 1 : 0, Long.valueOf(j)));
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void dispatchPointer(MotionEvent motionEvent) {
            Engine engine = this.mEngine;
            if (engine != null) {
                engine.dispatchPointer(motionEvent);
            } else {
                motionEvent.recycle();
            }
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle) {
            Engine engine = this.mEngine;
            if (engine != null) {
                engine.mWindow.dispatchWallpaperCommand(str, i, i2, i3, bundle, false);
            }
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setZoomOut(float f) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageI(WallpaperService.MSG_ZOOM, Float.floatToIntBits(f)));
        }

        public void reportShown() {
            if (this.mShownReported) {
                return;
            }
            this.mShownReported = true;
            try {
                this.mConnection.engineShown(this);
            } catch (RemoteException e) {
                Log.w(WallpaperService.TAG, "Wallpaper host disappeared", e);
            }
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void requestWallpaperColors() {
            this.mCaller.sendMessage(this.mCaller.obtainMessage(10050));
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void destroy() {
            this.mCaller.sendMessage(this.mCaller.obtainMessage(20));
        }

        public void detach() {
            this.mDetached.set(true);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void scalePreview(Rect rect) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageO(WallpaperService.MSG_SCALE_PREVIEW, rect));
        }

        private void doDetachEngine() {
            WallpaperService.this.mActiveEngines.remove(this.mEngine);
            this.mEngine.detach();
        }

        @Override // com.android.internal.os.HandlerCaller.Callback
        public void executeMessage(Message message) throws Throwable {
            if (this.mDetached.get()) {
                if (WallpaperService.this.mActiveEngines.contains(this.mEngine)) {
                    doDetachEngine();
                    return;
                }
                return;
            }
            switch (message.what) {
                case 10:
                    try {
                        this.mConnection.attachEngine(this, this.mDisplayId);
                        Engine engineOnCreateEngine = WallpaperService.this.onCreateEngine();
                        this.mEngine = engineOnCreateEngine;
                        WallpaperService.this.mActiveEngines.add(engineOnCreateEngine);
                        engineOnCreateEngine.attach(this);
                        return;
                    } catch (RemoteException e) {
                        Log.w(WallpaperService.TAG, "Wallpaper host disappeared", e);
                        return;
                    }
                case 20:
                    doDetachEngine();
                    return;
                case 30:
                    this.mEngine.doDesiredSizeChanged(message.arg1, message.arg2);
                    return;
                case 40:
                    this.mEngine.doDisplayPaddingChanged((Rect) message.obj);
                    return;
                case 50:
                    this.mEngine.doAmbientModeChanged(message.arg1 != 0, ((Long) message.obj).longValue());
                    return;
                case 10000:
                    this.mEngine.updateSurface(true, false, false);
                    return;
                case 10010:
                    this.mEngine.doVisibilityChanged(message.arg1 != 0);
                    return;
                case 10020:
                    this.mEngine.doOffsetsChanged(true);
                    return;
                case 10025:
                    this.mEngine.doCommand((WallpaperCommand) message.obj);
                    return;
                case 10030:
                    this.mEngine.updateSurface(true, false, message.arg1 != 0);
                    this.mEngine.doOffsetsChanged(true);
                    return;
                case 10035:
                    return;
                case 10040:
                    MotionEvent motionEvent = (MotionEvent) message.obj;
                    if (motionEvent.getAction() == 2) {
                        synchronized (this.mEngine.mLock) {
                            if (this.mEngine.mPendingMove == motionEvent) {
                                this.mEngine.mPendingMove = null;
                            } else {
                                z = true;
                            }
                            break;
                        }
                    }
                    if (!z) {
                        this.mEngine.onTouchEvent(motionEvent);
                    }
                    motionEvent.recycle();
                    return;
                case 10050:
                    IWallpaperConnection iWallpaperConnection = this.mConnection;
                    if (iWallpaperConnection == null) {
                        return;
                    }
                    try {
                        iWallpaperConnection.onWallpaperColorsChanged(this.mEngine.onComputeColors(), this.mDisplayId);
                        return;
                    } catch (RemoteException unused) {
                        return;
                    }
                case WallpaperService.MSG_ZOOM /* 10100 */:
                    this.mEngine.setZoom(Float.intBitsToFloat(message.arg1));
                    return;
                case WallpaperService.MSG_SCALE_PREVIEW /* 10110 */:
                    this.mEngine.scalePreview((Rect) message.obj);
                    return;
                default:
                    Log.w(WallpaperService.TAG, "Unknown message type " + message.what);
                    return;
            }
        }
    }

    class IWallpaperServiceWrapper extends IWallpaperService.Stub {
        private IWallpaperEngineWrapper mEngineWrapper;
        private final WallpaperService mTarget;

        public IWallpaperServiceWrapper(WallpaperService wallpaperService) {
            this.mTarget = wallpaperService;
        }

        @Override // android.service.wallpaper.IWallpaperService
        public void attach(IWallpaperConnection iWallpaperConnection, IBinder iBinder, int i, boolean z, int i2, int i3, Rect rect, int i4) {
            this.mEngineWrapper = WallpaperService.this.new IWallpaperEngineWrapper(this.mTarget, iWallpaperConnection, iBinder, i, z, i2, i3, rect, i4);
        }

        @Override // android.service.wallpaper.IWallpaperService
        public void detach() {
            this.mEngineWrapper.detach();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < this.mActiveEngines.size(); i++) {
            this.mActiveEngines.get(i).detach();
        }
        this.mActiveEngines.clear();
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return new IWallpaperServiceWrapper(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print("State of wallpaper ");
        printWriter.print(this);
        printWriter.println(SettingsStringUtil.DELIMITER);
        for (int i = 0; i < this.mActiveEngines.size(); i++) {
            Engine engine = this.mActiveEngines.get(i);
            printWriter.print("  Engine ");
            printWriter.print(engine);
            printWriter.println(SettingsStringUtil.DELIMITER);
            engine.dump("    ", fileDescriptor, printWriter, strArr);
        }
    }
}
