package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: renamed from: androidx.appcompat.widget.a, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0055a extends ViewGroup {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final C0006a f348c;
    protected final Context d;
    protected ActionMenuView e;
    protected ActionMenuPresenter f;
    protected int g;
    protected androidx.core.view.v h;
    private boolean i;
    private boolean j;

    /* JADX INFO: renamed from: androidx.appcompat.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    protected class C0006a implements androidx.core.view.w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f349a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f350b;

        protected C0006a() {
        }

        @Override // androidx.core.view.w
        public void a(View view) {
            if (this.f349a) {
                return;
            }
            AbstractC0055a abstractC0055a = AbstractC0055a.this;
            abstractC0055a.h = null;
            AbstractC0055a.super.setVisibility(this.f350b);
        }

        @Override // androidx.core.view.w
        public void b(View view) {
            AbstractC0055a.super.setVisibility(0);
            this.f349a = false;
        }

        @Override // androidx.core.view.w
        public void c(View view) {
            this.f349a = true;
        }

        public C0006a d(androidx.core.view.v vVar, int i) {
            AbstractC0055a.this.h = vVar;
            this.f350b = i;
            return this;
        }
    }

    AbstractC0055a(@NonNull Context context) {
        this(context, null);
    }

    protected static int d(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    protected int c(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    protected int e(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public abstract void f(int i);

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public androidx.core.view.v g(int i, long j) {
        androidx.core.view.v vVar = this.h;
        if (vVar != null) {
            vVar.b();
        }
        if (i != 0) {
            androidx.core.view.v vVarB = ViewCompat.b(this);
            vVarB.a(0.0f);
            vVarB.d(j);
            C0006a c0006a = this.f348c;
            c0006a.d(vVarB, i);
            vVarB.f(c0006a);
            return vVarB;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        androidx.core.view.v vVarB2 = ViewCompat.b(this);
        vVarB2.a(1.0f);
        vVarB2.d(j);
        C0006a c0006a2 = this.f348c;
        c0006a2.d(vVarB2, i);
        vVarB2.f(c0006a2);
        return vVarB2;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        f(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.E(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.j = false;
        }
        if (!this.j) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.j = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.j = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.i = false;
        }
        if (!this.i) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.i = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.i = false;
        }
        return true;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            androidx.core.view.v vVar = this.h;
            if (vVar != null) {
                vVar.b();
            }
            super.setVisibility(i);
        }
    }

    AbstractC0055a(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    AbstractC0055a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f348c = new C0006a();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.d = new ContextThemeWrapper(context, typedValue.resourceId);
        } else {
            this.d = context;
        }
    }
}
