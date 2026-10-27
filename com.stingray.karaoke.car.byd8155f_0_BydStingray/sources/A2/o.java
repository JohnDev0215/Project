package A2;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class o extends GLSurfaceView {
    public final CopyOnWriteArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SensorManager f356m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Sensor f357n;
    public final e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Handler f358p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final k f359q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public SurfaceTexture f360r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Surface f361s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f362t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f363u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f364v;

    public o(Context context) {
        super(context, null);
        this.l = new CopyOnWriteArrayList();
        this.f358p = new Handler(Looper.getMainLooper());
        Object systemService = context.getSystemService("sensor");
        systemService.getClass();
        SensorManager sensorManager = (SensorManager) systemService;
        this.f356m = sensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(15);
        this.f357n = defaultSensor == null ? sensorManager.getDefaultSensor(11) : defaultSensor;
        k kVar = new k();
        this.f359q = kVar;
        n nVar = new n(this, kVar);
        View.OnTouchListener pVar = new p(context, nVar);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getClass();
        this.o = new e(windowManager.getDefaultDisplay(), pVar, nVar);
        this.f362t = true;
        setEGLContextClientVersion(2);
        setRenderer(nVar);
        setOnTouchListener(pVar);
    }

    public final void a() {
        boolean z6 = this.f362t && this.f363u;
        Sensor sensor = this.f357n;
        if (sensor == null || z6 == this.f364v) {
            return;
        }
        e eVar = this.o;
        SensorManager sensorManager = this.f356m;
        if (z6) {
            sensorManager.registerListener(eVar, sensor, 0);
        } else {
            sensorManager.unregisterListener(eVar);
        }
        this.f364v = z6;
    }

    public a getCameraMotionListener() {
        return this.f359q;
    }

    public z2.n getVideoFrameMetadataListener() {
        return this.f359q;
    }

    public Surface getVideoSurface() {
        return this.f361s;
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f358p.post(new l(this, 0));
    }

    @Override // android.opengl.GLSurfaceView
    public final void onPause() {
        this.f363u = false;
        a();
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public final void onResume() {
        super.onResume();
        this.f363u = true;
        a();
    }

    public void setDefaultStereoMode(int i7) {
        this.f359q.f341v = i7;
    }

    public void setUseSensorRotation(boolean z6) {
        this.f362t = z6;
        a();
    }
}
