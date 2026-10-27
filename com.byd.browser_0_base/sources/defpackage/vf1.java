package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import java.text.NumberFormat;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.components.commerce.core.ShoppingService;

/* JADX INFO: compiled from: MerchantTrustMessageViewModel.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vf1 {

    /* JADX INFO: compiled from: MerchantTrustMessageViewModel.java */
    public interface a {
    }

    public static SpannableStringBuilder a(Context context, ShoppingService.MerchantInfo merchantInfo, int i) {
        boolean zM6bsIDpc;
        int i2;
        if (i == 0) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        NumberFormat integerInstance = NumberFormat.getIntegerInstance();
        integerInstance.setMaximumFractionDigits(1);
        int i3 = 2;
        if (i == 2 && merchantInfo.b > 0) {
            Resources resources = context.getResources();
            int i4 = R$plurals.merchant_viewer_message_description_reviews_from_google;
            int i5 = merchantInfo.b;
            spannableStringBuilder.append((CharSequence) resources.getQuantityString(i4, i5, integerInstance.format(i5)));
            return spannableStringBuilder;
        }
        double dRound = ((double) Math.round(merchantInfo.a * 10.0f)) / 10.0d;
        NumberFormat integerInstance2 = NumberFormat.getIntegerInstance();
        integerInstance2.setMaximumFractionDigits(1);
        integerInstance2.setMinimumFractionDigits(1);
        if (a8.P()) {
            sk skVar = bq.a;
            zM6bsIDpc = N.M6bsIDpc("CommerceMerchantViewer", "trust_signals_message_use_rating_bar", true);
        } else {
            zM6bsIDpc = true;
        }
        if (zM6bsIDpc) {
            spannableStringBuilder.append((CharSequence) integerInstance2.format(dRound));
            spannableStringBuilder.append((CharSequence) " ");
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
            int iFloor = (int) Math.floor(dRound);
            int iCeil = (int) Math.ceil(dRound);
            int i6 = 0;
            while (i6 < iFloor) {
                spannableStringBuilder2.append((CharSequence) " ");
                z82 z82Var = new z82(context, i3);
                int i7 = i6 + 1;
                spannableStringBuilder2.setSpan(z82Var, i6, i7, 33);
                i6 = i7;
                i3 = 2;
            }
            if (dRound - ((double) iFloor) > 0.0d) {
                spannableStringBuilder2.append((CharSequence) " ");
                i2 = 33;
                spannableStringBuilder2.setSpan(new z82(context, 1), iFloor, iFloor + 1, 33);
            } else {
                i2 = 33;
            }
            while (iCeil < 5) {
                spannableStringBuilder2.append((CharSequence) " ");
                int i8 = iCeil + 1;
                spannableStringBuilder2.setSpan(new z82(context, 0), iCeil, i8, i2);
                iCeil = i8;
            }
            spannableStringBuilder.append((CharSequence) spannableStringBuilder2);
        } else {
            spannableStringBuilder.append((CharSequence) context.getResources().getString(R$string.merchant_viewer_message_description_rating, integerInstance.format(dRound), integerInstance.format(5L)));
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, spannableStringBuilder.length(), 17);
        }
        spannableStringBuilder.append((CharSequence) " ");
        int i9 = merchantInfo.b;
        if (i9 > 0) {
            spannableStringBuilder.append((CharSequence) context.getResources().getQuantityString(R$plurals.merchant_viewer_message_description_reviews, i9, integerInstance.format(i9)));
        } else {
            spannableStringBuilder.append((CharSequence) context.getResources().getString(R$string.page_info_store_info_description_with_no_review));
        }
        return spannableStringBuilder;
    }
}
