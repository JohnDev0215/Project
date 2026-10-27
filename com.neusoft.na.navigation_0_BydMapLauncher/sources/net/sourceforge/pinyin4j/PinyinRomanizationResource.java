package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Document;
import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
class PinyinRomanizationResource {
    private Document pinyinMappingDoc;

    private static class PinyinRomanizationSystemResourceHolder {
        static final PinyinRomanizationResource theInstance = new PinyinRomanizationResource();

        private PinyinRomanizationSystemResourceHolder() {
        }
    }

    private PinyinRomanizationResource() {
        initializeResource();
    }

    static PinyinRomanizationResource getInstance() {
        return PinyinRomanizationSystemResourceHolder.theInstance;
    }

    private void initializeResource() {
        try {
            setPinyinMappingDoc(Parser.parse("", ResourceHelper.getResourceInputStream("/pinyindb/pinyin_mapping.xml")));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    private void setPinyinMappingDoc(Document document) {
        this.pinyinMappingDoc = document;
    }

    Document getPinyinMappingDoc() {
        return this.pinyinMappingDoc;
    }
}
