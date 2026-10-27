-- import laser lua library functions
require("lalulib")

--Simple mode : guidanceLevel = 1
--Detail mode : guidanceLevel = 2
--Standard mode : guidanceLevel = 3

countryCode = "THA"
unitArray = {"เมตร", "เมตร", "กิโลเมตร", "กิโลเมตร", "กิโลเมตร", "ฟุต", "ฟุต", "หลา", "หลา", "ไมล์", "ไมล์", "1/4", "1/2", "3/4"}

function getVersionInfo()
   local t =
   {
      ["country"] = "THA",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "tha",             -- ISO 639-3  alpha-3 language code
      ["version"] = "2.17.0",       -- version
      ["date"] = "2024-07-02",      -- date of the last change
      ["lua"] = _VERSION,           -- lua version
      ["exe"] = os.date( "%H:%M:%S %Y-%m-%d"),  -- execution date
      ["utc"] = os.date( "!%H:%M:%S %Y-%m-%d"), -- UTC execution date
      ["project"] = "greatwall",    -- project title
      ["module"] = "guidance",      -- guidance
   }
   return dumpToString(t)
end

function spellWeatherType(weatherTypeNum)
   local s = ""
   if (weatherTypeNum == 1) then
      s = "ซันนี่"
   elseif (weatherTypeNum == 2) then
      s = "มีเมฆมาก"
   elseif (weatherTypeNum == 3) then
      s = "มืดครึ้ม"
   elseif (weatherTypeNum == 4) then
      s = "ฝนตกปรอย ๆ"
   elseif (weatherTypeNum == 5) then
      s = "ฝนฟ้าคะนอง"
   elseif (weatherTypeNum == 6) then
      s = "ฝนฟ้าคะนองพร้อมมีลูกเห็บตก"
   elseif (weatherTypeNum == 7) then
      s = "หิมะฝน"
   elseif (weatherTypeNum == 8) then
      s = "ฝนตกเล็กน้อย"
   elseif (weatherTypeNum == 9) then
      s = "ฝนตกปานกลาง"
   elseif (weatherTypeNum == 10) then
      s = "ฝนตกหนัก"
   elseif (weatherTypeNum == 11) then
      s = "พายุฝน"
   elseif (weatherTypeNum == 12) then
      s = "พายุฝนตกหนัก"
   elseif (weatherTypeNum == 13) then
      s = "พายุฝนตกชุกหนาแน่น"
   elseif (weatherTypeNum == 14) then
      s = "ฝนเยือกแข็ง"
   elseif (weatherTypeNum == 15) then
      s = "ฝนตกเล็กน้อยถึงปานกลาง"
   elseif (weatherTypeNum == 16) then
      s = "ฝนตกปานกลางถึงตกหนัก"
   elseif (weatherTypeNum == 17) then
      s = "ฝนตกหนักถึงมีพายุ"
   elseif (weatherTypeNum == 18) then
      s = "พายุฝนถึงพายุฝนตกหนัก"
   elseif (weatherTypeNum == 19) then
      s = "พายุฝนตกหนักถึงตกชุกหนาแน่น"
   elseif (weatherTypeNum == 20) then
      s = "หิมะตกโปรยปราย"
   elseif (weatherTypeNum == 21) then
      s = "หิมะตกเล็กน้อย"
   elseif (weatherTypeNum == 22) then
      s = "หิมะตกปานกลาง"
   elseif (weatherTypeNum == 23) then
      s = "หิมะตกหนัก"
   elseif (weatherTypeNum == 24) then
      s = "พายุหิมะ"
   elseif (weatherTypeNum == 25) then
      s = "หิมะตกเล็กน้อยถึงปานกลาง"
   elseif (weatherTypeNum == 26) then
      s = "หิมะตกปานกลางถึงตกหนัก"
   elseif (weatherTypeNum == 27) then
      s = "หิมะตกหนักถึงพายุหิมะ"
   elseif (weatherTypeNum == 28) then
      s = "หมอก"
   elseif (weatherTypeNum == 29) then
      s = "หมอกหนา"
   elseif (weatherTypeNum == 30) then
      s = "หมอกหนาจัด"
   elseif (weatherTypeNum == 31) then
      s = "หมอกมาก"
   elseif (weatherTypeNum == 32) then
      s = "หมอกหนาจัดมาก"
   elseif (weatherTypeNum == 33) then
      s = "ฝุ่นละออง"
   elseif (weatherTypeNum == 34) then
      s = "ทรายลอย"
   elseif (weatherTypeNum == 35) then
      s = "พายุทราย"
   elseif (weatherTypeNum == 36) then
      s = "หมอกแดด"
   elseif (weatherTypeNum == 37) then
      s = "หมอกแดดปานกลาง"
   elseif (weatherTypeNum == 38) then
      s = "หมอกแดดอย่างหนัก"
   elseif (weatherTypeNum == 39) then
      s = "หมอกแดดรุนแรง"
   else
      s = ""
   end
   return s
end

function spellRoughDirection(man)
   local s = ""
   if (man.DirOClock == 0) then
      s = "ตรง"
   elseif (isInRanges(man.DirOClock, 1, 5)) then
      s = "ขวา"
   elseif (isInRanges(man.DirOClock, 7, 11)) then
      s = "ซ้าย"
   end
   return s
end

function spellFineDirection(man)

   -- if the route is in highway or toll road, then change slight left/right to left/right.
   if (man.FromProps.Area.motorway or man.FromProps.Area.toll) then
      if (man.DirOClock == 2) then
         man.DirOClock = 3
      elseif (man.DirOClock == 10) then
         man.DirOClock = 9
      end
   end

   local s = spellRoughDirection(man)
   if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
      if (man.Action == "exit") then
         if (man.project == "zeekr") then
            s = concEx(s, "เล็กน้อย")
         else
            s = concEx("เบี่ยง", s)
         end
      end
   elseif ((man.DirOClock == 2) or (man.DirOClock == 10)) then
      if (man.project == "zeekr") then
         s = concEx(s, "เล็กน้อย")
      else
         s = concEx("เบี่ยง", s)
      end
   elseif (isInRanges(man.DirOClock, 4, 5, 7, 8)) then
      s = concEx(s, "หักศอก")
   end
   return s
end

function getSoundFile(man, enableAutonomous)
   local s = ""
   return s
end

function testBeginDistance(isHighway, man)
   local ret = false
   local dist = getIntNumber(man.Distance)

   if (man.DistUnit == "kilometer") then
      if (isHighway) then
         ret = (dist >= 3000)
      else
         ret = (dist >= 2000)
      end
   elseif (man.DistUnit == "mile") then
      if (isHighway) then
         ret = (dist >= 2)
      else
         ret = (dist >= 1)
      end
   end
   return ret
end

function spellDistancePart(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.Stage == 1) then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         return s
      end
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         if (man.Distance > 0) then
            if (man.project == "zeekr") then
               s = concEx(s, "อีก", spellDistanceAndUnit(man, countryCode, unitArray))
            else
               s = concEx(s, "อีกประมาณ", spellDistanceAndUnit(man, countryCode, unitArray))
            end
         end
      else
         if (man.project == "zeekr") then
            s = "ตอนนี้"
         else
            s = "เตรียม"
         end
      end
   else
      if (man.Stage > 2) and ((guidanceLevel == 1) or (guidanceLevel == 3)) then
         return s
      end
      if (man.Distance > 0) then
         if (man.project == "zeekr") then
            s = concEx(s, "อีก", spellDistanceAndUnit(man, countryCode, unitArray))
         else
            s = concEx(s, "อีกประมาณ", spellDistanceAndUnit(man, countryCode, unitArray))
         end
      end
   end
   return s
end

function spellHere(man)
   local s = ""
   if (man.StageClass == "just_before") then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         s = ""
      else
         s = ""
      end
   end
   return s
end

function spellDirectionPart(man)
   local s = ""
   local isFromHighway = man.FromProps.Area.motorway
   if (isFromHighway == nil) then
      isFromHighway = false
   end

   -- roundabout exit voice
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = concEx(s, "เลี้ยว")
      if (man.HintProps.Hint.left_hand_traffic) then
         s = concEx(s, "ซ้าย")
      else
         s = concEx(s, "ขวา")
      end
   elseif (man.HintProps.Hint.along_main_road) then
      if (man.project == "zeekr") then
         s = "ขับต่อไปตามถนนหลัก"
      else
         s = "ไปตามเส้นทางหลักต่อไป"
      end
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      if (man.project == "zeekr") then
         s = "ตรงไปบน"
      else
         s = "ชิดกลาง"
      end
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = concEx(s, spellRoughDirection(man), "ไป")
         if (man.Nth > 0) then
            s = concEx(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "กลับรถ"
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            s = concEx(s, "เลี้ยว", spellFineDirection(man))
         else
            s = concEx(s, "ชิด", spellFineDirection(man))
         end
      else
         s = concEx(s, "เลี้ยว", spellFineDirection(man))
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
            s = concEx(s, "", spellNthStreet(man.Nth, false))
         end
      end
   end
   return s
end

function spellRoundaboutDirection(man)
   -- roundabout exit voice
   local s = "เลี้ยว"
   if (man.HintProps.Hint.left_hand_traffic) then
      s = concEx(s, "ซ้าย")
   else
      s = concEx(s, "ขวา")
   end
   return s
end

function getDirectionName(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (  ((guidanceLevel == 2 and man.Stage > 0 and man.StageClass ~= "just_before") or (guidanceLevel == 1 and man.Stage == 2) or (guidanceLevel == 3 and man.Stage == 2))
      and (isNotEmpty(man.TowardsProps.Name))
      and (man.TowardsProps.ObjectType ~= "road_number")
      and (man.TowardsProps.ObjectType ~= "road_name")) then
      s = " " .. man.TowardsProps.Name .. " "
   end
   return s
end

function getExitName(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (((guidanceLevel > 1 and man.Stage > 1) or (guidanceLevel == 1 and man.Stage == 2))
      and (isNotEmpty(man.HintProps.ExitName))) then
      s = " " .. man.HintProps.ExitName .. " "
   end
   return s
end

function spellNamesPart(man, useRoadNameOrNumber)
   local s = ""
   local isFromHighway = man.FromProps.Area.motorway
   if (isFromHighway == nil) then
      isFromHighway = false
   end
   local isToHighway = man.ToProps.Area.motorway
   if (isToHighway == nil) then
      isToHighway = false
   end
   local exitName = getExitName(man)
   local signPostName = getDirectionName(man)
   -- highway exit
   if (isFromHighway) then
      if (#exitName > 0) then
         if (man.project == "zeekr") then
            if (#signPostName > 0) then
               s = concEx(s, "ใช้ทางออก", exitName, "ทาง", spellFineDirection(man))
            else
               s = concEx(s, "ใช้ทางออกที่", exitName, "ทาง", spellFineDirection(man))
            end
         else
            s = concEx(s, "ในทางออก", exitName)
         end
      end
      if (#signPostName > 0) then
         if (man.project == "zeekr") then
            s = concEx(s, "ไป", signPostName)
         else
            s = concEx(s, "ไปทาง", signPostName)
         end
      end
      if (man.Action == "exit") then
         if (man.project == "zeekr") then
            if (#exitName > 0) then
               s = s
            elseif (#signPostName > 0) then
               s = concEx(s, " ใช้ทางออกทาง", spellFineDirection(man), s)
            else
               s = concEx(s, " ใช้ทางออกทาง", spellFineDirection(man))
            end
         else
            s = concEx(s, " เตรียมออกจากทางด่วน")
         end
      end
      return s
   else
       local isRamp = man.ToProps.Area.ramp
      if (isRamp and #signPostName > 0) then
         if (man.project == "zeekr") then
            s = concEx(s, "ไปยัง", signPostName)
         else
            s = concEx(s, "ไปทาง", signPostName)
         end
      else
         if (useRoadNameOrNumber) then
            local roadName = getRoadNameOrNumber(man)
            if (#roadName > 0) then
               if (man.project == "zeekr") then
                  s = concEx(s, "บน", roadName)
               else
                  s = concEx(s, "สู่", roadName)
               end
            end
         end
      end
   end
   return s
end

function spellIntersectionCount(man)
   -- not count intersection count in manual request.
   if (man.HintProps.Hint.manual_request) then
      return ""
   end

   local guidanceLevel = man.GuidanceLevel

   if (man.project == "zeekr" and man.Stage == 1 and guidanceLevel == 1) then
      return ""
   end

   local isHighway = man.FromProps.Area.motorway
   if (isHighway == nil) then
      isHighway = false
   end

   if (not man.IsIntersection) then
      return "";
   end
   if (man.ContainTrafficLight and man.TrafficLightIntersectionCount > 3 and man.StageClass ~= "just_before") then
      return ""
   end
   if ((not man.ContainTrafficLight) and man.IntersectionCount > 3 and man.StageClass ~= "just_before") then
      return "";
   end

   if (man.next ~= nil and man.Stage == 1 and man.StageClass ~= "just_before") then
      return ""
   end
   
   if (man.StageClass == "just_before"
      and (man.TrafficLightIntersectionCount == 0 and (man.IntersectionCount == 0 or man.IntersectionCount == 255))) then
      return ""
   end

   if (man.Stage == 4 and man.TrafficLightIntersectionCount == 0) then
      return "";
   end
   if (man.StageClass == "just_before" and man.IntersectionCount == 0) then
      return "";
   end
   local s = ""
   if (man.StageClass == "just_before" and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      if (man.ContainTrafficLight) then
         s = concEx(s, "ที่สัญญาณไฟจราจร")
         if (man.TrafficLightIntersectionCount > 0) then
            s = concEx(s, "", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1))
         end
      else
         if (man.project == "zeekr") then
            s = concEx(s, "ที่ทางแยก", spellOrdinalNumber(man.IntersectionCount + 1))
         else
            s = concEx(s, "ที่สี่แยก", spellOrdinalNumber(man.IntersectionCount + 1))
         end
      end
   elseif (man.ContainTrafficLight and (not isHighway)) then
      if ((guidanceLevel == 1) and (man.StageClass == "just_before")) or ((guidanceLevel == 3) and (man.StageClass == "just_before")) or (guidanceLevel == 2) then
         s = concEx(s, "ที่สัญญาณไฟจราจร")
         if (man.TrafficLightIntersectionCount > 0) then
            s = concEx(s, "", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1))
         end
      end
   elseif (isHighway and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      if (man.project == "zeekr") then
         s = concEx(s, "ที่ทางแยก", spellOrdinalNumber(man.IntersectionCount + 1))
      else
         s = concEx(s, "ที่สี่แยก", spellOrdinalNumber(man.IntersectionCount + 1))
      end
   end
   return s
end

function spellPrepositionAndGender(props)
   local s = ""
   if (props.Preposition == "into") or (props.Preposition == "onto") then
      s = "สู่"
   end
   return s
end
function replaceAbbreviations(name)
   local s = name

   local phonemeStartStr = string.char(27) .. "\\lang="
   local isPhoneme = false
   if ((type(s) == "string") and (#s >= #phonemeStartStr)) then
      isPhoneme = (string.sub(s, 1, #phonemeStartStr) == phonemeStartStr)
   end
   if (not isPhoneme) then
      local replacementTbl = {
         {"%sN$", " North"},
         {"%sS$", " South"},
         {"%sE$", " East"},
         {"%sW$", " West"},

         {"%sNE$", " Northeast"},
         {"%sNW$", " Northwest"},
         {"%sSE$", " Southeast"},
         {"%sSW$", " Southwest"},

         {"^N%s", "North "},
         {"^S%s", "South "},
         {"^E%s", "East "},
         {"^W%s", "West "},

         {"^NE%s", "Northeast "},
         {"^NW%s", "Northwest "},
         {"^SE%s", "Southeast "},
         {"^SW%s", "Southwest "},

--         {"^I%-", "Interstate "},
         {"^US%-", "U S "},

         {"%sAve$", " Avenue"},
         {"%sBlvd$", " Boulevard"},
         {"%sBrg$", " Bridge"},
         {"%sCp$", " Camp"},
         {"%sCir$", " Circle"},
         {"%sCres$", " Crescent"},
         {"%sCt$", " Court"},
         {"%sDr$", " Drive"},
         {"%sHl$", " Hill"},
         {"%sLn$", " Lane"},
         {"%sPkwy$", " Parkway"},
         {"%sPl$", " Place"},
         {"%sPlz$", " Plaza"},
         {"%sRdg$", " Ridge"},
         {"%sRd$", " Road"},
         {"%sSq$", " Square"},
         {"%sSt$", " Street"},
         {"%sTer$", " Terrace"},
         {"%sTrce$", " Trace"},
         {"%sTrl$", " Trail"},

         {"%sAve%s", " Avenue "},
         {"%sBlvd%s", " Boulevard "},
         {"%sBrg%s", " Bridge "},
         {"%sCp%s", " Camp "},
         {"%sCir%s", " Circle "},
         {"%sCres%s", " Crescent "},
         {"%sCt%s", " Court "},
         {"%sDr%s", " Drive "},
         {"%sHl%s", " Hill "},
         {"%sLn%s", " Lane "},
         {"%sPkwy%s", " Parkway "},
         {"%sPl%s", " Place "},
         {"%sPlz%s", " Plaza "},
         {"%sRdg%s", " Ridge "},
         {"%sRd%s", " Road "},
         {"%sSq%s", " Square "},
         {"%sSt%s", " Street "},
         {"%sTer%s", " Terrace "},
         {"%sTrce%s", " Trace "},
         {"%sTrl%s", " Trail "},

         {"[%-%s]BL$", " Business Loop"},
         {"[%-%s]BS$", " Business Spur"},
         {"[%-%s]Bus.$", " Business Route"},
         {"[%-%s]Expy$", " Expressway"},
         {"[%-%s]Fwy$", " Freeway"},
         {"[%-%s]Hwy$", " Highway"},

         {"[%-%s]BL%s", " Business Loop "},
         {"[%-%s]BS%s", " Business Spur "},
         {"[%-%s]Bus.%s", " Business Route "},
         {"[%-%s]Expy%s", " Expressway "},
         {"[%-%s]Fwy%s", " Freeway "},
         {"[%-%s]Hwy%s", " Highway "},
      }

      for _, promptPart in ipairs(replacementTbl) do
         s = string.gsub(s, promptPart[1], promptPart[2])
      end
   end
   return s
end

function getRoadNameOrNumber(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (   ((guidanceLevel == 2 and man.Stage > 0 and man.StageClass ~= "just_before") or (guidanceLevel == 1 and man.Stage == 2) or (guidanceLevel == 3 and man.Stage == 2))
      and (isNotEmpty(man.ToProps.Name))
      and ((man.ToProps.ObjectType == "road_number")
         or (man.ToProps.ObjectType == "road_name"))) then

      if (man.ToProps.Name ~= man.FromProps.Name) then
         s = " " .. man.ToProps.Name .. " "
      end
   end
   return s
end

function spellOrdinalNumber(number)
   local s = ""
   if (number == 1) then
      s = "ที่หนึ่ง"
   elseif (number == 2) then
      s = "ที่สอง"
   elseif (number == 3) then
      s = "ที่สาม"
   elseif (number == 4) then
      s = "ที่สี่"
   elseif (number == 5) then
      s = "ที่ห้า"
   elseif (number == 6) then
      s = "ที่หก"
   elseif (number == 7) then
      s = "ที่เจ็ด"
   elseif (number == 8) then
      s = "ที่แปด"
   elseif (number == 9) then
      s = "ที่เก้า"
   elseif (number == 10) then
      s = "ที่สิบ"
   elseif (number == 11) then
      s = "ที่สิบเอ็ด"
   elseif (number == 12) then
      s = "ที่สิบสอง"
   else
      s = spellNumberEx(number)
   end
   return s
end

function spellTimeTTA(timeVal)
   local s = ""
   if (isNumber(timeVal)) then
      timeVal =  (timeVal >= 0) and (math.floor(timeVal + 0.5)) or (math.ceil(timeVal - 0.5))
      timeVal = timeVal + 30
      local hour = math.floor(timeVal / 3600)
      local minutes = math.floor((timeVal % 3600) / 60)
      if (hour >= 1) then
         s = hour .. "ชั่วโมง"
      end

      if (minutes >= 1) then
         s = s .. "" .. minutes .. "นาที"
      end
   end
   if (#s > 0) then
      s = "คุณสามารถมาถึงจุดหมายได้ " .. s
   end
   return s
end

function spellTime(timeVal, isTwelveHoursClock, arrivalTimeMode)
   local s = ""
   if (arrivalTimeMode == "announce_arrival_time_mode_tta") then
      s = spellTimeTTA(timeVal);
      return s
   end
   if (isNumber(timeVal)) then
      timeVal =  (timeVal >= 0) and (math.floor(timeVal + 0.5)) or (math.ceil(timeVal - 0.5))
      timeVal = timeVal + 30
      local t = os.date("!*t", timeVal)
      if (t == nil) then
         --ignore date and daylight saving time and try again
         timeVal = timeVal % 86400 -- 60 * 60 * 24 secondsOfDay
         t = os.date("!*t", timeVal)
      end
      if (t ~= nil and t.hour ~= nil and t.min ~= nil) then
         local hourFormat = ((isTwelveHoursClock and "!%I") or "!%H")
         local hour = getIntNumber(os.date(hourFormat, timeVal))
         t.min = getIntNumber(t.min)

         if (t.min == 0) and isInRanges(t.hour, 0, 9) then
            s = "0"..hour .. ".00 น. "
         elseif (t.min == 0) and isInRanges(t.hour, 10, 23) then
            s = hour .. ".00 น. "
         elseif (isInRanges(t.min, 0, 9)) then
            s = hour .. ":0" .. t.min
         elseif (isInRanges(t.min, 10, 59)) then
            s = hour .. ":" .. t.min
         end

         if (isTwelveHoursClock) then
            local twelveHoursClockMark = string.lower(os.date("!%p", timeVal))
            if (t.min == 0) then
               s = hour ..  ".00น. "
            elseif (isInRanges(t.min, 0, 9)) then
               s = hour .. ":0" .. t.min
            else
               s = hour .. ":" .. t.min
            end
            if (twelveHoursClockMark == "am") then
               s = "ตอนเช้า " .. s
            elseif (twelveHoursClockMark == "pm") then
               s = "ตอนบ่าย " .. s
            end
         end
      end
   end
   if (#s > 0) then
      if (isTwelveHoursClock) then
         s = "เวลาถึงจุดหมายปลายทาง ประมาณ" .. s
      else
         s = "เวลาถึงจุดหมายปลายทาง ประมาณ " .. s
      end
   end
   return s
end

function spellNthStreet(nth, isStraight)
   local s = ""
   if (nth == 1) then
      s = "ซ้าย"
   elseif (nth == 2) then
      s = "กลาง"
   elseif (nth == 3) then
      s = "ขวา"
   end
   if (isStraight) then
      s = "ทางถนน" .. s-----translation is not given, translate by myself
   else
      s = "และใช้ถนน" .. s-----translation is not given, translate by myself
   end
   return s
end

function processIntersectionAction(man)
   local s = spellDistancePart(man)
   s = concEx(s, "", spellDirectionPart(man))
   s = concEx(s, "", spellIntersectionCount(man))
   s = concEx(s, "", spellNamesPart(man, true))
   s = concEx(s, spellHere(man))
   return s
end

function processIntersectionActionCombined(man)
   local s = ""
   s = spellDirectionPart(man)
   s = concEx(s, spellNamesPart(man, false))

   if (#s > 0) then
      s = concEx(" ", s)
      if (     (isAvailable(man.previous))
         and   (man.previous.Stage == 2)) then
         if (man.project == "zeekr") then
            s = concEx("", s)
         else
            s = concEx("เตรียม", s)
         end
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  ((not man.previous.HintProps.Hint.along_main_road) and man.HintProps.Hint.along_main_road)) then
         s = concEx(s, "")
      elseif (     (isAvailable(man.previous))
         and   (man.previous.Action == man.Action)
         and   (man.previous.DirOClock == man.DirOClock)) then
         s = concEx(s, "อีกครั้ง")
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  (spellDirectionPart(man.previous) == spellDirectionPart(man))) then
         s = concEx(s, "อีกครั้ง")
      end
   end
   return s
end

function processRoundaboutAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.project == "zeekr" and guidanceLevel == 1) then
      return s
   end
   if (man.Action == "roundabout_leave") then
      if (man.HintProps.Hint.first_announcement or
         (man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass)) then
         if (man.project == "zeekr") then
            s = "ออกจากวงเวียนที่ทางออก" .. spellOrdinalNumber(man.Nth)
         else
            s = "ออกจากวงเวียนในทางออก" .. spellOrdinalNumber(man.Nth)
         end
         return s
      end
   end
   if (man.Action == "roundabout" and ((man.Stage == 2 or man.Stage == 3) or (man.Stage == 1 and man.DerivedBegin))) then
      if (man.project == "zeekr") then
         if (man.Stage == 3) then
            s = "ข้างหน้าจะเข้าวงเวียน"
         else
            s = " ออกจากวงเวียนที่ทางออก" .. spellOrdinalNumber(man.Nth)
         end
      else
         --s = "อีกประมาณ" .. spellDistanceAndUnit(man, countryCode, unitArray) .. "เข้าวงเวียน"
         s = s .. " จากนั้นออกจากวงเวียนในทางออก" .. spellOrdinalNumber(man.Nth)
      end
   elseif (man.StageClass == "roundabout_export") then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         s = ""
      else
         s = "ผ่านทางออกที่" .. spellOrdinalNumber(man.CurrentRoundaboutExitNum)
         if (man.CurRoundaboutExitPhaseCombinedWithNext) then
            s = concat(s, " จากนั้นออกจากวงเวียนในทางออก", spellOrdinalNumber(man.CurrentRoundaboutExitNum + 1))
         end
         man._NoCombination = true
      end
   elseif (man.StageClass == "roundabout_exit") then
      if (man.project ~= "zeekr" or guidanceLevel > 1) then
         s = spellRoundaboutDirection(man) .. "ออกจากวงเวียน"
      end
   end
   return s
end

function processRoundaboutActionCombined(man)
   local s = spellDistancePart(man)
   s = concat(s, "ออกจากวงเวียนในทางออก", spellOrdinalNumber(man.Nth))
   s = concEx(s, spellHere(man))

   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
      s = concEx(s, "ในไม่ช้า")
   end
   return s
end

function processDestinationAction(man, onlyDirection)
   local s = ""
   local triggerVoice = false
   if (man.HintProps.Hint.manual_request) then
      triggerVoice = true
   elseif (man.project == "zeekr" and man.GuidanceLevel == 1) then
      triggerVoice = false
   elseif (man.Stage == 1) then
      triggerVoice = true
   elseif (man.DestinationAllPhases) then
      if (man.GuidanceLevel == 1) then
         triggerVoice = true
      elseif ((man.GuidanceLevel == 2 or man.GuidanceLevel == 3) and (man.Stage == 2 or man.Stage == 4)) then
         triggerVoice = true
      end
   end

   if (not triggerVoice) then
      return s
   end

   if (man.FromProps.Area.crime and man.Stage == 1) then
      s = ""
   elseif (man.Stage == 1) then
      s = "คุณมาถึง"
   else
      s = spellDistancePart(man)
      if (onlyDirection) then
         s = ""
      end
      if (man.project == "zeekr") then
         s = s .. "จะถึง"
      else
         s = s .. "คุณมาถึง"
      end
      s = concEx(s, spellHere(man))
   end

   s = concat(s, "", spellDestinationName(man))
   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
         s = concEx(s, "ในไม่ช้า")
   end
   return s
end

function spellDestinationName(man)

   local DestNumber = man.HintProps.CustomDestNumber
   if (DestNumber == nil or getIntNumber(DestNumber) > 100) then
      DestNumber = getIntNumber(man.TotalNumberOfDestinations) - getIntNumber(man.DestinationIndex)
   else
      DestNumber = spellNumberEx(DestNumber)
   end
   local s = ""
   if (man.FromProps.Area.ferry) then
      if (man.project == "zeekr") then
         s = "ท่าเรือข้ามฟาก"
      else
         s = "ท่าเรือเฟอร์รี่"
      end
   elseif (man.FromProps.Area.ipd) then
      s = concEx(s, "คุณถึงสถานีชาร์จไฟแล้ว จะต้องชาร์จไฟจนถึง", spellNumberEx(man.Nth))
      s = s .. "%"
   elseif (man.FromProps.Area.crime and man.Stage == 1) then
      s = "ใกล้ปั๊มน้ำมัน Setel คุณต้องการบริการ Setel หรือไม่? "
   elseif (man.Nth == 0) then
      if (man.project == "zeekr") then
         s = "จุดหมายปลายทางแล้ว"
      else
         s = "ปลายทางแล้ว"
      end
      if (man.DirOClock == 9) then
         if (man.project == "zeekr") then
            s = concat(s, "", "อยู่ด้านซ้าย")
         else
            s = concat(s, "", "อยู่ทางซ้ายมือ")
         end
      elseif (man.DirOClock == 3) then
         if (man.project == "zeekr") then
            s = concat(s, "", "อยู่ด้านขวา")
         else
            s = concat(s, "", "อยู่ทางขวามือ")
         end
      end
   elseif (isInRanges(DestNumber, 1, 100)) then
      s = concEx(s, "จุดแวะพัก", spellNumberEx(DestNumber), "แล้ว")
      if (man.DirOClock == 9) then
         if (man.project == "zeekr") then
            s = concat(s, "", "อยู่ด้านซ้าย")
         else
            s = concat(s, "", "อยู่ทางซ้ายมือ")
         end
      elseif (man.DirOClock == 3) then
         if (man.project == "zeekr") then
            s = concat(s, "", "อยู่ด้านขวา")
         else
            s = concat(s, "", "อยู่ทางขวามือ")
         end
      end
   end

   if ((man.Nth == 0) and (man.Stage == 1) and (not isAvailable(man.previous)) ) then
      s = concat(s, " ", "ขอบคุณสำหรับการใช้บริการ")
   end

   return s
end

function processEnterAction(man)
   man._NoCombination = true
   return ""
end

function processTollAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.project == "zeekr") then
      if (man.Stage == 2 or (man.Stage == 4 and guidanceLevel == 3)) then
         return s
      end
   end
   if (guidanceLevel == 1) then
      if (man.Stage == 2) then
         if (man.project == "zeekr") then
            s = "มีด่านเก็บเงินอยู่ข้างหน้า"
         else
            s = "ถึงด่านเก็บค่าผ่านทางในไม่ช้า"
         end
      end
   elseif (guidanceLevel == 3) then
      if (man.Stage == 2) then
         if (man.project == "zeekr") then
            s = "มีด่านเก็บเงินอยู่ข้างหน้า"
         else
            s = "ถึงด่านเก็บค่าผ่านทางในไม่ช้า"
         end
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 3) ) then
         if (man.project == "zeekr") then
            s = "มีด่านเก็บเงินอยู่ข้างหน้า"
         else
            s = "ข้างหน้ามีด่านเก็บค่าผ่านทาง"
         end
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         if (man.project == "zeekr") then
            s = "มีด่านเก็บเงินอยู่ข้างหน้า"
         else
            s = "ถึงด่านเก็บค่าผ่านทางในไม่ช้า"
         end
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         if (man.project == "zeekr") then
            s = "มีด่านเก็บเงินอยู่ข้างหน้า"
         else
            s = "ข้างหน้ามีด่านเก็บค่าผ่านทาง"
         end
      end
   end
   return s
end

function processTunnelAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.project == "zeekr" and man.Stage == 2 and guidanceLevel == 3) then
      return s
   end
   if (guidanceLevel == 1) then
      s = ""
   elseif (guidanceLevel == 3) then
      if (man.Stage == 2) then
         if (man.project == "zeekr") then
            s = "มีอุโมงค์อยู่ข้างหน้า"
         else
            s = "เตรียมเข้าสู่อุโมงค์"
         end
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         if (man.project == "zeekr") then
            s = "มีอุโมงค์อยู่ข้างหน้า"
         else
            s = "เตรียมเข้าสู่อุโมงค์"
         end
      elseif (man.project == "zeekr" and man.Stage == 2 and guidanceLevel == 2) then
         if (man.project == "zeekr") then
            s = "มีอุโมงค์อยู่ข้างหน้า"
         else
            s = "เตรียมเข้าสู่อุโมงค์"
         end
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         if (man.project == "zeekr") then
            s = "มีอุโมงค์อยู่ข้างหน้า"
         else
            s = "อุโมงค์ข้างหน้า"
         end
      end
   end
   return s
end

function processEnterActionWarning(man, AnnouncementIsNotEmpty)
   local s = "โปรดปฏิบัติตามกฎจราจร"
   return s
end

function processEnterActionCombined(man)
   local s = ""
   if (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel)) then
      s = processTunnelAction(man)
   end
   if (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) then
      s = processTollAction(man)
   end
   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
         s = concEx(s, " ในไม่ช้า")
   end
   return s
end

function processMotorwayEnterAction(man)
   local s = spellDistancePart(man)
   s = concEx(s, "", spellDirectionPart(man))
   s = concEx(s, "", "ถึงทางขึ้นทางด่วน")
   s = concEx(s, "", spellMotorWayEnterNamesPart(man, true))
   s = concEx(s, spellHere(man))
   return s
end


function spellMotorWayEnterNamesPart(man, useRoadNameOrNumber)
   local s = ""
   local signPostName = getDirectionName(man)
   -- motorway entry
   if (man.HintProps.Hint.motorway_entry) then
       if (#signPostName > 0) then
         if (man.project == "zeekr") then
            s = concEx(s, "ไปยัง", signPostName)
         else
            s = concEx(s, " ไปทาง", signPostName)
         end
      end
         return s
   end
   return s
end

function processLaneMergeAction(man)
   local s = ""
   local firstStage = getIntNumber(man.FirstStage)
   if ((firstStage < 1) or (firstStage > 2)) then
      firstStage = 2
   end
   if (man.Stage == firstStage) then
      s = "ระวังการรวมทราฟฟิก"
      if ((man.DirOClock ~= 0) and (man.DirOClock ~= 6)) then
         s = concEx(s, "จากด้าน", spellRoughDirection(man))
      end
   end
   return s
end

function processFollowRoadAction(man)
   local s = ""
   if (man.project == "zeekr") then
      s = concEx(s, "โปรดขับไปตามเส้นทางนี้ประมาณ")
   else
      s = concEx(s, "ไปต่อตามเส้นทางในอีกประมาณ")
   end
   if (man.Distance > 0) then
      s = concEx(s, spellDistanceAndUnit(man, countryCode, unitArray))
   end
   return s
end

function processDPTrafficLightAction(man)---not given
   local s = "สี่แยกไฟจราจรข้างหน้าโปรดตรงไป"
   if (man.Stage == 1 and man.TrafficLightIntersectionCount > 0) then
      s = "ตรงไปผ่าน" .. spellOrdinalNumber(man.TrafficLightIntersectionCount) .. "ไฟจราจร"
   end
   return s
end

function processDPRecomLaneAction(man)
   local s = ""
   if (man.IsContiLaneVoice) then
      if (man.BestRecomNum > 0) then
         if (not man.IsManeuver) then
            local dir = spellDirection(man.RecomLaneDir)
            if (dir ~= "") then
               s = concEx(dir, " ")
            end
         end
         if (man.project == "zeekr") then
            s = concEx(s, "คำแนะนำ: ขับ")
         else
            s = concEx(s, "แนะนำให้ใช้เลน")
         end
      else
         if (man.project == "zeekr") then
            s = concEx(s, "ขับ")
         else
            s = concEx(s, "โปรดใช้เลน")
         end
      end
      local pos = ""
      if ((man.IsInc2nd) and (man.IsInc3rd)) then
         if (man.project == "zeekr") then
            s = concEx(s, "เลนที่สองหรือสาม")
         else
            s = concEx(s, "เลนที่สองและสาม")
         end
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = concEx(s, pos)
      elseif (man.IsInc2nd) then
         s = concEx(s, "เลนที่สอง")
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = concEx(s, pos)
      elseif (man.IsInc3rd) then
         s = concEx(s, "เลนที่สาม")
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = concEx(s, pos)
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 1)) then
         if (man.project == "zeekr") then
            s = concEx(s, "เลน ซ้ายสุด")
         else
            s = concEx(s, "เลนซ้ายสุด")
         end
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 2)) then
         if (man.project == "zeekr") then
            s = concEx(s, "เลน ขวาสุด")
         else
            s = concEx(s, "เลนขวาสุด")
         end
      else
         if (man.BestRecomNum > 1) then
            local bestNumber = spellNumberEx(man.BestRecomNum)
            if (man.project == "zeekr") then
               s = concEx(s, "หนึ่งใน", bestNumber, "เลน")
            else
               s = concEx(s, bestNumber, "เลน")
            end
         elseif (man.RecomNum > 1) then
            local number = spellNumberEx(man.RecomNum)
            if (man.project == "zeekr") then
               s = concEx(s, "หนึ่งใน", number, "เลน")
            else
               s = concEx(s, number, "เลน")
            end
         else
            s = concEx(s, "เลน")
         end
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = concEx(s, pos)
      end
   else
      if (not man.IsManeuver) then
         local dir = spellDirection(man.RecomLaneDir)
         if (dir ~= "") then
            s = concEx(dir, " ")
         end
      end
      if (man.project == "zeekr") then
         s = concEx(s, "ขับ")
      else
         s = concEx(s, "โปรดใช้")
      end
      if (man.RecomNum > 1) then
         local number = spellNumberEx(man.RecomNum)
         if (man.project == "zeekr") then
            s = concEx(s, "หนึ่งใน", number, "เลน")
         else
            s = concEx(s, number, "เลนส์")
         end
      else
         s = concEx(s, "เลน")
      end
      local pos = spellPosition(man.isContiLaneVoice, man.RecomLanePos)
      s = concEx(s, pos)
   end
   return s
end

function spellDirection(dir)
   local s = ""
   if (dir == 1) then
      s = "เลี้ยวซ้ายหักศอก"                --Turn sharp left
   elseif (dir == 2) then
      s = "เลี้ยวซ้าย"                    --Turn left
   elseif (dir == 3) then
      s = "เลี้ยวเบี่ยงซ้าย"                --Turn slight left
   elseif (dir == 4) then
      s = "เลี้ยวขวาหักศอก"                 --Turn sharp right
   elseif (dir == 5) then
      s = "เลี้ยวขวา"                     --Turn right
   elseif (dir == 6) then
      s = "เลี้ยวเบี่ยงขวา"                --Turn slight right
   elseif (dir == 7) then
      s = "ตรงไป"                      --Go straight on
   elseif (dir == 8) then
      s = "กลับรถ"                     --Make a u-turn
   end
   return s
end

function spellPosition(isContiLaneVoice, position)
   local s = ""
   if (isContiLaneVoice) then
      if (position == 1) then
         s = "ทางด้านซ้าย"
      elseif (position == 2) then
         s = "ทางด้านขวา"
      elseif (position == 3) then
         s = "ทางด้าน กึ่งกลาง"
      end
   else
      if (position == 1) then
         s = "ซ้าย"
      elseif (position == 2) then
         s = "ขวา"
      elseif (position == 3) then
         s = "กึ่งกลาง"
      end
   end
   return s
end

function processDPSAPAAction(man)
   local s = ""
   local distM = man.DistM
   local nextDistM = man.NextDistM
   local distKm = getIntNumber(distM / 1000)
   local nextDistKm = getIntNumber(nextDistM / 1000)
   if ((distKm >= 1) and testDistance3(distM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
      if (man.project == "zeekr") then
         s = concEx("ในอีกประมาณ", spellDistanceAndUnit(man, countryCode, unitArray))
      else
         s = concEx("อีก", spellDistanceAndUnit(man, countryCode, unitArray))
      end
   end
   if (man.project == "zeekr") then
      if (man.AreaType == 1000) then
         s = concEx("สถานที่บริการทางหลวง", s)
      elseif (man.AreaType == 1001) then
         s = concEx("ลานจอดรถ", s)
      end
   else
      s = concEx(s, getAreaType(man.AreaType), "ข้างหน้า")
   end
   if ((nextDistKm >= 1) and testDistance4(nextDistM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray, true) ~= "")) then
      if (man.project == "zeekr") then
         s = concEx(s, " จุดต่อไปอยู่ห่างออกไปประมาณ", spellDistanceAndUnit(man, countryCode, unitArray, true))
      else
         s = concEx(s, " อีกอันอยู่ในระยะ", spellDistanceAndUnit(man, countryCode, unitArray, true))
      end
   end
   return s
end

function getAreaType(type)
   local s = ""
   if (type == 1000) then
     s = "พื้นที่ให้บริการ"
   elseif (type == 1001) then
     s = "บริเวณที่จอดรถ"
   end
   return s
end

function processWNHighAccidentAreaAction()
   local s = "ข้างหน้าเป็นบริเวณที่มีอุบัติเหตุเกิดขึ้นบ่อยครั้ง โปรดขับรถด้วยความระมัดระวัง"
   return s
end

function processWNSchoolZoneAction()
   local s = "ข้างหน้าเป็นบริเวณโรงเรียน โปรดขับรถด้วยความระมัดระวัง"
   return s
end

function processWNRailwayAction()
   local s = "ข้างหน้ามีทางรถไฟ โปรดขับรถด้วยความระมัดระวัง"
   return s
end

function processWNDestParkingAction(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel == 2) then
      s = "ได้แนะนำที่จอดรถปลายทางให้คุณแล้ว คลิกเพื่อไป"
   end
   return s
end

function processWNDestChargingStationAction()
   local s = "สถานีชาร์จใกล้ปลายทางที่แนะนำ"                      --Recommendations of charging stations near the destination
   return s
end

function processWNTunnelAction()
   local s = "อุโมงค์ข้างหน้า"
   return s
end

function processWNGpsAction()---not given
   local s = "สัญญาณ GPS ปัจจุบันอ่อนและการอัปเดตตำแหน่งอาจไม่ตรงเวลา"
   return s
end

function processWNLaneMergeAction(man)
   local s = ""
   if (man.LaneMergeType == 1) then
      if (man.project == "zeekr") then
         s = "ทางร่วมจากทาง ซ้าย ข้างหน้า โปรดขับขี่ด้วยความระมัดระวัง"
      else
         s = "ข้างหน้ามีทางร่วมด้านซ้าย โปรดขับรถด้วยความระมัดระวัง"
      end
   elseif (man.LaneMergeType == 2) then
      if (man.project == "zeekr") then
         s = "ทางร่วมจากทาง ขวา ข้างหน้า โปรดขับขี่ด้วยความระมัดระวัง"
      else
         s = "ข้างหน้ามีทางร่วมด้านขวา โปรดขับรถด้วยความระมัดระวัง"
      end
   end
   return s
end

function processWNTrafficCameraAction(man)
   local s = ""
   if (man.CameraType == 1) then
      s = "มีกล้องจับความเร็ว"
      if (man.project == "zeekr") then
         s = "กล้องตรวจจับความเร็ว"
      end
   elseif (man.CameraType == 7) then
      s = "มีกล้องจับความเร็วและกล้องจับการฝ่าไฟแดง จำกัด"
      if (man.project == "zeekr") then
         s = "กล้องตรวจจับความเร็วและกล้องตรวจจับการฝ่าไฟแดง"
      end
   elseif (man.CameraType == 3) then
      s = "มีกล้องจับการฝ่าไฟแดง"
      if (man.project == "zeekr") then
         s = "กล้องตรวจจับการฝ่าไฟแดง"
      end
   elseif (man.CameraType == 11) then
      s = "คุณกำลังขับรถเข้าไปในเขตพื้นที่ตรวจจับความเร็วใน"
      if (man.project == "zeekr") then
         s = "ตรวจจับความเร็วเฉลี่ย"
      end
   elseif (man.CameraType == 12) then
      s = "คุณกำลังออกจากเขตพื้นที่ตรวจจับความเร็วใน"
      if (man.project == "zeekr") then
         s = "การตรวจจับความเร็วเฉลี่ยจะสิ้นสุดในอีก"
      end
   else
      return s
   end
   local currentDis = man.Distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit_TrafficCamera(man) ~= "")) then
      if (man.project == "zeekr") then
         if (man.CameraType == 12) then
            s = conc(s, spellDistanceAndUnit_TrafficCamera(man))
         else
            s = conc(s, spellDistanceAndUnit_TrafficCamera(man), "เมตรข้างหน้า")
         end
      else
         s = conc(s, "อีก", spellDistanceAndUnit_TrafficCamera(man), "ข้างหน้า")
      end
   else
      if (man.project == "zeekr") then
         s = conc(s, "เมตรข้างหน้า")
      else
         s = conc(s, "ข้างหน้า")
      end
   end
   local speedLimit = man.SpeedLimit
   local unit = "กิโลเมตร/ชั่วโมง"
   if (speedLimit > 0) then
      if (man.DistUnit == "mile" or man.DistUnit == "quartermile" or man.DistUnit == "yard" or man.DistUnit == "foot") then
         speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
         unit = "ไมล์ต่อชั่วโมง"
      end
   end
   local limitedSpeed = spellNumberEx(speedLimit)
   if ((man.CameraType == 1) and (limitedSpeed ~= "0")) then
      s = conc(s, ". จำกัดความเร็ว", limitedSpeed, unit)
   elseif ((man.CameraType == 7) and (limitedSpeed ~= "0")) then
      s = conc(s, ". จำกัดความเร็ว", limitedSpeed, unit)
   elseif (man.CameraType == 3) then
      s = conc(s, ". โปรดขับรถอย่างระมัดระวัง")
   elseif ((man.CameraType == 11) and (limitedSpeed ~= "0")) then
      s = conc(s, ". จำกัดความเร็ว", limitedSpeed, unit)
   end
   return s
end

function spellDistanceAndUnit_TrafficCamera(man)
   local s = ""
   if (man.DistUnit == "meter") then
      s = conc(spellNumber(man.Distance), "เมตร")
   elseif (man.DistUnit == "kilometer") then
      local kilometerDistance = man.Distance;
      if (kilometerDistance < 10000) then
         local kilometerString = "";
         kilometerDistance = math.modf(man.Distance / 1000);
         subkilometerValue = math.modf((man.Distance - kilometerDistance * 1000) / 100);
         if (subkilometerValue == 0) then
            kilometerString = kilometerDistance;
         else
            kilometerString = kilometerDistance;
            kilometerString = kilometerString .. "."
            kilometerString = kilometerString .. subkilometerValue;
         end
         if (kilometerDistance == 1 and subkilometerValue == 0) then
            s = conc(kilometerString, "กิโลเมตร")
         else
            s = conc(kilometerString, "กิโลเมตร")
         end
      else
         kilometerDistance = math.modf(man.Distance / 1000);
         s = conc(spellNumber(kilometerDistance), "กิโลเมตร")
      end
   elseif (man.DistUnit == "foot") then
      s = conc(spellNumber(man.Distance), "ฟุต")
   elseif (man.DistUnit == "yard") then
      s = conc(spellNumber(man.Distance), "หลา")
   elseif (man.DistUnit == "mile") then
      if (man.Distance == 1) then
         s = conc(spellNumber(man.Distance), "ไมล์")
      else
         s = conc(spellNumber(man.Distance), "ไมล์")
      end
   elseif (man.DistUnit == "quartermile") then
      if (man.Distance == 1) then
         s = "1/4"
      elseif (man.Distance == 2) then
         s = "1/2"
      elseif (man.Distance == 3) then
         s = "3/4"
      end
      if (#s > 0) then
         s = conc(s, "ไมล์")
      end
   end
   return s
end

function processWNCurveAction(man)
   local s = ""
   if (man.IsContiCurve) then
      if (man.project == "zeekr") then
         s = "ทางโค้งต่อเนื่องข้างหน้า โปรดขับขี่ด้วยความระมัดระวัง"
      else
         s = "ข้างหน้ามีโค้งหักศอกอย่างต่อเนื่อง โปรดขับรถด้วยความระมัดระวัง"
      end
   else
      if (man.project == "zeekr") then
         s = "ทางโค้งข้างหน้า โปรดขับขี่ด้วยความระมัดระวัง"
      else
         s = "ข้างหน้ามีโค้งหักศอก โปรดขับรถด้วยความระมัดระวัง"
      end
   end
   return s
end

function processWNTrafficAction(man)
   local s = ""
   local content = getTrafficContent(man.TrafficKind)
   if (man.project == "zeekr") then
      if (man.TrafficKind == 0) then
         content = "การจราจรแออัด"
      elseif (man.TrafficKind == 3) then
         content = "งานก่อสร้าง"
      elseif (man.TrafficKind == 4) then
         content = "ทางแคบลง"
      elseif (man.TrafficKind == 6) then
         content = "รถเหินน้ำ"
      elseif (man.TrafficKind == 10) then
         content = "ทางปิด"
      elseif (man.TrafficKind == 11) then
         content = "สภาพอากาศเลวร้าย"
      elseif (man.TrafficKind == 12) then
         content = "มีคนบนถนน"
      elseif (man.TrafficKind == 13) then
         content = "เริ่มควบคุมความสูง"
      elseif (man.TrafficKind == 14) then
         content = "กฎจราจรเปลี่ยน"
      elseif (man.TrafficKind == 16) then
         content = "ไม่สามารถให้บริการได้"
      elseif (man.TrafficKind == 17) then
         content = "รถเคลื่อนที่ได้ช้า"
      elseif (man.TrafficKind == 18) then
         content = "ท้ายแถวอันตราย"
      elseif (man.TrafficKind == 19) then
         content = "เสี่ยงต่อไฟไหม้"
      elseif (man.TrafficKind == 20) then
         content = "เวลาล่าช้า"
      elseif (man.TrafficKind == 21) then
         content = "ด่านตรวจ"
      elseif (man.TrafficKind == 22) then
         content = "อุปกรณ์บนท้องถนนมีความผิดปกติ"
      end
   end
   if (content ~= "") then
      local distM = man.DistM
      local distKm = getIntNumber(distM / 1000)
      if ((distKm >= 1) and testDistance2(distM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
         if (man.project == "zeekr") then
            s = concEx("ในอีก", spellDistanceAndUnit(man, countryCode, unitArray), " ")
         else
            s = concEx("ในประมาณ", spellDistanceAndUnit(man, countryCode, unitArray), " ")
         end
      end
      if (man.project == "zeekr") then
         s = concEx(content, s)
      else
         s = concEx(s, content)
      end
   end
   return s
end

function processWNOverspeedAction(man)
   local s = ""
   local speedLimit = man.SpeedLimit
   local carSpeed = man.CarSpeed
   local speedLimitStr = ""
   local carSpeedStr = ""
   local unit = "กิโลเมตรต่อชั่วโมง"  -- km/h
   if (man.DistUnit == "mile") then
      speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
      unit = "ไมล์ต่อชั่วโมง"  -- mph
   end
   speedLimitStr = spellNumberEx(speedLimit)
   carSpeedStr = spellNumberEx(carSpeed)
   s = concEx("จำกัดความเร็ว", speedLimitStr, unit, " ความเร็วของคุณเกินกำหนดแล้ว")
   if (man.project == "zeekr") then
      s = concEx("ความเร็วในการขับขี่ของคุณเกินความเร็วสูงสุดที่", speedLimitStr, unit)
   end
   return s
end

function processWNBorderAction(man)
   local s = ""
   if (man.BorderName ~= "") then
      local borderName = " " .. man.BorderName .. " "
      s = concEx("กำลังเข้าสู่", borderName)
   end
   return s
end

function processWNInappLaneAction(man)
   local s = ""
   if (man.InappLaneReason == 0) then
      return s
   end
   if (man.InappLaneReason == 1) then
      -- no entry
      if (spellTimeForInappLane(man.FromHour, man.FromMinutes) ~= "" and spellTimeForInappLane(man.ToHour, man.ToMinutes) ~= "") then
         s = "เลนปัจจุบัน  ในช่วงเวลา " .. spellTimeForInappLane(man.FromHour, man.FromMinutes) .. "~" .. spellTimeForInappLane(man.ToHour, man.ToMinutes) .. "  เป็น  เลน  ห้ามผ่าน"
      end
   else
      -- other reasons
      if (spellInappLaneReason(man.InappLaneReason) ~= "") then
         s = "เลนปัจจุบัน  เป็น เลนกำหนดใช้เฉพาะ " .. spellInappLaneReason(man.InappLaneReason)
      end
   end
   return s
end

function processWNNarrowLaneAction(man)
   local s = ""
   if (man.NarrowLaneVoiceType == 1) then
      if (man.NarrowLaneDir == 1) then
         s = "ข้างหน้า เลนซ้ายแคบลง"
      elseif (man.NarrowLaneDir == 2) then
         s = "ข้างหน้า เลนขวาแคบลง"
      elseif (man.NarrowLaneDir == 3) then
         s = "ข้างหน้า เลนทั้งสองด้านแคบลง"
      else
         return s
      end
      s = s .. " โปรดขับรถด้วยความระมัดระวัง"
   elseif (man.NarrowLaneVoiceType == 2) then
      if (man.NarrowLaneDir == 1) then
         s = "ซ้าย"
      elseif (man.NarrowLaneDir == 2) then
         s = "ขวา"
      else
         return s
      end
      s = "ทางข้างหน้าแคบลง โปรดเปลี่ยนเลนไปทางด้าน" .. s
   end
   return s
end

function processEVLowBattery(man)---not given
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      s = "แบตเตอรี่อ่อนกรุณาชาร์จ"
   end
   return s
end

function processEVFewChargingStations(man)---not given
   local s = ""
   if (man.GuidanceLevel > 1) then
      s = "มีสถานีชาร์จน้อย กรุณาเรียกเก็บเงินให้ทันเวลา "
   end
   return s
end

function processSRPSuccess()
   local s = "การวางแผนเส้นทางการชาร์จอัจฉริยะเสร็จสมบูรณ์"
   return s
end

function processSRPFail()
   local s = "Battery level not sufficient to arrive at the destination. No charging station found along the route, please charge timely"
   return s
end

function processBeepDRGPopup(man)---not given
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      local minutes = spellNumberEx(man.Minutes)
      s = concEx("พบเส้นทางใหม่", minutes, "นาทีจะถูกบันทึก")
      if (man.project == "zeekr") then
         s = concEx("พบเส้นทางใหม่ จะประหยัดเวลาได้", minutes, "นาที")
      end
   end
   return s
end

function spellTimeForInappLane(hour, min)
   local s = ""
   if (isNumber(hour) and isNumber(min)) then
      if (min == 0) and isInRanges(hour, 0, 9) then
         s = "0" .. hour .. ".00 น "
      elseif (min == 0) and isInRanges(hour, 10, 23) then
         s = hour .. ".00 น "
      else
         s = hour .. " " .. min .. ""
      end
   end
   return s
end

function spellInappLaneReason(reason)
   local s = ""
   if (reason == 2) then
      s = "รถที่มีผู้โดยสารมาก"
   elseif (reason == 3) then
      s = "รถแท็กซี่"
   elseif (reason == 4) then
      s = "รถเมล์"
   elseif (reason == 5) then
      s = "รถพยาบาล"
   end
   return s
end

function getTrafficContent(kind)
   local s = ""
   if (kind == 0) then
      s = "มีช่วงที่รถติด"
   elseif (kind == 1) then
      s = "มีสิ่งกีดขวาง"
   elseif (kind == 2) then
      s = "มีอุบัติเหตุเกิดขึ้น"
   elseif (kind == 3) then
      s = "มีงานก่อสร้างถนน"
   elseif (kind == 4) then
      s = "มีเลนแคบ ๆ"
   elseif (kind == 5) then
      s = "ระวังถนนลื่น"
   elseif (kind == 6) then
      s = "ระวังรถไถลบนพื้นเปียก"
   elseif (kind == 7) then
      s = "มีไฟ"
   elseif (kind == 8) then
      s = "ระวังถนนทรุดตัว"
   elseif (kind == 9) then
      s = "มีรถเสีย"
   elseif (kind == 10) then
      s = "มีการปิดถนน"
   elseif (kind == 11) then
      s = "มีสภาพอากาศที่รุนแรง"
   elseif (kind == 12) then
      s = "มีคนซุ่มซ่าม"
   elseif (kind == 13) then
      s = "มีระบบเตือนความสูงเกินกำหนด"
   elseif (kind == 14) then
      s = "มีการเปลี่ยนแปลงกฎจราจร"
   elseif (kind == 15) then
      s = "มีกิจกรรมสำคัญ"
   elseif (kind == 16) then
      s = "มีบริการที่ไม่สามารถใช้งานได้"
   elseif (kind == 17) then
      s = "มีรถเคลื่อนที่ช้า"
   elseif (kind == 18) then
      s = "มีจุดสิ้นสุดที่อันตราย"
   elseif (kind == 19) then
      s = "มีความเสี่ยงเกิดไฟไหม้"
   elseif (kind == 20) then
      s = "มีมาตรการรักษาความปลอดภัยพิเศษ"
   elseif (kind == 21) then
      s = "มีด่านตำรวจ"
   elseif (kind == 22) then
      s = "มีอุปกรณ์บนถนนชำรุด"
   end
   return s
end

function testDistance(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 999
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function testDistance2(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 20000
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function testDistance3(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 5000
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function testDistance4(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 999999
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function processMainAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel

   -- check warning voice
   if (man.Action == "dp_traffic_light" or
      man.Action == "dp_recom_lane" or
      man.Action == "dp_sapa" or
      man.Action == "wn_high_accident_area" or
      man.Action == "wn_school_zone" or
      man.Action == "wn_railway" or
      man.Action == "wn_dest_parking" or
      man.Action == "wn_dest_charging_station" or
      man.Action == "wn_tunnel" or
      man.Action == "wn_gps" or
      man.Action == "wn_lane_merge" or
      man.Action == "wn_traffic_camera" or
      man.Action == "wn_curve" or
      man.Action == "wn_traffic" or
      man.Action == "wn_overspeed" or
      man.Action == "wn_border" or
      man.Action == "wn_inapp_lane" or
      man.Action == "wn_narrow_lane" or
      man.Action == "ev_low_battery" or
      man.Action == "ev_few_charging_stations" or
      man.Action == "srp_success" or
      man.Action == "srp_fail" or
      man.Action == "beep_drg_popup") then
      s = processWarningAction(man)
      return s
   end

   if (man.Action == "guidance_starts" or
      man.Action == "enter_better_route" or
      man.Action == "blocked_road" or
      man.Action == "time_regulation_road" or
      man.Action == "no_guidance_active" or
      man.Action == "drive_to_route" or
      man.Action == "ti_route_changed" or
      man.Action == "version") then
      s = processCommonAction(man)
      return s
   end

   if (man.HintProps.Hint.first_announcement) then
      s = concEx(s, processFirstAnnouncement(man))
      man._NoCombination = true
      return s
   end

   s = concEx(s, processNormalAction(man))
   return s
end

function processCommonAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.Action == "guidance_starts") then
      if (man.project ~= "zeekr" or guidanceLevel > 1) then
         s = "โปรดปฏิบัติตามกฎจราจร "
      end
   elseif (man.Action == "enter_better_route") then
      if (guidanceLevel > 1) then
         s = "ได้เข้าสู่เส้นทางที่ดีกว่าแล้ว "
      else
         s = ""
      end
   elseif (man.Action == "blocked_road") then
      if (guidanceLevel > 1) then
         s = "เส้นทางข้างหน้ารถติด นำทางไปสู่เส้นทางใหม่ "
      else
         s = ""
      end
   elseif (man.Action == "time_regulation_road") then
      if (guidanceLevel > 1) then
         s = "ข้างหน้ามีการกำหนดเวลาวิ่งรถ นำทางไปสู่เส้นทางใหม่ "
      else
         s = ""
      end
   elseif (man.Action == "no_guidance_active") then
      s = "โปรดตั้งค่าเส้นทาง "
   elseif (man.Action == "drive_to_route") then
      s = "โปรดไปตามถนน การนำทางไปจุดหมายปลายทางกำลังเริ่มต้น "
   elseif (man.Action == "ti_route_changed") then
      s = "อัปเดตเส้นทางที่เหมาะสมแล้ว "
   elseif (man.Action == "version") then  --special version info
      s = getVersionInfo(man)
   end
   if (#s > 0) then
      man._NoCombination = true
   end
   return s
end

function processFirstAnnouncement(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   local DestNumber = man.HintProps.CustomDestNumber
   if (DestNumber == nil or getIntNumber(DestNumber) > 100) then
      DestNumber = getIntNumber(man.TotalNumberOfDestinations) - getIntNumber(man.DestinationIndex)
   else
      DestNumber = getIntNumber(DestNumber)
   end
   if (guidanceLevel == 2) then
      if ((getIntNumber(man.TotalNumberOfDestinations) == 1) or (getIntNumber(man.DestinationIndex) == 0)) then
         s = concEx(s, " ", "เริ่มต้นนำทางไป จุดหมายปลายทาง")
         if (man.project == "zeekr") then
            s = concEx(s, " ", "เริ่มการนำทาง")
         end
      elseif (isInRanges(DestNumber, 1, 5)) then
         s = concEx(s, " ", "เริ่มต้นนำทางไป จุดหมายปลายทาง", spellNumberEx(DestNumber))
         if (man.project == "zeekr") then
            s = concEx(s, " ", "เริ่มการนำทางไปยังจุดที่", spellNumberEx(DestNumber))
         end
      end
      local aTime = spellTime(man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode)
      if (#aTime > 0) then
         s = concEx(s .. " ", aTime)
      end

      s = concEx(s, ".", processWeatherTypesString(man))
   end

   if (man.Action == "roundabout_leave" and
      man.Stage == man.FirstStage and
      man.StageClass == man.FirstStageClass) then
      s = concEx(s, processRoundaboutAction(man))
   end
   return s
end

function processWeatherTypesString(man)
   local s = ""
   if (getTableValueCount(man.WeatherTypes) > 0) then
      local weatherTypeCount = 1;
      if (man.project == "zeekr") then
         s = concEx(s, "สภาพอากาศที่จุดหมายปลายทาง:")
      else
         s = concEx(s, "สภาพอากาศที่ปลายทางคือ")
      end
      for key, weatherType in pairsSortedByKeys(man.WeatherTypes) do
         if (weatherType ~= nil) then
            if (weatherTypeCount > 1) then
               s = concEx(s, "ถึงมีถึงมี")
            end
            local weatherTypeStr = spellWeatherType(weatherType.weather_type)
            if (man.project == "zeekr") then
               if (weatherType.weather_type == 1) then
                  weatherTypeStr = "แดดจัด"
               elseif (weatherType.weather_type == 2) then
                  weatherTypeStr = "เมฆมาก"
               elseif (weatherType.weather_type == 3) then
                  weatherTypeStr = "มีเมฆเต็มท้องฟ้า"
               elseif (weatherType.weather_type == 4) then
                  weatherTypeStr = "ฝนตกปรอยๆ"
               elseif (weatherType.weather_type == 5) then
                  weatherTypeStr = "พายุฝนฟ้าคะนอง"
               elseif (weatherType.weather_type == 6) then
                  weatherTypeStr = "พายุฝนฟ้าคะนองและลูกเห็บ"
               elseif (weatherType.weather_type == 7) then
                  weatherTypeStr = "ฝนหิมะ"
               elseif (weatherType.weather_type == 11) then
                  weatherTypeStr = "พายุ"
               elseif (weatherType.weather_type == 12) then
                  weatherTypeStr = "พายุหนัก"
               elseif (weatherType.weather_type == 13) then
                  weatherTypeStr = "พายุรุนแรง"
               elseif (weatherType.weather_type == 16) then
                  weatherTypeStr = "ฝนตกปานกลางถึงหนัก"
               elseif (weatherType.weather_type == 17) then
                  weatherTypeStr = "ฝนตกหนักถึงพายุ"
               elseif (weatherType.weather_type == 18) then
                  weatherTypeStr = "พายุถึงพายุหนัก"
               elseif (weatherType.weather_type == 19) then
                  weatherTypeStr = "พายุหนักถึงพายุรุนแรง"
               elseif (weatherType.weather_type == 20) then
                  weatherTypeStr = "หิมะโปรยปราย"
               elseif (weatherType.weather_type == 26) then
                  weatherTypeStr = "หิมะตกปานกลางถึงหนัก"
               elseif (weatherType.weather_type == 28) then
                  weatherTypeStr = "มีหมอกลง"
               elseif (weatherType.weather_type == 30) then
                  weatherTypeStr = "หมอกหนาทึบ"
               elseif (weatherType.weather_type == 31) then
                  weatherTypeStr = "หมอกลงจัด"
               elseif (weatherType.weather_type == 32) then
                  weatherTypeStr = "หมอกหนาทึบมาก"
               elseif (weatherType.weather_type == 33) then
                  weatherTypeStr = "ฝุ่น"
               elseif (weatherType.weather_type == 34) then
                  weatherTypeStr = "ทราย"
               elseif (weatherType.weather_type == 38) then
                  weatherTypeStr = "หมอกแดดรุนแรง"
               elseif (weatherType.weather_type == 39) then
                  weatherTypeStr = "หมอกแดดอันตราย"
               end
            end
            if (weatherTypeStr == "") then
               return ""
            else
               s = concEx(s , weatherTypeStr)
            end
            weatherTypeCount  = weatherTypeCount + 1;
         end
      end
   end
   return s
end

function processNormalAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel

   if (man.HintProps.Hint.manual_request) then
      man._NoCombination = true
      guidanceLevel = 2 -- priority boost
   end

   if (guidanceLevel == 1) or (guidanceLevel == 3) then
      s = processActionSimpleMode(man)
   elseif (guidanceLevel == 2) then
      s = processActionDetailMode(man)
   end

   if (man.HintProps.Hint.manual_request) then
      if ((s == nil or #s == 0) and (man.FromProps.Area.off_road ~= true)) then -- when no other announcement can be generated
         if (man.project == "zeekr") then
            s = "โปรดขับต่อไปบนเส้นทางที่ไฮไลต์"
         else
            s = "โปรดไปตามถนน"
         end
      end

      local aTime = spellTime(man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode)
      if ((#aTime > 0) and (#s > 0)) then
         s = concEx(s .. " ", aTime)
      elseif ((#aTime > 0) and (#s == 0)) then
         s = concEx("", aTime)
      end
   end
   return s
end

function processActionSimpleMode(man)
   local s = ""
   if (man.Action == "roundabout" or
      man.Action == "roundabout_leave" or
      man.Action == "destination" or
      man.HintProps.Hint.along_main_road or
      (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) or
      (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel))) then
      s = processSpecialManeuverSimple(man)
      return s
   else
      s = processNormalManeuverSimple(man)
   end
   if (s ~= "") then
      if (man.Stage == 1 and man.StageClass == "just_before") then
         man._NoCombination = true
      end
   end
   return s
end

function processSpecialManeuverSimple(man)
   local s = ""
   if (man.Action == "enter") then
      if (man.Stage <= 2) then
         if (man.Stage == 2 or
            (man.FirstStage == man.Stage and man.StageClass == man.FirstStageClass)) then
            if (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) then
               s = processTollAction(man)
            elseif (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel)) then
               s = processTunnelAction(man)
            end
         end
      end
   elseif (man.Action == "roundabout") then
      if (man.Stage <= 2) then
         if (man.Stage == 2 or
            (man.Stage == 1 and man.StageClass == "roundabout_exit") or
            (man.Stage == 1 and man.DerivedBegin) or
            (man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass)) then
            s = processRoundaboutAction(man)
         end
      end
   elseif (man.Action == "roundabout_leave") then
      if (man.Stage == 2 or
         (man.Stage == 1 and man.StageClass == "roundabout_exit") or
         (man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass)) then
         s = processRoundaboutAction(man)
      end
   elseif (man.HintProps.Hint.along_main_road) then
      if (man.Stage <= 2) then
         if (man.Stage == 2 or
            (man.FirstStage == man.Stage and man.StageClass == man.FirstStageClass)) then
            s = processAlongMainRoadSimpleVoice(man)
         end
      end
   elseif (man.Action == "destination") then
      s = processDestinationAction(man, false)
   end
   return s
end

function processAlongMainRoadSimpleVoice(man)
   local s = ""
   if (man.project == "zeekr") then
      s = "ขับต่อไปตามถนนหลัก"
   else
      s = "ไปตามเส้นทางหลักต่อไป"
   end
   -- along main road send action phase sentence at approach phase when the mode is simple
   s = "เตรียม" .. s
   return s
end

function processNormalManeuverSimple(man)
   local s = ""

   local isMotorway = false
   if (man.FromProps.Area.motorway) then
      isMotorway = true
   end

   -- normal maneuvers begin and prpare phase doesn't speak at simple mode
   if (man.Stage <= 2) then
      if (man.Stage == 2 or
         (man.Stage == 1 and man.StageClass == "just_before") or
         (man.FirstStage == man.Stage and man.StageClass == man.FirstStageClass)) then
         if ( (not man.HintProps.Hint.first_maneuver) and
             (not man.HintProps.Hint.first_announcement) and
             (testBeginDistance(isMotorway, man))) then
               s = processFollowRoadAction(man)
         elseif (man.HintProps.Hint.motorway_entry) then
            if (man.project == "zeekr" and (man.Stage == 2 or man.StageClass == "just_before") and man.GuidanceLevel == 1) then
               s = ""
            else
               s = processMotorwayEnterAction(man)
            end
         elseif (man.Action == "intersection") or (man.Action == "fork") or (man.Action == "roundabout_enter") then
            if (man.Stage == 1 and man.StageClass ~= "just_before") then
               s = ""
            elseif (man.project == "zeekr" and (man.Stage == 2 or man.StageClass == "just_before") and man.GuidanceLevel == 1) then
               s = ""
            else
               s = processIntersectionAction(man)
            end
         elseif (man.Action == "exit") then
            if (man.Stage == 1 and man.StageClass ~= "just_before") then
               s = ""
            elseif (man.project == "zeekr" and (man.Stage == 2 or man.StageClass == "just_before") and man.GuidanceLevel == 1) then
               s = ""
            else
               s = processIntersectionAction(man)
            end
         elseif (man.StageClass ~= "just_before") then
            if (man.Action == "follow_road") then
               s = processFollowRoadAction(man)
            elseif (man.Action == "lane_merge") then
               s = processLaneMergeAction(man)
            end
         end
      end
   end
   return s
end

function processActionDetailMode(man)
   local s = ""

   local isMotorway = false
   if (man.FromProps.Area.motorway) then
      isMotorway = true
   end

   if ( (not man.HintProps.Hint.first_maneuver) and
       (not man.HintProps.Hint.first_announcement) and
       (testBeginDistance(isMotorway, man))) then
      s = processFollowRoadAction(man)
   elseif (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) then
      s = processTollAction(man)
   elseif (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel)) then
      s = processTunnelAction(man)
   elseif (man.HintProps.Hint.along_main_road) then
      if ((man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass) or (man.Stage == 1 and man.StageClass ~= "just_before")) then
         s = processIntersectionAction(man)
      else
         man._NoCombination = true
      end
   elseif (man.Action == "roundabout" or man.Action == "roundabout_leave") then
      s = processRoundaboutAction(man)
   elseif (man.HintProps.Hint.motorway_entry) then
      s = processMotorwayEnterAction(man)
   elseif (man.Action == "intersection") or (man.Action == "fork") or (man.Action == "roundabout_enter") then
      s = processIntersectionAction(man)
   elseif (man.Action == "exit") then
      s = processIntersectionAction(man)
   elseif (man.StageClass ~= "just_before") then
      if (man.Action == "destination") then
         s = processDestinationAction(man, false)
      elseif (man.Action == "follow_road") then
         s = processFollowRoadAction(man)
      elseif (man.Action == "lane_merge") then
         s = processLaneMergeAction(man)
      end
   end
   return s
end

function processWarningAction(man)
   local s = ""
   if (man.Action == "dp_traffic_light") then
      s = processDPTrafficLightAction(man)
   elseif (man.Action == "dp_recom_lane") then
      s = processDPRecomLaneAction(man)
   elseif (man.Action == "dp_sapa") then
      s = processDPSAPAAction(man)
   elseif (man.Action == "wn_high_accident_area") then
      s = processWNHighAccidentAreaAction()
      if (man.project == "zeekr") then
         s = "เกิดอุบัติเหตุบ่อยครั้งข้างหน้า โปรดขับขี่ด้วยความระมัดระวัง"
      end
   elseif (man.Action == "wn_school_zone") then
      s = processWNSchoolZoneAction()
      if (man.project == "zeekr") then
         s = "เขตโรงเรียนข้างหน้า โปรดขับขี่ด้วยความระมัดระวัง"
      end
   elseif (man.Action == "wn_railway") then
      s = processWNRailwayAction()
      if (man.project == "zeekr") then
         s = "ทางข้ามทางรถไฟข้างหน้า โปรดขับขี่ด้วยความระมัดระวัง"
      end
   elseif (man.Action == "wn_dest_parking") then
      s = processWNDestParkingAction(man)
      if (man.project == "zeekr") then
         s = "พบที่จอดรถใกล้จุดหมายปลายทาง แตะไป"
      end
   elseif (man.Action == "wn_dest_charging_station") then
      s = processWNDestChargingStationAction()
      if (man.project == "zeekr") then
         s = "พบสถานีชาร์จรถยนต์ไฟฟ้าใกล้กับจุดหมายปลายทาง"                      --Recommendations of charging stations near the destination
      end
   elseif (man.Action == "wn_tunnel") then
      s = processWNTunnelAction()
   elseif (man.Action == "wn_gps") then
      s = processWNGpsAction()
   elseif (man.Action == "wn_lane_merge") then
      s = processWNLaneMergeAction(man)
   elseif (man.Action == "wn_traffic_camera") then
      s = processWNTrafficCameraAction(man)
   elseif (man.Action == "wn_curve") then
      s = processWNCurveAction(man)
   elseif (man.Action == "wn_traffic") then
      s = processWNTrafficAction(man)
   elseif (man.Action == "wn_overspeed") then
      s = processWNOverspeedAction(man)
   elseif (man.Action == "wn_border") then
      s = processWNBorderAction(man)
   elseif (man.Action == "wn_inapp_lane") then
      s = processWNInappLaneAction(man)
   elseif (man.Action == "wn_narrow_lane") then
      s = processWNNarrowLaneAction(man)
   elseif (man.Action == "ev_low_battery") then
      s = processEVLowBattery(man)
      if (man.project == "zeekr") then
         s = "แบตเตอรี่ต่ำ โปรดชาร์จให้ทันเวลา"
      end
   elseif (man.Action == "ev_few_charging_stations") then
      s = processEVFewChargingStations(man)
      if (man.project == "zeekr") then
         s = "มีสถานีชาร์จรถยนต์ไฟฟ้าไม่กี่แห่ง โปรดชาร์จให้ทันเวลา"
      end
   elseif (man.Action == "srp_success") then
      s = processSRPSuccess()
   elseif (man.Action == "srp_fail") then
      s = processSRPFail()
      if (man.project == "zeekr") then
         s = "แบตเตอรี่ไม่พอที่จะไปถึงจุดหมายปลายทาง ไม่มีสถานีชาร์จรถยนต์ไฟฟ้าตามริมทาง โปรดชาร์จให้ทันเวลา"
      end
   elseif (man.Action == "beep_drg_popup") then
      s = processBeepDRGPopup(man)
   end
   return s
end

function processCombinedAction(man, mainString)
   local s = ""
   if (man.project == "zeekr" and man.Stage == 1 and guidanceLevel == 1) then
      return s
   end
   --other actions, no linguistic combination
   if (man.Action == "no_guidance_active") then
      s = processMainAction(man)
      s = concat(mainString, " ", s)
      return s
   end

   if (#mainString > 0) then
      if (   (isAvailable(man.previous))
         and (man.previous.Stage < 3) and (man.previous.StageClass ~= "just_before")) then
         if (man.Action == "intersection" or man.Action == "fork") then
            s = processIntersectionActionCombined(man)
         elseif (man.Action == "exit") then
            s = processIntersectionActionCombined(man)
         elseif (man.Action == "enter") then
            s = processEnterActionCombined(man)
         elseif (man.Action == "destination") then
            s = processDestinationAction(man, true)
         else
            s = ""
         end
      end
      s = concat(mainString, " จากนั้น", s)
   end
   return s
end

function split(str)
   local t={}
   local startPoint = 1
   local endPoint
   repeat
      local vStart, vEnd = string.find(str, "[^%s]+", startPoint)
      startPoint = vStart
      endPoint = vEnd
      if (startPoint) then
         table.insert(t, string.sub(str, startPoint, endPoint))
         startPoint = endPoint + 1
      end
   until (not startPoint)
   return t
end

function integrateAudioSeq(filename, completeWavPath)
   local s = ""
   if ((type(filename) == "string") and (#filename > 0)) then
      s = string.char(27) .. "\\audio=\"" .. completeWavPath .. filename .. "\"\\"
   end
   return s
end

local function getWaveFileTokens()
   local tokens =
   {
      --other
      ["Pingpong"] = {"PINPON_5"},
      ["Pingpong_AD"] = {"NTS_AD_kokuchi_"},
      ["Pong"] = {"PON_2"},
      ["Ponpon"] = {"PONPO-N"},
      --other
   }
   return tokens
end

local function getSoundFileTokens()
   local tokens =
   {
      -- sounds
      ["Pingpong"] = {"PINPON_5"},
      ["Pingpong_AD"] = {"PINPON_5"},
      ["Pong"] = {"PON_2"},
      ["Ponpon"] = {"PONPO-N"},
   }
   return tokens
end

function replaceTokens(promptTable, wavPath)
   local s = ""
   local dirSeparator = package.config:sub(1,1)
   local tokens = getWaveFileTokens()
   wavPath = convToString(wavPath)

   if (isNotEmpty(wavPath)) then
      if (string.sub(wavPath, -1) ~= dirSeparator) then
         wavPath = wavPath .. dirSeparator
      end
      local genericWavPath = wavPath .. "generic" .. dirSeparator

      local markTbl =
      {
         ["."] = ".",
         [","] = ",",
      }

      for _, promptPart in ipairs(promptTable) do
         local appendix = ""
         repeat
            local endChar = string.sub(promptPart, -1)
            local translation = markTbl[endChar]
            if (translation ~= nil) then
               appendix = translation .. appendix
               promptPart = string.sub(promptPart, 1, #promptPart - 1)
            end
         until (translation == nil)

         if (#promptPart > 0) then
            local replacementTbl = tokens[promptPart]
            if (replacementTbl ~= nil) then
               promptPart = ""
               for _, replacement in ipairs(replacementTbl) do
                  local rep = convToString(replacement)
                  if (#rep > 0) then
                     promptPart = promptPart .. integrateAudioSeq(rep .. ".wav", genericWavPath)
                  end
               end
               appendix = ""  -- no appendix, because in some cases point is pronounced as dot
            end
         end
         s = conc(s, promptPart .. appendix)
      end
   end
   return s
end

function main(...)
   local s = ""
   local isVersionRequest = false
   local previousMan
   local paramCount = select('#', ...)
   local mainStage = 0
   local mainVoiceDataPath = ""
   local mainUsePhoneme = true
   local isManualRequest = false

   logPrint(dump(...))
   for j = 1, paramCount do
      local man = select(j, ...)

      if (type(man) == "table") then

         if ((j + 1) <= paramCount) then
            man.next = select(j + 1, ...)
         end
         man.DirOClock = getIntNumber(man.DirOClock) % 12
         man.Stage = getIntNumber(man.Stage)
         local prototypeManTbl = {
            ["FromProps"] = {["Area"] = {}},
            ["ToProps"] = {["Area"] = {}},
            ["TowardsProps"] = {["Area"] = {}},
            ["HintProps"] = {["Hint"] = {}}}
         setPrototypeTable(man, prototypeManTbl)

         if (j == 1) then
            mainVoiceDataPath = convToString(man.HintProps.VoiceDataPath)
            mainUsePhoneme = (man.HintProps.Hint.use_phoneme == true)
            s = processMainAction(man)
            mainStage = getIntNumber(man.Stage)
            isVersionRequest = (man.Action == "version")
            isManualRequest = man.HintProps.Hint.manual_request
         else
            man.previous = previousMan
            if ((#s == 0) and (mainStage == 2)) then
               man.Stage = mainStage
               man.Distance = man.Distance + previousMan.Distance
               s = processMainAction(man)
            else
               s = processCombinedAction(man, convToString(s))
            end
            man.previous = nil
         end
         man.next = nil
         if (man._NoCombination) then
            break
         end
         previousMan = man
      end
   end
   if ((#s > 0) and (not isVersionRequest)) then
      s = trim(s)
      s = s .. " "
   end
   if ((replaceWithFilenames) and (not isVersionRequest) and (#mainVoiceDataPath > 0) and (mainUsePhoneme)) then
      local v = s
      local t = split(s)
      s = replaceTokens(t, mainVoiceDataPath)
      if (s ~= v) then
         logPrint("IntermediateResult: " .. v)
      end
      if (isManualRequest and #s > 0) then
         s = string.char(27) .. "\\mrk=REPEAT\\" .. s -- Marking for the BL to identify a repeated announcement
      end
   end
   return s
end

function dump(...)
   local s = ""
   for j = 1, select('#', ...) do
      local object = select(j, ...)
      s =  conc(s, "Parameter ", j, ": ", dumpToString(object))
   end
   return s
end

function logPrint(message, isWarning)
   if (  ((not _G.logOutputDisabled) or (isWarning))
      and (type(message) == "string")
      and (#message > 0)) then

      if (_G.logOutput) then
         _G.logOutput(message)
      else
         print(message)
      end
   end
end

function changeWaveFileReplacement( enable)
   replaceWithFilenames = (enable == true)
end

local startlua = {_NAME="start"}
startlua.main = main
startlua.dump = dump
startlua.spellSpecNr = spellNumber
startlua.spellNumber = spellNumber
startlua.spellTime = spellTime
startlua.spellDistanceAndUnit = spellDistanceAndUnit
startlua.getWaveFileTokens = getSoundFileTokens
startlua.getSoundFileTokens = getSoundFileTokens
startlua.changeWaveFileReplacement = changeWaveFileReplacement

return startlua