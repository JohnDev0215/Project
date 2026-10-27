package android.inputmethodservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.autofill.AutofillId;
import android.view.inputmethod.InlineSuggestion;
import android.view.inputmethod.InlineSuggestionsRequest;
import android.view.inputmethod.InlineSuggestionsResponse;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.internal.view.IInlineSuggestionsRequestCallback;
import com.android.internal.view.IInlineSuggestionsResponseCallback;
import com.android.internal.view.InlineSuggestionsRequestInfo;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
class InlineSuggestionSession {
    static final InlineSuggestionsResponse EMPTY_RESPONSE = new InlineSuggestionsResponse((List<InlineSuggestion>) Collections.emptyList());
    private static final String TAG = "ImsInlineSuggestionSession";
    private final IInlineSuggestionsRequestCallback mCallback;
    private boolean mCallbackInvoked = false;
    private final Supplier<IBinder> mHostInputTokenSupplier;
    private final InlineSuggestionSessionController mInlineSuggestionSessionController;
    private final Handler mMainThreadHandler;
    private Boolean mPreviousResponseIsEmpty;
    private final InlineSuggestionsRequestInfo mRequestInfo;
    private final Function<Bundle, InlineSuggestionsRequest> mRequestSupplier;
    private InlineSuggestionsResponseCallbackImpl mResponseCallback;
    private final Consumer<InlineSuggestionsResponse> mResponseConsumer;

    InlineSuggestionSession(InlineSuggestionsRequestInfo inlineSuggestionsRequestInfo, IInlineSuggestionsRequestCallback iInlineSuggestionsRequestCallback, Function<Bundle, InlineSuggestionsRequest> function, Supplier<IBinder> supplier, Consumer<InlineSuggestionsResponse> consumer, InlineSuggestionSessionController inlineSuggestionSessionController, Handler handler) {
        this.mRequestInfo = inlineSuggestionsRequestInfo;
        this.mCallback = iInlineSuggestionsRequestCallback;
        this.mRequestSupplier = function;
        this.mHostInputTokenSupplier = supplier;
        this.mResponseConsumer = consumer;
        this.mInlineSuggestionSessionController = inlineSuggestionSessionController;
        this.mMainThreadHandler = handler;
    }

    InlineSuggestionsRequestInfo getRequestInfo() {
        return this.mRequestInfo;
    }

    IInlineSuggestionsRequestCallback getRequestCallback() {
        return this.mCallback;
    }

    boolean shouldSendImeStatus() {
        return this.mResponseCallback != null;
    }

    boolean isCallbackInvoked() {
        return this.mCallbackInvoked;
    }

    void invalidate() {
        try {
            this.mCallback.onInlineSuggestionsSessionInvalidated();
        } catch (RemoteException e) {
            Log.w(TAG, "onInlineSuggestionsSessionInvalidated() remote exception:" + e);
        }
        if (this.mResponseCallback != null) {
            consumeInlineSuggestionsResponse(EMPTY_RESPONSE);
            this.mResponseCallback.invalidate();
            this.mResponseCallback = null;
        }
    }

    void makeInlineSuggestionRequestUncheck() {
        if (this.mCallbackInvoked) {
            return;
        }
        try {
            InlineSuggestionsRequest inlineSuggestionsRequestApply = this.mRequestSupplier.apply(this.mRequestInfo.getUiExtras());
            if (inlineSuggestionsRequestApply == null) {
                this.mCallback.onInlineSuggestionsUnsupported();
            } else {
                inlineSuggestionsRequestApply.setHostInputToken(this.mHostInputTokenSupplier.get());
                inlineSuggestionsRequestApply.filterContentTypes();
                InlineSuggestionsResponseCallbackImpl inlineSuggestionsResponseCallbackImpl = new InlineSuggestionsResponseCallbackImpl();
                this.mResponseCallback = inlineSuggestionsResponseCallbackImpl;
                this.mCallback.onInlineSuggestionsRequest(inlineSuggestionsRequestApply, inlineSuggestionsResponseCallbackImpl);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "makeInlinedSuggestionsRequest() remote exception:" + e);
        }
        this.mCallbackInvoked = true;
    }

    void handleOnInlineSuggestionsResponse(AutofillId autofillId, InlineSuggestionsResponse inlineSuggestionsResponse) {
        if (this.mInlineSuggestionSessionController.match(autofillId)) {
            consumeInlineSuggestionsResponse(inlineSuggestionsResponse);
        }
    }

    void consumeInlineSuggestionsResponse(InlineSuggestionsResponse inlineSuggestionsResponse) {
        boolean zIsEmpty = inlineSuggestionsResponse.getInlineSuggestions().isEmpty();
        if (zIsEmpty && Boolean.TRUE.equals(this.mPreviousResponseIsEmpty)) {
            return;
        }
        this.mPreviousResponseIsEmpty = Boolean.valueOf(zIsEmpty);
        this.mResponseConsumer.accept(inlineSuggestionsResponse);
    }

    private static final class InlineSuggestionsResponseCallbackImpl extends IInlineSuggestionsResponseCallback.Stub {
        private volatile boolean mInvalid;
        private final WeakReference<InlineSuggestionSession> mSession;

        private InlineSuggestionsResponseCallbackImpl(InlineSuggestionSession inlineSuggestionSession) {
            this.mInvalid = false;
            this.mSession = new WeakReference<>(inlineSuggestionSession);
        }

        void invalidate() {
            this.mInvalid = true;
        }

        @Override // com.android.internal.view.IInlineSuggestionsResponseCallback
        public void onInlineSuggestionsResponse(AutofillId autofillId, InlineSuggestionsResponse inlineSuggestionsResponse) {
            InlineSuggestionSession inlineSuggestionSession;
            if (this.mInvalid || (inlineSuggestionSession = this.mSession.get()) == null) {
                return;
            }
            inlineSuggestionSession.mMainThreadHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.inputmethodservice.-$$Lambda$gFA42jHMHyW9teqkAqcpq3JsqOY
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((InlineSuggestionSession) obj).handleOnInlineSuggestionsResponse((AutofillId) obj2, (InlineSuggestionsResponse) obj3);
                }
            }, inlineSuggestionSession, autofillId, inlineSuggestionsResponse));
        }
    }
}
