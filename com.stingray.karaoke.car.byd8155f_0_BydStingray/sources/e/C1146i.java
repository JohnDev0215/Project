package e;

import B5.H;
import android.os.HandlerThread;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1146i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f13665a = new ArrayList();

    public C1146i() {
        HandlerThread handlerThread = new HandlerThread("TestGearboxManagerImplCallback");
        handlerThread.start();
        new H(this, handlerThread.getLooper(), 9);
    }
}
