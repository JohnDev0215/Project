package p;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nv0 implements Comparator {
    public final /* synthetic */ int c;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.c) {
            case 0:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i = 0; i < bArr.length; i++) {
                    byte b = bArr[i];
                    byte b2 = bArr2[i];
                    if (b != b2) {
                        return b - b2;
                    }
                }
                return 0;
            case 1:
                return ((fh) ((n30) obj)).a.compareTo(((fh) ((n30) obj2)).a);
            case 2:
                Charset charset = j40.d;
                String name = ((File) obj).getName();
                int i2 = j40.e;
                return name.substring(0, i2).compareTo(((File) obj2).getName().substring(0, i2));
            default:
                Charset charset2 = j40.d;
                return ((File) obj2).getName().compareTo(((File) obj).getName());
        }
    }
}
