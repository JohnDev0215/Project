package b.b.g.r;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/* JADX INFO: compiled from: TextLengthUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class x {

    /* JADX INFO: compiled from: TextLengthUtil.java */
    public static class a implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f1840a;

        public a(TextView textView) {
            this.f1840a = textView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            b.b.m.c.a.a("TextLengthUtil", "---checkAndChangeWidthOnGlobalLayout--onGlobalLayout--");
            if (this.f1840a.isShown()) {
                x.a(this.f1840a, new ViewTreeObserver.OnGlobalLayoutListener() { // from class: b.b.g.r.a
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public final void onGlobalLayout() {
                        this.f1788a.onGlobalLayout();
                    }
                });
            }
        }
    }

    /* JADX INFO: compiled from: TextLengthUtil.java */
    public static class b implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f1841a;

        public b(TextView textView) {
            this.f1841a = textView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapLineWidthOnGlobalLayout--onGlobalLayout--");
            if (this.f1841a.isShown()) {
                x.c(this.f1841a, new ViewTreeObserver.OnGlobalLayoutListener() { // from class: b.b.g.r.c
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public final void onGlobalLayout() {
                        this.f1791a.onGlobalLayout();
                    }
                });
            }
        }
    }

    /* JADX INFO: compiled from: TextLengthUtil.java */
    public static class c implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f1842a;

        public c(TextView textView) {
            this.f1842a = textView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapParentWidthOnGlobalLayout--onGlobalLayout--");
            if (this.f1842a.isShown()) {
                x.e(this.f1842a, new ViewTreeObserver.OnGlobalLayoutListener() { // from class: b.b.g.r.b
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public final void onGlobalLayout() {
                        this.f1789a.onGlobalLayout();
                    }
                });
            }
        }
    }

    public static void a(TextView textView, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (textView == null) {
            return;
        }
        b.b.m.c.a.a("TextLengthUtil", "---checkAndChangeWidth---");
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        int measuredWidth = textView.getMeasuredWidth();
        int measuredWidth2 = viewGroup.getMeasuredWidth();
        float f2 = measuredWidth2;
        float f3 = measuredWidth / (1.0f * f2);
        b.b.m.c.a.a("TextLengthUtil", "---checkAndChangeWidth---title: " + ((Object) textView.getText()) + ", viewWidth:" + measuredWidth + ", parentWidth: " + measuredWidth2 + ", rate: " + f3);
        if (f3 < 0.538f) {
            textView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            return;
        }
        layoutParams.width = (int) (f2 * 0.538f);
        textView.setSingleLine(false);
        textView.setLayoutParams(layoutParams);
    }

    public static void b(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new a(textView));
    }

    public static void c(TextView textView, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (textView == null) {
            return;
        }
        int maxWidth = textView.getMaxWidth();
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        int measuredWidth = textView.getMeasuredWidth();
        int lineCount = textView.getLayout().getLineCount();
        b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapLineWidth---title: " + ((Object) textView.getText()) + ", viewWidth:" + measuredWidth + ", maxWidth: " + maxWidth + ", lineCount: " + lineCount);
        if (measuredWidth < maxWidth || lineCount < 2) {
            return;
        }
        float f2 = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            float lineWidth = textView.getLayout().getLineWidth(i);
            if (f2 < lineWidth) {
                f2 = lineWidth;
            }
        }
        b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapLineWidth---vglp.width: " + layoutParams.width + ", lineMaxWidth: " + f2);
        int i2 = ((int) f2) + 1;
        if (i2 == layoutParams.width) {
            textView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            return;
        }
        layoutParams.width = i2;
        textView.setSingleLine(false);
        textView.setLayoutParams(layoutParams);
    }

    public static void d(TextView textView) {
        if (textView == null) {
            return;
        }
        b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapLineWidthOnGlobalLayout---");
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new b(textView));
    }

    public static void e(TextView textView, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (textView == null) {
            return;
        }
        b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapParentWidth---");
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        int measuredWidth = textView.getMeasuredWidth();
        int measuredWidth2 = viewGroup.getMeasuredWidth();
        int iJ = j(viewGroup);
        b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapParentWidth---title: " + ((Object) textView.getText()) + ", parentWidth: " + measuredWidth2 + ", titleWidth:" + measuredWidth + ", subviewWidth: " + iJ);
        if (iJ < measuredWidth2) {
            textView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            return;
        }
        layoutParams.width = (int) (measuredWidth2 * 0.538f);
        b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapParentWidth---title: " + ((Object) textView.getText()) + ", parentWidth: " + measuredWidth2 + ", vglpTitle.width:" + layoutParams.width);
        textView.setSingleLine(false);
        textView.setLayoutParams(layoutParams);
    }

    public static void f(TextView textView) {
        if (textView == null) {
            return;
        }
        b.b.m.c.a.a("TextLengthUtil", "---checkAndWrapParentWidthOnGlobalLayout---");
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new c(textView));
    }

    public static String g(String str) {
        return i(str, 15, 10);
    }

    public static String h(String str) {
        return i(str, 63, 63);
    }

    public static String i(String str, int i, int i2) {
        if (TextUtils.isEmpty(str) || str.length() <= i2) {
            return str;
        }
        b.b.m.c.a.a("TextLengthUtil", "---getName--text.length: " + str.length());
        int iK = b.b.m.g.f.k(str);
        if (iK >= 0 && iK < i) {
            int length = str.length();
            if (length > i2) {
                length = i2;
            }
            int i3 = i2 - 2;
            i = b.b.m.g.f.a(str.substring(0, length)) < i3 ? (iK <= i2 || iK >= i) ? i2 : iK + 1 : i3;
        }
        b.b.m.c.a.a("TextLengthUtil", "---getName--1--maxLength: " + i + ", index: " + iK);
        if (i >= str.length()) {
            return str;
        }
        return str.substring(0, i) + "...";
    }

    public static int j(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return 0;
        }
        int childCount = viewGroup.getChildCount();
        View childAt = null;
        int measuredWidth = 0;
        for (int i = 0; i < childCount; i++) {
            childAt = viewGroup.getChildAt(i);
            measuredWidth += childAt.getMeasuredWidth();
            b.b.m.c.a.a("TextLengthUtil", "---getParentSubviewsWidth---i: " + i + ", title: " + ((Object) ((TextView) childAt).getText()) + ", measuredWidth: " + childAt.getMeasuredWidth());
            if (childAt.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                measuredWidth += marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
            }
        }
        b.b.m.c.a.a("TextLengthUtil", "---getParentSubviewsWidth---title: " + ((Object) ((TextView) childAt).getText()) + ", widthCount: " + measuredWidth);
        return measuredWidth;
    }

    public static String k(String str) {
        return i(str, 15, 10);
    }
}
