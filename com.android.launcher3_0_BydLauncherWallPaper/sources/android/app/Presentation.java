package android.app;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Presentation extends Dialog {
    private static final int MSG_CANCEL = 1;
    private static final String TAG = "Presentation";
    private final Display mDisplay;
    private final DisplayManager.DisplayListener mDisplayListener;
    private final DisplayManager mDisplayManager;
    private final Handler mHandler;
    private final IBinder mToken;

    public void onDisplayChanged() {
    }

    public void onDisplayRemoved() {
    }

    public Presentation(Context context, Display display) {
        this(context, display, 0);
    }

    public Presentation(Context context, Display display, int i) {
        super(createPresentationContext(context, display, i), i, false);
        Binder binder = new Binder();
        this.mToken = binder;
        this.mHandler = new Handler((Looper) Objects.requireNonNull(Looper.myLooper(), "Presentation must be constructed on a looper thread."));
        this.mDisplayListener = new DisplayManager.DisplayListener() { // from class: android.app.Presentation.2
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int i2) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int i2) {
                if (i2 == Presentation.this.mDisplay.getDisplayId()) {
                    Presentation.this.handleDisplayRemoved();
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int i2) {
                if (i2 == Presentation.this.mDisplay.getDisplayId()) {
                    Presentation.this.handleDisplayChanged();
                }
            }
        };
        this.mDisplay = display;
        this.mDisplayManager = (DisplayManager) getContext().getSystemService(Context.DISPLAY_SERVICE);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.token = binder;
        window.setAttributes(attributes);
        window.setGravity(119);
        window.setType(getWindowType(-1, display));
        setCanceledOnTouchOutside(false);
    }

    public Display getDisplay() {
        return this.mDisplay;
    }

    public Resources getResources() {
        return getContext().getResources();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.mDisplayManager.registerDisplayListener(this.mDisplayListener, this.mHandler);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
        super.onStop();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDisplayRemoved() {
        onDisplayRemoved();
        cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDisplayChanged() {
        onDisplayChanged();
    }

    private boolean isConfigurationStillValid() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mDisplay.getMetrics(displayMetrics);
        return displayMetrics.equalsPhysical(getResources().getDisplayMetrics());
    }

    private static Context createPresentationContext(Context context, Display display, int i) {
        if (context == null) {
            throw new IllegalArgumentException("outerContext must not be null");
        }
        if (display == null) {
            throw new IllegalArgumentException("display must not be null");
        }
        Context contextCreateDisplayContext = context.createDisplayContext(display);
        if (i == 0) {
            TypedValue typedValue = new TypedValue();
            contextCreateDisplayContext.getTheme().resolveAttribute(16843712, typedValue, true);
            i = typedValue.resourceId;
        }
        final WindowManagerImpl windowManagerImplCreatePresentationWindowManager = ((WindowManagerImpl) context.getSystemService(Context.WINDOW_SERVICE)).createPresentationWindowManager(contextCreateDisplayContext);
        return new ContextThemeWrapper(contextCreateDisplayContext, i) { // from class: android.app.Presentation.1
            @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
            public Object getSystemService(String str) {
                if (Context.WINDOW_SERVICE.equals(str)) {
                    return windowManagerImplCreatePresentationWindowManager;
                }
                return super.getSystemService(str);
            }
        };
    }

    private static int getWindowType(int i, Display display) {
        return i != -1 ? i : (display.getFlags() & 4) != 0 ? 2030 : 2037;
    }
}
