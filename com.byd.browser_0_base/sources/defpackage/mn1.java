package defpackage;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.TagTechnology;
import java.io.IOException;

/* JADX INFO: compiled from: NfcTagHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mn1 {
    public final TagTechnology a;
    public final c b;
    public boolean c;
    public final String d;

    /* JADX INFO: compiled from: NfcTagHandler.java */
    public static class a implements c {
        public final NdefFormatable a;

        public a(NdefFormatable ndefFormatable) {
            this.a = ndefFormatable;
        }

        @Override // mn1.c
        public final void a(NdefMessage ndefMessage) throws IOException, FormatException {
            this.a.format(ndefMessage);
        }

        @Override // mn1.c
        public final boolean b() throws IOException {
            try {
                this.a.formatReadOnly(new NdefMessage(new NdefRecord((short) 0, null, null, null), new NdefRecord[0]));
                return true;
            } catch (FormatException unused) {
                return false;
            }
        }

        @Override // mn1.c
        public final boolean c() {
            return true;
        }

        @Override // mn1.c
        public final NdefMessage read() {
            return new NdefMessage(new NdefRecord((short) 0, null, null, null), new NdefRecord[0]);
        }
    }

    /* JADX INFO: compiled from: NfcTagHandler.java */
    public static class b implements c {
        public final Ndef a;

        public b(Ndef ndef) {
            this.a = ndef;
        }

        @Override // mn1.c
        public final void a(NdefMessage ndefMessage) throws IOException, FormatException {
            this.a.writeNdefMessage(ndefMessage);
        }

        @Override // mn1.c
        public final boolean b() {
            return this.a.makeReadOnly();
        }

        @Override // mn1.c
        public final boolean c() {
            return this.a.getNdefMessage() == null;
        }

        @Override // mn1.c
        public final NdefMessage read() {
            return this.a.getNdefMessage();
        }
    }

    /* JADX INFO: compiled from: NfcTagHandler.java */
    public interface c {
        void a(NdefMessage ndefMessage);

        boolean b();

        boolean c();

        NdefMessage read();
    }

    public mn1(TagTechnology tagTechnology, c cVar, byte[] bArr) {
        String string;
        this.a = tagTechnology;
        this.b = cVar;
        if (bArr.length < 0) {
            string = null;
        } else {
            StringBuilder sb = new StringBuilder(bArr.length * 3);
            for (byte b2 : bArr) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(String.format("%02x", Integer.valueOf(b2 & 255)));
            }
            string = sb.toString();
        }
        this.d = string;
    }

    public final void a() throws IOException {
        TagTechnology tagTechnology = this.a;
        if (tagTechnology.isConnected()) {
            return;
        }
        tagTechnology.connect();
        this.c = true;
    }
}
