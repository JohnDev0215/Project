package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: LandscapeLyricsView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010$\u001a\u00020#\u0012\u0006\u0010&\u001a\u00020%¢\u0006\u0004\b'\u0010(J \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u001a\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\u001a\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010\u0010\u001a\u00020\u0007H\u0002J\u001a\u0010\u0015\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0012\u0010\u0016\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0006\u0010\u0019\u001a\u00020\u0004R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006)"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LandscapeLyricsView;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsView;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsLineView;", "lyricsLineView", "", "isPlaying", "isCurrentPosition", "", "updateEqualizerIcon", "", "currentPosition", "getLyricsStartPosition", "", "text", "showFocusedLyricsLineText", "showUnFocusedLyricsLineText", "initLyricsLineList", "Landroid/view/View;", "view", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "setBottomMarginForItemView", "isTabletLandscape", "setCurrentPosition", "initStyleSheet", "isStage", "", "mLyricsLineViewList", "Ljava/util/List;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mFocusedLyricsLineView", "Lcom/amazon/mp3/playback/view/lyrics/LyricsLineView;", "mIsPlaying", "Z", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LandscapeLyricsView extends LyricsView {
    private LyricsLineView mFocusedLyricsLineView;
    private boolean mIsPlaying;
    private final List<LyricsLineView> mLyricsLineViewList;
    private StyleSheet mStyleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LandscapeLyricsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.mLyricsLineViewList = new ArrayList();
        LayoutInflater.from(getContext()).inflate(R.layout.view_landscape_lyrics, (ViewGroup) this, true);
        initLyricsLineList();
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsView
    public void setCurrentPosition(int currentPosition) {
        List<LyricsLine> lines;
        Lyrics mCurrentLyrics;
        List<LyricsLine> lines2;
        LyricsLine lyricsLine;
        String line;
        int lyricsStartPosition = getLyricsStartPosition(currentPosition);
        int i = 0;
        for (Object obj : this.mLyricsLineViewList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            LyricsLineView lyricsLineView = (LyricsLineView) obj;
            int i3 = i + lyricsStartPosition;
            boolean z = i3 == currentPosition;
            Lyrics mCurrentLyrics2 = getMCurrentLyrics();
            if (i3 < ((mCurrentLyrics2 == null || (lines = mCurrentLyrics2.getLines()) == null) ? 0 : lines.size()) && (mCurrentLyrics = getMCurrentLyrics()) != null && (lines2 = mCurrentLyrics.getLines()) != null && (lyricsLine = lines2.get(i3)) != null && (line = lyricsLine.getLine()) != null) {
                if (Intrinsics.areEqual("...", line)) {
                    updateEqualizerIcon(lyricsLineView, this.mIsPlaying, z);
                    lyricsLineView.showEqualizer();
                } else if (z) {
                    this.mFocusedLyricsLineView = lyricsLineView;
                    showFocusedLyricsLineText(lyricsLineView, line);
                } else {
                    showUnFocusedLyricsLineText(lyricsLineView, line);
                }
                getMLyricsMetricsReporter().linesViewed(i3, i3);
            }
            i = i2;
        }
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsView
    public void initStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.mStyleSheet = styleSheet;
        if (isStage()) {
            ((LyricsLineView) findViewById(R.id.TopLyricsLine)).setVisibility(8);
            ((LyricsLineView) findViewById(R.id.BottomLyricsLine)).setVisibility(8);
        }
        int i = 0;
        for (Object obj : this.mLyricsLineViewList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            LyricsLineView lyricsLineView = (LyricsLineView) obj;
            lyricsLineView.initStyleSheet(styleSheet);
            Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
            if (spacerInPixels != null) {
                int iIntValue = spacerInPixels.intValue();
                lyricsLineView.setPadding(iIntValue, lyricsLineView.getPaddingTop(), iIntValue, lyricsLineView.getPaddingBottom());
            }
            if (i < this.mLyricsLineViewList.size() - 1) {
                setBottomMarginForItemView(lyricsLineView, styleSheet);
            }
            i = i2;
        }
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsView
    public void updateEqualizerIcon(boolean isPlaying) {
        this.mIsPlaying = isPlaying;
        for (LyricsLineView lyricsLineView : this.mLyricsLineViewList) {
            updateEqualizerIcon(lyricsLineView, isPlaying, Intrinsics.areEqual(this.mFocusedLyricsLineView, lyricsLineView));
        }
    }

    private final void updateEqualizerIcon(LyricsLineView lyricsLineView, boolean isPlaying, boolean isCurrentPosition) {
        if (isCurrentPosition) {
            lyricsLineView.updateEqualizerIcon(isPlaying);
        } else {
            lyricsLineView.updateEqualizerIcon(false);
        }
    }

    private final int getLyricsStartPosition(int currentPosition) {
        List<LyricsLine> lines;
        int size = this.mLyricsLineViewList.size();
        Lyrics mCurrentLyrics = getMCurrentLyrics();
        return RangesKt___RangesKt.coerceIn(currentPosition - (size / 2), 0, (mCurrentLyrics == null || (lines = mCurrentLyrics.getLines()) == null) ? 0 : Math.max(0, lines.size() - size));
    }

    private final void showFocusedLyricsLineText(LyricsLineView lyricsLineView, String text) {
        FontStyleKey fontStyleKey;
        FontStyle fontStyle;
        if (isStage()) {
            fontStyleKey = FontStyleKey.LYRICS_XRAY;
        } else {
            fontStyleKey = isTabletLandscape(this.mStyleSheet) ? FontStyleKey.HEADLINE_3 : FontStyleKey.HEADLINE_4;
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(fontStyleKey)) != null) {
            lyricsLineView.setTextStyle(fontStyle);
        }
        lyricsLineView.setTextAlpha(1.0f);
        lyricsLineView.showLyricsText(text);
    }

    private final void showUnFocusedLyricsLineText(LyricsLineView lyricsLineView, String text) {
        Float alpha;
        FontStyle fontStyle;
        FontStyleKey fontStyleKey = isTabletLandscape(this.mStyleSheet) ? FontStyleKey.HEADLINE_4 : FontStyleKey.PRIMARY;
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(fontStyleKey)) != null) {
            lyricsLineView.setTextStyle(fontStyle);
        }
        StyleSheet styleSheet2 = this.mStyleSheet;
        if (styleSheet2 != null && (alpha = styleSheet2.getAlpha(AlphaKey.GLASS_3)) != null) {
            lyricsLineView.setTextAlpha(alpha.floatValue());
        }
        lyricsLineView.showLyricsText(text);
    }

    private final void initLyricsLineList() {
        List<LyricsLineView> list = this.mLyricsLineViewList;
        View viewFindViewById = findViewById(R.id.MiddleLyricsLine);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.MiddleLyricsLine)");
        list.add((LyricsLineView) viewFindViewById);
        if (isStage()) {
            return;
        }
        List<LyricsLineView> list2 = this.mLyricsLineViewList;
        View viewFindViewById2 = findViewById(R.id.TopLyricsLine);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.TopLyricsLine)");
        list2.add((LyricsLineView) viewFindViewById2);
        List<LyricsLineView> list3 = this.mLyricsLineViewList;
        View viewFindViewById3 = findViewById(R.id.BottomLyricsLine);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.BottomLyricsLine)");
        list3.add((LyricsLineView) viewFindViewById3);
    }

    private final void setBottomMarginForItemView(View view, StyleSheet styleSheet) {
        Integer spacerInPixels;
        if (view == null) {
            return;
        }
        if (isTabletLandscape(styleSheet)) {
            spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.EPIC);
        } else {
            spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        }
        Integer num = spacerInPixels;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (num == null || layoutParams == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, Boolean.FALSE, Integer.valueOf(layoutParams.leftMargin), Integer.valueOf(layoutParams.topMargin), Integer.valueOf(layoutParams.rightMargin), num);
    }

    private final boolean isTabletLandscape(StyleSheet styleSheet) {
        LandscapeType landscapeType;
        if (styleSheet == null) {
            landscapeType = null;
        } else {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            landscapeType = styleSheet.getLandscapeType(context);
        }
        return landscapeType == LandscapeType.TABLET_LANDSCAPE;
    }

    public final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }
}
