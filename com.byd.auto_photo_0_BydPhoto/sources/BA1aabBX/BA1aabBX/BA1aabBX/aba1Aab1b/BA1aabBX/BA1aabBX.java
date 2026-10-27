package BA1aabBX.BA1aabBX.BA1aabBX.aba1Aab1b.BA1aabBX;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class BA1aabBX implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OverlayInfo f13a;

    public BA1aabBX(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f13a = overlayInfo;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof BA1aabBX)) {
            return false;
        }
        return ((BA1aabBX) obj).f13a.equals(this.f13a);
    }

    public int hashCode() {
        return this.f13a.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f13a.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f13a.toString();
    }
}
