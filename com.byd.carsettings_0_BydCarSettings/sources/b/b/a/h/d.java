package b.b.a.h;

import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.BindingAdapter;
import com.byd.base.view.BydSwitch;

/* JADX INFO: compiled from: DataBindUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    @BindingAdapter({"android:checked"})
    public static void a(BydSwitch bydSwitch, boolean z) {
        if (bydSwitch.isChecked() == z) {
            return;
        }
        bydSwitch.setChecked(z, false);
    }

    @BindingAdapter({"android:layout_width"})
    public static void b(View view, float f2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) f2;
        view.setLayoutParams(layoutParams);
    }
}
