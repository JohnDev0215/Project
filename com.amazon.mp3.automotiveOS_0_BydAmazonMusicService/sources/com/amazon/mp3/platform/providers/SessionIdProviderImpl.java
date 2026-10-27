package com.amazon.mp3.platform.providers;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.authentication.AuthInfo;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.featuregating.weblabs.WeblabSessionIdGenerator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionIdProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/platform/providers/SessionIdProviderImpl;", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SessionIdProviderImpl {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = SessionIdProviderImpl.class.getSimpleName();

    /* JADX INFO: compiled from: SessionIdProviderImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\b\u0010\t\u001a\u0004\u0018\u00010\u0006R\u001c\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\u00060\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/platform/providers/SessionIdProviderImpl$Companion;", "", "", "isAuthenticated", "Landroid/content/Context;", "context", "", "getSessionId", "getGeneratedSessionId", "getLoginSessionId", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/platform/providers/SessionIdProviderImpl;", "instance", "Lcom/amazon/mp3/platform/providers/SessionIdProviderImpl;", "unauthenticatedSessionIdKey", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getSessionId(boolean isAuthenticated, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (isAuthenticated) {
                return getLoginSessionId();
            }
            return getGeneratedSessionId(context);
        }

        @VisibleForTesting
        public final String getGeneratedSessionId(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(SessionIdProviderImpl.TAG, 0);
            String string = sharedPreferences.getString("unauthenticatedSessionIdKey", null);
            if (string == null) {
                string = WeblabSessionIdGenerator.INSTANCE.generateSessionId();
                sharedPreferences.edit().putString("unauthenticatedSessionIdKey", string).apply();
            }
            Log.error(SessionIdProviderImpl.TAG, Intrinsics.stringPlus("Generated Un-auth SessionId for Weblab usage: ", string));
            return string;
        }

        public final String getLoginSessionId() {
            AuthInfo authInfoMAPR5 = AmazonApplication.getAuthInfoMAPR5();
            if (authInfoMAPR5 == null) {
                Log.error(SessionIdProviderImpl.TAG, "Unable to fetch session ID due to missing AuthInfo");
                return null;
            }
            try {
                return authInfoMAPR5.find(AuthDataType.SESSION_ID);
            } catch (AuthenticationException e) {
                Log.error(SessionIdProviderImpl.TAG, Intrinsics.stringPlus("Unable to fetch session ID from AuthInfo ", e.getMessage()));
                return null;
            } catch (Exception e2) {
                String str = SessionIdProviderImpl.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("MAP token fetch failed with error ");
                sb.append((Object) e2.getMessage());
                sb.append(", cause: ");
                Throwable cause = e2.getCause();
                sb.append((Object) (cause == null ? null : cause.getMessage()));
                Log.error(str, sb.toString());
                return null;
            }
        }
    }
}
