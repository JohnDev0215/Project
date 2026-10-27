package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes3.dex */
public class TextAttributes implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.TextAttributes");
    private boolean allowTruncation;
    private int color;
    private String linkUrl;
    private String size;
    private String style;

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Boolean.valueOf(this.allowTruncation), this.linkUrl, Integer.valueOf(this.color), this.style, this.size);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TextAttributes)) {
            return false;
        }
        TextAttributes textAttributes = (TextAttributes) obj;
        return Helper.equals(Boolean.valueOf(this.allowTruncation), Boolean.valueOf(textAttributes.allowTruncation)) && Helper.equals(this.linkUrl, textAttributes.linkUrl) && Helper.equals(Integer.valueOf(this.color), Integer.valueOf(textAttributes.color)) && Helper.equals(this.style, textAttributes.style) && Helper.equals(this.size, textAttributes.size);
    }
}
