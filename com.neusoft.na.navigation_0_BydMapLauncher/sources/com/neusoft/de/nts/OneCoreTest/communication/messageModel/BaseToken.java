package com.neusoft.de.nts.OneCoreTest.communication.messageModel;

/* JADX INFO: loaded from: classes2.dex */
public class BaseToken {
    private Long sequenceId;

    public BaseToken() {
    }

    public BaseToken(Long l) {
        this.sequenceId = l;
    }

    public Long getSequenceId() {
        return this.sequenceId;
    }

    public void setSequenceId(Long l) {
        this.sequenceId = l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Long l = this.sequenceId;
        Long l2 = ((BaseToken) obj).sequenceId;
        return l != null ? l.equals(l2) : l2 == null;
    }

    public int hashCode() {
        Long l = this.sequenceId;
        if (l != null) {
            return l.hashCode();
        }
        return 0;
    }
}
