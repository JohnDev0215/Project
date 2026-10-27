package com.amazon.mp3.view.player;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.view.FocusedTextView;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: PlayerMetadataView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 m2\u00020\u0001:\u0001mB'\b\u0017\u0012\u0006\u0010g\u001a\u00020f\u0012\n\b\u0002\u0010i\u001a\u0004\u0018\u00010h\u0012\b\b\u0002\u0010j\u001a\u00020Z¢\u0006\u0004\bk\u0010lJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001c\u0010\r\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bJ$\u0010\u0011\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\tJ\u0010\u0010\u0013\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0012J\u001a\u0010\u0016\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\tJ\u0006\u0010\u0017\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019J\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0006J\u0006\u0010!\u001a\u00020\u0006J\u000e\u0010#\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0006J\u000e\u0010$\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u0010\u0010%\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\"\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020&2\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010*\u001a\u00020)H\u0002J\u0010\u0010,\u001a\u00020\u00042\u0006\u0010*\u001a\u00020)H\u0002J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010*\u001a\u00020)H\u0002J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010*\u001a\u00020)H\u0002R\u001b\u00104\u001a\u00020/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u001b\u00108\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u00101\u001a\u0004\b6\u00107R\u001b\u0010=\u001a\u0002098FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b:\u00101\u001a\u0004\b;\u0010<R\u001b\u0010B\u001a\u00020>8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b?\u00101\u001a\u0004\b@\u0010AR\u001b\u0010E\u001a\u00020>8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u00101\u001a\u0004\bD\u0010AR\u001b\u0010I\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bF\u00101\u001a\u0004\bG\u0010HR\u001b\u0010L\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bJ\u00101\u001a\u0004\bK\u0010HR\u001b\u0010O\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bM\u00101\u001a\u0004\bN\u00107R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010PR\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010SR\u0018\u0010T\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010SR\u0018\u0010U\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010SR\u0018\u0010W\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u001c\u0010[\u001a\b\u0012\u0004\u0012\u00020Z0Y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010\\R\u0018\u0010^\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0018\u0010`\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010_R\u0016\u0010a\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bc\u0010bR\u0014\u0010d\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010e¨\u0006n"}, d2 = {"Lcom/amazon/mp3/view/player/PlayerMetadataView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "initViewStyling", "", "show", "showBadging", "", "text", "Landroid/graphics/drawable/Drawable;", "badgeIcon", "setBadge", ContextMappingConstants.PRIMARY_TEXT, ContextMappingConstants.SECONDARY_TEXT, "descriptiveText", "setText", "", "setPrimaryText", "text1", "text2", "setSecondaryText", "switchToStageLandscape", "displayLoadingView", "Landroid/view/View$OnClickListener;", "onClickListener", "setBadgeOnClickListener", "setArtistNameTextOnClickLIstener", "initializeLikeButton", "setLikeButtonOnClickListener", AppStateModule.APP_STATE_ACTIVE, "setLikeButtonState", "isLikeButtonActive", Constants.ENABLE_DISABLE, "setLikeButtonEnabledState", "showLikeButton", "useSmallScreenStyling", "Lcom/amazon/ui/foundations/views/BaseButton;", "badge", "applyBadgeStyle", "Lcom/amazon/mp3/view/FocusedTextView;", "focusedTextView", "initializeMarquee", "initializeMarqueeWithoutRerunOnClick", "runMarqueeOnce", "stopMarquee", "Landroid/widget/LinearLayout;", "tagLayout$delegate", "Lkotlin/Lazy;", "getTagLayout", "()Landroid/widget/LinearLayout;", "tagLayout", "tag$delegate", "getTag", "()Lcom/amazon/ui/foundations/views/BaseButton;", ParserUtil.TAG_QUERY_PARAM_NAME, "Landroid/view/View;", "badgeShowcaseTarget$delegate", "getBadgeShowcaseTarget", "()Landroid/view/View;", "badgeShowcaseTarget", "Landroid/widget/TextView;", "loadingPrimaryTextView$delegate", "getLoadingPrimaryTextView", "()Landroid/widget/TextView;", "loadingPrimaryTextView", "loadingSecondaryTextView$delegate", "getLoadingSecondaryTextView", "loadingSecondaryTextView", "primaryTextView$delegate", "getPrimaryTextView", "()Lcom/amazon/mp3/view/FocusedTextView;", "primaryTextView", "secondaryTextView$delegate", "getSecondaryTextView", "secondaryTextView", "likeButton$delegate", "getLikeButton", "likeButton", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "tagBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "likeButtonBuilder", "likeButtonActiveBuilder", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "accentStyle", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "Landroidx/lifecycle/MutableLiveData;", "", "titleTopMargin", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/ui/foundations/styles/FontStyle;", "secondaryFontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "descriptiveFontStyle", "isSmallScreen", "Z", "isBadgingSupported", "focusedListener", "Landroid/view/View$OnClickListener;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlayerMetadataView extends ConstraintLayout {
    private ButtonStyle accentStyle;

    /* JADX INFO: renamed from: badgeShowcaseTarget$delegate, reason: from kotlin metadata */
    private final Lazy badgeShowcaseTarget;
    private FontStyle descriptiveFontStyle;
    private final View.OnClickListener focusedListener;
    private boolean isBadgingSupported;
    private boolean isSmallScreen;

    /* JADX INFO: renamed from: likeButton$delegate, reason: from kotlin metadata */
    private final Lazy likeButton;
    private BaseButton.StyleBuilder likeButtonActiveBuilder;
    private BaseButton.StyleBuilder likeButtonBuilder;

    /* JADX INFO: renamed from: loadingPrimaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy loadingPrimaryTextView;

    /* JADX INFO: renamed from: loadingSecondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy loadingSecondaryTextView;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;
    private FontStyle secondaryFontStyle;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;
    private StyleSheet styleSheet;

    /* JADX INFO: renamed from: tag$delegate, reason: from kotlin metadata */
    private final Lazy tag;
    private BaseButton.StyleBuilder tagBuilder;

    /* JADX INFO: renamed from: tagLayout$delegate, reason: from kotlin metadata */
    private final Lazy tagLayout;
    private MutableLiveData<Integer> titleTopMargin;

    /* JADX INFO: compiled from: PlayerMetadataView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XLARGE3.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlayerMetadataView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlayerMetadataView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.tagLayout = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$tagLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R.id.tag_layout);
            }
        });
        this.tag = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$tag$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.KatanaNPSBadge);
            }
        });
        this.badgeShowcaseTarget = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$badgeShowcaseTarget$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R.id.tag_target);
            }
        });
        this.loadingPrimaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$loadingPrimaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.loading_primary_text);
            }
        });
        this.loadingSecondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$loadingSecondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.loading_secondary_text);
            }
        });
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<FocusedTextView>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FocusedTextView invoke() {
                return (FocusedTextView) this.this$0.findViewById(R.id.PlayerTrackTitle);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<FocusedTextView>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FocusedTextView invoke() {
                return (FocusedTextView) this.this$0.findViewById(R.id.PlayerArtistName);
            }
        });
        this.likeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$likeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.LikeButton);
            }
        });
        this.titleTopMargin = new MutableLiveData<>();
        this.focusedListener = new View.OnClickListener() { // from class: com.amazon.mp3.view.player.PlayerMetadataView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerMetadataView.m1724focusedListener$lambda0(this.f$0, view);
            }
        };
        View.inflate(context, R.layout.player_metadata_view, this);
    }

    public /* synthetic */ PlayerMetadataView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final LinearLayout getTagLayout() {
        Object value = this.tagLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tagLayout>(...)");
        return (LinearLayout) value;
    }

    private final BaseButton getTag() {
        Object value = this.tag.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tag>(...)");
        return (BaseButton) value;
    }

    public final View getBadgeShowcaseTarget() {
        Object value = this.badgeShowcaseTarget.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-badgeShowcaseTarget>(...)");
        return (View) value;
    }

    private final TextView getLoadingPrimaryTextView() {
        Object value = this.loadingPrimaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-loadingPrimaryTextView>(...)");
        return (TextView) value;
    }

    private final TextView getLoadingSecondaryTextView() {
        Object value = this.loadingSecondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-loadingSecondaryTextView>(...)");
        return (TextView) value;
    }

    private final FocusedTextView getPrimaryTextView() {
        Object value = this.primaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextView>(...)");
        return (FocusedTextView) value;
    }

    private final FocusedTextView getSecondaryTextView() {
        Object value = this.secondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView>(...)");
        return (FocusedTextView) value;
    }

    private final BaseButton getLikeButton() {
        Object value = this.likeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-likeButton>(...)");
        return (BaseButton) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: focusedListener$lambda-0, reason: not valid java name */
    public static final void m1724focusedListener$lambda0(PlayerMetadataView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runMarqueeOnce(this$0.getPrimaryTextView());
        this$0.runMarqueeOnce(this$0.getSecondaryTextView());
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.tagBuilder = styleSheet.getTagBuilder(TagSizeKey.MEDIUM, TagStyleKey.OUTLINE_TIER_HD);
        IconSizeKey iconSizeKey = IconSizeKey.MEDIUM;
        IconStyleKey iconStyleKey = IconStyleKey.PRIMARY;
        this.likeButtonBuilder = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        this.likeButtonActiveBuilder = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        this.accentStyle = styleSheet.getIconStyle(IconStyleKey.ACCENT);
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            InputFilter[] inputFilterArr = {new InputFilter.LengthFilter(R.integer.stage_metadata_char_count)};
            getPrimaryTextView().setFilters(inputFilterArr);
            getSecondaryTextView().setFilters(inputFilterArr);
        }
        ColorKey colorKey = ColorKey.COLOR_PRIMARY;
        Integer color = styleSheet.getColor(colorKey, AlphaKey.GLASS_2);
        if (color != null) {
            int iIntValue = color.intValue();
            getLoadingPrimaryTextView().setBackgroundColor(iIntValue);
            getLoadingSecondaryTextView().setBackgroundColor(iIntValue);
        }
        if (useSmallScreenStyling(styleSheet) && this.isBadgingSupported) {
            FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
            if (fontStyle != null) {
                FontUtil fontUtil = FontUtil.INSTANCE;
                fontUtil.applyFontStyle(getLoadingPrimaryTextView(), fontStyle);
                fontUtil.applyFontStyle(getPrimaryTextView(), fontStyle);
            }
        } else {
            getSecondaryTextView().setVisibility(0);
            FontStyleKey fontStyleKey = (AmazonApplication.getCapabilities().isYAImmerseEnabled() || useSmallScreenStyling(styleSheet)) ? FontStyleKey.HEADLINE_4 : FontStyleKey.HEADLINE_3;
            FontStyle fontStyle2 = styleSheet.getFontStyle(fontStyleKey);
            if (fontStyle2 != null) {
                FontUtil fontUtil2 = FontUtil.INSTANCE;
                fontUtil2.applyFontStyle(getLoadingPrimaryTextView(), fontStyle2);
                fontUtil2.applyFontStyle(getPrimaryTextView(), fontStyle2);
            }
            Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI);
            if (spacerInPixels != null) {
                int iIntValue2 = spacerInPixels.intValue();
                LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
                layoutParamUtils.setLayoutMargins(getLoadingSecondaryTextView(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(iIntValue2), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
                layoutParamUtils.setLayoutMargins(getSecondaryTextView(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(iIntValue2), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
            }
        }
        FontStyle fontStyle3 = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle3 != null) {
            FontUtil fontUtil3 = FontUtil.INSTANCE;
            fontUtil3.applyFontStyle(getLoadingSecondaryTextView(), fontStyle3);
            this.secondaryFontStyle = fontStyle3;
            fontUtil3.applyFontStyle(getSecondaryTextView(), fontStyle3);
            Integer color2 = styleSheet.getColor(colorKey, AlphaKey.GLASS_4);
            if (color2 != null) {
                this.descriptiveFontStyle = FontStyle.copy$default(fontStyle3, null, null, color2.intValue(), null, 11, null);
            }
        }
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.EPIC);
        if (spacerInPixels2 != null) {
            getTagLayout().setLayoutParams(new ConstraintLayout.LayoutParams(-2, spacerInPixels2.intValue()));
        }
        initializeLikeButton();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean useSmallScreenStyling(com.amazon.music.views.library.styles.StyleSheet r4) {
        /*
            r3 = this;
            boolean r0 = com.amazon.mp3.util.ScreenUtil.isPortrait()
            if (r0 == 0) goto L1d
            com.amazon.music.views.library.styles.keys.ScreenSizeKey r0 = com.amazon.music.views.library.styles.keys.ScreenSizeKey.XSMALL
            android.content.Context r1 = r3.getContext()
            java.lang.String r2 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.amazon.music.views.library.styles.keys.ScreenSizeKey r4 = r4.getWidthScreenSizeKey(r1)
            int r4 = r0.compareTo(r4)
            if (r4 < 0) goto L1d
            r4 = 1
            goto L1e
        L1d:
            r4 = 0
        L1e:
            r3.isSmallScreen = r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.player.PlayerMetadataView.useSmallScreenStyling(com.amazon.music.views.library.styles.StyleSheet):boolean");
    }

    public final void showBadging(boolean show) {
        this.isBadgingSupported = show;
        getTagLayout().setVisibility(show ? 0 : 8);
        getTag().setVisibility(4);
    }

    public final void setBadge(String text, Drawable badgeIcon) {
        if (!TextUtils.isEmpty(text)) {
            BaseButton.StyleBuilder tagBuilder = null;
            if (Intrinsics.areEqual(text, getContext().getResources().getString(R.string.dmusic_nps_audio_quality_badge_sd_text))) {
                StyleSheet styleSheet = this.styleSheet;
                if (styleSheet != null) {
                    tagBuilder = styleSheet.getTagBuilder(TagSizeKey.MEDIUM, TagStyleKey.GLASS_PRIMARY);
                }
            } else {
                StyleSheet styleSheet2 = this.styleSheet;
                if (styleSheet2 != null) {
                    tagBuilder = styleSheet2.getTagBuilder(TagSizeKey.MEDIUM, TagStyleKey.OUTLINE_TIER_HD);
                }
            }
            this.tagBuilder = tagBuilder;
            BaseButton tag = getTag();
            Intrinsics.checkNotNull(text);
            applyBadgeStyle(tag, text, badgeIcon);
            getTag().setVisibility(0);
            return;
        }
        getTag().setVisibility(4);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void applyBadgeStyle(com.amazon.ui.foundations.views.BaseButton r5, java.lang.String r6, android.graphics.drawable.Drawable r7) {
        /*
            r4 = this;
            r0 = 0
            if (r7 == 0) goto Ld
            com.amazon.ui.foundations.views.BaseButton$StyleBuilder r1 = r4.tagBuilder
            if (r1 != 0) goto L8
            goto L11
        L8:
            com.amazon.ui.foundations.views.BaseButton$StyleBuilder r1 = r1.withIcon(r7)
            goto L1d
        Ld:
            com.amazon.ui.foundations.views.BaseButton$StyleBuilder r1 = r4.tagBuilder
            if (r1 != 0) goto L13
        L11:
            r1 = r0
            goto L1d
        L13:
            android.graphics.drawable.ColorDrawable r2 = new android.graphics.drawable.ColorDrawable
            r3 = 0
            r2.<init>(r3)
            com.amazon.ui.foundations.views.BaseButton$StyleBuilder r1 = r1.withIcon(r2)
        L1d:
            if (r1 != 0) goto L20
            goto L4a
        L20:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r1.withText(r6)
            r1.applyStyle(r5)
            if (r7 != 0) goto L33
            com.google.android.material.button.MaterialButton r5 = r5.getButton()
            r5.setIcon(r0)
            goto L4a
        L33:
            android.content.Context r6 = r4.getContext()
            android.content.res.Resources r6 = r6.getResources()
            r7 = 2131166658(0x7f0705c2, float:1.7947568E38)
            float r6 = r6.getDimension(r7)
            int r6 = (int) r6
            com.google.android.material.button.MaterialButton r5 = r5.getButton()
            r5.setIconPadding(r6)
        L4a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.player.PlayerMetadataView.applyBadgeStyle(com.amazon.ui.foundations.views.BaseButton, java.lang.String, android.graphics.drawable.Drawable):void");
    }

    public final void setText(String primaryText, String secondaryText, String descriptiveText) {
        if (this.isSmallScreen && this.isBadgingSupported) {
            setPrimaryText(TextUtils.concat(!TextUtils.isEmpty(primaryText) ? FontUtil.INSTANCE.getSpannableString(primaryText, this.secondaryFontStyle) : "", !TextUtils.isEmpty(secondaryText) ? FontUtil.INSTANCE.getSpannableString(" - ", this.descriptiveFontStyle) : "", !TextUtils.isEmpty(secondaryText) ? FontUtil.INSTANCE.getSpannableString(secondaryText, this.secondaryFontStyle) : "", TextUtils.isEmpty(descriptiveText) ? "" : FontUtil.INSTANCE.getSpannableString(Intrinsics.stringPlus(" - ", descriptiveText), this.descriptiveFontStyle)));
            setSecondaryText(null, null);
            return;
        }
        setPrimaryText(primaryText);
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            setSecondaryText(secondaryText, "");
        } else {
            setSecondaryText(secondaryText, descriptiveText);
        }
    }

    public final void setPrimaryText(CharSequence text) {
        getPrimaryTextView().setText(StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(String.valueOf(text)));
        initializeMarquee(getPrimaryTextView());
    }

    public final void setSecondaryText(String text1, String text2) {
        if (TextUtils.isEmpty(text1) || TextUtils.isEmpty(text2)) {
            FocusedTextView secondaryTextView = getSecondaryTextView();
            if (text1 == null) {
                text1 = text2;
            }
            if (text1 == null) {
                text1 = "";
            }
            secondaryTextView.setText(text1);
            FontStyle fontStyle = this.secondaryFontStyle;
            if (fontStyle != null) {
                FontUtil.INSTANCE.applyFontStyle(getSecondaryTextView(), fontStyle);
            }
        } else {
            FontUtil.INSTANCE.applyFontStyle(getSecondaryTextView(), text1, this.secondaryFontStyle, Intrinsics.stringPlus(" - ", text2), this.descriptiveFontStyle);
        }
        Providers providers = Providers.INSTANCE;
        AtomicMap<String, InterfaceProvider> providerInstances = providers.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(WeblabProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        WeblabTreatment treatment = null;
        if (!(interfaceProvider instanceof WeblabProvider)) {
            interfaceProvider = null;
        }
        WeblabProvider weblabProvider = (WeblabProvider) interfaceProvider;
        if (weblabProvider != null) {
            String string = Weblab.DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AA.toString();
            Intrinsics.checkNotNullExpressionValue(string, "DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AA.toString()");
            weblabProvider.getTreatment(string, true);
        }
        AtomicMap<String, InterfaceProvider> providerInstances2 = providers.getProviderInstances();
        String originalClassName2 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(WeblabProvider.class));
        Intrinsics.checkNotNull(originalClassName2);
        InterfaceProvider interfaceProvider2 = providerInstances2.get(originalClassName2);
        if (!(interfaceProvider2 instanceof WeblabProvider)) {
            interfaceProvider2 = null;
        }
        WeblabProvider weblabProvider2 = (WeblabProvider) interfaceProvider2;
        if (weblabProvider2 != null) {
            String string2 = Weblab.DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AB.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AB.toString()");
            treatment = weblabProvider2.getTreatment(string2, true);
        }
        if (treatment == WeblabTreatment.T1) {
            initializeMarqueeWithoutRerunOnClick(getSecondaryTextView());
        } else {
            initializeMarquee(getSecondaryTextView());
        }
    }

    public final void switchToStageLandscape() {
        Integer spacerInPixels;
        Integer spacerInPixels2;
        ScreenSizeKey widthScreenSizeKey;
        Integer color;
        FontStyle fontStyle;
        FontStyle fontStyleCopy$default;
        FontStyle fontStyle2;
        FontStyle fontStyleCopy$default2;
        Integer color2;
        StyleSheet styleSheet = this.styleSheet;
        int iIntValue = (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.LARGE)) == null) ? 0 : spacerInPixels.intValue();
        StyleSheet styleSheet2 = this.styleSheet;
        int iIntValue2 = (styleSheet2 == null || (spacerInPixels2 = styleSheet2.getSpacerInPixels(SpacerKey.SMALL)) == null) ? 0 : spacerInPixels2.intValue();
        FocusedTextView primaryTextView = getPrimaryTextView();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getPrimaryTextView().getLayoutParams());
        layoutParams.weight = 1.0f;
        layoutParams.gravity = GravityCompat.END;
        int i = iIntValue + iIntValue2;
        layoutParams.setMarginEnd(i);
        primaryTextView.setLayoutParams(layoutParams);
        FocusedTextView secondaryTextView = getSecondaryTextView();
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(getSecondaryTextView().getLayoutParams());
        layoutParams2.weight = 1.0f;
        layoutParams2.gravity = GravityCompat.END;
        layoutParams2.setMarginEnd(i);
        secondaryTextView.setLayoutParams(layoutParams2);
        StyleSheet styleSheet3 = this.styleSheet;
        if (styleSheet3 == null) {
            widthScreenSizeKey = null;
        } else {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            widthScreenSizeKey = styleSheet3.getWidthScreenSizeKey(context);
        }
        if ((widthScreenSizeKey == null ? -1 : WhenMappings.$EnumSwitchMapping$0[widthScreenSizeKey.ordinal()]) == 1) {
            StyleSheet styleSheet4 = this.styleSheet;
            if (styleSheet4 != null && (fontStyle2 = styleSheet4.getFontStyle(FontStyleKey.TABS)) != null && (fontStyleCopy$default2 = FontStyle.copy$default(fontStyle2, null, null, 0, Boolean.FALSE, 7, null)) != null) {
                FontUtil fontUtil = FontUtil.INSTANCE;
                fontUtil.applyFontStyle(getPrimaryTextView(), fontStyleCopy$default2);
                fontUtil.applyFontStyle(getSecondaryTextView(), fontStyleCopy$default2);
                StyleSheet styleSheet5 = this.styleSheet;
                if (styleSheet5 != null && (color2 = styleSheet5.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_4)) != null) {
                    int iIntValue3 = color2.intValue();
                    getSecondaryTextView().setTextColor(iIntValue3);
                    this.secondaryFontStyle = new FontStyle(fontStyleCopy$default2.getFontSize(), fontStyleCopy$default2.getTypeface(), iIntValue3, fontStyleCopy$default2.getAllCaps());
                }
            }
        } else {
            StyleSheet styleSheet6 = this.styleSheet;
            if (styleSheet6 != null && (fontStyle = styleSheet6.getFontStyle(FontStyleKey.TERTIARY)) != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, null, null, 0, Boolean.FALSE, 7, null)) != null) {
                FontSize fontSize = fontStyleCopy$default.getFontSize();
                Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_bold));
                Intrinsics.checkNotNullExpressionValue(typefaceCreateFromAsset, "createFromAsset(\n       …                        )");
                FontStyle fontStyle3 = new FontStyle(fontSize, typefaceCreateFromAsset, fontStyleCopy$default.getColor(), fontStyleCopy$default.getAllCaps());
                FontUtil fontUtil2 = FontUtil.INSTANCE;
                fontUtil2.applyFontStyle(getPrimaryTextView(), fontStyle3);
                fontUtil2.applyFontStyle(getSecondaryTextView(), fontStyle3);
                this.secondaryFontStyle = fontStyleCopy$default;
            }
            StyleSheet styleSheet7 = this.styleSheet;
            if (styleSheet7 != null && (color = styleSheet7.getColor(ColorKey.COLOR_PRIMARY)) != null) {
                getPrimaryTextView().setTextColor(color.intValue());
            }
        }
        showBadging(false);
        showLikeButton(false);
    }

    public final void displayLoadingView(boolean show) {
        getLoadingPrimaryTextView().setVisibility(show ? 0 : 8);
        getLoadingSecondaryTextView().setVisibility(show ? 0 : 8);
    }

    public final void setBadgeOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getTag().setOnClickListener(onClickListener);
        getTagLayout().setOnClickListener(onClickListener);
    }

    public final void setArtistNameTextOnClickLIstener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getSecondaryTextView().setOnClickListener(onClickListener);
    }

    public final void initializeLikeButton() {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilder3;
        BaseButton.StyleBuilder styleBuilder4;
        BaseButton.StyleBuilder styleBuilder5;
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            Drawable drawable = getContext().getDrawable(R.drawable.ic_action_favorite_svg);
            if (drawable != null && (styleBuilder5 = this.likeButtonBuilder) != null) {
                styleBuilder5.withIcon(drawable);
            }
            Drawable drawable2 = getContext().getDrawable(R.drawable.ic_action_favoriteon_svg);
            if (drawable2 != null && (styleBuilder4 = this.likeButtonActiveBuilder) != null) {
                styleBuilder4.withIcon(drawable2);
            }
            ButtonStyle buttonStyle = this.accentStyle;
            if (buttonStyle != null && (styleBuilder3 = this.likeButtonActiveBuilder) != null) {
                styleBuilder3.withActiveStyle(buttonStyle);
            }
        } else {
            Drawable drawable3 = getContext().getDrawable(R.drawable.ic_action_like);
            if (drawable3 != null && (styleBuilder2 = this.likeButtonBuilder) != null) {
                styleBuilder2.withIcon(drawable3);
            }
            ButtonStyle buttonStyle2 = this.accentStyle;
            if (buttonStyle2 != null && (styleBuilder = this.likeButtonBuilder) != null) {
                styleBuilder.withActiveStyle(buttonStyle2);
            }
        }
        BaseButton.StyleBuilder styleBuilder6 = this.likeButtonBuilder;
        if (styleBuilder6 == null) {
            return;
        }
        styleBuilder6.applyStyle(getLikeButton());
    }

    public final void setLikeButtonOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getLikeButton().setOnClickListener(onClickListener);
    }

    public final void setLikeButtonState(boolean active) {
        BaseButton.StyleBuilder styleBuilder;
        int i = active ? R.string.dmusic_like_button_liked_state_desc : R.string.dmusic_like_button_neutral_state_desc;
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled() && (!active ? (styleBuilder = this.likeButtonBuilder) != null : (styleBuilder = this.likeButtonActiveBuilder) != null)) {
            styleBuilder.applyStyle(getLikeButton());
        }
        getLikeButton().setActive(active);
        getLikeButton().setContentDescription(getContext().getResources().getString(i));
    }

    public final boolean isLikeButtonActive() {
        return getLikeButton().getIsActive();
    }

    public final void setLikeButtonEnabledState(boolean isEnabled) {
        getLikeButton().setEnabled(isEnabled);
    }

    public final void showLikeButton(boolean show) {
        if (show) {
            getLikeButton().setVisibility(0);
        } else {
            getLikeButton().setVisibility(8);
        }
    }

    private final void initializeMarquee(final FocusedTextView focusedTextView) {
        stopMarquee(focusedTextView);
        focusedTextView.setOnClickListener(null);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.mp3.view.player.PlayerMetadataView.initializeMarquee.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (focusedTextView.getLayout() == null) {
                    return true;
                }
                this.getViewTreeObserver().removeOnPreDrawListener(this);
                if (!StringsKt__StringsJVMKt.equals(r0.getText().toString(), focusedTextView.getText().toString(), true)) {
                    focusedTextView.setOnClickListener(this.focusedListener);
                    this.runMarqueeOnce(focusedTextView);
                }
                return true;
            }
        });
    }

    private final void initializeMarqueeWithoutRerunOnClick(final FocusedTextView focusedTextView) {
        stopMarquee(focusedTextView);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.mp3.view.player.PlayerMetadataView.initializeMarqueeWithoutRerunOnClick.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (focusedTextView.getLayout() == null) {
                    return true;
                }
                this.getViewTreeObserver().removeOnPreDrawListener(this);
                if (!Intrinsics.areEqual(r0.getText(), focusedTextView.getText())) {
                    this.runMarqueeOnce(focusedTextView);
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runMarqueeOnce(FocusedTextView focusedTextView) {
        if (focusedTextView.isFocused()) {
            stopMarquee(focusedTextView);
        }
        focusedTextView.setFocused(true);
        focusedTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        focusedTextView.setMarqueeRepeatLimit(1);
    }

    private final void stopMarquee(FocusedTextView focusedTextView) {
        focusedTextView.setEllipsize(TextUtils.TruncateAt.END);
        focusedTextView.setFocused(false);
    }
}
