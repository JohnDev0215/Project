package com.stingray.karaoke.car.deeplink;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b ¨\u0006!"}, d2 = {"com/stingray/karaoke/car/deeplink/DeepLinkManager$DeepLinkType", "", "Lcom/stingray/karaoke/car/deeplink/DeepLinkManager$DeepLinkType;", "", "linkName", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "Ljava/lang/String;", "getLinkName", "()Ljava/lang/String;", "Subscription", "SongDetails", "ArtistSongs", "PartyMix", "SongsGenre", "Era", "Language", "ArtistsGenre", "CommandBack", "CommandPlay", "CommandPause", "CommandSkip", "CommandPrevious", "CommandReplay", "CommandLoopOne", "CommandLoopAll", "CommandLoopOff", "CommandLeadVocalOn", "CommandLeadVocalOff", "CommandQuit", "CommandSearch", "CommandSearchAndPlay", "CommandHistory", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DeepLinkManager$DeepLinkType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ DeepLinkManager$DeepLinkType[] $VALUES;
    private final String linkName;
    public static final DeepLinkManager$DeepLinkType Subscription = new DeepLinkManager$DeepLinkType("Subscription", 0, "subscription");
    public static final DeepLinkManager$DeepLinkType SongDetails = new DeepLinkManager$DeepLinkType("SongDetails", 1, "song");
    public static final DeepLinkManager$DeepLinkType ArtistSongs = new DeepLinkManager$DeepLinkType("ArtistSongs", 2, "artist");
    public static final DeepLinkManager$DeepLinkType PartyMix = new DeepLinkManager$DeepLinkType("PartyMix", 3, "party_mix");
    public static final DeepLinkManager$DeepLinkType SongsGenre = new DeepLinkManager$DeepLinkType("SongsGenre", 4, "genres");
    public static final DeepLinkManager$DeepLinkType Era = new DeepLinkManager$DeepLinkType("Era", 5, "eras");
    public static final DeepLinkManager$DeepLinkType Language = new DeepLinkManager$DeepLinkType("Language", 6, "languages");
    public static final DeepLinkManager$DeepLinkType ArtistsGenre = new DeepLinkManager$DeepLinkType("ArtistsGenre", 7, "artists");
    public static final DeepLinkManager$DeepLinkType CommandBack = new DeepLinkManager$DeepLinkType("CommandBack", 8, "back");
    public static final DeepLinkManager$DeepLinkType CommandPlay = new DeepLinkManager$DeepLinkType("CommandPlay", 9, "play");
    public static final DeepLinkManager$DeepLinkType CommandPause = new DeepLinkManager$DeepLinkType("CommandPause", 10, "pause");
    public static final DeepLinkManager$DeepLinkType CommandSkip = new DeepLinkManager$DeepLinkType("CommandSkip", 11, "skip");
    public static final DeepLinkManager$DeepLinkType CommandPrevious = new DeepLinkManager$DeepLinkType("CommandPrevious", 12, "previous");
    public static final DeepLinkManager$DeepLinkType CommandReplay = new DeepLinkManager$DeepLinkType("CommandReplay", 13, "replay");
    public static final DeepLinkManager$DeepLinkType CommandLoopOne = new DeepLinkManager$DeepLinkType("CommandLoopOne", 14, "loop_one");
    public static final DeepLinkManager$DeepLinkType CommandLoopAll = new DeepLinkManager$DeepLinkType("CommandLoopAll", 15, "loop_all");
    public static final DeepLinkManager$DeepLinkType CommandLoopOff = new DeepLinkManager$DeepLinkType("CommandLoopOff", 16, "loop_off");
    public static final DeepLinkManager$DeepLinkType CommandLeadVocalOn = new DeepLinkManager$DeepLinkType("CommandLeadVocalOn", 17, "lead_vocal_on");
    public static final DeepLinkManager$DeepLinkType CommandLeadVocalOff = new DeepLinkManager$DeepLinkType("CommandLeadVocalOff", 18, "lead_vocal_off");
    public static final DeepLinkManager$DeepLinkType CommandQuit = new DeepLinkManager$DeepLinkType("CommandQuit", 19, "quit");
    public static final DeepLinkManager$DeepLinkType CommandSearch = new DeepLinkManager$DeepLinkType("CommandSearch", 20, "search");
    public static final DeepLinkManager$DeepLinkType CommandSearchAndPlay = new DeepLinkManager$DeepLinkType("CommandSearchAndPlay", 21, "search_and_play");
    public static final DeepLinkManager$DeepLinkType CommandHistory = new DeepLinkManager$DeepLinkType("CommandHistory", 22, "history");

    private static final /* synthetic */ DeepLinkManager$DeepLinkType[] $values() {
        return new DeepLinkManager$DeepLinkType[]{Subscription, SongDetails, ArtistSongs, PartyMix, SongsGenre, Era, Language, ArtistsGenre, CommandBack, CommandPlay, CommandPause, CommandSkip, CommandPrevious, CommandReplay, CommandLoopOne, CommandLoopAll, CommandLoopOff, CommandLeadVocalOn, CommandLeadVocalOff, CommandQuit, CommandSearch, CommandSearchAndPlay, CommandHistory};
    }

    static {
        DeepLinkManager$DeepLinkType[] deepLinkManager$DeepLinkTypeArr$values = $values();
        $VALUES = deepLinkManager$DeepLinkTypeArr$values;
        $ENTRIES = r.e(deepLinkManager$DeepLinkTypeArr$values);
    }

    private DeepLinkManager$DeepLinkType(String str, int i7, String str2) {
        this.linkName = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static DeepLinkManager$DeepLinkType valueOf(String str) {
        return (DeepLinkManager$DeepLinkType) Enum.valueOf(DeepLinkManager$DeepLinkType.class, str);
    }

    public static DeepLinkManager$DeepLinkType[] values() {
        return (DeepLinkManager$DeepLinkType[]) $VALUES.clone();
    }

    public final String getLinkName() {
        return this.linkName;
    }
}
