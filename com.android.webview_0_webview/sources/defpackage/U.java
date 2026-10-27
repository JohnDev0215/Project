package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.view.ViewGroup;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U extends C1303p0 {

    @ViewDebug.ExportedProperty
    public boolean c;

    @ViewDebug.ExportedProperty
    public int d;

    @ViewDebug.ExportedProperty
    public int e;

    @ViewDebug.ExportedProperty
    public boolean f;

    @ViewDebug.ExportedProperty
    public boolean g;
    public boolean h;

    public U(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public U(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public U(U u) {
        super(u);
        this.c = u.c;
    }

    public U(int i, int i2) {
        super(i, i2);
        this.c = false;
    }
}
