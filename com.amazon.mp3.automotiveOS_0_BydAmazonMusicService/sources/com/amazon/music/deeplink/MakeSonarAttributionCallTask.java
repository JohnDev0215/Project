package com.amazon.music.deeplink;

import android.os.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MakeSonarAttributionCallTask extends AsyncTask<String, Void, Boolean> {
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) MakeSonarAttributionCallTask.class);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        if (bool.booleanValue()) {
            LOGGER.debug("Sonar attribution call was successful");
        } else {
            LOGGER.debug("Sonar attribution call was unsuccessful");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.net.HttpURLConnection] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Boolean doInBackground(java.lang.String... r5) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            r2 = 0
            r5 = r5[r2]     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            java.net.URLConnection r5 = r1.openConnection()     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            java.lang.Object r5 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r5)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            java.net.URLConnection r5 = (java.net.URLConnection) r5     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            int r0 = r5.getResponseCode()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 != r1) goto L23
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            r5.disconnect()
            return r0
        L23:
            org.slf4j.Logger r0 = com.amazon.music.deeplink.MakeSonarAttributionCallTask.LOGGER     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            r1.<init>()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            java.lang.String r2 = "Sonar attribution returned with a status : "
            r1.append(r2)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            int r2 = r5.getResponseCode()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            r1.append(r2)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            java.lang.String r1 = r1.toString()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            r0.error(r1)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5d
            r5.disconnect()
            return r0
        L43:
            r0 = move-exception
            goto L4e
        L45:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L5e
        L4a:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L4e:
            org.slf4j.Logger r1 = com.amazon.music.deeplink.MakeSonarAttributionCallTask.LOGGER     // Catch: java.lang.Throwable -> L5d
            java.lang.String r2 = "Sonar attribution call failed"
            r1.error(r2, r0)     // Catch: java.lang.Throwable -> L5d
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> L5d
            if (r5 == 0) goto L5c
            r5.disconnect()
        L5c:
            return r0
        L5d:
            r0 = move-exception
        L5e:
            if (r5 == 0) goto L63
            r5.disconnect()
        L63:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.deeplink.MakeSonarAttributionCallTask.doInBackground(java.lang.String[]):java.lang.Boolean");
    }
}
