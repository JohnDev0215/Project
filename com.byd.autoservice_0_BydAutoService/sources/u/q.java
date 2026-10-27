package u;

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
import androidx.constraintlayout.widget.R$id;
import androidx.constraintlayout.widget.R$styleable;
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
    public final MotionLayout f5243a;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public MotionEvent f5256n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public MotionLayout.f f5258p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f5259q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5260r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5261s;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public y.d f5244b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f5245c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5246d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<b> f5247e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f5248f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<b> f5249g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SparseArray<y.b> f5250h = new SparseArray<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public HashMap<String, Integer> f5251i = new HashMap<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public SparseIntArray f5252j = new SparseIntArray();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f5253k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5254l = 400;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5255m = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f5257o = false;

    /* JADX INFO: compiled from: MotionScene.java */
    public class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t.c f5262a;

        public a(t.c cVar) {
            this.f5262a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f3) {
            return (float) this.f5262a.a(f3);
        }
    }

    public q(Context context, MotionLayout motionLayout, int i3) {
        this.f5243a = motionLayout;
        C(context, i3);
        SparseArray<y.b> sparseArray = this.f5250h;
        int i4 = R$id.motion_base;
        sparseArray.put(i4, new y.b());
        this.f5251i.put("motion_base", Integer.valueOf(i4));
    }

    public static String P(String str) {
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(47);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }

    public final boolean A(int i3) {
        int i4 = this.f5252j.get(i3);
        int size = this.f5252j.size();
        while (i4 > 0) {
            if (i4 == i3) {
                return true;
            }
            int i5 = size - 1;
            if (size < 0) {
                return true;
            }
            i4 = this.f5252j.get(i4);
            size = i5;
        }
        return false;
    }

    public final boolean B() {
        return this.f5258p != null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void C(Context context, int i3) {
        XmlResourceParser xml = context.getResources().getXml(i3);
        b bVar = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                byte b3 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    if (this.f5253k) {
                        System.out.println("parsing = " + name);
                    }
                    switch (name.hashCode()) {
                        case -1349929691:
                            b3 = !name.equals("ConstraintSet") ? (byte) -1 : (byte) 5;
                            break;
                        case -1239391468:
                            b3 = !name.equals("KeyFrameSet") ? (byte) -1 : (byte) 6;
                            break;
                        case 269306229:
                            if (!name.equals("Transition")) {
                                b3 = -1;
                            }
                            break;
                        case 312750793:
                            b3 = !name.equals("OnClick") ? (byte) -1 : (byte) 3;
                            break;
                        case 327855227:
                            b3 = !name.equals("OnSwipe") ? (byte) -1 : (byte) 2;
                            break;
                        case 793277014:
                            b3 = !name.equals("MotionScene") ? (byte) -1 : (byte) 0;
                            break;
                        case 1382829617:
                            b3 = !name.equals("StateSet") ? (byte) -1 : (byte) 4;
                            break;
                        default:
                            b3 = -1;
                            break;
                    }
                    switch (b3) {
                        case 0:
                            E(context, xml);
                            break;
                        case 1:
                            ArrayList<b> arrayList = this.f5247e;
                            b bVar2 = new b(this, context, xml);
                            arrayList.add(bVar2);
                            if (this.f5245c == null && !bVar2.f5265b) {
                                this.f5245c = bVar2;
                                if (bVar2.f5275l != null) {
                                    this.f5245c.f5275l.p(this.f5259q);
                                }
                            }
                            if (bVar2.f5265b) {
                                if (bVar2.f5266c == -1) {
                                    this.f5248f = bVar2;
                                } else {
                                    this.f5249g.add(bVar2);
                                }
                                this.f5247e.remove(bVar2);
                            }
                            bVar = bVar2;
                            break;
                        case 2:
                            if (bVar == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i3) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            bVar.f5275l = new t(context, this.f5243a, xml);
                            break;
                        case 3:
                            bVar.t(context, xml);
                            break;
                        case 4:
                            this.f5244b = new y.d(context, xml);
                            break;
                        case 5:
                            D(context, xml);
                            break;
                        case 6:
                            bVar.f5274k.add(new h(context, xml));
                            break;
                        default:
                            Log.v("MotionScene", "WARNING UNKNOWN ATTRIBUTE " + name);
                            break;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (XmlPullParserException e4) {
            e4.printStackTrace();
        }
    }

    public final void D(Context context, XmlPullParser xmlPullParser) {
        y.b bVar = new y.b();
        bVar.A(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int iO = -1;
        int iO2 = -1;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            String attributeName = xmlPullParser.getAttributeName(i3);
            String attributeValue = xmlPullParser.getAttributeValue(i3);
            if (this.f5253k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            if (attributeName.equals("deriveConstraintsFrom")) {
                iO2 = o(context, attributeValue);
            } else if (attributeName.equals("id")) {
                iO = o(context, attributeValue);
                this.f5251i.put(P(attributeValue), Integer.valueOf(iO));
            }
        }
        if (iO != -1) {
            if (this.f5243a.V != 0) {
                bVar.B(true);
            }
            bVar.v(context, xmlPullParser);
            if (iO2 != -1) {
                this.f5252j.put(iO, iO2);
            }
            this.f5250h.put(iO, bVar);
        }
    }

    public final void E(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i3);
            if (index == R$styleable.MotionScene_defaultDuration) {
                this.f5254l = typedArrayObtainStyledAttributes.getInt(index, this.f5254l);
            } else if (index == R$styleable.MotionScene_layoutDuringTransition) {
                this.f5255m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void F(float f3, float f4) {
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return;
        }
        this.f5245c.f5275l.m(f3, f4);
    }

    public void G(float f3, float f4) {
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return;
        }
        this.f5245c.f5275l.n(f3, f4);
    }

    public void H(MotionEvent motionEvent, int i3, MotionLayout motionLayout) {
        MotionLayout.f fVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.f5258p == null) {
            this.f5258p = this.f5243a.j0();
        }
        this.f5258p.a(motionEvent);
        if (i3 != -1) {
            int action = motionEvent.getAction();
            boolean z3 = false;
            if (action == 0) {
                this.f5260r = motionEvent.getRawX();
                this.f5261s = motionEvent.getRawY();
                this.f5256n = motionEvent;
                if (this.f5245c.f5275l != null) {
                    RectF rectFE = this.f5245c.f5275l.e(this.f5243a, rectF);
                    if (rectFE != null && !rectFE.contains(this.f5256n.getX(), this.f5256n.getY())) {
                        this.f5256n = null;
                        return;
                    }
                    RectF rectFJ = this.f5245c.f5275l.j(this.f5243a, rectF);
                    if (rectFJ == null || rectFJ.contains(this.f5256n.getX(), this.f5256n.getY())) {
                        this.f5257o = false;
                    } else {
                        this.f5257o = true;
                    }
                    this.f5245c.f5275l.o(this.f5260r, this.f5261s);
                    return;
                }
                return;
            }
            if (action == 2) {
                float rawY = motionEvent.getRawY() - this.f5261s;
                float rawX = motionEvent.getRawX() - this.f5260r;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.f5256n) == null) {
                    return;
                }
                b bVarG = g(i3, rawX, rawY, motionEvent2);
                if (bVarG != null) {
                    motionLayout.setTransition(bVarG);
                    RectF rectFJ2 = this.f5245c.f5275l.j(this.f5243a, rectF);
                    if (rectFJ2 != null && !rectFJ2.contains(this.f5256n.getX(), this.f5256n.getY())) {
                        z3 = true;
                    }
                    this.f5257o = z3;
                    this.f5245c.f5275l.q(this.f5260r, this.f5261s);
                }
            }
        }
        b bVar = this.f5245c;
        if (bVar != null && bVar.f5275l != null && !this.f5257o) {
            this.f5245c.f5275l.l(motionEvent, this.f5258p, i3, this);
        }
        this.f5260r = motionEvent.getRawX();
        this.f5261s = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (fVar = this.f5258p) == null) {
            return;
        }
        fVar.d();
        this.f5258p = null;
        int i4 = motionLayout.D;
        if (i4 != -1) {
            f(motionLayout, i4);
        }
    }

    public final void I(int i3) {
        int i4 = this.f5252j.get(i3);
        if (i4 > 0) {
            I(this.f5252j.get(i3));
            y.b bVar = this.f5250h.get(i3);
            y.b bVar2 = this.f5250h.get(i4);
            if (bVar2 != null) {
                bVar.z(bVar2);
                this.f5252j.put(i3, -1);
            } else {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + u.a.b(this.f5243a.getContext(), i4));
            }
        }
    }

    public void J(MotionLayout motionLayout) {
        for (int i3 = 0; i3 < this.f5250h.size(); i3++) {
            int iKeyAt = this.f5250h.keyAt(i3);
            if (A(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            I(iKeyAt);
        }
        for (int i4 = 0; i4 < this.f5250h.size(); i4++) {
            this.f5250h.valueAt(i4).y(motionLayout);
        }
    }

    public void K(int i3) {
        b bVar = this.f5245c;
        if (bVar != null) {
            bVar.E(i3);
        } else {
            this.f5254l = i3;
        }
    }

    public void L(boolean z3) {
        this.f5259q = z3;
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return;
        }
        this.f5245c.f5275l.p(this.f5259q);
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
            y.d r0 = r6.f5244b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            y.d r2 = r6.f5244b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            java.util.ArrayList<u.q$b> r3 = r6.f5247e
            java.util.Iterator r3 = r3.iterator()
        L1e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r3.next()
            u.q$b r4 = (u.q.b) r4
            int r5 = u.q.b.a(r4)
            if (r5 != r2) goto L36
            int r5 = u.q.b.c(r4)
            if (r5 == r0) goto L42
        L36:
            int r5 = u.q.b.a(r4)
            if (r5 != r8) goto L1e
            int r5 = u.q.b.c(r4)
            if (r5 != r7) goto L1e
        L42:
            r6.f5245c = r4
            if (r4 == 0) goto L57
            u.t r7 = u.q.b.m(r4)
            if (r7 == 0) goto L57
            u.q$b r7 = r6.f5245c
            u.t r7 = u.q.b.m(r7)
            boolean r8 = r6.f5259q
            r7.p(r8)
        L57:
            return
        L58:
            u.q$b r7 = r6.f5248f
            java.util.ArrayList<u.q$b> r3 = r6.f5249g
            java.util.Iterator r3 = r3.iterator()
        L60:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L74
            java.lang.Object r4 = r3.next()
            u.q$b r4 = (u.q.b) r4
            int r5 = u.q.b.a(r4)
            if (r5 != r8) goto L60
            r7 = r4
            goto L60
        L74:
            u.q$b r8 = new u.q$b
            r8.<init>(r6, r7)
            u.q.b.d(r8, r0)
            u.q.b.b(r8, r2)
            if (r0 == r1) goto L86
            java.util.ArrayList<u.q$b> r7 = r6.f5247e
            r7.add(r8)
        L86:
            r6.f5245c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: u.q.M(int, int):void");
    }

    public void N(b bVar) {
        this.f5245c = bVar;
        if (bVar == null || bVar.f5275l == null) {
            return;
        }
        this.f5245c.f5275l.p(this.f5259q);
    }

    public void O() {
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return;
        }
        this.f5245c.f5275l.r();
    }

    public boolean Q() {
        Iterator<b> it = this.f5247e.iterator();
        while (it.hasNext()) {
            if (it.next().f5275l != null) {
                return true;
            }
        }
        b bVar = this.f5245c;
        return (bVar == null || bVar.f5275l == null) ? false : true;
    }

    public void e(MotionLayout motionLayout, int i3) {
        for (b bVar : this.f5247e) {
            if (bVar.f5276m.size() > 0) {
                Iterator it = bVar.f5276m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).c(motionLayout);
                }
            }
        }
        for (b bVar2 : this.f5249g) {
            if (bVar2.f5276m.size() > 0) {
                Iterator it2 = bVar2.f5276m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).c(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f5247e) {
            if (bVar3.f5276m.size() > 0) {
                Iterator it3 = bVar3.f5276m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i3, bVar3);
                }
            }
        }
        for (b bVar4 : this.f5249g) {
            if (bVar4.f5276m.size() > 0) {
                Iterator it4 = bVar4.f5276m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i3, bVar4);
                }
            }
        }
    }

    public boolean f(MotionLayout motionLayout, int i3) {
        if (B() || this.f5246d) {
            return false;
        }
        for (b bVar : this.f5247e) {
            if (bVar.f5277n != 0) {
                if (i3 == bVar.f5267d && (bVar.f5277n == 4 || bVar.f5277n == 2)) {
                    MotionLayout.TransitionState transitionState = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(bVar);
                    if (bVar.f5277n == 4) {
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
                if (i3 == bVar.f5266c && (bVar.f5277n == 3 || bVar.f5277n == 1)) {
                    MotionLayout.TransitionState transitionState2 = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState2);
                    motionLayout.setTransition(bVar);
                    if (bVar.f5277n == 3) {
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

    public b g(int i3, float f3, float f4, MotionEvent motionEvent) {
        if (i3 == -1) {
            return this.f5245c;
        }
        List<b> listZ = z(i3);
        float f5 = 0.0f;
        b bVar = null;
        RectF rectF = new RectF();
        for (b bVar2 : listZ) {
            if (!bVar2.f5278o && bVar2.f5275l != null) {
                bVar2.f5275l.p(this.f5259q);
                RectF rectFJ = bVar2.f5275l.j(this.f5243a, rectF);
                if (rectFJ == null || motionEvent == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF rectFJ2 = bVar2.f5275l.j(this.f5243a, rectF);
                    if (rectFJ2 == null || motionEvent == null || rectFJ2.contains(motionEvent.getX(), motionEvent.getY())) {
                        float fA = bVar2.f5275l.a(f3, f4) * (bVar2.f5266c == i3 ? -1.0f : 1.1f);
                        if (fA > f5) {
                            bVar = bVar2;
                            f5 = fA;
                        }
                    }
                }
            }
        }
        return bVar;
    }

    public int h() {
        b bVar = this.f5245c;
        if (bVar != null) {
            return bVar.f5279p;
        }
        return -1;
    }

    public y.b i(int i3) {
        return j(i3, -1, -1);
    }

    public y.b j(int i3, int i4, int i5) {
        int iC;
        if (this.f5253k) {
            System.out.println("id " + i3);
            System.out.println("size " + this.f5250h.size());
        }
        y.d dVar = this.f5244b;
        if (dVar != null && (iC = dVar.c(i3, i4, i5)) != -1) {
            i3 = iC;
        }
        if (this.f5250h.get(i3) != null) {
            return this.f5250h.get(i3);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + u.a.b(this.f5243a.getContext(), i3) + " In MotionScene");
        SparseArray<y.b> sparseArray = this.f5250h;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] k() {
        int size = this.f5250h.size();
        int[] iArr = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = this.f5250h.keyAt(i3);
        }
        return iArr;
    }

    public ArrayList<b> l() {
        return this.f5247e;
    }

    public int m() {
        b bVar = this.f5245c;
        return bVar != null ? bVar.f5271h : this.f5254l;
    }

    public int n() {
        b bVar = this.f5245c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f5266c;
    }

    public final int o(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.f5253k) {
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
        int i3 = this.f5245c.f5268e;
        if (i3 == -2) {
            return AnimationUtils.loadInterpolator(this.f5243a.getContext(), this.f5245c.f5270g);
        }
        if (i3 == -1) {
            return new a(t.c.c(this.f5245c.f5269f));
        }
        if (i3 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i3 == 1) {
            return new AccelerateInterpolator();
        }
        if (i3 == 2) {
            return new DecelerateInterpolator();
        }
        if (i3 == 4) {
            return new AnticipateInterpolator();
        }
        if (i3 != 5) {
            return null;
        }
        return new BounceInterpolator();
    }

    public void q(n nVar) {
        b bVar = this.f5245c;
        if (bVar != null) {
            Iterator it = bVar.f5274k.iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(nVar);
            }
        } else {
            b bVar2 = this.f5248f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.f5274k.iterator();
                while (it2.hasNext()) {
                    ((h) it2.next()).a(nVar);
                }
            }
        }
    }

    public float r() {
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return 0.0f;
        }
        return this.f5245c.f5275l.f();
    }

    public float s() {
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return 0.0f;
        }
        return this.f5245c.f5275l.g();
    }

    public boolean t() {
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return false;
        }
        return this.f5245c.f5275l.h();
    }

    public float u(float f3, float f4) {
        b bVar = this.f5245c;
        if (bVar == null || bVar.f5275l == null) {
            return 0.0f;
        }
        return this.f5245c.f5275l.i(f3, f4);
    }

    public final int v(int i3) {
        int iC;
        y.d dVar = this.f5244b;
        return (dVar == null || (iC = dVar.c(i3, -1, -1)) == -1) ? i3 : iC;
    }

    public float w() {
        b bVar = this.f5245c;
        if (bVar != null) {
            return bVar.f5272i;
        }
        return 0.0f;
    }

    public int x() {
        b bVar = this.f5245c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f5267d;
    }

    public b y(int i3) {
        for (b bVar : this.f5247e) {
            if (bVar.f5264a == i3) {
                return bVar;
            }
        }
        return null;
    }

    public List<b> z(int i3) {
        int iV = v(i3);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f5247e) {
            if (bVar.f5267d == iV || bVar.f5266c == iV) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5264a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5265b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5266c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5267d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5268e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f5269f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5270g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f5271h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f5272i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final q f5273j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public ArrayList<h> f5274k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public t f5275l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public ArrayList<a> f5276m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f5277n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f5278o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f5279p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f5280q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f5281r;

        /* JADX INFO: compiled from: MotionScene.java */
        public static class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final b f5282b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f5283c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f5284d;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.f5283c = -1;
                this.f5284d = 17;
                this.f5282b = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.OnClick);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i3 = 0; i3 < indexCount; i3++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i3);
                    if (index == R$styleable.OnClick_targetId) {
                        this.f5283c = typedArrayObtainStyledAttributes.getResourceId(index, this.f5283c);
                    } else if (index == R$styleable.OnClick_clickAction) {
                        this.f5284d = typedArrayObtainStyledAttributes.getInt(index, this.f5284d);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i3, b bVar) {
                int i4 = this.f5283c;
                View viewFindViewById = motionLayout;
                if (i4 != -1) {
                    viewFindViewById = motionLayout.findViewById(i4);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f5283c);
                    return;
                }
                int i5 = bVar.f5267d;
                int i6 = bVar.f5266c;
                if (i5 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i7 = this.f5284d;
                boolean z3 = false;
                boolean z4 = ((i7 & 1) != 0 && i3 == i5) | ((i7 & 1) != 0 && i3 == i5) | ((i7 & 256) != 0 && i3 == i5) | ((i7 & 16) != 0 && i3 == i6);
                if ((i7 & 4096) != 0 && i3 == i6) {
                    z3 = true;
                }
                if (z4 || z3) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            public boolean b(b bVar, MotionLayout motionLayout) {
                b bVar2 = this.f5282b;
                if (bVar2 == bVar) {
                    return true;
                }
                int i3 = bVar2.f5266c;
                int i4 = this.f5282b.f5267d;
                if (i4 == -1) {
                    return motionLayout.D != i3;
                }
                int i5 = motionLayout.D;
                return i5 == i4 || i5 == i3;
            }

            public void c(MotionLayout motionLayout) {
                int i3 = this.f5283c;
                if (i3 == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i3);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f5283c);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f5282b.f5273j.f5243a;
                if (motionLayout.i0()) {
                    if (this.f5282b.f5267d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.u0(this.f5282b.f5266c);
                            return;
                        }
                        b bVar = new b(this.f5282b.f5273j, this.f5282b);
                        bVar.f5267d = currentState;
                        bVar.f5266c = this.f5282b.f5266c;
                        motionLayout.setTransition(bVar);
                        motionLayout.s0();
                        return;
                    }
                    b bVar2 = this.f5282b.f5273j.f5245c;
                    int i3 = this.f5284d;
                    boolean z3 = false;
                    boolean z4 = ((i3 & 1) == 0 && (i3 & 256) == 0) ? false : true;
                    boolean z5 = ((i3 & 16) == 0 && (i3 & 4096) == 0) ? false : true;
                    if (z4 && z5) {
                        b bVar3 = this.f5282b.f5273j.f5245c;
                        b bVar4 = this.f5282b;
                        if (bVar3 != bVar4) {
                            motionLayout.setTransition(bVar4);
                        }
                        if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                            z5 = false;
                            z3 = z4;
                        }
                    } else {
                        z3 = z4;
                    }
                    if (b(bVar2, motionLayout)) {
                        if (z3 && (this.f5284d & 1) != 0) {
                            motionLayout.setTransition(this.f5282b);
                            motionLayout.s0();
                            return;
                        }
                        if (z5 && (this.f5284d & 16) != 0) {
                            motionLayout.setTransition(this.f5282b);
                            motionLayout.t0();
                        } else if (z3 && (this.f5284d & 256) != 0) {
                            motionLayout.setTransition(this.f5282b);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z5 || (this.f5284d & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f5282b);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        public b(q qVar, b bVar) {
            this.f5264a = -1;
            this.f5265b = false;
            this.f5266c = -1;
            this.f5267d = -1;
            this.f5268e = 0;
            this.f5269f = null;
            this.f5270g = -1;
            this.f5271h = 400;
            this.f5272i = 0.0f;
            this.f5274k = new ArrayList<>();
            this.f5275l = null;
            this.f5276m = new ArrayList<>();
            this.f5277n = 0;
            this.f5278o = false;
            this.f5279p = -1;
            this.f5280q = 0;
            this.f5281r = 0;
            this.f5273j = qVar;
            if (bVar != null) {
                this.f5279p = bVar.f5279p;
                this.f5268e = bVar.f5268e;
                this.f5269f = bVar.f5269f;
                this.f5270g = bVar.f5270g;
                this.f5271h = bVar.f5271h;
                this.f5274k = bVar.f5274k;
                this.f5272i = bVar.f5272i;
                this.f5280q = bVar.f5280q;
            }
        }

        public int A() {
            return this.f5267d;
        }

        public t B() {
            return this.f5275l;
        }

        public boolean C() {
            return !this.f5278o;
        }

        public boolean D(int i3) {
            return (i3 & this.f5281r) != 0;
        }

        public void E(int i3) {
            this.f5271h = i3;
        }

        public void t(Context context, XmlPullParser xmlPullParser) {
            this.f5276m.add(new a(context, this, xmlPullParser));
        }

        public String u(Context context) {
            String resourceEntryName = this.f5267d == -1 ? "null" : context.getResources().getResourceEntryName(this.f5267d);
            if (this.f5266c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.f5266c);
        }

        public final void v(q qVar, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                if (index == R$styleable.Transition_constraintSetEnd) {
                    this.f5266c = typedArray.getResourceId(index, this.f5266c);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f5266c))) {
                        y.b bVar = new y.b();
                        bVar.u(context, this.f5266c);
                        qVar.f5250h.append(this.f5266c, bVar);
                    }
                } else if (index == R$styleable.Transition_constraintSetStart) {
                    this.f5267d = typedArray.getResourceId(index, this.f5267d);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f5267d))) {
                        y.b bVar2 = new y.b();
                        bVar2.u(context, this.f5267d);
                        qVar.f5250h.append(this.f5267d, bVar2);
                    }
                } else if (index == R$styleable.Transition_motionInterpolator) {
                    int i4 = typedArray.peekValue(index).type;
                    if (i4 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.f5270g = resourceId;
                        if (resourceId != -1) {
                            this.f5268e = -2;
                        }
                    } else if (i4 == 3) {
                        String string = typedArray.getString(index);
                        this.f5269f = string;
                        if (string.indexOf("/") > 0) {
                            this.f5270g = typedArray.getResourceId(index, -1);
                            this.f5268e = -2;
                        } else {
                            this.f5268e = -1;
                        }
                    } else {
                        this.f5268e = typedArray.getInteger(index, this.f5268e);
                    }
                } else if (index == R$styleable.Transition_duration) {
                    this.f5271h = typedArray.getInt(index, this.f5271h);
                } else if (index == R$styleable.Transition_staggered) {
                    this.f5272i = typedArray.getFloat(index, this.f5272i);
                } else if (index == R$styleable.Transition_autoTransition) {
                    this.f5277n = typedArray.getInteger(index, this.f5277n);
                } else if (index == R$styleable.Transition_android_id) {
                    this.f5264a = typedArray.getResourceId(index, this.f5264a);
                } else if (index == R$styleable.Transition_transitionDisable) {
                    this.f5278o = typedArray.getBoolean(index, this.f5278o);
                } else if (index == R$styleable.Transition_pathMotionArc) {
                    this.f5279p = typedArray.getInteger(index, -1);
                } else if (index == R$styleable.Transition_layoutDuringTransition) {
                    this.f5280q = typedArray.getInteger(index, 0);
                } else if (index == R$styleable.Transition_transitionFlags) {
                    this.f5281r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f5267d == -1) {
                this.f5265b = true;
            }
        }

        public final void w(q qVar, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transition);
            v(qVar, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int x() {
            return this.f5271h;
        }

        public int y() {
            return this.f5266c;
        }

        public int z() {
            return this.f5280q;
        }

        public b(q qVar, Context context, XmlPullParser xmlPullParser) {
            this.f5264a = -1;
            this.f5265b = false;
            this.f5266c = -1;
            this.f5267d = -1;
            this.f5268e = 0;
            this.f5269f = null;
            this.f5270g = -1;
            this.f5271h = 400;
            this.f5272i = 0.0f;
            this.f5274k = new ArrayList<>();
            this.f5275l = null;
            this.f5276m = new ArrayList<>();
            this.f5277n = 0;
            this.f5278o = false;
            this.f5279p = -1;
            this.f5280q = 0;
            this.f5281r = 0;
            this.f5271h = qVar.f5254l;
            this.f5280q = qVar.f5255m;
            this.f5273j = qVar;
            w(qVar, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
