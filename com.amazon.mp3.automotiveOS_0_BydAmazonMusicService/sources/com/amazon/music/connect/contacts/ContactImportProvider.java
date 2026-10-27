package com.amazon.music.connect.contacts;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContactImportProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u000bJ\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/connect/contacts/ContactImportProvider;", "", "getOAuthInfoForActor", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/data/OAuthInfo;", "actorId", "", "requestContactPermissionsIfNotGranted", "", "callback", "Lcom/amazon/music/connect/contacts/ContactImportProvider$ContactsPermissionsCallback;", "ContactsPermissionsCallback", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContactImportProvider {

    /* JADX INFO: compiled from: ContactImportProvider.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/connect/contacts/ContactImportProvider$ContactsPermissionsCallback;", "", "onDenied", "", "permissions", "", "", "([Ljava/lang/String;)V", "onGranted", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ContactsPermissionsCallback {
        void onDenied(String[] permissions);

        void onGranted();
    }

    Outcome<OAuthInfo> getOAuthInfoForActor(String actorId);

    void requestContactPermissionsIfNotGranted(ContactsPermissionsCallback callback);
}
