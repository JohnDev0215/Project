package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.nc2;
import defpackage.wm3;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: VoiceToolbarButtonController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class uo3 extends ad {
    public final nw2<xe3> k;
    public final a l;

    /* JADX INFO: compiled from: VoiceToolbarButtonController.java */
    public interface a {
    }

    public uo3(AppCompatActivity appCompatActivity, Drawable drawable, z2 z2Var, fc2 fc2Var, bi1 bi1Var, nc2.e eVar) {
        super(z2Var, bi1Var, drawable, appCompatActivity.getString(R$string.accessibility_toolbar_btn_mic), 0, 4);
        this.k = fc2Var;
        this.l = eVar;
    }

    @Override // defpackage.ad
    public final hw0 a(Tab tab) {
        wm3.a aVar = new wm3.a(1);
        aVar.b = true;
        Resources resources = tab.getContext().getResources();
        int i = R$string.adaptive_toolbar_button_voice_search_iph;
        hw0 hw0Var = new hw0(resources, "IPH_AdaptiveButtonInTopToolbarCustomization_VoiceSearch", i, i);
        hw0Var.q = aVar;
        return hw0Var;
    }

    @Override // defpackage.ad
    public final boolean g(Tab tab) {
        if (!super.g(tab)) {
            return false;
        }
        so3 so3VarD = nc2.this.x.E.d();
        return (so3VarD == null ? false : so3VarD.a()) && bk3.i(tab.getUrl());
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        n3.X0("MobileTopToolbarVoiceButton");
        so3 so3VarD = nc2.this.x.E.d();
        if (so3VarD != null) {
            so3VarD.f(4);
        }
        nw2<xe3> nw2Var = this.k;
        if (nw2Var.f()) {
            nw2Var.get().notifyEvent("adaptive_toolbar_customization_voice_search_opened");
        }
    }
}
