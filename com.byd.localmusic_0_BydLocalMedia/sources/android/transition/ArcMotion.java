package android.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.android.internal.R;

/* JADX INFO: loaded from: classes3.dex */
public class ArcMotion extends PathMotion {
    private static final float DEFAULT_MAX_ANGLE_DEGREES = 70.0f;
    private static final float DEFAULT_MAX_TANGENT = (float) Math.tan(Math.toRadians(35.0d));
    private static final float DEFAULT_MIN_ANGLE_DEGREES = 0.0f;
    private float mMaximumAngle;
    private float mMaximumTangent;
    private float mMinimumHorizontalAngle;
    private float mMinimumHorizontalTangent;
    private float mMinimumVerticalAngle;
    private float mMinimumVerticalTangent;

    public ArcMotion() {
        this.mMinimumHorizontalAngle = 0.0f;
        this.mMinimumVerticalAngle = 0.0f;
        this.mMaximumAngle = DEFAULT_MAX_ANGLE_DEGREES;
        this.mMinimumHorizontalTangent = 0.0f;
        this.mMinimumVerticalTangent = 0.0f;
        this.mMaximumTangent = DEFAULT_MAX_TANGENT;
    }

    public ArcMotion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMinimumHorizontalAngle = 0.0f;
        this.mMinimumVerticalAngle = 0.0f;
        this.mMaximumAngle = DEFAULT_MAX_ANGLE_DEGREES;
        this.mMinimumHorizontalTangent = 0.0f;
        this.mMinimumVerticalTangent = 0.0f;
        this.mMaximumTangent = DEFAULT_MAX_TANGENT;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ArcMotion);
        setMinimumVerticalAngle(typedArrayObtainStyledAttributes.getFloat(1, 0.0f));
        setMinimumHorizontalAngle(typedArrayObtainStyledAttributes.getFloat(0, 0.0f));
        setMaximumAngle(typedArrayObtainStyledAttributes.getFloat(2, DEFAULT_MAX_ANGLE_DEGREES));
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setMinimumHorizontalAngle(float f) {
        this.mMinimumHorizontalAngle = f;
        this.mMinimumHorizontalTangent = toTangent(f);
    }

    public float getMinimumHorizontalAngle() {
        return this.mMinimumHorizontalAngle;
    }

    public void setMinimumVerticalAngle(float f) {
        this.mMinimumVerticalAngle = f;
        this.mMinimumVerticalTangent = toTangent(f);
    }

    public float getMinimumVerticalAngle() {
        return this.mMinimumVerticalAngle;
    }

    public void setMaximumAngle(float f) {
        this.mMaximumAngle = f;
        this.mMaximumTangent = toTangent(f);
    }

    public float getMaximumAngle() {
        return this.mMaximumAngle;
    }

    private static float toTangent(float f) {
        if (f < 0.0f || f > 90.0f) {
            throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        }
        return (float) Math.tan(Math.toRadians(f / 2.0f));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009b  */
    @Override // android.transition.PathMotion
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Path getPath(float r16, float r17, float r18, float r19) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            android.graphics.Path r8 = new android.graphics.Path
            r8.<init>()
            r8.moveTo(r1, r2)
            float r3 = r18 - r1
            float r4 = r19 - r2
            float r5 = r3 * r3
            float r6 = r4 * r4
            float r5 = r5 + r6
            float r6 = r1 + r18
            r7 = 1073741824(0x40000000, float:2.0)
            float r6 = r6 / r7
            float r9 = r2 + r19
            float r9 = r9 / r7
            r10 = 1048576000(0x3e800000, float:0.25)
            float r10 = r10 * r5
            int r11 = (r2 > r19 ? 1 : (r2 == r19 ? 0 : -1))
            if (r11 <= 0) goto L27
            r11 = 1
            goto L28
        L27:
            r11 = 0
        L28:
            r12 = 0
            int r13 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            r14 = 1056964608(0x3f000000, float:0.5)
            if (r13 != 0) goto L3c
            float r3 = java.lang.Math.abs(r3)
            float r3 = r3 * r14
            float r4 = r0.mMinimumHorizontalTangent
            float r3 = r3 * r4
            float r3 = r3 + r9
            r4 = r3
            r3 = r6
        L3a:
            r11 = r12
            goto L7c
        L3c:
            int r13 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r13 != 0) goto L4b
            float r3 = java.lang.Math.abs(r4)
            float r3 = r3 * r14
            float r4 = r0.mMinimumVerticalTangent
            float r3 = r3 * r4
            float r3 = r3 + r6
            r4 = r9
            goto L3a
        L4b:
            float r13 = java.lang.Math.abs(r3)
            float r14 = java.lang.Math.abs(r4)
            int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
            if (r13 >= 0) goto L6b
            float r4 = r4 * r7
            float r5 = r5 / r4
            float r3 = java.lang.Math.abs(r5)
            if (r11 == 0) goto L65
            float r3 = r19 + r3
            r4 = r3
            r3 = r18
            goto L68
        L65:
            float r3 = r3 + r2
            r4 = r3
            r3 = r1
        L68:
            float r5 = r0.mMinimumVerticalTangent
            goto L79
        L6b:
            float r3 = r3 * r7
            float r5 = r5 / r3
            if (r11 == 0) goto L73
            float r3 = r1 + r5
            r4 = r2
            goto L77
        L73:
            float r3 = r18 - r5
            r4 = r19
        L77:
            float r5 = r0.mMinimumHorizontalTangent
        L79:
            float r11 = r10 * r5
            float r11 = r11 * r5
        L7c:
            float r5 = r6 - r3
            float r13 = r9 - r4
            float r5 = r5 * r5
            float r13 = r13 * r13
            float r5 = r5 + r13
            float r13 = r0.mMaximumTangent
            float r10 = r10 * r13
            float r10 = r10 * r13
            int r13 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r13 == 0) goto L90
            int r13 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r13 >= 0) goto L90
            goto L97
        L90:
            int r11 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r11 <= 0) goto L96
            r11 = r10
            goto L97
        L96:
            r11 = r12
        L97:
            int r10 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r10 == 0) goto La9
            float r11 = r11 / r5
            double r10 = (double) r11
            double r10 = java.lang.Math.sqrt(r10)
            float r5 = (float) r10
            float r3 = r3 - r6
            float r3 = r3 * r5
            float r3 = r3 + r6
            float r4 = r4 - r9
            float r5 = r5 * r4
            float r4 = r9 + r5
        La9:
            float r1 = r1 + r3
            float r5 = r1 / r7
            float r1 = r2 + r4
            float r6 = r1 / r7
            float r3 = r3 + r18
            float r9 = r3 / r7
            float r4 = r4 + r19
            float r7 = r4 / r7
            r1 = r8
            r2 = r5
            r3 = r6
            r4 = r9
            r5 = r7
            r6 = r18
            r7 = r19
            r1.cubicTo(r2, r3, r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.transition.ArcMotion.getPath(float, float, float, float):android.graphics.Path");
    }
}
