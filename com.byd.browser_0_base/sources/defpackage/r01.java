package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.RenderFrameHost;

/* JADX INFO: compiled from: InstalledAppProviderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class r01 implements a21<q01> {
    public final RenderFrameHost e;

    public r01(RenderFrameHost renderFrameHost) {
        this.e = renderFrameHost;
    }

    @Override // defpackage.a21
    public final y11 d() {
        Profile profileB = Profile.b(n3.R(this.e));
        RenderFrameHost renderFrameHost = this.e;
        synchronized (j11.a) {
            if (j11.b == null) {
                AppHooks.get().getClass();
                j11.b = new j11();
            }
        }
        j11 j11Var = j11.b;
        Objects.requireNonNull(j11Var);
        return new w01(profileB, renderFrameHost, new c01(10, j11Var));
    }
}
