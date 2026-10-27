package p;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class ap implements hm1 {
    public final /* synthetic */ int a;
    public final String b;
    public final String c;
    public final Serializable d;

    public /* synthetic */ ap(String str, String str2, Serializable serializable, int i) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = serializable;
    }

    public final InputStream a() {
        int i = this.a;
        Object obj = this.d;
        switch (i) {
            case 0:
                byte[] bArr = (byte[]) obj;
                if (bArr == null || bArr.length == 0) {
                    return null;
                }
                return new ByteArrayInputStream((byte[]) obj);
            default:
                File file = (File) obj;
                if (!file.exists() || !file.isFile()) {
                    return null;
                }
                try {
                    return new FileInputStream((File) obj);
                } catch (FileNotFoundException unused) {
                    return null;
                }
        }
    }
}
