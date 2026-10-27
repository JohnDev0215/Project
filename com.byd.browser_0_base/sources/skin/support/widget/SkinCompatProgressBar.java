package skin.support.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import defpackage.oo2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatProgressBar extends ProgressBar implements ro2 {
    public final oo2 e;

    public SkinCompatProgressBar(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        oo2 oo2Var = this.e;
        if (oo2Var != null) {
            oo2Var.J();
        }
    }

    public SkinCompatProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.progressBarStyle);
    }

    public SkinCompatProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        oo2 oo2Var = new oo2(this);
        this.e = oo2Var;
        oo2Var.K(attributeSet, i);
    }
}
