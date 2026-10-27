package com.stingray.karaoke.car.ui.navigation;

import W5.r;
import com.stingray.drivemodedetector.R;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\n\u0010\tj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/stingray/karaoke/car/ui/navigation/StingrayKaraokeTabScreen;", "", "", "iconId", "labelId", "<init>", "(Ljava/lang/String;III)V", "I", "getIconId", "()I", "getLabelId", "Companion", "W5/r", "Homepage", "CompanionPage", "ExplorePage", "MyKaraokePage", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class StingrayKaraokeTabScreen {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ StingrayKaraokeTabScreen[] $VALUES;
    public static final r Companion;
    private final int iconId;
    private final int labelId;
    public static final StingrayKaraokeTabScreen Homepage = new StingrayKaraokeTabScreen("Homepage", 0, R.drawable.ic_home, R.string.Tab_Home);
    public static final StingrayKaraokeTabScreen CompanionPage = new StingrayKaraokeTabScreen("CompanionPage", 1, R.drawable.ic_mobile, R.string.Tab_Passenger);
    public static final StingrayKaraokeTabScreen ExplorePage = new StingrayKaraokeTabScreen("ExplorePage", 2, R.drawable.ic_magnifying_glass, R.string.Tab_Explore);
    public static final StingrayKaraokeTabScreen MyKaraokePage = new StingrayKaraokeTabScreen("MyKaraokePage", 3, R.drawable.ic_profile, R.string.Tab_MyKaraoke);

    private static final /* synthetic */ StingrayKaraokeTabScreen[] $values() {
        return new StingrayKaraokeTabScreen[]{Homepage, CompanionPage, ExplorePage, MyKaraokePage};
    }

    static {
        StingrayKaraokeTabScreen[] stingrayKaraokeTabScreenArr$values = $values();
        $VALUES = stingrayKaraokeTabScreenArr$values;
        $ENTRIES = h6.r.e(stingrayKaraokeTabScreenArr$values);
        Companion = new r();
    }

    private StingrayKaraokeTabScreen(String str, int i7, int i10, int i11) {
        this.iconId = i10;
        this.labelId = i11;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static StingrayKaraokeTabScreen valueOf(String str) {
        return (StingrayKaraokeTabScreen) Enum.valueOf(StingrayKaraokeTabScreen.class, str);
    }

    public static StingrayKaraokeTabScreen[] values() {
        return (StingrayKaraokeTabScreen[]) $VALUES.clone();
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final int getLabelId() {
        return this.labelId;
    }
}
