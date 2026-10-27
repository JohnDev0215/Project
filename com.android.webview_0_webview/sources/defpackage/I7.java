package defpackage;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.InflateException;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class I7 {
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0327, code lost:
    
        if (r27 == null) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0329, code lost:
    
        if (r13 == null) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x032b, code lost:
    
        r1 = new android.animation.Animator[r13.size()];
        r2 = r13.iterator();
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x033a, code lost:
    
        if (r2.hasNext() == false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x033c, code lost:
    
        r1[r3] = (android.animation.Animator) r2.next();
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0348, code lost:
    
        if (r28 != 0) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x034a, code lost:
    
        r27.playTogether(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x034e, code lost:
    
        r27.playSequentially(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0351, code lost:
    
        return r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0300  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.animation.Animator a(android.content.Context r22, android.content.res.Resources r23, android.content.res.Resources.Theme r24, org.xmlpull.v1.XmlPullParser r25, android.util.AttributeSet r26, android.animation.AnimatorSet r27, int r28, float r29) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 850
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.I7.a(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    public static Keyframe b(Keyframe keyframe, float f) {
        return keyframe.getType() == Float.TYPE ? Keyframe.ofFloat(f) : keyframe.getType() == Integer.TYPE ? Keyframe.ofInt(f) : Keyframe.ofObject(f);
    }

    public static PropertyValuesHolder c(TypedArray typedArray, int i, int i2, int i3, String str) {
        PropertyValuesHolder propertyValuesHolderOfInt;
        PropertyValuesHolder propertyValuesHolderOfObject;
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        boolean z = typedValuePeekValue != null;
        int i4 = z ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i3);
        boolean z2 = typedValuePeekValue2 != null;
        int i5 = z2 ? typedValuePeekValue2.type : 0;
        if (i == 4) {
            i = ((z && d(i4)) || (z2 && d(i5))) ? 3 : 0;
        }
        boolean z3 = i == 0;
        PropertyValuesHolder propertyValuesHolder = null;
        if (i != 2) {
            J7 j7 = i == 3 ? J7.a : null;
            if (z3) {
                if (z) {
                    float dimension = i4 == 5 ? typedArray.getDimension(i2, 0.0f) : typedArray.getFloat(i2, 0.0f);
                    if (z2) {
                        propertyValuesHolderOfInt = PropertyValuesHolder.ofFloat(str, dimension, i5 == 5 ? typedArray.getDimension(i3, 0.0f) : typedArray.getFloat(i3, 0.0f));
                    } else {
                        propertyValuesHolderOfInt = PropertyValuesHolder.ofFloat(str, dimension);
                    }
                } else {
                    propertyValuesHolderOfInt = PropertyValuesHolder.ofFloat(str, i5 == 5 ? typedArray.getDimension(i3, 0.0f) : typedArray.getFloat(i3, 0.0f));
                }
            } else {
                if (!z) {
                    if (z2) {
                        propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, i5 == 5 ? (int) typedArray.getDimension(i3, 0.0f) : d(i5) ? typedArray.getColor(i3, 0) : typedArray.getInt(i3, 0));
                    }
                    if (propertyValuesHolder == null && j7 != null) {
                        propertyValuesHolder.setEvaluator(j7);
                        return propertyValuesHolder;
                    }
                }
                int dimension2 = i4 == 5 ? (int) typedArray.getDimension(i2, 0.0f) : d(i4) ? typedArray.getColor(i2, 0) : typedArray.getInt(i2, 0);
                if (z2) {
                    propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, dimension2, i5 == 5 ? (int) typedArray.getDimension(i3, 0.0f) : d(i5) ? typedArray.getColor(i3, 0) : typedArray.getInt(i3, 0));
                } else {
                    propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, dimension2);
                }
            }
            propertyValuesHolder = propertyValuesHolderOfInt;
            return propertyValuesHolder == null ? propertyValuesHolder : propertyValuesHolder;
        }
        String string = typedArray.getString(i2);
        String string2 = typedArray.getString(i3);
        C1305p2[] c1305p2ArrC = AbstractC1360q2.c(string);
        C1305p2[] c1305p2ArrC2 = AbstractC1360q2.c(string2);
        if (c1305p2ArrC == null && c1305p2ArrC2 == null) {
            return null;
        }
        if (c1305p2ArrC == null) {
            if (c1305p2ArrC2 != null) {
                return PropertyValuesHolder.ofObject(str, new H7(), c1305p2ArrC2);
            }
            return null;
        }
        H7 h7 = new H7();
        if (c1305p2ArrC2 == null) {
            propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, h7, c1305p2ArrC);
        } else {
            if (!AbstractC1360q2.a(c1305p2ArrC, c1305p2ArrC2)) {
                throw new InflateException(" Can't morph from " + string + " to " + string2);
            }
            propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, h7, c1305p2ArrC, c1305p2ArrC2);
        }
        return propertyValuesHolderOfObject;
    }

    public static boolean d(int i) {
        return i >= 28 && i <= 31;
    }

    public static ValueAnimator e(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f, XmlPullParser xmlPullParser) {
        ValueAnimator valueAnimator2;
        TypedArray typedArray;
        TypedArray typedArray2;
        ValueAnimator valueAnimator3;
        TypedArray typedArrayG = AbstractC1195n2.g(resources, theme, attributeSet, C7.g);
        TypedArray typedArrayG2 = AbstractC1195n2.g(resources, theme, attributeSet, C7.k);
        ValueAnimator valueAnimator4 = valueAnimator == null ? new ValueAnimator() : valueAnimator;
        long jD = AbstractC1195n2.d(typedArrayG, xmlPullParser, "duration", 1, 300);
        int resourceId = 0;
        long jD2 = AbstractC1195n2.d(typedArrayG, xmlPullParser, "startOffset", 2, 0);
        int iD = AbstractC1195n2.d(typedArrayG, xmlPullParser, "valueType", 7, 4);
        if (AbstractC1195n2.f(xmlPullParser, "valueFrom") && AbstractC1195n2.f(xmlPullParser, "valueTo")) {
            if (iD == 4) {
                TypedValue typedValuePeekValue = typedArrayG.peekValue(5);
                boolean z = typedValuePeekValue != null;
                int i = z ? typedValuePeekValue.type : 0;
                TypedValue typedValuePeekValue2 = typedArrayG.peekValue(6);
                boolean z2 = typedValuePeekValue2 != null;
                iD = ((z && d(i)) || (z2 && d(z2 ? typedValuePeekValue2.type : 0))) ? 3 : 0;
            }
            PropertyValuesHolder propertyValuesHolderC = c(typedArrayG, iD, 5, 6, "");
            if (propertyValuesHolderC != null) {
                valueAnimator4.setValues(propertyValuesHolderC);
            }
        }
        valueAnimator4.setDuration(jD);
        valueAnimator4.setStartDelay(jD2);
        valueAnimator4.setRepeatCount(AbstractC1195n2.d(typedArrayG, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator4.setRepeatMode(AbstractC1195n2.d(typedArrayG, xmlPullParser, "repeatMode", 4, 1));
        if (typedArrayG2 != null) {
            ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator4;
            String strE = AbstractC1195n2.e(typedArrayG2, xmlPullParser, "pathData", 1);
            if (strE != null) {
                String strE2 = AbstractC1195n2.e(typedArrayG2, xmlPullParser, "propertyXName", 2);
                String strE3 = AbstractC1195n2.e(typedArrayG2, xmlPullParser, "propertyYName", 3);
                if (strE2 == null && strE3 == null) {
                    throw new InflateException(typedArrayG2.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
                }
                Path pathD = AbstractC1360q2.d(strE);
                float f2 = 0.5f * f;
                PathMeasure pathMeasure = new PathMeasure(pathD, false);
                ArrayList arrayList = new ArrayList();
                arrayList.add(Float.valueOf(0.0f));
                float length = 0.0f;
                do {
                    length += pathMeasure.getLength();
                    arrayList.add(Float.valueOf(length));
                } while (pathMeasure.nextContour());
                PathMeasure pathMeasure2 = new PathMeasure(pathD, false);
                int iMin = Math.min(100, ((int) (length / f2)) + 1);
                float[] fArr = new float[iMin];
                float[] fArr2 = new float[iMin];
                float[] fArr3 = new float[2];
                float f3 = length / (iMin - 1);
                valueAnimator2 = valueAnimator4;
                typedArray = typedArrayG;
                int i2 = 0;
                float f4 = 0.0f;
                while (true) {
                    if (resourceId >= iMin) {
                        break;
                    }
                    int i3 = iMin;
                    pathMeasure2.getPosTan(f4 - ((Float) arrayList.get(i2)).floatValue(), fArr3, null);
                    fArr[resourceId] = fArr3[0];
                    fArr2[resourceId] = fArr3[1];
                    f4 += f3;
                    int i4 = i2 + 1;
                    if (i4 < arrayList.size() && f4 > ((Float) arrayList.get(i4)).floatValue()) {
                        pathMeasure2.nextContour();
                        i2 = i4;
                    }
                    resourceId++;
                    iMin = i3;
                }
                PropertyValuesHolder propertyValuesHolderOfFloat = strE2 != null ? PropertyValuesHolder.ofFloat(strE2, fArr) : null;
                PropertyValuesHolder propertyValuesHolderOfFloat2 = strE3 != null ? PropertyValuesHolder.ofFloat(strE3, fArr2) : null;
                if (propertyValuesHolderOfFloat == null) {
                    resourceId = 0;
                    objectAnimator.setValues(propertyValuesHolderOfFloat2);
                } else {
                    resourceId = 0;
                    if (propertyValuesHolderOfFloat2 == null) {
                        objectAnimator.setValues(propertyValuesHolderOfFloat);
                    } else {
                        objectAnimator.setValues(propertyValuesHolderOfFloat, propertyValuesHolderOfFloat2);
                    }
                }
            } else {
                valueAnimator2 = valueAnimator4;
                typedArray = typedArrayG;
                objectAnimator.setPropertyName(AbstractC1195n2.e(typedArrayG2, xmlPullParser, "propertyName", 0));
            }
        } else {
            valueAnimator2 = valueAnimator4;
            typedArray = typedArrayG;
        }
        if (AbstractC1195n2.f(xmlPullParser, "interpolator")) {
            typedArray2 = typedArray;
            resourceId = typedArray2.getResourceId(resourceId, resourceId);
        } else {
            typedArray2 = typedArray;
        }
        if (resourceId > 0) {
            valueAnimator3 = valueAnimator2;
            valueAnimator3.setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
        } else {
            valueAnimator3 = valueAnimator2;
        }
        typedArray2.recycle();
        if (typedArrayG2 != null) {
            typedArrayG2.recycle();
        }
        return valueAnimator3;
    }
}
