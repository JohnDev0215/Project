package org.apache.commons.net.tftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public class TFTPClient extends TFTP {
    public static final int DEFAULT_MAX_TIMEOUTS = 5;
    private int maxTimeouts = 5;
    private long totalBytesReceived;
    private long totalBytesSent;

    public void setMaxTimeouts(int numTimeouts) {
        if (numTimeouts < 1) {
            this.maxTimeouts = 1;
        } else {
            this.maxTimeouts = numTimeouts;
        }
    }

    public int getMaxTimeouts() {
        return this.maxTimeouts;
    }

    public long getTotalBytesReceived() {
        return this.totalBytesReceived;
    }

    public long getTotalBytesSent() {
        return this.totalBytesSent;
    }

    /* JADX WARN: Code restructure failed: missing block: B:137:0x02bd, code lost:
    
        r12.setBlockNumber(r7);
        r13 = r12;
        r5 = r5 + r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x02c4, code lost:
    
        r25 = r6;
        r11 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x02c9, code lost:
    
        r22.totalBytesReceived += (long) r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02cd, code lost:
    
        if (r14 == 512) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x02cf, code lost:
    
        bufferedSend(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02d2, code lost:
    
        endBufferedOps();
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02d6, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x02e6, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02fb A[LOOP:1: B:173:0x0045->B:149:0x02fb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02bd A[EDGE_INSN: B:160:0x02bd->B:137:0x02bd BREAK  A[LOOP:1: B:173:0x0045->B:149:0x02fb], EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0306 A[SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int receiveFile(java.lang.String r23, int r24, java.io.OutputStream r25, java.net.InetAddress r26, int r27) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 793
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.tftp.TFTPClient.receiveFile(java.lang.String, int, java.io.OutputStream, java.net.InetAddress, int):int");
    }

    public int receiveFile(String fileName, int mode, OutputStream output, String hostname, int port) throws IOException {
        return receiveFile(fileName, mode, output, InetAddress.getByName(hostname), port);
    }

    public int receiveFile(String fileName, int mode, OutputStream output, InetAddress host) throws IOException {
        return receiveFile(fileName, mode, output, host, 69);
    }

    public int receiveFile(String fileName, int mode, OutputStream output, String hostname) throws IOException {
        return receiveFile(fileName, mode, output, InetAddress.getByName(hostname), 69);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01e6, code lost:
    
        endBufferedOps();
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01ea, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01eb, code lost:
    
        r0 = 512;
        r2 = 4;
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01f0, code lost:
    
        if (r0 <= 0) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01f2, code lost:
    
        r10 = r14.read(r19.sendBuffer, r2, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01f9, code lost:
    
        if (r10 <= 0) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01fb, code lost:
    
        r2 = r2 + r10;
        r0 = r0 - r10;
        r6 = r6 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01ff, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0209, code lost:
    
        if (r6 >= 512) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x020b, code lost:
    
        r13 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x020d, code lost:
    
        r3.setBlockNumber(r7);
        r3.setData(r19.sendBuffer, 4, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0218, code lost:
    
        r2 = r3;
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x021e, code lost:
    
        r18 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0224, code lost:
    
        r19.totalBytesSent += (long) r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0232, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0239, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0242, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01e3, code lost:
    
        if (r13 == false) goto L102;
     */
    /* JADX WARN: Removed duplicated region for block: B:125:0x024c A[LOOP:1: B:143:0x0049->B:125:0x024c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0259 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01e3 A[EDGE_INSN: B:168:0x01e3->B:99:0x01e3 BREAK  A[LOOP:1: B:143:0x0049->B:125:0x024c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01de  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendFile(java.lang.String r20, int r21, java.io.InputStream r22, java.net.InetAddress r23, int r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 629
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.tftp.TFTPClient.sendFile(java.lang.String, int, java.io.InputStream, java.net.InetAddress, int):void");
    }

    public void sendFile(String fileName, int mode, InputStream input, String hostname, int port) throws Throwable {
        sendFile(fileName, mode, input, InetAddress.getByName(hostname), port);
    }

    public void sendFile(String fileName, int mode, InputStream input, InetAddress host) throws Throwable {
        sendFile(fileName, mode, input, host, 69);
    }

    public void sendFile(String fileName, int mode, InputStream input, String hostname) throws Throwable {
        sendFile(fileName, mode, input, InetAddress.getByName(hostname), 69);
    }
}
