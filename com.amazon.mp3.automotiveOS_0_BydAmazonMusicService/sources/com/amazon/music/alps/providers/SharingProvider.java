package com.amazon.music.alps.providers;

import android.content.Context;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SharingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/alps/providers/SharingProvider;", "", "share", "", "context", "Landroid/content/Context;", "content", "Lcom/amazon/music/alps/providers/SharingProvider$ShareContent;", "ShareContent", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SharingProvider {
    void share(Context context, ShareContent content);

    /* JADX INFO: compiled from: SharingProvider.kt */
    @Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001B\u009d\u0001\u0012\b\b\u0002\u0010\t\u001a\u00020\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010.\u001a\u00020\u0002¢\u0006\u0004\b0\u00101J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0019\u0010\u0010R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u0010R\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010\"\u001a\u0004\u0018\u00010!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010&\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b&\u0010\u000e\u001a\u0004\b'\u0010\u0010R\u0019\u0010(\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b(\u0010\u000e\u001a\u0004\b)\u0010\u0010R\u0019\u0010*\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b*\u0010\u000e\u001a\u0004\b+\u0010\u0010R\u0019\u0010,\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b,\u0010\u000e\u001a\u0004\b-\u0010\u0010R\u0017\u0010.\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b.\u0010\u000e\u001a\u0004\b/\u0010\u0010¨\u00062"}, d2 = {"Lcom/amazon/music/alps/providers/SharingProvider$ShareContent;", "", "", "toString", "", "hashCode", "other", "", "equals", "contentType", "I", "getContentType", "()I", "refMarker", "Ljava/lang/String;", "getRefMarker", "()Ljava/lang/String;", "message", "getMessage", "", "imageAspectRatio", "Ljava/lang/Float;", "getImageAspectRatio", "()Ljava/lang/Float;", "backgroundImage", "getBackgroundImage", ContextMappingConstants.IMAGE, "getImage", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "getEntityType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "getEntityIdType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "getEntityId", "subject", "getSubject", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "title", "getTitle", ImagesContract.URL, "getUrl", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class ShareContent {
        private final String backgroundImage;
        private final int contentType;
        private final String entityId;
        private final EntityIdType entityIdType;
        private final EntityType entityType;
        private final String image;
        private final Float imageAspectRatio;
        private final String message;
        private final String refMarker;
        private final String subject;
        private final String subtitle;
        private final String title;
        private final String url;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ShareContent)) {
                return false;
            }
            ShareContent shareContent = (ShareContent) other;
            return this.contentType == shareContent.contentType && Intrinsics.areEqual(this.refMarker, shareContent.refMarker) && Intrinsics.areEqual(this.message, shareContent.message) && Intrinsics.areEqual((Object) this.imageAspectRatio, (Object) shareContent.imageAspectRatio) && Intrinsics.areEqual(this.backgroundImage, shareContent.backgroundImage) && Intrinsics.areEqual(this.image, shareContent.image) && this.entityType == shareContent.entityType && this.entityIdType == shareContent.entityIdType && Intrinsics.areEqual(this.entityId, shareContent.entityId) && Intrinsics.areEqual(this.subject, shareContent.subject) && Intrinsics.areEqual(this.subtitle, shareContent.subtitle) && Intrinsics.areEqual(this.title, shareContent.title) && Intrinsics.areEqual(this.url, shareContent.url);
        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.contentType) * 31;
            String str = this.refMarker;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.message;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Float f = this.imageAspectRatio;
            int iHashCode4 = (iHashCode3 + (f == null ? 0 : f.hashCode())) * 31;
            String str3 = this.backgroundImage;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.image;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            EntityType entityType = this.entityType;
            int iHashCode7 = (iHashCode6 + (entityType == null ? 0 : entityType.hashCode())) * 31;
            EntityIdType entityIdType = this.entityIdType;
            int iHashCode8 = (iHashCode7 + (entityIdType == null ? 0 : entityIdType.hashCode())) * 31;
            String str5 = this.entityId;
            int iHashCode9 = (iHashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.subject;
            int iHashCode10 = (iHashCode9 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.subtitle;
            int iHashCode11 = (iHashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.title;
            return ((iHashCode11 + (str8 != null ? str8.hashCode() : 0)) * 31) + this.url.hashCode();
        }

        public String toString() {
            return "ShareContent(contentType=" + this.contentType + ", refMarker=" + ((Object) this.refMarker) + ", message=" + ((Object) this.message) + ", imageAspectRatio=" + this.imageAspectRatio + ", backgroundImage=" + ((Object) this.backgroundImage) + ", image=" + ((Object) this.image) + ", entityType=" + this.entityType + ", entityIdType=" + this.entityIdType + ", entityId=" + ((Object) this.entityId) + ", subject=" + ((Object) this.subject) + ", subtitle=" + ((Object) this.subtitle) + ", title=" + ((Object) this.title) + ", url=" + this.url + ')';
        }

        public ShareContent(int i, String str, String str2, Float f, String str3, String str4, EntityType entityType, EntityIdType entityIdType, String str5, String str6, String str7, String str8, String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.contentType = i;
            this.refMarker = str;
            this.message = str2;
            this.imageAspectRatio = f;
            this.backgroundImage = str3;
            this.image = str4;
            this.entityType = entityType;
            this.entityIdType = entityIdType;
            this.entityId = str5;
            this.subject = str6;
            this.subtitle = str7;
            this.title = str8;
            this.url = url;
        }

        public final int getContentType() {
            return this.contentType;
        }

        public final String getRefMarker() {
            return this.refMarker;
        }

        public final String getMessage() {
            return this.message;
        }

        public /* synthetic */ ShareContent(int i, String str, String str2, Float f, String str3, String str4, EntityType entityType, EntityIdType entityIdType, String str5, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? -1 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? Float.valueOf(1.0f) : f, (i2 & 16) != 0 ? "" : str3, (i2 & 32) != 0 ? "" : str4, (i2 & 64) != 0 ? EntityType.UNKNOWN : entityType, (i2 & 128) != 0 ? null : entityIdType, (i2 & 256) != 0 ? "" : str5, (i2 & 512) != 0 ? "" : str6, (i2 & 1024) != 0 ? "" : str7, (i2 & 2048) != 0 ? "" : str8, str9);
        }

        public final Float getImageAspectRatio() {
            return this.imageAspectRatio;
        }

        public final String getBackgroundImage() {
            return this.backgroundImage;
        }

        public final String getImage() {
            return this.image;
        }

        public final EntityType getEntityType() {
            return this.entityType;
        }

        public final EntityIdType getEntityIdType() {
            return this.entityIdType;
        }

        public final String getEntityId() {
            return this.entityId;
        }

        public final String getSubject() {
            return this.subject;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUrl() {
            return this.url;
        }
    }
}
