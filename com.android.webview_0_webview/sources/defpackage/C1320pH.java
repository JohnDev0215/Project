package defpackage;

import org.chromium.base.Callback;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: pH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1320pH implements Callback {
    public final /* synthetic */ long a;
    public final /* synthetic */ AH b;
    public final /* synthetic */ BH c;
    public final /* synthetic */ MediaDrmBridge d;

    public C1320pH(MediaDrmBridge mediaDrmBridge, long j, AH ah, BH bh) {
        this.d = mediaDrmBridge;
        this.a = j;
        this.b = ah;
        this.c = bh;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            MediaDrmBridge.l(this.d, this.b, this.c.b, this.a);
        } else {
            this.d.t(this.a, "Fail to update persistent storage");
        }
    }
}
