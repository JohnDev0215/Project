package defpackage;

/* JADX INFO: compiled from: ChannelDefinitions.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ym {

    /* JADX INFO: compiled from: ChannelDefinitions.java */
    public static class a {
        public final String a;
        public final int b;
        public final int c;
        public final String d = "general";
        public final boolean e;
        public final boolean f;

        public a(String str, int i, int i2, boolean z, boolean z2) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.e = z;
            this.f = z2;
        }

        public static a a(int i, int i2, String str) {
            return new a(str, i, i2, false, false);
        }
    }

    /* JADX INFO: compiled from: ChannelDefinitions.java */
    public static class b {
        public final String a;
        public final int b;

        public b(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }
}
