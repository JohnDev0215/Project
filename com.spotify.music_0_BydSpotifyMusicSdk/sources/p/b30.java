package p;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b30 implements FilenameFilter {
    public final /* synthetic */ int a;

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        switch (this.a) {
            case 0:
                return str.startsWith(".ae");
            case 1:
                Charset charset = j40.d;
                return str.startsWith("event") && !str.endsWith("_");
            default:
                Charset charset2 = j40.d;
                return str.startsWith("event");
        }
    }
}
