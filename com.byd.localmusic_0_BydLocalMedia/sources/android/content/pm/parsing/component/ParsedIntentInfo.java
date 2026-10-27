package android.content.pm.parsing.component;

import android.content.IntentFilter;
import android.os.Parcel;
import android.util.Pair;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ParsedIntentInfo extends IntentFilter {
    public static final Parceler PARCELER = new Parceler();
    boolean hasDefault;
    int icon;
    int labelRes;
    CharSequence nonLocalizedLabel;

    public static class Parceler implements Parcelling<ParsedIntentInfo> {
        @Override // com.android.internal.util.Parcelling
        public void parcel(ParsedIntentInfo parsedIntentInfo, Parcel parcel, int i) {
            parsedIntentInfo.writeIntentInfoToParcel(parcel, i);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.android.internal.util.Parcelling
        public ParsedIntentInfo unparcel(Parcel parcel) {
            return new ParsedIntentInfo(parcel);
        }
    }

    public static class ListParceler implements Parcelling<List<ParsedIntentInfo>> {
        @Override // com.android.internal.util.Parcelling
        public void parcel(List<ParsedIntentInfo> list, Parcel parcel, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                ParsedIntentInfo.PARCELER.parcel(list.get(i2), parcel, i);
            }
        }

        @Override // com.android.internal.util.Parcelling
        public List<ParsedIntentInfo> unparcel(Parcel parcel) {
            int i = parcel.readInt();
            if (i == -1) {
                return null;
            }
            if (i == 0) {
                return new ArrayList(0);
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(ParsedIntentInfo.PARCELER.unparcel(parcel));
            }
            return arrayList;
        }
    }

    public static class StringPairListParceler implements Parcelling<List<Pair<String, ParsedIntentInfo>>> {
        @Override // com.android.internal.util.Parcelling
        public void parcel(List<Pair<String, ParsedIntentInfo>> list, Parcel parcel, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Pair<String, ParsedIntentInfo> pair = list.get(i2);
                parcel.writeString(pair.first);
                ParsedIntentInfo.PARCELER.parcel(pair.second, parcel, i);
            }
        }

        @Override // com.android.internal.util.Parcelling
        public List<Pair<String, ParsedIntentInfo>> unparcel(Parcel parcel) {
            int i = parcel.readInt();
            if (i == -1) {
                return null;
            }
            if (i == 0) {
                return new ArrayList(0);
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(Pair.create(parcel.readString(), ParsedIntentInfo.PARCELER.unparcel(parcel)));
            }
            return arrayList;
        }
    }

    public ParsedIntentInfo() {
    }

    public ParsedIntentInfo(Parcel parcel) {
        super(parcel);
        this.hasDefault = parcel.readBoolean();
        this.labelRes = parcel.readInt();
        this.nonLocalizedLabel = parcel.readCharSequence();
        this.icon = parcel.readInt();
    }

    public void writeIntentInfoToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBoolean(this.hasDefault);
        parcel.writeInt(this.labelRes);
        parcel.writeCharSequence(this.nonLocalizedLabel);
        parcel.writeInt(this.icon);
    }

    public String toString() {
        return "ProviderIntentInfo{" + Integer.toHexString(System.identityHashCode(this)) + '}';
    }

    public boolean isHasDefault() {
        return this.hasDefault;
    }

    public int getLabelRes() {
        return this.labelRes;
    }

    public CharSequence getNonLocalizedLabel() {
        return this.nonLocalizedLabel;
    }

    public int getIcon() {
        return this.icon;
    }
}
