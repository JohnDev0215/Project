package com.google.android.textclassifier;

/* JADX INFO: loaded from: classes.dex */
public final class RemoteActionTemplate {
    public final String action;
    public final String[] category;
    public final String data;
    public final String description;
    public final String descriptionWithAppName;
    public final NamedVariant[] extras;
    public final Integer flags;
    public final String packageName;
    public final Integer requestCode;
    public final String titleWithEntity;
    public final String titleWithoutEntity;
    public final String type;

    public RemoteActionTemplate(String str, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, String[] strArr, String str8, NamedVariant[] namedVariantArr, Integer num2) {
        this.titleWithoutEntity = str;
        this.titleWithEntity = str2;
        this.description = str3;
        this.descriptionWithAppName = str4;
        this.action = str5;
        this.data = str6;
        this.type = str7;
        this.flags = num;
        this.category = strArr;
        this.packageName = str8;
        this.extras = namedVariantArr;
        this.requestCode = num2;
    }
}
