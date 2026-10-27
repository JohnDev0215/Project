package com.byd.ime.subtype.model;

import android.hardware.bydauto_test.TestSettingManagerImplCallback;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubtypeConfigData.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/byd/ime/subtype/model/SubtypeConfigData;", "", "()V", SubtypeConfigData.JSON_KEY_LANGUAGE, "", "getLanguage", "()Ljava/lang/String;", TestSettingManagerImplCallback.SETLANGUAGE, "(Ljava/lang/String;)V", SubtypeConfigData.JSON_KEY_STATE, "", "getState", "()I", "setState", "(I)V", SubtypeConfigData.JSON_KEY_SUBTYPE_LOCAL, "getSubtypeLocal", "setSubtypeLocal", "toString", "Companion", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubtypeConfigData {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DEFAULT_SUBTYPE_LOCAL_LANGUAGE_EN = "en_US";
    public static final String JSON_FILE_NAME = "country_subtype.json";
    public static final String JSON_KEY_LANGUAGE = "language";
    public static final String JSON_KEY_STATE = "state";
    public static final String JSON_KEY_SUBTYPE_LOCAL = "subtypeLocal";
    public static final int STATE_DEFAULT_SELECT = 0;
    private static final int STATE_NOT_SUPPORT = -1;
    public static final int STATE_SUPPORT = 1;
    public static final String SUBTYPE_LOCAL_LANGUAGE_Arabic = "ar";
    public static final String SUBTYPE_LOCAL_LANGUAGE_Hebrew = "iw";
    public static final String SUBTYPE_LOCAL_LANGUAGE_Portuguese_Brazil = "pt_BR";
    public static final String SUBTYPE_LOCAL_LANGUAGE_Portuguese_Brazil_DISPLAY_NAME = "Português (Brasil)";
    public static final String SUBTYPE_LOCAL_LANGUAGE_Spanish_US = "es_US";
    public static final String SUBTYPE_LOCAL_LANGUAGE_Spanish_US_DISPLAY_NAME = "Español (América)";
    public String language;
    private int state = 1;
    public String subtypeLocal;

    public final String getLanguage() {
        String str = this.language;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(JSON_KEY_LANGUAGE);
        return null;
    }

    public final void setLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.language = str;
    }

    public final String getSubtypeLocal() {
        String str = this.subtypeLocal;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(JSON_KEY_SUBTYPE_LOCAL);
        return null;
    }

    public final void setSubtypeLocal(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtypeLocal = str;
    }

    public final int getState() {
        return this.state;
    }

    public final void setState(int i) {
        this.state = i;
    }

    /* JADX INFO: compiled from: SubtypeConfigData.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/byd/ime/subtype/model/SubtypeConfigData$Companion;", "", "()V", "DEFAULT_SUBTYPE_LOCAL_LANGUAGE_EN", "", "JSON_FILE_NAME", "JSON_KEY_LANGUAGE", "JSON_KEY_STATE", "JSON_KEY_SUBTYPE_LOCAL", "STATE_DEFAULT_SELECT", "", "STATE_NOT_SUPPORT", "STATE_SUPPORT", "SUBTYPE_LOCAL_LANGUAGE_Arabic", "SUBTYPE_LOCAL_LANGUAGE_Hebrew", "SUBTYPE_LOCAL_LANGUAGE_Portuguese_Brazil", "SUBTYPE_LOCAL_LANGUAGE_Portuguese_Brazil_DISPLAY_NAME", "SUBTYPE_LOCAL_LANGUAGE_Spanish_US", "SUBTYPE_LOCAL_LANGUAGE_Spanish_US_DISPLAY_NAME", "indexItemBySubtypeLocal", "Lcom/byd/ime/subtype/model/SubtypeConfigData;", "local", "itemList", "", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SubtypeConfigData indexItemBySubtypeLocal(String local, List<SubtypeConfigData> itemList) {
            Intrinsics.checkNotNullParameter(local, "local");
            Intrinsics.checkNotNullParameter(itemList, "itemList");
            int size = itemList.size();
            for (int i = 0; i < size; i++) {
                SubtypeConfigData subtypeConfigData = itemList.get(i);
                if (Intrinsics.areEqual(subtypeConfigData.getSubtypeLocal(), local) && subtypeConfigData.getState() != -1) {
                    return subtypeConfigData;
                }
            }
            return null;
        }
    }

    public String toString() {
        return "SubtypeData{language='" + getLanguage() + "', subtypeLocal=" + getSubtypeLocal() + ", state=" + this.state + '}';
    }
}
