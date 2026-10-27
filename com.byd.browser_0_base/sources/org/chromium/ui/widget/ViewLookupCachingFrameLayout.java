package org.chromium.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import defpackage.zj;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class ViewLookupCachingFrameLayout extends OptimizedFrameLayout {
    public final SparseArray<WeakReference<View>> f;

    public class a implements ViewGroup.OnHierarchyChangeListener {
        public a() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewAdded(View view, View view2) {
            ViewLookupCachingFrameLayout.this.f.remove(view2.getId());
            ViewLookupCachingFrameLayout.l(view2, this);
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewRemoved(View view, View view2) {
            ViewLookupCachingFrameLayout.this.f.remove(view2.getId());
            ViewLookupCachingFrameLayout.l(view2, null);
        }
    }

    public ViewLookupCachingFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new SparseArray<>();
        setOnHierarchyChangeListener(new a());
    }

    public static void l(View view, ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.setOnHierarchyChangeListener(onHierarchyChangeListener);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                l(viewGroup.getChildAt(i), onHierarchyChangeListener);
            }
        }
    }

    public SparseArray<WeakReference<View>> getCache() {
        return this.f;
    }

    public final View j(int i) {
        SparseArray<WeakReference<View>> sparseArray = this.f;
        WeakReference<View> weakReference = sparseArray.get(i);
        View viewFindViewById = weakReference != null ? weakReference.get() : null;
        if (viewFindViewById == null) {
            viewFindViewById = findViewById(i);
        }
        int i2 = zj.a;
        if (viewFindViewById != null) {
            sparseArray.put(i, new WeakReference<>(viewFindViewById));
        }
        return viewFindViewById;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        super.setOnHierarchyChangeListener(onHierarchyChangeListener);
    }
}
