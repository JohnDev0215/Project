package spi;

import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import androidx.window.layout.SidecarWindowBackend;
import androidx.window.layout.WindowLayoutInfo;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class jn implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ jn(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((ResourcesCompat.FontCallback) this.b).lambda$callbackSuccessAsync$0((Typeface) this.c);
                break;
            default:
                SidecarWindowBackend.WindowLayoutChangeCallbackWrapper.m54accept$lambda0((SidecarWindowBackend.WindowLayoutChangeCallbackWrapper) this.b, (WindowLayoutInfo) this.c);
                break;
        }
    }
}
