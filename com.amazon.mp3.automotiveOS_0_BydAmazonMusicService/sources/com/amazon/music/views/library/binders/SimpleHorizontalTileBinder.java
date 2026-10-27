package com.amazon.music.views.library.binders;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.recyclerview.CompositeClickListener;
import com.amazon.music.views.library.recyclerview.DefaultItemClickListener;
import com.amazon.music.views.library.recyclerview.OnItemClickListener;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.utils.MediaUtil;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.PlayNotificationIcon;
import com.amazon.music.views.library.views.SimpleHorizontalTileView;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SimpleHorizontalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BS\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0003H\u0016J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016J \u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/music/views/library/binders/SimpleHorizontalTileBinder;", "Lcom/amazon/music/views/library/binders/LibraryAddableBinder;", "Lcom/amazon/music/views/library/views/SimpleHorizontalTileView;", "Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "itemClickListener", "Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "position", "", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SimpleHorizontalTileBinder extends LibraryAddableBinder<SimpleHorizontalTileView, SimpleHorizontalTileModel> {
    private final ArtworkFrameBinder artworkFrameBinder;
    private final ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final OnItemClickListener itemClickListener;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: SimpleHorizontalTileBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HorizontalTileType.values().length];
            iArr[HorizontalTileType.FEATURED.ordinal()] = 1;
            iArr[HorizontalTileType.REGULAR.ordinal()] = 2;
            iArr[HorizontalTileType.DETAILED.ordinal()] = 3;
            iArr[HorizontalTileType.COMPACT.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SimpleHorizontalTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SimpleHorizontalTileModel.class);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleHorizontalTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ContentEligibilityProvider contentEligibilityProvider, ArtworkFrameBinder artworkFrameBinder, RowButtonOnClickProvider rowButtonOnClickProvider, ContentEnabilityProvider contentEnabilityProvider, ClickListenerFactory clickListenerFactory, OnItemClickListener onItemClickListener) {
        super(userSubscription, contentEligibilityProvider, rowButtonOnClickProvider, null);
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(artworkFrameBinder, "artworkFrameBinder");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        this.styleSheet = styleSheet;
        this.artworkFrameBinder = artworkFrameBinder;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.itemClickListener = onItemClickListener;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SimpleHorizontalTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SimpleHorizontalTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.LibraryAddableBinder
    public void handleStateChange(SimpleHorizontalTileView view, SimpleHorizontalTileModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 1)) {
            if (model.getPlayState() != null) {
                if (MediaUtil.INSTANCE.isPodcastEntity(model.getContentType())) {
                    view.setPodcastPlayState(model.getPlayState());
                    return;
                } else {
                    view.setPlayState(model.getPlayState());
                    return;
                }
            }
            return;
        }
        super.handleStateChange(view, model, payload);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final SimpleHorizontalTileView view, final SimpleHorizontalTileModel model) {
        float dimension;
        Integer imageSize;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        ArtworkFrameView artworkFrameLayout = view.getArtworkFrameLayout();
        ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
        int i = WhenMappings.$EnumSwitchMapping$0[model.getHorizontalTileType().ordinal()];
        if (i == 1) {
            dimension = view.getContext().getResources().getDimension(R$dimen.artwork_dimen_featured_small);
        } else if (i == 2 || i == 3) {
            dimension = view.getContext().getResources().getDimension(R$dimen.artwork_default_dimen);
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            dimension = view.getContext().getResources().getDimension(R$dimen.artwork_dimen_compact);
        }
        int iIntValue = (int) dimension;
        ViewGroup.LayoutParams layoutParams = view.getArtworkFrameViewContainer().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ((ConstraintLayout.LayoutParams) layoutParams).verticalBias = 0.5f;
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        Resources resources = view.getContext().getResources();
        int i2 = R$dimen.spacer_16;
        layoutParamUtils.setLayoutMargins(artworkFrameLayout, iIntValue, iIntValue, Boolean.FALSE, Integer.valueOf((int) view.getContext().getResources().getDimension(R$dimen.spacer_12)), Integer.valueOf((int) resources.getDimension(i2)), 0, Integer.valueOf((int) view.getContext().getResources().getDimension(i2)));
        if (artworkFrameModel != null) {
            this.artworkFrameBinder.bind(artworkFrameLayout, artworkFrameModel);
        }
        ImageStyle imageStyle = this.styleSheet.getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            artworkFrameLayout.getArtworkImageView().setImageStyle(imageStyle);
        }
        ArtworkFrameModel artworkFrameModel2 = model.getArtworkFrameModel();
        if (artworkFrameModel2 != null && (imageSize = artworkFrameModel2.getImageSize()) != null) {
            iIntValue = imageSize.intValue();
        }
        ImageSize imageSize2 = this.styleSheet.getImageSize(iIntValue);
        if (imageSize2 != null) {
            artworkFrameLayout.getArtworkImageView().setImageSize(imageSize2);
            artworkFrameLayout.setArtworkSize(-2, -2);
        }
        artworkFrameLayout.disablePlayNotificationIcon();
        artworkFrameLayout.setPlayIcon((Integer) null);
        PlayNotificationIcon.setStyle$default(view.getEqualizerIcon(), null, this.styleSheet.getColor(ColorKey.COLOR_CAR_MODE_EQUALIZER), 1, null);
        view.setBackgroundColor(0);
        view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.SimpleHorizontalTileBinder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SimpleHorizontalTileBinder.m2363bind$lambda2(view, model);
            }
        });
        if (model.getHorizontalTileType() == HorizontalTileType.FEATURED) {
            String subTitle = model.getSubTitle();
            if (subTitle != null) {
                view.setSubtitle(subTitle);
            }
            view.setTitleFontStyle(FontStyleKey.SECONDARY_HUGE);
            view.setSubTitleFontStyle(FontStyleKey.SECONDARY_BIG);
            view.setBackground(view.getContext().getDrawable(R$drawable.round_corner_tile));
            view.updateFeaturedPresetMargin();
        } else if (model.getHorizontalTileType() == HorizontalTileType.DETAILED) {
            String subTitle2 = model.getSubTitle();
            if (subTitle2 != null) {
                view.setSubtitle(subTitle2);
            }
            Integer ordinal = model.getOrdinal();
            if (ordinal != null) {
                view.setOrdinal(ordinal.intValue());
            }
            view.setTitleFontStyle(FontStyleKey.PRIMARY_HUGE);
            view.setSubTitleFontStyle(FontStyleKey.SECONDARY);
        } else {
            Integer ordinal2 = model.getOrdinal();
            if (ordinal2 != null) {
                view.setOrdinal(ordinal2.intValue());
            }
            String subTitle3 = model.getSubTitle();
            if (subTitle3 != null) {
                view.setSubtitle(subTitle3);
            }
            view.setTitleFontStyle(FontStyleKey.HEADLINE_4);
            view.setSubTitleFontStyle(FontStyleKey.PRIMARY_GLASS_T2_BIG_EMBER_RG);
            view.getRowButtonView().setVisibility(8);
            view.getRowButtonContainerView().setVisibility(8);
        }
        String title = model.getTitle();
        if (title != null) {
            view.setTitle(title);
        }
        model.setEnableAdd(false);
        view.setVisibility(0);
        ContentEnabilityProvider contentEnabilityProvider = this.contentEnabilityProvider;
        if (contentEnabilityProvider != null) {
            contentEnabilityProvider.setContentEnabled(view, model);
        }
        if (MediaUtil.INSTANCE.isPodcastEntity(model.getContentType())) {
            view.setPodcastPlayState(model.getPlayState());
        } else {
            view.setPlayState(model.getPlayState());
        }
        artworkFrameLayout.enableStationBadge(false);
        bindLibraryState(view, model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2, reason: not valid java name */
    public static final void m2363bind$lambda2(final SimpleHorizontalTileView view, final SimpleHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.SimpleHorizontalTileBinder$bind$3$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (model.getHorizontalTileType() != HorizontalTileType.REGULAR) {
                    view.hideTransparentGradientOverlay();
                }
            }
        });
    }

    public final void bind(SimpleHorizontalTileView view, SimpleHorizontalTileModel model, int position) {
        View.OnClickListener onClickListenerCreate;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model);
        List<ContentMetadata> metadataList = model.getMetadataList();
        if (metadataList != null) {
            onClickListenerCreate = this.clickListenerFactory.create(metadataList, metadataList.indexOf(model.getMetadata()), model.getTitle());
        } else {
            onClickListenerCreate = this.clickListenerFactory.create(model.getMetadata());
        }
        view.setOnClickListener(new CompositeClickListener(CollectionsKt__CollectionsKt.listOf((Object[]) new View.OnClickListener[]{onClickListenerCreate, new DefaultItemClickListener(this.itemClickListener, position)})));
    }
}
