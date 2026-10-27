package spi;

import android.app.PendingIntent;
import android.util.Log;
import android.view.View;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class u0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ PendingIntent b;

    public /* synthetic */ u0(PendingIntent pendingIntent, int i) {
        this.a = i;
        this.b = pendingIntent;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                try {
                    this.b.send();
                } catch (PendingIntent.CanceledException unused) {
                    if (Log.isLoggable("PlaybckErrViewCtrlr", 6)) {
                        Log.e("PlaybckErrViewCtrlr", "Pending intent canceled");
                    }
                    return;
                }
                break;
            default:
                try {
                    this.b.send();
                } catch (PendingIntent.CanceledException unused2) {
                    if (Log.isLoggable("PlaybckErrViewCtrlr", 6)) {
                        Log.e("PlaybckErrViewCtrlr", "Pending intent canceled");
                        return;
                    }
                    return;
                }
                break;
        }
    }
}
