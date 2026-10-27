package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.alps.R$dimen;
import com.amazon.music.alps.liveevent.viewmodel.SponsoredImageItemViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.UIClickWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SponsorImageItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J(\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0007R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/SponsorImageItemBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Landroid/widget/ImageView;", "Lcom/amazon/music/alps/liveevent/viewmodel/SponsoredImageItemViewModel;", "picasso", "Lcom/squareup/picasso/Picasso;", "onViewed", "Lkotlin/Function0;", "", "(Lcom/squareup/picasso/Picasso;Lkotlin/jvm/functions/Function0;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "createView", "context", "Landroid/content/Context;", "loadImage", ImagesContract.URL, "", "imageWidth", "", "imageHeight", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SponsorImageItemBinder implements UniversalBinder<ImageView, SponsoredImageItemViewModel> {
    private final Function0<Unit> onViewed;
    private final Picasso picasso;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SponsoredImageItemViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SponsoredImageItemViewModel.class);
    }

    public SponsorImageItemBinder(Picasso picasso, Function0<Unit> onViewed) {
        Intrinsics.checkNotNullParameter(picasso, "picasso");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.picasso = picasso;
        this.onViewed = onViewed;
    }

    public /* synthetic */ SponsorImageItemBinder(Picasso picasso, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(picasso, (i & 2) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.binder.SponsorImageItemBinder.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        } : function0);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public ImageView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ImageView(context);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final ImageView view, final SponsoredImageItemViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Resources resources = view.getResources();
        float dimension = resources.getDimension(R$dimen.sponsor_image_width);
        float dimension2 = resources.getDimension(R$dimen.sponsor_image_height);
        String imageUrl = model.getImageUrl();
        if (imageUrl != null) {
            loadImage(imageUrl, view, (int) dimension, (int) dimension2);
        }
        if (model.getExternalLink() != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.alps.liveevent.binder.SponsorImageItemBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SponsorImageItemBinder.m1875bind$lambda3$lambda2(model, view, view2);
                }
            });
        }
        this.onViewed.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-3$lambda-2, reason: not valid java name */
    public static final void m1875bind$lambda3$lambda2(SponsoredImageItemViewModel model, ImageView view, View view2) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof MetricsProvider)) {
            interfaceProvider = null;
        }
        MetricsProvider metricsProvider = (MetricsProvider) interfaceProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.GO_SPONSOR_IMAGE.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(PageType.LIVE_EVENT_DETAIL.name());
            PageSubType pageSubType = model.getPageSubType();
            metricsProvider.sendMetricsEvent(builderWithPageType.withPageSubType(pageSubType != null ? pageSubType.name() : null).withEntityType(EntityType.LIVE_EVENT.name()).withEntityId(model.getLiveEventId()).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).withUrl(model.getExternalLink()).build());
        }
        view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(model.getExternalLink())));
    }

    @VisibleForTesting
    public final void loadImage(String url, ImageView view, int imageWidth, int imageHeight) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(view, "view");
        this.picasso.load(url).resize(imageWidth, imageHeight).centerInside().into(view);
    }
}
