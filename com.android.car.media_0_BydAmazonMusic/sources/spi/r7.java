package spi;

import android.content.Context;
import spi.a7;

/* JADX INFO: compiled from: MusicWidgetUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class r7 {
    public transient boolean a;

    /* JADX INFO: compiled from: MusicWidgetUtil.java */
    public static class a {
        public static final r7 a = new r7();

        private a() {
        }
    }

    public final void a(Context context) {
        this.a = true;
        a7.a aVar = new a7.a();
        aVar.b("mediatitle", null);
        aVar.b("mediaartist", null);
        aVar.b("mediaduration", null);
        aVar.b("mediaalbum", null);
        a7.a(context, 255, null);
    }
}
