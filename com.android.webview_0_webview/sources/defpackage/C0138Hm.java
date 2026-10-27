package defpackage;

/* JADX INFO: renamed from: Hm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0138Hm extends IllegalArgumentException {
    public C0138Hm(int i, int i2) {
        super("Unpaired surrogate at index " + i + " of " + i2);
    }
}
