package javax.activation;

import com.sun.activation.registries.LogSupport;
import com.sun.activation.registries.MailcapFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class MailcapCommandMap extends CommandMap {
    private static final String confDir;
    private MailcapFile[] DB;

    static {
        String str;
        try {
            str = (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.activation.MailcapCommandMap.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    String property = System.getProperty("java.home");
                    StringBuilder sb = new StringBuilder();
                    sb.append(property);
                    String str2 = File.separator;
                    sb.append(str2);
                    sb.append("conf");
                    String string = sb.toString();
                    if (new File(string).exists()) {
                        return string + str2;
                    }
                    return property + str2 + "lib" + str2;
                }
            });
        } catch (Exception unused) {
            str = null;
        }
        confDir = str;
    }

    public MailcapCommandMap() throws Throwable {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(null);
        LogSupport.log("MailcapCommandMap: load HOME");
        try {
            String property = System.getProperty("user.home");
            if (property != null) {
                MailcapFile mailcapFileLoadFile = loadFile(property + File.separator + ".mailcap");
                if (mailcapFileLoadFile != null) {
                    arrayList.add(mailcapFileLoadFile);
                }
            }
        } catch (SecurityException unused) {
        }
        LogSupport.log("MailcapCommandMap: load SYS");
        try {
            String str = confDir;
            if (str != null) {
                MailcapFile mailcapFileLoadFile2 = loadFile(str + "mailcap");
                if (mailcapFileLoadFile2 != null) {
                    arrayList.add(mailcapFileLoadFile2);
                }
            }
        } catch (SecurityException unused2) {
        }
        LogSupport.log("MailcapCommandMap: load JAR");
        loadAllResources(arrayList, "META-INF/mailcap");
        LogSupport.log("MailcapCommandMap: load DEF");
        MailcapFile mailcapFileLoadResource = loadResource("/META-INF/mailcap.default");
        if (mailcapFileLoadResource != null) {
            arrayList.add(mailcapFileLoadResource);
        }
        MailcapFile[] mailcapFileArr = new MailcapFile[arrayList.size()];
        this.DB = mailcapFileArr;
        this.DB = (MailcapFile[]) arrayList.toArray(mailcapFileArr);
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0092: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:38:0x0092 */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x004c A[EXC_TOP_SPLITTER, PHI: r2
  0x004c: PHI (r2v5 java.io.InputStream) = (r2v3 java.io.InputStream), (r2v4 java.io.InputStream), (r2v7 java.io.InputStream) binds: [B:28:0x0072, B:34:0x008d, B:13:0x004a] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.sun.activation.registries.MailcapFile loadResource(java.lang.String r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = "MailcapCommandMap: can't load "
            r1 = 0
            java.lang.Class r2 = r6.getClass()     // Catch: java.lang.Throwable -> L54 java.lang.SecurityException -> L56 java.io.IOException -> L73
            java.io.InputStream r2 = javax.activation.SecuritySupport.getResourceAsStream(r2, r7)     // Catch: java.lang.Throwable -> L54 java.lang.SecurityException -> L56 java.io.IOException -> L73
            if (r2 == 0) goto L30
            com.sun.activation.registries.MailcapFile r3 = new com.sun.activation.registries.MailcapFile     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            r3.<init>(r2)     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            boolean r4 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            if (r4 == 0) goto L2c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            r4.<init>()     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            java.lang.String r5 = "MailcapCommandMap: successfully loaded mailcap file: "
            r4.append(r5)     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            r4.append(r7)     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            java.lang.String r4 = r4.toString()     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            com.sun.activation.registries.LogSupport.log(r4)     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
        L2c:
            r2.close()     // Catch: java.io.IOException -> L2f
        L2f:
            return r3
        L30:
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            if (r3 == 0) goto L4a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            r3.<init>()     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            java.lang.String r4 = "MailcapCommandMap: not loading mailcap file: "
            r3.append(r4)     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            r3.append(r7)     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            java.lang.String r3 = r3.toString()     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
            com.sun.activation.registries.LogSupport.log(r3)     // Catch: java.lang.SecurityException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L91
        L4a:
            if (r2 == 0) goto L90
        L4c:
            r2.close()     // Catch: java.io.IOException -> L90
            goto L90
        L50:
            r3 = move-exception
            goto L58
        L52:
            r3 = move-exception
            goto L75
        L54:
            r7 = move-exception
            goto L93
        L56:
            r3 = move-exception
            r2 = r1
        L58:
            boolean r4 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: java.lang.Throwable -> L91
            if (r4 == 0) goto L70
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91
            r4.<init>()     // Catch: java.lang.Throwable -> L91
            r4.append(r0)     // Catch: java.lang.Throwable -> L91
            r4.append(r7)     // Catch: java.lang.Throwable -> L91
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L91
            com.sun.activation.registries.LogSupport.log(r7, r3)     // Catch: java.lang.Throwable -> L91
        L70:
            if (r2 == 0) goto L90
            goto L4c
        L73:
            r3 = move-exception
            r2 = r1
        L75:
            boolean r4 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: java.lang.Throwable -> L91
            if (r4 == 0) goto L8d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91
            r4.<init>()     // Catch: java.lang.Throwable -> L91
            r4.append(r0)     // Catch: java.lang.Throwable -> L91
            r4.append(r7)     // Catch: java.lang.Throwable -> L91
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L91
            com.sun.activation.registries.LogSupport.log(r7, r3)     // Catch: java.lang.Throwable -> L91
        L8d:
            if (r2 == 0) goto L90
            goto L4c
        L90:
            return r1
        L91:
            r7 = move-exception
            r1 = r2
        L93:
            if (r1 == 0) goto L98
            r1.close()     // Catch: java.io.IOException -> L98
        L98:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.loadResource(java.lang.String):com.sun.activation.registries.MailcapFile");
    }

    private void loadAllResources(List list, String str) throws Throwable {
        URL[] systemResources;
        int i = 0;
        try {
            ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
            if (contextClassLoader == null) {
                contextClassLoader = getClass().getClassLoader();
            }
            if (contextClassLoader != null) {
                systemResources = SecuritySupport.getResources(contextClassLoader, str);
            } else {
                systemResources = SecuritySupport.getSystemResources(str);
            }
            if (systemResources != null) {
                if (LogSupport.isLoggable()) {
                    LogSupport.log("MailcapCommandMap: getResources");
                }
                int i2 = 0;
                while (i < systemResources.length) {
                    try {
                        URL url = systemResources[i];
                        InputStream inputStreamOpenStream = null;
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("MailcapCommandMap: URL " + url);
                        }
                        try {
                            try {
                                inputStreamOpenStream = SecuritySupport.openStream(url);
                                if (inputStreamOpenStream != null) {
                                    list.add(new MailcapFile(inputStreamOpenStream));
                                    try {
                                        if (LogSupport.isLoggable()) {
                                            LogSupport.log("MailcapCommandMap: successfully loaded mailcap file from URL: " + url);
                                        }
                                        i2 = 1;
                                    } catch (IOException e) {
                                        e = e;
                                        i2 = 1;
                                        if (LogSupport.isLoggable()) {
                                            LogSupport.log("MailcapCommandMap: can't load " + url, e);
                                        }
                                        if (inputStreamOpenStream != null) {
                                        }
                                        i++;
                                    } catch (SecurityException e2) {
                                        e = e2;
                                        i2 = 1;
                                        if (LogSupport.isLoggable()) {
                                            LogSupport.log("MailcapCommandMap: can't load " + url, e);
                                        }
                                        if (inputStreamOpenStream == null) {
                                            i++;
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        i2 = 1;
                                        if (inputStreamOpenStream != null) {
                                            try {
                                                inputStreamOpenStream.close();
                                            } catch (IOException unused) {
                                            }
                                        }
                                        throw th;
                                    }
                                } else if (LogSupport.isLoggable()) {
                                    LogSupport.log("MailcapCommandMap: not loading mailcap file from URL: " + url);
                                }
                            } catch (IOException e3) {
                                e = e3;
                            } catch (SecurityException e4) {
                                e = e4;
                            }
                            if (inputStreamOpenStream != null) {
                                try {
                                    inputStreamOpenStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            i++;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        i = i2;
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("MailcapCommandMap: can't load " + str, e);
                        }
                    }
                }
                i = i2;
            }
        } catch (Exception e6) {
            e = e6;
        }
        if (i == 0) {
            if (LogSupport.isLoggable()) {
                LogSupport.log("MailcapCommandMap: !anyLoaded");
            }
            MailcapFile mailcapFileLoadResource = loadResource("/" + str);
            if (mailcapFileLoadResource != null) {
                list.add(mailcapFileLoadResource);
            }
        }
    }

    private MailcapFile loadFile(String str) {
        try {
            return new MailcapFile(str);
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x006e, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006f, code lost:
    
        r2 = r4.DB;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0072, code lost:
    
        if (r1 >= r2.length) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0076, code lost:
    
        if (r2[r1] != null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007d, code lost:
    
        if (com.sun.activation.registries.LogSupport.isLoggable() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007f, code lost:
    
        com.sun.activation.registries.LogSupport.log("  search fallback DB #" + r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0093, code lost:
    
        r2 = r4.DB[r1].getMailcapFallbackList(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x009b, code lost:
    
        if (r2 == null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009d, code lost:
    
        r2 = (java.util.List) r2.get("content-handler");
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a5, code lost:
    
        if (r2 == null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a7, code lost:
    
        r2 = getDataContentHandler((java.lang.String) r2.get(0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b1, code lost:
    
        if (r2 == null) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b4, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b5, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ba, code lost:
    
        return null;
     */
    @Override // javax.activation.CommandMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized javax.activation.DataContentHandler createDataContentHandler(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: java.lang.Throwable -> Lbb
            if (r0 == 0) goto L1b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbb
            r0.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r1 = "MailcapCommandMap: createDataContentHandler for "
            r0.append(r1)     // Catch: java.lang.Throwable -> Lbb
            r0.append(r5)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lbb
            com.sun.activation.registries.LogSupport.log(r0)     // Catch: java.lang.Throwable -> Lbb
        L1b:
            if (r5 == 0) goto L23
            java.util.Locale r0 = java.util.Locale.ENGLISH     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r5 = r5.toLowerCase(r0)     // Catch: java.lang.Throwable -> Lbb
        L23:
            r0 = 0
            r1 = r0
        L25:
            com.sun.activation.registries.MailcapFile[] r2 = r4.DB     // Catch: java.lang.Throwable -> Lbb
            int r3 = r2.length     // Catch: java.lang.Throwable -> Lbb
            if (r1 >= r3) goto L6e
            r2 = r2[r1]     // Catch: java.lang.Throwable -> Lbb
            if (r2 != 0) goto L2f
            goto L6b
        L2f:
            boolean r2 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto L49
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbb
            r2.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r3 = "  search DB #"
            r2.append(r3)     // Catch: java.lang.Throwable -> Lbb
            r2.append(r1)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lbb
            com.sun.activation.registries.LogSupport.log(r2)     // Catch: java.lang.Throwable -> Lbb
        L49:
            com.sun.activation.registries.MailcapFile[] r2 = r4.DB     // Catch: java.lang.Throwable -> Lbb
            r2 = r2[r1]     // Catch: java.lang.Throwable -> Lbb
            java.util.Map r2 = r2.getMailcapList(r5)     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto L6b
            java.lang.String r3 = "content-handler"
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Throwable -> Lbb
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto L6b
            java.lang.Object r2 = r2.get(r0)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> Lbb
            javax.activation.DataContentHandler r2 = r4.getDataContentHandler(r2)     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto L6b
            monitor-exit(r4)
            return r2
        L6b:
            int r1 = r1 + 1
            goto L25
        L6e:
            r1 = r0
        L6f:
            com.sun.activation.registries.MailcapFile[] r2 = r4.DB     // Catch: java.lang.Throwable -> Lbb
            int r3 = r2.length     // Catch: java.lang.Throwable -> Lbb
            if (r1 >= r3) goto Lb8
            r2 = r2[r1]     // Catch: java.lang.Throwable -> Lbb
            if (r2 != 0) goto L79
            goto Lb5
        L79:
            boolean r2 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto L93
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbb
            r2.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r3 = "  search fallback DB #"
            r2.append(r3)     // Catch: java.lang.Throwable -> Lbb
            r2.append(r1)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lbb
            com.sun.activation.registries.LogSupport.log(r2)     // Catch: java.lang.Throwable -> Lbb
        L93:
            com.sun.activation.registries.MailcapFile[] r2 = r4.DB     // Catch: java.lang.Throwable -> Lbb
            r2 = r2[r1]     // Catch: java.lang.Throwable -> Lbb
            java.util.Map r2 = r2.getMailcapFallbackList(r5)     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto Lb5
            java.lang.String r3 = "content-handler"
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Throwable -> Lbb
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto Lb5
            java.lang.Object r2 = r2.get(r0)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> Lbb
            javax.activation.DataContentHandler r2 = r4.getDataContentHandler(r2)     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto Lb5
            monitor-exit(r4)
            return r2
        Lb5:
            int r1 = r1 + 1
            goto L6f
        Lb8:
            r5 = 0
            monitor-exit(r4)
            return r5
        Lbb:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.createDataContentHandler(java.lang.String):javax.activation.DataContentHandler");
    }

    private DataContentHandler getDataContentHandler(String str) {
        Class<?> cls;
        if (LogSupport.isLoggable()) {
            LogSupport.log("    got content-handler");
        }
        if (LogSupport.isLoggable()) {
            LogSupport.log("      class " + str);
        }
        try {
            ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
            if (contextClassLoader == null) {
                contextClassLoader = getClass().getClassLoader();
            }
            try {
                cls = contextClassLoader.loadClass(str);
            } catch (Exception unused) {
                cls = Class.forName(str);
            }
            if (cls != null) {
                return (DataContentHandler) cls.newInstance();
            }
            return null;
        } catch (ClassNotFoundException e) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e);
            return null;
        } catch (IllegalAccessException e2) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e2);
            return null;
        } catch (InstantiationException e3) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e3);
            return null;
        }
    }
}
