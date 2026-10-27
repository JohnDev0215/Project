package org.chromium.content.browser;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.view.WindowManager;
import cerence.ark.assistant.controller.domain.hmi.HMIRequestsEnum;
import java.util.Iterator;
import org.chromium.base.Log;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.gfx.DeviceDisplayInfo;

/* JADX INFO: loaded from: classes3.dex */
public class ScreenOrientationListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "cr.ScreenOrientation";
    private static ScreenOrientationListener sInstance;
    private Context mAppContext;
    private int mOrientation;
    private final ObserverList<ScreenOrientationObserver> mObservers = new ObserverList<>();
    private ScreenOrientationListenerBackend mBackend = new ScreenOrientationDisplayListener();

    private interface ScreenOrientationListenerBackend {
        void startAccurateListening();

        void startListening();

        void stopAccurateListening();

        void stopListening();
    }

    public interface ScreenOrientationObserver {
        void onScreenOrientationChanged(int i);
    }

    private class ScreenOrientationConfigurationListener implements ScreenOrientationListenerBackend, ComponentCallbacks {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long POLLING_DELAY = 500;
        private int mAccurateCount;

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }

        private ScreenOrientationConfigurationListener() {
            this.mAccurateCount = 0;
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void startListening() {
            ScreenOrientationListener.this.mAppContext.registerComponentCallbacks(this);
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void stopListening() {
            ScreenOrientationListener.this.mAppContext.unregisterComponentCallbacks(this);
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void startAccurateListening() {
            int i = this.mAccurateCount + 1;
            this.mAccurateCount = i;
            if (i > 1) {
                return;
            }
            ThreadUtils.postOnUiThreadDelayed(new Runnable() { // from class: org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationConfigurationListener.1
                @Override // java.lang.Runnable
                public void run() {
                    this.onConfigurationChanged(null);
                    if (this.mAccurateCount < 1) {
                        return;
                    }
                    ThreadUtils.postOnUiThreadDelayed(this, ScreenOrientationConfigurationListener.POLLING_DELAY);
                }
            }, POLLING_DELAY);
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void stopAccurateListening() {
            this.mAccurateCount--;
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
            ScreenOrientationListener.this.notifyObservers();
        }
    }

    private class ScreenOrientationDisplayListener implements ScreenOrientationListenerBackend, DisplayManager.DisplayListener {
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void startAccurateListening() {
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void stopAccurateListening() {
        }

        private ScreenOrientationDisplayListener() {
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void startListening() {
            ((DisplayManager) ScreenOrientationListener.this.mAppContext.getSystemService(HMIRequestsEnum.DISPLAY)).registerDisplayListener(this, null);
        }

        @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationListenerBackend
        public void stopListening() {
            ((DisplayManager) ScreenOrientationListener.this.mAppContext.getSystemService(HMIRequestsEnum.DISPLAY)).unregisterDisplayListener(this);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            ScreenOrientationListener.this.notifyObservers();
        }
    }

    public static ScreenOrientationListener getInstance() {
        ThreadUtils.assertOnUiThread();
        if (sInstance == null) {
            sInstance = new ScreenOrientationListener();
        }
        return sInstance;
    }

    private ScreenOrientationListener() {
    }

    public void addObserver(final ScreenOrientationObserver screenOrientationObserver, Context context) {
        if (this.mAppContext == null) {
            this.mAppContext = context.getApplicationContext();
        }
        if (!this.mObservers.addObserver(screenOrientationObserver)) {
            Log.w(TAG, "Adding an observer that is already present!", new Object[0]);
            return;
        }
        if (this.mObservers.size() == 1) {
            updateOrientation();
            this.mBackend.startListening();
        }
        ThreadUtils.assertOnUiThread();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.ScreenOrientationListener.1
            @Override // java.lang.Runnable
            public void run() {
                screenOrientationObserver.onScreenOrientationChanged(ScreenOrientationListener.this.mOrientation);
            }
        });
    }

    public void removeObserver(ScreenOrientationObserver screenOrientationObserver) {
        if (!this.mObservers.removeObserver(screenOrientationObserver)) {
            Log.w(TAG, "Removing an inexistent observer!", new Object[0]);
        } else if (this.mObservers.isEmpty()) {
            this.mBackend.stopListening();
        }
    }

    public void startAccurateListening() {
        this.mBackend.startAccurateListening();
    }

    public void stopAccurateListening() {
        this.mBackend.stopAccurateListening();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObservers() {
        int i = this.mOrientation;
        updateOrientation();
        if (this.mOrientation == i) {
            return;
        }
        DeviceDisplayInfo.create(this.mAppContext).updateNativeSharedDisplayInfo();
        Iterator<ScreenOrientationObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onScreenOrientationChanged(this.mOrientation);
        }
    }

    private void updateOrientation() {
        int rotation = ((WindowManager) this.mAppContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 0) {
            this.mOrientation = 0;
            return;
        }
        if (rotation == 1) {
            this.mOrientation = 90;
        } else if (rotation == 2) {
            this.mOrientation = 180;
        } else {
            if (rotation == 3) {
                this.mOrientation = -90;
                return;
            }
            throw new IllegalStateException("Display.getRotation() shouldn't return that value");
        }
    }
}
