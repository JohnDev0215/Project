package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.music.alps.R$dimen;
import com.amazon.music.alps.R$drawable;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.liveevent.ktx.StringKt;
import com.amazon.music.alps.liveevent.ktx.ViewKt;
import com.amazon.music.alps.liveevent.view.NewEventHeaderView;
import com.amazon.music.alps.liveevent.viewmodel.NewEventHeaderViewModel;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.ButtonModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.amazon.ui.foundations.ktx.ViewGroupKt;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.joda.time.DateTime;

/* JADX INFO: compiled from: NewEventHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\u0002\u0010\rJ\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0007J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0007J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0007J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0002H\u0007J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\u0010\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/NewEventHeaderBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView;", "Lcom/amazon/music/alps/liveevent/viewmodel/NewEventHeaderViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "uiEvents", "Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UIEvents;", "userEvents", "Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UserEvents;", "onViewed", "Lkotlin/Function0;", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UIEvents;Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UserEvents;Lkotlin/jvm/functions/Function0;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "bindActionButtons", "bindCountdown", "bindPrimaryButtons", "bindStyles", "bindText", "createView", "context", "Landroid/content/Context;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NewEventHeaderBinder implements UniversalBinder<NewEventHeaderView, NewEventHeaderViewModel> {
    private final Function0<Unit> onViewed;
    private final StyleSheet styleSheet;
    private final NewEventHeaderView.UIEvents uiEvents;
    private final NewEventHeaderView.UserEvents userEvents;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<NewEventHeaderViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(NewEventHeaderViewModel.class);
    }

    public NewEventHeaderBinder(StyleSheet styleSheet, NewEventHeaderView.UIEvents uIEvents, NewEventHeaderView.UserEvents userEvents, Function0<Unit> onViewed) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.styleSheet = styleSheet;
        this.uiEvents = uIEvents;
        this.userEvents = userEvents;
        this.onViewed = onViewed;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public NewEventHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new NewEventHeaderView(context, this.uiEvents);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(NewEventHeaderView view, NewEventHeaderViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bindStyles(view);
        bindText(view, model);
        bindPrimaryButtons(view, model);
        bindCountdown(view, model);
        bindActionButtons(view, model);
        this.onViewed.invoke();
    }

    @VisibleForTesting
    public final void bindStyles(NewEventHeaderView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        StyleSheet styleSheet = this.styleSheet;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return;
        }
        view.setPadding(gridSize.getMarginSpace() + gridSize.getColumnSpace(), view.getPaddingTop(), gridSize.getMarginSpace() + gridSize.getColumnSpace(), view.getPaddingBottom());
        ViewKt.applyFontStyle(view.getLabel(), this.styleSheet, FontStyleKey.LABEL);
        ViewKt.applyFontStyle(view.getHeadline(), this.styleSheet, FontStyleKey.HEADLINE_2);
        ViewKt.applyFontStyle(view.getPrimaryText(), this.styleSheet, FontStyleKey.HEADLINE_4);
        ViewKt.applyFontStyle(view.getSecondaryText(), this.styleSheet, FontStyleKey.SECONDARY);
    }

    private final void bindText(NewEventHeaderView view, NewEventHeaderViewModel model) {
        view.getLabel().setText(model.getLabel());
        view.getHeadline().setText(model.getHeadline());
        view.getPrimaryText().setText(model.getPrimaryText());
        view.getSecondaryText().setText(model.getSecondaryText());
        TextViewKt.collapseIfEmpty(view.getLabel());
        TextViewKt.collapseIfEmpty(view.getHeadline());
        TextViewKt.collapseIfEmpty(view.getPrimaryText());
        TextViewKt.collapseIfEmpty(view.getSecondaryText());
    }

    @VisibleForTesting
    public final void bindPrimaryButtons(NewEventHeaderView view, NewEventHeaderViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        final ButtonModel primaryButton = model.getPrimaryButton();
        if (primaryButton != null) {
            BaseButton.StyleBuilder buttonBuilder = this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
            if (buttonBuilder != null) {
                String text = primaryButton.getText();
                if (text == null) {
                    text = "";
                }
                BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(text);
                if (styleBuilderWithText != null) {
                    styleBuilderWithText.applyStyle(view.getPrimaryButton());
                }
            }
            view.getPrimaryButton().setContentDescription(primaryButton.getText());
            view.getPrimaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.alps.liveevent.binder.NewEventHeaderBinder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NewEventHeaderBinder.m1871bindPrimaryButtons$lambda6$lambda5$lambda4(this.f$0, primaryButton, view2);
                }
            });
        }
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getPrimaryButton(), model.getPrimaryButton() == null);
        final ButtonModel secondaryButton = model.getSecondaryButton();
        if (secondaryButton != null) {
            BaseButton.StyleBuilder buttonBuilder2 = this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.OUTLINE);
            if (buttonBuilder2 != null) {
                String text2 = secondaryButton.getText();
                BaseButton.StyleBuilder styleBuilderWithText2 = buttonBuilder2.withText(text2 != null ? text2 : "");
                if (styleBuilderWithText2 != null) {
                    styleBuilderWithText2.applyStyle(view.getSecondaryButton());
                }
            }
            view.getSecondaryButton().setContentDescription(secondaryButton.getText());
            view.getSecondaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.alps.liveevent.binder.NewEventHeaderBinder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NewEventHeaderBinder.m1870bindPrimaryButtons$lambda10$lambda9$lambda8(this.f$0, secondaryButton, view2);
                }
            });
        }
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getSecondaryButton(), model.getSecondaryButton() == null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindPrimaryButtons$lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m1871bindPrimaryButtons$lambda6$lambda5$lambda4(NewEventHeaderBinder this$0, ButtonModel button, View view) {
        Function1<String, Unit> onClickPrimaryButton;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        NewEventHeaderView.UserEvents userEvents = this$0.userEvents;
        if (userEvents == null || (onClickPrimaryButton = userEvents.getOnClickPrimaryButton()) == null) {
            return;
        }
        String target = button.getTarget();
        if (target == null) {
            target = "";
        }
        onClickPrimaryButton.invoke(target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindPrimaryButtons$lambda-10$lambda-9$lambda-8, reason: not valid java name */
    public static final void m1870bindPrimaryButtons$lambda10$lambda9$lambda8(NewEventHeaderBinder this$0, ButtonModel button, View view) {
        Function1<String, Unit> onClickSecondaryButton;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        NewEventHeaderView.UserEvents userEvents = this$0.userEvents;
        if (userEvents == null || (onClickSecondaryButton = userEvents.getOnClickSecondaryButton()) == null) {
            return;
        }
        String target = button.getTarget();
        if (target == null) {
            target = "";
        }
        onClickSecondaryButton.invoke(target);
    }

    @VisibleForTesting
    public final void bindCountdown(NewEventHeaderView view, NewEventHeaderViewModel model) {
        String scheduledStartDate;
        DateTime dateTimeIso8601DateTime;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        int i = 0;
        if (model.getShowCountdown()) {
            TextView[] counters = view.getCounters();
            int length = counters.length;
            int i2 = 0;
            while (i2 < length) {
                TextView textView = counters[i2];
                i2++;
                ViewKt.applyFontStyle(textView, this.styleSheet, FontStyleKey.HEADLINE_4);
                Integer color = this.styleSheet.getColor(ColorKey.COLOR_PRIMARY);
                Intrinsics.checkNotNull(color);
                textView.setTextColor(color.intValue());
            }
            TextView[] labels = view.getLabels();
            int length2 = labels.length;
            int i3 = 0;
            while (i3 < length2) {
                TextView textView2 = labels[i3];
                i3++;
                ViewKt.applyFontStyle(textView2, this.styleSheet, FontStyleKey.TERTIARY);
            }
            view.getLabelDays().setText(view.getContext().getString(R$string.unit_days));
            view.getLabelHours().setText(view.getContext().getString(R$string.unit_hours));
            view.getLabelMinutes().setText(view.getContext().getString(R$string.unit_minutes));
            view.getLabelSeconds().setText(view.getContext().getString(R$string.unit_seconds));
            long jCurrentTimeMillis = System.currentTimeMillis();
            Lineup nextLineup = model.getNextLineup();
            long millis = (nextLineup == null || (scheduledStartDate = nextLineup.getScheduledStartDate()) == null || (dateTimeIso8601DateTime = StringKt.iso8601DateTime(scheduledStartDate)) == null) ? 0L : dateTimeIso8601DateTime.getMillis();
            view.configureCountdown(jCurrentTimeMillis < millis ? millis : 0L);
        }
        Object[] objArrPlus = ArraysKt___ArraysJvmKt.plus((Object[]) view.getLabels(), (Object[]) view.getCounters());
        int length3 = objArrPlus.length;
        while (i < length3) {
            Object obj = objArrPlus[i];
            i++;
            com.amazon.ui.foundations.ktx.ViewKt.collapseIf((TextView) obj, !model.getShowCountdown());
        }
    }

    @VisibleForTesting
    public final void bindActionButtons(NewEventHeaderView view, NewEventHeaderViewModel model) {
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Drawable drawable2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.getButtonContainer().removeAllViews();
        ArrayList<BaseButton> arrayList = new ArrayList();
        if (model.getAddToCalendarButton() != null && (drawable2 = ContextCompat.getDrawable(view.getContext(), R$drawable.ic_action_calendar)) != null) {
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            BaseButton baseButton = new BaseButton(context, null, 0, 6, null);
            String text = model.getAddToCalendarButton().getText();
            BaseButton.StyleBuilder buttonBuilder = this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.SOLID);
            if (buttonBuilder != null) {
                BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(text == null ? "" : text);
                if (styleBuilderWithText != null && (styleBuilderWithIcon2 = styleBuilderWithText.withIcon(drawable2)) != null) {
                    styleBuilderWithIcon2.applyStyle(baseButton);
                }
            }
            baseButton.setContentDescription(text);
            baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.alps.liveevent.binder.NewEventHeaderBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NewEventHeaderBinder.m1868bindActionButtons$lambda19$lambda18$lambda17$lambda16(this.f$0, view2);
                }
            });
            arrayList.add(baseButton);
        }
        if (model.getShareButton() != null && (drawable = ContextCompat.getDrawable(view.getContext(), R$drawable.ic_action_share)) != null) {
            Context context2 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "view.context");
            BaseButton baseButton2 = new BaseButton(context2, null, 0, 6, null);
            BaseButton.StyleBuilder iconBuilder = this.styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS);
            if (iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
                styleBuilderWithIcon.applyStyle(baseButton2);
            }
            baseButton2.setContentDescription(model.getShareButton().getText());
            baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.alps.liveevent.binder.NewEventHeaderBinder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NewEventHeaderBinder.m1869bindActionButtons$lambda24$lambda23$lambda22$lambda21(this.f$0, view2);
                }
            });
            arrayList.add(baseButton2);
        }
        int dimension = (int) view.getContext().getResources().getDimension(R$dimen.spacer_mini);
        for (BaseButton baseButton3 : arrayList) {
            baseButton3.setPadding(baseButton3.getPaddingLeft(), baseButton3.getPaddingTop(), dimension, baseButton3.getPaddingBottom());
            view.getButtonContainer().addView(baseButton3);
        }
        ViewGroupKt.collapseIfEmpty(view.getButtonContainer());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindActionButtons$lambda-19$lambda-18$lambda-17$lambda-16, reason: not valid java name */
    public static final void m1868bindActionButtons$lambda19$lambda18$lambda17$lambda16(NewEventHeaderBinder this$0, View view) {
        Function0<Unit> onAddToCalendar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NewEventHeaderView.UserEvents userEvents = this$0.userEvents;
        if (userEvents == null || (onAddToCalendar = userEvents.getOnAddToCalendar()) == null) {
            return;
        }
        onAddToCalendar.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindActionButtons$lambda-24$lambda-23$lambda-22$lambda-21, reason: not valid java name */
    public static final void m1869bindActionButtons$lambda24$lambda23$lambda22$lambda21(NewEventHeaderBinder this$0, View view) {
        Function0<Unit> onShare;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NewEventHeaderView.UserEvents userEvents = this$0.userEvents;
        if (userEvents == null || (onShare = userEvents.getOnShare()) == null) {
            return;
        }
        onShare.invoke();
    }
}
