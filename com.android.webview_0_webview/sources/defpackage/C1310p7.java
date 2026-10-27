package defpackage;

import android.util.Property;
import android.view.View;

/* JADX INFO: renamed from: p7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1310p7 extends Property {
    public C1310p7(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(AbstractC1419r7.a((View) obj));
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        float fFloatValue = ((Float) obj2).floatValue();
        AbstractC1419r7.a.e((View) obj, fFloatValue);
    }
}
