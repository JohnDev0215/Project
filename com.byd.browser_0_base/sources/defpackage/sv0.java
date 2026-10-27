package defpackage;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

/* JADX INFO: compiled from: HorizontalListDividerDrawable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sv0 extends LayerDrawable {
    public static final /* synthetic */ int e = 0;

    public sv0(Drawable[] drawableArr) {
        super(drawableArr);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        setLayerInset(0, 0, rect.height() - getDrawable(0).getIntrinsicHeight(), 0, 0);
        super.onBoundsChange(rect);
    }
}
