package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.VectorEnabledTintResources;
import skin.support.R$styleable;

/* JADX INFO: compiled from: SkinAppCompatViewInflater.java */
/* JADX INFO: loaded from: classes3.dex */
public final class co2 implements bp2, lp2 {
    public co2() {
        if (xo2.a == null) {
            synchronized (xo2.class) {
                if (xo2.a == null) {
                    xo2.a = new xo2();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x0117  */
    @Override // defpackage.bp2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View a(java.lang.String r3, android.content.Context r4, android.util.AttributeSet r5) {
        /*
            Method dump skipped, instruction units count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.co2.a(java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    @Override // defpackage.lp2
    public final Context b(Context context, AttributeSet attributeSet) {
        int i = VectorEnabledTintResources.a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.View, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_theme, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != 0 ? ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).a == resourceId) ? context : new ContextThemeWrapper(context, resourceId) : context;
    }
}
