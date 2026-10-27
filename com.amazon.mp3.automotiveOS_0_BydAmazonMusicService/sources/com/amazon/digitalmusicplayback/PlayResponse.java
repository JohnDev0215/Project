package com.amazon.digitalmusicplayback;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class PlayResponse {
    final EngineError error;
    final ArrayList<SequenceEntry> sequenceEntries;

    public PlayResponse(EngineError engineError, ArrayList<SequenceEntry> arrayList) {
        this.error = engineError;
        this.sequenceEntries = arrayList;
    }

    public EngineError getError() {
        return this.error;
    }

    public ArrayList<SequenceEntry> getSequenceEntries() {
        return this.sequenceEntries;
    }

    public String toString() {
        return "PlayResponse{error=" + this.error + ",sequenceEntries=" + this.sequenceEntries + "}";
    }
}
