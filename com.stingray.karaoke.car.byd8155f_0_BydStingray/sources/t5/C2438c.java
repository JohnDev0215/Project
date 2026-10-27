package t5;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: t5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2438c {
    public static final ArrayList c = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharsetEncoder[] f22469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22470b;

    static {
        String[] strArr = {"IBM437", "ISO-8859-2", "ISO-8859-3", "ISO-8859-4", "ISO-8859-5", "ISO-8859-6", "ISO-8859-7", "ISO-8859-8", "ISO-8859-9", "ISO-8859-10", "ISO-8859-11", "ISO-8859-13", "ISO-8859-14", "ISO-8859-15", "ISO-8859-16", "windows-1250", "windows-1251", "windows-1252", "windows-1256", "Shift_JIS"};
        for (int i7 = 0; i7 < 20; i7++) {
            String str = strArr[i7];
            if (((EnumC2437b) EnumC2437b.o.get(str)) != null) {
                try {
                    c.add(Charset.forName(str).newEncoder());
                } catch (UnsupportedCharsetException unused) {
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C2438c(String str, Charset charset) {
        int i7;
        boolean z6;
        ArrayList<CharsetEncoder> arrayList = new ArrayList();
        arrayList.add(StandardCharsets.ISO_8859_1.newEncoder());
        int i10 = 0;
        boolean z10 = charset != null && charset.name().startsWith("UTF");
        int i11 = 0;
        while (true) {
            i7 = -1;
            if (i11 >= str.length()) {
                break;
            }
            for (CharsetEncoder charsetEncoder : arrayList) {
                char cCharAt = str.charAt(i11);
                if (cCharAt == -1 || charsetEncoder.canEncode(cCharAt)) {
                    z6 = true;
                    break;
                }
            }
            z6 = false;
            if (!z6) {
                Iterator it = c.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CharsetEncoder charsetEncoder2 = (CharsetEncoder) it.next();
                    if (charsetEncoder2.canEncode(str.charAt(i11))) {
                        arrayList.add(charsetEncoder2);
                        z6 = true;
                        break;
                    }
                }
            }
            if (!z6) {
                z10 = true;
            }
            i11++;
        }
        if (arrayList.size() != 1 || z10) {
            this.f22469a = new CharsetEncoder[arrayList.size() + 2];
            Iterator it2 = arrayList.iterator();
            int i12 = 0;
            while (it2.hasNext()) {
                this.f22469a[i12] = (CharsetEncoder) it2.next();
                i12++;
            }
            this.f22469a[i12] = StandardCharsets.UTF_8.newEncoder();
            this.f22469a[i12 + 1] = StandardCharsets.UTF_16BE.newEncoder();
        } else {
            this.f22469a = new CharsetEncoder[]{(CharsetEncoder) arrayList.get(0)};
        }
        if (charset != null) {
            while (true) {
                CharsetEncoder[] charsetEncoderArr = this.f22469a;
                if (i10 >= charsetEncoderArr.length) {
                    break;
                }
                if (charsetEncoderArr[i10] != null && charset.name().equals(this.f22469a[i10].charset().name())) {
                    i7 = i10;
                    break;
                }
                i10++;
            }
        }
        this.f22470b = i7;
    }
}
