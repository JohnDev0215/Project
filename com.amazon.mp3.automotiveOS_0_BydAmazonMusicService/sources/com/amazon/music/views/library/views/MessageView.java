package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessageView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u001b\u0010\u0005\u001a\u00020\u00068DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/views/library/views/MessageView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mMessageText", "Landroid/widget/TextView;", "getMMessageText", "()Landroid/widget/TextView;", "mMessageText$delegate", "Lkotlin/Lazy;", "setMessageText", "", "text", "", "setTextStyle", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class MessageView extends ConstraintLayout {

    /* JADX INFO: renamed from: mMessageText$delegate, reason: from kotlin metadata */
    private final Lazy mMessageText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessageView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mMessageText = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.MessageView$mMessageText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.message_text);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_message_layout, this);
    }

    protected final TextView getMMessageText() {
        Object value = this.mMessageText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mMessageText>(...)");
        return (TextView) value;
    }

    public final void setMessageText(String text) {
        TextView mMessageText = getMMessageText();
        if (mMessageText == null) {
            return;
        }
        mMessageText.setText(text);
    }

    public final void setTextStyle(StyleSheet styleSheet) {
        Integer spacerInPixels;
        Integer color = styleSheet == null ? null : styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_5);
        FontSize fontSize = styleSheet != null ? styleSheet.getFontSize(FontSizeKey.BODY_2) : null;
        int iIntValue = (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI)) == null) ? 0 : spacerInPixels.intValue();
        if (color != null) {
            getMMessageText().setTextColor(color.intValue());
        }
        if (fontSize != null) {
            FontUtil.INSTANCE.applyFontSize(getMMessageText(), fontSize);
        }
        getMMessageText().setGravity(8388611);
        getMMessageText().setTextAlignment(2);
        getMMessageText().setPadding(iIntValue, 0, iIntValue, 0);
    }
}
