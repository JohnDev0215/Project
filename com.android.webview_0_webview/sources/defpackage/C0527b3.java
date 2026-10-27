package defpackage;

/* JADX INFO: renamed from: b3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0527b3 implements InterfaceC0471a3 {
    public final Object[] a;
    public int b;

    public C0527b3(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.a = new Object[i];
    }

    @Override // defpackage.InterfaceC0471a3
    public boolean a(Object obj) {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.b) {
                z = false;
                break;
            }
            if (this.a[i] == obj) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i2 = this.b;
        Object[] objArr = this.a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = obj;
        this.b = i2 + 1;
        return true;
    }

    @Override // defpackage.InterfaceC0471a3
    public Object b() {
        int i = this.b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.a;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.b = i - 1;
        return obj;
    }
}
