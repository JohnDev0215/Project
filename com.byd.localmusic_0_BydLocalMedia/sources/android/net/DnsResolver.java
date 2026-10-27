package android.net;

import android.net.DnsResolver;
import android.net.util.DnsUtils;
import android.os.CancellationSignal;
import android.os.Looper;
import android.os.MessageQueue;
import android.system.ErrnoException;
import android.system.OsConstants;
import com.android.net.module.util.DnsPacket;
import java.io.FileDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public final class DnsResolver {
    public static final int CLASS_IN = 1;
    public static final int ERROR_PARSE = 0;
    public static final int ERROR_SYSTEM = 1;
    private static final int FD_EVENTS = 5;
    public static final int FLAG_EMPTY = 0;
    public static final int FLAG_NO_CACHE_LOOKUP = 4;
    public static final int FLAG_NO_CACHE_STORE = 2;
    public static final int FLAG_NO_RETRY = 1;
    private static final int MAXPACKET = 8192;
    private static final int NETID_UNSET = 0;
    private static final int SLEEP_TIME_MS = 2;
    private static final String TAG = "DnsResolver";
    public static final int TYPE_A = 1;
    public static final int TYPE_AAAA = 28;
    private static final DnsResolver sInstance = new DnsResolver();

    public interface Callback<T> {
        void onAnswer(T t, int i);

        void onError(DnsException dnsException);
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface DnsError {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface QueryClass {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface QueryFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface QueryType {
    }

    public static DnsResolver getInstance() {
        return sInstance;
    }

    private DnsResolver() {
    }

    public static class DnsException extends Exception {
        public final int code;

        DnsException(int i, Throwable th) {
            super(th);
            this.code = i;
        }
    }

    public void rawQuery(Network network, byte[] bArr, int i, Executor executor, CancellationSignal cancellationSignal, final Callback<? super byte[]> callback) {
        int netIdForResolv;
        if (cancellationSignal == null || !cancellationSignal.isCanceled()) {
            Object obj = new Object();
            if (network != null) {
                try {
                    netIdForResolv = network.getNetIdForResolv();
                } catch (ErrnoException e) {
                    executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$h2SsAzA5_rVr-mzxppK8PJLQe98
                        @Override // java.lang.Runnable
                        public final void run() {
                            callback.onError(new DnsResolver.DnsException(1, e));
                        }
                    });
                    return;
                }
            } else {
                netIdForResolv = 0;
            }
            FileDescriptor fileDescriptorResNetworkSend = NetworkUtils.resNetworkSend(netIdForResolv, bArr, bArr.length, i);
            synchronized (obj) {
                registerFDListener(executor, fileDescriptorResNetworkSend, callback, cancellationSignal, obj);
                if (cancellationSignal == null) {
                    return;
                }
                addCancellationSignal(cancellationSignal, fileDescriptorResNetworkSend, obj);
            }
        }
    }

    public void rawQuery(Network network, String str, int i, int i2, int i3, Executor executor, CancellationSignal cancellationSignal, final Callback<? super byte[]> callback) {
        int netIdForResolv;
        if (cancellationSignal == null || !cancellationSignal.isCanceled()) {
            Object obj = new Object();
            if (network != null) {
                try {
                    netIdForResolv = network.getNetIdForResolv();
                } catch (ErrnoException e) {
                    executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$GTAgQiExADAzbCx0WiV_97W72-g
                        @Override // java.lang.Runnable
                        public final void run() {
                            callback.onError(new DnsResolver.DnsException(1, e));
                        }
                    });
                    return;
                }
            } else {
                netIdForResolv = 0;
            }
            FileDescriptor fileDescriptorResNetworkQuery = NetworkUtils.resNetworkQuery(netIdForResolv, str, i, i2, i3);
            synchronized (obj) {
                registerFDListener(executor, fileDescriptorResNetworkQuery, callback, cancellationSignal, obj);
                if (cancellationSignal == null) {
                    return;
                }
                addCancellationSignal(cancellationSignal, fileDescriptorResNetworkQuery, obj);
            }
        }
    }

    private class InetAddressAnswerAccumulator implements Callback<byte[]> {
        private DnsException mDnsException;
        private final Network mNetwork;
        private int mRcode;
        private final int mTargetAnswerCount;
        private final Callback<? super List<InetAddress>> mUserCallback;
        private int mReceivedAnswerCount = 0;
        private final List<InetAddress> mAllAnswers = new ArrayList();

        InetAddressAnswerAccumulator(Network network, int i, Callback<? super List<InetAddress>> callback) {
            this.mNetwork = network;
            this.mTargetAnswerCount = i;
            this.mUserCallback = callback;
        }

        private boolean maybeReportError() {
            int i = this.mRcode;
            if (i != 0) {
                this.mUserCallback.onAnswer(this.mAllAnswers, i);
                return true;
            }
            DnsException dnsException = this.mDnsException;
            if (dnsException == null) {
                return false;
            }
            this.mUserCallback.onError(dnsException);
            return true;
        }

        private void maybeReportAnswer() {
            int i = this.mReceivedAnswerCount + 1;
            this.mReceivedAnswerCount = i;
            if (i != this.mTargetAnswerCount) {
                return;
            }
            if (this.mAllAnswers.isEmpty() && maybeReportError()) {
                return;
            }
            this.mUserCallback.onAnswer(DnsUtils.rfc6724Sort(this.mNetwork, this.mAllAnswers), this.mRcode);
        }

        @Override // android.net.DnsResolver.Callback
        public void onAnswer(byte[] bArr, int i) {
            if (this.mReceivedAnswerCount == 0 || i == 0) {
                this.mRcode = i;
            }
            try {
                this.mAllAnswers.addAll(new DnsAddressAnswer(bArr).getAddresses());
            } catch (DnsPacket.ParseException e) {
                ParseException parseException = new ParseException(e.reason, e.getCause());
                parseException.setStackTrace(e.getStackTrace());
                this.mDnsException = new DnsException(0, parseException);
            }
            maybeReportAnswer();
        }

        @Override // android.net.DnsResolver.Callback
        public void onError(DnsException dnsException) {
            this.mDnsException = dnsException;
            maybeReportAnswer();
        }
    }

    public void query(Network network, String str, int i, Executor executor, CancellationSignal cancellationSignal, final Callback<? super List<InetAddress>> callback) throws ErrnoException {
        Network dnsNetwork;
        final FileDescriptor fileDescriptorResNetworkQuery;
        final FileDescriptor fileDescriptorResNetworkQuery2;
        if (cancellationSignal == null || !cancellationSignal.isCanceled()) {
            final Object obj = new Object();
            if (network != null) {
                dnsNetwork = network;
            } else {
                try {
                    dnsNetwork = NetworkUtils.getDnsNetwork();
                } catch (ErrnoException e) {
                    executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$vvKhya16sREGcN1Gxnqgw-LBoV4
                        @Override // java.lang.Runnable
                        public final void run() {
                            callback.onError(new DnsResolver.DnsException(1, e));
                        }
                    });
                    return;
                }
            }
            final boolean zHaveIpv6 = DnsUtils.haveIpv6(dnsNetwork);
            final boolean zHaveIpv4 = DnsUtils.haveIpv4(dnsNetwork);
            if (!zHaveIpv6 && !zHaveIpv4) {
                executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$kjq9c3feWPGKUPV3AzJBFi1GUvw
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback.onError(new DnsResolver.DnsException(1, new ErrnoException("resNetworkQuery", OsConstants.ENONET)));
                    }
                });
                return;
            }
            int i2 = 0;
            if (zHaveIpv6) {
                try {
                    fileDescriptorResNetworkQuery = NetworkUtils.resNetworkQuery(dnsNetwork.getNetIdForResolv(), str, 1, 28, i);
                    i2 = 1;
                } catch (ErrnoException e2) {
                    executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$uxb9gSgrd6Qyj9SLhCAtOvpxa3I
                        @Override // java.lang.Runnable
                        public final void run() {
                            callback.onError(new DnsResolver.DnsException(1, e2));
                        }
                    });
                    return;
                }
            } else {
                fileDescriptorResNetworkQuery = null;
            }
            try {
                Thread.sleep(2L);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (zHaveIpv4) {
                try {
                    i2++;
                    fileDescriptorResNetworkQuery2 = NetworkUtils.resNetworkQuery(dnsNetwork.getNetIdForResolv(), str, 1, 1, i);
                } catch (ErrnoException e3) {
                    if (zHaveIpv6) {
                        NetworkUtils.resNetworkCancel(fileDescriptorResNetworkQuery);
                    }
                    executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$t5xp-fS_zTQ564hG-PIaWJdBP8c
                        @Override // java.lang.Runnable
                        public final void run() {
                            callback.onError(new DnsResolver.DnsException(1, e3));
                        }
                    });
                    return;
                }
            } else {
                fileDescriptorResNetworkQuery2 = null;
            }
            InetAddressAnswerAccumulator inetAddressAnswerAccumulator = new InetAddressAnswerAccumulator(dnsNetwork, i2, callback);
            synchronized (obj) {
                if (zHaveIpv6) {
                    try {
                        registerFDListener(executor, fileDescriptorResNetworkQuery, inetAddressAnswerAccumulator, cancellationSignal, obj);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (zHaveIpv4) {
                    registerFDListener(executor, fileDescriptorResNetworkQuery2, inetAddressAnswerAccumulator, cancellationSignal, obj);
                }
                if (cancellationSignal == null) {
                    return;
                }
                cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.net.-$$Lambda$DnsResolver$DW9jYL2ZOH6BjebIVPhZIrrhoD8
                    @Override // android.os.CancellationSignal.OnCancelListener
                    public final void onCancel() {
                        this.f$0.lambda$query$6$DnsResolver(obj, zHaveIpv4, fileDescriptorResNetworkQuery2, zHaveIpv6, fileDescriptorResNetworkQuery);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$query$6$DnsResolver(Object obj, boolean z, FileDescriptor fileDescriptor, boolean z2, FileDescriptor fileDescriptor2) {
        synchronized (obj) {
            if (z) {
                try {
                    cancelQuery(fileDescriptor);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z2) {
                cancelQuery(fileDescriptor2);
            }
        }
    }

    public void query(Network network, String str, int i, int i2, Executor executor, CancellationSignal cancellationSignal, final Callback<? super List<InetAddress>> callback) {
        if (cancellationSignal == null || !cancellationSignal.isCanceled()) {
            Object obj = new Object();
            if (network == null) {
                try {
                    network = NetworkUtils.getDnsNetwork();
                } catch (ErrnoException e) {
                    executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$wc3_cnx2aezlAHvMEbQVFaTPAcE
                        @Override // java.lang.Runnable
                        public final void run() {
                            callback.onError(new DnsResolver.DnsException(1, e));
                        }
                    });
                    return;
                }
            }
            FileDescriptor fileDescriptorResNetworkQuery = NetworkUtils.resNetworkQuery(network.getNetIdForResolv(), str, 1, i, i2);
            InetAddressAnswerAccumulator inetAddressAnswerAccumulator = new InetAddressAnswerAccumulator(network, 1, callback);
            synchronized (obj) {
                registerFDListener(executor, fileDescriptorResNetworkQuery, inetAddressAnswerAccumulator, cancellationSignal, obj);
                if (cancellationSignal == null) {
                    return;
                }
                addCancellationSignal(cancellationSignal, fileDescriptorResNetworkQuery, obj);
            }
        }
    }

    public static final class DnsResponse {
        public final byte[] answerbuf;
        public final int rcode;

        public DnsResponse(byte[] bArr, int i) {
            this.answerbuf = bArr;
            this.rcode = i;
        }
    }

    private void registerFDListener(final Executor executor, FileDescriptor fileDescriptor, final Callback<? super byte[]> callback, final CancellationSignal cancellationSignal, final Object obj) {
        final MessageQueue queue = Looper.getMainLooper().getQueue();
        queue.addOnFileDescriptorEventListener(fileDescriptor, 5, new MessageQueue.OnFileDescriptorEventListener() { // from class: android.net.-$$Lambda$DnsResolver$kxKi6qjPYeR_SIipxW4tYpxyM50
            @Override // android.os.MessageQueue.OnFileDescriptorEventListener
            public final int onFileDescriptorEvents(FileDescriptor fileDescriptor2, int i) {
                return DnsResolver.lambda$registerFDListener$9(queue, executor, obj, cancellationSignal, callback, fileDescriptor2, i);
            }
        });
    }

    static /* synthetic */ int lambda$registerFDListener$9(MessageQueue messageQueue, Executor executor, final Object obj, final CancellationSignal cancellationSignal, final Callback callback, final FileDescriptor fileDescriptor, int i) {
        messageQueue.removeOnFileDescriptorEventListener(fileDescriptor);
        executor.execute(new Runnable() { // from class: android.net.-$$Lambda$DnsResolver$hIO7FFv0AXN6Nj0Dzka-LD8S870
            @Override // java.lang.Runnable
            public final void run() {
                DnsResolver.lambda$registerFDListener$8(obj, cancellationSignal, fileDescriptor, callback);
            }
        });
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ void lambda$registerFDListener$8(java.lang.Object r4, android.os.CancellationSignal r5, java.io.FileDescriptor r6, android.net.DnsResolver.Callback r7) {
        /*
            monitor-enter(r4)
            if (r5 == 0) goto Lb
            boolean r5 = r5.isCanceled()     // Catch: java.lang.Throwable -> L11
            if (r5 == 0) goto Lb
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L11
            return
        Lb:
            r5 = 0
            android.net.DnsResolver$DnsResponse r6 = android.net.NetworkUtils.resNetworkResult(r6)     // Catch: java.lang.Throwable -> L11 android.system.ErrnoException -> L13
            goto L32
        L11:
            r5 = move-exception
            goto L47
        L13:
            r6 = move-exception
            java.lang.String r0 = "DnsResolver"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L11
            r1.<init>()     // Catch: java.lang.Throwable -> L11
            java.lang.String r2 = "resNetworkResult:"
            r1.append(r2)     // Catch: java.lang.Throwable -> L11
            java.lang.String r2 = r6.toString()     // Catch: java.lang.Throwable -> L11
            r1.append(r2)     // Catch: java.lang.Throwable -> L11
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L11
            android.util.Log.e(r0, r1)     // Catch: java.lang.Throwable -> L11
            r3 = r6
            r6 = r5
            r5 = r3
        L32:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L11
            if (r5 == 0) goto L3f
            android.net.DnsResolver$DnsException r4 = new android.net.DnsResolver$DnsException
            r6 = 1
            r4.<init>(r6, r5)
            r7.onError(r4)
            return
        L3f:
            byte[] r4 = r6.answerbuf
            int r5 = r6.rcode
            r7.onAnswer(r4, r5)
            return
        L47:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L11
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.DnsResolver.lambda$registerFDListener$8(java.lang.Object, android.os.CancellationSignal, java.io.FileDescriptor, android.net.DnsResolver$Callback):void");
    }

    private void cancelQuery(FileDescriptor fileDescriptor) {
        if (fileDescriptor.valid()) {
            Looper.getMainLooper().getQueue().removeOnFileDescriptorEventListener(fileDescriptor);
            NetworkUtils.resNetworkCancel(fileDescriptor);
        }
    }

    private void addCancellationSignal(CancellationSignal cancellationSignal, final FileDescriptor fileDescriptor, final Object obj) {
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.net.-$$Lambda$DnsResolver$05nTktlCCI7FQsULCMbgIrjmrGc
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                this.f$0.lambda$addCancellationSignal$10$DnsResolver(obj, fileDescriptor);
            }
        });
    }

    public /* synthetic */ void lambda$addCancellationSignal$10$DnsResolver(Object obj, FileDescriptor fileDescriptor) {
        synchronized (obj) {
            cancelQuery(fileDescriptor);
        }
    }

    private static class DnsAddressAnswer extends DnsPacket {
        private static final boolean DBG = false;
        private static final String TAG = "DnsResolver.DnsAddressAnswer";
        private final int mQueryType;

        DnsAddressAnswer(byte[] bArr) throws DnsPacket.ParseException {
            super(bArr);
            if ((this.mHeader.flags & 32768) == 0) {
                throw new DnsPacket.ParseException("Not an answer packet");
            }
            if (this.mHeader.getRecordCount(0) == 0) {
                throw new DnsPacket.ParseException("No question found");
            }
            this.mQueryType = this.mRecords[0].get(0).nsType;
        }

        public List<InetAddress> getAddresses() {
            ArrayList arrayList = new ArrayList();
            if (this.mHeader.getRecordCount(1) == 0) {
                return arrayList;
            }
            for (DnsPacket.DnsRecord dnsRecord : this.mRecords[1]) {
                int i = dnsRecord.nsType;
                if (i == this.mQueryType && (i == 1 || i == 28)) {
                    try {
                        arrayList.add(InetAddress.getByAddress(dnsRecord.getRR()));
                    } catch (UnknownHostException unused) {
                    }
                }
            }
            return arrayList;
        }
    }
}
