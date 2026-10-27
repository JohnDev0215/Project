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
    private final MotionLayout f1522a;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private MotionEvent f1535n;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private MotionLayout.g f1538q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1539r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    final v f1540s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    float f1541t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    float f1542u;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    androidx.constraintlayout.widget.k f1523b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b f1524c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1525d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<b> f1526e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f1527f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<b> f1528g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private SparseArray<androidx.constraintlayout.widget.d> f1529h = new SparseArray<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private HashMap<String, Integer> f1530i = new HashMap<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private SparseIntArray f1531j = new SparseIntArray();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f1532k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1533l = 400;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1534m = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f1536o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f1537p = false;

    /* JADX INFO: compiled from: MotionScene.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p.c f1543a;

        a(p.c cVar) {
            this.f1543a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            return (float) this.f1543a.a(f7);
        }
    }

    q(Context context, MotionLayout motionLayout, int i7) {
        this.f1522a = motionLayout;
        this.f1540s = new v(motionLayout);
        J(context, i7);
        SparseArray<androidx.constraintlayout.widget.d> sparseArray = this.f1529h;
        int i8 = androidx.constraintlayout.widget.h.motion_base;
        sparseArray.put(i8, new androidx.constraintlayout.widget.d());
        this.f1530i.put("motion_base", Integer.valueOf(i8));
    }

    private boolean H(int i7) {
        int i8 = this.f1531j.get(i7);
        int size = this.f1531j.size();
        while (i8 > 0) {
            if (i8 == i7) {
                return true;
            }
            int i9 = size - 1;
            if (size < 0) {
                return true;
            }
            i8 = this.f1531j.get(i8);
            size = i9;
        }
        return false;
    }

    private boolean I() {
        return this.f1538q != null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void J(Context context, int i7) {
        XmlResourceParser xml = context.getResources().getXml(i7);
        b bVar = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                byte b7 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    if (this.f1532k) {
                        System.out.println("parsing = " + name);
                    }
                    switch (name.hashCode()) {
                        case -1349929691:
                            b7 = !name.equals("ConstraintSet") ? (byte) -1 : (byte) 5;
                            break;
                        case -1239391468:
                            b7 = !name.equals("KeyFrameSet") ? (byte) -1 : (byte) 8;
                            break;
                        case -687739768:
                            b7 = !name.equals("Include") ? (byte) -1 : (byte) 7;
                            break;
                        case 61998586:
                            b7 = !name.equals("ViewTransition") ? (byte) -1 : (byte) 9;
                            break;
                        case 269306229:
                            if (!name.equals("Transition")) {
                                b7 = -1;
                            }
                            break;
                        case 312750793:
                            b7 = !name.equals("OnClick") ? (byte) -1 : (byte) 3;
                            break;
                        case 327855227:
                            b7 = !name.equals("OnSwipe") ? (byte) -1 : (byte) 2;
                            break;
                        case 793277014:
                            b7 = !name.equals("MotionScene") ? (byte) -1 : (byte) 0;
                            break;
                        case 1382829617:
                            b7 = !name.equals("StateSet") ? (byte) -1 : (byte) 4;
                            break;
                        case 1942574248:
                            b7 = !name.equals("include") ? (byte) -1 : (byte) 6;
                            break;
                        default:
                            b7 = -1;
                            break;
                    }
                    switch (b7) {
                        case 0:
                            N(context, xml);
                            break;
                        case 1:
                            ArrayList<b> arrayList = this.f1526e;
                            b bVar2 = new b(this, context, xml);
                            arrayList.add(bVar2);
                            if (this.f1524c == null && !bVar2.f1546b) {
                                this.f1524c = bVar2;
                                if (bVar2.f1556l != null) {
                                    this.f1524c.f1556l.x(this.f1539r);
                                }
                            }
                            if (bVar2.f1546b) {
                                if (bVar2.f1547c == -1) {
                                    this.f1527f = bVar2;
                                } else {
                                    this.f1528g.add(bVar2);
                                }
                                this.f1526e.remove(bVar2);
                            }
                            bVar = bVar2;
                            break;
                        case 2:
                            if (bVar == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i7) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            if (bVar != null) {
                                bVar.f1556l = new r(context, this.f1522a, xml);
                            }
                            break;
                        case 3:
                            if (bVar != null) {
                                bVar.u(context, xml);
                            }
                            break;
                        case 4:
                            this.f1523b = new androidx.constraintlayout.widget.k(context, xml);
                            break;
                        case 5:
                            K(context, xml);
                            break;
                        case 6:
                        case 7:
                            M(context, xml);
                            break;
                        case 8:
                            g gVar = new g(context, xml);
                            if (bVar != null) {
                                bVar.f1555k.add(gVar);
                            }
                            break;
                        case 9:
                            this.f1540s.a(new u(context, xml));
                            break;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
    }

    private int K(Context context, XmlPullParser xmlPullParser) {
        String attributeValue;
        androidx.constraintlayout.widget.d dVar = new androidx.constraintlayout.widget.d();
        dVar.R(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int iQ = -1;
        int iQ2 = -1;
        for (int i7 = 0; i7 < attributeCount; i7++) {
            String attributeName = xmlPullParser.getAttributeName(i7);
            attributeValue = xmlPullParser.getAttributeValue(i7);
            if (this.f1532k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            switch (attributeName) {
                case "deriveConstraintsFrom":
                    iQ2 = q(context, attributeValue);
                    break;
                case "constraintRotate":
                    try {
                        dVar.f1771d = Integer.parseInt(attributeValue);
                        break;
                    } catch (NumberFormatException unused) {
                        attributeValue.hashCode();
                        switch (attributeValue) {
                            case "x_left":
                                dVar.f1771d = 4;
                                break;
                            case "left":
                                dVar.f1771d = 2;
                                break;
                            case "none":
                                dVar.f1771d = 0;
                                break;
                            case "right":
                                dVar.f1771d = 1;
                                break;
                            case "x_right":
                                dVar.f1771d = 3;
                                break;
                        }
                    }
                    break;
                case "id":
                    iQ = q(context, attributeValue);
                    this.f1530i.put(Z(attributeValue), Integer.valueOf(iQ));
                    dVar.f1769b = androidx.constraintlayout.motion.widget.a.c(context, iQ);
                    break;
            }
        }
        if (iQ != -1) {
            if (this.f1522a.f1267d0 != 0) {
                dVar.S(true);
            }
            dVar.E(context, xmlPullParser);
            if (iQ2 != -1) {
                this.f1531j.put(iQ, iQ2);
            }
            this.f1529h.put(iQ, dVar);
        }
        return iQ;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int L(Context context, int i7) {
        XmlResourceParser xml = context.getResources().getXml(i7);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && "ConstraintSet".equals(name)) {
                    return K(context, xml);
                }
            }
            return -1;
        } catch (IOException e7) {
            e7.printStackTrace();
            return -1;
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
            return -1;
        }
    }

    private void M(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.i.include);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            if (index == androidx.constraintlayout.widget.i.include_constraintSet) {
                L(context, typedArrayObtainStyledAttributes.getResourceId(index, -1));
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void N(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.i.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            if (index == androidx.constraintlayout.widget.i.MotionScene_defaultDuration) {
                int i8 = typedArrayObtainStyledAttributes.getInt(index, this.f1533l);
                this.f1533l = i8;
                if (i8 < 8) {
                    this.f1533l = 8;
                }
            } else if (index == androidx.constraintlayout.widget.i.MotionScene_layoutDuringTransition) {
                this.f1534m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void R(int i7, MotionLayout motionLayout) {
        androidx.constraintlayout.widget.d dVar = this.f1529h.get(i7);
        dVar.f1770c = dVar.f1769b;
        int i8 = this.f1531j.get(i7);
        if (i8 > 0) {
            R(i8, motionLayout);
            androidx.constraintlayout.widget.d dVar2 = this.f1529h.get(i8);
            if (dVar2 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + androidx.constraintlayout.motion.widget.a.c(this.f1522a.getContext(), i8));
                return;
            }
            dVar.f1770c += "/" + dVar2.f1770c;
            dVar.M(dVar2);
        } else {
            dVar.f1770c += "  layout";
            dVar.L(motionLayout);
        }
        dVar.h(dVar);
    }

    public static String Z(String str) {
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(47);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }

    private int q(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.f1532k) {
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

    private int x(int i7) {
        int iC;
        androidx.constraintlayout.widget.k kVar = this.f1523b;
        return (kVar == null || (iC = kVar.c(i7, -1, -1)) == -1) ? i7 : iC;
    }

    float A() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0.0f;
        }
        return this.f1524c.f1556l.m();
    }

    float B() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0.0f;
        }
        return this.f1524c.f1556l.n();
    }

    float C() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0.0f;
        }
        return this.f1524c.f1556l.o();
    }

    public float D() {
        b bVar = this.f1524c;
        if (bVar != null) {
            return bVar.f1553i;
        }
        return 0.0f;
    }

    int E() {
        b bVar = this.f1524c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f1548d;
    }

    public b F(int i7) {
        for (b bVar : this.f1526e) {
            if (bVar.f1545a == i7) {
                return bVar;
            }
        }
        return null;
    }

    public List<b> G(int i7) {
        int iX = x(i7);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f1526e) {
            if (bVar.f1548d == iX || bVar.f1547c == iX) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    void O(float f7, float f8) {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return;
        }
        this.f1524c.f1556l.u(f7, f8);
    }

    void P(float f7, float f8) {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return;
        }
        this.f1524c.f1556l.v(f7, f8);
    }

    void Q(MotionEvent motionEvent, int i7, MotionLayout motionLayout) {
        MotionLayout.g gVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.f1538q == null) {
            this.f1538q = this.f1522a.s0();
        }
        this.f1538q.b(motionEvent);
        if (i7 != -1) {
            int action = motionEvent.getAction();
            boolean z6 = false;
            if (action == 0) {
                this.f1541t = motionEvent.getRawX();
                this.f1542u = motionEvent.getRawY();
                this.f1535n = motionEvent;
                this.f1536o = false;
                if (this.f1524c.f1556l != null) {
                    RectF rectFF = this.f1524c.f1556l.f(this.f1522a, rectF);
                    if (rectFF != null && !rectFF.contains(this.f1535n.getX(), this.f1535n.getY())) {
                        this.f1535n = null;
                        this.f1536o = true;
                        return;
                    }
                    RectF rectFP = this.f1524c.f1556l.p(this.f1522a, rectF);
                    if (rectFP == null || rectFP.contains(this.f1535n.getX(), this.f1535n.getY())) {
                        this.f1537p = false;
                    } else {
                        this.f1537p = true;
                    }
                    this.f1524c.f1556l.w(this.f1541t, this.f1542u);
                    return;
                }
                return;
            }
            if (action == 2 && !this.f1536o) {
                float rawY = motionEvent.getRawY() - this.f1542u;
                float rawX = motionEvent.getRawX() - this.f1541t;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.f1535n) == null) {
                    return;
                }
                b bVarH = h(i7, rawX, rawY, motionEvent2);
                if (bVarH != null) {
                    motionLayout.setTransition(bVarH);
                    RectF rectFP2 = this.f1524c.f1556l.p(this.f1522a, rectF);
                    if (rectFP2 != null && !rectFP2.contains(this.f1535n.getX(), this.f1535n.getY())) {
                        z6 = true;
                    }
                    this.f1537p = z6;
                    this.f1524c.f1556l.y(this.f1541t, this.f1542u);
                }
            }
        }
        if (this.f1536o) {
            return;
        }
        b bVar = this.f1524c;
        if (bVar != null && bVar.f1556l != null && !this.f1537p) {
            this.f1524c.f1556l.s(motionEvent, this.f1538q, i7, this);
        }
        this.f1541t = motionEvent.getRawX();
        this.f1542u = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (gVar = this.f1538q) == null) {
            return;
        }
        gVar.a();
        this.f1538q = null;
        int i8 = motionLayout.I;
        if (i8 != -1) {
            g(motionLayout, i8);
        }
    }

    void S(MotionLayout motionLayout) {
        for (int i7 = 0; i7 < this.f1529h.size(); i7++) {
            int iKeyAt = this.f1529h.keyAt(i7);
            if (H(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            R(iKeyAt, motionLayout);
        }
    }

    public void T(int i7, androidx.constraintlayout.widget.d dVar) {
        this.f1529h.put(i7, dVar);
    }

    public void U(int i7) {
        b bVar = this.f1524c;
        if (bVar != null) {
            bVar.E(i7);
        } else {
            this.f1533l = i7;
        }
    }

    public void V(boolean z6) {
        this.f1539r = z6;
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return;
        }
        this.f1524c.f1556l.x(this.f1539r);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void W(int r7, int r8) {
        /*
            r6 = this;
            androidx.constraintlayout.widget.k r0 = r6.f1523b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            androidx.constraintlayout.widget.k r2 = r6.f1523b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            androidx.constraintlayout.motion.widget.q$b r3 = r6.f1524c
            if (r3 == 0) goto L2b
            int r3 = androidx.constraintlayout.motion.widget.q.b.a(r3)
            if (r3 != r8) goto L2b
            androidx.constraintlayout.motion.widget.q$b r3 = r6.f1524c
            int r3 = androidx.constraintlayout.motion.widget.q.b.c(r3)
            if (r3 != r7) goto L2b
            return
        L2b:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.f1526e
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
            r6.f1524c = r4
            if (r4 == 0) goto L6a
            androidx.constraintlayout.motion.widget.r r7 = androidx.constraintlayout.motion.widget.q.b.l(r4)
            if (r7 == 0) goto L6a
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f1524c
            androidx.constraintlayout.motion.widget.r r7 = androidx.constraintlayout.motion.widget.q.b.l(r7)
            boolean r8 = r6.f1539r
            r7.x(r8)
        L6a:
            return
        L6b:
            androidx.constraintlayout.motion.widget.q$b r7 = r6.f1527f
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r3 = r6.f1528g
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
            java.util.ArrayList<androidx.constraintlayout.motion.widget.q$b> r7 = r6.f1526e
            r7.add(r8)
        L99:
            r6.f1524c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.q.W(int, int):void");
    }

    public void X(b bVar) {
        this.f1524c = bVar;
        if (bVar == null || bVar.f1556l == null) {
            return;
        }
        this.f1524c.f1556l.x(this.f1539r);
    }

    void Y() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return;
        }
        this.f1524c.f1556l.z();
    }

    boolean a0() {
        Iterator<b> it = this.f1526e.iterator();
        while (it.hasNext()) {
            if (it.next().f1556l != null) {
                return true;
            }
        }
        b bVar = this.f1524c;
        return (bVar == null || bVar.f1556l == null) ? false : true;
    }

    public void b0(int i7, View... viewArr) {
        this.f1540s.h(i7, viewArr);
    }

    public void f(MotionLayout motionLayout, int i7) {
        for (b bVar : this.f1526e) {
            if (bVar.f1557m.size() > 0) {
                Iterator it = bVar.f1557m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).c(motionLayout);
                }
            }
        }
        for (b bVar2 : this.f1528g) {
            if (bVar2.f1557m.size() > 0) {
                Iterator it2 = bVar2.f1557m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).c(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f1526e) {
            if (bVar3.f1557m.size() > 0) {
                Iterator it3 = bVar3.f1557m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i7, bVar3);
                }
            }
        }
        for (b bVar4 : this.f1528g) {
            if (bVar4.f1557m.size() > 0) {
                Iterator it4 = bVar4.f1557m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i7, bVar4);
                }
            }
        }
    }

    boolean g(MotionLayout motionLayout, int i7) {
        b bVar;
        if (I() || this.f1525d) {
            return false;
        }
        for (b bVar2 : this.f1526e) {
            if (bVar2.f1558n != 0 && ((bVar = this.f1524c) != bVar2 || !bVar.D(2))) {
                if (i7 == bVar2.f1548d && (bVar2.f1558n == 4 || bVar2.f1558n == 2)) {
                    MotionLayout.k kVar = MotionLayout.k.FINISHED;
                    motionLayout.setState(kVar);
                    motionLayout.setTransition(bVar2);
                    if (bVar2.f1558n == 4) {
                        motionLayout.C0();
                        motionLayout.setState(MotionLayout.k.SETUP);
                        motionLayout.setState(MotionLayout.k.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.g0(true);
                        motionLayout.setState(MotionLayout.k.SETUP);
                        motionLayout.setState(MotionLayout.k.MOVING);
                        motionLayout.setState(kVar);
                        motionLayout.t0();
                    }
                    return true;
                }
                if (i7 == bVar2.f1547c && (bVar2.f1558n == 3 || bVar2.f1558n == 1)) {
                    MotionLayout.k kVar2 = MotionLayout.k.FINISHED;
                    motionLayout.setState(kVar2);
                    motionLayout.setTransition(bVar2);
                    if (bVar2.f1558n == 3) {
                        motionLayout.E0();
                        motionLayout.setState(MotionLayout.k.SETUP);
                        motionLayout.setState(MotionLayout.k.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.g0(true);
                        motionLayout.setState(MotionLayout.k.SETUP);
                        motionLayout.setState(MotionLayout.k.MOVING);
                        motionLayout.setState(kVar2);
                        motionLayout.t0();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public b h(int i7, float f7, float f8, MotionEvent motionEvent) {
        if (i7 == -1) {
            return this.f1524c;
        }
        List<b> listG = G(i7);
        float f9 = 0.0f;
        b bVar = null;
        RectF rectF = new RectF();
        for (b bVar2 : listG) {
            if (!bVar2.f1559o && bVar2.f1556l != null) {
                bVar2.f1556l.x(this.f1539r);
                RectF rectFP = bVar2.f1556l.p(this.f1522a, rectF);
                if (rectFP == null || motionEvent == null || rectFP.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF rectFF = bVar2.f1556l.f(this.f1522a, rectF);
                    if (rectFF == null || motionEvent == null || rectFF.contains(motionEvent.getX(), motionEvent.getY())) {
                        float fA = bVar2.f1556l.a(f7, f8);
                        if (bVar2.f1556l.f1577l && motionEvent != null) {
                            fA = ((float) (Math.atan2(f8 + r10, f7 + r9) - Math.atan2(motionEvent.getX() - bVar2.f1556l.f1574i, motionEvent.getY() - bVar2.f1556l.f1575j))) * 10.0f;
                        }
                        float f10 = fA * (bVar2.f1547c == i7 ? -1.0f : 1.1f);
                        if (f10 > f9) {
                            bVar = bVar2;
                            f9 = f10;
                        }
                    }
                }
            }
        }
        return bVar;
    }

    public int i() {
        b bVar = this.f1524c;
        if (bVar != null) {
            return bVar.f1560p;
        }
        return -1;
    }

    int j() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0;
        }
        return this.f1524c.f1556l.d();
    }

    androidx.constraintlayout.widget.d k(int i7) {
        return l(i7, -1, -1);
    }

    androidx.constraintlayout.widget.d l(int i7, int i8, int i9) {
        int iC;
        if (this.f1532k) {
            System.out.println("id " + i7);
            System.out.println("size " + this.f1529h.size());
        }
        androidx.constraintlayout.widget.k kVar = this.f1523b;
        if (kVar != null && (iC = kVar.c(i7, i8, i9)) != -1) {
            i7 = iC;
        }
        if (this.f1529h.get(i7) != null) {
            return this.f1529h.get(i7);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + androidx.constraintlayout.motion.widget.a.c(this.f1522a.getContext(), i7) + " In MotionScene");
        SparseArray<androidx.constraintlayout.widget.d> sparseArray = this.f1529h;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] m() {
        int size = this.f1529h.size();
        int[] iArr = new int[size];
        for (int i7 = 0; i7 < size; i7++) {
            iArr[i7] = this.f1529h.keyAt(i7);
        }
        return iArr;
    }

    public ArrayList<b> n() {
        return this.f1526e;
    }

    public int o() {
        b bVar = this.f1524c;
        return bVar != null ? bVar.f1552h : this.f1533l;
    }

    int p() {
        b bVar = this.f1524c;
        if (bVar == null) {
            return -1;
        }
        return bVar.f1547c;
    }

    public Interpolator r() {
        int i7 = this.f1524c.f1549e;
        if (i7 == -2) {
            return AnimationUtils.loadInterpolator(this.f1522a.getContext(), this.f1524c.f1551g);
        }
        if (i7 == -1) {
            return new a(p.c.c(this.f1524c.f1550f));
        }
        if (i7 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i7 == 1) {
            return new AccelerateInterpolator();
        }
        if (i7 == 2) {
            return new DecelerateInterpolator();
        }
        if (i7 == 4) {
            return new BounceInterpolator();
        }
        if (i7 == 5) {
            return new OvershootInterpolator();
        }
        if (i7 != 6) {
            return null;
        }
        return new AnticipateInterpolator();
    }

    public void s(m mVar) {
        b bVar = this.f1524c;
        if (bVar != null) {
            Iterator it = bVar.f1555k.iterator();
            while (it.hasNext()) {
                ((g) it.next()).b(mVar);
            }
        } else {
            b bVar2 = this.f1527f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.f1555k.iterator();
                while (it2.hasNext()) {
                    ((g) it2.next()).b(mVar);
                }
            }
        }
    }

    float t() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0.0f;
        }
        return this.f1524c.f1556l.g();
    }

    float u() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0.0f;
        }
        return this.f1524c.f1556l.h();
    }

    boolean v() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return false;
        }
        return this.f1524c.f1556l.i();
    }

    float w(float f7, float f8) {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0.0f;
        }
        return this.f1524c.f1556l.j(f7, f8);
    }

    int y() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0;
        }
        return this.f1524c.f1556l.k();
    }

    float z() {
        b bVar = this.f1524c;
        if (bVar == null || bVar.f1556l == null) {
            return 0.0f;
        }
        return this.f1524c.f1556l.l();
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1545a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1546b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1547c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f1548d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1549e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f1550f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f1551g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f1552h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private float f1553i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final q f1554j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private ArrayList<g> f1555k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private r f1556l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private ArrayList<a> f1557m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private int f1558n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private boolean f1559o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private int f1560p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private int f1561q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private int f1562r;

        /* JADX INFO: compiled from: MotionScene.java */
        public static class a implements View.OnClickListener {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            private final b f1563e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            int f1564g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            int f1565h;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.f1564g = -1;
                this.f1565h = 17;
                this.f1563e = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.i.OnClick);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i7 = 0; i7 < indexCount; i7++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i7);
                    if (index == androidx.constraintlayout.widget.i.OnClick_targetId) {
                        this.f1564g = typedArrayObtainStyledAttributes.getResourceId(index, this.f1564g);
                    } else if (index == androidx.constraintlayout.widget.i.OnClick_clickAction) {
                        this.f1565h = typedArrayObtainStyledAttributes.getInt(index, this.f1565h);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i7, b bVar) {
                int i8 = this.f1564g;
                View viewFindViewById = motionLayout;
                if (i8 != -1) {
                    viewFindViewById = motionLayout.findViewById(i8);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f1564g);
                    return;
                }
                int i9 = bVar.f1548d;
                int i10 = bVar.f1547c;
                if (i9 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                int i11 = this.f1565h;
                boolean z6 = false;
                boolean z7 = ((i11 & 1) != 0 && i7 == i9) | ((i11 & 1) != 0 && i7 == i9) | ((i11 & 256) != 0 && i7 == i9) | ((i11 & 16) != 0 && i7 == i10);
                if ((i11 & 4096) != 0 && i7 == i10) {
                    z6 = true;
                }
                if (z7 || z6) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            boolean b(b bVar, MotionLayout motionLayout) {
                b bVar2 = this.f1563e;
                if (bVar2 == bVar) {
                    return true;
                }
                int i7 = bVar2.f1547c;
                int i8 = this.f1563e.f1548d;
                if (i8 == -1) {
                    return motionLayout.I != i7;
                }
                int i9 = motionLayout.I;
                return i9 == i8 || i9 == i7;
            }

            public void c(MotionLayout motionLayout) {
                int i7 = this.f1564g;
                if (i7 == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i7);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f1564g);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f1563e.f1554j.f1522a;
                if (motionLayout.r0()) {
                    if (this.f1563e.f1548d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.F0(this.f1563e.f1547c);
                            return;
                        }
                        b bVar = new b(this.f1563e.f1554j, this.f1563e);
                        bVar.f1548d = currentState;
                        bVar.f1547c = this.f1563e.f1547c;
                        motionLayout.setTransition(bVar);
                        motionLayout.C0();
                        return;
                    }
                    b bVar2 = this.f1563e.f1554j.f1524c;
                    int i7 = this.f1565h;
                    boolean z6 = false;
                    boolean z7 = ((i7 & 1) == 0 && (i7 & 256) == 0) ? false : true;
                    boolean z8 = ((i7 & 16) == 0 && (i7 & 4096) == 0) ? false : true;
                    if (z7 && z8) {
                        b bVar3 = this.f1563e.f1554j.f1524c;
                        b bVar4 = this.f1563e;
                        if (bVar3 != bVar4) {
                            motionLayout.setTransition(bVar4);
                        }
                        if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                            z8 = false;
                            z6 = z7;
                        }
                    } else {
                        z6 = z7;
                    }
                    if (b(bVar2, motionLayout)) {
                        if (z6 && (this.f1565h & 1) != 0) {
                            motionLayout.setTransition(this.f1563e);
                            motionLayout.C0();
                            return;
                        }
                        if (z8 && (this.f1565h & 16) != 0) {
                            motionLayout.setTransition(this.f1563e);
                            motionLayout.E0();
                        } else if (z6 && (this.f1565h & 256) != 0) {
                            motionLayout.setTransition(this.f1563e);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z8 || (this.f1565h & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f1563e);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }
        }

        b(q qVar, b bVar) {
            this.f1545a = -1;
            this.f1546b = false;
            this.f1547c = -1;
            this.f1548d = -1;
            this.f1549e = 0;
            this.f1550f = null;
            this.f1551g = -1;
            this.f1552h = 400;
            this.f1553i = 0.0f;
            this.f1555k = new ArrayList<>();
            this.f1556l = null;
            this.f1557m = new ArrayList<>();
            this.f1558n = 0;
            this.f1559o = false;
            this.f1560p = -1;
            this.f1561q = 0;
            this.f1562r = 0;
            this.f1554j = qVar;
            this.f1552h = qVar.f1533l;
            if (bVar != null) {
                this.f1560p = bVar.f1560p;
                this.f1549e = bVar.f1549e;
                this.f1550f = bVar.f1550f;
                this.f1551g = bVar.f1551g;
                this.f1552h = bVar.f1552h;
                this.f1555k = bVar.f1555k;
                this.f1553i = bVar.f1553i;
                this.f1561q = bVar.f1561q;
            }
        }

        private void v(q qVar, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArray.getIndex(i7);
                if (index == androidx.constraintlayout.widget.i.Transition_constraintSetEnd) {
                    this.f1547c = typedArray.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1547c);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.d dVar = new androidx.constraintlayout.widget.d();
                        dVar.D(context, this.f1547c);
                        qVar.f1529h.append(this.f1547c, dVar);
                    } else if ("xml".equals(resourceTypeName)) {
                        this.f1547c = qVar.L(context, this.f1547c);
                    }
                } else if (index == androidx.constraintlayout.widget.i.Transition_constraintSetStart) {
                    this.f1548d = typedArray.getResourceId(index, this.f1548d);
                    String resourceTypeName2 = context.getResources().getResourceTypeName(this.f1548d);
                    if ("layout".equals(resourceTypeName2)) {
                        androidx.constraintlayout.widget.d dVar2 = new androidx.constraintlayout.widget.d();
                        dVar2.D(context, this.f1548d);
                        qVar.f1529h.append(this.f1548d, dVar2);
                    } else if ("xml".equals(resourceTypeName2)) {
                        this.f1548d = qVar.L(context, this.f1548d);
                    }
                } else if (index == androidx.constraintlayout.widget.i.Transition_motionInterpolator) {
                    int i8 = typedArray.peekValue(index).type;
                    if (i8 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.f1551g = resourceId;
                        if (resourceId != -1) {
                            this.f1549e = -2;
                        }
                    } else if (i8 == 3) {
                        String string = typedArray.getString(index);
                        this.f1550f = string;
                        if (string != null) {
                            if (string.indexOf("/") > 0) {
                                this.f1551g = typedArray.getResourceId(index, -1);
                                this.f1549e = -2;
                            } else {
                                this.f1549e = -1;
                            }
                        }
                    } else {
                        this.f1549e = typedArray.getInteger(index, this.f1549e);
                    }
                } else if (index == androidx.constraintlayout.widget.i.Transition_duration) {
                    int i9 = typedArray.getInt(index, this.f1552h);
                    this.f1552h = i9;
                    if (i9 < 8) {
                        this.f1552h = 8;
                    }
                } else if (index == androidx.constraintlayout.widget.i.Transition_staggered) {
                    this.f1553i = typedArray.getFloat(index, this.f1553i);
                } else if (index == androidx.constraintlayout.widget.i.Transition_autoTransition) {
                    this.f1558n = typedArray.getInteger(index, this.f1558n);
                } else if (index == androidx.constraintlayout.widget.i.Transition_android_id) {
                    this.f1545a = typedArray.getResourceId(index, this.f1545a);
                } else if (index == androidx.constraintlayout.widget.i.Transition_transitionDisable) {
                    this.f1559o = typedArray.getBoolean(index, this.f1559o);
                } else if (index == androidx.constraintlayout.widget.i.Transition_pathMotionArc) {
                    this.f1560p = typedArray.getInteger(index, -1);
                } else if (index == androidx.constraintlayout.widget.i.Transition_layoutDuringTransition) {
                    this.f1561q = typedArray.getInteger(index, 0);
                } else if (index == androidx.constraintlayout.widget.i.Transition_transitionFlags) {
                    this.f1562r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f1548d == -1) {
                this.f1546b = true;
            }
        }

        private void w(q qVar, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.Transition);
            v(qVar, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int A() {
            return this.f1548d;
        }

        public r B() {
            return this.f1556l;
        }

        public boolean C() {
            return !this.f1559o;
        }

        public boolean D(int i7) {
            return (i7 & this.f1562r) != 0;
        }

        public void E(int i7) {
            this.f1552h = Math.max(i7, 8);
        }

        public void F(boolean z6) {
            this.f1559o = !z6;
        }

        public void G(int i7, String str, int i8) {
            this.f1549e = i7;
            this.f1550f = str;
            this.f1551g = i8;
        }

        public void H(int i7) {
            this.f1560p = i7;
        }

        public void t(g gVar) {
            this.f1555k.add(gVar);
        }

        public void u(Context context, XmlPullParser xmlPullParser) {
            this.f1557m.add(new a(context, this, xmlPullParser));
        }

        public int x() {
            return this.f1558n;
        }

        public int y() {
            return this.f1547c;
        }

        public int z() {
            return this.f1561q;
        }

        public b(int i7, q qVar, int i8, int i9) {
            this.f1545a = -1;
            this.f1546b = false;
            this.f1547c = -1;
            this.f1548d = -1;
            this.f1549e = 0;
            this.f1550f = null;
            this.f1551g = -1;
            this.f1552h = 400;
            this.f1553i = 0.0f;
            this.f1555k = new ArrayList<>();
            this.f1556l = null;
            this.f1557m = new ArrayList<>();
            this.f1558n = 0;
            this.f1559o = false;
            this.f1560p = -1;
            this.f1561q = 0;
            this.f1562r = 0;
            this.f1545a = i7;
            this.f1554j = qVar;
            this.f1548d = i8;
            this.f1547c = i9;
            this.f1552h = qVar.f1533l;
            this.f1561q = qVar.f1534m;
        }

        b(q qVar, Context context, XmlPullParser xmlPullParser) {
            this.f1545a = -1;
            this.f1546b = false;
            this.f1547c = -1;
            this.f1548d = -1;
            this.f1549e = 0;
            this.f1550f = null;
            this.f1551g = -1;
            this.f1552h = 400;
            this.f1553i = 0.0f;
            this.f1555k = new ArrayList<>();
            this.f1556l = null;
            this.f1557m = new ArrayList<>();
            this.f1558n = 0;
            this.f1559o = false;
            this.f1560p = -1;
            this.f1561q = 0;
            this.f1562r = 0;
            this.f1552h = qVar.f1533l;
            this.f1561q = qVar.f1534m;
            this.f1554j = qVar;
            w(qVar, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
