package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.PlainIconButtonElement;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlainIconButtonBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0012\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/PlainIconButtonBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/PlainIconButtonElement;", "Lcom/amazon/ui/foundations/views/BaseButton;", "()V", "iconSizeKey", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "getIconSizeKey", "()Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "setIconSizeKey", "(Lcom/amazon/music/views/library/styles/keys/IconSizeKey;)V", "iconStyleKey", "Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "getIconStyleKey", "()Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "setIconStyleKey", "(Lcom/amazon/music/views/library/styles/keys/IconStyleKey;)V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlainIconButtonBinder extends ElementBinder<PlainIconButtonElement, BaseButton> {
    private IconSizeKey iconSizeKey = IconSizeKey.MEDIUM;
    private IconStyleKey iconStyleKey = IconStyleKey.GLASS;

    public final IconSizeKey getIconSizeKey() {
        return this.iconSizeKey;
    }

    public final void setIconSizeKey(IconSizeKey iconSizeKey) {
        Intrinsics.checkNotNullParameter(iconSizeKey, "<set-?>");
        this.iconSizeKey = iconSizeKey;
    }

    public final IconStyleKey getIconStyleKey() {
        return this.iconStyleKey;
    }

    public final void setIconStyleKey(IconStyleKey iconStyleKey) {
        Intrinsics.checkNotNullParameter(iconStyleKey, "<set-?>");
        this.iconStyleKey = iconStyleKey;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public BaseButton createView(ViewGroup parent) {
        return new BaseButton(getContext(), null, 0, 6, null);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(final PlainIconButtonElement element, BaseButton view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        BaseButton.StyleBuilder iconBuilder = getStyleSheet().getIconBuilder(getIconSizeKey(), getIconStyleKey());
        if (iconBuilder != null) {
            iconBuilder.applyStyle(view);
        }
        view.setTag(element.widgetId());
        view.getButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.PlainIconButtonBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PlainIconButtonBinder.m2237bind$lambda4$lambda0(this.f$0, element, view2);
            }
        });
        ImageResources imageResourcesIconResources = element.iconResources();
        if (imageResourcesIconResources != null) {
            ViewKt.setIcon(view, imageResourcesIconResources.image());
            ViewKt.setActiveIcon(view, imageResourcesIconResources.activeImage());
        }
        view.getButton().setContentDescription(element.iconDescription());
        Integer num = Constants.INSTANCE.getIdMap().get(element.resourceId());
        if (num != null) {
            view.setId(num.intValue());
        }
        String strIcon = element.icon();
        if (strIcon != null) {
            PlainIconButtonBinder$bind$1$4$target$1 plainIconButtonBinder$bind$1$4$target$1 = new PlainIconButtonBinder$bind$1$4$target$1(view);
            view.getButton().setTag(plainIconButtonBinder$bind$1$4$target$1);
            Picasso.get().load(strIcon).into(plainIconButtonBinder$bind$1$4$target$1);
        }
        view.setActive(element.shouldToggle() ? element.isActive() : false);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-4$lambda-0, reason: not valid java name */
    public static final void m2237bind$lambda4$lambda0(PlainIconButtonBinder this$0, PlainIconButtonElement element, View view) {
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
}
