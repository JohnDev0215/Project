package GGW.GGW.GGW.WWHH.GGW;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class GGW implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OverlayInfo f54a;

    public GGW(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f54a = overlayInfo;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof GGW)) {
            return false;
        }
        return ((GGW) obj).f54a.equals(this.f54a);
    }

    public int hashCode() {
        return this.f54a.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f54a.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f54a.toString();
    }
}
