package com.amazon.music.recents.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public enum ActivityType {
    PLAYED("PLAYED"),
    ADDED("ADDED"),
    PURCHASED("PURCHASED"),
    UPLOADED("UPLOADED"),
    DOWNLOADED("DOWNLOADED"),
    LIKED("LIKED");

    public final String value;

    ActivityType(String str) {
        this.value = str;
    }

    public static final List<String> toListString(List<ActivityType> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ActivityType> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().value);
        }
        return arrayList;
    }
}
