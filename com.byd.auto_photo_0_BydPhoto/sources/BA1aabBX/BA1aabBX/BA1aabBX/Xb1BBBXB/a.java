package BA1aabBX.BA1aabBX.BA1aabBX.Xb1BBBXB;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.mirror.MirrorState;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkMirrorState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MirrorState f6a;

    public a(MirrorState mirrorState) {
        if (mirrorState == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("mirrorState is null");
        }
        this.f6a = mirrorState;
    }

    public MirrorState a() {
        return this.f6a;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        return this.f6a.equals(((a) obj).a());
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public int getDisplayId() {
        return this.f6a.getDisplayId();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String getMirrorApp() {
        return this.f6a.getMirrorApp();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public String[] getMirrorAppCollection() {
        return this.f6a.getMirrorAppCollection();
    }

    public int hashCode() {
        return this.f6a.hashCode();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror() {
        return this.f6a.inMirror();
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorState
    public boolean inMirror(String str) {
        return this.f6a.inMirror(str);
    }

    @NonNull
    public String toString() {
        return this.f6a.toString();
    }
}
