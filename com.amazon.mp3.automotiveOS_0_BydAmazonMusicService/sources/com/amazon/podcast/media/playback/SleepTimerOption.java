package com.amazon.podcast.media.playback;

import com.amazon.podcast.R$string;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'FIVE_MINUTES' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes5.dex */
public final class SleepTimerOption {
    private static final /* synthetic */ SleepTimerOption[] $VALUES;
    public static final SleepTimerOption FIFTEEN_MINUTES;
    public static final SleepTimerOption FIVE_MINUTES;
    public static final SleepTimerOption FORTY_FIVE_MINUTES;
    public static final SleepTimerOption ONE_HOUR;
    public static final SleepTimerOption TEN_MINUTES;
    public static final SleepTimerOption THIRTY_MINUTES;
    private final int resourceId;
    private final long value;

    public static SleepTimerOption valueOf(String name) {
        return (SleepTimerOption) Enum.valueOf(SleepTimerOption.class, name);
    }

    public static SleepTimerOption[] values() {
        return (SleepTimerOption[]) $VALUES.clone();
    }

    static {
        int i = R$string.podcast_sleep_timer_duration_selection_string;
        SleepTimerOption sleepTimerOption = new SleepTimerOption("FIVE_MINUTES", 0, 5L, i);
        FIVE_MINUTES = sleepTimerOption;
        SleepTimerOption sleepTimerOption2 = new SleepTimerOption("TEN_MINUTES", 1, 10L, i);
        TEN_MINUTES = sleepTimerOption2;
        SleepTimerOption sleepTimerOption3 = new SleepTimerOption("FIFTEEN_MINUTES", 2, 15L, i);
        FIFTEEN_MINUTES = sleepTimerOption3;
        SleepTimerOption sleepTimerOption4 = new SleepTimerOption("THIRTY_MINUTES", 3, 30L, i);
        THIRTY_MINUTES = sleepTimerOption4;
        SleepTimerOption sleepTimerOption5 = new SleepTimerOption("FORTY_FIVE_MINUTES", 4, 45L, i);
        FORTY_FIVE_MINUTES = sleepTimerOption5;
        SleepTimerOption sleepTimerOption6 = new SleepTimerOption("ONE_HOUR", 5, 60L, i);
        ONE_HOUR = sleepTimerOption6;
        $VALUES = new SleepTimerOption[]{sleepTimerOption, sleepTimerOption2, sleepTimerOption3, sleepTimerOption4, sleepTimerOption5, sleepTimerOption6};
    }

    private SleepTimerOption(String $enum$name, int $enum$ordinal, final long value, final int resourceId) {
        this.resourceId = resourceId;
        this.value = value;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public long getValueMs() {
        return this.value * 60 * 1000;
    }

    public int getValue() {
        return (int) this.value;
    }
}
