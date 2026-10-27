package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/* JADX INFO: compiled from: FragmentLayoutInflaterFactory.java */
/* JADX INFO: loaded from: classes.dex */
class h implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f1440b;

    h(j jVar) {
        this.f1440b = jVar;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.f1440b);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.k.c.Fragment);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(b.k.c.Fragment_android_name);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(b.k.c.Fragment_android_id, -1);
        String string = typedArrayObtainStyledAttributes.getString(b.k.c.Fragment_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        if (attributeValue == null || !f.b(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment fragmentX = resourceId != -1 ? this.f1440b.X(resourceId) : null;
        if (fragmentX == null && string != null) {
            fragmentX = this.f1440b.Y(string);
        }
        if (fragmentX == null && id != -1) {
            fragmentX = this.f1440b.X(id);
        }
        if (j.q0(2)) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + fragmentX);
        }
        if (fragmentX == null) {
            fragmentX = this.f1440b.f0().a(context.getClassLoader(), attributeValue);
            fragmentX.o = true;
            fragmentX.x = resourceId != 0 ? resourceId : id;
            fragmentX.y = id;
            fragmentX.z = string;
            fragmentX.p = true;
            j jVar = this.f1440b;
            fragmentX.t = jVar;
            g<?> gVar = jVar.p;
            fragmentX.u = gVar;
            fragmentX.p0(gVar.h(), attributeSet, fragmentX.f1363d);
            this.f1440b.d(fragmentX);
            this.f1440b.A0(fragmentX);
        } else {
            if (fragmentX.p) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            fragmentX.p = true;
            g<?> gVar2 = this.f1440b.p;
            fragmentX.u = gVar2;
            fragmentX.p0(gVar2.h(), attributeSet, fragmentX.f1363d);
        }
        j jVar2 = this.f1440b;
        if (jVar2.o >= 1 || !fragmentX.o) {
            jVar2.A0(fragmentX);
        } else {
            jVar2.B0(fragmentX, 1);
        }
        View view2 = fragmentX.I;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragmentX.I.getTag() == null) {
                fragmentX.I.setTag(string);
            }
            return fragmentX.I;
        }
        throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
    }
}
