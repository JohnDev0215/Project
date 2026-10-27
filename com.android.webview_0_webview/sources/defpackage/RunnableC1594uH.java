package defpackage;

import android.media.MediaDrm;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: uH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1594uH implements Runnable {
    public final /* synthetic */ AH s;
    public final /* synthetic */ boolean t;
    public final /* synthetic */ List u;
    public final /* synthetic */ boolean v;
    public final /* synthetic */ C1649vH w;

    public RunnableC1594uH(C1649vH c1649vH, AH ah, boolean z, List list, boolean z2) {
        this.w = c1649vH;
        this.s = ah;
        this.t = z;
        this.u = list;
        this.v = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.c();
        C1649vH c1649vH = this.w;
        MediaDrmBridge mediaDrmBridge = c1649vH.a;
        AH ah = this.s;
        List<MediaDrm.KeyStatus> list = this.u;
        Objects.requireNonNull(c1649vH);
        ArrayList arrayList = new ArrayList();
        for (MediaDrm.KeyStatus keyStatus : list) {
            arrayList.add(new MediaDrmBridge.KeyStatus(keyStatus.getKeyId(), keyStatus.getStatusCode(), null));
        }
        mediaDrmBridge.v(ah, arrayList.toArray(), this.t, this.v);
    }
}
