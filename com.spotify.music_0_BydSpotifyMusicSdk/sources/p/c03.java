package p;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;

/* JADX INFO: loaded from: classes.dex */
public final class c03 extends b03 {
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c03(com.google.android.play.core.assetpacks.a aVar, j23 j23Var, int i) {
        super(aVar, j23Var);
        this.d = i;
    }

    @Override // p.b03, p.a33
    public final void c(Bundle bundle, Bundle bundle2) {
        switch (this.d) {
            case 1:
                super.c(bundle, bundle2);
                this.b.b((ParcelFileDescriptor) bundle.getParcelable("chunk_file_descriptor"));
                break;
            default:
                super.c(bundle, bundle2);
                break;
        }
    }
}
