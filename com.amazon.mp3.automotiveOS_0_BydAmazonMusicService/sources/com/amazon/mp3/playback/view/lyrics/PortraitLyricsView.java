package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.PlayNotificationIcon;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PortraitLyricsView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0016R\u001b\u0010\u0011\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/PortraitLyricsView;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsView;", "", "currentPosition", "", "setCurrentPosition", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "", "isPlaying", "updateEqualizerIcon", "Lcom/amazon/mp3/playback/view/lyrics/LyricsLineView;", "mLyricsLineView$delegate", "Lkotlin/Lazy;", "getMLyricsLineView", "()Lcom/amazon/mp3/playback/view/lyrics/LyricsLineView;", "mLyricsLineView", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PortraitLyricsView extends LyricsView {

    /* JADX INFO: renamed from: mLyricsLineView$delegate, reason: from kotlin metadata */
    private final Lazy mLyricsLineView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PortraitLyricsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        LayoutInflater.from(getContext()).inflate(R.layout.view_portrait_lyrics, (ViewGroup) this, true);
        this.mLyricsLineView = LazyKt__LazyJVMKt.lazy(new Function0<LyricsLineView>() { // from class: com.amazon.mp3.playback.view.lyrics.PortraitLyricsView$mLyricsLineView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricsLineView invoke() {
                return (LyricsLineView) this.this$0.findViewById(R.id.LyricsLineView);
            }
        });
    }

    private final LyricsLineView getMLyricsLineView() {
        Object value = this.mLyricsLineView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mLyricsLineView>(...)");
        return (LyricsLineView) value;
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsView
    public void setCurrentPosition(int currentPosition) {
        List<LyricsLine> lines;
        LyricsLine lyricsLine;
        String line;
        Lyrics mCurrentLyrics = getMCurrentLyrics();
        if (mCurrentLyrics == null || (lines = mCurrentLyrics.getLines()) == null || (lyricsLine = lines.get(currentPosition)) == null || (line = lyricsLine.getLine()) == null) {
            return;
        }
        if (Intrinsics.areEqual("...", line)) {
            getMLyricsLineView().showEqualizer();
        } else {
            getMLyricsLineView().showLyricsText(line);
        }
        getMLyricsMetricsReporter().linesViewed(currentPosition, currentPosition);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsView
    public void initStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        getMLyricsLineView().initStyleSheet(styleSheet);
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        if (spacerInPixels != null) {
            int iIntValue = spacerInPixels.intValue();
            getMLyricsLineView().setPadding(iIntValue, 0, iIntValue, 0);
        }
        FontStyle fontStyle = styleSheet.getFontStyle(AmazonApplication.getCapabilities().isYAImmerseEnabled() ? FontStyleKey.LYRICS_XRAY : FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            getMLyricsLineView().setTextStyle(fontStyle);
        }
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        if (spacerInPixels2 == null) {
            return;
        }
        int iIntValue2 = spacerInPixels2.intValue();
        PlayNotificationIcon equalizerView = getMLyricsLineView().getEqualizerView();
        ViewGroup.LayoutParams layoutParams = equalizerView.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(equalizerView, layoutParams.width, layoutParams.height, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(iIntValue2), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        }
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsView
    public void updateEqualizerIcon(boolean isPlaying) {
        getMLyricsLineView().updateEqualizerIcon(isPlaying);
    }
}
