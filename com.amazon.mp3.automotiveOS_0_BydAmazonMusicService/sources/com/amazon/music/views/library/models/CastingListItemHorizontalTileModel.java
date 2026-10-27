package com.amazon.music.views.library.models;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.enums.DeviceTypeEnum;
import kotlin.Metadata;

/* JADX INFO: compiled from: CastingListItemHorizontalTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0011\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0012\u001a\u0004\b\u0014\u0010\u0013R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0006R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u0006¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "headerTitle", "Ljava/lang/String;", "getHeaderTitle", "()Ljava/lang/String;", "title", "getTitle", "subTitle", "getSubTitle", "Lcom/amazon/music/views/library/models/enums/DeviceTypeEnum;", Splash.PARAMS_DEVICE_TYPE, "Lcom/amazon/music/views/library/models/enums/DeviceTypeEnum;", "getDeviceType", "()Lcom/amazon/music/views/library/models/enums/DeviceTypeEnum;", "", "isActive", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "isPlaying", "targetId", "getTargetId", "deviceTypeId", "getDeviceTypeId", "category", "getCategory", "imageUrl", "getImageUrl", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CastingListItemHorizontalTileModel extends BaseViewContentModel {
    private final String category;
    private final DeviceTypeEnum deviceType;
    private final String deviceTypeId;
    private final String headerTitle;
    private final String imageUrl;
    private final Boolean isActive;
    private final Boolean isPlaying;
    private final String subTitle;
    private final String targetId;
    private final String title;

    public final String getHeaderTitle() {
        return this.headerTitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final DeviceTypeEnum getDeviceType() {
        return this.deviceType;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public final Boolean getIsActive() {
        return this.isActive;
    }

    /* JADX INFO: renamed from: isPlaying, reason: from getter */
    public final Boolean getIsPlaying() {
        return this.isPlaying;
    }

    public final String getTargetId() {
        return this.targetId;
    }

    public final String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }
}
