package android.hardware.display;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.MathUtils;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class BrightnessCorrection implements Parcelable {
    public static final Parcelable.Creator<BrightnessCorrection> CREATOR = new Parcelable.Creator<BrightnessCorrection>() { // from class: android.hardware.display.BrightnessCorrection.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BrightnessCorrection createFromParcel(Parcel parcel) {
            if (parcel.readInt() != 1) {
                return null;
            }
            return ScaleAndTranslateLog.readFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BrightnessCorrection[] newArray(int i) {
            return new BrightnessCorrection[i];
        }
    };
    private static final int SCALE_AND_TRANSLATE_LOG = 1;
    private static final String TAG_SCALE_AND_TRANSLATE_LOG = "scale-and-translate-log";
    private BrightnessCorrectionImplementation mImplementation;

    private interface BrightnessCorrectionImplementation {
        float apply(float f);

        void saveToXml(XmlSerializer xmlSerializer) throws IOException;

        String toString();

        void writeToParcel(Parcel parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private BrightnessCorrection(BrightnessCorrectionImplementation brightnessCorrectionImplementation) {
        this.mImplementation = brightnessCorrectionImplementation;
    }

    public static BrightnessCorrection createScaleAndTranslateLog(float f, float f2) {
        return new BrightnessCorrection(new ScaleAndTranslateLog(f, f2));
    }

    public float apply(float f) {
        return this.mImplementation.apply(f);
    }

    public String toString() {
        return this.mImplementation.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BrightnessCorrection) {
            return ((BrightnessCorrection) obj).mImplementation.equals(this.mImplementation);
        }
        return false;
    }

    public int hashCode() {
        return this.mImplementation.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.mImplementation.writeToParcel(parcel);
    }

    public void saveToXml(XmlSerializer xmlSerializer) throws IOException {
        this.mImplementation.saveToXml(xmlSerializer);
    }

    public static BrightnessCorrection loadFromXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
            if (TAG_SCALE_AND_TRANSLATE_LOG.equals(xmlPullParser.getName())) {
                return ScaleAndTranslateLog.loadFromXml(xmlPullParser);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float loadFloatFromXml(XmlPullParser xmlPullParser, String str) {
        try {
            return Float.parseFloat(xmlPullParser.getAttributeValue(null, str));
        } catch (NullPointerException | NumberFormatException unused) {
            return Float.NaN;
        }
    }

    private static class ScaleAndTranslateLog implements BrightnessCorrectionImplementation {
        private static final String ATTR_SCALE = "scale";
        private static final String ATTR_TRANSLATE = "translate";
        private static final float MAX_SCALE = 2.0f;
        private static final float MAX_TRANSLATE = 0.7f;
        private static final float MIN_SCALE = 0.5f;
        private static final float MIN_TRANSLATE = -0.6f;
        private final float mScale;
        private final float mTranslate;

        ScaleAndTranslateLog(float f, float f2) {
            if (Float.isNaN(f) || Float.isNaN(f2)) {
                throw new IllegalArgumentException("scale and translate must be numbers");
            }
            this.mScale = MathUtils.constrain(f, 0.5f, MAX_SCALE);
            this.mTranslate = MathUtils.constrain(f2, MIN_TRANSLATE, MAX_TRANSLATE);
        }

        @Override // android.hardware.display.BrightnessCorrection.BrightnessCorrectionImplementation
        public float apply(float f) {
            return MathUtils.exp((this.mScale * MathUtils.log(f)) + this.mTranslate);
        }

        @Override // android.hardware.display.BrightnessCorrection.BrightnessCorrectionImplementation
        public String toString() {
            return "ScaleAndTranslateLog(" + this.mScale + ", " + this.mTranslate + ")";
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ScaleAndTranslateLog)) {
                return false;
            }
            ScaleAndTranslateLog scaleAndTranslateLog = (ScaleAndTranslateLog) obj;
            return scaleAndTranslateLog.mScale == this.mScale && scaleAndTranslateLog.mTranslate == this.mTranslate;
        }

        public int hashCode() {
            return ((Float.hashCode(this.mScale) + 31) * 31) + Float.hashCode(this.mTranslate);
        }

        @Override // android.hardware.display.BrightnessCorrection.BrightnessCorrectionImplementation
        public void writeToParcel(Parcel parcel) {
            parcel.writeInt(1);
            parcel.writeFloat(this.mScale);
            parcel.writeFloat(this.mTranslate);
        }

        @Override // android.hardware.display.BrightnessCorrection.BrightnessCorrectionImplementation
        public void saveToXml(XmlSerializer xmlSerializer) throws IOException {
            xmlSerializer.startTag(null, BrightnessCorrection.TAG_SCALE_AND_TRANSLATE_LOG);
            xmlSerializer.attribute(null, "scale", Float.toString(this.mScale));
            xmlSerializer.attribute(null, ATTR_TRANSLATE, Float.toString(this.mTranslate));
            xmlSerializer.endTag(null, BrightnessCorrection.TAG_SCALE_AND_TRANSLATE_LOG);
        }

        static BrightnessCorrection readFromParcel(Parcel parcel) {
            return BrightnessCorrection.createScaleAndTranslateLog(parcel.readFloat(), parcel.readFloat());
        }

        static BrightnessCorrection loadFromXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            return BrightnessCorrection.createScaleAndTranslateLog(BrightnessCorrection.loadFloatFromXml(xmlPullParser, "scale"), BrightnessCorrection.loadFloatFromXml(xmlPullParser, ATTR_TRANSLATE));
        }
    }
}
