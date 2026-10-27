package sst.sst.sst.StsT.t1sT;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.GraphicBuffer;
import android.graphics.Rect;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ParcelCreator"})
public class a implements IDiLinkGraphicBuffer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private GraphicBuffer f3217a;

    public a(GraphicBuffer graphicBuffer) {
        if (graphicBuffer == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("snapshot is null");
        }
        this.f3217a = graphicBuffer;
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void destroy() {
        this.f3217a.destroy();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getFormat() {
        return this.f3217a.getFormat();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getHeight() {
        return this.f3217a.getHeight();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getUsage() {
        return this.f3217a.getUsage();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getWidth() {
        return this.f3217a.getWidth();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public boolean isDestroyed() {
        return this.f3217a.isDestroyed();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas() {
        return this.f3217a.lockCanvas();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas(Rect rect) {
        return this.f3217a.lockCanvas(rect);
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void unlockCanvasAndPost(Canvas canvas) {
        this.f3217a.unlockCanvasAndPost(canvas);
    }
}
