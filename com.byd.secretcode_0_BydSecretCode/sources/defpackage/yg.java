package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.secretcode.R;

/* JADX INFO: compiled from: BaseNetworkHolder.java */
/* JADX INFO: loaded from: classes.dex */
public class yg extends RecyclerView.c0 {
    public ImageView t;
    public TextView u;
    public TextView v;
    public RelativeLayout w;

    public yg(View view) {
        super(view);
        this.t = (ImageView) view.findViewById(R.id.network_signal_level_iv);
        this.u = (TextView) view.findViewById(R.id.network_signal_name_tv);
        this.v = (TextView) view.findViewById(R.id.network_signal_state_tv);
        this.w = (RelativeLayout) view.findViewById(R.id.network_signal_message_container);
    }
}
