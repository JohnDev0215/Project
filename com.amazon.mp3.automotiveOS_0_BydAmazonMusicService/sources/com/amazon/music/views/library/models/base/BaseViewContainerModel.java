package com.amazon.music.views.library.models.base;

import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewContainerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B;\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012J\u0014\u0010\u0016\u001a\u00020\u00122\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "T", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "viewId", "", "models", "", "tileType", "", "tags", "(Ljava/lang/String;Ljava/util/List;ILjava/util/List;)V", "getModels", "()Ljava/util/List;", "setModels", "(Ljava/util/List;)V", "getTileType", "()I", "notifyArtistFollowed", "", "notifyPodcastFollowState", "notifyPodcastJumpBackInState", "notifyPodcastSaveState", "updateModels", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseViewContainerModel<T extends BaseViewModel> extends BaseViewModel {
    private List<? extends T> models;
    private final int tileType;

    public /* synthetic */ BaseViewContainerModel(String str, List list, int i, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i2 & 4) != 0 ? 2 : i, (i2 & 8) != 0 ? null : list2);
    }

    public List<T> getModels() {
        return this.models;
    }

    public void setModels(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.models = list;
    }

    public int getTileType() {
        return this.tileType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected BaseViewContainerModel(String str, List<? extends T> models, int i, List<String> list) {
        super(str, list);
        Intrinsics.checkNotNullParameter(models, "models");
        this.models = models;
        this.tileType = i;
    }

    public final void updateModels(List<? extends T> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        if (getModels().equals(models)) {
            return;
        }
        setModels(models);
        notifyChanged(5);
    }

    public final void notifyArtistFollowed() {
        notifyChanged(7);
    }

    public final void notifyPodcastJumpBackInState() {
        notifyChanged(10);
    }

    public final void notifyPodcastFollowState() {
        notifyChanged(8);
    }

    public final void notifyPodcastSaveState() {
        notifyChanged(9);
    }
}
