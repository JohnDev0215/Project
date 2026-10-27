package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentUrl {
    private final boolean attachedContent;
    private final String contentId;
    private final String url;

    public ContentUrl(String str) {
        Preconditions.notNull(str, "url == null");
        this.url = str;
        boolean zEqualsIgnoreCase = "cid".equalsIgnoreCase(parseScheme(str));
        this.attachedContent = zEqualsIgnoreCase;
        if (zEqualsIgnoreCase) {
            String contentId = parseContentId(str);
            this.contentId = contentId;
            if (contentId == null || contentId.isEmpty()) {
                throw new IllegalArgumentException("Invalid url " + str);
            }
            return;
        }
        this.contentId = null;
    }

    private String parseScheme(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            return null;
        }
        return str.substring(0, iIndexOf).trim();
    }

    private String parseContentId(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            return null;
        }
        return str.substring(iIndexOf + 1).trim();
    }

    public boolean isAttachedContent() {
        return this.attachedContent;
    }

    public String getContentId() {
        return this.contentId;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContentUrl.class != obj.getClass()) {
            return false;
        }
        ContentUrl contentUrl = (ContentUrl) obj;
        if (this.attachedContent != contentUrl.attachedContent || !this.url.equals(contentUrl.url)) {
            return false;
        }
        String str = this.contentId;
        String str2 = contentUrl.contentId;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        int iHashCode = this.url.hashCode() * 31;
        String str = this.contentId;
        return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + (this.attachedContent ? 1 : 0);
    }

    public String toString() {
        return "ContentUrl{url='" + this.url + "', contentId='" + this.contentId + "', attachedContent=" + this.attachedContent + '}';
    }
}
