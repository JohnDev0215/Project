package mNTT77n7n.mNTT77n7n.mNTT77n7n.nMMnMM.mNTT77n7n;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class mNTT77n7n implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OverlayInfo f3164a;

    public mNTT77n7n(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f3164a = overlayInfo;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof mNTT77n7n)) {
            return false;
        }
        return ((mNTT77n7n) obj).f3164a.equals(this.f3164a);
    }

    public int hashCode() {
        return this.f3164a.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f3164a.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f3164a.toString();
    }
}
