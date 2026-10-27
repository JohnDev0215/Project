package b.f.a.b;

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
    public final MotionLayout f2115a;
    public MotionEvent n;
    public MotionLayout.f p;
    public boolean q;
    public float r;
    public float s;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.f.c.f f2116b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f2117c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2118d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<b> f2119e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f2120f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<b> f2121g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SparseArray<b.f.c.b> f2122h = new SparseArray<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public HashMap<String, Integer> f2123i = new HashMap<>();
    public SparseIntArray j = new SparseIntArray();
    public boolean k = false;
    public int l = 400;
    public int m = 0;
    public boolean o = false;

    /* JADX INFO: compiled from: MotionScene.java */
    public class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.f.a.a.c f2124a;

        public a(b.f.a.a.c cVar) {
            this.f2124a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return (float) this.f2124a.a(f2);
        }
    }

    public q(Context context, MotionLayout motionLayout, int i2) {
        this.f2115a = motionLayout;
        C(context, i2);
        SparseArray<b.f.c.b> sparseArray = this.f2122h;
        int i3 = b.f.c.d.motion_base;
        sparseArray.put(i3, new b.f.c.b());
        this.f2123i.put("motion_base", Integer.valueOf(i3));
    }

    public static String P(String str) {
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(47);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }

    public final boolean A(int i2) {
        int i3 = this.j.get(i2);
        int size = this.j.size();
        while (i3 > 0) {
            if (i3 == i2) {
                return true;
            }
            int i4 = size - 1;
            if (size < 0) {
                return true;
            }
            i3 = this.j.get(i3);
            size = i4;
        }
        return false;
    }

    public final boolean B() {
        return this.p != null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void C(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
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
                            b2 = !name.equals("KeyFrameSet") ? (byte) -1 : (byte) 6;
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
                        default:
                            b2 = -1;
                            break;
                    }
                    switch (b2) {
                        case 0:
                            E(context, xml);
                            break;
                        case 1:
                            ArrayList<b> arrayList = this.f2119e;
                            b bVar2 = new b(this, context, xml);
                            arrayList.add(bVar2);
                            if (this.f2117c == null && !bVar2.f2127b) {
                                this.f2117c = bVar2;
                                if (bVar2.l != null) {
                                    this.f2117c.l.p(this.q);
                                }
                            }
                            if (bVar2.f2127b) {
                                if (bVar2.f2128c == -1) {
                                    this.f2120f = bVar2;
                                } else {
                                    this.f2121g.add(bVar2);
                                }
                                this.f2119e.remove(bVar2);
                            }
                            bVar = bVar2;
                            break;
                        case 2:
                            if (bVar == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i2) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            bVar.l = new t(context, this.f2115a, xml);
                            break;
                        case 3:
                            bVar.t(context, xml);
                            break;
                        case 4:
                            this.f2116b = new b.f.c.f(context, xml);
                            break;
                        case 5:
                            D(context, xml);
                            break;
                        case 6:
                            bVar.k.add(new h(context, xml));
                            break;
                        default:
                            Log.v("MotionScene", "WARNING UNKNOWN ATTRIBUTE " + name);
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

    public final void D(Context context, XmlPullParser xmlPullParser) {
        b.f.c.b bVar = new b.f.c.b();
        bVar.A(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int iO = -1;
        int iO2 = -1;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (this.k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            if (attributeName.equals("deriveConstraintsFrom")) {
                iO2 = o(context, attributeValue);
            } else if (attributeName.equals("id")) {
                iO = o(context, attributeValue);
                this.f2123i.put(P(attributeValue), Integer.valueOf(iO));
            }
        }
        if (iO != -1) {
            if (this.f2115a.V != 0) {
                bVar.B(true);
            }
            bVar.v(context, xmlPullParser);
            if (iO2 != -1) {
                this.j.put(iO, iO2);
            }
            this.f2122h.put(iO, bVar);
        }
    }

    public final void E(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), b.f.c.e.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == b.f.c.e.MotionScene_defaultDuration) {
                this.l = typedArrayObtainStyledAttributes.getInt(index, this.l);
            } else if (index == b.f.c.e.MotionScene_layoutDuringTransition) {
                this.m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void F(float f2, float f3) {
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f2117c.l.m(f2, f3);
    }

    public void G(float f2, float f3) {
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f2117c.l.n(f2, f3);
    }

    public void H(MotionEvent motionEvent, int i2, MotionLayout motionLayout) {
        MotionLayout.f fVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.p == null) {
            this.p = this.f2115a.j0();
        }
        this.p.a(motionEvent);
        if (i2 != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.r = motionEvent.getRawX();
                this.s = motionEvent.getRawY();
                this.n = motionEvent;
                if (this.f2117c.l != null) {
                    RectF rectFE = this.f2117c.l.e(this.f2115a, rectF);
                    if (rectFE != null && !rectFE.contains(this.n.getX(), this.n.getY())) {
                        this.n = null;
                        return;
                    }
                    RectF rectFJ = this.f2117c.l.j(this.f2115a, rectF);
                    if (rectFJ == null || rectFJ.contains(this.n.getX(), this.n.getY())) {
                        this.o = false;
                    } else {
                        this.o = true;
                    }
                    this.f2117c.l.o(this.r, this.s);
                    return;
                }
                return;
            }
            if (action == 2) {
                float rawY = motionEvent.getRawY() - this.s;
                float rawX = motionEvent.getRawX() - this.r;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.n) == null) {
                    return;
                }
                b bVarG = g(i2, rawX, rawY, motionEvent2);
                if (bVarG != null) {
                    motionLayout.setTransition(bVarG);
                    RectF rectFJ2 = this.f2117c.l.j(this.f2115a, rectF);
                    if (rectFJ2 != null && !rectFJ2.contains(this.n.getX(), this.n.getY())) {
                        z = true;
                    }
                    this.o = z;
                    this.f2117c.l.q(this.r, this.s);
                }
            }
        }
        b bVar = this.f2117c;
        if (bVar != null && bVar.l != null && !this.o) {
            this.f2117c.l.l(motionEvent, this.p, i2, this);
        }
        this.r = motionEvent.getRawX();
        this.s = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (fVar = this.p) == null) {
            return;
        }
        fVar.d();
        this.p = null;
        int i3 = motionLayout.D;
        if (i3 != -1) {
            f(motionLayout, i3);
        }
    }

    public final void I(int i2) {
        int i3 = this.j.get(i2);
        if (i3 > 0) {
            I(this.j.get(i2));
            b.f.c.b bVar = this.f2122h.get(i2);
            b.f.c.b bVar2 = this.f2122h.get(i3);
            if (bVar2 != null) {
                bVar.z(bVar2);
                this.j.put(i2, -1);
            } else {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + b.f.a.b.a.b(this.f2115a.getContext(), i3));
            }
        }
    }

    public void J(MotionLayout motionLayout) {
        for (int i2 = 0; i2 < this.f2122h.size(); i2++) {
            int iKeyAt = this.f2122h.keyAt(i2);
            if (A(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            I(iKeyAt);
        }
        for (int i3 = 0; i3 < this.f2122h.size(); i3++) {
            this.f2122h.valueAt(i3).y(motionLayout);
        }
    }

    public void K(int i2) {
        b bVar = this.f2117c;
        if (bVar != null) {
            bVar.E(i2);
        } else {
            this.l = i2;
        }
    }

    public void L(boolean z) {
        this.q = z;
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f2117c.l.p(this.q);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void M(int r7, int r8) {
        /*
            r6 = this;
            b.f.c.f r0 = r6.f2116b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            b.f.c.f r2 = r6.f2116b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            java.util.ArrayList<b.f.a.b.q$b> r3 = r6.f2119e
            java.util.Iterator r3 = r3.iterator()
        L1e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r3.next()
            b.f.a.b.q$b r4 = (b.f.a.b.q.b) r4
            int r5 = b.f.a.b.q.b.a(r4)
            if (r5 != r2) goto L36
            int r5 = b.f.a.b.q.b.c(r4)
            if (r5 == r0) goto L42
        L36:
            int r5 = b.f.a.b.q.b.a(r4)
            if (r5 != r8) goto L1e
            int r5 = b.f.a.b.q.b.c(r4)
            if (r5 != r7) goto L1e
        L42:
            r6.f2117c = r4
            if (r4 == 0) goto L57
            b.f.a.b.t r7 = b.f.a.b.q.b.m(r4)
            if (r7 == 0) goto L57
            b.f.a.b.q$b r7 = r6.f2117c
            b.f.a.b.t r7 = b.f.a.b.q.b.m(r7)
            boolean r8 = r6.q
            r7.p(r8)
        L57:
            return
        L58:
            b.f.a.b.q$b r7 = r6.f2120f
            java.util.ArrayList<b.f.a.b.q$b> r3 = r6.f2121g
            java.util.Iterator r3 = r3.iterator()
        L60:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L74
            java.lang.Object r4 = r3.next()
            b.f.a.b.q$b r4 = (b.f.a.b.q.b) r4
            int r5 = b.f.a.b.q.b.a(r4)
            if (r5 != r8) goto L60
            r7 = r4
            goto L60
        L74:
            b.f.a.b.q$b r8 = new b.f.a.b.q$b
            r8.<init>(r6, r7)
            b.f.a.b.q.b.d(r8, r0)
            b.f.a.b.q.b.b(r8, r2)
            if (r0 == r1) goto L86
            java.util.ArrayList<b.f.a.b.q$b> r7 = r6.f2119e
            r7.add(r8)
        L86:
            r6.f2117c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.f.a.b.q.M(int, int):void");
    }

    public void N(b bVar) {
        this.f2117c = bVar;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f2117c.l.p(this.q);
    }

    public void O() {
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f2117c.l.r();
    }

    public boolean Q() {
        Iterator<b> it = this.f2119e.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        b bVar = this.f2117c;
        return (bVar == null || bVar.l == null) ? false : true;
    }

    public void e(MotionLayout motionLayout, int i2) {
        for (b bVar : this.f2119e) {
            if (bVar.m.size() > 0) {
                Iterator it = bVar.m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).c(motionLayout);
                }
            }
        }
        for (b bVar2 : this.f2121g) {
            if (bVar2.m.size() > 0) {
                Iterator it2 = bVar2.m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).c(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f2119e) {
            if (bVar3.m.size() > 0) {
                Iterator it3 = bVar3.m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i2, bVar3);
                }
            }
        }
        for (b bVar4 : this.f2121g) {
            if (bVar4.m.size() > 0) {
                Iterator it4 = bVar4.m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i2, bVar4);
                }
            }
        }
    }

    public boolean f(MotionLayout motionLayout, int i2) {
        if (B() || this.f2118d) {
            return false;
        }
        for (b bVar : this.f2119e) {
            if (bVar.n != 0) {
                if (i2 == bVar.f2129d && (bVar.n == 4 || bVar.n == 2)) {
                    MotionLayout.TransitionState transitionState = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(bVar);
                    if (bVar.n == 4) {
                        motionLayout.s0();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.Y(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState);
                    }
                    return true;
                }
                if (i2 == bVar.f2128c && (bVar.n == 3 || bVar.n == 1)) {
                    MotionLayout.TransitionState transitionState2 = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState2);
                    motionLayout.setTransition(bVar);
                    if (bVar.n == 3) {
                        motionLayout.t0();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.Y(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState2);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public b g(int i2, float f2, float f3, MotionEvent motionEvent) {
        if (i2 == -1) {
            return this.f2117c;
        }
        List<b> listZ = z(i2);
        float f4 = 0.0f;
        b bVar = null;
        RectF rectF = new RectF();
        for (b bVar2 : listZ) {
            if (!bVar2.o && bVar2.l != null) {
                bVar2.l.p(this.q);
                RectF rectFJ = bVar2.l.j(this.f2115a, rectF);
                if (rectFJ == null || motionEvent == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF rectFJ2 = bVar2.l.j(this.f2115a, rectF);
                    if (rectFJ2 == null || motionEvent == null || rectFJ2.contains(motionEvent.getX(), motionEvent.getY())) {
                        float fA = bVar2.l.a(f2, f3) * (bVar2.f2128c == i2 ? -1.0f : 1.1f);
                        if (fA > f4) {
                            bVar = bVar2;
                            f4 = fA;
                        }
                    }
                }
            }
        }
        return bVar;
    }

    public int h() {
        b bVar = this.f2117c;
        if (bVar != null) {
            return bVar.p;
        }
        return -1;
    }

    public b.f.c.b i(int i2) {
        return j(i2, -1, -1);
    }

    public b.f.c.b j(int i2, int i3, int i4) {
        int iC;
        if (this.k) {
            System.out.println("id " + i2);
            System.out.println("size " + this.f2122h.size());
        }
        b.f.c.f fVar = this.f2116b;
        if (fVar != null && (iC = fVar.c(i2, i3, i4)) != -1) {
            i2 = iC;
        }
        if (this.f2122h.get(i2) != null) {
            return this.f2122h.get(i2);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + b.f.a.b.a.b(this.f2115a.getContext(), i2) + " In MotionScene");
        SparseArray<b.f.c.b> sparseArray = this.f2122h;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] k() {
        int size = this.f2122h.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.f2122h.keyAt(i2);
        }
        return iArr;
    }

    public ArrayList<b> l() {
        return this.f2119e;
    }

    public int m() {
        b bVar = this.f2117c;
        return bVar != null ? bVar.f2133h : this.l;
    }

    public int n() {
        b bVar = this.f2117c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f2128c;
    }

    public final int o(Context context, String str) {
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

    public Interpolator p() {
        int i2 = this.f2117c.f2130e;
        if (i2 == -2) {
            return AnimationUtils.loadInterpolator(this.f2115a.getContext(), this.f2117c.f2132g);
        }
        if (i2 == -1) {
            return new a(b.f.a.a.c.c(this.f2117c.f2131f));
        }
        if (i2 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i2 == 1) {
            return new AccelerateInterpolator();
        }
        if (i2 == 2) {
            return new DecelerateInterpolator();
        }
        if (i2 == 4) {
            return new AnticipateInterpolator();
        }
        if (i2 != 5) {
            return null;
        }
        return new BounceInterpolator();
    }

    public void q(n nVar) {
        b bVar = this.f2117c;
        if (bVar != null) {
            Iterator it = bVar.k.iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(nVar);
            }
        } else {
            b bVar2 = this.f2120f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.k.iterator();
                while (it2.hasNext()) {
                    ((h) it2.next()).a(nVar);
                }
            }
        }
    }

    public float r() {
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f2117c.l.f();
    }

    public float s() {
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f2117c.l.g();
    }

    public boolean t() {
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return false;
        }
        return this.f2117c.l.h();
    }

    public float u(float f2, float f3) {
        b bVar = this.f2117c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f2117c.l.i(f2, f3);
    }

    public final int v(int i2) {
        int iC;
        b.f.c.f fVar = this.f2116b;
        return (fVar == null || (iC = fVar.c(i2, -1, -1)) == -1) ? i2 : iC;
    }

    public float w() {
        b bVar = this.f2117c;
        if (bVar != null) {
            return bVar.f2134i;
        }
        return 0.0f;
    }

    public int x() {
        b bVar = this.f2117c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f2129d;
    }

    public b y(int i2) {
        for (b bVar : this.f2119e) {
            if (bVar.f2126a == i2) {
                return bVar;
            }
        }
        return null;
    }

    public List<b> z(int i2) {
        int iV = v(i2);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f2119e) {
            if (bVar.f2129d == iV || bVar.f2128c == iV) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2126a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2127b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2128c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2129d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2130e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f2131f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2132g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f2133h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f2134i;
        public final q j;
        public ArrayList<h> k;
        public t l;
        public ArrayList<a> m;
        public int n;
        public boolean o;
        public int p;
        public int q;
        public int r;

        /* JADX INFO: compiled from: MotionScene.java */
        public static class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final b f2135b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f2136c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f2137d;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.f2136c = -1;
                this.f2137d = 17;
                this.f2135b = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), b.f.c.e.OnClick);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i2 = 0; i2 < indexCount; i2++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i2);
                    if (index == b.f.c.e.OnClick_targetId) {
                        this.f2136c = typedArrayObtainStyledAttributes.getResourceId(index, this.f2136c);
                    } else if (index == b.f.c.e.OnClick_clickAction) {
                        this.f2137d = typedArrayObtainStyledAttributes.getInt(index, this.f2137d);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i2, b bVar) {
                int i3 = this.f2136c;
                View viewFindViewById = motionLayout;
                if (i3 != -1) {
                    viewFindViewById = motionLayout.findViewById(i3);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f2136c);
                    return;
                }
                int i4 = bVar.f2129d;
                int i5 = bVar.f2128c;
                if (i4 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i6 = this.f2137d;
                boolean z = false;
                boolean z2 = ((i6 & 1) != 0 && i2 == i4) | ((i6 & 1) != 0 && i2 == i4) | ((i6 & 256) != 0 && i2 == i4) | ((i6 & 16) != 0 && i2 == i5);
                if ((i6 & 4096) != 0 && i2 == i5) {
                    z = true;
                }
                if (z2 || z) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            public boolean b(b bVar, MotionLayout motionLayout) {
                b bVar2 = this.f2135b;
                if (bVar2 == bVar) {
                    return true;
                }
                int i2 = bVar2.f2128c;
                int i3 = this.f2135b.f2129d;
                if (i3 == -1) {
                    return motionLayout.D != i2;
                }
                int i4 = motionLayout.D;
                return i4 == i3 || i4 == i2;
            }

            public void c(MotionLayout motionLayout) {
                int i2 = this.f2136c;
                if (i2 == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i2);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f2136c);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f2135b.j.f2115a;
                if (motionLayout.i0()) {
                    if (this.f2135b.f2129d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.u0(this.f2135b.f2128c);
                            return;
                        }
                        b bVar = new b(this.f2135b.j, this.f2135b);
                        bVar.f2129d = currentState;
                        bVar.f2128c = this.f2135b.f2128c;
                        motionLayout.setTransition(bVar);
                        motionLayout.s0();
                        return;
                    }
                    b bVar2 = this.f2135b.j.f2117c;
                    int i2 = this.f2137d;
                    boolean z = false;
                    boolean z2 = ((i2 & 1) == 0 && (i2 & 256) == 0) ? false : true;
                    boolean z3 = ((i2 & 16) == 0 && (i2 & 4096) == 0) ? false : true;
                    if (z2 && z3) {
                        b bVar3 = this.f2135b.j.f2117c;
                        b bVar4 = this.f2135b;
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
                        if (z && (this.f2137d & 1) != 0) {
                            motionLayout.setTransition(this.f2135b);
                            motionLayout.s0();
                            return;
                        }
                        if (z3 && (this.f2137d & 16) != 0) {
                            motionLayout.setTransition(this.f2135b);
                            motionLayout.t0();
                        } else if (z && (this.f2137d & 256) != 0) {
                            motionLayout.setTransition(this.f2135b);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z3 || (this.f2137d & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f2135b);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        public b(q qVar, b bVar) {
            this.f2126a = -1;
            this.f2127b = false;
            this.f2128c = -1;
            this.f2129d = -1;
            this.f2130e = 0;
            this.f2131f = null;
            this.f2132g = -1;
            this.f2133h = 400;
            this.f2134i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.j = qVar;
            if (bVar != null) {
                this.p = bVar.p;
                this.f2130e = bVar.f2130e;
                this.f2131f = bVar.f2131f;
                this.f2132g = bVar.f2132g;
                this.f2133h = bVar.f2133h;
                this.k = bVar.k;
                this.f2134i = bVar.f2134i;
                this.q = bVar.q;
            }
        }

        public int A() {
            return this.f2129d;
        }

        public t B() {
            return this.l;
        }

        public boolean C() {
            return !this.o;
        }

        public boolean D(int i2) {
            return (i2 & this.r) != 0;
        }

        public void E(int i2) {
            this.f2133h = i2;
        }

        public void t(Context context, XmlPullParser xmlPullParser) {
            this.m.add(new a(context, this, xmlPullParser));
        }

        public String u(Context context) {
            String resourceEntryName = this.f2129d == -1 ? "null" : context.getResources().getResourceEntryName(this.f2129d);
            if (this.f2128c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.f2128c);
        }

        public final void v(q qVar, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                if (index == b.f.c.e.Transition_constraintSetEnd) {
                    this.f2128c = typedArray.getResourceId(index, this.f2128c);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f2128c))) {
                        b.f.c.b bVar = new b.f.c.b();
                        bVar.u(context, this.f2128c);
                        qVar.f2122h.append(this.f2128c, bVar);
                    }
                } else if (index == b.f.c.e.Transition_constraintSetStart) {
                    this.f2129d = typedArray.getResourceId(index, this.f2129d);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f2129d))) {
                        b.f.c.b bVar2 = new b.f.c.b();
                        bVar2.u(context, this.f2129d);
                        qVar.f2122h.append(this.f2129d, bVar2);
                    }
                } else if (index == b.f.c.e.Transition_motionInterpolator) {
                    int i3 = typedArray.peekValue(index).type;
                    if (i3 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.f2132g = resourceId;
                        if (resourceId != -1) {
                            this.f2130e = -2;
                        }
                    } else if (i3 == 3) {
                        String string = typedArray.getString(index);
                        this.f2131f = string;
                        if (string.indexOf("/") > 0) {
                            this.f2132g = typedArray.getResourceId(index, -1);
                            this.f2130e = -2;
                        } else {
                            this.f2130e = -1;
                        }
                    } else {
                        this.f2130e = typedArray.getInteger(index, this.f2130e);
                    }
                } else if (index == b.f.c.e.Transition_duration) {
                    this.f2133h = typedArray.getInt(index, this.f2133h);
                } else if (index == b.f.c.e.Transition_staggered) {
                    this.f2134i = typedArray.getFloat(index, this.f2134i);
                } else if (index == b.f.c.e.Transition_autoTransition) {
                    this.n = typedArray.getInteger(index, this.n);
                } else if (index == b.f.c.e.Transition_android_id) {
                    this.f2126a = typedArray.getResourceId(index, this.f2126a);
                } else if (index == b.f.c.e.Transition_transitionDisable) {
                    this.o = typedArray.getBoolean(index, this.o);
                } else if (index == b.f.c.e.Transition_pathMotionArc) {
                    this.p = typedArray.getInteger(index, -1);
                } else if (index == b.f.c.e.Transition_layoutDuringTransition) {
                    this.q = typedArray.getInteger(index, 0);
                } else if (index == b.f.c.e.Transition_transitionFlags) {
                    this.r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f2129d == -1) {
                this.f2127b = true;
            }
        }

        public final void w(q qVar, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f.c.e.Transition);
            v(qVar, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int x() {
            return this.f2133h;
        }

        public int y() {
            return this.f2128c;
        }

        public int z() {
            return this.q;
        }

        public b(q qVar, Context context, XmlPullParser xmlPullParser) {
            this.f2126a = -1;
            this.f2127b = false;
            this.f2128c = -1;
            this.f2129d = -1;
            this.f2130e = 0;
            this.f2131f = null;
            this.f2132g = -1;
            this.f2133h = 400;
            this.f2134i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.f2133h = qVar.l;
            this.q = qVar.m;
            this.j = qVar;
            w(qVar, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
