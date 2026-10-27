package defpackage;

import android.graphics.Rect;
import android.util.Property;
import android.view.View;

/* JADX INFO: renamed from: q7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1365q7 extends Property {
    public C1365q7(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return AbstractC1251o3.f((View) obj);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        AbstractC1251o3.F((View) obj, (Rect) obj2);
    }
}
