package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import defpackage.pt3;

/* JADX INFO: compiled from: ViewPager.java */
/* JADX INFO: loaded from: classes.dex */
public final class en3 implements wr1 {
    public final Rect e = new Rect();
    public final /* synthetic */ ViewPager f;

    public en3(ViewPager viewPager) {
        this.f = viewPager;
    }

    @Override // defpackage.wr1
    public final pt3 k(View view, pt3 pt3Var) {
        pt3 pt3VarG = om3.g(view, pt3Var);
        if (pt3VarG.a.m()) {
            return pt3VarG;
        }
        int iC = pt3VarG.c();
        Rect rect = this.e;
        rect.left = iC;
        rect.top = pt3VarG.e();
        rect.right = pt3VarG.d();
        rect.bottom = pt3VarG.b();
        ViewPager viewPager = this.f;
        int childCount = viewPager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            pt3 pt3VarC = om3.c(viewPager.getChildAt(i), pt3VarG);
            rect.left = Math.min(pt3VarC.c(), rect.left);
            rect.top = Math.min(pt3VarC.e(), rect.top);
            rect.right = Math.min(pt3VarC.d(), rect.right);
            rect.bottom = Math.min(pt3VarC.b(), rect.bottom);
        }
        pt3.c bVar = Build.VERSION.SDK_INT >= 30 ? new pt3.b(pt3VarG) : new pt3.a(pt3VarG);
        bVar.g(n01.c(rect));
        return bVar.b();
    }
}
