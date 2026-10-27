package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.strictmode.FragmentStrictMode$Flag;
import androidx.fragment.app.strictmode.FragmentTagUsageViolation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class dx0 implements LayoutInflater.Factory2 {
    public final ox0 c;

    public dx0(ox0 ox0Var) {
        this.c = ox0Var;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        androidx.fragment.app.b bVarF;
        boolean zEquals = bx0.class.getName().equals(str);
        ox0 ox0Var = this.c;
        if (zEquals) {
            return new bx0(context, attributeSet, ox0Var);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, vz1.a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (attributeValue != null) {
            try {
                if (ww0.class.isAssignableFrom(ix0.b(context.getClassLoader(), attributeValue))) {
                    int id = view != null ? view.getId() : 0;
                    if (id == -1 && resourceId == -1 && string == null) {
                        throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                    }
                    ww0 ww0VarA = resourceId != -1 ? ox0Var.A(resourceId) : null;
                    if (ww0VarA == null && string != null) {
                        gh2 gh2Var = ox0Var.c;
                        int size = ((ArrayList) gh2Var.l).size() - 1;
                        while (true) {
                            if (size >= 0) {
                                ww0 ww0Var = (ww0) ((ArrayList) gh2Var.l).get(size);
                                if (ww0Var != null && string.equals(ww0Var.F)) {
                                    ww0VarA = ww0Var;
                                    break;
                                }
                                size--;
                            } else {
                                Iterator it = ((HashMap) gh2Var.e).values().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        ww0VarA = null;
                                        break;
                                    }
                                    androidx.fragment.app.b bVar = (androidx.fragment.app.b) it.next();
                                    if (bVar != null) {
                                        ww0 ww0Var2 = bVar.c;
                                        if (string.equals(ww0Var2.F)) {
                                            ww0VarA = ww0Var2;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (ww0VarA == null && id != -1) {
                        ww0VarA = ox0Var.A(id);
                    }
                    if (ww0VarA == null) {
                        ix0 ix0VarC = ox0Var.C();
                        context.getClassLoader();
                        ww0VarA = ix0VarC.a(attributeValue);
                        ww0VarA.u = true;
                        ww0VarA.D = resourceId != 0 ? resourceId : id;
                        ww0VarA.E = id;
                        ww0VarA.F = string;
                        ww0VarA.v = true;
                        ww0VarA.z = ox0Var;
                        yw0 yw0Var = ox0Var.t;
                        ww0VarA.A = yw0Var;
                        Context context2 = yw0Var.r;
                        ww0VarA.K = true;
                        if ((yw0Var != null ? yw0Var.q : null) != null) {
                            ww0VarA.K = true;
                        }
                        bVarF = ox0Var.a(ww0VarA);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            ww0VarA.toString();
                            Integer.toHexString(resourceId);
                        }
                    } else {
                        if (ww0VarA.v) {
                            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
                        }
                        ww0VarA.v = true;
                        ww0VarA.z = ox0Var;
                        yw0 yw0Var2 = ox0Var.t;
                        ww0VarA.A = yw0Var2;
                        Context context3 = yw0Var2.r;
                        ww0VarA.K = true;
                        if ((yw0Var2 != null ? yw0Var2.q : null) != null) {
                            ww0VarA.K = true;
                        }
                        bVarF = ox0Var.f(ww0VarA);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            ww0VarA.toString();
                            Integer.toHexString(resourceId);
                        }
                    }
                    ViewGroup viewGroup = (ViewGroup) view;
                    tx0 tx0Var = ux0.a;
                    FragmentTagUsageViolation fragmentTagUsageViolation = new FragmentTagUsageViolation(ww0VarA, viewGroup);
                    ux0.c(fragmentTagUsageViolation);
                    tx0 tx0VarA = ux0.a(ww0VarA);
                    if (tx0VarA.a.contains(FragmentStrictMode$Flag.l) && ux0.e(tx0VarA, ww0VarA.getClass(), FragmentTagUsageViolation.class)) {
                        ux0.b(tx0VarA, fragmentTagUsageViolation);
                    }
                    ww0VarA.L = viewGroup;
                    bVarF.k();
                    bVarF.j();
                    View view2 = ww0VarA.M;
                    if (view2 == null) {
                        throw new IllegalStateException(u4.j("Fragment ", attributeValue, " did not create a view."));
                    }
                    if (resourceId != 0) {
                        view2.setId(resourceId);
                    }
                    if (ww0VarA.M.getTag() == null) {
                        ww0VarA.M.setTag(string);
                    }
                    ww0VarA.M.addOnAttachStateChangeListener(new cx0(this, bVarF));
                    return ww0VarA.M;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        return null;
    }
}
