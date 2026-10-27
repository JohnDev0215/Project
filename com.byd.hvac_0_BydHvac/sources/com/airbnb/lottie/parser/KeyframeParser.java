package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class KeyframeParser {
    private static final float MAX_CP_VALUE = 100.0f;
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static JsonReader.Options NAMES = JsonReader.Options.of("t", "s", "e", "o", "i", "h", "to", "ti");

    @Nullable
    private static WeakReference<Interpolator> getInterpolator(int i) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = pathInterpolatorCache().get(i);
        }
        return weakReference;
    }

    public static <T> Keyframe<T> parse(JsonReader jsonReader, LottieComposition lottieComposition, float f, ValueParser<T> valueParser, boolean z) throws IOException {
        return z ? parseKeyframe(lottieComposition, jsonReader, f, valueParser) : parseStaticValue(jsonReader, f, valueParser);
    }

    private static <T> Keyframe<T> parseKeyframe(LottieComposition lottieComposition, JsonReader jsonReader, float f, ValueParser<T> valueParser) throws IOException {
        Interpolator interpolator;
        T t;
        Interpolator interpolatorCreate;
        jsonReader.beginObject();
        boolean z = false;
        float fNextDouble = 0.0f;
        PointF pointFJsonToPoint = null;
        PointF pointFJsonToPoint2 = null;
        T t2 = null;
        T t3 = null;
        PointF pointFJsonToPoint3 = null;
        PointF pointFJsonToPoint4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    fNextDouble = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    t3 = valueParser.parse(jsonReader, f);
                    break;
                case 2:
                    t2 = valueParser.parse(jsonReader, f);
                    break;
                case 3:
                    pointFJsonToPoint = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                case 4:
                    pointFJsonToPoint2 = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                case 5:
                    z = jsonReader.nextInt() == 1;
                    break;
                case 6:
                    pointFJsonToPoint4 = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                case 7:
                    pointFJsonToPoint3 = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        if (z) {
            interpolator = LINEAR_INTERPOLATOR;
            t = t3;
        } else {
            if (pointFJsonToPoint == null || pointFJsonToPoint2 == null) {
                interpolator = LINEAR_INTERPOLATOR;
            } else {
                float f2 = -f;
                pointFJsonToPoint.x = MiscUtils.clamp(pointFJsonToPoint.x, f2, f);
                pointFJsonToPoint.y = MiscUtils.clamp(pointFJsonToPoint.y, -100.0f, 100.0f);
                pointFJsonToPoint2.x = MiscUtils.clamp(pointFJsonToPoint2.x, f2, f);
                float fClamp = MiscUtils.clamp(pointFJsonToPoint2.y, -100.0f, 100.0f);
                pointFJsonToPoint2.y = fClamp;
                int iHashFor = Utils.hashFor(pointFJsonToPoint.x, pointFJsonToPoint.y, pointFJsonToPoint2.x, fClamp);
                WeakReference<Interpolator> interpolator2 = getInterpolator(iHashFor);
                Interpolator interpolator3 = interpolator2 != null ? interpolator2.get() : null;
                if (interpolator2 == null || interpolator3 == null) {
                    pointFJsonToPoint.x /= f;
                    pointFJsonToPoint.y /= f;
                    float f3 = pointFJsonToPoint2.x / f;
                    pointFJsonToPoint2.x = f3;
                    float f4 = pointFJsonToPoint2.y / f;
                    pointFJsonToPoint2.y = f4;
                    try {
                        interpolatorCreate = PathInterpolatorCompat.create(pointFJsonToPoint.x, pointFJsonToPoint.y, f3, f4);
                    } catch (IllegalArgumentException e) {
                        interpolatorCreate = e.getMessage().equals("The Path cannot loop back on itself.") ? PathInterpolatorCompat.create(Math.min(pointFJsonToPoint.x, 1.0f), pointFJsonToPoint.y, Math.max(pointFJsonToPoint2.x, 0.0f), pointFJsonToPoint2.y) : new LinearInterpolator();
                    }
                    interpolator3 = interpolatorCreate;
                    try {
                        putInterpolator(iHashFor, new WeakReference(interpolator3));
                    } catch (ArrayIndexOutOfBoundsException unused) {
                    }
                }
                interpolator = interpolator3;
            }
            t = t2;
        }
        Keyframe<T> keyframe = new Keyframe<>(lottieComposition, t3, t, interpolator, fNextDouble, null);
        keyframe.pathCp1 = pointFJsonToPoint4;
        keyframe.pathCp2 = pointFJsonToPoint3;
        return keyframe;
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader jsonReader, float f, ValueParser<T> valueParser) throws IOException {
        return new Keyframe<>(valueParser.parse(jsonReader, f));
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    private static void putInterpolator(int i, WeakReference<Interpolator> weakReference) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(i, weakReference);
        }
    }
}
