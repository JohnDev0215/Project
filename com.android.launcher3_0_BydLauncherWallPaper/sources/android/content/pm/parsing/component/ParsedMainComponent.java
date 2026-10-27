package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ParsedMainComponent extends ParsedComponent {
    public static final Parcelable.Creator<ParsedMainComponent> CREATOR = new Parcelable.Creator<ParsedMainComponent>() { // from class: android.content.pm.parsing.component.ParsedMainComponent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedMainComponent createFromParcel(Parcel parcel) {
            return new ParsedMainComponent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedMainComponent[] newArray(int i) {
            return new ParsedMainComponent[i];
        }
    };
    boolean directBootAware;
    boolean enabled;
    boolean exported;
    int order;
    private String processName;
    String splitName;

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParsedMainComponent() {
        this.enabled = true;
    }

    public ParsedMainComponent(ParsedMainComponent parsedMainComponent) {
        super(parsedMainComponent);
        this.enabled = true;
        this.processName = parsedMainComponent.processName;
        this.directBootAware = parsedMainComponent.directBootAware;
        this.enabled = parsedMainComponent.enabled;
        this.exported = parsedMainComponent.exported;
        this.order = parsedMainComponent.order;
        this.splitName = parsedMainComponent.splitName;
    }

    public ParsedMainComponent setProcessName(String str) {
        this.processName = TextUtils.safeIntern(str);
        return this;
    }

    public ParsedMainComponent setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    public String getClassName() {
        return getName();
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ParsingPackageImpl.sForInternedString.parcel(this.processName, parcel, i);
        parcel.writeBoolean(this.directBootAware);
        parcel.writeBoolean(this.enabled);
        parcel.writeBoolean(this.exported);
        parcel.writeInt(this.order);
        parcel.writeString(this.splitName);
    }

    protected ParsedMainComponent(Parcel parcel) {
        super(parcel);
        this.enabled = true;
        this.processName = ParsingPackageImpl.sForInternedString.unparcel(parcel);
        this.directBootAware = parcel.readBoolean();
        this.enabled = parcel.readBoolean();
        this.exported = parcel.readBoolean();
        this.order = parcel.readInt();
        this.splitName = parcel.readString();
    }

    public String getProcessName() {
        return this.processName;
    }

    public boolean isDirectBootAware() {
        return this.directBootAware;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isExported() {
        return this.exported;
    }

    public int getOrder() {
        return this.order;
    }

    public String getSplitName() {
        return this.splitName;
    }

    public ParsedMainComponent setDirectBootAware(boolean z) {
        this.directBootAware = z;
        return this;
    }

    public ParsedMainComponent setExported(boolean z) {
        this.exported = z;
        return this;
    }

    public ParsedMainComponent setSplitName(String str) {
        this.splitName = str;
        return this;
    }
}
