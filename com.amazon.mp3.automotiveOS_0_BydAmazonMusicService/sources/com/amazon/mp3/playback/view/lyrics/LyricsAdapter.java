package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.playback.OnLyricsLineClickListener;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.PlayNotificationIcon;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.utils.ScreenUtil;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String mAttribution;
    private final Context mContext;
    private boolean mIsFullScreen;
    private boolean mIsLyricsSelectionScreen;
    private Lyrics mLyrics;
    private OnCurrentLineHeightUpdatedListener mOnCurrentLineHeightUpdatedListener;
    private OnLyricsLineClickListener mOnLyricsLineClickListener;
    private StyleSheet mStyleSheet;
    private int mCurrentLinePosition = -1;
    private boolean mIsPlaying = false;
    private final Lyrics EMPTY_LYRICS = new Lyrics();

    private enum ITEM_TYPE {
        LINE,
        ATTRIBUTION,
        INTERLUDE
    }

    interface OnCurrentLineHeightUpdatedListener {
        void onCurrentLineHeightUpdated(int height);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(final int position) {
        return 0L;
    }

    public LyricsAdapter(Context context, Lyrics lyrics, StyleSheet styleSheet, boolean isFullScreen, boolean isLyricsSelectionScreen) {
        this.mIsFullScreen = false;
        this.mIsLyricsSelectionScreen = false;
        this.mContext = context;
        this.mStyleSheet = styleSheet;
        this.mIsFullScreen = isFullScreen;
        this.mIsLyricsSelectionScreen = isLyricsSelectionScreen;
        setLyrics(lyrics);
    }

    private void setLyrics(Lyrics lyrics) {
        this.mLyrics = lyrics;
        if (lyrics != null) {
            this.mAttribution = createAttributionLine(getCSV(lyrics.getCopyrights()), getCSV(this.mLyrics.getWriter()), prepareSourceString());
        } else {
            this.mAttribution = "";
        }
    }

    public void updateNoLyrics() {
        updateLyrics(this.EMPTY_LYRICS);
    }

    public void updateLyrics(Lyrics lyrics) {
        setLyrics(lyrics);
        notifyDataSetChanged();
    }

    public int getCurrentLinePosition() {
        return this.mCurrentLinePosition;
    }

    public void setCurrentLinePosition(int position) {
        this.mCurrentLinePosition = position;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.mIsPlaying = isPlaying;
    }

    public void setStyleSheet(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
        notifyDataSetChanged();
    }

    public void setIsFullScreen(boolean isFullScreen) {
        this.mIsFullScreen = isFullScreen;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder textViewHolder;
        ITEM_TYPE item_type = ITEM_TYPE.values()[i];
        View view = getView(item_type, viewGroup);
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$mp3$playback$view$lyrics$LyricsAdapter$ITEM_TYPE[item_type.ordinal()];
        if (i2 == 1 || i2 == 2) {
            textViewHolder = new TextViewHolder((LyricsLineContainer) view);
        } else {
            if (i2 != 3) {
                return null;
            }
            InterludeViewHolder interludeViewHolder = new InterludeViewHolder((LyricsLineContainer) view);
            initEqualizerView(interludeViewHolder.getEqualizerView());
            textViewHolder = interludeViewHolder;
        }
        return textViewHolder;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.view.lyrics.LyricsAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$view$lyrics$LyricsAdapter$ITEM_TYPE;

        static {
            int[] iArr = new int[ITEM_TYPE.values().length];
            $SwitchMap$com$amazon$mp3$playback$view$lyrics$LyricsAdapter$ITEM_TYPE = iArr;
            try {
                iArr[ITEM_TYPE.LINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$lyrics$LyricsAdapter$ITEM_TYPE[ITEM_TYPE.ATTRIBUTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$lyrics$LyricsAdapter$ITEM_TYPE[ITEM_TYPE.INTERLUDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$playback$view$lyrics$LyricsAdapter$ITEM_TYPE[ITEM_TYPE.values()[getItemViewType(position)].ordinal()];
        if (i != 1) {
            if (i == 2) {
                if (viewHolder instanceof TextViewHolder) {
                    bindAttributionView((TextViewHolder) viewHolder);
                }
            } else if (i == 3 && (viewHolder instanceof InterludeViewHolder)) {
                bindInterludeView((InterludeViewHolder) viewHolder, position);
            }
        } else if (viewHolder instanceof TextViewHolder) {
            bindLineView((TextViewHolder) viewHolder, position);
        }
        setVerticalMarginForItemView(viewHolder.itemView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        Lyrics lyrics = this.mLyrics;
        if (lyrics == null) {
            return 0;
        }
        int size = lyrics.getLines().size();
        return this.mAttribution.isEmpty() ? size : size + 1;
    }

    public View getView(ITEM_TYPE type, ViewGroup parent) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$playback$view$lyrics$LyricsAdapter$ITEM_TYPE[type.ordinal()];
        return LayoutInflater.from(this.mContext).inflate(i != 1 ? i != 2 ? i != 3 ? 0 : R.layout.view_interlude_line : R.layout.view_attribution_line : R.layout.view_lyrics_line_text, parent, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        if (position >= this.mLyrics.getLines().size()) {
            return ITEM_TYPE.ATTRIBUTION.ordinal();
        }
        if ("...".equals(this.mLyrics.getLines().get(position).getLine())) {
            return ITEM_TYPE.INTERLUDE.ordinal();
        }
        return ITEM_TYPE.LINE.ordinal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        View view = holder.itemView;
        view.setOnClickListener(null);
        if (view instanceof LyricsLineContainer) {
            ((LyricsLineContainer) view).resetAlpha();
        }
    }

    public void setOnCurrentLineHeightUpdatedListener(OnCurrentLineHeightUpdatedListener listener) {
        this.mOnCurrentLineHeightUpdatedListener = listener;
    }

    public void setOnLyricsLineClickListener(OnLyricsLineClickListener listener) {
        this.mOnLyricsLineClickListener = listener;
    }

    private void bindInterludeView(final InterludeViewHolder interludeViewHolder, final int position) {
        if (interludeViewHolder == null) {
            return;
        }
        final LyricsLine lyricsLine = this.mLyrics.getLines().get(position);
        boolean z = position == this.mCurrentLinePosition;
        if (z) {
            interludeViewHolder.itemView.post(new Runnable() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsAdapter$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$bindInterludeView$0(interludeViewHolder);
                }
            });
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null) {
            interludeViewHolder.bindInterludeView(z, this.mIsPlaying, styleSheet.getAlpha(AlphaKey.GLASS_4));
        }
        interludeViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindInterludeView$1(lyricsLine, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindInterludeView$0(InterludeViewHolder interludeViewHolder) {
        updateCurrentLineHeight(interludeViewHolder.itemView.getMeasuredHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindInterludeView$1(LyricsLine lyricsLine, int i, View view) {
        OnLyricsLineClickListener onLyricsLineClickListener = this.mOnLyricsLineClickListener;
        if (onLyricsLineClickListener != null) {
            onLyricsLineClickListener.onLyricsLineClicked(lyricsLine, i, null);
        }
    }

    private void bindAttributionView(TextViewHolder textViewHolder) {
        TextView lyricsTextView;
        if (textViewHolder == null || (lyricsTextView = textViewHolder.getLyricsTextView()) == null) {
            return;
        }
        lyricsTextView.setText(this.mAttribution);
        applyTextViewWithFontStyleKey(lyricsTextView, FontStyleKey.TERTIARY);
    }

    private void bindLineView(TextViewHolder textViewHolder, final int position) {
        Integer spacerInPixels;
        if (textViewHolder == null) {
            return;
        }
        final LyricsLine lyricsLine = this.mLyrics.getLines().get(position);
        final TextView lyricsTextView = textViewHolder.getLyricsTextView();
        if (lyricsTextView == null) {
            return;
        }
        lyricsTextView.setText(lyricsLine.getLine());
        if (this.mStyleSheet != null) {
            applyTextViewWithFontStyleKey(lyricsTextView, getFontStyleKey(position));
            if (this.mIsLyricsSelectionScreen) {
                if (lyricsLine.isLineSelected()) {
                    lyricsTextView.setSelected(true);
                    setSelectedLineBackground(lyricsTextView);
                } else {
                    lyricsTextView.setSelected(false);
                    setUnSelectedLineBackground(lyricsTextView);
                }
            }
            LyricsLineContainer lyricsLineContainer = textViewHolder.getLyricsLineContainer();
            if (lyricsLineContainer != null) {
                if (position != this.mCurrentLinePosition && !this.mIsLyricsSelectionScreen) {
                    applyLyricsLineContainerWithAlphaKey(lyricsLineContainer, this.mIsFullScreen ? AlphaKey.GLASS_4 : AlphaKey.GLASS_3);
                } else {
                    lyricsLineContainer.setViewAlpha(1.0f);
                }
            }
            if (!this.mIsFullScreen && (spacerInPixels = this.mStyleSheet.getSpacerInPixels(SpacerKey.MEDIUM)) != null) {
                lyricsTextView.setPadding(spacerInPixels.intValue(), lyricsTextView.getPaddingTop(), lyricsTextView.getPaddingRight(), lyricsTextView.getPaddingBottom());
            }
        }
        lyricsTextView.setGravity(this.mIsFullScreen ? 17 : GravityCompat.END);
        if (position == this.mCurrentLinePosition) {
            lyricsTextView.post(new Runnable() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsAdapter$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$bindLineView$2(lyricsTextView);
                }
            });
        }
        lyricsTextView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindLineView$3(lyricsLine, position, lyricsTextView, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindLineView$2(TextView textView) {
        updateCurrentLineHeight(textView.getMeasuredHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindLineView$3(LyricsLine lyricsLine, int i, TextView textView, View view) {
        OnLyricsLineClickListener onLyricsLineClickListener = this.mOnLyricsLineClickListener;
        if (onLyricsLineClickListener != null) {
            onLyricsLineClickListener.onLyricsLineClicked(lyricsLine, i, textView);
        }
    }

    private FontStyleKey getFontStyleKey(int position) {
        if (position != this.mCurrentLinePosition) {
            if (this.mIsFullScreen || isTabletLandscape(this.mStyleSheet)) {
                return FontStyleKey.HEADLINE_4;
            }
            return FontStyleKey.PRIMARY;
        }
        if (this.mIsFullScreen || isTabletLandscape(this.mStyleSheet)) {
            return FontStyleKey.HEADLINE_3;
        }
        return FontStyleKey.HEADLINE_4;
    }

    private boolean isTabletLandscape(final StyleSheet styleSheet) {
        if (styleSheet == null) {
            return ScreenUtil.INSTANCE.isLandscape(this.mContext);
        }
        return styleSheet.getLandscapeType(this.mContext).equals(LandscapeType.TABLET_LANDSCAPE);
    }

    private void initEqualizerView(PlayNotificationIcon equalizerView) {
        Integer iconSize;
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null || equalizerView == null) {
            return;
        }
        ButtonSize iconSize2 = styleSheet.getIconSize(IconSizeKey.MEDIUM);
        if (iconSize2 != null && (iconSize = iconSize2.getIconSize()) != null) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(equalizerView, iconSize.intValue(), iconSize.intValue(), Boolean.FALSE, null, null, null, null);
        }
        Integer color = this.mStyleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color != null) {
            equalizerView.setColorFilter(color.intValue());
        }
    }

    public void setSelectedLineBackground(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_bg);
        textView.setTextColor(this.mContext.getColor(R.color.primary));
    }

    public void setUnSelectedLineBackground(TextView textView) {
        textView.setBackgroundColor(this.mContext.getResources().getColor(R.color.transparent));
        textView.setTextColor(this.mContext.getColor(R.color.primary_glass_4));
    }

    private void applyTextViewWithFontStyleKey(TextView textView, FontStyleKey fontStyleKey) {
        FontSize fontSize;
        FontSize fontSizeCopy;
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            return;
        }
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            fontStyleKey = FontStyleKey.LYRICS_XRAY;
        }
        FontStyle fontStyle = styleSheet.getFontStyle(fontStyleKey);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
        }
        if (!this.mIsLyricsSelectionScreen || (fontSize = this.mStyleSheet.getFontSize(FontSizeKey.HEADLINE_3)) == null || (fontSizeCopy = fontSize.copy(this.mContext.getResources().getDimensionPixelSize(R.dimen.text_size_20), Integer.valueOf(this.mContext.getResources().getDimensionPixelSize(R.dimen.lyrics_selection_line_height)))) == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontSize(textView, fontSizeCopy);
    }

    private void applyLyricsLineContainerWithAlphaKey(LyricsLineContainer lyricsLineContainer, AlphaKey alphaKey) {
        Float alpha;
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null || (alpha = styleSheet.getAlpha(alphaKey)) == null) {
            return;
        }
        lyricsLineContainer.setViewAlpha(alpha.floatValue());
    }

    private void setVerticalMarginForItemView(View view) {
        StyleSheet styleSheet;
        Integer spacerInPixels;
        if (view == null || (styleSheet = this.mStyleSheet) == null) {
            return;
        }
        if (this.mIsFullScreen || isTabletLandscape(styleSheet)) {
            spacerInPixels = this.mStyleSheet.getSpacerInPixels(SpacerKey.EPIC);
        } else {
            spacerInPixels = this.mStyleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (!this.mIsLyricsSelectionScreen) {
            if (spacerInPixels == null || layoutParams == null) {
                return;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = spacerInPixels.intValue() / 2;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = spacerInPixels.intValue() / 2;
            return;
        }
        Integer spacerInPixels2 = this.mStyleSheet.getSpacerInPixels(SpacerKey.GIANT);
        Integer spacerInPixels3 = this.mStyleSheet.getSpacerInPixels(SpacerKey.MINI);
        if (spacerInPixels2 == null || spacerInPixels3 == null) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = spacerInPixels3.intValue() + spacerInPixels2.intValue();
        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = spacerInPixels2.intValue() + spacerInPixels3.intValue();
        ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = spacerInPixels3.intValue();
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = spacerInPixels3.intValue();
    }

    private String prepareSourceString() {
        String source = this.mLyrics.getSource();
        return "LYRIC_FIND".equals(source) ? "LyricFind" : source;
    }

    private static String getCSV(String[] values) {
        if (values == null || values.length == 0) {
            return "";
        }
        if (values.length == 1) {
            return values[0];
        }
        StringBuilder sb = new StringBuilder();
        for (String str : values) {
            sb.append(str);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private static String createAttributionLine(String copyrights, String writers, String source) {
        StringBuilder sb = new StringBuilder();
        if (copyrights != null && !copyrights.isEmpty()) {
            sb.append('\n');
            sb.append("Publishers");
            sb.append(" : ");
            sb.append(copyrights);
        }
        if (writers != null && !writers.isEmpty()) {
            sb.append('\n');
            sb.append("Song Writers");
            sb.append(" : ");
            sb.append(writers);
        }
        if (source != null && !source.isEmpty()) {
            sb.append('\n');
            sb.append("Licensed & Provided by");
            sb.append(" : ");
            sb.append(source);
        }
        sb.append('\n');
        return sb.toString();
    }

    private void updateCurrentLineHeight(int height) {
        OnCurrentLineHeightUpdatedListener onCurrentLineHeightUpdatedListener = this.mOnCurrentLineHeightUpdatedListener;
        if (onCurrentLineHeightUpdatedListener != null) {
            onCurrentLineHeightUpdatedListener.onCurrentLineHeightUpdated(height);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class InterludeViewHolder extends RecyclerView.ViewHolder {
        private LyricsLineContainer mLyricsLineContainer;
        private PlayNotificationIcon mPlayNotificationIcon;

        public InterludeViewHolder(LyricsLineContainer lyricsLineContainer) {
            super(lyricsLineContainer);
            this.mPlayNotificationIcon = (PlayNotificationIcon) lyricsLineContainer.findViewById(R.id.LyricsInterludeView);
            this.mLyricsLineContainer = lyricsLineContainer;
        }

        public void bindInterludeView(boolean isCurrentPosition, boolean isPlaying, Float iconOffStateAlpha) {
            PlayNotificationIcon playNotificationIcon = this.mPlayNotificationIcon;
            if (playNotificationIcon == null) {
                return;
            }
            playNotificationIcon.setIsPlaying(isCurrentPosition && isPlaying);
            LyricsLineContainer lyricsLineContainer = this.mLyricsLineContainer;
            if (lyricsLineContainer != null) {
                if ((!isCurrentPosition || !isPlaying) && iconOffStateAlpha != null) {
                    lyricsLineContainer.setViewAlpha(iconOffStateAlpha.floatValue());
                } else {
                    lyricsLineContainer.setViewAlpha(1.0f);
                }
            }
        }

        public PlayNotificationIcon getEqualizerView() {
            return this.mPlayNotificationIcon;
        }
    }

    private static class TextViewHolder extends RecyclerView.ViewHolder {
        private LyricsLineContainer mLyricsLineContainer;
        private TextView mLyricsTextView;

        public TextViewHolder(LyricsLineContainer lyricsLineContainer) {
            super(lyricsLineContainer);
            this.mLyricsTextView = (TextView) lyricsLineContainer.findViewById(R.id.LyricsTextView);
            this.mLyricsLineContainer = lyricsLineContainer;
        }

        public TextView getLyricsTextView() {
            return this.mLyricsTextView;
        }

        public LyricsLineContainer getLyricsLineContainer() {
            return this.mLyricsLineContainer;
        }
    }
}
