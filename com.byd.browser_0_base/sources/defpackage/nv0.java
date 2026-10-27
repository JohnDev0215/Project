package defpackage;

import android.content.Context;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.wm3;
import java.util.function.BooleanSupplier;
import org.chromium.chrome.R$string;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: HomeButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nv0 {
    public final Context a;
    public final View b;
    public final BooleanSupplier c;
    public final pk3 d;
    public final BooleanSupplier e;
    public final i20 f;
    public final qs1<Boolean> g;
    public final nw2<Boolean> h;

    public nv0(AppCompatActivity appCompatActivity, View view, pk3 pk3Var, s20 s20Var, qs1 qs1Var, se1 se1Var, bn0 bn0Var, z2 z2Var) {
        this.a = appCompatActivity;
        this.b = view;
        this.d = pk3Var;
        this.e = s20Var;
        this.g = qs1Var;
        this.h = se1Var;
        this.c = bn0Var;
        this.f = new i20(z2Var, new mv0(this), null);
    }

    public final void a(GURL gurl) {
        View view = this.b;
        if (view == null || !view.isShown() || this.e.getAsBoolean() || bk3.l(gurl) || this.h.get().booleanValue()) {
            return;
        }
        qs1<Boolean> qs1Var = this.g;
        if (qs1Var.get() == null || qs1Var.get().booleanValue()) {
            return;
        }
        boolean asBoolean = this.c.getAsBoolean();
        hw0 hw0Var = new hw0(this.a.getResources(), "IPH_NewTabPageHomeButton", asBoolean ? R$string.iph_ntp_with_feed_text : R$string.iph_ntp_without_feed_text, asBoolean ? R$string.iph_ntp_with_feed_accessibility_text : R$string.iph_ntp_without_feed_accessibility_text);
        hw0Var.j = view;
        hw0Var.q = new wm3.a(1);
        this.d.a(hw0Var.a());
    }
}
