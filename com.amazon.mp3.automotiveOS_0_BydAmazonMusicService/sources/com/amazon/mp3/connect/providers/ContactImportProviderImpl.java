package com.amazon.mp3.connect.providers;

import android.content.Context;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.ActorOAuthProviderMAPR5;
import com.amazon.music.connect.contacts.ContactImportProvider;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ContactImportProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/connect/providers/ContactImportProviderImpl;", "Lcom/amazon/music/connect/contacts/ContactImportProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "getOAuthInfoForActor", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/data/OAuthInfo;", "actorId", "", "requestContactPermissionsIfNotGranted", "", "callback", "Lcom/amazon/music/connect/contacts/ContactImportProvider$ContactsPermissionsCallback;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContactImportProviderImpl implements ContactImportProvider {
    private final Context context;
    private final Logger logger;

    public ContactImportProviderImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.logger = LoggerFactory.getLogger(ContactImportProviderImpl.class.getSimpleName());
    }

    @Override // com.amazon.music.connect.contacts.ContactImportProvider
    public void requestContactPermissionsIfNotGranted(final ContactImportProvider.ContactsPermissionsCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        GrantPermissionActivity.requestIfNotGranted(this.context, "android.permission.READ_CONTACTS", new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.connect.providers.ContactImportProviderImpl$requestContactPermissionsIfNotGranted$resultReceiver$1
            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onGranted() {
                callback.onGranted();
            }

            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onDenied(String[] permissions) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                callback.onDenied(permissions);
            }
        });
    }

    @Override // com.amazon.music.connect.contacts.ContactImportProvider
    public Outcome<OAuthInfo> getOAuthInfoForActor(String actorId) throws Exception {
        Intrinsics.checkNotNullParameter(actorId, "actorId");
        try {
            String accessToken = new ActorOAuthProviderMAPR5(this.context, actorId).getOAuthInfo().accessToken;
            Intrinsics.checkNotNullExpressionValue(accessToken, "accessToken");
            return new Outcome.Success(new OAuthInfo(accessToken));
        } catch (Exception e) {
            if (e instanceof AuthenticationException ? true : e instanceof NullPointerException) {
                this.logger.error("Failed to retrieve OAuthInfo", (Throwable) e);
                return new Outcome.Failure(e);
            }
            throw e;
        }
    }
}
