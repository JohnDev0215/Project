package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import skin.support.widget.SkinCompatCardView;

/* JADX INFO: compiled from: SkinCardViewInflater.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fo2 implements bp2 {
    @Override // defpackage.bp2
    public final View a(String str, Context context, AttributeSet attributeSet) {
        str.getClass();
        if (str.equals("androidx.cardview.widget.CardView")) {
            return new SkinCompatCardView(context, attributeSet);
        }
        return null;
    }
}
