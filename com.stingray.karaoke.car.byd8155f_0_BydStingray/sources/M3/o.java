package m3;

import android.view.View;
import android.widget.TextView;
import com.stingray.drivemodedetector.R;
import h2.AbstractC1343A;
import s3.U;

/* JADX INFO: loaded from: classes.dex */
public final class o extends U {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final TextView f19071u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final View f19072v;

    public o(View view) {
        super(view);
        if (AbstractC1343A.f15195a < 26) {
            view.setFocusable(true);
        }
        this.f19071u = (TextView) view.findViewById(R.id.exo_text);
        this.f19072v = view.findViewById(R.id.exo_check);
    }
}
