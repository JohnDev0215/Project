package defpackage;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;

/* JADX INFO: renamed from: x6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1748x6 extends Property {
    public Rect a;

    public C1748x6(Class cls, String str) {
        super(cls, str);
        this.a = new Rect();
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        ((Drawable) obj).copyBounds(this.a);
        Rect rect = this.a;
        return new PointF(rect.left, rect.top);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        Drawable drawable = (Drawable) obj;
        PointF pointF = (PointF) obj2;
        drawable.copyBounds(this.a);
        this.a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
        drawable.setBounds(this.a);
    }
}
