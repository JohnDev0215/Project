package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.ZZeeef.eZZfeFZFF;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class eZZfeFZFF implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OverlayInfo f3045a;

    public eZZfeFZFF(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f3045a = overlayInfo;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof eZZfeFZFF)) {
            return false;
        }
        return ((eZZfeFZFF) obj).f3045a.equals(this.f3045a);
    }

    public int hashCode() {
        return this.f3045a.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f3045a.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f3045a.toString();
    }
}
