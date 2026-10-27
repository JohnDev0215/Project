package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: FragmentLayoutInflaterFactory.java */
/* JADX INFO: loaded from: classes.dex */
class j implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final FragmentManager f2340e;

    /* JADX INFO: compiled from: FragmentLayoutInflaterFactory.java */
    class a implements View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ p f2341e;

        a(p pVar) {
            this.f2341e = pVar;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            Fragment fragmentK = this.f2341e.k();
            this.f2341e.m();
            x.n((ViewGroup) fragmentK.M.getParent(), j.this.f2340e).j();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    j(FragmentManager fragmentManager) {
        this.f2340e = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        p pVarV;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.f2340e);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q0.c.Fragment);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(q0.c.Fragment_android_name);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(q0.c.Fragment_android_id, -1);
        String string = typedArrayObtainStyledAttributes.getString(q0.c.Fragment_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        if (attributeValue == null || !h.b(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment fragmentG0 = resourceId != -1 ? this.f2340e.g0(resourceId) : null;
        if (fragmentG0 == null && string != null) {
            fragmentG0 = this.f2340e.h0(string);
        }
        if (fragmentG0 == null && id != -1) {
            fragmentG0 = this.f2340e.g0(id);
        }
        if (fragmentG0 == null) {
            fragmentG0 = this.f2340e.q0().a(context.getClassLoader(), attributeValue);
            fragmentG0.f2123s = true;
            fragmentG0.B = resourceId != 0 ? resourceId : id;
            fragmentG0.C = id;
            fragmentG0.D = string;
            fragmentG0.f2124t = true;
            FragmentManager fragmentManager = this.f2340e;
            fragmentG0.f2128x = fragmentManager;
            fragmentG0.f2129y = fragmentManager.t0();
            fragmentG0.G0(this.f2340e.t0().j(), attributeSet, fragmentG0.f2111g);
            pVarV = this.f2340e.g(fragmentG0);
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Fragment " + fragmentG0 + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
            }
        } else {
            if (fragmentG0.f2124t) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            fragmentG0.f2124t = true;
            FragmentManager fragmentManager2 = this.f2340e;
            fragmentG0.f2128x = fragmentManager2;
            fragmentG0.f2129y = fragmentManager2.t0();
            fragmentG0.G0(this.f2340e.t0().j(), attributeSet, fragmentG0.f2111g);
            pVarV = this.f2340e.v(fragmentG0);
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Retained Fragment " + fragmentG0 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
            }
        }
        fragmentG0.L = (ViewGroup) view;
        pVarV.m();
        pVarV.j();
        View view2 = fragmentG0.M;
        if (view2 == null) {
            throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
        }
        if (resourceId != 0) {
            view2.setId(resourceId);
        }
        if (fragmentG0.M.getTag() == null) {
            fragmentG0.M.setTag(string);
        }
        fragmentG0.M.addOnAttachStateChangeListener(new a(pVarV));
        return fragmentG0.M;
    }
}
