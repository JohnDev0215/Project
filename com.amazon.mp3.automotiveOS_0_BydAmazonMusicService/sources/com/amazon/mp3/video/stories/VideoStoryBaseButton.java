package com.amazon.mp3.video.stories;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryBaseButton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0005¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryBaseButton;", "Lcom/amazon/ui/foundations/views/BaseButton;", "context", "Landroid/content/Context;", "buttonId", "", "(Landroid/content/Context;I)V", "setEllipsize", "", "truncateAt", "Landroid/text/TextUtils$TruncateAt;", "setMaxLines", "maxLines", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoStoryBaseButton extends BaseButton {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStoryBaseButton(Context context, int i) {
        super(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        super.setId(i);
    }

    public final void setMaxLines(int maxLines) {
        getButton().setMaxLines(maxLines);
    }

    public final void setEllipsize(TextUtils.TruncateAt truncateAt) {
        Intrinsics.checkNotNullParameter(truncateAt, "truncateAt");
        getButton().setEllipsize(truncateAt);
    }
}
