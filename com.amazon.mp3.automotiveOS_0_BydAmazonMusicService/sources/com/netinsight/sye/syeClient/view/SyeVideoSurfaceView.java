package com.netinsight.sye.syeClient.view;

import a.b.a.a.n.b;
import a.b.a.a.o.e.b;
import a.b.a.a.o.e.c;
import a.b.a.a.o.e.h;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B'\b\u0007\u0012\u0006\u0010?\u001a\u00020>\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010@\u0012\b\b\u0002\u0010B\u001a\u00020\u0004¢\u0006\u0004\bC\u0010DJ\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\f\u0010\tJ\u000f\u0010\r\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001a\u0010\u0016J\u000f\u0010\u001b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001b\u0010\u000eR\u0016\u0010\u001f\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\"\u001a\u00020 8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010!R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R*\u0010-\u001a\u00020%2\u0006\u0010&\u001a\u00020%8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0016\u0010/\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010\u001eR\u0016\u00103\u001a\u0002008V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0016\u00105\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u0010\u001eR\u0016\u00109\u001a\u0002068V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0016\u0010=\u001a\u00020:8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<¨\u0006E"}, d2 = {"Lcom/netinsight/sye/syeClient/view/SyeVideoSurfaceView;", "Landroid/view/SurfaceView;", "La/b/a/a/o/e/b;", "Landroid/view/SurfaceHolder$Callback;", "", "width", "height", "", "onVideoStreamSizeChanged", "(II)V", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "teardown", "()V", "La/b/a/a/o/e/b$a;", "surfaceReceiver", "setSurfaceReceiver", "(La/b/a/a/o/e/b$a;)V", "Landroid/view/SurfaceHolder;", "holder", "surfaceCreated", "(Landroid/view/SurfaceHolder;)V", "format", "surfaceChanged", "(Landroid/view/SurfaceHolder;III)V", "surfaceDestroyed", "a", "La/b/a/a/o/e/h;", "d", "La/b/a/a/o/e/h;", "streamResolution", "", "Ljava/lang/String;", "TAG", "c", "La/b/a/a/o/e/b$a;", "Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;", "value", "g", "Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;", "getDisplaySettings", "()Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;", "setDisplaySettings", "(Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;)V", "displaySettings", "e", "layoutResolution", "Landroid/view/View;", "getContainingView", "()Landroid/view/View;", "containingView", "f", "surfaceResolution", "La/b/a/a/o/e/c;", "getResolution", "()La/b/a/a/o/e/c;", "resolution", "La/b/a/a/i/b;", "b", "La/b/a/a/i/b;", "Log", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeVideoSurfaceView extends SurfaceView implements b, SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final String TAG;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final a.b.a.a.i.b Log;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public b.a surfaceReceiver;

    /* JADX INFO: renamed from: d, reason: from kotlin metadata */
    public final h streamResolution;

    /* JADX INFO: renamed from: e, reason: from kotlin metadata */
    public final h layoutResolution;

    /* JADX INFO: renamed from: f, reason: from kotlin metadata */
    public final h surfaceResolution;

    /* JADX INFO: renamed from: g, reason: from kotlin metadata */
    public ISyeDisplaySettings displaySettings;

    @JvmOverloads
    public SyeVideoSurfaceView(Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public SyeVideoSurfaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    @JvmOverloads
    public SyeVideoSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        String name = SyeVideoSurfaceView.class.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "SyeVideoSurfaceView::class.java.name");
        this.TAG = name;
        this.Log = a.b.a.a.i.b.d.a(name, b.a.Video);
        this.streamResolution = new h();
        this.layoutResolution = new h();
        this.surfaceResolution = new h();
        this.displaySettings = new SyeDisplaySettings();
        getHolder().addCallback(this);
    }

    public /* synthetic */ SyeVideoSurfaceView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void a() {
        Handler handler = getHandler();
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.netinsight.sye.syeClient.view.SyeVideoSurfaceView$updateLayout$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f776a.invalidate();
                    this.f776a.requestLayout();
                }
            });
        }
    }

    @Override // a.b.a.a.o.e.b
    public View getContainingView() {
        return this;
    }

    @Override // a.b.a.a.o.e.b
    public ISyeDisplaySettings getDisplaySettings() {
        return this.displaySettings;
    }

    @Override // a.b.a.a.o.e.b
    public c getResolution() {
        return new h(this.surfaceResolution);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    @Override // android.view.SurfaceView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r16, int r17) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netinsight.sye.syeClient.view.SyeVideoSurfaceView.onMeasure(int, int):void");
    }

    @Override // a.b.a.a.o.e.b
    public void onVideoStreamSizeChanged(int width, int height) {
        this.Log.a(this.TAG, "onVideoStreamSizeChanged = " + width + 'x' + height);
        h hVar = this.streamResolution;
        synchronized (hVar) {
            hVar.f139a = width;
            hVar.b = height;
        }
        a();
    }

    @Override // a.b.a.a.o.e.b
    public void setDisplaySettings(ISyeDisplaySettings iSyeDisplaySettings) {
        this.displaySettings = iSyeDisplaySettings;
        b.a aVar = this.surfaceReceiver;
        if (aVar != null) {
            aVar.a(getDisplaySettings());
        }
        a();
    }

    @Override // a.b.a.a.o.e.b
    public synchronized void setSurfaceReceiver(b.a surfaceReceiver) {
        this.surfaceReceiver = surfaceReceiver;
        if (surfaceReceiver != null) {
            SurfaceHolder holder = getHolder();
            Intrinsics.checkExpressionValueIsNotNull(holder, "holder");
            Surface surface = holder.getSurface();
            if (surface == null || !surface.isValid()) {
                a.b.a.a.i.b bVar = this.Log;
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("getAvailable surface first returns ");
                sb.append(surface != null ? Boolean.valueOf(surface.isValid()) : "null");
                bVar.a(str, sb.toString());
            } else {
                this.Log.a(this.TAG, "getAvailable surface ok");
                surfaceReceiver.a(surface);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0013  */
    @Override // android.view.SurfaceHolder.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void surfaceChanged(android.view.SurfaceHolder r5, int r6, int r7, int r8) {
        /*
            r4 = this;
            a.b.a.a.o.e.h r5 = r4.surfaceResolution
            monitor-enter(r5)
            int r6 = r5.f139a     // Catch: java.lang.Throwable -> L84
            monitor-exit(r5)
            if (r6 != r7) goto L13
            monitor-enter(r5)
            int r6 = r5.b     // Catch: java.lang.Throwable -> L10
            monitor-exit(r5)
            if (r6 != r8) goto L13
            r5 = 1
            goto L14
        L10:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L13:
            r5 = 0
        L14:
            r6 = 120(0x78, float:1.68E-43)
            if (r5 != 0) goto L65
            a.b.a.a.i.b r5 = r4.Log
            java.lang.String r0 = r4.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "surfaceChanged "
            r1.append(r2)
            r1.append(r7)
            r1.append(r6)
            r1.append(r8)
            java.lang.String r2 = " need update was="
            r1.append(r2)
            a.b.a.a.o.e.h r2 = r4.surfaceResolution
            monitor-enter(r2)
            int r3 = r2.f139a     // Catch: java.lang.Throwable -> L62
            monitor-exit(r2)
            r1.append(r3)
            r1.append(r6)
            a.b.a.a.o.e.h r6 = r4.surfaceResolution
            monitor-enter(r6)
            int r2 = r6.b     // Catch: java.lang.Throwable -> L5f
            monitor-exit(r6)
            r1.append(r2)
            java.lang.String r6 = r1.toString()
            r5.a(r0, r6)
            a.b.a.a.o.e.h r5 = r4.layoutResolution
            monitor-enter(r5)
            r5.f139a = r7     // Catch: java.lang.Throwable -> L5c
            r5.b = r8     // Catch: java.lang.Throwable -> L5c
            monitor-exit(r5)
            r4.a()
            goto L83
        L5c:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L5f:
            r5 = move-exception
            monitor-exit(r6)
            throw r5
        L62:
            r5 = move-exception
            monitor-exit(r2)
            throw r5
        L65:
            a.b.a.a.i.b r5 = r4.Log
            java.lang.String r0 = r4.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "surfaceChanged no update "
            r1.append(r2)
            r1.append(r7)
            r1.append(r6)
            r1.append(r8)
            java.lang.String r6 = r1.toString()
            r5.a(r0, r6)
        L83:
            return
        L84:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netinsight.sye.syeClient.view.SyeVideoSurfaceView.surfaceChanged(android.view.SurfaceHolder, int, int, int):void");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);
        h hVar = this.layoutResolution;
        int width = getWidth();
        int height = getHeight();
        synchronized (hVar) {
            hVar.f139a = width;
            hVar.b = height;
        }
        h hVar2 = this.surfaceResolution;
        int width2 = getWidth();
        int height2 = getHeight();
        synchronized (hVar2) {
            hVar2.f139a = width2;
            hVar2.b = height2;
        }
        b.a aVar = this.surfaceReceiver;
        if (aVar != null) {
            Surface surface = holder.getSurface();
            Intrinsics.checkExpressionValueIsNotNull(surface, "holder.surface");
            aVar.a(surface);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public synchronized void surfaceDestroyed(SurfaceHolder holder) {
        b.a aVar = this.surfaceReceiver;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // a.b.a.a.o.e.b
    public synchronized void teardown() {
        getHolder().removeCallback(this);
    }
}
