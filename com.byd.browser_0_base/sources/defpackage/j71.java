package defpackage;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import org.chromium.components.content_creation.notes.models.Background;

/* JADX INFO: compiled from: LinearGradientBackground.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j71 implements Background {
    public final int[] a;
    public final int b;

    public j71(int[] iArr, int i) {
        this.a = iArr;
        this.b = i;
    }

    @Override // org.chromium.components.content_creation.notes.models.Background
    public final void a(View view, float f) {
        if (view == null) {
            return;
        }
        int iM = gq2.m(this.b);
        GradientDrawable gradientDrawable = new GradientDrawable((iM == 0 || iM == 1) ? GradientDrawable.Orientation.TOP_BOTTOM : iM != 2 ? iM != 3 ? iM != 4 ? GradientDrawable.Orientation.TOP_BOTTOM : GradientDrawable.Orientation.BR_TL : GradientDrawable.Orientation.RIGHT_LEFT : GradientDrawable.Orientation.TR_BL, this.a);
        gradientDrawable.setCornerRadius(f);
        view.setBackground(gradientDrawable);
    }
}
