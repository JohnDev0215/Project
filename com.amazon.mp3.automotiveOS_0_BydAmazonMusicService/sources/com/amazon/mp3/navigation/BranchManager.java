package com.amazon.mp3.navigation;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.util.Log;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class BranchManager {
    private static final String TAG = "BranchManager";

    public Observable<Uri> getUriFromBranchReferringParameters(final Activity activity) {
        return Observable.create(new Observable.OnSubscribe<Uri>() { // from class: com.amazon.mp3.navigation.BranchManager.1
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Uri> subscriber) {
                Branch.getInstance(activity).initSession(new Branch.BranchReferralInitListener() { // from class: com.amazon.mp3.navigation.BranchManager.1.1
                    @Override // io.branch.referral.Branch.BranchReferralInitListener
                    public void onInitFinished(final JSONObject referringParams, final BranchError error) {
                        Log.verbose(BranchManager.TAG, "Branch session returned referringParams " + referringParams);
                        if (error != null) {
                            subscriber.onError(new BranchReturnedNoUriException("Cannot init Branch session. Error: " + error.getMessage()));
                            return;
                        }
                        if (referringParams != null) {
                            String strReconstructUriFromReferringParams = BranchManager.this.reconstructUriFromReferringParams(referringParams);
                            if (TextUtils.isEmpty(strReconstructUriFromReferringParams)) {
                                subscriber.onError(new BranchReturnedNoUriException("Branch session returned empty deep link path URI."));
                                return;
                            }
                            Uri uri = Uri.parse(strReconstructUriFromReferringParams);
                            Log.verbose(BranchManager.TAG, "Branch session returned referring uri: %s", uri.toString());
                            DeeplinkHandlerActivity.setBranchLink(referringParams.optString("~referring_link"));
                            String strOptString = referringParams.optString("+referrer");
                            if (!TextUtils.isEmpty(strOptString)) {
                                DeeplinkHandlerActivity.setDeeplinkRefererUri(Uri.parse(strOptString));
                            }
                            subscriber.onNext(uri);
                            subscriber.onCompleted();
                            return;
                        }
                        subscriber.onError(new BranchReturnedNoUriException("Branch session returned empty referringParams."));
                    }
                }, activity.getIntent().getData(), activity);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public static final class BranchReturnedNoUriException extends Exception {
        private BranchReturnedNoUriException(final String message) {
            super(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String reconstructUriFromReferringParams(final JSONObject referringParams) {
        StringBuilder sb = new StringBuilder();
        String strOptString = referringParams.optString("$deeplink_path");
        if (TextUtils.isEmpty(strOptString)) {
            return "";
        }
        sb.append(strOptString + "?");
        Iterator<String> itKeys = referringParams.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!shouldIgnoreParam(next)) {
                addQueryParam(sb, next, referringParams.optString(next));
            }
        }
        return sb.toString();
    }

    private boolean shouldIgnoreParam(final String queryParamName) {
        return queryParamName.startsWith(MqttTopic.SINGLE_LEVEL_WILDCARD) || queryParamName.startsWith("$") || queryParamName.startsWith("~") || queryParamName.equals("$deeplink_path");
    }

    private void addQueryParam(final StringBuilder uriString, final String queryParamName, final String queryParamValue) {
        if (TextUtils.isEmpty(queryParamValue)) {
            return;
        }
        uriString.append(queryParamName + "=" + queryParamValue + "&");
    }
}
