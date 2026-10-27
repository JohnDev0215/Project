package ts.car.service.bt.phone;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ContactItem {
    public int id;
    public String givenName = null;
    public String familyName = null;
    public String displayName = null;
    public String namePinyin = null;
    public List<PhoneItem> phoneList = null;
    public String address = null;
    public String email = null;
    public String title = null;
    public String organization = null;
    public String photo = null;
    public String photoFormat = null;

    public static class PhoneItem {
        public String number = null;
        public int type = 0;
        public String label = null;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
