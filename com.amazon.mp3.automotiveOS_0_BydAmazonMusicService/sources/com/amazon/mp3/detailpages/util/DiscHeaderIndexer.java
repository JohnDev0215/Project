package com.amazon.mp3.detailpages.util;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Track;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;

/* JADX INFO: compiled from: DiscHeaderIndexer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0002\u0014\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0005J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer;", "", "()V", "discs", "Ljava/util/HashMap;", "", "Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer$Disc;", "Lkotlin/collections/HashMap;", "addDisc", "", "discNumber", "disc", "getDisc", "getDiscForMusicTrack", "track", "Lcom/amazon/mp3/library/item/MusicTrack;", "getDiscForTrackEntity", "Lcom/amazon/musicensembleservice/brush/Track;", "hasDiscs", "", "Companion", MqttDisconnect.KEY, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DiscHeaderIndexer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HashMap<Integer, Disc> discs;

    public /* synthetic */ DiscHeaderIndexer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private DiscHeaderIndexer() {
        this.discs = new HashMap<>();
    }

    /* JADX INFO: compiled from: DiscHeaderIndexer.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer$Companion;", "", "()V", "initializeIndexerWithAbstractItems", "Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer;", ContextMappingConstants.ITEMS, "", "Lcom/amazon/mp3/library/item/AbstractItem;", "initializeIndexerWithEntities", "entities", "", "Lcom/amazon/musicensembleservice/brush/Entity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DiscHeaderIndexer initializeIndexerWithEntities(List<? extends Entity> entities) {
            Intrinsics.checkNotNullParameter(entities, "entities");
            DiscHeaderIndexer discHeaderIndexer = new DiscHeaderIndexer(null);
            String string = AmazonApplication.getContext().getString(R.string.dmusic_library_disc_num_header);
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…_library_disc_num_header)");
            int i = -1;
            for (Entity entity : entities) {
                if (entity instanceof Track) {
                    Track track = (Track) entity;
                    int iLongValue = (int) track.getDiscNumber().longValue();
                    if (iLongValue != i) {
                        int iLongValue2 = (int) track.getTrackNumber().longValue();
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(iLongValue)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                        String upperCase = str.toUpperCase();
                        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                        discHeaderIndexer.addDisc(iLongValue, new Disc(iLongValue2, iLongValue, upperCase));
                        i = iLongValue;
                    }
                }
            }
            return discHeaderIndexer;
        }

        public final DiscHeaderIndexer initializeIndexerWithAbstractItems(Collection<? extends AbstractItem> items) {
            MusicTrack musicTrack;
            int discNumber;
            Intrinsics.checkNotNullParameter(items, "items");
            DiscHeaderIndexer discHeaderIndexer = new DiscHeaderIndexer(null);
            String string = AmazonApplication.getContext().getString(R.string.dmusic_library_disc_num_header);
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…_library_disc_num_header)");
            int i = -1;
            for (AbstractItem abstractItem : items) {
                if ((abstractItem instanceof MusicTrack) && (discNumber = (musicTrack = (MusicTrack) abstractItem).getDiscNumber()) != i) {
                    int trackNum = (int) musicTrack.getTrackNum();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(discNumber)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                    String upperCase = str.toUpperCase();
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                    discHeaderIndexer.addDisc(discNumber, new Disc(trackNum, discNumber, upperCase));
                    i = discNumber;
                }
            }
            return discHeaderIndexer;
        }
    }

    /* JADX INFO: compiled from: DiscHeaderIndexer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer$Disc;", "", "", "toString", "", "hashCode", "other", "", "equals", "startPosition", "I", "getStartPosition", "()I", "discNumber", "getDiscNumber", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "<init>", "(IILjava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class Disc {
        private final int discNumber;
        private final int startPosition;
        private final String title;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Disc)) {
                return false;
            }
            Disc disc = (Disc) other;
            return this.startPosition == disc.startPosition && this.discNumber == disc.discNumber && Intrinsics.areEqual(this.title, disc.title);
        }

        public int hashCode() {
            return (((Integer.hashCode(this.startPosition) * 31) + Integer.hashCode(this.discNumber)) * 31) + this.title.hashCode();
        }

        public String toString() {
            return "Disc(startPosition=" + this.startPosition + ", discNumber=" + this.discNumber + ", title=" + this.title + ')';
        }

        public Disc(int i, int i2, String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.startPosition = i;
            this.discNumber = i2;
            this.title = title;
        }

        public final int getDiscNumber() {
            return this.discNumber;
        }

        public final int getStartPosition() {
            return this.startPosition;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    public final boolean hasDiscs() {
        return this.discs.size() > 1;
    }

    public final Disc getDisc(int discNumber) {
        return this.discs.get(Integer.valueOf(discNumber));
    }

    public final Disc getDiscForTrackEntity(Track track) {
        Long trackNumber;
        Intrinsics.checkNotNullParameter(track, "track");
        if (hasDiscs() && (trackNumber = track.getTrackNumber()) != null) {
            long jLongValue = trackNumber.longValue();
            for (Disc disc : this.discs.values()) {
                if (((int) jLongValue) == disc.getStartPosition()) {
                    return disc;
                }
            }
        }
        return null;
    }

    public final Disc getDiscForMusicTrack(MusicTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        if (!hasDiscs()) {
            return null;
        }
        for (Disc disc : this.discs.values()) {
            if (((int) track.getTrackNum()) == disc.getStartPosition() && track.getDiscNumber() == disc.getDiscNumber()) {
                return disc;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addDisc(int discNumber, Disc disc) {
        this.discs.put(Integer.valueOf(discNumber), disc);
    }
}
