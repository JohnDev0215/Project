package skin.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.go2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialCoordinatorLayout extends CoordinatorLayout implements ro2 {
    public final go2 B;

    public SkinMaterialCoordinatorLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.B;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    public SkinMaterialCoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialCoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        go2 go2Var = new go2(this);
        this.B = go2Var;
        go2Var.K(attributeSet, 0);
    }
}
