package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.AttributeSet;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: MotionScene.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MotionLayout f951a;
    private MotionEvent n;
    private MotionLayout.g q;
    private boolean r;
    final t s;
    float t;
    float u;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    androidx.constraintlayout.widget.g f952b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b f953c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f954d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<b> f955e = new ArrayList<>();
    private b f = null;
    private ArrayList<b> g = new ArrayList<>();
    private SparseArray<androidx.constraintlayout.widget.b> h = new SparseArray<>();
    private HashMap<String, Integer> i = new HashMap<>();
    private SparseIntArray j = new SparseIntArray();
    private boolean k = false;
    private int l = 400;
    private int m = 0;
    private boolean o = false;
    private boolean p = false;

    /* JADX INFO: compiled from: MotionScene.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ androidx.constraintlayout.core.j.a.c f956a;

        a(androidx.constraintlayout.core.j.a.c cVar) {
            this.f956a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.f956a.a(f);
        }
    }

    q(Context context, MotionLayout motionLayout, int i) {
        this.f951a = motionLayout;
        this.s = new t(motionLayout);
        K(context, i);
        SparseArray<androidx.constraintlayout.widget.b> sparseArray = this.h;
        int i2 = androidx.constraintlayout.widget.d.f1105a;
        sparseArray.put(i2, new androidx.constraintlayout.widget.b());
        this.i.put("motion_base", Integer.valueOf(i2));
    }

    private boolean I(int i) {
        int i2 = this.j.get(i);
        int size = this.j.size();
        while (i2 > 0) {
            if (i2 == i) {
                return true;
            }
            int i3 = size - 1;
            if (size < 0) {
                return true;
            }
            i2 = this.j.get(i2);
            size = i3;
        }
        return false;
    }

    private boolean J() {
        return this.q != null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void K(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        b bVar = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                byte b2 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    if (this.k) {
                        System.out.println("parsing = " + name);
                    }
                    switch (name.hashCode()) {
                        case -1349929691:
                            b2 = !name.equals("ConstraintSet") ? (byte) -1 : (byte) 5;
                            break;
                        case -1239391468:
                            b2 = !name.equals("KeyFrameSet") ? (byte) -1 : (byte) 8;
                            break;
                        case -687739768:
                            b2 = !name.equals("Include") ? (byte) -1 : (byte) 7;
                            break;
                        case 61998586:
                            b2 = !name.equals("ViewTransition") ? (byte) -1 : (byte) 9;
                            break;
                        case 269306229:
                            if (!name.equals("Transition")) {
                                b2 = -1;
                            }
                            break;
                        case 312750793:
                            b2 = !name.equals("OnClick") ? (byte) -1 : (byte) 3;
                            break;
                        case 327855227:
                            b2 = !name.equals("OnSwipe") ? (byte) -1 : (byte) 2;
                            break;
                        case 793277014:
                            b2 = !name.equals("MotionScene") ? (byte) -1 : (byte) 0;
                            break;
                        case 1382829617:
                            b2 = !name.equals("StateSet") ? (byte) -1 : (byte) 4;
                            break;
                        case 1942574248:
                            b2 = !name.equals("include") ? (byte) -1 : (byte) 6;
                            break;
                        default:
                            b2 = -1;
                            break;
                    }
                    switch (b2) {
                        case 0:
                            O(context, xml);
                            break;
                        case 1:
                            ArrayList<b> arrayList = this.f955e;
                            b bVar2 = new b(this, context, xml);
                            arrayList.add(bVar2);
                            if (this.f953c == null && !bVar2.f959b) {
                                this.f953c = bVar2;
                                if (bVar2.l != null) {
                                    this.f953c.l.x(this.r);
                                }
                            }
                            if (bVar2.f959b) {
                                if (bVar2.f960c == -1) {
                                    this.f = bVar2;
                                } else {
                                    this.g.add(bVar2);
                                }
                                this.f955e.remove(bVar2);
                            }
                            bVar = bVar2;
                            break;
                        case 2:
                            if (bVar == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            if (bVar != null) {
                                bVar.l = new r(context, this.f951a, xml);
                            }
                            break;
                        case 3:
                            if (bVar != null) {
                                bVar.u(context, xml);
                            }
                            break;
                        case 4:
                            this.f952b = new androidx.constraintlayout.widget.g(context, xml);
                            break;
                        case 5:
                            L(context, xml);
                            break;
                        case 6:
                        case 7:
                            N(context, xml);
                            break;
                        case 8:
                            h hVar = new h(context, xml);
                            if (bVar != null) {
                                bVar.k.add(hVar);
                            }
                            break;
                        case 9:
                            this.s.a(new s(context, xml));
                            break;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    private int L(Context context, XmlPullParser xmlPullParser) {
        String attributeValue;
        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
        bVar.P(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int iR = -1;
        int iR2 = -1;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            attributeValue = xmlPullParser.getAttributeValue(i);
            if (this.k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            switch (attributeName) {
                case "deriveConstraintsFrom":
                    iR2 = r(context, attributeValue);
                    break;
                case "constraintRotate":
                    try {
                        bVar.g = Integer.parseInt(attributeValue);
                        break;
                    } catch (NumberFormatException unused) {
                        attributeValue.hashCode();
                        switch (attributeValue) {
                            case "x_left":
                                bVar.g = 4;
                                break;
                            case "left":
                                bVar.g = 2;
                                break;
                            case "none":
                                bVar.g = 0;
                                break;
                            case "right":
                                bVar.g = 1;
                                break;
                            case "x_right":
                                bVar.g = 3;
                                break;
                        }
                    }
                    break;
                case "id":
                    iR = r(context, attributeValue);
                    this.i.put(a0(attributeValue), Integer.valueOf(iR));
                    bVar.f1074e = androidx.constraintlayout.motion.widget.b.c(context, iR);
                    break;
            }
        }
        if (iR != -1) {
            if (this.f951a.X != 0) {
                bVar.Q(true);
            }
            bVar.C(context, xmlPullParser);
            if (iR2 != -1) {
                this.j.put(iR, iR2);
            }
            this.h.put(iR, bVar);
        }
        return iR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int M(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && "ConstraintSet".equals(name)) {
                    return L(context, xml);
                }
            }
            return -1;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    private void N(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.e.gc);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == androidx.constraintlayout.widget.e.hc) {
                M(context, typedArrayObtainStyledAttributes.getResourceId(index, -1));
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void O(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.e.ja);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == androidx.constraintlayout.widget.e.ka) {
                int i2 = typedArrayObtainStyledAttributes.getInt(index, this.l);
                this.l = i2;
                if (i2 < 8) {
                    this.l = 8;
                }
            } else if (index == androidx.constraintlayout.widget.e.la) {
                this.m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void S(int i, MotionLayout motionLayout) {
        androidx.constraintlayout.widget.b bVar = this.h.get(i);
        bVar.f = bVar.f1074e;
        int i2 = this.j.get(i);
        if (i2 > 0) {
            S(i2, motionLayout);
            androidx.constraintlayout.widget.b bVar2 = this.h.get(i2);
            if (bVar2 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + androidx.constraintlayout.motion.widget.b.c(this.f951a.getContext(), i2));
                return;
            }
            bVar.f += "/" + bVar2.f;
            bVar.K(bVar2);
        } else {
            bVar.f += "  layout";
            bVar.J(motionLayout);
        }
        bVar.h(bVar);
    }

    public static String a0(String str) {
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(47);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }

    private int r(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.k) {
                System.out.println("id getMap res = " + identifier);
            }
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

    private int y(int i) {
        int iC;
        androidx.constraintlayout.widget.g gVar = this.f952b;
        return (gVar == null || (iC = gVar.c(i, -1, -1)) == -1) ? i : iC;
    }

    float A() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f953c.l.l();
    }

    float B() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f953c.l.m();
    }

    float C() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f953c.l.n();
    }

    float D() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f953c.l.o();
    }

    public float E() {
        b bVar = this.f953c;
        if (bVar != null) {
            return bVar.i;
        }
        return 0.0f;
    }

    int F() {
        b bVar = this.f953c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f961d;
    }

    public b G(int i) {
        for (b bVar : this.f955e) {
            if (bVar.f958a == i) {
                return bVar;
            }
        }
        return null;
    }

    public List<b> H(int i) {
        int iY = y(i);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f955e) {
            if (bVar.f961d == iY || bVar.f960c == iY) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    void P(float f, float f2) {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f953c.l.u(f, f2);
    }

    void Q(float f, float f2) {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f953c.l.v(f, f2);
    }

    void R(MotionEvent motionEvent, int i, MotionLayout motionLayout) {
        MotionLayout.g gVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.q == null) {
            this.q = this.f951a.s0();
        }
        this.q.a(motionEvent);
        if (i != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.t = motionEvent.getRawX();
                this.u = motionEvent.getRawY();
                this.n = motionEvent;
                this.o = false;
                if (this.f953c.l != null) {
                    RectF rectFF = this.f953c.l.f(this.f951a, rectF);
                    if (rectFF != null && !rectFF.contains(this.n.getX(), this.n.getY())) {
                        this.n = null;
                        this.o = true;
                        return;
                    }
                    RectF rectFP = this.f953c.l.p(this.f951a, rectF);
                    if (rectFP == null || rectFP.contains(this.n.getX(), this.n.getY())) {
                        this.p = false;
                    } else {
                        this.p = true;
                    }
                    this.f953c.l.w(this.t, this.u);
                    return;
                }
                return;
            }
            if (action == 2 && !this.o) {
                float rawY = motionEvent.getRawY() - this.u;
                float rawX = motionEvent.getRawX() - this.t;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.n) == null) {
                    return;
                }
                b bVarI = i(i, rawX, rawY, motionEvent2);
                if (bVarI != null) {
                    motionLayout.setTransition(bVarI);
                    RectF rectFP2 = this.f953c.l.p(this.f951a, rectF);
                    if (rectFP2 != null && !rectFP2.contains(this.n.getX(), this.n.getY())) {
                        z = true;
                    }
                    this.p = z;
                    this.f953c.l.z(this.t, this.u);
                }
            }
        }
        if (this.o) {
            return;
        }
        b bVar = this.f953c;
        if (bVar != null && bVar.l != null && !this.p) {
            this.f953c.l.s(motionEvent, this.q, i, this);
        }
        this.t = motionEvent.getRawX();
        this.u = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (gVar = this.q) == null) {
            return;
        }
        gVar.d();
        this.q = null;
        int i2 = motionLayout.F;
        if (i2 != -1) {
            h(motionLayout, i2);
        }
    }

    void T(MotionLayout motionLayout) {
        for (int i = 0; i < this.h.size(); i++) {
            int iKeyAt = this.h.keyAt(i);
            if (I(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            S(iKeyAt, motionLayout);
        }
    }

    public void U(int i, androidx.constraintlayout.widget.b bVar) {
        this.h.put(i, bVar);
    }

    public void V(int i) {
        b bVar = this.f953c;
        if (bVar != null) {
            bVar.E(i);
        } else {
            this.l = i;
        }
    }

    public void W(boolean z) {
        this.r = z;
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f953c.l.x(this.r);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void X(int r7, int r8) {
        /*
            r6 = this;
            androidx.constraintlayout.widget.g r0 = r6.f952b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            androidx.constraintlayout.widget.g r2 = r6.f952b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            androidx.constraintlayout.motion.widget.q$b r3 = r6.f953c
            if (r3 == 0) goto L2b
            int r3 = androidx.constraintlayout.motion.widget.q.b.a(r3)
            if (r3 != r8) goto L2b
            androidx.constraintlayout.motion.widget.q$b r3 = r6.f953c
            int r3 = androidx.constraintlayout.motion.widget.q.b.c(r3)
            if (r3 != r7) goto L2b
            return
        L2b:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.f955e
            java.util.Iterator r3 = r3.iterator()
        L31:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L6b
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.q$b r4 = (androidx.constraintlayout.motion.widget.q.b) r4
            int r5 = androidx.constraintlayout.motion.widget.q.b.a(r4)
            if (r5 != r2) goto L49
            int r5 = androidx.constraintlayout.motion.widget.q.b.c(r4)
            if (r5 == r0) goto L55
        L49:
            int r5 = androidx.constraintlayout.motion.widget.q.b.a(r4)
            if (r5 != r8) goto L31
            int r5 = androidx.constraintlayout.motion.widget.q.b.c(r4)
            if (r5 != r7) goto L31
        L55:
            r6.f953c = r4
            if (r4 == 0) goto L6a
            androidx.constraintlayout.motion.widget.r r7 = androidx.constraintlayout.motion.widget.q.b.l(r4)
            if (r7 == 0) goto L6a
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f953c
            androidx.constraintlayout.motion.widget.r r7 = androidx.constraintlayout.motion.widget.q.b.l(r7)
            boolean r8 = r6.r
            r7.x(r8)
        L6a:
            return
        L6b:
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.g
            java.util.Iterator r3 = r3.iterator()
        L73:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L87
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.q$b r4 = (androidx.constraintlayout.motion.widget.q.b) r4
            int r5 = androidx.constraintlayout.motion.widget.q.b.a(r4)
            if (r5 != r8) goto L73
            r7 = r4
            goto L73
        L87:
            androidx.constraintlayout.motion.widget.q$b r8 = new androidx.constraintlayout.motion.widget.q$b
            r8.<init>(r6, r7)
            androidx.constraintlayout.motion.widget.q.b.d(r8, r0)
            androidx.constraintlayout.motion.widget.q.b.b(r8, r2)
            if (r0 == r1) goto L99
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r7 = r6.f955e
            r7.add(r8)
        L99:
            r6.f953c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.q.X(int, int):void");
    }

    public void Y(b bVar) {
        this.f953c = bVar;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f953c.l.x(this.r);
    }

    void Z() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f953c.l.A();
    }

    boolean b0() {
        Iterator<b> it = this.f955e.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        b bVar = this.f953c;
        return (bVar == null || bVar.l == null) ? false : true;
    }

    public void c0(int i, View... viewArr) {
        this.s.i(i, viewArr);
    }

    public void f(MotionLayout motionLayout, int i) {
        for (b bVar : this.f955e) {
            if (bVar.m.size() > 0) {
                Iterator it = bVar.m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).c(motionLayout);
                }
            }
        }
        for (b bVar2 : this.g) {
            if (bVar2.m.size() > 0) {
                Iterator it2 = bVar2.m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).c(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f955e) {
            if (bVar3.m.size() > 0) {
                Iterator it3 = bVar3.m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i, bVar3);
                }
            }
        }
        for (b bVar4 : this.g) {
            if (bVar4.m.size() > 0) {
                Iterator it4 = bVar4.m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i, bVar4);
                }
            }
        }
    }

    public boolean g(int i, n nVar) {
        return this.s.d(i, nVar);
    }

    boolean h(MotionLayout motionLayout, int i) {
        b bVar;
        if (J() || this.f954d) {
            return false;
        }
        for (b bVar2 : this.f955e) {
            if (bVar2.n != 0 && ((bVar = this.f953c) != bVar2 || !bVar.D(2))) {
                if (i == bVar2.f961d && (bVar2.n == 4 || bVar2.n == 2)) {
                    MotionLayout.TransitionState transitionState = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(bVar2);
                    if (bVar2.n == 4) {
                        motionLayout.C0();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.f0(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState);
                        motionLayout.t0();
                    }
                    return true;
                }
                if (i == bVar2.f960c && (bVar2.n == 3 || bVar2.n == 1)) {
                    MotionLayout.TransitionState transitionState2 = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState2);
                    motionLayout.setTransition(bVar2);
                    if (bVar2.n == 3) {
                        motionLayout.E0();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.f0(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState2);
                        motionLayout.t0();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public b i(int i, float f, float f2, MotionEvent motionEvent) {
        if (i == -1) {
            return this.f953c;
        }
        List<b> listH = H(i);
        float f3 = 0.0f;
        b bVar = null;
        RectF rectF = new RectF();
        for (b bVar2 : listH) {
            if (!bVar2.o && bVar2.l != null) {
                bVar2.l.x(this.r);
                RectF rectFP = bVar2.l.p(this.f951a, rectF);
                if (rectFP == null || motionEvent == null || rectFP.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF rectFF = bVar2.l.f(this.f951a, rectF);
                    if (rectFF == null || motionEvent == null || rectFF.contains(motionEvent.getX(), motionEvent.getY())) {
                        float fA = bVar2.l.a(f, f2);
                        if (bVar2.l.n && motionEvent != null) {
                            fA = ((float) (Math.atan2(f2 + r10, f + r9) - Math.atan2(motionEvent.getX() - bVar2.l.k, motionEvent.getY() - bVar2.l.l))) * 10.0f;
                        }
                        float f4 = fA * (bVar2.f960c == i ? -1.0f : 1.1f);
                        if (f4 > f3) {
                            bVar = bVar2;
                            f3 = f4;
                        }
                    }
                }
            }
        }
        return bVar;
    }

    public int j() {
        b bVar = this.f953c;
        if (bVar != null) {
            return bVar.p;
        }
        return -1;
    }

    int k() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0;
        }
        return this.f953c.l.d();
    }

    androidx.constraintlayout.widget.b l(int i) {
        return m(i, -1, -1);
    }

    androidx.constraintlayout.widget.b m(int i, int i2, int i3) {
        int iC;
        if (this.k) {
            System.out.println("id " + i);
            System.out.println("size " + this.h.size());
        }
        androidx.constraintlayout.widget.g gVar = this.f952b;
        if (gVar != null && (iC = gVar.c(i, i2, i3)) != -1) {
            i = iC;
        }
        if (this.h.get(i) != null) {
            return this.h.get(i);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + androidx.constraintlayout.motion.widget.b.c(this.f951a.getContext(), i) + " In MotionScene");
        SparseArray<androidx.constraintlayout.widget.b> sparseArray = this.h;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] n() {
        int size = this.h.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.h.keyAt(i);
        }
        return iArr;
    }

    public ArrayList<b> o() {
        return this.f955e;
    }

    public int p() {
        b bVar = this.f953c;
        return bVar != null ? bVar.h : this.l;
    }

    int q() {
        b bVar = this.f953c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f960c;
    }

    public Interpolator s() {
        int i = this.f953c.f962e;
        if (i == -2) {
            return AnimationUtils.loadInterpolator(this.f951a.getContext(), this.f953c.g);
        }
        if (i == -1) {
            return new a(androidx.constraintlayout.core.j.a.c.c(this.f953c.f));
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

    public void t(n nVar) {
        b bVar = this.f953c;
        if (bVar != null) {
            Iterator it = bVar.k.iterator();
            while (it.hasNext()) {
                ((h) it.next()).b(nVar);
            }
        } else {
            b bVar2 = this.f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.k.iterator();
                while (it2.hasNext()) {
                    ((h) it2.next()).b(nVar);
                }
            }
        }
    }

    float u() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f953c.l.g();
    }

    float v() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f953c.l.h();
    }

    boolean w() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return false;
        }
        return this.f953c.l.i();
    }

    float x(float f, float f2) {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f953c.l.j(f, f2);
    }

    int z() {
        b bVar = this.f953c;
        if (bVar == null || bVar.l == null) {
            return 0;
        }
        return this.f953c.l.k();
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f958a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f959b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f960c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f961d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f962e;
        private String f;
        private int g;
        private int h;
        private float i;
        private final q j;
        private ArrayList<h> k;
        private r l;
        private ArrayList<a> m;
        private int n;
        private boolean o;
        private int p;
        private int q;
        private int r;

        /* JADX INFO: compiled from: MotionScene.java */
        public static class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final b f963b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f964c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int f965d;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.f964c = -1;
                this.f965d = 17;
                this.f963b = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.e.qa);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i);
                    if (index == androidx.constraintlayout.widget.e.sa) {
                        this.f964c = typedArrayObtainStyledAttributes.getResourceId(index, this.f964c);
                    } else if (index == androidx.constraintlayout.widget.e.ra) {
                        this.f965d = typedArrayObtainStyledAttributes.getInt(index, this.f965d);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i, b bVar) {
                int i2 = this.f964c;
                View viewFindViewById = motionLayout;
                if (i2 != -1) {
                    viewFindViewById = motionLayout.findViewById(i2);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f964c);
                    return;
                }
                int i3 = bVar.f961d;
                int i4 = bVar.f960c;
                if (i3 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i5 = this.f965d;
                boolean z = false;
                boolean z2 = ((i5 & 1) != 0 && i == i3) | ((i5 & 1) != 0 && i == i3) | ((i5 & 256) != 0 && i == i3) | ((i5 & 16) != 0 && i == i4);
                if ((i5 & 4096) != 0 && i == i4) {
                    z = true;
                }
                if (z2 || z) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            boolean b(b bVar, MotionLayout motionLayout) {
                b bVar2 = this.f963b;
                if (bVar2 == bVar) {
                    return true;
                }
                int i = bVar2.f960c;
                int i2 = this.f963b.f961d;
                if (i2 == -1) {
                    return motionLayout.F != i;
                }
                int i3 = motionLayout.F;
                return i3 == i2 || i3 == i;
            }

            public void c(MotionLayout motionLayout) {
                int i = this.f964c;
                if (i == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f964c);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f963b.j.f951a;
                if (motionLayout.r0()) {
                    if (this.f963b.f961d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.F0(this.f963b.f960c);
                            return;
                        }
                        b bVar = new b(this.f963b.j, this.f963b);
                        bVar.f961d = currentState;
                        bVar.f960c = this.f963b.f960c;
                        motionLayout.setTransition(bVar);
                        motionLayout.C0();
                        return;
                    }
                    b bVar2 = this.f963b.j.f953c;
                    int i = this.f965d;
                    boolean z = false;
                    boolean z2 = ((i & 1) == 0 && (i & 256) == 0) ? false : true;
                    boolean z3 = ((i & 16) == 0 && (i & 4096) == 0) ? false : true;
                    if (z2 && z3) {
                        b bVar3 = this.f963b.j.f953c;
                        b bVar4 = this.f963b;
                        if (bVar3 != bVar4) {
                            motionLayout.setTransition(bVar4);
                        }
                        if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                            z3 = false;
                            z = z2;
                        }
                    } else {
                        z = z2;
                    }
                    if (b(bVar2, motionLayout)) {
                        if (z && (this.f965d & 1) != 0) {
                            motionLayout.setTransition(this.f963b);
                            motionLayout.C0();
                            return;
                        }
                        if (z3 && (this.f965d & 16) != 0) {
                            motionLayout.setTransition(this.f963b);
                            motionLayout.E0();
                        } else if (z && (this.f965d & 256) != 0) {
                            motionLayout.setTransition(this.f963b);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z3 || (this.f965d & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f963b);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        b(q qVar, b bVar) {
            this.f958a = -1;
            this.f959b = false;
            this.f960c = -1;
            this.f961d = -1;
            this.f962e = 0;
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
            this.j = qVar;
            this.h = qVar.l;
            if (bVar != null) {
                this.p = bVar.p;
                this.f962e = bVar.f962e;
                this.f = bVar.f;
                this.g = bVar.g;
                this.h = bVar.h;
                this.k = bVar.k;
                this.i = bVar.i;
                this.q = bVar.q;
            }
        }

        private void v(q qVar, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.yb) {
                    this.f960c = typedArray.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f960c);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        bVar.B(context, this.f960c);
                        qVar.h.append(this.f960c, bVar);
                    } else if ("xml".equals(resourceTypeName)) {
                        this.f960c = qVar.M(context, this.f960c);
                    }
                } else if (index == androidx.constraintlayout.widget.e.zb) {
                    this.f961d = typedArray.getResourceId(index, this.f961d);
                    String resourceTypeName2 = context.getResources().getResourceTypeName(this.f961d);
                    if ("layout".equals(resourceTypeName2)) {
                        androidx.constraintlayout.widget.b bVar2 = new androidx.constraintlayout.widget.b();
                        bVar2.B(context, this.f961d);
                        qVar.h.append(this.f961d, bVar2);
                    } else if ("xml".equals(resourceTypeName2)) {
                        this.f961d = qVar.M(context, this.f961d);
                    }
                } else if (index == androidx.constraintlayout.widget.e.Cb) {
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.g = resourceId;
                        if (resourceId != -1) {
                            this.f962e = -2;
                        }
                    } else if (i2 == 3) {
                        String string = typedArray.getString(index);
                        this.f = string;
                        if (string != null) {
                            if (string.indexOf("/") > 0) {
                                this.g = typedArray.getResourceId(index, -1);
                                this.f962e = -2;
                            } else {
                                this.f962e = -1;
                            }
                        }
                    } else {
                        this.f962e = typedArray.getInteger(index, this.f962e);
                    }
                } else if (index == androidx.constraintlayout.widget.e.Ab) {
                    int i3 = typedArray.getInt(index, this.h);
                    this.h = i3;
                    if (i3 < 8) {
                        this.h = 8;
                    }
                } else if (index == androidx.constraintlayout.widget.e.Eb) {
                    this.i = typedArray.getFloat(index, this.i);
                } else if (index == androidx.constraintlayout.widget.e.xb) {
                    this.n = typedArray.getInteger(index, this.n);
                } else if (index == androidx.constraintlayout.widget.e.wb) {
                    this.f958a = typedArray.getResourceId(index, this.f958a);
                } else if (index == androidx.constraintlayout.widget.e.Fb) {
                    this.o = typedArray.getBoolean(index, this.o);
                } else if (index == androidx.constraintlayout.widget.e.Db) {
                    this.p = typedArray.getInteger(index, -1);
                } else if (index == androidx.constraintlayout.widget.e.Bb) {
                    this.q = typedArray.getInteger(index, 0);
                } else if (index == androidx.constraintlayout.widget.e.Gb) {
                    this.r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f961d == -1) {
                this.f959b = true;
            }
        }

        private void w(q qVar, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.vb);
            v(qVar, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int A() {
            return this.f961d;
        }

        public r B() {
            return this.l;
        }

        public boolean C() {
            return !this.o;
        }

        public boolean D(int i) {
            return (i & this.r) != 0;
        }

        public void E(int i) {
            this.h = Math.max(i, 8);
        }

        public void F(boolean z) {
            this.o = !z;
        }

        public void G(int i, String str, int i2) {
            this.f962e = i;
            this.f = str;
            this.g = i2;
        }

        public void H(int i) {
            r rVarB = B();
            if (rVarB != null) {
                rVarB.y(i);
            }
        }

        public void I(int i) {
            this.p = i;
        }

        public void t(h hVar) {
            this.k.add(hVar);
        }

        public void u(Context context, XmlPullParser xmlPullParser) {
            this.m.add(new a(context, this, xmlPullParser));
        }

        public int x() {
            return this.n;
        }

        public int y() {
            return this.f960c;
        }

        public int z() {
            return this.q;
        }

        public b(int i, q qVar, int i2, int i3) {
            this.f958a = -1;
            this.f959b = false;
            this.f960c = -1;
            this.f961d = -1;
            this.f962e = 0;
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
            this.f958a = i;
            this.j = qVar;
            this.f961d = i2;
            this.f960c = i3;
            this.h = qVar.l;
            this.q = qVar.m;
        }

        b(q qVar, Context context, XmlPullParser xmlPullParser) {
            this.f958a = -1;
            this.f959b = false;
            this.f960c = -1;
            this.f961d = -1;
            this.f962e = 0;
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
            this.h = qVar.l;
            this.q = qVar.m;
            this.j = qVar;
            w(qVar, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
