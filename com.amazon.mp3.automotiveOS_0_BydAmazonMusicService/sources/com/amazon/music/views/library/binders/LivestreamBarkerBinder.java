package com.amazon.music.views.library.binders;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.LivestreamBarkerModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.LivestreamBarkerView;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: LivestreamBarkerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 !2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001!B'\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0005\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\""}, d2 = {"Lcom/amazon/music/views/library/binders/LivestreamBarkerBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/LivestreamBarkerView;", "Lcom/amazon/music/views/library/models/LivestreamBarkerModel;", "Landroid/content/Context;", "context", "createView", "view", "model", "", "bind", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/imageloader/ImageLoader;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "", "fullScreen", "Z", "Landroid/content/Context;", "", "numItemsAccessible", "I", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;Lcom/amazon/music/views/library/click/ClickListenerFactory;Z)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class LivestreamBarkerBinder implements UniversalBinder<LivestreamBarkerView, LivestreamBarkerModel> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ClickListenerFactory clickListenerFactory;
    private Context context;
    private final boolean fullScreen;
    private final ImageLoader imageLoader;
    private int numItemsAccessible;
    private final StyleSheet styleSheet;

    @JvmStatic
    public static final void bindWithView(Context context, StyleSheet styleSheet, ImageLoader imageLoader, ClickListenerFactory clickListenerFactory, boolean z, LivestreamBarkerView livestreamBarkerView, LivestreamBarkerModel livestreamBarkerModel) {
        INSTANCE.bindWithView(context, styleSheet, imageLoader, clickListenerFactory, z, livestreamBarkerView, livestreamBarkerModel);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<LivestreamBarkerModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(LivestreamBarkerModel.class);
    }

    public LivestreamBarkerBinder(StyleSheet styleSheet, ImageLoader imageLoader, ClickListenerFactory clickListenerFactory, boolean z) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
        this.clickListenerFactory = clickListenerFactory;
        this.fullScreen = z;
        this.numItemsAccessible = 1;
    }

    /* JADX INFO: compiled from: LivestreamBarkerBinder.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J \u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J8\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J(\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0015H\u0002J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/views/library/binders/LivestreamBarkerBinder$Companion;", "", "()V", "bindWithView", "", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "fullScreen", "", "view", "Lcom/amazon/music/views/library/views/LivestreamBarkerView;", "model", "Lcom/amazon/music/views/library/models/LivestreamBarkerModel;", "loadArtistImage", "artistImageUrl", "", "artistImage", "Landroid/widget/ImageView;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "loadBackgroundImage", "use16x9Background", "loadSourceLogoImage", "sourceLogo", ImagesContract.URL, "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void bindWithView(Context context, StyleSheet styleSheet, ImageLoader imageLoader, ClickListenerFactory clickListenerFactory, boolean fullScreen, LivestreamBarkerView view, LivestreamBarkerModel model) {
            int i;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
            Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(model, "model");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams == null) {
                marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
            } else {
                marginLayoutParams.width = -1;
                marginLayoutParams.height = -2;
            }
            Object systemService = context.getSystemService("window");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
            TypedValue typedValue = new TypedValue();
            context.getResources().getValue(R$dimen.carousel_item_fill_percent, typedValue, true);
            float f = typedValue.getFloat();
            if (fullScreen) {
                f = 1.0f;
            }
            GridSize gridSize = styleSheet.getGridSize(context);
            int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
            int columnSpace = (int) (((displayMetrics.widthPixels - (marginSpace * 2)) - ((styleSheet.getGridSize(context) == null ? 0 : r3.getColumnSpace()) * 2)) * f);
            float fraction = context.getResources().getFraction(R$fraction.feature_barker_aspect_ratio, 1, 1);
            marginLayoutParams.width = columnSpace;
            view.getBackgroundImageView().getLayoutParams().height = (int) (columnSpace / fraction);
            boolean zUse16x9Background = use16x9Background(context);
            view.setEnabled(true);
            view.setClickable(false);
            view.setOnClickListener(null);
            view.setLayoutParams(marginLayoutParams);
            if (model.getMetadata() != null) {
                view.setClickable(true);
                view.setOnClickListener(clickListenerFactory.create(model.getMetadata()));
            }
            loadBackgroundImage(context, styleSheet, imageLoader, view, model, zUse16x9Background);
            ImageBinder imageBinder = new ImageBinder(context, styleSheet, imageLoader);
            ImageView artistArtworkImageView = view.getArtistArtworkImageView();
            if (model.getArtistMetadata() != null) {
                ScreenSizeKey screenSizeKey = styleSheet.getScreenSizeKey(displayMetrics.widthPixels);
                Resources resources = context.getResources();
                if (screenSizeKey.compareTo(ScreenSizeKey.MEDIUM) > 0) {
                    i = R$dimen.artwork_default_dimen;
                } else {
                    i = R$dimen.spacer_40;
                }
                view.setArtistArtworkMargins((int) resources.getDimension(i));
                String imageUrl = model.getArtistMetadata().getImageUrl();
                if (imageUrl != null) {
                    LivestreamBarkerBinder.INSTANCE.loadArtistImage(imageUrl, artistArtworkImageView, imageBinder);
                }
                artistArtworkImageView.setVisibility(0);
                artistArtworkImageView.setOnClickListener(clickListenerFactory.create(model.getArtistMetadata()));
            } else {
                artistArtworkImageView.setVisibility(8);
            }
            ImageView sourceLogoImageView = view.getSourceLogoImageView();
            if (model.getSourceLogoUrl() != null) {
                sourceLogoImageView.setVisibility(0);
                loadSourceLogoImage(styleSheet, imageLoader, sourceLogoImageView, model.getSourceLogoUrl());
                view.setSourceLogoMargins();
            } else {
                sourceLogoImageView.setVisibility(8);
            }
            String viewerCount = model.getViewerCount();
            if (viewerCount != null) {
                view.setViewerCount(viewerCount);
            }
            if (model.getPrimaryTitle() != null) {
                view.setPrimaryTitle(model.getPrimaryTitle());
            } else {
                view.setPrimaryTitle("");
            }
            if (model.getSecondaryTitle() != null) {
                view.setSecondaryTitle(model.getSecondaryTitle());
            } else {
                view.setSecondaryTitle("");
            }
            if (model.getTertiaryTitle() != null) {
                view.setTertiaryTitle(model.getTertiaryTitle());
            } else {
                view.setTertiaryTitle("");
            }
            view.setLiveContentGradient(columnSpace);
        }

        private final void loadArtistImage(String artistImageUrl, ImageView artistImage, ImageBinder imageBinder) {
            ImageBinder.bind$default(imageBinder, artistImage, artistImageUrl, 4, 0, 8, null);
        }

        private final void loadSourceLogoImage(StyleSheet styleSheet, ImageLoader imageLoader, ImageView sourceLogo, String url) {
            imageLoader.loadImage(url, new LivestreamBarkerBinder$Companion$loadSourceLogoImage$callback$1(sourceLogo, styleSheet.getSpacerInPixels(SpacerKey.SMALL) == null ? 0.0f : r2.intValue()));
        }

        private final boolean use16x9Background(Context context) {
            return context.getResources().getFraction(R$fraction.feature_barker_aspect_ratio, 1, 1) < 2.8888888f;
        }

        private final void loadBackgroundImage(Context context, StyleSheet styleSheet, ImageLoader imageLoader, LivestreamBarkerView view, LivestreamBarkerModel model, boolean use16x9Background) {
            int i = view.getLayoutParams().width;
            String imageUrl = model.getImageUrl();
            Picasso.get().invalidate(imageUrl);
            ImageSize imageSize = styleSheet.getImageSize(i);
            if (imageSize != null) {
                view.getBackgroundImageView().setImageSize(imageSize);
            }
            view.getBackgroundImageView().setScalingFactor(context.getResources().getFraction(R$fraction.feature_barker_aspect_ratio, 1, 1));
            imageLoader.loadImage(imageUrl, new LivestreamBarkerBinder$Companion$loadBackgroundImage$callback$1(view, context, styleSheet));
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public LivestreamBarkerView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        return new LivestreamBarkerView(context, this.styleSheet, null, 0, 0, 28, null);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(LivestreamBarkerView view, LivestreamBarkerModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Companion companion = INSTANCE;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        companion.bindWithView(context, this.styleSheet, this.imageLoader, this.clickListenerFactory, this.fullScreen, view, model);
    }
}
