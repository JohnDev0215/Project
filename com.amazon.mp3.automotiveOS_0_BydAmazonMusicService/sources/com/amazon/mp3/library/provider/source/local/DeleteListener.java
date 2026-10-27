package com.amazon.mp3.library.provider.source.local;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeleteListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/provider/source/local/DeleteListener;", "", "onComplete", "", "result", "Lcom/amazon/mp3/library/provider/source/local/DeleteListener$DeletionResult;", "DeletionResult", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DeleteListener {
    void onComplete(DeletionResult result);

    /* JADX INFO: compiled from: DeleteListener.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001cB)\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u001b\u0010\u001dB!\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u001b\u0010\u001eR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\"\u0010\u0012\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007R$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006 "}, d2 = {"Lcom/amazon/mp3/library/provider/source/local/DeleteListener$DeletionResult;", "", "", "isSuccess", "Z", "()Z", "setSuccess", "(Z)V", "", "totalDeleted", "I", "getTotalDeleted", "()I", "setTotalDeleted", "(I)V", "total", "getTotal", "setTotal", "isSDCardPermissionError", "setSDCardPermissionError", "Landroid/net/Uri;", "tracksUri", "Landroid/net/Uri;", "getTracksUri", "()Landroid/net/Uri;", "setTracksUri", "(Landroid/net/Uri;)V", "<init>", "(ZZ)V", "(ZZII)V", "(ZZLandroid/net/Uri;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class DeletionResult {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private boolean isSDCardPermissionError;
        private boolean isSuccess;
        private int total;
        private int totalDeleted;
        private Uri tracksUri;

        /* JADX INFO: renamed from: isSuccess, reason: from getter */
        public final boolean getIsSuccess() {
            return this.isSuccess;
        }

        public final int getTotalDeleted() {
            return this.totalDeleted;
        }

        public final void setTotalDeleted(int i) {
            this.totalDeleted = i;
        }

        public final int getTotal() {
            return this.total;
        }

        public final void setTotal(int i) {
            this.total = i;
        }

        /* JADX INFO: renamed from: isSDCardPermissionError, reason: from getter */
        public final boolean getIsSDCardPermissionError() {
            return this.isSDCardPermissionError;
        }

        public final Uri getTracksUri() {
            return this.tracksUri;
        }

        public DeletionResult(boolean z, boolean z2) {
            this.isSuccess = z;
            this.isSDCardPermissionError = z2;
        }

        public DeletionResult(boolean z, boolean z2, int i, int i2) {
            this.isSuccess = z;
            this.isSDCardPermissionError = z2;
            this.totalDeleted = i;
            this.total = i2;
        }

        public DeletionResult(boolean z, boolean z2, Uri tracksUri) {
            Intrinsics.checkNotNullParameter(tracksUri, "tracksUri");
            this.isSuccess = z;
            this.isSDCardPermissionError = z2;
            this.tracksUri = tracksUri;
        }

        /* JADX INFO: compiled from: DeleteListener.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/library/provider/source/local/DeleteListener$DeletionResult$Companion;", "", "()V", "buildDeletionError", "Lcom/amazon/mp3/library/provider/source/local/DeleteListener$DeletionResult;", "buildDeletionSDCardPermissionError", "tracksUri", "Landroid/net/Uri;", "buildDeletionSuccess", "isSuccess", "", "totalDeleted", "", "total", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final DeletionResult buildDeletionError() {
                return new DeletionResult(false, false);
            }

            public final DeletionResult buildDeletionSuccess(boolean isSuccess, int totalDeleted, int total) {
                return new DeletionResult(isSuccess, false, totalDeleted, total);
            }

            public final DeletionResult buildDeletionSDCardPermissionError(Uri tracksUri) {
                Intrinsics.checkNotNullParameter(tracksUri, "tracksUri");
                return new DeletionResult(false, true, tracksUri);
            }
        }
    }
}
