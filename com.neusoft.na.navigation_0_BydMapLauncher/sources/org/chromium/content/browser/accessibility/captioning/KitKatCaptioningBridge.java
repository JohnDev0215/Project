package org.chromium.content.browser.accessibility.captioning;

import android.content.Context;
import android.view.accessibility.CaptioningManager;
import java.util.Locale;
import org.chromium.content.browser.accessibility.captioning.SystemCaptioningBridge;

/* JADX INFO: loaded from: classes3.dex */
public class KitKatCaptioningBridge implements SystemCaptioningBridge {
    private static KitKatCaptioningBridge sKitKatCaptioningBridge;
    private final CaptioningManager mCaptioningManager;
    private final CaptioningManager.CaptioningChangeListener mCaptioningChangeListener = new KitKatCaptioningChangeListener();
    private final CaptioningChangeDelegate mCaptioningChangeDelegate = new CaptioningChangeDelegate();

    private class KitKatCaptioningChangeListener extends CaptioningManager.CaptioningChangeListener {
        private KitKatCaptioningChangeListener() {
        }

        @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
        public void onEnabledChanged(boolean z) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onEnabledChanged(z);
        }

        @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
        public void onFontScaleChanged(float f) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onFontScaleChanged(f);
        }

        @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
        public void onLocaleChanged(Locale locale) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onLocaleChanged(locale);
        }

        @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
        public void onUserStyleChanged(CaptioningManager.CaptionStyle captionStyle) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onUserStyleChanged(KitKatCaptioningBridge.this.getCaptioningStyleFrom(captionStyle));
        }
    }

    public static KitKatCaptioningBridge getInstance(Context context) {
        if (sKitKatCaptioningBridge == null) {
            sKitKatCaptioningBridge = new KitKatCaptioningBridge(context);
        }
        return sKitKatCaptioningBridge;
    }

    private KitKatCaptioningBridge(Context context) {
        this.mCaptioningManager = (CaptioningManager) context.getApplicationContext().getSystemService("captioning");
    }

    private void syncToDelegate() {
        this.mCaptioningChangeDelegate.onEnabledChanged(this.mCaptioningManager.isEnabled());
        this.mCaptioningChangeDelegate.onFontScaleChanged(this.mCaptioningManager.getFontScale());
        this.mCaptioningChangeDelegate.onLocaleChanged(this.mCaptioningManager.getLocale());
        this.mCaptioningChangeDelegate.onUserStyleChanged(getCaptioningStyleFrom(this.mCaptioningManager.getUserStyle()));
    }

    @Override // org.chromium.content.browser.accessibility.captioning.SystemCaptioningBridge
    public void syncToListener(SystemCaptioningBridge.SystemCaptioningBridgeListener systemCaptioningBridgeListener) {
        if (!this.mCaptioningChangeDelegate.hasActiveListener()) {
            syncToDelegate();
        }
        this.mCaptioningChangeDelegate.notifyListener(systemCaptioningBridgeListener);
    }

    @Override // org.chromium.content.browser.accessibility.captioning.SystemCaptioningBridge
    public void addListener(SystemCaptioningBridge.SystemCaptioningBridgeListener systemCaptioningBridgeListener) {
        if (!this.mCaptioningChangeDelegate.hasActiveListener()) {
            this.mCaptioningManager.addCaptioningChangeListener(this.mCaptioningChangeListener);
            syncToDelegate();
        }
        this.mCaptioningChangeDelegate.addListener(systemCaptioningBridgeListener);
        this.mCaptioningChangeDelegate.notifyListener(systemCaptioningBridgeListener);
    }

    @Override // org.chromium.content.browser.accessibility.captioning.SystemCaptioningBridge
    public void removeListener(SystemCaptioningBridge.SystemCaptioningBridgeListener systemCaptioningBridgeListener) {
        this.mCaptioningChangeDelegate.removeListener(systemCaptioningBridgeListener);
        if (this.mCaptioningChangeDelegate.hasActiveListener()) {
            return;
        }
        this.mCaptioningManager.removeCaptioningChangeListener(this.mCaptioningChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CaptioningStyle getCaptioningStyleFrom(CaptioningManager.CaptionStyle captionStyle) {
        return CaptioningStyle.createFrom(captionStyle);
    }
}
