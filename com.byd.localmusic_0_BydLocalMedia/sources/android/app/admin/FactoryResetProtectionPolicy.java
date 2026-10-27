package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public final class FactoryResetProtectionPolicy implements Parcelable {
    private static final String ATTR_VALUE = "value";
    public static final Parcelable.Creator<FactoryResetProtectionPolicy> CREATOR = new Parcelable.Creator<FactoryResetProtectionPolicy>() { // from class: android.app.admin.FactoryResetProtectionPolicy.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FactoryResetProtectionPolicy createFromParcel(Parcel parcel) {
            ArrayList arrayList = new ArrayList();
            int i = parcel.readInt();
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(parcel.readString());
            }
            return new FactoryResetProtectionPolicy(arrayList, parcel.readBoolean());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FactoryResetProtectionPolicy[] newArray(int i) {
            return new FactoryResetProtectionPolicy[i];
        }
    };
    private static final String KEY_FACTORY_RESET_PROTECTION_ACCOUNT = "factory_reset_protection_account";
    private static final String KEY_FACTORY_RESET_PROTECTION_ENABLED = "factory_reset_protection_enabled";
    private static final String LOG_TAG = "FactoryResetProtectionPolicy";
    private final List<String> mFactoryResetProtectionAccounts;
    private final boolean mFactoryResetProtectionEnabled;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private FactoryResetProtectionPolicy(List<String> list, boolean z) {
        this.mFactoryResetProtectionAccounts = list;
        this.mFactoryResetProtectionEnabled = z;
    }

    public List<String> getFactoryResetProtectionAccounts() {
        return this.mFactoryResetProtectionAccounts;
    }

    public boolean isFactoryResetProtectionEnabled() {
        return this.mFactoryResetProtectionEnabled;
    }

    public static class Builder {
        private List<String> mFactoryResetProtectionAccounts;
        private boolean mFactoryResetProtectionEnabled = true;

        public Builder setFactoryResetProtectionAccounts(List<String> list) {
            this.mFactoryResetProtectionAccounts = new ArrayList(list);
            return this;
        }

        public Builder setFactoryResetProtectionEnabled(boolean z) {
            this.mFactoryResetProtectionEnabled = z;
            return this;
        }

        public FactoryResetProtectionPolicy build() {
            return new FactoryResetProtectionPolicy(this.mFactoryResetProtectionAccounts, this.mFactoryResetProtectionEnabled);
        }
    }

    public String toString() {
        return "FactoryResetProtectionPolicy{mFactoryResetProtectionAccounts=" + this.mFactoryResetProtectionAccounts + ", mFactoryResetProtectionEnabled=" + this.mFactoryResetProtectionEnabled + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFactoryResetProtectionAccounts.size());
        Iterator<String> it = this.mFactoryResetProtectionAccounts.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next());
        }
        parcel.writeBoolean(this.mFactoryResetProtectionEnabled);
    }

    public static FactoryResetProtectionPolicy readFromXml(XmlPullParser xmlPullParser) {
        try {
            boolean z = Boolean.parseBoolean(xmlPullParser.getAttributeValue(null, KEY_FACTORY_RESET_PROTECTION_ENABLED));
            ArrayList arrayList = new ArrayList();
            int depth = xmlPullParser.getDepth();
            while (true) {
                int next = xmlPullParser.next();
                if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= depth)) {
                    break;
                }
                if (next != 3 && next != 4 && xmlPullParser.getName().equals(KEY_FACTORY_RESET_PROTECTION_ACCOUNT)) {
                    arrayList.add(xmlPullParser.getAttributeValue(null, "value"));
                }
            }
            return new FactoryResetProtectionPolicy(arrayList, z);
        } catch (IOException | XmlPullParserException e) {
            Log.w(LOG_TAG, "Reading from xml failed", e);
            return null;
        }
    }

    public void writeToXml(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.attribute(null, KEY_FACTORY_RESET_PROTECTION_ENABLED, Boolean.toString(this.mFactoryResetProtectionEnabled));
        for (String str : this.mFactoryResetProtectionAccounts) {
            xmlSerializer.startTag(null, KEY_FACTORY_RESET_PROTECTION_ACCOUNT);
            xmlSerializer.attribute(null, "value", str);
            xmlSerializer.endTag(null, KEY_FACTORY_RESET_PROTECTION_ACCOUNT);
        }
    }

    public boolean isNotEmpty() {
        return !this.mFactoryResetProtectionAccounts.isEmpty() && this.mFactoryResetProtectionEnabled;
    }
}
