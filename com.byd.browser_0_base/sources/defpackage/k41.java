package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public final class k41 extends y31 {
    public float w;
    public float e = 0.1f;
    public int f = -1;
    public int g = -1;
    public int h = -1;
    public RectF i = new RectF();
    public RectF j = new RectF();
    public HashMap<String, Method> k = new HashMap<>();
    public String l = null;
    public int m = -1;
    public String n = null;
    public String o = null;
    public int p = -1;
    public int q = -1;
    public View r = null;
    public boolean s = true;
    public boolean t = true;
    public boolean u = true;
    public float v = Float.NaN;
    public boolean x = false;

    /* JADX INFO: compiled from: KeyTrigger.java */
    public static class a {
        public static final SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTrigger_framePosition, 8);
            sparseIntArray.append(R$styleable.KeyTrigger_onCross, 4);
            sparseIntArray.append(R$styleable.KeyTrigger_onNegativeCross, 1);
            sparseIntArray.append(R$styleable.KeyTrigger_onPositiveCross, 2);
            sparseIntArray.append(R$styleable.KeyTrigger_motionTarget, 7);
            sparseIntArray.append(R$styleable.KeyTrigger_triggerId, 6);
            sparseIntArray.append(R$styleable.KeyTrigger_triggerSlack, 5);
            sparseIntArray.append(R$styleable.KeyTrigger_motion_triggerOnCollision, 9);
            sparseIntArray.append(R$styleable.KeyTrigger_motion_postLayoutCollision, 10);
            sparseIntArray.append(R$styleable.KeyTrigger_triggerReceiver, 11);
            sparseIntArray.append(R$styleable.KeyTrigger_viewTransitionOnCross, 12);
            sparseIntArray.append(R$styleable.KeyTrigger_viewTransitionOnNegativeCross, 13);
            sparseIntArray.append(R$styleable.KeyTrigger_viewTransitionOnPositiveCross, 14);
        }
    }

    public k41() {
        this.d = new HashMap<>();
    }

    public static void j(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    @Override // defpackage.y31
    /* JADX INFO: renamed from: b */
    public final y31 clone() {
        k41 k41Var = new k41();
        super.c(this);
        k41Var.l = this.l;
        k41Var.m = this.m;
        k41Var.n = this.n;
        k41Var.o = this.o;
        k41Var.p = this.p;
        k41Var.q = this.q;
        k41Var.r = this.r;
        k41Var.e = this.e;
        k41Var.s = this.s;
        k41Var.t = this.t;
        k41Var.u = this.u;
        k41Var.v = this.v;
        k41Var.w = this.w;
        k41Var.x = this.x;
        k41Var.i = this.i;
        k41Var.j = this.j;
        k41Var.k = this.k;
        return k41Var;
    }

    @Override // defpackage.y31
    public final void e(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyTrigger);
        SparseIntArray sparseIntArray = a.a;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            SparseIntArray sparseIntArray2 = a.a;
            switch (sparseIntArray2.get(index)) {
                case 1:
                    this.n = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 2:
                    this.o = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 3:
                default:
                    Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
                    break;
                case 4:
                    this.l = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 5:
                    this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                    break;
                case 6:
                    this.p = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                    break;
                case 7:
                    if (MotionLayout.J0) {
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.b);
                        this.b = resourceId;
                        if (resourceId == -1) {
                            this.c = typedArrayObtainStyledAttributes.getString(index);
                        }
                    } else if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        this.c = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        this.b = typedArrayObtainStyledAttributes.getResourceId(index, this.b);
                    }
                    break;
                case 8:
                    int integer = typedArrayObtainStyledAttributes.getInteger(index, this.a);
                    this.a = integer;
                    this.v = (integer + 0.5f) / 100.0f;
                    break;
                case 9:
                    this.q = typedArrayObtainStyledAttributes.getResourceId(index, this.q);
                    break;
                case 10:
                    this.x = typedArrayObtainStyledAttributes.getBoolean(index, this.x);
                    break;
                case 11:
                    this.m = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                    break;
                case 12:
                    this.h = typedArrayObtainStyledAttributes.getResourceId(index, this.h);
                    break;
                case 13:
                    this.f = typedArrayObtainStyledAttributes.getResourceId(index, this.f);
                    break;
                case 14:
                    this.g = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                    break;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00de A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(android.view.View r10, float r11) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.k41.h(android.view.View, float):void");
    }

    public final void i(View view, String str) {
        Method method;
        if (str == null) {
            return;
        }
        if (!str.startsWith(".")) {
            if (this.k.containsKey(str)) {
                method = this.k.get(str);
                if (method == null) {
                    return;
                }
            } else {
                method = null;
            }
            if (method == null) {
                try {
                    method = view.getClass().getMethod(str, new Class[0]);
                    this.k.put(str, method);
                } catch (NoSuchMethodException unused) {
                    this.k.put(str, null);
                    Log.e("KeyTrigger", "Could not find method \"" + str + "\"on class " + view.getClass().getSimpleName() + " " + b70.d(view));
                    return;
                }
            }
            try {
                method.invoke(view, new Object[0]);
                return;
            } catch (Exception unused2) {
                Log.e("KeyTrigger", "Exception in call \"" + this.l + "\"on class " + view.getClass().getSimpleName() + " " + b70.d(view));
                return;
            }
        }
        boolean z = str.length() == 1;
        if (!z) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.d.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z || lowerCase.matches(str)) {
                ox oxVar = this.d.get(str2);
                if (oxVar != null) {
                    Class<?> cls = view.getClass();
                    boolean z2 = oxVar.a;
                    String str3 = oxVar.b;
                    String strJ = !z2 ? C0175b.j("set", str3) : str3;
                    try {
                        switch (gq2.m(oxVar.c)) {
                            case 0:
                            case 7:
                                cls.getMethod(strJ, Integer.TYPE).invoke(view, Integer.valueOf(oxVar.d));
                                break;
                            case 1:
                                cls.getMethod(strJ, Float.TYPE).invoke(view, Float.valueOf(oxVar.e));
                                break;
                            case 2:
                                cls.getMethod(strJ, Integer.TYPE).invoke(view, Integer.valueOf(oxVar.h));
                                break;
                            case 3:
                                Method method2 = cls.getMethod(strJ, Drawable.class);
                                ColorDrawable colorDrawable = new ColorDrawable();
                                colorDrawable.setColor(oxVar.h);
                                method2.invoke(view, colorDrawable);
                                break;
                            case 4:
                                cls.getMethod(strJ, CharSequence.class).invoke(view, oxVar.f);
                                break;
                            case 5:
                                cls.getMethod(strJ, Boolean.TYPE).invoke(view, Boolean.valueOf(oxVar.g));
                                break;
                            case 6:
                                cls.getMethod(strJ, Float.TYPE).invoke(view, Float.valueOf(oxVar.e));
                                break;
                        }
                    } catch (IllegalAccessException e) {
                        StringBuilder sbN = C0175b.n(" Custom Attribute \"", str3, "\" not found on ");
                        sbN.append(cls.getName());
                        Log.e("TransitionLayout", sbN.toString(), e);
                    } catch (NoSuchMethodException e2) {
                        Log.e("TransitionLayout", cls.getName() + " must have a method " + strJ, e2);
                    } catch (InvocationTargetException e3) {
                        StringBuilder sbN2 = C0175b.n(" Custom Attribute \"", str3, "\" not found on ");
                        sbN2.append(cls.getName());
                        Log.e("TransitionLayout", sbN2.toString(), e3);
                    }
                }
            }
        }
    }

    @Override // defpackage.y31
    public final void a(HashMap<String, rn3> map) {
    }

    @Override // defpackage.y31
    public final void d(HashSet<String> hashSet) {
    }
}
