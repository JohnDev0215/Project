package G3;

import K3.m;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2665a;

    public a(boolean z6) {
        this.f2665a = z6;
    }

    @Override // G3.b
    public final String a(Object obj, m mVar) {
        File file = (File) obj;
        if (!this.f2665a) {
            return file.getPath();
        }
        return file.getPath() + ':' + file.lastModified();
    }
}
