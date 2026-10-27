package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: AllPasswordsBottomSheetView.java */
/* JADX INFO: loaded from: classes.dex */
public final class k4 implements ii {
    public final ji e;
    public Callback<Integer> f;
    public final RecyclerView g;
    public final LinearLayout h;
    public final a i = new a();

    /* JADX INFO: compiled from: AllPasswordsBottomSheetView.java */
    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void c(int i) {
            k4 k4Var = k4.this;
            k4Var.f.onResult(Integer.valueOf(i));
            k4Var.e.e(k4Var.i);
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            if (i != 0) {
                return;
            }
            k4 k4Var = k4.this;
            k4Var.f.onResult(0);
            k4Var.e.e(k4Var.i);
        }
    }

    public k4(Context context, ji jiVar) {
        this.e = jiVar;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.all_passwords_bottom_sheet, (ViewGroup) null);
        this.h = linearLayout;
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(R$id.sheet_item_list);
        this.g = recyclerView;
        recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.setItemAnimator(null);
        if (qx1.c()) {
            ((TextView) linearLayout.findViewById(R$id.sheet_title)).setText(R$string.all_passwords_bottom_sheet_title_gpm);
        }
    }

    @Override // defpackage.ii
    public final View d() {
        return this.h;
    }

    @Override // defpackage.ii
    public final void destroy() {
        this.e.e(this.i);
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final boolean f() {
        return false;
    }

    @Override // defpackage.ii
    public final View g() {
        return null;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.all_passwords_bottom_sheet_half_height;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.all_passwords_bottom_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final int m() {
        return this.g.computeVerticalScrollOffset();
    }

    @Override // defpackage.ii
    public final float n() {
        return -2.0f;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.all_passwords_bottom_sheet_content_description;
    }

    @Override // defpackage.ii
    public final boolean q() {
        return false;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.all_passwords_bottom_sheet_full_height;
    }

    @Override // defpackage.ii
    public final boolean t() {
        return false;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return false;
    }
}
