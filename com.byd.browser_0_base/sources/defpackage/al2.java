package defpackage;

/* JADX INFO: compiled from: ShareSheetLinkToggleMetricsHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class al2 {

    /* JADX INFO: compiled from: ShareSheetLinkToggleMetricsHelper.java */
    public static final class a {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public static void a(a aVar, String str) {
        String str2;
        if (aVar.a == 2) {
            return;
        }
        StringBuilder sb = new StringBuilder("Sharing.SharingHubAndroid.");
        switch (aVar.b) {
            case 0:
                str2 = "NotSpecified";
                break;
            case 1:
                str2 = "Image";
                break;
            case 2:
                str2 = "Gif";
                break;
            case 3:
                str2 = "HighlightedText";
                break;
            case 4:
                str2 = "Screenshot";
                break;
            case 5:
                str2 = "Webnotes";
                break;
            case 6:
                str2 = "LightweightReaction";
                break;
            default:
                str2 = "";
                break;
        }
        sb.append(str2);
        sb.append(".");
        sb.append(str);
        oj2.U(aVar.a, 2, sb.toString());
    }
}
