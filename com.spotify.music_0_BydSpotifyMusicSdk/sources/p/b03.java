package p;

import android.os.Bundle;
import com.google.android.play.core.assetpacks.AssetPackException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class b03 extends z23 {
    public final j23 b;
    public final /* synthetic */ com.google.android.play.core.assetpacks.a c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b03(com.google.android.play.core.assetpacks.a aVar, j23 j23Var) {
        super("com.google.android.play.core.assetpacks.protocol.IAssetModuleServiceCallback");
        this.c = aVar;
        this.b = j23Var;
    }

    @Override // p.a33
    public void M(ArrayList arrayList) {
        this.c.d.c(this.b);
        com.google.android.play.core.assetpacks.a.g.e("onGetSessionStates", new Object[0]);
    }

    @Override // p.a33
    public void Q(Bundle bundle, Bundle bundle2) {
        this.c.e.c(this.b);
        com.google.android.play.core.assetpacks.a.g.e("onKeepAlive(%b)", Boolean.valueOf(bundle.getBoolean("keep_alive")));
    }

    @Override // p.a33
    public void V(Bundle bundle) {
        g03 g03Var = this.c.d;
        j23 j23Var = this.b;
        g03Var.c(j23Var);
        int i = bundle.getInt("error_code");
        com.google.android.play.core.assetpacks.a.g.c("onError(%d)", Integer.valueOf(i));
        j23Var.a(new AssetPackException(i));
    }

    @Override // p.a33
    public void c(Bundle bundle, Bundle bundle2) {
        this.c.d.c(this.b);
        com.google.android.play.core.assetpacks.a.g.e("onGetChunkFileDescriptor", new Object[0]);
    }
}
