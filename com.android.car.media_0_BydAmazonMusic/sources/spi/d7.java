package spi;

import android.car.media.XCarMediaManager;
import android.util.Log;
import com.android.car.media.common.source.MediaSourceViewModel;

/* JADX INFO: compiled from: MediaSourceViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class d7 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ MediaSourceViewModel b;

    public d7(MediaSourceViewModel mediaSourceViewModel, int i) {
        this.b = mediaSourceViewModel;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            b7 value = this.b.c.getValue();
            if (this.b.g == this.a && value != null) {
                Log.d("MediaSourceViewModel", "delay reConnectMediaSource: " + this.a);
                this.b.i.b(value);
            }
            XCarMediaManager xCarMediaManager = this.b.b;
            if (xCarMediaManager == null || value == null) {
                return;
            }
            xCarMediaManager.setMediaSource(value.a, this.a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
