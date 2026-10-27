package com.amazon.music.metrics.mts.event.types;

/* JADX INFO: loaded from: classes4.dex */
public class SelectionSourceInfo {
    private String mSelectionSourceId;
    private String mSelectionSourceSessionId;
    private SelectionSourceType mSelectionSourceType;

    public SelectionSourceInfo(SelectionSourceType selectionSourceType, String str) {
        this(selectionSourceType, str, null);
    }

    public SelectionSourceInfo(SelectionSourceType selectionSourceType, String str, String str2) {
        this.mSelectionSourceType = selectionSourceType;
        this.mSelectionSourceId = str;
        this.mSelectionSourceSessionId = str2;
    }

    public SelectionSourceType getSelectionSourceType() {
        return this.mSelectionSourceType;
    }

    public String getSelectionSourceId() {
        return this.mSelectionSourceId;
    }

    public String getSelectionSourceSessionId() {
        return this.mSelectionSourceSessionId;
    }
}
