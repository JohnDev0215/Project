package com.amazon.music.curate.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryContextMapping.kt */
/* JADX INFO: loaded from: classes4.dex */
@Entity
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\b\u0017\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0013J\b\u0010%\u001a\u00020\u0005H\u0016R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015¨\u0006&"}, d2 = {"Lcom/amazon/music/curate/model/LibraryContextMapping;", "", "id", "", ContextMappingConstants.PAGE_CONTEXT, "", ContextMappingConstants.WIDGET_CONTEXT, ContextMappingConstants.WIDGET_ITEM_CONTEXT, ContextMappingConstants.ENTITY_ID_TYPE, ContextMappingConstants.ENTITY_ID, ContextMappingConstants.LABEL, ContextMappingConstants.PRIMARY_TEXT, ContextMappingConstants.SECONDARY_TEXT, ContextMappingConstants.TERTIARY_TEXT, ContextMappingConstants.IMAGE, ContextMappingConstants.CONTENT_ENCODING, ContextMappingConstants.CONTENT_SRC, "contentInfoInstId", ContextMappingConstants.PLAY_MODE, "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContentEncoding", "()Ljava/lang/String;", "getContentInfoInstId", "getContentSrc", "getEntityId", "getEntityIdType", "getId", "()I", "getImage", "getLabel", "getPageContext", "getPlayMode", "getPrimaryText", "getSecondaryText", "getTertiaryText", "getWidgetContext", "getWidgetItemContext", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LibraryContextMapping {

    @ColumnInfo(name = "content_encoding")
    private final String contentEncoding;

    @ColumnInfo(name = "content_info_inst_id")
    private final String contentInfoInstId;

    @ColumnInfo(name = "content_src")
    private final String contentSrc;

    @ColumnInfo(name = "entity_id")
    private final String entityId;

    @ColumnInfo(name = "entity_id_type")
    private final String entityIdType;

    @PrimaryKey(autoGenerate = true)
    private final int id;

    @ColumnInfo(name = ContextMappingConstants.IMAGE)
    private final String image;

    @ColumnInfo(name = ContextMappingConstants.LABEL)
    private final String label;

    @ColumnInfo(name = "page_context")
    private final String pageContext;

    @ColumnInfo(name = "play_mode")
    private final String playMode;

    @ColumnInfo(name = "primary_text")
    private final String primaryText;

    @ColumnInfo(name = "secondary_text")
    private final String secondaryText;

    @ColumnInfo(name = "tertiary_text")
    private final String tertiaryText;

    @ColumnInfo(name = "widget_context")
    private final String widgetContext;

    @ColumnInfo(name = "widget_item_context")
    private final String widgetItemContext;

    public LibraryContextMapping(int i, String pageContext, String widgetContext, String widgetItemContext, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        Intrinsics.checkNotNullParameter(pageContext, "pageContext");
        Intrinsics.checkNotNullParameter(widgetContext, "widgetContext");
        Intrinsics.checkNotNullParameter(widgetItemContext, "widgetItemContext");
        this.id = i;
        this.pageContext = pageContext;
        this.widgetContext = widgetContext;
        this.widgetItemContext = widgetItemContext;
        this.entityIdType = str;
        this.entityId = str2;
        this.label = str3;
        this.primaryText = str4;
        this.secondaryText = str5;
        this.tertiaryText = str6;
        this.image = str7;
        this.contentEncoding = str8;
        this.contentSrc = str9;
        this.contentInfoInstId = str10;
        this.playMode = str11;
    }

    public /* synthetic */ LibraryContextMapping(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14);
    }

    public final int getId() {
        return this.id;
    }

    public final String getPageContext() {
        return this.pageContext;
    }

    public final String getWidgetContext() {
        return this.widgetContext;
    }

    public final String getWidgetItemContext() {
        return this.widgetItemContext;
    }

    public final String getEntityIdType() {
        return this.entityIdType;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }

    public final String getTertiaryText() {
        return this.tertiaryText;
    }

    public final String getImage() {
        return this.image;
    }

    public final String getContentEncoding() {
        return this.contentEncoding;
    }

    public final String getContentSrc() {
        return this.contentSrc;
    }

    public final String getContentInfoInstId() {
        return this.contentInfoInstId;
    }

    public final String getPlayMode() {
        return this.playMode;
    }

    public String toString() {
        return "LibraryContextMapping(id='" + this.id + "', pageContext='" + this.pageContext + "', widgetContext='" + this.widgetContext + "', widgetItemContext='" + this.widgetItemContext + "', entityIdType='" + ((Object) this.entityIdType) + "', entityId=" + ((Object) this.entityId) + ", label=" + ((Object) this.label) + ", primaryText=" + ((Object) this.primaryText) + ", secondaryText=" + ((Object) this.secondaryText) + ", tertiaryText=" + ((Object) this.tertiaryText) + ", image=" + ((Object) this.image) + ", contentEncoding=" + ((Object) this.contentEncoding) + ", contentSrc=" + ((Object) this.contentSrc) + ", contentInfoInstId=" + ((Object) this.contentInfoInstId) + ", playMode=" + ((Object) this.playMode) + ')';
    }
}
