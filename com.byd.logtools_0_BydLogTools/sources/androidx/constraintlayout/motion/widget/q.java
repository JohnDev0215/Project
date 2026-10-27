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
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.HttpUrl;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: MotionScene.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MotionLayout f857a;
    private MotionEvent n;
    private MotionLayout.f p;
    private boolean q;
    float r;
    float s;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    androidx.constraintlayout.widget.f f858b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b f859c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f860d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<b> f861e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f862f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<b> f863g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private SparseArray<androidx.constraintlayout.widget.b> f864h = new SparseArray<>();
    private HashMap<String, Integer> i = new HashMap<>();
    private SparseIntArray j = new SparseIntArray();
    private boolean k = false;
    private int l = 400;
    private int m = 0;
    private boolean o = false;

    /* JADX INFO: compiled from: MotionScene.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.e.a.a.c f865a;

        a(b.e.a.a.c cVar) {
            this.f865a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return (float) this.f865a.a(f2);
        }
    }

    q(Context context, MotionLayout motionLayout, int i) {
        this.f857a = motionLayout;
        C(context, i);
        SparseArray<androidx.constraintlayout.widget.b> sparseArray = this.f864h;
        int i2 = androidx.constraintlayout.widget.d.f1174a;
        sparseArray.put(i2, new androidx.constraintlayout.widget.b());
        this.i.put("motion_base", Integer.valueOf(i2));
    }

    private boolean A(int i) {
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

    private boolean B() {
        return this.p != null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void C(Context context, int i) {
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
                            ArrayList<b> arrayList = this.f861e;
                            b bVar2 = new b(this, context, xml);
                            arrayList.add(bVar2);
                            if (this.f859c == null && !bVar2.f868b) {
                                this.f859c = bVar2;
                                if (bVar2.l != null) {
                                    this.f859c.l.p(this.q);
                                }
                            }
                            if (bVar2.f868b) {
                                if (bVar2.f869c == -1) {
                                    this.f862f = bVar2;
                                } else {
                                    this.f863g.add(bVar2);
                                }
                                this.f861e.remove(bVar2);
                            }
                            bVar = bVar2;
                            break;
                        case 2:
                            if (bVar == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            bVar.l = new t(context, this.f857a, xml);
                            break;
                        case 3:
                            bVar.t(context, xml);
                            break;
                        case 4:
                            this.f858b = new androidx.constraintlayout.widget.f(context, xml);
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

    private void D(Context context, XmlPullParser xmlPullParser) {
        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
        bVar.A(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int iO = -1;
        int iO2 = -1;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (this.k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            if (attributeName.equals("deriveConstraintsFrom")) {
                iO2 = o(context, attributeValue);
            } else if (attributeName.equals("id")) {
                iO = o(context, attributeValue);
                this.i.put(P(attributeValue), Integer.valueOf(iO));
            }
        }
        if (iO != -1) {
            if (this.f857a.V != 0) {
                bVar.B(true);
            }
            bVar.v(context, xmlPullParser);
            if (iO2 != -1) {
                this.j.put(iO, iO2);
            }
            this.f864h.put(iO, bVar);
        }
    }

    private void E(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.e.O6);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == androidx.constraintlayout.widget.e.P6) {
                this.l = typedArrayObtainStyledAttributes.getInt(index, this.l);
            } else if (index == androidx.constraintlayout.widget.e.Q6) {
                this.m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void I(int i) {
        int i2 = this.j.get(i);
        if (i2 > 0) {
            I(this.j.get(i));
            androidx.constraintlayout.widget.b bVar = this.f864h.get(i);
            androidx.constraintlayout.widget.b bVar2 = this.f864h.get(i2);
            if (bVar2 != null) {
                bVar.z(bVar2);
                this.j.put(i, -1);
            } else {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + androidx.constraintlayout.motion.widget.a.b(this.f857a.getContext(), i2));
            }
        }
    }

    public static String P(String str) {
        if (str == null) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        int iIndexOf = str.indexOf(47);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }

    private int o(Context context, String str) {
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

    private int v(int i) {
        int iC;
        androidx.constraintlayout.widget.f fVar = this.f858b;
        return (fVar == null || (iC = fVar.c(i, -1, -1)) == -1) ? i : iC;
    }

    void F(float f2, float f3) {
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f859c.l.m(f2, f3);
    }

    void G(float f2, float f3) {
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f859c.l.n(f2, f3);
    }

    void H(MotionEvent motionEvent, int i, MotionLayout motionLayout) {
        MotionLayout.f fVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.p == null) {
            this.p = this.f857a.j0();
        }
        this.p.a(motionEvent);
        if (i != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.r = motionEvent.getRawX();
                this.s = motionEvent.getRawY();
                this.n = motionEvent;
                if (this.f859c.l != null) {
                    RectF rectFE = this.f859c.l.e(this.f857a, rectF);
                    if (rectFE != null && !rectFE.contains(this.n.getX(), this.n.getY())) {
                        this.n = null;
                        return;
                    }
                    RectF rectFJ = this.f859c.l.j(this.f857a, rectF);
                    if (rectFJ == null || rectFJ.contains(this.n.getX(), this.n.getY())) {
                        this.o = false;
                    } else {
                        this.o = true;
                    }
                    this.f859c.l.o(this.r, this.s);
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
                b bVarG = g(i, rawX, rawY, motionEvent2);
                if (bVarG != null) {
                    motionLayout.setTransition(bVarG);
                    RectF rectFJ2 = this.f859c.l.j(this.f857a, rectF);
                    if (rectFJ2 != null && !rectFJ2.contains(this.n.getX(), this.n.getY())) {
                        z = true;
                    }
                    this.o = z;
                    this.f859c.l.q(this.r, this.s);
                }
            }
        }
        b bVar = this.f859c;
        if (bVar != null && bVar.l != null && !this.o) {
            this.f859c.l.l(motionEvent, this.p, i, this);
        }
        this.r = motionEvent.getRawX();
        this.s = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (fVar = this.p) == null) {
            return;
        }
        fVar.d();
        this.p = null;
        int i2 = motionLayout.D;
        if (i2 != -1) {
            f(motionLayout, i2);
        }
    }

    void J(MotionLayout motionLayout) {
        for (int i = 0; i < this.f864h.size(); i++) {
            int iKeyAt = this.f864h.keyAt(i);
            if (A(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            I(iKeyAt);
        }
        for (int i2 = 0; i2 < this.f864h.size(); i2++) {
            this.f864h.valueAt(i2).y(motionLayout);
        }
    }

    public void K(int i) {
        b bVar = this.f859c;
        if (bVar != null) {
            bVar.E(i);
        } else {
            this.l = i;
        }
    }

    public void L(boolean z) {
        this.q = z;
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f859c.l.p(this.q);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void M(int r7, int r8) {
        /*
            r6 = this;
            androidx.constraintlayout.widget.f r0 = r6.f858b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            androidx.constraintlayout.widget.f r2 = r6.f858b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.f861e
            java.util.Iterator r3 = r3.iterator()
        L1e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.q$b r4 = (androidx.constraintlayout.motion.widget.q.b) r4
            int r5 = androidx.constraintlayout.motion.widget.q.b.a(r4)
            if (r5 != r2) goto L36
            int r5 = androidx.constraintlayout.motion.widget.q.b.c(r4)
            if (r5 == r0) goto L42
        L36:
            int r5 = androidx.constraintlayout.motion.widget.q.b.a(r4)
            if (r5 != r8) goto L1e
            int r5 = androidx.constraintlayout.motion.widget.q.b.c(r4)
            if (r5 != r7) goto L1e
        L42:
            r6.f859c = r4
            if (r4 == 0) goto L57
            androidx.constraintlayout.motion.widget.t r7 = androidx.constraintlayout.motion.widget.q.b.m(r4)
            if (r7 == 0) goto L57
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f859c
            androidx.constraintlayout.motion.widget.t r7 = androidx.constraintlayout.motion.widget.q.b.m(r7)
            boolean r8 = r6.q
            r7.p(r8)
        L57:
            return
        L58:
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f862f
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.f863g
            java.util.Iterator r3 = r3.iterator()
        L60:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L74
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.q$b r4 = (androidx.constraintlayout.motion.widget.q.b) r4
            int r5 = androidx.constraintlayout.motion.widget.q.b.a(r4)
            if (r5 != r8) goto L60
            r7 = r4
            goto L60
        L74:
            androidx.constraintlayout.motion.widget.q$b r8 = new androidx.constraintlayout.motion.widget.q$b
            r8.<init>(r6, r7)
            androidx.constraintlayout.motion.widget.q.b.d(r8, r0)
            androidx.constraintlayout.motion.widget.q.b.b(r8, r2)
            if (r0 == r1) goto L86
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r7 = r6.f861e
            r7.add(r8)
        L86:
            r6.f859c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.q.M(int, int):void");
    }

    public void N(b bVar) {
        this.f859c = bVar;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f859c.l.p(this.q);
    }

    void O() {
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f859c.l.r();
    }

    boolean Q() {
        Iterator<b> it = this.f861e.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        b bVar = this.f859c;
        return (bVar == null || bVar.l == null) ? false : true;
    }

    public void e(MotionLayout motionLayout, int i) {
        for (b bVar : this.f861e) {
            if (bVar.m.size() > 0) {
                Iterator it = bVar.m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).c(motionLayout);
                }
            }
        }
        for (b bVar2 : this.f863g) {
            if (bVar2.m.size() > 0) {
                Iterator it2 = bVar2.m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).c(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f861e) {
            if (bVar3.m.size() > 0) {
                Iterator it3 = bVar3.m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i, bVar3);
                }
            }
        }
        for (b bVar4 : this.f863g) {
            if (bVar4.m.size() > 0) {
                Iterator it4 = bVar4.m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i, bVar4);
                }
            }
        }
    }

    boolean f(MotionLayout motionLayout, int i) {
        if (B() || this.f860d) {
            return false;
        }
        for (b bVar : this.f861e) {
            if (bVar.n != 0) {
                if (i == bVar.f870d && (bVar.n == 4 || bVar.n == 2)) {
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
                if (i == bVar.f869c && (bVar.n == 3 || bVar.n == 1)) {
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

    public b g(int i, float f2, float f3, MotionEvent motionEvent) {
        if (i == -1) {
            return this.f859c;
        }
        List<b> listZ = z(i);
        float f4 = 0.0f;
        b bVar = null;
        RectF rectF = new RectF();
        for (b bVar2 : listZ) {
            if (!bVar2.o && bVar2.l != null) {
                bVar2.l.p(this.q);
                RectF rectFJ = bVar2.l.j(this.f857a, rectF);
                if (rectFJ == null || motionEvent == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF rectFJ2 = bVar2.l.j(this.f857a, rectF);
                    if (rectFJ2 == null || motionEvent == null || rectFJ2.contains(motionEvent.getX(), motionEvent.getY())) {
                        float fA = bVar2.l.a(f2, f3) * (bVar2.f869c == i ? -1.0f : 1.1f);
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
        b bVar = this.f859c;
        if (bVar != null) {
            return bVar.p;
        }
        return -1;
    }

    androidx.constraintlayout.widget.b i(int i) {
        return j(i, -1, -1);
    }

    androidx.constraintlayout.widget.b j(int i, int i2, int i3) {
        int iC;
        if (this.k) {
            System.out.println("id " + i);
            System.out.println("size " + this.f864h.size());
        }
        androidx.constraintlayout.widget.f fVar = this.f858b;
        if (fVar != null && (iC = fVar.c(i, i2, i3)) != -1) {
            i = iC;
        }
        if (this.f864h.get(i) != null) {
            return this.f864h.get(i);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + androidx.constraintlayout.motion.widget.a.b(this.f857a.getContext(), i) + " In MotionScene");
        SparseArray<androidx.constraintlayout.widget.b> sparseArray = this.f864h;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] k() {
        int size = this.f864h.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.f864h.keyAt(i);
        }
        return iArr;
    }

    public ArrayList<b> l() {
        return this.f861e;
    }

    public int m() {
        b bVar = this.f859c;
        return bVar != null ? bVar.f874h : this.l;
    }

    int n() {
        b bVar = this.f859c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f869c;
    }

    public Interpolator p() {
        int i = this.f859c.f871e;
        if (i == -2) {
            return AnimationUtils.loadInterpolator(this.f857a.getContext(), this.f859c.f873g);
        }
        if (i == -1) {
            return new a(b.e.a.a.c.c(this.f859c.f872f));
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
            return new AnticipateInterpolator();
        }
        if (i != 5) {
            return null;
        }
        return new BounceInterpolator();
    }

    public void q(n nVar) {
        b bVar = this.f859c;
        if (bVar != null) {
            Iterator it = bVar.k.iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(nVar);
            }
        } else {
            b bVar2 = this.f862f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.k.iterator();
                while (it2.hasNext()) {
                    ((h) it2.next()).a(nVar);
                }
            }
        }
    }

    float r() {
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f859c.l.f();
    }

    float s() {
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f859c.l.g();
    }

    boolean t() {
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return false;
        }
        return this.f859c.l.h();
    }

    float u(float f2, float f3) {
        b bVar = this.f859c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f859c.l.i(f2, f3);
    }

    public float w() {
        b bVar = this.f859c;
        if (bVar != null) {
            return bVar.i;
        }
        return 0.0f;
    }

    int x() {
        b bVar = this.f859c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f870d;
    }

    public b y(int i) {
        for (b bVar : this.f861e) {
            if (bVar.f867a == i) {
                return bVar;
            }
        }
        return null;
    }

    public List<b> z(int i) {
        int iV = v(i);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f861e) {
            if (bVar.f870d == iV || bVar.f869c == iV) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f867a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f868b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f869c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f870d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f871e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f872f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f873g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f874h;
        private float i;
        private final q j;
        private ArrayList<h> k;
        private t l;
        private ArrayList<a> m;
        private int n;
        private boolean o;
        private int p;
        private int q;
        private int r;

        /* JADX INFO: compiled from: MotionScene.java */
        static class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final b f875b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f876c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int f877d;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.f876c = -1;
                this.f877d = 17;
                this.f875b = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.e.V6);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i);
                    if (index == androidx.constraintlayout.widget.e.X6) {
                        this.f876c = typedArrayObtainStyledAttributes.getResourceId(index, this.f876c);
                    } else if (index == androidx.constraintlayout.widget.e.W6) {
                        this.f877d = typedArrayObtainStyledAttributes.getInt(index, this.f877d);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i, b bVar) {
                int i2 = this.f876c;
                View viewFindViewById = motionLayout;
                if (i2 != -1) {
                    viewFindViewById = motionLayout.findViewById(i2);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f876c);
                    return;
                }
                int i3 = bVar.f870d;
                int i4 = bVar.f869c;
                if (i3 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i5 = this.f877d;
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
                b bVar2 = this.f875b;
                if (bVar2 == bVar) {
                    return true;
                }
                int i = bVar2.f869c;
                int i2 = this.f875b.f870d;
                if (i2 == -1) {
                    return motionLayout.D != i;
                }
                int i3 = motionLayout.D;
                return i3 == i2 || i3 == i;
            }

            public void c(MotionLayout motionLayout) {
                int i = this.f876c;
                if (i == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f876c);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f875b.j.f857a;
                if (motionLayout.i0()) {
                    if (this.f875b.f870d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.u0(this.f875b.f869c);
                            return;
                        }
                        b bVar = new b(this.f875b.j, this.f875b);
                        bVar.f870d = currentState;
                        bVar.f869c = this.f875b.f869c;
                        motionLayout.setTransition(bVar);
                        motionLayout.s0();
                        return;
                    }
                    b bVar2 = this.f875b.j.f859c;
                    int i = this.f877d;
                    boolean z = false;
                    boolean z2 = ((i & 1) == 0 && (i & 256) == 0) ? false : true;
                    boolean z3 = ((i & 16) == 0 && (i & 4096) == 0) ? false : true;
                    if (z2 && z3) {
                        b bVar3 = this.f875b.j.f859c;
                        b bVar4 = this.f875b;
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
                        if (z && (this.f877d & 1) != 0) {
                            motionLayout.setTransition(this.f875b);
                            motionLayout.s0();
                            return;
                        }
                        if (z3 && (this.f877d & 16) != 0) {
                            motionLayout.setTransition(this.f875b);
                            motionLayout.t0();
                        } else if (z && (this.f877d & 256) != 0) {
                            motionLayout.setTransition(this.f875b);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z3 || (this.f877d & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f875b);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        b(q qVar, b bVar) {
            this.f867a = -1;
            this.f868b = false;
            this.f869c = -1;
            this.f870d = -1;
            this.f871e = 0;
            this.f872f = null;
            this.f873g = -1;
            this.f874h = 400;
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
            if (bVar != null) {
                this.p = bVar.p;
                this.f871e = bVar.f871e;
                this.f872f = bVar.f872f;
                this.f873g = bVar.f873g;
                this.f874h = bVar.f874h;
                this.k = bVar.k;
                this.i = bVar.i;
                this.q = bVar.q;
            }
        }

        private void v(q qVar, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.U7) {
                    this.f869c = typedArray.getResourceId(index, this.f869c);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f869c))) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        bVar.u(context, this.f869c);
                        qVar.f864h.append(this.f869c, bVar);
                    }
                } else if (index == androidx.constraintlayout.widget.e.V7) {
                    this.f870d = typedArray.getResourceId(index, this.f870d);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f870d))) {
                        androidx.constraintlayout.widget.b bVar2 = new androidx.constraintlayout.widget.b();
                        bVar2.u(context, this.f870d);
                        qVar.f864h.append(this.f870d, bVar2);
                    }
                } else if (index == androidx.constraintlayout.widget.e.Y7) {
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.f873g = resourceId;
                        if (resourceId != -1) {
                            this.f871e = -2;
                        }
                    } else if (i2 == 3) {
                        String string = typedArray.getString(index);
                        this.f872f = string;
                        if (string.indexOf("/") > 0) {
                            this.f873g = typedArray.getResourceId(index, -1);
                            this.f871e = -2;
                        } else {
                            this.f871e = -1;
                        }
                    } else {
                        this.f871e = typedArray.getInteger(index, this.f871e);
                    }
                } else if (index == androidx.constraintlayout.widget.e.W7) {
                    this.f874h = typedArray.getInt(index, this.f874h);
                } else if (index == androidx.constraintlayout.widget.e.a8) {
                    this.i = typedArray.getFloat(index, this.i);
                } else if (index == androidx.constraintlayout.widget.e.T7) {
                    this.n = typedArray.getInteger(index, this.n);
                } else if (index == androidx.constraintlayout.widget.e.S7) {
                    this.f867a = typedArray.getResourceId(index, this.f867a);
                } else if (index == androidx.constraintlayout.widget.e.b8) {
                    this.o = typedArray.getBoolean(index, this.o);
                } else if (index == androidx.constraintlayout.widget.e.Z7) {
                    this.p = typedArray.getInteger(index, -1);
                } else if (index == androidx.constraintlayout.widget.e.X7) {
                    this.q = typedArray.getInteger(index, 0);
                } else if (index == androidx.constraintlayout.widget.e.c8) {
                    this.r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f870d == -1) {
                this.f868b = true;
            }
        }

        private void w(q qVar, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.R7);
            v(qVar, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int A() {
            return this.f870d;
        }

        public t B() {
            return this.l;
        }

        public boolean C() {
            return !this.o;
        }

        public boolean D(int i) {
            return (i & this.r) != 0;
        }

        public void E(int i) {
            this.f874h = i;
        }

        public void t(Context context, XmlPullParser xmlPullParser) {
            this.m.add(new a(context, this, xmlPullParser));
        }

        public String u(Context context) {
            String resourceEntryName = this.f870d == -1 ? "null" : context.getResources().getResourceEntryName(this.f870d);
            if (this.f869c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.f869c);
        }

        public int x() {
            return this.f874h;
        }

        public int y() {
            return this.f869c;
        }

        public int z() {
            return this.q;
        }

        b(q qVar, Context context, XmlPullParser xmlPullParser) {
            this.f867a = -1;
            this.f868b = false;
            this.f869c = -1;
            this.f870d = -1;
            this.f871e = 0;
            this.f872f = null;
            this.f873g = -1;
            this.f874h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.f874h = qVar.l;
            this.q = qVar.m;
            this.j = qVar;
            w(qVar, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
