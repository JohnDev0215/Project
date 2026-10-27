package com.visualon.OSMPUtils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.visualon.OSMPUtils.voCustomerTagWrap;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes7.dex */
public class voID3ContainerImpl implements voID3Container {
    private static final long MAX_CHECK_TIME = 2000;
    private static final long MIN_DELAY_TIME = 100;
    private static final String TAG = "@@@voID3ContainerImpl";
    private static final int messageCustomTag = -252706812;
    private EventHandler mEventHandler;
    private voCustomerTagWrap.onID3Listener mID3listener;
    private ReentrantLock mLock;
    private String mSourceUrl = null;
    private final ReentrantLock mMutex = new ReentrantLock();
    private LinkedList<voOSTimedTag> customerTagList = new LinkedList<>();
    private String urlPath = "";
    TreeMap<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() { // from class: com.visualon.OSMPUtils.voID3ContainerImpl.1
        @Override // java.util.Comparator
        public int compare(Integer num, Integer num2) {
            return num2.compareTo(num);
        }
    });

    private class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (voID3ContainerImpl.this.mEventHandler == null) {
                if (voLog.enablePrintLog()) {
                    voLog.e(voID3ContainerImpl.TAG, "voID3ContainerImpl went away with unhandled events", new Object[0]);
                }
            } else if (message.what == voID3ContainerImpl.messageCustomTag) {
                voID3ContainerImpl.this.mLock.lock();
                if (voID3ContainerImpl.this.mID3listener != null) {
                    voID3ContainerImpl.this.check((int) voID3ContainerImpl.this.mID3listener.onGetPos());
                }
                voID3ContainerImpl.this.mLock.unlock();
            }
        }
    }

    public voID3ContainerImpl() {
        this.mLock = null;
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mEventHandler = new EventHandler(looperMyLooper);
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "Looper.myLooper()", new Object[0]);
            }
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(mainLooper);
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "getMainLooper()", new Object[0]);
                }
            } else {
                this.mEventHandler = null;
            }
        }
        this.mLock = new ReentrantLock();
    }

    @Override // com.visualon.OSMPUtils.voFrameScrubbing
    public void setSourceUrl(String str) {
        this.mSourceUrl = str;
    }

    @Override // com.visualon.OSMPUtils.voCustomerTagWrap
    public void check(long j) {
        synchronized (this.customerTagList) {
            Iterator<voOSTimedTag> it = this.customerTagList.iterator();
            while (it.hasNext()) {
                voOSTimedTag next = it.next();
                if (next.TimeStamp() > j) {
                    break;
                }
                this.mLock.lock();
                if (this.mID3listener != null) {
                    Long lValueOf = Long.valueOf(next.TimeStamp());
                    String str = new String(next.Data());
                    if (voLog.enablePrintLog()) {
                        voLog.v(TAG, "Call onSendID3Event, tag flag is %d, size is %d, timestamp is %d, %s", Integer.valueOf(next.Flag()), Integer.valueOf(next.Size()), Integer.valueOf(lValueOf.intValue()), str);
                    }
                    this.mID3listener.onSendID3Event(voOSType.VOOSMP_SRC_CB_Customer_Tag, 1, 0, next);
                }
                this.mLock.unlock();
                it.remove();
            }
            if (!this.customerTagList.isEmpty()) {
                long jTimeStamp = this.customerTagList.getFirst().TimeStamp() - j;
                if (j == 0) {
                    this.mEventHandler.sendEmptyMessageDelayed(messageCustomTag, MIN_DELAY_TIME);
                } else {
                    this.mEventHandler.sendEmptyMessageDelayed(messageCustomTag, Math.max(Math.min(MAX_CHECK_TIME, jTimeStamp), MIN_DELAY_TIME));
                }
            }
        }
    }

    @Override // com.visualon.OSMPUtils.voCustomerTagWrap
    public void reset() {
        synchronized (this.customerTagList) {
            this.customerTagList.clear();
        }
    }

    @Override // com.visualon.OSMPUtils.voFrameScrubbing
    public void setFrameScrubbingUrl(Object obj) {
        String str;
        int iLastIndexOf;
        this.mMutex.lock();
        try {
            String[] strArrSplit = obj.toString().split("\n");
            String str2 = "";
            if (strArrSplit != null) {
                int i = 0;
                while (true) {
                    if (i >= strArrSplit.length) {
                        break;
                    }
                    String strTrim = strArrSplit[i].trim();
                    strArrSplit[i] = strTrim;
                    String strReplace = strTrim.replace('\r', (char) 0);
                    strArrSplit[i] = strReplace;
                    if (strReplace.toLowerCase().endsWith(".m3u8")) {
                        str2 = strArrSplit[i];
                        break;
                    }
                    i++;
                }
            }
            if (str2.length() > 0) {
                if (!str2.toLowerCase().startsWith("http") && (str = this.mSourceUrl) != null && (iLastIndexOf = str.lastIndexOf(47)) >= 0) {
                    str2 = this.mSourceUrl.substring(0, iLastIndexOf + 1) + str2;
                    if (voLog.enablePrintLog()) {
                        voLog.v(TAG, "VOOSMP_SRC_CB_Customer_Tag url = %s", str2);
                    }
                }
                setThumbnailURL(str2);
            }
        } finally {
            this.mMutex.unlock();
        }
    }

    @Override // com.visualon.OSMPUtils.voFrameScrubbing
    public void setThumbnailURL(String str) {
        String[] strArrSplit;
        this.mMutex.lock();
        try {
            try {
                URL url = new URL(str);
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Thumbnail PlayList download begin", new Object[0]);
                }
                if (str.lastIndexOf("/") >= 0) {
                    this.urlPath = str.substring(0, str.lastIndexOf("/"));
                }
                InputStream inputStreamOpenStream = FirebasePerfUrlConnection.openStream(url);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (inputStreamOpenStream != null) {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStreamOpenStream.read(bArr);
                        if (i <= 0) {
                            break;
                        } else {
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                    }
                }
                inputStreamOpenStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                if (byteArray != null && (strArrSplit = new String(byteArray).split("\n")) != null) {
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < strArrSplit.length) {
                        if (strArrSplit[i2].length() > 8 && strArrSplit[i2].substring(0, 8).compareToIgnoreCase("#EXTINF:") == 0 && i2 < strArrSplit.length - 1) {
                            int i4 = Integer.parseInt(strArrSplit[i2].substring(8).replace("\r", "").replace(",", "").trim()) * 1000;
                            i2++;
                            this.treeMap.put(Integer.valueOf(i3), strArrSplit[i2]);
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "Thumbnail PlayList item begin time = %d, url = %s", Integer.valueOf(i3), strArrSplit[i2]);
                            }
                            i3 += i4;
                        }
                        i2++;
                    }
                    if (!this.treeMap.isEmpty()) {
                        this.treeMap.put(Integer.valueOf(i3), "");
                    }
                }
            } catch (Exception e) {
                if (voLog.enablePrintLog()) {
                    voLog.e(TAG, "Thumbnail PlayList download Exception", new Object[0]);
                }
                e.printStackTrace();
            }
        } finally {
            this.mMutex.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0040, code lost:
    
        if (r5.startsWith("http://") != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0048, code lost:
    
        if (r5.startsWith("https://") != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:
    
        r0 = r4.urlPath + "/" + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0029, code lost:
    
        r0 = r2.getValue();
        r5 = r0.toLowerCase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
    
        if (r5.length() <= 0) goto L15;
     */
    @Override // com.visualon.OSMPUtils.voFrameScrubbing
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getThumbnailByTime(int r5) {
        /*
            r4 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r4.mMutex
            r0.lock()
            java.lang.String r0 = ""
            java.util.TreeMap<java.lang.Integer, java.lang.String> r1 = r4.treeMap     // Catch: java.lang.Throwable -> L66
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L66
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L66
        L11:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L66
            if (r2 == 0) goto L60
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L66
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: java.lang.Throwable -> L66
            java.lang.Object r3 = r2.getKey()     // Catch: java.lang.Throwable -> L66
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> L66
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L66
            if (r3 >= r5) goto L11
            java.lang.Object r5 = r2.getValue()     // Catch: java.lang.Throwable -> L66
            r0 = r5
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L66
            java.lang.String r5 = r0.toLowerCase()     // Catch: java.lang.Throwable -> L66
            int r1 = r5.length()     // Catch: java.lang.Throwable -> L66
            if (r1 <= 0) goto L60
            java.lang.String r1 = "http://"
            boolean r1 = r5.startsWith(r1)     // Catch: java.lang.Throwable -> L66
            if (r1 != 0) goto L60
            java.lang.String r1 = "https://"
            boolean r5 = r5.startsWith(r1)     // Catch: java.lang.Throwable -> L66
            if (r5 != 0) goto L60
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
            r5.<init>()     // Catch: java.lang.Throwable -> L66
            java.lang.String r1 = r4.urlPath     // Catch: java.lang.Throwable -> L66
            r5.append(r1)     // Catch: java.lang.Throwable -> L66
            java.lang.String r1 = "/"
            r5.append(r1)     // Catch: java.lang.Throwable -> L66
            r5.append(r0)     // Catch: java.lang.Throwable -> L66
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L66
        L60:
            java.util.concurrent.locks.ReentrantLock r5 = r4.mMutex
            r5.unlock()
            return r0
        L66:
            r5 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = r4.mMutex
            r0.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPUtils.voID3ContainerImpl.getThumbnailByTime(int):java.lang.String");
    }

    @Override // com.visualon.OSMPUtils.voID3Container
    public void setID3Listener(voCustomerTagWrap.onID3Listener onid3listener) {
        this.mLock.lock();
        this.mID3listener = onid3listener;
        this.mLock.unlock();
    }

    @Override // com.visualon.OSMPUtils.voCustomerTagWrap
    public void addID3(voOSTimedTag voostimedtag) {
        synchronized (this.customerTagList) {
            this.customerTagList.add(voostimedtag);
        }
    }

    @Override // com.visualon.OSMPUtils.voCustomerTagWrap
    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.customerTagList) {
            zIsEmpty = this.customerTagList.isEmpty();
        }
        return zIsEmpty;
    }

    @Override // com.visualon.OSMPUtils.voID3Container
    public int onRequest(int i, int i2, int i3, Object obj) {
        if (i == 33554464) {
            final voOSTimedTag voostimedtag = (voOSTimedTag) obj;
            if (voostimedtag == null) {
                if (voLog.enablePrintLog()) {
                    voLog.e(TAG, "VOOSMP_SRC_CB_Customer_Tag = null", new Object[0]);
                }
                return 0;
            }
            if (i2 == 99) {
                new Thread(new Runnable() { // from class: com.visualon.OSMPUtils.voID3ContainerImpl.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            String str = new String(voostimedtag.Data());
                            if (voLog.enablePrintLog()) {
                                voLog.v(voID3ContainerImpl.TAG, "VOOSMP_SRC_CB_Customer_Tag = %s", str);
                            }
                            voID3ContainerImpl.this.setFrameScrubbingUrl(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (voLog.enablePrintLog()) {
                                voLog.e(voID3ContainerImpl.TAG, "download error", new Object[0]);
                            }
                        }
                    }
                }).start();
            } else if (i2 == 1) {
                Long lValueOf = Long.valueOf(voostimedtag.TimeStamp());
                String str = new String(voostimedtag.Data());
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "mTimedTagList customerTagWrap saving, Flag is %d, size is %d, timestamp is %d, %s", Integer.valueOf(voostimedtag.Flag()), Integer.valueOf(voostimedtag.Size()), Integer.valueOf(lValueOf.intValue()), str);
                }
                synchronized (this.customerTagList) {
                    if (isEmpty()) {
                        addID3(voostimedtag);
                        this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(messageCustomTag));
                    } else {
                        addID3(voostimedtag);
                    }
                }
                return 0;
            }
        }
        this.mLock.lock();
        voCustomerTagWrap.onID3Listener onid3listener = this.mID3listener;
        if (onid3listener == null) {
            return 0;
        }
        int iOnSendID3Event = onid3listener.onSendID3Event(i, i2, i3, obj);
        this.mLock.unlock();
        return iOnSendID3Event;
    }
}
