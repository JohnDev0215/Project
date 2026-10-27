package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.language.settings.c;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LanguagePickerMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m51 {
    public final em3 a;
    public final a8 b;
    public final PropertyModel c;
    public final ya1 d;
    public String e;
    public int f;

    public m51(PropertyModel propertyModel, ya1 ya1Var, em3 em3Var, a8 a8Var) {
        this.a = em3Var;
        this.b = a8Var;
        this.c = propertyModel;
        this.d = ya1Var;
        this.e = em3Var.c();
    }

    /* JADX WARN: Type inference failed for: r3v8, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [T, l51] */
    /* JADX WARN: Type inference failed for: r8v0, types: [T, java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v0, types: [T, java.lang.String] */
    public final void a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        boolean zK = false;
        for (String str : list) {
            this.b.getClass();
            f51 f51Var = (f51) c.b().a.get(str);
            b51 b51Var = f51Var == null ? null : new b51(f51Var.a, f51Var.b, f51Var.c);
            if (b51Var != null) {
                HashMap mapE = PropertyModel.e(h51.f);
                PropertyModel.r<String> rVar = h51.a;
                int i = zj.a;
                PropertyModel.g gVar = new PropertyModel.g();
                ?? r8 = b51Var.a;
                gVar.a = r8;
                mapE.put(rVar, gVar);
                PropertyModel.r<String> rVar2 = h51.b;
                PropertyModel.g gVar2 = new PropertyModel.g();
                gVar2.a = b51Var.b;
                mapE.put(rVar2, gVar2);
                PropertyModel.r<String> rVar3 = h51.c;
                PropertyModel.g gVar3 = new PropertyModel.g();
                gVar3.a = b51Var.c;
                mapE.put(rVar3, gVar3);
                PropertyModel.n nVar = h51.d;
                boolean zEquals = TextUtils.equals(r8, this.e);
                PropertyModel.a aVar = new PropertyModel.a();
                aVar.a = zEquals;
                mapE.put(nVar, aVar);
                PropertyModel.r<Callback<String>> rVar4 = h51.e;
                ?? l51Var = new l51(0, this);
                PropertyModel.g gVar4 = new PropertyModel.g();
                gVar4.a = l51Var;
                mapE.put(rVar4, gVar4);
                PropertyModel propertyModel = new PropertyModel(mapE);
                arrayList.add(new xa1(1, propertyModel));
                zK |= propertyModel.k(nVar);
            }
        }
        this.d.A(arrayList);
        this.c.l(n51.c, zK);
    }
}
