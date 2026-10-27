package defpackage;

import android.accounts.Account;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.components.signin.a;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: u0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountManagerFacadeImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0213u0 extends i9<List<Account>> {
    public final /* synthetic */ a h;

    public C0213u0(a aVar) {
        this.h = aVar;
    }

    @Override // defpackage.i9
    public final List<Account> b() {
        ((by2) this.h.a).getClass();
        return Collections.unmodifiableList(Arrays.asList(new Account[0]));
    }

    @Override // defpackage.i9
    public final void l(List<Account> list) {
        a aVar = this.h;
        aVar.c.set(list);
        aVar.l();
    }
}
