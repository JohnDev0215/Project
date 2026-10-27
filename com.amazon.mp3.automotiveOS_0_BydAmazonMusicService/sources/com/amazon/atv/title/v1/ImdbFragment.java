package com.amazon.atv.title.v1;

import com.amazon.atv.title.v1.Fragment;
import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ImdbFragment extends Fragment {

    @NotThreadSafe
    public static abstract class Builder extends Fragment.Builder {
    }

    protected ImdbFragment(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.title.v1.Fragment
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImdbFragment)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.title.v1.Fragment
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.title.v1.Fragment
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<ImdbFragment> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.IMDBFRAGMENT_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
