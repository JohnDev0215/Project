package spi;

import java.util.List;

/* JADX INFO: compiled from: ContentListItemOEMV1.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x2 implements z5 {
    public final List<wa> a;
    public boolean b;

    /* JADX INFO: compiled from: ContentListItemOEMV1.java */
    public enum a {
        NONE,
        SWITCH,
        CHECK_BOX,
        RADIO_BUTTON,
        ICON,
        CHEVRON
    }

    /* JADX INFO: compiled from: ContentListItemOEMV1.java */
    public static final class b {
        public final a a;
        public boolean b;

        public b(a aVar) {
            c cVar = c.STANDARD;
            this.b = false;
            this.a = aVar;
        }
    }

    /* JADX INFO: compiled from: ContentListItemOEMV1.java */
    public enum c {
        CONTENT,
        STANDARD,
        AVATAR
    }

    public x2(b bVar) {
        a aVar = bVar.a;
        this.a = null;
        this.b = bVar.b;
    }
}
