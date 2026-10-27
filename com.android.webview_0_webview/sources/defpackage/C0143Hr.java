package defpackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: Hr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0143Hr extends AbstractC0125Gr {
    public Px a;

    public C0143Hr(Px px) {
        this.a = px;
    }

    @Override // defpackage.AbstractC0125Gr
    public List a() {
        ArrayList arrayList = new ArrayList();
        for (File file : this.a.k(Px.e)) {
            b(arrayList, file.getName(), EnumC0231Mq.PENDING);
        }
        for (File file2 : this.a.k(Px.g)) {
            b(arrayList, file2.getName(), EnumC0231Mq.PENDING_USER_REQUESTED);
        }
        for (File file3 : this.a.k(Px.h)) {
            b(arrayList, file3.getName(), EnumC0231Mq.SKIPPED);
        }
        return arrayList;
    }

    public final void b(List list, String str, EnumC0231Mq enumC0231Mq) {
        String strG = Px.g(str);
        if (strG != null) {
            C0249Nq c0249Nq = new C0249Nq(strG);
            c0249Nq.a = enumC0231Mq;
            list.add(c0249Nq);
        }
    }
}
