package com.android.inputmethod.latin.common;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class CoordinateUtils {
    private static final int ELEMENT_SIZE = 2;
    private static final int INDEX_X = 0;
    private static final int INDEX_Y = 1;

    @Nonnull
    public static int[] newInstance() {
        return new int[2];
    }

    private CoordinateUtils() {
    }

    public static int x(@Nonnull final int[] coords) {
        return coords[0];
    }

    public static int y(@Nonnull final int[] coords) {
        return coords[1];
    }

    public static void set(@Nonnull final int[] coords, final int x, final int y) {
        coords[0] = x;
        coords[1] = y;
    }

    public static void copy(@Nonnull final int[] destination, @Nonnull final int[] source) {
        destination[0] = source[0];
        destination[1] = source[1];
    }

    @Nonnull
    public static int[] newCoordinateArray(final int arraySize) {
        return new int[arraySize * 2];
    }

    @Nonnull
    public static int[] newCoordinateArray(final int arraySize, final int defaultX, final int defaultY) {
        int[] iArr = new int[arraySize * 2];
        for (int i = 0; i < arraySize; i++) {
            setXYInArray(iArr, i, defaultX, defaultY);
        }
        return iArr;
    }

    public static int xFromArray(@Nonnull final int[] coordsArray, final int index) {
        return coordsArray[(index * 2) + 0];
    }

    public static int yFromArray(@Nonnull final int[] coordsArray, final int index) {
        return coordsArray[(index * 2) + 1];
    }

    @Nonnull
    public static int[] coordinateFromArray(@Nonnull final int[] coordsArray, final int index) {
        int[] iArrNewInstance = newInstance();
        set(iArrNewInstance, xFromArray(coordsArray, index), yFromArray(coordsArray, index));
        return iArrNewInstance;
    }

    public static void setXYInArray(@Nonnull final int[] coordsArray, final int index, final int x, final int y) {
        int i = index * 2;
        coordsArray[i + 0] = x;
        coordsArray[i + 1] = y;
    }

    public static void setCoordinateInArray(@Nonnull final int[] coordsArray, final int index, @Nonnull final int[] coords) {
        setXYInArray(coordsArray, index, x(coords), y(coords));
    }
}
