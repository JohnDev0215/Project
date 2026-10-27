package defpackage;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.a;

/* JADX INFO: compiled from: FadeTabIndicatorInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public final class uj0 extends a {
    @Override // com.google.android.material.tabs.a
    public final void b(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        if (f >= 0.5f) {
            view = view2;
        }
        RectF rectFA = a.a(tabLayout, view);
        float fA = f < 0.5f ? u5.a(1.0f, 0.0f, 0.0f, 0.5f, f) : u5.a(0.0f, 1.0f, 0.5f, 1.0f, f);
        drawable.setBounds((int) rectFA.left, drawable.getBounds().top, (int) rectFA.right, drawable.getBounds().bottom);
        drawable.setAlpha((int) (fA * 255.0f));
    }
}
