package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.VirtualLayout;
import androidx.constraintlayout.widget.e;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CircularFlow extends VirtualLayout {
    private static int m;
    private static float n;
    ConstraintLayout o;
    int p;
    private float[] q;
    private int[] r;
    private int s;
    private int t;
    private String u;
    private String v;
    private Float w;
    private Integer x;

    public CircularFlow(Context context) {
        super(context);
    }

    private void A() {
        this.o = (ConstraintLayout) getParent();
        for (int i = 0; i < this.f1029c; i++) {
            View viewK = this.o.k(this.f1028b[i]);
            if (viewK != null) {
                int i2 = m;
                float f = n;
                int[] iArr = this.r;
                if (iArr == null || i >= iArr.length) {
                    Integer num = this.x;
                    if (num == null || num.intValue() == -1) {
                        Log.e("CircularFlow", "Added radius to view with id: " + this.j.get(Integer.valueOf(viewK.getId())));
                    } else {
                        this.s++;
                        if (this.r == null) {
                            this.r = new int[1];
                        }
                        int[] radius = getRadius();
                        this.r = radius;
                        radius[this.s - 1] = i2;
                    }
                } else {
                    i2 = iArr[i];
                }
                float[] fArr = this.q;
                if (fArr == null || i >= fArr.length) {
                    Float f2 = this.w;
                    if (f2 == null || f2.floatValue() == -1.0f) {
                        Log.e("CircularFlow", "Added angle to view with id: " + this.j.get(Integer.valueOf(viewK.getId())));
                    } else {
                        this.t++;
                        if (this.q == null) {
                            this.q = new float[1];
                        }
                        float[] angles = getAngles();
                        this.q = angles;
                        angles[this.t - 1] = f;
                    }
                } else {
                    f = fArr[i];
                }
                ConstraintLayout.b bVar = (ConstraintLayout.b) viewK.getLayoutParams();
                bVar.r = f;
                bVar.p = this.p;
                bVar.q = i2;
                viewK.setLayoutParams(bVar);
            }
        }
        h();
    }

    private void setAngles(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        this.t = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                y(str.substring(i).trim());
                return;
            } else {
                y(str.substring(i, iIndexOf).trim());
                i = iIndexOf + 1;
            }
        }
    }

    private void setRadius(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        this.s = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                z(str.substring(i).trim());
                return;
            } else {
                z(str.substring(i, iIndexOf).trim());
                i = iIndexOf + 1;
            }
        }
    }

    private void y(String str) {
        float[] fArr;
        if (str == null || str.length() == 0 || this.f1030d == null || (fArr = this.q) == null) {
            return;
        }
        if (this.t + 1 > fArr.length) {
            this.q = Arrays.copyOf(fArr, fArr.length + 1);
        }
        this.q[this.t] = Integer.parseInt(str);
        this.t++;
    }

    private void z(String str) {
        int[] iArr;
        if (str == null || str.length() == 0 || this.f1030d == null || (iArr = this.r) == null) {
            return;
        }
        if (this.s + 1 > iArr.length) {
            this.r = Arrays.copyOf(iArr, iArr.length + 1);
        }
        this.r[this.s] = (int) (Integer.parseInt(str) * this.f1030d.getResources().getDisplayMetrics().density);
        this.s++;
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.q, this.t);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.r, this.s);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.x1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.T1) {
                    this.p = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                } else if (index == e.P1) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.u = string;
                    setAngles(string);
                } else if (index == e.S1) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.v = string2;
                    setRadius(string2);
                } else if (index == e.Q1) {
                    Float fValueOf = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(index, n));
                    this.w = fValueOf;
                    setDefaultAngle(fValueOf.floatValue());
                } else if (index == e.R1) {
                    Integer numValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, m));
                    this.x = numValueOf;
                    setDefaultRadius(numValueOf.intValue());
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.u;
        if (str != null) {
            this.q = new float[1];
            setAngles(str);
        }
        String str2 = this.v;
        if (str2 != null) {
            this.r = new int[1];
            setRadius(str2);
        }
        Float f = this.w;
        if (f != null) {
            setDefaultAngle(f.floatValue());
        }
        Integer num = this.x;
        if (num != null) {
            setDefaultRadius(num.intValue());
        }
        A();
    }

    public void setDefaultAngle(float f) {
        n = f;
    }

    public void setDefaultRadius(int i) {
        m = i;
    }

    public CircularFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularFlow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
