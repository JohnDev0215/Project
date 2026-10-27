package defpackage;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import org.chromium.components.content_creation.notes.models.Background;

/* JADX INFO: compiled from: SolidBackground.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fq2 implements Background {
    public final int a;

    public fq2(int i) {
        this.a = i;
    }

    @Override // org.chromium.components.content_creation.notes.models.Background
    public final void a(View view, float f) {
        if (view == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.a);
        gradientDrawable.setCornerRadius(f);
        view.setBackground(gradientDrawable);
    }
}
