package defpackage;

import android.graphics.Rect;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: TabWebContentsDelegateAndroid.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class k53 extends WebContentsDelegateAndroid {
    public abstract boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z);

    public boolean canShowAppBanners() {
        return !(this instanceof SearchActivity.b.a);
    }

    public String getManifestScope() {
        return null;
    }

    public boolean isCustomTab() {
        return false;
    }

    public boolean isForceDarkWebContentEnabled() {
        return false;
    }

    public boolean isInstalledWebappDelegateGeolocation() {
        return false;
    }

    public boolean isModalContextMenu() {
        return true;
    }

    public boolean isNightModeEnabled() {
        return false;
    }

    public boolean isPictureInPictureEnabled() {
        return false;
    }

    public abstract void setOverlayMode(boolean z);

    public boolean shouldEnableEmbeddedMediaExperience() {
        return false;
    }

    public abstract boolean shouldResumeRequestsForCreatedWindow();
}
