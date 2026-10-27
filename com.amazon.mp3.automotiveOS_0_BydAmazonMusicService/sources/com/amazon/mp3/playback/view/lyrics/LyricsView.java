package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u0000  2\u00020\u0001:\u0001 B\u0019\b\u0016\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH&J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH&R$\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006!"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsView;", "Landroid/widget/LinearLayout;", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "lyrics", "", "setLyrics", "", "currentPosition", "setCurrentPosition", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "", "isPlaying", "updateEqualizerIcon", "mCurrentLyrics", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "getMCurrentLyrics", "()Lcom/amazon/mp3/lyrics/item/Lyrics;", "setMCurrentLyrics", "(Lcom/amazon/mp3/lyrics/item/Lyrics;)V", "Lcom/amazon/mp3/playback/view/lyrics/LyricsMetricsReporter;", "mLyricsMetricsReporter", "Lcom/amazon/mp3/playback/view/lyrics/LyricsMetricsReporter;", "getMLyricsMetricsReporter", "()Lcom/amazon/mp3/playback/view/lyrics/LyricsMetricsReporter;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class LyricsView extends LinearLayout {
    private Lyrics mCurrentLyrics;
    private final LyricsMetricsReporter mLyricsMetricsReporter;

    public abstract void initStyleSheet(StyleSheet styleSheet);

    public abstract void setCurrentPosition(int currentPosition);

    public abstract void updateEqualizerIcon(boolean isPlaying);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        LyricsMetricsReporter lyricsMetricsReporter = LyricsMetricsReporter.getInstance(getContext());
        Intrinsics.checkNotNullExpressionValue(lyricsMetricsReporter, "getInstance(context)");
        this.mLyricsMetricsReporter = lyricsMetricsReporter;
    }

    protected final Lyrics getMCurrentLyrics() {
        return this.mCurrentLyrics;
    }

    protected final void setMCurrentLyrics(Lyrics lyrics) {
        this.mCurrentLyrics = lyrics;
    }

    protected final LyricsMetricsReporter getMLyricsMetricsReporter() {
        return this.mLyricsMetricsReporter;
    }

    public final void setLyrics(Lyrics lyrics) {
        Intrinsics.checkNotNullParameter(lyrics, "lyrics");
        this.mCurrentLyrics = lyrics;
    }
}
