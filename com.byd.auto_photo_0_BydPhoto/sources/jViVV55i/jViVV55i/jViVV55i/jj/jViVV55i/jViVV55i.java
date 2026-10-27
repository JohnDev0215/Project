package jViVV55i.jViVV55i.jViVV55i.jj.jViVV55i;

import android.content.om.OverlayInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkOverlayInfo.class, singleton = true)
public class jViVV55i implements IDiLinkOverlayInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ boolean f3067a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private OverlayInfo f3068b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f3069c;

    public jViVV55i(OverlayInfo overlayInfo) {
        if (overlayInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("overlayInfo is null");
        }
        this.f3068b = overlayInfo;
        if (!f3067a && overlayInfo == null) {
            throw new AssertionError();
        }
        this.f3069c = overlayInfo.isStatic;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof jViVV55i)) {
            return false;
        }
        return ((jViVV55i) obj).f3068b.equals(this.f3068b);
    }

    public int hashCode() {
        return this.f3068b.hashCode();
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayInfo
    public boolean isEnabled() {
        return this.f3068b.isEnabled();
    }

    @NonNull
    public String toString() {
        return this.f3068b.toString();
    }
}
