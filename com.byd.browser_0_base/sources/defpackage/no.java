package defpackage;

import J.N;
import java.io.Serializable;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: ChosenObjectInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class no implements Serializable {
    public final int e;
    public final String f;
    public final String g;
    public final String h;
    public final boolean i;

    public no(int i, String str, String str2, String str3, boolean z) {
        this.e = i;
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.i = z;
    }

    public final void a(Profile profile) {
        if (this.i) {
            return;
        }
        N.Mw9TjMNp(profile, this.e, this.f, this.h);
    }
}
