package com.amazon.mp3.playback.util;

import android.graphics.Point;
import com.amazon.mp3.view.BauhausSeekBarWidget;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPreviewSeekBarTracker.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0012\u001a\u0004\u0018\u00010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/playback/util/VideoPreviewSeekBarTracker;", "", "seekBar", "Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "(Lcom/amazon/mp3/view/BauhausSeekBarWidget;)V", "HOLDER_VALUE_NOT_FOUND", "", "HOLDER_X_INDEX", "HOLDER_Y_INDEX", "mPosition", "Landroid/graphics/Point;", "mSeekBar", "mSeekBarLocationHolder", "", "findSeekBarThumbXLocation", "holder", "getSeekbarProgressRatio", "", "getThumbScreenCoords", "hasSeekBarLocation", "", "updateSeekBarLocation", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoPreviewSeekBarTracker {
    private final int HOLDER_VALUE_NOT_FOUND;
    private final int HOLDER_X_INDEX;
    private final int HOLDER_Y_INDEX;
    private final Point mPosition;
    private BauhausSeekBarWidget mSeekBar;
    private final int[] mSeekBarLocationHolder;

    public VideoPreviewSeekBarTracker(BauhausSeekBarWidget seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        this.HOLDER_VALUE_NOT_FOUND = -1;
        this.HOLDER_Y_INDEX = 1;
        this.mSeekBar = seekBar;
        this.mSeekBarLocationHolder = new int[]{-1, -1};
        this.mPosition = new Point(-1, -1);
    }

    public final Point getThumbScreenCoords() {
        updateSeekBarLocation();
        if (!hasSeekBarLocation()) {
            return null;
        }
        this.mPosition.set(findSeekBarThumbXLocation(this.mSeekBar, this.mSeekBarLocationHolder), this.mSeekBarLocationHolder[this.HOLDER_Y_INDEX]);
        return this.mPosition;
    }

    private final void updateSeekBarLocation() {
        this.mSeekBar.getSeekBarLocation(this.mSeekBarLocationHolder);
        int[] iArr = this.mSeekBarLocationHolder;
        int i = this.HOLDER_Y_INDEX;
        iArr[i] = iArr[i] + (this.mSeekBar.getSeekBarHeight() / 2);
    }

    private final boolean hasSeekBarLocation() {
        int[] iArr = this.mSeekBarLocationHolder;
        int i = iArr[this.HOLDER_X_INDEX];
        int i2 = this.HOLDER_VALUE_NOT_FOUND;
        return (i == i2 || iArr[this.HOLDER_Y_INDEX] == i2) ? false : true;
    }

    private final float getSeekbarProgressRatio(BauhausSeekBarWidget seekBar) {
        return seekBar.getProgress() / seekBar.getMax();
    }

    private final int findSeekBarThumbXLocation(BauhausSeekBarWidget seekBar, int[] holder) {
        return holder[this.HOLDER_X_INDEX] + seekBar.getSeekBarLeftPadding() + ((int) ((seekBar.getSeekBarWidth() - (seekBar.getSeekBarLeftPadding() + seekBar.getSeekBarRightPadding())) * getSeekbarProgressRatio(seekBar)));
    }
}
