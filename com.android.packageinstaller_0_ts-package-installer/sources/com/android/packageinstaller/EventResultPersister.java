package com.android.packageinstaller;

import android.os.AsyncTask;
import android.util.AtomicFile;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import androidx.core.app.NotificationCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes2.dex */
class EventResultPersister {
    static final String EXTRA_ID = "EventResultPersister.EXTRA_ID";
    static final int GENERATE_NEW_ID = Integer.MIN_VALUE;
    private static final String LOG_TAG = EventResultPersister.class.getSimpleName();
    private int mCounter;
    private boolean mIsPersistScheduled;
    private boolean mIsPersistingStateValid;
    private final AtomicFile mResultsFile;
    private final Object mLock = new Object();
    private final SparseArray<EventResult> mResults = new SparseArray<>();
    private final SparseArray<EventResultObserver> mObservers = new SparseArray<>();

    interface EventResultObserver {
        void onResult(int i, int i2, String str);
    }

    public int getNewId() throws OutOfIdsException {
        int i;
        synchronized (this.mLock) {
            if (this.mCounter == Integer.MAX_VALUE) {
                throw new OutOfIdsException();
            }
            this.mCounter++;
            writeState();
            i = this.mCounter - 1;
        }
        return i;
    }

    private static void nextElement(XmlPullParser parser) throws XmlPullParserException, IOException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                return;
            }
        } while (type != 1);
    }

    private static int readIntAttribute(XmlPullParser parser, String name) {
        return Integer.parseInt(parser.getAttributeValue(null, name));
    }

    private static String readStringAttribute(XmlPullParser parser, String name) {
        return parser.getAttributeValue(null, name);
    }

    EventResultPersister(File resultFile) {
        AtomicFile atomicFile = new AtomicFile(resultFile);
        this.mResultsFile = atomicFile;
        this.mCounter = -2147483647;
        try {
            FileInputStream stream = atomicFile.openRead();
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(stream, StandardCharsets.UTF_8.name());
                nextElement(parser);
                while (parser.getEventType() != 1) {
                    String tagName = parser.getName();
                    if ("results".equals(tagName)) {
                        this.mCounter = readIntAttribute(parser, "counter");
                    } else if ("result".equals(tagName)) {
                        int id = readIntAttribute(parser, "id");
                        int status = readIntAttribute(parser, NotificationCompat.CATEGORY_STATUS);
                        int legacyStatus = readIntAttribute(parser, "legacyStatus");
                        String statusMessage = readStringAttribute(parser, "statusMessage");
                        if (this.mResults.get(id) != null) {
                            throw new Exception("id " + id + " has two results");
                        }
                        this.mResults.put(id, new EventResult(status, legacyStatus, statusMessage));
                    } else {
                        throw new Exception("unexpected tag");
                    }
                    nextElement(parser);
                }
                if (stream != null) {
                    stream.close();
                }
            } finally {
            }
        } catch (Exception e) {
            this.mResults.clear();
            writeState();
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:23:0x007f
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    void onEventReceived(android.content.Context r19, android.content.Intent r20) {
        /*
            r18 = this;
            r7 = r18
            r8 = r20
            java.lang.String r0 = "android.content.pm.extra.STATUS"
            r1 = 0
            int r9 = r8.getIntExtra(r0, r1)
            r0 = -1
            if (r9 != r0) goto L1c
            java.lang.String r0 = "android.intent.extra.INTENT"
            android.os.Parcelable r0 = r8.getParcelableExtra(r0)
            android.content.Intent r0 = (android.content.Intent) r0
            r10 = r19
            r10.startActivity(r0)
            return
        L1c:
            r10 = r19
            java.lang.String r0 = "EventResultPersister.EXTRA_ID"
            int r11 = r8.getIntExtra(r0, r1)
            java.lang.String r0 = "android.content.pm.extra.STATUS_MESSAGE"
            java.lang.String r12 = r8.getStringExtra(r0)
            java.lang.String r0 = "android.content.pm.extra.LEGACY_STATUS"
            int r13 = r8.getIntExtra(r0, r1)
            r1 = 0
            java.lang.Object r14 = r7.mLock
            monitor-enter(r14)
            android.util.SparseArray<com.android.packageinstaller.EventResultPersister$EventResultObserver> r0 = r7.mObservers     // Catch: java.lang.Throwable -> L7f
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L7f
            r2 = 0
        L3b:
            if (r2 >= r0) goto L58
            android.util.SparseArray<com.android.packageinstaller.EventResultPersister$EventResultObserver> r3 = r7.mObservers     // Catch: java.lang.Throwable -> L7f
            int r3 = r3.keyAt(r2)     // Catch: java.lang.Throwable -> L7f
            if (r3 != r11) goto L55
            android.util.SparseArray<com.android.packageinstaller.EventResultPersister$EventResultObserver> r3 = r7.mObservers     // Catch: java.lang.Throwable -> L7f
            java.lang.Object r3 = r3.valueAt(r2)     // Catch: java.lang.Throwable -> L7f
            com.android.packageinstaller.EventResultPersister$EventResultObserver r3 = (com.android.packageinstaller.EventResultPersister.EventResultObserver) r3     // Catch: java.lang.Throwable -> L7f
            r1 = r3
            android.util.SparseArray<com.android.packageinstaller.EventResultPersister$EventResultObserver> r3 = r7.mObservers     // Catch: java.lang.Throwable -> L7f
            r3.removeAt(r2)     // Catch: java.lang.Throwable -> L7f
            r15 = r1
            goto L59
        L55:
            int r2 = r2 + 1
            goto L3b
        L58:
            r15 = r1
        L59:
            if (r15 == 0) goto L5f
            r15.onResult(r9, r13, r12)     // Catch: java.lang.Throwable -> L7c
            goto L7a
        L5f:
            android.util.SparseArray<com.android.packageinstaller.EventResultPersister$EventResult> r6 = r7.mResults     // Catch: java.lang.Throwable -> L7c
            com.android.packageinstaller.EventResultPersister$EventResult r5 = new com.android.packageinstaller.EventResultPersister$EventResult     // Catch: java.lang.Throwable -> L7c
            r16 = 0
            r1 = r5
            r2 = r18
            r3 = r9
            r4 = r13
            r17 = r0
            r0 = r5
            r5 = r12
            r7 = r6
            r6 = r16
            r1.<init>(r3, r4, r5)     // Catch: java.lang.Throwable -> L7c
            r7.put(r11, r0)     // Catch: java.lang.Throwable -> L7c
            r18.writeState()     // Catch: java.lang.Throwable -> L7c
        L7a:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L7c
            return
        L7c:
            r0 = move-exception
            r1 = r15
            goto L80
        L7f:
            r0 = move-exception
        L80:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L7f
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.packageinstaller.EventResultPersister.onEventReceived(android.content.Context, android.content.Intent):void");
    }

    private void writeState() {
        synchronized (this.mLock) {
            this.mIsPersistingStateValid = false;
            if (!this.mIsPersistScheduled) {
                this.mIsPersistScheduled = true;
                AsyncTask.execute(new Runnable() { // from class: com.android.packageinstaller.-$$Lambda$EventResultPersister$usQNOmFbHMT14knm3XmlDVnm0EE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$writeState$0$EventResultPersister();
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$writeState$0$EventResultPersister() {
        int counter;
        SparseArray<EventResult> results;
        while (true) {
            synchronized (this.mLock) {
                counter = this.mCounter;
                results = this.mResults.clone();
                this.mIsPersistingStateValid = true;
            }
            FileOutputStream stream = null;
            try {
                stream = this.mResultsFile.startWrite();
                XmlSerializer serializer = Xml.newSerializer();
                serializer.setOutput(stream, StandardCharsets.UTF_8.name());
                serializer.startDocument(null, true);
                serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                serializer.startTag(null, "results");
                serializer.attribute(null, "counter", Integer.toString(counter));
                int numResults = results.size();
                for (int i = 0; i < numResults; i++) {
                    serializer.startTag(null, "result");
                    serializer.attribute(null, "id", Integer.toString(results.keyAt(i)));
                    serializer.attribute(null, NotificationCompat.CATEGORY_STATUS, Integer.toString(results.valueAt(i).status));
                    serializer.attribute(null, "legacyStatus", Integer.toString(results.valueAt(i).legacyStatus));
                    if (results.valueAt(i).message != null) {
                        serializer.attribute(null, "statusMessage", results.valueAt(i).message);
                    }
                    serializer.endTag(null, "result");
                }
                serializer.endTag(null, "results");
                serializer.endDocument();
                this.mResultsFile.finishWrite(stream);
            } catch (IOException e) {
                if (stream != null) {
                    this.mResultsFile.failWrite(stream);
                }
                Log.e(LOG_TAG, "error writing results", e);
                this.mResultsFile.delete();
            }
            synchronized (this.mLock) {
                if (this.mIsPersistingStateValid) {
                    this.mIsPersistScheduled = false;
                    return;
                }
            }
        }
    }

    int addObserver(int id, EventResultObserver observer) throws OutOfIdsException {
        synchronized (this.mLock) {
            int resultIndex = -1;
            if (id == Integer.MIN_VALUE) {
                id = getNewId();
            } else {
                resultIndex = this.mResults.indexOfKey(id);
            }
            if (resultIndex >= 0) {
                EventResult result = this.mResults.valueAt(resultIndex);
                observer.onResult(result.status, result.legacyStatus, result.message);
                this.mResults.removeAt(resultIndex);
                writeState();
            } else {
                this.mObservers.put(id, observer);
            }
        }
        return id;
    }

    void removeObserver(int id) {
        synchronized (this.mLock) {
            this.mObservers.delete(id);
        }
    }

    private class EventResult {
        public final int legacyStatus;
        public final String message;
        public final int status;

        private EventResult(int status, int legacyStatus, String message) {
            this.status = status;
            this.legacyStatus = legacyStatus;
            this.message = message;
        }
    }

    class OutOfIdsException extends Exception {
        OutOfIdsException() {
        }
    }
}
