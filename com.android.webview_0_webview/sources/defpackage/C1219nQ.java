package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: renamed from: nQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1219nQ implements RN {
    public final /* synthetic */ Intent a;
    public final /* synthetic */ String b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ C1329pQ d;

    public C1219nQ(C1329pQ c1329pQ, Intent intent, String str, boolean z) {
        this.d = c1329pQ;
        this.a = intent;
        this.b = str;
        this.c = z;
    }

    @Override // defpackage.RN
    public void a(String[] strArr, int[] iArr) {
        if (iArr.length <= 0 || iArr[0] != 0 || !(!this.d.c)) {
            if (this.c) {
                Objects.requireNonNull(this.d);
            }
        } else {
            RD rd = new RD(this.a.getDataString(), 6);
            if (!TextUtils.isEmpty(this.b)) {
                rd.d = new C0984jE(this.b, 0);
            }
            this.d.b.z(rd);
        }
    }
}
