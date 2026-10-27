package m3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.stingray.drivemodedetector.R;
import h2.AbstractC1343A;
import s3.U;

/* JADX INFO: renamed from: m3.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1813m extends U {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final TextView f19064u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final TextView f19065v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ImageView f19066w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ s f19067x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1813m(s sVar, View view) {
        super(view);
        this.f19067x = sVar;
        if (AbstractC1343A.f15195a < 26) {
            view.setFocusable(true);
        }
        this.f19064u = (TextView) view.findViewById(R.id.exo_main_text);
        this.f19065v = (TextView) view.findViewById(R.id.exo_sub_text);
        this.f19066w = (ImageView) view.findViewById(R.id.exo_icon);
        view.setOnClickListener(new K6.e(this, 3));
    }
}
