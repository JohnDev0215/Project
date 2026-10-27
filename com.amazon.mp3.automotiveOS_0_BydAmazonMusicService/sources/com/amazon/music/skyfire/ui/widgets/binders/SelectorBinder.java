package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.SelectorElement;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.widgets.views.SelectorView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectorBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/SelectorBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/SelectorElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/SelectorView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SelectorBinder extends ElementBinder<SelectorElement, SelectorView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public SelectorView createView(ViewGroup parent) {
        return new SelectorView(getContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0045  */
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bind(final Touch.WidgetsInterface.v1_0.SelectorElement r4, com.amazon.music.skyfire.ui.widgets.views.SelectorView r5) {
        /*
            r3 = this;
            java.lang.String r0 = "element"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.amazon.music.views.library.styles.StyleSheet r0 = r3.getStyleSheet()
            android.content.Context r1 = r5.getContext()
            java.lang.String r2 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.amazon.ui.foundations.styles.GridSize r0 = r0.getGridSize(r1)
            if (r0 != 0) goto L1f
            goto L5a
        L1f:
            com.amazon.music.skyfire.ui.utils.ScreenUtils$Companion r0 = com.amazon.music.skyfire.ui.utils.ScreenUtils.INSTANCE
            android.content.Context r1 = r5.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r0 = r0.isLandscape(r1)
            if (r0 != 0) goto L45
            com.amazon.music.views.library.styles.StyleSheet r0 = r3.getStyleSheet()
            android.content.Context r1 = r5.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.amazon.music.views.library.styles.keys.ScreenSizeKey r0 = r0.getWidthScreenSizeKey(r1)
            com.amazon.music.views.library.styles.keys.ScreenSizeKey r1 = com.amazon.music.views.library.styles.keys.ScreenSizeKey.LARGE
            int r0 = r0.compareTo(r1)
            if (r0 < 0) goto L5a
        L45:
            android.content.Context r0 = r5.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.amazon.music.skyfire.ui.R.dimen.selector_view_max_width
            float r0 = r0.getDimension(r1)
            int r0 = kotlin.math.MathKt.roundToInt(r0)
            r5.setMaxWidth(r0)
        L5a:
            android.widget.TextView r0 = r5.getText()
            com.amazon.music.views.library.styles.StyleSheet r1 = r3.getStyleSheet()
            com.amazon.music.views.library.styles.keys.FontStyleKey r2 = com.amazon.music.views.library.styles.keys.FontStyleKey.PRIMARY
            com.amazon.music.skyfire.ui.ktx.ViewKt.applyFontStyle(r0, r1, r2)
            android.widget.TextView r0 = r5.getText()
            java.lang.String r1 = r4.primaryText()
            r0.setText(r1)
            com.amazon.music.skyfire.ui.widgets.binders.SelectorBinder$$ExternalSyntheticLambda0 r0 = new com.amazon.music.skyfire.ui.widgets.binders.SelectorBinder$$ExternalSyntheticLambda0
            r0.<init>()
            r5.setOnClickListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.skyfire.ui.widgets.binders.SelectorBinder.bind(Touch.WidgetsInterface.v1_0.SelectorElement, com.amazon.music.skyfire.ui.widgets.views.SelectorView):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2240bind$lambda2$lambda1(SelectorBinder this$0, SelectorElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnItemSelected = element.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "element.onItemSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnItemSelected);
    }
}
