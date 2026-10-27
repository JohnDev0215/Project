package com.dee.app.contacts.common.utils;

import com.dee.app.contacts.interfaces.models.data.Contact;
import com.dee.app.contacts.interfaces.models.data.ContactEmailAddress;
import com.dee.app.contacts.interfaces.models.data.ContactForSync;
import com.dee.app.contacts.interfaces.models.data.ContactPhoneNumber;
import com.dee.app.contacts.interfaces.models.data.ContactUploadInfo;
import com.dee.app.contacts.interfaces.models.data.PreferenceInfo;
import com.dee.app.contacts.interfaces.models.data.enums.SyncOperationType;
import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreferenceKey;
import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreferences;
import com.dee.app.contacts.interfaces.models.data.server.ServerContact;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class ContactsConverterUtil {
    private ContactsConverterUtil() {
    }

    public static ContactForSync convertContactToContactForSync(ContactUploadInfo contactUploadInfo, SyncOperationType syncOperationType) {
        return new ContactForSync(contactUploadInfo, syncOperationType);
    }

    public static ContactUploadInfo convertContactToContactUploadInfo(Contact contact) {
        ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
        if (contact.getContactName() != null) {
            contactUploadInfo.setContactName(contact.getContactName().getFirstName(), contact.getContactName().getLastName(), contact.getContactName().getPhoneticFirstName(), contact.getContactName().getPhoneticLastName(), contact.getContactName().getNickName());
        }
        contactUploadInfo.setCompany(contact.getCompany());
        contactUploadInfo.setRelationship(contact.getRelationship());
        contactUploadInfo.setServerContactId(contact.getId());
        contactUploadInfo.setDeviceContactId(contact.getDeviceContactId());
        contactUploadInfo.setContactSourceType(contact.getContactSourceType());
        contactUploadInfo.setSourceDeviceId(contact.getSourceDeviceId());
        if (contact.getPhoneNumbers() != null) {
            for (ContactPhoneNumber contactPhoneNumber : contact.getPhoneNumbers()) {
                contactUploadInfo.addPhoneNumber(contactPhoneNumber.getPhoneNumber(), contactPhoneNumber.getRawType());
            }
        }
        if (contact.getEmails() != null) {
            for (ContactEmailAddress contactEmailAddress : contact.getEmails()) {
                contactUploadInfo.addEmailAddress(contactEmailAddress.getEmailAddress(), contactEmailAddress.getRawType());
            }
        }
        return contactUploadInfo;
    }

    public static List<Contact> convertServerContactsToLocalContacts(List<ServerContact> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ServerContact> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertServerContactToLocalContact(it.next()));
        }
        return arrayList;
    }

    public static Contact convertServerContactToLocalContact(ServerContact serverContact) {
        Contact contact = new Contact();
        contact.setId(serverContact.getId());
        contact.setCommsIds(serverContact.getCommsIds());
        contact.setOwnerCommsId(serverContact.getOwnerCommsId());
        contact.setDeviceContactId(serverContact.getDeviceContactId());
        contact.setSourceDeviceId(serverContact.getSourceDeviceId());
        contact.setReferenceCommsId(serverContact.getReferenceCommsId());
        contact.setReferenceContactId(serverContact.getReferenceContactId());
        contact.setHomeGroup(serverContact.isHomeGroup());
        contact.setAlexaEnabled(serverContact.isAlexaEnabled());
        contact.setContactSourceType(serverContact.getContactSourceType());
        contact.setPhoneNumbers(serverContact.getPhoneNumbers());
        contact.setEmails(serverContact.getEmails());
        contact.setAddresses(serverContact.getAddresses());
        contact.setBlocked(serverContact.isBlocked());
        contact.setIsChild(serverContact.isChild());
        contact.setBulkImport(serverContact.isBulkImport());
        contact.setIsMerged(serverContact.isMerged());
        contact.setShouldMerge(serverContact.isShouldMerge());
        contact.setMergedContactIdentifiers(serverContact.getMergedContactIdentifiers());
        contact.setShouldDisplay(serverContact.isShouldDisplay());
        contact.setIsLinked(serverContact.isLinked());
        contact.setContactName(serverContact.getName());
        contact.setCompany(serverContact.getCompany());
        contact.setRelationship(serverContact.getRelationship());
        contact.setSourceDeviceId(serverContact.getSourceDeviceId());
        contact.setContactPreferences(buildContactPreferences(serverContact.getContactPreferenceInfoList()));
        return contact;
    }

    public static ContactUploadInfo convertServerContactToContactUploadInfo(ServerContact serverContact) {
        return convertContactToContactUploadInfo(convertServerContactToLocalContact(serverContact));
    }

    private static ContactPreferences buildContactPreferences(List<PreferenceInfo> list) {
        ContactPreferences contactPreferences = new ContactPreferences();
        if (list != null && !list.isEmpty()) {
            HashMap map = new HashMap();
            for (PreferenceInfo preferenceInfo : list) {
                map.put(preferenceInfo.getKey(), preferenceInfo);
            }
            for (Map.Entry entry : map.entrySet()) {
                setContactPreferences(contactPreferences, (String) entry.getKey(), ((PreferenceInfo) entry.getValue()).getValue());
            }
        }
        return contactPreferences;
    }

    /* JADX INFO: renamed from: com.dee.app.contacts.common.utils.ContactsConverterUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactpreference$ContactPreferenceKey;

        static {
            int[] iArr = new int[ContactPreferenceKey.values().length];
            $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactpreference$ContactPreferenceKey = iArr;
            try {
                iArr[ContactPreferenceKey.FAVORITE_CONTACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactpreference$ContactPreferenceKey[ContactPreferenceKey.EMERGENCY_CONTACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static void setContactPreferences(ContactPreferences contactPreferences, String str, String str2) {
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactpreference$ContactPreferenceKey[ContactPreferenceKey.getPreferenceKey(str).ordinal()];
        if (i == 1) {
            contactPreferences.setFavoritePreference(Boolean.valueOf(str2).booleanValue());
        } else {
            if (i != 2) {
                return;
            }
            contactPreferences.setEmergencyPreference(Boolean.valueOf(str2).booleanValue());
        }
    }
}
