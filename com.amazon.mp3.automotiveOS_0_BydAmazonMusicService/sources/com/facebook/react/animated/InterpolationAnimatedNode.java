package com.facebook.react.animated;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes6.dex */
class InterpolationAnimatedNode extends ValueAnimatedNode {
    private static final Pattern fpPattern = Pattern.compile("[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?");
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final boolean mHasStringOutput;
    private final double[] mInputRange;
    private int mNumVals;
    private final double[] mOutputRange;
    private double[][] mOutputs;

    @Nullable
    private ValueAnimatedNode mParent;
    private String mPattern;
    private final Matcher mSOutputMatcher;
    private boolean mShouldRound;

    private static double[] fromDoubleArray(ReadableArray readableArray) {
        int size = readableArray.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = readableArray.getDouble(i);
        }
        return dArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static double interpolate(double r13, double r15, double r17, double r19, double r21, java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.interpolate(double, double, double, double, double, java.lang.String, java.lang.String):double");
    }

    static double interpolate(double d, double[] dArr, double[] dArr2, String str, String str2) {
        int iFindRangeIndex = findRangeIndex(d, dArr);
        int i = iFindRangeIndex + 1;
        return interpolate(d, dArr[iFindRangeIndex], dArr[i], dArr2[iFindRangeIndex], dArr2[i], str, str2);
    }

    private static int findRangeIndex(double d, double[] dArr) {
        int i = 1;
        while (i < dArr.length - 1 && dArr[i] < d) {
            i++;
        }
        return i - 1;
    }

    public InterpolationAnimatedNode(ReadableMap readableMap) {
        this.mInputRange = fromDoubleArray(readableMap.getArray("inputRange"));
        ReadableArray array = readableMap.getArray("outputRange");
        boolean z = array.getType(0) == ReadableType.String;
        this.mHasStringOutput = z;
        if (z) {
            int size = array.size();
            this.mOutputRange = new double[size];
            String string = array.getString(0);
            this.mPattern = string;
            this.mShouldRound = string.startsWith("rgb");
            this.mSOutputMatcher = fpPattern.matcher(this.mPattern);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                Matcher matcher = fpPattern.matcher(array.getString(i));
                ArrayList arrayList2 = new ArrayList();
                arrayList.add(arrayList2);
                while (matcher.find()) {
                    arrayList2.add(Double.valueOf(Double.parseDouble(matcher.group())));
                }
                this.mOutputRange[i] = ((Double) arrayList2.get(0)).doubleValue();
            }
            int size2 = ((ArrayList) arrayList.get(0)).size();
            this.mNumVals = size2;
            this.mOutputs = new double[size2][];
            for (int i2 = 0; i2 < this.mNumVals; i2++) {
                double[] dArr = new double[size];
                this.mOutputs[i2] = dArr;
                for (int i3 = 0; i3 < size; i3++) {
                    dArr[i3] = ((Double) ((ArrayList) arrayList.get(i3)).get(i2)).doubleValue();
                }
            }
        } else {
            this.mOutputRange = fromDoubleArray(array);
            this.mSOutputMatcher = null;
        }
        this.mExtrapolateLeft = readableMap.getString("extrapolateLeft");
        this.mExtrapolateRight = readableMap.getString("extrapolateRight");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onAttachedToNode(AnimatedNode animatedNode) {
        if (this.mParent != null) {
            throw new IllegalStateException("Parent already attached");
        }
        if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
        this.mParent = (ValueAnimatedNode) animatedNode;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onDetachedFromNode(AnimatedNode animatedNode) {
        if (animatedNode != this.mParent) {
            throw new IllegalArgumentException("Invalid parent node provided");
        }
        this.mParent = null;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        ValueAnimatedNode valueAnimatedNode = this.mParent;
        if (valueAnimatedNode == null) {
            return;
        }
        double value = valueAnimatedNode.getValue();
        double dInterpolate = interpolate(value, this.mInputRange, this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
        this.mValue = dInterpolate;
        if (this.mHasStringOutput) {
            if (this.mNumVals > 1) {
                StringBuffer stringBuffer = new StringBuffer(this.mPattern.length());
                this.mSOutputMatcher.reset();
                int i = 0;
                while (this.mSOutputMatcher.find()) {
                    int i2 = i + 1;
                    double dInterpolate2 = interpolate(value, this.mInputRange, this.mOutputs[i], this.mExtrapolateLeft, this.mExtrapolateRight);
                    if (this.mShouldRound) {
                        boolean z = i2 == 4;
                        if (z) {
                            dInterpolate2 *= 1000.0d;
                        }
                        int iRound = (int) Math.round(dInterpolate2);
                        this.mSOutputMatcher.appendReplacement(stringBuffer, z ? Double.toString(((double) iRound) / 1000.0d) : Integer.toString(iRound));
                    } else {
                        int i3 = (int) dInterpolate2;
                        this.mSOutputMatcher.appendReplacement(stringBuffer, ((double) i3) != dInterpolate2 ? Double.toString(dInterpolate2) : Integer.toString(i3));
                    }
                    i = i2;
                }
                this.mSOutputMatcher.appendTail(stringBuffer);
                this.mAnimatedObject = stringBuffer.toString();
                return;
            }
            this.mAnimatedObject = this.mSOutputMatcher.replaceFirst(String.valueOf(dInterpolate));
        }
    }

    @Override // com.facebook.react.animated.ValueAnimatedNode, com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        return "InterpolationAnimatedNode[" + this.mTag + "] super: " + super.prettyPrint();
    }
}
