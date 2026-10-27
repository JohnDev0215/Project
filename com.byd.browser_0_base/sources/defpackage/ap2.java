package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import skin.support.constraint.SkinCompatConstraintLayout;

/* JADX INFO: compiled from: SkinConstraintViewInflater.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ap2 implements bp2 {
    @Override // defpackage.bp2
    public final View a(String str, Context context, AttributeSet attributeSet) {
        str.getClass();
        if (str.equals("androidx.constraintlayout.widget.ConstraintLayout")) {
            return new SkinCompatConstraintLayout(context, attributeSet);
        }
        return null;
    }
}
