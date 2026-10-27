package androidx.interpolator.view.animation;

/* JADX INFO: loaded from: classes2.dex */
final class LookupTableInterpolator {
    private LookupTableInterpolator() {
    }

    static float interpolate(float[] values, float stepSize, float input) {
        if (input >= 1.0f) {
            return 1.0f;
        }
        if (input <= 0.0f) {
            return 0.0f;
        }
        int position = Math.min((int) ((values.length - 1) * input), values.length - 2);
        float quantized = position * stepSize;
        float diff = input - quantized;
        float weight = diff / stepSize;
        return values[position] + ((values[position + 1] - values[position]) * weight);
    }
}
