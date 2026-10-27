package com.byd.share;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.auto_photo.R;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DragToShareContainer extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f2882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<b> f2883b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2884c;
    private Rect d;
    private Bitmap e;
    private int f;
    private int g;
    private View.OnTouchListener h;
    private c i;

    class a implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private float f2885a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f2886b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private b f2887c;

        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1) {
                    b bVar = this.f2887c;
                    if (bVar != null) {
                        bVar.f2890c.setImageBitmap(DragToShareContainer.this.e);
                        this.f2887c.d = true;
                        if (DragToShareContainer.this.i != null) {
                            DragToShareContainer.this.i.a(this.f2887c.f2888a);
                        }
                    }
                } else if (action == 2) {
                    float rawX = motionEvent.getRawX();
                    float rawY = motionEvent.getRawY();
                    int i = (int) (rawX - this.f2885a);
                    int i2 = (int) (rawY - this.f2886b);
                    int left = DragToShareContainer.this.f2882a.getLeft() + i;
                    int top = DragToShareContainer.this.f2882a.getTop() + i2;
                    DragToShareContainer.this.f2882a.layout(left, top, DragToShareContainer.this.f + left, DragToShareContainer.this.g + top);
                    DragToShareContainer dragToShareContainer = DragToShareContainer.this;
                    b bVarI = dragToShareContainer.i(left + (dragToShareContainer.f / 2), top + (DragToShareContainer.this.g / 2));
                    if (bVarI == null) {
                        b bVar2 = this.f2887c;
                        if (bVar2 != null) {
                            bVar2.f2889b.setSelected(false);
                            this.f2887c = null;
                        }
                    } else if (bVarI.f2888a != DragToShareContainer.this.f2884c) {
                        this.f2887c = bVarI;
                        bVarI.f2889b.setSelected(true);
                    }
                    this.f2885a = rawX;
                    this.f2886b = rawY;
                } else if (action == 3) {
                }
                DragToShareContainer.this.f2882a.layout(DragToShareContainer.this.d.left, DragToShareContainer.this.d.top, DragToShareContainer.this.d.right, DragToShareContainer.this.d.bottom);
            } else {
                this.f2885a = motionEvent.getRawX();
                this.f2886b = motionEvent.getRawY();
                this.f2887c = null;
            }
            return true;
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2888a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        FrameLayout f2889b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ImageView f2890c;
        boolean d;
        boolean e;

        private b() {
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    public interface c {
        void a(int i);
    }

    private class d implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b f2891a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2892b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2893c;

        private d(b bVar) {
            this.f2891a = bVar;
            this.f2892b = bVar.f2889b.getLeft() - DragToShareContainer.this.d.left;
            this.f2893c = bVar.f2889b.getTop() - DragToShareContainer.this.d.top;
        }

        /* synthetic */ d(DragToShareContainer dragToShareContainer, b bVar, a aVar) {
            this(bVar);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (fFloatValue >= 0.9f) {
                this.f2891a.f2890c.setImageBitmap(DragToShareContainer.this.e);
                DragToShareContainer.this.f2882a.layout(DragToShareContainer.this.d.left, DragToShareContainer.this.d.top, DragToShareContainer.this.d.right, DragToShareContainer.this.d.bottom);
            } else {
                int i = (int) ((this.f2892b * fFloatValue) + DragToShareContainer.this.d.left);
                int i2 = (int) ((fFloatValue * this.f2893c) + DragToShareContainer.this.d.top);
                DragToShareContainer.this.f2882a.layout(i, i2, DragToShareContainer.this.f + i, DragToShareContainer.this.g + i2);
            }
        }
    }

    public DragToShareContainer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2883b = new LinkedList();
        this.d = new Rect();
        this.h = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b i(int i, int i2) {
        for (b bVar : this.f2883b) {
            if (bVar.e && !bVar.d) {
                int left = bVar.f2889b.getLeft();
                int top = bVar.f2889b.getTop();
                int right = bVar.f2889b.getRight();
                int bottom = bVar.f2889b.getBottom();
                if (i > left && i < right && i2 > top && i2 < bottom) {
                    return bVar;
                }
            }
        }
        return null;
    }

    public ImageView getDragImageView() {
        return this.f2882a;
    }

    public boolean j(int i) {
        a aVar;
        b next;
        Iterator<b> it = this.f2883b.iterator();
        while (true) {
            aVar = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next.f2888a == i) {
                break;
            }
        }
        if (next == null || !next.e || next.d) {
            return false;
        }
        next.d = true;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(500L);
        valueAnimatorOfFloat.addUpdateListener(new d(this, next, aVar));
        valueAnimatorOfFloat.start();
        return true;
    }

    public void k() {
        this.f2883b.clear();
    }

    public void l() {
        this.f2882a.setImageBitmap(this.e);
        int size = this.f2883b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f2883b.get(i);
            if (bVar.f2888a == this.f2884c) {
                bVar.f2890c.setImageBitmap(this.e);
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f2883b.size() == 0) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt.getVisibility() == 0 && (childAt instanceof FrameLayout)) {
                    b bVar = new b(null);
                    bVar.f2888a = childAt.getId();
                    FrameLayout frameLayout = (FrameLayout) childAt;
                    bVar.f2889b = frameLayout;
                    bVar.f2890c = (ImageView) frameLayout.getChildAt(0);
                    if (bVar.f2888a == this.f2884c || bVar.f2889b.isSelected()) {
                        bVar.d = true;
                    }
                    bVar.e = childAt.isEnabled() && bVar.f2890c.isEnabled();
                    this.f2883b.add(bVar);
                }
            }
            ImageView imageView = (ImageView) findViewById(R.id.view_drag_image);
            this.f2882a = imageView;
            this.f = imageView.getMeasuredWidth();
            this.g = this.f2882a.getMeasuredHeight();
            this.f2882a.setOnTouchListener(this.h);
        }
        int size = this.f2883b.size();
        for (int i6 = 0; i6 < size; i6++) {
            b bVar2 = this.f2883b.get(i6);
            if (bVar2.f2888a == this.f2884c) {
                int left = bVar2.f2889b.getLeft() + bVar2.f2890c.getLeft();
                int top = bVar2.f2889b.getTop() + bVar2.f2890c.getTop();
                int measuredWidth = bVar2.f2890c.getMeasuredWidth();
                int measuredHeight = bVar2.f2890c.getMeasuredHeight();
                ViewGroup.LayoutParams layoutParams = this.f2882a.getLayoutParams();
                if (measuredWidth != this.f2882a.getMeasuredWidth()) {
                    layoutParams.width = measuredWidth;
                    this.f = measuredWidth;
                }
                if (measuredHeight != this.f2882a.getMeasuredHeight()) {
                    layoutParams.height = measuredHeight;
                    this.g = measuredHeight;
                }
                this.d.set(left, top, measuredWidth + left, measuredHeight + top);
                ImageView imageView2 = this.f2882a;
                Rect rect = this.d;
                imageView2.layout(rect.left, rect.top, rect.right, rect.bottom);
                return;
            }
        }
    }

    public void setDrawFromViewId(int i) {
        this.f2884c = i;
    }

    public void setOnTargetSelectListener(c cVar) {
        this.i = cVar;
    }

    public void setShowBitmap(Bitmap bitmap) {
        this.e = bitmap;
    }
}
