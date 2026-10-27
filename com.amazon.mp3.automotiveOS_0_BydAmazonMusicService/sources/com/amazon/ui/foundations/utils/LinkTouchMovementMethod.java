package com.amazon.ui.foundations.utils;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkTouchMovementMethod.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/ui/foundations/utils/LinkTouchMovementMethod;", "Landroid/text/method/LinkMovementMethod;", "()V", "mPressedSpan", "Lcom/amazon/ui/foundations/utils/TouchableSpan;", "getPressedSpan", "textView", "Landroid/widget/TextView;", "spannable", "Landroid/text/Spannable;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onTouchEvent", "", "positionWithinTag", "position", "", ParserUtil.TAG_QUERY_PARAM_NAME, "", "Companion", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LinkTouchMovementMethod extends LinkMovementMethod {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<LinkTouchMovementMethod> instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<LinkTouchMovementMethod>() { // from class: com.amazon.ui.foundations.utils.LinkTouchMovementMethod$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LinkTouchMovementMethod invoke() {
            return new LinkTouchMovementMethod();
        }
    });
    private TouchableSpan mPressedSpan;

    /* JADX INFO: compiled from: LinkTouchMovementMethod.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/ui/foundations/utils/LinkTouchMovementMethod$Companion;", "", "()V", "instance", "Lcom/amazon/ui/foundations/utils/LinkTouchMovementMethod;", "getInstance", "()Lcom/amazon/ui/foundations/utils/LinkTouchMovementMethod;", "instance$delegate", "Lkotlin/Lazy;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LinkTouchMovementMethod getInstance() {
            return (LinkTouchMovementMethod) LinkTouchMovementMethod.instance$delegate.getValue();
        }
    }

    @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(spannable, "spannable");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() == 0) {
            TouchableSpan pressedSpan = getPressedSpan(textView, spannable, event);
            this.mPressedSpan = pressedSpan;
            if (pressedSpan != null) {
                pressedSpan.setPressed(true);
            }
            Selection.setSelection(spannable, spannable.getSpanStart(this.mPressedSpan), spannable.getSpanEnd(this.mPressedSpan));
        } else if (event.getAction() == 2) {
            TouchableSpan pressedSpan2 = getPressedSpan(textView, spannable, event);
            TouchableSpan touchableSpan = this.mPressedSpan;
            if (pressedSpan2 != touchableSpan) {
                if (touchableSpan != null) {
                    touchableSpan.setPressed(false);
                }
                this.mPressedSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            super.onTouchEvent(textView, spannable, event);
            TouchableSpan touchableSpan2 = this.mPressedSpan;
            if (touchableSpan2 != null) {
                touchableSpan2.setPressed(false);
            }
            this.mPressedSpan = null;
            Selection.removeSelection(spannable);
        }
        return true;
    }

    private final TouchableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {
        int x = (((int) event.getX()) - textView.getTotalPaddingLeft()) + textView.getScrollX();
        int y = (((int) event.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY();
        Layout layout = textView.getLayout();
        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(y), x);
        Object[] spans = spannable.getSpans(offsetForHorizontal, offsetForHorizontal, TouchableSpan.class);
        Intrinsics.checkNotNullExpressionValue(spans, "spannable.getSpans<Touch…pan::class.java\n        )");
        TouchableSpan[] touchableSpanArr = (TouchableSpan[]) spans;
        if ((!(touchableSpanArr.length == 0)) && positionWithinTag(offsetForHorizontal, spannable, touchableSpanArr[0])) {
            return touchableSpanArr[0];
        }
        return null;
    }

    private final boolean positionWithinTag(int position, Spannable spannable, Object tag) {
        return position >= spannable.getSpanStart(tag) && position <= spannable.getSpanEnd(tag);
    }
}
