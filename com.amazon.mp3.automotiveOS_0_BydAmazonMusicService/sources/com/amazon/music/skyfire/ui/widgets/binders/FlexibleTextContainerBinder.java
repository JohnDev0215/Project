package com.amazon.music.skyfire.ui.widgets.binders;

import Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement;
import Touch.WidgetsInterface.v1_0.HTMLTextElement;
import Touch.WidgetsInterface.v1_0.TextElement;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.ViewGroup;
import com.amazon.music.views.library.views.FlexibleTextContainerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlexibleTextContainerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0002H\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/FlexibleTextContainerBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/FlexibleTextContainerElement;", "Lcom/amazon/music/views/library/views/FlexibleTextContainerView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "parseContent", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FlexibleTextContainerBinder extends ElementBinder<FlexibleTextContainerElement, FlexibleTextContainerView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public FlexibleTextContainerView createView(ViewGroup parent) {
        FlexibleTextContainerView flexibleTextContainerView = new FlexibleTextContainerView(getContext());
        flexibleTextContainerView.applyStyle(getStyleSheet());
        return flexibleTextContainerView;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(FlexibleTextContainerElement element, FlexibleTextContainerView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.setMaxCollapsedLineCount(element.lineCountWhenCollapsed());
        String strCollapseLabelText = element.collapseLabelText();
        Intrinsics.checkNotNullExpressionValue(strCollapseLabelText, "element.collapseLabelText()");
        view.setCollapseLabelText(strCollapseLabelText);
        String strExpandLabelText = element.expandLabelText();
        Intrinsics.checkNotNullExpressionValue(strExpandLabelText, "element.expandLabelText()");
        view.setExpandLabelText(strExpandLabelText);
        view.setText(parseContent(element));
    }

    private final CharSequence parseContent(FlexibleTextContainerElement element) {
        Unit unit;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        List<TextElement> listRichContent = element.richContent();
        if (listRichContent == null) {
            unit = null;
        } else {
            ArrayList<TextElement> arrayList = new ArrayList();
            Iterator<T> it = listRichContent.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (((TextElement) next).text() != null) {
                    arrayList.add(next);
                }
            }
            for (TextElement textElement : arrayList) {
                if (textElement instanceof HTMLTextElement) {
                    spannableStringBuilder.append((CharSequence) Html.fromHtml(textElement.text(), 0));
                } else {
                    spannableStringBuilder.append((CharSequence) textElement.text());
                }
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String strContent = element.content();
            if (strContent == null) {
                strContent = "";
            }
            spannableStringBuilder.append((CharSequence) strContent);
        }
        return spannableStringBuilder;
    }
}
