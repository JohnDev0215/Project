package org.apache.commons.io;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class IOExceptionList extends IOException {
    private final List<? extends Throwable> causeList;

    public IOExceptionList(String str, List<? extends Throwable> list) {
        super(str, (list == null || list.isEmpty()) ? null : list.get(0));
        this.causeList = list == null ? Collections.emptyList() : list;
    }
}
