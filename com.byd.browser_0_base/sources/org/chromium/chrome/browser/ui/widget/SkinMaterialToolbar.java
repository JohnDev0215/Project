package org.chromium.chrome.browser.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.R;
import com.google.android.material.appbar.MaterialToolbar;
import defpackage.go2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes2.dex */
public class SkinMaterialToolbar extends MaterialToolbar implements ro2 {
    public final go2 i0;

    public SkinMaterialToolbar(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.i0;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.i0;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    public SkinMaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public SkinMaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        go2 go2Var = new go2(this);
        this.i0 = go2Var;
        go2Var.K(attributeSet, i);
    }
}
