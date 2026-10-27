package a.b.a.a;

import a.b.a.a.a;
import com.netinsight.sye.syeClient.internal.NativeSyePlayer;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a.b {
    public final /* synthetic */ a c;
    public final /* synthetic */ String d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(a aVar, String str, String str2) {
        super(str2);
        this.c = aVar;
        this.d = str;
    }

    @Override // a.b.a.a.a.b
    public void a() {
        NativeSyePlayer nativeSyePlayer = this.c.e;
        if (nativeSyePlayer != null) {
            nativeSyePlayer.a(1, this.d);
        }
    }
}
