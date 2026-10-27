package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;
import r.m;
import r.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatToggleButton extends ToggleButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f598d;

    public AppCompatToggleButton(Context context) {
        this(context, null);
    }

    public AppCompatToggleButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    public AppCompatToggleButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        z.a(this, getContext());
        m mVar = new m(this);
        this.f598d = mVar;
        mVar.m(attributeSet, i8);
    }
}
