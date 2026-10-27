-- import laser lua library functions
require("lalulib")

--Simple mode : guidanceLevel = 1
--Detail mode : guidanceLevel = 2
--Standard mode : guidanceLevel = 3

countryCode = "PRT"
unitArray = {"metros", "metros", "quilómetro", "quilómetros", "quilómetros", "pés", "pés", "jardas", "jardas", "milha", "milhas", "um quarto de", "meia", "três quartos de"}

function getVersionInfo()
   local t =
   {
      ["country"] = "PRT",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "por",             -- ISO 639-3  alpha-3 language code
      ["version"] = "1.8.3",        -- version
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
      s = "Ensolarado"                           --sunny
   elseif (weatherTypeNum == 2) then
      s = "Nublado"                             --cloudy
   elseif (weatherTypeNum == 3) then
      s = "Nublado"                              --overcast
   elseif (weatherTypeNum == 4) then
      s = "Aguaceiros"                             --shower
   elseif (weatherTypeNum == 5) then
      s = "Trovoada"                 --thundershower
   elseif (weatherTypeNum == 6) then
      s = "Trovoada com granizo"     --thundershower with hail
   elseif (weatherTypeNum == 7) then
      s = "Chuva de neve"                       --sleet
   elseif (weatherTypeNum == 8) then
      s = "Chuva leve"                           --light rain
   elseif (weatherTypeNum == 9) then
      s = "Chuva moderada"                       --moderate rain
   elseif (weatherTypeNum == 10) then
      s = "Chuva forte"                          --heavy rain
   elseif (weatherTypeNum == 11) then
      s = "Tempestade"                           --storm
   elseif (weatherTypeNum == 12) then
      s = "Tempestade Forte"                     --heavy storm
   elseif (weatherTypeNum == 13) then
      s = "Tempestade Severa"                    --severe storm
   elseif (weatherTypeNum == 14) then
      s = "Chuva de gelo"                        --ice rain
   elseif (weatherTypeNum == 15) then
      s = "Chuva leve a moderada"                --light to moderate rain
   elseif (weatherTypeNum == 16) then
      s = "Tempestade moderada a forte"          --moderate to heavy storm
   elseif (weatherTypeNum == 17) then
      s = "Chuva forte a tempestade"             --heavy rain to storm
   elseif (weatherTypeNum == 18) then
      s = "Tempestade a forte tempestade"        --storm to heavy storm
   elseif (weatherTypeNum == 19) then
      s = "Tempestade forte a severa"            --heavy to severe storm
   elseif (weatherTypeNum == 20) then
      s = "Aguaceiros de neve"                       --snow flurry
   elseif (weatherTypeNum == 21) then
      s = "Neve leve"                            --light snow
   elseif (weatherTypeNum == 22) then
      s = "Neve moderada"                        --moderate snow
   elseif (weatherTypeNum == 23) then
      s = "Neve forte"                           --heavy snow
   elseif (weatherTypeNum == 24) then
      s = "Tempestade de neve"                   --snowstorm
   elseif (weatherTypeNum == 25) then
      s = "Neve leve a moderada"                 --light to moderate snow
   elseif (weatherTypeNum == 26) then
      s = "Neve moderada a forte"                --moderate to heavy snow
   elseif (weatherTypeNum == 27) then
      s = "Neve forte a tempestade de neve"      --heavy snow to snowstorm
   elseif (weatherTypeNum == 28) then
      s = "Nevoado"                             --foggy
   elseif (weatherTypeNum == 29) then
      s = "Nevoeiro denso"                       --dense fog
   elseif (weatherTypeNum == 30) then
      s = "Nevoeiro denso forte"                 --heavy dense fog
   elseif (weatherTypeNum == 31) then
      s = "Nevoeiro forte"                       --heavy fog
   elseif (weatherTypeNum == 32) then
      s = "Nevoeiro denso muito forte"           --extra-heavy dense fog
   elseif (weatherTypeNum == 33) then
      s = "Poeira"                               --dust
   elseif (weatherTypeNum == 34) then
      s = "Areia"                                --sand
   elseif (weatherTypeNum == 35) then
      s = "Tempestade de areia"                  --sandstorm
   elseif (weatherTypeNum == 36) then
      s = "Névoa"                                --haze
   elseif (weatherTypeNum == 37) then
      s = "Névoa moderada"                       --moderate haze
   elseif (weatherTypeNum == 38) then
      s = "Névoa severa"                         --severe haze
   elseif (weatherTypeNum == 39) then
      s = "Névoa perigosa"                       --hazardous haze
   else
      s = ""
   end
   return s
end

function spellRoughDirection(man)
   local s = ""
   if (man.DirOClock == 0) then
      s = "em frente"          --straight
   elseif (isInRanges(man.DirOClock, 1, 5)) then
      s = "à direita"            --right
   elseif (isInRanges(man.DirOClock, 7, 11)) then
      s = "à esquerda"           --left
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
         s = conc("ligeiramente", s)        --slight
      end
   elseif ((man.DirOClock == 2) or (man.DirOClock == 10)) then
      s = conc("ligeiramente", s)           --slight
   elseif (isInRanges(man.DirOClock, 4, 5, 7, 8)) then
      s = conc("acentuadamente", s)              --sharp
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
   if (man.StageClass == "just_before") then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         s = ""
      else
         s = ""
      end
   elseif (man.Stage == 1) then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         return s
      end
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         if (man.Distance > 0) then
            s = conc(s, "A", spellDistanceAndUnit(man, countryCode, unitArray))         --In about
         end
      else
         s = "Agora "                 --Soon
      end
   else
      if (man.Stage > 2) and ((guidanceLevel == 1) or (guidanceLevel == 3)) then
         return s
      end
      if (man.Distance > 0) then
         s = conc(s, "A", spellDistanceAndUnit(man, countryCode, unitArray))         --In about
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
      s = conc(s, "vire")              --turn
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "à esquerda")     --left
      else
         s = conc(s, "à direita")      --right
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Continue na estrada principal"                     --Keep following main road
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = "vá em frente"                           --keep center
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, "Siga", spellRoughDirection(man))      --go
         if (man.Nth > 0) then
            s = conc(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "faça inversão de marcha"                        --make a u-turn
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            if (isFromHighway) then
               s = ""
            else
               s = conc(s, "vire", spellFineDirection(man))              --turn
            end
         else
            s = conc(s, "mantenha-se", spellFineDirection(man))       --keep
         end
      else
         if (man.Action == "exit") then
            if (isFromHighway) then
               s = ""
            else
               s = conc(s, "vire", spellFineDirection(man))                 --turn
            end
         else
            s = conc(s, "vire", spellFineDirection(man))                 --turn
         end
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
            s = conc(s, "", spellNthStreet(man.Nth, false))
         end
      end
   end
   return s
end

function spellRoundaboutDirection(man)
   -- roundabout exit voice
   local s = "vire"                  --turn
   if (man.HintProps.Hint.left_hand_traffic) then
      s = conc(s, "à esquerda")      --left
   else
      s = conc(s, "à direita")       --right
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
         s = conc(s, " entre na saída", exitName, spellFineDirection(man))              -- at exit
      end
      if (#signPostName > 0) then
         s = conc(s, " em direcção a", signPostName)     -- towards
      end
      if (man.Action == "exit") then
         if (#exitName > 0) then
            s = s
         elseif (#signPostName > 0) then
            s = conc("saia", spellFineDirection(man), s)
         else
            s = conc("saia", spellFineDirection(man))
         end
      end
      return s
   else
      local isRamp = man.ToProps.Area.ramp
      if (isRamp and #signPostName > 0) then
         s = conc(s, " em direcção a", signPostName)     -- towards
      else
         if (useRoadNameOrNumber) then
            local roadName = getRoadNameOrNumber(man)
            if (#roadName > 0) then
               s = conc(s, " em", roadName)              -- onto
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
      s = "no"
      if (man.ContainTrafficLight) then
         if (man.TrafficLightIntersectionCount > 0) then
            s = conc(s, " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1))
         end
         s = s .. " semáforo"          -- traffic light
      else
         s = conc(s, " ", spellOrdinalNumber(man.IntersectionCount + 1))
         s = s .. " cruzamento"        -- intersection
      end
   elseif (man.ContainTrafficLight and (not isHighway)) then
      if ((guidanceLevel == 1) and (man.StageClass == "just_before")) or ((guidanceLevel == 3) and (man.StageClass == "just_before")) or (guidanceLevel == 2) then
         s = "no"
         if (man.TrafficLightIntersectionCount > 0) then
            s = conc(s, " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1))
         end
         s = s .. " semáforo"             -- traffic light
      end
   elseif (isHighway and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      s = "no"
      s = conc(s, " ", spellOrdinalNumber(man.IntersectionCount + 1))
      s = s .. " cruzamento"           -- intersection
   end
   return s
end

function spellPrepositionAndGender(props)
   local s = ""
   if (props.Preposition == "into") or (props.Preposition == "onto") then
      s = "op"              --onto
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
      s = "primeira"                 --first
   elseif (number == 2) then
      s = "segunda"                  --second
   elseif (number == 3) then
      s = "terceira"                 --third
   elseif (number == 4) then
      s = "quarta"                   --fourth
   elseif (number == 5) then
      s = "quinta"                   --fifth
   elseif (number == 6) then
      s = "sexta"                    --sixth
   elseif (number == 7) then
      s = "sétima"                   --seventh
   elseif (number == 8) then
      s = "oitava"                   --eighth
   elseif (number == 9) then
      s = "nona"                     --ninth
   elseif (number == 10) then
      s = "décima"                   --tenth
   elseif (number == 11) then
      s = "décima primeira"          --eleventh
   elseif (number == 12) then
      s = "décima segunda"           --twelfth
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
         s = hour .. " horas"         -- hours
      elseif (hour == 1) then
         s = hour .. " hora"          -- hour
      end

      if (minutes > 1) then
         s = s .. " " .. minutes .. " minutos"       -- minutes
      elseif(minutes == 1) then
         s = s .. " " .. minutes .. " minuto"        -- minute
      end
   end
   if (#s > 0) then
      s = "You can get destination in " .. s         --You can get destination in 
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

         if (t.min == 0) and (t.hour == 0) then
            s = "0 horas"                       --midnight
         elseif (t.min == 0) and (t.hour == 1) then
            s = "1 hora"
         elseif (t.min == 0) and isInRanges(t.hour, 2, 23) then
            s = hour .. " horas"                   -- o'clock
         elseif (t.hour == 0) and isInRanges(t.min, 1, 59) then
            s = "0 horas e " .. t.min
         elseif (t.hour == 1) and isInRanges(t.min, 1, 59) then
            s = "1 hora e " .. t.min
         elseif isInRanges(t.hour, 2, 23) and isInRanges(t.min, 1, 59) then
            s = hour .. " horas e " .. t.min
         end

         if (isTwelveHoursClock) then
            local zeroClock = false
            local twelveHoursClockMark = string.lower(os.date("!%p", timeVal))
            if (t.min == 0) and (t.hour == 0) then
               s = "meia-noite"                       --midnight
               zeroClock = true
            elseif (t.min == 0) and (t.hour == 12) then
               s = "meio-dia"                         --noon
               zeroClock = true
            elseif (t.min == 0) then
               s = hour ..  ""
            elseif (isInRanges(t.min, 1, 9)) then
               s = hour .. "h" .. t.min        -- o 
            else
               s = hour .. "h" .. t.min
            end
            if (twelveHoursClockMark == "am") and (not zeroClock) then
               s = s .. " manhã"                       -- a m
            elseif (twelveHoursClockMark == "pm") and (not zeroClock) then
               s = s .. " tarde"                       -- p m
            end
         end
      end
   end
   if (#s > 0) then
      s = "Chegada prevista " .. s           --Estimated time of arrival, 
   end
   return s
end

function spellNthStreet(nth, isStraight)
   local s = ""
   if (nth == 1) then
      s = " esquerda"              -- the left
   elseif (nth == 2) then
      s = " centro"                -- the center
   elseif (nth == 3) then
      s = " direita"               -- the right
   end
   if (isStraight) then
      s = "para" .. s .. " estrada"         --"to" .. s .. " road"
   else
      s = "e pegue" .. s .. " estrada"      --and take .. s .. " road"
   end
   return s
end

function processIntersectionAction(man)
   local s = spellDistancePart(man)
   s = conc(s, "", spellDirectionPart(man))
   s = conc(s, "", spellIntersectionCount(man))
   s = conc(s, "", spellNamesPart(man, true))
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
            s = spellDirectionPartWITHagain(man)
         else
            s = conc("", s)                      --soon
         end
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  ((not man.previous.HintProps.Hint.along_main_road) and man.HintProps.Hint.along_main_road)) then
         s = conc(s, "")
      elseif (     (isAvailable(man.previous))
         and   (man.previous.Action == man.Action)
         and   (man.previous.DirOClock == man.DirOClock)) then
         s = spellDirectionPartWITHagain(man)                     --again
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  (spellDirectionPart(man.previous) == spellDirectionPart(man))) then
         s = spellDirectionPartWITHagain(man)                     --again
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
      s = conc(s, "vire novamente")              --turn
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "à esquerda")     --left
      else
         s = conc(s, "à direita")      --right
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Continue na estrada principal"                     --Keep following main road
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = "vá em frente"                           --keep center
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, "Siga", spellRoughDirection(man))      --go
         if (man.Nth > 0) then
            s = conc(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "faça inversão de marcha"                        --make a u-turn
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            s = conc(s, "vire novamente", spellFineDirection(man))              --turn
         else
            s = conc(s, "mantenha-se", spellFineDirection(man))       --keep
         end
      else
         s = conc(s, "vire novamente", spellFineDirection(man))                 --turn
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
            s = conc(s, "", spellNthStreet(man.Nth, false))
         end
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
         s = "Pegue a saída " .. spellOrdinalNumber(man.Nth) .. " e saia da rotatória"      --"Take the " ..  .. " exit and leave the roundabout"
         return s
      end
   end
   if (man.Action == "roundabout" and ((man.Stage == 2 or man.Stage == 3) or (man.Stage == 1 and man.DerivedBegin))) then
      s = "A " .. spellDistanceAndUnit(man, countryCode, unitArray) .. " entre na rotatória"   --"In about " .. .. " enter the roundabout"
      s = s .. ", em seguida pegue a saída " .. spellOrdinalNumber(man.Nth) .. " e saia da rotatória"  --", then take the " ..    .. " exit and leave the roundabout"
   elseif (man.StageClass == "roundabout_export") then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         s = ""
      else
         s = "Deixe a rotunda na " .. spellOrdinalNumber(man.CurrentRoundaboutExitNum) .. " saída"   --"Go through the " .. .. " exit"
         if (man.CurRoundaboutExitPhaseCombinedWithNext) then
            s = concat(s, ", em seguida pegue a saída ", spellOrdinalNumber(man.CurrentRoundaboutExitNum + 1)) .. " e saia da rotatória"   --", then take the ",  .. " exit and leave the roundabout"
         end
         man._NoCombination = true
      end
   elseif (man.StageClass == "roundabout_exit") then
      if (man.project ~= "zeekr" or guidanceLevel > 1) then
         s = "Agora " .. spellRoundaboutDirection(man)    -- and leave the roundabout
      end
   end
   return s
end

function processRoundaboutActionCombined(man)
   local s = spellDistancePart(man)
   s = conc(s, "saia da rotatória na saída", spellOrdinalNumber(man.Nth))  --"leave the roundabout at the",..,"exit"

   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
      s = conc("Em breve", s)          --soon
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
      s = "Chegou ao "                --You have reached 
   else
      s = spellDistancePart(man)
      if (onlyDirection) then
         s = ""
      end
      s = s .. " chegará ao "    -- you will have reached 
   end

   s = concat(s, " ", spellDestinationName(man))
   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
         s = conc("Em breve", s)          --soon
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
   if (man.FromProps.Area.ferry) then
      s = "ferry"                   --the ferry terminal
   elseif (man.FromProps.Area.ipd) then
      s = conc(s, "estações de carregamento, por favor carregue a", spellNumber(man.Nth))      --the charging station, please charge to
      s = s .. "%"             --%
   elseif (man.FromProps.Area.crime and man.Stage == 1) then
      s = "Approaching Setel gas station, do you need Setel service?"     --Approaching Setel gas station, do you need Setel service?
   elseif (man.Nth == 0) then
      s = "destino"                     --the destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", "Está à esquerda")         --It is on the left
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", "Está à direita")          --It is on the right
      end
   elseif (isInRanges(DestNumber, 1, 100)) then
      s = conc(s, "destino intermediário", spellNumber(DestNumber))               --the intermediate destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", "Está à esquerda")         --It is on the left.
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", "Está à direita")          --It is on the right.
      end
   end

   if ((man.Nth == 0) and (man.Stage == 1) and (not isAvailable(man.previous)) ) then
      s = concat(s, ". ", "")                --Thank you for driving
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
         s = "Portagem à frente"                  --Soon reach toll gate
      end
   elseif (guidanceLevel == 3) then
      if (man.Stage == 2) then
         s = "Portagem à frente"
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 3) ) then
         s = "Portagem à frente"
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = "Portagem à frente"                 --Soon reach toll gate
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = "Portagem à frente"                           --Toll gate ahead
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
         s = "Túnel à frente"             --Soon tunnel ahead
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = "Túnel à frente"             --Soon tunnel ahead
      elseif (man.project == "zeekr" and man.Stage == 2 and guidanceLevel == 2) then
         s = "Túnel à frente"             --Soon tunnel ahead
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = "Túnel à frente"                       --tunnel ahead
      end
   end
   return s
end

function processEnterActionWarning(man, AnnouncementIsNotEmpty)
   local s = "Por favor, observe as regras de trânsito aplicáveis"      --Please follow actual traffic regulations
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
         s = conc("Em breve", s)              --soon
   end
   return s
end

function processMotorwayEnterAction(man)
   local s = spellDistancePart(man)
   s = conc(s, "", spellDirectionPart(man))
   s = conc(s, "", "")         --, you have reached the highway entrance
   s = conc(s, "", spellMotorWayEnterNamesPart(man, true))
   return s
end


function spellMotorWayEnterNamesPart(man, useRoadNameOrNumber)
   local s = ""
   local signPostName = getDirectionName(man)
   -- motorway entry
   if (man.HintProps.Hint.motorway_entry) then
       if (#signPostName > 0) then
         s = conc(s, ", em direcção a", signPostName)            --towards
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
      s = "Be careful of merging traffic"         --Be careful of merging traffic
      if ((man.DirOClock ~= 0) and (man.DirOClock ~= 6)) then
         s = conc(s, "de", spellRoughDirection(man))            --from the
      end
   end
   return s
end


function processFollowRoadAction(man)
   local s = ""
   if (man.Distance > 0) then
      s = conc(s, "durante", spellDistanceAndUnit(man, countryCode, unitArray))      --Within about
   end
   s = conc("Siga a estrada", s)                --Please follow current road
   return s
end

function processDPTrafficLightAction(man)
   local s = "semáforo interseção à frente, Siga em frente"        --Traffic light intersection ahead, please go straight
   if (man.Stage == 1 and man.TrafficLightIntersectionCount > 0) then
      s = "Siga em frente pela semáforo " .. spellOrdinalNumber(man.TrafficLightIntersectionCount)   --"Go straight through the " .. " traffic light"
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
               s = conc(dir, ".")
            end
         end
         s = conc(s, "Recomendação: Pegue")              --Recommend taking the
      else
         s = conc(s, "Por favor, pegue a")                --Please take the
      end
      local pos = ""
      if ((man.IsInc2nd) and (man.IsInc3rd)) then
         s = conc(s, "a segunda ou terceira faixa")       --second and third lanes
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (man.IsInc2nd) then
         s = conc(s, "segunda faixa")                     --second lane
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (man.IsInc3rd) then
         s = conc(s, "terceira faixa")                    --third lane
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 1)) then
         s = conc(s, "a faixa mais à esquerda")             --leftmost lane
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 2)) then
         s = conc(s, "a faixa mais à direita")              --rightmost lane
      else
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         if (man.BestRecomNum > 1) then
            local bestNumber = convToString(man.BestRecomNum)
            s = conc(s, "una das", bestNumber, "faixas", pos)             --lanes
         elseif (man.RecomNum > 1) then
            local number = convToString(man.RecomNum)
            s = conc(s, "una das", number, "faixas", pos)                 --lanes
         else
            s = conc(s, "a faixa", pos)                          --lane
         end
      end
   else
      if (not man.IsManeuver) then
         local dir = spellDirection(man.RecomLaneDir)
         if (dir ~= "") then
            s = conc(dir, ".")
         end
      end
      s = conc(s, "Pegue")                   --Please take the
      local pos = spellPosition(man.isContiLaneVoice, man.RecomLanePos)
      if (man.RecomNum > 1) then
         local number = convToString(man.RecomNum)
         s = conc(s, "una das", number, "faixas", pos)                    --lanes
      else
         s = conc(s, "a faixa", pos)                             --lane
      end
   end
   return s
end

function spellDirection(dir)
   local s = ""
   if (dir == 1) then
      s = "vire acentuadamente à esquerda"          --Turn sharp left
   elseif (dir == 2) then
      s = "vire à esquerda"                    --Turn left
   elseif (dir == 3) then
      s = "vire ligeiramente à esquerda"       --Turn slight left
   elseif (dir == 4) then
      s = "vire acentuadamente à direita"           --Turn sharp right
   elseif (dir == 5) then
      s = "vire à direita"                     --Turn right
   elseif (dir == 6) then
      s = "vire ligeiramente à direita"        --Turn slight right
   elseif (dir == 7) then
      s = "vá em frente"                       --Go straight on
   elseif (dir == 8) then
      s = "faça inversão de marcha"                   --Make a u-turn
   end
   return s
end

function spellPosition(isContiLaneVoice, position)
   local s = ""
   if (isContiLaneVoice) then
      if (position == 1) then
         s = "esquerdas"         --on the left
      elseif (position == 2) then
         s = "direitas"          --on the right
      elseif (position == 3) then
         s = "à centro"           --on the center
      end
   else
      if (position == 1) then
         s = "da esquerda"                   --left
      elseif (position == 2) then
         s = "da direita"                    --right
      elseif (position == 3) then
         s = "da central"                     --center
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
      s = conc("a", spellDistanceAndUnit(man, countryCode, unitArray))         --In
   end
   s = conc(s, getAreaType(man.AreaType), "")             --ahead
   if ((nextDistKm >= 1) and testDistance4(nextDistM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray, true) ~= "")) then
      if (man.AreaType == 1000) then
         s = conc(s, ". A seguinte área de serviço fica a", spellDistanceAndUnit(man, countryCode, unitArray, true))                --. The next one is in
      elseif (man.AreaType == 1001) then
         s = conc(s, ". A seguinte área de estacionamento fica a", spellDistanceAndUnit(man, countryCode, unitArray, true))         --. The next one is in
      end
   end
   return s
end

function getAreaType(type)
   local s = ""
   if (type == 1000) then
     s = "Área de serviço"                 --service area
   elseif (type == 1001) then
     s = "Área de estacionamento"          --parking area
   end
   return s
end

function processWNHighAccidentAreaAction()
   local s = "Zona de alto acidente à frente. Por favor, conduza com cuidado"           --High accident area ahead. Please drive carefully
   return s
end

function processWNSchoolZoneAction()
   local s = "Zona escolar à frente. Por favor, conduza com cuidado"       --School zone ahead. Please drive carefully
   return s
end

function processWNRailwayAction()
   local s = "Passagem de nível à frente. Por favor, conduza com cuidado"           --Railway ahead. Please drive carefully
   return s
end

function processWNDestParkingAction(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel == 2) then
      s = "Estacionamento encontrado perto do destino, toque em Ir"   --The destination parking lot has been recommended for you, click Go
   end
   return s
end

function processWNDestChargingStationAction()
   local s = "Estações de carregamento encontradas perto do destino"  --Recommendations of charging stations near the destination
   return s
end

function processWNTunnelAction()
   local s = "Túnel à frente"                 --Tunnel ahead
   return s
end

function processWNGpsAction()
   local s = "The current GPS signal is weak, and the position update may not be timely"      --The current GPS signal is weak, and the position update may not be timely
   return s
end

function processWNLaneMergeAction(man)
   local s = ""
   if (man.LaneMergeType == 1) then
      s = "Tráfego se fundindo da esquerda"                    --Left side
   elseif (man.LaneMergeType == 2) then
      s = "Tráfego se fundindo da direita"                     --Right side
   end
   s = conc(s, "à frente. Por favor, conduza com cuidado")                 --merger ahead. Please drive carefully
   return s
end

function processWNTrafficCameraAction(man)
   local s = ""
   if (man.CameraType == 1) then
      s = "Câmara de velocidade a"                  --A speed camera is
   elseif (man.CameraType == 7) then
      s = "Câmara de velocidade e de semáforo a"     --Speed and red light cameras are
   elseif (man.CameraType == 3) then
      s = "Câmara de semáforo a"                --A red light camera is
   elseif (man.CameraType == 11) then
      s = "Zona de verificação de velocidade média a"   --You are driving into a speed measurement section
   elseif (man.CameraType == 12) then
      s = "Fim da zona de verificação de velocidade média a"   --You are leaving the speed measurement section
   else
      return s
   end
   local currentDis = man.Distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
      if (man.CameraType == 12) then
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray))
      else
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray), "mais à frente")    --ahead
      end
   else
      s = conc(s, "mais à frente")                               --ahead
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
      s = conc(s, ". Limite de velocidade", limitedSpeed, unit)  --". The limit is",.., "km/h"
   elseif ((man.CameraType == 7) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite de velocidade", limitedSpeed, unit)  --". The limit is",.., "km/h"
   elseif (man.CameraType == 3) then
      s = conc(s, ".")                  --. Please drive carefully
   elseif ((man.CameraType == 11) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite de velocidade", limitedSpeed, unit)  --". The limit is",.., "km/h"
   end
   return s
end

function processWNCurveAction(man)
   local s = ""
   if (man.IsContiCurve) then
      s = "Curva contínua à frente. Por favor, conduza com cuidado"       --Continuous curve ahead. Please drive carefully
   else
      s = "Curva dupla à frente. Por favor, conduza com cuidado"                --Curve ahead. Please drive carefully
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
         s = conc("A", spellDistanceAndUnit(man, countryCode, unitArray), ",")           --In about
      end
      s = conc(s, "há um", content)                               --there is a
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
   s = "Está a exceder o limite de velocidade de " .. speedLimitStr .. " " .. unit  --"Speed limit is " ..  "km/h. You are exceeding the speed limit"
   return s
end

function processWNBorderAction(man)
   local s = ""
   if (man.BorderName ~= "") then
      s = conc("Entrada em", man.BorderName)            --Entering
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
         s = "The current lane is forbidden from " .. spellTimeForInappLane(man.FromHour, man.FromMinutes) .. " to " .. spellTimeForInappLane(man.ToHour, man.ToMinutes)             --"The current lane is forbidden from " .. .. " to " ..
      end
   else
      -- other reasons
      if (spellInappLaneReason(man.InappLaneReason) ~= "") then
         s = "The current lane is dedicated to " .. spellInappLaneReason(man.InappLaneReason)   --The current lane is dedicated to 
      end
   end
   return s
end

function processWNNarrowLaneAction(man)
   local s = ""
   if (man.NarrowLaneVoiceType == 1) then
      if (man.NarrowLaneDir == 1) then
         s = "Forward left lane narrows"              --Forward left lane narrows
      elseif (man.NarrowLaneDir == 2) then
         s = "Forward right lane narrows"             --Forward right lane narrows
      elseif (man.NarrowLaneDir == 3) then
         s = "Forward both lanes narrow"              --Forward both lanes narrow
      else
         return s
      end
      s = s .. ". Por favor, conduza com cuidado"                 --. Please drive carefully
   elseif (man.NarrowLaneVoiceType == 2) then
      if (man.NarrowLaneDir == 1) then
         s = "lado Esquerdo"                                --left side
      elseif (man.NarrowLaneDir == 2) then
         s = "lado Direito"                                 --right side
      else
         return s
      end
      s = "The road ahead becomes narrower. Please change lanes to the " .. s             --The road ahead becomes narrower. Please change lanes to the 
   end
   return s
end

function processEVLowBattery(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      s = "Bateria fraca, por favor carregue"            --Low battery, please charge
   end
   return s
end

function processEVFewChargingStations(man)
   local s = ""
   if (man.GuidanceLevel > 1) then
      s = "Há poucas estações de carregamento. Por favor, carregue atempadamente"        --There are few charging stations. Please charge timely
   end
   return s
end

function processSRPSuccess()
   local s = "Smart charing route planning has been completed"
   return s
end

function processSRPFail()
   local s = "Nível da bateria insuficiente para chegar ao destino. Nenhuma estação de carregamento encontrada ao longo da rota, por favor, carregue atempadamente"  -- Battery level not sufficient to arrive at the destination. No charging station found along the route, please charge timely
   return s
end

function processBeepDRGPopup(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      local minutes = convToString(man.Minutes)
      s = conc("Nova rota encontrada.", minutes, "minutos serão poupados")   --"A new route found,",.., "minutes will be saved"
   end
   return s
end

function spellTimeForInappLane(hour, min)
   local s = ""
   if (isNumber(hour) and isNumber(min)) then
      if (min == 0) and (hour == 0) then
         s = "meia-noite"                    --midnight
      elseif (min == 0) and isInRanges(hour, 1, 11) then
         s = hour .. " horas"                -- o'clock
      elseif (min == 0) and (hour == 12) then
         s = "meio-dia"                      --noon
      elseif (min == 0) and isInRanges(hour, 13, 23) then
         s = hour .. " horas"                -- o'clock
      elseif (isInRanges(min, 1, 9)) then
         s = hour .. " o " .. min .. ""      -- o 
      else
         s = hour .. " " .. min .. ""
      end
   end
   return s
end

function spellInappLaneReason(reason)
   local s = ""
   if (reason == 2) then
      s = "HOV"               --HOV
   elseif (reason == 3) then
      s = "taxis"             --taxis
   elseif (reason == 4) then
      s = "buses"             --buses
   elseif (reason == 5) then
      s = "ambulances"        --ambulances
   end
   return s
end

function getTrafficContent(kind)
   local s = ""
   if (kind == 0) then
      s = "engarrafamento"                          --congestion
   elseif (kind == 1) then
      s = "obstáculos na estrada"                                 --obstacle
   elseif (kind == 2) then
      s = "Acidente"                                  --accident
   elseif (kind == 3) then
      s = "obras"                           --road work
   elseif (kind == 4) then
      s = "estreitamento de faixa"                          --narrow lanes
   elseif (kind == 5) then
      s = "estrada escorregadia"                      --slippery road
   elseif (kind == 6) then
      s = "aquaplanagem"                              --aquaplaning
   elseif (kind == 7) then
      s = "incêndio"                                  --fire
   elseif (kind == 8) then
      s = "desabamento de estrada"                    --road subsidence
   elseif (kind == 9) then
      s = "veículo avariado"                   --disabled vehicle
   elseif (kind == 10) then
      s = "estrada fechada"                           --road closed
   elseif (kind == 11) then
      s = "condições meteorológicas extremas"             --extreme weather conditions
   elseif (kind == 12) then
      s = "pessoas na estrada"                       --reckless persons
   elseif (kind == 13) then
      s = "controlo de altura desencadeado"      --over height warning system triggered
   elseif (kind == 14) then
      s = "regras de tráfego alteradas"               --traffic regulations changed
   elseif (kind == 15) then
      s = "evento principal"                         --major event
   elseif (kind == 16) then
      s = "serviço não disponível"                    --service not usable
   elseif (kind == 17) then
      s = "veículo lento"                --slow moving vehicle
   elseif (kind == 18) then
      s = "fim de fila perigoso"                      --dangerous end of queue
   elseif (kind == 19) then
      s = "risco de incêndio"                         --risk of fire
   elseif (kind == 20) then
      s = "atraso de tempo"               --extra security measures
   elseif (kind == 21) then
      s = "controlo policial"                --police checkpoint
   elseif (kind == 22) then
      s = "equipamento rodoviário defeituoso"     --malfunctioning roadside equipment
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
      if (man.project ~= "zeekr" or guidanceLevel > 1) then
         s = "Por favor, siga os regulamentos de trânsito actuais"                   --Please follow actual traffic regulations
      end
   elseif (man.Action == "enter_better_route") then
      if (guidanceLevel > 1) then
         s = "Have entered the better route"                    --Have entered the better route
      else
         s = ""
      end
   elseif (man.Action == "blocked_road") then
      if (guidanceLevel > 1) then
         s = "Road blocked ahead, guide with new route"         --Road blocked ahead, guide with new route
      else
         s = ""
      end
   elseif (man.Action == "time_regulation_road") then
      if (guidanceLevel > 1) then
         s = "Time regulation ahead, guide with new route"      --Time regulation ahead, guide with new route
      else
         s = ""
      end
   elseif (man.Action == "no_guidance_active") then
      s = "Por favor, defina a rota"                                    --Please set route
   elseif (man.Action == "drive_to_route") then
      s = "Avance para a rota mostrada. Orientação para destino está a começar"    --Please follow the route. Guidance to destination is then starting
   elseif (man.Action == "ti_route_changed") then
      s = "Optimal route is updated"                         --Optimal route is updated
   elseif (man.Action == "version") then                     --special version info
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
         s = conc(s, " A navegação está a começar")          -- Guidance to destination is starting
      elseif (isInRanges(DestNumber, 1, 5)) then
         s = conc(s, " A navegação para o destino intermédio", spellNumber(DestNumber), "está a começar")    --" Guidance to intermediate destination", .., "is starting"
      end
      local aTime = spellTime(man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode)
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
   if (getTableValueCount(man.WeatherTypes) > 0) then
      local weatherTypeCount = 1;
      s = conc(s , " Tempo no destino: ")    -- Destination Weather is 
      for key, weatherType in pairsSortedByKeys(man.WeatherTypes) do
         if (weatherType ~= nil) then
            if (weatherTypeCount > 1) then
               s = conc(s, " para ")               -- to 
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
         s = "Avance para a rota mostrada"           --Please follow the road
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
   s = "Agora Continue na estrada principal"                --Soon Keep following main road
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
   elseif (man.Action == "wn_school_zone") then
      s = processWNSchoolZoneAction()
   elseif (man.Action == "wn_railway") then
      s = processWNRailwayAction()
   elseif (man.Action == "wn_dest_parking") then
      s = processWNDestParkingAction(man)
   elseif (man.Action == "wn_dest_charging_station") then
      s = processWNDestChargingStationAction()
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
      s = processSRPFail()
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
      s = concat(mainString, " e depois ", s)                 -- and then 
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