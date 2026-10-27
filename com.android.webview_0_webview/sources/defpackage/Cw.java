package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Cw {
    public Long a;
    public int b;
    public Boolean c;

    public Cw(Bw bw) {
    }

    public void a(int i) {
        Boolean bool = Boolean.TRUE;
        if (i == 1 || this.b != 0) {
            if (8 == i && this.c == null) {
                this.c = Boolean.FALSE;
            } else if (32 == i) {
                if (this.c == null) {
                    this.c = bool;
                }
                this.c = bool;
                i = 8;
            }
            this.b = i | this.b;
        }
    }
}
