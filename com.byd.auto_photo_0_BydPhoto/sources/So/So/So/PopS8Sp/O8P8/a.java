package So.So.So.PopS8Sp.O8P8;

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
    private GraphicBuffer f143a;

    public a(GraphicBuffer graphicBuffer) {
        if (graphicBuffer == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("snapshot is null");
        }
        this.f143a = graphicBuffer;
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void destroy() {
        this.f143a.destroy();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getFormat() {
        return this.f143a.getFormat();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getHeight() {
        return this.f143a.getHeight();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getUsage() {
        return this.f143a.getUsage();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getWidth() {
        return this.f143a.getWidth();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public boolean isDestroyed() {
        return this.f143a.isDestroyed();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas() {
        return this.f143a.lockCanvas();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas(Rect rect) {
        return this.f143a.lockCanvas(rect);
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void unlockCanvasAndPost(Canvas canvas) {
        this.f143a.unlockCanvasAndPost(canvas);
    }
}
