package com.stingray.karaoke.scoring;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/stingray/karaoke/scoring/MicrophoneCommand;", "", "<init>", "(Ljava/lang/String;I)V", "StartReceiving", "StopReceiving", "StartPitchCorrection", "StopPitchCorrection", "StartPassthroughLiveVoice", "StopPassthroughLiveVoice", "scoring_baseEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MicrophoneCommand {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ MicrophoneCommand[] $VALUES;
    public static final MicrophoneCommand StartReceiving = new MicrophoneCommand("StartReceiving", 0);
    public static final MicrophoneCommand StopReceiving = new MicrophoneCommand("StopReceiving", 1);
    public static final MicrophoneCommand StartPitchCorrection = new MicrophoneCommand("StartPitchCorrection", 2);
    public static final MicrophoneCommand StopPitchCorrection = new MicrophoneCommand("StopPitchCorrection", 3);
    public static final MicrophoneCommand StartPassthroughLiveVoice = new MicrophoneCommand("StartPassthroughLiveVoice", 4);
    public static final MicrophoneCommand StopPassthroughLiveVoice = new MicrophoneCommand("StopPassthroughLiveVoice", 5);

    private static final /* synthetic */ MicrophoneCommand[] $values() {
        return new MicrophoneCommand[]{StartReceiving, StopReceiving, StartPitchCorrection, StopPitchCorrection, StartPassthroughLiveVoice, StopPassthroughLiveVoice};
    }

    static {
        MicrophoneCommand[] microphoneCommandArr$values = $values();
        $VALUES = microphoneCommandArr$values;
        $ENTRIES = r.e(microphoneCommandArr$values);
    }

    private MicrophoneCommand(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static MicrophoneCommand valueOf(String str) {
        return (MicrophoneCommand) Enum.valueOf(MicrophoneCommand.class, str);
    }

    public static MicrophoneCommand[] values() {
        return (MicrophoneCommand[]) $VALUES.clone();
    }
}
