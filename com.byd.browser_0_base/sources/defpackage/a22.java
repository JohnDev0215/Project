package defpackage;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import java.util.List;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PlayerFrameProperties.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a22 {
    public static final PropertyModel.r<Bitmap[][]> a;
    public static final PropertyModel.r<Size> b;
    public static final PropertyModel.r<Point> c;
    public static final PropertyModel.r<Rect> d;
    public static final PropertyModel.r<List<View>> e;
    public static final PropertyModel.r<List<Rect>> f;
    public static final PropertyModel.r<Matrix> g;
    public static final v62[] h;

    static {
        PropertyModel.r<Bitmap[][]> rVar = new PropertyModel.r<>(null, true);
        a = rVar;
        PropertyModel.r<Size> rVar2 = new PropertyModel.r<>();
        b = rVar2;
        PropertyModel.r<Point> rVar3 = new PropertyModel.r<>(null, true);
        c = rVar3;
        PropertyModel.r<Rect> rVar4 = new PropertyModel.r<>(null, true);
        d = rVar4;
        PropertyModel.r<List<View>> rVar5 = new PropertyModel.r<>();
        e = rVar5;
        PropertyModel.r<List<Rect>> rVar6 = new PropertyModel.r<>();
        f = rVar6;
        PropertyModel.r<Matrix> rVar7 = new PropertyModel.r<>(null, true);
        g = rVar7;
        h = new v62[]{rVar, rVar2, rVar3, rVar4, rVar5, rVar6, rVar7};
    }
}
