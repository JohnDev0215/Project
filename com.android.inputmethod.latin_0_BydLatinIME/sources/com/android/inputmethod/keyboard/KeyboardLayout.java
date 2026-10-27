package com.android.inputmethod.keyboard;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class KeyboardLayout {
    private final int[] mKeyCodes;
    private final int[] mKeyHeights;
    private final int[] mKeyWidths;
    private final int[] mKeyXCoordinates;
    private final int[] mKeyYCoordinates;
    public final int mKeyboardHeight;
    public final int mKeyboardWidth;
    public final int mMostCommonKeyHeight;
    public final int mMostCommonKeyWidth;

    public KeyboardLayout(ArrayList<Key> layoutKeys, int mostCommonKeyWidth, int mostCommonKeyHeight, int keyboardWidth, int keyboardHeight) {
        this.mMostCommonKeyWidth = mostCommonKeyWidth;
        this.mMostCommonKeyHeight = mostCommonKeyHeight;
        this.mKeyboardWidth = keyboardWidth;
        this.mKeyboardHeight = keyboardHeight;
        this.mKeyCodes = new int[layoutKeys.size()];
        this.mKeyXCoordinates = new int[layoutKeys.size()];
        this.mKeyYCoordinates = new int[layoutKeys.size()];
        this.mKeyWidths = new int[layoutKeys.size()];
        this.mKeyHeights = new int[layoutKeys.size()];
        for (int i = 0; i < layoutKeys.size(); i++) {
            Key key = layoutKeys.get(i);
            this.mKeyCodes[i] = Character.toLowerCase(key.getCode());
            this.mKeyXCoordinates[i] = key.getX();
            this.mKeyYCoordinates[i] = key.getY();
            this.mKeyWidths[i] = key.getWidth();
            this.mKeyHeights[i] = key.getHeight();
        }
    }

    public int[] getKeyCodes() {
        return this.mKeyCodes;
    }

    public int[] getKeyXCoordinates() {
        return this.mKeyXCoordinates;
    }

    public int[] getKeyYCoordinates() {
        return this.mKeyYCoordinates;
    }

    public int[] getKeyWidths() {
        return this.mKeyWidths;
    }

    public int[] getKeyHeights() {
        return this.mKeyHeights;
    }

    public static KeyboardLayout newKeyboardLayout(@Nonnull final List<Key> sortedKeys, int mostCommonKeyWidth, int mostCommonKeyHeight, int occupiedWidth, int occupiedHeight) {
        ArrayList arrayList = new ArrayList();
        for (Key key : sortedKeys) {
            if (ProximityInfo.needsProximityInfo(key) && key.getCode() != 44) {
                arrayList.add(key);
            }
        }
        return new KeyboardLayout(arrayList, mostCommonKeyWidth, mostCommonKeyHeight, occupiedWidth, occupiedHeight);
    }
}
