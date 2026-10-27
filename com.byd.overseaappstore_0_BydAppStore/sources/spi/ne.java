package spi;

import android.content.Context;
import androidx.activity.contextaware.OnContextAvailableListener;
import com.byd.overseaappstore.ui.Hilt_MainActivity;
import com.byd.overseaappstore.ui.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public final class ne implements OnContextAvailableListener {
    public final /* synthetic */ Hilt_MainActivity a;

    public ne(Hilt_MainActivity hilt_MainActivity) {
        this.a = hilt_MainActivity;
    }

    @Override // androidx.activity.contextaware.OnContextAvailableListener
    public final void onContextAvailable(Context context) {
        Hilt_MainActivity hilt_MainActivity = this.a;
        if (hilt_MainActivity.e) {
            return;
        }
        hilt_MainActivity.e = true;
        ((ph) hilt_MainActivity.a()).b((MainActivity) hilt_MainActivity);
    }
}
