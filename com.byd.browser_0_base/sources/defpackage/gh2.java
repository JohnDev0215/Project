package defpackage;

import android.content.Context;
import android.text.Layout;
import android.text.TextPaint;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: SectionUiUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gh2 {
    public static String a(Context context, dh2 dh2Var, Layout layout, TextPaint textPaint) {
        int iC = dh2Var.c();
        lf0 lf0VarA = dh2Var.a(0);
        String string = context.getString(R$string.autofill_address_summary_separator);
        String strB = lf0VarA.b(-1, string);
        int i = iC - 1;
        if (i == 0) {
            return strB;
        }
        int i2 = dh2Var.b;
        int i3 = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? 0 : R$plurals.payment_request_payment_methods_preview : R$plurals.payment_request_contacts_preview : R$plurals.payment_request_shipping_options_preview : R$plurals.payment_request_shipping_addresses_preview;
        String quantityString = context.getResources().getQuantityString(i3, i, strB, Integer.valueOf(i));
        if (textPaint != null && layout != null) {
            int ellipsizedWidth = layout.getEllipsizedWidth();
            while (Layout.getDesiredWidth(quantityString, textPaint) > ellipsizedWidth) {
                strB = lf0VarA.b(strB.length(), string);
                quantityString = context.getResources().getQuantityString(i3, i, strB, Integer.valueOf(i));
            }
        }
        return quantityString;
    }
}
