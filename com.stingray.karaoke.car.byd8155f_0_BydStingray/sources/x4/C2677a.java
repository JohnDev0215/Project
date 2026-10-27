package x4;

import A4.h;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import t1.ViewTreeObserverOnPreDrawListenerC2429e;
import w4.g;

/* JADX INFO: renamed from: x4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2677a implements c {
    public final ImageView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d f23779m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Animatable f23780n;
    public final /* synthetic */ int o;

    public C2677a(ImageView imageView, int i7) {
        this.o = i7;
        h.c(imageView, "Argument must not be null");
        this.l = imageView;
        this.f23779m = new d(imageView);
    }

    @Override // x4.c
    public final w4.c D() {
        Object tag = this.l.getTag(R.id.glide_custom_view_target_tag);
        if (tag == null) {
            return null;
        }
        if (tag instanceof w4.c) {
            return (w4.c) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    @Override // x4.c
    public final void E(Drawable drawable) {
        d dVar = this.f23779m;
        ViewTreeObserver viewTreeObserver = dVar.f23781a.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(dVar.c);
        }
        dVar.c = null;
        dVar.f23782b.clear();
        Animatable animatable = this.f23780n;
        if (animatable != null) {
            animatable.stop();
        }
        a(null);
        this.f23780n = null;
        this.l.setImageDrawable(drawable);
    }

    @Override // x4.c
    public final void F(Object obj) {
        a(obj);
        if (!(obj instanceof Animatable)) {
            this.f23780n = null;
            return;
        }
        Animatable animatable = (Animatable) obj;
        this.f23780n = animatable;
        animatable.start();
    }

    @Override // t4.b
    public final void J() {
        Animatable animatable = this.f23780n;
        if (animatable != null) {
            animatable.start();
        }
    }

    public final void a(Object obj) {
        switch (this.o) {
            case 0:
                this.l.setImageBitmap((Bitmap) obj);
                break;
            default:
                this.l.setImageDrawable((Drawable) obj);
                break;
        }
    }

    @Override // x4.c
    public final void g(g gVar) {
        d dVar = this.f23779m;
        ImageView imageView = dVar.f23781a;
        int paddingRight = imageView.getPaddingRight() + imageView.getPaddingLeft();
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int iA = dVar.a(imageView.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingRight);
        ImageView imageView2 = dVar.f23781a;
        int paddingBottom = imageView2.getPaddingBottom() + imageView2.getPaddingTop();
        ViewGroup.LayoutParams layoutParams2 = imageView2.getLayoutParams();
        int iA2 = dVar.a(imageView2.getHeight(), layoutParams2 != null ? layoutParams2.height : 0, paddingBottom);
        if ((iA > 0 || iA == Integer.MIN_VALUE) && (iA2 > 0 || iA2 == Integer.MIN_VALUE)) {
            gVar.m(iA, iA2);
            return;
        }
        ArrayList arrayList = dVar.f23782b;
        if (!arrayList.contains(gVar)) {
            arrayList.add(gVar);
        }
        if (dVar.c == null) {
            ViewTreeObserver viewTreeObserver = imageView2.getViewTreeObserver();
            ViewTreeObserverOnPreDrawListenerC2429e viewTreeObserverOnPreDrawListenerC2429e = new ViewTreeObserverOnPreDrawListenerC2429e(dVar);
            dVar.c = viewTreeObserverOnPreDrawListenerC2429e;
            viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC2429e);
        }
    }

    @Override // x4.c
    public final void i(Drawable drawable) {
        a(null);
        this.f23780n = null;
        this.l.setImageDrawable(drawable);
    }

    @Override // x4.c
    public final void n(w4.c cVar) {
        this.l.setTag(R.id.glide_custom_view_target_tag, cVar);
    }

    @Override // x4.c
    public final void t(Drawable drawable) {
        a(null);
        this.f23780n = null;
        this.l.setImageDrawable(drawable);
    }

    public final String toString() {
        return "Target for: " + this.l;
    }

    @Override // x4.c
    public final void u(g gVar) {
        this.f23779m.f23782b.remove(gVar);
    }
}
