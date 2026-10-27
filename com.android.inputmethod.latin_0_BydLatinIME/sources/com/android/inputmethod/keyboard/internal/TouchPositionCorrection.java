package com.android.inputmethod.keyboard.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class TouchPositionCorrection {
    private static final int TOUCH_POSITION_CORRECTION_RECORD_SIZE = 3;
    private boolean mEnabled;
    private float[] mRadii;
    private float[] mXs;
    private float[] mYs;

    public float getX(final int row) {
        return 0.0f;
    }

    public void load(final String[] data) {
        boolean z;
        int length = data.length;
        if (length % 3 != 0) {
            return;
        }
        int i = length / 3;
        this.mXs = new float[i];
        this.mYs = new float[i];
        this.mRadii = new float[i];
        int i2 = 0;
        while (true) {
            z = true;
            if (i2 >= length) {
                break;
            }
            try {
                int i3 = i2 % 3;
                int i4 = i2 / 3;
                float f = Float.parseFloat(data[i2]);
                if (i3 == 0) {
                    this.mXs[i4] = f;
                } else if (i3 == 1) {
                    this.mYs[i4] = f;
                } else {
                    this.mRadii[i4] = f;
                }
                i2++;
            } catch (NumberFormatException unused) {
                this.mEnabled = false;
                this.mXs = null;
                this.mYs = null;
                this.mRadii = null;
                return;
            }
            this.mEnabled = false;
            this.mXs = null;
            this.mYs = null;
            this.mRadii = null;
            return;
        }
        if (length <= 0) {
            z = false;
        }
        this.mEnabled = z;
    }

    public void setEnabled(final boolean enabled) {
        this.mEnabled = enabled;
    }

    public boolean isValid() {
        return this.mEnabled;
    }

    public int getRows() {
        return this.mRadii.length;
    }

    public float getY(final int row) {
        return this.mYs[row];
    }

    public float getRadius(final int row) {
        return this.mRadii[row];
    }
}
