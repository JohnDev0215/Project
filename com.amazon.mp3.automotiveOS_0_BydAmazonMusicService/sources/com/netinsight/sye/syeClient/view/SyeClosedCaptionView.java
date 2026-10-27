package com.netinsight.sye.syeClient.view;

import a.b.a.a.g.b;
import a.b.a.a.g.c;
import a.b.a.a.g.d;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.netinsight.sye.syeClient.closedCaptions.CCType;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0007\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0014¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0014\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006!"}, d2 = {"Lcom/netinsight/sye/syeClient/view/SyeClosedCaptionView;", "Landroid/view/View;", "La/b/a/a/g/d;", "v", "", "setVideoClientView", "(Landroid/view/View;)V", "La/b/a/a/g/b;", "syeCCObject", "render", "(La/b/a/a/g/b;)V", "Landroid/graphics/Canvas;", "canvas", "onDraw", "(Landroid/graphics/Canvas;)V", "clearText", "()V", "La/b/a/a/g/c;", "a", "La/b/a/a/g/c;", "mPainter708", "c", "Landroid/view/View;", "m708ClientView", "b", "La/b/a/a/g/b;", "currentDisplay", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public class SyeClosedCaptionView extends View implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public c mPainter708;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public b currentDisplay;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public View m708ClientView;

    @JvmOverloads
    public SyeClosedCaptionView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public SyeClosedCaptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPainter708 = new a.b.a.a.g.g.b(this);
        setWillNotDraw(false);
    }

    public /* synthetic */ SyeClosedCaptionView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    @Override // a.b.a.a.g.d
    public synchronized void clearText() {
        this.currentDisplay = null;
        postInvalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004b A[Catch: all -> 0x005d, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000d, B:9:0x0011, B:10:0x0019, B:12:0x001f, B:14:0x002b, B:15:0x002e, B:17:0x0034, B:19:0x003a, B:20:0x003d, B:24:0x0047, B:26:0x004b, B:27:0x0053, B:28:0x005a, B:22:0x0043), top: B:34:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0053 A[SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDraw(android.graphics.Canvas r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            a.b.a.a.g.b r0 = r4.currentDisplay     // Catch: java.lang.Throwable -> L5d
            if (r0 == 0) goto L5b
            com.netinsight.sye.syeClient.closedCaptions.CCType r1 = r0.getType()     // Catch: java.lang.Throwable -> L5d
            com.netinsight.sye.syeClient.closedCaptions.CCType r2 = com.netinsight.sye.syeClient.closedCaptions.CCType.DTVCC     // Catch: java.lang.Throwable -> L5d
            if (r1 != r2) goto L5b
            android.view.View r1 = r4.m708ClientView     // Catch: java.lang.Throwable -> L5d
            if (r1 == 0) goto L5b
            a.b.a.a.g.g.a r0 = (a.b.a.a.g.g.a) r0     // Catch: java.lang.Throwable -> L5d
            java.util.ArrayList<a.b.a.a.g.g.a$a> r0 = r0.f61a     // Catch: java.lang.Throwable -> L5d
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L5d
        L19:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L5d
            if (r1 == 0) goto L5b
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L5d
            a.b.a.a.g.g.a$a r1 = (a.b.a.a.g.g.a.C0010a) r1     // Catch: java.lang.Throwable -> L5d
            int r2 = r1.f62a     // Catch: java.lang.Throwable -> L5d
            android.view.View r3 = r4.m708ClientView     // Catch: java.lang.Throwable -> L5d
            if (r3 != 0) goto L2e
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L5d
        L2e:
            int r3 = r3.getHeight()     // Catch: java.lang.Throwable -> L5d
            if (r2 != r3) goto L43
            int r2 = r1.b     // Catch: java.lang.Throwable -> L5d
            android.view.View r3 = r4.m708ClientView     // Catch: java.lang.Throwable -> L5d
            if (r3 != 0) goto L3d
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L5d
        L3d:
            int r3 = r3.getWidth()     // Catch: java.lang.Throwable -> L5d
            if (r2 == r3) goto L47
        L43:
            int r2 = r1.b     // Catch: java.lang.Throwable -> L5d
            if (r2 != 0) goto L19
        L47:
            a.b.a.a.g.c r2 = r4.mPainter708     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L53
            a.b.a.a.g.g.b r2 = (a.b.a.a.g.g.b) r2     // Catch: java.lang.Throwable -> L5d
            java.util.ArrayList<a.b.a.a.g.g.a$a$b> r1 = r1.d     // Catch: java.lang.Throwable -> L5d
            r2.a(r1, r5)     // Catch: java.lang.Throwable -> L5d
            goto L19
        L53:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException     // Catch: java.lang.Throwable -> L5d
            java.lang.String r0 = "null cannot be cast to non-null type com.netinsight.sye.syeClient.closedCaptions.cc708.SyeClosedCaption708Painter"
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L5d
            throw r5     // Catch: java.lang.Throwable -> L5d
        L5b:
            monitor-exit(r4)
            return
        L5d:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netinsight.sye.syeClient.view.SyeClosedCaptionView.onDraw(android.graphics.Canvas):void");
    }

    @Override // a.b.a.a.g.d
    public synchronized void render(b syeCCObject) {
        if (syeCCObject.getType() != CCType.DTVCC) {
            return;
        }
        if (syeCCObject.b()) {
            syeCCObject = null;
        }
        this.currentDisplay = syeCCObject;
        postInvalidate();
    }

    public final void setVideoClientView(View v) {
        this.m708ClientView = v;
    }
}
