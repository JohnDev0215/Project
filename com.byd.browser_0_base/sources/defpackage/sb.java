package defpackage;

import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.os.Handler;
import android.os.Looper;
import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sb implements Consumer {
    public final /* synthetic */ int a;

    public /* synthetic */ sb(int i) {
        this.a = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                ((IServiceConnectionListener) obj).onServiceDisconnected(false);
                break;
            default:
                new Handler(Looper.getMainLooper()).post(new ve3(26, new lu2((jo3) obj)));
                break;
        }
    }
}
