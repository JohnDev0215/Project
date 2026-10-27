package p;

import com.spotify.extendedmetadata.proto.ExtensionKind;

/* JADX INFO: loaded from: classes.dex */
public final class ol0 implements p51 {
    public static final ol0 a = new ol0();

    @Override // p.p51
    public final boolean isInRange(int i) {
        return ExtensionKind.a(i) != null;
    }
}
