package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends d {
    HashMap<String, Method> A;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1429g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f1430h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1431i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f1432j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private String f1433k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1434l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1435m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private View f1436n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    float f1437o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f1438p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f1439q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1440r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f1441s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f1442t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f1443u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    int f1444v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    int f1445w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    int f1446x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    RectF f1447y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    RectF f1448z;

    /* JADX INFO: compiled from: KeyTrigger.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1449a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1449a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.i.KeyTrigger_framePosition, 8);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_onCross, 4);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_onNegativeCross, 1);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_onPositiveCross, 2);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_motionTarget, 7);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_triggerId, 6);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_triggerSlack, 5);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_motion_triggerOnCollision, 9);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_motion_postLayoutCollision, 10);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_triggerReceiver, 11);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_viewTransitionOnCross, 12);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_viewTransitionOnNegativeCross, 13);
            f1449a.append(androidx.constraintlayout.widget.i.KeyTrigger_viewTransitionOnPositiveCross, 14);
        }

        public static void a(k kVar, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArray.getIndex(i7);
                switch (f1449a.get(index)) {
                    case 1:
                        kVar.f1432j = typedArray.getString(index);
                        break;
                    case 2:
                        kVar.f1433k = typedArray.getString(index);
                        break;
                    case 3:
                    default:
                        Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1449a.get(index));
                        break;
                    case 4:
                        kVar.f1430h = typedArray.getString(index);
                        break;
                    case 5:
                        kVar.f1437o = typedArray.getFloat(index, kVar.f1437o);
                        break;
                    case 6:
                        kVar.f1434l = typedArray.getResourceId(index, kVar.f1434l);
                        break;
                    case 7:
                        if (MotionLayout.f1260h1) {
                            int resourceId = typedArray.getResourceId(index, kVar.f1351b);
                            kVar.f1351b = resourceId;
                            if (resourceId == -1) {
                                kVar.f1352c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            kVar.f1352c = typedArray.getString(index);
                        } else {
                            kVar.f1351b = typedArray.getResourceId(index, kVar.f1351b);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, kVar.f1350a);
                        kVar.f1350a = integer;
                        kVar.f1441s = (integer + 0.5f) / 100.0f;
                        break;
                    case 9:
                        kVar.f1435m = typedArray.getResourceId(index, kVar.f1435m);
                        break;
                    case 10:
                        kVar.f1443u = typedArray.getBoolean(index, kVar.f1443u);
                        break;
                    case 11:
                        kVar.f1431i = typedArray.getResourceId(index, kVar.f1431i);
                        break;
                    case 12:
                        kVar.f1446x = typedArray.getResourceId(index, kVar.f1446x);
                        break;
                    case 13:
                        kVar.f1444v = typedArray.getResourceId(index, kVar.f1444v);
                        break;
                    case 14:
                        kVar.f1445w = typedArray.getResourceId(index, kVar.f1445w);
                        break;
                }
            }
        }
    }

    public k() {
        int i7 = d.f1349f;
        this.f1431i = i7;
        this.f1432j = null;
        this.f1433k = null;
        this.f1434l = i7;
        this.f1435m = i7;
        this.f1436n = null;
        this.f1437o = 0.1f;
        this.f1438p = true;
        this.f1439q = true;
        this.f1440r = true;
        this.f1441s = Float.NaN;
        this.f1443u = false;
        this.f1444v = i7;
        this.f1445w = i7;
        this.f1446x = i7;
        this.f1447y = new RectF();
        this.f1448z = new RectF();
        this.A = new HashMap<>();
        this.f1353d = 5;
        this.f1354e = new HashMap<>();
    }

    private void v(String str, View view) {
        Method method;
        if (str == null) {
            return;
        }
        if (str.startsWith(".")) {
            w(str, view);
            return;
        }
        if (this.A.containsKey(str)) {
            method = this.A.get(str);
            if (method == null) {
                return;
            }
        } else {
            method = null;
        }
        if (method == null) {
            try {
                method = view.getClass().getMethod(str, new Class[0]);
                this.A.put(str, method);
            } catch (NoSuchMethodException unused) {
                this.A.put(str, null);
                Log.e("KeyTrigger", "Could not find method \"" + str + "\"on class " + view.getClass().getSimpleName() + " " + androidx.constraintlayout.motion.widget.a.d(view));
                return;
            }
        }
        try {
            method.invoke(view, new Object[0]);
        } catch (Exception unused2) {
            Log.e("KeyTrigger", "Exception in call \"" + this.f1430h + "\"on class " + view.getClass().getSimpleName() + " " + androidx.constraintlayout.motion.widget.a.d(view));
        }
    }

    private void w(String str, View view) {
        boolean z6 = str.length() == 1;
        if (!z6) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.f1354e.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z6 || lowerCase.matches(str)) {
                androidx.constraintlayout.widget.a aVar = this.f1354e.get(str2);
                if (aVar != null) {
                    aVar.a(view);
                }
            }
        }
    }

    private void x(RectF rectF, View view, boolean z6) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z6) {
            view.getMatrix().mapRect(rectF);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void a(HashMap<String, t.d> map) {
    }

    @Override // androidx.constraintlayout.motion.widget.d
    /* JADX INFO: renamed from: b */
    public d clone() {
        return new k().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public d c(d dVar) {
        super.c(dVar);
        k kVar = (k) dVar;
        this.f1429g = kVar.f1429g;
        this.f1430h = kVar.f1430h;
        this.f1431i = kVar.f1431i;
        this.f1432j = kVar.f1432j;
        this.f1433k = kVar.f1433k;
        this.f1434l = kVar.f1434l;
        this.f1435m = kVar.f1435m;
        this.f1436n = kVar.f1436n;
        this.f1437o = kVar.f1437o;
        this.f1438p = kVar.f1438p;
        this.f1439q = kVar.f1439q;
        this.f1440r = kVar.f1440r;
        this.f1441s = kVar.f1441s;
        this.f1442t = kVar.f1442t;
        this.f1443u = kVar.f1443u;
        this.f1447y = kVar.f1447y;
        this.f1448z = kVar.f1448z;
        this.A = kVar.A;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void d(HashSet<String> hashSet) {
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void e(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.KeyTrigger), context);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void u(float r10, android.view.View r11) {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.k.u(float, android.view.View):void");
    }
}
