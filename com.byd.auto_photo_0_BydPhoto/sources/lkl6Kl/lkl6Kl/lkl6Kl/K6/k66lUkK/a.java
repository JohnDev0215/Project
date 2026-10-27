package lkl6Kl.lkl6Kl.lkl6Kl.K6.k66lUkK;

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
    private GraphicBuffer f3102a;

    public a(GraphicBuffer graphicBuffer) {
        if (graphicBuffer == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("snapshot is null");
        }
        this.f3102a = graphicBuffer;
    }

    public GraphicBuffer a() {
        return this.f3102a;
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void destroy() {
        this.f3102a.destroy();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getFormat() {
        return this.f3102a.getFormat();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getHeight() {
        return this.f3102a.getHeight();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getUsage() {
        return this.f3102a.getUsage();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getWidth() {
        return this.f3102a.getWidth();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public boolean isDestroyed() {
        return this.f3102a.isDestroyed();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas() {
        return this.f3102a.lockCanvas();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas(Rect rect) {
        return this.f3102a.lockCanvas(rect);
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void unlockCanvasAndPost(Canvas canvas) {
        this.f3102a.unlockCanvasAndPost(canvas);
    }
}
