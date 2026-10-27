package defpackage;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ox implements FilenameFilter {
    public final /* synthetic */ Pattern a;

    public Ox(Px px, Pattern pattern) {
        this.a = pattern;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return this.a.matcher(str).find();
    }
}
