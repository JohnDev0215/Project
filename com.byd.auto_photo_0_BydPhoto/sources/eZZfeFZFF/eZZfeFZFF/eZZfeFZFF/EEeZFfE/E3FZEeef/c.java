package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE.E3FZEeef;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.GraphicBuffer;
import android.graphics.Rect;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ParcelCreator"})
public class c implements IDiLinkGraphicBuffer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private GraphicBuffer f3025a;

    public c(GraphicBuffer graphicBuffer) {
        if (graphicBuffer == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("snapshot is null");
        }
        this.f3025a = graphicBuffer;
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void destroy() {
        this.f3025a.destroy();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getFormat() {
        return this.f3025a.getFormat();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getHeight() {
        return this.f3025a.getHeight();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getUsage() {
        return this.f3025a.getUsage();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public int getWidth() {
        return this.f3025a.getWidth();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public boolean isDestroyed() {
        return this.f3025a.isDestroyed();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas() {
        return this.f3025a.lockCanvas();
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public Canvas lockCanvas(Rect rect) {
        return this.f3025a.lockCanvas(rect);
    }

    @Override // com.byd.dilink.app.am.IDiLinkGraphicBuffer
    public void unlockCanvasAndPost(Canvas canvas) {
        this.f3025a.unlockCanvasAndPost(canvas);
    }
}
