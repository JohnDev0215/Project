package RR9QRrr.RR9QRrr.RR9QRrr.RrRRrrrQr.RR9QRrr;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class RR9QRrr implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OverlayInfo f121a;

    public RR9QRrr(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f121a = overlayInfo;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof RR9QRrr)) {
            return false;
        }
        return ((RR9QRrr) obj).f121a.equals(this.f121a);
    }

    public int hashCode() {
        return this.f121a.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f121a.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f121a.toString();
    }
}
