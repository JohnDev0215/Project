package android.service.autofill.augmented;

import android.annotation.SystemApi;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.service.autofill.augmented.AugmentedAutofillService;
import android.service.autofill.augmented.PresentationParams;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.autofill.IAutofillWindowPresenter;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.pooled.PooledLambda;
import dalvik.system.CloseGuard;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class FillWindow implements AutoCloseable {
    private static final String TAG = FillWindow.class.getSimpleName();
    private Rect mBounds;
    private boolean mDestroyed;
    private View mFillView;
    private AugmentedAutofillService.AutofillProxy mProxy;
    private boolean mShowing;
    private boolean mUpdateCalled;
    private WindowManager mWm;
    private final Object mLock = new Object();
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final Handler mUiThreadHandler = new Handler(Looper.getMainLooper());

    public boolean update(PresentationParams.Area area, View view, long j) {
        if (AugmentedAutofillService.sDebug) {
            Log.d(TAG, "Updating " + area + " + with " + view);
        }
        Preconditions.checkNotNull(area);
        Preconditions.checkNotNull(area.proxy);
        Preconditions.checkNotNull(view);
        PresentationParams.SystemPopupPresentationParams smartSuggestionParams = area.proxy.getSmartSuggestionParams();
        if (smartSuggestionParams == null) {
            Log.w(TAG, "No SmartSuggestionParams");
            return false;
        }
        if (area.getBounds() == null) {
            Log.wtf(TAG, "No Rect on SmartSuggestionParams");
            return false;
        }
        synchronized (this.mLock) {
            checkNotDestroyedLocked();
            this.mProxy = area.proxy;
            this.mWm = (WindowManager) view.getContext().getSystemService(WindowManager.class);
            this.mFillView = view;
            view.setOnTouchListener(new View.OnTouchListener() { // from class: android.service.autofill.augmented.-$$Lambda$FillWindow$j5yljDJ4VfFL37B-iWRonGGOKN4
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    return this.f$0.lambda$update$0$FillWindow(view2, motionEvent);
                }
            });
            this.mShowing = false;
            this.mBounds = new Rect(area.getBounds());
            if (AugmentedAutofillService.sDebug) {
                Log.d(TAG, "Created FillWindow: params= " + smartSuggestionParams + " view=" + view);
            }
            this.mUpdateCalled = true;
            this.mDestroyed = false;
            this.mProxy.setFillWindow(this);
        }
        return true;
    }

    public /* synthetic */ boolean lambda$update$0$FillWindow(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 4) {
            return false;
        }
        if (AugmentedAutofillService.sVerbose) {
            Log.v(TAG, "Outside touch detected, hiding the window");
        }
        hide();
        return false;
    }

    void show() {
        if (AugmentedAutofillService.sDebug) {
            Log.d(TAG, "show()");
        }
        synchronized (this.mLock) {
            checkNotDestroyedLocked();
            if (this.mWm == null || this.mFillView == null) {
                throw new IllegalStateException("update() not called yet, or already destroyed()");
            }
            if (this.mProxy != null) {
                try {
                    this.mProxy.requestShowFillUi(this.mBounds.right - this.mBounds.left, this.mBounds.bottom - this.mBounds.top, null, new FillWindowPresenter(this));
                } catch (RemoteException e) {
                    Log.w(TAG, "Error requesting to show fill window", e);
                }
                this.mProxy.logEvent(2);
            }
        }
    }

    private void hide() {
        if (AugmentedAutofillService.sDebug) {
            Log.d(TAG, "hide()");
        }
        synchronized (this.mLock) {
            checkNotDestroyedLocked();
            if (this.mWm == null || this.mFillView == null) {
                throw new IllegalStateException("update() not called yet, or already destroyed()");
            }
            if (this.mProxy != null && this.mShowing) {
                try {
                    this.mProxy.requestHideFillUi();
                } catch (RemoteException e) {
                    Log.w(TAG, "Error requesting to hide fill window", e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleShow(WindowManager.LayoutParams layoutParams) {
        if (AugmentedAutofillService.sDebug) {
            Log.d(TAG, "handleShow()");
        }
        synchronized (this.mLock) {
            if (this.mWm != null && this.mFillView != null) {
                try {
                    layoutParams.flags |= 262144;
                    if (!this.mShowing) {
                        this.mWm.addView(this.mFillView, layoutParams);
                        this.mShowing = true;
                    } else {
                        this.mWm.updateViewLayout(this.mFillView, layoutParams);
                    }
                } catch (WindowManager.BadTokenException unused) {
                    if (AugmentedAutofillService.sDebug) {
                        Log.d(TAG, "Filed with token " + layoutParams.token + " gone.");
                    }
                } catch (IllegalStateException unused2) {
                    if (AugmentedAutofillService.sDebug) {
                        Log.d(TAG, "Exception showing window.");
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHide() {
        if (AugmentedAutofillService.sDebug) {
            Log.d(TAG, "handleHide()");
        }
        synchronized (this.mLock) {
            if (this.mWm != null && this.mFillView != null && this.mShowing) {
                try {
                    this.mWm.removeView(this.mFillView);
                    this.mShowing = false;
                } catch (IllegalStateException unused) {
                    if (AugmentedAutofillService.sDebug) {
                        Log.d(TAG, "Exception hiding window.");
                    }
                }
            }
        }
    }

    public void destroy() {
        if (AugmentedAutofillService.sDebug) {
            Log.d(TAG, "destroy(): mDestroyed=" + this.mDestroyed + " mShowing=" + this.mShowing + " mFillView=" + this.mFillView);
        }
        synchronized (this.mLock) {
            if (this.mDestroyed) {
                return;
            }
            if (this.mUpdateCalled) {
                this.mFillView.setOnClickListener(null);
                hide();
                this.mProxy.logEvent(3);
            }
            this.mDestroyed = true;
            this.mCloseGuard.close();
        }
    }

    protected void finalize() throws Throwable {
        try {
            this.mCloseGuard.warnIfOpen();
            destroy();
        } finally {
            super.finalize();
        }
    }

    private void checkNotDestroyedLocked() {
        if (this.mDestroyed) {
            throw new IllegalStateException("already destroyed()");
        }
    }

    public void dump(String str, PrintWriter printWriter) {
        synchronized (this) {
            printWriter.print(str);
            printWriter.print("destroyed: ");
            printWriter.println(this.mDestroyed);
            printWriter.print(str);
            printWriter.print("updateCalled: ");
            printWriter.println(this.mUpdateCalled);
            if (this.mFillView != null) {
                printWriter.print(str);
                printWriter.print("fill window: ");
                printWriter.println(this.mShowing ? "shown" : "hidden");
                printWriter.print(str);
                printWriter.print("fill view: ");
                printWriter.println(this.mFillView);
                printWriter.print(str);
                printWriter.print("mBounds: ");
                printWriter.println(this.mBounds);
                printWriter.print(str);
                printWriter.print("mWm: ");
                printWriter.println(this.mWm);
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        destroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class FillWindowPresenter extends IAutofillWindowPresenter.Stub {
        private final WeakReference<FillWindow> mFillWindowReference;

        FillWindowPresenter(FillWindow fillWindow) {
            this.mFillWindowReference = new WeakReference<>(fillWindow);
        }

        @Override // android.view.autofill.IAutofillWindowPresenter
        public void show(WindowManager.LayoutParams layoutParams, Rect rect, boolean z, int i) {
            if (AugmentedAutofillService.sDebug) {
                Log.d(FillWindow.TAG, "FillWindowPresenter.show()");
            }
            FillWindow fillWindow = this.mFillWindowReference.get();
            if (fillWindow != null) {
                fillWindow.mUiThreadHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.service.autofill.augmented.-$$Lambda$FillWindow$FillWindowPresenter$hdkNZGuYdsvcArvQ2SoMspO1EO8
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ((FillWindow) obj).handleShow((WindowManager.LayoutParams) obj2);
                    }
                }, fillWindow, layoutParams));
            }
        }

        @Override // android.view.autofill.IAutofillWindowPresenter
        public void hide(Rect rect) {
            if (AugmentedAutofillService.sDebug) {
                Log.d(FillWindow.TAG, "FillWindowPresenter.hide()");
            }
            FillWindow fillWindow = this.mFillWindowReference.get();
            if (fillWindow != null) {
                fillWindow.mUiThreadHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.service.autofill.augmented.-$$Lambda$FillWindow$FillWindowPresenter$EnBAJTZRgK05SBPnOQ9Edaq3VXs
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((FillWindow) obj).handleHide();
                    }
                }, fillWindow));
            }
        }
    }
}
