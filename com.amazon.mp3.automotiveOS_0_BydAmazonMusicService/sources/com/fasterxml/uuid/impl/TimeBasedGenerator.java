package com.fasterxml.uuid.impl;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.NoArgGenerator;
import com.fasterxml.uuid.UUIDTimer;
import java.util.UUID;

/* JADX INFO: loaded from: classes6.dex */
public class TimeBasedGenerator extends NoArgGenerator {
    protected final EthernetAddress _ethernetAddress;
    protected final UUIDTimer _timer;
    protected final long _uuidL2;

    public TimeBasedGenerator(EthernetAddress ethernetAddress, UUIDTimer uUIDTimer) {
        byte[] bArr = new byte[16];
        ethernetAddress = ethernetAddress == null ? EthernetAddress.constructMulticastAddress() : ethernetAddress;
        this._ethernetAddress = ethernetAddress;
        ethernetAddress.toByteArray(bArr, 10);
        int clockSequence = uUIDTimer.getClockSequence();
        bArr[8] = (byte) (clockSequence >> 8);
        bArr[9] = (byte) clockSequence;
        this._uuidL2 = UUIDUtil.initUUIDSecondLong(UUIDUtil.gatherLong(bArr, 8));
        this._timer = uUIDTimer;
    }

    public UUID generate() {
        long timestamp = this._timer.getTimestamp();
        int i = (int) (timestamp >>> 32);
        return new UUID((((long) ((int) timestamp)) << 32) | ((((long) ((((i << 16) | (i >>> 16)) & (-61441)) | 4096)) << 32) >>> 32), this._uuidL2);
    }
}
