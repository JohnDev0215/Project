package com.android.inputmethod.latin;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import com.android.inputmethod.latin.ContactsManager;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.permissions.PermissionsUtil;
import com.android.inputmethod.latin.personalization.AccountUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsBinaryDictionary extends ExpandableBinaryDictionary implements ContactsManager.ContactsChangedListener {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DUMP = false;
    private static final String NAME = "contacts";
    private static final String TAG = "ContactsBinaryDictionary";
    private final ContactsManager mContactsManager;
    private final boolean mUseFirstLastBigrams;

    protected ContactsBinaryDictionary(final Context context, final Locale locale, final File dictFile, final String name) {
        super(context, getDictName(name, locale, dictFile), locale, "contacts", dictFile);
        this.mUseFirstLastBigrams = ContactsDictionaryUtils.useFirstLastBigramsForLocale(locale);
        ContactsManager contactsManager = new ContactsManager(context);
        this.mContactsManager = contactsManager;
        contactsManager.registerForUpdates(this);
        reloadDictionaryIfRequired();
    }

    public static ContactsBinaryDictionary getDictionary(final Context context, final Locale locale, final File dictFile, final String dictNamePrefix, @Nullable final String account) {
        return new ContactsBinaryDictionary(context, locale, dictFile, dictNamePrefix + "contacts");
    }

    @Override // com.android.inputmethod.latin.ExpandableBinaryDictionary, com.android.inputmethod.latin.Dictionary
    public synchronized void close() {
        this.mContactsManager.close();
        super.close();
    }

    @Override // com.android.inputmethod.latin.ExpandableBinaryDictionary
    public void loadInitialContentsLocked() {
        loadDeviceAccountsEmailAddressesLocked();
        loadDictionaryForUriLocked(ContactsContract.Profile.CONTENT_URI);
        loadDictionaryForUriLocked(ContactsContract.Contacts.CONTENT_URI);
    }

    private void loadDeviceAccountsEmailAddressesLocked() {
        List<String> deviceAccountsEmailAddresses = AccountUtils.getDeviceAccountsEmailAddresses(this.mContext);
        if (deviceAccountsEmailAddresses == null || deviceAccountsEmailAddresses.isEmpty()) {
            return;
        }
        for (String str : deviceAccountsEmailAddresses) {
            runGCIfRequiredLocked(true);
            addUnigramLocked(str, 40, false, false, -1);
        }
    }

    private void loadDictionaryForUriLocked(final Uri uri) {
        if (!PermissionsUtil.checkAllPermissionsGranted(this.mContext, "android.permission.READ_CONTACTS")) {
            Log.i(TAG, "No permission to read contacts. Not loading the Dictionary.");
        }
        ArrayList<String> validNames = this.mContactsManager.getValidNames(uri);
        Iterator<String> it = validNames.iterator();
        while (it.hasNext()) {
            addNameLocked(it.next());
        }
        if (uri.equals(ContactsContract.Contacts.CONTENT_URI)) {
            this.mContactsManager.updateLocalState(validNames);
        }
    }

    private void addNameLocked(final String name) {
        int iCodePointCount = StringUtils.codePointCount(name);
        NgramContext emptyPrevWordsContext = NgramContext.getEmptyPrevWordsContext(3);
        int i = 0;
        while (i < iCodePointCount) {
            if (Character.isLetter(name.codePointAt(i))) {
                int wordEndPosition = ContactsDictionaryUtils.getWordEndPosition(name, iCodePointCount, i);
                String strSubstring = name.substring(i, wordEndPosition);
                int i2 = wordEndPosition - 1;
                int iCodePointCount2 = StringUtils.codePointCount(strSubstring);
                if (iCodePointCount2 <= 48 && iCodePointCount2 > 1) {
                    runGCIfRequiredLocked(true);
                    addUnigramLocked(strSubstring, 40, false, false, -1);
                    if (emptyPrevWordsContext.isValid() && this.mUseFirstLastBigrams) {
                        runGCIfRequiredLocked(true);
                        addNgramEntryLocked(emptyPrevWordsContext, strSubstring, 90, -1);
                    }
                    emptyPrevWordsContext = emptyPrevWordsContext.getNextNgramContext(new NgramContext.WordInfo(strSubstring));
                }
                i = i2;
            }
            i++;
        }
    }

    @Override // com.android.inputmethod.latin.ContactsManager.ContactsChangedListener
    public void onContactsChange() {
        setNeedsToRecreate();
    }
}
