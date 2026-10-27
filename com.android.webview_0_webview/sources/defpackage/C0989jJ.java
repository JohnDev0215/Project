package defpackage;

import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: renamed from: jJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0989jJ {
    public final ByteBuffer a;
    public final List b;
    public C1541tJ c;

    public C0989jJ(ByteBuffer byteBuffer, List list) {
        this.a = byteBuffer;
        this.b = list;
    }

    public C1541tJ a() {
        if (this.c == null) {
            this.c = new C1541tJ(this);
        }
        return this.c;
    }
}
