package android.media.midi;

import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MidiSender {
    public abstract void onConnect(MidiReceiver midiReceiver);

    public abstract void onDisconnect(MidiReceiver midiReceiver);

    public void connect(MidiReceiver midiReceiver) {
        Objects.requireNonNull(midiReceiver, "receiver null in MidiSender.connect");
        onConnect(midiReceiver);
    }

    public void disconnect(MidiReceiver midiReceiver) {
        Objects.requireNonNull(midiReceiver, "receiver null in MidiSender.disconnect");
        onDisconnect(midiReceiver);
    }
}
