package com.amazon.video.sdk.stream;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: TimedTextUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextColorMapping;", "", "color", "", "(Ljava/lang/String;II)V", "getColor", "()I", "WHITE", "BLACK", "RED", "GREEN", "BLUE", "YELLOW", "MAGENTA", "CYAN", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
enum TimedTextColorMapping {
    WHITE(-1),
    BLACK(ViewCompat.MEASURED_STATE_MASK),
    RED(SupportMenu.CATEGORY_MASK),
    GREEN(-16711936),
    BLUE(-16776961),
    YELLOW(InputDeviceCompat.SOURCE_ANY),
    MAGENTA(-65281),
    CYAN(-16711681);

    private final int color;

    TimedTextColorMapping(int i) {
        this.color = i;
    }

    public final int getColor() {
        return this.color;
    }
}
