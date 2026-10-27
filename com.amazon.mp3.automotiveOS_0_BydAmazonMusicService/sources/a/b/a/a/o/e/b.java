package a.b.a.a.o.e;

import android.view.Surface;
import android.view.View;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;

/* JADX INFO: loaded from: classes.dex */
public interface b {

    public interface a {
        void a();

        void a(Surface surface);

        void a(ISyeDisplaySettings iSyeDisplaySettings);
    }

    View getContainingView();

    ISyeDisplaySettings getDisplaySettings();

    c getResolution();

    void onVideoStreamSizeChanged(int i, int i2);

    void setDisplaySettings(ISyeDisplaySettings iSyeDisplaySettings);

    void setSurfaceReceiver(a aVar);

    void teardown();
}
