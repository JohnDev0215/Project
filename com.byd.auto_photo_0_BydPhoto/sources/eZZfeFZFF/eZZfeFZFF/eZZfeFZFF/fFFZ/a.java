package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.fFFZ;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.mirror.MirrorState;

/* JADX INFO: loaded from: classes2.dex */
public class a implements IDiLinkMirrorState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MirrorState f3050a;

    public a(MirrorState mirrorState) {
        if (mirrorState == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("mirrorState is null");
        }
        this.f3050a = mirrorState;
    }

    public MirrorState a() {
        return this.f3050a;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        return this.f3050a.equals(((a) obj).a());
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public int getDisplayId() {
        return this.f3050a.getDisplayId();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String getMirrorApp() {
        return this.f3050a.getMirrorApp();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String[] getMirrorAppCollection() {
        return this.f3050a.getMirrorAppCollection();
    }

    public int hashCode() {
        return this.f3050a.hashCode();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror() {
        return this.f3050a.inMirror();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror(String str) {
        return this.f3050a.inMirror(str);
    }

    @NonNull
    public String toString() {
        return this.f3050a.toString();
    }
}
