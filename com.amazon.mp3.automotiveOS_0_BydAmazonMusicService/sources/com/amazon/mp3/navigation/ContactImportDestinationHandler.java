package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.connect.contacts.ContactImportWebViewActivity;
import com.amazon.music.destination.ContactImportDestination;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactImportDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/navigation/ContactImportDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/ContactImportDestination;", "()V", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "contactImportDestination", "navigateTo", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContactImportDestinationHandler implements DestinationHandler<ContactImportDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, ContactImportDestination contactImportDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contactImportDestination, "contactImportDestination");
        context.startActivity(getIntent(context, contactImportDestination));
    }

    public final Intent getIntent(Context context, ContactImportDestination contactImportDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contactImportDestination, "contactImportDestination");
        ContactImportWebViewActivity.Companion companion = ContactImportWebViewActivity.INSTANCE;
        String customerId = AccountDetailUtil.get(context).getCustomerId();
        Intrinsics.checkNotNullExpressionValue(customerId, "get(context).customerId");
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        Intrinsics.checkNotNullExpressionValue(musicDomain, "get().musicDomain");
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "getMusicTerritoryOfResidence()");
        Intent intentStart = companion.start(context, customerId, musicDomain, musicTerritoryOfResidence, contactImportDestination.getPath(), contactImportDestination.getRef());
        intentStart.setFlags(268435456);
        return intentStart;
    }
}
