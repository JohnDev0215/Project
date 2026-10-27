package org.chromium.media;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CodecProfileLevelList$CodecProfileLevelAdapter {
    public final int a;
    public final int b;
    public final int c;

    public CodecProfileLevelList$CodecProfileLevelAdapter(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public int getCodec() {
        return this.a;
    }

    public int getLevel() {
        return this.c;
    }

    public int getProfile() {
        return this.b;
    }
}
