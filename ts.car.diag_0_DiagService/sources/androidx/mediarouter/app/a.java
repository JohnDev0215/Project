package androidx.mediarouter.app;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.W;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.r;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$string;
import androidx.mediarouter.R$styleable;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class a extends View {
    private static C0029a s;
    static final SparseArray<Drawable.ConstantState> t = new SparseArray<>(2);
    private static final int[] u = {R.attr.state_checked};
    private static final int[] v = {R.attr.state_checkable};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final u f850c;
    private final b d;
    private t e;
    private f f;
    private boolean g;
    private int h;
    c i;
    private Drawable j;
    private int k;
    private int l;
    private ColorStateList m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r;

    /* JADX INFO: renamed from: androidx.mediarouter.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private static final class C0029a extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f851a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f852b = true;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private List<a> f853c = new ArrayList();

        C0029a(Context context) {
            this.f851a = context;
        }

        public boolean a() {
            return this.f852b;
        }

        public void b(a aVar) {
            if (this.f853c.size() == 0) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                this.f851a.registerReceiver(this, intentFilter);
            }
            this.f853c.add(aVar);
        }

        public void c(a aVar) {
            this.f853c.remove(aVar);
            if (this.f853c.size() == 0) {
                this.f851a.unregisterReceiver(this);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || this.f852b == (!intent.getBooleanExtra("noConnectivity", false))) {
                return;
            }
            this.f852b = z;
            Iterator<a> it = this.f853c.iterator();
            while (it.hasNext()) {
                it.next().f();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class b extends u.b {
        b() {
        }

        @Override // androidx.mediarouter.media.u.b
        public void a(u uVar, u.f fVar) {
            a.this.e();
        }

        @Override // androidx.mediarouter.media.u.b
        public void b(u uVar, u.f fVar) {
            a.this.e();
        }

        @Override // androidx.mediarouter.media.u.b
        public void c(u uVar, u.f fVar) {
            a.this.e();
        }

        @Override // androidx.mediarouter.media.u.b
        public void d(u uVar, u.g gVar) {
            a.this.e();
        }

        @Override // androidx.mediarouter.media.u.b
        public void e(u uVar, u.g gVar) {
            a.this.e();
        }

        @Override // androidx.mediarouter.media.u.b
        public void g(u uVar, u.g gVar) {
            a.this.e();
        }

        @Override // androidx.mediarouter.media.u.b
        public void h(u uVar, u.g gVar) {
            a.this.e();
        }

        @Override // androidx.mediarouter.media.u.b
        public void j(u uVar, u.g gVar) {
            a.this.e();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class c extends AsyncTask<Void, Void, Drawable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f855a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Context f856b;

        c(int i, Context context) {
            this.f855a = i;
            this.f856b = context;
        }

        private void a(Drawable drawable) {
            if (drawable != null) {
                a.t.put(this.f855a, drawable.getConstantState());
            }
            a.this.i = null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Drawable doInBackground(Void... voidArr) {
            if (a.t.get(this.f855a) == null) {
                return this.f856b.getResources().getDrawable(this.f855a);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Drawable drawable) {
            a(drawable);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Drawable drawable) {
            if (drawable != null) {
                a(drawable);
            } else {
                Drawable.ConstantState constantState = a.t.get(this.f855a);
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                a.this.i = null;
            }
            a.this.k(drawable);
        }
    }

    public a(Context context) {
        this(context, null);
    }

    private Activity b() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private FragmentManager c() {
        Activity activityB = b();
        if (activityB instanceof FragmentActivity) {
            return ((FragmentActivity) activityB).o();
        }
        return null;
    }

    private void d() {
        if (this.k > 0) {
            c cVar = this.i;
            if (cVar != null) {
                cVar.cancel(false);
            }
            c cVar2 = new c(this.k, getContext());
            this.i = cVar2;
            this.k = 0;
            cVar2.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    private void n() {
        int i = this.l;
        String string = getContext().getString(i != 1 ? i != 2 ? R$string.mr_cast_button_disconnected : R$string.mr_cast_button_connected : R$string.mr_cast_button_connecting);
        setContentDescription(string);
        if (!this.r || TextUtils.isEmpty(string)) {
            string = null;
        }
        W.a(this, string);
    }

    public void a() {
        this.p = true;
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.j != null) {
            this.j.setState(getDrawableState());
            invalidate();
        }
    }

    void e() {
        boolean z;
        u.g gVarJ = this.f850c.j();
        int iC = !gVarJ.v() && gVarJ.D(this.e) ? gVarJ.c() : 0;
        if (this.l != iC) {
            this.l = iC;
            z = true;
        } else {
            z = false;
        }
        if (z) {
            n();
            refreshDrawableState();
        }
        if (iC == 1) {
            d();
        }
        if (this.g) {
            setEnabled(this.q || this.f850c.k(this.e, 1));
        }
        Drawable drawable = this.j;
        if (drawable == null || !(drawable.getCurrent() instanceof AnimationDrawable)) {
            return;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) this.j.getCurrent();
        if (this.g) {
            if ((z || iC == 1) && !animationDrawable.isRunning()) {
                animationDrawable.start();
                return;
            }
            return;
        }
        if (iC == 2) {
            if (animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
            animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
        }
    }

    void f() {
        super.setVisibility((this.h != 0 || this.q || s.a()) ? this.h : 4);
        Drawable drawable = this.j;
        if (drawable != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    public void g(boolean z) {
        if (z != this.q) {
            this.q = z;
            f();
            e();
        }
    }

    void h(boolean z) {
        if (z != this.r) {
            this.r = z;
            n();
        }
    }

    public void i(@NonNull f fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        this.f = fVar;
    }

    public void j(Drawable drawable) {
        this.k = 0;
        k(drawable);
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.j;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void k(Drawable drawable) {
        Drawable drawable2;
        c cVar = this.i;
        if (cVar != null) {
            cVar.cancel(false);
        }
        Drawable drawable3 = this.j;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.j);
        }
        if (drawable != null) {
            if (this.m != null) {
                drawable = androidx.core.graphics.drawable.a.q(drawable.mutate());
                androidx.core.graphics.drawable.a.n(drawable, this.m);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            drawable.setVisible(getVisibility() == 0, false);
        }
        this.j = drawable;
        refreshDrawableState();
        if (this.g && (drawable2 = this.j) != null && (drawable2.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.j.getCurrent();
            int i = this.l;
            if (i == 1) {
                if (animationDrawable.isRunning()) {
                    return;
                }
                animationDrawable.start();
            } else if (i == 2) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    public void l(t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.e.equals(tVar)) {
            return;
        }
        if (this.g) {
            if (!this.e.f()) {
                this.f850c.m(this.d);
            }
            if (!tVar.f()) {
                this.f850c.a(tVar, this.d);
            }
        }
        this.e = tVar;
        e();
    }

    public boolean m() {
        ApplicationInfo applicationInfo;
        if (!this.g) {
            return false;
        }
        if (u.l()) {
            Context context = getContext();
            Intent intentPutExtra = new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").putExtra("com.android.settings.panel.extra.PACKAGE_NAME", context.getPackageName()).putExtra("key_media_session_token", this.f850c.h());
            Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intentPutExtra, 0).iterator();
            while (it.hasNext()) {
                ActivityInfo activityInfo = it.next().activityInfo;
                if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null && (applicationInfo.flags & 129) != 0) {
                    context.startActivity(intentPutExtra);
                    return true;
                }
            }
        }
        FragmentManager fragmentManagerC = c();
        if (fragmentManagerC == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
        }
        u.g gVarJ = this.f850c.j();
        if (gVarJ.v() || !gVarJ.D(this.e)) {
            if (fragmentManagerC.h0("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
                return false;
            }
            androidx.mediarouter.app.c cVarB = this.f.b();
            cVarB.G1(this.e);
            cVarB.H1(this.p);
            r rVarL = fragmentManagerC.l();
            rVarL.d(cVarB, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
            rVarL.g();
        } else {
            if (fragmentManagerC.h0("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
                return false;
            }
            e eVarC = this.f.c();
            eVarC.F1(this.e);
            eVarC.G1(this.p);
            r rVarL2 = fragmentManagerC.l();
            rVarL2.d(eVarC, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment");
            rVarL2.g();
        }
        return true;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.g = true;
        if (!this.e.f()) {
            this.f850c.a(this.e, this.d);
        }
        e();
        s.b(this);
    }

    @Override // android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        int i2 = this.l;
        if (i2 == 1) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, v);
        } else if (i2 == 2) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, u);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.g = false;
            if (!this.e.f()) {
                this.f850c.m(this.d);
            }
            s.c(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.j != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int intrinsicWidth = this.j.getIntrinsicWidth();
            int intrinsicHeight = this.j.getIntrinsicHeight();
            int i = paddingLeft + (((width - paddingLeft) - intrinsicWidth) / 2);
            int i2 = paddingTop + (((height - paddingTop) - intrinsicHeight) / 2);
            this.j.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.j.draw(canvas);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i3 = this.n;
        Drawable drawable = this.j;
        int iMax = Math.max(i3, drawable != null ? drawable.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight() : 0);
        int i4 = this.o;
        Drawable drawable2 = this.j;
        int iMax2 = Math.max(i4, drawable2 != null ? drawable2.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom() : 0);
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
    public boolean performClick() {
        boolean zPerformClick = super.performClick();
        if (!zPerformClick) {
            playSoundEffect(0);
        }
        d();
        return m() || zPerformClick;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        this.h = i;
        f();
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.j;
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.mediaRouteButtonStyle);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        Drawable.ConstantState constantState;
        super(j.a(context), attributeSet, i);
        this.e = t.f996c;
        this.f = f.a();
        this.h = 0;
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.MediaRouteButton, i, 0);
        ViewCompat.K(this, context2, R$styleable.MediaRouteButton, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        if (isInEditMode()) {
            this.f850c = null;
            this.d = null;
            this.j = getResources().getDrawable(typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0));
            return;
        }
        this.f850c = u.g(context2);
        this.d = new b();
        if (s == null) {
            s = new C0029a(context2.getApplicationContext());
        }
        this.m = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.MediaRouteButton_mediaRouteButtonTint);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minWidth, 0);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minHeight, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0);
        this.k = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        typedArrayObtainStyledAttributes.recycle();
        int i2 = this.k;
        if (i2 != 0 && (constantState = t.get(i2)) != null) {
            j(constantState.newDrawable());
        }
        if (this.j == null) {
            if (resourceId != 0) {
                Drawable.ConstantState constantState2 = t.get(resourceId);
                if (constantState2 != null) {
                    k(constantState2.newDrawable());
                } else {
                    c cVar = new c(resourceId, getContext());
                    this.i = cVar;
                    cVar.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                }
            } else {
                d();
            }
        }
        n();
        setClickable(true);
    }
}
