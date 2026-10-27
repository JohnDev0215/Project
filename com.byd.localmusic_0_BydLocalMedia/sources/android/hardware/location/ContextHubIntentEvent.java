package android.hardware.location;

import android.annotation.SystemApi;
import android.content.Intent;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class ContextHubIntentEvent {
    private final ContextHubInfo mContextHubInfo;
    private final int mEventType;
    private final int mNanoAppAbortCode;
    private final long mNanoAppId;
    private final NanoAppMessage mNanoAppMessage;

    private ContextHubIntentEvent(ContextHubInfo contextHubInfo, int i, long j, NanoAppMessage nanoAppMessage, int i2) {
        this.mContextHubInfo = contextHubInfo;
        this.mEventType = i;
        this.mNanoAppId = j;
        this.mNanoAppMessage = nanoAppMessage;
        this.mNanoAppAbortCode = i2;
    }

    private ContextHubIntentEvent(ContextHubInfo contextHubInfo, int i) {
        this(contextHubInfo, i, -1L, null, -1);
    }

    private ContextHubIntentEvent(ContextHubInfo contextHubInfo, int i, long j) {
        this(contextHubInfo, i, j, null, -1);
    }

    private ContextHubIntentEvent(ContextHubInfo contextHubInfo, int i, long j, NanoAppMessage nanoAppMessage) {
        this(contextHubInfo, i, j, nanoAppMessage, -1);
    }

    private ContextHubIntentEvent(ContextHubInfo contextHubInfo, int i, long j, int i2) {
        this(contextHubInfo, i, j, null, i2);
    }

    public static ContextHubIntentEvent fromIntent(Intent intent) {
        Objects.requireNonNull(intent, "Intent cannot be null");
        hasExtraOrThrow(intent, ContextHubManager.EXTRA_CONTEXT_HUB_INFO);
        ContextHubInfo contextHubInfo = (ContextHubInfo) intent.getParcelableExtra(ContextHubManager.EXTRA_CONTEXT_HUB_INFO);
        if (contextHubInfo == null) {
            throw new IllegalArgumentException("ContextHubInfo extra was null");
        }
        int intExtraOrThrow = getIntExtraOrThrow(intent, ContextHubManager.EXTRA_EVENT_TYPE);
        switch (intExtraOrThrow) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                long longExtraOrThrow = getLongExtraOrThrow(intent, ContextHubManager.EXTRA_NANOAPP_ID);
                if (intExtraOrThrow != 5) {
                    if (intExtraOrThrow == 4) {
                        return new ContextHubIntentEvent(contextHubInfo, intExtraOrThrow, longExtraOrThrow, getIntExtraOrThrow(intent, ContextHubManager.EXTRA_NANOAPP_ABORT_CODE));
                    }
                    return new ContextHubIntentEvent(contextHubInfo, intExtraOrThrow, longExtraOrThrow);
                }
                hasExtraOrThrow(intent, ContextHubManager.EXTRA_MESSAGE);
                NanoAppMessage nanoAppMessage = (NanoAppMessage) intent.getParcelableExtra(ContextHubManager.EXTRA_MESSAGE);
                if (nanoAppMessage == null) {
                    throw new IllegalArgumentException("NanoAppMessage extra was null");
                }
                return new ContextHubIntentEvent(contextHubInfo, intExtraOrThrow, longExtraOrThrow, nanoAppMessage);
            case 6:
                return new ContextHubIntentEvent(contextHubInfo, intExtraOrThrow);
            default:
                throw new IllegalArgumentException("Unknown intent event type " + intExtraOrThrow);
        }
    }

    public int getEventType() {
        return this.mEventType;
    }

    public ContextHubInfo getContextHubInfo() {
        return this.mContextHubInfo;
    }

    public long getNanoAppId() {
        if (this.mEventType == 6) {
            throw new UnsupportedOperationException("Cannot invoke getNanoAppId() on Context Hub reset event");
        }
        return this.mNanoAppId;
    }

    public int getNanoAppAbortCode() {
        if (this.mEventType != 4) {
            throw new UnsupportedOperationException("Cannot invoke getNanoAppAbortCode() on non-abort event: " + this.mEventType);
        }
        return this.mNanoAppAbortCode;
    }

    public NanoAppMessage getNanoAppMessage() {
        if (this.mEventType != 5) {
            throw new UnsupportedOperationException("Cannot invoke getNanoAppMessage() on non-message event: " + this.mEventType);
        }
        return this.mNanoAppMessage;
    }

    public String toString() {
        String str = "ContextHubIntentEvent[eventType = " + this.mEventType + ", contextHubId = " + this.mContextHubInfo.getId();
        if (this.mEventType != 6) {
            str = str + ", nanoAppId = 0x" + Long.toHexString(this.mNanoAppId);
        }
        if (this.mEventType == 4) {
            str = str + ", nanoAppAbortCode = " + this.mNanoAppAbortCode;
        }
        if (this.mEventType == 5) {
            str = str + ", nanoAppMessage = " + this.mNanoAppMessage;
        }
        return str + "]";
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z2 = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextHubIntentEvent)) {
            return false;
        }
        ContextHubIntentEvent contextHubIntentEvent = (ContextHubIntentEvent) obj;
        if (contextHubIntentEvent.getEventType() != this.mEventType || !contextHubIntentEvent.getContextHubInfo().equals(this.mContextHubInfo)) {
            return false;
        }
        try {
            if (this.mEventType != 6) {
                z = (contextHubIntentEvent.getNanoAppId() == this.mNanoAppId) & true;
            } else {
                z = true;
            }
            if (this.mEventType == 4) {
                if (contextHubIntentEvent.getNanoAppAbortCode() != this.mNanoAppAbortCode) {
                    z2 = false;
                }
                z &= z2;
            }
            return this.mEventType == 5 ? contextHubIntentEvent.getNanoAppMessage().equals(this.mNanoAppMessage) & z : z;
        } catch (UnsupportedOperationException unused) {
            return false;
        }
    }

    private static void hasExtraOrThrow(Intent intent, String str) {
        if (intent.hasExtra(str)) {
            return;
        }
        throw new IllegalArgumentException("Intent did not have extra: " + str);
    }

    private static int getIntExtraOrThrow(Intent intent, String str) {
        hasExtraOrThrow(intent, str);
        return intent.getIntExtra(str, -1);
    }

    private static long getLongExtraOrThrow(Intent intent, String str) {
        hasExtraOrThrow(intent, str);
        return intent.getLongExtra(str, -1L);
    }
}
