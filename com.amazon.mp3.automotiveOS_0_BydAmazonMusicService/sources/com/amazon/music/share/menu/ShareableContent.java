package com.amazon.music.share.menu;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareableContent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 a2\u00020\u0001:\u0001aB\u0007¢\u0006\u0004\b]\u0010^B\u0011\b\u0016\u0012\u0006\u0010_\u001a\u00020\u0002¢\u0006\u0004\b]\u0010`J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR$\u0010\u0013\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR$\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR$\u0010\u0019\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010$\u001a\u0004\u0018\u00010#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010*\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010\u000b\u001a\u0004\b+\u0010\r\"\u0004\b,\u0010\u000fR$\u0010-\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010\u000b\u001a\u0004\b.\u0010\r\"\u0004\b/\u0010\u000fR$\u00100\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u0010\u000b\u001a\u0004\b1\u0010\r\"\u0004\b2\u0010\u000fR\"\u00104\u001a\u0002038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010:\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b:\u0010\u000b\u001a\u0004\b;\u0010\r\"\u0004\b<\u0010\u000fR$\u0010=\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010\u000b\u001a\u0004\b>\u0010\r\"\u0004\b?\u0010\u000fR$\u0010@\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b@\u0010\u000b\u001a\u0004\bA\u0010\r\"\u0004\bB\u0010\u000fR$\u0010C\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR*\u0010J\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010I8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR$\u0010Q\u001a\u0004\u0018\u00010P8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR$\u0010W\u001a\u0004\u0018\u00010P8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bW\u0010R\u001a\u0004\bX\u0010T\"\u0004\bY\u0010VR*\u0010Z\u001a\n\u0012\u0004\u0012\u00020P\u0018\u00010I8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bZ\u0010K\u001a\u0004\b[\u0010M\"\u0004\b\\\u0010O¨\u0006b"}, d2 = {"Lcom/amazon/music/share/menu/ShareableContent;", "Landroid/os/Parcelable;", "Landroid/os/Parcel;", "dest", "", "flags", "", "writeToParcel", "describeContents", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "setSubtitle", "subject", "getSubject", "setSubject", ContextMappingConstants.ENTITY_ID, "getEntityId", "setEntityId", "privateEntityId", "getPrivateEntityId", "setPrivateEntityId", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "getEntityIdType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "setEntityIdType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;)V", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "getEntityType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "setEntityType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;)V", "message", "getMessage", "setMessage", "backgroundImage", "getBackgroundImage", "setBackgroundImage", ContextMappingConstants.IMAGE, "getImage", "setImage", "", "imageAspectRatio", "F", "getImageAspectRatio", "()F", "setImageAspectRatio", "(F)V", ImagesContract.URL, "getUrl", "setUrl", "contentType", "getContentType", "setContentType", "ref", "getRef", "setRef", "shareTextId", "Ljava/lang/Integer;", "getShareTextId", "()Ljava/lang/Integer;", "setShareTextId", "(Ljava/lang/Integer;)V", "", "lyrics", "Ljava/util/List;", "getLyrics", "()Ljava/util/List;", "setLyrics", "(Ljava/util/List;)V", "Landroid/graphics/Bitmap;", "imageBitmap", "Landroid/graphics/Bitmap;", "getImageBitmap", "()Landroid/graphics/Bitmap;", "setImageBitmap", "(Landroid/graphics/Bitmap;)V", "backgroundBitmap", "getBackgroundBitmap", "setBackgroundBitmap", "shareCardsBackgroundBitmaps", "getShareCardsBackgroundBitmaps", "setShareCardsBackgroundBitmaps", "<init>", "()V", "parcel", "(Landroid/os/Parcel;)V", "CREATOR", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class ShareableContent implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Bitmap backgroundBitmap;
    private String backgroundImage;
    private String contentType;
    private String entityId;
    private EntityIdType entityIdType;
    private EntityType entityType;
    private String image;
    private float imageAspectRatio;
    private Bitmap imageBitmap;
    private List<String> lyrics;
    private String message;
    private String privateEntityId;
    private String ref;
    private List<Bitmap> shareCardsBackgroundBitmaps;
    private Integer shareTextId;
    private String subject;
    private String subtitle;
    private String title;
    private String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ShareableContent() {
        this.imageAspectRatio = 1.0f;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final void setSubtitle(String str) {
        this.subtitle = str;
    }

    public final void setSubject(String str) {
        this.subject = str;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final void setEntityId(String str) {
        this.entityId = str;
    }

    public final String getPrivateEntityId() {
        return this.privateEntityId;
    }

    public final void setPrivateEntityId(String str) {
        this.privateEntityId = str;
    }

    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public final void setEntityIdType(EntityIdType entityIdType) {
        this.entityIdType = entityIdType;
    }

    public final EntityType getEntityType() {
        return this.entityType;
    }

    public final void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    public final String getBackgroundImage() {
        return this.backgroundImage;
    }

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
    }

    public final float getImageAspectRatio() {
        return this.imageAspectRatio;
    }

    public final void setImageAspectRatio(float f) {
        this.imageAspectRatio = f;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final void setContentType(String str) {
        this.contentType = str;
    }

    public final String getRef() {
        return this.ref;
    }

    public final void setRef(String str) {
        this.ref = str;
    }

    public final Integer getShareTextId() {
        return this.shareTextId;
    }

    public final void setShareTextId(Integer num) {
        this.shareTextId = num;
    }

    public final List<String> getLyrics() {
        return this.lyrics;
    }

    public final void setLyrics(List<String> list) {
        this.lyrics = list;
    }

    public final Bitmap getImageBitmap() {
        return this.imageBitmap;
    }

    public final void setImageBitmap(Bitmap bitmap) {
        this.imageBitmap = bitmap;
    }

    public final Bitmap getBackgroundBitmap() {
        return this.backgroundBitmap;
    }

    public final void setBackgroundBitmap(Bitmap bitmap) {
        this.backgroundBitmap = bitmap;
    }

    public final List<Bitmap> getShareCardsBackgroundBitmaps() {
        return this.shareCardsBackgroundBitmaps;
    }

    public final void setShareCardsBackgroundBitmaps(List<Bitmap> list) {
        this.shareCardsBackgroundBitmaps = list;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShareableContent(Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.subject = parcel.readString();
        this.entityId = parcel.readString();
        String string = parcel.readString();
        this.entityIdType = string == null ? null : EntityIdType.valueOf(string);
        String string2 = parcel.readString();
        this.entityType = string2 != null ? EntityType.valueOf(string2) : null;
        this.message = parcel.readString();
        this.backgroundImage = parcel.readString();
        this.image = parcel.readString();
        this.imageAspectRatio = parcel.readFloat();
        this.url = parcel.readString();
        this.contentType = parcel.readString();
        this.ref = parcel.readString();
        this.shareTextId = Integer.valueOf(parcel.readInt());
        this.lyrics = parcel.createStringArrayList();
        this.imageBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.backgroundBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.shareCardsBackgroundBitmaps = (List) parcel.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.subject);
        dest.writeString(this.entityId);
        EntityIdType entityIdType = this.entityIdType;
        dest.writeString(entityIdType == null ? null : entityIdType.name());
        EntityType entityType = this.entityType;
        dest.writeString(entityType != null ? entityType.name() : null);
        dest.writeString(this.message);
        dest.writeString(this.backgroundImage);
        dest.writeString(this.image);
        dest.writeFloat(this.imageAspectRatio);
        dest.writeString(this.url);
        dest.writeString(this.contentType);
        dest.writeString(this.ref);
        dest.writeStringList(this.lyrics);
        Integer num = this.shareTextId;
        if (num != null) {
            dest.writeInt(num.intValue());
        }
        dest.writeParcelable(this.imageBitmap, flags);
        dest.writeParcelable(this.backgroundBitmap, flags);
        dest.writeTypedList(this.shareCardsBackgroundBitmaps);
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.ShareableContent$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ShareableContent.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/music/share/menu/ShareableContent$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/amazon/music/share/menu/ShareableContent;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/amazon/music/share/menu/ShareableContent;", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<ShareableContent> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareableContent createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ShareableContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareableContent[] newArray(int size) {
            return new ShareableContent[size];
        }
    }
}
