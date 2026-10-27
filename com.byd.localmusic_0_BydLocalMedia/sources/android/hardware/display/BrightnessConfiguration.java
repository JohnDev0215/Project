package android.hardware.display;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.android.internal.util.Preconditions;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class BrightnessConfiguration implements Parcelable {
    private static final String ATTR_CATEGORY = "category";
    private static final String ATTR_COLLECT_COLOR = "collect-color";
    private static final String ATTR_DESCRIPTION = "description";
    private static final String ATTR_LUX = "lux";
    private static final String ATTR_MODEL_LOWER_BOUND = "model-lower-bound";
    private static final String ATTR_MODEL_TIMEOUT = "model-timeout";
    private static final String ATTR_MODEL_UPPER_BOUND = "model-upper-bound";
    private static final String ATTR_NITS = "nits";
    private static final String ATTR_PACKAGE_NAME = "package-name";
    public static final Parcelable.Creator<BrightnessConfiguration> CREATOR = new Parcelable.Creator<BrightnessConfiguration>() { // from class: android.hardware.display.BrightnessConfiguration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BrightnessConfiguration createFromParcel(Parcel parcel) {
            Builder builder = new Builder(parcel.createFloatArray(), parcel.createFloatArray());
            int i = parcel.readInt();
            for (int i2 = 0; i2 < i; i2++) {
                builder.addCorrectionByPackageName(parcel.readString(), BrightnessCorrection.CREATOR.createFromParcel(parcel));
            }
            int i3 = parcel.readInt();
            for (int i4 = 0; i4 < i3; i4++) {
                builder.addCorrectionByCategory(parcel.readInt(), BrightnessCorrection.CREATOR.createFromParcel(parcel));
            }
            builder.setDescription(parcel.readString());
            builder.setShouldCollectColorSamples(parcel.readBoolean());
            builder.setShortTermModelTimeoutMillis(parcel.readLong());
            builder.setShortTermModelLowerLuxMultiplier(parcel.readFloat());
            builder.setShortTermModelUpperLuxMultiplier(parcel.readFloat());
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BrightnessConfiguration[] newArray(int i) {
            return new BrightnessConfiguration[i];
        }
    };
    public static final long SHORT_TERM_TIMEOUT_UNSET = -1;
    private static final String TAG_BRIGHTNESS_CORRECTION = "brightness-correction";
    private static final String TAG_BRIGHTNESS_CORRECTIONS = "brightness-corrections";
    private static final String TAG_BRIGHTNESS_CURVE = "brightness-curve";
    private static final String TAG_BRIGHTNESS_PARAMS = "brightness-params";
    private static final String TAG_BRIGHTNESS_POINT = "brightness-point";
    private final Map<Integer, BrightnessCorrection> mCorrectionsByCategory;
    private final Map<String, BrightnessCorrection> mCorrectionsByPackageName;
    private final String mDescription;
    private final float[] mLux;
    private final float[] mNits;
    private final float mShortTermModelLowerLuxMultiplier;
    private final long mShortTermModelTimeout;
    private final float mShortTermModelUpperLuxMultiplier;
    private final boolean mShouldCollectColorSamples;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private BrightnessConfiguration(float[] fArr, float[] fArr2, Map<String, BrightnessCorrection> map, Map<Integer, BrightnessCorrection> map2, String str, boolean z, long j, float f, float f2) {
        this.mLux = fArr;
        this.mNits = fArr2;
        this.mCorrectionsByPackageName = map;
        this.mCorrectionsByCategory = map2;
        this.mDescription = str;
        this.mShouldCollectColorSamples = z;
        this.mShortTermModelTimeout = j;
        this.mShortTermModelLowerLuxMultiplier = f;
        this.mShortTermModelUpperLuxMultiplier = f2;
    }

    public Pair<float[], float[]> getCurve() {
        float[] fArr = this.mLux;
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        float[] fArr2 = this.mNits;
        return Pair.create(fArrCopyOf, Arrays.copyOf(fArr2, fArr2.length));
    }

    public BrightnessCorrection getCorrectionByPackageName(String str) {
        return this.mCorrectionsByPackageName.get(str);
    }

    public BrightnessCorrection getCorrectionByCategory(int i) {
        return this.mCorrectionsByCategory.get(Integer.valueOf(i));
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean shouldCollectColorSamples() {
        return this.mShouldCollectColorSamples;
    }

    public long getShortTermModelTimeoutMillis() {
        return this.mShortTermModelTimeout;
    }

    public float getShortTermModelUpperLuxMultiplier() {
        return this.mShortTermModelUpperLuxMultiplier;
    }

    public float getShortTermModelLowerLuxMultiplier() {
        return this.mShortTermModelLowerLuxMultiplier;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloatArray(this.mLux);
        parcel.writeFloatArray(this.mNits);
        parcel.writeInt(this.mCorrectionsByPackageName.size());
        for (Map.Entry<String, BrightnessCorrection> entry : this.mCorrectionsByPackageName.entrySet()) {
            String key = entry.getKey();
            BrightnessCorrection value = entry.getValue();
            parcel.writeString(key);
            value.writeToParcel(parcel, i);
        }
        parcel.writeInt(this.mCorrectionsByCategory.size());
        for (Map.Entry<Integer, BrightnessCorrection> entry2 : this.mCorrectionsByCategory.entrySet()) {
            int iIntValue = entry2.getKey().intValue();
            BrightnessCorrection value2 = entry2.getValue();
            parcel.writeInt(iIntValue);
            value2.writeToParcel(parcel, i);
        }
        parcel.writeString(this.mDescription);
        parcel.writeBoolean(this.mShouldCollectColorSamples);
        parcel.writeLong(this.mShortTermModelTimeout);
        parcel.writeFloat(this.mShortTermModelLowerLuxMultiplier);
        parcel.writeFloat(this.mShortTermModelUpperLuxMultiplier);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BrightnessConfiguration{[");
        int length = this.mLux.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append("(");
            sb.append(this.mLux[i]);
            sb.append(", ");
            sb.append(this.mNits[i]);
            sb.append(")");
        }
        sb.append("], {");
        for (Map.Entry<String, BrightnessCorrection> entry : this.mCorrectionsByPackageName.entrySet()) {
            sb.append("'" + entry.getKey() + "': " + entry.getValue() + ", ");
        }
        for (Map.Entry<Integer, BrightnessCorrection> entry2 : this.mCorrectionsByCategory.entrySet()) {
            sb.append(entry2.getKey() + ": " + entry2.getValue() + ", ");
        }
        sb.append("}, '");
        String str = this.mDescription;
        if (str != null) {
            sb.append(str);
        }
        sb.append(", shouldCollectColorSamples = " + this.mShouldCollectColorSamples);
        if (this.mShortTermModelTimeout >= 0) {
            sb.append(", shortTermModelTimeout = " + this.mShortTermModelTimeout);
        }
        if (!Float.isNaN(this.mShortTermModelLowerLuxMultiplier)) {
            sb.append(", shortTermModelLowerLuxMultiplier = " + this.mShortTermModelLowerLuxMultiplier);
        }
        if (!Float.isNaN(this.mShortTermModelLowerLuxMultiplier)) {
            sb.append(", shortTermModelUpperLuxMultiplier = " + this.mShortTermModelUpperLuxMultiplier);
        }
        sb.append("'}");
        return sb.toString();
    }

    public int hashCode() {
        int iHashCode = ((((((Arrays.hashCode(this.mLux) + 31) * 31) + Arrays.hashCode(this.mNits)) * 31) + this.mCorrectionsByPackageName.hashCode()) * 31) + this.mCorrectionsByCategory.hashCode();
        String str = this.mDescription;
        if (str != null) {
            iHashCode = (iHashCode * 31) + str.hashCode();
        }
        return (((((((iHashCode * 31) + Boolean.hashCode(this.mShouldCollectColorSamples)) * 31) + Long.hashCode(this.mShortTermModelTimeout)) * 31) + Float.hashCode(this.mShortTermModelLowerLuxMultiplier)) * 31) + Float.hashCode(this.mShortTermModelUpperLuxMultiplier);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BrightnessConfiguration)) {
            return false;
        }
        BrightnessConfiguration brightnessConfiguration = (BrightnessConfiguration) obj;
        return Arrays.equals(this.mLux, brightnessConfiguration.mLux) && Arrays.equals(this.mNits, brightnessConfiguration.mNits) && this.mCorrectionsByPackageName.equals(brightnessConfiguration.mCorrectionsByPackageName) && this.mCorrectionsByCategory.equals(brightnessConfiguration.mCorrectionsByCategory) && Objects.equals(this.mDescription, brightnessConfiguration.mDescription) && this.mShouldCollectColorSamples == brightnessConfiguration.mShouldCollectColorSamples && this.mShortTermModelTimeout == brightnessConfiguration.mShortTermModelTimeout && checkFloatEquals(this.mShortTermModelLowerLuxMultiplier, brightnessConfiguration.mShortTermModelLowerLuxMultiplier) && checkFloatEquals(this.mShortTermModelUpperLuxMultiplier, brightnessConfiguration.mShortTermModelUpperLuxMultiplier);
    }

    private boolean checkFloatEquals(float f, float f2) {
        return (Float.isNaN(f) && Float.isNaN(f2)) || f == f2;
    }

    public void saveToXml(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, TAG_BRIGHTNESS_CURVE);
        String str = this.mDescription;
        if (str != null) {
            xmlSerializer.attribute(null, "description", str);
        }
        for (int i = 0; i < this.mLux.length; i++) {
            xmlSerializer.startTag(null, TAG_BRIGHTNESS_POINT);
            xmlSerializer.attribute(null, ATTR_LUX, Float.toString(this.mLux[i]));
            xmlSerializer.attribute(null, ATTR_NITS, Float.toString(this.mNits[i]));
            xmlSerializer.endTag(null, TAG_BRIGHTNESS_POINT);
        }
        xmlSerializer.endTag(null, TAG_BRIGHTNESS_CURVE);
        xmlSerializer.startTag(null, TAG_BRIGHTNESS_CORRECTIONS);
        for (Map.Entry<String, BrightnessCorrection> entry : this.mCorrectionsByPackageName.entrySet()) {
            String key = entry.getKey();
            BrightnessCorrection value = entry.getValue();
            xmlSerializer.startTag(null, TAG_BRIGHTNESS_CORRECTION);
            xmlSerializer.attribute(null, ATTR_PACKAGE_NAME, key);
            value.saveToXml(xmlSerializer);
            xmlSerializer.endTag(null, TAG_BRIGHTNESS_CORRECTION);
        }
        for (Map.Entry<Integer, BrightnessCorrection> entry2 : this.mCorrectionsByCategory.entrySet()) {
            int iIntValue = entry2.getKey().intValue();
            BrightnessCorrection value2 = entry2.getValue();
            xmlSerializer.startTag(null, TAG_BRIGHTNESS_CORRECTION);
            xmlSerializer.attribute(null, "category", Integer.toString(iIntValue));
            value2.saveToXml(xmlSerializer);
            xmlSerializer.endTag(null, TAG_BRIGHTNESS_CORRECTION);
        }
        xmlSerializer.endTag(null, TAG_BRIGHTNESS_CORRECTIONS);
        xmlSerializer.startTag(null, TAG_BRIGHTNESS_PARAMS);
        if (this.mShouldCollectColorSamples) {
            xmlSerializer.attribute(null, ATTR_COLLECT_COLOR, Boolean.toString(true));
        }
        long j = this.mShortTermModelTimeout;
        if (j >= 0) {
            xmlSerializer.attribute(null, ATTR_MODEL_TIMEOUT, Long.toString(j));
        }
        if (!Float.isNaN(this.mShortTermModelLowerLuxMultiplier)) {
            xmlSerializer.attribute(null, ATTR_MODEL_LOWER_BOUND, Float.toString(this.mShortTermModelLowerLuxMultiplier));
        }
        if (!Float.isNaN(this.mShortTermModelUpperLuxMultiplier)) {
            xmlSerializer.attribute(null, ATTR_MODEL_UPPER_BOUND, Float.toString(this.mShortTermModelUpperLuxMultiplier));
        }
        xmlSerializer.endTag(null, TAG_BRIGHTNESS_PARAMS);
    }

    public static BrightnessConfiguration loadFromXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String str;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        int depth = xmlPullParser.getDepth();
        float fLoadFloatFromXml = Float.NaN;
        String str2 = null;
        String attributeValue = null;
        long jLongValue = -1;
        boolean z = false;
        float fLoadFloatFromXml2 = Float.NaN;
        while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
            if (TAG_BRIGHTNESS_CURVE.equals(xmlPullParser.getName())) {
                attributeValue = xmlPullParser.getAttributeValue(str2, "description");
                int depth2 = xmlPullParser.getDepth();
                while (XmlUtils.nextElementWithin(xmlPullParser, depth2)) {
                    if (TAG_BRIGHTNESS_POINT.equals(xmlPullParser.getName())) {
                        float fLoadFloatFromXml3 = loadFloatFromXml(xmlPullParser, ATTR_LUX);
                        float fLoadFloatFromXml4 = loadFloatFromXml(xmlPullParser, ATTR_NITS);
                        arrayList.add(Float.valueOf(fLoadFloatFromXml3));
                        arrayList2.add(Float.valueOf(fLoadFloatFromXml4));
                    }
                }
            } else {
                if (TAG_BRIGHTNESS_CORRECTIONS.equals(xmlPullParser.getName())) {
                    int depth3 = xmlPullParser.getDepth();
                    while (XmlUtils.nextElementWithin(xmlPullParser, depth3)) {
                        if (TAG_BRIGHTNESS_CORRECTION.equals(xmlPullParser.getName())) {
                            String attributeValue2 = xmlPullParser.getAttributeValue(str2, ATTR_PACKAGE_NAME);
                            String attributeValue3 = xmlPullParser.getAttributeValue(str2, "category");
                            BrightnessCorrection brightnessCorrectionLoadFromXml = BrightnessCorrection.loadFromXml(xmlPullParser);
                            if (attributeValue2 != null) {
                                map.put(attributeValue2, brightnessCorrectionLoadFromXml);
                            } else if (attributeValue3 != null) {
                                try {
                                    map2.put(Integer.valueOf(Integer.parseInt(attributeValue3)), brightnessCorrectionLoadFromXml);
                                } catch (NullPointerException | NumberFormatException unused) {
                                }
                            }
                            str2 = null;
                        }
                    }
                    str = str2;
                } else if (TAG_BRIGHTNESS_PARAMS.equals(xmlPullParser.getName())) {
                    str = null;
                    z = Boolean.parseBoolean(xmlPullParser.getAttributeValue(null, ATTR_COLLECT_COLOR));
                    Long lLoadLongFromXml = loadLongFromXml(xmlPullParser, ATTR_MODEL_TIMEOUT);
                    if (lLoadLongFromXml != null) {
                        jLongValue = lLoadLongFromXml.longValue();
                    }
                    fLoadFloatFromXml = loadFloatFromXml(xmlPullParser, ATTR_MODEL_LOWER_BOUND);
                    fLoadFloatFromXml2 = loadFloatFromXml(xmlPullParser, ATTR_MODEL_UPPER_BOUND);
                } else {
                    str = null;
                }
                str2 = str;
            }
        }
        int size = arrayList.size();
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = ((Float) arrayList.get(i)).floatValue();
            fArr2[i] = ((Float) arrayList2.get(i)).floatValue();
        }
        Builder builder = new Builder(fArr, fArr2);
        builder.setDescription(attributeValue);
        for (Map.Entry entry : map.entrySet()) {
            builder.addCorrectionByPackageName((String) entry.getKey(), (BrightnessCorrection) entry.getValue());
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            builder.addCorrectionByCategory(((Integer) entry2.getKey()).intValue(), (BrightnessCorrection) entry2.getValue());
        }
        builder.setShouldCollectColorSamples(z);
        builder.setShortTermModelTimeoutMillis(jLongValue);
        builder.setShortTermModelLowerLuxMultiplier(fLoadFloatFromXml);
        builder.setShortTermModelUpperLuxMultiplier(fLoadFloatFromXml2);
        return builder.build();
    }

    private static float loadFloatFromXml(XmlPullParser xmlPullParser, String str) {
        try {
            return Float.parseFloat(xmlPullParser.getAttributeValue(null, str));
        } catch (NullPointerException | NumberFormatException unused) {
            return Float.NaN;
        }
    }

    private static Long loadLongFromXml(XmlPullParser xmlPullParser, String str) {
        try {
            return Long.valueOf(Long.parseLong(xmlPullParser.getAttributeValue(null, str)));
        } catch (NullPointerException | NumberFormatException unused) {
            return null;
        }
    }

    public static class Builder {
        private static final int MAX_CORRECTIONS_BY_CATEGORY = 20;
        private static final int MAX_CORRECTIONS_BY_PACKAGE_NAME = 20;
        private Map<Integer, BrightnessCorrection> mCorrectionsByCategory;
        private Map<String, BrightnessCorrection> mCorrectionsByPackageName;
        private float[] mCurveLux;
        private float[] mCurveNits;
        private String mDescription;
        private boolean mShouldCollectColorSamples;
        private long mShortTermModelTimeout = -1;
        private float mShortTermModelLowerLuxMultiplier = Float.NaN;
        private float mShortTermModelUpperLuxMultiplier = Float.NaN;

        public int getMaxCorrectionsByCategory() {
            return 20;
        }

        public int getMaxCorrectionsByPackageName() {
            return 20;
        }

        public Builder(float[] fArr, float[] fArr2) {
            Objects.requireNonNull(fArr);
            Objects.requireNonNull(fArr2);
            if (fArr.length == 0 || fArr2.length == 0) {
                throw new IllegalArgumentException("Lux and nits arrays must not be empty");
            }
            if (fArr.length != fArr2.length) {
                throw new IllegalArgumentException("Lux and nits arrays must be the same length");
            }
            if (fArr[0] != 0.0f) {
                throw new IllegalArgumentException("Initial control point must be for 0 lux");
            }
            Preconditions.checkArrayElementsInRange(fArr, 0.0f, Float.MAX_VALUE, BrightnessConfiguration.ATTR_LUX);
            Preconditions.checkArrayElementsInRange(fArr2, 0.0f, Float.MAX_VALUE, BrightnessConfiguration.ATTR_NITS);
            checkMonotonic(fArr, true, BrightnessConfiguration.ATTR_LUX);
            checkMonotonic(fArr2, false, BrightnessConfiguration.ATTR_NITS);
            this.mCurveLux = fArr;
            this.mCurveNits = fArr2;
            this.mCorrectionsByPackageName = new HashMap();
            this.mCorrectionsByCategory = new HashMap();
        }

        public Builder addCorrectionByPackageName(String str, BrightnessCorrection brightnessCorrection) {
            Objects.requireNonNull(str, "packageName must not be null");
            Objects.requireNonNull(brightnessCorrection, "correction must not be null");
            if (this.mCorrectionsByPackageName.size() >= getMaxCorrectionsByPackageName()) {
                throw new IllegalArgumentException("Too many corrections by package name");
            }
            this.mCorrectionsByPackageName.put(str, brightnessCorrection);
            return this;
        }

        public Builder addCorrectionByCategory(int i, BrightnessCorrection brightnessCorrection) {
            Objects.requireNonNull(brightnessCorrection, "correction must not be null");
            if (this.mCorrectionsByCategory.size() >= getMaxCorrectionsByCategory()) {
                throw new IllegalArgumentException("Too many corrections by category");
            }
            this.mCorrectionsByCategory.put(Integer.valueOf(i), brightnessCorrection);
            return this;
        }

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public Builder setShouldCollectColorSamples(boolean z) {
            this.mShouldCollectColorSamples = z;
            return this;
        }

        public Builder setShortTermModelTimeoutMillis(long j) {
            this.mShortTermModelTimeout = j;
            return this;
        }

        public Builder setShortTermModelUpperLuxMultiplier(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Negative lux multiplier");
            }
            this.mShortTermModelUpperLuxMultiplier = f;
            return this;
        }

        public Builder setShortTermModelLowerLuxMultiplier(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Negative lux multiplier");
            }
            this.mShortTermModelLowerLuxMultiplier = f;
            return this;
        }

        public BrightnessConfiguration build() {
            if (this.mCurveLux == null || this.mCurveNits == null) {
                throw new IllegalStateException("A curve must be set!");
            }
            return new BrightnessConfiguration(this.mCurveLux, this.mCurveNits, this.mCorrectionsByPackageName, this.mCorrectionsByCategory, this.mDescription, this.mShouldCollectColorSamples, this.mShortTermModelTimeout, this.mShortTermModelLowerLuxMultiplier, this.mShortTermModelUpperLuxMultiplier);
        }

        private static void checkMonotonic(float[] fArr, boolean z, String str) {
            if (fArr.length <= 1) {
                return;
            }
            float f = fArr[0];
            for (int i = 1; i < fArr.length; i++) {
                if (f > fArr[i] || (f == fArr[i] && z)) {
                    throw new IllegalArgumentException(str + " values must be " + (z ? "strictly increasing" : "monotonic"));
                }
                f = fArr[i];
            }
        }
    }
}
