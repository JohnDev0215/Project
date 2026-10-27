package p;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f23 implements FilenameFilter {
    public static final /* synthetic */ f23 a = new f23();

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return com.google.android.play.core.assetpacks.q.a.matcher(str).matches();
    }
}
