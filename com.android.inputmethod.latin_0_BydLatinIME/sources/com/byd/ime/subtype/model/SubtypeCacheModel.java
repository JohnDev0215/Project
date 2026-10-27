package com.byd.ime.subtype.model;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubtypeCacheModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005J\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\u0019\u001a\u00020\u0005J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u0007J\u0010\u0010 \u001a\u0004\u0018\u00010\u00052\u0006\u0010!\u001a\u00020\u0007J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u0007H\u0002J\u0006\u0010$\u001a\u00020\u0011J$\u0010%\u001a\u00020\u00112\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006("}, d2 = {"Lcom/byd/ime/subtype/model/SubtypeCacheModel;", "", "()V", "mAllSubtypeItemList", "", "Lcom/byd/ime/subtype/model/SubtypeItem;", "mCurrentSubtypeLocal", "", "getMCurrentSubtypeLocal", "()Ljava/lang/String;", "setMCurrentSubtypeLocal", "(Ljava/lang/String;)V", "mSelectItemList", "mSystemLanguage", "getMSystemLanguage", "setMSystemLanguage", "addSubTypeItemToAllSubtype", "", "subtypeItem", "addSubTypeItemToSelect", "clearData", "clearSelectSubTypeItemLists", "getAllSubtypeItemList", "getCurrentSubtypeItem", "getDefaultSubtypeEN", "getNextSelectItem", "getSelectItemList", "getSystemLanguageSubtype", "indexCurrentLocalFromSelectSubtype", "", "indexSubTypeItemFromAllSubtypeByLanguage", SubtypeConfigData.JSON_KEY_LANGUAGE, "indexSubTypeItemFromAllSubtypeBySubtypeLocal", SubtypeConfigData.JSON_KEY_SUBTYPE_LOCAL, "indexSubtype", "local", "sortSelectItemListListByAllOrder", "sortSelectItemListListByAllOrderInner", "selectSubtypeItemList", "orderList", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubtypeCacheModel {
    public String mCurrentSubtypeLocal;
    public String mSystemLanguage;
    private List<SubtypeItem> mAllSubtypeItemList = new ArrayList();
    private List<SubtypeItem> mSelectItemList = new ArrayList();

    public final String getMSystemLanguage() {
        String str = this.mSystemLanguage;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSystemLanguage");
        return null;
    }

    public final void setMSystemLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mSystemLanguage = str;
    }

    public final String getMCurrentSubtypeLocal() {
        String str = this.mCurrentSubtypeLocal;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCurrentSubtypeLocal");
        return null;
    }

    public final void setMCurrentSubtypeLocal(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCurrentSubtypeLocal = str;
    }

    public final void addSubTypeItemToAllSubtype(SubtypeItem subtypeItem) {
        Intrinsics.checkNotNullParameter(subtypeItem, "subtypeItem");
        this.mAllSubtypeItemList.add(subtypeItem);
    }

    public final List<SubtypeItem> getAllSubtypeItemList() {
        return this.mAllSubtypeItemList;
    }

    public final SubtypeItem getCurrentSubtypeItem() {
        return indexSubTypeItemFromAllSubtypeBySubtypeLocal(getMCurrentSubtypeLocal());
    }

    public final SubtypeItem indexSubTypeItemFromAllSubtypeBySubtypeLocal(String subtypeLocal) {
        Intrinsics.checkNotNullParameter(subtypeLocal, "subtypeLocal");
        int size = this.mAllSubtypeItemList.size();
        for (int i = 0; i < size; i++) {
            SubtypeItem subtypeItem = this.mAllSubtypeItemList.get(i);
            if (Intrinsics.areEqual(subtypeItem.getLocal(), subtypeLocal)) {
                return subtypeItem;
            }
        }
        return null;
    }

    public final SubtypeItem indexSubTypeItemFromAllSubtypeByLanguage(String language) {
        Intrinsics.checkNotNullParameter(language, "language");
        int size = this.mAllSubtypeItemList.size();
        for (int i = 0; i < size; i++) {
            SubtypeItem subtypeItem = this.mAllSubtypeItemList.get(i);
            if (Intrinsics.areEqual(subtypeItem.getSubtypeConfigData().getLanguage(), language)) {
                return subtypeItem;
            }
        }
        return null;
    }

    public final int indexCurrentLocalFromSelectSubtype() {
        int size = this.mSelectItemList.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(this.mSelectItemList.get(i).getSubtypeConfigData().getLanguage(), getMCurrentSubtypeLocal())) {
                return i;
            }
        }
        return 1;
    }

    public final SubtypeItem getDefaultSubtypeEN() {
        return indexSubTypeItemFromAllSubtypeBySubtypeLocal(SubtypeConfigData.DEFAULT_SUBTYPE_LOCAL_LANGUAGE_EN);
    }

    public final SubtypeItem getSystemLanguageSubtype() {
        return indexSubTypeItemFromAllSubtypeByLanguage(getMSystemLanguage());
    }

    public final void addSubTypeItemToSelect(SubtypeItem subtypeItem) {
        Intrinsics.checkNotNullParameter(subtypeItem, "subtypeItem");
        if (indexSubtype(subtypeItem.getLocal()) == -1) {
            this.mSelectItemList.add(subtypeItem);
        }
    }

    public final void clearSelectSubTypeItemLists() {
        this.mSelectItemList.clear();
    }

    public final List<SubtypeItem> getSelectItemList() {
        return this.mSelectItemList;
    }

    public final SubtypeItem getNextSelectItem() {
        if (this.mSelectItemList.size() == 1) {
            return this.mSelectItemList.get(0);
        }
        return this.mSelectItemList.get((indexSubtype(getMCurrentSubtypeLocal()) + 1) % this.mSelectItemList.size());
    }

    private final int indexSubtype(String local) {
        List<SubtypeItem> list = this.mSelectItemList;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(local, list.get(i).getLocal())) {
                return i;
            }
        }
        return -1;
    }

    public final void sortSelectItemListListByAllOrder() {
        sortSelectItemListListByAllOrderInner(this.mSelectItemList, this.mAllSubtypeItemList);
    }

    private final void sortSelectItemListListByAllOrderInner(List<SubtypeItem> selectSubtypeItemList, List<SubtypeItem> orderList) {
        int i = 0;
        for (int size = orderList.size() - 1; -1 < size && i != selectSubtypeItemList.size(); size--) {
            SubtypeItem subtypeItem = orderList.get(size);
            int size2 = selectSubtypeItemList.size();
            int i2 = 0;
            while (true) {
                if (i2 < size2) {
                    if (Intrinsics.areEqual(subtypeItem.getLocal(), selectSubtypeItemList.get(i2).getLocal())) {
                        selectSubtypeItemList.add(0, selectSubtypeItemList.remove(i2));
                        i++;
                        break;
                    }
                    i2++;
                }
            }
        }
    }

    public final void clearData() {
        this.mAllSubtypeItemList.clear();
    }
}
