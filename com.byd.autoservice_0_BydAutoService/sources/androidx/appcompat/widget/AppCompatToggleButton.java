package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;
import o.m;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatToggleButton extends ToggleButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f497b;

    public AppCompatToggleButton(Context context) {
        this(context, null);
    }

    public AppCompatToggleButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    public AppCompatToggleButton(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        z.a(this, getContext());
        m mVar = new m(this);
        this.f497b = mVar;
        mVar.m(attributeSet, i3);
    }
}
