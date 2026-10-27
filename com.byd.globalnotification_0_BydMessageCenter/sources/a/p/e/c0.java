package a.p.e;

import a.p.e.l;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class c0 extends RecyclerView.j {
    public boolean g = true;

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public boolean a(RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2, RecyclerView.j.c cVar, RecyclerView.j.c cVar2) {
        int i;
        int i2;
        int i3 = cVar.f1644a;
        int i4 = cVar.f1645b;
        if (b0Var2.u()) {
            int i5 = cVar.f1644a;
            i2 = cVar.f1645b;
            i = i5;
        } else {
            i = cVar2.f1644a;
            i2 = cVar2.f1645b;
        }
        l lVar = (l) this;
        if (b0Var == b0Var2) {
            return lVar.j(b0Var, i3, i4, i, i2);
        }
        float translationX = b0Var.f1629a.getTranslationX();
        float translationY = b0Var.f1629a.getTranslationY();
        float alpha = b0Var.f1629a.getAlpha();
        lVar.o(b0Var);
        b0Var.f1629a.setTranslationX(translationX);
        b0Var.f1629a.setTranslationY(translationY);
        b0Var.f1629a.setAlpha(alpha);
        lVar.o(b0Var2);
        b0Var2.f1629a.setTranslationX(-((int) ((i - i3) - translationX)));
        b0Var2.f1629a.setTranslationY(-((int) ((i2 - i4) - translationY)));
        b0Var2.f1629a.setAlpha(0.0f);
        lVar.k.add(new l.a(b0Var, b0Var2, i3, i4, i, i2));
        return true;
    }

    public abstract boolean j(RecyclerView.b0 b0Var, int i, int i2, int i3, int i4);
}
