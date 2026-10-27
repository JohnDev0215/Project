package com.amazon.music.alps.liveevent.enums;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lcom/amazon/music/alps/liveevent/enums/ImageType;", "", "(Ljava/lang/String;I)V", "BACKGROUND", "PRIMARY", "PROFILE", "SHARING", "SPONSOR", "UNKNOWN", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum ImageType {
    BACKGROUND,
    PRIMARY,
    PROFILE,
    SHARING,
    SPONSOR,
    UNKNOWN;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ImageType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/alps/liveevent/enums/ImageType$Companion;", "", "()V", "from", "Lcom/amazon/music/alps/liveevent/enums/ImageType;", "imageType", "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImageType from(String imageType) {
            ImageType imageType2;
            ImageType[] imageTypeArrValues = ImageType.values();
            int length = imageTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    imageType2 = null;
                    break;
                }
                imageType2 = imageTypeArrValues[i];
                i++;
                if (Intrinsics.areEqual(imageType2.name(), imageType)) {
                    break;
                }
            }
            return imageType2 == null ? ImageType.UNKNOWN : imageType2;
        }
    }
}
