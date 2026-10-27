package com.android.settingslib.search;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes6.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface SearchIndexable {
    public static final int ALL = 31;
    public static final int ARC = 16;
    public static final int AUTO = 8;
    public static final int MOBILE = 1;
    public static final int TV = 2;
    public static final int WEAR = 4;

    int forTarget() default 31;
}
