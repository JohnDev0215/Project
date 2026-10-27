package com.ecarx.xui.adaptapi.tbox.journallog;

/* JADX INFO: loaded from: classes2.dex */
public interface IJournalLog {

    public interface JournalLogStatusCallback {
        void onJournalLogServiceOnOff(boolean z);
    }

    boolean isJournalLogServiceOn();

    boolean setJournalLogServiceOn(boolean z);

    void setJournalLogStatusCallback(JournalLogStatusCallback journalLogStatusCallback);

    void unsetJournalLogStatusCallback(JournalLogStatusCallback journalLogStatusCallback);
}
