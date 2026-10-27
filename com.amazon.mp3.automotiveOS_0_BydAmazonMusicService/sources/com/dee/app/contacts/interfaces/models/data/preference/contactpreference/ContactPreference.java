package com.dee.app.contacts.interfaces.models.data.preference.contactpreference;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DROP_IN_ENABLED' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes5.dex */
public final class ContactPreference {
    private static final /* synthetic */ ContactPreference[] $VALUES;
    public static final ContactPreference DROP_IN_DISABLED;
    public static final ContactPreference DROP_IN_ENABLED;
    public static final ContactPreference EMERGENCY_CONTACT_DISABLED;
    public static final ContactPreference EMERGENCY_CONTACT_ENABLED;
    public static final ContactPreference FAVORITE_DISABLED;
    public static final ContactPreference FAVORITE_ENABLED;
    private final ContactPreferenceKey key;
    private final ContactPreferenceValue value;

    public static ContactPreference valueOf(String str) {
        return (ContactPreference) Enum.valueOf(ContactPreference.class, str);
    }

    public static ContactPreference[] values() {
        return (ContactPreference[]) $VALUES.clone();
    }

    static {
        ContactPreferenceKey contactPreferenceKey = ContactPreferenceKey.DROP_IN;
        ContactPreference contactPreference = new ContactPreference("DROP_IN_ENABLED", 0, contactPreferenceKey, ContactPreferenceValue.ENABLED);
        DROP_IN_ENABLED = contactPreference;
        ContactPreference contactPreference2 = new ContactPreference("DROP_IN_DISABLED", 1, contactPreferenceKey, ContactPreferenceValue.DISABLED);
        DROP_IN_DISABLED = contactPreference2;
        ContactPreferenceKey contactPreferenceKey2 = ContactPreferenceKey.EMERGENCY_CONTACT;
        ContactPreferenceValue contactPreferenceValue = ContactPreferenceValue.TRUE;
        ContactPreference contactPreference3 = new ContactPreference("EMERGENCY_CONTACT_ENABLED", 2, contactPreferenceKey2, contactPreferenceValue);
        EMERGENCY_CONTACT_ENABLED = contactPreference3;
        ContactPreferenceValue contactPreferenceValue2 = ContactPreferenceValue.FALSE;
        ContactPreference contactPreference4 = new ContactPreference("EMERGENCY_CONTACT_DISABLED", 3, contactPreferenceKey2, contactPreferenceValue2);
        EMERGENCY_CONTACT_DISABLED = contactPreference4;
        ContactPreferenceKey contactPreferenceKey3 = ContactPreferenceKey.FAVORITE_CONTACT;
        ContactPreference contactPreference5 = new ContactPreference("FAVORITE_ENABLED", 4, contactPreferenceKey3, contactPreferenceValue);
        FAVORITE_ENABLED = contactPreference5;
        ContactPreference contactPreference6 = new ContactPreference("FAVORITE_DISABLED", 5, contactPreferenceKey3, contactPreferenceValue2);
        FAVORITE_DISABLED = contactPreference6;
        $VALUES = new ContactPreference[]{contactPreference, contactPreference2, contactPreference3, contactPreference4, contactPreference5, contactPreference6};
    }

    private ContactPreference(String str, int i, ContactPreferenceKey contactPreferenceKey, ContactPreferenceValue contactPreferenceValue) {
        this.key = contactPreferenceKey;
        this.value = contactPreferenceValue;
    }

    public ContactPreferenceKey getPreferenceKey() {
        return this.key;
    }

    public ContactPreferenceValue getPreferenceValue() {
        return this.value;
    }

    public static ContactPreference getContactPreferenceForKeyAndValue(String str, String str2) {
        for (ContactPreference contactPreference : values()) {
            if (contactPreference.getPreferenceKey().isPreferenceKeyForString(str) && contactPreference.getPreferenceValue().isPreferenceValueForString(str2)) {
                return contactPreference;
            }
        }
        return null;
    }
}
