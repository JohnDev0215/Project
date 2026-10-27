package defpackage;

import android.R;
import android.content.res.TypedArray;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/* JADX INFO: compiled from: BottomSheetDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class li implements View.OnClickListener {
    public final /* synthetic */ BottomSheetDialog e;

    public li(BottomSheetDialog bottomSheetDialog) {
        this.e = bottomSheetDialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        BottomSheetDialog bottomSheetDialog = this.e;
        if (bottomSheetDialog.n && bottomSheetDialog.isShowing()) {
            if (!bottomSheetDialog.p) {
                TypedArray typedArrayObtainStyledAttributes = bottomSheetDialog.getContext().obtainStyledAttributes(new int[]{R.attr.windowCloseOnTouchOutside});
                bottomSheetDialog.o = typedArrayObtainStyledAttributes.getBoolean(0, true);
                typedArrayObtainStyledAttributes.recycle();
                bottomSheetDialog.p = true;
            }
            if (bottomSheetDialog.o) {
                bottomSheetDialog.cancel();
            }
        }
    }
}
