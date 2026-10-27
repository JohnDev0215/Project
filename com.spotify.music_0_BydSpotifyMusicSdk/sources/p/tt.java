package p;

import com.google.protobuf.ByteString;
import com.spotify.auto.contexts.proto.ClientId;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class tt implements di0 {
    @Override // p.di0
    public final String a() {
        return "context_client_id";
    }

    @Override // p.di0
    public final com.google.protobuf.i getData() {
        boolean z;
        st stVarF = ClientId.f();
        cl clVar = el.d;
        el elVarG = clVar.c;
        if (elVarG == null) {
            bl blVar = clVar.a;
            char[] cArr = blVar.b;
            int length = cArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                char c = cArr[i];
                if (c < 'A' || c > 'Z') {
                    i++;
                } else {
                    int length2 = cArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            z = false;
                            break;
                        }
                        char c2 = cArr[i2];
                        if (c2 >= 'a' && c2 <= 'z') {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                    if (!(!z)) {
                        throw new IllegalStateException("Cannot call lowerCase() on a mixed-case alphabet");
                    }
                    char[] cArr2 = new char[cArr.length];
                    for (int i3 = 0; i3 < cArr.length; i3++) {
                        char c3 = cArr[i3];
                        if (c3 >= 'A' && c3 <= 'Z') {
                            c3 = (char) (c3 ^ ' ');
                        }
                        cArr2[i3] = c3;
                    }
                    bl blVar2 = new bl(u4.m(new StringBuilder(), blVar.a, ".lowerCase()"), cArr2);
                    if (blVar.i && !blVar2.i) {
                        byte[] bArr = blVar2.g;
                        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                        for (int i4 = 65; i4 <= 90; i4++) {
                            int i5 = i4 | 32;
                            byte b = bArr[i4];
                            byte b2 = bArr[i5];
                            if (b == -1) {
                                bArrCopyOf[i4] = b2;
                            } else {
                                char c4 = (char) i4;
                                char c5 = (char) i5;
                                if (!(b2 == -1)) {
                                    throw new IllegalStateException(v03.z("Can't ignoreCase() since '%s' and '%s' encode different values", Character.valueOf(c4), Character.valueOf(c5)));
                                }
                                bArrCopyOf[i5] = b;
                            }
                        }
                        blVar2 = new bl(u4.m(new StringBuilder(), blVar2.a, ".ignoreCase()"), blVar2.b, bArrCopyOf, true);
                    }
                    blVar = blVar2;
                }
            }
            elVarG = blVar == clVar.a ? clVar : clVar.g(blVar, clVar.b);
            clVar.c = elVarG;
        }
        byte[] bArrA = elVarG.a("1fd85409eb8e441fb783f60caf2fdaab");
        stVarF.d(ByteString.c(bArrA, 0, bArrA.length));
        return stVarF.build();
    }
}
