package i5;

import android.os.Handler;
import android.os.Message;
import com.byd.uereport.widget.DelayDismissTextView;

/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class a implements Handler.Callback {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ DelayDismissTextView f5503d;

    public /* synthetic */ a(DelayDismissTextView delayDismissTextView) {
        this.f5503d = delayDismissTextView;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        return this.f5503d.g(message);
    }
}
