package com.amazon.mp3.view;

import android.content.Context;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausToastUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpannableTextView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0019\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/view/SpannableTextView;", "Landroid/widget/TextView;", "Landroid/text/SpannedString;", "annotatedText", "Landroid/text/SpannableString;", "getSpannableString", "", "text", "Landroid/widget/TextView$BufferType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "setText", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "SupportedAnnotations", "SupportedTextStyles", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SpannableTextView extends TextView {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpannableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (TextUtils.isEmpty(text) || TextView.BufferType.SPANNABLE != type) {
            super.setText(text, type);
        } else {
            if (text == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.text.SpannedString");
            }
            super.setText(getSpannableString((SpannedString) text));
        }
    }

    /* JADX INFO: compiled from: SpannableTextView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/view/SpannableTextView$SupportedAnnotations;", "", "entry", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getEntry", "()Ljava/lang/String;", "TEXT_STYLE", "CLICKABLE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum SupportedAnnotations {
        TEXT_STYLE("textStyle"),
        CLICKABLE("clickable");

        private final String entry;

        SupportedAnnotations(String str) {
            this.entry = str;
        }

        public final String getEntry() {
            return this.entry;
        }
    }

    /* JADX INFO: compiled from: SpannableTextView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/view/SpannableTextView$SupportedTextStyles;", "", "entry", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getEntry", "()Ljava/lang/String;", "BOLD", "ITALIC", "UNDERLINE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum SupportedTextStyles {
        BOLD("bold"),
        ITALIC("italic"),
        UNDERLINE("underline");

        private final String entry;

        SupportedTextStyles(String str) {
            this.entry = str;
        }

        public final String getEntry() {
            return this.entry;
        }
    }

    private final SpannableString getSpannableString(SpannedString annotatedText) {
        SpannableString spannableString = new SpannableString(annotatedText);
        if (annotatedText != null) {
            int i = 0;
            Annotation[] annotations = (Annotation[]) annotatedText.getSpans(0, annotatedText.length(), Annotation.class);
            Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
            int length = annotations.length;
            while (i < length) {
                final Annotation annotation = annotations[i];
                i++;
                String key = annotation.getKey();
                if (Intrinsics.areEqual(key, SupportedAnnotations.TEXT_STYLE.getEntry())) {
                    String value = annotation.getValue();
                    if (Intrinsics.areEqual(value, SupportedTextStyles.BOLD.getEntry())) {
                        spannableString.setSpan(new StyleSpan(1), annotatedText.getSpanStart(annotation), annotatedText.getSpanEnd(annotation), 33);
                    } else if (Intrinsics.areEqual(value, SupportedTextStyles.UNDERLINE.getEntry())) {
                        spannableString.setSpan(new UnderlineSpan(), annotatedText.getSpanStart(annotation), annotatedText.getSpanEnd(annotation), 33);
                    } else if (Intrinsics.areEqual(value, SupportedTextStyles.ITALIC.getEntry())) {
                        spannableString.setSpan(new StyleSpan(2), annotatedText.getSpanStart(annotation), annotatedText.getSpanEnd(annotation), 33);
                    }
                } else if (Intrinsics.areEqual(key, SupportedAnnotations.CLICKABLE.getEntry())) {
                    spannableString.setSpan(new ClickableSpan() { // from class: com.amazon.mp3.view.SpannableTextView$getSpannableString$1$click$1
                        @Override // android.text.style.ClickableSpan
                        public void onClick(View widget) {
                            Intrinsics.checkNotNullParameter(widget, "widget");
                            BauhausToastUtils.showTextToast(this.this$0.getContext(), annotation.getValue(), 1);
                        }
                    }, annotatedText.getSpanStart(annotation), annotatedText.getSpanEnd(annotation), 33);
                    spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.vivace_blue)), annotatedText.getSpanStart(annotation), annotatedText.getSpanEnd(annotation), 33);
                }
            }
        }
        return spannableString;
    }
}
