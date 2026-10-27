package org.chromium.chrome.browser.complex_tasks;

import defpackage.ly2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: loaded from: classes.dex */
public class TaskTabHelper {
    @CalledByNative
    public static long getParentRootTaskId(Tab tab) {
        Long l = (Long) ly2.a(tab).b("ParentRootTaskId");
        if (l == null) {
            return -1L;
        }
        return l.longValue();
    }

    @CalledByNative
    public static long getParentTaskId(Tab tab) {
        Long l = (Long) ly2.a(tab).b("ParentTaskId");
        if (l == null) {
            return -1L;
        }
        return l.longValue();
    }
}
