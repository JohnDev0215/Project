package lkl6Kl.lkl6Kl.lkl6Kl.ULUlK.lkl6Kl;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class lkl6Kl implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ boolean f3113a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private OverlayInfo f3114b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f3115c;

    public lkl6Kl(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f3114b = overlayInfo;
        if (!f3113a && overlayInfo == null) {
            throw new AssertionError();
        }
        this.f3115c = overlayInfo.isStatic;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof lkl6Kl)) {
            return false;
        }
        return ((lkl6Kl) obj).f3114b.equals(this.f3114b);
    }

    public int hashCode() {
        return this.f3114b.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f3114b.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f3114b.toString();
    }
}
