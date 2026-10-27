package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;

/* JADX INFO: compiled from: RoundedBitmapDrawable21.java */
/* JADX INFO: loaded from: classes.dex */
public final class qc2 extends rc2 {
    public qc2(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    @Override // defpackage.rc2
    public final void a(int i, int i2, int i3, Rect rect, Rect rect2) {
        Gravity.apply(i, i2, i3, rect, rect2, 0);
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        c();
        outline.setRoundRect(this.h, this.g);
    }
}
