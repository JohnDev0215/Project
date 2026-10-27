package com.byd.ime.subtype.model;

import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubtypeItem.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/byd/ime/subtype/model/SubtypeItem;", "", "richInputMethodSubtype", "Lcom/android/inputmethod/latin/RichInputMethodSubtype;", "(Lcom/android/inputmethod/latin/RichInputMethodSubtype;)V", "mRichInputMethodSubtype", "getMRichInputMethodSubtype", "()Lcom/android/inputmethod/latin/RichInputMethodSubtype;", "setMRichInputMethodSubtype", "subtypeConfigData", "Lcom/byd/ime/subtype/model/SubtypeConfigData;", "getSubtypeConfigData", "()Lcom/byd/ime/subtype/model/SubtypeConfigData;", "setSubtypeConfigData", "(Lcom/byd/ime/subtype/model/SubtypeConfigData;)V", "getDisplayNameInSystemLocale", "", "getLanguageDisplayName", "getLocal", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubtypeItem {
    private RichInputMethodSubtype mRichInputMethodSubtype;
    public SubtypeConfigData subtypeConfigData;

    public SubtypeItem(RichInputMethodSubtype richInputMethodSubtype) {
        Intrinsics.checkNotNullParameter(richInputMethodSubtype, "richInputMethodSubtype");
        this.mRichInputMethodSubtype = richInputMethodSubtype;
    }

    public final RichInputMethodSubtype getMRichInputMethodSubtype() {
        return this.mRichInputMethodSubtype;
    }

    public final void setMRichInputMethodSubtype(RichInputMethodSubtype richInputMethodSubtype) {
        Intrinsics.checkNotNullParameter(richInputMethodSubtype, "<set-?>");
        this.mRichInputMethodSubtype = richInputMethodSubtype;
    }

    public final SubtypeConfigData getSubtypeConfigData() {
        SubtypeConfigData subtypeConfigData = this.subtypeConfigData;
        if (subtypeConfigData != null) {
            return subtypeConfigData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("subtypeConfigData");
        return null;
    }

    public final void setSubtypeConfigData(SubtypeConfigData subtypeConfigData) {
        Intrinsics.checkNotNullParameter(subtypeConfigData, "<set-?>");
        this.subtypeConfigData = subtypeConfigData;
    }

    public final String getLanguageDisplayName() {
        String middleDisplayName = this.mRichInputMethodSubtype.getMiddleDisplayName();
        Intrinsics.checkNotNullExpressionValue(middleDisplayName, "mRichInputMethodSubtype.middleDisplayName");
        return middleDisplayName;
    }

    public final String getLocal() {
        String locale = this.mRichInputMethodSubtype.getRawSubtype().getLocale();
        Intrinsics.checkNotNullExpressionValue(locale, "mRichInputMethodSubtype.rawSubtype.locale");
        return locale;
    }

    public final String getDisplayNameInSystemLocale() {
        String subtypeDisplayNameInSystemLocale = SubtypeLocaleUtils.getSubtypeDisplayNameInSystemLocale(this.mRichInputMethodSubtype.getRawSubtype());
        Intrinsics.checkNotNullExpressionValue(subtypeDisplayNameInSystemLocale, "getSubtypeDisplayNameInS…MethodSubtype.rawSubtype)");
        return subtypeDisplayNameInSystemLocale;
    }
}
