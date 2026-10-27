package RR9QRrr.RR9QRrr.RR9QRrr.RQRrRRRRQ;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.mirror.MirrorState;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkMirrorState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MirrorState f106a;

    public a(MirrorState mirrorState) {
        if (mirrorState == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("mirrorState is null");
        }
        this.f106a = mirrorState;
    }

    public MirrorState a() {
        return this.f106a;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        return this.f106a.equals(((a) obj).a());
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public int getDisplayId() {
        return this.f106a.getDisplayId();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String getMirrorApp() {
        return this.f106a.getMirrorApp();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String[] getMirrorAppCollection() {
        return this.f106a.getMirrorAppCollection();
    }

    public int hashCode() {
        return this.f106a.hashCode();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror() {
        return this.f106a.inMirror();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror(String str) {
        return this.f106a.inMirror(str);
    }

    @NonNull
    public String toString() {
        return this.f106a.toString();
    }
}
