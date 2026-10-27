package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.models.CompactHeaderModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.views.CompactHeaderView;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.views.BaseImage;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CompactHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000b\u0010\u0019R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Lcom/amazon/music/views/library/binders/CompactHeaderBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/CompactHeaderView;", "Lcom/amazon/music/views/library/models/CompactHeaderModel;", "Lcom/amazon/ui/foundations/views/BaseImage;", ContextMappingConstants.IMAGE, "", ImagesContract.URL, "", "loadImage", "Landroid/content/Context;", "context", "createView", "view", "model", "bind", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/imageloader/ImageLoader;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "", "imageRotation", "I", "Landroid/content/Context;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CompactHeaderBinder implements UniversalBinder<CompactHeaderView, CompactHeaderModel> {
    private Context context;
    private final ImageLoader imageLoader;
    private final int imageRotation;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<CompactHeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(CompactHeaderModel.class);
    }

    public CompactHeaderBinder(StyleSheet styleSheet, ImageLoader imageLoader) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public CompactHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        return new CompactHeaderView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(CompactHeaderView view, CompactHeaderModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setPrimaryText(model.getPrimaryText());
        view.setLabel(model.getLabel());
        view.initViewMargins();
        loadImage(view.getImage(), model.getImageUrl());
    }

    private final void loadImage(BaseImage image, String url) {
        Context context = this.context;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        int dimension = (int) context.getResources().getDimension(R$dimen.compact_header_image_radius);
        ImageStyle imageStyle = this.styleSheet.getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            image.setImageStyle(imageStyle);
        }
        ImageSize imageSize = this.styleSheet.getImageSize(dimension);
        if (imageSize != null) {
            image.setImageSize(imageSize);
        }
        Integer cornerSize = this.styleSheet.getCornerSize(CornerSizeKey.ROUND);
        if (cornerSize != null) {
            image.applyCornerRadius(cornerSize.intValue());
        }
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        new ImageBinder(context2, this.styleSheet, this.imageLoader).bind(url, image.getImage(), R$drawable.default_profile, ImageLoader.TransformationType.Circle, this.imageRotation);
    }
}
