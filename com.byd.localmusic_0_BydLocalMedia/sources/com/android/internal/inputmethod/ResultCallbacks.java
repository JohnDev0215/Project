package com.android.internal.inputmethod;

import android.view.inputmethod.ExtractedText;
import com.android.internal.inputmethod.CancellationGroup;
import com.android.internal.inputmethod.ICharSequenceResultCallback;
import com.android.internal.inputmethod.IExtractedTextResultCallback;
import com.android.internal.inputmethod.IIntResultCallback;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class ResultCallbacks {
    private ResultCallbacks() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T unwrap(AtomicReference<WeakReference<T>> atomicReference) {
        WeakReference<T> andSet = atomicReference.getAndSet(null);
        if (andSet == null) {
            return null;
        }
        T t = andSet.get();
        andSet.clear();
        return t;
    }

    public static IIntResultCallback.Stub of(CancellationGroup.Completable.Int r2) {
        final AtomicReference atomicReference = new AtomicReference(new WeakReference(r2));
        return new IIntResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.1
            @Override // com.android.internal.inputmethod.IIntResultCallback
            public void onResult(int i) {
                CancellationGroup.Completable.Int r0 = (CancellationGroup.Completable.Int) ResultCallbacks.unwrap(atomicReference);
                if (r0 == null) {
                    return;
                }
                r0.onComplete(i);
            }
        };
    }

    public static ICharSequenceResultCallback.Stub of(CancellationGroup.Completable.CharSequence charSequence) {
        final AtomicReference atomicReference = new AtomicReference(new WeakReference(charSequence));
        return new ICharSequenceResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.2
            @Override // com.android.internal.inputmethod.ICharSequenceResultCallback
            public void onResult(CharSequence charSequence2) {
                CancellationGroup.Completable.CharSequence charSequence3 = (CancellationGroup.Completable.CharSequence) ResultCallbacks.unwrap(atomicReference);
                if (charSequence3 == null) {
                    return;
                }
                charSequence3.onComplete(charSequence2);
            }
        };
    }

    public static IExtractedTextResultCallback.Stub of(CancellationGroup.Completable.ExtractedText extractedText) {
        final AtomicReference atomicReference = new AtomicReference(new WeakReference(extractedText));
        return new IExtractedTextResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.3
            @Override // com.android.internal.inputmethod.IExtractedTextResultCallback
            public void onResult(ExtractedText extractedText2) {
                CancellationGroup.Completable.ExtractedText extractedText3 = (CancellationGroup.Completable.ExtractedText) ResultCallbacks.unwrap(atomicReference);
                if (extractedText3 == null) {
                    return;
                }
                extractedText3.onComplete(extractedText2);
            }
        };
    }
}
