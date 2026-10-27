package com.airbnb.lottie.parser;

import android.graphics.Color;
import androidx.annotation.IntRange;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GradientColorParser implements ValueParser<GradientColor> {
    private int colorPoints;

    public GradientColorParser(int i) {
        this.colorPoints = i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008c  */
    @Override // com.airbnb.lottie.parser.ValueParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.airbnb.lottie.model.content.GradientColor parse(com.airbnb.lottie.parser.moshi.JsonReader r13, float r14) throws java.io.IOException {
        /*
            r12 = this;
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r0 = r13.peek()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r1 = com.airbnb.lottie.parser.moshi.JsonReader.Token.BEGIN_ARRAY
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L11
            r0 = r2
            goto L12
        L11:
            r0 = r3
        L12:
            if (r0 == 0) goto L17
            r13.beginArray()
        L17:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L2a
            double r4 = r13.nextDouble()
            float r1 = (float) r4
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            r14.add(r1)
            goto L17
        L2a:
            if (r0 == 0) goto L2f
            r13.endArray()
        L2f:
            int r13 = r12.colorPoints
            r0 = -1
            if (r13 != r0) goto L3c
            int r13 = r14.size()
            int r13 = r13 / 4
            r12.colorPoints = r13
        L3c:
            int r13 = r12.colorPoints
            float[] r0 = new float[r13]
            int[] r13 = new int[r13]
            r1 = r3
            r4 = r1
        L44:
            int r5 = r12.colorPoints
            int r5 = r5 * 4
            if (r3 >= r5) goto L92
            int r5 = r3 / 4
            java.lang.Object r6 = r14.get(r3)
            java.lang.Float r6 = (java.lang.Float) r6
            float r6 = r6.floatValue()
            double r6 = (double) r6
            int r8 = r3 % 4
            if (r8 == 0) goto L7a
            r9 = 4643176031446892544(0x406fe00000000000, double:255.0)
            if (r8 == r2) goto L77
            r11 = 2
            if (r8 == r11) goto L74
            r11 = 3
            if (r8 == r11) goto L69
            goto L8f
        L69:
            double r6 = r6 * r9
            int r6 = (int) r6
            r7 = 255(0xff, float:3.57E-43)
            int r6 = android.graphics.Color.argb(r7, r1, r4, r6)
            r13[r5] = r6
            goto L8f
        L74:
            double r6 = r6 * r9
            int r4 = (int) r6
            goto L8f
        L77:
            double r6 = r6 * r9
            int r1 = (int) r6
            goto L8f
        L7a:
            if (r5 <= 0) goto L8c
            int r8 = r5 + (-1)
            r8 = r0[r8]
            float r9 = (float) r6
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 < 0) goto L8c
            r6 = 1008981770(0x3c23d70a, float:0.01)
            float r9 = r9 + r6
            r0[r5] = r9
            goto L8f
        L8c:
            float r6 = (float) r6
            r0[r5] = r6
        L8f:
            int r3 = r3 + 1
            goto L44
        L92:
            com.airbnb.lottie.model.content.GradientColor r1 = new com.airbnb.lottie.model.content.GradientColor
            r1.<init>(r0, r13)
            r12.addOpacityStopsToGradientIfNeeded(r1, r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.GradientColorParser.parse(com.airbnb.lottie.parser.moshi.JsonReader, float):com.airbnb.lottie.model.content.GradientColor");
    }

    private void addOpacityStopsToGradientIfNeeded(GradientColor gradientColor, List<Float> list) {
        int i = this.colorPoints * 4;
        if (list.size() <= i) {
            return;
        }
        int size = (list.size() - i) / 2;
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        int i2 = 0;
        while (i < list.size()) {
            if (i % 2 == 0) {
                dArr[i2] = list.get(i).floatValue();
            } else {
                dArr2[i2] = list.get(i).floatValue();
                i2++;
            }
            i++;
        }
        for (int i3 = 0; i3 < gradientColor.getSize(); i3++) {
            int i4 = gradientColor.getColors()[i3];
            gradientColor.getColors()[i3] = Color.argb(getOpacityAtPosition(gradientColor.getPositions()[i3], dArr, dArr2), Color.red(i4), Color.green(i4), Color.blue(i4));
        }
    }

    @IntRange(from = 0, to = 255)
    private int getOpacityAtPosition(double d, double[] dArr, double[] dArr2) {
        double dLerp;
        int i = 1;
        while (true) {
            if (i < dArr.length) {
                int i2 = i - 1;
                double d2 = dArr[i2];
                double d3 = dArr[i];
                if (d3 >= d) {
                    dLerp = MiscUtils.lerp(dArr2[i2], dArr2[i], MiscUtils.clamp((d - d2) / (d3 - d2), 0.0d, 1.0d));
                    break;
                }
                i++;
            } else {
                dLerp = dArr2[dArr2.length - 1];
                break;
            }
        }
        return (int) (dLerp * 255.0d);
    }
}
