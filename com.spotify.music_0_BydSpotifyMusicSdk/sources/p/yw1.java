package p;

import com.spotify.prefs.esperanto.proto.EsPrefs$PrefValues;
import java.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public final class yw1 implements io.reactivex.rxjava3.functions.o {
    public static final yw1 e = new yw1(0);
    public static final yw1 k = new yw1(1);
    public final /* synthetic */ int c;

    public /* synthetic */ yw1(int i) {
        this.c = i;
    }

    public final EsPrefs$PrefValues a(byte[] bArr) {
        switch (this.c) {
            case 0:
                v03.j(bArr, "data");
                try {
                    return EsPrefs$PrefValues.g(bArr);
                } catch (Exception e2) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.prefs.esperanto.proto.EsPrefs.PrefValues: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e2);
                }
            default:
                v03.j(bArr, "data");
                try {
                    return EsPrefs$PrefValues.g(bArr);
                } catch (Exception e3) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.prefs.esperanto.proto.EsPrefs.PrefValues: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e3);
                }
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final /* bridge */ /* synthetic */ Object apply(Object obj) {
        switch (this.c) {
        }
        return a((byte[]) obj);
    }
}
