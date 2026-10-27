package com.amazon.music.explore.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.EventHeaderElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.music.explore.R;
import com.amazon.music.explore.widgets.views.EventHeaderView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.picasso.transformations.RoundedCornerTransformation;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.widgets.binders.ElementBinder;
import com.amazon.music.skyfire.ui.widgets.binders.TextButtonBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/explore/widgets/binders/EventHeaderBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/EventHeaderElement;", "Lcom/amazon/music/explore/widgets/views/EventHeaderView;", "()V", "applyStyles", "", "view", "bind", "element", "bindActionButtons", "bindPrimaryButtons", "bindText", "bindThumbnail", "bindTimer", "createView", "parent", "Landroid/view/ViewGroup;", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EventHeaderBinder extends ElementBinder<EventHeaderElement, EventHeaderView> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static SimpleDateFormat TIMEZONE_FORMATTER = new SimpleDateFormat("z", Locale.getDefault());

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public EventHeaderView createView(ViewGroup parent) {
        return new EventHeaderView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(EventHeaderElement element, EventHeaderView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.setDispatcherContext(getDispatcherContext());
        applyStyles(view);
        bindText(element, view);
        bindThumbnail(element, view);
        bindPrimaryButtons(element, view);
        bindTimer(element, view);
        bindActionButtons(element, view);
        MethodsDispatcher<Method> methodsDispatcher = getMethodsDispatcher();
        String ownerId = getOwnerId();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        methodsDispatcher.dispatchMethods(ownerId, listOnViewed);
    }

    private final void applyStyles(EventHeaderView view) {
        StyleSheet styleSheet = getStyleSheet();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize != null) {
            view.setPadding(gridSize.getMarginSpace() + gridSize.getColumnSpace(), view.getPaddingTop(), gridSize.getMarginSpace() + gridSize.getColumnSpace(), view.getPaddingBottom());
        }
        ViewKt.applyFontStyle(view.getLabel(), getStyleSheet(), FontStyleKey.LABEL);
        ViewKt.applyFontStyle(view.getHeadline(), getStyleSheet(), FontStyleKey.HEADLINE_2);
        ViewKt.applyFontStyle(view.getPrimaryText(), getStyleSheet(), FontStyleKey.PRIMARY);
        ViewKt.applyFontStyle(view.getSecondaryText(), getStyleSheet(), FontStyleKey.SECONDARY);
        TextView[] counters = view.getCounters();
        int length = counters.length;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            TextView textView = counters[i2];
            i2++;
            ViewKt.applyFontStyle(textView, getStyleSheet(), FontStyleKey.HEADLINE_4);
            Integer color = getStyleSheet().getColor(ColorKey.COLOR_PRIMARY);
            Intrinsics.checkNotNull(color);
            textView.setTextColor(color.intValue());
        }
        TextView[] labels = view.getLabels();
        int length2 = labels.length;
        while (i < length2) {
            TextView textView2 = labels[i];
            i++;
            ViewKt.applyFontStyle(textView2, getStyleSheet(), FontStyleKey.TERTIARY);
        }
    }

    private final void bindText(EventHeaderElement element, EventHeaderView view) {
        view.getLabel().setText(element.label());
        view.getHeadline().setText(element.primaryText());
        view.getPrimaryText().setText(element.secondaryText());
        TextViewKt.collapseIfEmpty(view.getPrimaryText());
        view.getSecondaryText().setText(element.tertiaryText());
        TextViewKt.collapseIfEmpty(view.getSecondaryText());
    }

    private final void bindThumbnail(EventHeaderElement element, EventHeaderView view) {
        if (element.image() != null) {
            Resources resources = view.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.event_header_thumbnail_width);
            Picasso.get().load(element.image()).transform(new RoundedCornerTransformation(getStyleSheet().getImageCornerSize((int) (dimensionPixelSize / resources.getDisplayMetrics().density)), getStyleSheet().getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_2), (int) resources.getDimension(R.dimen.live_event_thumbnail_border_width))).resize(dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.event_header_thumbnail_height)).centerCrop().into(view.getThumbnail());
        }
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getThumbnail(), element.image() == null);
    }

    private final void bindPrimaryButtons(EventHeaderElement element, EventHeaderView view) {
        ButtonElement buttonElementPrimaryButton = element.primaryButton();
        final TextButtonElement textButtonElement = buttonElementPrimaryButton instanceof TextButtonElement ? (TextButtonElement) buttonElementPrimaryButton : null;
        if (textButtonElement != null) {
            BaseButton.StyleBuilder buttonBuilder = getStyleSheet().getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
            if (buttonBuilder != null) {
                String strText = textButtonElement.text();
                Intrinsics.checkNotNullExpressionValue(strText, "button.text()");
                BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(strText);
                if (styleBuilderWithText != null) {
                    styleBuilderWithText.applyStyle(view.getPrimaryButton());
                }
            }
            view.getPrimaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.widgets.binders.EventHeaderBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    EventHeaderBinder.m2007bindPrimaryButtons$lambda5$lambda4(this.f$0, textButtonElement, view2);
                }
            });
            MethodsDispatcher<Method> methodsDispatcher = getMethodsDispatcher();
            String ownerId = getOwnerId();
            List<Method> listOnViewed = textButtonElement.onViewed();
            Intrinsics.checkNotNullExpressionValue(listOnViewed, "button.onViewed()");
            methodsDispatcher.dispatchMethods(ownerId, listOnViewed);
        }
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getPrimaryButton(), element.primaryButton() == null);
        ButtonElement buttonElementSecondaryButton = element.secondaryButton();
        final TextButtonElement textButtonElement2 = buttonElementSecondaryButton instanceof TextButtonElement ? (TextButtonElement) buttonElementSecondaryButton : null;
        if (textButtonElement2 != null) {
            BaseButton.StyleBuilder buttonBuilder2 = getStyleSheet().getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.OUTLINE);
            if (buttonBuilder2 != null) {
                String strText2 = textButtonElement2.text();
                Intrinsics.checkNotNullExpressionValue(strText2, "button.text()");
                BaseButton.StyleBuilder styleBuilderWithText2 = buttonBuilder2.withText(strText2);
                if (styleBuilderWithText2 != null) {
                    styleBuilderWithText2.applyStyle(view.getSecondaryButton());
                }
            }
            view.getSecondaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.widgets.binders.EventHeaderBinder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    EventHeaderBinder.m2008bindPrimaryButtons$lambda7$lambda6(this.f$0, textButtonElement2, view2);
                }
            });
            MethodsDispatcher<Method> methodsDispatcher2 = getMethodsDispatcher();
            String ownerId2 = getOwnerId();
            List<Method> listOnViewed2 = textButtonElement2.onViewed();
            Intrinsics.checkNotNullExpressionValue(listOnViewed2, "button.onViewed()");
            methodsDispatcher2.dispatchMethods(ownerId2, listOnViewed2);
        }
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getSecondaryButton(), element.secondaryButton() == null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindPrimaryButtons$lambda-5$lambda-4, reason: not valid java name */
    public static final void m2007bindPrimaryButtons$lambda5$lambda4(EventHeaderBinder this$0, TextButtonElement button, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnItemSelected = button.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "button.onItemSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnItemSelected);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindPrimaryButtons$lambda-7$lambda-6, reason: not valid java name */
    public static final void m2008bindPrimaryButtons$lambda7$lambda6(EventHeaderBinder this$0, TextButtonElement button, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnItemSelected = button.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "button.onItemSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnItemSelected);
    }

    private final void bindTimer(EventHeaderElement element, EventHeaderView view) {
        view.getLabelDays().setText(element.daysText());
        view.getLabelHours().setText(element.hoursText());
        view.getLabelMinutes().setText(element.minutesText());
        view.getLabelSeconds().setText(element.secondsText());
        long jCurrentTimeMillis = System.currentTimeMillis();
        Date dateStartTime = element.startTime();
        long time = dateStartTime == null ? 0L : dateStartTime.getTime();
        view.configureTimer(jCurrentTimeMillis < time ? time : 0L);
    }

    private final void bindActionButtons(EventHeaderElement element, EventHeaderView view) {
        View viewAttachView;
        TextButtonBinder textButtonBinder = new TextButtonBinder();
        textButtonBinder.setButtonSizeKey(ButtonSizeKey.LARGE);
        textButtonBinder.setButtonStyleKey(ButtonStyleKey.GLASS);
        textButtonBinder.setBindingProvider(getBindingProvider());
        int dimension = (int) getContext().getResources().getDimension(R.dimen.spacer_mini);
        view.getButtonContainer().removeAllViews();
        List<ButtonElement> listButtons = element.buttons();
        Intrinsics.checkNotNullExpressionValue(listButtons, "element.buttons()");
        for (ButtonElement e : listButtons) {
            if (e instanceof TextButtonElement) {
                BaseButton baseButtonCreateView = textButtonBinder.createView((ViewGroup) view.getButtonContainer());
                ViewGroupKt.plusAssign(view.getButtonContainer(), baseButtonCreateView);
                Intrinsics.checkNotNullExpressionValue(e, "e");
                textButtonBinder.bind((TextButtonElement) e, baseButtonCreateView);
                viewAttachView = baseButtonCreateView;
            } else {
                ElementBindingProvider bindingProvider = getBindingProvider();
                Intrinsics.checkNotNullExpressionValue(e, "e");
                viewAttachView = bindingProvider.attachView(e, view.getButtonContainer());
            }
            if (viewAttachView != null) {
                viewAttachView.setPadding(viewAttachView.getPaddingLeft(), viewAttachView.getPaddingTop(), dimension, viewAttachView.getPaddingBottom());
            }
        }
    }

    /* JADX INFO: compiled from: EventHeaderBinder.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/explore/widgets/binders/EventHeaderBinder$Companion;", "", "()V", "TIMEZONE_FORMATTER", "Ljava/text/SimpleDateFormat;", "getTIMEZONE_FORMATTER", "()Ljava/text/SimpleDateFormat;", "setTIMEZONE_FORMATTER", "(Ljava/text/SimpleDateFormat;)V", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SimpleDateFormat getTIMEZONE_FORMATTER() {
            return EventHeaderBinder.TIMEZONE_FORMATTER;
        }

        public final void setTIMEZONE_FORMATTER(SimpleDateFormat simpleDateFormat) {
            Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
            EventHeaderBinder.TIMEZONE_FORMATTER = simpleDateFormat;
        }
    }
}
