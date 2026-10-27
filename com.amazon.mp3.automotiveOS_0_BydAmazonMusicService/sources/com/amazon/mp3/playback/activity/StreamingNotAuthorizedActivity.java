package com.amazon.mp3.playback.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.mp3.activity.util.SingleInstanceMonitor;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.LibraryBaseActivity;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.cirrus.LibraryRequest;
import com.amazon.mp3.playback.service.streaming.StreamingNotAuthorizedErrorHandler;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringId;
import com.amazon.music.inappmessaging.internal.model.Splash;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingNotAuthorizedActivity extends LibraryBaseActivity {
    private static final String TAG = "StreamingNotAuthorizedActivity";
    private SingleInstanceMonitor mInstanceMonitor;
    private DialogInterface.OnClickListener mDialogYesClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playback.activity.StreamingNotAuthorizedActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            new AutoKickAsyncTask().execute(new Void[0]);
        }
    };
    private DialogInterface.OnClickListener mDialogNoClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playback.activity.StreamingNotAuthorizedActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            StreamingNotAuthorizedActivity.this.finish();
        }
    };

    enum RevocationResult {
        Success,
        NetworkError,
        UnexpectedCirrusError
    }

    public static void startIfTriggered(Activity context) {
        if (StreamingNotAuthorizedErrorHandler.isTriggered()) {
            StreamingNotAuthorizedErrorHandler.reset(context);
            context.startActivity(getStartIntent(context));
        }
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) StreamingNotAuthorizedActivity.class);
        intent.addFlags(536870912);
        intent.addFlags(131072);
        return intent;
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SingleInstanceMonitor singleInstanceMonitor = new SingleInstanceMonitor(this);
        this.mInstanceMonitor = singleInstanceMonitor;
        singleInstanceMonitor.onCreate(savedInstanceState);
        StreamingNotAuthorizedErrorHandler.hideNotification(this);
        createAndShowKickDialog();
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mInstanceMonitor.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mInstanceMonitor.onStart();
    }

    private Dialog createAndShowKickDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_streaming_concurrency_error_auto_kick_title);
        bauhausDialogBuilder.setMessage(StringId.getOrConvertStringId(R.string.dmusic_streaming_concurrency_error_auto_kick_message));
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, this.mDialogNoClickListener);
        Dialog dialogCreate = bauhausDialogBuilder.create();
        dialogCreate.setCancelable(false);
        dialogCreate.show();
        return dialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressDialog createAndShowProgressDialog() {
        return ProgressDialog.show(this, "", getString(R.string.dmusic_streaming_concurrency_kick_progress_message), true, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Dialog createAndShowErrorDialog(RevocationResult result) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        if (result == RevocationResult.NetworkError) {
            bauhausDialogBuilder.setTitle(R.string.dmusic_streaming_concurrency_kick_error_title);
            bauhausDialogBuilder.setMessage(R.string.dmusic_streaming_concurrency_kick_network_error_message);
        } else if (result == RevocationResult.UnexpectedCirrusError) {
            bauhausDialogBuilder.setTitle(R.string.dmusic_streaming_concurrency_kick_error_title);
            bauhausDialogBuilder.setMessage(R.string.dmusic_streaming_concurrency_kick_unexpected_cirrus_error_message);
        } else {
            throw new IllegalArgumentException("unknown RevocationResult");
        }
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_retry, this.mDialogYesClickListener);
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_ok, this.mDialogNoClickListener);
        Dialog dialogCreate = bauhausDialogBuilder.create();
        dialogCreate.setCancelable(false);
        dialogCreate.show();
        return dialogCreate;
    }

    private class AutoKickAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog mProgressDialog;
        private RevocationResult mResult;

        private AutoKickAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            try {
                JSONObject firstStreamingDevice = getFirstStreamingDevice();
                if (firstStreamingDevice != null) {
                    String strOptString = firstStreamingDevice.optString(Splash.PARAMS_DEVICE_TYPE);
                    String strOptString2 = firstStreamingDevice.optString(Splash.PARAMS_DEVICE_ID);
                    if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                        revokeStreamingDevice(strOptString, strOptString2);
                    }
                }
                this.mResult = RevocationResult.Success;
                return null;
            } catch (AbstractHttpClient.HttpClientException e) {
                Log.error(StreamingNotAuthorizedActivity.TAG, "Kick failed due to JSON HttpClientException %s", e.getClass());
                this.mResult = RevocationResult.NetworkError;
                return null;
            } catch (CirrusExceptions.CirrusException e2) {
                Log.error(StreamingNotAuthorizedActivity.TAG, "Kick failed due to JSON CirrusException %s", e2.getClass());
                this.mResult = RevocationResult.UnexpectedCirrusError;
                return null;
            } catch (JSONException unused) {
                Log.error(StreamingNotAuthorizedActivity.TAG, "Kick failed due to JSON parse exception");
                this.mResult = RevocationResult.UnexpectedCirrusError;
                return null;
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            this.mProgressDialog = StreamingNotAuthorizedActivity.this.createAndShowProgressDialog();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            super.onPostExecute(result);
            this.mProgressDialog.dismiss();
            if (this.mResult == RevocationResult.Success) {
                StreamingNotAuthorizedActivity.this.finish();
            } else {
                if (BlueMoonExceptionsUtil.isBlueMoonException()) {
                    return;
                }
                StreamingNotAuthorizedActivity.this.createAndShowErrorDialog(this.mResult);
            }
        }

        private JSONObject getFirstStreamingDevice() throws JSONException, AbstractHttpClient.HttpClientException, CirrusExceptions.CirrusException {
            JSONArray jSONArray = LibraryRequest.ListStreamingDevices.execute().getJSONObject("listStreamingDevicesResponse").getJSONObject("listStreamingDevicesResult").getJSONArray("devices");
            if (jSONArray == null || jSONArray.length() <= 0) {
                return null;
            }
            return jSONArray.getJSONObject(0);
        }

        private void revokeStreamingDevice(String deviceType, String deviceId) throws JSONException, AbstractHttpClient.HttpClientException, CirrusExceptions.CirrusException {
            LibraryRequest.RevokeStreamingDevice.execute(new JSONObject().put(Splash.PARAMS_DEVICE_TYPE, deviceType).put(Splash.PARAMS_DEVICE_ID, deviceId));
        }
    }
}
