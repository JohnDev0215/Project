package com.amazon.podcast;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import com.amazon.podcast.featuregating.PodcastFeatureGating;

/* JADX INFO: loaded from: classes5.dex */
public final class RuntimeStyleSheet {
    public static String FONT_AMAZON_EMBER_BOLD_PATH = "fonts/AmazonEmber_Bd.ttf";
    private static String FONT_AMAZON_EMBER_DISPLAY_CONDENSED_PATH = "fonts/AmazonEmberDisplayCd_He.ttf";
    private static String FONT_SHARP_GROTESK_BOLD_20_PATH = "fonts/SharpGroteskPanEuroBold20.ttf";
    private static String FONT_SHARP_GROTESK_SEMI_BOLD_20_PATH = "fonts/SharpGroteskPanEuroSmBld20.ttf";
    private int PTCCircularBackgroundDrawableId;
    private int PTCCircularPlaceholderDrawableId;
    private int accentColor;
    private int accentGlass3RoundedCornersDrawableId;
    private int accentGlass4RoundedCornersDrawableId;
    private int accentOutlineRoundedCornersDrawableId;
    private int accentRoundedCornersDrawableId;
    private int applyButtonSelectorStateColorId;
    private int baseColor;
    private int basePlaceHolderDrawableId;
    private int buttonOutlineBackgroundDrawableId;
    private int buttonRefinementOutlineDrawableId;
    private int buttonRefinementSelectedDrawableId;
    private int buttonRefinementSelectorDrawableId;
    private int buttonSolidBackgroundDrawableId;
    private int cardAboutBackgroundGradientDrawableId;
    private final Context context;
    private int downloadProgressBarMediumDrawableId;
    private int downloadProgressBarSmallDrawableId;
    private int gradient2DrawableId;
    private int gradient3DrawableId;
    private boolean headline1AllCapsFlag;
    private int headline1LineHeight;
    private float headline1LineSpacing;
    private float headline1TextSize;
    private Typeface headline1TypeFace;
    private boolean headline2AllCapsFlag;
    private int headline2LineHeight;
    private float headline2LineSpacing;
    private float headline2TextSize;
    private Typeface headline2TypeFace;
    private Typeface headline3TypeFace;
    private Typeface headline4TypeFace;
    private int icEmptyStateImageDrawableId;
    private boolean isSonicRushEnabled;
    private int pillItemBorderDrawableId;
    private int podcastRibbonNotificationBackgroundLayer2DrawableId;
    private int podcastTabIndicatorDrawableId;
    private int podcastTranscriptEqualizerPrimaryDrawableId;
    private float t4TextSize;

    public RuntimeStyleSheet(final Context context) {
        this.context = context;
        init();
    }

    private void updateFeatureFlagsStatus() {
        this.isSonicRushEnabled = PodcastFeatureGating.IS_SONIC_RUSH.isEnabled();
    }

    private void init() {
        updateFeatureFlagsStatus();
        Resources resources = this.context.getResources();
        AssetManager assets = this.context.getAssets();
        if (this.isSonicRushEnabled) {
            this.baseColor = resources.getColor(R$color.refresh_base);
            this.accentColor = resources.getColor(R$color.refresh_podcast_accent);
            this.headline1TypeFace = Typeface.createFromAsset(assets, FONT_SHARP_GROTESK_BOLD_20_PATH);
            this.headline2TypeFace = Typeface.createFromAsset(assets, FONT_SHARP_GROTESK_BOLD_20_PATH);
            this.headline3TypeFace = Typeface.createFromAsset(assets, FONT_SHARP_GROTESK_SEMI_BOLD_20_PATH);
            this.headline4TypeFace = Typeface.createFromAsset(assets, FONT_SHARP_GROTESK_SEMI_BOLD_20_PATH);
            this.headline1TextSize = resources.getDimension(R$dimen.refresh_text_size_h1);
            this.headline1LineSpacing = resources.getDimension(R$dimen.refresh_line_spacing_h1);
            this.headline1LineHeight = resources.getDimensionPixelSize(R$dimen.refresh_line_height_h1);
            this.headline1AllCapsFlag = false;
            this.headline2TextSize = resources.getDimension(R$dimen.refresh_text_size_h2);
            this.headline2LineSpacing = resources.getDimension(R$dimen.refresh_line_spacing_h2);
            this.headline2LineHeight = resources.getDimensionPixelSize(R$dimen.refresh_line_height_h2);
            this.headline2AllCapsFlag = false;
            this.t4TextSize = resources.getDimension(R$dimen.podcast_text_size_t4);
            this.applyButtonSelectorStateColorId = R$color.refresh_apply_button_selector;
            this.accentGlass3RoundedCornersDrawableId = R$drawable.refresh_accent_glass_3_rounded_corners;
            this.accentGlass4RoundedCornersDrawableId = R$drawable.refresh_accent_glass_4_rounded_corners;
            this.accentOutlineRoundedCornersDrawableId = R$drawable.refresh_accent_outline_rounded_corners;
            this.accentRoundedCornersDrawableId = R$drawable.refresh_accent_rounded_corners;
            this.basePlaceHolderDrawableId = R$drawable.refresh_base_placeholder;
            this.buttonOutlineBackgroundDrawableId = R$drawable.refresh_button_outline_background;
            this.buttonRefinementOutlineDrawableId = R$drawable.refresh_button_refinement_outline;
            this.buttonRefinementSelectedDrawableId = R$drawable.refresh_button_refinement_selected;
            this.buttonRefinementSelectorDrawableId = R$drawable.refresh_button_refinement_selector;
            this.buttonSolidBackgroundDrawableId = R$drawable.refresh_button_solid_background;
            this.cardAboutBackgroundGradientDrawableId = R$drawable.refresh_card_about_background_gradient;
            this.downloadProgressBarMediumDrawableId = R$drawable.refresh_download_progress_bar_medium;
            this.downloadProgressBarSmallDrawableId = R$drawable.refresh_download_progress_bar_small;
            this.gradient2DrawableId = R$drawable.refresh_gradient2;
            this.gradient3DrawableId = R$drawable.refresh_gradient3;
            this.icEmptyStateImageDrawableId = R$drawable.refresh_ic_empty_state_image;
            this.pillItemBorderDrawableId = R$drawable.refresh_pill_item_border;
            this.podcastRibbonNotificationBackgroundLayer2DrawableId = R$drawable.refresh_podcast_ribbon_notification_background_layer2;
            this.podcastTabIndicatorDrawableId = R$drawable.refresh_podcast_tab_indicator;
            this.podcastTranscriptEqualizerPrimaryDrawableId = R$drawable.refresh_podcast_transcript_equalizer_primary;
            this.PTCCircularBackgroundDrawableId = R$drawable.refresh_ptc_circular_background;
            this.PTCCircularPlaceholderDrawableId = R$drawable.refresh_ptc_circular_placeholder;
            return;
        }
        this.baseColor = resources.getColor(R$color.base);
        this.accentColor = resources.getColor(R$color.podcast_accent);
        this.headline1TypeFace = Typeface.createFromAsset(assets, FONT_AMAZON_EMBER_DISPLAY_CONDENSED_PATH);
        this.headline2TypeFace = Typeface.createFromAsset(assets, FONT_AMAZON_EMBER_DISPLAY_CONDENSED_PATH);
        this.headline3TypeFace = Typeface.createFromAsset(assets, FONT_AMAZON_EMBER_BOLD_PATH);
        this.headline4TypeFace = Typeface.createFromAsset(assets, FONT_AMAZON_EMBER_BOLD_PATH);
        int i = R$dimen.podcast_text_size_h1;
        this.headline1TextSize = resources.getDimension(i);
        int i2 = R$dimen.zero_dp;
        this.headline1LineSpacing = resources.getDimension(i2);
        this.headline2LineHeight = resources.getDimensionPixelSize(i);
        this.headline1AllCapsFlag = true;
        int i3 = R$dimen.podcast_text_size_h2;
        this.headline2TextSize = resources.getDimension(i3);
        this.headline2LineSpacing = resources.getDimension(i2);
        this.headline2LineHeight = resources.getDimensionPixelSize(i3);
        this.headline2AllCapsFlag = true;
        this.applyButtonSelectorStateColorId = R$color.apply_button_selector;
        this.accentGlass3RoundedCornersDrawableId = R$drawable.accent_glass_3_rounded_corners;
        this.accentGlass4RoundedCornersDrawableId = R$drawable.accent_glass_4_rounded_corners;
        this.accentOutlineRoundedCornersDrawableId = R$drawable.accent_outline_rounded_corners;
        this.accentRoundedCornersDrawableId = R$drawable.accent_rounded_corners;
        this.basePlaceHolderDrawableId = R$drawable.base_placeholder;
        this.buttonOutlineBackgroundDrawableId = R$drawable.button_outline_background;
        this.buttonRefinementOutlineDrawableId = R$drawable.button_refinement_outline;
        this.buttonRefinementSelectedDrawableId = R$drawable.button_refinement_selected;
        this.buttonRefinementSelectorDrawableId = R$drawable.button_refinement_selector;
        this.buttonSolidBackgroundDrawableId = R$drawable.button_solid_background;
        this.cardAboutBackgroundGradientDrawableId = R$drawable.card_about_background_gradient;
        this.downloadProgressBarMediumDrawableId = R$drawable.download_progress_bar_medium;
        this.downloadProgressBarSmallDrawableId = R$drawable.download_progress_bar_small;
        this.gradient2DrawableId = R$drawable.gradient2;
        this.gradient3DrawableId = R$drawable.gradient3;
        this.icEmptyStateImageDrawableId = R$drawable.ic_empty_state_image;
        this.pillItemBorderDrawableId = R$drawable.pill_item_border;
        this.podcastRibbonNotificationBackgroundLayer2DrawableId = R$drawable.podcast_ribbon_notification_background_layer2;
        this.podcastTabIndicatorDrawableId = R$drawable.podcast_tab_indicator;
        this.podcastTranscriptEqualizerPrimaryDrawableId = R$drawable.podcast_transcript_equalizer_primary;
        this.PTCCircularBackgroundDrawableId = R$drawable.ptc_circular_background;
        this.PTCCircularPlaceholderDrawableId = R$drawable.ptc_circular_placeholder;
    }

    public int getAccentColor() {
        return this.accentColor;
    }

    public int getBaseColor() {
        return this.baseColor;
    }

    public Typeface getHeadline2TypeFace() {
        return this.headline2TypeFace;
    }

    public Typeface getHeadline3TypeFace() {
        return this.headline3TypeFace;
    }

    public Typeface getHeadline4TypeFace() {
        return this.headline4TypeFace;
    }

    public float getHeadline1TextSize() {
        return this.headline1TextSize;
    }

    public float getHeadline1LineSpacing() {
        return this.headline1LineSpacing;
    }

    public float getHeadline2TextSize() {
        return this.headline2TextSize;
    }

    public float getT4TextSize() {
        return this.t4TextSize;
    }

    public float getHeadline2LineSpacing() {
        return this.headline2LineSpacing;
    }

    public boolean isHeadline2AllCaps() {
        return this.headline2AllCapsFlag;
    }

    public int getApplyButtonSelectorStateColorId() {
        return this.applyButtonSelectorStateColorId;
    }

    public int getAccentGlass3RoundedCornersDrawableId() {
        return this.accentGlass3RoundedCornersDrawableId;
    }

    public int getAccentGlass4RoundedCornersDrawableId() {
        return this.accentGlass4RoundedCornersDrawableId;
    }

    public int getAccentOutlineRoundedCornersDrawableId() {
        return this.accentOutlineRoundedCornersDrawableId;
    }

    public int getAccentRoundedCornersDrawableId() {
        return this.accentRoundedCornersDrawableId;
    }

    public int getBasePlaceHolderDrawableId() {
        return this.basePlaceHolderDrawableId;
    }

    public int getButtonOutlineBackgroundDrawableId() {
        return this.buttonOutlineBackgroundDrawableId;
    }

    public int getButtonRefinementOutlineDrawableId() {
        return this.buttonRefinementOutlineDrawableId;
    }

    public int getButtonRefinementSelectedDrawableId() {
        return this.buttonRefinementSelectedDrawableId;
    }

    public int getButtonRefinementSelectorDrawableId() {
        return this.buttonRefinementSelectorDrawableId;
    }

    public int getButtonSolidBackgroundDrawableId() {
        return this.buttonSolidBackgroundDrawableId;
    }

    public int getCardAboutBackgroundGradientDrawableId() {
        return this.cardAboutBackgroundGradientDrawableId;
    }

    public int getDownloadProgressBarMediumDrawableId() {
        return this.downloadProgressBarMediumDrawableId;
    }

    public int getDownloadProgressBarSmallDrawableId() {
        return this.downloadProgressBarSmallDrawableId;
    }

    public int getGradient2DrawableId() {
        return this.gradient2DrawableId;
    }

    public int getGradient3DrawableId() {
        return this.gradient3DrawableId;
    }

    public int getIcEmptyStateImageDrawableId() {
        return this.icEmptyStateImageDrawableId;
    }

    public int getPillItemBorderDrawableId() {
        return this.pillItemBorderDrawableId;
    }

    public int getPodcastRibbonNotificationBackgroundLayer2DrawableId() {
        return this.podcastRibbonNotificationBackgroundLayer2DrawableId;
    }

    public int getPodcastTabIndicatorDrawableId() {
        return this.podcastTabIndicatorDrawableId;
    }

    public int getPodcastTranscriptEqualizerPrimaryDrawableId() {
        return this.podcastTranscriptEqualizerPrimaryDrawableId;
    }

    public int getPTCCircularBackgroundDrawableId() {
        return this.PTCCircularBackgroundDrawableId;
    }

    public int getPTCCircularPlaceholderDrawableId() {
        return this.PTCCircularPlaceholderDrawableId;
    }

    public int getHeadline1LineHeight() {
        return this.headline1LineHeight;
    }

    public int getHeadline2LineHeight() {
        return this.headline2LineHeight;
    }
}
