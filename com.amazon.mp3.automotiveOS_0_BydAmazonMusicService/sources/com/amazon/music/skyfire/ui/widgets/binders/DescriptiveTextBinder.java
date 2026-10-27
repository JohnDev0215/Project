package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement;
import Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetItemElement;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.views.TextContainerView;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: DescriptiveTextBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0002H\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/DescriptiveTextBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/DescriptiveTextWidgetElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/TextContainerView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "parseContent", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveTextBinder extends ElementBinder<DescriptiveTextWidgetElement, TextContainerView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public TextContainerView createView(ViewGroup parent) {
        TextContainerView textContainerView = new TextContainerView(getContext());
        textContainerView.applyStyle(getStyleSheet());
        return textContainerView;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(DescriptiveTextWidgetElement element, TextContainerView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        if (StringUtils.isNotEmpty(element.footer())) {
            String strFooter = element.footer();
            Intrinsics.checkNotNull(strFooter);
            Intrinsics.checkNotNullExpressionValue(strFooter, "element.footer()!!");
            view.setEllipseLabelText(strFooter);
        }
        view.setText(parseContent(element));
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    private final CharSequence parseContent(DescriptiveTextWidgetElement element) {
        String str;
        String strInterpunct = element.interpunct();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        List<DescriptiveTextWidgetItemElement> listTextWidgetItems = element.textWidgetItems();
        if (listTextWidgetItems != null) {
            for (DescriptiveTextWidgetItemElement descriptiveTextWidgetItemElement : listTextWidgetItems) {
                if (descriptiveTextWidgetItemElement != null) {
                    String strStringPlus = "";
                    if (spannableStringBuilder.length() > 0) {
                        if (StringUtils.isNotBlank(strInterpunct)) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            str = String.format(" %s ", Arrays.copyOf(new Object[]{strInterpunct}, 1));
                            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                        } else {
                            str = " ";
                        }
                        strStringPlus = Intrinsics.stringPlus("", str);
                    }
                    spannableStringBuilder.append(Intrinsics.stringPlus(strStringPlus, descriptiveTextWidgetItemElement.secondaryText()), new ForegroundColorSpan(Color.parseColor("#FFFFFFFF")), 33);
                    spannableStringBuilder.append((CharSequence) Intrinsics.stringPlus(" ", descriptiveTextWidgetItemElement.primaryText()));
                }
            }
        }
        return spannableStringBuilder;
    }
}
