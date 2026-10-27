package DDC.DDC.DDC.DcDc2c22Y;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.mirror.MirrorState;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkMirrorState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MirrorState f21a;

    public a(MirrorState mirrorState) {
        if (mirrorState == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("mirrorState is null");
        }
        this.f21a = mirrorState;
    }

    public MirrorState a() {
        return this.f21a;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        return this.f21a.equals(((a) obj).a());
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public int getDisplayId() {
        return this.f21a.getDisplayId();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String getMirrorApp() {
        return this.f21a.getMirrorApp();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String[] getMirrorAppCollection() {
        return this.f21a.getMirrorAppCollection();
    }

    public int hashCode() {
        return this.f21a.hashCode();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror() {
        return this.f21a.inMirror();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror(String str) {
        return this.f21a.inMirror(str);
    }

    @NonNull
    public String toString() {
        return this.f21a.toString();
    }
}
