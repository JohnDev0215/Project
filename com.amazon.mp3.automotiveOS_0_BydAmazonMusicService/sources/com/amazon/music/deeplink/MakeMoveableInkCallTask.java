package com.amazon.music.deeplink;

import android.net.Uri;
import android.os.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MakeMoveableInkCallTask extends AsyncTask<String, Void, Uri> {
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) MakeMoveableInkCallTask.class);
    private static final String MI_SUBSTRING = "mi.amazonmusic.com";
    private static final int REQUEST_TIMEOUT = 1500;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Uri uri) {
        if (!uri.toString().contains(MI_SUBSTRING)) {
            LOGGER.debug("Movable Ink request was successful");
        } else {
            LOGGER.debug("Movable Ink request was unsuccessful");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0079: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:23:0x0079 */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007c  */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.net.Uri doInBackground(java.lang.String... r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r3 = r7[r0]     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.lang.Object r2 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r2)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.net.URLConnection r2 = (java.net.URLConnection) r2     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r2.setInstanceFollowRedirects(r0)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r1 = 1500(0x5dc, float:2.102E-42)
            r2.setReadTimeout(r1)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r2.setConnectTimeout(r1)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r2.connect()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            int r1 = r2.getResponseCode()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r3 = 302(0x12e, float:4.23E-43)
            if (r1 != r3) goto L39
            java.lang.String r1 = "Location"
            java.lang.String r1 = r2.getHeaderField(r1)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            android.net.Uri r7 = android.net.Uri.parse(r1)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r2.disconnect()
            return r7
        L39:
            org.slf4j.Logger r1 = com.amazon.music.deeplink.MakeMoveableInkCallTask.LOGGER     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r3.<init>()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            java.lang.String r4 = "Movable Ink request returned with a status : "
            r3.append(r4)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            int r4 = r2.getResponseCode()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r3.append(r4)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            java.lang.String r3 = r3.toString()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r1.error(r3)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r1 = r7[r0]     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            android.net.Uri r7 = android.net.Uri.parse(r1)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L78
            r2.disconnect()
            return r7
        L5d:
            r1 = move-exception
            goto L65
        L5f:
            r7 = move-exception
            goto L7a
        L61:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L65:
            org.slf4j.Logger r3 = com.amazon.music.deeplink.MakeMoveableInkCallTask.LOGGER     // Catch: java.lang.Throwable -> L78
            java.lang.String r4 = "Movable Ink request failed"
            r3.error(r4, r1)     // Catch: java.lang.Throwable -> L78
            r7 = r7[r0]     // Catch: java.lang.Throwable -> L78
            android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch: java.lang.Throwable -> L78
            if (r2 == 0) goto L77
            r2.disconnect()
        L77:
            return r7
        L78:
            r7 = move-exception
            r1 = r2
        L7a:
            if (r1 == 0) goto L7f
            r1.disconnect()
        L7f:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.deeplink.MakeMoveableInkCallTask.doInBackground(java.lang.String[]):android.net.Uri");
    }
}
