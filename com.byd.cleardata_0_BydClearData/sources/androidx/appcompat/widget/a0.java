package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: classes.dex */
public class a0 extends ToggleButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final x f906b;

    public a0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    public a0(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        q0.a(this, getContext());
        x xVar = new x(this);
        this.f906b = xVar;
        xVar.m(attributeSet, i);
    }
}
