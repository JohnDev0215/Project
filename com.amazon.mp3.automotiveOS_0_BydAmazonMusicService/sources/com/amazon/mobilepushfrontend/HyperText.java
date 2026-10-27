package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes3.dex */
public class HyperText implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.HyperText");
    private TextAttributes attributes;
    private boolean lineBreak;
    private String text;

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Boolean.valueOf(this.lineBreak), this.attributes, this.text);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HyperText)) {
            return false;
        }
        HyperText hyperText = (HyperText) obj;
        return Helper.equals(Boolean.valueOf(this.lineBreak), Boolean.valueOf(hyperText.lineBreak)) && Helper.equals(this.attributes, hyperText.attributes) && Helper.equals(this.text, hyperText.text);
    }
}
