package com.android.server.telecom.callfiltering;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.BlockedNumberContract;
import android.telecom.CallerInfo;
import android.telecom.Log;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallerInfoLookupHelper;
import com.android.server.telecom.LoggedHandlerExecutor;
import com.android.server.telecom.callfiltering.CallFilteringResult;
import com.android.server.telecom.settings.BlockedNumbersUtil;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class BlockCheckerFilter extends CallFilter {
    private final BlockCheckerAdapter mBlockCheckerAdapter;
    private final Call mCall;
    private final CallerInfoLookupHelper mCallerInfoLookupHelper;
    private boolean mContactExists = false;
    private final Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    public BlockCheckerFilter(Context context, Call call, CallerInfoLookupHelper callerInfoLookupHelper, BlockCheckerAdapter blockCheckerAdapter) {
        this.mCall = call;
        this.mContext = context;
        this.mCallerInfoLookupHelper = callerInfoLookupHelper;
        this.mBlockCheckerAdapter = blockCheckerAdapter;
        HandlerThread handlerThread = new HandlerThread("BlockCheckerFilter");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    @Override // com.android.server.telecom.callfiltering.CallFilter
    public CompletionStage<CallFilteringResult> startFilterLookup(CallFilteringResult callFilteringResult) {
        Log.addEvent(this.mCall, "BLOCK_CHECK_INITIATED");
        final CompletableFuture<CallFilteringResult> completableFuture = new CompletableFuture<>();
        Bundle bundle = new Bundle();
        if (BlockedNumbersUtil.isEnhancedCallBlockingEnabledByPlatform(this.mContext)) {
            int handlePresentation = this.mCall.getHandlePresentation();
            bundle.putInt("extra_call_presentation", handlePresentation);
            if (handlePresentation == 1) {
                this.mCallerInfoLookupHelper.startLookup(this.mCall.getHandle(), new CallerInfoLookupHelper.OnQueryCompleteListener() { // from class: com.android.server.telecom.callfiltering.BlockCheckerFilter.1
                    @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
                    public void onContactPhotoQueryComplete(Uri uri, CallerInfo callerInfo) {
                    }

                    @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
                    public void onCallerInfoQueryComplete(Uri uri, CallerInfo callerInfo) {
                        if (callerInfo != null && callerInfo.contactExists) {
                            BlockCheckerFilter.this.mContactExists = true;
                        }
                        BlockCheckerFilter.this.getBlockStatus(completableFuture);
                    }
                });
            } else {
                getBlockStatus(completableFuture);
            }
        } else {
            getBlockStatus(completableFuture);
        }
        return completableFuture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBlockStatus(final CompletableFuture<CallFilteringResult> completableFuture) {
        final Bundle bundle = new Bundle();
        if (BlockedNumbersUtil.isEnhancedCallBlockingEnabledByPlatform(this.mContext)) {
            int handlePresentation = this.mCall.getHandlePresentation();
            bundle.putInt("extra_call_presentation", handlePresentation);
            if (handlePresentation == 1) {
                bundle.putBoolean("extra_contact_exist", this.mContactExists);
            }
        }
        final String schemeSpecificPart = this.mCall.getHandle() == null ? null : this.mCall.getHandle().getSchemeSpecificPart();
        CompletableFuture.supplyAsync(new Supplier() { // from class: com.android.server.telecom.callfiltering.-$$Lambda$BlockCheckerFilter$nkSmJYUC7mmd5wZwXoRbI7beg9g
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$getBlockStatus$0$BlockCheckerFilter(schemeSpecificPart, bundle);
            }
        }, new LoggedHandlerExecutor(this.mHandler, "BCF.gBS", null)).thenApplyAsync(new Function() { // from class: com.android.server.telecom.callfiltering.-$$Lambda$BlockCheckerFilter$c60H37nNxeKiwn13C3h7NBoH-eY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$getBlockStatus$1$BlockCheckerFilter(completableFuture, (Integer) obj);
            }
        }, (Executor) new LoggedHandlerExecutor(this.mHandler, "BCF.gBS", null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getBlockStatus$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$getBlockStatus$0$BlockCheckerFilter(String str, Bundle bundle) {
        return Integer.valueOf(this.mBlockCheckerAdapter.getBlockStatus(this.mContext, str, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getBlockStatus$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$getBlockStatus$1$BlockCheckerFilter(CompletableFuture completableFuture, Integer num) {
        int iIntValue = num.intValue();
        completeResult(completableFuture, iIntValue);
        return Integer.valueOf(iIntValue);
    }

    private int completeResult(CompletableFuture<CallFilteringResult> completableFuture, int i) {
        CallFilteringResult callFilteringResultBuild;
        if (i != 0) {
            CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
            builder.setShouldAllowCall(false);
            builder.setShouldReject(true);
            builder.setShouldAddToCallLog(true);
            builder.setShouldShowNotification(false);
            builder.setCallBlockReason(getBlockReason(i));
            builder.setCallScreeningAppName(null);
            builder.setCallScreeningComponentName(null);
            builder.setContactExists(this.mContactExists);
            callFilteringResultBuild = builder.build();
        } else {
            CallFilteringResult.Builder builder2 = new CallFilteringResult.Builder();
            builder2.setShouldAllowCall(true);
            builder2.setShouldReject(false);
            builder2.setShouldSilence(false);
            builder2.setShouldAddToCallLog(true);
            builder2.setShouldShowNotification(true);
            builder2.setContactExists(this.mContactExists);
            callFilteringResultBuild = builder2.build();
        }
        Log.addEvent(this.mCall, "BLOCK_CHECK_FINISHED", BlockedNumberContract.SystemContract.blockStatusToString(i) + " " + callFilteringResultBuild);
        completableFuture.complete(callFilteringResultBuild);
        this.mHandlerThread.quitSafely();
        return i;
    }

    private int getBlockReason(int i) {
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 5;
        }
        if (i == 3) {
            return 4;
        }
        if (i == 4) {
            return 6;
        }
        if (i == 5) {
            return 7;
        }
        Log.w(this, "There's no call log block reason can be converted", new Object[0]);
        return 3;
    }
}
