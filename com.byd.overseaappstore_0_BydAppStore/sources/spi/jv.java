package spi;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.R$id;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class jv<T extends View, Z> extends k1<Z> {
    public static int d = R$id.glide_custom_view_target_tag;
    public final T b;
    public final a c;

    @VisibleForTesting
    public static final class a {

        @Nullable
        @VisibleForTesting
        public static Integer e;
        public final View a;
        public final List<tp> b = new ArrayList();
        public boolean c;

        @Nullable
        public ViewTreeObserverOnPreDrawListenerC0129a d;

        /* JADX INFO: renamed from: spi.jv$a$a, reason: collision with other inner class name */
        public static final class ViewTreeObserverOnPreDrawListenerC0129a implements ViewTreeObserver.OnPreDrawListener {
            public final WeakReference<a> a;

            public ViewTreeObserverOnPreDrawListenerC0129a(@NonNull a aVar) {
                this.a = new WeakReference<>(aVar);
            }

            /* JADX WARN: Type inference failed for: r1v2, types: [java.util.ArrayList, java.util.List<spi.tp>] */
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", 2)) {
                    Log.v("ViewTarget", "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                a aVar = this.a.get();
                if (aVar == null || aVar.b.isEmpty()) {
                    return true;
                }
                int iD = aVar.d();
                int iC = aVar.c();
                if (!aVar.e(iD, iC)) {
                    return true;
                }
                Iterator it = new ArrayList(aVar.b).iterator();
                while (it.hasNext()) {
                    ((tp) it.next()).b(iD, iC);
                }
                aVar.a();
                return true;
            }
        }

        public a(@NonNull View view) {
            this.a = view;
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [java.util.ArrayList, java.util.List<spi.tp>] */
        public final void a() {
            ViewTreeObserver viewTreeObserver = this.a.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.d);
            }
            this.d = null;
            this.b.clear();
        }

        public final int b(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.c && this.a.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.a.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable("ViewTarget", 4)) {
                Log.i("ViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            Context context = this.a.getContext();
            if (e == null) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                Objects.requireNonNull(windowManager, "Argument must not be null");
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                e = Integer.valueOf(Math.max(point.x, point.y));
            }
            return e.intValue();
        }

        public final int c() {
            int paddingBottom = this.a.getPaddingBottom() + this.a.getPaddingTop();
            ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
            return b(this.a.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingBottom);
        }

        public final int d() {
            int paddingRight = this.a.getPaddingRight() + this.a.getPaddingLeft();
            ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
            return b(this.a.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingRight);
        }

        public final boolean e(int i, int i2) {
            if (i > 0 || i == Integer.MIN_VALUE) {
                return i2 > 0 || i2 == Integer.MIN_VALUE;
            }
            return false;
        }
    }

    public jv(@NonNull T t) {
        Objects.requireNonNull(t, "Argument must not be null");
        this.b = t;
        this.c = new a(t);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<spi.tp>] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.ArrayList, java.util.List<spi.tp>] */
    @Override // spi.rs
    @CallSuper
    public final void a(@NonNull tp tpVar) {
        a aVar = this.c;
        int iD = aVar.d();
        int iC = aVar.c();
        if (aVar.e(iD, iC)) {
            tpVar.b(iD, iC);
            return;
        }
        if (!aVar.b.contains(tpVar)) {
            aVar.b.add(tpVar);
        }
        if (aVar.d == null) {
            ViewTreeObserver viewTreeObserver = aVar.a.getViewTreeObserver();
            a.ViewTreeObserverOnPreDrawListenerC0129a viewTreeObserverOnPreDrawListenerC0129a = new a.ViewTreeObserverOnPreDrawListenerC0129a(aVar);
            aVar.d = viewTreeObserverOnPreDrawListenerC0129a;
            viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC0129a);
        }
    }

    @Override // spi.k1, spi.rs
    public final void e(@Nullable jm jmVar) {
        this.b.setTag(d, jmVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.tp>] */
    @Override // spi.rs
    @CallSuper
    public final void f(@NonNull tp tpVar) {
        this.c.b.remove(tpVar);
    }

    @Override // spi.k1, spi.rs
    @CallSuper
    public void g(@Nullable Drawable drawable) {
    }

    @Override // spi.k1, spi.rs
    @Nullable
    public final jm h() {
        Object tag = this.b.getTag(d);
        if (tag == null) {
            return null;
        }
        if (tag instanceof jm) {
            return (jm) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    @Override // spi.k1, spi.rs
    @CallSuper
    public void i(@Nullable Drawable drawable) {
        this.c.a();
    }

    public final String toString() {
        StringBuilder sbK = e.k("Target for: ");
        sbK.append(this.b);
        return sbK.toString();
    }

    @Deprecated
    public jv(@NonNull T t, boolean z) {
        this(t);
        if (z) {
            this.c.c = true;
        }
    }
}
