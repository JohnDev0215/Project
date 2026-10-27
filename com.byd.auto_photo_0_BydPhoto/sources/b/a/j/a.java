package b.a.j;

import android.graphics.Path;
import android.graphics.RectF;
import com.byd.imageedit.CropImageView;

/* JADX INFO: loaded from: classes2.dex */
public class a extends b {
    private RectF E;

    public a(CropImageView cropImageView) {
        super(cropImageView, false);
        this.y = false;
        this.E = new RectF();
    }

    @Override // b.a.j.b
    protected void y() {
        this.f522c.reset();
        this.E.set(this.d);
        this.f522c.addOval(this.E, Path.Direction.CW);
    }
}
