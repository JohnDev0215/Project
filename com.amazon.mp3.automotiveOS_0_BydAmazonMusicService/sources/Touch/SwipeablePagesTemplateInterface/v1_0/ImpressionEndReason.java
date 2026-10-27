package Touch.SwipeablePagesTemplateInterface.v1_0;

import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes.dex */
public enum ImpressionEndReason {
    AUTO(DebugKt.DEBUG_PROPERTY_VALUE_AUTO),
    MANUAL("manual"),
    SWITCH_TO_PREVIEW("switchToPreview"),
    SWITCH_TO_FULLSCREEN("switchToFullscreen"),
    USER_NAVIGATION("userNavigation");

    private final String value;

    ImpressionEndReason(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
