package com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'AMAZON_CONTACTS_ENABLED' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes5.dex */
public final class ContactOwnerPreference {
    private static final /* synthetic */ ContactOwnerPreference[] $VALUES;
    public static final ContactOwnerPreference AMAZON_CONTACTS_DISABLED;
    public static final ContactOwnerPreference AMAZON_CONTACTS_ENABLED;
    public static final ContactOwnerPreference AUTO_DISCOVERY_DISABLED;
    public static final ContactOwnerPreference AUTO_DISCOVERY_ENABLED;
    public static final ContactOwnerPreference CONTACT_IMPORT_DISABLED;
    public static final ContactOwnerPreference CONTACT_IMPORT_ENABLED;
    public static final ContactOwnerPreference CONTACT_SELECTIVE_IMPORT;
    public static final ContactOwnerPreference PHONE_NUMBER_VERIFIED_DISABLED;
    public static final ContactOwnerPreference PHONE_NUMBER_VERIFIED_ENABLED;
    private final ContactOwnerPreferenceKey key;
    private final ContactOwnerPreferenceValue value;

    public static ContactOwnerPreference valueOf(String str) {
        return (ContactOwnerPreference) Enum.valueOf(ContactOwnerPreference.class, str);
    }

    public static ContactOwnerPreference[] values() {
        return (ContactOwnerPreference[]) $VALUES.clone();
    }

    static {
        ContactOwnerPreferenceKey contactOwnerPreferenceKey = ContactOwnerPreferenceKey.AMAZON_CONTACTS;
        ContactOwnerPreferenceValue contactOwnerPreferenceValue = ContactOwnerPreferenceValue.ENABLED;
        ContactOwnerPreference contactOwnerPreference = new ContactOwnerPreference("AMAZON_CONTACTS_ENABLED", 0, contactOwnerPreferenceKey, contactOwnerPreferenceValue);
        AMAZON_CONTACTS_ENABLED = contactOwnerPreference;
        ContactOwnerPreferenceValue contactOwnerPreferenceValue2 = ContactOwnerPreferenceValue.DISABLED;
        ContactOwnerPreference contactOwnerPreference2 = new ContactOwnerPreference("AMAZON_CONTACTS_DISABLED", 1, contactOwnerPreferenceKey, contactOwnerPreferenceValue2);
        AMAZON_CONTACTS_DISABLED = contactOwnerPreference2;
        ContactOwnerPreferenceKey contactOwnerPreferenceKey2 = ContactOwnerPreferenceKey.CONTACT_IMPORT;
        ContactOwnerPreference contactOwnerPreference3 = new ContactOwnerPreference("CONTACT_IMPORT_ENABLED", 2, contactOwnerPreferenceKey2, contactOwnerPreferenceValue);
        CONTACT_IMPORT_ENABLED = contactOwnerPreference3;
        ContactOwnerPreference contactOwnerPreference4 = new ContactOwnerPreference("CONTACT_IMPORT_DISABLED", 3, contactOwnerPreferenceKey2, contactOwnerPreferenceValue2);
        CONTACT_IMPORT_DISABLED = contactOwnerPreference4;
        ContactOwnerPreference contactOwnerPreference5 = new ContactOwnerPreference("CONTACT_SELECTIVE_IMPORT", 4, contactOwnerPreferenceKey2, ContactOwnerPreferenceValue.SELECTIVE_IMPORT);
        CONTACT_SELECTIVE_IMPORT = contactOwnerPreference5;
        ContactOwnerPreferenceKey contactOwnerPreferenceKey3 = ContactOwnerPreferenceKey.AUTO_DISCOVERY;
        ContactOwnerPreference contactOwnerPreference6 = new ContactOwnerPreference("AUTO_DISCOVERY_ENABLED", 5, contactOwnerPreferenceKey3, contactOwnerPreferenceValue);
        AUTO_DISCOVERY_ENABLED = contactOwnerPreference6;
        ContactOwnerPreference contactOwnerPreference7 = new ContactOwnerPreference("AUTO_DISCOVERY_DISABLED", 6, contactOwnerPreferenceKey3, contactOwnerPreferenceValue2);
        AUTO_DISCOVERY_DISABLED = contactOwnerPreference7;
        ContactOwnerPreferenceKey contactOwnerPreferenceKey4 = ContactOwnerPreferenceKey.PHONE_NUMBER_VERIFIED;
        ContactOwnerPreference contactOwnerPreference8 = new ContactOwnerPreference("PHONE_NUMBER_VERIFIED_ENABLED", 7, contactOwnerPreferenceKey4, contactOwnerPreferenceValue);
        PHONE_NUMBER_VERIFIED_ENABLED = contactOwnerPreference8;
        ContactOwnerPreference contactOwnerPreference9 = new ContactOwnerPreference("PHONE_NUMBER_VERIFIED_DISABLED", 8, contactOwnerPreferenceKey4, contactOwnerPreferenceValue2);
        PHONE_NUMBER_VERIFIED_DISABLED = contactOwnerPreference9;
        $VALUES = new ContactOwnerPreference[]{contactOwnerPreference, contactOwnerPreference2, contactOwnerPreference3, contactOwnerPreference4, contactOwnerPreference5, contactOwnerPreference6, contactOwnerPreference7, contactOwnerPreference8, contactOwnerPreference9};
    }

    private ContactOwnerPreference(String str, int i, ContactOwnerPreferenceKey contactOwnerPreferenceKey, ContactOwnerPreferenceValue contactOwnerPreferenceValue) {
        this.key = contactOwnerPreferenceKey;
        this.value = contactOwnerPreferenceValue;
    }

    public ContactOwnerPreferenceKey getPreferenceKey() {
        return this.key;
    }

    public ContactOwnerPreferenceValue getPreferenceValue() {
        return this.value;
    }

    public static ContactOwnerPreference getContactPreferenceForKeyAndValue(String str, String str2) {
        for (ContactOwnerPreference contactOwnerPreference : values()) {
            if (contactOwnerPreference.getPreferenceKey().isPreferenceKeyForString(str) && contactOwnerPreference.getPreferenceValue().isPreferenceValueForString(str2)) {
                return contactOwnerPreference;
            }
        }
        return null;
    }
}
