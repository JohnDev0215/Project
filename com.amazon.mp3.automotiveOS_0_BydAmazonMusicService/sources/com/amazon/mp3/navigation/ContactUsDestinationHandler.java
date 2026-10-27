package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.activity.CantileverActivity;
import com.amazon.music.destination.ContactUsDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class ContactUsDestinationHandler implements DestinationHandler<ContactUsDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final ContactUsDestination contactsUsDestination) {
        ContactUsDestination.ContactUsTarget target = contactsUsDestination.getTarget();
        Intent intent = CantileverActivity.getIntent(context);
        if (target != null) {
            intent.putExtra("target", target.getValue());
        }
        intent.setFlags(335544320);
        context.startActivity(intent);
    }
}
