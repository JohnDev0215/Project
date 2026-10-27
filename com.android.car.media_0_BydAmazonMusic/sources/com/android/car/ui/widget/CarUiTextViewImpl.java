package com.android.car.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.OneShotPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executor;
import spi.n7;
import spi.u1;
import spi.v1;
import spi.w1;
import spi.x1;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class CarUiTextViewImpl extends CarUiTextView {
    public static final /* synthetic */ int d = 0;

    @NonNull
    public List<u1> b;
    public OneShotPreDrawListener c;

    public CarUiTextViewImpl(Context context) {
        super(context);
        this.b = Collections.emptyList();
    }

    public static void a(CarUiTextViewImpl carUiTextViewImpl) {
        CharSequence charSequenceC;
        Objects.requireNonNull(carUiTextViewImpl.b);
        if (carUiTextViewImpl.getLayout() == null) {
            carUiTextViewImpl.c = OneShotPreDrawListener.add(carUiTextViewImpl, new v1(carUiTextViewImpl, 1));
            return;
        }
        carUiTextViewImpl.c = null;
        if (carUiTextViewImpl.b.stream().allMatch(n7.t)) {
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String str = "";
        int i = 0;
        while (i < carUiTextViewImpl.b.size()) {
            u1 u1Var = carUiTextViewImpl.b.get(i);
            SpannableStringBuilder spannableStringBuilderAppend = spannableStringBuilder.append((CharSequence) str);
            if (u1Var.c.size() > 1) {
                Iterator<CharSequence> it = u1Var.c.iterator();
                while (it.hasNext()) {
                    charSequenceC = it.next();
                    if (charSequenceC.length() > u1Var.b || !TextUtils.equals(charSequenceC, carUiTextViewImpl.c(charSequenceC, u1Var.a))) {
                    }
                }
                charSequenceC = carUiTextViewImpl.c(u1Var.b(), u1Var.a);
            } else {
                charSequenceC = carUiTextViewImpl.c(u1Var.b(), u1Var.a);
            }
            spannableStringBuilderAppend.append(charSequenceC);
            i++;
            str = "\n";
        }
        carUiTextViewImpl.b(spannableStringBuilder, false, x1.d);
    }

    public final void b(@NonNull CharSequence charSequence, boolean z, @NonNull Executor executor) {
        executor.execute(new w1(this, new WeakReference(this), charSequence, getTextMetricsParams(), z));
    }

    public final CharSequence c(CharSequence charSequence, int i) {
        Layout layout = getLayout();
        Objects.requireNonNull(layout);
        int width = layout.getWidth();
        if (i == 1) {
            return TextUtils.ellipsize(charSequence, getPaint(), width, TextUtils.TruncateAt.END);
        }
        int length = charSequence.length();
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        while (!z) {
            int iBreakText = getPaint().breakText(charSequence, i3, length, true, width, null) + i3;
            int iIndexOf = TextUtils.indexOf(charSequence, "\n", i3, iBreakText);
            if (iIndexOf != -1) {
                iBreakText = Math.min(iBreakText, iIndexOf + 1);
            }
            i4++;
            if (i4 == i || iBreakText > length - 1) {
                z = true;
            }
            if (!z && !Character.isWhitespace(charSequence.charAt(iBreakText))) {
                int i5 = 0;
                while (!Character.isWhitespace(charSequence.charAt((iBreakText - i5) - 1))) {
                    i5++;
                    if (iBreakText - i5 == i3 || i5 >= iBreakText) {
                        i5 = 0;
                        break;
                    }
                }
                iBreakText -= i5;
            }
            int i6 = i3;
            i3 = iBreakText;
            i2 = i6;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(charSequence.subSequence(0, i2));
        try {
            Scanner scanner = new Scanner(charSequence.subSequence(i2, length).toString());
            try {
                if (scanner.hasNextLine()) {
                    spannableStringBuilder.append(TextUtils.ellipsize(scanner.nextLine(), getPaint(), width, TextUtils.TruncateAt.END));
                    if (charSequence instanceof Spanned) {
                        TextUtils.copySpansFrom((Spanned) charSequence, 0, spannableStringBuilder.length(), Object.class, spannableStringBuilder, 0);
                    }
                }
                scanner.close();
            } finally {
            }
        } catch (Exception e) {
            Log.w("CarUiTextViewImpl", "scanner exception: " + e);
        }
        return spannableStringBuilder;
    }

    @Override // com.android.car.ui.widget.CarUiTextView
    public void setText(@NonNull List<u1> list) {
        Objects.requireNonNull(list);
        this.b = list;
        b(u1.a(list), true, x1.b);
    }

    public CarUiTextViewImpl(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = Collections.emptyList();
    }

    @Override // com.android.car.ui.widget.CarUiTextView
    public void setText(@NonNull u1 u1Var) {
        Objects.requireNonNull(u1Var);
        this.b = Collections.singletonList(u1Var);
        b(u1Var.b(), true, x1.c);
    }

    public CarUiTextViewImpl(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = Collections.emptyList();
    }
}
