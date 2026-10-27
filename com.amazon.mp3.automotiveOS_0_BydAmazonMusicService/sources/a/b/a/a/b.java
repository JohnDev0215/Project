package a.b.a.a;

import a.b.a.a.a;
import com.netinsight.sye.syeClient.internal.NativeSyePlayer;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a.b {
    public final /* synthetic */ a c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(a aVar, String str) {
        super(str);
        this.c = aVar;
    }

    @Override // a.b.a.a.a.b
    public void a() {
        NativeSyePlayer nativeSyePlayer = this.c.e;
        if (nativeSyePlayer != null) {
            nativeSyePlayer.poll(nativeSyePlayer.f762a);
        }
        this.c.t = false;
    }
}
