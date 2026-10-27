package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import androidx.core.text.HtmlCompat;
import com.amazon.music.alps.liveevent.viewmodel.FlexibleTextContainerViewModel;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.FlexibleTextContainerView;
import com.amazon.ui.foundations.styles.GridSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: FlexibleTextContainerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0002H\u0007J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/FlexibleTextContainerBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/FlexibleTextContainerView;", "Lcom/amazon/music/alps/liveevent/viewmodel/FlexibleTextContainerViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "onViewed", "Lkotlin/Function0;", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lkotlin/jvm/functions/Function0;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "bindStyle", "createView", "context", "Landroid/content/Context;", "parseHtml", "", "htmlText", "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FlexibleTextContainerBinder implements UniversalBinder<FlexibleTextContainerView, FlexibleTextContainerViewModel> {
    private final Function0<Unit> onViewed;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<FlexibleTextContainerViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(FlexibleTextContainerViewModel.class);
    }

    public FlexibleTextContainerBinder(StyleSheet styleSheet, Function0<Unit> onViewed) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.styleSheet = styleSheet;
        this.onViewed = onViewed;
    }

    public /* synthetic */ FlexibleTextContainerBinder(StyleSheet styleSheet, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, (i & 2) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.binder.FlexibleTextContainerBinder.1
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
    public FlexibleTextContainerView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        FlexibleTextContainerView flexibleTextContainerView = new FlexibleTextContainerView(context);
        flexibleTextContainerView.applyStyle(this.styleSheet);
        return flexibleTextContainerView;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(FlexibleTextContainerView view, FlexibleTextContainerViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bindStyle(view);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        Integer maxCollapsedLineCount = model.getMaxCollapsedLineCount();
        if (maxCollapsedLineCount != null) {
            view.setMaxCollapsedLineCount(maxCollapsedLineCount.intValue());
        }
        String collapseLabelText = model.getCollapseLabelText();
        if (collapseLabelText != null) {
            view.setCollapseLabelText(collapseLabelText);
        }
        String expandLabelText = model.getExpandLabelText();
        if (expandLabelText != null) {
            view.setExpandLabelText(expandLabelText);
        }
        String htmlText = model.getHtmlText();
        if (htmlText != null) {
            view.setText(parseHtml(htmlText));
        }
        this.onViewed.invoke();
    }

    @VisibleForTesting
    public final void bindStyle(FlexibleTextContainerView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        StyleSheet styleSheet = this.styleSheet;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return;
        }
        int columnSpace = gridSize.getColumnSpace() / 2;
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        view.setPadding(columnSpace, iIntValue, columnSpace, iIntValue);
    }

    private final CharSequence parseHtml(String htmlText) {
        if (htmlText == null || htmlText.length() == 0) {
            return "";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) HtmlCompat.fromHtml(htmlText, 0));
        return spannableStringBuilder;
    }
}
