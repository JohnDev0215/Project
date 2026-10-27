package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Size;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.UnguessableToken;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.components.paintpreview.player.frame.PlayerFrameView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: PlayerFrameCoordinator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class w12 {
    public final y12 a;
    public final b22 b;
    public final c22 c;
    public final PlayerFrameView d;
    public final ArrayList e = new ArrayList();

    public w12(Context context, PlayerCompositorDelegateImpl playerCompositorDelegateImpl, UnguessableToken unguessableToken, int i, int i2, int i3, int i4, float f, boolean z, j22 j22Var, f22 f22Var, g22 g22Var, yq2 yq2Var, es3 es3Var) {
        PropertyModel propertyModel = new PropertyModel(PropertyModel.e(a22.h));
        OverScroller overScroller = new OverScroller(context);
        overScroller.setFriction(ViewConfiguration.getScrollFriction() / 2.0f);
        y12 y12Var = new y12(propertyModel, playerCompositorDelegateImpl, f22Var, unguessableToken, new Size(i, i2), i3, i4, f, es3Var);
        this.a = y12Var;
        if (z) {
            Matrix matrix = (Matrix) propertyModel.j(a22.g);
            Objects.requireNonNull(f22Var);
            this.b = new b22(matrix, y12Var, yq2Var, new aq3(7, f22Var));
        }
        Objects.requireNonNull(f22Var);
        c22 c22Var = new c22(overScroller, y12Var, new ve3(23, f22Var), new i22(1, f22Var));
        this.c = c22Var;
        PlayerFrameView playerFrameView = new PlayerFrameView(context, z, y12Var, new gq0(this.b, c22Var, y12Var), g22Var);
        this.d = playerFrameView;
        if (j22Var != null) {
            c22Var.a = j22Var;
        }
        j.b(propertyModel, playerFrameView, new cg(29));
    }

    public final void a() {
        t12 t12Var = this.d.e;
        t12Var.h = true;
        t12Var.b = null;
        v12 v12Var = this.a.p;
        u12 u12Var = v12Var.a;
        if (u12Var != null) {
            u12Var.c();
            v12Var.a = null;
        }
        u12 u12Var2 = v12Var.b;
        if (u12Var2 != null) {
            u12Var2.c();
            v12Var.b = null;
        }
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((w12) it.next()).a();
        }
    }

    public final void b() {
        c22 c22Var = this.c;
        if (c22Var != null) {
            c22Var.k = false;
        }
        b22 b22Var = this.b;
        if (b22Var != null) {
            b22Var.h = false;
        }
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((w12) it.next()).b();
        }
    }
}
