package com.byd.auto_emanual;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.byd.auto_emanual.image.KImageView;
import com.byd.auto_emanual.util.ViewUtil;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class ContentItem {
    private ContentItemType contentItemType;
    private String country;
    private String coverPath;
    private Uri coverUri;
    private String footer;
    private String header;
    private String language;
    private String managerId;
    private String nameIndex;
    private String path;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String resourceCode;
    private String resourceLanguage;
    private String serverFilepath;
    private String type;

    public enum ContentItemType {
        VIDEO,
        IMAGE,
        ZIP,
        HTML
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Uri getCoverUri() {
        return this.coverUri;
    }

    public void setCoverUri(Uri coverUri) {
        this.coverUri = coverUri;
    }

    public String getCoverPath() {
        return this.coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
        setCoverUri(Uri.fromFile(new File(getCoverPath())));
    }

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ContentItemType getContentItemType() {
        ContentItemType contentItemType = this.contentItemType;
        if (contentItemType != null) {
            return contentItemType;
        }
        if ("1".equals(this.type)) {
            setContentItemType(ContentItemType.VIDEO);
        } else if (Parameters.CONTENT_ITEM_FOOTER.equals(this.type)) {
            setContentItemType(ContentItemType.IMAGE);
        }
        return this.contentItemType;
    }

    public void setContentItemType(ContentItemType contentItemType) {
        this.contentItemType = contentItemType;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
        if ("1".equals(type)) {
            setContentItemType(ContentItemType.VIDEO);
        } else if (Parameters.CONTENT_ITEM_FOOTER.equals(type)) {
            setContentItemType(ContentItemType.IMAGE);
        }
    }

    public String getManagerId() {
        return this.managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getNameIndex() {
        return this.nameIndex;
    }

    public void setNameIndex(String nameIndex) {
        this.nameIndex = nameIndex;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getResourceLanguage() {
        return this.resourceLanguage;
    }

    public void setResourceLanguage(String resourceLanguage) {
        this.resourceLanguage = resourceLanguage;
    }

    public String getServerFilepath() {
        return this.serverFilepath;
    }

    public void setServerFilepath(String serverFilepath) {
        this.serverFilepath = serverFilepath;
    }

    public String getReserve1() {
        return this.reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return this.reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return getResourceCode();
    }

    public void setReserve3(String reserve3) {
        String str = this.resourceCode;
        if (str != null) {
            this.reserve3 = str;
        }
    }

    public String getResourceCode() {
        return this.resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
        this.reserve3 = resourceCode;
    }

    public static void bindHolderItem(TextView header, KImageView cover, TextView footer, TextView path, ImageView playLogo, ContentItem itemData) {
        header.setText(itemData.getHeader().replace("\\n", "\n"));
        if (ViewUtil.isRtl(header.getContext().getResources().getConfiguration().getLayoutDirection())) {
            header.setGravity(GravityCompat.END);
        } else {
            header.setGravity(GravityCompat.START);
        }
        cover.setImagePath(itemData.getCoverPath());
        cover.setErrorResId(R.drawable.net_error_video);
        footer.setText(itemData.getFooter());
        path.setText(itemData.getPath());
        path.setTag(itemData.getContentItemType());
        if (!itemData.getContentItemType().equals(ContentItemType.VIDEO) || playLogo == null) {
            playLogo.setVisibility(8);
        } else {
            playLogo.setVisibility(0);
        }
    }
}
