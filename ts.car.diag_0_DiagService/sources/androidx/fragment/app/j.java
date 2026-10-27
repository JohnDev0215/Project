package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.R$styleable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class j implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final FragmentManager f696c;

    j(FragmentManager fragmentManager) {
        this.f696c = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory
    @Nullable
    public View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    @Nullable
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        p pVarV;
        if (f.class.getName().equals(str)) {
            return new f(context, attributeSet, this.f696c);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Fragment);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(R$styleable.Fragment_android_name);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.Fragment_android_id, -1);
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.Fragment_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        if (attributeValue == null || !h.b(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment fragmentG0 = resourceId != -1 ? this.f696c.g0(resourceId) : null;
        if (fragmentG0 == null && string != null) {
            fragmentG0 = this.f696c.h0(string);
        }
        if (fragmentG0 == null && id != -1) {
            fragmentG0 = this.f696c.g0(id);
        }
        if (FragmentManager.D0(2)) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + fragmentG0);
        }
        if (fragmentG0 == null) {
            fragmentG0 = this.f696c.p0().a(context.getClassLoader(), attributeValue);
            fragmentG0.p = true;
            fragmentG0.y = resourceId != 0 ? resourceId : id;
            fragmentG0.z = id;
            fragmentG0.A = string;
            fragmentG0.q = true;
            FragmentManager fragmentManager = this.f696c;
            fragmentG0.u = fragmentManager;
            fragmentG0.v = fragmentManager.s0();
            fragmentG0.p0(this.f696c.s0().k(), attributeSet, fragmentG0.d);
            pVarV = this.f696c.v(fragmentG0);
            this.f696c.g(fragmentG0);
        } else {
            if (fragmentG0.q) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            fragmentG0.q = true;
            FragmentManager fragmentManager2 = this.f696c;
            fragmentG0.u = fragmentManager2;
            fragmentG0.v = fragmentManager2.s0();
            fragmentG0.p0(this.f696c.s0().k(), attributeSet, fragmentG0.d);
            pVarV = this.f696c.v(fragmentG0);
        }
        pVarV.k();
        pVarV.i();
        View view2 = fragmentG0.J;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragmentG0.J.getTag() == null) {
                fragmentG0.J.setTag(string);
            }
            return fragmentG0.J;
        }
        throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
    }
}
