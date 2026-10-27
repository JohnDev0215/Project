package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ny0;
import java.util.HashMap;
import java.util.Objects;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: IncognitoReauthCoordinatorBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ky0 implements jy0 {
    public final Context a;
    public final oy0 b;
    public j c;
    public View d;

    public ky0(Context context, ny0 ny0Var, ny0.a aVar, Runnable runnable) {
        this.a = context;
        this.b = new oy0(aVar, ny0Var, runnable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, zc0] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, l10] */
    public final void b(py0 py0Var, boolean z) {
        this.d = LayoutInflater.from(this.a).inflate(R$layout.incognito_reauth_view, (ViewGroup) null);
        oy0 oy0Var = this.b;
        Objects.requireNonNull(oy0Var);
        ?? zc0Var = new zc0(9, oy0Var);
        ?? l10Var = new l10(20, oy0Var);
        HashMap mapE = PropertyModel.e(vy0.e);
        PropertyModel.l<Runnable> lVar = vy0.a;
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = zc0Var;
        mapE.put(lVar, gVar);
        PropertyModel.l<Runnable> lVar2 = vy0.b;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = l10Var;
        mapE.put(lVar2, gVar2);
        PropertyModel.h hVar = vy0.c;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z;
        mapE.put(hVar, aVar);
        PropertyModel.l<l81> lVar3 = vy0.d;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = py0Var;
        this.c = j.b(C0175b.p(mapE, lVar3, gVar3, mapE), this.d, new bg(9));
    }
}
