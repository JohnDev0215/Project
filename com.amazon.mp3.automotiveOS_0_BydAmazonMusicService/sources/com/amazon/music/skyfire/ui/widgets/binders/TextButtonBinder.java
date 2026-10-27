package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: TextButtonBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0002J\u0012\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/TextButtonBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/TextButtonElement;", "Lcom/amazon/ui/foundations/views/BaseButton;", "()V", "buttonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "getButtonSizeKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "setButtonSizeKey", "(Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;)V", "buttonStyleKey", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "getButtonStyleKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "setButtonStyleKey", "(Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;)V", "bind", "", "element", "view", "checkForIcon", "checkForStyle", "createView", "parent", "Landroid/view/ViewGroup;", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextButtonBinder extends ElementBinder<TextButtonElement, BaseButton> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ButtonSizeKey buttonSizeKey = ButtonSizeKey.MEDIUM;
    private ButtonStyleKey buttonStyleKey = ButtonStyleKey.SOLID;

    /* JADX INFO: compiled from: TextButtonBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextButtonElement.Style.values().length];
            iArr[TextButtonElement.Style.GLASS.ordinal()] = 1;
            iArr[TextButtonElement.Style.SOLID.ordinal()] = 2;
            iArr[TextButtonElement.Style.OUTLINE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final void styleTextButtonAsWidget(BaseButton baseButton, StyleSheet styleSheet) {
        INSTANCE.styleTextButtonAsWidget(baseButton, styleSheet);
    }

    public final ButtonSizeKey getButtonSizeKey() {
        return this.buttonSizeKey;
    }

    public final void setButtonSizeKey(ButtonSizeKey buttonSizeKey) {
        Intrinsics.checkNotNullParameter(buttonSizeKey, "<set-?>");
        this.buttonSizeKey = buttonSizeKey;
    }

    public final ButtonStyleKey getButtonStyleKey() {
        return this.buttonStyleKey;
    }

    public final void setButtonStyleKey(ButtonStyleKey buttonStyleKey) {
        Intrinsics.checkNotNullParameter(buttonStyleKey, "<set-?>");
        this.buttonStyleKey = buttonStyleKey;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public BaseButton createView(ViewGroup parent) {
        return new BaseButton(getContext(), null, 0, 6, null);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(final TextButtonElement element, BaseButton view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        checkForIcon(element);
        checkForStyle(element);
        BaseButton.StyleBuilder buttonBuilder = getStyleSheet().getButtonBuilder(getButtonSizeKey(), getButtonStyleKey());
        if (buttonBuilder != null) {
            String strText = element.text();
            Intrinsics.checkNotNullExpressionValue(strText, "element.text()");
            buttonBuilder.withText(strText);
        }
        String strActiveText = element.activeText();
        if (strActiveText != null && buttonBuilder != null) {
            buttonBuilder.withActiveText(strActiveText);
        }
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(view);
        }
        view.setTag(element.widgetId());
        view.getButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.TextButtonBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TextButtonBinder.m2241bind$lambda5$lambda1(this.f$0, element, view2);
            }
        });
        Integer num = Constants.INSTANCE.getIdMap().get(element.resourceId());
        if (num != null) {
            view.setId(num.intValue());
        }
        ImageResources imageResourcesIconResources = element.iconResources();
        if (imageResourcesIconResources != null) {
            ViewKt.setIcon(view, imageResourcesIconResources.image());
            ViewKt.setActiveIcon(view, imageResourcesIconResources.activeImage());
        }
        String strIcon = element.icon();
        if (strIcon != null) {
            TextButtonBinder$bind$1$5$target$1 textButtonBinder$bind$1$5$target$1 = new TextButtonBinder$bind$1$5$target$1(view);
            view.getButton().setTag(textButtonBinder$bind$1$5$target$1);
            Picasso.get().load(strIcon).into(textButtonBinder$bind$1$5$target$1);
        }
        view.setActive(element.shouldToggle() ? element.isActive() : false);
        view.setVisibility(0);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-5$lambda-1, reason: not valid java name */
    public static final void m2241bind$lambda5$lambda1(TextButtonBinder this$0, TextButtonElement element, View view) {
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

    private final void checkForIcon(TextButtonElement element) {
        if (element.icon() == null && element.iconResources() == null) {
            return;
        }
        ButtonSizeKey buttonSizeKey = this.buttonSizeKey;
        if (buttonSizeKey == ButtonSizeKey.MEDIUM) {
            this.buttonSizeKey = ButtonSizeKey.MEDIUM_WITH_ICON;
        } else if (buttonSizeKey == ButtonSizeKey.LARGE) {
            this.buttonSizeKey = ButtonSizeKey.LARGE_WITH_ICON;
        }
    }

    private final void checkForStyle(TextButtonElement element) {
        ButtonStyleKey buttonStyleKey;
        if (element.style() != null) {
            TextButtonElement.Style style = element.style();
            int i = style == null ? -1 : WhenMappings.$EnumSwitchMapping$0[style.ordinal()];
            if (i == 1) {
                buttonStyleKey = ButtonStyleKey.GLASS;
            } else if (i != 2 && i == 3) {
                buttonStyleKey = ButtonStyleKey.OUTLINE;
            } else {
                buttonStyleKey = ButtonStyleKey.SOLID;
            }
            this.buttonStyleKey = buttonStyleKey;
        }
    }

    /* JADX INFO: compiled from: TextButtonBinder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/TextButtonBinder$Companion;", "", "()V", "styleTextButtonAsWidget", "", "view", "Lcom/amazon/ui/foundations/views/BaseButton;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void styleTextButtonAsWidget(BaseButton view, StyleSheet styleSheet) {
            Intrinsics.checkNotNullParameter(view, "view");
            if (styleSheet == null) {
                return;
            }
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            GridSize gridSize = styleSheet.getGridSize(context);
            if (gridSize == null) {
                return;
            }
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            view.setPadding(marginSpace, view.getPaddingTop(), marginSpace, view.getPaddingBottom());
            ScreenUtils.Companion companion = ScreenUtils.INSTANCE;
            Context context2 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            if (!companion.isLandscape(context2)) {
                Context context3 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context3, "context");
                if (styleSheet.getWidthScreenSizeKey(context3).compareTo(ScreenSizeKey.LARGE) < 0) {
                    return;
                }
            }
            view.setMaxWidth(MathKt__MathJVMKt.roundToInt(view.getContext().getResources().getDimension(R.dimen.text_button_widget_max_width) + (marginSpace * 2)));
        }
    }
}
