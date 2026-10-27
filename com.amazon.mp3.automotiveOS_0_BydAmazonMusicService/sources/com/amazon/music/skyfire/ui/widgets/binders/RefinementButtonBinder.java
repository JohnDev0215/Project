package com.amazon.music.skyfire.ui.widgets.binders;

import Touch.WidgetsInterface.v1_0.RefinementButtonElement;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.views.library.views.RefinementPillView;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementButtonBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/RefinementButtonBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/RefinementButtonElement;", "Lcom/amazon/music/views/library/views/RefinementPillView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementButtonBinder extends ElementBinder<RefinementButtonElement, RefinementPillView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public RefinementPillView createView(ViewGroup parent) {
        return new RefinementPillView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(final RefinementButtonElement element, RefinementPillView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        final BaseButton button = view.getButton();
        Boolean boolIsActive = element.isActive();
        Intrinsics.checkNotNullExpressionValue(boolIsActive, "element.isActive");
        button.setActive(boolIsActive.booleanValue());
        String strText = element.text();
        Intrinsics.checkNotNullExpressionValue(strText, "element.text()");
        button.setText(strText);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.RefinementButtonBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RefinementButtonBinder.m2239bind$lambda1$lambda0(button, element, this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX INFO: renamed from: bind$lambda-1$lambda-0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2239bind$lambda1$lambda0(com.amazon.ui.foundations.views.BaseButton r0, Touch.WidgetsInterface.v1_0.RefinementButtonElement r1, com.amazon.music.skyfire.ui.widgets.binders.RefinementButtonBinder r2, android.view.View r3) {
        /*
            java.lang.String r3 = "$this_with"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = "$element"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            boolean r0 = r0.getIsActive()
            if (r0 == 0) goto L36
            java.util.List r0 = r1.onItemDeselected()
            if (r0 == 0) goto L25
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L23
            goto L25
        L23:
            r0 = 0
            goto L26
        L25:
            r0 = 1
        L26:
            if (r0 != 0) goto L36
            java.util.List r0 = r1.onItemDeselected()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.String r1 = "{\n                      …)!!\n                    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            goto L40
        L36:
            java.util.List r0 = r1.onItemSelected()
            java.lang.String r1 = "{\n                      …d()\n                    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
        L40:
            com.amazon.music.skyfire.ui.skyfire.DispatcherContext r1 = r2.getDispatcherContext()
            if (r1 != 0) goto L47
            goto L4c
        L47:
            r2 = 2
            r3 = 0
            com.amazon.music.skyfire.ui.ktx.SkyFireKt.dispatch$default(r1, r0, r3, r2, r3)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.skyfire.ui.widgets.binders.RefinementButtonBinder.m2239bind$lambda1$lambda0(com.amazon.ui.foundations.views.BaseButton, Touch.WidgetsInterface.v1_0.RefinementButtonElement, com.amazon.music.skyfire.ui.widgets.binders.RefinementButtonBinder, android.view.View):void");
    }
}
