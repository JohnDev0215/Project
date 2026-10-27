package n6;

import com.stingray.karaoke.scoring.MicrophoneCommand;

/* JADX INFO: renamed from: n6.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC1982o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f20123a;

    static {
        int[] iArr = new int[MicrophoneCommand.values().length];
        try {
            iArr[MicrophoneCommand.StartReceiving.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[MicrophoneCommand.StopReceiving.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[MicrophoneCommand.StartPitchCorrection.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[MicrophoneCommand.StopPitchCorrection.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[MicrophoneCommand.StartPassthroughLiveVoice.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[MicrophoneCommand.StopPassthroughLiveVoice.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        f20123a = iArr;
    }
}
