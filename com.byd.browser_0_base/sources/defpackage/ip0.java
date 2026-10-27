package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$styleable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.f;
import androidx.fragment.app.h;
import androidx.fragment.app.j;
import defpackage.tp0;

/* JADX INFO: compiled from: FragmentLayoutInflaterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class ip0 implements LayoutInflater.Factory2 {
    public final FragmentManager e;

    public ip0(FragmentManager fragmentManager) {
        this.e = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zIsAssignableFrom;
        h hVarE;
        boolean zEquals = FragmentContainerView.class.getName().equals(str);
        FragmentManager fragmentManager = this.e;
        if (zEquals) {
            return new FragmentContainerView(context, attributeSet, fragmentManager);
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
        if (attributeValue != null) {
            try {
                zIsAssignableFrom = Fragment.class.isAssignableFrom(f.b(context.getClassLoader(), attributeValue));
            } catch (ClassNotFoundException unused) {
                zIsAssignableFrom = false;
            }
            if (zIsAssignableFrom) {
                int id = view != null ? view.getId() : 0;
                if (id == -1 && resourceId == -1 && string == null) {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                }
                Fragment fragmentA = resourceId != -1 ? fragmentManager.A(resourceId) : null;
                if (fragmentA == null && string != null) {
                    fragmentA = fragmentManager.B(string);
                }
                if (fragmentA == null && id != -1) {
                    fragmentA = fragmentManager.A(id);
                }
                if (fragmentA == null) {
                    f fVarF = fragmentManager.F();
                    context.getClassLoader();
                    fragmentA = fVarF.a(attributeValue);
                    fragmentA.q = true;
                    fragmentA.z = resourceId != 0 ? resourceId : id;
                    fragmentA.A = id;
                    fragmentA.B = string;
                    fragmentA.r = true;
                    fragmentA.v = fragmentManager;
                    hp0<?> hp0Var = fragmentManager.u;
                    fragmentA.w = hp0Var;
                    Context context2 = hp0Var.l;
                    fragmentA.H = true;
                    if ((hp0Var != null ? hp0Var.k : null) != null) {
                        fragmentA.H = true;
                    }
                    hVarE = fragmentManager.a(fragmentA);
                } else {
                    if (fragmentA.r) {
                        throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
                    }
                    fragmentA.r = true;
                    fragmentA.v = fragmentManager;
                    hp0<?> hp0Var2 = fragmentManager.u;
                    fragmentA.w = hp0Var2;
                    Context context3 = hp0Var2.l;
                    fragmentA.H = true;
                    if ((hp0Var2 != null ? hp0Var2.k : null) != null) {
                        fragmentA.H = true;
                    }
                    hVarE = fragmentManager.e(fragmentA);
                }
                ViewGroup viewGroup = (ViewGroup) view;
                tp0.b bVar = tp0.a;
                up0 up0Var = new up0(fragmentA, viewGroup, 0);
                tp0.b bVarA = tp0.a(fragmentA);
                if (bVarA.a.contains(tp0.a.DETECT_FRAGMENT_TAG_USAGE) && tp0.d(bVarA, fragmentA.getClass(), up0.class)) {
                    tp0.b(bVarA, up0Var);
                }
                fragmentA.I = viewGroup;
                hVarE.k();
                hVarE.j();
                View view2 = fragmentA.f12J;
                if (view2 == null) {
                    throw new IllegalStateException(gq2.g("Fragment ", attributeValue, " did not create a view."));
                }
                if (resourceId != 0) {
                    view2.setId(resourceId);
                }
                if (fragmentA.f12J.getTag() == null) {
                    fragmentA.f12J.setTag(string);
                }
                fragmentA.f12J.addOnAttachStateChangeListener(new a(hVarE));
                return fragmentA.f12J;
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: FragmentLayoutInflaterFactory.java */
    public class a implements View.OnAttachStateChangeListener {
        public final /* synthetic */ h e;

        public a(h hVar) {
            this.e = hVar;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            h hVar = this.e;
            Fragment fragment = hVar.c;
            hVar.k();
            j.f((ViewGroup) fragment.f12J.getParent(), ip0.this.e).e();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
        }
    }
}
