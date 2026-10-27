package a.l.d;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class p implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f1021a;

    public p(r rVar) {
        this.f1021a = rVar;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (l.class.getName().equals(str)) {
            return new l(context, attributeSet, this.f1021a);
        }
        Fragment fragment = null;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.c.Fragment);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(a.l.c.Fragment_android_name);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(a.l.c.Fragment_android_id, -1);
        String string = typedArrayObtainStyledAttributes.getString(a.l.c.Fragment_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        if (attributeValue == null || !n.b(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment fragmentE = resourceId != -1 ? this.f1021a.E(resourceId) : null;
        if (fragmentE == null && string != null) {
            y yVar = this.f1021a.f1027c;
            if (yVar == null) {
                throw null;
            }
            int size = yVar.f1059a.size();
            while (true) {
                size--;
                if (size >= 0) {
                    Fragment fragment2 = yVar.f1059a.get(size);
                    if (fragment2 != null && string.equals(fragment2.x)) {
                        fragment = fragment2;
                        break;
                    }
                } else {
                    Iterator<x> it = yVar.f1060b.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        x next = it.next();
                        if (next != null) {
                            Fragment fragment3 = next.f1057b;
                            if (string.equals(fragment3.x)) {
                                fragment = fragment3;
                                break;
                            }
                        }
                    }
                }
            }
            fragmentE = fragment;
        }
        if (fragmentE == null && id != -1) {
            fragmentE = this.f1021a.E(id);
        }
        if (r.J(2)) {
            StringBuilder sbD = b.a.a.a.a.d("onCreateView: id=0x");
            sbD.append(Integer.toHexString(resourceId));
            sbD.append(" fname=");
            sbD.append(attributeValue);
            sbD.append(" existing=");
            sbD.append(fragmentE);
            Log.v("FragmentManager", sbD.toString());
        }
        if (fragmentE == null) {
            fragmentE = this.f1021a.H().a(context.getClassLoader(), attributeValue);
            fragmentE.m = true;
            fragmentE.v = resourceId != 0 ? resourceId : id;
            fragmentE.w = id;
            fragmentE.x = string;
            fragmentE.n = true;
            r rVar = this.f1021a;
            fragmentE.r = rVar;
            o<?> oVar = rVar.n;
            fragmentE.s = oVar;
            Context context2 = oVar.f1018b;
            fragmentE.A(attributeSet, fragmentE.f1554b);
            this.f1021a.b(fragmentE);
            r rVar2 = this.f1021a;
            rVar2.Q(fragmentE, rVar2.m);
        } else {
            if (fragmentE.n) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            fragmentE.n = true;
            o<?> oVar2 = this.f1021a.n;
            fragmentE.s = oVar2;
            Context context3 = oVar2.f1018b;
            fragmentE.A(attributeSet, fragmentE.f1554b);
        }
        r rVar3 = this.f1021a;
        if (rVar3.m >= 1 || !fragmentE.m) {
            r rVar4 = this.f1021a;
            rVar4.Q(fragmentE, rVar4.m);
        } else {
            rVar3.Q(fragmentE, 1);
        }
        View view2 = fragmentE.F;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragmentE.F.getTag() == null) {
                fragmentE.F.setTag(string);
            }
            return fragmentE.F;
        }
        throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
