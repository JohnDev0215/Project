package org.chromium.chrome.browser.autofill;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bq;
import defpackage.c3;
import defpackage.gp;
import defpackage.iq2;
import defpackage.sk;
import defpackage.un1;
import defpackage.xm1;
import java.util.Calendar;
import java.util.LinkedList;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.g;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AutofillUiUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: compiled from: AutofillUiUtils.java */
    public class a extends ClickableSpan {
        public final /* synthetic */ Callback e;
        public final /* synthetic */ g.a f;

        public a(Callback callback, g.a aVar) {
            this.e = callback;
            this.f = aVar;
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(View view) {
            this.e.onResult(this.f.c);
        }
    }

    public static GURL a(GURL gurl, int i, int i2) {
        if (gurl.j().equals("https://www.gstatic.com/autofill/virtualcard/icon/capitalone.png")) {
            return gurl;
        }
        return new GURL(gurl.j() + "=w" + i + "-h" + i2 + "-n");
    }

    public static Drawable b(Context context, PersonalDataManager.CreditCard creditCard, int i, int i2) {
        sk skVar = bq.a;
        if (N.M09VlOh_("AutofillEnableCardArtImage") && creditCard.getCardArtUrl() != null && creditCard.getCardArtUrl().b) {
            Resources resources = context.getResources();
            Bitmap bitmapB = PersonalDataManager.c().b(a(creditCard.getCardArtUrl(), resources.getDimensionPixelSize(i), resources.getDimensionPixelSize(i2)));
            if (bitmapB != null) {
                return new BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmapB, resources.getDimensionPixelSize(i), resources.getDimensionPixelSize(i2), true));
            }
        }
        return AppCompatResources.a(context, creditCard.k);
    }

    public static int c(EditText editText, EditText editText2, boolean z, boolean z2) {
        int i;
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(1);
        int i3 = calendar.get(2) + 1;
        try {
            i = Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException unused) {
        }
        if (i < 1 || i > 12) {
            i = -1;
        }
        if (i == -1) {
            if (editText.getText().length() == 2 || (!editText.isFocused() && z)) {
                return 1;
            }
            if (!z2) {
                return 6;
            }
        }
        int iD = d(editText2);
        if (iD == -1) {
            return (editText2.getText().length() == 2 || (!editText2.isFocused() && z2)) ? 2 : 6;
        }
        if (i == -1) {
            return 6;
        }
        return (iD != i2 || i >= i3) ? 7 : 3;
    }

    public static int d(EditText editText) {
        int i = Calendar.getInstance().get(1);
        try {
            int i2 = Integer.parseInt(editText.getText().toString());
            if (i2 < 0) {
                return -1;
            }
            if (i2 < 100) {
                i2 += i - (i % 100);
            }
            if (i2 >= i && i2 <= i + 10) {
                return i2;
            }
        } catch (NumberFormatException unused) {
        }
        return -1;
    }

    public static SpannableStringBuilder e(Context context, LinkedList<g> linkedList, boolean z, Callback<String> callback) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (g gVar : linkedList) {
            SpannableString spannableString = new SpannableString(gVar.a);
            for (g.a aVar : gVar.b) {
                if (z) {
                    spannableString.setSpan(new a(callback, aVar), aVar.a, aVar.b, 17);
                } else {
                    spannableString.setSpan(new un1(context, new gp(callback, 2, aVar)), aVar.a, aVar.b, 17);
                }
            }
            spannableStringBuilder.append((CharSequence) spannableString);
        }
        return spannableStringBuilder;
    }

    public static SpannableString f(Context context, int i, c3 c3Var) {
        return iq2.a(context.getString(i), new iq2.a(new un1(context, new xm1(c3Var, 1, "https://support.google.com/googlepay/answer/11234179")), "<link1>", "</link1>"));
    }

    public static void g(int i, Context context, TextView textView) {
        if (i == 6 || i == 7) {
            textView.setText((CharSequence) null);
            textView.setVisibility(8);
            return;
        }
        Resources resources = context.getResources();
        String string = i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : resources.getString(R$string.autofill_card_unmask_prompt_error_try_again_cvc_and_expiration) : resources.getString(R$string.autofill_card_unmask_prompt_error_try_again_cvc) : resources.getString(R$string.autofill_card_unmask_prompt_error_try_again_expiration_date) : resources.getString(R$string.autofill_card_unmask_prompt_error_try_again_expiration_year) : resources.getString(R$string.autofill_card_unmask_prompt_error_try_again_expiration_month);
        textView.setText(string);
        textView.setVisibility(0);
        textView.announceForAccessibility(string);
    }

    public static void h(int i, Context context, EditText editText, EditText editText2, EditText editText3) {
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(context.getColor(R$color.input_underline_error_color), PorterDuff.Mode.SRC_IN);
        boolean z = i == 1 || i == 3 || i == 5;
        boolean z2 = i == 2 || i == 3 || i == 5;
        editText.getBackground().mutate().setColorFilter(z ? porterDuffColorFilter : null);
        editText2.getBackground().mutate().setColorFilter(z2 ? porterDuffColorFilter : null);
        if (editText3 != null) {
            if (!(i == 4 || i == 5)) {
                porterDuffColorFilter = null;
            }
            editText3.getBackground().mutate().setColorFilter(porterDuffColorFilter);
        }
    }
}
