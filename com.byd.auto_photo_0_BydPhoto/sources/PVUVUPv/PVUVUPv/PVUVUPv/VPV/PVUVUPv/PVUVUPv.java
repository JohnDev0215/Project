package PVUVUPv.PVUVUPv.PVUVUPv.VPV.PVUVUPv;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class PVUVUPv implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OverlayInfo f76a;

    public PVUVUPv(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f76a = overlayInfo;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof PVUVUPv)) {
            return false;
        }
        return ((PVUVUPv) obj).f76a.equals(this.f76a);
    }

    public int hashCode() {
        return this.f76a.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f76a.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f76a.toString();
    }
}
