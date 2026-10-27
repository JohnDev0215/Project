package android.view.inputmethod;

import android.annotation.NonNull;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Size;
import android.util.Slog;
import android.view.SurfaceControlViewHost;
import android.view.ViewGroup;
import android.widget.inline.InlineContentView;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Parcelling;
import com.android.internal.view.inline.IInlineContentCallback;
import com.android.internal.view.inline.IInlineContentProvider;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public final class InlineSuggestion implements Parcelable {
    public static final Parcelable.Creator<InlineSuggestion> CREATOR;
    private static final String TAG = "InlineSuggestion";
    static Parcelling<InlineContentCallbackImpl> sParcellingForInlineContentCallback;
    private final IInlineContentProvider mContentProvider;
    private final InlineSuggestionInfo mInfo;
    private InlineContentCallbackImpl mInlineContentCallback;

    @Deprecated
    private void __metadata() {
    }

    private static boolean isValid(int i, int i2, int i3) {
        if (i == -2) {
            return true;
        }
        return i >= i2 && i <= i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static InlineSuggestion newInlineSuggestion(InlineSuggestionInfo inlineSuggestionInfo) {
        return new InlineSuggestion(inlineSuggestionInfo, null, null);
    }

    public InlineSuggestion(InlineSuggestionInfo inlineSuggestionInfo, IInlineContentProvider iInlineContentProvider) {
        this(inlineSuggestionInfo, iInlineContentProvider, null);
    }

    public void inflate(Context context, Size size, Executor executor, final Consumer<InlineContentView> consumer) {
        Size minSize = this.mInfo.getInlinePresentationSpec().getMinSize();
        Size maxSize = this.mInfo.getInlinePresentationSpec().getMaxSize();
        if (!isValid(size.getWidth(), minSize.getWidth(), maxSize.getWidth()) || !isValid(size.getHeight(), minSize.getHeight(), maxSize.getHeight())) {
            throw new IllegalArgumentException("size is neither between min:" + minSize + " and max:" + maxSize + ", nor wrap_content");
        }
        this.mInlineContentCallback = getInlineContentCallback(context, executor, consumer);
        IInlineContentProvider iInlineContentProvider = this.mContentProvider;
        if (iInlineContentProvider == null) {
            executor.execute(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$1Z84E2nGcL7UkGjb1l1YHUY09Qo
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(null);
                }
            });
            return;
        }
        try {
            iInlineContentProvider.provideContent(size.getWidth(), size.getHeight(), new InlineContentCallbackWrapper(this.mInlineContentCallback));
        } catch (RemoteException e) {
            Slog.w(TAG, "Error creating suggestion content surface: " + e);
            executor.execute(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$Svlod0cVfjyzxKJchAioH7zzqJQ
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(null);
                }
            });
        }
    }

    private synchronized InlineContentCallbackImpl getInlineContentCallback(Context context, Executor executor, Consumer<InlineContentView> consumer) {
        if (this.mInlineContentCallback != null) {
            throw new IllegalStateException("Already called #inflate()");
        }
        return new InlineContentCallbackImpl(context, this.mContentProvider, executor, consumer);
    }

    private static final class InlineContentCallbackWrapper extends IInlineContentCallback.Stub {
        private final WeakReference<InlineContentCallbackImpl> mCallbackImpl;

        InlineContentCallbackWrapper(InlineContentCallbackImpl inlineContentCallbackImpl) {
            this.mCallbackImpl = new WeakReference<>(inlineContentCallbackImpl);
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onContent(SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) {
            InlineContentCallbackImpl inlineContentCallbackImpl = this.mCallbackImpl.get();
            if (inlineContentCallbackImpl != null) {
                inlineContentCallbackImpl.onContent(surfacePackage, i, i2);
            }
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onClick() {
            InlineContentCallbackImpl inlineContentCallbackImpl = this.mCallbackImpl.get();
            if (inlineContentCallbackImpl != null) {
                inlineContentCallbackImpl.onClick();
            }
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onLongClick() {
            InlineContentCallbackImpl inlineContentCallbackImpl = this.mCallbackImpl.get();
            if (inlineContentCallbackImpl != null) {
                inlineContentCallbackImpl.onLongClick();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class InlineContentCallbackImpl {
        private final Consumer<InlineContentView> mCallback;
        private final Executor mCallbackExecutor;
        private final Context mContext;
        private final IInlineContentProvider mInlineContentProvider;
        private SurfaceControlViewHost.SurfacePackage mSurfacePackage;
        private Consumer<SurfaceControlViewHost.SurfacePackage> mSurfacePackageConsumer;
        private InlineContentView mView;
        private final Handler mMainHandler = new Handler(Looper.getMainLooper());
        private boolean mFirstContentReceived = false;

        InlineContentCallbackImpl(Context context, IInlineContentProvider iInlineContentProvider, Executor executor, Consumer<InlineContentView> consumer) {
            this.mContext = context;
            this.mInlineContentProvider = iInlineContentProvider;
            this.mCallbackExecutor = executor;
            this.mCallback = consumer;
        }

        public void onContent(final SurfaceControlViewHost.SurfacePackage surfacePackage, final int i, final int i2) {
            this.mMainHandler.post(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$InlineContentCallbackImpl$1iQeV802Da0EJqlELwXcCuQIVdM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onContent$0$InlineSuggestion$InlineContentCallbackImpl(surfacePackage, i, i2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: handleOnContent, reason: merged with bridge method [inline-methods] */
        public void lambda$onContent$0$InlineSuggestion$InlineContentCallbackImpl(SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) {
            if (!this.mFirstContentReceived) {
                handleOnFirstContentReceived(surfacePackage, i, i2);
                this.mFirstContentReceived = true;
            } else {
                handleOnSurfacePackage(surfacePackage);
            }
        }

        private void handleOnFirstContentReceived(SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) {
            this.mSurfacePackage = surfacePackage;
            if (surfacePackage == null) {
                this.mCallbackExecutor.execute(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$InlineContentCallbackImpl$n8U3GjMW_nvrfe7Z6T3PZXOJb5o
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleOnFirstContentReceived$1$InlineSuggestion$InlineContentCallbackImpl();
                    }
                });
                return;
            }
            InlineContentView inlineContentView = new InlineContentView(this.mContext);
            this.mView = inlineContentView;
            inlineContentView.setLayoutParams(new ViewGroup.LayoutParams(i, i2));
            this.mView.setChildSurfacePackageUpdater(getSurfacePackageUpdater());
            this.mCallbackExecutor.execute(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$InlineContentCallbackImpl$uxQy-Xvm9tEtpDfDJOobVwjMLjY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleOnFirstContentReceived$2$InlineSuggestion$InlineContentCallbackImpl();
                }
            });
        }

        public /* synthetic */ void lambda$handleOnFirstContentReceived$1$InlineSuggestion$InlineContentCallbackImpl() {
            this.mCallback.accept(null);
        }

        public /* synthetic */ void lambda$handleOnFirstContentReceived$2$InlineSuggestion$InlineContentCallbackImpl() {
            this.mCallback.accept(this.mView);
        }

        private void handleOnSurfacePackage(SurfaceControlViewHost.SurfacePackage surfacePackage) {
            Consumer<SurfaceControlViewHost.SurfacePackage> consumer;
            if (surfacePackage == null) {
                return;
            }
            if (this.mSurfacePackage != null || (consumer = this.mSurfacePackageConsumer) == null) {
                surfacePackage.release();
                try {
                    this.mInlineContentProvider.onSurfacePackageReleased();
                    return;
                } catch (RemoteException e) {
                    Slog.w(InlineSuggestion.TAG, "Error calling onSurfacePackageReleased(): " + e);
                    return;
                }
            }
            this.mSurfacePackage = surfacePackage;
            if (surfacePackage == null || consumer == null) {
                return;
            }
            consumer.accept(surfacePackage);
            this.mSurfacePackageConsumer = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleOnSurfacePackageReleased() {
            if (this.mSurfacePackage != null) {
                try {
                    this.mInlineContentProvider.onSurfacePackageReleased();
                } catch (RemoteException e) {
                    Slog.w(InlineSuggestion.TAG, "Error calling onSurfacePackageReleased(): " + e);
                }
                this.mSurfacePackage = null;
            }
            this.mSurfacePackageConsumer = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleGetSurfacePackage(Consumer<SurfaceControlViewHost.SurfacePackage> consumer) {
            SurfaceControlViewHost.SurfacePackage surfacePackage = this.mSurfacePackage;
            if (surfacePackage != null) {
                consumer.accept(surfacePackage);
                return;
            }
            this.mSurfacePackageConsumer = consumer;
            try {
                this.mInlineContentProvider.requestSurfacePackage();
            } catch (RemoteException e) {
                Slog.w(InlineSuggestion.TAG, "Error calling getSurfacePackage(): " + e);
                consumer.accept(null);
                this.mSurfacePackageConsumer = null;
            }
        }

        /* JADX INFO: renamed from: android.view.inputmethod.InlineSuggestion$InlineContentCallbackImpl$1, reason: invalid class name */
        class AnonymousClass1 implements InlineContentView.SurfacePackageUpdater {
            AnonymousClass1() {
            }

            @Override // android.widget.inline.InlineContentView.SurfacePackageUpdater
            public void onSurfacePackageReleased() {
                InlineContentCallbackImpl.this.mMainHandler.post(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$InlineContentCallbackImpl$1$D6eD5QR1yay7_fJkCbPrrjOmAUs
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSurfacePackageReleased$0$InlineSuggestion$InlineContentCallbackImpl$1();
                    }
                });
            }

            public /* synthetic */ void lambda$onSurfacePackageReleased$0$InlineSuggestion$InlineContentCallbackImpl$1() {
                InlineContentCallbackImpl.this.handleOnSurfacePackageReleased();
            }

            @Override // android.widget.inline.InlineContentView.SurfacePackageUpdater
            public void getSurfacePackage(final Consumer<SurfaceControlViewHost.SurfacePackage> consumer) {
                InlineContentCallbackImpl.this.mMainHandler.post(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$InlineContentCallbackImpl$1$kqBwKkTdRUmxIUsJkLYtI_7F9u0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$getSurfacePackage$1$InlineSuggestion$InlineContentCallbackImpl$1(consumer);
                    }
                });
            }

            public /* synthetic */ void lambda$getSurfacePackage$1$InlineSuggestion$InlineContentCallbackImpl$1(Consumer consumer) {
                InlineContentCallbackImpl.this.handleGetSurfacePackage(consumer);
            }
        }

        private InlineContentView.SurfacePackageUpdater getSurfacePackageUpdater() {
            return new AnonymousClass1();
        }

        public void onClick() {
            this.mMainHandler.post(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$InlineContentCallbackImpl$eBD70lfriFltBhoclpzNcJGZRdA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onClick$3$InlineSuggestion$InlineContentCallbackImpl();
                }
            });
        }

        public /* synthetic */ void lambda$onClick$3$InlineSuggestion$InlineContentCallbackImpl() {
            InlineContentView inlineContentView = this.mView;
            if (inlineContentView == null || !inlineContentView.hasOnClickListeners()) {
                return;
            }
            this.mView.callOnClick();
        }

        public void onLongClick() {
            this.mMainHandler.post(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InlineSuggestion$InlineContentCallbackImpl$kJ55cHQ8DrRkgYoKfUMf2DIhrBo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onLongClick$4$InlineSuggestion$InlineContentCallbackImpl();
                }
            });
        }

        public /* synthetic */ void lambda$onLongClick$4$InlineSuggestion$InlineContentCallbackImpl() {
            InlineContentView inlineContentView = this.mView;
            if (inlineContentView == null || !inlineContentView.hasOnLongClickListeners()) {
                return;
            }
            this.mView.performLongClick();
        }
    }

    private static class InlineContentCallbackImplParceling implements Parcelling<InlineContentCallbackImpl> {
        @Override // com.android.internal.util.Parcelling
        public void parcel(InlineContentCallbackImpl inlineContentCallbackImpl, Parcel parcel, int i) {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.android.internal.util.Parcelling
        public InlineContentCallbackImpl unparcel(Parcel parcel) {
            return null;
        }

        private InlineContentCallbackImplParceling() {
        }
    }

    public InlineSuggestion(InlineSuggestionInfo inlineSuggestionInfo, IInlineContentProvider iInlineContentProvider, InlineContentCallbackImpl inlineContentCallbackImpl) {
        this.mInfo = inlineSuggestionInfo;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) inlineSuggestionInfo);
        this.mContentProvider = iInlineContentProvider;
        this.mInlineContentCallback = inlineContentCallbackImpl;
    }

    public InlineSuggestionInfo getInfo() {
        return this.mInfo;
    }

    public IInlineContentProvider getContentProvider() {
        return this.mContentProvider;
    }

    public InlineContentCallbackImpl getInlineContentCallback() {
        return this.mInlineContentCallback;
    }

    public String toString() {
        return "InlineSuggestion { info = " + this.mInfo + ", contentProvider = " + this.mContentProvider + ", inlineContentCallback = " + this.mInlineContentCallback + " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InlineSuggestion inlineSuggestion = (InlineSuggestion) obj;
        return Objects.equals(this.mInfo, inlineSuggestion.mInfo) && Objects.equals(this.mContentProvider, inlineSuggestion.mContentProvider) && Objects.equals(this.mInlineContentCallback, inlineSuggestion.mInlineContentCallback);
    }

    public int hashCode() {
        return ((((Objects.hashCode(this.mInfo) + 31) * 31) + Objects.hashCode(this.mContentProvider)) * 31) + Objects.hashCode(this.mInlineContentCallback);
    }

    static {
        Parcelling<InlineContentCallbackImpl> parcelling = Parcelling.Cache.get(InlineContentCallbackImplParceling.class);
        sParcellingForInlineContentCallback = parcelling;
        if (parcelling == null) {
            sParcellingForInlineContentCallback = Parcelling.Cache.put(new InlineContentCallbackImplParceling());
        }
        CREATOR = new Parcelable.Creator<InlineSuggestion>() { // from class: android.view.inputmethod.InlineSuggestion.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public InlineSuggestion[] newArray(int i) {
                return new InlineSuggestion[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public InlineSuggestion createFromParcel(Parcel parcel) {
                return new InlineSuggestion(parcel);
            }
        };
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        byte b = this.mContentProvider != null ? (byte) 2 : (byte) 0;
        if (this.mInlineContentCallback != null) {
            b = (byte) (b | 4);
        }
        parcel.writeByte(b);
        parcel.writeTypedObject(this.mInfo, i);
        IInlineContentProvider iInlineContentProvider = this.mContentProvider;
        if (iInlineContentProvider != null) {
            parcel.writeStrongInterface(iInlineContentProvider);
        }
        sParcellingForInlineContentCallback.parcel(this.mInlineContentCallback, parcel, i);
    }

    InlineSuggestion(Parcel parcel) {
        byte b = parcel.readByte();
        InlineSuggestionInfo inlineSuggestionInfo = (InlineSuggestionInfo) parcel.readTypedObject(InlineSuggestionInfo.CREATOR);
        IInlineContentProvider iInlineContentProviderAsInterface = (b & 2) == 0 ? null : IInlineContentProvider.Stub.asInterface(parcel.readStrongBinder());
        InlineContentCallbackImpl inlineContentCallbackImplUnparcel = sParcellingForInlineContentCallback.unparcel(parcel);
        this.mInfo = inlineSuggestionInfo;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) inlineSuggestionInfo);
        this.mContentProvider = iInlineContentProviderAsInterface;
        this.mInlineContentCallback = inlineContentCallbackImplUnparcel;
    }
}
