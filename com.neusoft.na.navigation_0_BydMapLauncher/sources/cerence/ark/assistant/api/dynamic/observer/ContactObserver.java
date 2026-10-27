package cerence.ark.assistant.api.dynamic.observer;

import android.net.Uri;
import android.provider.ContactsContract;
import cerence.ark.assistant.api.dynamic.ContentType;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class ContactObserver extends BaseContentObserver {
    private static final String TAG = "ContactObserver";

    public ContactObserver() {
        super(ContentType.CONTACT);
    }

    @Override // cerence.ark.assistant.api.dynamic.observer.BaseContentObserver
    protected Uri getUri() {
        if (!Utils.checkSelfPermission("android.permission.READ_CONTACTS")) {
            Logger.error(TAG, "registerObserver: READ_CONTACTS permission is not granted");
            return null;
        }
        return ContactsContract.Contacts.CONTENT_URI;
    }
}
