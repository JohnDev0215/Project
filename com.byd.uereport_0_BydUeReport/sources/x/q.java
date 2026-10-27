package x;

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
    public final MotionLayout f8955a;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public MotionEvent f8968n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public MotionLayout.f f8970p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8971q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f8972r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8973s;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b0.d f8956b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f8957c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f8958d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<b> f8959e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f8960f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<b> f8961g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SparseArray<b0.b> f8962h = new SparseArray<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public HashMap<String, Integer> f8963i = new HashMap<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public SparseIntArray f8964j = new SparseIntArray();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f8965k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8966l = 400;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8967m = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f8969o = false;

    /* JADX INFO: compiled from: MotionScene.java */
    public class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ w.c f8974a;

        public a(w.c cVar) {
            this.f8974a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f8) {
            return (float) this.f8974a.a(f8);
        }
    }

    public q(Context context, MotionLayout motionLayout, int i8) {
        this.f8955a = motionLayout;
        C(context, i8);
        SparseArray<b0.b> sparseArray = this.f8962h;
        int i9 = R$id.motion_base;
        sparseArray.put(i9, new b0.b());
        this.f8963i.put("motion_base", Integer.valueOf(i9));
    }

    public static String P(String str) {
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(47);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }

    public final boolean A(int i8) {
        int i9 = this.f8964j.get(i8);
        int size = this.f8964j.size();
        while (i9 > 0) {
            if (i9 == i8) {
                return true;
            }
            int i10 = size - 1;
            if (size < 0) {
                return true;
            }
            i9 = this.f8964j.get(i9);
            size = i10;
        }
        return false;
    }

    public final boolean B() {
        return this.f8970p != null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void C(Context context, int i8) {
        XmlResourceParser xml = context.getResources().getXml(i8);
        b bVar = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                byte b8 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    if (this.f8965k) {
                        System.out.println("parsing = " + name);
                    }
                    switch (name.hashCode()) {
                        case -1349929691:
                            b8 = !name.equals("ConstraintSet") ? (byte) -1 : (byte) 5;
                            break;
                        case -1239391468:
                            b8 = !name.equals("KeyFrameSet") ? (byte) -1 : (byte) 6;
                            break;
                        case 269306229:
                            if (!name.equals("Transition")) {
                                b8 = -1;
                            }
                            break;
                        case 312750793:
                            b8 = !name.equals("OnClick") ? (byte) -1 : (byte) 3;
                            break;
                        case 327855227:
                            b8 = !name.equals("OnSwipe") ? (byte) -1 : (byte) 2;
                            break;
                        case 793277014:
                            b8 = !name.equals("MotionScene") ? (byte) -1 : (byte) 0;
                            break;
                        case 1382829617:
                            b8 = !name.equals("StateSet") ? (byte) -1 : (byte) 4;
                            break;
                        default:
                            b8 = -1;
                            break;
                    }
                    switch (b8) {
                        case 0:
                            E(context, xml);
                            break;
                        case 1:
                            ArrayList<b> arrayList = this.f8959e;
                            b bVar2 = new b(this, context, xml);
                            arrayList.add(bVar2);
                            if (this.f8957c == null && !bVar2.f8977b) {
                                this.f8957c = bVar2;
                                if (bVar2.f8987l != null) {
                                    this.f8957c.f8987l.p(this.f8971q);
                                }
                            }
                            if (bVar2.f8977b) {
                                if (bVar2.f8978c == -1) {
                                    this.f8960f = bVar2;
                                } else {
                                    this.f8961g.add(bVar2);
                                }
                                this.f8959e.remove(bVar2);
                            }
                            bVar = bVar2;
                            break;
                        case 2:
                            if (bVar == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i8) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            bVar.f8987l = new t(context, this.f8955a, xml);
                            break;
                        case 3:
                            bVar.t(context, xml);
                            break;
                        case 4:
                            this.f8956b = new b0.d(context, xml);
                            break;
                        case 5:
                            D(context, xml);
                            break;
                        case 6:
                            bVar.f8986k.add(new h(context, xml));
                            break;
                        default:
                            Log.v("MotionScene", "WARNING UNKNOWN ATTRIBUTE " + name);
                            break;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        } catch (XmlPullParserException e9) {
            e9.printStackTrace();
        }
    }

    public final void D(Context context, XmlPullParser xmlPullParser) {
        b0.b bVar = new b0.b();
        bVar.A(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int iO = -1;
        int iO2 = -1;
        for (int i8 = 0; i8 < attributeCount; i8++) {
            String attributeName = xmlPullParser.getAttributeName(i8);
            String attributeValue = xmlPullParser.getAttributeValue(i8);
            if (this.f8965k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            if (attributeName.equals("deriveConstraintsFrom")) {
                iO2 = o(context, attributeValue);
            } else if (attributeName.equals("id")) {
                iO = o(context, attributeValue);
                this.f8963i.put(P(attributeValue), Integer.valueOf(iO));
            }
        }
        if (iO != -1) {
            if (this.f8955a.X != 0) {
                bVar.B(true);
            }
            bVar.v(context, xmlPullParser);
            if (iO2 != -1) {
                this.f8964j.put(iO, iO2);
            }
            this.f8962h.put(iO, bVar);
        }
    }

    public final void E(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i8 = 0; i8 < indexCount; i8++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i8);
            if (index == R$styleable.MotionScene_defaultDuration) {
                this.f8966l = typedArrayObtainStyledAttributes.getInt(index, this.f8966l);
            } else if (index == R$styleable.MotionScene_layoutDuringTransition) {
                this.f8967m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void F(float f8, float f9) {
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return;
        }
        this.f8957c.f8987l.m(f8, f9);
    }

    public void G(float f8, float f9) {
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return;
        }
        this.f8957c.f8987l.n(f8, f9);
    }

    public void H(MotionEvent motionEvent, int i8, MotionLayout motionLayout) {
        MotionLayout.f fVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.f8970p == null) {
            this.f8970p = this.f8955a.j0();
        }
        this.f8970p.a(motionEvent);
        if (i8 != -1) {
            int action = motionEvent.getAction();
            boolean z7 = false;
            if (action == 0) {
                this.f8972r = motionEvent.getRawX();
                this.f8973s = motionEvent.getRawY();
                this.f8968n = motionEvent;
                if (this.f8957c.f8987l != null) {
                    RectF rectFE = this.f8957c.f8987l.e(this.f8955a, rectF);
                    if (rectFE != null && !rectFE.contains(this.f8968n.getX(), this.f8968n.getY())) {
                        this.f8968n = null;
                        return;
                    }
                    RectF rectFJ = this.f8957c.f8987l.j(this.f8955a, rectF);
                    if (rectFJ == null || rectFJ.contains(this.f8968n.getX(), this.f8968n.getY())) {
                        this.f8969o = false;
                    } else {
                        this.f8969o = true;
                    }
                    this.f8957c.f8987l.o(this.f8972r, this.f8973s);
                    return;
                }
                return;
            }
            if (action == 2) {
                float rawY = motionEvent.getRawY() - this.f8973s;
                float rawX = motionEvent.getRawX() - this.f8972r;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.f8968n) == null) {
                    return;
                }
                b bVarG = g(i8, rawX, rawY, motionEvent2);
                if (bVarG != null) {
                    motionLayout.setTransition(bVarG);
                    RectF rectFJ2 = this.f8957c.f8987l.j(this.f8955a, rectF);
                    if (rectFJ2 != null && !rectFJ2.contains(this.f8968n.getX(), this.f8968n.getY())) {
                        z7 = true;
                    }
                    this.f8969o = z7;
                    this.f8957c.f8987l.q(this.f8972r, this.f8973s);
                }
            }
        }
        b bVar = this.f8957c;
        if (bVar != null && bVar.f8987l != null && !this.f8969o) {
            this.f8957c.f8987l.l(motionEvent, this.f8970p, i8, this);
        }
        this.f8972r = motionEvent.getRawX();
        this.f8973s = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (fVar = this.f8970p) == null) {
            return;
        }
        fVar.d();
        this.f8970p = null;
        int i9 = motionLayout.F;
        if (i9 != -1) {
            f(motionLayout, i9);
        }
    }

    public final void I(int i8) {
        int i9 = this.f8964j.get(i8);
        if (i9 > 0) {
            I(this.f8964j.get(i8));
            b0.b bVar = this.f8962h.get(i8);
            b0.b bVar2 = this.f8962h.get(i9);
            if (bVar2 != null) {
                bVar.z(bVar2);
                this.f8964j.put(i8, -1);
            } else {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + x.a.b(this.f8955a.getContext(), i9));
            }
        }
    }

    public void J(MotionLayout motionLayout) {
        for (int i8 = 0; i8 < this.f8962h.size(); i8++) {
            int iKeyAt = this.f8962h.keyAt(i8);
            if (A(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            I(iKeyAt);
        }
        for (int i9 = 0; i9 < this.f8962h.size(); i9++) {
            this.f8962h.valueAt(i9).y(motionLayout);
        }
    }

    public void K(int i8) {
        b bVar = this.f8957c;
        if (bVar != null) {
            bVar.E(i8);
        } else {
            this.f8966l = i8;
        }
    }

    public void L(boolean z7) {
        this.f8971q = z7;
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return;
        }
        this.f8957c.f8987l.p(this.f8971q);
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
            b0.d r0 = r6.f8956b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            b0.d r2 = r6.f8956b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            java.util.ArrayList<x.q$b> r3 = r6.f8959e
            java.util.Iterator r3 = r3.iterator()
        L1e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r3.next()
            x.q$b r4 = (x.q.b) r4
            int r5 = x.q.b.a(r4)
            if (r5 != r2) goto L36
            int r5 = x.q.b.c(r4)
            if (r5 == r0) goto L42
        L36:
            int r5 = x.q.b.a(r4)
            if (r5 != r8) goto L1e
            int r5 = x.q.b.c(r4)
            if (r5 != r7) goto L1e
        L42:
            r6.f8957c = r4
            if (r4 == 0) goto L57
            x.t r7 = x.q.b.m(r4)
            if (r7 == 0) goto L57
            x.q$b r7 = r6.f8957c
            x.t r7 = x.q.b.m(r7)
            boolean r8 = r6.f8971q
            r7.p(r8)
        L57:
            return
        L58:
            x.q$b r7 = r6.f8960f
            java.util.ArrayList<x.q$b> r3 = r6.f8961g
            java.util.Iterator r3 = r3.iterator()
        L60:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L74
            java.lang.Object r4 = r3.next()
            x.q$b r4 = (x.q.b) r4
            int r5 = x.q.b.a(r4)
            if (r5 != r8) goto L60
            r7 = r4
            goto L60
        L74:
            x.q$b r8 = new x.q$b
            r8.<init>(r6, r7)
            x.q.b.d(r8, r0)
            x.q.b.b(r8, r2)
            if (r0 == r1) goto L86
            java.util.ArrayList<x.q$b> r7 = r6.f8959e
            r7.add(r8)
        L86:
            r6.f8957c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x.q.M(int, int):void");
    }

    public void N(b bVar) {
        this.f8957c = bVar;
        if (bVar == null || bVar.f8987l == null) {
            return;
        }
        this.f8957c.f8987l.p(this.f8971q);
    }

    public void O() {
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return;
        }
        this.f8957c.f8987l.r();
    }

    public boolean Q() {
        Iterator<b> it = this.f8959e.iterator();
        while (it.hasNext()) {
            if (it.next().f8987l != null) {
                return true;
            }
        }
        b bVar = this.f8957c;
        return (bVar == null || bVar.f8987l == null) ? false : true;
    }

    public void e(MotionLayout motionLayout, int i8) {
        for (b bVar : this.f8959e) {
            if (bVar.f8988m.size() > 0) {
                Iterator it = bVar.f8988m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).c(motionLayout);
                }
            }
        }
        for (b bVar2 : this.f8961g) {
            if (bVar2.f8988m.size() > 0) {
                Iterator it2 = bVar2.f8988m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).c(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f8959e) {
            if (bVar3.f8988m.size() > 0) {
                Iterator it3 = bVar3.f8988m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i8, bVar3);
                }
            }
        }
        for (b bVar4 : this.f8961g) {
            if (bVar4.f8988m.size() > 0) {
                Iterator it4 = bVar4.f8988m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i8, bVar4);
                }
            }
        }
    }

    public boolean f(MotionLayout motionLayout, int i8) {
        if (B() || this.f8958d) {
            return false;
        }
        for (b bVar : this.f8959e) {
            if (bVar.f8989n != 0) {
                if (i8 == bVar.f8979d && (bVar.f8989n == 4 || bVar.f8989n == 2)) {
                    MotionLayout.TransitionState transitionState = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(bVar);
                    if (bVar.f8989n == 4) {
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
                if (i8 == bVar.f8978c && (bVar.f8989n == 3 || bVar.f8989n == 1)) {
                    MotionLayout.TransitionState transitionState2 = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState2);
                    motionLayout.setTransition(bVar);
                    if (bVar.f8989n == 3) {
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

    public b g(int i8, float f8, float f9, MotionEvent motionEvent) {
        if (i8 == -1) {
            return this.f8957c;
        }
        List<b> listZ = z(i8);
        float f10 = 0.0f;
        b bVar = null;
        RectF rectF = new RectF();
        for (b bVar2 : listZ) {
            if (!bVar2.f8990o && bVar2.f8987l != null) {
                bVar2.f8987l.p(this.f8971q);
                RectF rectFJ = bVar2.f8987l.j(this.f8955a, rectF);
                if (rectFJ == null || motionEvent == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF rectFJ2 = bVar2.f8987l.j(this.f8955a, rectF);
                    if (rectFJ2 == null || motionEvent == null || rectFJ2.contains(motionEvent.getX(), motionEvent.getY())) {
                        float fA = bVar2.f8987l.a(f8, f9) * (bVar2.f8978c == i8 ? -1.0f : 1.1f);
                        if (fA > f10) {
                            bVar = bVar2;
                            f10 = fA;
                        }
                    }
                }
            }
        }
        return bVar;
    }

    public int h() {
        b bVar = this.f8957c;
        if (bVar != null) {
            return bVar.f8991p;
        }
        return -1;
    }

    public b0.b i(int i8) {
        return j(i8, -1, -1);
    }

    public b0.b j(int i8, int i9, int i10) {
        int iC;
        if (this.f8965k) {
            System.out.println("id " + i8);
            System.out.println("size " + this.f8962h.size());
        }
        b0.d dVar = this.f8956b;
        if (dVar != null && (iC = dVar.c(i8, i9, i10)) != -1) {
            i8 = iC;
        }
        if (this.f8962h.get(i8) != null) {
            return this.f8962h.get(i8);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + x.a.b(this.f8955a.getContext(), i8) + " In MotionScene");
        SparseArray<b0.b> sparseArray = this.f8962h;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] k() {
        int size = this.f8962h.size();
        int[] iArr = new int[size];
        for (int i8 = 0; i8 < size; i8++) {
            iArr[i8] = this.f8962h.keyAt(i8);
        }
        return iArr;
    }

    public ArrayList<b> l() {
        return this.f8959e;
    }

    public int m() {
        b bVar = this.f8957c;
        return bVar != null ? bVar.f8983h : this.f8966l;
    }

    public int n() {
        b bVar = this.f8957c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f8978c;
    }

    public final int o(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.f8965k) {
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
        int i8 = this.f8957c.f8980e;
        if (i8 == -2) {
            return AnimationUtils.loadInterpolator(this.f8955a.getContext(), this.f8957c.f8982g);
        }
        if (i8 == -1) {
            return new a(w.c.c(this.f8957c.f8981f));
        }
        if (i8 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i8 == 1) {
            return new AccelerateInterpolator();
        }
        if (i8 == 2) {
            return new DecelerateInterpolator();
        }
        if (i8 == 4) {
            return new AnticipateInterpolator();
        }
        if (i8 != 5) {
            return null;
        }
        return new BounceInterpolator();
    }

    public void q(n nVar) {
        b bVar = this.f8957c;
        if (bVar != null) {
            Iterator it = bVar.f8986k.iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(nVar);
            }
        } else {
            b bVar2 = this.f8960f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.f8986k.iterator();
                while (it2.hasNext()) {
                    ((h) it2.next()).a(nVar);
                }
            }
        }
    }

    public float r() {
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return 0.0f;
        }
        return this.f8957c.f8987l.f();
    }

    public float s() {
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return 0.0f;
        }
        return this.f8957c.f8987l.g();
    }

    public boolean t() {
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return false;
        }
        return this.f8957c.f8987l.h();
    }

    public float u(float f8, float f9) {
        b bVar = this.f8957c;
        if (bVar == null || bVar.f8987l == null) {
            return 0.0f;
        }
        return this.f8957c.f8987l.i(f8, f9);
    }

    public final int v(int i8) {
        int iC;
        b0.d dVar = this.f8956b;
        return (dVar == null || (iC = dVar.c(i8, -1, -1)) == -1) ? i8 : iC;
    }

    public float w() {
        b bVar = this.f8957c;
        if (bVar != null) {
            return bVar.f8984i;
        }
        return 0.0f;
    }

    public int x() {
        b bVar = this.f8957c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f8979d;
    }

    public b y(int i8) {
        for (b bVar : this.f8959e) {
            if (bVar.f8976a == i8) {
                return bVar;
            }
        }
        return null;
    }

    public List<b> z(int i8) {
        int iV = v(i8);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f8959e) {
            if (bVar.f8979d == iV || bVar.f8978c == iV) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8976a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f8977b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f8978c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f8979d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8980e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f8981f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f8982g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f8983h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f8984i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final q f8985j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public ArrayList<h> f8986k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public t f8987l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public ArrayList<a> f8988m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f8989n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f8990o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f8991p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f8992q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f8993r;

        /* JADX INFO: compiled from: MotionScene.java */
        public static class a implements View.OnClickListener {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final b f8994d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public int f8995e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public int f8996f;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.f8995e = -1;
                this.f8996f = 17;
                this.f8994d = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.OnClick);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i8 = 0; i8 < indexCount; i8++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i8);
                    if (index == R$styleable.OnClick_targetId) {
                        this.f8995e = typedArrayObtainStyledAttributes.getResourceId(index, this.f8995e);
                    } else if (index == R$styleable.OnClick_clickAction) {
                        this.f8996f = typedArrayObtainStyledAttributes.getInt(index, this.f8996f);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i8, b bVar) {
                int i9 = this.f8995e;
                View viewFindViewById = motionLayout;
                if (i9 != -1) {
                    viewFindViewById = motionLayout.findViewById(i9);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f8995e);
                    return;
                }
                int i10 = bVar.f8979d;
                int i11 = bVar.f8978c;
                if (i10 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i12 = this.f8996f;
                boolean z7 = false;
                boolean z8 = ((i12 & 1) != 0 && i8 == i10) | ((i12 & 1) != 0 && i8 == i10) | ((i12 & 256) != 0 && i8 == i10) | ((i12 & 16) != 0 && i8 == i11);
                if ((i12 & 4096) != 0 && i8 == i11) {
                    z7 = true;
                }
                if (z8 || z7) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            public boolean b(b bVar, MotionLayout motionLayout) {
                b bVar2 = this.f8994d;
                if (bVar2 == bVar) {
                    return true;
                }
                int i8 = bVar2.f8978c;
                int i9 = this.f8994d.f8979d;
                if (i9 == -1) {
                    return motionLayout.F != i8;
                }
                int i10 = motionLayout.F;
                return i10 == i9 || i10 == i8;
            }

            public void c(MotionLayout motionLayout) {
                int i8 = this.f8995e;
                if (i8 == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i8);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f8995e);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f8994d.f8985j.f8955a;
                if (motionLayout.i0()) {
                    if (this.f8994d.f8979d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.u0(this.f8994d.f8978c);
                            return;
                        }
                        b bVar = new b(this.f8994d.f8985j, this.f8994d);
                        bVar.f8979d = currentState;
                        bVar.f8978c = this.f8994d.f8978c;
                        motionLayout.setTransition(bVar);
                        motionLayout.s0();
                        return;
                    }
                    b bVar2 = this.f8994d.f8985j.f8957c;
                    int i8 = this.f8996f;
                    boolean z7 = false;
                    boolean z8 = ((i8 & 1) == 0 && (i8 & 256) == 0) ? false : true;
                    boolean z9 = ((i8 & 16) == 0 && (i8 & 4096) == 0) ? false : true;
                    if (z8 && z9) {
                        b bVar3 = this.f8994d.f8985j.f8957c;
                        b bVar4 = this.f8994d;
                        if (bVar3 != bVar4) {
                            motionLayout.setTransition(bVar4);
                        }
                        if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                            z9 = false;
                            z7 = z8;
                        }
                    } else {
                        z7 = z8;
                    }
                    if (b(bVar2, motionLayout)) {
                        if (z7 && (this.f8996f & 1) != 0) {
                            motionLayout.setTransition(this.f8994d);
                            motionLayout.s0();
                            return;
                        }
                        if (z9 && (this.f8996f & 16) != 0) {
                            motionLayout.setTransition(this.f8994d);
                            motionLayout.t0();
                        } else if (z7 && (this.f8996f & 256) != 0) {
                            motionLayout.setTransition(this.f8994d);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z9 || (this.f8996f & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f8994d);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        public b(q qVar, b bVar) {
            this.f8976a = -1;
            this.f8977b = false;
            this.f8978c = -1;
            this.f8979d = -1;
            this.f8980e = 0;
            this.f8981f = null;
            this.f8982g = -1;
            this.f8983h = 400;
            this.f8984i = 0.0f;
            this.f8986k = new ArrayList<>();
            this.f8987l = null;
            this.f8988m = new ArrayList<>();
            this.f8989n = 0;
            this.f8990o = false;
            this.f8991p = -1;
            this.f8992q = 0;
            this.f8993r = 0;
            this.f8985j = qVar;
            if (bVar != null) {
                this.f8991p = bVar.f8991p;
                this.f8980e = bVar.f8980e;
                this.f8981f = bVar.f8981f;
                this.f8982g = bVar.f8982g;
                this.f8983h = bVar.f8983h;
                this.f8986k = bVar.f8986k;
                this.f8984i = bVar.f8984i;
                this.f8992q = bVar.f8992q;
            }
        }

        public int A() {
            return this.f8979d;
        }

        public t B() {
            return this.f8987l;
        }

        public boolean C() {
            return !this.f8990o;
        }

        public boolean D(int i8) {
            return (i8 & this.f8993r) != 0;
        }

        public void E(int i8) {
            this.f8983h = i8;
        }

        public void t(Context context, XmlPullParser xmlPullParser) {
            this.f8988m.add(new a(context, this, xmlPullParser));
        }

        public String u(Context context) {
            String resourceEntryName = this.f8979d == -1 ? "null" : context.getResources().getResourceEntryName(this.f8979d);
            if (this.f8978c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.f8978c);
        }

        public final void v(q qVar, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArray.getIndex(i8);
                if (index == R$styleable.Transition_constraintSetEnd) {
                    this.f8978c = typedArray.getResourceId(index, this.f8978c);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f8978c))) {
                        b0.b bVar = new b0.b();
                        bVar.u(context, this.f8978c);
                        qVar.f8962h.append(this.f8978c, bVar);
                    }
                } else if (index == R$styleable.Transition_constraintSetStart) {
                    this.f8979d = typedArray.getResourceId(index, this.f8979d);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f8979d))) {
                        b0.b bVar2 = new b0.b();
                        bVar2.u(context, this.f8979d);
                        qVar.f8962h.append(this.f8979d, bVar2);
                    }
                } else if (index == R$styleable.Transition_motionInterpolator) {
                    int i9 = typedArray.peekValue(index).type;
                    if (i9 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.f8982g = resourceId;
                        if (resourceId != -1) {
                            this.f8980e = -2;
                        }
                    } else if (i9 == 3) {
                        String string = typedArray.getString(index);
                        this.f8981f = string;
                        if (string.indexOf("/") > 0) {
                            this.f8982g = typedArray.getResourceId(index, -1);
                            this.f8980e = -2;
                        } else {
                            this.f8980e = -1;
                        }
                    } else {
                        this.f8980e = typedArray.getInteger(index, this.f8980e);
                    }
                } else if (index == R$styleable.Transition_duration) {
                    this.f8983h = typedArray.getInt(index, this.f8983h);
                } else if (index == R$styleable.Transition_staggered) {
                    this.f8984i = typedArray.getFloat(index, this.f8984i);
                } else if (index == R$styleable.Transition_autoTransition) {
                    this.f8989n = typedArray.getInteger(index, this.f8989n);
                } else if (index == R$styleable.Transition_android_id) {
                    this.f8976a = typedArray.getResourceId(index, this.f8976a);
                } else if (index == R$styleable.Transition_transitionDisable) {
                    this.f8990o = typedArray.getBoolean(index, this.f8990o);
                } else if (index == R$styleable.Transition_pathMotionArc) {
                    this.f8991p = typedArray.getInteger(index, -1);
                } else if (index == R$styleable.Transition_layoutDuringTransition) {
                    this.f8992q = typedArray.getInteger(index, 0);
                } else if (index == R$styleable.Transition_transitionFlags) {
                    this.f8993r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f8979d == -1) {
                this.f8977b = true;
            }
        }

        public final void w(q qVar, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transition);
            v(qVar, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int x() {
            return this.f8983h;
        }

        public int y() {
            return this.f8978c;
        }

        public int z() {
            return this.f8992q;
        }

        public b(q qVar, Context context, XmlPullParser xmlPullParser) {
            this.f8976a = -1;
            this.f8977b = false;
            this.f8978c = -1;
            this.f8979d = -1;
            this.f8980e = 0;
            this.f8981f = null;
            this.f8982g = -1;
            this.f8983h = 400;
            this.f8984i = 0.0f;
            this.f8986k = new ArrayList<>();
            this.f8987l = null;
            this.f8988m = new ArrayList<>();
            this.f8989n = 0;
            this.f8990o = false;
            this.f8991p = -1;
            this.f8992q = 0;
            this.f8993r = 0;
            this.f8983h = qVar.f8966l;
            this.f8992q = qVar.f8967m;
            this.f8985j = qVar;
            w(qVar, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
