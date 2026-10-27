package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.widgets.ConstraintAnchor$Type;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.util.ArrayList;
import java.util.HashMap;
import p.ay;
import p.cy;
import p.d11;
import p.dy;
import p.ey;
import p.fd2;
import p.hy;
import p.py;
import p.ra1;
import p.sz1;
import p.ty;
import p.uy;
import p.xy;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    public static fd2 x;
    public final SparseArray c;
    public final ArrayList e;
    public final uy k;
    public int l;
    public int m;
    public int n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f16p;
    public int q;
    public py r;
    public hy s;
    public int t;
    public HashMap u;
    public final SparseArray v;
    public final ey w;

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new SparseArray();
        this.e = new ArrayList(4);
        this.k = new uy();
        this.l = 0;
        this.m = 0;
        this.n = Integer.MAX_VALUE;
        this.o = Integer.MAX_VALUE;
        this.f16p = true;
        this.q = 257;
        this.r = null;
        this.s = null;
        this.t = -1;
        this.u = new HashMap();
        this.v = new SparseArray();
        this.w = new ey(this, this);
        i(attributeSet, 0);
    }

    public static dy g() {
        dy dyVar = new dy(-2, -2);
        dyVar.a = -1;
        dyVar.b = -1;
        dyVar.c = -1.0f;
        dyVar.d = true;
        dyVar.e = -1;
        dyVar.f = -1;
        dyVar.g = -1;
        dyVar.h = -1;
        dyVar.i = -1;
        dyVar.j = -1;
        dyVar.k = -1;
        dyVar.l = -1;
        dyVar.m = -1;
        dyVar.n = -1;
        dyVar.o = -1;
        dyVar.f117p = -1;
        dyVar.q = 0;
        dyVar.r = 0.0f;
        dyVar.s = -1;
        dyVar.t = -1;
        dyVar.u = -1;
        dyVar.v = -1;
        dyVar.w = Integer.MIN_VALUE;
        dyVar.x = Integer.MIN_VALUE;
        dyVar.y = Integer.MIN_VALUE;
        dyVar.z = Integer.MIN_VALUE;
        dyVar.A = Integer.MIN_VALUE;
        dyVar.B = Integer.MIN_VALUE;
        dyVar.C = Integer.MIN_VALUE;
        dyVar.D = 0;
        dyVar.E = 0.5f;
        dyVar.F = 0.5f;
        dyVar.G = null;
        dyVar.H = -1.0f;
        dyVar.I = -1.0f;
        dyVar.J = 0;
        dyVar.K = 0;
        dyVar.L = 0;
        dyVar.M = 0;
        dyVar.N = 0;
        dyVar.O = 0;
        dyVar.P = 0;
        dyVar.Q = 0;
        dyVar.R = 1.0f;
        dyVar.S = 1.0f;
        dyVar.T = -1;
        dyVar.U = -1;
        dyVar.V = -1;
        dyVar.W = false;
        dyVar.X = false;
        dyVar.Y = null;
        dyVar.Z = 0;
        dyVar.a0 = true;
        dyVar.b0 = true;
        dyVar.c0 = false;
        dyVar.d0 = false;
        dyVar.e0 = false;
        dyVar.f0 = -1;
        dyVar.g0 = -1;
        dyVar.h0 = -1;
        dyVar.i0 = -1;
        dyVar.j0 = Integer.MIN_VALUE;
        dyVar.k0 = Integer.MIN_VALUE;
        dyVar.l0 = 0.5f;
        dyVar.p0 = new ty();
        return dyVar;
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int iMax2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static fd2 getSharedValues() {
        if (x == null) {
            fd2 fd2Var = new fd2();
            new SparseIntArray();
            new HashMap();
            x = fd2Var;
        }
        return x;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof dy;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.e;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                ((ay) arrayList.get(i)).getClass();
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        int i5 = Integer.parseInt(strArrSplit[2]);
                        int i6 = (int) ((i3 / 1080.0f) * width);
                        int i7 = (int) ((i4 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = i6;
                        float f2 = i7;
                        float f3 = i6 + ((int) ((i5 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float f4 = i7 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f3, f2, f3, f4, paint);
                        canvas.drawLine(f3, f4, f, f4, paint);
                        canvas.drawLine(f, f4, f, f2, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f, f2, f3, f4, paint);
                        canvas.drawLine(f, f4, f3, f2, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
        this.f16p = true;
        super.forceLayout();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return g();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        dy dyVar = new dy(context, attributeSet);
        dyVar.a = -1;
        dyVar.b = -1;
        dyVar.c = -1.0f;
        dyVar.d = true;
        dyVar.e = -1;
        dyVar.f = -1;
        dyVar.g = -1;
        dyVar.h = -1;
        dyVar.i = -1;
        dyVar.j = -1;
        dyVar.k = -1;
        dyVar.l = -1;
        dyVar.m = -1;
        dyVar.n = -1;
        dyVar.o = -1;
        dyVar.f117p = -1;
        dyVar.q = 0;
        dyVar.r = 0.0f;
        dyVar.s = -1;
        dyVar.t = -1;
        dyVar.u = -1;
        dyVar.v = -1;
        dyVar.w = Integer.MIN_VALUE;
        dyVar.x = Integer.MIN_VALUE;
        dyVar.y = Integer.MIN_VALUE;
        dyVar.z = Integer.MIN_VALUE;
        dyVar.A = Integer.MIN_VALUE;
        dyVar.B = Integer.MIN_VALUE;
        dyVar.C = Integer.MIN_VALUE;
        dyVar.D = 0;
        dyVar.E = 0.5f;
        dyVar.F = 0.5f;
        dyVar.G = null;
        dyVar.H = -1.0f;
        dyVar.I = -1.0f;
        dyVar.J = 0;
        dyVar.K = 0;
        dyVar.L = 0;
        dyVar.M = 0;
        dyVar.N = 0;
        dyVar.O = 0;
        dyVar.P = 0;
        dyVar.Q = 0;
        dyVar.R = 1.0f;
        dyVar.S = 1.0f;
        dyVar.T = -1;
        dyVar.U = -1;
        dyVar.V = -1;
        dyVar.W = false;
        dyVar.X = false;
        dyVar.Y = null;
        dyVar.Z = 0;
        dyVar.a0 = true;
        dyVar.b0 = true;
        dyVar.c0 = false;
        dyVar.d0 = false;
        dyVar.e0 = false;
        dyVar.f0 = -1;
        dyVar.g0 = -1;
        dyVar.h0 = -1;
        dyVar.i0 = -1;
        dyVar.j0 = Integer.MIN_VALUE;
        dyVar.k0 = Integer.MIN_VALUE;
        dyVar.l0 = 0.5f;
        dyVar.p0 = new ty();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sz1.b);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            int i2 = cy.a.get(index);
            switch (i2) {
                case 1:
                    dyVar.V = typedArrayObtainStyledAttributes.getInt(index, dyVar.V);
                    break;
                case 2:
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.f117p);
                    dyVar.f117p = resourceId;
                    if (resourceId == -1) {
                        dyVar.f117p = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 3:
                    dyVar.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.q);
                    break;
                case 4:
                    float f = typedArrayObtainStyledAttributes.getFloat(index, dyVar.r) % 360.0f;
                    dyVar.r = f;
                    if (f < 0.0f) {
                        dyVar.r = (360.0f - f) % 360.0f;
                    }
                    break;
                case 5:
                    dyVar.a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, dyVar.a);
                    break;
                case 6:
                    dyVar.b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, dyVar.b);
                    break;
                case 7:
                    dyVar.c = typedArrayObtainStyledAttributes.getFloat(index, dyVar.c);
                    break;
                case 8:
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.e);
                    dyVar.e = resourceId2;
                    if (resourceId2 == -1) {
                        dyVar.e = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 9:
                    int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.f);
                    dyVar.f = resourceId3;
                    if (resourceId3 == -1) {
                        dyVar.f = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 10:
                    int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.g);
                    dyVar.g = resourceId4;
                    if (resourceId4 == -1) {
                        dyVar.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 11:
                    int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.h);
                    dyVar.h = resourceId5;
                    if (resourceId5 == -1) {
                        dyVar.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 12:
                    int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.i);
                    dyVar.i = resourceId6;
                    if (resourceId6 == -1) {
                        dyVar.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 13:
                    int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.j);
                    dyVar.j = resourceId7;
                    if (resourceId7 == -1) {
                        dyVar.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 14:
                    int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.k);
                    dyVar.k = resourceId8;
                    if (resourceId8 == -1) {
                        dyVar.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 15:
                    int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.l);
                    dyVar.l = resourceId9;
                    if (resourceId9 == -1) {
                        dyVar.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 16:
                    int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.m);
                    dyVar.m = resourceId10;
                    if (resourceId10 == -1) {
                        dyVar.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 17:
                    int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.s);
                    dyVar.s = resourceId11;
                    if (resourceId11 == -1) {
                        dyVar.s = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 18:
                    int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.t);
                    dyVar.t = resourceId12;
                    if (resourceId12 == -1) {
                        dyVar.t = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 19:
                    int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.u);
                    dyVar.u = resourceId13;
                    if (resourceId13 == -1) {
                        dyVar.u = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 20:
                    int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.v);
                    dyVar.v = resourceId14;
                    if (resourceId14 == -1) {
                        dyVar.v = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 21:
                    dyVar.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.w);
                    break;
                case 22:
                    dyVar.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.x);
                    break;
                case 23:
                    dyVar.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.y);
                    break;
                case 24:
                    dyVar.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.z);
                    break;
                case 25:
                    dyVar.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.A);
                    break;
                case 26:
                    dyVar.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.B);
                    break;
                case 27:
                    dyVar.W = typedArrayObtainStyledAttributes.getBoolean(index, dyVar.W);
                    break;
                case 28:
                    dyVar.X = typedArrayObtainStyledAttributes.getBoolean(index, dyVar.X);
                    break;
                case 29:
                    dyVar.E = typedArrayObtainStyledAttributes.getFloat(index, dyVar.E);
                    break;
                case 30:
                    dyVar.F = typedArrayObtainStyledAttributes.getFloat(index, dyVar.F);
                    break;
                case 31:
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    dyVar.L = i3;
                    if (i3 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                    }
                    break;
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                    int i4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    dyVar.M = i4;
                    if (i4 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                    }
                    break;
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                    try {
                        dyVar.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.N);
                    } catch (Exception unused) {
                        if (typedArrayObtainStyledAttributes.getInt(index, dyVar.N) == -2) {
                            dyVar.N = -2;
                        }
                    }
                    break;
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                    try {
                        dyVar.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.P);
                    } catch (Exception unused2) {
                        if (typedArrayObtainStyledAttributes.getInt(index, dyVar.P) == -2) {
                            dyVar.P = -2;
                        }
                    }
                    break;
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                    dyVar.R = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, dyVar.R));
                    dyVar.L = 2;
                    break;
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                    try {
                        dyVar.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.O);
                    } catch (Exception unused3) {
                        if (typedArrayObtainStyledAttributes.getInt(index, dyVar.O) == -2) {
                            dyVar.O = -2;
                        }
                    }
                    break;
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                    try {
                        dyVar.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.Q);
                    } catch (Exception unused4) {
                        if (typedArrayObtainStyledAttributes.getInt(index, dyVar.Q) == -2) {
                            dyVar.Q = -2;
                        }
                    }
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                    dyVar.S = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, dyVar.S));
                    dyVar.M = 2;
                    break;
                default:
                    switch (i2) {
                        case 44:
                            py.h(dyVar, typedArrayObtainStyledAttributes.getString(index));
                            break;
                        case 45:
                            dyVar.H = typedArrayObtainStyledAttributes.getFloat(index, dyVar.H);
                            break;
                        case 46:
                            dyVar.I = typedArrayObtainStyledAttributes.getFloat(index, dyVar.I);
                            break;
                        case 47:
                            dyVar.J = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 48:
                            dyVar.K = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 49:
                            dyVar.T = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, dyVar.T);
                            break;
                        case 50:
                            dyVar.U = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, dyVar.U);
                            break;
                        case 51:
                            dyVar.Y = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 52:
                            int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.n);
                            dyVar.n = resourceId15;
                            if (resourceId15 == -1) {
                                dyVar.n = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 53:
                            int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, dyVar.o);
                            dyVar.o = resourceId16;
                            if (resourceId16 == -1) {
                                dyVar.o = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 54:
                            dyVar.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.D);
                            break;
                        case 55:
                            dyVar.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dyVar.C);
                            break;
                        default:
                            switch (i2) {
                                case 64:
                                    py.g(dyVar, typedArrayObtainStyledAttributes, index, 0);
                                    break;
                                case 65:
                                    py.g(dyVar, typedArrayObtainStyledAttributes, index, 1);
                                    break;
                                case 66:
                                    dyVar.Z = typedArrayObtainStyledAttributes.getInt(index, dyVar.Z);
                                    break;
                                case 67:
                                    dyVar.d = typedArrayObtainStyledAttributes.getBoolean(index, dyVar.d);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        dyVar.a();
        return dyVar;
    }

    public int getMaxHeight() {
        return this.o;
    }

    public int getMaxWidth() {
        return this.n;
    }

    public int getMinHeight() {
        return this.m;
    }

    public int getMinWidth() {
        return this.l;
    }

    public int getOptimizationLevel() {
        return this.k.C0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        uy uyVar = this.k;
        if (uyVar.j == null) {
            int id2 = getId();
            if (id2 != -1) {
                uyVar.j = getContext().getResources().getResourceEntryName(id2);
            } else {
                uyVar.j = "parent";
            }
        }
        if (uyVar.h0 == null) {
            uyVar.h0 = uyVar.j;
        }
        for (ty tyVar : uyVar.p0) {
            View view = (View) tyVar.f0;
            if (view != null) {
                if (tyVar.j == null && (id = view.getId()) != -1) {
                    tyVar.j = getContext().getResources().getResourceEntryName(id);
                }
                if (tyVar.h0 == null) {
                    tyVar.h0 = tyVar.j;
                }
            }
        }
        uyVar.l(sb);
        return sb.toString();
    }

    public final ty h(View view) {
        if (view == this) {
            return this.k;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof dy) {
            return ((dy) view.getLayoutParams()).p0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof dy) {
            return ((dy) view.getLayoutParams()).p0;
        }
        return null;
    }

    public final void i(AttributeSet attributeSet, int i) {
        uy uyVar = this.k;
        uyVar.f0 = this;
        ey eyVar = this.w;
        uyVar.t0 = eyVar;
        uyVar.r0.f = eyVar;
        this.c.put(getId(), this);
        this.r = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, sz1.b, i, 0);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == 16) {
                    this.l = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.l);
                } else if (index == 17) {
                    this.m = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.m);
                } else if (index == 14) {
                    this.n = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.n);
                } else if (index == 15) {
                    this.o = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.o);
                } else if (index == 113) {
                    this.q = typedArrayObtainStyledAttributes.getInt(index, this.q);
                } else if (index == 56) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            j(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.s = null;
                        }
                    }
                } else if (index == 34) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        py pyVar = new py();
                        this.r = pyVar;
                        pyVar.e(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.r = null;
                    }
                    this.t = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        uyVar.C0 = this.q;
        ra1.f178p = uyVar.S(512);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(int r13) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.j(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0467  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x04f7 A[PHI: r12
  0x04f7: PHI (r12v18 boolean) = (r12v17 boolean), (r12v17 boolean), (r12v21 boolean) binds: [B:248:0x04cc, B:250:0x04d2, B:266:0x04f4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0683 A[PHI: r0 r1
  0x0683: PHI (r0v7 int) = (r0v6 int), (r0v11 int) binds: [B:289:0x0555, B:387:0x0683] A[DONT_GENERATE, DONT_INLINE]
  0x0683: PHI (r1v1 p.uy) = (r1v0 p.uy), (r1v4 p.uy) binds: [B:289:0x0555, B:387:0x0683] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01d9 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k(p.uy r26, int r27, int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 1682
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.k(p.uy, int, int, int):void");
    }

    public final void l(String str, Integer num) {
        if ((str instanceof String) && (num instanceof Integer)) {
            if (this.u == null) {
                this.u = new HashMap();
            }
            int iIndexOf = str.indexOf("/");
            if (iIndexOf != -1) {
                str = str.substring(iIndexOf + 1);
            }
            this.u.put(str, Integer.valueOf(num.intValue()));
        }
    }

    public final void m(ty tyVar, dy dyVar, SparseArray sparseArray, int i, ConstraintAnchor$Type constraintAnchor$Type) {
        View view = (View) this.c.get(i);
        ty tyVar2 = (ty) sparseArray.get(i);
        if (tyVar2 == null || view == null || !(view.getLayoutParams() instanceof dy)) {
            return;
        }
        dyVar.c0 = true;
        ConstraintAnchor$Type constraintAnchor$Type2 = ConstraintAnchor$Type.m;
        if (constraintAnchor$Type == constraintAnchor$Type2) {
            dy dyVar2 = (dy) view.getLayoutParams();
            dyVar2.c0 = true;
            dyVar2.p0.E = true;
        }
        tyVar.g(constraintAnchor$Type2).a(tyVar2.g(constraintAnchor$Type), dyVar.D, dyVar.C);
        tyVar.E = true;
        tyVar.g(ConstraintAnchor$Type.e).g();
        tyVar.g(ConstraintAnchor$Type.l).g();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            dy dyVar = (dy) childAt.getLayoutParams();
            ty tyVar = dyVar.p0;
            if (childAt.getVisibility() != 8 || dyVar.d0 || dyVar.e0 || zIsInEditMode) {
                int iP = tyVar.p();
                int iQ = tyVar.q();
                childAt.layout(iP, iQ, tyVar.o() + iP, tyVar.i() + iQ);
            }
        }
        ArrayList arrayList = this.e;
        int size = arrayList.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((ay) arrayList.get(i6)).getClass();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:163:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0198  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 1569
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        ty tyVarH = h(view);
        if ((view instanceof Guideline) && !(tyVarH instanceof d11)) {
            dy dyVar = (dy) view.getLayoutParams();
            d11 d11Var = new d11();
            dyVar.p0 = d11Var;
            dyVar.d0 = true;
            d11Var.O(dyVar.V);
        }
        if (view instanceof ay) {
            ay ayVar = (ay) view;
            ayVar.e();
            ((dy) view.getLayoutParams()).e0 = true;
            ArrayList arrayList = this.e;
            if (!arrayList.contains(ayVar)) {
                arrayList.add(ayVar);
            }
        }
        this.c.put(view.getId(), view);
        this.f16p = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.c.remove(view.getId());
        ty tyVarH = h(view);
        this.k.p0.remove(tyVarH);
        tyVarH.A();
        this.e.remove(view);
        this.f16p = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.f16p = true;
        super.requestLayout();
    }

    public void setConstraintSet(py pyVar) {
        this.r = pyVar;
    }

    @Override // android.view.View
    public void setId(int i) {
        int id = getId();
        SparseArray sparseArray = this.c;
        sparseArray.remove(id);
        super.setId(i);
        sparseArray.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i == this.o) {
            return;
        }
        this.o = i;
        requestLayout();
    }

    public void setMaxWidth(int i) {
        if (i == this.n) {
            return;
        }
        this.n = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.m) {
            return;
        }
        this.m = i;
        requestLayout();
    }

    public void setMinWidth(int i) {
        if (i == this.l) {
            return;
        }
        this.l = i;
        requestLayout();
    }

    public void setOnConstraintsChanged(xy xyVar) {
        hy hyVar = this.s;
        if (hyVar != null) {
            hyVar.getClass();
        }
    }

    public void setOptimizationLevel(int i) {
        this.q = i;
        uy uyVar = this.k;
        uyVar.C0 = i;
        ra1.f178p = uyVar.S(512);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new SparseArray();
        this.e = new ArrayList(4);
        this.k = new uy();
        this.l = 0;
        this.m = 0;
        this.n = Integer.MAX_VALUE;
        this.o = Integer.MAX_VALUE;
        this.f16p = true;
        this.q = 257;
        this.r = null;
        this.s = null;
        this.t = -1;
        this.u = new HashMap();
        this.v = new SparseArray();
        this.w = new ey(this, this);
        i(attributeSet, i);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        dy dyVar = new dy(layoutParams);
        dyVar.a = -1;
        dyVar.b = -1;
        dyVar.c = -1.0f;
        dyVar.d = true;
        dyVar.e = -1;
        dyVar.f = -1;
        dyVar.g = -1;
        dyVar.h = -1;
        dyVar.i = -1;
        dyVar.j = -1;
        dyVar.k = -1;
        dyVar.l = -1;
        dyVar.m = -1;
        dyVar.n = -1;
        dyVar.o = -1;
        dyVar.f117p = -1;
        dyVar.q = 0;
        dyVar.r = 0.0f;
        dyVar.s = -1;
        dyVar.t = -1;
        dyVar.u = -1;
        dyVar.v = -1;
        dyVar.w = Integer.MIN_VALUE;
        dyVar.x = Integer.MIN_VALUE;
        dyVar.y = Integer.MIN_VALUE;
        dyVar.z = Integer.MIN_VALUE;
        dyVar.A = Integer.MIN_VALUE;
        dyVar.B = Integer.MIN_VALUE;
        dyVar.C = Integer.MIN_VALUE;
        dyVar.D = 0;
        dyVar.E = 0.5f;
        dyVar.F = 0.5f;
        dyVar.G = null;
        dyVar.H = -1.0f;
        dyVar.I = -1.0f;
        dyVar.J = 0;
        dyVar.K = 0;
        dyVar.L = 0;
        dyVar.M = 0;
        dyVar.N = 0;
        dyVar.O = 0;
        dyVar.P = 0;
        dyVar.Q = 0;
        dyVar.R = 1.0f;
        dyVar.S = 1.0f;
        dyVar.T = -1;
        dyVar.U = -1;
        dyVar.V = -1;
        dyVar.W = false;
        dyVar.X = false;
        dyVar.Y = null;
        dyVar.Z = 0;
        dyVar.a0 = true;
        dyVar.b0 = true;
        dyVar.c0 = false;
        dyVar.d0 = false;
        dyVar.e0 = false;
        dyVar.f0 = -1;
        dyVar.g0 = -1;
        dyVar.h0 = -1;
        dyVar.i0 = -1;
        dyVar.j0 = Integer.MIN_VALUE;
        dyVar.k0 = Integer.MIN_VALUE;
        dyVar.l0 = 0.5f;
        dyVar.p0 = new ty();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            ((ViewGroup.MarginLayoutParams) dyVar).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) dyVar).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) dyVar).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) dyVar).bottomMargin = marginLayoutParams.bottomMargin;
            dyVar.setMarginStart(marginLayoutParams.getMarginStart());
            dyVar.setMarginEnd(marginLayoutParams.getMarginEnd());
        }
        if (layoutParams instanceof dy) {
            dy dyVar2 = (dy) layoutParams;
            dyVar.a = dyVar2.a;
            dyVar.b = dyVar2.b;
            dyVar.c = dyVar2.c;
            dyVar.d = dyVar2.d;
            dyVar.e = dyVar2.e;
            dyVar.f = dyVar2.f;
            dyVar.g = dyVar2.g;
            dyVar.h = dyVar2.h;
            dyVar.i = dyVar2.i;
            dyVar.j = dyVar2.j;
            dyVar.k = dyVar2.k;
            dyVar.l = dyVar2.l;
            dyVar.m = dyVar2.m;
            dyVar.n = dyVar2.n;
            dyVar.o = dyVar2.o;
            dyVar.f117p = dyVar2.f117p;
            dyVar.q = dyVar2.q;
            dyVar.r = dyVar2.r;
            dyVar.s = dyVar2.s;
            dyVar.t = dyVar2.t;
            dyVar.u = dyVar2.u;
            dyVar.v = dyVar2.v;
            dyVar.w = dyVar2.w;
            dyVar.x = dyVar2.x;
            dyVar.y = dyVar2.y;
            dyVar.z = dyVar2.z;
            dyVar.A = dyVar2.A;
            dyVar.B = dyVar2.B;
            dyVar.C = dyVar2.C;
            dyVar.D = dyVar2.D;
            dyVar.E = dyVar2.E;
            dyVar.F = dyVar2.F;
            dyVar.G = dyVar2.G;
            dyVar.H = dyVar2.H;
            dyVar.I = dyVar2.I;
            dyVar.J = dyVar2.J;
            dyVar.K = dyVar2.K;
            dyVar.W = dyVar2.W;
            dyVar.X = dyVar2.X;
            dyVar.L = dyVar2.L;
            dyVar.M = dyVar2.M;
            dyVar.N = dyVar2.N;
            dyVar.P = dyVar2.P;
            dyVar.O = dyVar2.O;
            dyVar.Q = dyVar2.Q;
            dyVar.R = dyVar2.R;
            dyVar.S = dyVar2.S;
            dyVar.T = dyVar2.T;
            dyVar.U = dyVar2.U;
            dyVar.V = dyVar2.V;
            dyVar.a0 = dyVar2.a0;
            dyVar.b0 = dyVar2.b0;
            dyVar.c0 = dyVar2.c0;
            dyVar.d0 = dyVar2.d0;
            dyVar.f0 = dyVar2.f0;
            dyVar.g0 = dyVar2.g0;
            dyVar.h0 = dyVar2.h0;
            dyVar.i0 = dyVar2.i0;
            dyVar.j0 = dyVar2.j0;
            dyVar.k0 = dyVar2.k0;
            dyVar.l0 = dyVar2.l0;
            dyVar.Y = dyVar2.Y;
            dyVar.Z = dyVar2.Z;
            dyVar.p0 = dyVar2.p0;
        }
        return dyVar;
    }
}
