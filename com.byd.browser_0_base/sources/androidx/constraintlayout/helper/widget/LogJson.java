package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.a;
import defpackage.C0173a;
import defpackage.aw;
import defpackage.b70;
import defpackage.ga1;
import defpackage.gq2;
import defpackage.ox;
import defpackage.wq0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class LogJson extends ConstraintHelper {
    public int n;
    public int o;
    public String p;
    public boolean q;
    public boolean r;

    public static class a {
        public androidx.constraintlayout.widget.a a;
        public Writer b;
        public Context c;
        public int d = 0;
        public final HashMap<Integer, String> e = new HashMap<>();
        public final HashMap<Integer, String> f = new HashMap<>();

        static {
            new AtomicInteger(1);
        }

        public final String a(int i) {
            return "'" + b(i) + "'";
        }

        public final String b(int i) {
            String string;
            HashMap<Integer, String> map = this.e;
            if (map.containsKey(Integer.valueOf(i))) {
                return "" + map.get(Integer.valueOf(i));
            }
            if (i == 0) {
                return "parent";
            }
            HashMap<Integer, String> map2 = this.f;
            try {
                if (map2.containsKey(Integer.valueOf(i))) {
                    string = map2.get(Integer.valueOf(i));
                } else if (i != -1) {
                    string = this.c.getResources().getResourceEntryName(i);
                } else {
                    StringBuilder sb = new StringBuilder("unknown");
                    int i2 = this.d + 1;
                    this.d = i2;
                    sb.append(i2);
                    string = sb.toString();
                }
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder("unknown");
                int i3 = this.d + 1;
                this.d = i3;
                sb2.append(i3);
                string = sb2.toString();
            }
            map.put(Integer.valueOf(i), string);
            return "" + string + "";
        }

        public final void c(int i, int i2, int i3, String str, String str2) throws IOException {
            if (i == -1) {
                return;
            }
            this.b.write("    ".concat(str));
            this.b.write(":[");
            this.b.write(a(i));
            this.b.write(", ");
            this.b.write("'" + str2 + "'");
            if (i2 != 0 || i3 != Integer.MIN_VALUE) {
                this.b.write(", " + i2);
                if (i3 != Integer.MIN_VALUE) {
                    this.b.write(", " + i3);
                }
            }
            this.b.write("],\n");
        }

        public final void d(String str, int i, int i2, float f, int i3, int i4) throws IOException {
            String strG;
            if (i != 0) {
                if (i == -2) {
                    this.b.write("    " + str + ": 'wrap',\n");
                    return;
                }
                if (i == -1) {
                    this.b.write("    " + str + ": 'parent',\n");
                    return;
                }
                this.b.write("    " + str + ": " + i + ",\n");
                return;
            }
            if (i4 == -1 && i3 == -1) {
                if (i2 == 1) {
                    this.b.write("    " + str + ": '???????????',\n");
                    return;
                }
                if (i2 != 2) {
                    return;
                }
                this.b.write("    " + str + ": '" + f + "%',\n");
                return;
            }
            if (i2 == 0) {
                strG = gq2.g("    ", str, ": {value:'spread'");
            } else if (i2 == 1) {
                strG = gq2.g("    ", str, ": {value:'wrap'");
            } else if (i2 != 2) {
                strG = "-----";
            } else {
                strG = "    " + str + ": {value: '" + f + "%'";
            }
            if (i4 != -1) {
                strG = strG + ", max: " + i4;
            }
            if (i4 != -1) {
                strG = strG + ", min: " + i3;
            }
            this.b.write(gq2.f(strG, "},\n"));
        }

        public final void e() throws IOException {
            this.b.write("{\n");
            for (int i : this.a.j()) {
                Integer numValueOf = Integer.valueOf(i);
                a.C0004a c0004aI = this.a.i(numValueOf.intValue());
                String strB = b(numValueOf.intValue());
                if (!"LogJson".equals(strB)) {
                    this.b.write("  " + strB + ":{\n");
                    a.b bVar = c0004aI.e;
                    if (bVar.j0 != null) {
                        StringBuilder sb = new StringBuilder(gq2.g("type: '_", strB, "_' , contains: ["));
                        int i2 = 0;
                        while (true) {
                            int[] iArr = bVar.j0;
                            if (i2 < iArr.length) {
                                int i3 = iArr[i2];
                                sb.append(i2 == 0 ? "" : ", ");
                                sb.append(a(i3));
                                i2++;
                            } else {
                                this.b.write(((Object) sb) + "]\n");
                            }
                        }
                    }
                    if (bVar.k0 != null) {
                        StringBuilder sb2 = new StringBuilder("  type: '???' , contains: [");
                        String[] strArrSplit = bVar.k0.split(",");
                        int i4 = 0;
                        while (i4 < strArrSplit.length) {
                            String str = strArrSplit[i4];
                            sb2.append(i4 == 0 ? "" : ", ");
                            sb2.append("`");
                            sb2.append(str);
                            sb2.append("`");
                            i4++;
                        }
                        this.b.write(((Object) sb2) + "]\n");
                    }
                    d("height", bVar.d, bVar.Z, bVar.f0, bVar.d0, bVar.b0);
                    d("width", bVar.c, bVar.Y, bVar.e0, bVar.c0, bVar.a0);
                    c(bVar.i, bVar.G, bVar.N, "left", "left");
                    c(bVar.j, bVar.G, bVar.N, "left", "right");
                    c(bVar.k, bVar.H, bVar.P, "right", "left");
                    c(bVar.l, bVar.H, bVar.P, "right", "right");
                    c(bVar.q, -1, bVar.T, "baseline", "baseline");
                    c(bVar.r, -1, bVar.T, "baseline", "top");
                    c(bVar.s, -1, bVar.T, "baseline", "bottom");
                    c(bVar.n, bVar.I, bVar.O, "top", "bottom");
                    c(bVar.m, bVar.I, bVar.O, "top", "top");
                    c(bVar.p, bVar.f9J, bVar.Q, "bottom", "bottom");
                    c(bVar.o, bVar.f9J, bVar.Q, "bottom", "top");
                    c(bVar.u, bVar.L, bVar.S, "start", "start");
                    c(bVar.t, bVar.L, bVar.S, "start", "end");
                    c(bVar.v, bVar.K, bVar.R, "end", "start");
                    c(bVar.w, bVar.K, bVar.R, "end", "end");
                    h("horizontalBias", bVar.x, 0.5f);
                    h("verticalBias", bVar.y, 0.5f);
                    int i5 = bVar.A;
                    float f = bVar.C;
                    int i6 = bVar.B;
                    if (i5 != -1) {
                        this.b.write("    circle");
                        this.b.write(":[");
                        this.b.write(a(i5));
                        this.b.write(", " + f);
                        this.b.write(i6 + "],\n");
                    }
                    int i7 = bVar.F;
                    int i8 = bVar.e;
                    int i9 = bVar.f;
                    float f2 = bVar.g;
                    f(i7, "orientation");
                    f(i8, "guideBegin");
                    f(i9, "guideEnd");
                    g("guidePercent", f2);
                    i("dimensionRatio", bVar.z);
                    f(bVar.h0, "barrierMargin");
                    f(bVar.i0, "type");
                    i("ReferenceId", bVar.k0);
                    boolean z = bVar.o0;
                    if (!z) {
                        this.b.write("    mBarrierAllowsGoneWidgets");
                        this.b.write(": " + z);
                        this.b.write(",\n");
                    }
                    f(bVar.p0, "WrapBehavior");
                    g("verticalWeight", bVar.U);
                    g("horizontalWeight", bVar.V);
                    f(bVar.W, "horizontalChainStyle");
                    f(bVar.X, "verticalChainStyle");
                    f(bVar.g0, "barrierDirection");
                    int[] iArr2 = bVar.j0;
                    if (iArr2 != null) {
                        this.b.write("    ReferenceIds");
                        this.b.write(": ");
                        int i10 = 0;
                        while (i10 < iArr2.length) {
                            Writer writer = this.b;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(i10 == 0 ? "[" : ", ");
                            sb3.append(a(iArr2[i10]));
                            writer.write(sb3.toString());
                            i10++;
                        }
                        this.b.write("],\n");
                    }
                    a.e eVar = c0004aI.f;
                    if (eVar.m) {
                        g("elevation", eVar.n);
                    }
                    h("rotationX", eVar.c, 0.0f);
                    h("rotationY", eVar.d, 0.0f);
                    h("rotationZ", eVar.b, 0.0f);
                    h("scaleX", eVar.e, 1.0f);
                    h("scaleY", eVar.f, 1.0f);
                    h("translationX", eVar.j, 0.0f);
                    h("translationY", eVar.k, 0.0f);
                    h("translationZ", eVar.l, 0.0f);
                    HashMap<String, ox> map = c0004aI.g;
                    if (map != null && map.size() > 0) {
                        this.b.write("    custom: {\n");
                        Iterator<String> it = map.keySet().iterator();
                        while (it.hasNext()) {
                            ox oxVar = map.get(it.next());
                            if (oxVar != null) {
                                String strI = gq2.i(new StringBuilder("      "), oxVar.b, ": ");
                                switch (gq2.m(oxVar.c)) {
                                    case 0:
                                        StringBuilder sbJ = C0173a.j(strI);
                                        sbJ.append(oxVar.d);
                                        strI = sbJ.toString();
                                        break;
                                    case 1:
                                        StringBuilder sbJ2 = C0173a.j(strI);
                                        sbJ2.append(oxVar.e);
                                        strI = sbJ2.toString();
                                        break;
                                    case 2:
                                        StringBuilder sbJ3 = C0173a.j(strI);
                                        sbJ3.append("#" + ("00000000" + Integer.toHexString(oxVar.h)).substring(r4.length() - 8));
                                        strI = sbJ3.toString();
                                        break;
                                    case 3:
                                    case 5:
                                    case 7:
                                        strI = null;
                                        break;
                                    case 4:
                                        strI = gq2.i(C0173a.k(strI, "'"), oxVar.f, "'");
                                        break;
                                    case 6:
                                        StringBuilder sbJ4 = C0173a.j(strI);
                                        sbJ4.append(oxVar.e);
                                        strI = sbJ4.toString();
                                        break;
                                }
                                if (strI != null) {
                                    this.b.write(strI.concat(",\n"));
                                }
                            }
                        }
                        this.b.write("     } \n");
                    }
                    this.b.write("  },\n");
                }
            }
            this.b.write("},\n");
        }

        public final void f(int i, String str) throws IOException {
            if (i == 0 || i == -1) {
                return;
            }
            this.b.write("    ".concat(str));
            this.b.write(": " + i);
            this.b.write(",\n");
        }

        public final void g(String str, float f) throws IOException {
            if (f == -1.0f) {
                return;
            }
            this.b.write("    ".concat(str));
            this.b.write(": " + f);
            this.b.write(",\n");
        }

        public final void h(String str, float f, float f2) throws IOException {
            if (f == f2) {
                return;
            }
            this.b.write("    ".concat(str));
            this.b.write(": " + f);
            this.b.write(",\n");
        }

        public final void i(String str, String str2) throws IOException {
            if (str2 == null) {
                return;
            }
            this.b.write("    ".concat(str));
            this.b.write(": '".concat(str2));
            this.b.write("',\n");
        }
    }

    public LogJson(Context context) {
        super(context);
        this.n = 1000;
        this.o = 2;
        this.p = null;
        this.q = true;
        this.r = false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = this.o;
        int i2 = 1;
        if (i == 1) {
            this.r = true;
            postDelayed(new aw(i2, this), this.n);
            return;
        }
        int i3 = 2;
        if (i == 2) {
            postDelayed(new wq0(i3, this), this.n);
        } else {
            if (i != 3) {
                return;
            }
            ((ConstraintLayout) getParent()).addOnLayoutChangeListener(new ga1(0, this));
        }
    }

    public void setDelay(int i) {
        this.n = i;
    }

    public final void u(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.LogJson);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.LogJson_logJsonDelay) {
                    this.n = typedArrayObtainStyledAttributes.getInt(index, this.n);
                } else if (index == R$styleable.LogJson_logJsonMode) {
                    this.o = typedArrayObtainStyledAttributes.getInt(index, this.o);
                } else if (index == R$styleable.LogJson_logJsonTo) {
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        this.p = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        this.q = typedArrayObtainStyledAttributes.getInt(index, 0) == 2;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        setVisibility(8);
    }

    public final void v() {
        HashMap<Integer, String> map;
        int i;
        int i2;
        HashMap<Integer, String> map2;
        String str;
        ConstraintLayout constraintLayout = (ConstraintLayout) getParent();
        a aVar = new a();
        StringWriter stringWriter = new StringWriter();
        int childCount = constraintLayout.getChildCount();
        int i3 = 0;
        while (true) {
            map = aVar.f;
            i = -1;
            if (i3 >= childCount) {
                break;
            }
            View childAt = constraintLayout.getChildAt(i3);
            String simpleName = childAt.getClass().getSimpleName();
            int id = childAt.getId();
            if (id == -1) {
                int iGenerateViewId = View.generateViewId();
                childAt.setId(iGenerateViewId);
                if (!"LogJson".equals(simpleName)) {
                    simpleName = "noid_".concat(simpleName);
                }
                map.put(Integer.valueOf(iGenerateViewId), simpleName);
            } else if ("LogJson".equals(simpleName)) {
                map.put(Integer.valueOf(id), simpleName);
            }
            i3++;
        }
        stringWriter.append((CharSequence) "{\n");
        stringWriter.append((CharSequence) "Widgets:{\n");
        int childCount2 = constraintLayout.getChildCount();
        int i4 = -1;
        while (i4 < childCount2) {
            View childAt2 = i4 == i ? constraintLayout : constraintLayout.getChildAt(i4);
            int id2 = childAt2.getId();
            if ("LogJson".equals(childAt2.getClass().getSimpleName())) {
                i2 = childCount2;
                map2 = map;
            } else {
                String strD = map.containsKey(Integer.valueOf(id2)) ? map.get(Integer.valueOf(id2)) : i4 == i ? "parent" : b70.d(childAt2);
                String simpleName2 = childAt2.getClass().getSimpleName();
                String str2 = ", bounds: [" + childAt2.getLeft() + ", " + childAt2.getTop() + ", " + childAt2.getRight() + ", " + childAt2.getBottom() + "]},\n";
                stringWriter.append((CharSequence) ("  " + strD + ": { "));
                if (i4 == -1) {
                    stringWriter.append((CharSequence) ("type: '" + childAt2.getClass().getSimpleName() + "' , "));
                    try {
                        ViewGroup.LayoutParams layoutParams = childAt2.getLayoutParams();
                        String str3 = "'WRAP_CONTENT'";
                        int i5 = layoutParams.width;
                        i2 = childCount2;
                        if (i5 == -1) {
                            str = "'MATCH_PARENT'";
                        } else if (i5 == -2) {
                            str = "'WRAP_CONTENT'";
                        } else {
                            try {
                                str = layoutParams.width + "";
                            } catch (Exception unused) {
                                map2 = map;
                            }
                        }
                        StringBuilder sb = new StringBuilder();
                        map2 = map;
                        try {
                            sb.append("width: ");
                            sb.append(str);
                            sb.append(", ");
                            stringWriter.append((CharSequence) sb.toString());
                            int i6 = layoutParams.height;
                            if (i6 == -1) {
                                str3 = "'MATCH_PARENT'";
                            } else if (i6 != -2) {
                                str3 = layoutParams.height + "";
                            }
                            stringWriter.append((CharSequence) "height: ").append((CharSequence) str3);
                        } catch (Exception unused2) {
                        }
                    } catch (Exception unused3) {
                        i2 = childCount2;
                    }
                } else {
                    i2 = childCount2;
                    map2 = map;
                    if (simpleName2.contains("Text")) {
                        if (childAt2 instanceof TextView) {
                            stringWriter.append((CharSequence) ("type: 'Text', label: '" + ((TextView) childAt2).getText().toString().replaceAll("'", "\\'") + "'"));
                        } else {
                            stringWriter.append((CharSequence) "type: 'Text' },\n");
                        }
                    } else if (simpleName2.contains("Button")) {
                        if (childAt2 instanceof Button) {
                            stringWriter.append((CharSequence) ("type: 'Button', label: '" + ((Object) ((Button) childAt2).getText()) + "'"));
                        } else {
                            stringWriter.append((CharSequence) "type: 'Button'");
                        }
                    } else if (simpleName2.contains("Image")) {
                        stringWriter.append((CharSequence) "type: 'Image'");
                    } else if (simpleName2.contains("View")) {
                        stringWriter.append((CharSequence) "type: 'Box'");
                    } else {
                        stringWriter.append((CharSequence) ("type: '" + childAt2.getClass().getSimpleName() + "'"));
                    }
                }
                stringWriter.append((CharSequence) str2);
            }
            i4++;
            childCount2 = i2;
            map = map2;
            i = -1;
        }
        stringWriter.append((CharSequence) "},\n");
        stringWriter.append((CharSequence) "  ConstraintSet:{\n");
        androidx.constraintlayout.widget.a aVar2 = new androidx.constraintlayout.widget.a();
        aVar2.e(constraintLayout);
        try {
            stringWriter.append((CharSequence) ((constraintLayout.getId() == -1 ? "cset" : b70.d(constraintLayout)) + ":"));
            aVar.b = stringWriter;
            aVar.c = constraintLayout.getContext();
            aVar.a = aVar2;
            aVar2.i(2);
            aVar.e();
            stringWriter.append((CharSequence) "\n");
            stringWriter.append((CharSequence) "  }\n");
            stringWriter.append((CharSequence) "}\n");
            String string = stringWriter.toString();
            String strConcat = this.p;
            if (strConcat != null) {
                if (!strConcat.endsWith(".json5")) {
                    strConcat = strConcat.concat(".json5");
                }
                try {
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), strConcat);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(string.getBytes());
                    fileOutputStream.close();
                    file.getCanonicalPath();
                    return;
                } catch (IOException e) {
                    e.toString();
                    return;
                }
            }
            if (this.q) {
                System.out.println(string);
                return;
            }
            int length = string.length();
            int i7 = 0;
            while (i7 < length) {
                int iIndexOf = string.indexOf("\n", i7);
                if (iIndexOf == -1) {
                    string.substring(i7);
                    return;
                } else {
                    string.substring(i7, iIndexOf);
                    i7 = iIndexOf + 1;
                }
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public LogJson(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = 1000;
        this.o = 2;
        this.p = null;
        this.q = true;
        this.r = false;
        u(attributeSet);
    }

    public LogJson(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = 1000;
        this.o = 2;
        this.p = null;
        this.q = true;
        this.r = false;
        u(attributeSet);
    }
}
