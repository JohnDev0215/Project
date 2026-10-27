package com.byd.vrdownload.utils;

/* JADX INFO: loaded from: classes.dex */
public class IdWorker {
    private long datacenterId;
    private long maxDatacenterId;
    private long maxWorkerId;
    private long sequence;
    private long workerId;
    private long twepoch = 1288834974657L;
    private long workerIdBits = 5;
    private long datacenterIdBits = 5;
    private long sequenceBits = 12;
    private long workerIdShift = 12;
    private long datacenterIdShift = 12 + 5;
    private long timestampLeftShift = (12 + 5) + 5;
    private long sequenceMask = ((-1) << ((int) 12)) ^ (-1);
    private long lastTimestamp = -1;

    public IdWorker(long j, long j2, long j3) {
        long j4 = ((-1) << ((int) 5)) ^ (-1);
        this.maxWorkerId = j4;
        long j5 = ((-1) << ((int) 5)) ^ (-1);
        this.maxDatacenterId = j5;
        if (j > j4 || j < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", Long.valueOf(this.maxWorkerId)));
        }
        if (j2 > j5 || j2 < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", Long.valueOf(this.maxDatacenterId)));
        }
        this.workerId = j;
        this.datacenterId = j2;
        this.sequence = j3;
    }

    public long getWorkerId() {
        return this.workerId;
    }

    public long getDatacenterId() {
        return this.datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long jTimeGen;
        jTimeGen = timeGen();
        long j = this.lastTimestamp;
        if (jTimeGen < j) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d.", Long.valueOf(this.lastTimestamp));
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", Long.valueOf(this.lastTimestamp - jTimeGen)));
        }
        if (j == jTimeGen) {
            long j2 = (this.sequence + 1) & this.sequenceMask;
            this.sequence = j2;
            if (j2 == 0) {
                jTimeGen = tilNextMillis(j);
            }
        } else {
            this.sequence = 0L;
        }
        this.lastTimestamp = jTimeGen;
        return ((jTimeGen - this.twepoch) << ((int) this.timestampLeftShift)) | (this.datacenterId << ((int) this.datacenterIdShift)) | (this.workerId << ((int) this.workerIdShift)) | this.sequence;
    }

    private long tilNextMillis(long j) {
        long jTimeGen = timeGen();
        while (jTimeGen <= j) {
            jTimeGen = timeGen();
        }
        return jTimeGen;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] strArr) {
        IdWorker idWorker = new IdWorker(1L, 1L, 1L);
        for (int i = 0; i < 30; i++) {
            System.out.println(idWorker.nextId());
        }
    }
}
