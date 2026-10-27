package c.a.g.h;

import e.u;
import e.z;
import f.k;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;

/* JADX INFO: compiled from: UploadFileRequestBody.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2698a = "g";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z f2699b;

    public g(String str, File file) {
        String contentTypeFor;
        try {
            contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(URLEncoder.encode(str, "UTF-8"));
        } catch (Exception e2) {
            e2.printStackTrace();
            contentTypeFor = null;
        }
        contentTypeFor = contentTypeFor == null ? "application/octet-stream" : contentTypeFor;
        c.a.b.d.e.c(f2698a, "contentType = " + contentTypeFor);
        this.f2699b = z.c(u.c(contentTypeFor), file);
    }

    @Override // e.z
    public long a() {
        try {
            z zVar = this.f2699b;
            if (zVar != null) {
                return zVar.a();
            }
            return 0L;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    @Override // e.z
    public u b() {
        z zVar = this.f2699b;
        if (zVar != null) {
            return zVar.b();
        }
        return null;
    }

    @Override // e.z
    public void h(f.d dVar) throws IOException {
        if (this.f2699b == null) {
            return;
        }
        f.d dVarA = k.a(new a(dVar));
        this.f2699b.h(dVarA);
        dVarA.flush();
    }
}
