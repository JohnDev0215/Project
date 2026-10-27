package com.amazon.avod.upgrade;

import com.amazon.avod.util.DLog;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public class SequentialUpgradeManager<T> implements UpgradeManager<T> {
    private final SortedMap<Integer, UpgradeAction<? super T>> mSortedMap = new TreeMap();

    public void register(int i, UpgradeAction<? super T> upgradeAction) {
        upgradeAction.getClass();
        this.mSortedMap.put(Integer.valueOf(i), upgradeAction);
    }

    @Override // com.amazon.avod.upgrade.UpgradeManager
    public void upgrade(T t, int i, int i2) {
        DLog.logf("Upgrading %s from %d to %d", t, Integer.valueOf(i), Integer.valueOf(i2));
        if (i >= i2) {
            throw new IllegalStateException(String.format(Locale.US, "fromVersion(%d) should be less than toVersion(%d)", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        for (UpgradeAction<? super T> upgradeAction : this.mSortedMap.subMap(Integer.valueOf(i), Integer.valueOf(i2)).values()) {
            DLog.logf("Upgrading %s from %d to %d. Applying action [%s].", t, Integer.valueOf(i), Integer.valueOf(i2), upgradeAction);
            upgradeAction.applyUpgrade(t);
            DLog.logf("Action [%s] complete.", upgradeAction);
        }
    }
}
