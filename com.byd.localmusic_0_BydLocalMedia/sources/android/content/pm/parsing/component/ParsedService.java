package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ParsedService extends ParsedMainComponent {
    public static final Parcelable.Creator<ParsedService> CREATOR = new Parcelable.Creator<ParsedService>() { // from class: android.content.pm.parsing.component.ParsedService.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedService createFromParcel(Parcel parcel) {
            return new ParsedService(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedService[] newArray(int i) {
            return new ParsedService[i];
        }
    };
    int foregroundServiceType;
    private String permission;

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParsedService(ParsedService parsedService) {
        super(parsedService);
        this.foregroundServiceType = parsedService.foregroundServiceType;
        this.permission = parsedService.permission;
    }

    public ParsedMainComponent setPermission(String str) {
        this.permission = TextUtils.isEmpty(str) ? null : str.intern();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Service{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ');
        ComponentName.appendShortString(sb, getPackageName(), getName());
        sb.append('}');
        return sb.toString();
    }

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.foregroundServiceType);
        ParsingPackageImpl.sForInternedString.parcel(this.permission, parcel, i);
    }

    public ParsedService() {
    }

    protected ParsedService(Parcel parcel) {
        super(parcel);
        this.foregroundServiceType = parcel.readInt();
        this.permission = ParsingPackageImpl.sForInternedString.unparcel(parcel);
    }

    public int getForegroundServiceType() {
        return this.foregroundServiceType;
    }

    public String getPermission() {
        return this.permission;
    }
}
