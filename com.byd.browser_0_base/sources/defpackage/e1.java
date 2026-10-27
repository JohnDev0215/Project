package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.b1;
import defpackage.di1;
import java.util.HashMap;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: compiled from: AccountPickerDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e1 {
    public final b1 a;
    public final bi1 b;
    public final PropertyModel c;

    /* JADX WARN: Type inference failed for: r0v2, types: [T, androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, d1] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.String] */
    public e1(Context context, b1.a aVar, bi1 bi1Var) {
        ?? r0 = (RecyclerView) LayoutInflater.from(context).inflate(R$layout.account_picker_dialog_body, (ViewGroup) null);
        r0.setLayoutManager(new LinearLayoutManager(context));
        this.a = new b1(r0, aVar);
        this.b = bi1Var;
        HashMap mapE = PropertyModel.e(di1.B);
        PropertyModel.r<String> rVar = di1.c;
        ?? string = context.getString(R$string.signin_account_picker_dialog_title);
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string;
        mapE.put(rVar, gVar);
        PropertyModel.n nVar = di1.q;
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = true;
        mapE.put(nVar, aVar2);
        PropertyModel.r<View> rVar2 = di1.h;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = r0;
        mapE.put(rVar2, gVar2);
        PropertyModel.l<di1.a> lVar = di1.a;
        ?? d1Var = new d1(this);
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = d1Var;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar, gVar3, mapE);
        this.c = propertyModelP;
        bi1Var.j(propertyModelP, 1, false);
    }
}
