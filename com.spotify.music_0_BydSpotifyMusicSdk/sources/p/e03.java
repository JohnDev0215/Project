package p;

import android.os.Bundle;
import com.google.android.play.core.assetpacks.AssetPackException;
import com.google.android.play.core.assetpacks.zzbn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class e03 extends b03 {
    public final /* synthetic */ int d;
    public final /* synthetic */ com.google.android.play.core.assetpacks.a e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e03(com.google.android.play.core.assetpacks.a aVar, j23 j23Var, int i) {
        super(aVar, j23Var);
        this.d = i;
        this.e = aVar;
    }

    @Override // p.b03, p.a33
    public final void M(ArrayList arrayList) {
        switch (this.d) {
            case 0:
                super.M(arrayList);
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle = (Bundle) it.next();
                    com.google.android.play.core.assetpacks.a aVar = this.e;
                    g13 g13Var = aVar.b;
                    ArrayList<String> arrayList3 = new ArrayList();
                    zb0 zb0Var = zb0.r;
                    ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
                    HashMap map = new HashMap();
                    int size = stringArrayList.size();
                    for (int i = 0; i < size; i++) {
                        String str = stringArrayList.get(i);
                        map.put(str, zzbn.a(bundle, str, g13Var, aVar.c, zb0Var));
                    }
                    for (String str2 : arrayList3) {
                        map.put(str2, new zzbn(str2, 4, 0, 0L, 0L, (int) Math.rint(0.0d), 1, "", ""));
                    }
                    bundle.getLong("total_bytes_to_download");
                    zzbn zzbnVar = (zzbn) map.values().iterator().next();
                    if (zzbnVar == null) {
                        com.google.android.play.core.assetpacks.a.g.c("onGetSessionStates: Bundle contained no pack.", new Object[0]);
                    }
                    int i2 = zzbnVar.b;
                    if (i2 == 1 || i2 == 7 || i2 == 2 || i2 == 3) {
                        arrayList2.add(zzbnVar.a);
                    }
                }
                this.b.b(arrayList2);
                break;
            default:
                super.M(arrayList);
                break;
        }
    }

    @Override // p.b03, p.a33
    public final void Q(Bundle bundle, Bundle bundle2) {
        switch (this.d) {
            case 1:
                super.Q(bundle, bundle2);
                com.google.android.play.core.assetpacks.a aVar = this.e;
                if (!aVar.f.compareAndSet(true, false)) {
                    com.google.android.play.core.assetpacks.a.g.f("Expected keepingAlive to be true, but was false.", new Object[0]);
                }
                if (bundle.getBoolean("keep_alive")) {
                    aVar.e();
                }
                break;
            default:
                super.Q(bundle, bundle2);
                break;
        }
    }

    @Override // p.b03, p.a33
    public final void V(Bundle bundle) {
        switch (this.d) {
            case 1:
                g03 g03Var = this.e.e;
                j23 j23Var = this.b;
                g03Var.c(j23Var);
                int i = bundle.getInt("error_code");
                com.google.android.play.core.assetpacks.a.g.c("onError(%d)", Integer.valueOf(i));
                j23Var.a(new AssetPackException(i));
                break;
            default:
                super.V(bundle);
                break;
        }
    }
}
