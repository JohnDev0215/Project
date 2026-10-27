package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.SimpleVerticalTileModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.SimpleVerticalTileView;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SimpleVerticalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B1\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018J \u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0002J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 H\u0016J(\u0010!\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/amazon/music/views/library/binders/SimpleVerticalTileBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/SimpleVerticalTileView;", "Lcom/amazon/music/views/library/models/SimpleVerticalTileModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "width", "", "bindView", "clearView", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "updateSize", "height", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SimpleVerticalTileBinder implements BaseStateChangeBinder<SimpleVerticalTileView, SimpleVerticalTileModel> {
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final StyleSheet styleSheet;
    private final UserSubscription userSubscription;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SimpleVerticalTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SimpleVerticalTileModel.class);
    }

    public SimpleVerticalTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ArtworkFrameBinder artworkFrameBinder, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(artworkFrameBinder, "artworkFrameBinder");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        this.styleSheet = styleSheet;
        this.userSubscription = userSubscription;
        this.artworkFrameBinder = artworkFrameBinder;
        this.clickListenerFactory = clickListenerFactory;
        this.contentEnabilityProvider = contentEnabilityProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SimpleVerticalTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SimpleVerticalTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(SimpleVerticalTileView view, SimpleVerticalTileModel model, Object payload) {
        ArtworkFrameModel artworkFrameModel;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!Intrinsics.areEqual(payload, (Object) 1) || (artworkFrameModel = model.getArtworkFrameModel()) == null) {
            return;
        }
        this.artworkFrameBinder.handleStateChange(view.getArtworkFrameView(), artworkFrameModel, payload);
    }

    public final void bind(SimpleVerticalTileView view, SimpleVerticalTileModel model, int width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bindView(view, model, width);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SimpleVerticalTileView view, SimpleVerticalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, -1);
    }

    private final void bindView(final SimpleVerticalTileView view, final SimpleVerticalTileModel model, int width) {
        clearView(view);
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        float fraction = artworkFrameView.getContext().getResources().getFraction(R$fraction.aspect_ratio_1_1, 1, 1);
        float f = width;
        int i = (int) (f / fraction);
        final int i2 = (int) (f * fraction);
        if (width == -2 || width == -1) {
            view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.SimpleVerticalTileBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SimpleVerticalTileBinder.m2365bindView$lambda1(view, this, model, i2);
                }
            });
        } else {
            updateSize(view, model, width, i);
        }
        ImageStyle imageStyle = this.styleSheet.getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            artworkFrameView.getArtworkImageView().setImageStyle(imageStyle);
        }
        artworkFrameView.getArtworkImageView().setScalingFactor(fraction);
        ArtworkFrameBinder artworkFrameBinder = this.artworkFrameBinder;
        ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
        Intrinsics.checkNotNull(artworkFrameModel);
        artworkFrameBinder.bind(artworkFrameView, artworkFrameModel);
        ButtonSize buttonSize = this.styleSheet.getButtonSize(ButtonSizeKey.LARGE);
        Integer height = buttonSize == null ? null : buttonSize.getHeight();
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_ACCENT);
        if (height != null && color != null) {
            artworkFrameView.setPlayNotificationIconStyle(height.intValue(), color.intValue());
        }
        ArtworkFrameView.setPlayIconGravity$default(artworkFrameView, 8388691, false, 2, null);
        ContentMetadata metadata = model.getMetadata();
        String title = model.getTitle();
        if (title != null) {
            view.setTitle(title);
        }
        view.setVisibility(0);
        ContentEnabilityProvider contentEnabilityProvider = this.contentEnabilityProvider;
        if (contentEnabilityProvider != null) {
            contentEnabilityProvider.setContentEnabled(view, model);
        }
        artworkFrameView.setPlayIconClickListener(this.clickListenerFactory.create(metadata));
        List<ContentMetadata> metadataList = model.getMetadataList();
        if (metadataList != null) {
            view.setOnClickListener(ClickListenerFactory.DefaultImpls.create$default(this.clickListenerFactory, metadataList, metadataList.indexOf(model.getMetadata()), null, 4, null));
        } else {
            view.setOnClickListener(this.clickListenerFactory.create(metadata));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindView$lambda-1, reason: not valid java name */
    public static final void m2365bindView$lambda1(final SimpleVerticalTileView view, final SimpleVerticalTileBinder this$0, final SimpleVerticalTileModel model, final int i) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.SimpleVerticalTileBinder$bindView$1$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                SimpleVerticalTileBinder simpleVerticalTileBinder = this$0;
                SimpleVerticalTileView simpleVerticalTileView = view;
                simpleVerticalTileBinder.updateSize(simpleVerticalTileView, model, i, simpleVerticalTileView.getMeasuredWidth());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSize(SimpleVerticalTileView view, SimpleVerticalTileModel model, int width, int height) {
        ImageSize imageSize;
        Integer contentType;
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
        boolean z = false;
        if (artworkFrameModel != null && (contentType = artworkFrameModel.getContentType()) != null && contentType.intValue() == 4) {
            z = true;
        }
        if (z) {
            imageSize = ImageSize.INSTANCE.getRoundImageSize(width);
        } else {
            imageSize = new ImageSize(width, Integer.valueOf(this.styleSheet.getImageCornerSize(height)));
        }
        artworkFrameView.setArtworkSize(imageSize);
        artworkFrameView.setLayoutParams(new ConstraintLayout.LayoutParams(width, -2));
    }

    private final void clearView(SimpleVerticalTileView view) {
        view.setTitle("");
        view.getArtworkFrameView().setPlayIcon((Integer) null);
    }
}
