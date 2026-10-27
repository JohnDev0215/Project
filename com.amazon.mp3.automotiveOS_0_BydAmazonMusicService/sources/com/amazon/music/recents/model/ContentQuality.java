package com.amazon.music.recents.model;

import UpsellInterface.v1_0.UpsellType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public enum ContentQuality {
    STANDARD(UpsellType.FREE),
    HIGHQUALITY("HIGHQUALITY");

    public final String value;

    ContentQuality(String str) {
        this.value = str;
    }

    public static final List<String> toListString(List<ContentQuality> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ContentQuality> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().value);
        }
        return arrayList;
    }
}
