package p;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class nx2 extends j52 {
    public final /* synthetic */ int b;
    public final /* synthetic */ i52 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ nx2(i52 i52Var, int i, int i2) {
        super(i);
        this.b = i2;
        this.c = i52Var;
    }

    @Override // p.j52
    public final void a(androidx.sqlite.db.framework.b bVar) {
        switch (this.b) {
            case 0:
                bVar.w("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.w("CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
                bVar.w("CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
                bVar.w("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT NOT NULL, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `last_enqueue_time` INTEGER NOT NULL DEFAULT -1, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `period_count` INTEGER NOT NULL DEFAULT 0, `generation` INTEGER NOT NULL DEFAULT 0, `next_schedule_time_override` INTEGER NOT NULL DEFAULT 9223372036854775807, `next_schedule_time_override_generation` INTEGER NOT NULL DEFAULT 0, `stop_reason` INTEGER NOT NULL DEFAULT -256, `required_network_type` INTEGER NOT NULL, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB NOT NULL, PRIMARY KEY(`id`))");
                bVar.w("CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
                bVar.w("CREATE INDEX IF NOT EXISTS `index_WorkSpec_last_enqueue_time` ON `WorkSpec` (`last_enqueue_time`)");
                bVar.w("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.w("CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
                bVar.w("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `generation` INTEGER NOT NULL DEFAULT 0, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`, `generation`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.w("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.w("CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
                bVar.w("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.w("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
                bVar.w("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                bVar.w("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7d73d21f1bd82c9e5268b6dcf9fde2cb')");
                break;
            default:
                bVar.w("CREATE TABLE IF NOT EXISTS `Events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `authenticated` INTEGER, `eventName` TEXT, `sequenceId` BLOB, `sequenceNumber` INTEGER NOT NULL, `fragments` BLOB, `owner` TEXT, `deviceId` TEXT)");
                bVar.w("CREATE INDEX IF NOT EXISTS `index_Events_eventName` ON `Events` (`eventName`)");
                bVar.w("CREATE TABLE IF NOT EXISTS `EventSequenceNumbers` (`eventName` TEXT NOT NULL, `sequenceId` BLOB NOT NULL, `sequenceNumberNext` INTEGER NOT NULL, PRIMARY KEY(`eventName`, `sequenceId`))");
                bVar.w("CREATE TABLE IF NOT EXISTS `RateLimitedEvents` (`eventName` TEXT NOT NULL, `count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`eventName`))");
                bVar.w("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                bVar.w("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c6ffea087c1eb57023f5bbcc2c41e2ce')");
                break;
        }
    }

    @Override // p.j52
    public final k52 h(androidx.sqlite.db.framework.b bVar) {
        switch (this.b) {
            case 0:
                HashMap map = new HashMap(2);
                map.put("work_spec_id", new ri2("work_spec_id", "TEXT", true, 1, null, 1));
                map.put("prerequisite_id", new ri2("prerequisite_id", "TEXT", true, 2, null, 1));
                HashSet hashSet = new HashSet(2);
                hashSet.add(new si2("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                hashSet.add(new si2("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("prerequisite_id"), Arrays.asList("id")));
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new ui2("index_Dependency_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
                hashSet2.add(new ui2("index_Dependency_prerequisite_id", false, Arrays.asList("prerequisite_id"), Arrays.asList("ASC")));
                vi2 vi2Var = new vi2("Dependency", map, hashSet, hashSet2);
                vi2 vi2VarA = vi2.a(bVar, "Dependency");
                if (vi2Var.equals(vi2VarA)) {
                    HashMap map2 = new HashMap(30);
                    map2.put("id", new ri2("id", "TEXT", true, 1, null, 1));
                    map2.put("state", new ri2("state", "INTEGER", true, 0, null, 1));
                    map2.put("worker_class_name", new ri2("worker_class_name", "TEXT", true, 0, null, 1));
                    map2.put("input_merger_class_name", new ri2("input_merger_class_name", "TEXT", true, 0, null, 1));
                    map2.put("input", new ri2("input", "BLOB", true, 0, null, 1));
                    map2.put("output", new ri2("output", "BLOB", true, 0, null, 1));
                    map2.put("initial_delay", new ri2("initial_delay", "INTEGER", true, 0, null, 1));
                    map2.put("interval_duration", new ri2("interval_duration", "INTEGER", true, 0, null, 1));
                    map2.put("flex_duration", new ri2("flex_duration", "INTEGER", true, 0, null, 1));
                    map2.put("run_attempt_count", new ri2("run_attempt_count", "INTEGER", true, 0, null, 1));
                    map2.put("backoff_policy", new ri2("backoff_policy", "INTEGER", true, 0, null, 1));
                    map2.put("backoff_delay_duration", new ri2("backoff_delay_duration", "INTEGER", true, 0, null, 1));
                    map2.put("last_enqueue_time", new ri2("last_enqueue_time", "INTEGER", true, 0, "-1", 1));
                    map2.put("minimum_retention_duration", new ri2("minimum_retention_duration", "INTEGER", true, 0, null, 1));
                    map2.put("schedule_requested_at", new ri2("schedule_requested_at", "INTEGER", true, 0, null, 1));
                    map2.put("run_in_foreground", new ri2("run_in_foreground", "INTEGER", true, 0, null, 1));
                    map2.put("out_of_quota_policy", new ri2("out_of_quota_policy", "INTEGER", true, 0, null, 1));
                    map2.put("period_count", new ri2("period_count", "INTEGER", true, 0, "0", 1));
                    map2.put("generation", new ri2("generation", "INTEGER", true, 0, "0", 1));
                    map2.put("next_schedule_time_override", new ri2("next_schedule_time_override", "INTEGER", true, 0, "9223372036854775807", 1));
                    map2.put("next_schedule_time_override_generation", new ri2("next_schedule_time_override_generation", "INTEGER", true, 0, "0", 1));
                    map2.put("stop_reason", new ri2("stop_reason", "INTEGER", true, 0, "-256", 1));
                    map2.put("required_network_type", new ri2("required_network_type", "INTEGER", true, 0, null, 1));
                    map2.put("requires_charging", new ri2("requires_charging", "INTEGER", true, 0, null, 1));
                    map2.put("requires_device_idle", new ri2("requires_device_idle", "INTEGER", true, 0, null, 1));
                    map2.put("requires_battery_not_low", new ri2("requires_battery_not_low", "INTEGER", true, 0, null, 1));
                    map2.put("requires_storage_not_low", new ri2("requires_storage_not_low", "INTEGER", true, 0, null, 1));
                    map2.put("trigger_content_update_delay", new ri2("trigger_content_update_delay", "INTEGER", true, 0, null, 1));
                    map2.put("trigger_max_content_delay", new ri2("trigger_max_content_delay", "INTEGER", true, 0, null, 1));
                    map2.put("content_uri_triggers", new ri2("content_uri_triggers", "BLOB", true, 0, null, 1));
                    HashSet hashSet3 = new HashSet(0);
                    HashSet hashSet4 = new HashSet(2);
                    hashSet4.add(new ui2("index_WorkSpec_schedule_requested_at", false, Arrays.asList("schedule_requested_at"), Arrays.asList("ASC")));
                    hashSet4.add(new ui2("index_WorkSpec_last_enqueue_time", false, Arrays.asList("last_enqueue_time"), Arrays.asList("ASC")));
                    vi2 vi2Var2 = new vi2("WorkSpec", map2, hashSet3, hashSet4);
                    vi2 vi2VarA2 = vi2.a(bVar, "WorkSpec");
                    if (vi2Var2.equals(vi2VarA2)) {
                        HashMap map3 = new HashMap(2);
                        map3.put("tag", new ri2("tag", "TEXT", true, 1, null, 1));
                        map3.put("work_spec_id", new ri2("work_spec_id", "TEXT", true, 2, null, 1));
                        HashSet hashSet5 = new HashSet(1);
                        hashSet5.add(new si2("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                        HashSet hashSet6 = new HashSet(1);
                        hashSet6.add(new ui2("index_WorkTag_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
                        vi2 vi2Var3 = new vi2("WorkTag", map3, hashSet5, hashSet6);
                        vi2 vi2VarA3 = vi2.a(bVar, "WorkTag");
                        if (vi2Var3.equals(vi2VarA3)) {
                            HashMap map4 = new HashMap(3);
                            map4.put("work_spec_id", new ri2("work_spec_id", "TEXT", true, 1, null, 1));
                            map4.put("generation", new ri2("generation", "INTEGER", true, 2, "0", 1));
                            map4.put("system_id", new ri2("system_id", "INTEGER", true, 0, null, 1));
                            HashSet hashSet7 = new HashSet(1);
                            hashSet7.add(new si2("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                            vi2 vi2Var4 = new vi2("SystemIdInfo", map4, hashSet7, new HashSet(0));
                            vi2 vi2VarA4 = vi2.a(bVar, "SystemIdInfo");
                            if (vi2Var4.equals(vi2VarA4)) {
                                HashMap map5 = new HashMap(2);
                                map5.put("name", new ri2("name", "TEXT", true, 1, null, 1));
                                map5.put("work_spec_id", new ri2("work_spec_id", "TEXT", true, 2, null, 1));
                                HashSet hashSet8 = new HashSet(1);
                                hashSet8.add(new si2("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                                HashSet hashSet9 = new HashSet(1);
                                hashSet9.add(new ui2("index_WorkName_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
                                vi2 vi2Var5 = new vi2("WorkName", map5, hashSet8, hashSet9);
                                vi2 vi2VarA5 = vi2.a(bVar, "WorkName");
                                if (vi2Var5.equals(vi2VarA5)) {
                                    HashMap map6 = new HashMap(2);
                                    map6.put("work_spec_id", new ri2("work_spec_id", "TEXT", true, 1, null, 1));
                                    map6.put("progress", new ri2("progress", "BLOB", true, 0, null, 1));
                                    HashSet hashSet10 = new HashSet(1);
                                    hashSet10.add(new si2("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                                    vi2 vi2Var6 = new vi2("WorkProgress", map6, hashSet10, new HashSet(0));
                                    vi2 vi2VarA6 = vi2.a(bVar, "WorkProgress");
                                    if (vi2Var6.equals(vi2VarA6)) {
                                        HashMap map7 = new HashMap(2);
                                        map7.put("key", new ri2("key", "TEXT", true, 1, null, 1));
                                        map7.put("long_value", new ri2("long_value", "INTEGER", false, 0, null, 1));
                                        vi2 vi2Var7 = new vi2("Preference", map7, new HashSet(0), new HashSet(0));
                                        vi2 vi2VarA7 = vi2.a(bVar, "Preference");
                                        if (!vi2Var7.equals(vi2VarA7)) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            default:
                HashMap map8 = new HashMap(8);
                map8.put("id", new ri2("id", "INTEGER", true, 1, null, 1));
                map8.put("authenticated", new ri2("authenticated", "INTEGER", false, 0, null, 1));
                map8.put("eventName", new ri2("eventName", "TEXT", false, 0, null, 1));
                map8.put("sequenceId", new ri2("sequenceId", "BLOB", false, 0, null, 1));
                map8.put("sequenceNumber", new ri2("sequenceNumber", "INTEGER", true, 0, null, 1));
                map8.put("fragments", new ri2("fragments", "BLOB", false, 0, null, 1));
                map8.put("owner", new ri2("owner", "TEXT", false, 0, null, 1));
                map8.put("deviceId", new ri2("deviceId", "TEXT", false, 0, null, 1));
                HashSet hashSet11 = new HashSet(0);
                HashSet hashSet12 = new HashSet(1);
                hashSet12.add(new ui2("index_Events_eventName", false, Arrays.asList("eventName"), Arrays.asList("ASC")));
                vi2 vi2Var8 = new vi2("Events", map8, hashSet11, hashSet12);
                vi2 vi2VarA8 = vi2.a(bVar, "Events");
                if (vi2Var8.equals(vi2VarA8)) {
                    HashMap map9 = new HashMap(3);
                    map9.put("eventName", new ri2("eventName", "TEXT", true, 1, null, 1));
                    map9.put("sequenceId", new ri2("sequenceId", "BLOB", true, 2, null, 1));
                    map9.put("sequenceNumberNext", new ri2("sequenceNumberNext", "INTEGER", true, 0, null, 1));
                    vi2 vi2Var9 = new vi2("EventSequenceNumbers", map9, new HashSet(0), new HashSet(0));
                    vi2 vi2VarA9 = vi2.a(bVar, "EventSequenceNumbers");
                    if (vi2Var9.equals(vi2VarA9)) {
                        HashMap map10 = new HashMap(3);
                        map10.put("eventName", new ri2("eventName", "TEXT", true, 1, null, 1));
                        map10.put("count", new ri2("count", "INTEGER", true, 0, null, 1));
                        map10.put("timestamp", new ri2("timestamp", "INTEGER", true, 0, null, 1));
                        vi2 vi2Var10 = new vi2("RateLimitedEvents", map10, new HashSet(0), new HashSet(0));
                        vi2 vi2VarA10 = vi2.a(bVar, "RateLimitedEvents");
                        if (!vi2Var10.equals(vi2VarA10)) {
                        }
                    }
                }
                break;
        }
        return new k52((String) null, true);
    }
}
