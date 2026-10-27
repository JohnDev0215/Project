package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionLayout.g;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.a;
import defpackage.b70;
import defpackage.bj1;
import defpackage.f41;
import defpackage.gq2;
import defpackage.hf0;
import defpackage.os2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: MotionScene.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final MotionLayout a;
    public os2 b;
    public b c;
    public final ArrayList<b> d;
    public b e;
    public final ArrayList<b> f;
    public final SparseArray<androidx.constraintlayout.widget.a> g;
    public final HashMap<String, Integer> h;
    public final SparseIntArray i;
    public int j;
    public int k;
    public MotionEvent l;
    public boolean m;
    public boolean n;
    public MotionLayout.f o;
    public boolean p;
    public final d q;
    public float r;
    public float s;

    /* JADX INFO: renamed from: androidx.constraintlayout.motion.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MotionScene.java */
    public class InterpolatorC0002a implements Interpolator {
        public final /* synthetic */ hf0 a;

        public InterpolatorC0002a(hf0 hf0Var) {
            this.a = hf0Var;
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            return (float) this.a.a(f);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public a(android.content.Context r17, androidx.constraintlayout.motion.widget.MotionLayout r18, int r19) {
        /*
            Method dump skipped, instruction units count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.a.<init>(android.content.Context, androidx.constraintlayout.motion.widget.MotionLayout, int):void");
    }

    public static int d(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
        } else {
            identifier = -1;
        }
        if (identifier != -1) {
            return identifier;
        }
        if (str.length() > 1) {
            return Integer.parseInt(str.substring(1));
        }
        Log.e("MotionScene", "error in parsing id");
        return identifier;
    }

    public final boolean a(int i, MotionLayout motionLayout) {
        if (this.o != null) {
            return false;
        }
        for (b bVar : this.d) {
            int i2 = bVar.n;
            if (i2 != 0) {
                b bVar2 = this.c;
                if (bVar2 == bVar) {
                    if ((bVar2.r & 2) != 0) {
                        continue;
                    }
                }
                int i3 = bVar.d;
                MotionLayout.i iVar = MotionLayout.i.FINISHED;
                MotionLayout.i iVar2 = MotionLayout.i.MOVING;
                MotionLayout.i iVar3 = MotionLayout.i.SETUP;
                if (i == i3 && (i2 == 4 || i2 == 2)) {
                    motionLayout.setState(iVar);
                    motionLayout.setTransition(bVar);
                    if (bVar.n == 4) {
                        motionLayout.t(1.0f);
                        motionLayout.z0 = null;
                        motionLayout.setState(iVar3);
                        motionLayout.setState(iVar2);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.v(true);
                        motionLayout.setState(iVar3);
                        motionLayout.setState(iVar2);
                        motionLayout.setState(iVar);
                        motionLayout.C();
                    }
                    return true;
                }
                if (i == bVar.c && (i2 == 3 || i2 == 1)) {
                    motionLayout.setState(iVar);
                    motionLayout.setTransition(bVar);
                    if (bVar.n == 3) {
                        motionLayout.t(0.0f);
                        motionLayout.setState(iVar3);
                        motionLayout.setState(iVar2);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.v(true);
                        motionLayout.setState(iVar3);
                        motionLayout.setState(iVar2);
                        motionLayout.setState(iVar);
                        motionLayout.C();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public final androidx.constraintlayout.widget.a b(int i) {
        int iA;
        os2 os2Var = this.b;
        if (os2Var != null && (iA = os2Var.a(i)) != -1) {
            i = iA;
        }
        SparseArray<androidx.constraintlayout.widget.a> sparseArray = this.g;
        if (sparseArray.get(i) != null) {
            return sparseArray.get(i);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + b70.c(this.a.getContext(), i) + " In MotionScene");
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public final int c() {
        b bVar = this.c;
        return bVar != null ? bVar.h : this.j;
    }

    public final Interpolator e() {
        b bVar = this.c;
        int i = bVar.e;
        if (i == -2) {
            return AnimationUtils.loadInterpolator(this.a.getContext(), this.c.g);
        }
        if (i == -1) {
            return new InterpolatorC0002a(hf0.c(bVar.f));
        }
        if (i == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i == 1) {
            return new AccelerateInterpolator();
        }
        if (i == 2) {
            return new DecelerateInterpolator();
        }
        if (i == 4) {
            return new BounceInterpolator();
        }
        if (i == 5) {
            return new OvershootInterpolator();
        }
        if (i != 6) {
            return null;
        }
        return new AnticipateInterpolator();
    }

    public final void f(bj1 bj1Var) {
        b bVar = this.c;
        if (bVar != null) {
            Iterator<f41> it = bVar.k.iterator();
            while (it.hasNext()) {
                it.next().a(bj1Var);
            }
        } else {
            b bVar2 = this.e;
            if (bVar2 != null) {
                Iterator<f41> it2 = bVar2.k.iterator();
                while (it2.hasNext()) {
                    it2.next().a(bj1Var);
                }
            }
        }
    }

    public final float g() {
        androidx.constraintlayout.motion.widget.b bVar;
        b bVar2 = this.c;
        if (bVar2 == null || (bVar = bVar2.l) == null) {
            return 0.0f;
        }
        return bVar.t;
    }

    public final int h() {
        b bVar = this.c;
        if (bVar == null) {
            return -1;
        }
        return bVar.d;
    }

    public final int i(Context context, XmlResourceParser xmlResourceParser) {
        String attributeValue;
        androidx.constraintlayout.widget.a aVar = new androidx.constraintlayout.widget.a();
        aVar.f = false;
        int attributeCount = xmlResourceParser.getAttributeCount();
        int iD = -1;
        int iD2 = -1;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlResourceParser.getAttributeName(i);
            attributeValue = xmlResourceParser.getAttributeValue(i);
            attributeName.getClass();
            switch (attributeName) {
                case "deriveConstraintsFrom":
                    iD2 = d(context, attributeValue);
                    break;
                case "constraintRotate":
                    try {
                        aVar.d = Integer.parseInt(attributeValue);
                        break;
                    } catch (NumberFormatException unused) {
                        attributeValue.getClass();
                        switch (attributeValue) {
                            case "x_left":
                                aVar.d = 4;
                                break;
                            case "left":
                                aVar.d = 2;
                                break;
                            case "none":
                                aVar.d = 0;
                                break;
                            case "right":
                                aVar.d = 1;
                                break;
                            case "x_right":
                                aVar.d = 3;
                                break;
                        }
                    }
                    break;
                case "id":
                    iD = d(context, attributeValue);
                    int iIndexOf = attributeValue.indexOf(47);
                    if (iIndexOf >= 0) {
                        attributeValue = attributeValue.substring(iIndexOf + 1);
                    }
                    this.h.put(attributeValue, Integer.valueOf(iD));
                    aVar.a = b70.c(context, iD);
                    break;
                case "stateLabels":
                    aVar.c = attributeValue.split(",");
                    int i2 = 0;
                    while (true) {
                        String[] strArr = aVar.c;
                        if (i2 < strArr.length) {
                            strArr[i2] = strArr[i2].trim();
                            i2++;
                        }
                    }
                    break;
            }
        }
        if (iD != -1) {
            int i3 = this.a.O;
            aVar.l(context, xmlResourceParser);
            if (iD2 != -1) {
                this.i.put(iD, iD2);
            }
            this.g.put(iD, aVar);
        }
        return iD;
    }

    public final int j(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && "ConstraintSet".equals(name)) {
                    return i(context, xml);
                }
            }
            return -1;
        } catch (IOException e) {
            Log.e("MotionScene", "Error parsing resource: " + i, e);
            return -1;
        } catch (XmlPullParserException e2) {
            Log.e("MotionScene", "Error parsing resource: " + i, e2);
            return -1;
        }
    }

    public final void k(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.include);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.include_constraintSet) {
                j(context, typedArrayObtainStyledAttributes.getResourceId(index, -1));
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void l(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.MotionScene_defaultDuration) {
                int i2 = typedArrayObtainStyledAttributes.getInt(index, this.j);
                this.j = i2;
                if (i2 < 8) {
                    this.j = 8;
                }
            } else if (index == R$styleable.MotionScene_layoutDuringTransition) {
                this.k = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void m(int i, MotionLayout motionLayout) {
        SparseArray<androidx.constraintlayout.widget.a> sparseArray = this.g;
        androidx.constraintlayout.widget.a aVar = sparseArray.get(i);
        aVar.b = aVar.a;
        int i2 = this.i.get(i);
        HashMap<Integer, a.C0004a> map = aVar.g;
        if (i2 > 0) {
            m(i2, motionLayout);
            androidx.constraintlayout.widget.a aVar2 = sparseArray.get(i2);
            if (aVar2 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + b70.c(this.a.getContext(), i2));
                return;
            }
            aVar.b += "/" + aVar2.b;
            HashMap<Integer, a.C0004a> map2 = aVar2.g;
            for (Integer num : map2.keySet()) {
                int iIntValue = num.intValue();
                a.C0004a c0004a = map2.get(num);
                if (!map.containsKey(Integer.valueOf(iIntValue))) {
                    map.put(Integer.valueOf(iIntValue), new a.C0004a());
                }
                a.C0004a c0004a2 = map.get(Integer.valueOf(iIntValue));
                if (c0004a2 != null) {
                    a.b bVar = c0004a2.e;
                    if (!bVar.b) {
                        bVar.a(c0004a.e);
                    }
                    a.d dVar = c0004a2.c;
                    if (!dVar.a) {
                        a.d dVar2 = c0004a.c;
                        dVar.a = dVar2.a;
                        dVar.b = dVar2.b;
                        dVar.d = dVar2.d;
                        dVar.e = dVar2.e;
                        dVar.c = dVar2.c;
                    }
                    a.e eVar = c0004a2.f;
                    if (!eVar.a) {
                        eVar.a(c0004a.f);
                    }
                    a.c cVar = c0004a2.d;
                    if (!cVar.a) {
                        cVar.a(c0004a.d);
                    }
                    for (String str : c0004a.g.keySet()) {
                        if (!c0004a2.g.containsKey(str)) {
                            c0004a2.g.put(str, c0004a.g.get(str));
                        }
                    }
                }
            }
        } else {
            aVar.b = gq2.i(new StringBuilder(), aVar.b, "  layout");
            int childCount = motionLayout.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = motionLayout.getChildAt(i3);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                int id = childAt.getId();
                if (aVar.f && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (!map.containsKey(Integer.valueOf(id))) {
                    map.put(Integer.valueOf(id), new a.C0004a());
                }
                a.C0004a c0004a3 = map.get(Integer.valueOf(id));
                if (c0004a3 != null) {
                    a.b bVar2 = c0004a3.e;
                    if (!bVar2.b) {
                        c0004a3.c(id, layoutParams);
                        if (childAt instanceof ConstraintHelper) {
                            bVar2.j0 = ((ConstraintHelper) childAt).getReferencedIds();
                            if (childAt instanceof Barrier) {
                                Barrier barrier = (Barrier) childAt;
                                bVar2.o0 = barrier.getAllowsGoneWidget();
                                bVar2.g0 = barrier.getType();
                                bVar2.h0 = barrier.getMargin();
                            }
                        }
                        bVar2.b = true;
                    }
                    a.d dVar3 = c0004a3.c;
                    if (!dVar3.a) {
                        dVar3.b = childAt.getVisibility();
                        dVar3.d = childAt.getAlpha();
                        dVar3.a = true;
                    }
                    a.e eVar2 = c0004a3.f;
                    if (!eVar2.a) {
                        eVar2.a = true;
                        eVar2.b = childAt.getRotation();
                        eVar2.c = childAt.getRotationX();
                        eVar2.d = childAt.getRotationY();
                        eVar2.e = childAt.getScaleX();
                        eVar2.f = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (pivotX != 0.0d || pivotY != 0.0d) {
                            eVar2.g = pivotX;
                            eVar2.h = pivotY;
                        }
                        eVar2.j = childAt.getTranslationX();
                        eVar2.k = childAt.getTranslationY();
                        eVar2.l = childAt.getTranslationZ();
                        if (eVar2.m) {
                            eVar2.n = childAt.getElevation();
                        }
                    }
                }
            }
        }
        for (a.C0004a c0004a4 : map.values()) {
            if (c0004a4.h != null) {
                if (c0004a4.b == null) {
                    c0004a4.h.e(aVar.i(c0004a4.a));
                } else {
                    Iterator<Integer> it = map.keySet().iterator();
                    while (it.hasNext()) {
                        a.C0004a c0004aI = aVar.i(it.next().intValue());
                        String str2 = c0004aI.e.l0;
                        if (str2 != null && c0004a4.b.matches(str2)) {
                            c0004a4.h.e(c0004aI);
                            c0004aI.g.putAll((HashMap) c0004a4.g.clone());
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n(int r9, int r10) {
        /*
            r8 = this;
            os2 r0 = r8.b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.a(r9)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r9
        Ld:
            os2 r2 = r8.b
            int r2 = r2.a(r10)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r9
        L17:
            r2 = r10
        L18:
            androidx.constraintlayout.motion.widget.a$b r3 = r8.c
            if (r3 == 0) goto L25
            int r4 = r3.c
            if (r4 != r10) goto L25
            int r3 = r3.d
            if (r3 != r9) goto L25
            return
        L25:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.a$b> r3 = r8.d
            java.util.Iterator r4 = r3.iterator()
        L2b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L51
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.motion.widget.a$b r5 = (androidx.constraintlayout.motion.widget.a.b) r5
            int r6 = r5.c
            if (r6 != r2) goto L3f
            int r7 = r5.d
            if (r7 == r0) goto L45
        L3f:
            if (r6 != r10) goto L2b
            int r6 = r5.d
            if (r6 != r9) goto L2b
        L45:
            r8.c = r5
            androidx.constraintlayout.motion.widget.b r9 = r5.l
            if (r9 == 0) goto L50
            boolean r8 = r8.p
            r9.c(r8)
        L50:
            return
        L51:
            androidx.constraintlayout.motion.widget.a$b r9 = r8.e
            java.util.ArrayList<androidx.constraintlayout.motion.widget.a$b> r4 = r8.f
            java.util.Iterator r4 = r4.iterator()
        L59:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L6b
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.motion.widget.a$b r5 = (androidx.constraintlayout.motion.widget.a.b) r5
            int r6 = r5.c
            if (r6 != r10) goto L59
            r9 = r5
            goto L59
        L6b:
            androidx.constraintlayout.motion.widget.a$b r10 = new androidx.constraintlayout.motion.widget.a$b
            r10.<init>(r8, r9)
            r10.d = r0
            r10.c = r2
            if (r0 == r1) goto L79
            r3.add(r10)
        L79:
            r8.c = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.a.n(int, int):void");
    }

    public final boolean o() {
        Iterator<b> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        b bVar = this.c;
        return (bVar == null || bVar.l == null) ? false : true;
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {
        public int a;
        public boolean b;
        public int c;
        public int d;
        public int e;
        public String f;
        public int g;
        public int h;
        public float i;
        public final a j;
        public final ArrayList<f41> k;
        public androidx.constraintlayout.motion.widget.b l;
        public final ArrayList<ViewOnClickListenerC0003a> m;
        public int n;
        public boolean o;
        public int p;
        public int q;
        public int r;

        /* JADX INFO: renamed from: androidx.constraintlayout.motion.widget.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MotionScene.java */
        public static class ViewOnClickListenerC0003a implements View.OnClickListener {
            public final b e;
            public final int f;
            public final int g;

            public ViewOnClickListenerC0003a(Context context, b bVar, XmlResourceParser xmlResourceParser) {
                this.f = -1;
                this.g = 17;
                this.e = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.OnClick);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i);
                    if (index == R$styleable.OnClick_targetId) {
                        this.f = typedArrayObtainStyledAttributes.getResourceId(index, this.f);
                    } else if (index == R$styleable.OnClick_clickAction) {
                        this.g = typedArrayObtainStyledAttributes.getInt(index, this.g);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public final void a(MotionLayout motionLayout, int i, b bVar) {
                int i2 = this.f;
                View viewFindViewById = motionLayout;
                if (i2 != -1) {
                    viewFindViewById = motionLayout.findViewById(i2);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + i2);
                    return;
                }
                int i3 = bVar.d;
                int i4 = bVar.c;
                if (i3 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i5 = this.g;
                int i6 = i5 & 1;
                if (((i6 != 0 && i == i3) | (i6 != 0 && i == i3) | ((i5 & 256) != 0 && i == i3) | ((i5 & 16) != 0 && i == i4)) || ((i5 & 4096) != 0 && i == i4)) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            public final void b(MotionLayout motionLayout) {
                int i = this.f;
                if (i == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + i);
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                b bVar = this.e;
                a aVar = bVar.j;
                MotionLayout motionLayout = aVar.a;
                if (motionLayout.D) {
                    if (bVar.d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState != -1) {
                            b bVar2 = new b(bVar.j, bVar);
                            bVar2.d = currentState;
                            bVar2.c = bVar.c;
                            motionLayout.setTransition(bVar2);
                            motionLayout.t(1.0f);
                            motionLayout.z0 = null;
                            return;
                        }
                        int i2 = bVar.c;
                        if (motionLayout.isAttachedToWindow()) {
                            motionLayout.I(i2, -1);
                            return;
                        }
                        if (motionLayout.y0 == null) {
                            motionLayout.y0 = motionLayout.new g();
                        }
                        motionLayout.y0.d = i2;
                        return;
                    }
                    b bVar3 = aVar.c;
                    int i3 = this.g;
                    int i4 = i3 & 1;
                    boolean z = true;
                    boolean z2 = (i4 == 0 && (i3 & 256) == 0) ? false : true;
                    int i5 = i3 & 16;
                    boolean z3 = (i5 == 0 && (i3 & 4096) == 0) ? false : true;
                    if (z2 && z3) {
                        if (bVar3 != bVar) {
                            motionLayout.setTransition(bVar);
                        }
                        if (motionLayout.getCurrentState() == motionLayout.getEndState() || motionLayout.getProgress() > 0.5f) {
                            z2 = false;
                        } else {
                            z3 = false;
                        }
                    }
                    if (bVar != bVar3) {
                        int i6 = bVar.c;
                        int i7 = bVar.d;
                        if (i7 != -1 ? !((i = motionLayout.z) == i7 || i == i6) : motionLayout.z == i6) {
                            z = false;
                        }
                    }
                    if (z) {
                        if (z2 && i4 != 0) {
                            motionLayout.setTransition(bVar);
                            motionLayout.t(1.0f);
                            motionLayout.z0 = null;
                            return;
                        }
                        if (z3 && i5 != 0) {
                            motionLayout.setTransition(bVar);
                            motionLayout.t(0.0f);
                        } else if (z2 && (i3 & 256) != 0) {
                            motionLayout.setTransition(bVar);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z3 || (i3 & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(bVar);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        public b(a aVar, b bVar) {
            this.a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.j = aVar;
            this.h = aVar.j;
            if (bVar != null) {
                this.p = bVar.p;
                this.e = bVar.e;
                this.f = bVar.f;
                this.g = bVar.g;
                this.h = bVar.h;
                this.k = bVar.k;
                this.i = bVar.i;
                this.q = bVar.q;
            }
        }

        public b(a aVar, int i, int i2) {
            this.a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.a = -1;
            this.j = aVar;
            this.d = i;
            this.c = i2;
            this.h = aVar.j;
            this.q = aVar.k;
        }

        public b(a aVar, Context context, XmlResourceParser xmlResourceParser) {
            this.a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.h = aVar.j;
            this.q = aVar.k;
            this.j = aVar;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.Transition);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = R$styleable.Transition_constraintSetEnd;
                SparseArray<androidx.constraintlayout.widget.a> sparseArray = aVar.g;
                if (index == i2) {
                    this.c = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.c);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.a aVar2 = new androidx.constraintlayout.widget.a();
                        aVar2.k(context, this.c);
                        sparseArray.append(this.c, aVar2);
                    } else if ("xml".equals(resourceTypeName)) {
                        this.c = aVar.j(context, this.c);
                    }
                } else if (index == R$styleable.Transition_constraintSetStart) {
                    this.d = typedArrayObtainStyledAttributes.getResourceId(index, this.d);
                    String resourceTypeName2 = context.getResources().getResourceTypeName(this.d);
                    if ("layout".equals(resourceTypeName2)) {
                        androidx.constraintlayout.widget.a aVar3 = new androidx.constraintlayout.widget.a();
                        aVar3.k(context, this.d);
                        sparseArray.append(this.d, aVar3);
                    } else if ("xml".equals(resourceTypeName2)) {
                        this.d = aVar.j(context, this.d);
                    }
                } else if (index == R$styleable.Transition_motionInterpolator) {
                    int i3 = typedArrayObtainStyledAttributes.peekValue(index).type;
                    if (i3 == 1) {
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                        this.g = resourceId;
                        if (resourceId != -1) {
                            this.e = -2;
                        }
                    } else if (i3 == 3) {
                        String string = typedArrayObtainStyledAttributes.getString(index);
                        this.f = string;
                        if (string != null) {
                            if (string.indexOf("/") > 0) {
                                this.g = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                this.e = -2;
                            } else {
                                this.e = -1;
                            }
                        }
                    } else {
                        this.e = typedArrayObtainStyledAttributes.getInteger(index, this.e);
                    }
                } else if (index == R$styleable.Transition_duration) {
                    int i4 = typedArrayObtainStyledAttributes.getInt(index, this.h);
                    this.h = i4;
                    if (i4 < 8) {
                        this.h = 8;
                    }
                } else if (index == R$styleable.Transition_staggered) {
                    this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                } else if (index == R$styleable.Transition_autoTransition) {
                    this.n = typedArrayObtainStyledAttributes.getInteger(index, this.n);
                } else if (index == R$styleable.Transition_android_id) {
                    this.a = typedArrayObtainStyledAttributes.getResourceId(index, this.a);
                } else if (index == R$styleable.Transition_transitionDisable) {
                    this.o = typedArrayObtainStyledAttributes.getBoolean(index, this.o);
                } else if (index == R$styleable.Transition_pathMotionArc) {
                    this.p = typedArrayObtainStyledAttributes.getInteger(index, -1);
                } else if (index == R$styleable.Transition_layoutDuringTransition) {
                    this.q = typedArrayObtainStyledAttributes.getInteger(index, 0);
                } else if (index == R$styleable.Transition_transitionFlags) {
                    this.r = typedArrayObtainStyledAttributes.getInteger(index, 0);
                }
            }
            if (this.d == -1) {
                this.b = true;
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
