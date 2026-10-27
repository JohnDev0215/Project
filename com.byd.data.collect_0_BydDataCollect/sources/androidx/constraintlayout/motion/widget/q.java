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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: MotionScene.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MotionLayout f909a;
    private MotionEvent n;
    private MotionLayout.f p;
    private boolean q;
    float r;
    float s;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    androidx.constraintlayout.widget.g f910b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b f911c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f912d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<b> f913e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f914f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<b> f915g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private SparseArray<androidx.constraintlayout.widget.c> f916h = new SparseArray<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private HashMap<String, Integer> f917i = new HashMap<>();
    private SparseIntArray j = new SparseIntArray();
    private boolean k = false;
    private int l = 400;
    private int m = 0;
    private boolean o = false;

    /* JADX INFO: compiled from: MotionScene.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.e.a.a.c f918a;

        a(b.e.a.a.c cVar) {
            this.f918a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return (float) this.f918a.a(f2);
        }
    }

    q(Context context, MotionLayout motionLayout, int i2) {
        this.f909a = motionLayout;
        C(context, i2);
        SparseArray<androidx.constraintlayout.widget.c> sparseArray = this.f916h;
        int i3 = androidx.constraintlayout.widget.e.motion_base;
        sparseArray.put(i3, new androidx.constraintlayout.widget.c());
        this.f917i.put("motion_base", Integer.valueOf(i3));
    }

    private boolean A(int i2) {
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

    private boolean B() {
        return this.p != null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void C(Context context, int i2) {
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
                            ArrayList<b> arrayList = this.f913e;
                            b bVar2 = new b(this, context, xml);
                            arrayList.add(bVar2);
                            if (this.f911c == null && !bVar2.f921b) {
                                this.f911c = bVar2;
                                if (bVar2.l != null) {
                                    this.f911c.l.p(this.q);
                                }
                            }
                            if (bVar2.f921b) {
                                if (bVar2.f922c == -1) {
                                    this.f914f = bVar2;
                                } else {
                                    this.f915g.add(bVar2);
                                }
                                this.f913e.remove(bVar2);
                            }
                            bVar = bVar2;
                            break;
                        case 2:
                            if (bVar == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i2) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            bVar.l = new t(context, this.f909a, xml);
                            break;
                        case 3:
                            bVar.t(context, xml);
                            break;
                        case 4:
                            this.f910b = new androidx.constraintlayout.widget.g(context, xml);
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
        androidx.constraintlayout.widget.c cVar = new androidx.constraintlayout.widget.c();
        cVar.A(false);
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
                this.f917i.put(P(attributeValue), Integer.valueOf(iO));
            }
        }
        if (iO != -1) {
            if (this.f909a.V != 0) {
                cVar.B(true);
            }
            cVar.v(context, xmlPullParser);
            if (iO2 != -1) {
                this.j.put(iO, iO2);
            }
            this.f916h.put(iO, cVar);
        }
    }

    private void E(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.f.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == androidx.constraintlayout.widget.f.MotionScene_defaultDuration) {
                this.l = typedArrayObtainStyledAttributes.getInt(index, this.l);
            } else if (index == androidx.constraintlayout.widget.f.MotionScene_layoutDuringTransition) {
                this.m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void I(int i2) {
        int i3 = this.j.get(i2);
        if (i3 > 0) {
            I(this.j.get(i2));
            androidx.constraintlayout.widget.c cVar = this.f916h.get(i2);
            androidx.constraintlayout.widget.c cVar2 = this.f916h.get(i3);
            if (cVar2 != null) {
                cVar.z(cVar2);
                this.j.put(i2, -1);
            } else {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + androidx.constraintlayout.motion.widget.a.b(this.f909a.getContext(), i3));
            }
        }
    }

    public static String P(String str) {
        if (str == null) {
            return "";
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

    private int v(int i2) {
        int iC;
        androidx.constraintlayout.widget.g gVar = this.f910b;
        return (gVar == null || (iC = gVar.c(i2, -1, -1)) == -1) ? i2 : iC;
    }

    void F(float f2, float f3) {
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f911c.l.m(f2, f3);
    }

    void G(float f2, float f3) {
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f911c.l.n(f2, f3);
    }

    void H(MotionEvent motionEvent, int i2, MotionLayout motionLayout) {
        MotionLayout.f fVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.p == null) {
            this.p = this.f909a.j0();
        }
        this.p.a(motionEvent);
        if (i2 != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.r = motionEvent.getRawX();
                this.s = motionEvent.getRawY();
                this.n = motionEvent;
                if (this.f911c.l != null) {
                    RectF rectFE = this.f911c.l.e(this.f909a, rectF);
                    if (rectFE != null && !rectFE.contains(this.n.getX(), this.n.getY())) {
                        this.n = null;
                        return;
                    }
                    RectF rectFJ = this.f911c.l.j(this.f909a, rectF);
                    if (rectFJ == null || rectFJ.contains(this.n.getX(), this.n.getY())) {
                        this.o = false;
                    } else {
                        this.o = true;
                    }
                    this.f911c.l.o(this.r, this.s);
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
                    RectF rectFJ2 = this.f911c.l.j(this.f909a, rectF);
                    if (rectFJ2 != null && !rectFJ2.contains(this.n.getX(), this.n.getY())) {
                        z = true;
                    }
                    this.o = z;
                    this.f911c.l.q(this.r, this.s);
                }
            }
        }
        b bVar = this.f911c;
        if (bVar != null && bVar.l != null && !this.o) {
            this.f911c.l.l(motionEvent, this.p, i2, this);
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

    void J(MotionLayout motionLayout) {
        for (int i2 = 0; i2 < this.f916h.size(); i2++) {
            int iKeyAt = this.f916h.keyAt(i2);
            if (A(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            I(iKeyAt);
        }
        for (int i3 = 0; i3 < this.f916h.size(); i3++) {
            this.f916h.valueAt(i3).y(motionLayout);
        }
    }

    public void K(int i2) {
        b bVar = this.f911c;
        if (bVar != null) {
            bVar.E(i2);
        } else {
            this.l = i2;
        }
    }

    public void L(boolean z) {
        this.q = z;
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f911c.l.p(this.q);
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
            androidx.constraintlayout.widget.g r0 = r6.f910b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            androidx.constraintlayout.widget.g r2 = r6.f910b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.f913e
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
            r6.f911c = r4
            if (r4 == 0) goto L57
            androidx.constraintlayout.motion.widget.t r7 = androidx.constraintlayout.motion.widget.q.b.m(r4)
            if (r7 == 0) goto L57
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f911c
            androidx.constraintlayout.motion.widget.t r7 = androidx.constraintlayout.motion.widget.q.b.m(r7)
            boolean r8 = r6.q
            r7.p(r8)
        L57:
            return
        L58:
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f914f
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.f915g
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
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r7 = r6.f913e
            r7.add(r8)
        L86:
            r6.f911c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.q.M(int, int):void");
    }

    public void N(b bVar) {
        this.f911c = bVar;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f911c.l.p(this.q);
    }

    void O() {
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.f911c.l.r();
    }

    boolean Q() {
        Iterator<b> it = this.f913e.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        b bVar = this.f911c;
        return (bVar == null || bVar.l == null) ? false : true;
    }

    public void e(MotionLayout motionLayout, int i2) {
        for (b bVar : this.f913e) {
            if (bVar.m.size() > 0) {
                Iterator it = bVar.m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).c(motionLayout);
                }
            }
        }
        for (b bVar2 : this.f915g) {
            if (bVar2.m.size() > 0) {
                Iterator it2 = bVar2.m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).c(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f913e) {
            if (bVar3.m.size() > 0) {
                Iterator it3 = bVar3.m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i2, bVar3);
                }
            }
        }
        for (b bVar4 : this.f915g) {
            if (bVar4.m.size() > 0) {
                Iterator it4 = bVar4.m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i2, bVar4);
                }
            }
        }
    }

    boolean f(MotionLayout motionLayout, int i2) {
        if (B() || this.f912d) {
            return false;
        }
        for (b bVar : this.f913e) {
            if (bVar.n != 0) {
                if (i2 == bVar.f923d && (bVar.n == 4 || bVar.n == 2)) {
                    MotionLayout.j jVar = MotionLayout.j.FINISHED;
                    motionLayout.setState(jVar);
                    motionLayout.setTransition(bVar);
                    if (bVar.n == 4) {
                        motionLayout.s0();
                        motionLayout.setState(MotionLayout.j.SETUP);
                        motionLayout.setState(MotionLayout.j.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.Y(true);
                        motionLayout.setState(MotionLayout.j.SETUP);
                        motionLayout.setState(MotionLayout.j.MOVING);
                        motionLayout.setState(jVar);
                    }
                    return true;
                }
                if (i2 == bVar.f922c && (bVar.n == 3 || bVar.n == 1)) {
                    MotionLayout.j jVar2 = MotionLayout.j.FINISHED;
                    motionLayout.setState(jVar2);
                    motionLayout.setTransition(bVar);
                    if (bVar.n == 3) {
                        motionLayout.t0();
                        motionLayout.setState(MotionLayout.j.SETUP);
                        motionLayout.setState(MotionLayout.j.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.Y(true);
                        motionLayout.setState(MotionLayout.j.SETUP);
                        motionLayout.setState(MotionLayout.j.MOVING);
                        motionLayout.setState(jVar2);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public b g(int i2, float f2, float f3, MotionEvent motionEvent) {
        if (i2 == -1) {
            return this.f911c;
        }
        List<b> listZ = z(i2);
        float f4 = 0.0f;
        b bVar = null;
        RectF rectF = new RectF();
        for (b bVar2 : listZ) {
            if (!bVar2.o && bVar2.l != null) {
                bVar2.l.p(this.q);
                RectF rectFJ = bVar2.l.j(this.f909a, rectF);
                if (rectFJ == null || motionEvent == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF rectFJ2 = bVar2.l.j(this.f909a, rectF);
                    if (rectFJ2 == null || motionEvent == null || rectFJ2.contains(motionEvent.getX(), motionEvent.getY())) {
                        float fA = bVar2.l.a(f2, f3) * (bVar2.f922c == i2 ? -1.0f : 1.1f);
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
        b bVar = this.f911c;
        if (bVar != null) {
            return bVar.p;
        }
        return -1;
    }

    androidx.constraintlayout.widget.c i(int i2) {
        return j(i2, -1, -1);
    }

    androidx.constraintlayout.widget.c j(int i2, int i3, int i4) {
        int iC;
        if (this.k) {
            System.out.println("id " + i2);
            System.out.println("size " + this.f916h.size());
        }
        androidx.constraintlayout.widget.g gVar = this.f910b;
        if (gVar != null && (iC = gVar.c(i2, i3, i4)) != -1) {
            i2 = iC;
        }
        if (this.f916h.get(i2) != null) {
            return this.f916h.get(i2);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + androidx.constraintlayout.motion.widget.a.b(this.f909a.getContext(), i2) + " In MotionScene");
        SparseArray<androidx.constraintlayout.widget.c> sparseArray = this.f916h;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] k() {
        int size = this.f916h.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.f916h.keyAt(i2);
        }
        return iArr;
    }

    public ArrayList<b> l() {
        return this.f913e;
    }

    public int m() {
        b bVar = this.f911c;
        return bVar != null ? bVar.f927h : this.l;
    }

    int n() {
        b bVar = this.f911c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f922c;
    }

    public Interpolator p() {
        int i2 = this.f911c.f924e;
        if (i2 == -2) {
            return AnimationUtils.loadInterpolator(this.f909a.getContext(), this.f911c.f926g);
        }
        if (i2 == -1) {
            return new a(b.e.a.a.c.c(this.f911c.f925f));
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
        b bVar = this.f911c;
        if (bVar != null) {
            Iterator it = bVar.k.iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(nVar);
            }
        } else {
            b bVar2 = this.f914f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.k.iterator();
                while (it2.hasNext()) {
                    ((h) it2.next()).a(nVar);
                }
            }
        }
    }

    float r() {
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f911c.l.f();
    }

    float s() {
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f911c.l.g();
    }

    boolean t() {
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return false;
        }
        return this.f911c.l.h();
    }

    float u(float f2, float f3) {
        b bVar = this.f911c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.f911c.l.i(f2, f3);
    }

    public float w() {
        b bVar = this.f911c;
        if (bVar != null) {
            return bVar.f928i;
        }
        return 0.0f;
    }

    int x() {
        b bVar = this.f911c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f923d;
    }

    public b y(int i2) {
        for (b bVar : this.f913e) {
            if (bVar.f920a == i2) {
                return bVar;
            }
        }
        return null;
    }

    public List<b> z(int i2) {
        int iV = v(i2);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f913e) {
            if (bVar.f923d == iV || bVar.f922c == iV) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f920a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f921b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f922c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f923d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f924e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f925f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f926g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f927h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private float f928i;
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
            private final b f929b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f930c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int f931d;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.f930c = -1;
                this.f931d = 17;
                this.f929b = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.f.OnClick);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i2 = 0; i2 < indexCount; i2++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i2);
                    if (index == androidx.constraintlayout.widget.f.OnClick_targetId) {
                        this.f930c = typedArrayObtainStyledAttributes.getResourceId(index, this.f930c);
                    } else if (index == androidx.constraintlayout.widget.f.OnClick_clickAction) {
                        this.f931d = typedArrayObtainStyledAttributes.getInt(index, this.f931d);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i2, b bVar) {
                int i3 = this.f930c;
                View viewFindViewById = motionLayout;
                if (i3 != -1) {
                    viewFindViewById = motionLayout.findViewById(i3);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f930c);
                    return;
                }
                int i4 = bVar.f923d;
                int i5 = bVar.f922c;
                if (i4 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i6 = this.f931d;
                boolean z = false;
                boolean z2 = ((i6 & 1) != 0 && i2 == i4) | ((i6 & 1) != 0 && i2 == i4) | ((i6 & 256) != 0 && i2 == i4) | ((i6 & 16) != 0 && i2 == i5);
                if ((i6 & 4096) != 0 && i2 == i5) {
                    z = true;
                }
                if (z2 || z) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            boolean b(b bVar, MotionLayout motionLayout) {
                b bVar2 = this.f929b;
                if (bVar2 == bVar) {
                    return true;
                }
                int i2 = bVar2.f922c;
                int i3 = this.f929b.f923d;
                if (i3 == -1) {
                    return motionLayout.D != i2;
                }
                int i4 = motionLayout.D;
                return i4 == i3 || i4 == i2;
            }

            public void c(MotionLayout motionLayout) {
                int i2 = this.f930c;
                if (i2 == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i2);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f930c);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f929b.j.f909a;
                if (motionLayout.i0()) {
                    if (this.f929b.f923d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.u0(this.f929b.f922c);
                            return;
                        }
                        b bVar = new b(this.f929b.j, this.f929b);
                        bVar.f923d = currentState;
                        bVar.f922c = this.f929b.f922c;
                        motionLayout.setTransition(bVar);
                        motionLayout.s0();
                        return;
                    }
                    b bVar2 = this.f929b.j.f911c;
                    int i2 = this.f931d;
                    boolean z = false;
                    boolean z2 = ((i2 & 1) == 0 && (i2 & 256) == 0) ? false : true;
                    boolean z3 = ((i2 & 16) == 0 && (i2 & 4096) == 0) ? false : true;
                    if (z2 && z3) {
                        b bVar3 = this.f929b.j.f911c;
                        b bVar4 = this.f929b;
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
                        if (z && (this.f931d & 1) != 0) {
                            motionLayout.setTransition(this.f929b);
                            motionLayout.s0();
                            return;
                        }
                        if (z3 && (this.f931d & 16) != 0) {
                            motionLayout.setTransition(this.f929b);
                            motionLayout.t0();
                        } else if (z && (this.f931d & 256) != 0) {
                            motionLayout.setTransition(this.f929b);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z3 || (this.f931d & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f929b);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        b(q qVar, b bVar) {
            this.f920a = -1;
            this.f921b = false;
            this.f922c = -1;
            this.f923d = -1;
            this.f924e = 0;
            this.f925f = null;
            this.f926g = -1;
            this.f927h = 400;
            this.f928i = 0.0f;
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
                this.f924e = bVar.f924e;
                this.f925f = bVar.f925f;
                this.f926g = bVar.f926g;
                this.f927h = bVar.f927h;
                this.k = bVar.k;
                this.f928i = bVar.f928i;
                this.q = bVar.q;
            }
        }

        private void v(q qVar, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                if (index == androidx.constraintlayout.widget.f.Transition_constraintSetEnd) {
                    this.f922c = typedArray.getResourceId(index, this.f922c);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f922c))) {
                        androidx.constraintlayout.widget.c cVar = new androidx.constraintlayout.widget.c();
                        cVar.u(context, this.f922c);
                        qVar.f916h.append(this.f922c, cVar);
                    }
                } else if (index == androidx.constraintlayout.widget.f.Transition_constraintSetStart) {
                    this.f923d = typedArray.getResourceId(index, this.f923d);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f923d))) {
                        androidx.constraintlayout.widget.c cVar2 = new androidx.constraintlayout.widget.c();
                        cVar2.u(context, this.f923d);
                        qVar.f916h.append(this.f923d, cVar2);
                    }
                } else if (index == androidx.constraintlayout.widget.f.Transition_motionInterpolator) {
                    int i3 = typedArray.peekValue(index).type;
                    if (i3 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.f926g = resourceId;
                        if (resourceId != -1) {
                            this.f924e = -2;
                        }
                    } else if (i3 == 3) {
                        String string = typedArray.getString(index);
                        this.f925f = string;
                        if (string.indexOf("/") > 0) {
                            this.f926g = typedArray.getResourceId(index, -1);
                            this.f924e = -2;
                        } else {
                            this.f924e = -1;
                        }
                    } else {
                        this.f924e = typedArray.getInteger(index, this.f924e);
                    }
                } else if (index == androidx.constraintlayout.widget.f.Transition_duration) {
                    this.f927h = typedArray.getInt(index, this.f927h);
                } else if (index == androidx.constraintlayout.widget.f.Transition_staggered) {
                    this.f928i = typedArray.getFloat(index, this.f928i);
                } else if (index == androidx.constraintlayout.widget.f.Transition_autoTransition) {
                    this.n = typedArray.getInteger(index, this.n);
                } else if (index == androidx.constraintlayout.widget.f.Transition_android_id) {
                    this.f920a = typedArray.getResourceId(index, this.f920a);
                } else if (index == androidx.constraintlayout.widget.f.Transition_transitionDisable) {
                    this.o = typedArray.getBoolean(index, this.o);
                } else if (index == androidx.constraintlayout.widget.f.Transition_pathMotionArc) {
                    this.p = typedArray.getInteger(index, -1);
                } else if (index == androidx.constraintlayout.widget.f.Transition_layoutDuringTransition) {
                    this.q = typedArray.getInteger(index, 0);
                } else if (index == androidx.constraintlayout.widget.f.Transition_transitionFlags) {
                    this.r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f923d == -1) {
                this.f921b = true;
            }
        }

        private void w(q qVar, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.f.Transition);
            v(qVar, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int A() {
            return this.f923d;
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
            this.f927h = i2;
        }

        public void t(Context context, XmlPullParser xmlPullParser) {
            this.m.add(new a(context, this, xmlPullParser));
        }

        public String u(Context context) {
            String resourceEntryName = this.f923d == -1 ? "null" : context.getResources().getResourceEntryName(this.f923d);
            if (this.f922c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.f922c);
        }

        public int x() {
            return this.f927h;
        }

        public int y() {
            return this.f922c;
        }

        public int z() {
            return this.q;
        }

        b(q qVar, Context context, XmlPullParser xmlPullParser) {
            this.f920a = -1;
            this.f921b = false;
            this.f922c = -1;
            this.f923d = -1;
            this.f924e = 0;
            this.f925f = null;
            this.f926g = -1;
            this.f927h = 400;
            this.f928i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.f927h = qVar.l;
            this.q = qVar.m;
            this.j = qVar;
            w(qVar, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
