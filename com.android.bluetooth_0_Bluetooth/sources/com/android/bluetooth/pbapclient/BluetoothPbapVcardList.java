package com.android.bluetooth.pbapclient;

import android.accounts.Account;
import com.android.vcard.VCardConfig;
import com.android.vcard.VCardEntry;
import com.android.vcard.VCardEntryConstructor;
import com.android.vcard.VCardEntryCounter;
import com.android.vcard.VCardEntryHandler;
import com.android.vcard.VCardParser;
import com.android.vcard.VCardParser_V21;
import com.android.vcard.VCardParser_V30;
import com.android.vcard.exception.VCardException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class BluetoothPbapVcardList {
    private final Account mAccount;
    private final ArrayList<VCardEntry> mCards = new ArrayList<>();

    class CardEntryHandler implements VCardEntryHandler {
        CardEntryHandler() {
        }

        @Override // com.android.vcard.VCardEntryHandler
        public void onStart() {
        }

        @Override // com.android.vcard.VCardEntryHandler
        public void onEntryCreated(VCardEntry entry) {
            BluetoothPbapVcardList.this.mCards.add(entry);
        }

        @Override // com.android.vcard.VCardEntryHandler
        public void onEnd() {
        }
    }

    BluetoothPbapVcardList(Account account, InputStream in, byte format) throws IOException {
        this.mAccount = account;
        parse(in, format);
    }

    private void parse(InputStream in, byte format) throws IOException {
        VCardParser parser;
        if (format == 1) {
            parser = new VCardParser_V30();
        } else {
            parser = new VCardParser_V21();
        }
        VCardEntryConstructor constructor = new VCardEntryConstructor(VCardConfig.VCARD_TYPE_V21_GENERIC, this.mAccount);
        VCardEntryCounter counter = new VCardEntryCounter();
        CardEntryHandler handler = new CardEntryHandler();
        constructor.addEntryHandler(handler);
        parser.addInterpreter(constructor);
        parser.addInterpreter(counter);
        try {
            parser.parse(in);
        } catch (VCardException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return this.mCards.size();
    }

    public ArrayList<VCardEntry> getList() {
        return this.mCards;
    }

    public VCardEntry getFirst() {
        return this.mCards.get(0);
    }
}
