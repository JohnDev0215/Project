package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class ViewStubCompat extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f978e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f979g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private WeakReference<View> f980h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private LayoutInflater f981i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private a f982j;

    public interface a {
        void a(ViewStubCompat viewStubCompat, View view);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public View a() {
        ViewParent parent = getParent();
        if (!(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
        if (this.f978e == 0) {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        LayoutInflater layoutInflaterFrom = this.f981i;
        if (layoutInflaterFrom == null) {
            layoutInflaterFrom = LayoutInflater.from(getContext());
        }
        View viewInflate = layoutInflaterFrom.inflate(this.f978e, viewGroup, false);
        int i7 = this.f979g;
        if (i7 != -1) {
            viewInflate.setId(i7);
        }
        int iIndexOfChild = viewGroup.indexOfChild(this);
        viewGroup.removeViewInLayout(this);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            viewGroup.addView(viewInflate, iIndexOfChild, layoutParams);
        } else {
            viewGroup.addView(viewInflate, iIndexOfChild);
        }
        this.f980h = new WeakReference<>(viewInflate);
        a aVar = this.f982j;
        if (aVar != null) {
            aVar.a(this, viewInflate);
        }
        return viewInflate;
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f979g;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f981i;
    }

    public int getLayoutResource() {
        return this.f978e;
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i7) {
        this.f979g = i7;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f981i = layoutInflater;
    }

    public void setLayoutResource(int i7) {
        this.f978e = i7;
    }

    public void setOnInflateListener(a aVar) {
        this.f982j = aVar;
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        WeakReference<View> weakReference = this.f980h;
        if (weakReference != null) {
            View view = weakReference.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(i7);
            return;
        }
        super.setVisibility(i7);
        if (i7 == 0 || i7 == 4) {
            a();
        }
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f978e = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.j.ViewStubCompat, i7, 0);
        this.f979g = typedArrayObtainStyledAttributes.getResourceId(e.j.ViewStubCompat_android_inflatedId, -1);
        this.f978e = typedArrayObtainStyledAttributes.getResourceId(e.j.ViewStubCompat_android_layout, 0);
        setId(typedArrayObtainStyledAttributes.getResourceId(e.j.ViewStubCompat_android_id, -1));
        typedArrayObtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }
}
