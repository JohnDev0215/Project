package android.graphics;

/* JADX INFO: loaded from: classes.dex */
public final class BlendModeColorFilter extends ColorFilter {
    final int mColor;
    private final BlendMode mMode;

    private static native long native_CreateBlendModeFilter(int i, int i2);

    public BlendModeColorFilter(int i, BlendMode blendMode) {
        this.mColor = i;
        this.mMode = blendMode;
    }

    public int getColor() {
        return this.mColor;
    }

    public BlendMode getMode() {
        return this.mMode;
    }

    @Override // android.graphics.ColorFilter
    long createNativeInstance() {
        return native_CreateBlendModeFilter(this.mColor, this.mMode.getXfermode().porterDuffMode);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && ((BlendModeColorFilter) obj).mMode == this.mMode;
    }

    public int hashCode() {
        return (this.mMode.hashCode() * 31) + this.mColor;
    }
}
