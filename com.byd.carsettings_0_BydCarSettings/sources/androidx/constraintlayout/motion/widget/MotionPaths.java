package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MotionPaths implements Comparable<MotionPaths> {
    public static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    public static final int OFF_HEIGHT = 4;
    public static final int OFF_PATH_ROTATE = 5;
    public static final int OFF_POSITION = 0;
    public static final int OFF_WIDTH = 3;
    public static final int OFF_X = 1;
    public static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    public static final int PERPENDICULAR = 1;
    public static final int SCREEN = 3;
    public static final String TAG = "MotionPaths";
    public static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    public LinkedHashMap<String, ConstraintAttribute> attributes;
    public float height;
    public int mDrawPath;
    public Easing mKeyFrameEasing;
    public int mMode;
    public int mPathMotionArc;
    public float mPathRotate;
    public float mProgress;
    public double[] mTempDelta;
    public double[] mTempValue;
    public float position;
    public float time;
    public float width;
    public float x;
    public float y;

    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    private boolean diff(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    private static final float xRotate(float f2, float f3, float f4, float f5, float f6, float f7) {
        return (((f6 - f4) * f3) - ((f7 - f5) * f2)) + f4;
    }

    private static final float yRotate(float f2, float f3, float f4, float f5, float f6, float f7) {
        return ((f6 - f4) * f2) + ((f7 - f5) * f3) + f5;
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.mPathMotionArc = motion.mPathMotionArc;
        this.mPathRotate = motion.mPathRotate;
        this.mDrawPath = motion.mDrawPath;
        this.mProgress = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.getType() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.attributes.put(str, constraintAttribute);
            }
        }
    }

    public void different(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | diff(this.position, motionPaths.position);
        zArr[1] = zArr[1] | diff(this.x, motionPaths.x) | z;
        zArr[2] = z | diff(this.y, motionPaths.y) | zArr[2];
        zArr[3] = zArr[3] | diff(this.width, motionPaths.width);
        zArr[4] = diff(this.height, motionPaths.height) | zArr[4];
    }

    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.position, this.x, this.y, this.width, this.height, this.mPathRotate};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    public void getBounds(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f2 = this.width;
        float f3 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f4 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 3) {
                f2 = f4;
            } else if (i3 == 4) {
                f3 = f4;
            }
        }
        fArr[i] = f2;
        fArr[i + 1] = f3;
    }

    public void getCenter(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f2 = this.x;
        float f3 = this.y;
        float f4 = this.width;
        float f5 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f6 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f2 = f6;
            } else if (i3 == 2) {
                f3 = f6;
            } else if (i3 == 3) {
                f4 = f6;
            } else if (i3 == 4) {
                f5 = f6;
            }
        }
        fArr[i] = f2 + (f4 / 2.0f) + 0.0f;
        fArr[i + 1] = f3 + (f5 / 2.0f) + 0.0f;
    }

    public int getCustomData(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = this.attributes.get(str);
        if (constraintAttribute.noOfInterpValues() == 1) {
            dArr[i] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int iNoOfInterpValues = constraintAttribute.noOfInterpValues();
        constraintAttribute.getValuesToInterpolate(new float[iNoOfInterpValues]);
        int i2 = 0;
        while (i2 < iNoOfInterpValues) {
            dArr[i] = r1[i2];
            i2++;
            i++;
        }
        return iNoOfInterpValues;
    }

    public int getCustomDataCount(String str) {
        return this.attributes.get(str).noOfInterpValues();
    }

    public void getRect(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f2 = this.x;
        float f3 = this.y;
        float f4 = this.width;
        float f5 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f6 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f2 = f6;
            } else if (i3 == 2) {
                f3 = f6;
            } else if (i3 == 3) {
                f4 = f6;
            } else if (i3 == 4) {
                f5 = f6;
            }
        }
        float f7 = f4 + f2;
        float f8 = f5 + f3;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f2 + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = f3 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f7 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f3 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f7 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f8 + 0.0f;
        fArr[i9] = f2 + 0.0f;
        fArr[i9 + 1] = f8 + 0.0f;
    }

    public boolean hasCustomData(String str) {
        return this.attributes.containsKey(str);
    }

    public void initCartesian(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.mFramePosition / 100.0f;
        this.time = f2;
        this.mDrawPath = keyPosition.mDrawPath;
        float f3 = Float.isNaN(keyPosition.mPercentWidth) ? f2 : keyPosition.mPercentWidth;
        float f4 = Float.isNaN(keyPosition.mPercentHeight) ? f2 : keyPosition.mPercentHeight;
        float f5 = motionPaths2.width;
        float f6 = motionPaths.width;
        float f7 = motionPaths2.height;
        float f8 = motionPaths.height;
        this.position = this.time;
        float f9 = motionPaths.x;
        float f10 = motionPaths.y;
        float f11 = (motionPaths2.x + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (motionPaths2.y + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = ((f5 - f6) * f3) / 2.0f;
        this.x = (int) ((f9 + (f11 * f2)) - f13);
        float f14 = ((f7 - f8) * f4) / 2.0f;
        this.y = (int) ((f10 + (f12 * f2)) - f14);
        this.width = (int) (f6 + r9);
        this.height = (int) (f8 + r12);
        float f15 = Float.isNaN(keyPosition.mPercentX) ? f2 : keyPosition.mPercentX;
        float f16 = Float.isNaN(keyPosition.mAltPercentY) ? 0.0f : keyPosition.mAltPercentY;
        if (!Float.isNaN(keyPosition.mPercentY)) {
            f2 = keyPosition.mPercentY;
        }
        float f17 = Float.isNaN(keyPosition.mAltPercentX) ? 0.0f : keyPosition.mAltPercentX;
        this.mMode = 2;
        this.x = (int) (((motionPaths.x + (f15 * f11)) + (f17 * f12)) - f13);
        this.y = (int) (((motionPaths.y + (f11 * f16)) + (f12 * f2)) - f14);
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initPath(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.mFramePosition / 100.0f;
        this.time = f2;
        this.mDrawPath = keyPosition.mDrawPath;
        float f3 = Float.isNaN(keyPosition.mPercentWidth) ? f2 : keyPosition.mPercentWidth;
        float f4 = Float.isNaN(keyPosition.mPercentHeight) ? f2 : keyPosition.mPercentHeight;
        float f5 = motionPaths2.width - motionPaths.width;
        float f6 = motionPaths2.height - motionPaths.height;
        this.position = this.time;
        if (!Float.isNaN(keyPosition.mPercentX)) {
            f2 = keyPosition.mPercentX;
        }
        float f7 = motionPaths.x;
        float f8 = motionPaths.width;
        float f9 = motionPaths.y;
        float f10 = motionPaths.height;
        float f11 = (motionPaths2.x + (motionPaths2.width / 2.0f)) - ((f8 / 2.0f) + f7);
        float f12 = (motionPaths2.y + (motionPaths2.height / 2.0f)) - ((f10 / 2.0f) + f9);
        float f13 = f11 * f2;
        float f14 = (f5 * f3) / 2.0f;
        this.x = (int) ((f7 + f13) - f14);
        float f15 = f2 * f12;
        float f16 = (f6 * f4) / 2.0f;
        this.y = (int) ((f9 + f15) - f16);
        this.width = (int) (f8 + r7);
        this.height = (int) (f10 + r8);
        float f17 = Float.isNaN(keyPosition.mPercentY) ? 0.0f : keyPosition.mPercentY;
        this.mMode = 1;
        float f18 = (int) ((motionPaths.x + f13) - f14);
        this.x = f18;
        float f19 = (int) ((motionPaths.y + f15) - f16);
        this.y = f19;
        this.x = f18 + ((-f12) * f17);
        this.y = f19 + (f11 * f17);
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initScreen(int i, int i2, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.mFramePosition / 100.0f;
        this.time = f2;
        this.mDrawPath = keyPosition.mDrawPath;
        float f3 = Float.isNaN(keyPosition.mPercentWidth) ? f2 : keyPosition.mPercentWidth;
        float f4 = Float.isNaN(keyPosition.mPercentHeight) ? f2 : keyPosition.mPercentHeight;
        float f5 = motionPaths2.width;
        float f6 = motionPaths.width;
        float f7 = motionPaths2.height;
        float f8 = motionPaths.height;
        this.position = this.time;
        float f9 = motionPaths.x;
        float f10 = motionPaths.y;
        float f11 = motionPaths2.x + (f5 / 2.0f);
        float f12 = motionPaths2.y + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.x = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.y = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.width = (int) (f6 + f13);
        this.height = (int) (f8 + f14);
        this.mMode = 3;
        if (!Float.isNaN(keyPosition.mPercentX)) {
            this.x = (int) (keyPosition.mPercentX * ((int) (i - this.width)));
        }
        if (!Float.isNaN(keyPosition.mPercentY)) {
            this.y = (int) (keyPosition.mPercentY * ((int) (i2 - this.height)));
        }
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void setBounds(float f2, float f3, float f4, float f5) {
        this.x = f2;
        this.y = f3;
        this.width = f4;
        this.height = f5;
    }

    public void setDpDt(float f2, float f3, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f8 = (float) dArr[i];
            double d2 = dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f4 = f8;
            } else if (i2 == 2) {
                f6 = f8;
            } else if (i2 == 3) {
                f5 = f8;
            } else if (i2 == 4) {
                f7 = f8;
            }
        }
        float f9 = f4 - ((0.0f * f5) / 2.0f);
        float f10 = f6 - ((0.0f * f7) / 2.0f);
        fArr[0] = (f9 * (1.0f - f2)) + (((f5 * 1.0f) + f9) * f2) + 0.0f;
        fArr[1] = (f10 * (1.0f - f3)) + (((f7 * 1.0f) + f10) * f3) + 0.0f;
    }

    public void setView(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f2;
        boolean z;
        float f3 = this.x;
        float f4 = this.y;
        float f5 = this.width;
        float f6 = this.height;
        boolean z2 = true;
        if (iArr.length != 0 && this.mTempValue.length <= iArr[iArr.length - 1]) {
            int i = iArr[iArr.length - 1] + 1;
            this.mTempValue = new double[i];
            this.mTempDelta = new double[i];
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.mTempValue[iArr[i2]] = dArr[i2];
            this.mTempDelta[iArr[i2]] = dArr2[i2];
        }
        int i3 = 0;
        float f7 = Float.NaN;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        while (true) {
            double[] dArr4 = this.mTempValue;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f2 = f3;
                z = z2;
            } else {
                double d2 = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.mTempValue[i3])) {
                    d2 = this.mTempValue[i3] + d2;
                }
                f2 = f3;
                float f12 = (float) d2;
                float f13 = (float) this.mTempDelta[i3];
                z = true;
                if (i3 == 1) {
                    f8 = f13;
                    f3 = f12;
                } else if (i3 == 2) {
                    f4 = f12;
                    f10 = f13;
                } else if (i3 == 3) {
                    f5 = f12;
                    f9 = f13;
                } else if (i3 == 4) {
                    f6 = f12;
                    f11 = f13;
                } else if (i3 == 5) {
                    f3 = f2;
                    f7 = f12;
                }
                i3++;
                z2 = z;
            }
            f3 = f2;
            i3++;
            z2 = z;
        }
        float f14 = f3;
        boolean z3 = z2;
        if (!Float.isNaN(f7)) {
            view.setRotation((float) (((double) (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN)) + ((double) f7) + Math.toDegrees(Math.atan2(f10 + (f11 / 2.0f), f8 + (f9 / 2.0f)))));
        } else if (!Float.isNaN(Float.NaN)) {
            view.setRotation(Float.NaN);
        }
        float f15 = f14 + 0.5f;
        int i4 = (int) f15;
        float f16 = f4 + 0.5f;
        int i5 = (int) f16;
        int i6 = (int) (f15 + f5);
        int i7 = (int) (f16 + f6);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if ((i8 == view.getMeasuredWidth() && i9 == view.getMeasuredHeight()) ? false : z3) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(i9, 1073741824));
        }
        view.layout(i4, i5, i6, i7);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull MotionPaths motionPaths) {
        return Float.compare(this.position, motionPaths.position);
    }

    public MotionPaths(int i, int i2, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        int i3 = keyPosition.mPositionType;
        if (i3 == 1) {
            initPath(keyPosition, motionPaths, motionPaths2);
        } else if (i3 != 2) {
            initCartesian(keyPosition, motionPaths, motionPaths2);
        } else {
            initScreen(i, i2, keyPosition, motionPaths, motionPaths2);
        }
    }
}
