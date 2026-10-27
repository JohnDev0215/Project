package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListTagsForResourceResult implements Serializable {
    private String nextToken;
    private List<Tag> tags;

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public ListTagsForResourceResult withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public ListTagsForResourceResult withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListTagsForResourceResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTags() != null) {
            sb.append("tags: " + getTags() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTags() == null ? 0 : getTags().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTagsForResourceResult)) {
            return false;
        }
        ListTagsForResourceResult listTagsForResourceResult = (ListTagsForResourceResult) obj;
        if ((listTagsForResourceResult.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (listTagsForResourceResult.getTags() != null && !listTagsForResourceResult.getTags().equals(getTags())) {
            return false;
        }
        if ((listTagsForResourceResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTagsForResourceResult.getNextToken() == null || listTagsForResourceResult.getNextToken().equals(getNextToken());
    }
}
