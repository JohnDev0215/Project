package org.chromium.chrome.browser.profiles;

import J.N;
import android.util.Log;
import defpackage.C0175b;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.cookies.CookiesFetcher;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.content_public.browser.BrowserContextHandle;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class Profile implements BrowserContextHandle {
    public final OTRProfileID a;
    public long b;

    public Profile(long j) {
        this.b = j;
        if (N.MEt51B0E(j, this)) {
            this.a = (OTRProfileID) N.MyopTl49(this.b, this);
        } else {
            this.a = null;
        }
    }

    public static Profile b(WebContents webContents) {
        return (Profile) N.MvvJTucy(webContents);
    }

    public static int c(Profile profile) {
        if (profile.h()) {
            return N.MWNOGDsM(profile.b, profile) ? 1 : 4;
        }
        return 0;
    }

    @CalledByNative
    public static Profile create(long j) {
        return new Profile(j);
    }

    public static Profile d() {
        Object obj = ThreadUtils.a;
        if (ProfileManager.b) {
            return (Profile) N.M_3GXkeQ();
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            StringBuilder sbM = C0175b.m("  ", i, "   :");
            sbM.append(stackTrace[i]);
            Log.e("stack", sbM.toString());
        }
        throw new IllegalStateException("Browser hasn't finished initialization yet!");
    }

    @Override // org.chromium.content_public.browser.BrowserContextHandle
    public final long a() {
        return N.MgjF0wyD(this.b);
    }

    public final Profile e(OTRProfileID oTRProfileID) {
        return (Profile) N.MIzCSj22(this.b, this, oTRProfileID, true);
    }

    public final Profile f(boolean z) {
        return (Profile) N.Mhxya0Qy(this.b, this, z);
    }

    public final ProfileKey g() {
        return (ProfileKey) N.MjGj0xKY(this.b, this);
    }

    @CalledByNative
    public final long getNativePointer() {
        return this.b;
    }

    public final boolean h() {
        return this.a != null;
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.b = 0L;
        if (this.a != null) {
            CookiesFetcher.a();
        }
        Iterator<ProfileManager.a> it = ProfileManager.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ProfileManager.a) aVar.next()).e(this);
            }
        }
    }
}
