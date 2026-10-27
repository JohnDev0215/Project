package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ContactUpdater extends AsyncDCUpdater {
    public ContactUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<Contact> contacts = iDynamicContentProvider.getContacts(cancellationSignal);
        if (contacts != null && !contacts.isEmpty()) {
            for (Contact contact : contacts) {
                if (contact != null && !TextUtils.isEmpty(contact.getName())) {
                    arrayList.add(contact.getId() + DccConstant.SEPARATOR + contact.getName());
                }
            }
        }
        return arrayList;
    }
}
