package skin.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.go2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialAppBarLayout extends AppBarLayout implements ro2 {
    public final go2 y;

    public SkinMaterialAppBarLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.y;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    public SkinMaterialAppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        go2 go2Var = new go2(this);
        this.y = go2Var;
        go2Var.K(attributeSet, 0);
    }
}
