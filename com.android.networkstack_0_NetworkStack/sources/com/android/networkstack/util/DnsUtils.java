package com.android.networkstack.util;

import android.net.DnsResolver;
import android.net.Network;
import android.net.TrafficStats;
import android.net.util.Stopwatch;
import android.util.Log;
import com.android.server.connectivity.NetworkMonitor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class DnsUtils {
    private static final boolean DBG = Log.isLoggable(DnsUtils.class.getSimpleName(), 3);
    private static final String TAG = "DnsUtils";

    private static String dnsTypeToStr(int i) {
        return i != -1 ? i != 1 ? i != 28 ? "UNDEFINED" : "AAAA" : "A" : "ADDRCONFIG";
    }

    public static InetAddress[] getAllByName(DnsResolver dnsResolver, Network network, String str, int i, NetworkMonitor.DnsLogFunc dnsLogFunc) throws UnknownHostException {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder(str);
        try {
            arrayList.addAll(Arrays.asList(getAllByName(dnsResolver, network, str, 28, 4, i, dnsLogFunc)));
        } catch (UnknownHostException e) {
            sb.append(String.format(" (%s)%s", dnsTypeToStr(28), e.getMessage()));
        }
        try {
            arrayList.addAll(Arrays.asList(getAllByName(dnsResolver, network, str, 1, 4, i, dnsLogFunc)));
        } catch (UnknownHostException e2) {
            sb.append(String.format(" (%s)%s", dnsTypeToStr(1), e2.getMessage()));
        }
        if (arrayList.size() == 0) {
            dnsLogFunc.log("FAIL: " + sb.toString());
            throw new UnknownHostException(str);
        }
        dnsLogFunc.log("OK: " + str + " " + arrayList.toString());
        return (InetAddress[]) arrayList.toArray(new InetAddress[0]);
    }

    public static InetAddress[] getAllByName(DnsResolver dnsResolver, Network network, final String str, int i, int i2, int i3, NetworkMonitor.DnsLogFunc dnsLogFunc) throws UnknownHostException {
        long jStop;
        List list;
        final CompletableFuture completableFuture = new CompletableFuture();
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        DnsResolver.Callback<List<InetAddress>> callback = new DnsResolver.Callback<List<InetAddress>>() { // from class: com.android.networkstack.util.DnsUtils.1
            @Override // android.net.DnsResolver.Callback
            public void onAnswer(List<InetAddress> list2, int i4) {
                if (i4 == 0 && list2 != null && list2.size() != 0) {
                    completableFuture.complete(list2);
                } else {
                    completableFuture.completeExceptionally(new UnknownHostException());
                }
            }

            @Override // android.net.DnsResolver.Callback
            public void onError(DnsResolver.DnsException dnsException) {
                if (DnsUtils.DBG) {
                    Log.d(DnsUtils.TAG, "DNS error resolving " + str, dnsException);
                }
                completableFuture.completeExceptionally(dnsException);
            }
        };
        int andSetThreadStatsTag = TrafficStats.getAndSetThreadStatsTag(-127);
        if (i == -1) {
            dnsResolver.query(network, str, i2, new Executor() { // from class: com.android.networkstack.util.-$$Lambda$DnsUtils$wfjeqM32iamO5uPDgvFfb-CPZOg
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    runnable.run();
                }
            }, null, callback);
        } else {
            dnsResolver.query(network, str, i, i2, new Executor() { // from class: com.android.networkstack.util.-$$Lambda$DnsUtils$yTVtff-6rgd-poAiwKNFKah2CA4
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    runnable.run();
                }
            }, null, callback);
        }
        TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
        String message = null;
        List list2 = null;
        try {
            try {
                List list3 = (List) completableFuture.get(i3, TimeUnit.MILLISECONDS);
                logDnsResult(list3, stopwatch.stop() / 1000, dnsLogFunc, i, null);
                list2 = list3;
            } catch (InterruptedException | TimeoutException unused) {
                message = "Timeout";
                jStop = stopwatch.stop() / 1000;
                list = null;
                logDnsResult(list, jStop, dnsLogFunc, i, message);
            } catch (ExecutionException e) {
                message = e.getMessage();
                jStop = stopwatch.stop() / 1000;
                list = null;
                logDnsResult(list, jStop, dnsLogFunc, i, message);
            }
            if (message != null) {
                throw new UnknownHostException(str);
            }
            return (InetAddress[]) list2.toArray(new InetAddress[0]);
        } catch (Throwable th) {
            logDnsResult(null, stopwatch.stop() / 1000, dnsLogFunc, i, null);
            throw th;
        }
    }

    private static void logDnsResult(List<InetAddress> list, long j, NetworkMonitor.DnsLogFunc dnsLogFunc, int i, String str) {
        if (dnsLogFunc == null) {
            return;
        }
        if (list == null || list.size() == 0) {
            dnsLogFunc.log(String.format("%dms FAIL in type %s %s", Long.valueOf(j), dnsTypeToStr(i), str));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (InetAddress inetAddress : list) {
            sb.append(',');
            sb.append(inetAddress.getHostAddress());
        }
        dnsLogFunc.log(String.format("%dms OK %s", Long.valueOf(j), sb.substring(1)));
    }
}
