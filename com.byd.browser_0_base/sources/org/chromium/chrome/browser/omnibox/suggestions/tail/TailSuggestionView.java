package org.chromium.chrome.browser.omnibox.suggestions.tail;

import android.content.Context;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import defpackage.ao3;
import defpackage.f4;
import java.util.ArrayList;
import org.chromium.chrome.R$style;

/* JADX INFO: loaded from: classes2.dex */
public class TailSuggestionView extends TextView {
    public f4 e;
    public int f;
    public int g;

    public TailSuggestionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setGravity(16);
        setMaxLines(1);
        setTextAppearance(getContext(), R$style.TextAppearance_TextLarge_Primary);
    }

    @Override // android.view.View
    public final void layout(int i, int i2, int i3, int i4) {
        int iMax;
        f4 f4Var = this.e;
        if (f4Var != null) {
            int i5 = this.g;
            int i6 = this.f;
            int i7 = i3 - i;
            int i8 = f4Var.b;
            f4Var.b = Math.max(i8, i6);
            f4Var.c = Math.max(f4Var.c, i5);
            int i9 = f4Var.b;
            if (i7 >= i9) {
                iMax = i6 - i5;
            } else {
                if (i9 != i8) {
                    ArrayList arrayList = f4Var.a;
                    int size = arrayList.size();
                    for (int i10 = 0; i10 < size; i10++) {
                        View view = (View) arrayList.get(i10);
                        if (view != this) {
                            ao3.e(view, "AlignmentManger.relayoutAllViewsExcept");
                        }
                    }
                }
                iMax = Math.max(i7 - f4Var.c, 0);
            }
            if (getLayoutDirection() == 1) {
                i3 -= iMax;
            } else {
                i += iMax;
            }
        }
        super.layout(i, i2, i3, i4);
    }

    public void setAlignmentManager(f4 f4Var) {
        this.e = f4Var;
        f4Var.a.add(this);
    }

    public void setFullText(String str) {
        this.f = (int) getPaint().measureText(str, 0, str.length());
    }

    public void setTailText(Spannable spannable) {
        this.g = (int) getPaint().measureText(spannable, 0, spannable.length());
        setText(spannable);
    }

    public TailSuggestionView(Context context) {
        this(context, null);
    }
}
