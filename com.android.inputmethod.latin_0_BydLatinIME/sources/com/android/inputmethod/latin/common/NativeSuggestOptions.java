package com.android.inputmethod.latin.common;

/* JADX INFO: loaded from: classes2.dex */
public class NativeSuggestOptions {
    private static final int BLOCK_OFFENSIVE_WORDS = 2;
    private static final int IS_GESTURE = 0;
    private static final int OPTIONS_SIZE = 5;
    private static final int SPACE_AWARE_GESTURE_ENABLED = 3;
    private static final int USE_FULL_EDIT_DISTANCE = 1;
    private static final int WEIGHT_FOR_LOCALE_IN_THOUSANDS = 4;
    private final int[] mOptions = new int[5];

    public void setIsGesture(final boolean value) {
        setBooleanOption(0, value);
    }

    public void setUseFullEditDistance(final boolean value) {
        setBooleanOption(1, value);
    }

    public void setBlockOffensiveWords(final boolean value) {
        setBooleanOption(2, value);
    }

    public void setWeightForLocale(final float value) {
        setIntegerOption(4, (int) (value * 1000.0f));
    }

    public int[] getOptions() {
        return this.mOptions;
    }

    private void setBooleanOption(int i, boolean z) {
        this.mOptions[i] = z ? 1 : 0;
    }

    private void setIntegerOption(final int key, final int value) {
        this.mOptions[key] = value;
    }
}
