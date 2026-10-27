package com.amazon.avod.qos.internal;

import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class HostnameResolver {
    private final ExecutorService mExecutor;
    private final Map<String, String> mResolvedHostnames = Maps.newConcurrentMap();
    private final Joiner mJoiner = Joiner.on(", ");

    public HostnameResolver(ExecutorService executorService) {
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService);
    }

    public void resolveAsync(@Nullable String str) {
        String hostnameFromUrl = getHostnameFromUrl(str);
        if (hostnameFromUrl == null || this.mResolvedHostnames.containsKey(hostnameFromUrl)) {
            return;
        }
        this.mExecutor.execute(new ResolveTask(hostnameFromUrl));
    }

    public String lookup(@Nullable String str) {
        String hostnameFromUrl = getHostnameFromUrl(str);
        if (hostnameFromUrl == null) {
            return null;
        }
        return this.mResolvedHostnames.get(hostnameFromUrl);
    }

    protected List<String> getIpsForHost(String str) throws UnknownHostException {
        InetAddress[] allByName = InetAddress.getAllByName(str);
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(allByName.length);
        for (InetAddress inetAddress : allByName) {
            arrayListNewArrayListWithCapacity.add(inetAddress.getHostAddress());
        }
        return arrayListNewArrayListWithCapacity;
    }

    private String getHostnameFromUrl(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    private class ResolveTask implements Runnable {
        private final String mHostnameToResolve;

        public ResolveTask(String str) {
            this.mHostnameToResolve = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strJoin = HostnameResolver.this.mJoiner.join(HostnameResolver.this.getIpsForHost(this.mHostnameToResolve));
                if (Strings.isNullOrEmpty(strJoin)) {
                    return;
                }
                HostnameResolver.this.mResolvedHostnames.put(this.mHostnameToResolve, strJoin);
            } catch (UnknownHostException e) {
                DLog.warnf("Could not resolve IPs from the hostname: %s (%s)", this.mHostnameToResolve, e.getMessage());
            }
        }
    }
}
