package org.chromium.chrome.browser.privacy_sandbox;

import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class Topic {
    public final int a;
    public final int b;
    public final String c;

    public Topic(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Topic)) {
            return false;
        }
        Topic topic = (Topic) obj;
        return this.a == topic.a && this.b == topic.b;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.a), Integer.valueOf(this.b));
    }

    public final String toString() {
        return "Topic{topicId=" + this.a + ", taxonomyVersion=" + this.b + ", name=" + this.c + '}';
    }
}
