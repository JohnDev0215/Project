package defpackage;

import android.os.Build;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: wH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1704wH implements Callback {
    public final AH a;
    public final long b;
    public final boolean c;
    public final /* synthetic */ MediaDrmBridge d;

    public C1704wH(MediaDrmBridge mediaDrmBridge, AH ah, long j, boolean z) {
        this.d = mediaDrmBridge;
        this.a = ah;
        this.b = j;
        this.c = z;
    }

    @Override // org.chromium.base.Callback
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(Boolean bool) {
        if (!bool.booleanValue()) {
            this.d.t(this.b, "failed to update key after response accepted");
            return;
        }
        this.a.c();
        MediaDrmBridge.g(this.d, this.b);
        if (this.c || Build.VERSION.SDK_INT >= 23) {
            return;
        }
        this.d.v(this.a, ((ArrayList) MediaDrmBridge.c(0)).toArray(), true, this.c);
    }
}
