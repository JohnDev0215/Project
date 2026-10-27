package RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr.RqqqQRRR9;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.GraphicBuffer;
import android.graphics.Rect;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ParcelCreator"})
public class a implements IDiLinkGraphicBuffer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private GraphicBuffer f133a;

    public a(GraphicBuffer graphicBuffer) {
        if (graphicBuffer == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("snapshot is null");
        }
        this.f133a = graphicBuffer;
    }

    public GraphicBuffer a() {
        return this.f133a;
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void destroy() {
        this.f133a.destroy();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getFormat() {
        return this.f133a.getFormat();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getHeight() {
        return this.f133a.getHeight();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getUsage() {
        return this.f133a.getUsage();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getWidth() {
        return this.f133a.getWidth();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public boolean isDestroyed() {
        return this.f133a.isDestroyed();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas() {
        return this.f133a.lockCanvas();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas(Rect rect) {
        return this.f133a.lockCanvas(rect);
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void unlockCanvasAndPost(Canvas canvas) {
        this.f133a.unlockCanvasAndPost(canvas);
    }
}
