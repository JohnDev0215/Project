package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: TouchToFillCreditCardView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ie3 extends ne3 {
    public final ji j;
    public final RecyclerView k;

    /* JADX INFO: compiled from: TouchToFillCreditCardView.java */
    public static class a extends b31 {
        public a(Context context) {
            super(context);
        }

        @Override // defpackage.b31
        public final boolean j(RecyclerView recyclerView) {
            int iK = recyclerView.getAdapter().k();
            return iK > 1 && recyclerView.getAdapter().m(iK - 2) == 2;
        }

        @Override // defpackage.b31
        public final int k(int i, int i2, boolean z) {
            return z ? R$drawable.touch_to_fill_credential_background_modern_rounded_all : i == i2 - 1 ? R$drawable.touch_to_fill_credential_background_modern_rounded_down : i == 1 ? R$drawable.touch_to_fill_credential_background_modern_rounded_up : R$drawable.touch_to_fill_credential_background_modern;
        }

        @Override // defpackage.b31
        public final boolean l(int i) {
            return i == 0 || i != 1;
        }
    }

    public ie3(Context context, ji jiVar) {
        super(jiVar, (RelativeLayout) LayoutInflater.from(context).inflate(R$layout.touch_to_fill_sheet, (ViewGroup) null));
        this.j = jiVar;
        RecyclerView recyclerViewX = x();
        this.k = recyclerViewX;
        recyclerViewX.g(new a(context));
    }

    @Override // defpackage.ne3
    public final int A() {
        return 1;
    }

    @Override // defpackage.ne3
    public final int b() {
        return 3;
    }

    @Override // defpackage.ne3
    public final int c() {
        return this.f.getResources().getDimensionPixelSize(R$dimen.ttf_sheet_padding);
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.autofill_credit_card_bottom_sheet_half_height;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.autofill_credit_card_bottom_sheet_closed;
    }

    @Override // defpackage.ii
    public final int m() {
        return this.k.computeVerticalScrollOffset();
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.autofill_credit_card_bottom_sheet_content_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.autofill_credit_card_bottom_sheet_full_height;
    }

    @Override // defpackage.ne3
    public final View v() {
        return this.f.findViewById(R$id.drag_handlebar);
    }

    @Override // defpackage.ne3
    public final RecyclerView x() {
        return (RecyclerView) this.f.findViewById(R$id.sheet_item_list);
    }

    @Override // defpackage.ne3
    public final int z() {
        return this.f.getResources().getDimensionPixelSize(R$dimen.ttf_sheet_padding);
    }
}
