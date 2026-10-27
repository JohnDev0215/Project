package org.apache.commons.compress.changes;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ChangeSetResults {
    private final List<String> addedFromChangeSet = new ArrayList();
    private final List<String> addedFromStream = new ArrayList();
    private final List<String> deleted = new ArrayList();

    void deleted(String str) {
        this.deleted.add(str);
    }

    void addedFromStream(String str) {
        this.addedFromStream.add(str);
    }

    void addedFromChangeSet(String str) {
        this.addedFromChangeSet.add(str);
    }

    public List<String> getAddedFromChangeSet() {
        return this.addedFromChangeSet;
    }

    public List<String> getAddedFromStream() {
        return this.addedFromStream;
    }

    public List<String> getDeleted() {
        return this.deleted;
    }

    boolean hasBeenAdded(String str) {
        return this.addedFromChangeSet.contains(str) || this.addedFromStream.contains(str);
    }
}
