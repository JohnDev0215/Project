package k4;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public final class y implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f18223b;

    public /* synthetic */ y(q qVar, int i7) {
        this.f18222a = i7;
        this.f18223b = qVar;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, e4.h hVar) {
        Uri uriFromFile;
        switch (this.f18222a) {
            case 0:
                String str = (String) obj;
                if (TextUtils.isEmpty(str)) {
                    uriFromFile = null;
                } else if (str.charAt(0) == '/') {
                    uriFromFile = Uri.fromFile(new File(str));
                } else {
                    Uri uri = Uri.parse(str);
                    uriFromFile = uri.getScheme() == null ? Uri.fromFile(new File(str)) : uri;
                }
                if (uriFromFile == null) {
                    return null;
                }
                q qVar = this.f18223b;
                if (qVar.b(uriFromFile)) {
                    return qVar.a(uriFromFile, i7, i10, hVar);
                }
                return null;
            default:
                return this.f18223b.a(new g((URL) obj), i7, i10, hVar);
        }
    }

    @Override // k4.q
    public final /* bridge */ /* synthetic */ boolean b(Object obj) {
        switch (this.f18222a) {
            case 0:
                break;
            default:
                break;
        }
        return true;
    }
}
