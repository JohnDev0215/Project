package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabList.java */
/* JADX INFO: loaded from: classes2.dex */
public interface w03 {
    int getCount();

    Tab getTabAt(int i);

    int index();

    boolean isIncognito();

    int r(Tab tab);
}
