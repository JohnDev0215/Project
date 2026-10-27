package androidx.mediarouter.app;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$string;
import androidx.mediarouter.R$styleable;
import androidx.mediarouter.media.g;
import androidx.mediarouter.media.h;
import defpackage.ce1;
import defpackage.in3;
import defpackage.kd1;
import defpackage.od1;
import defpackage.om3;
import defpackage.pd1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteButton extends View {
    public static a u;
    public static final SparseArray<Drawable.ConstantState> v = new SparseArray<>(2);
    public static final int[] w = {R.attr.state_checked};
    public static final int[] x = {R.attr.state_checkable};
    public final h e;
    public final b f;
    public g g;
    public pd1 h;
    public boolean i;
    public int j;
    public c k;
    public Drawable l;
    public int m;
    public int n;
    public int o;
    public final ColorStateList p;
    public final int q;
    public final int r;
    public boolean s;
    public boolean t;

    public static final class a extends BroadcastReceiver {
        public final Context a;
        public boolean b = true;
        public final ArrayList c = new ArrayList();

        public a(Context context) {
            this.a = context;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            boolean z;
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || this.b == (!intent.getBooleanExtra("noConnectivity", false))) {
                return;
            }
            this.b = z;
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ((MediaRouteButton) it.next()).c();
            }
        }
    }

    public final class b extends h.a {
        public b() {
        }

        @Override // androidx.mediarouter.media.h.a
        public final void a() {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void b() {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void c() {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void d() {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void e(h.C0020h c0020h) {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void f() {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void g(h.C0020h c0020h) {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void h() {
            MediaRouteButton.this.b();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void j(ce1 ce1Var) {
            if (ce1Var != null) {
                throw null;
            }
            MediaRouteButton.this.getClass();
        }
    }

    public final class c extends AsyncTask<Void, Void, Drawable> {
        public final int a;
        public final Context b;

        public c(int i, Context context) {
            this.a = i;
            this.b = context;
        }

        @Override // android.os.AsyncTask
        public final Drawable doInBackground(Void[] voidArr) {
            SparseArray<Drawable.ConstantState> sparseArray = MediaRouteButton.v;
            int i = this.a;
            if (sparseArray.get(i) == null) {
                return AppCompatResources.a(this.b, i);
            }
            return null;
        }

        @Override // android.os.AsyncTask
        public final void onCancelled(Drawable drawable) {
            Drawable drawable2 = drawable;
            if (drawable2 != null) {
                MediaRouteButton.v.put(this.a, drawable2.getConstantState());
            }
            MediaRouteButton.this.k = null;
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(Drawable drawable) {
            Drawable drawableNewDrawable = drawable;
            int i = this.a;
            MediaRouteButton mediaRouteButton = MediaRouteButton.this;
            if (drawableNewDrawable != null) {
                MediaRouteButton.v.put(i, drawableNewDrawable.getConstantState());
                mediaRouteButton.k = null;
            } else {
                Drawable.ConstantState constantState = MediaRouteButton.v.get(i);
                if (constantState != null) {
                    drawableNewDrawable = constantState.newDrawable();
                }
                mediaRouteButton.k = null;
            }
            mediaRouteButton.setRemoteIndicatorDrawableInternal(drawableNewDrawable);
        }
    }

    public MediaRouteButton(Context context) {
        this(context, null);
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private FragmentManager getFragmentManager() {
        Activity activity = getActivity();
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).x1();
        }
        return null;
    }

    public final void a() {
        if (this.m > 0) {
            c cVar = this.k;
            if (cVar != null) {
                cVar.cancel(false);
            }
            c cVar2 = new c(this.m, getContext());
            this.k = cVar2;
            this.m = 0;
            cVar2.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    public final void b() {
        this.e.getClass();
        h.C0020h c0020hF = h.f();
        boolean z = true;
        boolean z2 = !c0020hF.f();
        int i = z2 ? c0020hF.h : 0;
        if (this.o != i) {
            this.o = i;
            e();
            refreshDrawableState();
        }
        if (i == 1) {
            a();
        }
        if (this.i) {
            if (!this.s && !z2 && !h.g(this.g)) {
                z = false;
            }
            setEnabled(z);
        }
    }

    public final void c() {
        int i = this.j;
        if (i == 0 && !this.s && !u.b) {
            i = 4;
        }
        super.setVisibility(i);
        Drawable drawable = this.l;
        if (drawable != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    public final boolean d() {
        if (!this.i) {
            return false;
        }
        h hVar = this.e;
        hVar.getClass();
        h.b();
        h.c();
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
        }
        hVar.getClass();
        if (h.f().f()) {
            if (fragmentManager.B("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
                return false;
            }
            this.h.getClass();
            kd1 kd1Var = new kd1();
            kd1Var.P0(this.g);
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
            aVar.d(0, kd1Var, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment", 1);
            aVar.h(true);
        } else {
            if (fragmentManager.B("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
                return false;
            }
            this.h.getClass();
            od1 od1Var = new od1();
            g gVar = this.g;
            if (gVar == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            if (od1Var.q0 == null) {
                Bundle bundle = od1Var.j;
                if (bundle != null) {
                    Bundle bundle2 = bundle.getBundle("selector");
                    g gVar2 = null;
                    if (bundle2 != null) {
                        gVar2 = new g(null, bundle2);
                    } else {
                        g gVar3 = g.c;
                    }
                    od1Var.q0 = gVar2;
                }
                if (od1Var.q0 == null) {
                    od1Var.q0 = g.c;
                }
            }
            if (!od1Var.q0.equals(gVar)) {
                od1Var.q0 = gVar;
                Bundle bundle3 = od1Var.j;
                if (bundle3 == null) {
                    bundle3 = new Bundle();
                }
                bundle3.putBundle("selector", gVar.a);
                od1Var.C0(bundle3);
            }
            androidx.fragment.app.a aVar2 = new androidx.fragment.app.a(fragmentManager);
            aVar2.d(0, od1Var, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment", 1);
            aVar2.h(true);
        }
        return true;
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.l != null) {
            this.l.setState(getDrawableState());
            if (this.l.getCurrent() instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) this.l.getCurrent();
                int i = this.o;
                if (i == 1 || this.n != i) {
                    if (!animationDrawable.isRunning()) {
                        animationDrawable.start();
                    }
                } else if (i == 2 && !animationDrawable.isRunning()) {
                    animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
                }
            }
            invalidate();
        }
        this.n = this.o;
    }

    public final void e() {
        int i = this.o;
        String string = getContext().getString(i != 1 ? i != 2 ? R$string.mr_cast_button_disconnected : R$string.mr_cast_button_connected : R$string.mr_cast_button_connecting);
        setContentDescription(string);
        if (!this.t || TextUtils.isEmpty(string)) {
            string = null;
        }
        setTooltipText(string);
    }

    public pd1 getDialogFactory() {
        return this.h;
    }

    public g getRouteSelector() {
        return this.g;
    }

    @Override // android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.l;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.i = true;
        if (!this.g.c()) {
            this.e.a(this.g, this.f, 0);
        }
        b();
        a aVar = u;
        ArrayList arrayList = aVar.c;
        if (arrayList.size() == 0) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            int i = Build.VERSION.SDK_INT;
            Context context = aVar.a;
            if (i < 33) {
                context.registerReceiver(aVar, intentFilter);
            } else {
                context.registerReceiver(aVar, intentFilter, 4);
            }
        }
        arrayList.add(this);
    }

    @Override // android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.e == null) {
            return iArrOnCreateDrawableState;
        }
        int i2 = this.o;
        if (i2 == 1) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, x);
        } else if (i2 == 2) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, w);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.i = false;
            if (!this.g.c()) {
                this.e.h(this.f);
            }
            a aVar = u;
            ArrayList arrayList = aVar.c;
            arrayList.remove(this);
            if (arrayList.size() == 0) {
                aVar.a.unregisterReceiver(aVar);
            }
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.l != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int intrinsicWidth = this.l.getIntrinsicWidth();
            int intrinsicHeight = this.l.getIntrinsicHeight();
            int i = (((width - paddingLeft) - intrinsicWidth) / 2) + paddingLeft;
            int i2 = (((height - paddingTop) - intrinsicHeight) / 2) + paddingTop;
            this.l.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.l.draw(canvas);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int paddingRight;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        Drawable drawable = this.l;
        int paddingBottom = 0;
        if (drawable != null) {
            paddingRight = getPaddingRight() + getPaddingLeft() + drawable.getIntrinsicWidth();
        } else {
            paddingRight = 0;
        }
        int iMax = Math.max(this.q, paddingRight);
        Drawable drawable2 = this.l;
        if (drawable2 != null) {
            paddingBottom = getPaddingBottom() + getPaddingTop() + drawable2.getIntrinsicHeight();
        }
        int iMax2 = Math.max(this.r, paddingBottom);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, iMax);
        } else if (mode != 1073741824) {
            size = iMax;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, iMax2);
        } else if (mode2 != 1073741824) {
            size2 = iMax2;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public final boolean performClick() {
        boolean z;
        boolean zPerformClick = super.performClick();
        if (!zPerformClick) {
            playSoundEffect(0);
        }
        a();
        if (this.i) {
            this.e.getClass();
            h.b();
            h.c();
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager == null) {
                throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
            }
            if (h.f().f()) {
                if (fragmentManager.B("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                    Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
                    z = false;
                } else {
                    this.h.getClass();
                    kd1 kd1Var = new kd1();
                    kd1Var.P0(this.g);
                    androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
                    aVar.d(0, kd1Var, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment", 1);
                    aVar.h(true);
                    z = true;
                }
            } else if (fragmentManager.B("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
                z = false;
            } else {
                this.h.getClass();
                od1 od1Var = new od1();
                g gVar = this.g;
                if (gVar == null) {
                    throw new IllegalArgumentException("selector must not be null");
                }
                if (od1Var.q0 == null) {
                    Bundle bundle = od1Var.j;
                    if (bundle != null) {
                        Bundle bundle2 = bundle.getBundle("selector");
                        g gVar2 = null;
                        if (bundle2 != null) {
                            gVar2 = new g(null, bundle2);
                        } else {
                            g gVar3 = g.c;
                        }
                        od1Var.q0 = gVar2;
                    }
                    if (od1Var.q0 == null) {
                        od1Var.q0 = g.c;
                    }
                }
                if (!od1Var.q0.equals(gVar)) {
                    od1Var.q0 = gVar;
                    Bundle bundle3 = od1Var.j;
                    if (bundle3 == null) {
                        bundle3 = new Bundle();
                    }
                    bundle3.putBundle("selector", gVar.a);
                    od1Var.C0(bundle3);
                }
                androidx.fragment.app.a aVar2 = new androidx.fragment.app.a(fragmentManager);
                aVar2.d(0, od1Var, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment", 1);
                aVar2.h(true);
                z = true;
            }
        } else {
            z = false;
        }
        return z || zPerformClick;
    }

    public void setAlwaysVisible(boolean z) {
        if (z != this.s) {
            this.s = z;
            c();
            b();
        }
    }

    public void setCheatSheetEnabled(boolean z) {
        if (z != this.t) {
            this.t = z;
            e();
        }
    }

    public void setDialogFactory(pd1 pd1Var) {
        if (pd1Var == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        this.h = pd1Var;
    }

    public void setRemoteIndicatorDrawable(Drawable drawable) {
        this.m = 0;
        setRemoteIndicatorDrawableInternal(drawable);
    }

    public void setRemoteIndicatorDrawableInternal(Drawable drawable) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.cancel(false);
        }
        Drawable drawable2 = this.l;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.l);
        }
        if (drawable != null) {
            ColorStateList colorStateList = this.p;
            if (colorStateList != null) {
                drawable = drawable.mutate();
                drawable.setTintList(colorStateList);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            drawable.setVisible(getVisibility() == 0, false);
        }
        this.l = drawable;
        refreshDrawableState();
    }

    public void setRouteSelector(g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.g.equals(gVar)) {
            return;
        }
        if (this.i) {
            boolean zC = this.g.c();
            b bVar = this.f;
            h hVar = this.e;
            if (!zC) {
                hVar.h(bVar);
            }
            if (!gVar.c()) {
                hVar.a(gVar, bVar, 0);
            }
        }
        this.g = gVar;
        b();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        this.j = i;
        c();
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.l;
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet, int i) {
        Drawable.ConstantState constantState;
        int i2 = f.a;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, f.f(context));
        int iH = f.h(contextThemeWrapper, R$attr.mediaRouteTheme);
        super(iH != 0 ? new ContextThemeWrapper(contextThemeWrapper, iH) : contextThemeWrapper, attributeSet, i);
        this.g = g.c;
        this.h = pd1.a;
        this.j = 0;
        Context context2 = getContext();
        int[] iArr = R$styleable.MediaRouteButton;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, 0);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context2, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        if (isInEditMode()) {
            this.e = null;
            this.f = null;
            this.l = AppCompatResources.a(context2, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0));
            return;
        }
        this.e = h.d(context2);
        this.f = new b();
        h.C0020h c0020hF = h.f();
        int i3 = c0020hF.f() ^ true ? c0020hF.h : 0;
        this.o = i3;
        this.n = i3;
        if (u == null) {
            u = new a(context2.getApplicationContext());
        }
        this.p = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.MediaRouteButton_mediaRouteButtonTint);
        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minWidth, 0);
        this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minHeight, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0);
        this.m = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        typedArrayObtainStyledAttributes.recycle();
        int i4 = this.m;
        SparseArray<Drawable.ConstantState> sparseArray = v;
        if (i4 != 0 && (constantState = sparseArray.get(i4)) != null) {
            setRemoteIndicatorDrawable(constantState.newDrawable());
        }
        if (this.l == null) {
            if (resourceId != 0) {
                Drawable.ConstantState constantState2 = sparseArray.get(resourceId);
                if (constantState2 != null) {
                    setRemoteIndicatorDrawableInternal(constantState2.newDrawable());
                } else {
                    c cVar = new c(resourceId, getContext());
                    this.k = cVar;
                    cVar.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                }
            } else {
                a();
            }
        }
        e();
        setClickable(true);
    }
}
