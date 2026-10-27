package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.picassoimageloader.CircleTransformation;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.models.FeaturedToggleViewModel;
import com.amazon.music.views.library.providers.FeaturedToggleListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.utils.GradientOpacityTransformation;
import com.amazon.music.views.library.utils.RoundedCornersTransformation;
import com.amazon.music.views.library.views.FeaturedToggleView;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: FeaturedToggleViewBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0003H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/views/library/binders/FeaturedToggleViewBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/FeaturedToggleView;", "Lcom/amazon/music/views/library/models/FeaturedToggleViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "clickListener", "Lcom/amazon/music/views/library/providers/FeaturedToggleListenerProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/FeaturedToggleListenerProvider;Lcom/amazon/music/uicontentview/ContentViewedListener;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "bindToggle", "configureIcons", "toggleType", "Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ToggleType;", "createView", "context", "Landroid/content/Context;", "loadArtwork", "reportContentView", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedToggleViewBinder implements UniversalBinder<FeaturedToggleView, FeaturedToggleViewModel> {
    private final FeaturedToggleListenerProvider clickListener;
    private final ContentViewedListener contentViewedListener;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: FeaturedToggleViewBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FeaturedToggleViewModel.ToggleType.values().length];
            iArr[FeaturedToggleViewModel.ToggleType.ARTIST_COMMENTARY.ordinal()] = 1;
            iArr[FeaturedToggleViewModel.ToggleType.AAUP_SELECTION.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<FeaturedToggleViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(FeaturedToggleViewModel.class);
    }

    public FeaturedToggleViewBinder(StyleSheet styleSheet, FeaturedToggleListenerProvider clickListener, ContentViewedListener contentViewedListener) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        this.styleSheet = styleSheet;
        this.clickListener = clickListener;
        this.contentViewedListener = contentViewedListener;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public FeaturedToggleView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new FeaturedToggleView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(FeaturedToggleView view, FeaturedToggleViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.configureTexts(model.getPrimaryText(), model.getLabelText());
        configureIcons(view, model.getToggleType());
        bindToggle(view, model);
        loadArtwork(view, model);
        reportContentView(model);
    }

    private final void bindToggle(final FeaturedToggleView view, final FeaturedToggleViewModel model) {
        view.getToggleButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.FeaturedToggleViewBinder$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FeaturedToggleViewBinder.m2312bindToggle$lambda1(this.f$0, model, view, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindToggle$lambda-1, reason: not valid java name */
    public static final void m2312bindToggle$lambda1(FeaturedToggleViewBinder this$0, FeaturedToggleViewModel model, FeaturedToggleView view, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        this$0.clickListener.onClick(model, view);
    }

    private final void loadArtwork(final FeaturedToggleView view, final FeaturedToggleViewModel model) {
        if (model.getPrimaryImage() == null || model.getSecondaryImage() == null) {
            view.hideArtworkContainerView();
        }
        if (model.getPrimaryImage() == null || model.getSecondaryImage() == null) {
            return;
        }
        view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.FeaturedToggleViewBinder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FeaturedToggleViewBinder.m2313loadArtwork$lambda5$lambda4$lambda2(model, this, view);
            }
        });
        view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.FeaturedToggleViewBinder$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FeaturedToggleViewBinder.m2314loadArtwork$lambda5$lambda4$lambda3(model, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadArtwork$lambda-5$lambda-4$lambda-2, reason: not valid java name */
    public static final void m2313loadArtwork$lambda5$lambda4$lambda2(FeaturedToggleViewModel model, FeaturedToggleViewBinder this$0, FeaturedToggleView view) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Picasso.get().load(model.getPrimaryImage().getUrl()).placeholder(model.getPrimaryImage().getPlaceholderResId()).fit().transform(new GradientOpacityTransformation()).transform(new RoundedCornersTransformation(this$0.styleSheet.getCornerSize(CornerSizeKey.SMALL) == null ? 0.0f : r3.intValue())).into(view.getPrimaryImageView().getImage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadArtwork$lambda-5$lambda-4$lambda-3, reason: not valid java name */
    public static final void m2314loadArtwork$lambda5$lambda4$lambda3(FeaturedToggleViewModel model, FeaturedToggleView view) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        Picasso.get().load(model.getSecondaryImage().getUrl()).placeholder(model.getSecondaryImage().getPlaceholderResId()).fit().transform(new CircleTransformation()).into(view.getSecondaryImageView().getImage());
    }

    private final void configureIcons(FeaturedToggleView view, FeaturedToggleViewModel.ToggleType toggleType) {
        int i = WhenMappings.$EnumSwitchMapping$0[toggleType.ordinal()];
        if (i == 1) {
            view.configureIcons(R$drawable.ic_other_commentaryon, R$drawable.ic_other_commentaryoff);
        } else {
            if (i != 2) {
                return;
            }
            int i2 = R$drawable.ic_star_wrapped;
            view.configureIcons(i2, i2);
        }
    }

    private final void reportContentView(FeaturedToggleViewModel model) {
        String identifier = model.getToggleType().getIdentifier();
        HashMap<String, String> supplementaryContentInfo = model.getSupplementaryContentInfo();
        supplementaryContentInfo.put("contentName", identifier);
        this.contentViewedListener.onItemRendered(identifier, supplementaryContentInfo);
    }
}
