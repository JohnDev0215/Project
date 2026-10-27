package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ParsedComponent implements Parcelable {
    private static ParsedIntentInfo.ListParceler sForIntentInfos = (ParsedIntentInfo.ListParceler) Parcelling.Cache.getOrCreate(ParsedIntentInfo.ListParceler.class);
    int banner;
    private ComponentName componentName;
    int descriptionRes;
    int flags;
    int icon;
    private List<ParsedIntentInfo> intents;
    int labelRes;
    int logo;
    protected Bundle metaData;
    private String name;
    CharSequence nonLocalizedLabel;
    private String packageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    ParsedComponent() {
    }

    public ParsedComponent(ParsedComponent parsedComponent) {
        this.metaData = parsedComponent.metaData;
        this.name = parsedComponent.name;
        this.icon = parsedComponent.getIcon();
        this.labelRes = parsedComponent.getLabelRes();
        this.nonLocalizedLabel = parsedComponent.getNonLocalizedLabel();
        this.logo = parsedComponent.getLogo();
        this.banner = parsedComponent.getBanner();
        this.descriptionRes = parsedComponent.getDescriptionRes();
        this.flags = parsedComponent.getFlags();
        setPackageName(parsedComponent.packageName);
        this.intents = new ArrayList(parsedComponent.getIntents());
    }

    public void addIntent(ParsedIntentInfo parsedIntentInfo) {
        this.intents = CollectionUtils.add(this.intents, parsedIntentInfo);
    }

    public List<ParsedIntentInfo> getIntents() {
        List<ParsedIntentInfo> list = this.intents;
        return list != null ? list : Collections.emptyList();
    }

    public ParsedComponent setName(String str) {
        this.name = TextUtils.safeIntern(str);
        return this;
    }

    public void setPackageName(String str) {
        this.packageName = TextUtils.safeIntern(str);
        this.componentName = null;
    }

    public ComponentName getComponentName() {
        if (this.componentName == null) {
            this.componentName = new ComponentName(getPackageName(), getName());
        }
        return this.componentName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(getIcon());
        parcel.writeInt(getLabelRes());
        parcel.writeCharSequence(getNonLocalizedLabel());
        parcel.writeInt(getLogo());
        parcel.writeInt(getBanner());
        parcel.writeInt(getDescriptionRes());
        parcel.writeInt(getFlags());
        ParsingPackageImpl.sForInternedString.parcel(this.packageName, parcel, i);
        sForIntentInfos.parcel(getIntents(), parcel, i);
        parcel.writeBundle(this.metaData);
    }

    protected ParsedComponent(Parcel parcel) {
        ClassLoader classLoader = Object.class.getClassLoader();
        this.name = parcel.readString();
        this.icon = parcel.readInt();
        this.labelRes = parcel.readInt();
        this.nonLocalizedLabel = parcel.readCharSequence();
        this.logo = parcel.readInt();
        this.banner = parcel.readInt();
        this.descriptionRes = parcel.readInt();
        this.flags = parcel.readInt();
        this.packageName = ParsingPackageImpl.sForInternedString.unparcel(parcel);
        this.intents = sForIntentInfos.unparcel(parcel);
        this.metaData = parcel.readBundle(classLoader);
    }

    public String getName() {
        return this.name;
    }

    public int getIcon() {
        return this.icon;
    }

    public int getLabelRes() {
        return this.labelRes;
    }

    public CharSequence getNonLocalizedLabel() {
        return this.nonLocalizedLabel;
    }

    public int getLogo() {
        return this.logo;
    }

    public int getBanner() {
        return this.banner;
    }

    public int getDescriptionRes() {
        return this.descriptionRes;
    }

    public int getFlags() {
        return this.flags;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public Bundle getMetaData() {
        return this.metaData;
    }
}
