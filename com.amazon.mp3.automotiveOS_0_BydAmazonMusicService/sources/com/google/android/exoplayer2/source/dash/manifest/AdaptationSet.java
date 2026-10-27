package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class AdaptationSet {
    public final List<Descriptor> accessibilityDescriptors;
    public final int id;
    public final List<Representation> representations;
    public final List<Descriptor> supplementalProperties;
    public final int type;

    public AdaptationSet(int i, int i2, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3) {
        List<Descriptor> listUnmodifiableList;
        List<Descriptor> listUnmodifiableList2;
        this.id = i;
        this.type = i2;
        this.representations = Collections.unmodifiableList(list);
        if (list2 == null) {
            listUnmodifiableList = Collections.emptyList();
        } else {
            listUnmodifiableList = Collections.unmodifiableList(list2);
        }
        this.accessibilityDescriptors = listUnmodifiableList;
        if (list3 == null) {
            listUnmodifiableList2 = Collections.emptyList();
        } else {
            listUnmodifiableList2 = Collections.unmodifiableList(list3);
        }
        this.supplementalProperties = listUnmodifiableList2;
    }
}
