package defpackage;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.UpdateAppearance;

/* JADX INFO: compiled from: SuggestionSpannable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ew2 extends SpannableString {
    public ew2(String str) {
        super(str);
    }

    @Override // android.text.SpannableString
    public final boolean equals(Object obj) {
        if (!(obj instanceof ew2)) {
            return false;
        }
        ew2 ew2Var = (ew2) obj;
        if (!TextUtils.equals(this, ew2Var)) {
            return false;
        }
        if (TextUtils.isEmpty(this)) {
            return true;
        }
        UpdateAppearance[] updateAppearanceArr = (UpdateAppearance[]) getSpans(0, length(), UpdateAppearance.class);
        UpdateAppearance[] updateAppearanceArr2 = (UpdateAppearance[]) ew2Var.getSpans(0, ew2Var.length(), UpdateAppearance.class);
        if (updateAppearanceArr.length != updateAppearanceArr2.length) {
            return false;
        }
        for (int i = 0; i < updateAppearanceArr.length; i++) {
            UpdateAppearance updateAppearance = updateAppearanceArr[i];
            UpdateAppearance updateAppearance2 = updateAppearanceArr2[i];
            if (getSpanStart(updateAppearance) == ew2Var.getSpanStart(updateAppearance2) && getSpanEnd(updateAppearance) == ew2Var.getSpanEnd(updateAppearance2) && getSpanFlags(updateAppearance) == ew2Var.getSpanFlags(updateAppearance2) && updateAppearance.getClass() == updateAppearance2.getClass()) {
                if (updateAppearance instanceof ForegroundColorSpan) {
                    if (((ForegroundColorSpan) updateAppearance).getForegroundColor() != ((ForegroundColorSpan) updateAppearance2).getForegroundColor()) {
                        return false;
                    }
                } else if (updateAppearance instanceof BackgroundColorSpan) {
                    if (((BackgroundColorSpan) updateAppearance).getBackgroundColor() != ((BackgroundColorSpan) updateAppearance2).getBackgroundColor()) {
                        return false;
                    }
                } else if (updateAppearance instanceof StyleSpan) {
                    if (((StyleSpan) updateAppearance).getStyle() != ((StyleSpan) updateAppearance2).getStyle()) {
                        return false;
                    }
                } else if (updateAppearance instanceof TextAppearanceSpan) {
                    TextAppearanceSpan textAppearanceSpan = (TextAppearanceSpan) updateAppearance;
                    TextAppearanceSpan textAppearanceSpan2 = (TextAppearanceSpan) updateAppearance2;
                    if (!TextUtils.equals(textAppearanceSpan.getFamily(), textAppearanceSpan2.getFamily()) || !textAppearanceSpan.getLinkTextColor().equals(textAppearanceSpan2.getLinkTextColor()) || !textAppearanceSpan.getTextColor().equals(textAppearanceSpan2.getTextColor()) || textAppearanceSpan.getTextSize() != textAppearanceSpan2.getTextSize() || textAppearanceSpan.getTextStyle() != textAppearanceSpan2.getTextStyle()) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
