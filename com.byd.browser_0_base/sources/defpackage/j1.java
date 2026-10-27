package defpackage;

import android.content.Context;
import android.os.UserManager;
import android.util.Log;
import defpackage.gy1;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: compiled from: AccountRestrictionPatternReceiver.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j1 extends i9<List<gy1>> {
    public final /* synthetic */ s62 h;
    public final /* synthetic */ k1 i;

    public j1(k1 k1Var, s62 s62Var) {
        this.i = k1Var;
        this.h = s62Var;
    }

    @Override // defpackage.i9
    public final List<gy1> b() {
        this.i.getClass();
        ArrayList arrayList = new ArrayList();
        try {
            Context context = xz.a;
            String[] stringArray = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName()).getStringArray("RestrictAccountsToPatterns");
            if (stringArray != null) {
                for (String str : stringArray) {
                    arrayList.add(new gy1(str));
                }
            }
        } catch (gy1.a e) {
            Log.e("cr_AccountRestriction", "Can't get account restriction patterns", e);
        }
        return arrayList;
    }

    @Override // defpackage.i9
    public final void l(List<gy1> list) {
        this.h.b(list);
    }
}
