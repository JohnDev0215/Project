package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import com.amazon.music.views.library.models.DescriptiveTextItemModel;
import com.amazon.music.views.library.models.DescriptiveTextModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.TextContainerView;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt___StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: DescriptiveTextBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001b2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0003H\u0002J\u001c\u0010\u0016\u001a\u00020\u0017*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0017H\u0002R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/views/library/binders/DescriptiveTextBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/TextContainerView;", "Lcom/amazon/music/views/library/models/DescriptiveTextModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "parseContent", "", "ellipsize", "", "maxLength", "", "ellipsis", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveTextBinder implements UniversalBinder<TextContainerView, DescriptiveTextModel> {
    private static final int DEFAULT_PRIMARY_COLOR = Color.parseColor("#FFFFFFFF");
    private static final int DEFAULT_SECONDARY_COLOR = Color.parseColor("#99ffffff");
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<DescriptiveTextModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(DescriptiveTextModel.class);
    }

    public DescriptiveTextBinder(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public TextContainerView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        TextContainerView textContainerView = new TextContainerView(context);
        textContainerView.applyStyle(getStyleSheet());
        return textContainerView;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(TextContainerView view, DescriptiveTextModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setText(parseContent(model));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        sb.append((Object) model.getSeparator());
        sb.append(' ');
        spannableStringBuilder.append((CharSequence) sb.toString());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(DEFAULT_PRIMARY_COLOR), 0, spannableStringBuilder.length(), 33);
        view.setTrimToken(spannableStringBuilder);
        view.setTruncationText(model.getFooter());
        view.setAlwaysDisplayTruncationText(Boolean.valueOf(model.getAlwaysDisplayFooterText()));
        view.setMaxLineCount(model.getMaxLineCount());
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue = spacerInPixels != null ? spacerInPixels.intValue() : 0;
        view.setContentMargins(view, (10 & 2) != 0 ? null : null, (10 & 4) != 0 ? null : Integer.valueOf(iIntValue), (10 & 8) != 0 ? null : null, (10 & 16) != 0 ? null : Integer.valueOf(iIntValue));
    }

    private final CharSequence parseContent(DescriptiveTextModel model) {
        String strStringPlus;
        String str;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (DescriptiveTextItemModel descriptiveTextItemModel : model.getText()) {
            if (spannableStringBuilder.length() > 0) {
                if (StringUtils.isNotBlank(model.getSeparator())) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    str = String.format(" %s ", Arrays.copyOf(new Object[]{model.getSeparator()}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                } else {
                    str = " ";
                }
                strStringPlus = Intrinsics.stringPlus("", str);
            } else {
                strStringPlus = "";
            }
            String secondaryText = descriptiveTextItemModel.getSecondaryText();
            if (secondaryText != null) {
                strStringPlus = Intrinsics.stringPlus(strStringPlus, ellipsize(secondaryText, model.getMaxItemLength(), model.getEllipsisText()));
            }
            spannableStringBuilder.append(strStringPlus, new ForegroundColorSpan(DEFAULT_PRIMARY_COLOR), 33);
            String primaryText = descriptiveTextItemModel.getPrimaryText();
            if (primaryText != null) {
                spannableStringBuilder.append(Intrinsics.stringPlus(descriptiveTextItemModel.getSecondaryText() == null ? "" : " ", ellipsize(primaryText, model.getMaxItemLength(), model.getEllipsisText())), new ForegroundColorSpan(DEFAULT_SECONDARY_COLOR), 33);
            }
        }
        return spannableStringBuilder;
    }

    private final String ellipsize(String str, int i, String str2) {
        String strTake = StringsKt___StringsKt.take(str, i);
        if (str.length() <= i) {
            str2 = "";
        }
        return Intrinsics.stringPlus(strTake, str2);
    }
}
