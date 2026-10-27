package a.b.a.a.o.e;

import a.b.a.a.n.b;
import a.b.a.a.o.e.b;
import a.b.a.a.o.g.b;
import android.os.Handler;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class f implements b, SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f133a = 1;
    public final String b;
    public final a.b.a.a.i.b c;
    public b.a d;
    public final h e;
    public ISyeDisplaySettings f;
    public final SurfaceView g;

    public static final class a implements Runnable {
        public final /* synthetic */ ISyeDisplaySettings.VideoGravity b;

        public a(ISyeDisplaySettings.VideoGravity videoGravity) {
            this.b = videoGravity;
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0120  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instruction units count: 497
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.o.e.f.a.run():void");
        }
    }

    public f(SurfaceView surfaceView, ISyeDisplaySettings iSyeDisplaySettings) {
        this.g = surfaceView;
        StringBuilder sb = new StringBuilder();
        sb.append("SurfaceViewDisplaySurf_");
        int i = f133a;
        f133a = i + 1;
        sb.append(i);
        String string = sb.toString();
        this.b = string;
        this.c = a.b.a.a.i.b.d.a(string, b.a.Video);
        this.e = new h();
        this.f = iSyeDisplaySettings;
        surfaceView.getHolder().addCallback(this);
        SurfaceHolder holder = surfaceView.getHolder();
        Intrinsics.checkExpressionValueIsNotNull(holder, "surfaceView.holder");
        if (holder.getSurface() != null) {
            SurfaceHolder holder2 = surfaceView.getHolder();
            Intrinsics.checkExpressionValueIsNotNull(holder2, "surfaceView.holder");
            Surface surface = holder2.getSurface();
            Intrinsics.checkExpressionValueIsNotNull(surface, "surfaceView.holder.surface");
            if (surface.isValid()) {
                surfaceView.setWillNotDraw(false);
            }
        }
    }

    public final void a(ISyeDisplaySettings.VideoGravity videoGravity) {
        Handler handler = this.g.getHandler();
        if (handler != null) {
            handler.post(new a(videoGravity));
        }
    }

    @Override // a.b.a.a.o.e.b
    public View getContainingView() {
        return this.g;
    }

    @Override // a.b.a.a.o.e.b
    public ISyeDisplaySettings getDisplaySettings() {
        return this.f;
    }

    @Override // a.b.a.a.o.e.b
    public c getResolution() {
        return new h(this.g.getWidth(), this.g.getHeight());
    }

    @Override // a.b.a.a.o.e.b
    public void onVideoStreamSizeChanged(int i, int i2) {
        this.c.a(this.b, "onVideoStreamSizeChanged: " + i + " x " + i2);
        h hVar = this.e;
        synchronized (hVar) {
            hVar.f139a = i;
            hVar.b = i2;
        }
        a(this.f.getGravity());
    }

    @Override // a.b.a.a.o.e.b
    public void setDisplaySettings(ISyeDisplaySettings iSyeDisplaySettings) {
        this.f = iSyeDisplaySettings;
        a(iSyeDisplaySettings.getGravity());
        b.a aVar = this.d;
        if (aVar != null) {
            aVar.a(this.f);
        }
    }

    @Override // a.b.a.a.o.e.b
    public synchronized void setSurfaceReceiver(b.a aVar) {
        this.d = aVar;
        if (aVar != null) {
            SurfaceHolder holder = this.g.getHolder();
            Intrinsics.checkExpressionValueIsNotNull(holder, "surfaceView.holder");
            Surface surface = holder.getSurface();
            if (surface == null || !surface.isValid()) {
                a.b.a.a.i.b bVar = this.c;
                String str = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append("getAvailable surface first returns ");
                sb.append(surface != null ? Boolean.valueOf(surface.isValid()) : "null");
                bVar.a(str, sb.toString());
            } else {
                this.c.a(this.b, "getAvailable surface ok");
                ((b.a) aVar).a(surface);
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        a(this.f.getGravity());
    }

    @Override // android.view.SurfaceHolder.Callback
    public synchronized void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.g.setWillNotDraw(false);
        b.a aVar = this.d;
        if (aVar != null) {
            Surface surface = surfaceHolder.getSurface();
            Intrinsics.checkExpressionValueIsNotNull(surface, "holder.surface");
            aVar.a(surface);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public synchronized void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        b.a aVar = this.d;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // a.b.a.a.o.e.b
    public synchronized void teardown() {
        this.g.getHolder().removeCallback(this);
    }
}
