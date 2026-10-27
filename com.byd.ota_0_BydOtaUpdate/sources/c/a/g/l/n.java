package c.a.g.l;

import android.content.Context;
import com.byd.repository.bean.UrlSuffixInfo;
import java.io.BufferedInputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: compiled from: ServerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: compiled from: ServerUtil.java */
    public class a extends c.b.a.u.a<List<UrlSuffixInfo>> {
    }

    public static String a(Context context, int i2) throws Throwable {
        BufferedInputStream bufferedInputStream;
        byte[] bArr;
        ByteBuffer byteBufferAllocate;
        c.a.b.d.e.c("ServerUtil", "countryCode = " + i2);
        if (i2 == -1) {
            return "";
        }
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(context.getAssets().open("UrlSuffixData.json"));
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            int iAvailable = bufferedInputStream.available();
            c.a.b.d.e.c("ServerUtil", String.format("fileSize=%d", Integer.valueOf(iAvailable)));
            bArr = new byte[1024];
            byteBufferAllocate = ByteBuffer.allocate(iAvailable);
        } catch (Exception e4) {
            e = e4;
            bufferedInputStream2 = bufferedInputStream;
            e.printStackTrace();
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        while (true) {
            int i3 = bufferedInputStream.read(bArr);
            if (i3 <= 0) {
                break;
            }
            byteBufferAllocate.put(bArr, 0, i3);
            return "";
        }
        List<UrlSuffixInfo> list = (List) h.c(new String(byteBufferAllocate.array()), new a());
        c.a.b.d.e.c("ServerUtil", "urlSuffixInfoArrayList size = " + list.size());
        for (UrlSuffixInfo urlSuffixInfo : list) {
            if (urlSuffixInfo.key == i2) {
                c.a.b.d.e.c("ServerUtil", "urlSuffixInfo = " + urlSuffixInfo);
                String str = urlSuffixInfo.data;
                try {
                    bufferedInputStream.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                return str;
            }
            return "";
        }
        bufferedInputStream.close();
        return "";
    }
}
