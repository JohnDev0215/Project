package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.FeaturedCardElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.transformations.GradientTransformation;
import com.amazon.music.skyfire.ui.transformations.OverlayTransformation;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.views.FeaturedCardView;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedCardBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/FeaturedCardBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/FeaturedCardElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/FeaturedCardView;", "()V", "bind", "", "element", "view", "bindImage", ImagesContract.URL, "", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedCardBinder extends ElementBinder<FeaturedCardElement, FeaturedCardView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public FeaturedCardView createView(ViewGroup parent) {
        return new FeaturedCardView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(final FeaturedCardElement element, FeaturedCardView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.getBarkerView().setContentDescription(element.imageDescription());
        String strImage = element.image();
        Intrinsics.checkNotNullExpressionValue(strImage, "element.image()");
        bindImage(view, strImage);
        Intrinsics.checkNotNullExpressionValue(element.onItemSelected(), "element.onItemSelected()");
        view.setClickable(!r0.isEmpty());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.FeaturedCardBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FeaturedCardBinder.m2216bind$lambda1$lambda0(this.f$0, element, view2);
            }
        });
        ElementBindingProvider bindingProvider = getBindingProvider();
        HorizontalItemElement horizontalItemElementItem = element.item();
        Intrinsics.checkNotNullExpressionValue(horizontalItemElementItem, "element.item()");
        bindingProvider.bind(horizontalItemElementItem, view.getHorizontalTile());
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2216bind$lambda1$lambda0(FeaturedCardBinder this$0, FeaturedCardElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        List<Method> listOnItemSelected = element.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "element.onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
    }

    private final void bindImage(final FeaturedCardView view, String url) {
        Target target = new Target() { // from class: com.amazon.music.skyfire.ui.widgets.binders.FeaturedCardBinder$bindImage$target$1
            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception error, Drawable drawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                view.setImage(bitmap);
                view.setVisibility(0);
                view.setTag(null);
            }
        };
        view.setTag(target);
        view.setVisibility(8);
        Picasso.get().load(url).transform(new OverlayTransformation(ContextCompat.getColor(getContext(), R.color.secondary_glass_4))).transform(new GradientTransformation(ContextCompat.getColor(getContext(), R.color.secondary_glass_3), ContextCompat.getColor(getContext(), R.color.secondary), getStyleSheet().getWidthScreenSizeKey(getContext()).compareTo(ScreenSizeKey.LARGE) >= 0 ? 3 : 0)).into(target);
    }
}
