package com.android.launcher3.uioverrides;

import android.content.Context;
import android.util.Pair;
import com.android.launcher3.uioverrides.dynamicui.ColorExtractionAlgorithm;
import com.android.launcher3.uioverrides.dynamicui.WallpaperColorsCompat;
import com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class WallpaperColorInfo implements WallpaperManagerCompat.OnColorsChangedListenerCompat {
    private static final int FALLBACK_COLOR = -1;
    private static final int MAIN_COLOR_DARK = -14671580;
    private static final int MAIN_COLOR_LIGHT = -2433824;
    private static final int MAIN_COLOR_REGULAR = -16777216;
    private static WallpaperColorInfo sInstance;
    private static final Object sInstanceLock = new Object();
    private final ColorExtractionAlgorithm mExtractionType;
    private boolean mIsDark;
    private final ArrayList<OnChangeListener> mListeners = new ArrayList<>();
    private int mMainColor;
    private int mSecondaryColor;
    private boolean mSupportsDarkText;
    private OnChangeListener[] mTempListeners;
    private final WallpaperManagerCompat mWallpaperManager;

    public interface OnChangeListener {
        void onExtractedColorsChanged(WallpaperColorInfo wallpaperColorInfo);
    }

    public static WallpaperColorInfo getInstance(Context context) {
        WallpaperColorInfo wallpaperColorInfo;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new WallpaperColorInfo(context.getApplicationContext());
            }
            wallpaperColorInfo = sInstance;
        }
        return wallpaperColorInfo;
    }

    private WallpaperColorInfo(Context context) {
        WallpaperManagerCompat wallpaperManagerCompat = WallpaperManagerCompat.getInstance(context);
        this.mWallpaperManager = wallpaperManagerCompat;
        wallpaperManagerCompat.addOnColorsChangedListener(this);
        this.mExtractionType = new ColorExtractionAlgorithm();
        update(wallpaperManagerCompat.getWallpaperColors(1));
    }

    public int getMainColor() {
        return this.mMainColor;
    }

    public int getSecondaryColor() {
        return this.mSecondaryColor;
    }

    public boolean isDark() {
        return this.mIsDark;
    }

    public boolean supportsDarkText() {
        return this.mSupportsDarkText;
    }

    public boolean isMainColorDark() {
        return this.mMainColor == -14671580;
    }

    @Override // com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat.OnColorsChangedListenerCompat
    public void onColorsChanged(WallpaperColorsCompat colors, int which) {
        if ((which & 1) != 0) {
            update(colors);
            notifyChange();
        }
    }

    private void update(WallpaperColorsCompat wallpaperColors) {
        Pair<Integer, Integer> pairExtractInto = this.mExtractionType.extractInto(wallpaperColors);
        if (pairExtractInto != null) {
            this.mMainColor = pairExtractInto.first.intValue();
            this.mSecondaryColor = pairExtractInto.second.intValue();
        } else {
            this.mMainColor = -1;
            this.mSecondaryColor = -1;
        }
        this.mSupportsDarkText = wallpaperColors != null && (wallpaperColors.getColorHints() & 1) > 0;
        this.mIsDark = wallpaperColors != null && (wallpaperColors.getColorHints() & 2) > 0;
    }

    public void addOnChangeListener(OnChangeListener listener) {
        this.mListeners.add(listener);
    }

    public void removeOnChangeListener(OnChangeListener listener) {
        this.mListeners.remove(listener);
    }

    private void notifyChange() {
        OnChangeListener[] onChangeListenerArr = this.mTempListeners;
        OnChangeListener[] onChangeListenerArr2 = (OnChangeListener[]) this.mListeners.toArray((onChangeListenerArr == null || onChangeListenerArr.length != this.mListeners.size()) ? new OnChangeListener[this.mListeners.size()] : this.mTempListeners);
        this.mTempListeners = onChangeListenerArr2;
        for (OnChangeListener onChangeListener : onChangeListenerArr2) {
            onChangeListener.onExtractedColorsChanged(this);
        }
    }
}
