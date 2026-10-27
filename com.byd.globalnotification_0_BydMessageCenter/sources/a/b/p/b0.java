package a.b.p;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: classes.dex */
public class b0 extends ToggleButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f235a;

    public b0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyleToggle);
        s0.a(this, getContext());
        y yVar = new y(this);
        this.f235a = yVar;
        yVar.d(attributeSet, R.attr.buttonStyleToggle);
    }
}
