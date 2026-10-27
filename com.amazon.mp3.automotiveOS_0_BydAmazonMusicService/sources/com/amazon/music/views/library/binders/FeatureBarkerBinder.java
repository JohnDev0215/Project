package com.amazon.music.views.library.binders;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.ktx.ViewKt;
import com.amazon.music.views.library.models.FeatureBarkerModel;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.FeatureBarkerView;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.views.BaseImage;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: FeatureBarkerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B7\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\u0010\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J \u0010 \u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\rH\u0002J\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010#\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010$\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u0019J\b\u0010!\u001a\u00020\rH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/music/views/library/binders/FeatureBarkerBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/FeatureBarkerView;", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "fullScreen", "", "hideBackgroundImageShadow", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;ZZ)V", "containerBlockRef", "", "context", "Landroid/content/Context;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "numItemsAccessible", "", "position", "bind", "", "view", "model", "createView", "loadBackgroundImage", "use16x9Background", "setContainerBlockRef", "setNumItemsAccessible", "setPosition", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeatureBarkerBinder implements UniversalBinder<FeatureBarkerView, FeatureBarkerModel> {
    private final ArtworkFrameBinder artworkFrameBinder;
    private final BrushUriClickListenerProvider brushUriClickListenerProvider;
    private String containerBlockRef;
    private Context context;
    private final boolean fullScreen;
    private final boolean hideBackgroundImageShadow;
    private final ImageLoader imageLoader;
    private int numItemsAccessible;
    private int position;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<FeatureBarkerModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(FeatureBarkerModel.class);
    }

    public FeatureBarkerBinder(StyleSheet styleSheet, ImageLoader imageLoader, ArtworkFrameBinder artworkFrameBinder, BrushUriClickListenerProvider brushUriClickListenerProvider, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        Intrinsics.checkNotNullParameter(artworkFrameBinder, "artworkFrameBinder");
        Intrinsics.checkNotNullParameter(brushUriClickListenerProvider, "brushUriClickListenerProvider");
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
        this.artworkFrameBinder = artworkFrameBinder;
        this.brushUriClickListenerProvider = brushUriClickListenerProvider;
        this.fullScreen = z;
        this.hideBackgroundImageShadow = z2;
        this.numItemsAccessible = 1;
    }

    public /* synthetic */ FeatureBarkerBinder(StyleSheet styleSheet, ImageLoader imageLoader, ArtworkFrameBinder artworkFrameBinder, BrushUriClickListenerProvider brushUriClickListenerProvider, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, imageLoader, artworkFrameBinder, brushUriClickListenerProvider, z, (i & 32) != 0 ? false : z2);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public FeatureBarkerView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        return new FeatureBarkerView(context, this.styleSheet, null, 0, 0, 28, null);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(FeatureBarkerView view, final FeatureBarkerModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        } else {
            marginLayoutParams.width = -1;
            marginLayoutParams.height = -2;
        }
        Context context = this.context;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(new DisplayMetrics());
        TypedValue typedValue = new TypedValue();
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context3 = null;
        }
        context3.getResources().getValue(R$dimen.carousel_item_fill_percent, typedValue, true);
        float f = typedValue.getFloat();
        if (this.fullScreen) {
            f = 1.0f;
        }
        StyleSheet styleSheet = this.styleSheet;
        Context context4 = this.context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context4 = null;
        }
        GridSize gridSize = styleSheet.getGridSize(context4);
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        StyleSheet styleSheet2 = this.styleSheet;
        Context context5 = this.context;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context5 = null;
        }
        int columnSpace = (int) (((r4.widthPixels - (marginSpace * 2)) - ((styleSheet2.getGridSize(context5) == null ? 0 : r8.getColumnSpace()) * 2)) * f);
        Context context6 = this.context;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context6 = null;
        }
        int fraction = (int) (columnSpace / context6.getResources().getFraction(R$fraction.feature_barker_aspect_ratio, 1, 1));
        marginLayoutParams.width = columnSpace;
        marginLayoutParams.height = fraction;
        boolean zUse16x9Background = use16x9Background();
        view.initViewMargins(zUse16x9Background, columnSpace, fraction);
        view.setEnabled(true);
        view.setClickable(false);
        view.setOnClickListener(null);
        view.setLayoutParams(marginLayoutParams);
        if (model.getArtworkFrameModel() != null) {
            ArtworkFrameView imageArtView = view.getImageArtView();
            ArtworkFrameBinder artworkFrameBinder = this.artworkFrameBinder;
            if (artworkFrameBinder != null) {
                artworkFrameBinder.bind(imageArtView, model.getArtworkFrameModel());
            }
            if (model.getTarget() != null) {
                imageArtView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.FeatureBarkerBinder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FeatureBarkerBinder.m2298bind$lambda2$lambda1$lambda0(this.f$0, model, view2);
                    }
                });
            }
        }
        if (model.getTarget() != null) {
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.FeatureBarkerBinder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FeatureBarkerBinder.m2299bind$lambda4$lambda3(this.f$0, model, view2);
                }
            });
        }
        loadBackgroundImage(view, model, zUse16x9Background);
        if (model.getMainText() != null) {
            view.setMainText(model.getMainText());
        } else {
            view.setMainText("");
        }
        if (model.getSubText() != null) {
            view.setSubText(model.getSubText());
        } else {
            view.setSubText("");
        }
        if (model.getAltDescription() != null) {
            view.setContentDescription(model.getAltDescription());
        }
        if (model.getInsetHorizontally()) {
            StyleSheet styleSheet3 = this.styleSheet;
            Context context7 = this.context;
            if (context7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            } else {
                context2 = context7;
            }
            ViewKt.setHorizontalMargin(view, styleSheet3.horizontalInsetWidth(context2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2298bind$lambda2$lambda1$lambda0(FeatureBarkerBinder this$0, FeatureBarkerModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        BrushUriClickListenerProvider.DefaultImpls.onClick$default(this$0.brushUriClickListenerProvider, model.getTarget(), model.getViewId(), null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-4$lambda-3, reason: not valid java name */
    public static final void m2299bind$lambda4$lambda3(FeatureBarkerBinder this$0, FeatureBarkerModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        BrushUriClickListenerProvider.DefaultImpls.onClick$default(this$0.brushUriClickListenerProvider, model.getTarget(), model.getViewId(), null, 4, null);
    }

    public final void setContainerBlockRef(String containerBlockRef) {
        Intrinsics.checkNotNullParameter(containerBlockRef, "containerBlockRef");
        this.containerBlockRef = containerBlockRef;
    }

    public final void setPosition(int position) {
        this.position = position;
    }

    public final void setNumItemsAccessible(int numItemsAccessible) {
        this.numItemsAccessible = numItemsAccessible;
    }

    private final boolean use16x9Background() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return context.getResources().getFraction(R$fraction.feature_barker_aspect_ratio, 1, 1) < 2.8888888f;
    }

    private final void loadBackgroundImage(FeatureBarkerView view, FeatureBarkerModel model, boolean use16x9Background) {
        String backgroundImageUrl4x1;
        View viewFindViewById;
        int i = view.getLayoutParams().width;
        boolean z = model.getMainText() != null;
        Context context = null;
        if (use16x9Background && model.getBackgroundImageUrl16x9() != null) {
            backgroundImageUrl4x1 = model.getBackgroundImageUrl16x9();
        } else {
            backgroundImageUrl4x1 = model.getBackgroundImageUrl4x1() != null ? model.getBackgroundImageUrl4x1() : null;
        }
        if (backgroundImageUrl4x1 == null) {
            return;
        }
        ImageSize imageSize = this.styleSheet.getImageSize(i);
        if (imageSize != null) {
            view.getBackgroundImageView().setImageSize(imageSize);
        }
        BaseImage backgroundImageView = view.getBackgroundImageView();
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context = context2;
        }
        backgroundImageView.setScalingFactor(context.getResources().getFraction(R$fraction.feature_barker_aspect_ratio, 1, 1));
        Integer cornerSize = this.styleSheet.getCornerSize(CornerSizeKey.MEDIUM);
        if (cornerSize != null) {
            view.getBackgroundImageView().applyCornerRadius(cornerSize.intValue());
        }
        if (this.hideBackgroundImageShadow && (viewFindViewById = view.getBackgroundImageView().findViewById(R$id.image_shadow)) != null) {
            viewFindViewById.setVisibility(8);
        }
        this.imageLoader.loadImage(backgroundImageUrl4x1, new FeatureBarkerBinder$loadBackgroundImage$callback$1(view, z, this, use16x9Background));
    }
}
