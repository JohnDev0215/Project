package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: f5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0752f5 {
    public final InterfaceC0696e5 a;
    public final C0641d5 b = new C0641d5();
    public final List c = new ArrayList();

    public C0752f5(InterfaceC0696e5 interfaceC0696e5) {
        this.a = interfaceC0696e5;
    }

    public int a() {
        return ((L5) this.a).b() - this.c.size();
    }

    public final int b(int i) {
        if (i < 0) {
            return -1;
        }
        int iB = ((L5) this.a).b();
        int i2 = i;
        while (i2 < iB) {
            int iA = i - (i2 - this.b.a(i2));
            if (iA == 0) {
                while (this.b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iA;
        }
        return -1;
    }

    public View c(int i) {
        return ((L5) this.a).a.getChildAt(i);
    }

    public int d() {
        return ((L5) this.a).b();
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}
