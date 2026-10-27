package org.mockito.internal.progress;

/* JADX INFO: loaded from: classes4.dex */
public class SequenceNumber {
    private static int sequenceNumber = 1;

    public static synchronized int next() {
        int i;
        i = sequenceNumber;
        sequenceNumber = i + 1;
        return i;
    }
}
