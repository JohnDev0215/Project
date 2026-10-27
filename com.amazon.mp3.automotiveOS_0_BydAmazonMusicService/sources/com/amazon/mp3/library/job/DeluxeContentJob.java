package com.amazon.mp3.library.job;

import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.download.library.DownloadFileUtil;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.playback.service.PlaybackServiceUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;
import com.google.firebase.perf.network.FirebasePerfHttpClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class DeluxeContentJob extends Job {
    private static final String TAG = "DeluxeContentJob";
    private volatile boolean mJobCancelled = false;
    private String mPath;
    private volatile HttpGet mRequest;
    private DeluxeContentUtil.ContentType mRequestType;
    private Uri mUri;

    public DeluxeContentJob(DeluxeContentUtil.ContentType type, Uri uri, String path) {
        this.mRequestType = type;
        this.mPath = path;
        this.mUri = uri;
    }

    public DeluxeContentJob(DeluxeContentUtil.ContentType type, Uri uri) {
        this.mRequestType = type;
        this.mUri = uri;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() {
        Bundle bundle = new Bundle();
        try {
            String str = DeluxeContentUtil.EXTRA_REQUEST_KEY;
            bundle.putSerializable(str, this.mRequestType);
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[this.mRequestType.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return 1;
                }
                String deluxeContentUri = getDeluxeContentUri();
                if (deluxeContentUri == null) {
                    return 3;
                }
                bundle.putSerializable(str, DeluxeContentUtil.ContentType.VIDEO);
                bundle.putString(DeluxeContentUtil.EXTRA_URI_KEY, deluxeContentUri);
                setResult(bundle);
                return 1;
            }
            bundle.putSerializable(str, DeluxeContentUtil.ContentType.DOCUMENT);
            bundle.putString(DeluxeContentUtil.EXTRA_PATH_KEY, this.mPath);
            setResult(bundle);
            if (new File(this.mPath).exists()) {
                return 1;
            }
            try {
                String deluxeContentUri2 = getDeluxeContentUri();
                if (deluxeContentUri2 == null) {
                    return 3;
                }
                this.mRequest = new HttpGet(deluxeContentUri2);
                if (this.mJobCancelled) {
                    return 2;
                }
                HttpResponse httpResponseExecute = FirebasePerfHttpClient.execute(new DefaultHttpClient(), this.mRequest);
                FileOutputStream fileOutputStreamOpenOutputStream = DownloadFileUtil.openOutputStream(this.mPath, (int) httpResponseExecute.getEntity().getContentLength());
                try {
                    try {
                        httpResponseExecute.getEntity().writeTo(fileOutputStreamOpenOutputStream);
                        if (fileOutputStreamOpenOutputStream != null) {
                            fileOutputStreamOpenOutputStream.close();
                        }
                        return 1;
                    } catch (IOException unused) {
                        Log.warning(TAG, "IOException trying to write downloaded booklet to file");
                        int i2 = this.mJobCancelled ? 2 : 3;
                        if (fileOutputStreamOpenOutputStream != null) {
                            fileOutputStreamOpenOutputStream.close();
                        }
                        return i2;
                    }
                } catch (Throwable th) {
                    if (fileOutputStreamOpenOutputStream != null) {
                        fileOutputStreamOpenOutputStream.close();
                    }
                    throw th;
                }
            } catch (ClientProtocolException unused2) {
                Log.warning(TAG, "ClientProtocolException trying to execute http request to get booklet");
                return 3;
            } catch (IOException unused3) {
                Log.warning(TAG, "IOException trying to execute http request to get booklet");
                return this.mJobCancelled ? 2 : 3;
            }
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "HttpClientException trying to get URI from luid " + e.getMessage());
            return 3;
        } catch (DMLSExceptions.DMLSException e2) {
            Log.error(TAG, "DMLSException trying to get URI from luid " + e2.getMessage());
            return this.mJobCancelled ? 2 : 3;
        } catch (JSONException e3) {
            Log.error(TAG, "JSONException trying to get URI from luid " + e3.getMessage());
            return 3;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.job.DeluxeContentJob$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType;

        static {
            int[] iArr = new int[DeluxeContentUtil.ContentType.values().length];
            $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType = iArr;
            try {
                iArr[DeluxeContentUtil.ContentType.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[DeluxeContentUtil.ContentType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.mp3.service.job.Job
    public void cancel() {
        super.cancel();
        this.mJobCancelled = true;
        if (this.mRequest != null) {
            this.mRequest.abort();
        }
    }

    @Override // com.amazon.mp3.service.job.Job
    protected void onFinished(int resultCode, Bundle resultBundle) {
        super.onFinished(resultCode, resultBundle);
        if ((resultCode == 2 || resultCode == 3) && this.mPath != null) {
            new File(this.mPath).delete();
        }
    }

    private String getDeluxeContentUri() throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        return PlaybackServiceUtil.getDeluxeContentUriFromLuid(PlaybackServiceUtil.getLuidFromContentUri(this.mUri));
    }
}
