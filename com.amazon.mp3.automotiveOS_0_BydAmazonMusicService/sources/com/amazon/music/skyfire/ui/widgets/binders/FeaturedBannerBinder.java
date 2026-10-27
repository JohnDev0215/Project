package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.FeaturedBannerElement;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.views.FeaturedBannerView;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedBannerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u0006*\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/FeaturedBannerBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/FeaturedBannerElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/FeaturedBannerView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "bindImage", ImagesContract.URL, "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedBannerBinder extends ElementBinder<FeaturedBannerElement, FeaturedBannerView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public FeaturedBannerView createView(ViewGroup parent) {
        return new FeaturedBannerView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(final FeaturedBannerElement element, FeaturedBannerView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.getBarkerView().setContentDescription(element.imageDescription());
        String strImage = element.image();
        Intrinsics.checkNotNullExpressionValue(strImage, "element.image()");
        if (strImage.length() > 0) {
            String strImage2 = element.image();
            Intrinsics.checkNotNullExpressionValue(strImage2, "element.image()");
            bindImage(view, strImage2);
        }
        Intrinsics.checkNotNullExpressionValue(element.onItemSelected(), "element.onItemSelected()");
        view.setClickable(!r0.isEmpty());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.FeaturedBannerBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FeaturedBannerBinder.m2215bind$lambda1$lambda0(this.f$0, element, view2);
            }
        });
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2215bind$lambda1$lambda0(FeaturedBannerBinder this$0, FeaturedBannerElement element, View view) {
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

    private final void bindImage(final FeaturedBannerView featuredBannerView, String str) {
        Target target = new Target() { // from class: com.amazon.music.skyfire.ui.widgets.binders.FeaturedBannerBinder$bindImage$target$1
            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception error, Drawable drawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                featuredBannerView.setImage(bitmap);
                featuredBannerView.setVisibility(0);
                featuredBannerView.setTag(null);
            }
        };
        featuredBannerView.setTag(target);
        featuredBannerView.setVisibility(8);
        Picasso.get().load(str).into(target);
    }
}
