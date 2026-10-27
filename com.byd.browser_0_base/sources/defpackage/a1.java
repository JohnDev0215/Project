package defpackage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.ButtonCompat;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: compiled from: AccountPickerBottomSheetView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a1 implements ii {
    public static final int[] k;
    public final a e;
    public final View f;
    public final ViewFlipper g;
    public final RecyclerView h;
    public final View i;
    public final ButtonCompat j;

    /* JADX INFO: compiled from: AccountPickerBottomSheetView.java */
    public interface a {
    }

    static {
        int i = R$id.account_picker_header_title;
        k = new int[]{i, i, i, R$id.account_picker_signin_in_progress_title, R$id.account_picker_general_error_title, R$id.account_picker_auth_error_title};
    }

    public a1(Activity activity, C0219x0 c0219x0) {
        this.e = c0219x0;
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.account_picker_bottom_sheet_view, (ViewGroup) null);
        this.f = viewInflate;
        ViewFlipper viewFlipper = (ViewFlipper) viewInflate.findViewById(R$id.account_picker_state_view_flipper);
        this.g = viewFlipper;
        b(viewFlipper, 0, R$id.account_picker_state_no_account);
        b(viewFlipper, 1, R$id.account_picker_state_collapsed);
        b(viewFlipper, 2, R$id.account_picker_state_expanded);
        b(viewFlipper, 3, R$id.account_picker_state_signin_in_progress);
        b(viewFlipper, 4, R$id.account_picker_state_general_error);
        b(viewFlipper, 5, R$id.account_picker_state_auth_error);
        RecyclerView recyclerView = (RecyclerView) viewFlipper.getChildAt(2).findViewById(R$id.account_picker_account_list);
        this.h = recyclerView;
        recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        this.i = viewFlipper.getChildAt(1).findViewById(R$id.account_picker_selected_account);
        this.j = (ButtonCompat) viewFlipper.getChildAt(1).findViewById(R$id.account_picker_dismiss_button);
        View childAt = viewFlipper.getChildAt(0);
        int i = R$string.signin_add_account_to_device;
        int i2 = R$id.account_picker_continue_as_button;
        ((ButtonCompat) childAt.findViewById(i2)).setText(i);
        View childAt2 = viewFlipper.getChildAt(4);
        ((ButtonCompat) childAt2.findViewById(i2)).setText(R$string.signin_account_picker_general_error_button);
        View childAt3 = viewFlipper.getChildAt(5);
        ((ButtonCompat) childAt3.findViewById(i2)).setText(R$string.auth_error_card_button);
    }

    public static void b(ViewFlipper viewFlipper, int i, int i2) {
        if (viewFlipper.getChildAt(i).getId() != i2) {
            throw new IllegalArgumentException(gq2.e("Match failed with ViewState:", i));
        }
    }

    @Override // defpackage.ii
    public final void a() {
        C0219x0 c0219x0 = (C0219x0) this.e;
        PropertyModel.p pVar = C0221y0.e;
        PropertyModel propertyModel = c0219x0.i;
        if (propertyModel.h(pVar) == 2) {
            propertyModel.n(pVar, 1);
        }
    }

    @Override // defpackage.ii
    public final View d() {
        return this.f;
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final View g() {
        return null;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.account_picker_bottom_sheet_accessibility_opened;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.account_picker_bottom_sheet_accessibility_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final aq1<Boolean> l() {
        return ((C0219x0) this.e).o;
    }

    @Override // defpackage.ii
    public final int m() {
        return 0;
    }

    @Override // defpackage.ii
    public final boolean o() {
        C0219x0 c0219x0 = (C0219x0) this.e;
        PropertyModel.p pVar = C0221y0.e;
        PropertyModel propertyModel = c0219x0.i;
        if (propertyModel.h(pVar) != 2) {
            return false;
        }
        propertyModel.n(pVar, 1);
        return true;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.signin_account_picker_bottom_sheet_subtitle;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.account_picker_bottom_sheet_accessibility_opened;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    public void setOnClickListenerOfContinueButton(View.OnClickListener onClickListener) {
        int i = 0;
        while (true) {
            ViewFlipper viewFlipper = this.g;
            if (i >= viewFlipper.getChildCount()) {
                return;
            }
            ButtonCompat buttonCompat = (ButtonCompat) viewFlipper.getChildAt(i).findViewById(R$id.account_picker_continue_as_button);
            if (buttonCompat != null) {
                buttonCompat.setOnClickListener(onClickListener);
            }
            i++;
        }
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
