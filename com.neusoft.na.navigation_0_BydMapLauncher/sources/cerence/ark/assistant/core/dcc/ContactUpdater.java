package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ContactUpdater extends AsyncDCUpdater {
    private static final String TAG = "ContactUpdater";

    public ContactUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<Contact> contacts = iDynamicContentProvider.getContacts(cancellationSignal);
        if (contacts != null && !contacts.isEmpty()) {
            Logger.info(TAG, "Contacts count: " + contacts.size());
            for (int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);
                if (contact == null || TextUtils.isEmpty(contact.getName())) {
                    Logger.warn(TAG, "contact is null or contact name is empty, index = " + i);
                } else {
                    arrayList.add(contact.getId() + DccConstant.DCC_SEPARATOR + contact.getName());
                }
            }
        }
        return arrayList;
    }
}
