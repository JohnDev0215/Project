package p;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import io.reactivex.rxjava3.core.SingleEmitter;

/* JADX INFO: loaded from: classes.dex */
public final class cu implements yi2 {
    public final /* synthetic */ SingleEmitter c;

    public cu(SingleEmitter singleEmitter) {
        this.c = singleEmitter;
    }

    @Override // p.yi2
    public final void f(Drawable drawable) {
        v03.j(drawable, "result");
        this.c.onSuccess(((BitmapDrawable) drawable).getBitmap());
    }

    @Override // p.yi2
    public final void g(Drawable drawable) {
        this.c.tryOnError(new RuntimeException("Error getting bitmap from request"));
    }
}
