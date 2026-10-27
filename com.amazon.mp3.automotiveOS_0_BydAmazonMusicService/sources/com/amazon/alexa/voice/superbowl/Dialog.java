package com.amazon.alexa.voice.superbowl;

import com.amazon.alexa.voice.core.internal.util.RandomString;

/* JADX INFO: loaded from: classes2.dex */
public final class Dialog {
    private volatile String requestId = RandomString.string();

    public String newRequestId() {
        synchronized (this) {
            this.requestId = RandomString.string();
        }
        return this.requestId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Dialog.class != obj.getClass()) {
            return false;
        }
        Dialog dialog = (Dialog) obj;
        return this.requestId != null ? this.requestId.equals(dialog.requestId) : dialog.requestId == null;
    }

    public int hashCode() {
        if (this.requestId != null) {
            return this.requestId.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Dialog{requestId='" + this.requestId + "'}";
    }
}
