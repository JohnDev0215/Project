package defpackage;

import android.os.Looper;
import android.os.Message;
import java.util.Objects;
import org.chromium.device.geolocation.LocationProviderAdapter;

/* JADX INFO: renamed from: fd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerC0779fd extends HandlerC0347Tg {
    public final /* synthetic */ C0835gd a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0779fd(C0835gd c0835gd, Looper looper) {
        super(looper);
        this.a = c0835gd;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Cif.a(message.what == 1);
        InterfaceC0723ed interfaceC0723ed = (InterfaceC0723ed) message.obj;
        Object obj = this.a.b;
        if (obj == null) {
            Objects.requireNonNull((C0152Ii) interfaceC0723ed);
            return;
        }
        try {
            C0152Ii c0152Ii = (C0152Ii) interfaceC0723ed;
            Objects.requireNonNull(c0152Ii);
            LocationProviderAdapter.b(c0152Ii.a);
        } catch (RuntimeException e) {
            Objects.requireNonNull((C0152Ii) interfaceC0723ed);
            throw e;
        }
    }
}
