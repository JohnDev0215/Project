package defpackage;

import android.os.ParcelFileDescriptor;
import java.io.FileOutputStream;
import java.util.Objects;
import org.chromium.android_webview.services.AwVariationsSeedFetcher;

/* JADX INFO: renamed from: ms, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1184ms implements Runnable {
    public ParcelFileDescriptor s;
    public long t;
    public final /* synthetic */ C1239ns u;

    public RunnableC1184ms(C1239ns c1239ns, ParcelFileDescriptor parcelFileDescriptor, long j) {
        this.u = c1239ns;
        this.s = parcelFileDescriptor;
        this.t = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Objects.requireNonNull(this.u);
            AwVariationsSeedFetcher.b();
            if (this.u.c == null && !this.u.d) {
                this.u.c = AbstractC0626cr.f(AbstractC0626cr.e());
                this.u.d = this.u.c == null;
            }
            if (this.u.c != null && this.t < this.u.c.c) {
                AbstractC0626cr.b("Writing new seed to app's data directory");
                AbstractC0626cr.i(new FileOutputStream(this.s.getFileDescriptor()), this.u.c);
            }
        } finally {
            AbstractC0626cr.a(this.s);
            Objects.requireNonNull(this.u);
        }
    }
}
