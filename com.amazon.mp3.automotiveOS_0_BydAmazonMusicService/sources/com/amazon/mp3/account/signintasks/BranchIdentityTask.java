package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.account.signintasks.BranchIdentityTask;
import com.amazon.mp3.service.CCMPAdserviceDirectedIDService;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.signin.SignInTask;
import com.android.volley.Response;
import io.branch.referral.Branch;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: BranchIdentityTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/account/signintasks/BranchIdentityTask;", "Lcom/amazon/music/signin/SignInTask;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "execute", "", "getName", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BranchIdentityTask implements SignInTask {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = BranchIdentityTask.class.getSimpleName();
    private final Context mContext;

    @JvmStatic
    public static final void clearBranchIdentity() {
        INSTANCE.clearBranchIdentity();
    }

    @JvmStatic
    public static final boolean isBranchUserIdentified() {
        return INSTANCE.isBranchUserIdentified();
    }

    @JvmStatic
    public static final void setBranchIdentity(Context context) {
        INSTANCE.setBranchIdentity(context);
    }

    public BranchIdentityTask(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("Executing sign in task ", str));
        if (Branch.getInstance().isUserIdentified()) {
            return;
        }
        INSTANCE.setBranchIdentity(this.mContext);
    }

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    /* JADX INFO: compiled from: BranchIdentityTask.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/account/signintasks/BranchIdentityTask$Companion;", "", "()V", "BRANCH_IDENTIFIER", "", "TAG", "kotlin.jvm.PlatformType", "clearBranchIdentity", "", "isBranchUserIdentified", "", "setBranchIdentity", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void setBranchIdentity(final Context context) {
            if (context != null) {
                final String customerId = AccountDetailUtil.get(context).getCustomerId();
                if (StringUtils.isEmpty(customerId)) {
                    Log.error(BranchIdentityTask.TAG, "CustomerId is null, unable to set Branch identity");
                } else {
                    final Response.Listener listener = new Response.Listener() { // from class: com.amazon.mp3.account.signintasks.BranchIdentityTask$Companion$$ExternalSyntheticLambda0
                        @Override // com.android.volley.Response.Listener
                        public final void onResponse(Object obj) {
                            BranchIdentityTask.Companion.m109setBranchIdentity$lambda0((JSONObject) obj);
                        }
                    };
                    ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.account.signintasks.BranchIdentityTask$Companion$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            BranchIdentityTask.Companion.m110setBranchIdentity$lambda1(context, customerId, listener);
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: setBranchIdentity$lambda-0, reason: not valid java name */
        public static final void m109setBranchIdentity$lambda0(JSONObject response) {
            String string;
            Intrinsics.checkNotNullParameter(response, "response");
            Log.debug(BranchIdentityTask.TAG, "DirectedID API returned response %s", response);
            try {
                string = response.getString("directedId");
            } catch (JSONException e) {
                Log.error(BranchIdentityTask.TAG, "JSONException received when retrieving directedId", e.getMessage());
                string = null;
            }
            if (StringUtils.isNotEmpty(string)) {
                Branch branch = Branch.getInstance();
                Intrinsics.checkNotNull(string);
                branch.setIdentity(string);
                Log.info(BranchIdentityTask.TAG, "Setting DID: %s as Branch identity", string);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: setBranchIdentity$lambda-1, reason: not valid java name */
        public static final void m110setBranchIdentity$lambda1(Context context, String str, Response.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Log.debug(BranchIdentityTask.TAG, "Calling DID API to retrieve DirectedId");
            CCMPAdserviceDirectedIDService.INSTANCE.getDirectedId(context, str, "1e549fe4-1c1b-11ee-be56-0242ac120002", listener);
        }

        @JvmStatic
        public final boolean isBranchUserIdentified() {
            return Branch.getInstance().isUserIdentified();
        }

        @JvmStatic
        public final void clearBranchIdentity() {
            Log.info(BranchIdentityTask.TAG, "Removing previous Branch identity");
            Branch.getInstance().logout();
        }
    }
}
