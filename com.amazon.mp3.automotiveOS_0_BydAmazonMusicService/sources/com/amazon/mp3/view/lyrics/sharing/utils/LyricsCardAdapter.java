package com.amazon.mp3.view.lyrics.sharing.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsShareCardStyle;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsCardAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001)B-\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b'\u0010(J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0014\u0010&\u001a\u00020#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006*"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsCardAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsCardAdapter$CardViewHolder;", "holder", "", "position", "", "bindBackgrounds", "bindLyricsView", "Landroid/widget/ImageView;", "imageView", "bindAlbumArt", "bindAmazonLogo", "bindTrackDetails", "Landroid/view/ViewGroup;", "parent", "viewType", "onCreateViewHolder", "onBindViewHolder", "getItemCount", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "lyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsShareCardStyle;", "lyricsShareCardStyles", "Ljava/util/List;", "Landroidx/viewpager2/widget/ViewPager2;", "lyricsCardSelectionViewpager", "Landroidx/viewpager2/widget/ViewPager2;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;Ljava/util/List;Landroidx/viewpager2/widget/ViewPager2;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "CardViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsCardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private final ViewPager2 lyricsCardSelectionViewpager;
    private final List<LyricsShareCardStyle> lyricsShareCardStyles;
    private final LyricsSharingDataModel lyricsSharingDataModel;
    private final StyleSheet styleSheet;

    public LyricsCardAdapter(LyricsSharingDataModel lyricsSharingDataModel, List<LyricsShareCardStyle> lyricsShareCardStyles, ViewPager2 lyricsCardSelectionViewpager, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(lyricsSharingDataModel, "lyricsSharingDataModel");
        Intrinsics.checkNotNullParameter(lyricsShareCardStyles, "lyricsShareCardStyles");
        Intrinsics.checkNotNullParameter(lyricsCardSelectionViewpager, "lyricsCardSelectionViewpager");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.lyricsSharingDataModel = lyricsSharingDataModel;
        this.lyricsShareCardStyles = lyricsShareCardStyles;
        this.lyricsCardSelectionViewpager = lyricsCardSelectionViewpager;
        this.styleSheet = styleSheet;
    }

    private final Context getContext() {
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        return applicationContext;
    }

    /* JADX INFO: compiled from: LyricsCardAdapter.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010R\u0017\u0010\u0015\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0016\u0010\u000bR\u0017\u0010\u0017\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0019\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u001a\u0010\u0010R\u0017\u0010\u001b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001c\u0010\u000b¨\u0006!"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsCardAdapter$CardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/cardview/widget/CardView;", "card", "Landroidx/cardview/widget/CardView;", "getCard", "()Landroidx/cardview/widget/CardView;", "Landroid/widget/ImageView;", "cardBackground", "Landroid/widget/ImageView;", "getCardBackground", "()Landroid/widget/ImageView;", "Landroid/widget/TextView;", "lyricsLineOne", "Landroid/widget/TextView;", "getLyricsLineOne", "()Landroid/widget/TextView;", "lyricsLineTwo", "getLyricsLineTwo", "lyricsLineThree", "getLyricsLineThree", "albumArt", "getAlbumArt", "trackTitle", "getTrackTitle", "artistName", "getArtistName", "amazonLogo", "getAmazonLogo", "Landroid/view/View;", "itemView", "<init>", "(Landroid/view/View;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        private final ImageView albumArt;
        private final ImageView amazonLogo;
        private final TextView artistName;
        private final CardView card;
        private final ImageView cardBackground;
        private final TextView lyricsLineOne;
        private final TextView lyricsLineThree;
        private final TextView lyricsLineTwo;
        private final TextView trackTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CardViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.lyrics_card);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.lyrics_card)");
            this.card = (CardView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.card_background);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.card_background)");
            this.cardBackground = (ImageView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.lyrics_line_one);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "itemView.findViewById(R.id.lyrics_line_one)");
            this.lyricsLineOne = (TextView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.lyrics_line_two);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "itemView.findViewById(R.id.lyrics_line_two)");
            this.lyricsLineTwo = (TextView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(R.id.lyrics_line_three);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "itemView.findViewById(R.id.lyrics_line_three)");
            this.lyricsLineThree = (TextView) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(R.id.album_art);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "itemView.findViewById(R.id.album_art)");
            this.albumArt = (ImageView) viewFindViewById6;
            View viewFindViewById7 = itemView.findViewById(R.id.track_title);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "itemView.findViewById(R.id.track_title)");
            this.trackTitle = (TextView) viewFindViewById7;
            View viewFindViewById8 = itemView.findViewById(R.id.artist_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "itemView.findViewById(R.id.artist_name)");
            this.artistName = (TextView) viewFindViewById8;
            View viewFindViewById9 = itemView.findViewById(R.id.logo_image);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "itemView.findViewById(R.id.logo_image)");
            this.amazonLogo = (ImageView) viewFindViewById9;
        }

        public final ImageView getCardBackground() {
            return this.cardBackground;
        }

        public final TextView getLyricsLineOne() {
            return this.lyricsLineOne;
        }

        public final TextView getLyricsLineTwo() {
            return this.lyricsLineTwo;
        }

        public final TextView getLyricsLineThree() {
            return this.lyricsLineThree;
        }

        public final ImageView getAlbumArt() {
            return this.albumArt;
        }

        public final TextView getTrackTitle() {
            return this.trackTitle;
        }

        public final TextView getArtistName() {
            return this.artistName;
        }

        public final ImageView getAmazonLogo() {
            return this.amazonLogo;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lyrics_card, parent, false);
        this.lyricsCardSelectionViewpager.setClipChildren(false);
        this.lyricsCardSelectionViewpager.setClipToPadding(false);
        this.lyricsCardSelectionViewpager.setOffscreenPageLimit(3);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new CardViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        bindBackgrounds(holder, position);
        bindLyricsView(holder, position);
        bindAlbumArt(holder.getAlbumArt());
        bindTrackDetails(holder, position);
        bindAmazonLogo(holder, position);
    }

    private final void bindBackgrounds(CardViewHolder holder, int position) {
        Integer backgroundDrawable = this.lyricsShareCardStyles.get(position).getLyricsShareCardBackground().getBackgroundDrawable();
        if (backgroundDrawable != null) {
            holder.getCardBackground().setImageResource(backgroundDrawable.intValue());
        }
        Bitmap backgroundBitmap = this.lyricsShareCardStyles.get(position).getLyricsShareCardBackground().getBackgroundBitmap();
        if (backgroundBitmap != null) {
            holder.getCardBackground().setImageBitmap(backgroundBitmap);
        }
        if (this.lyricsShareCardStyles.get(position).getLyricsShareCardBackground().getIsBlurAlbumArtBackground()) {
            holder.getCardBackground().setForeground(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.stage_background_art_gradient, null));
        }
    }

    private final void bindLyricsView(CardViewHolder holder, int position) {
        int startIndexOfLyricsSelected;
        int endIndexOfLyricsSelected;
        List<LyricsLine> lines;
        LyricsLine lyricsLine;
        List<LyricsLine> lines2;
        LyricsLine lyricsLine2;
        List<LyricsLine> lines3;
        LyricsLine lyricsLine3;
        if (this.lyricsSharingDataModel.getStartIndexOfLyricsSelected() == -1 || this.lyricsSharingDataModel.getEndIndexOfLyricsSelected() == -1 || (startIndexOfLyricsSelected = this.lyricsSharingDataModel.getStartIndexOfLyricsSelected()) > (endIndexOfLyricsSelected = this.lyricsSharingDataModel.getEndIndexOfLyricsSelected())) {
            return;
        }
        while (true) {
            int i = startIndexOfLyricsSelected + 1;
            int startIndexOfLyricsSelected2 = startIndexOfLyricsSelected - this.lyricsSharingDataModel.getStartIndexOfLyricsSelected();
            String line = null;
            if (startIndexOfLyricsSelected2 == 0) {
                TextView lyricsLineOne = holder.getLyricsLineOne();
                Lyrics lyrics = this.lyricsSharingDataModel.getLyrics();
                if (lyrics != null && (lines = lyrics.getLines()) != null && (lyricsLine = lines.get(startIndexOfLyricsSelected)) != null) {
                    line = lyricsLine.getLine();
                }
                lyricsLineOne.setText(line);
                holder.getLyricsLineOne().setVisibility(0);
                FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LYRICS_XRAY);
                if (fontStyle != null) {
                    FontUtil.INSTANCE.applyFontStyle(holder.getLyricsLineOne(), fontStyle);
                }
                FontSize fontSize = this.styleSheet.getFontSize(FontSizeKey.HEADLINE_4);
                if (fontSize != null) {
                    FontUtil.INSTANCE.applyFontSize(holder.getLyricsLineOne(), fontSize);
                }
                holder.getLyricsLineOne().setTextColor(this.lyricsShareCardStyles.get(position).getThemeColor());
            } else if (startIndexOfLyricsSelected2 == 1) {
                TextView lyricsLineTwo = holder.getLyricsLineTwo();
                Lyrics lyrics2 = this.lyricsSharingDataModel.getLyrics();
                if (lyrics2 != null && (lines2 = lyrics2.getLines()) != null && (lyricsLine2 = lines2.get(startIndexOfLyricsSelected)) != null) {
                    line = lyricsLine2.getLine();
                }
                lyricsLineTwo.setText(line);
                holder.getLyricsLineTwo().setVisibility(0);
                FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.LYRICS_XRAY);
                if (fontStyle2 != null) {
                    FontUtil.INSTANCE.applyFontStyle(holder.getLyricsLineTwo(), fontStyle2);
                }
                FontSize fontSize2 = this.styleSheet.getFontSize(FontSizeKey.HEADLINE_4);
                if (fontSize2 != null) {
                    FontUtil.INSTANCE.applyFontSize(holder.getLyricsLineTwo(), fontSize2);
                }
                holder.getLyricsLineTwo().setTextColor(this.lyricsShareCardStyles.get(position).getThemeColor());
            } else if (startIndexOfLyricsSelected2 == 2) {
                TextView lyricsLineThree = holder.getLyricsLineThree();
                Lyrics lyrics3 = this.lyricsSharingDataModel.getLyrics();
                if (lyrics3 != null && (lines3 = lyrics3.getLines()) != null && (lyricsLine3 = lines3.get(startIndexOfLyricsSelected)) != null) {
                    line = lyricsLine3.getLine();
                }
                lyricsLineThree.setText(line);
                holder.getLyricsLineThree().setVisibility(0);
                FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.LYRICS_XRAY);
                if (fontStyle3 != null) {
                    FontUtil.INSTANCE.applyFontStyle(holder.getLyricsLineThree(), fontStyle3);
                }
                FontSize fontSize3 = this.styleSheet.getFontSize(FontSizeKey.HEADLINE_4);
                if (fontSize3 != null) {
                    FontUtil.INSTANCE.applyFontSize(holder.getLyricsLineThree(), fontSize3);
                }
                holder.getLyricsLineThree().setTextColor(this.lyricsShareCardStyles.get(position).getThemeColor());
            }
            if (startIndexOfLyricsSelected == endIndexOfLyricsSelected) {
                return;
            } else {
                startIndexOfLyricsSelected = i;
            }
        }
    }

    private final void bindAlbumArt(ImageView imageView) {
        Resources resources = AmazonApplication.getApplication().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.spacer_40);
        Drawable albumArt = this.lyricsSharingDataModel.getAlbumArt();
        RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(AmazonApplication.getApplication().getResources(), albumArt == null ? null : DrawableKt.toBitmap(albumArt, dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888));
        Intrinsics.checkNotNullExpressionValue(roundedBitmapDrawableCreate, "create(AmazonApplication…resources,albumArtBitmap)");
        roundedBitmapDrawableCreate.setCornerRadius(resources.getDimension(R.dimen.lyrics_card_corner_radius_10));
        imageView.setImageDrawable(roundedBitmapDrawableCreate);
    }

    private final void bindAmazonLogo(CardViewHolder holder, int position) {
        if (this.lyricsShareCardStyles.get(position).getThemeColor() == -16777216) {
            holder.getAmazonLogo().setBackgroundResource(R.drawable.amazon_music_logo_black);
        } else if (this.lyricsShareCardStyles.get(position).getThemeColor() == AmazonApplication.getApplication().getColor(R.color.cyan_accent)) {
            holder.getAmazonLogo().setBackgroundResource(R.drawable.amazon_music_logo_cyan);
        }
    }

    private final void bindTrackDetails(CardViewHolder holder, int position) {
        holder.getTrackTitle().setText(this.lyricsSharingDataModel.getTitle());
        holder.getTrackTitle().setTextColor(this.lyricsShareCardStyles.get(position).getThemeColor());
        holder.getArtistName().setText(this.lyricsSharingDataModel.getArtistName());
        holder.getArtistName().setTextColor(this.lyricsShareCardStyles.get(position).getThemeColor());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.lyricsShareCardStyles.size();
    }
}
