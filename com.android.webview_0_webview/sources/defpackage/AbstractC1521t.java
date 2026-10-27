package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: renamed from: t, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1521t extends ViewGroup.MarginLayoutParams {
    public int a;

    public AbstractC1521t(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.b);
        this.a = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public AbstractC1521t(int i, int i2) {
        super(i, i2);
        this.a = 0;
        this.a = 8388627;
    }

    public AbstractC1521t(AbstractC1521t abstractC1521t) {
        super((ViewGroup.MarginLayoutParams) abstractC1521t);
        this.a = 0;
        this.a = abstractC1521t.a;
    }

    public AbstractC1521t(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.a = 0;
    }
}
