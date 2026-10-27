package com.amazon.music.explore.views.swipeablePages;

import Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement;
import android.content.Context;
import android.util.TypedValue;
import com.amazon.music.explore.ExploreStyleSheet;
import com.amazon.music.explore.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePagesStyleSheet extends ExploreStyleSheet {
    private final int CHAR_COUNT_101;
    private final int CHAR_COUNT_201;
    private final int CHAR_COUNT_31;

    enum GranularScreenSizeKey {
        XSMALL,
        SMALL_1,
        SMALL_2,
        SMALL_3,
        MEDIUM,
        LARGE,
        XLARGE,
        XLARGE2,
        XLARGE3
    }

    public SwipeablePagesStyleSheet(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        this.CHAR_COUNT_31 = 31;
        this.CHAR_COUNT_101 = 101;
        this.CHAR_COUNT_201 = 201;
    }

    public int getPeekLeftHeight() {
        int iIntValue;
        int iIntValue2;
        if (ExploreStyleSheet.isXL(this.context)) {
            iIntValue = getSpacerInPixels(SpacerKey.EPIC).intValue() * 3;
            iIntValue2 = getSpacerInPixels(SpacerKey.BASE).intValue();
        } else {
            iIntValue = getSpacerInPixels(SpacerKey.EPIC).intValue() * 2;
            iIntValue2 = getSpacerInPixels(SpacerKey.BASE).intValue();
        }
        return iIntValue + iIntValue2;
    }

    public int getPeekRightHeight() {
        return getSpacerInPixels(SpacerKey.BASE).intValue() + getSpacerInPixels(SpacerKey.MEDIUM).intValue();
    }

    public int getThumbnailSize() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
        return (int) TypedValue.applyDimension(1, (i == 1 || i == 2) ? 200 : i != 3 ? i != 4 ? i != 5 ? 480 : 400 : 280 : 240, this.resources.getDisplayMetrics());
    }

    public float getBlurRadiusForStage() {
        TypedValue typedValue = new TypedValue();
        this.resources.getValue(R.dimen.stage_swipeable_pages_background_blur_radius, typedValue, true);
        return typedValue.getFloat();
    }

    public int getMaxLinesToShow(boolean z) {
        if (z) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
            if (i == 1) {
                return this.resources.getInteger(R.integer.xray_max_lines_full_view_xsmall);
            }
            if (i == 2) {
                return this.resources.getInteger(R.integer.xray_max_lines_full_view_small);
            }
            if (i == 3) {
                return this.resources.getInteger(R.integer.xray_max_lines_full_view_medium);
            }
            if (i == 4) {
                return this.resources.getInteger(R.integer.xray_max_lines_full_view_large);
            }
            if (i == 5) {
                return this.resources.getInteger(R.integer.xray_max_lines_full_view_xlarge);
            }
            return this.resources.getInteger(R.integer.xray_max_lines_full_view_xsmall);
        }
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
        if (i2 == 1) {
            return this.resources.getInteger(R.integer.xray_max_lines_preview_xsmall);
        }
        if (i2 == 2) {
            return this.resources.getInteger(R.integer.xray_max_lines_preview_small);
        }
        if (i2 == 3) {
            return this.resources.getInteger(R.integer.xray_max_lines_preview_medium);
        }
        if (i2 == 4) {
            return this.resources.getInteger(R.integer.xray_max_lines_preview_large);
        }
        if (i2 == 5) {
            return this.resources.getInteger(R.integer.xray_max_lines_preview_xlarge);
        }
        return this.resources.getInteger(R.integer.xray_max_lines_preview_xsmall);
    }

    public int getSwipeableSectionsMaxWidth() {
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.swipeable_pages_mini_player_thumbnail_end);
        int dimensionPixelSize2 = this.resources.getDimensionPixelSize(R.dimen.small_icon_button);
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return (((int) (getScreenWidth() * 0.85f)) - dimensionPixelSize) - dimensionPixelSize2;
            }
            return (((int) (getScreenWidth() * 0.75f)) - dimensionPixelSize) - dimensionPixelSize2;
        }
        return (getScreenWidth() - getGridSize().getMarginSpace()) - getGridSize().getColumnSpace();
    }

    public int getStageSwipeableSectionsMaxWidth() {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        int screenWidth = getScreenWidth();
        GranularScreenSizeKey granularScreenSizeKey = getGranularScreenSizeKey(screenWidth);
        if (granularScreenSizeKey == GranularScreenSizeKey.SMALL_1 || granularScreenSizeKey == GranularScreenSizeKey.XSMALL) {
            dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.spacer_small);
            dimensionPixelSize2 = this.resources.getDimensionPixelSize(R.dimen.spacer_micro);
        } else {
            dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.spacer_large);
            dimensionPixelSize2 = this.resources.getDimensionPixelSize(R.dimen.spacer_small);
        }
        return screenWidth - ((dimensionPixelSize + dimensionPixelSize2) * 2);
    }

    public int getScrollableListMaxWidth() {
        int columnSpace = getGridSize().getColumnSpace();
        int marginSpace = getGridSize().getMarginSpace();
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.medium_icon_button);
        return (((getScreenWidth() - marginSpace) - columnSpace) - dimensionPixelSize) - this.resources.getDimensionPixelSize(R.dimen.swipeable_pages_mini_player_thumbnail_end);
    }

    public int getThumbnailSizeForStage() {
        int screenWidth;
        if (isMobileLandscape()) {
            screenWidth = getScreenHeight();
        } else {
            screenWidth = getScreenWidth();
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[getGranularScreenSizeKey(screenWidth).ordinal()];
        if (i == 1) {
            return this.resources.getDimensionPixelSize(R.dimen.stage_thumbnail_xs);
        }
        if (i == 2) {
            return this.resources.getDimensionPixelSize(R.dimen.stage_thumbnail_small_1);
        }
        if (i == 3 || i == 4) {
            return this.resources.getDimensionPixelSize(R.dimen.stage_thumbnail_sm);
        }
        return this.resources.getDimensionPixelSize(R.dimen.stage_thumbnail_default);
    }

    public float getLabelFontSizeForStage() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()] == 1) {
            return this.resources.getDimension(R.dimen.text_t4);
        }
        return this.resources.getDimension(R.dimen.text_t2);
    }

    public float getLabelLineHeightForStage() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()] == 1) {
            return this.resources.getDimension(R.dimen.line_height_t4);
        }
        return this.resources.getDimension(R.dimen.line_height_t2);
    }

    /* JADX INFO: renamed from: com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey;

        static {
            int[] iArr = new int[ClickableTextElement.Style.values().length];
            $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style = iArr;
            try {
                iArr[ClickableTextElement.Style.PRIMARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.PRIMARY_TITLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.DESCRIPTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.SECONDARY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[GranularScreenSizeKey.values().length];
            $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey = iArr2;
            try {
                iArr2[GranularScreenSizeKey.XSMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[GranularScreenSizeKey.SMALL_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[GranularScreenSizeKey.SMALL_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[GranularScreenSizeKey.SMALL_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[GranularScreenSizeKey.MEDIUM.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[GranularScreenSizeKey.LARGE.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[GranularScreenSizeKey.XLARGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[GranularScreenSizeKey.XLARGE2.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr3 = new int[ScreenSizeKey.values().length];
            $SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey = iArr3;
            try {
                iArr3[ScreenSizeKey.XSMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[ScreenSizeKey.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[ScreenSizeKey.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[ScreenSizeKey.LARGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[ScreenSizeKey.XLARGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[ScreenSizeKey.XLARGE2.ordinal()] = 6;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    public int getClickableTextFontSizeForStage(ClickableTextElement clickableTextElement) {
        float clickableTextSizePrimary;
        int length = clickableTextElement.text().length();
        int i = AnonymousClass1.$SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[clickableTextElement.style().ordinal()];
        if (i == 1) {
            clickableTextSizePrimary = getClickableTextSizePrimary(length);
        } else if (i == 2) {
            clickableTextSizePrimary = getClickableTextSizeContent();
        } else if (i == 3) {
            clickableTextSizePrimary = getClickableTextSizePrimaryTitle();
        } else if (i == 4) {
            clickableTextSizePrimary = getClickableTextSizeDescription();
        } else {
            clickableTextSizePrimary = getClickableTextSizeSecondary();
        }
        return (int) clickableTextSizePrimary;
    }

    private float getClickableTextSizePrimary(int i) {
        if (i < 31) {
            int i2 = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
            if (i2 == 1 || i2 == 3) {
                return this.resources.getDimension(R.dimen.text_size_24);
            }
            return this.resources.getDimension(R.dimen.text_size_28);
        }
        if (31 <= i && i < 101) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()]) {
                case 1:
                case 4:
                    return this.resources.getDimension(R.dimen.text_h4);
                case 2:
                case 5:
                case 6:
                    return this.resources.getDimension(R.dimen.text_size_24);
                case 3:
                    return this.resources.getDimension(R.dimen.text_t1);
                default:
                    return this.resources.getDimension(R.dimen.text_size_28);
            }
        }
        if (101 <= i && i < 201) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[getGranularScreenSizeKey(getScreenWidth()).ordinal()]) {
                case 1:
                case 5:
                    return this.resources.getDimension(R.dimen.text_t1);
                case 2:
                case 6:
                    return this.resources.getDimension(R.dimen.text_h4);
                case 3:
                case 4:
                case 7:
                case 8:
                    return this.resources.getDimension(R.dimen.text_size_24);
                default:
                    return this.resources.getDimension(R.dimen.text_size_28);
            }
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()]) {
            case 1:
            case 3:
                return this.resources.getDimension(R.dimen.text_t1);
            case 2:
            case 4:
            case 5:
                return this.resources.getDimension(R.dimen.text_h4);
            case 6:
                return this.resources.getDimension(R.dimen.text_size_24);
            default:
                return this.resources.getDimension(R.dimen.text_size_28);
        }
    }

    private float getClickableTextSizeSecondary() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
        if (i == 1 || i == 3) {
            return this.resources.getDimension(R.dimen.text_t1);
        }
        return this.resources.getDimension(R.dimen.text_h4);
    }

    private float getClickableTextSizeContent() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()] == 1) {
            return this.resources.getDimension(R.dimen.text_size_24);
        }
        return this.resources.getDimension(R.dimen.text_size_28);
    }

    private float getClickableTextSizePrimaryTitle() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()] == 1) {
            return this.resources.getDimension(R.dimen.text_t1);
        }
        return this.resources.getDimension(R.dimen.text_h4);
    }

    private float getClickableTextSizeDescription() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()] == 1) {
            return this.resources.getDimension(R.dimen.text_t3);
        }
        return this.resources.getDimension(R.dimen.text_t1);
    }

    public int getClickableTextMaxChars(ClickableTextElement clickableTextElement, boolean z) {
        if (StringUtils.isEmpty(clickableTextElement.text())) {
            return 0;
        }
        int length = clickableTextElement.text().length();
        int i = AnonymousClass1.$SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[clickableTextElement.style().ordinal()];
        if (i != 1) {
            if (i != 3) {
                return i != 4 ? i != 5 ? length : this.resources.getInteger(R.integer.xray_buzz_secondary_text_max_chars) : !z ? this.resources.getInteger(R.integer.xray_related_description_max_chars) : length;
            }
            return this.resources.getInteger(R.integer.xray_related_primary_title_max_chars);
        }
        if (z) {
            return this.resources.getInteger(R.integer.xray_buzz_primary_text_expanded_view_max_chars);
        }
        return this.resources.getInteger(R.integer.xray_buzz_primary_text_preview_max_chars);
    }

    public int getClickableTextLineHeightForStage(ClickableTextElement clickableTextElement) {
        float clickableTextLineHeightPrimary;
        int length = clickableTextElement.text().length();
        int i = AnonymousClass1.$SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[clickableTextElement.style().ordinal()];
        if (i == 1) {
            clickableTextLineHeightPrimary = getClickableTextLineHeightPrimary(length);
        } else if (i == 2) {
            clickableTextLineHeightPrimary = getClickableTextLineHeightContent();
        } else if (i == 3) {
            clickableTextLineHeightPrimary = getClickableTextLineHeightPrimaryTitle();
        } else if (i == 4) {
            clickableTextLineHeightPrimary = getClickableTextLineHeightDescription();
        } else {
            clickableTextLineHeightPrimary = getClickableTextLineHeightSecondary();
        }
        return (int) clickableTextLineHeightPrimary;
    }

    private float getClickableTextLineHeightPrimary(int i) {
        if (i < 31) {
            return this.resources.getDimension(R.dimen.line_height_32);
        }
        if (31 <= i && i < 101) {
            int i2 = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
            if (i2 != 1) {
                if (i2 == 3) {
                    return this.resources.getDimension(R.dimen.line_height_t1);
                }
                if (i2 != 4) {
                    return this.resources.getDimension(R.dimen.line_height_32);
                }
            }
            return this.resources.getDimension(R.dimen.line_height_h4);
        }
        if (101 <= i && i < 201) {
            int i3 = AnonymousClass1.$SwitchMap$com$amazon$music$explore$views$swipeablePages$SwipeablePagesStyleSheet$GranularScreenSizeKey[getGranularScreenSizeKey(getScreenWidth()).ordinal()];
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 != 5) {
                        if (i3 != 6) {
                            return this.resources.getDimension(R.dimen.line_height_32);
                        }
                    }
                }
                return this.resources.getDimension(R.dimen.line_height_h4);
            }
            return this.resources.getDimension(R.dimen.line_height_t1);
        }
        int i4 = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
        if (i4 != 1) {
            if (i4 != 2) {
                if (i4 != 3) {
                    if (i4 != 4 && i4 != 5) {
                        return this.resources.getDimension(R.dimen.line_height_32);
                    }
                }
            }
            return this.resources.getDimension(R.dimen.line_height_h4);
        }
        return this.resources.getDimension(R.dimen.line_height_t1);
    }

    private float getClickableTextLineHeightSecondary() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()];
        if (i == 1 || i == 3) {
            return this.resources.getDimension(R.dimen.line_height_t1);
        }
        return this.resources.getDimension(R.dimen.line_height_h4);
    }

    private float getClickableTextLineHeightContent() {
        return this.resources.getDimension(R.dimen.line_height_32);
    }

    private float getClickableTextLineHeightPrimaryTitle() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()] == 1) {
            return this.resources.getDimension(R.dimen.line_height_t1);
        }
        return this.resources.getDimension(R.dimen.line_height_h4);
    }

    private float getClickableTextLineHeightDescription() {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey().ordinal()] == 1) {
            return this.resources.getDimension(R.dimen.line_height_t3);
        }
        return this.resources.getDimension(R.dimen.line_height_t1);
    }

    public GranularScreenSizeKey getGranularScreenSizeKey(int i) {
        int dimension = (int) this.resources.getDimension(R.dimen.screen_size_390);
        int dimension2 = (int) this.resources.getDimension(R.dimen.screen_size_414);
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$views$library$styles$keys$ScreenSizeKey[getScreenSizeKey(i).ordinal()]) {
            case 1:
                return GranularScreenSizeKey.XSMALL;
            case 2:
                if (i < dimension) {
                    return GranularScreenSizeKey.SMALL_1;
                }
                if (i < dimension2) {
                    return GranularScreenSizeKey.SMALL_2;
                }
                return GranularScreenSizeKey.SMALL_3;
            case 3:
                return GranularScreenSizeKey.MEDIUM;
            case 4:
                return GranularScreenSizeKey.LARGE;
            case 5:
                return GranularScreenSizeKey.XLARGE;
            case 6:
                return GranularScreenSizeKey.XLARGE2;
            default:
                return GranularScreenSizeKey.XLARGE3;
        }
    }
}
