package com.android.systemui.wm;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Display;
import android.view.IDisplayWindowListener;
import android.view.IWindowManager;
import com.android.systemui.util.SystemUIException;
import com.android.systemui.wm.DisplayChangeController;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class DisplayController {
    private static final String TAG = "DisplayController";
    private final DisplayChangeController mChangeController;
    private final Context mContext;
    private final IDisplayWindowListener mDisplayContainerListener;
    private final Handler mHandler;
    private final IWindowManager mWmService;
    private final SparseArray<DisplayRecord> mDisplays = new SparseArray<>();
    private final ArrayList<OnDisplaysChangedListener> mDisplayChangedListeners = new ArrayList<>();

    public interface OnDisplaysChangedListener {
        default void onDisplayAdded(int displayId) {
        }

        default void onDisplayConfigurationChanged(int displayId, Configuration newConfig) {
        }

        default void onDisplayRemoved(int displayId) {
        }

        default void onFixedRotationFinished(int displayId) {
        }

        default void onFixedRotationStarted(int displayId, int newRotation) {
        }
    }

    public Display getDisplay(int displayId) {
        return ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).getDisplay(displayId);
    }

    /* JADX INFO: renamed from: com.android.systemui.wm.DisplayController$1, reason: invalid class name */
    class AnonymousClass1 extends IDisplayWindowListener.Stub {
        AnonymousClass1() {
        }

        public void onDisplayAdded(final int displayId) {
            DisplayController.this.mHandler.post(new Runnable() { // from class: com.android.systemui.wm.-$$Lambda$DisplayController$1$Dk4cXF8f5K2H0y-wTGyvG_rjEq4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDisplayAdded$0$DisplayController$1(displayId);
                }
            });
        }

        public /* synthetic */ void lambda$onDisplayAdded$0$DisplayController$1(int i) {
            Log.d(DisplayController.TAG, "onDisplayAdded : " + i);
            synchronized (DisplayController.this.mDisplays) {
                if (DisplayController.this.mDisplays.get(i) != null) {
                    Log.e(DisplayController.TAG, "onDisplayAdded 1 return " + i);
                    return;
                }
                Display display = DisplayController.this.getDisplay(i);
                if (display == null) {
                    Log.e(DisplayController.TAG, "onDisplayAdded 2 return " + i);
                    return;
                }
                DisplayRecord displayRecord = new DisplayRecord(null);
                displayRecord.mDisplayId = i;
                displayRecord.mContext = i == 0 ? DisplayController.this.mContext : DisplayController.this.mContext.createDisplayContext(display);
                displayRecord.mDisplayLayout = new DisplayLayout(displayRecord.mContext, display);
                DisplayController.this.mDisplays.put(i, displayRecord);
                for (int i2 = 0; i2 < DisplayController.this.mDisplayChangedListeners.size(); i2++) {
                    Log.e(DisplayController.TAG, "onDisplayAdded notify " + i);
                    ((OnDisplaysChangedListener) DisplayController.this.mDisplayChangedListeners.get(i2)).onDisplayAdded(i);
                }
            }
        }

        public void onDisplayConfigurationChanged(final int displayId, final Configuration newConfig) {
            DisplayController.this.mHandler.post(new Runnable() { // from class: com.android.systemui.wm.-$$Lambda$DisplayController$1$YaaSnWIPkfZUem3NO81_ZlcHpTs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDisplayConfigurationChanged$1$DisplayController$1(displayId, newConfig);
                }
            });
        }

        public /* synthetic */ void lambda$onDisplayConfigurationChanged$1$DisplayController$1(int i, Configuration configuration) {
            synchronized (DisplayController.this.mDisplays) {
                DisplayRecord displayRecord = (DisplayRecord) DisplayController.this.mDisplays.get(i);
                if (displayRecord == null) {
                    Slog.w(DisplayController.TAG, "Skipping Display Configuration change on non-added display.");
                    return;
                }
                Display display = DisplayController.this.getDisplay(i);
                if (display != null) {
                    Context contextCreateDisplayContext = DisplayController.this.mContext;
                    if (i != 0) {
                        contextCreateDisplayContext = DisplayController.this.mContext.createDisplayContext(display);
                    }
                    displayRecord.mContext = contextCreateDisplayContext.createConfigurationContext(configuration);
                    displayRecord.mDisplayLayout = new DisplayLayout(displayRecord.mContext, display);
                    for (int i2 = 0; i2 < DisplayController.this.mDisplayChangedListeners.size(); i2++) {
                        ((OnDisplaysChangedListener) DisplayController.this.mDisplayChangedListeners.get(i2)).onDisplayConfigurationChanged(i, configuration);
                    }
                    return;
                }
                Slog.w(DisplayController.TAG, "Skipping Display Configuration change on invalid display. It may have been removed.");
            }
        }

        public void onDisplayRemoved(final int displayId) {
            DisplayController.this.mHandler.post(new Runnable() { // from class: com.android.systemui.wm.-$$Lambda$DisplayController$1$8MLG8skrQPP8fAigSK9PL2vFOsw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDisplayRemoved$2$DisplayController$1(displayId);
                }
            });
        }

        public /* synthetic */ void lambda$onDisplayRemoved$2$DisplayController$1(int i) {
            synchronized (DisplayController.this.mDisplays) {
                if (DisplayController.this.mDisplays.get(i) == null) {
                    return;
                }
                for (int size = DisplayController.this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                    ((OnDisplaysChangedListener) DisplayController.this.mDisplayChangedListeners.get(size)).onDisplayRemoved(i);
                }
                DisplayController.this.mDisplays.remove(i);
            }
        }

        public void onFixedRotationStarted(final int displayId, final int newRotation) {
            DisplayController.this.mHandler.post(new Runnable() { // from class: com.android.systemui.wm.-$$Lambda$DisplayController$1$hL125hd4wkNjyBSjEYJ4Kujfg4A
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFixedRotationStarted$3$DisplayController$1(displayId, newRotation);
                }
            });
        }

        public /* synthetic */ void lambda$onFixedRotationStarted$3$DisplayController$1(int i, int i2) {
            synchronized (DisplayController.this.mDisplays) {
                if (DisplayController.this.mDisplays.get(i) != null && DisplayController.this.getDisplay(i) != null) {
                    for (int size = DisplayController.this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                        ((OnDisplaysChangedListener) DisplayController.this.mDisplayChangedListeners.get(size)).onFixedRotationStarted(i, i2);
                    }
                    return;
                }
                Slog.w(DisplayController.TAG, "Skipping onFixedRotationStarted on unknown display, displayId=" + i);
            }
        }

        public void onFixedRotationFinished(final int displayId) {
            DisplayController.this.mHandler.post(new Runnable() { // from class: com.android.systemui.wm.-$$Lambda$DisplayController$1$9OJM4n2_t_vyd70ydLzL14Yk7Jc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFixedRotationFinished$4$DisplayController$1(displayId);
                }
            });
        }

        public /* synthetic */ void lambda$onFixedRotationFinished$4$DisplayController$1(int i) {
            synchronized (DisplayController.this.mDisplays) {
                if (DisplayController.this.mDisplays.get(i) != null && DisplayController.this.getDisplay(i) != null) {
                    for (int size = DisplayController.this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                        ((OnDisplaysChangedListener) DisplayController.this.mDisplayChangedListeners.get(size)).onFixedRotationFinished(i);
                    }
                    return;
                }
                Slog.w(DisplayController.TAG, "Skipping onFixedRotationFinished on unknown display, displayId=" + i);
            }
        }
    }

    @Inject
    public DisplayController(Context context, Handler mainHandler, IWindowManager wmService) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mDisplayContainerListener = anonymousClass1;
        this.mHandler = mainHandler;
        this.mContext = context;
        this.mWmService = wmService;
        this.mChangeController = new DisplayChangeController(mainHandler, wmService);
        try {
            wmService.registerDisplayWindowListener(anonymousClass1);
        } catch (RemoteException unused) {
            throw new SystemUIException("Unable to register hierarchy listener");
        }
    }

    public DisplayLayout getDisplayLayout(int displayId) {
        DisplayRecord displayRecord = this.mDisplays.get(displayId);
        if (displayRecord != null) {
            return displayRecord.mDisplayLayout;
        }
        return null;
    }

    public Context getDisplayContext(int displayId) {
        DisplayRecord displayRecord = this.mDisplays.get(displayId);
        if (displayRecord != null) {
            return displayRecord.mContext;
        }
        return null;
    }

    public void addDisplayWindowListener(OnDisplaysChangedListener listener) {
        synchronized (this.mDisplays) {
            if (this.mDisplayChangedListeners.contains(listener)) {
                return;
            }
            this.mDisplayChangedListeners.add(listener);
            for (int i = 0; i < this.mDisplays.size(); i++) {
                listener.onDisplayAdded(this.mDisplays.keyAt(i));
            }
        }
    }

    public void removeDisplayWindowListener(OnDisplaysChangedListener listener) {
        synchronized (this.mDisplays) {
            this.mDisplayChangedListeners.remove(listener);
        }
    }

    public void addDisplayChangingController(DisplayChangeController.OnDisplayChangingListener controller) {
        this.mChangeController.addRotationListener(controller);
    }

    public void removeDisplayChangingController(DisplayChangeController.OnDisplayChangingListener controller) {
        this.mChangeController.removeRotationListener(controller);
    }

    private static class DisplayRecord {
        Context mContext;
        int mDisplayId;
        DisplayLayout mDisplayLayout;

        private DisplayRecord() {
        }

        /* synthetic */ DisplayRecord(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
