package com.amazon.music.uicasting;

import androidx.mediarouter.media.MediaRouter;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.ui.model.castingtile.CastingTileModel;
import com.amazon.musicplayqueueservice.client.common.CastingDevice;

/* JADX INFO: loaded from: classes4.dex */
public class CastingTileConverter {
    public CastingTileModel convert(CastingDevice castingDevice) {
        String targetId = castingDevice.getTargetId();
        String name = castingDevice.getName();
        String imageUrl = castingDevice.getImageUrl();
        String category = castingDevice.getCategory();
        return CastingTileModel.builder("uuid", targetId, name).withDeviceTypeId(getDeviceTypeId(category)).withImageUrl(imageUrl).withCategory(category).withIsPlaying(castingDevice.isIsPlaying()).build();
    }

    public CastingTileModel convert(com.amazon.music.casting.CastingDevice castingDevice) {
        String targetId = castingDevice.getTargetId();
        String name = castingDevice.getName();
        String imageUrl = castingDevice.getImageUrl();
        CastingCategory category = castingDevice.getCategory();
        return CastingTileModel.builder("uuid", targetId, name).withDeviceTypeId(castingDevice.getDeviceTypeId()).withImageUrl(imageUrl).withCategory(category == null ? null : category.toString()).withIsPlaying(castingDevice.isPlaying()).build();
    }

    public CastingTileModel convert(MediaRouter.RouteInfo routeInfo) {
        String id = routeInfo.getId();
        String name = routeInfo.getName();
        String name2 = CastingCategory.CHROMECAST.getName();
        return CastingTileModel.builder("uuid", id, name).withDeviceTypeId(getDeviceTypeId(name2)).withImageUrl("").withCategory(name2).build();
    }

    private String getDeviceTypeId(String str) {
        CastingCategory castingCategory = CastingCategory.CHROMECAST;
        return castingCategory.getName().equals(str) ? castingCategory.getName() : "UNKNOWN";
    }
}
