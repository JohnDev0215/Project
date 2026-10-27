package com.android.bluetooth.pbapclient;

import com.android.vcard.VCardEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class PhonebookEntry {
    public String id;
    public Name name = new Name();
    public List<Phone> phones = new ArrayList();

    public static class Name {
        public String family;
        public String given;
        public String middle;
        public String prefix;
        public String suffix;

        public boolean equals(Object o) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            if (!(o instanceof Name)) {
                return false;
            }
            Name n = (Name) o;
            if (!Objects.equals(this.family, n.family) && ((str5 = this.family) == null || !str5.equals(n.family))) {
                return false;
            }
            if (!Objects.equals(this.given, n.given) && ((str4 = this.given) == null || !str4.equals(n.given))) {
                return false;
            }
            if (!Objects.equals(this.middle, n.middle) && ((str3 = this.middle) == null || !str3.equals(n.middle))) {
                return false;
            }
            if (Objects.equals(this.prefix, n.prefix) || ((str2 = this.prefix) != null && str2.equals(n.prefix))) {
                return Objects.equals(this.suffix, n.suffix) || ((str = this.suffix) != null && str.equals(n.suffix));
            }
            return false;
        }

        public int hashCode() {
            String str = this.family;
            int result = (str == null ? 0 : str.hashCode()) * 23;
            int i = result * 23;
            String str2 = this.given;
            int result2 = i + (str2 == null ? 0 : str2.hashCode());
            int result3 = result2 * 23;
            String str3 = this.middle;
            int result4 = (result3 + (str3 == null ? 0 : str3.hashCode())) * 23;
            String str4 = this.prefix;
            int result5 = (result4 + (str4 == null ? 0 : str4.hashCode())) * 23;
            String str5 = this.suffix;
            return result5 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "Name: { family: " + this.family + " given: " + this.given + " middle: " + this.middle + " prefix: " + this.prefix + " suffix: " + this.suffix + " }";
        }
    }

    public static class Phone {
        public String number;
        public int type;

        public boolean equals(Object o) {
            String str;
            if (!(o instanceof Phone)) {
                return false;
            }
            Phone p = (Phone) o;
            return (Objects.equals(this.number, p.number) || ((str = this.number) != null && str.equals(p.number))) && this.type == p.type;
        }

        public int hashCode() {
            return (this.type * 23) + this.number.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(" Phone: { number: ");
            sb.append(this.number);
            sb.append(" type: " + this.type);
            sb.append(" }");
            return sb.toString();
        }
    }

    public boolean equals(Object object) {
        if (object instanceof PhonebookEntry) {
            return equals((PhonebookEntry) object);
        }
        return false;
    }

    public PhonebookEntry() {
    }

    public PhonebookEntry(VCardEntry v) {
        VCardEntry.NameData n = v.getNameData();
        this.name.family = n.getFamily();
        this.name.given = n.getGiven();
        this.name.middle = n.getMiddle();
        this.name.prefix = n.getPrefix();
        this.name.suffix = n.getSuffix();
        List<VCardEntry.PhoneData> vp = v.getPhoneList();
        if (vp == null || vp.isEmpty()) {
            return;
        }
        for (VCardEntry.PhoneData p : vp) {
            Phone phone = new Phone();
            phone.type = p.getType();
            phone.number = p.getNumber();
            this.phones.add(phone);
        }
    }

    private boolean equals(PhonebookEntry p) {
        return this.name.equals(p.name) && this.phones.equals(p.phones);
    }

    public int hashCode() {
        return this.name.hashCode() + (this.phones.hashCode() * 23);
    }

    public String toString() {
        return "PhonebookEntry { id: " + this.id + " " + this.name.toString() + this.phones.toString() + " }";
    }
}
