package com.byd.auto_emanual.util;

import java.net.URI;
import java.util.regex.Pattern;
import org.apache.shiro.util.AntPathMatcher;

/* JADX INFO: loaded from: classes.dex */
public class PingUtil {
    private static final int DEFAULT_COUNT = 1;
    private static final String RESULT_BAD = "bad";
    private static final String RESULT_GOOD = "good";
    private static final String RESULT_NORMAL = "normal";
    private static final String TAG = "PingUtil";
    private static final int TIME_OUT = 100;
    private static final int TYPE_AVG = 2;
    private static final int TYPE_MAX = 3;
    private static final int TYPE_MDEV = 4;
    private static final int TYPE_MIN = 1;
    private static final String ipRegex = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

    public static String parseRTT(String url) {
        int rtt = getRTT(url);
        float packetLossFloat = getPacketLossFloat(url);
        if (rtt > 0 && rtt < 50) {
            return RESULT_GOOD;
        }
        if ((rtt <= 100 && rtt > 50) || packetLossFloat == 0.0f) {
            return RESULT_NORMAL;
        }
        if (rtt > 100 || rtt < 0 || packetLossFloat > 0.0f) {
        }
        return RESULT_BAD;
    }

    public static String parseRTT(String url, int type, int count, int timeout) throws Throwable {
        int rtt = getRTT(url, type, count, timeout);
        float packetLossFloat = getPacketLossFloat(url, count, timeout);
        if (rtt > 0 && rtt < 50) {
            return RESULT_GOOD;
        }
        if ((rtt <= 100 && rtt > 50) || packetLossFloat == 0.0f) {
            return RESULT_NORMAL;
        }
        if (rtt > 100 || rtt < 0 || packetLossFloat > 0.0f) {
        }
        return RESULT_BAD;
    }

    public static int getRTT(String url) {
        return getRTT(url, 2);
    }

    public static int getRTT(String url, int type) {
        return getRTT(url, type, 1, 100);
    }

    public static int getRTT(String url, int type, int count, int timeout) throws Throwable {
        String domain = getDomain(url);
        if (domain == null) {
            return -1;
        }
        String strPing = ping(createSimplePingCommand(count, timeout, domain));
        MLog.d(TAG, strPing);
        if (strPing != null) {
            try {
                String[] strArrSplit = strPing.substring(strPing.indexOf("min/avg/max/mdev") + 19).split(AntPathMatcher.DEFAULT_PATH_SEPARATOR);
                if (type == 1) {
                    return Math.round(Float.parseFloat(strArrSplit[0]));
                }
                if (type == 2) {
                    return Math.round(Float.parseFloat(strArrSplit[1]));
                }
                if (type == 3) {
                    return Math.round(Float.parseFloat(strArrSplit[2]));
                }
                if (type != 4) {
                    return -1;
                }
                return Math.round(Float.parseFloat(strArrSplit[3]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static float getPacketLossFloat(String url) {
        String packetLoss = getPacketLoss(url);
        if (packetLoss == null) {
            return -1.0f;
        }
        try {
            return Float.parseFloat(packetLoss.replace("%", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0f;
        }
    }

    public static float getPacketLossFloat(String url, int count, int timeout) {
        String packetLoss = getPacketLoss(url, count, timeout);
        if (packetLoss == null) {
            return -1.0f;
        }
        try {
            return Float.parseFloat(packetLoss.replace("%", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0f;
        }
    }

    public static String getPacketLoss(String url) {
        return getPacketLoss(url, 1, 100);
    }

    public static String getPacketLoss(String url, int count, int timeout) {
        String strPing;
        String domain = getDomain(url);
        if (domain != null && (strPing = ping(createSimplePingCommand(count, timeout, domain))) != null) {
            try {
                String strSubstring = strPing.substring(strPing.indexOf("received,"));
                return strSubstring.substring(9, strSubstring.indexOf("packet"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String getDomain(String url) {
        String host = null;
        try {
            host = URI.create(url).getHost();
            if (host == null) {
                if (isMatch(ipRegex, url)) {
                    return url;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return host;
    }

    private static boolean isMatch(String regex, String string) {
        return Pattern.matches(regex, string);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /* JADX WARN: Type inference failed for: r6v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String ping(java.lang.String r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L42
            java.lang.Process r6 = r1.exec(r6)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L42
            java.io.InputStream r1 = r6.getInputStream()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            r3.<init>(r1)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            r2.<init>(r3)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            r3.<init>()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
        L1c:
            java.lang.String r4 = r2.readLine()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            if (r4 == 0) goto L2b
            r3.append(r4)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            java.lang.String r4 = "\n"
            r3.append(r4)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            goto L1c
        L2b:
            r2.close()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            r1.close()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            java.lang.String r0 = r3.toString()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L4d
            if (r6 == 0) goto L3a
            r6.destroy()
        L3a:
            return r0
        L3b:
            r1 = move-exception
            goto L44
        L3d:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L4e
        L42:
            r1 = move-exception
            r6 = r0
        L44:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L4d
            if (r6 == 0) goto L4c
            r6.destroy()
        L4c:
            return r0
        L4d:
            r0 = move-exception
        L4e:
            if (r6 == 0) goto L53
            r6.destroy()
        L53:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_emanual.util.PingUtil.ping(java.lang.String):java.lang.String");
    }

    private static String createSimplePingCommand(int count, int timeout, String domain) {
        return "/system/bin/ping -c " + count + " -w " + timeout + " " + domain;
    }
}
