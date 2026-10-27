package p;

import android.webkit.MimeTypeMap;
import coil.decode.DataSource;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class bu0 implements qt0 {
    public final File a;

    public bu0(File file) {
        this.a = file;
    }

    @Override // p.qt0
    public final Object a(c00 c00Var) {
        String str = os1.e;
        File file = this.a;
        du0 du0Var = new du0(u11.A(file), hu0.a, null, null);
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String name = file.getName();
        v03.i(name, "name");
        return new fe2(du0Var, singleton.getMimeTypeFromExtension(kotlin.text.b.D0(name, '.', "")), DataSource.k);
    }
}
