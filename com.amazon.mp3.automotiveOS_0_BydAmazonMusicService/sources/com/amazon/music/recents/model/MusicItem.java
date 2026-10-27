package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MusicItem {

    @Nullable
    protected List<ContentEncoding> contentEncodings;
    protected String entityId;
    protected String entityIdType;

    @Nullable
    protected String image;

    @Nullable
    protected Boolean isFree;

    @Nullable
    protected Boolean isFreeOnDemandPlayable;

    @Nullable
    protected Boolean isMusicSubscription;

    @Nullable
    protected Boolean isPrime;

    @Nullable
    protected Boolean isSonicRush;

    @Nullable
    protected Boolean isSonicRushGolden;

    @Nullable
    protected Boolean isSonicRushOnDemandPlayable;
    protected Long timestamp;
    protected String title;

    public String getEntityId() {
        return this.entityId;
    }

    public String getEntityIdType() {
        return this.entityIdType;
    }

    public String getTitle() {
        return this.title;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    @Nullable
    public Boolean isPrime() {
        return this.isPrime;
    }

    @Nullable
    public Boolean isMusicSubscription() {
        return this.isMusicSubscription;
    }

    @Nullable
    public Boolean isFree() {
        return this.isFree;
    }

    @Nullable
    public Boolean isFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    @Nullable
    public Boolean isSonicRush() {
        return this.isSonicRush;
    }

    @Nullable
    public Boolean isSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    @Nullable
    public Boolean isSonicRushGolden() {
        return this.isSonicRushGolden;
    }

    @Nullable
    public String getImage() {
        return this.image;
    }

    @Nullable
    public List<ContentEncoding> getContentEncodings() {
        return this.contentEncodings;
    }

    protected MusicItem() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static abstract class Builder<T extends MusicItem, B extends Builder<T, B>> {
        private long initBits = 7;
        protected T object = (T) createObject();
        protected B thisObject = (B) thisObject();

        protected abstract T createObject();

        protected abstract B thisObject();

        public B entityId(String str) {
            this.object.entityId = (String) MusicItem.requireNonNull(str, ContextMappingConstants.ENTITY_ID);
            this.initBits &= -2;
            return this.thisObject;
        }

        public final B title(String str) {
            this.object.title = (String) MusicItem.requireNonNull(str, "title");
            this.initBits &= -3;
            return this.thisObject;
        }

        public final B timestamp(Long l) {
            this.object.timestamp = (Long) MusicItem.requireNonNull(l, TetheredMessageKey.timestamp);
            this.initBits &= -5;
            return this.thisObject;
        }

        public final B isPrime(@Nullable Boolean bool) {
            this.object.isPrime = bool;
            return this.thisObject;
        }

        public final B isMusicSubscription(@Nullable Boolean bool) {
            this.object.isMusicSubscription = bool;
            return this.thisObject;
        }

        public final B isFree(@Nullable Boolean bool) {
            this.object.isFree = bool;
            return this.thisObject;
        }

        public final B entityIdType(String str) {
            this.object.entityIdType = str;
            return this.thisObject;
        }

        public final B isFreeOnDemandPlayable(@Nullable Boolean bool) {
            this.object.isFreeOnDemandPlayable = bool;
            return this.thisObject;
        }

        public final B isSonicRush(@Nullable Boolean bool) {
            this.object.isSonicRush = bool;
            return this.thisObject;
        }

        public final B isSonicRushOnDemandPlayable(@Nullable Boolean bool) {
            this.object.isSonicRushOnDemandPlayable = bool;
            return this.thisObject;
        }

        public final B isSonicRushGolden(@Nullable Boolean bool) {
            this.object.isSonicRushGolden = bool;
            return this.thisObject;
        }

        public final B contentEncodings(@Nullable List<ContentEncoding> list) {
            this.object.contentEncodings = list;
            return this.thisObject;
        }

        public final B image(@Nullable String str) {
            this.object.image = str;
            return this.thisObject;
        }

        public T build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return this.object;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.ENTITY_ID);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("title");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add(TetheredMessageKey.timestamp);
            }
            return "Cannot build " + this.object + ", some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
