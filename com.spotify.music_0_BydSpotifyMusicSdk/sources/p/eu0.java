package p;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class eu0 implements n81 {
    public final boolean a;

    public eu0(boolean z) {
        this.a = z;
    }

    @Override // p.n81
    public final String a(Object obj, br1 br1Var) {
        File file = (File) obj;
        if (!this.a) {
            return file.getPath();
        }
        return file.getPath() + ':' + file.lastModified();
    }
}
