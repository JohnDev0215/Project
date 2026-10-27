package com.dee.app.contacts.interfaces.models.data.preference.identitypreference;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'CONTACT_SYNC_PREFERENCE_ENABLED' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes5.dex */
public final class ContactIdentityPreference {
    private static final /* synthetic */ ContactIdentityPreference[] $VALUES;
    public static final ContactIdentityPreference BLOCK_UNKNOWN_NUMBER_PREFERENCE_DISABLED;
    public static final ContactIdentityPreference BLOCK_UNKNOWN_NUMBER_PREFERENCE_ENABLED;
    public static final ContactIdentityPreference CONTACT_ACCESS_ALL_CONTACTS;
    public static final ContactIdentityPreference CONTACT_ACCESS_FAVORITES_ONLY;
    public static final ContactIdentityPreference CONTACT_SYNC_PREFERENCE_DISABLED;
    public static final ContactIdentityPreference CONTACT_SYNC_PREFERENCE_ENABLED;
    public static final ContactIdentityPreference GROUP_AMC_PREFERENCE_ENABLED;
    public static final ContactIdentityPreference GROUP_AMC_PREFERENCE_TYPE_DISABLED;
    private final IdentityPreferenceKey key;
    private final IdentityPreferenceValue value;

    public static ContactIdentityPreference valueOf(String str) {
        return (ContactIdentityPreference) Enum.valueOf(ContactIdentityPreference.class, str);
    }

    public static ContactIdentityPreference[] values() {
        return (ContactIdentityPreference[]) $VALUES.clone();
    }

    static {
        IdentityPreferenceKey identityPreferenceKey = IdentityPreferenceKey.CONTACT_SYNC_ENABLED;
        IdentityPreferenceValue identityPreferenceValue = IdentityPreferenceValue.ENABLED;
        ContactIdentityPreference contactIdentityPreference = new ContactIdentityPreference("CONTACT_SYNC_PREFERENCE_ENABLED", 0, identityPreferenceKey, identityPreferenceValue);
        CONTACT_SYNC_PREFERENCE_ENABLED = contactIdentityPreference;
        IdentityPreferenceValue identityPreferenceValue2 = IdentityPreferenceValue.DISABLED;
        ContactIdentityPreference contactIdentityPreference2 = new ContactIdentityPreference("CONTACT_SYNC_PREFERENCE_DISABLED", 1, identityPreferenceKey, identityPreferenceValue2);
        CONTACT_SYNC_PREFERENCE_DISABLED = contactIdentityPreference2;
        IdentityPreferenceKey identityPreferenceKey2 = IdentityPreferenceKey.CONTACT_ACCESS_CONTROL_TYPE;
        ContactIdentityPreference contactIdentityPreference3 = new ContactIdentityPreference("CONTACT_ACCESS_ALL_CONTACTS", 2, identityPreferenceKey2, IdentityPreferenceValue.ALL_CONTACTS);
        CONTACT_ACCESS_ALL_CONTACTS = contactIdentityPreference3;
        ContactIdentityPreference contactIdentityPreference4 = new ContactIdentityPreference("CONTACT_ACCESS_FAVORITES_ONLY", 3, identityPreferenceKey2, IdentityPreferenceValue.FAVORITES_ONLY);
        CONTACT_ACCESS_FAVORITES_ONLY = contactIdentityPreference4;
        IdentityPreferenceKey identityPreferenceKey3 = IdentityPreferenceKey.GROUP_AMC_PREFERENCE;
        ContactIdentityPreference contactIdentityPreference5 = new ContactIdentityPreference("GROUP_AMC_PREFERENCE_ENABLED", 4, identityPreferenceKey3, identityPreferenceValue);
        GROUP_AMC_PREFERENCE_ENABLED = contactIdentityPreference5;
        ContactIdentityPreference contactIdentityPreference6 = new ContactIdentityPreference("GROUP_AMC_PREFERENCE_TYPE_DISABLED", 5, identityPreferenceKey3, identityPreferenceValue2);
        GROUP_AMC_PREFERENCE_TYPE_DISABLED = contactIdentityPreference6;
        IdentityPreferenceKey identityPreferenceKey4 = IdentityPreferenceKey.BLOCK_UNKNOWN_NUMBER_PREFERENCE;
        ContactIdentityPreference contactIdentityPreference7 = new ContactIdentityPreference("BLOCK_UNKNOWN_NUMBER_PREFERENCE_ENABLED", 6, identityPreferenceKey4, identityPreferenceValue);
        BLOCK_UNKNOWN_NUMBER_PREFERENCE_ENABLED = contactIdentityPreference7;
        ContactIdentityPreference contactIdentityPreference8 = new ContactIdentityPreference("BLOCK_UNKNOWN_NUMBER_PREFERENCE_DISABLED", 7, identityPreferenceKey4, identityPreferenceValue2);
        BLOCK_UNKNOWN_NUMBER_PREFERENCE_DISABLED = contactIdentityPreference8;
        $VALUES = new ContactIdentityPreference[]{contactIdentityPreference, contactIdentityPreference2, contactIdentityPreference3, contactIdentityPreference4, contactIdentityPreference5, contactIdentityPreference6, contactIdentityPreference7, contactIdentityPreference8};
    }

    private ContactIdentityPreference(String str, int i, IdentityPreferenceKey identityPreferenceKey, IdentityPreferenceValue identityPreferenceValue) {
        this.key = identityPreferenceKey;
        this.value = identityPreferenceValue;
    }

    public IdentityPreferenceKey getPreferenceKey() {
        return this.key;
    }

    public IdentityPreferenceValue getPreferenceValue() {
        return this.value;
    }

    public static ContactIdentityPreference getIdentityPreferenceForKeyAndValue(String str, String str2) {
        for (ContactIdentityPreference contactIdentityPreference : values()) {
            if (contactIdentityPreference.getPreferenceKey().isPreferenceKeyForString(str) && contactIdentityPreference.getPreferenceValue().isPreferenceValueForString(str2)) {
                return contactIdentityPreference;
            }
        }
        return null;
    }
}
