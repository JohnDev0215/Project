package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: WebContentsFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class pq3 {

    /* JADX INFO: compiled from: WebContentsFactory.java */
    public static class a extends RuntimeException {
        public a() {
            super("vvv This is where WebContents was created. vvv");
        }
    }

    public static WebContents a(Profile profile, boolean z) {
        return (WebContents) N.MDMZjIJS(profile, z, false, new a());
    }
}
