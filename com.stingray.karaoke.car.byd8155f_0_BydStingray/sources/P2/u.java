package p2;

import android.media.AudioTrack;
import l3.C1764u;
import n2.C1924y;

/* JADX INFO: loaded from: classes.dex */
public final class u extends AudioTrack.StreamEventCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h3.y f20775a;

    public u(h3.y yVar) {
        this.f20775a = yVar;
    }

    @Override // android.media.AudioTrack.StreamEventCallback
    public final void onDataRequest(AudioTrack audioTrack, int i7) {
        v vVar;
        C1764u c1764u;
        C1924y c1924y;
        h3.y yVar = this.f20775a;
        if (audioTrack.equals(((v) yVar.o).f20830v) && (c1764u = (vVar = (v) yVar.o).f20826r) != null && vVar.f20799V && (c1924y = ((x) c1764u.f18608m).f22132Q) != null) {
            c1924y.a();
        }
    }

    @Override // android.media.AudioTrack.StreamEventCallback
    public final void onPresentationEnded(AudioTrack audioTrack) {
        h3.y yVar = this.f20775a;
        if (audioTrack.equals(((v) yVar.o).f20830v)) {
            ((v) yVar.o).f20798U = true;
        }
    }

    @Override // android.media.AudioTrack.StreamEventCallback
    public final void onTearDown(AudioTrack audioTrack) {
        v vVar;
        C1764u c1764u;
        C1924y c1924y;
        h3.y yVar = this.f20775a;
        if (audioTrack.equals(((v) yVar.o).f20830v) && (c1764u = (vVar = (v) yVar.o).f20826r) != null && vVar.f20799V && (c1924y = ((x) c1764u.f18608m).f22132Q) != null) {
            c1924y.a();
        }
    }
}
