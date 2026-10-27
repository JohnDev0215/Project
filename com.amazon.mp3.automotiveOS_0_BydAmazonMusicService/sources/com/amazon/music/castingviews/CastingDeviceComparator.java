package com.amazon.music.castingviews;

import com.amazon.music.casting.CastingDevice;
import com.amazon.music.model.Block;
import com.amazon.music.ui.model.castingtile.CastingTileModel;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public class CastingDeviceComparator implements Comparator {
    private static CastingDevice sPriorityDevice;

    public void setPriority(CastingDevice castingDevice) {
        sPriorityDevice = castingDevice;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        if (!(obj instanceof Block) || !(obj2 instanceof Block)) {
            throw new IllegalArgumentException("Object type passed is not of type Block");
        }
        boolean z = obj instanceof CastingTileModel;
        if (!z || !(obj2 instanceof CastingTileModel)) {
            if (z) {
                return -1;
            }
            if (obj2 instanceof CastingTileModel) {
                return 1;
            }
            throw new IllegalArgumentException("Object types passed cannot be compared");
        }
        CastingTileModel castingTileModel = (CastingTileModel) obj;
        CastingTileModel castingTileModel2 = (CastingTileModel) obj2;
        if (sPriorityDevice != null) {
            if (castingTileModel.targetId.get().equals(sPriorityDevice.getTargetId())) {
                return -1;
            }
            if (castingTileModel2.targetId.get().equals(sPriorityDevice.getTargetId())) {
                return 1;
            }
        }
        if (!castingTileModel.title.isPresent() || castingTileModel.title.get().isEmpty()) {
            return (!castingTileModel2.title.isPresent() || castingTileModel2.title.get().isEmpty()) ? 0 : 1;
        }
        if (!castingTileModel2.title.isPresent() || castingTileModel2.title.get().isEmpty()) {
            return -1;
        }
        return castingTileModel.title.get().compareTo(castingTileModel2.title.get());
    }
}
