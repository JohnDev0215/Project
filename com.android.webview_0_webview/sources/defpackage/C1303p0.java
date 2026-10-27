package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: renamed from: p0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1303p0 extends ViewGroup.MarginLayoutParams {
    public float a;
    public int b;

    public C1303p0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.W);
        this.a = typedArrayObtainStyledAttributes.getFloat(3, 0.0f);
        this.b = typedArrayObtainStyledAttributes.getInt(0, -1);
        typedArrayObtainStyledAttributes.recycle();
    }

    public C1303p0(int i, int i2) {
        super(i, i2);
        this.b = -1;
        this.a = 0.0f;
    }

    public C1303p0(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.b = -1;
    }
}
