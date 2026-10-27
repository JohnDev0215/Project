-- import laser lua library functions
require("lalulib")

--Simple mode : guidanceLevel = 1
--Detail mode : guidanceLevel = 2
--Standard mode : guidanceLevel = 3

countryCode = "FRA"
unitArray = {"mètres", "mètres", "kilomètre", "kilomètres", "kilomètres", "pieds", "pieds", "cours", "cours", "mile", "miles", "un quart de", "un demi", "trois quarts de"}

function getVersionInfo()
   local t =
   {
      ["country"] = "FRA",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "fra",             -- ISO 639-3  alpha-3 language code
      ["version"] = "2.23.1",       -- version
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
      s = "ensoleillé"
   elseif (weatherTypeNum == 2) then
      s = "Nuageux"
   elseif (weatherTypeNum == 3) then
      s = "Couvert"
   elseif (weatherTypeNum == 4) then
      s = "Averses"
   elseif (weatherTypeNum == 5) then
      s = "Orage"
   elseif (weatherTypeNum == 6) then
      s = "Orage avec grêle"
   elseif (weatherTypeNum == 7) then
      s = "Pluie de neige"
   elseif (weatherTypeNum == 8) then
      s = "Pluie légère"
   elseif (weatherTypeNum == 9) then
      s = "Pluie modérée"
   elseif (weatherTypeNum == 10) then
      s = "Forte pluie"
   elseif (weatherTypeNum == 11) then
      s = "Tempête"
   elseif (weatherTypeNum == 12) then
      s = "Forte tempête"
   elseif (weatherTypeNum == 13) then
      s = "Violente tempête"
   elseif (weatherTypeNum == 14) then
      s = "Pluie verglaçante"
   elseif (weatherTypeNum == 15) then
      s = "Pluie légère à modérée"
   elseif (weatherTypeNum == 16) then
      s = "Tempête modérée à forte"
   elseif (weatherTypeNum == 17) then
      s = "Forte pluie à tempête"
   elseif (weatherTypeNum == 18) then
      s = "Tempête à forte tempête"
   elseif (weatherTypeNum == 19) then
      s = "Tempête forte à violente"
   elseif (weatherTypeNum == 20) then
      s = "Averse de neige"
   elseif (weatherTypeNum == 21) then
      s = "Chutes de neige légères"
   elseif (weatherTypeNum == 22) then
      s = "Chutes de neige modérées"
   elseif (weatherTypeNum == 23) then
      s = "Chutes de neige fortes"
   elseif (weatherTypeNum == 24) then
      s = "Tempête de neige"
   elseif (weatherTypeNum == 25) then
      s = "Chutes de neige légères à modérées"
   elseif (weatherTypeNum == 26) then
      s = "Chutes de neige modérées à fortes"
   elseif (weatherTypeNum == 27) then
      s = "Chutes de neige fortes à tempête de neige"
   elseif (weatherTypeNum == 28) then
      s = "Brumeux"
   elseif (weatherTypeNum == 29) then
      s = "Brouillard dense"
   elseif (weatherTypeNum == 30) then
      s = "Brouillard épais et dense"
   elseif (weatherTypeNum == 31) then
      s = "Brouillard épais"
   elseif (weatherTypeNum == 32) then
      s = "Brouillard dense très fort"
   elseif (weatherTypeNum == 33) then
      s = "Poussière"
   elseif (weatherTypeNum == 34) then
      s = "Sable"
   elseif (weatherTypeNum == 35) then
      s = "Tempête de sable"
   elseif (weatherTypeNum == 36) then
      s = "Brume"
   elseif (weatherTypeNum == 37) then
      s = "Brume modérée"
   elseif (weatherTypeNum == 38) then
      s = "Brume forte"
   elseif (weatherTypeNum == 39) then
      s = "Brume dangereuse"
   else
      s = ""
   end
   return s
end

function spellRoughDirection(man)
   local s = ""
   if (man.DirOClock == 0) then
      s = "droit"
   elseif (isInRanges(man.DirOClock, 1, 5)) then
      s = "à droite"
   elseif (isInRanges(man.DirOClock, 7, 11)) then
      s = "à gauche"
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
         s = conc("légèrement", s)
      end
   elseif ((man.DirOClock == 2) or (man.DirOClock == 10)) then
      s = conc("légèrement", s)
   elseif (isInRanges(man.DirOClock, 4, 5, 7, 8)) then
      s = conc("fortement", s)
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
   local inString = ""
   if (man.project == "zeekr") then
     inString = "Dans"
   else
     inString = "À"
   end
   local guidanceLevel = man.GuidanceLevel
   if (man.StageClass == "just_before") then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         s = ""
      else
        if (man.project ~= "zeekr") then
           s = ""
        end
      end
   elseif (man.Stage == 1) then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         return s
      end
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         if (man.Distance > 0) then
            s = conc(s, "Dans", spellDistanceAndUnit(man, countryCode, unitArray)) -- In
         end
      else
        if (man.project == "zeekr") then
          s = "À présent" --Now
        else
         s = "Maintenant "  --Now
        end
      end
   else
      if (man.Stage > 2) and ((guidanceLevel == 1) or (guidanceLevel == 3)) then
         return s
      end
      if (man.Distance > 0) then
         s = conc(s, inString, spellDistanceAndUnit(man, countryCode, unitArray)) --In
      end
   end
   return s
end

function spellDistancePartAtEnd(man)
   local s = ""
   if (man.StageClass == "just_before") then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         s = ""
      else
        if (man.project == "zeekr") then
         s = " ici"
        end
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

   local keepStr = " serrer"
   if (man.project == "zeekr") then
     keepStr = " restez"
   end
   local straightStr = "allez tout droit"
   if (man.project == "zeekr") then
     straightStr = "continuez tout droit"
   end
   -- roundabout exit voice
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = conc(s, " tourner")
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "à gauche")
      else
         s = conc(s, "à droite")
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Continuer à suivre la route principale"
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = straightStr                                 --go straight on
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, " allez tou", spellRoughDirection(man))
         if (man.Nth > 0) then
            s = conc(s, spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "faire demi-tour"
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            if (isFromHighway) then
               s = ""
            else
               s = conc(s, "tourner", spellFineDirection(man))
            end
         else
            s = conc(s, keepStr, spellFineDirection(man))  --keep
         end
      else
         if (man.Action == "exit") then
            if (isFromHighway) then
               s = ""
            else
               s = conc(s, "sortez", spellFineDirection(man))
            end
         else
            s = conc(s, "sortez", spellFineDirection(man))
         end
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
          --  s = conc("", spellNthStreet(man.Nth, false))
         end
      end
   end
   return s
end

function spellRoundaboutDirection(man)
   -- roundabout exit voice
   local s = ""
   if (man.HintProps.Hint.left_hand_traffic) then
      s = conc(s, "à gauche")
   else
      s = conc(s, "à droite")
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
      s = man.TowardsProps.Name
   end
   return s
end

function getExitName(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (((guidanceLevel > 1 and man.Stage > 1) or (guidanceLevel == 1 and man.Stage == 2))
      and (isNotEmpty(man.HintProps.ExitName))) then
      s = man.HintProps.ExitName
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
         s = conc(s, " prendre la ", exitName, spellFineDirection(man)) --take exit
      end
      if (#signPostName > 0) then
         s = conc(s, " en direction de", signPostName)
      end
      if (man.Action == "exit") then
         if (#exitName > 0) then
            s = s
         elseif (#signPostName > 0) then
            s = conc("sortir vers la", spellFineDirection(man), s) --exit to the
         else
            s = conc("sortir vers la", spellFineDirection(man)) -- exit to the
         end
      end
      return s
   else
      local isRamp = man.ToProps.Area.ramp
      if (isRamp and #signPostName > 0) then
         s = conc(s, " en direction de", signPostName)
      else
         if (useRoadNameOrNumber) then
            local roadName = getRoadNameOrNumber(man)
            if (#roadName > 0) then
               s = conc(s, " sur", roadName)
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
         if (man.project == "zeekr" and man.TrafficLightIntersectionCount > 0) then
           s = "au niveau du"
         else
           s = "au" --at the
         end
         if (man.TrafficLightIntersectionCount > 0) then
            s = conc(s, " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1), "feu")
         else
            s = s .. " feu"  --traffic light
         end
      else
         s = "au"
         s = conc(s, " ", spellOrdinalNumber(man.IntersectionCount + 1))
         s = s .. " croisement"  --intersection
      end
   elseif (man.ContainTrafficLight and (not isHighway)) then
      if ((guidanceLevel == 1) and (man.StageClass == "just_before")) or ((guidanceLevel == 3) and (man.StageClass == "just_before")) or (guidanceLevel == 2) then
         s = "au" --at the
         if (man.TrafficLightIntersectionCount > 0) then
            s = conc(s, " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1), "feu")
         else
            s = s .. " feu"  --traffic light
         end
      end
   elseif (isHighway and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      s = "au"
      s = conc(s, " ", spellOrdinalNumber(man.IntersectionCount + 1))
      s = s .. " croisement" --intersection
   end
   return s
end

function spellPrepositionAndGender(props)
   local s = ""
   if (props.Preposition == "into") or (props.Preposition == "onto") then
      s = "sur"
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
         s = man.ToProps.Name
      end
   end
   return s
end

function spellOrdinalNumber(number)
   local s = ""
   if (number == 1) then
      s = "premier"
   elseif (number == 2) then
      s = "deuxième"
   elseif (number == 3) then
      s = "troisième"
   elseif (number == 4) then
      s = "quatrième"
   elseif (number == 5) then
      s = "cinquième"
   elseif (number == 6) then
      s = "sixième"
   elseif (number == 7) then
      s = "septième"
   elseif (number == 8) then
      s = "huitième"
   elseif (number == 9) then
      s = "neuvième"
   elseif (number == 10) then
      s = "dixième"
   elseif (number == 11) then
      s = "onzième"
   elseif (number == 12) then
      s = "douzième"
   else
      s = tostring(number) .. "."
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
      if (hour > 1) then
         s = hour .. " heures"
      elseif (hour == 1) then
         s = hour .. " heure"
      end

      if (minutes > 1) then
         s = s .. " " .. minutes .. " minutes"
      elseif(minutes == 1) then
         s = s .. " " .. minutes .. " minute"
      end
   end
   if (#s > 0) then
      s = "Vous pouvez obtenir la destination à " .. s
   end
   return s
end

function spellTime(timeVal, isTwelveHoursClock, arrivalTimeMode, projectName)
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

         if (t.min == 0) and (t.hour == 0) then
            s = "0 heures"
         elseif (t.min == 0) and (t.hour == 1) then
            s = hour .. " heure"
         elseif (t.min == 0) and isInRanges(t.hour, 2, 23) then
            s = hour .. " heures"
         elseif (t.hour == 0) and (isInRanges(t.min, 1, 59)) then
            if (t.min == 1) then
               s = "0 heures une"
            elseif (t.min == 21) then
               s = "0 heures vingt-et-une"
            elseif (t.min == 31) then
               s = "0 heures trente-et-une"
            elseif (t.min == 41) then
               s = "0 heures quarante-et-une"
            elseif (t.min == 51) then
               s = "0 heures cinquante-et-une"
            else
               s = "0 heures " .. t.min
            end
         elseif (t.hour == 1) and (isInRanges(t.min, 1, 59)) then
            if (t.min == 1) then
               s = "1 heure une"
            elseif (t.min == 21) then
               s = "1 heure vingt-et-une"
            elseif (t.min == 31) then
               s = "1 heure trente-et-une"
            elseif (t.min == 41) then
               s = "1 heure quarante-et-une"
            elseif (t.min == 51) then
               s = "1 heure cinquante-et-une"
            else
               s = "1 heure " .. t.min
            end
         elseif (isInRanges(t.hour, 2, 23)) and (isInRanges(t.min, 1, 59)) then
            if (t.min == 1) then
               s = hour .. " heures une"
            elseif (t.min == 21) then
               s = hour .. " heures vingt-et-une"
            elseif (t.min == 31) then
               s = hour .. " heures trente-et-une"
            elseif (t.min == 41) then
               s = hour .. " heures quarante-et-une"
            elseif (t.min == 51) then
               s = hour .. " heures cinquante-et-une"
            else
               s = hour .. " heures " .. t.min
            end
         end

         if (isTwelveHoursClock) then
            local zeroClock = false
            local twelveHoursClockMark = string.lower(os.date("!%p", timeVal))
            if (t.min == 0) and (t.hour == 0) then
               s = "minuit"
               zeroClock = true
            elseif (t.min == 0) and (t.hour == 12) then
               s = "midi"
               zeroClock = true
            elseif (t.min == 0) then
               s = hour ..  ""
            elseif (isInRanges(t.min, 1, 9)) then
               s = hour .. ":" .. t.min
            else
               s = hour .. ":" .. t.min
            end
            if (twelveHoursClockMark == "am") and (not zeroClock) then
               s = s .. " a m"
            elseif (twelveHoursClockMark == "pm") and (not zeroClock) then
               s = s .. " p m"
            end
         end
      end
   end
   if (#s > 0) then
     if (projectName == "zeekr") then
       s = "Heure d'arrivée estimée " .. s
      else
       s = "Arrivée prévue à " .. s
      end
   end
   return s
end

function spellNthStreet(nth, isStraight)
   local s = ""
   if (nth == 1) then
      s = " à gauche"
   elseif (nth == 2) then
      s = " au centre"
   elseif (nth == 3) then
      s = " à droite"
   end
   if (isStraight) then
      s = "restez" .. s
   else
      s = "restez" .. s
   end
   return s
end

function processIntersectionAction(man)
   local s = spellDistancePart(man)
   s = conc(s, "", spellDirectionPart(man))
   s = conc(s, "", spellIntersectionCount(man))
   s = conc(s, "", spellNamesPart(man, true))

   s = s .. spellDistancePartAtEnd(man)
   return s
end

function spellDirectionPartWITHsoon(man)
   local s = ""
   local isFromHighway = man.FromProps.Area.motorway
   if (isFromHighway == nil) then
      isFromHighway = false
   end

   -- roundabout exit voice
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = conc(s, " tourner")
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "à gauche")
      else
         s = conc(s, "à droite")
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Continuer à suivre la route principale"
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = "allez tout droit"
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, " allez tou", spellRoughDirection(man))
         if (man.Nth > 0) then
            s = conc(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "faire demi-tour"
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            s = conc(s, "tourner", spellFineDirection(man))
         else
            s = conc(s, " serrer", spellFineDirection(man))
         end
      else
         s = conc(s, "tourner", spellFineDirection(man))
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
            s = conc(s, "", spellNthStreet(man.Nth, false))
         end
      end
   end
   return s
end

function spellDirectionPartWITHagain(man)
   local s = ""
   local isFromHighway = man.FromProps.Area.motorway
   if (isFromHighway == nil) then
      isFromHighway = false
   end

   -- roundabout exit voice
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = conc(s, " tourner encore")
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "à gauche")
      else
         s = conc(s, "à droite")
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Continuer à suivre la route principale"
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = "allez tout droit"
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, " allez tou", spellRoughDirection(man))
         if (man.Nth > 0) then
            s = conc(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "faire demi-tour"
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            s = conc(s, "tourner encore", spellFineDirection(man))
         else
            s = conc(s, " serrer", spellFineDirection(man))
         end
      else
         s = conc(s, "tourner encore", spellFineDirection(man))
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
            s = conc(s, "", spellNthStreet(man.Nth, false))
         end
      end
   end
   return s
end

function processIntersectionActionCombined(man)
   local s = ""
   s = spellDirectionPart(man)
   s = conc(s, spellNamesPart(man, false))

   if (#s > 0) then
      s = conc(" ", s)
      if (     (isAvailable(man.previous))
         and   (man.previous.Stage == 2)) then
         if (spellDirectionPart(man.previous) == spellDirectionPart(man)) then
            s = spellDirectionPartWITHagain(man)                     --again
            s = conc(s, spellNamesPart(man, false))
         else
            s = spellDirectionPartWITHsoon(man)               --soon
            s = conc(s, spellNamesPart(man, false))
         end
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  ((not man.previous.HintProps.Hint.along_main_road) and man.HintProps.Hint.along_main_road)) then
         s = conc(s, "")
      elseif (     (isAvailable(man.previous))
         and   (man.previous.Action == man.Action)
         and   (man.previous.DirOClock == man.DirOClock)) then
         s = spellDirectionPartWITHagain(man)                  --again
         s = conc(s, spellNamesPart(man, false))
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  (spellDirectionPart(man.previous) == spellDirectionPart(man))) then
         s = spellDirectionPartWITHagain(man)                     --again
         s = conc(s, spellNamesPart(man, false))
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
   local roundaboutStr = " Quittez le rond-point à la "
   if (man.Action == "roundabout_leave") then
      if (man.HintProps.Hint.first_announcement or
         (man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass)) then
         s = roundaboutStr .. spellOrdinalNumber(man.Nth) .. " sortie"
         return s
      end
   end
   if (man.Action == "roundabout" and ((man.Stage == 2 or man.Stage == 3) or (man.Stage == 1 and man.DerivedBegin))) then
      --s = "À " .. spellDistanceAndUnit(man, countryCode, unitArray) .. " entrer dans le rond-point"
      if (man.project == "zeekr" and man.Stage == 3) then
         s = "Rond-point à venir"
      else
         s = roundaboutStr .. spellOrdinalNumber(man.Nth) .. " sortie"
      end
   elseif (man.StageClass == "roundabout_export") then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         s = ""
      else
         s = roundaboutStr .. spellOrdinalNumber(man.CurrentRoundaboutExitNum) .. " sortie"
         if (man.CurRoundaboutExitPhaseCombinedWithNext) then
            s = concat(s, ", puis prenez le ", spellOrdinalNumber(man.CurrentRoundaboutExitNum + 1)) .. " et quitter le rond-point"
         end
         man._NoCombination = true
      end
   elseif (man.StageClass == "roundabout_exit") then
      if (man.project ~= "zeekr" or guidanceLevel > 1) then
         s = "Maintenant " .. spellRoundaboutDirection(man)
      end
   end
   return s
end

function processRoundaboutActionCombined(man)
   local s = spellDistancePart(man)
   s = conc(s, "quitter le rond-point à la", spellOrdinalNumber(man.Nth), "sortie")

   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
      s = conc(s, "Bientôt")
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
      s = "Vous avez atteint "  --You have reached
   else
      s = spellDistancePart(man)
      if (onlyDirection) then
         s = ""
      end
      s = s .. " vous avez atteint " --you will have reached
   end

   s = concat(s, " ", spellDestinationName(man))
   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
         s = conc(s, "Bientôt")
   end
   return s
end

function spellDestinationName(man)

   local DestNumber = man.HintProps.CustomDestNumber
   if (DestNumber == nil or getIntNumber(DestNumber) > 100) then
      DestNumber = getIntNumber(man.TotalNumberOfDestinations) - getIntNumber(man.DestinationIndex)
   else
      DestNumber = getIntNumber(DestNumber)
   end
   local s = ""
   local deststr = "la destination"
   if (man.project == "zeekr") then
     deststr = "votre destination"
   end
   local destLeftSideStr = "C'est à gauche"
   local destRightSideStr = "C'est à droite"
   if (man.project == "zeekr") then
     destLeftSideStr = "sur la gauche"
     destRightSideStr = "sur la droite"
   end
   local interDeststr = "la destination intermédiaire"
   if (man.project == "zeekr") then
     interDeststr = "le point"
   end
   if (man.FromProps.Area.ferry) then
      s = "le ferry"
   elseif (man.FromProps.Area.ipd) then
      s = conc(s, "la station de charge, veuillez charger à", spellNumber(man.Nth))
      s = s .. "%"
   elseif (man.FromProps.Area.crime and man.Stage == 1) then
      s = "A l'approche de la station-service Setel, vous avez besoin du service Setel ?"
   elseif (man.Nth == 0) then
      s = deststr --the destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", destLeftSideStr) --It is on the left
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", destRightSideStr) -- It is on the right
      end
   elseif (isInRanges(DestNumber, 1, 100)) then
      s = conc(s, interDeststr, spellNumber(DestNumber)) --the intermediate destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", destLeftSideStr)
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", destRightSideStr)
      end
   end

   if ((man.Nth == 0) and (man.Stage == 1) and (not isAvailable(man.previous)) ) then
      s = concat(s, ". ", "")
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
   local commonstr = "Point de péage devant vous"
   if (man.project == "zeekr") then
     commonstr = "Vous arrivez au péage"
   end
   if (guidanceLevel == 1) then
      if (man.Stage == 2) then
         s = commonstr
      end
   elseif (guidanceLevel == 3) then
      if (man.Stage == 2) then
         s = commonstr
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 3) ) then
         s = commonstr
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = commonstr
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = commonstr
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
         s = "Tunnel devant vous"
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = "Tunnel devant vous"
      elseif (man.project == "zeekr" and man.Stage == 2 and guidanceLevel == 2) then
         s = "Tunnel devant vous"
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = "Tunnel devant vous"
      end
   end
   return s
end

function processEnterActionWarning(man, AnnouncementIsNotEmpty)
   local s = "Veuillez respecter le code de la route en vigueur"
   if (man.project == "zeekr") then
     s = "Veuillez respecter la réglementation routière en vigueur"
   end
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
         s = conc(s, "bientôt")
   end
   return s
end

function processMotorwayEnterAction(man)
   local s = spellDistancePart(man)
   s = conc(s, "", spellDirectionPart(man))
   s = conc(s, "", "")
   s = conc(s, "", spellMotorWayEnterNamesPart(man, true))
   return s
end


function spellMotorWayEnterNamesPart(man, useRoadNameOrNumber)
   local s = ""
   local signPostName = getDirectionName(man)
   -- motorway entry
   if (man.HintProps.Hint.motorway_entry) then
       if (#signPostName > 0) then
         s = conc(s, "en direction de", signPostName)
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
      s = "Attention à la fusion du trafic"
      if ((man.DirOClock ~= 0) and (man.DirOClock ~= 6)) then
         s = conc(s, "du", spellRoughDirection(man))
      end
   end
   return s
end


function processFollowRoadAction(man)
   local s = ""
   if (man.Distance > 0) then
      s = conc(s, "sur", spellDistanceAndUnit(man, countryCode, unitArray))
   end
   s = conc("Suivre la route", s)
   return s
end

function processDPTrafficLightAction(man)
   local s = "Feu de circulation à l'intersection, continuez tout droit"
   if (man.project == "zeekr") then
     s = "Feu de circulation à l'intersection, veuillez aller tout droit"
   end
   if (man.Stage == 1 and man.TrafficLightIntersectionCount > 0) then
      s = "Allez tout droit au  " .. spellOrdinalNumber(man.TrafficLightIntersectionCount) .. " feu"
   end
   return s
end

function processDPRecomLaneAction(man)
   local s = ""
   local onofStr = ""
   if (man.project == "zeekr") then
     onofStr = "files"
   else
     onofStr = "voies"
   end
   if (man.IsContiLaneVoice) then
      if (man.BestRecomNum > 0) then
         if (not man.IsManeuver) then
            local dir = spellDirection(man.RecomLaneDir, man.project)
            if (dir ~= "") then
               s = conc(dir, ".")
            end
         end
         s = conc(s, "Recommendation: Prenez")  --Recommendation: Take
      else
         s = conc(s, "veuillez prendre les")    --Take
      end
      local pos = ""
      if ((man.IsInc2nd) and (man.IsInc3rd)) then
         s = conc(s, "la deuxième ou troisième voie") --the second or third lane from the
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (man.IsInc2nd) then
         s = conc(s, "deuxième voie")  -- the second lane from the
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (man.IsInc3rd) then
         s = conc(s, "troisième voie")  --the third lane from the
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 1)) then
         s = conc(s, "la voie la plus à gauche") --the leftmost lane
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 2)) then
         s = conc(s, "la voie la plus à droite") --the rightmost lane
      else
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         if (man.BestRecomNum > 1) then
            local bestNumber = convToString(man.BestRecomNum)
            s = conc(s, "l’une des", bestNumber, onofStr, pos)  --on of the ... lanes
         elseif (man.RecomNum > 1) then
            local number = convToString(man.RecomNum)
            s = conc(s, "l’une des", number, onofStr, pos)   --on of the ... lanes
         else
            s = conc(s, "voie") -- the lane
         end

         s = conc(s, pos)
      end
   else
      if (not man.IsManeuver) then
         local dir = spellDirection(man.RecomLaneDir, man.project)
         if (dir ~= "") then
            s = conc(dir, ".")
         end
      end
      s = conc(s, "Prenez")  --Take
      local pos = spellPosition(man.isContiLaneVoice, man.RecomLanePos)
      if (man.RecomNum > 1) then
         local number = convToString(man.RecomNum)
         s = conc(s, "l’une des", number, onofStr, pos) --on of the ... lanes
      else
         s = conc(s, "la voie", pos) -- the lane
      end
   end
   return s
end

function spellDirection(dir, projectName)
   local s = ""
   if (dir == 1) then
      s = "Effectuer un virage serré à gauche"
   elseif (dir == 2) then
      s = "Tourner à gauche"
   elseif (dir == 3) then
      s = "Tourner légèrement à gauche"
   elseif (dir == 4) then
      s = "Effectuer un virage serré à droite"
   elseif (dir == 5) then
      s = "Tourner à droite"
   elseif (dir == 6) then
      s = "Tourner légèrement à droite"
   elseif (dir == 7) then
     if (projectName == "zeekr") then
       s = "continuez tout droit"  --Go straight on
     else
       s = "Aller tout droit"  --Go straight on
     end
   elseif (dir == 8) then
      s = "Faire un demi-tour"
   end
   return s
end

function spellPosition(isContiLaneVoice, position)
   local s = ""
   if (isContiLaneVoice) then
      if (position == 1) then
         s = "de gauche"
      elseif (position == 2) then
         s = "de droite"
      elseif (position == 3) then
         s = "du centre"
      end
   else
      if (position == 1) then
         s = "de gauche"
      elseif (position == 2) then
         s = "de droite"
      elseif (position == 3) then
         s = "du centre"
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
      s = conc("À", spellDistanceAndUnit(man, countryCode, unitArray))
   end
   s = conc(getAreaType(man.AreaType), s)
   if ((nextDistKm >= 1) and testDistance4(nextDistM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray, true) ~= "")) then
      if (man.AreaType == 1000) then
        if (man.project == "zeekr") then
          s = conc(s, ".  Environ ", spellDistanceAndUnit(man, countryCode, unitArray, true))
          s = s .. " avant la prochaine"
        else
         s = conc(s, ".  L'aire de repos suivante est à", spellDistanceAndUnit(man, countryCode, unitArray, true))
        end
      elseif (man.AreaType == 1001) then
        if (man.project == "zeekr") then
          s = conc(s, ".  Environ ", spellDistanceAndUnit(man, countryCode, unitArray, true))
          s = s .. " avant la prochaine"
        else
         s = conc(s, ".  Le parking suivant est à", spellDistanceAndUnit(man, countryCode, unitArray, true))
        end
      end
   end
   return s
end

function getAreaType(type)
   local s = ""
   if (man.project == "zeekr") then
     s = "Aire de repos dans environ"
  else
     if (type == 1000) then
      s = "Aire de repos"
    elseif (type == 1001) then
      s = "Parking"
    end
   end
   return s
end

function processWNHighAccidentAreaAction(projectName)
   local s = ""
   if (projectName == "zeekr") then
     s = "Vous arrivez dans une zone très accidentogène. Conduisez avec prudence."
   else
     s = "Zone d'accidents élevés devant vous. Conduisez avec vigilance"
   end
   return s
end

function processWNSchoolZoneAction(projectName)
   local s = ""
   if (projectName == "zeekr") then
     s = "Vous arrivez dans une zone scolaire. Conduisez avec prudence."
   else
     s = "Zone scolaire devant vous. Conduisez avec vigilance."
   end
   return s
end

function processWNRailwayAction(projectName)
   local s = ""
   if (projectName == "zeekr") then
     s = "Vous arrivez sur un passage à niveau. Conduisez avec prudence."
   else
     s = "Passage à niveau devant vous. Conduisez avec vigilance."
   end
   return s
end

function processWNDestParkingAction(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel == 2) then
      s = "Parking trouvé à proximité de la destination, tapez sur Aller"
      if (man.project == "zeekr") then
         s = "Parking trouvé à proximité de la destination. Appuyez sur Go."
      end
   end
   return s
end

function processWNDestChargingStationAction(projectName)
   local s = "Stations de recharge trouvées à proximité de la destination"
   if (projectName == "zeekr") then
     s = "Stations de charge trouvées à proximité de la destination"
   end
   return s
end

function processWNTunnelAction()
   local s = "Tunnel devant vous"
   return s
end

function processWNGpsAction()
   local s = "Le signal GPS actuel est faible et la mise à jour de la position peut ne pas être opportune"
   return s
end

function processWNLaneMergeAction(man)
   local s = ""
   if (man.project == "zeekr") then
      if (man.LaneMergeType == 1) then
        s = "Vous arrivez vers une voie d'insertion sur la gauche."
      elseif (man.LaneMergeType == 2) then
        s = "Vous arrivez vers une voie d'insertion sur la droite."
      end
      s = conc(s, " Conduisez avec prudence")
    else
      if (man.LaneMergeType == 1) then
       s = "Fusion du trafic de gauche"
      elseif (man.LaneMergeType == 2) then
       s = "Fusion du trafic de droite"
      end
      s = conc(s, "devant vous. Conduisez avec vigilance")
   end
   return s
end

function processWNTrafficCameraAction(man)
   local s = ""
   if (man.project == "zeekr") then
     if (man.CameraType == 1) then
      s = "Radar de vitesse dans "                               -- A speed camera is
     elseif (man.CameraType == 7) then
        s = "Radars de vitesse et de feu rouge dans"       -- Speed and red light cameras are
     elseif (man.CameraType == 3) then
        s = "Radar de feu rouge dans"                           -- A red light camera is
     elseif (man.CameraType == 11) then
        s = "Zone de contrôle de la vitesse moyenne dans"        -- You are driving into a speed measurement section
     elseif (man.CameraType == 12) then
        s = "Fin de la zone de contrôle de la vitesse moyenne dans"             -- You are leaving the speed measurement section
     else
        return s
    end
   else
    if (man.CameraType == 1) then
      s = "Radar à"                               -- A speed camera is
     elseif (man.CameraType == 7) then
        s = "Radar et radar de feu rouge à"       -- Speed and red light cameras are
     elseif (man.CameraType == 3) then
        s = "Radar de feu rouge à"                           -- A red light camera is
     elseif (man.CameraType == 11) then
        s = "Radar tronçon à"        -- You are driving into a speed measurement section
     elseif (man.CameraType == 12) then
        s = "Fin du radar tronçon à"             -- You are leaving the speed measurement section
     else
        return s
    end
   end
   local currentDis = man.Distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
      if (man.CameraType == 12) then
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray))
      else
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray), "devant vous")          -- ahead
      end
   else
      s = conc(s, "devant vous")
   end
   local speedLimit = man.SpeedLimit
   local unit = "km/h"
   if (speedLimit > 0) then
      if (man.DistUnit == "mile" or man.DistUnit == "quartermile" or man.DistUnit == "yard" or man.DistUnit == "foot") then
         speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
         unit = "mph"
      end
   end
   local limitedSpeed = convToString(speedLimit)
   if ((man.CameraType == 1) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite de vitesse", limitedSpeed, unit)        -- The limit is x km/h
   elseif ((man.CameraType == 7) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite de vitesse", limitedSpeed, unit)
   elseif (man.CameraType == 3) then
      s = conc(s, ".")                  -- Please drive carefully
   elseif ((man.CameraType == 11) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite de vitesse", limitedSpeed, unit)
   end
   return s
end

function processWNCurveAction(man)
   local s = ""
   if (man.IsContiCurve) then
     if (man.project == "zeekr") then
       s = "Vous arrivez dans un virage continu. Conduisez avec prudence"
     else
       s = "Double virage devant vous. Conduisez avec vigilance"
     end
   else
     if (man.project == "zeekr") then
       s = "Vous arrivez dans un virage. Conduisez avec prudence"
     else
       s = "Virage devant vous. Conduisez avec vigilance"
     end
   end
   return s
end

function processWNTrafficAction(man)
   local s = ""
   local content = getTrafficContent(man.TrafficKind)
   if (content ~= "") then
      local distM = man.DistM
      local distKm = getIntNumber(distM / 1000)
      if ((distKm >= 1) and testDistance2(distM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
         s = conc("À", spellDistanceAndUnit(man, countryCode, unitArray), ",")
      end
      s = conc(s, content)
   end
   return s
end

function processWNOverspeedAction(man)
   local s = ""
   local speedLimit = man.SpeedLimit
   local carSpeed = man.CarSpeed
   local speedLimitStr = ""
   local carSpeedStr = ""
   local unit = "km/h"
   if (man.DistUnit == "mile") then
      speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
      unit = "mph"
   end
   speedLimitStr = convToString(speedLimit)
   carSpeedStr = convToString(carSpeed)
   s = "Vous dépassez la limite de vitesse de " .. speedLimitStr .. " " .. unit
   return s
end

function processWNBorderAction(man)
   local s = ""
   if (man.BorderName ~= "") then
      s = conc("Entrée en", man.BorderName)
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
         s = "La voie actuelle est interdite de " .. spellTimeForInappLane(man.FromHour, man.FromMinutes) .. " à " .. spellTimeForInappLane(man.ToHour, man.ToMinutes)
      end
   else
      -- other reasons
      if (spellInappLaneReason(man.InappLaneReason) ~= "") then
         s = "La voie actuelle est dédiée à " .. spellInappLaneReason(man.InappLaneReason)
      end
   end
   return s
end

function processWNNarrowLaneAction(man)
   local s = ""
   if (man.NarrowLaneVoiceType == 1) then
      if (man.NarrowLaneDir == 1) then
         s = "La voie avant gauche se rétrécit"
      elseif (man.NarrowLaneDir == 2) then
         s = "La voie avant droite se rétrécit"
      elseif (man.NarrowLaneDir == 3) then
         s = "Avancez les deux voies étroites"
      else
         return s
      end
      s = s .. ". Conduisez avec vigilance"
   elseif (man.NarrowLaneVoiceType == 2) then
      if (man.NarrowLaneDir == 1) then
         s = "Croisement gauche"
      elseif (man.NarrowLaneDir == 2) then
         s = "Croisement droite"
      else
         return s
      end
      s = "La route à venir devient plus étroite. Veuillez changer de voie pour le " .. s
   end
   return s
end

function processEVLowBattery(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      if (man.project == "zeekr") then
         s = "Batterie faible. Veuillez recharger la batterie."
      else
         s = "Batterie faible, veuillez recharger"
      end
   end
   return s
end

function processEVFewChargingStations(man)
   local s = ""
   if (man.GuidanceLevel > 1) then
      if (man.project == "zeekr") then
         s = "Plusieurs stations de charge sont disponibles. Pensez à recharger la batterie bientôt."
      else
         s = "Il y a peu de stations de recharge. Veuillez recharger à temps"
      end
   end
   return s
end

function processSRPSuccess()
   local s = "Planification intelligente de l’itinéraire de charge effectuée"
   return s
end

function processSRPFail(projectName)
  local s = ""
  if (projectName == "zeekr") then
    s = "Le niveau de la batterie ne permettra pas d'atteindre la destination. Aucune station de charge trouvée sur l'itinéraire. Pensez à recharger la batterie bientôt"
  else
   s = "Le niveau de la batterie n'est pas suffisant pour arriver à destination. Aucune station de recharge n'a été trouvée le long de l'itinéraire, veuillez recharger sans tarder"  -- Battery level not sufficient to arrive at the destination. No charging station found along the route, please charge timely
   end
   return s
end

function processBeepDRGPopup(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      local minutes = convToString(man.Minutes)
      if (man.project == "zeekr") then
         s = conc("Un nouvel itinéraire a été calculé.", minutes, " sera enregistré")
      else
         s = conc("Un nouvel itinéraire a été trouvé. Vous gagnez", minutes, "minutes")
      end
   end
   return s
end

function spellTimeForInappLane(hour, min)
   local s = ""
   if (isNumber(hour) and isNumber(min)) then
      if (min == 0) and isInRanges(hour, 0, 1) then
         s = hour .. " heure"
      elseif (min == 0) and isInRanges(hour, 2, 23) then
         s = hour .. " heures"
      elseif (isInRanges(min, 1, 9)) then
         s = hour .. " heures " .. min .. "minutes"
      else
         s = hour .. " " .. min .. ""
      end
   end
   return s
end

function spellInappLaneReason(reason)
   local s = ""
   if (reason == 2) then
      s = "HOV"
   elseif (reason == 3) then
      s = "taxis"
   elseif (reason == 4) then
      s = "autobus"
   elseif (reason == 5) then
      s = "ambulances"
   end
   return s
end

function getTrafficContent(kind)
   local s = ""
   if (kind == 0) then
      s = "embouteillage"
   elseif (kind == 1) then
      s = "obstacle sur la route"
   elseif (kind == 2) then
      s = "accident"
   elseif (kind == 3) then
      s = "Travaux routiers"
   elseif (kind == 4) then
      s = "rétrécissement de voie"
   elseif (kind == 5) then
      s = "route glissante"
   elseif (kind == 6) then
      s = "aquaplanage"
   elseif (kind == 7) then
      s = "incendie"
   elseif (kind == 8) then
      s = "affaissement de la route"
   elseif (kind == 9) then
      s = "véhicule en panne"
   elseif (kind == 10) then
      s = "route fermée"
   elseif (kind == 11) then
      s = "conditions météorologiques extrêmes"
   elseif (kind == 12) then
      s = "personnes sur la route"
   elseif (kind == 13) then
      s = "contrôle d'altitude déclenché"
   elseif (kind == 14) then
      s = "modification des règles de circulation"
   elseif (kind == 15) then
      s = "événement majeur"
   elseif (kind == 16) then
      s = "service non disponible"
   elseif (kind == 17) then
      s = "véhicule lent"
   elseif (kind == 18) then
      s = "embouteillage dangereux"
   elseif (kind == 19) then
      s = "risque d'incendie"
   elseif (kind == 20) then
      s = "retards"
   elseif (kind == 21) then
      s = "contrôle de police"
   elseif (kind == 22) then
      s = "équipement routier défectueux"
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
      s = conc(s, processFirstAnnouncement(man))
      man._NoCombination = true
      return s
   end

   s = conc(s, processNormalAction(man))
   return s
end

function processCommonAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.Action == "guidance_starts") then
      if (man.project == "zeekr") then
         if (guidanceLevel > 1) then
            s = "Veuillez respecter la réglementation routière en vigueur"
         end
      else
         s = "Veuillez respecter le code de la route en vigueur"
      end
   elseif (man.Action == "enter_better_route") then
      if (guidanceLevel > 1) then
         s = "Sont entrés dans la meilleure route"
      else
         s = ""
      end
   elseif (man.Action == "blocked_road") then
      if (guidanceLevel > 1) then
         s = "Route bloquée devant, guide avec nouvel itinéraire"
      else
         s = ""
      end
   elseif (man.Action == "time_regulation_road") then
      if (guidanceLevel > 1) then
         s = "Régulation horaire à venir, guide avec nouvel itinéraire"
      else
         s = ""
      end
   elseif (man.Action == "no_guidance_active") then
      s = "Veuillez définir l'itinéraire"
   elseif (man.Action == "drive_to_route") then
      s = "Veuillez suivre l'itinéraire. Le guidage vers la destination commence alors"
   elseif (man.Action == "ti_route_changed") then
      s = "L'itinéraire optimal est mis à jour"
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
        if (man.project == "zeekr") then
          s = conc(s, "La navigation a démarré")
        else
         s = conc(s, " Démarrage du guidage")
        end
      elseif (isInRanges(DestNumber, 1, 5)) then
         if (man.project == "zeekr") then
          s = conc(s, " La navigation vers le point", spellNumber(DestNumber))
          s = s .. " a démarré"
         else
          s = conc(s, " Démarrage du guidage pour la destination intermédiaire", spellNumber(DestNumber))
         end
      end
      local aTime = spellTime(man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode, man.project)
      if (#aTime > 0) then
         s = conc(s .. ".", aTime)
      end

      s = conc(s, ".", processWeatherTypesString(man))
   end

   if (man.Action == "roundabout_leave" and
      man.Stage == man.FirstStage and
      man.StageClass == man.FirstStageClass) then
      s = conc(s, processRoundaboutAction(man))
   end
   return s
end

function processWeatherTypesString(man)
   local s = ""
   local weatherStr = " Météo à destination : ";
   if (man.project == "zeekr") then
     weatherStr = "Météo de votre destination : "
   end
   if (getTableValueCount(man.WeatherTypes) > 0) then
      local weatherTypeCount = 1;
      s = conc(s , weatherStr)
      for key, weatherType in pairsSortedByKeys(man.WeatherTypes) do
         if (weatherType ~= nil) then
            if (weatherTypeCount > 1) then
               s = conc(s, " vers ")
            end
            local weatherTypeStr = spellWeatherType(weatherType.weather_type)
            if (weatherTypeStr == "") then
               return ""
            else
               s = conc(s , weatherTypeStr)
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
          s = "Veuillez suivre l'itinéraire mis en surbrillance"
        else
         s = "Dirigez-vous vers l'itinéraire surligné"
        end
      end

      local aTime = spellTime(man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode)
      if ((#aTime > 0) and (#s > 0)) then
         s = conc(s .. ".", aTime)
      elseif ((#aTime > 0) and (#s == 0)) then
         s = conc("", aTime)
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
   -- along main road send action phase sentence at approach phase when the mode is simple
   s = "Bientôt Continuer à suivre la route principale"
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
      s = processWNHighAccidentAreaAction(man.project)
   elseif (man.Action == "wn_school_zone") then
      s = processWNSchoolZoneAction(man.project)
   elseif (man.Action == "wn_railway") then
      s = processWNRailwayAction(man.project)
   elseif (man.Action == "wn_dest_parking") then
      s = processWNDestParkingAction(man)
   elseif (man.Action == "wn_dest_charging_station") then
      s = processWNDestChargingStationAction(man.project)
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
   elseif (man.Action == "ev_few_charging_stations") then
      s = processEVFewChargingStations(man)
   elseif (man.Action == "srp_success") then
      s = processSRPSuccess()
   elseif (man.Action == "srp_fail") then
      s = processSRPFail(man.project)
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
      s = concat(mainString, ". ", s)
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
      if (man.project == "zeekr") then
        if (man.Stage == 1) then
          s = concat(mainString, "  puis,", s)  --and then
        else
          s = concat(mainString, "  Ensuite,", s)  --and then
        end
      else
        s = concat(mainString, " et ensuite ", s)  --and then
      end
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
      s = s .. "."
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