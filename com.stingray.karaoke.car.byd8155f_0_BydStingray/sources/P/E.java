package P;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class E extends RippleDrawable {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C1891t f5206m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Integer f5207n;
    public boolean o;

    public E(boolean z6) {
        super(ColorStateList.valueOf(-16777216), null, z6 ? new ColorDrawable(-1) : null);
        this.l = z6;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.Drawable
    public final Rect getDirtyBounds() {
        if (!this.l) {
            this.o = true;
        }
        Rect dirtyBounds = super.getDirtyBounds();
        this.o = false;
        return dirtyBounds;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean isProjected() {
        return this.o;
    }
}
