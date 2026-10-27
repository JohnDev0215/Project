package com.byd.bt.model;

import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedBtDevice extends BydData {
    public static final int CONTACTS_STATE_NO_DATA = -1;
    public static final int CONTACTS_STATE_START_SYNC = 1;
    public static final int CONTACTS_STATE_SYNCHRONIZING = 2;
    public static final int CONTACTS_STATE_SYNC_COMPLETED = 0;
    public static final int CONTACTS_STATE_SYNC_FAILED = 3;
    public static final int CONTACTS_STATE_SYNC_SUCCESS = 4;
    private BondDevice device;
    private boolean noContacts = false;

    public BondDevice getDevice() {
        return this.device;
    }

    public boolean isNoContacts() {
        return this.noContacts;
    }

    public void setDevice(BondDevice bondDevice) {
        this.device = bondDevice;
    }

    public void setNoContacts(boolean z7) {
        this.noContacts = z7;
    }

    public String toString() {
        return "ConnectedBtDevice{device=" + this.device + ", noContacts=" + this.noContacts + '}';
    }
}
