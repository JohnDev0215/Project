package skin.support.constraint;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.go2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatConstraintLayout extends ConstraintLayout implements ro2 {
    public final go2 u;

    public SkinCompatConstraintLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.u;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.u;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    public SkinCompatConstraintLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinCompatConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        go2 go2Var = new go2(this);
        this.u = go2Var;
        go2Var.K(attributeSet, i);
    }
}
