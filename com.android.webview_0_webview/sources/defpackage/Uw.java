package defpackage;

import android.util.Property;
import org.chromium.components.browser_ui.widget.NumberRollView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Uw extends Property {
    public Uw(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((NumberRollView) obj).u);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        ((NumberRollView) obj).b(((Float) obj2).floatValue());
    }
}
