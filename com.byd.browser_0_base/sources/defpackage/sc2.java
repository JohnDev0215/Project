package defpackage;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* JADX INFO: compiled from: RoundedCornerOutlineProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sc2 extends ViewOutlineProvider {
    public int a;
    public boolean b = true;
    public boolean c = true;
    public boolean d = true;
    public boolean e = true;

    public sc2(int i) {
        this.a = i;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int width = view.getWidth() - view.getPaddingRight();
        int height = view.getHeight() - view.getPaddingBottom();
        if (!this.b) {
            paddingLeft -= this.a;
        }
        int i = paddingLeft;
        if (!this.c) {
            paddingTop -= this.a;
        }
        int i2 = paddingTop;
        if (!this.d) {
            width += this.a;
        }
        int i3 = width;
        if (!this.e) {
            height += this.a;
        }
        outline.setRoundRect(i, i2, i3, height, this.a);
    }
}
