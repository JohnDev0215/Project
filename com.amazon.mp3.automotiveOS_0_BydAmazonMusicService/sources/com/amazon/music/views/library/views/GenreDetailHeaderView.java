package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenreDetailHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010O\u001a\u00020PH\u0002J\b\u0010Q\u001a\u00020PH\u0002J\u0016\u0010R\u001a\u00020P2\u0006\u00102\u001a\u0002032\u0006\u0010S\u001a\u00020TJ\u0015\u0010U\u001a\u00020P2\b\u0010V\u001a\u0004\u0018\u00010'¢\u0006\u0002\u0010+R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\f\u001a\u0004\b\u001a\u0010\u0010R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\f\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b#\u0010$R\u001e\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\f\u001a\u0004\b/\u00100R$\u00104\u001a\u0002032\u0006\u00102\u001a\u0002038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0016\"\u0004\b;\u0010\u0018R(\u0010>\u001a\u0004\u0018\u00010=2\b\u0010<\u001a\u0004\u0018\u00010=@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0016\"\u0004\bE\u0010\u0018R\u001a\u0010F\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0016\"\u0004\bH\u0010\u0018R$\u0010J\u001a\u0002032\u0006\u0010I\u001a\u0002038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bK\u00106\"\u0004\bL\u00108R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bM\u0010N¨\u0006W"}, d2 = {"Lcom/amazon/music/views/library/views/GenreDetailHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "equilizerContainerButton", "Landroid/widget/LinearLayout;", "getEquilizerContainerButton", "()Landroid/widget/LinearLayout;", "equilizerContainerButton$delegate", "Lkotlin/Lazy;", "equilizerContainerTextView", "Landroid/widget/TextView;", "getEquilizerContainerTextView", "()Landroid/widget/TextView;", "equilizerContainerTextView$delegate", "title", "", "headerText", "getHeaderText", "()Ljava/lang/String;", "setHeaderText", "(Ljava/lang/String;)V", "headerTextView", "getHeaderTextView", "headerTextView$delegate", "largeStationButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getLargeStationButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "largeStationButton$delegate", "mPlayNotificationIcon", "Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "getMPlayNotificationIcon", "()Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "mPlayNotificationIcon$delegate", "mPlayState", "", "getMPlayState", "()Ljava/lang/Integer;", "setMPlayState", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "playerButtonIcon", "Landroid/graphics/drawable/Drawable;", "getPlayerButtonIcon", "()Landroid/graphics/drawable/Drawable;", "playerButtonIcon$delegate", "show", "", "showLargeStationButton", "getShowLargeStationButton", "()Z", "setShowLargeStationButton", "(Z)V", "stationButtonActionText", "getStationButtonActionText", "setStationButtonActionText", "onClickListener", "Landroid/view/View$OnClickListener;", "stationButtonClickListener", "getStationButtonClickListener", "()Landroid/view/View$OnClickListener;", "setStationButtonClickListener", "(Landroid/view/View$OnClickListener;)V", "stationButtonPausedText", "getStationButtonPausedText", "setStationButtonPausedText", "stationButtonPlayingText", "getStationButtonPlayingText", "setStationButtonPlayingText", "enabled", "stationEligibility", "getStationEligibility", "setStationEligibility", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "initMargins", "", "initViewStyling", "setAndShowStationButton", "stationMetadata", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "setPlayStateAndShowStationEquilizerButtonView", "playState", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenreDetailHeaderView extends ConstraintLayout {

    /* JADX INFO: renamed from: equilizerContainerButton$delegate, reason: from kotlin metadata */
    private final Lazy equilizerContainerButton;

    /* JADX INFO: renamed from: equilizerContainerTextView$delegate, reason: from kotlin metadata */
    private final Lazy equilizerContainerTextView;

    /* JADX INFO: renamed from: headerTextView$delegate, reason: from kotlin metadata */
    private final Lazy headerTextView;

    /* JADX INFO: renamed from: largeStationButton$delegate, reason: from kotlin metadata */
    private final Lazy largeStationButton;

    /* JADX INFO: renamed from: mPlayNotificationIcon$delegate, reason: from kotlin metadata */
    private final Lazy mPlayNotificationIcon;
    private Integer mPlayState;

    /* JADX INFO: renamed from: playerButtonIcon$delegate, reason: from kotlin metadata */
    private final Lazy playerButtonIcon;
    private String stationButtonActionText;
    private View.OnClickListener stationButtonClickListener;
    private String stationButtonPausedText;
    private String stationButtonPlayingText;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenreDetailHeaderView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.headerTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.GenreDetailHeaderView$headerTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.header_text);
                if (viewFindViewById != null) {
                    return (TextView) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
        });
        this.largeStationButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.GenreDetailHeaderView$largeStationButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.large_station_button);
                if (viewFindViewById != null) {
                    return (BaseButton) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.ui.foundations.views.BaseButton");
            }
        });
        this.playerButtonIcon = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.GenreDetailHeaderView$playerButtonIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return this.this$0.getResources().getDrawable(R$drawable.player_btn_play);
            }
        });
        this.mPlayNotificationIcon = LazyKt__LazyJVMKt.lazy(new Function0<PlayNotificationIcon>() { // from class: com.amazon.music.views.library.views.GenreDetailHeaderView$mPlayNotificationIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayNotificationIcon invoke() {
                return (PlayNotificationIcon) this.this$0.findViewById(R$id.PlaystateIcon);
            }
        });
        this.equilizerContainerTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.GenreDetailHeaderView$equilizerContainerTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.internal_storage_state);
                if (viewFindViewById != null) {
                    return (TextView) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
        });
        this.equilizerContainerButton = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.GenreDetailHeaderView$equilizerContainerButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.play_equalizer);
            }
        });
        String string = getResources().getString(R$string.content_description_play_button);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…_description_play_button)");
        this.stationButtonActionText = string;
        String string2 = getResources().getString(R$string.content_description_playing);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…tent_description_playing)");
        this.stationButtonPlayingText = string2;
        String string3 = getResources().getString(R$string.content_description_paused);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…ntent_description_paused)");
        this.stationButtonPausedText = string3;
        View.inflate(context, R$layout.dmmviewlibrary_genre_detail_header_layout, this);
        initViewStyling();
        initMargins();
        getEquilizerContainerButton().setVisibility(8);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final TextView getHeaderTextView() {
        return (TextView) this.headerTextView.getValue();
    }

    private final BaseButton getLargeStationButton() {
        return (BaseButton) this.largeStationButton.getValue();
    }

    private final Drawable getPlayerButtonIcon() {
        Object value = this.playerButtonIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playerButtonIcon>(...)");
        return (Drawable) value;
    }

    private final PlayNotificationIcon getMPlayNotificationIcon() {
        Object value = this.mPlayNotificationIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mPlayNotificationIcon>(...)");
        return (PlayNotificationIcon) value;
    }

    private final TextView getEquilizerContainerTextView() {
        return (TextView) this.equilizerContainerTextView.getValue();
    }

    private final LinearLayout getEquilizerContainerButton() {
        Object value = this.equilizerContainerButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-equilizerContainerButton>(...)");
        return (LinearLayout) value;
    }

    public final Integer getMPlayState() {
        return this.mPlayState;
    }

    public final void setMPlayState(Integer num) {
        this.mPlayState = num;
    }

    public final String getStationButtonActionText() {
        return this.stationButtonActionText;
    }

    public final void setStationButtonActionText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationButtonActionText = str;
    }

    public final String getStationButtonPlayingText() {
        return this.stationButtonPlayingText;
    }

    public final void setStationButtonPlayingText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationButtonPlayingText = str;
    }

    public final String getStationButtonPausedText() {
        return this.stationButtonPausedText;
    }

    public final void setStationButtonPausedText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationButtonPausedText = str;
    }

    public final String getHeaderText() {
        return getHeaderTextView().getText().toString();
    }

    public final void setHeaderText(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getHeaderTextView().setText(title);
        getHeaderTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final boolean getShowLargeStationButton() {
        return getLargeStationButton().getVisibility() == 0;
    }

    public final void setShowLargeStationButton(boolean z) {
        getLargeStationButton().setVisibility(z ? 0 : 8);
    }

    public final void setAndShowStationButton(boolean show, StationMetadata stationMetadata) {
        Intrinsics.checkNotNullParameter(stationMetadata, "stationMetadata");
        if (show) {
            BaseButton.StyleBuilder buttonBuilder = this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.GLASS);
            String callToActionTitle = stationMetadata.getCallToActionTitle();
            if (callToActionTitle != null) {
                setStationButtonActionText(callToActionTitle);
            }
            String playingStateTitle = stationMetadata.getPlayingStateTitle();
            if (playingStateTitle != null) {
                setStationButtonPlayingText(playingStateTitle);
            }
            String pausedStateTitle = stationMetadata.getPausedStateTitle();
            if (pausedStateTitle != null) {
                setStationButtonPausedText(pausedStateTitle);
            }
            if (buttonBuilder != null) {
                buttonBuilder.withIcon(getPlayerButtonIcon());
                buttonBuilder.withText(getStationButtonActionText());
                buttonBuilder.applyStyle(getLargeStationButton());
            }
            LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
            BaseButton largeStationButton = getLargeStationButton();
            StyleSheet styleSheet = this.styleSheet;
            SpacerKey spacerKey = SpacerKey.LARGE;
            layoutParamUtils.setLayoutMargins(largeStationButton, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : styleSheet.getSpacerInPixels(spacerKey), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : this.styleSheet.getSpacerInPixels(spacerKey));
        }
        setShowLargeStationButton(show);
    }

    public final View.OnClickListener getStationButtonClickListener() {
        return this.stationButtonClickListener;
    }

    public final void setStationButtonClickListener(View.OnClickListener onClickListener) {
        getLargeStationButton().setOnClickListener(onClickListener);
    }

    public final boolean getStationEligibility() {
        return getLargeStationButton().isEnabled();
    }

    public final void setStationEligibility(boolean z) {
        getLargeStationButton().setEnabled(z);
    }

    private final void initViewStyling() {
        TextView headerTextView = getHeaderTextView();
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.HEADLINE_2);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(headerTextView, fontStyle);
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(headerTextView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.TRUE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        TextView equilizerContainerTextView = getEquilizerContainerTextView();
        FontStyle fontStyle2 = getStyleSheet().getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(equilizerContainerTextView, fontStyle2);
    }

    private final void initMargins() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        StyleSheet styleSheet2 = this.styleSheet;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        GridSize gridSize2 = styleSheet2.getGridSize(context2);
        int columnSpace = marginSpace + (gridSize2 == null ? 0 : gridSize2.getColumnSpace());
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.TRUE, (240 & 16) != 0 ? null : Integer.valueOf(columnSpace), (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(columnSpace), (240 & 128) != 0 ? null : Integer.valueOf(spacerInPixels2 == null ? 0 : spacerInPixels2.intValue()));
        setPadding(0, iIntValue, 0, 0);
    }

    public final void setPlayStateAndShowStationEquilizerButtonView(Integer playState) {
        Integer num = this.mPlayState;
        if (num != null && num.intValue() == 0 && playState != null && playState.intValue() == 0) {
            return;
        }
        if (this.mPlayState == null && playState != null && playState.intValue() == 0) {
            getEquilizerContainerButton().setVisibility(8);
            getLargeStationButton().setVisibility(0);
            this.mPlayState = playState;
            setEnabled(false);
            return;
        }
        getMPlayNotificationIcon().setIsPlaying(playState != null && playState.intValue() == 2);
        getLargeStationButton().setVisibility(8);
        getEquilizerContainerButton().setVisibility(0);
        this.mPlayState = playState;
        getEquilizerContainerTextView().setText((playState != null && playState.intValue() == 2) ? this.stationButtonPlayingText : this.stationButtonPausedText);
        setEnabled(true);
    }
}
