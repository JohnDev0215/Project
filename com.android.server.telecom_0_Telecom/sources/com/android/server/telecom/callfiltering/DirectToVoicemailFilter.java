package com.android.server.telecom.callfiltering;

import android.net.Uri;
import android.telecom.CallerInfo;
import android.telecom.Log;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallerInfoLookupHelper;
import com.android.server.telecom.callfiltering.CallFilteringResult;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/* JADX INFO: loaded from: classes.dex */
public class DirectToVoicemailFilter extends CallFilter {
    private final Call mCall;
    private final CallerInfoLookupHelper mCallerInfoLookupHelper;

    public DirectToVoicemailFilter(Call call, CallerInfoLookupHelper callerInfoLookupHelper) {
        this.mCall = call;
        this.mCallerInfoLookupHelper = callerInfoLookupHelper;
    }

    @Override // com.android.server.telecom.callfiltering.CallFilter
    public CompletionStage<CallFilteringResult> startFilterLookup(CallFilteringResult callFilteringResult) {
        Log.addEvent(this.mCall, "DIRECT_TO_VM_INITIATED");
        final CompletableFuture completableFuture = new CompletableFuture();
        this.mCallerInfoLookupHelper.startLookup(this.mCall.getHandle(), new CallerInfoLookupHelper.OnQueryCompleteListener() { // from class: com.android.server.telecom.callfiltering.DirectToVoicemailFilter.1
            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onContactPhotoQueryComplete(Uri uri, CallerInfo callerInfo) {
            }

            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onCallerInfoQueryComplete(Uri uri, CallerInfo callerInfo) {
                if (uri != null && Objects.equals(DirectToVoicemailFilter.this.mCall.getHandle(), uri) && callerInfo != null) {
                    CompletableFuture completableFuture2 = completableFuture;
                    CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
                    builder.setShouldAllowCall(!callerInfo.shouldSendToVoicemail);
                    builder.setShouldReject(callerInfo.shouldSendToVoicemail);
                    builder.setShouldAddToCallLog(true);
                    builder.setShouldShowNotification(true);
                    builder.setCallBlockReason(callerInfo.shouldSendToVoicemail ? 2 : 0);
                    builder.setContactExists(callerInfo.contactExists);
                    completableFuture2.complete(builder.build());
                } else {
                    if (callerInfo == null) {
                        Log.w(this, "CallerInfo lookup returned a null CallerInfo.", new Object[0]);
                    } else {
                        Log.w(this, "CallerInfo lookup returned with handle %s, should be %s", new Object[]{uri, DirectToVoicemailFilter.this.mCall.getHandle()});
                    }
                    completableFuture.complete(IncomingCallFilterGraph.DEFAULT_RESULT);
                }
                Log.addEvent(DirectToVoicemailFilter.this.mCall, "DIRECT_TO_VM_FINISHED");
            }
        });
        return completableFuture;
    }
}
