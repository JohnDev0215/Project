package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatRatingBar;
import defpackage.oo2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatRatingBar extends AppCompatRatingBar implements ro2 {
    public final oo2 f;

    public SkinCompatRatingBar(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        oo2 oo2Var = this.f;
        if (oo2Var != null) {
            oo2Var.J();
        }
    }

    public SkinCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.ratingBarStyle);
    }

    public SkinCompatRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        oo2 oo2Var = new oo2(this);
        this.f = oo2Var;
        oo2Var.K(attributeSet, i);
    }
}
