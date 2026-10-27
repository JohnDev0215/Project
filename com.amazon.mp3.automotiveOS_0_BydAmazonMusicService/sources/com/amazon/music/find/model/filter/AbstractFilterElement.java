package com.amazon.music.find.model.filter;

import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractFilterElement.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\u0000H&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\r"}, d2 = {"Lcom/amazon/music/find/model/filter/AbstractFilterElement;", "", "id", "", "title", Constants.ENABLE_DISABLE, "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "()Z", "getTitle", "deepCopy", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbstractFilterElement {
    private final String id;
    private final boolean isEnabled;
    private final String title;

    public abstract AbstractFilterElement deepCopy();

    public AbstractFilterElement(String id, String title, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.title = title;
        this.isEnabled = z;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public boolean getIsEnabled() {
        return this.isEnabled;
    }
}
