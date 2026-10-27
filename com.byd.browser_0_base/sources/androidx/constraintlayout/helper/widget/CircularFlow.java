package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CircularFlow extends VirtualLayout {
    public static float A;
    public static int z;
    public ConstraintLayout p;
    public int q;
    public float[] r;
    public int[] s;
    public int t;
    public int u;
    public String v;
    public String w;
    public Float x;
    public Integer y;

    public CircularFlow(Context context) {
        super(context);
    }

    private void setAngles(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        this.u = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                v(str.substring(i).trim());
                return;
            } else {
                v(str.substring(i, iIndexOf).trim());
                i = iIndexOf + 1;
            }
        }
    }

    private void setRadius(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        this.t = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                w(str.substring(i).trim());
                return;
            } else {
                w(str.substring(i, iIndexOf).trim());
                i = iIndexOf + 1;
            }
        }
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.r, this.u);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.s, this.t);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public final void n(AttributeSet attributeSet) {
        super.n(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_circularflow_viewCenter) {
                    this.q = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_circularflow_angles) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.v = string;
                    setAngles(string);
                } else if (index == R$styleable.ConstraintLayout_Layout_circularflow_radiusInDP) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.w = string2;
                    setRadius(string2);
                } else if (index == R$styleable.ConstraintLayout_Layout_circularflow_defaultAngle) {
                    Float fValueOf = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(index, A));
                    this.x = fValueOf;
                    setDefaultAngle(fValueOf.floatValue());
                } else if (index == R$styleable.ConstraintLayout_Layout_circularflow_defaultRadius) {
                    Integer numValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, z));
                    this.y = numValueOf;
                    setDefaultRadius(numValueOf.intValue());
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.v;
        if (str != null) {
            this.r = new float[1];
            setAngles(str);
        }
        String str2 = this.w;
        if (str2 != null) {
            this.s = new int[1];
            setRadius(str2);
        }
        Float f = this.x;
        if (f != null) {
            setDefaultAngle(f.floatValue());
        }
        Integer num = this.y;
        if (num != null) {
            setDefaultRadius(num.intValue());
        }
        this.p = (ConstraintLayout) getParent();
        for (int i = 0; i < this.f; i++) {
            View viewC = this.p.c(this.e[i]);
            if (viewC != null) {
                int i2 = z;
                float f2 = A;
                int[] iArr = this.s;
                HashMap<Integer, String> map = this.m;
                if (iArr == null || i >= iArr.length) {
                    Integer num2 = this.y;
                    if (num2 == null || num2.intValue() == -1) {
                        Log.e("CircularFlow", "Added radius to view with id: " + map.get(Integer.valueOf(viewC.getId())));
                    } else {
                        this.t++;
                        if (this.s == null) {
                            this.s = new int[1];
                        }
                        int[] radius = getRadius();
                        this.s = radius;
                        radius[this.t - 1] = i2;
                    }
                } else {
                    i2 = iArr[i];
                }
                float[] fArr = this.r;
                if (fArr == null || i >= fArr.length) {
                    Float f3 = this.x;
                    if (f3 == null || f3.floatValue() == -1.0f) {
                        Log.e("CircularFlow", "Added angle to view with id: " + map.get(Integer.valueOf(viewC.getId())));
                    } else {
                        this.u++;
                        if (this.r == null) {
                            this.r = new float[1];
                        }
                        float[] angles = getAngles();
                        this.r = angles;
                        angles[this.u - 1] = f2;
                    }
                } else {
                    f2 = fArr[i];
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewC.getLayoutParams();
                layoutParams.r = f2;
                layoutParams.p = this.q;
                layoutParams.q = i2;
                viewC.setLayoutParams(layoutParams);
            }
        }
        h();
    }

    public void setDefaultAngle(float f) {
        A = f;
    }

    public void setDefaultRadius(int i) {
        z = i;
    }

    public final void v(String str) {
        float[] fArr;
        if (str == null || str.length() == 0 || this.g == null || (fArr = this.r) == null) {
            return;
        }
        if (this.u + 1 > fArr.length) {
            this.r = Arrays.copyOf(fArr, fArr.length + 1);
        }
        this.r[this.u] = Integer.parseInt(str);
        this.u++;
    }

    public final void w(String str) {
        Context context;
        int[] iArr;
        if (str == null || str.length() == 0 || (context = this.g) == null || (iArr = this.s) == null) {
            return;
        }
        if (this.t + 1 > iArr.length) {
            this.s = Arrays.copyOf(iArr, iArr.length + 1);
        }
        this.s[this.t] = (int) (Integer.parseInt(str) * context.getResources().getDisplayMetrics().density);
        this.t++;
    }

    public CircularFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularFlow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
