package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.views.PillCellView;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PillNavigatorItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/PillNavigatorItemBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ItemElementBinder;", "LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetItemElement;", "Lcom/amazon/music/views/library/views/PillCellView;", "()V", "bind", "", "element", "view", "width", "", "createView", "parent", "Landroid/view/ViewGroup;", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PillNavigatorItemBinder extends ItemElementBinder<PillNavigatorWidgetItemElement, PillCellView> {
    private static final Logger logger;

    static {
        Logger logger2 = LoggerFactory.getLogger(PillNavigatorItemBinder.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(PillNavigatorI…r::class.java.simpleName)");
        logger = logger2;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder
    public void bind(final PillNavigatorWidgetItemElement element, PillCellView view, int width) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        String strPrimaryText = element.primaryText();
        Intrinsics.checkNotNullExpressionValue(strPrimaryText, "element.primaryText()");
        view.setText(strPrimaryText);
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.PillNavigatorItemBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PillNavigatorItemBinder.m2234bind$lambda2$lambda0(this.f$0, element, view2);
            }
        });
        String strImage = element.image();
        if (strImage == null) {
            return;
        }
        PillNavigatorItemBinder$bind$1$2$target$1 pillNavigatorItemBinder$bind$1$2$target$1 = new PillNavigatorItemBinder$bind$1$2$target$1(view, view);
        view.setTag(pillNavigatorItemBinder$bind$1$2$target$1);
        Picasso.get().load(strImage).into(pillNavigatorItemBinder$bind$1$2$target$1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-0, reason: not valid java name */
    public static final void m2234bind$lambda2$lambda0(PillNavigatorItemBinder this$0, PillNavigatorWidgetItemElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnItemSelected = element.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "element.onItemSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnItemSelected);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public PillCellView createView(ViewGroup parent) {
        return new PillCellView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(PillNavigatorWidgetItemElement element, PillCellView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        bind(element, view, -1);
    }
}
