package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatSeekBar;
import defpackage.qo2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatSeekBar extends AppCompatSeekBar implements ro2 {
    public final qo2 f;

    public SkinCompatSeekBar(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        qo2 qo2Var = this.f;
        if (qo2Var != null) {
            qo2Var.J();
        }
    }

    public SkinCompatSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.seekBarStyle);
    }

    public SkinCompatSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        qo2 qo2Var = new qo2(this);
        this.f = qo2Var;
        qo2Var.K(attributeSet, i);
    }
}
