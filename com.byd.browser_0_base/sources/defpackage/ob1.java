package defpackage;

import J.N;
import android.content.Context;
import android.text.SpannableString;
import defpackage.iq2;
import defpackage.lk1;
import java.util.HashMap;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ManagementMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ob1 {
    public final mk1 a;
    public final PropertyModel b;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, android.text.SpannableString] */
    public ob1(lk1.b bVar, Profile profile) {
        this.a = bVar;
        HashMap mapE = PropertyModel.e(qb1.d);
        PropertyModel.n nVar = qb1.b;
        boolean zMmSLoR8I = N.MmSLoR8I(profile);
        int i = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = zMmSLoR8I;
        mapE.put(nVar, aVar);
        PropertyModel.r<String> rVar = qb1.a;
        String strMR6Llk86 = profile != null ? N.MR6Llk86(profile) : "";
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = strMR6Llk86;
        mapE.put(rVar, gVar);
        PropertyModel.r<SpannableString> rVar2 = qb1.c;
        Context contextA = bVar.a();
        ?? A = iq2.a(contextA.getString(R$string.management_learn_more), new iq2.a(new un1(contextA, new ko0(13, this)), "<LINK>", "</LINK>"));
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = A;
        mapE.put(rVar2, gVar2);
        this.b = new PropertyModel(mapE);
    }
}
