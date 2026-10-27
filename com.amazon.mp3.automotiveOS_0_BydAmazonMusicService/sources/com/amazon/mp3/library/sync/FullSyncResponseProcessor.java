package com.amazon.mp3.library.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.library.service.sync.SSLHandshakeUtil;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.stability.StabilityManager;
import com.amazon.mp3.util.IoUtil;
import com.amazon.mp3.util.Log;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedWriter;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLHandshakeException;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class FullSyncResponseProcessor {
    private static final String TAG = "FullSyncResponseProcessor";
    private final Context mContext;
    private CSVProcessor mCsvParser;
    private final SQLiteDatabase mDb;

    public FullSyncResponseProcessor(Context context, SQLiteDatabase db) {
        this.mContext = context;
        this.mDb = db;
    }

    public int process(JSONObject response) throws Exception {
        int iDownloadAndParseCSVFile = downloadAndParseCSVFile(response.getString("snapshotURL"), response.getString("checkpoint"));
        SyncService.setLastCloudSyncTime(this.mContext, new Date().getTime());
        SettingsUtil.setLastAutoDownloadDateToLastPurchaseDateIfEnabled(this.mContext);
        return iDownloadAndParseCSVFile;
    }

    private int downloadAndParseCSVFile(String snapshotURL, String checkpoint) throws Exception {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Log.verbose(TAG, "Sync CSV file downloading started.");
        byte[] bArr = new byte[102400];
        PipedWriter pipedWriter = new PipedWriter();
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                CSVProcessor cSVProcessor = new CSVProcessor(this.mContext, pipedWriter, this.mDb);
                this.mCsvParser = cSVProcessor;
                cSVProcessor.start();
                Response responseExecute = FirebasePerfOkHttpClient.execute(UnifiedOkHttpClient.newBuilder().eventListener(StabilityManager.INSTANCE.getBugsnagOkHttpPlugin()).build().newCall(new Request.Builder().url(snapshotURL).addHeader("Accept-Encoding", "gzip").build()));
                if (responseExecute.isSuccessful()) {
                    InputStream inputStreamByteStream = responseExecute.body().byteStream();
                    if ("gzip".equalsIgnoreCase(responseExecute.header("Content-Encoding"))) {
                        inputStreamByteStream = new GZIPInputStream(inputStreamByteStream);
                    }
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStreamByteStream, 102400);
                    int i = 0;
                    while (true) {
                        try {
                            int i2 = bufferedInputStream2.read(bArr);
                            if (i2 == -1) {
                                break;
                            }
                            pipedWriter.append((CharSequence) new String(bArr, 0, i2));
                            i += i2;
                            Log.verbose(TAG, "Sync CSV file downloading Bytes read %s. Total read %s", Integer.valueOf(i2), Integer.valueOf(i));
                        } catch (SSLHandshakeException e) {
                            e = e;
                            SSLHandshakeUtil.captureAdditionalDebugLogs(e, snapshotURL);
                            MetricsLogger.sendSSLHandShakeFailedEvent(snapshotURL, e);
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream = bufferedInputStream2;
                            IoUtil.close(bufferedInputStream);
                            IoUtil.close(pipedWriter);
                            throw th;
                        }
                    }
                    pipedWriter.close();
                    String str = TAG;
                    Log.verbose(str, "Sync CSV waiting for CSV parser to complete operation.");
                    this.mCsvParser.join();
                    if (this.mCsvParser.isSuccess()) {
                        Log.verbose(str, "Sync CSV file parsing finished. Took %ss", Float.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0f));
                        SyncService.setSyncLastCheckpoint(this.mContext, checkpoint);
                        int updatedResourceCount = this.mCsvParser.getUpdatedResourceCount();
                        IoUtil.close(bufferedInputStream2);
                        IoUtil.close(pipedWriter);
                        return updatedResourceCount;
                    }
                    Log.error(str, "Sync CSV failed to parse a file.");
                    throw new Exception("Cannot parse CSV file");
                }
                throw new IOException("Failed to sync user's library with response = " + responseExecute);
            } catch (SSLHandshakeException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
