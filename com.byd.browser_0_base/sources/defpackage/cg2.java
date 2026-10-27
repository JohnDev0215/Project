package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.u8;
import java.util.ArrayList;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: SearchBoxMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cg2 implements v80, hk1, u8.a {
    public final Context e;
    public final PropertyModel f;
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();
    public q2 i;
    public u8 j;

    public cg2(Context context, PropertyModel propertyModel, ViewGroup viewGroup) {
        this.e = context;
        this.f = propertyModel;
        j.b(propertyModel, viewGroup, new eg2());
    }

    @Override // defpackage.hk1
    public final void G0() {
        Profile profileD = Profile.d();
        Context context = this.e;
        zi0 zi0Var = zi0.b;
        TemplateUrlService templateUrlServiceQ = oj2.q(profileD);
        bs0 bs0VarA = bs0.a();
        SharedPreferencesManager.getInstance();
        z7.m().getClass();
        this.j = new u8(context, zi0Var, templateUrlServiceQ, bs0VarA, this, z7.v(profileD), AccountManagerFacadeProvider.getInstance());
        k();
    }

    public void addLensButtonClickListener(View.OnClickListener onClickListener) {
        ArrayList arrayList = this.h;
        boolean z = !arrayList.isEmpty();
        arrayList.add(onClickListener);
        if (z) {
            return;
        }
        this.f.p(dg2.i, new by1(15, this));
    }

    public void addVoiceSearchButtonClickListener(View.OnClickListener onClickListener) {
        ArrayList arrayList = this.g;
        boolean z = !arrayList.isEmpty();
        arrayList.add(onClickListener);
        if (z) {
            return;
        }
        this.f.p(dg2.g, new vt(14, this));
    }

    @Override // u8.a
    public final void k() {
        u8 u8Var = this.j;
        if (u8Var == null) {
            return;
        }
        Drawable drawableA = AppCompatResources.a(u8Var.f, u8Var.o ? R$drawable.ic_colorful_mic : R$drawable.btn_mic);
        PropertyModel.r<Drawable> rVar = dg2.e;
        PropertyModel propertyModel = this.f;
        propertyModel.p(rVar, drawableA);
        propertyModel.p(dg2.f, this.j.o ? null : n3.y0(this.e));
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        u8 u8Var = this.j;
        if (u8Var != null) {
            u8Var.h.i(u8Var);
            u8Var.j.c(u8Var);
            u8Var.k.j(u8Var);
            this.j = null;
        }
        q2 q2Var = this.i;
        if (q2Var != null) {
            q2Var.e(this);
            this.i = null;
        }
    }

    public void setSearchBoxClickListener(View.OnClickListener onClickListener) {
        this.f.p(dg2.l, new gs(4, onClickListener));
    }
}
