package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.secretcode.R;

/* JADX INFO: compiled from: NetworkHolder.java */
/* JADX INFO: loaded from: classes.dex */
public class ah extends yg {
    public ah(View view) {
        super(view);
        this.t = (ImageView) view.findViewById(R.id.network_signal_level_iv);
        this.u = (TextView) view.findViewById(R.id.network_signal_name_tv);
        this.v = (TextView) view.findViewById(R.id.network_signal_state_tv);
        this.w = (RelativeLayout) view.findViewById(R.id.network_signal_message_container);
    }
}
