package com.amazon.music.views.library.models.base;

import com.amazon.music.views.library.views.HotSpotPulse;
import com.amazon.music.views.library.views.HotSpotToolTipDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HotspotSupportedView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H&J?\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/models/base/HotspotSupportedView;", "", "Lcom/amazon/music/views/library/views/HotSpotPulse;", "getHotspotView", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog;", "createTooltipView", "", "left", "top", "right", "bottom", "", "setHotspotMargins", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getHotSpotPulseView", "()Lcom/amazon/music/views/library/views/HotSpotPulse;", "hotSpotPulseView", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface HotspotSupportedView {
    HotSpotToolTipDialog createTooltipView();

    HotSpotPulse getHotSpotPulseView();

    HotSpotPulse getHotspotView();

    void setHotspotMargins(Integer left, Integer top, Integer right, Integer bottom);

    /* JADX INFO: compiled from: HotspotSupportedView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static HotSpotPulse getHotspotView(HotspotSupportedView hotspotSupportedView) {
            Intrinsics.checkNotNullParameter(hotspotSupportedView, "this");
            return hotspotSupportedView.getHotSpotPulseView();
        }

        public static /* synthetic */ void setHotspotMargins$default(HotspotSupportedView hotspotSupportedView, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setHotspotMargins");
            }
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                num2 = null;
            }
            if ((i & 4) != 0) {
                num3 = null;
            }
            if ((i & 8) != 0) {
                num4 = null;
            }
            hotspotSupportedView.setHotspotMargins(num, num2, num3, num4);
        }
    }
}
