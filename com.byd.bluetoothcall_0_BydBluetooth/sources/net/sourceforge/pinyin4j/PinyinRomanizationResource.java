package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.c;
import com.hp.hpl.sparta.m;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class PinyinRomanizationResource {
    private c pinyinMappingDoc;

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
            setPinyinMappingDoc(m.a("", ResourceHelper.getResourceInputStream("/pinyindb/pinyin_mapping.xml")));
        } catch (ParseException e2) {
            e2.printStackTrace();
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    private void setPinyinMappingDoc(c cVar) {
        this.pinyinMappingDoc = cVar;
    }

    c getPinyinMappingDoc() {
        return this.pinyinMappingDoc;
    }
}
