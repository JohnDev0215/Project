package com.android.internal.infra;

import android.content.ComponentName;
import android.util.ArraySet;
import android.util.SparseArray;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class GlobalWhitelistState {
    protected final Object mGlobalWhitelistStateLock = new Object();
    protected SparseArray<WhitelistHelper> mWhitelisterHelpers;

    public void setWhitelist(int i, List<String> list, List<ComponentName> list2) {
        synchronized (this.mGlobalWhitelistStateLock) {
            if (this.mWhitelisterHelpers == null) {
                this.mWhitelisterHelpers = new SparseArray<>(1);
            }
            WhitelistHelper whitelistHelper = this.mWhitelisterHelpers.get(i);
            if (whitelistHelper == null) {
                whitelistHelper = new WhitelistHelper();
                this.mWhitelisterHelpers.put(i, whitelistHelper);
            }
            whitelistHelper.setWhitelist(list, list2);
        }
    }

    public boolean isWhitelisted(int i, String str) {
        synchronized (this.mGlobalWhitelistStateLock) {
            boolean zIsWhitelisted = false;
            if (this.mWhitelisterHelpers == null) {
                return false;
            }
            WhitelistHelper whitelistHelper = this.mWhitelisterHelpers.get(i);
            if (whitelistHelper != null) {
                zIsWhitelisted = whitelistHelper.isWhitelisted(str);
            }
            return zIsWhitelisted;
        }
    }

    public boolean isWhitelisted(int i, ComponentName componentName) {
        synchronized (this.mGlobalWhitelistStateLock) {
            boolean zIsWhitelisted = false;
            if (this.mWhitelisterHelpers == null) {
                return false;
            }
            WhitelistHelper whitelistHelper = this.mWhitelisterHelpers.get(i);
            if (whitelistHelper != null) {
                zIsWhitelisted = whitelistHelper.isWhitelisted(componentName);
            }
            return zIsWhitelisted;
        }
    }

    public ArraySet<ComponentName> getWhitelistedComponents(int i, String str) {
        synchronized (this.mGlobalWhitelistStateLock) {
            ArraySet<ComponentName> whitelistedComponents = null;
            if (this.mWhitelisterHelpers == null) {
                return null;
            }
            WhitelistHelper whitelistHelper = this.mWhitelisterHelpers.get(i);
            if (whitelistHelper != null) {
                whitelistedComponents = whitelistHelper.getWhitelistedComponents(str);
            }
            return whitelistedComponents;
        }
    }

    public void resetWhitelist(int i) {
        synchronized (this.mGlobalWhitelistStateLock) {
            if (this.mWhitelisterHelpers == null) {
                return;
            }
            this.mWhitelisterHelpers.remove(i);
            if (this.mWhitelisterHelpers.size() == 0) {
                this.mWhitelisterHelpers = null;
            }
        }
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.print("State: ");
        synchronized (this.mGlobalWhitelistStateLock) {
            if (this.mWhitelisterHelpers == null) {
                printWriter.println("empty");
                return;
            }
            printWriter.print(this.mWhitelisterHelpers.size());
            printWriter.println(" services");
            String str2 = str + "  ";
            for (int i = 0; i < this.mWhitelisterHelpers.size(); i++) {
                int iKeyAt = this.mWhitelisterHelpers.keyAt(i);
                this.mWhitelisterHelpers.valueAt(i).dump(str2, "Whitelist for userId " + iKeyAt, printWriter);
            }
        }
    }
}
