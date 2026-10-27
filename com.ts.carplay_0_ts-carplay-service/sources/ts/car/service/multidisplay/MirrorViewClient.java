package ts.car.service.multidisplay;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public class MirrorViewClient extends TextureView {
    public MirrorViewClient(Context context) {
        super(context);
    }

    public MirrorViewClient(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MirrorViewClient(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MirrorViewClient(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setExitView(View view, WindowManager.LayoutParams params) {
    }

    public void exit() {
    }

    public int getdisplayId() {
        return 0;
    }

    public int getHostDisplayId() {
        return 0;
    }

    public void setDisplayId(int displayId) {
    }

    public void setHostDisplayId(int displayId) {
    }

    public void startMirrorApp(Intent intent) {
    }

    public void startMirrorDisplay(int displayId) throws RemoteException {
    }
}
