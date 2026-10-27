package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.providers.CachedArtworkProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.utils.MediaUtil;
import com.amazon.music.views.library.views.ArtworkFrameView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ArtworkFrameBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "cachedArtworkProvider", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/ImageBinder;Lcom/amazon/music/views/library/providers/CachedArtworkProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "handleStateChange", "payload", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ArtworkFrameBinder implements BaseStateChangeBinder<ArtworkFrameView, ArtworkFrameModel> {
    private final CachedArtworkProvider cachedArtworkProvider;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final Context context;
    private final ImageBinder imageBinder;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ArtworkFrameModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ArtworkFrameModel.class);
    }

    public ArtworkFrameBinder(Context context, StyleSheet styleSheet, ImageBinder imageBinder, CachedArtworkProvider cachedArtworkProvider, ContentEnabilityProvider contentEnabilityProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageBinder, "imageBinder");
        this.context = context;
        this.styleSheet = styleSheet;
        this.imageBinder = imageBinder;
        this.cachedArtworkProvider = cachedArtworkProvider;
        this.contentEnabilityProvider = contentEnabilityProvider;
    }

    public /* synthetic */ ArtworkFrameBinder(Context context, StyleSheet styleSheet, ImageBinder imageBinder, CachedArtworkProvider cachedArtworkProvider, ContentEnabilityProvider contentEnabilityProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, imageBinder, (i & 8) != 0 ? null : cachedArtworkProvider, (i & 16) != 0 ? null : contentEnabilityProvider);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public ArtworkFrameView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ArtworkFrameView(context, null, 0, 6, null);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(ArtworkFrameView view, ArtworkFrameModel model, Object payload) {
        Unit unit;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 1)) {
            Integer contentType = model.getContentType();
            if (contentType == null) {
                unit = null;
            } else {
                if (MediaUtil.INSTANCE.isPodcastEntity(contentType.intValue())) {
                    view.setPodcastPlayState(model.getPlayState());
                } else {
                    view.setPlayState(model.getPlayState());
                }
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                view.setPlayState(model.getPlayState());
            }
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final ArtworkFrameView view, final ArtworkFrameModel model) {
        Unit unit;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.initStyle(this.styleSheet, model.getPlayIconStyleKey());
        ContentMetadata metadata = model.getMetadata();
        boolean z = false;
        if (metadata != null) {
            ContentEnabilityProvider contentEnabilityProvider = this.contentEnabilityProvider;
            if (contentEnabilityProvider == null ? true : contentEnabilityProvider.shouldEnableContent(metadata)) {
                z = true;
            }
        }
        if (z && model.getImageUrl() == null && this.cachedArtworkProvider != null && model.getMetadata() != null) {
            view.getArtworkImageView().getImage().post(new Runnable() { // from class: com.amazon.music.views.library.binders.ArtworkFrameBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ArtworkFrameBinder.m2264bind$lambda3(this.f$0, view, model);
                }
            });
        } else if (model.getImageUrl() != null) {
            ImageBinder.bind$default(this.imageBinder, view.getArtworkImageView().getImage(), model.getImageUrl(), model.getContentType(), 0, 8, null);
        } else if (model.getImageDrawable() != null) {
            view.setArtworkImageDrawable(model.getImageDrawable());
        } else if (model.getResourceId() != null) {
            view.setArtworkImageResource(model.getResourceId().intValue(), true);
        }
        if (model.getSashImageResource() != null) {
            view.setSash(model.getSashImageResource().intValue());
        }
        view.setPlayIcon(model.getPlayIconType());
        Integer contentType = model.getContentType();
        if (contentType == null) {
            unit = null;
        } else {
            if (MediaUtil.INSTANCE.isPodcastEntity(contentType.intValue())) {
                view.setPodcastPlayState(model.getPlayState());
            } else {
                view.setPlayState(model.getPlayState());
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            view.setPlayState(model.getPlayState());
        }
        view.getArtworkImageView().setContentDescription(model.getImageDescription());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-3, reason: not valid java name */
    public static final void m2264bind$lambda3(ArtworkFrameBinder this$0, ArtworkFrameView view, ArtworkFrameModel model) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.cachedArtworkProvider.loadCachedArtwork(view.getArtworkImageView().getImage(), model.getMetadata());
    }
}
