package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: SocialProfileImageUploadFormQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u0017\u0018\u0019\u001aB\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0016¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$Data;", "", "other", "", "equals", "", "hashCode", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "<init>", "()V", "Companion", "Data", "FormField", "ProfileImageUploadForm", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class SocialProfileImageUploadFormQuery implements Query<Data> {
    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query SocialProfileImageUploadForm { profileImageUploadForm { url formFields { key value } fileFieldName } }";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "1750a83b5c731caa304dd17fce9a84fe2dddfdaa42a17f4272ef054ef7daf0c5";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "SocialProfileImageUploadForm";
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
    }

    public boolean equals(Object other) {
        return other != null && Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(other.getClass()), Reflection.getOrCreateKotlinClass(SocialProfileImageUploadFormQuery.class));
    }

    public int hashCode() {
        return Reflection.getOrCreateKotlinClass(SocialProfileImageUploadFormQuery.class).hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.SocialProfileImageUploadFormQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("profileImageUploadForm");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public SocialProfileImageUploadFormQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                SocialProfileImageUploadFormQuery.ProfileImageUploadForm profileImageUploadForm = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    profileImageUploadForm = (SocialProfileImageUploadFormQuery.ProfileImageUploadForm) Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfileImageUploadFormQuery_ResponseAdapter$ProfileImageUploadForm.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                }
                return new SocialProfileImageUploadFormQuery.Data(profileImageUploadForm);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, SocialProfileImageUploadFormQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("profileImageUploadForm");
                Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfileImageUploadFormQuery_ResponseAdapter$ProfileImageUploadForm.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getProfileImageUploadForm());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: SocialProfileImageUploadFormQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$ProfileImageUploadForm;", "profileImageUploadForm", "Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$ProfileImageUploadForm;", "getProfileImageUploadForm", "()Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$ProfileImageUploadForm;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$ProfileImageUploadForm;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final ProfileImageUploadForm profileImageUploadForm;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.profileImageUploadForm, ((Data) other).profileImageUploadForm);
        }

        public int hashCode() {
            ProfileImageUploadForm profileImageUploadForm = this.profileImageUploadForm;
            if (profileImageUploadForm == null) {
                return 0;
            }
            return profileImageUploadForm.hashCode();
        }

        public String toString() {
            return "Data(profileImageUploadForm=" + this.profileImageUploadForm + ')';
        }

        public Data(ProfileImageUploadForm profileImageUploadForm) {
            this.profileImageUploadForm = profileImageUploadForm;
        }

        public final ProfileImageUploadForm getProfileImageUploadForm() {
            return this.profileImageUploadForm;
        }
    }

    /* JADX INFO: compiled from: SocialProfileImageUploadFormQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR!\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$ProfileImageUploadForm;", "", "", "toString", "", "hashCode", "other", "", "equals", ImagesContract.URL, "Ljava/lang/String;", "getUrl", "()Ljava/lang/String;", "", "Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$FormField;", "formFields", "Ljava/util/List;", "getFormFields", "()Ljava/util/List;", "fileFieldName", "getFileFieldName", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class ProfileImageUploadForm {
        private final String fileFieldName;
        private final List<FormField> formFields;
        private final String url;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProfileImageUploadForm)) {
                return false;
            }
            ProfileImageUploadForm profileImageUploadForm = (ProfileImageUploadForm) other;
            return Intrinsics.areEqual(this.url, profileImageUploadForm.url) && Intrinsics.areEqual(this.formFields, profileImageUploadForm.formFields) && Intrinsics.areEqual(this.fileFieldName, profileImageUploadForm.fileFieldName);
        }

        public int hashCode() {
            String str = this.url;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            List<FormField> list = this.formFields;
            int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
            String str2 = this.fileFieldName;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "ProfileImageUploadForm(url=" + this.url + ", formFields=" + this.formFields + ", fileFieldName=" + this.fileFieldName + ')';
        }

        public ProfileImageUploadForm(String str, List<FormField> list, String str2) {
            this.url = str;
            this.formFields = list;
            this.fileFieldName = str2;
        }

        public final String getUrl() {
            return this.url;
        }

        public final List<FormField> getFormFields() {
            return this.formFields;
        }

        public final String getFileFieldName() {
            return this.fileFieldName;
        }
    }

    /* JADX INFO: compiled from: SocialProfileImageUploadFormQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$FormField;", "", "", "toString", "", "hashCode", "other", "", "equals", "key", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "value", "getValue", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FormField {
        private final String key;
        private final String value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FormField)) {
                return false;
            }
            FormField formField = (FormField) other;
            return Intrinsics.areEqual(this.key, formField.key) && Intrinsics.areEqual(this.value, formField.value);
        }

        public int hashCode() {
            String str = this.key;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.value;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "FormField(key=" + this.key + ", value=" + this.value + ')';
        }

        public FormField(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public final String getKey() {
            return this.key;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
