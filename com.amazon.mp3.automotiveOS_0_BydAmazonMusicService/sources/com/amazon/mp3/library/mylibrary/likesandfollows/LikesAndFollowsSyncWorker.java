package com.amazon.mp3.library.mylibrary.likesandfollows;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LikesAndFollowsSyncWorker.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/likesandfollows/LikesAndFollowsSyncWorker;", "Landroidx/work/Worker;", "ctx", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "TAG", "", "kotlin.jvm.PlatformType", "doWork", "Landroidx/work/ListenableWorker$Result;", "CallType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LikesAndFollowsSyncWorker extends Worker {
    private final String TAG;
    private final Context ctx;

    /* JADX INFO: compiled from: LikesAndFollowsSyncWorker.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \u00052\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/likesandfollows/LikesAndFollowsSyncWorker$CallType;", "", "(Ljava/lang/String;I)V", "SYNC_LIKED_ALBUMS", "SYNC_FOLLOWED_ARTISTS", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum CallType {
        SYNC_LIKED_ALBUMS,
        SYNC_FOLLOWED_ARTISTS;

        public static final String CALL_TYPE_DATA = "CALL_TYPE_DATA";
        public static final String IS_DELTA_SYNC_DATA = "IS_DELTA_SYNC_DATA";
    }

    /* JADX INFO: compiled from: LikesAndFollowsSyncWorker.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CallType.values().length];
            iArr[CallType.SYNC_FOLLOWED_ARTISTS.ordinal()] = 1;
            iArr[CallType.SYNC_LIKED_ALBUMS.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LikesAndFollowsSyncWorker(Context ctx, WorkerParameters params) {
        super(ctx, params);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(params, "params");
        this.ctx = ctx;
        this.TAG = LikesAndFollowsSyncWorker.class.getSimpleName();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        try {
            String string = getInputData().getString(CallType.CALL_TYPE_DATA);
            CallType callTypeValueOf = string == null ? null : CallType.valueOf(string);
            String str = getInputData().getBoolean(CallType.IS_DELTA_SYNC_DATA, false) ? "delta sync" : "full sync";
            int i = callTypeValueOf == null ? -1 : WhenMappings.$EnumSwitchMapping$0[callTypeValueOf.ordinal()];
            if (i == -1) {
                throw new IllegalArgumentException("Sync call type cannot be null!");
            }
            if (i == 1) {
                Log.debug(this.TAG, "Followed artists " + str + " called");
                LikesAndFollowsFireflyCallsKt.fetchAllFollowedArtists(this.ctx);
            } else if (i == 2) {
                Log.debug(this.TAG, "Liked albums " + str + " called");
            }
            ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
            Intrinsics.checkNotNullExpressionValue(resultSuccess, "{\n            val callTy…esult.success()\n        }");
            return resultSuccess;
        } catch (Throwable th) {
            Log.debug(this.TAG, Intrinsics.stringPlus("There was an error performing the following task ", getInputData().getString(CallType.CALL_TYPE_DATA)), th);
            ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
            Intrinsics.checkNotNullExpressionValue(resultFailure, "{\n            Log.debug(…esult.failure()\n        }");
            return resultFailure;
        }
    }
}
