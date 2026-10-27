package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import defpackage.go2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatRadioGroup extends RadioGroup implements ro2 {
    public final go2 e;

    public SkinCompatRadioGroup(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.e;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.e;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    public SkinCompatRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        go2 go2Var = new go2(this);
        this.e = go2Var;
        go2Var.K(attributeSet, 0);
    }
}
