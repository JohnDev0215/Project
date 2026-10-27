package b.a.k;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class j extends i {
    public j(Context context, boolean z, int i, int i2) {
        super(context, z, i);
        super.e();
        this.f = "parent=" + i2 + " and " + this.f;
    }

    @Override // b.a.k.i
    protected List<String> d() {
        List<String> listD = super.d();
        listD.add("parent");
        return listD;
    }

    @Override // b.a.k.i
    protected void e() {
    }
}
