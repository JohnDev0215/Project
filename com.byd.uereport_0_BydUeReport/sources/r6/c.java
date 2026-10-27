package r6;

import com.google.zxing.common.CharacterSetECI;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ECIEncoderSet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<CharsetEncoder> f7851a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharsetEncoder[] f7852b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7853c;

    static {
        String[] strArr = {"IBM437", "ISO-8859-2", "ISO-8859-3", "ISO-8859-4", "ISO-8859-5", "ISO-8859-6", "ISO-8859-7", "ISO-8859-8", "ISO-8859-9", "ISO-8859-10", "ISO-8859-11", "ISO-8859-13", "ISO-8859-14", "ISO-8859-15", "ISO-8859-16", "windows-1250", "windows-1251", "windows-1252", "windows-1256", "Shift_JIS"};
        for (int i8 = 0; i8 < 20; i8++) {
            String str = strArr[i8];
            if (CharacterSetECI.getCharacterSetECIByName(str) != null) {
                try {
                    f7851a.add(Charset.forName(str).newEncoder());
                } catch (UnsupportedCharsetException unused) {
                }
            }
        }
    }

    public c(String str, Charset charset, int i8) {
        boolean z7;
        ArrayList<CharsetEncoder> arrayList = new ArrayList();
        arrayList.add(StandardCharsets.ISO_8859_1.newEncoder());
        int i9 = 0;
        boolean z8 = charset != null && charset.name().startsWith("UTF");
        for (int i10 = 0; i10 < str.length(); i10++) {
            for (CharsetEncoder charsetEncoder : arrayList) {
                char cCharAt = str.charAt(i10);
                if (cCharAt == i8 || charsetEncoder.canEncode(cCharAt)) {
                    z7 = true;
                    break;
                }
            }
            z7 = false;
            if (!z7) {
                Iterator<CharsetEncoder> it = f7851a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CharsetEncoder next = it.next();
                    if (next.canEncode(str.charAt(i10))) {
                        arrayList.add(next);
                        z7 = true;
                        break;
                    }
                }
            }
            if (!z7) {
                z8 = true;
            }
        }
        if (arrayList.size() != 1 || z8) {
            this.f7852b = new CharsetEncoder[arrayList.size() + 2];
            Iterator it2 = arrayList.iterator();
            int i11 = 0;
            while (it2.hasNext()) {
                this.f7852b[i11] = (CharsetEncoder) it2.next();
                i11++;
            }
            this.f7852b[i11] = StandardCharsets.UTF_8.newEncoder();
            this.f7852b[i11 + 1] = StandardCharsets.UTF_16BE.newEncoder();
        } else {
            this.f7852b = new CharsetEncoder[]{(CharsetEncoder) arrayList.get(0)};
        }
        int i12 = -1;
        if (charset != null) {
            while (true) {
                CharsetEncoder[] charsetEncoderArr = this.f7852b;
                if (i9 >= charsetEncoderArr.length) {
                    break;
                }
                if (charsetEncoderArr[i9] != null && charset.name().equals(this.f7852b[i9].charset().name())) {
                    i12 = i9;
                    break;
                }
                i9++;
            }
        }
        this.f7853c = i12;
    }

    public boolean a(char c8, int i8) {
        return this.f7852b[i8].canEncode("" + c8);
    }

    public byte[] b(String str, int i8) {
        return str.getBytes(this.f7852b[i8].charset());
    }

    public Charset c(int i8) {
        return this.f7852b[i8].charset();
    }

    public int d(int i8) {
        return CharacterSetECI.getCharacterSetECI(this.f7852b[i8].charset()).getValue();
    }

    public int e() {
        return this.f7853c;
    }

    public int f() {
        return this.f7852b.length;
    }
}
