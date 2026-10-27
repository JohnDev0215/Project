package defpackage;

import android.view.View;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: CustomButtonParamsImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class n20 implements View.OnLongClickListener {
    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = view.getResources().getDisplayMetrics().widthPixels;
        int i2 = view.getResources().getDisplayMetrics().heightPixels;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int width = view.getWidth();
        Toast toastB = Toast.b(0, view.getContext(), view.getContentDescription());
        toastB.d(8388693, (i - iArr[0]) - (width / 2), i2 - iArr[1]);
        toastB.e();
        return true;
    }
}
